package com.e3expo.mms.bean.service;

import com.e3expo.mms.Exception.AccountIsAlreadyRegisteredException;
import com.e3expo.mms.Exception.AccountIsNotExistsException;
import com.e3expo.mms.Exception.UserAlreadyLoginException;
import com.e3expo.mms.Exception.UserIsNotInSessionException;
import com.e3expo.mms.Exception.UserIsNotLoginException;
import com.e3expo.mms.bean.dao.ApplicationDao;
import com.e3expo.mms.bean.dao.CityDao;
import com.e3expo.mms.bean.dao.DesignDao;
import com.e3expo.mms.bean.dao.RoleDao;
import com.e3expo.mms.bean.dao.UserDao;
import com.e3expo.mms.bean.model.City;
import com.e3expo.mms.bean.model.Design;
import com.e3expo.mms.bean.model.Role;
import com.e3expo.mms.bean.model.User;
import com.e3expo.mms.bean.param.UserListParam;
import com.e3expo.mms.bean.param.UserParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import sun.security.krb5.internal.crypto.Des;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.e3expo.mms.bean.enumeration.RoleEnum.DESIGNER;
import static com.e3expo.mms.bean.enumeration.RoleEnum.LOCAL_ADMIN;
import static com.e3expo.mms.bean.enumeration.RoleEnum.SYS_ADMIN;

@Service
public class UserService {

    private final UserDao userDao;
    private final CityDao cityDao;
    private final RoleDao roleDao;
    private final ApplicationDao applicationDao;
    private final DesignDao designDao;

    @Autowired
    public UserService(UserDao userDao, CityDao cityDao, RoleDao roleDao,
                       ApplicationDao applicationDao, DesignDao designDao) {
        this.userDao = userDao;
        this.cityDao = cityDao;
        this.roleDao = roleDao;
        this.applicationDao = applicationDao;
        this.designDao = designDao;
    }

    public int getUserTotalPage() {
        int totalNumber = userDao.getUserTotalNumber();
        return (int) Math.ceil((double) totalNumber / 15);
    }

    //根据页面返回用户的列表
    public ArrayList<User> getUserListByPage(int page) {
        int totalPage = getUserTotalPage();
        if (page <= 0) {
            page = 1;
        }
        if (page > totalPage && totalPage > 0) {
            page = totalPage;
        }
        return userDao.getUserListByPage(page);
    }


    //更新用户密码

    public void updatePassword(String newPassword) throws UserIsNotLoginException,
            UserIsNotInSessionException {
//        newPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        User user = getUserFromSession();
        user.setPassword(newPassword);
        userDao.updatePassword(user.getId(), newPassword);
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.removeAttribute("user");
        session.setAttribute("user", user);
    }


