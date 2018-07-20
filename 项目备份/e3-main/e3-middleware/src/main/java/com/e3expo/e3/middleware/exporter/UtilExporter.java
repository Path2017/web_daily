package com.e3expo.e3.middleware.exporter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.e3expo.e3.common.Constants;
import com.e3expo.e3.exceptions.FileException;
import com.e3expo.e3.exceptions.FileException.ErrorCode;
import com.e3expo.e3.model.UserFileConfig;
import com.e3expo.e3.model.form.FileFormat;
import com.e3expo.e3.service.interfaces.IUtil;
import com.e3expo.e3.util.DateUtils;

@Component
public class UtilExporter implements IUtil {
	@Autowired
	private DicExporter dicExporter;

	// 上传文件
	public String fileUpload(FileFormat fileFormat) throws IllegalStateException, IOException {
		if (fileFormat == null) {
			throw new FileException(ErrorCode.FILE_IS_NULL);
		}
		if (fileFormat.getKey() == null || fileFormat.getKey().length() == 0) {
			throw new FileException(ErrorCode.FILE_KEY_NOT_EXISTED);
		}
		if (fileFormat.getMultipartFile() == null || fileFormat.getMultipartFile().getSize() == 0) {
			throw new FileException(ErrorCode.FILE_IS_NULL);
		}
		UserFileConfig userFileConfig = this.dicExporter.getUserFileConfigByName(fileFormat.getKey());
		if (userFileConfig == null) {
			throw new FileException(ErrorCode.FILE_KEY_WRONG);
		}
		String fileFileName = fileFormat.getMultipartFile().getOriginalFilename();
		// 获取扩展名并转换成小写
		String extension = (fileFileName.substring(fileFileName.lastIndexOf(".") + 1, fileFileName.length()))
				.toLowerCase();
		String fileNewName = DateUtils.getMsTimeStamp() + "." + extension;
		//保存图片
		//返回图片路径，目前图片实现方式不确定，暂时保留
		return fileNewName;
	}
}
