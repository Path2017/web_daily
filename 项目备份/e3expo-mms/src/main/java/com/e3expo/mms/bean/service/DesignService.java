package com.e3expo.mms.bean.service;

import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.e3expo.mms.Exception.DesignIsNotExistException;
import com.e3expo.mms.Exception.EmptyFileException;
import com.e3expo.mms.Exception.MultipleCoversExistException;
import com.e3expo.mms.Exception.PermissionDeniedException;
import com.e3expo.mms.Exception.SketchDoesNotExistException;
import com.e3expo.mms.Exception.TooManyDesignSketchException;
import com.e3expo.mms.bean.dao.ApplicationDao;
import com.e3expo.mms.bean.dao.CityDao;
import com.e3expo.mms.bean.dao.DesignDao;
import com.e3expo.mms.bean.dao.UserDao;
import com.e3expo.mms.bean.model.Design;
import com.e3expo.mms.bean.model.DesignHistory;
import com.e3expo.mms.bean.model.DesignProfession;
import com.e3expo.mms.bean.model.DesignSketch;
import com.e3expo.mms.bean.model.DesignStructure;
import com.e3expo.mms.bean.model.ExhibitionType;
import com.e3expo.mms.bean.model.User;
import com.e3expo.mms.bean.param.DesignHistoryListParam;
import com.e3expo.mms.bean.param.DesignListParam;
import com.e3expo.mms.bean.param.DesignParam;
import com.e3expo.mms.bean.service.oss.OSSManager;
import com.e3expo.mms.bean.service.oss.listener.PutObjectAndRecordProgressListener;
import com.e3expo.mms.bean.service.oss.listener.PutObjectProgressListener;
import com.e3expo.mms.bean.service.oss.schedule.ScheduleManager;
import com.e3expo.mms.bean.service.oss.schedule.task.PutObjectToOSSTask;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.e3expo.mms.bean.enumeration.BooleanStatusEnum.*;
import static com.e3expo.mms.bean.enumeration.DesignOperationEnum.*;
import static com.e3expo.mms.bean.enumeration.DesignOrderTypeEnum.*;
import static com.e3expo.mms.bean.enumeration.RoleEnum.*;
import static com.e3expo.mms.config.constant.DesignConstant.*;
import static com.e3expo.mms.bean.enumeration.ModelStatusEnum.*;

@Service
public class DesignService implements ApplicationContextAware {

    // 提出常量
    private static final String zipFileKey = "zip-file";
    private static final String sketchFileKey = "sketch";
    private static final String appendFileKey = "append";

    @Autowired
    public DesignService(DesignDao designDao, CityDao cityDao, UserDao userDao, ApplicationDao applicationDao) {
        this.designDao = designDao;
        this.cityDao = cityDao;
        this.userDao = userDao;
        this.applicationDao = applicationDao;
    }
//    private int cover;

    public void setDesignDictionary(Model model) {
        // 查询相关字典，放到model中
        List<DesignProfession> professions = designDao.getDesignProfessions();
        List<DesignStructure> structures = designDao.getDesignStructures();
        List<ExhibitionType> exhibitionTypes = designDao.getExhibitionTypes();
        model.addAttribute("professions", professions);
        model.addAttribute("structures", structures);
        model.addAttribute("exhibitionTypes", exhibitionTypes);
    }


