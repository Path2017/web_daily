package com.e3expo.e3.webapp.common;

import com.e3expo.e3.util.DateUtils;

import java.util.Date;
import java.util.UUID;

import static com.e3expo.e3.common.Constants.UPLOAD_FILE_FOLDER_FULL_PATH;

/**
 * 生成上传图片的路径工具
 */
public class GenerateImagePathUtil {

    private static final String FOLDER_DATE_FORMAT = "yyyyMMdd";

    private static final String fileNameConnector = "_";

    private GenerateImagePathUtil() {
    }

    /**
     * 生成根据图片名生成图片的相对路径
     *
     * @param fileName
     * @return
     */
    public static String getUniqueFilePath(String fileName) {
        if (fileName == null) {
            throw new NullPointerException("filename is null!");
        }
        String folder = DateUtils.dateToString(new Date(), FOLDER_DATE_FORMAT);
        return folder + "/" + UUID.randomUUID() + fileNameConnector + fileName;
    }

    /**
     * 根据文件名，返回上传文件夹中所在的全路径
     *
     * @param relativePath 相对上传文件夹的路径，不能以'/'或者'\'开头
     * @return
     */
    public static String getFullPath(String relativePath) {
        if(relativePath.startsWith("/") || relativePath.startsWith("\\")){
            throw new IllegalArgumentException("imagePath should not start with '/' or '\\'");
        }

        return UPLOAD_FILE_FOLDER_FULL_PATH + "/" + relativePath;
    }
}