    public boolean checkPassword(String oldPassword) throws UserIsNotLoginException,
            UserIsNotInSessionException {
        User user = getUserFromSession();
        return BCrypt.checkpw(oldPassword, user.getPassword());
    }


    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }


    public boolean checkExistsUserByName(String usernameString) {
        return userDao.checkExistsUserByName(usernameString);
    }


    public void login(String username, String password)
            throws AccountIsNotExistsException,
            UserAlreadyLoginException {
        if (!userDao.checkExistsUserByName(username)) {
            throw new AccountIsNotExistsException();
        }
        Subject subject = SecurityUtils.getSubject();
//        if (subject.isAuthenticated()) {
//            //如果已经登录
//            throw new UserAlreadyLoginException();
//        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
        Session session = subject.getSession();
        User user = userDao.getUserByName(username);
        // todo
        session.setAttribute("user", user);

    }


    private User getUserFromSession() throws UserIsNotLoginException,
            UserIsNotInSessionException {
        Subject subject = SecurityUtils.getSubject();

        if (!subject.isAuthenticated()) {
            throw new UserIsNotLoginException();
        }

        Session session = subject.getSession();

        User user = (User) session.getAttribute("user");

        if (user == null) {
            throw new UserIsNotInSessionException();
        }

        return user;

    }

    /**
     * 根据用户角色不同，查询指定的时间和角色，回显到页面上
     *
     * @param model model
     * @param user  用户信息
     */
    public void createUserPage(Model model, User user) {
        List<City> cityList = new ArrayList<>();
        List<Role> roleList = new ArrayList<>();
        List<Role> allRoles = roleDao.getAllRoles();
        if (SYS_ADMIN.getRoleName().equals(user.getRole().getName())) {
            // 如果是系统管理员，查询城市，角色
            cityList.addAll(cityDao.getAllCities());
            for (Role role : allRoles) {
                if (role.getName().equals(SYS_ADMIN.getRoleName())){
                    continue;
                }
                roleList.add(role);
            }
        }
        if (LOCAL_ADMIN.getRoleName().equals(user.getRole().getName())) {
            // 如果是本地管理员，城市和角色固定。y
            roleList.addAll(allRoles.stream().filter(role -> DESIGNER.getRoleName().equals(role.getName())).collect(Collectors.toList()));
            cityList.add(user.getCity());
        }
        model.addAttribute("cityList", cityList);
        model.addAttribute("roleList", roleList);
    }

    /**
     * 创建用户
     *
     * @param param 表单参数
     */
    public void createUser(UserParam param) throws AccountIsAlreadyRegisteredException {
        // 查询用户名是否被占用
        if (userDao.checkExistsUserByName(param.getPhoneNumber())) {
            throw new AccountIsAlreadyRegisteredException();
        }
        // 添加到用户比表，插入角色表
        User user = new User(param);
        Role role = new Role();
        role.setId(param.getRoleID());
        user.setRole(role);
        user.setCreateTime(System.currentTimeMillis());
        userDao.createUser(user);
        if (param.getRoleID() == LOCAL_ADMIN.getRoleId()) {
            // 如果是本地管理员角色，就要向application表中添加本地设计图的默认许可
            List<Design> localDesigns = designDao.getLocalDesignsExceptSysAdminCreated(param.getCityID());
            if (localDesigns == null || localDesigns.size() == 0) {
                return;
            }
            List<Integer> designIds = localDesigns.stream().map(Design::getId).collect(Collectors.toList());
            applicationDao.defaultInsertForLocalAdmin(designIds, user.getId());
        }

    }


    /**
     * 分页条件查询用户信息
     *
     * @param param 条件和分页参数
     * @param model model
     * @param user  用户信息
     */
    public void pageConditionQuery(UserListParam param, Model model, User user) {
        String loginRole = user.getRole().getName();
        List<Role> allRoles = roleDao.getAllRoles();
        if (param.getCityID() == 0) {
            if (LOCAL_ADMIN.getRoleName().equals(loginRole)) {
                param.setCityID(user.getCityID());
            }
        }
        List<Byte> queryRoleList = new ArrayList<>();
        if (param.getRoleID() == 0) {
            if (LOCAL_ADMIN.getRoleName().equals(loginRole)) {
                // 如果是本地管理员，就只添加一个designer
                queryRoleList.addAll(allRoles.stream()
                        .filter(role -> role.getName().equals(DESIGNER.getRoleName()))
                        .map(Role::getId).collect(Collectors.toList()));
            } else if (SYS_ADMIN.getRoleName().equals(loginRole)) {
                // 如果是系统管理员，就只添加除了系统管理员角色
                queryRoleList.addAll(allRoles.stream()
                        .filter(role -> !role.getName().equals(SYS_ADMIN.getRoleName()))
                        .map(Role::getId).collect(Collectors.toList()));
            }
        } else {
            queryRoleList.add(param.getRoleID());
        }
        param.setRoleIDList(queryRoleList);

        param.setTotal(userDao.getUserTotalNumberByParam(param));

        param.setData(userDao.pageConditionQuery(param));
        // 将数据和分页信息添加到参数其中
        model.addAttribute("page", param);

    }

    /**
     * 根据用户ID将指定用户信息set进入model中
     *
     * @param model  model
     * @param userID 用户ID
     */
    public void setUserInfoByID(Model model, Integer userID) {
        User user = userDao.getUserById(userID);
        model.addAttribute("user", user);
    }

    /**
     * 更新用户信息
     *
     * @param param 用户信息参数
     */
    public void updateUser(UserParam param) throws AccountIsAlreadyRegisteredException {
        User userWithPhone = userDao.getUserByName(param.getPhoneNumber());
        if (userWithPhone != null && userWithPhone.getId() != param.getUserID()) {
            throw new AccountIsAlreadyRegisteredException();
        }
        param.setModifiedTime(System.currentTimeMillis());
        userDao.updateUserInfo(param);
        roleDao.updateUserRole(param.getUserID(), param.getRoleID());
    }

    /**
     * 更新用户在职/离职状态
     *
     * @param userID     userID
     * @param isResigned isResigned
     */
    public void updateIsResignedByUserID(int userID, byte isResigned) {
        UserParam userParam = new UserParam();
        userParam.setUserID(userID);
        userParam.setIsResigned(isResigned);
        userDao.updateUserInfo(userParam);
    }

    public boolean checkExistsUserByPhoneNumber(String phoneNumber) {
        return userDao.checkExistsUserByPhoneNumber(phoneNumber);
    }
}