    /**
     * 创建设计图
     *
     * @param param 设计图参数
     * @param user  登录用户
     */
    public void create(DesignParam param, User user) throws IOException {
        //
        if (param.getZipFile().getSize() == 0) {
            throw new EmptyFileException();
        }
        // 先将除OSS key 以外所有字段存入表中
        Design design = new Design(param);
        design.setCreateTime(System.currentTimeMillis());
        design.setPrimitiveName(param.getZipFile().getOriginalFilename());
        designDao.singleInsert(design);
        String folder = String.format("I%06d", design.getId());
        String zipOssKey = folder + "/" + zipFileKey;

        PutObjectAndRecordProgressListener putObjectAndRecordProgressListener =
                (PutObjectAndRecordProgressListener) applicationContext.getBean("putObjectAndRecordProgressListener");
        putObjectAndRecordProgressListener.setDesignId(design.getId());
        putObjectAndRecordProgressListener.setOssKey(zipOssKey);
        putObjectAndRecordProgressListener.setTotalBytes(param.getZipFile().getSize());
        PutObjectRequest putZipFileRequest = new PutObjectRequest(user.getCity().getBucket(), zipOssKey, param.getZipFile().getInputStream())
                .withProgressListener(putObjectAndRecordProgressListener);

        PutObjectToOSSTask putZipTask = new PutObjectToOSSTask(putZipFileRequest);
//                new PutObjectToOSSTask(user.getCity().getBucket(),
//                        zipOssKey, param.getZipFile().getInputStream(), design.getId(), param.getZipFile().getSize());
        ScheduleManager.schedule(putZipTask);

//        client.putObject(user.getCity().getBucket(), zipOssKey, param.getZipFile().getInputStream());

        // 更新oss key
//        designDao.updateZipFileOSSKey(zipOssKey, design.getId());
        // 插入到download表中
        insertIntoDownloadTable(user, design.getId());
        // 处理几个图片的部分
        MultipartFile[] sketchFiles = param.getSketchFiles();
//        List<MultipartFile> sketchFileList = Arrays.asList(sketchFiles);
//        sketchFileList.stream().filter(sketchFile -> !sketchFile.isEmpty()).forEach();
        // flag
        boolean hasCover = false;
        for (int i = 0; i < sketchFiles.length; i++) {
            if (sketchFiles[i].isEmpty()) continue;
            String sketchOSSKey = folder + "/" + sketchFileKey + i;
            DesignSketch designSketch = new DesignSketch(design.getId(), sketchOSSKey);
            if (hasCover) {
                // 如果有了封面
                designSketch.setIsCover(FALSE.getValue());
            } else {
                // 如果没有，更改flag
                hasCover = true;
                designSketch.setIsCover(TRUE.getValue());
            }
            designSketch.setCreateTime(System.currentTimeMillis());
            designSketch.setPrimitiveName(sketchFiles[i].getOriginalFilename());

            designDao.singleInsertSketch(designSketch);

            PutObjectToOSSTask putSketchFileTask =
                    new PutObjectToOSSTask(user.getCity().getBucket(),
                            sketchOSSKey, sketchFiles[i].getInputStream());
            ScheduleManager.schedule(putSketchFileTask);
//            client.putObject(user.getCity().getBucket(), sketchOSSKey, sketchFiles[i].getInputStream());
        }

    }

    /**
     * 根据当前登录用户状态，插入到申请表中
     *
     * @param user     登录用户
     * @param designID 设计图ID
     */
    private void insertIntoDownloadTable(User user, int designID) {
        String loginRole = user.getRole().getName();
        List<Integer> userList = new ArrayList<>();
        int systemAdminId = userDao.getSystemUserId();
        if (loginRole.equals(SYS_ADMIN.getRoleName())) {
            // 如果是系统管理员或者本地管理员创建的图，在下载表中添加本地管理员的
            // 加自己
            userList.add(user.getId());
        } else if (loginRole.equals(LOCAL_ADMIN.getRoleName())) {
            List<User> localAdmins = userDao.getLocalAdminsByCityId(user.getCity().getId());
            if (localAdmins != null && localAdmins.size() > 0) {
                // 加本地管理员
                localAdmins.forEach(localAdmin -> userList.add(localAdmin.getId()));
            }
            // 加系统管理员
            userList.add(systemAdminId);
        } else if (loginRole.equals(DESIGNER.getRoleName())) {
            // 加系统管理员
            userList.add(systemAdminId);
            // 加本地管理员
            List<User> localAdmins = userDao.getLocalAdminsByCityId(user.getCity().getId());
            if (localAdmins != null && localAdmins.size() > 0) {
                localAdmins.forEach(localAdmin -> userList.add(localAdmin.getId()));
            }
            // 加自己
            userList.add(user.getId());
        }
        // 插入到申请表中
        applicationDao.defaultInsert(userList, designID);
    }

