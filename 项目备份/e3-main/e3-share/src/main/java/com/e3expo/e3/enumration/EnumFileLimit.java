package com.e3expo.e3.enumration;

public enum EnumFileLimit {
	JPG(".jpg", 1 * 1024 * 1024), 
	PNG(".png", 1 * 1024 * 1024),
	PDF(".pdf", 1 * 1024 * 1024), 
	JPEG(".jpeg",1 * 1024 * 1024),
	RAR(".rar",	1 * 1024 * 1024), 
	AI(".ai", 1 * 1024 * 1024),
	ZIP(".zip", 1 * 1024 * 1024);
	EnumFileLimit(String fileFormat, long fileSize) {
		this.fileFormat = fileFormat;
		this.fileSize = fileSize;
	}

	private String fileFormat;
	private long fileSize;

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public static boolean isFileFormatIncluded(String name){
		for(EnumFileLimit type : EnumFileLimit.values()){
			if(type.fileFormat.equals(name)){
				return true;
			}
		}
		return false;
	}
	public static boolean isFileSizeRight(String name ,long fileSize){
		for(EnumFileLimit type : EnumFileLimit.values()){
			if(type.fileFormat.equals(name) && type.fileSize>=fileSize){
				return true;
			}
		}
		return false;
	}

}
