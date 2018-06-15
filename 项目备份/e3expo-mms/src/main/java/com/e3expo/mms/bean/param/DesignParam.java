package com.e3expo.mms.bean.param;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class DesignParam implements Serializable {

	private int ownerID;
	private int cityID;
	private byte professionID;
	private byte structureID;
	private byte exhibitionTypeID;
	private byte openSides;
	private int priceLowerLimit;
	private int priceUpperLimit;
	private int area;

//	private int designId;
	private int designID;
	private long modifiedTime;

	private MultipartFile[] sketchFiles;
	private MultipartFile zipFile;
	private String primitiveName;

	public DesignParam() {
	}

	public long getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(long modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public int getDesignID() {
		return designID;
	}

	public void setDesignID(int designID) {
		this.designID = designID;
	}

	public int getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}

	public int getCityID() {
		return cityID;
	}

	public void setCityID(int cityID) {
		this.cityID = cityID;
	}

	public byte getProfessionID() {
		return professionID;
	}

	public void setProfessionID(byte professionID) {
		this.professionID = professionID;
	}

	public byte getStructureID() {
		return structureID;
	}

	public void setStructureID(byte structureID) {
		this.structureID = structureID;
	}

	public byte getExhibitionTypeID() {
		return exhibitionTypeID;
	}

	public void setExhibitionTypeID(byte exhibitionTypeID) {
		this.exhibitionTypeID = exhibitionTypeID;
	}

	public byte getOpenSides() {
		return openSides;
	}

	public void setOpenSides(byte openSides) {
		this.openSides = openSides;
	}

	public int getPriceLowerLimit() {
		return priceLowerLimit;
	}

	public void setPriceLowerLimit(int priceLowerLimit) {
		this.priceLowerLimit = priceLowerLimit;
	}

	public int getPriceUpperLimit() {
		return priceUpperLimit;
	}

	public void setPriceUpperLimit(int priceUpperLimit) {
		this.priceUpperLimit = priceUpperLimit;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public MultipartFile[] getSketchFiles() {
		return sketchFiles;
	}

	public void setSketchFiles(MultipartFile[] sketchFiles) {
		this.sketchFiles = sketchFiles;
	}

	public MultipartFile getZipFile() {
		return zipFile;
	}

	public void setZipFile(MultipartFile zipFile) {
		this.zipFile = zipFile;
	}

	public String getPrimitiveName() {
		return primitiveName;
	}

	public void setPrimitiveName(String primitiveName) {
		this.primitiveName = primitiveName;
	}
}