    /**
     * 分页条件查询
     *
     * @param param 查询参数
     * @param model model
     * @param user  登录用户
     */
    public void pageConditionQuery(DesignListParam param, Model model, User user) {
        param.setTotal(designDao.getDesignTotalNumberByParam(param));
        List<Design> list = designDao.pageConditionQuery(param);
        // 排序
        sortListByOrderType(param.getOrderTypeValue(), list);

        param.setData(list);
        if (user.getRole().getName().equals(SYS_ADMIN.getRoleName())) {
            // 根据权限：如果是SYS_ADMIN，所有都可以编辑
            param.getData()
                    .forEach(design -> design.setLoginUserHasRight(true));
        } else if (user.getRole().getName().equals(LOCAL_ADMIN.getRoleName())) {
            // 如果是本地管理员，只有本地设计图（除了系统管理员创建的）有编辑权限
            param.getData().stream()
                    .filter(design -> design.getOwner().getId() != 1)
                    .filter(design -> design.getCity().getId() == user.getCity().getId())
                    .forEach(design -> design.setLoginUserHasRight(true));
        } else {
            // 设计师，只有自己创建的有权利
            param.getData().stream()
                    .filter(design -> design.getOwnerID() == user.getId())
                    .forEach(ownDesign -> ownDesign.setLoginUserHasRight(true));
        }
        // 设置临时访问url
        param.getData()
                .forEach(design -> design.getSketchList().stream()
//                        .filter(designSketch -> designSketch.getStatus() == VALID.getValue()
//                                && designSketch.getIsCover() == TRUE.getValue())
                                .filter(designSketch -> designSketch.getStatus() == VALID.getValue())
                                .filter(designSketch -> designSketch.getIsCover() == TRUE.getValue())
                                .forEach(designSketch -> design.setCoverTemporaryUrl(
                                        OSSManager.getInstance().getClient().generatePresignedUrl(
                                                design.getCity().getBucket(),
                                                designSketch.getOssKey(),
                                                new Date(System.currentTimeMillis() + OSS_IMG_EXPIRATION_MILLISECONDS)).toExternalForm()))
                );
        model.addAttribute("page", param);
    }

    /**
     * 根据排序方式值将Design列表谱排序。
     *
     * @param orderType 排序方式值
     * @param list      design列表
     */
    private void sortListByOrderType(byte orderType, List<Design> list) {
        Comparator comparator = null;
        switch (getDesignOrderTypeEnumByValue(orderType)) {
            case VIEWS_DESC:
                comparator = VIEWS_DESC.getComparator();
                break;
            case DOWNLOADS_DESC:
                comparator = DOWNLOADS_DESC.getComparator();
                break;
            case PRICE_DESC:
                comparator = PRICE_DESC.getComparator();
                break;
            case PRICE_ASC:
                comparator = PRICE_ASC.getComparator();
                break;
            case AREA_DESC:
                comparator = AREA_DESC.getComparator();
                break;
            case AREA_ASC:
                comparator = AREA_ASC.getComparator();
                break;
            default:
                break;
        }
        Collections.sort(list, comparator);
    }

    /**
     * 将cityList set到model中
     *
     * @param model model
     */
    public void setCityList(Model model) {
        model.addAttribute("cityList", cityDao.getAllCities());
    }


