package com.e3expo.e3.admin.services;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.e3expo.e3.common.Constants;
import com.e3expo.e3.common.SerialNo;
import com.e3expo.e3.enumration.EnumFileLimit;
import com.e3expo.e3.exceptions.FileException;
import com.e3expo.e3.util.DateUtils;

/**
 * 文件上传
 * 
 * @author lizy
 *
 */
@Service
public class FileService {
	/**
	 * 单文件上传 图片格式验证、大小验证，目前尚未添加
	 * 
	 * @param attachment
	 * @return
	 */
	public static String fileUpload(MultipartFile attachment) {
		return SaveFile(attachment);
	}

	/**
	 * 多文件上传 图片格式验证、大小验证，目前尚未添加
	 * 
	 * @param attachments
	 * @return
	 */
	public static List<String> fileUploads(MultipartFile[] attachments) {
		List<String> fileNameList = new ArrayList<>();
		for (MultipartFile multipartFile : attachments) {
			String fileName = SaveFile(multipartFile);
			fileNameList.add(fileName);
		}

		return fileNameList;
	}

	private static String SaveFile(MultipartFile attachment) {
		if(attachment==null ||attachment.getSize()==0) {
			throw new FileException(FileException.ErrorCode.FILE_IS_NULL);
		}
		String fileOriginalFileName=attachment.getOriginalFilename();
		String fileType=fileOriginalFileName.substring(fileOriginalFileName.lastIndexOf("."));
		if(!EnumFileLimit.isFileFormatIncluded(fileType)) {
			throw new FileException(FileException.ErrorCode.FILE_TYPE_WRONG);
		}
		if(!EnumFileLimit.isFileSizeRight(fileType,attachment.getSize())){
			throw new FileException(FileException.ErrorCode.FILE_SIZE_WRONG);
		}
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		String riqi = dateFormat.format(now);
		String dicAppend = "/" + riqi + "/";
		String dic = Constants.UPLOAD_FILE_FOLDER_FULL_PATH + dicAppend;
		File fileDic = new File(dic);
		if (fileDic.mkdirs()) {
			System.out.println("多级层文件夹创建成功！创建后的文件目录为：" + fileDic.getPath() + ",上级文件为:" + fileDic.getParent());
		}
		String fileMinName = DateUtils.getMsTimeStamp(now) + "-" + fileOriginalFileName;
		String realPath = dic + fileMinName;
		String fileName = dicAppend + fileMinName;
		File source = new File(realPath);
		try {
			attachment.transferTo(source);
		} catch (Exception e) {
			String errorMsg = "Upload file " + source.getAbsoluteFile() + " Error!" + e.getMessage();
			throw new RuntimeException(errorMsg);

		}
		return fileName;
	}

}
