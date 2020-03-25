package com.mttl.vlms.setting.stt010.dto;

import java.util.List;

import com.mttl.vlms.common.AbstractDto;

/**
 * TrailerDto
 * 
 * 
 *
 */
public class TrailerDto extends AbstractDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6908819914841454495L;

	private Integer id;

	private String trailerType;

	private String description;

	private boolean disabled;

	private String licenseNo;

	private String trailerCode;

	private String barcodeId;

	private String photoName;

	private List<String> uploadedPhoto;

	private String photoList;

	private boolean checkPhoto;

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getTrailerType() {
		return trailerType;
	}

	public void setTrailerType(String trailerType) {
		this.trailerType = trailerType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBarcodeId() {
		return barcodeId;
	}

	public void setBarcodeId(String barcodeId) {
		this.barcodeId = barcodeId;
	}

	public String getTrailerCode() {
		return trailerCode;
	}

	public void setTrailerCode(String trailerCode) {
		this.trailerCode = trailerCode;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public List<String> getUploadedPhoto() {
		return uploadedPhoto;
	}

	public void setUploadedPhoto(List<String> uploadedPhoto) {
		this.uploadedPhoto = uploadedPhoto;
	}

	public String getPhotoList() {
		return photoList;
	}

	public void setPhotoList(String photoList) {
		this.photoList = photoList;
	}

	public boolean isCheckPhoto() {
		return checkPhoto;
	}

	public void setCheckPhoto(boolean checkPhoto) {
		this.checkPhoto = checkPhoto;
	}

}