    /**
     * 将design详细信息插入到model中
     *
     * @param designId 设计图id
     * @param model    model
     * @param user     登录用户
     */
    public void getDesignDetailById(int designId, Model model, User user) {
//        // 插入到历史表
//        DesignHistory viewHistory = new DesignHistory(System.currentTimeMillis(), user.getId(), designId, VIEW_DETAILS.getId());
//        designDao.insertIntoDesignHistory(viewHistory);
//        designDao.increaseViews(designId);
        // 根据id查询详细信息
        Design design = designDao.getDesignDetailById(designId);
        // 查询是否有权利下载
        design.setApplicationStatus(getDesignDownloadStatus(user, designId));
        // 处理示意图部分
        design.getSketchList()
                .forEach(designSketch -> designSketch.setTemporaryUrl(
                        OSSManager.getInstance().getClient().generatePresignedUrl(
                                design.getCity().getBucket(),
                                designSketch.getOssKey(),
                                new Date(System.currentTimeMillis() + OSS_IMG_EXPIRATION_MILLISECONDS)).toExternalForm())
                );
        model.addAttribute("design", design);
    }

    /**
     * 将design详细信息插入到model中
     *
     * @param designId 设计图id
     * @param model    model
     * @param user     登录用户
     */
    public void getDesignDetailByIdIncreaseViews(int designId, Model model, User user) {
        getDesignDetailById(designId, model, user);
        // views + 1:
        DesignHistory viewHistory = new DesignHistory(System.currentTimeMillis(), user.getId(), designId, VIEW_DETAILS.getId());
        designDao.insertIntoDesignHistory(viewHistory);
        designDao.increaseViews(designId);
    }


    /**
     * 获取登录用户对于该图的是否可以下载的状态
     *
     * @param user     登录用户
     * @param designId 设计id
     * @return 判断该用户对于该图的下载状态
     */
    private byte getDesignDownloadStatus(User user, int designId) {

        return applicationDao.getApplicationStatus(designId, user.getId());
    }


    /**
     * 分页查询设计图操作历史
     *
     * @param param 参数
     * @param model model
     */
    public void pageQueryDesignHistory(DesignHistoryListParam param, Model model) {
        param.setTotal(designDao.getCountOfDesignHistory(param.getDesignId()));
        List<DesignHistory> historyList = designDao.pageQueryDesignHistory(param);
        param.setData(historyList);
        model.addAttribute("historyList", historyList);
    }

    /**
     * 将该sketch设为这个设计图的封面
     *
     * @param designId designId
     * @param sketchId sketchId
     * @param user     登录用户
     */
    public void setCover(int sketchId, int designId, User user) {
        List<DesignSketch> sketchList = designDao.getSketchListBySketchId(sketchId);
        List<DesignSketch> oldCover = sketchList.stream()
                .filter(designSketch -> designSketch.getIsCover() == TRUE.getValue())
                .collect(Collectors.toList());
        if (oldCover.size() != 1) {
            throw new MultipleCoversExistException();
        }
        designDao.changeCover(sketchId, oldCover.get(0).getId());
        designDao.insertIntoDesignHistory(new DesignHistory(System.currentTimeMillis(), user.getId(), designId, MODIFY.getId()));

    }


    /**
     * 设置指定设计图是否可见
     *
     * @param designId  设计图id
     * @param isVisible 是否可见
     */
    public void setDesignIsVisible(int designId, byte isVisible) {
        if (designDao.checkDesignExist(designId)) {
            designDao.setDesignIsVisible(designId, isVisible);
        } else {
            throw new DesignIsNotExistException();
        }
    }

