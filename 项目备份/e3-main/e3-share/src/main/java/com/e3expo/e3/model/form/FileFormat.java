package com.e3expo.e3.model.form;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class FileFormat implements Serializable {
	/**
	 * 所传文件的key，即所传文件的数据中的userFileConfig中的name字段对应的信息
	 */
	private String key;
	/**
	 * 多媒体文件接收参数
	 */
	private MultipartFile multipartFile;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

}
