package com.mttl.vlms.web.setting.stt010;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.common.Utils;
import com.mttl.vlms.setting.stt010.dto.TrailerDto;
import com.mttl.vlms.setting.stt010.service.TrailerService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;
import com.mttl.vlms.web.common.FileHandler;

/**
 * TrailerInputBean
 * 
 * 
 */
@ManagedBean(name = "TrailerInputBean")
@ViewScoped
public class TrailerInputBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{TrailerService}")
	private TrailerService trailerService;

	private TrailerDto trailerDto;

	private User loginUser;

	private UploadedFile uploadedPhoto;

	private List<String> uploadedFileNameList;

	private UploadedFile file;

	private String sourcePath;

	@ManagedProperty(value = "#{APP_CONFIG}")
	private Properties APP_CONFIG;

	@PostConstruct
	public void init() {

		trailerDto = new TrailerDto();
		trailerDto.setTrailerCode(trailerService.getNextTrailerId());
		trailerDto.setBarcodeId(trailerDto.getTrailerCode());
		loginUser = (User) getSessionParam(CommonConstants.LOGIN_USER);
		uploadedFileNameList = new ArrayList<String>();
		sourcePath = Utils.getSystemPath() + "temp//" + "trailer-saved-photos//" + trailerDto.getBarcodeId().toString();

	}

	public String inputTrailer() throws IOException {
		int countTrailerLicense = trailerService.checkDuplicateLicenseNo(trailerDto);
		int countLicenseId = trailerService.checkDuplicateTrailerId(trailerDto);
		if (countTrailerLicense > 0 || countLicenseId > 0) {
			if (countTrailerLicense > 0) {
				addErrorMessage(null, "MSG_CMN_010", trailerDto.getLicenseNo());
			}
			if (countLicenseId > 0) {
				addErrorMessage(null, "MSG_CMN_010", trailerDto.getTrailerCode());
			}

			return null;
		}

		trailerDto.setPhotoName(Utils.listToStringConcat(uploadedFileNameList));

		trailerService.insertTrailer(trailerDto, loginUser.getUserID());
		addInfoMessage(null, "MSG_CMN_002", trailerDto.getTrailerCode());
		keepSetMessage();
		if (null != trailerDto.getPhotoName()) {
			uploadPhotoFolder(trailerDto);
		}
		return "trailerList";
	}

	public void uploadPhoto(FileUploadEvent e) throws IOException {

		uploadedPhoto = e.getFile();
		String fileName = FilenameUtils.getName(uploadedPhoto.getFileName());
		uploadedFileNameList.add(fileName);
		byte[] bytes = null;
		if (null != uploadedPhoto) {
			bytes = uploadedPhoto.getContents();
			Utils.createDirectory(sourcePath);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(sourcePath + "/" + fileName)));
			stream.write(bytes);
			stream.close();
		}

		for (String name : uploadedFileNameList) {
			addInfoMessage(null, "MSG_CMN_039", name);
			keepSetMessage();
		}
	}

	public void uploadPhotoFolder(TrailerDto trailerDto) throws IOException {
		String destPath = APP_CONFIG.getProperty("IMAGE_BASE_FILEPATH") + APP_CONFIG.getProperty("TRAILER_IMAGES_FOLDER") + trailerDto.getId().toString();
		Utils.createDirectory(destPath);
		FileHandler.copyDirectory(sourcePath, destPath);

	}

	public TrailerDto getTrailerDto() {
		return trailerDto;
	}

	public void setTrailerDto(TrailerDto trailerDto) {
		this.trailerDto = trailerDto;
	}

	public void setTrailerService(TrailerService trailerService) {
		this.trailerService = trailerService;
	}

	public UploadedFile getUploadedPhoto() {
		return uploadedPhoto;
	}

	public void setUploadedPhoto(UploadedFile uploadedPhoto) {
		this.uploadedPhoto = uploadedPhoto;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public List<String> getUploadedFileNameList() {
		return uploadedFileNameList;
	}

	public void setUploadedFileNameList(List<String> uploadedFileNameList) {
		this.uploadedFileNameList = uploadedFileNameList;
	}

	public Properties getAPP_CONFIG() {
		return APP_CONFIG;
	}

	public void setAPP_CONFIG(Properties aPP_CONFIG) {
		APP_CONFIG = aPP_CONFIG;
	}

}