    /**
     * 删除指定设计
     *
     * @param designID 设计id
     * @param user     登陆用户
     */
    public void deleteDesignById(int designID, User user) {
        Design target = designDao.getDesignDetailById(designID);
        if (target == null) {
            throw new DesignIsNotExistException();
        }
        String loginRole = user.getRole().getName();
        if (!loginRole.equals(SYS_ADMIN.getRoleName())) {
            if (target.getOwnerID() == 1) {
                // 系统创建的图，只有系统管理员可以删除
                throw new PermissionDeniedException();
            }
            // 如果不是系统管理员
            if ((loginRole.equals(LOCAL_ADMIN.getRoleName()) && user.getCity().getId() != target.getCity().getId())
                    || (loginRole.equals(DESIGNER.getRoleName()) && target.getOwner().getId() != user.getId())) {
                // 如果是本地管理员， 但不是本城市， 或者 不是创建者本人，没有权限操作
                throw new PermissionDeniedException();
            }
        }
        // 从oss删除文件
        List<String> deleteKeys = new ArrayList<>();
        deleteKeys.add(target.getOssKey());
        target.getSketchList().forEach(designSketch -> deleteKeys.add(designSketch.getOssKey()));
        OSSManager.getInstance().getInternalClient().
                deleteObjects(new DeleteObjectsRequest(target.getCity().getBucket()).withKeys(deleteKeys));
        // 修改数据库字段
        if (designDao.checkDesignExist(designID)) {
            // 同时修改申请表和下载表中的字段名
            designDao.deleteDesignById(designID);
        }
        applicationDao.deleteApplicationByDesignId(designID);
    }


    /**
     * 追加sketch
     *
     * @param designId   设计id
     * @param sketchFile 文件
     * @param user       登录用户
     */
    public void appendSketch(int designId, MultipartFile sketchFile, User user) throws IOException {
        if (sketchFile.getSize() == 0) {
            throw new EmptyFileException();
        }
        Design targetDesign = designDao.getDesignDetailById(designId);
        // sketch数量
        int size = targetDesign.getSketchList().size();

        if (size >= 6) {
            throw new TooManyDesignSketchException();
        }
        // 获取名字
        String folder = targetDesign.getOssKey().split("/")[0];
        // key 命名？ append-
        String AppendSketchOssKey = folder + '/' + appendFileKey + "-" + System.currentTimeMillis();

        DesignSketch designSketch = new DesignSketch(designId, AppendSketchOssKey);
        // 如果没有示意图 就把这个示意图设置为封面
        if (size == 0) {
            designSketch.setIsCover(TRUE.getValue());
        } else {
            designSketch.setIsCover(FALSE.getValue());
        }
        designSketch.setCreateTime(System.currentTimeMillis());
        designSketch.setPrimitiveName(sketchFile.getOriginalFilename());

        designDao.singleInsertSketch(designSketch);

        PutObjectToOSSTask putSketchFileTask =
                new PutObjectToOSSTask(targetDesign.getCity().getBucket(),
                        AppendSketchOssKey, sketchFile.getInputStream());
        ScheduleManager.schedule(putSketchFileTask);
//        designDao.insertIntoDesignHistory(new DesignHistory(System.currentTimeMillis(), user.getId(), designId, MODIFY.getId()));
    }

    /**
     * 删除指定图
     *
     * @param sketchId 示意图ID
     * @param designId 设计ID
     * @param user     登陆用户
     */
    public void deleteSketchById(int sketchId, int designId, User user) {
        deleteSketchById(sketchId);
        designDao.insertIntoDesignHistory(new DesignHistory(System.currentTimeMillis(), user.getId(), designId, MODIFY.getId()));
    }

    /**
     * 删除图
     *
     * @param sketchId
     */
    private void deleteSketchById(int sketchId) {
        List<DesignSketch> sketchList = designDao.getSketchListBySketchId(sketchId);
        List<DesignSketch> afterDeleteList = sketchList.stream()
                .filter(designSketch -> designSketch.getId() != sketchId)
                .collect(Collectors.toList());
        DesignSketch target = null;
        for (DesignSketch sketch : sketchList) {
            if (sketch.getId() == sketchId) {
                target = sketch;
            }
        }

        if (target == null) {
            throw new SketchDoesNotExistException();
        }
        Design design = designDao.getDesignDetailById(target.getDesignID());
        // 删除OSS中文件
        OSSManager.getInstance().getInternalClient()
                .deleteObject(design.getCity().getBucket(), target.getOssKey());
        // do delete
        designDao.deleteSketchById(sketchId);
        if (target.getIsCover() == TRUE.getValue() && afterDeleteList != null && afterDeleteList.size() > 0) {
            designDao.changeCover(afterDeleteList.get(0).getId(), sketchId);
        }
    }


    public void modifyDesign(DesignParam param, User user) {
        designDao.modifyDesign(param);
        designDao.insertIntoDesignHistory(new DesignHistory(System.currentTimeMillis(), user.getId(), param.getDesignID(), MODIFY.getId()));
    }

    /**
     * 更换zip file
     *
     * @param designId 设计图ID
     * @param zipFile  压缩包文件
     * @param user
     */
    public void setZipFile(int designId, MultipartFile zipFile, User user) throws IOException {
        if (zipFile.getSize() == 0) {
            throw new EmptyFileException();
        }
        Design target = designDao.getDesignDetailById(designId);
        // 修改字段名
        designDao.updateZipFileName(designId, zipFile.getOriginalFilename());
        // 上传
        PutObjectAndRecordProgressListener putZipFileProgressListener =
                (PutObjectAndRecordProgressListener) applicationContext.getBean("putObjectAndRecordProgressListener");
        putZipFileProgressListener.setDesignId(designId);
        putZipFileProgressListener.setOssKey(target.getOssKey());
        putZipFileProgressListener.setTotalBytes(zipFile.getSize());
        PutObjectRequest putZipFileRequest = new PutObjectRequest(user.getCity().getBucket(), target.getOssKey(), zipFile.getInputStream())
                .withProgressListener(putZipFileProgressListener);

        PutObjectToOSSTask putZipTask = new PutObjectToOSSTask(putZipFileRequest);
//
//        PutObjectToOSSTask putSketchFileTask =
//                new PutObjectToOSSTask(target.getCity().getBucket(),
//                        target.getOssKey(), zipFile.getInputStream());
        ScheduleManager.schedule(putZipTask);
        designDao.insertIntoDesignHistory(new DesignHistory(System.currentTimeMillis(), user.getId(), designId, MODIFY.getId()));

    }

    private final DesignDao designDao;

    private final CityDao cityDao;

    private final UserDao userDao;

    private final ApplicationDao applicationDao;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private ApplicationContext applicationContext;


    /**
     * 删除目标文件, 追加多个文件
     *
     * @param sketchId    原sketchId
     * @param designId    设计图Id
     * @param sketchFiles 缩略图文件数组
     * @param user        登录用户
     */
    public void setSketch(int sketchId, int designId, MultipartFile[] sketchFiles, User user) throws IOException {
        for (int i = 0; i < sketchFiles.length; i++) {
            if (sketchFiles[i].isEmpty()) {
                continue;
            }
            appendSketch(designId, sketchFiles[i], user);
            if (i == 0) {
                // 如果是第一个文件，就更新Sketch
                deleteSketchById(sketchId);
            }
        }
        designDao.insertIntoDesignHistory(new DesignHistory(System.currentTimeMillis(), user.getId(), designId, MODIFY.getId()));
    }

    /**
     * 追加多张图片
     *
     * @param designId    设计ID
     * @param sketchFiles 缩略图文件
     * @param user        登录用户
     */
    public void appendSketches(int designId, MultipartFile[] sketchFiles, User user) throws IOException {
        for (MultipartFile sketchFile : sketchFiles) {
            if (sketchFile.isEmpty()) {
                continue;
            }
            appendSketch(designId, sketchFile, user);
        }
        designDao.insertIntoDesignHistory(new DesignHistory(System.currentTimeMillis(), user.getId(), designId, MODIFY.getId()));
    }
}
