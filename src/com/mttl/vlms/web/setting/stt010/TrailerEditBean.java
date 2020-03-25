package com.mttl.vlms.web.setting.stt010;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.common.Utils;
import com.mttl.vlms.setting.stt010.dto.TrailerDto;
import com.mttl.vlms.setting.stt010.service.TrailerService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;
import com.mttl.vlms.web.common.FileHandler;

/**
 * CountryEditBean
 * 
 * 
 */
@ManagedBean(name = "TrailerEditBean")
@ViewScoped
public class TrailerEditBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 6273397299924214278L;

	@ManagedProperty(value = "#{TrailerService}")
	private TrailerService trailerService;

	private TrailerDto trailerDtoEdit;

	private UploadedFile uploadedPhoto;

	private List<String> uploadedFileNameList;

	private List<String> newUploadedFileNameList;

	private List<String> deletePhotoList;

	private String sourcePath;

	private String newUploadedPath;

	private boolean isFileUpload;

	@ManagedProperty(value = "#{APP_CONFIG}")
	private Properties APP_CONFIG;

	private List<DefaultStreamedContent> images;

	@PostConstruct
	public void init() {
		trailerDtoEdit = (TrailerDto) getSessionParam(CommonConstants.TRAILER_DTO_EDIT);
		if (null == newUploadedFileNameList) {
			newUploadedFileNameList = new ArrayList<String>();
			if (null == trailerDtoEdit.getPhotoName()) {
				newUploadedFileNameList = new ArrayList<String>();
			} else {
				uploadedFileNameList = Arrays.asList(StringUtils.splitPreserveAllTokens(trailerDtoEdit.getPhotoName(), ","));
				newUploadedFileNameList.addAll(uploadedFileNameList);
			}
		}
		deletePhotoList = new ArrayList<String>();
		sourcePath = Utils.getSystemPath() + "temp//" + "trailer-saved-photos//" + trailerDtoEdit.getBarcodeId().toString();
		newUploadedPath = Utils.getSystemPath() + "temp//" + "trailer-saved-photos//" + trailerDtoEdit.getId().toString();
	}

	public void redirect() {
		TrailerDto trailerDto = trailerService.getTrailerByTrailerId(trailerDtoEdit.getId());
		if (trailerDto == null) {
			putSessionParam(CommonConstants.MESSAGE_CODE, "MSG_CMN_011");
			putSessionParam(CommonConstants.MESSAGE_PARAMETER, trailerDtoEdit.getLicenseNo());
			redirect("trailerList.xhtml?faces-redirect=true");
		}
	}

	public String editTrailer() throws IOException {
		if (trailerService.checkDuplicateLicenseNo(trailerDtoEdit) > 0) {
		}
		TrailerDto trailerDto = trailerService.getTrailerByTrailerId(trailerDtoEdit.getId());
		if (trailerDto == null) {
			addErrorMessage(null, "MSG_CMN_011", trailerDtoEdit.getTrailerCode());
			keepSetMessage();
			return "trailerList";
		}

		trailerDtoEdit.setPhotoName(Utils.listToStringConcat(newUploadedFileNameList));
		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		int count = trailerService.updateTrailer(trailerDtoEdit, user.getUserID());

		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_006", trailerDto.getTrailerCode());
		} else {
			addInfoMessage(null, "MSG_CMN_003", trailerDto.getTrailerCode());
		}
		keepSetMessage();
		uploadPhotoFolder(trailerDtoEdit);
		return "trailerList";
	}

	public void uploadPhoto(FileUploadEvent e) throws IOException {
		isFileUpload = true;
		uploadedPhoto = e.getFile();
		String fileName = FilenameUtils.getName(uploadedPhoto.getFileName());
		newUploadedFileNameList.add(fileName);
		byte[] bytes = null;
		if (null != uploadedPhoto) {
			bytes = uploadedPhoto.getContents();
			Utils.createDirectory(newUploadedPath);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(newUploadedPath + "/" + fileName)));
			stream.write(bytes);
			stream.close();
		}
	}

	public void uploadPhotoFolder(TrailerDto trailerDto) throws IOException {
		String destPath = APP_CONFIG.getProperty("IMAGE_BASE_FILEPATH") + APP_CONFIG.getProperty("TRAILER_IMAGES_FOLDER") + trailerDto.getId().toString();
		Utils.createDirectory(destPath);
		for (String deletedFileName : deletePhotoList) {
			FileHandler.forceDelete(destPath + "/" + deletedFileName);
		}
		if (isFileUpload == true) {
			FileHandler.copyDirectory(newUploadedPath, destPath);
			FileHandler.copyDirectory(newUploadedPath, sourcePath);
			FileHandler.forceDelete(newUploadedPath);
		}
	}

	public void showPicture(TrailerDto trailerDto) throws IOException {

		images = new ArrayList<DefaultStreamedContent>();
		if (null != trailerDto.getPhotoName()) {
			for (String name : newUploadedFileNameList) {
				File sourceFile = new File(sourcePath + "/" + name);
				if (sourceFile.exists()) {
					images.add(new DefaultStreamedContent(new FileInputStream(sourceFile), "image/jpeg", sourceFile.getName()));
				}
			}
		} else {
			images = null;
			addErrorMessage("Photo does not exist!!!!");
		}
	}

	public void removePhoto() throws IOException {
		String imageName = null;
		imageName = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ImageName");
		if (newUploadedFileNameList.contains(imageName)) {
			newUploadedFileNameList.remove(imageName);
			deletePhotoList.add(imageName);
			addInfoMessage(imageName + " is Successfully deleted");
			keepSetMessage();
		}
	}

	public void setTrailerService(TrailerService trailerService) {
		this.trailerService = trailerService;
	}

	public TrailerDto getTrailerDtoEdit() {
		return trailerDtoEdit;
	}

	public List<String> getUploadedFileNameList() {
		return uploadedFileNameList;
	}

	public void setUploadedFileNameList(List<String> uploadedFileNameList) {
		this.uploadedFileNameList = uploadedFileNameList;
	}

	public List<DefaultStreamedContent> getImages() {
		return images;
	}

	public void setImages(List<DefaultStreamedContent> images) {
		this.images = images;
	}

	public Properties getAPP_CONFIG() {
		return APP_CONFIG;
	}

	public void setAPP_CONFIG(Properties aPP_CONFIG) {
		APP_CONFIG = aPP_CONFIG;
	}

	public List<String> getNewUploadedFileNameList() {
		return newUploadedFileNameList;
	}

	public void setNewUploadedFileNameList(List<String> newUploadedFileNameList) {
		this.newUploadedFileNameList = newUploadedFileNameList;
	}

	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public List<String> getDeletePhotoList() {
		return deletePhotoList;
	}

	public void setDeletePhotoList(List<String> deletePhotoList) {
		this.deletePhotoList = deletePhotoList;
	}

	public String getNewUploadedPath() {
		return newUploadedPath;
	}

	public void setNewUploadedPath(String newUploadedPath) {
		this.newUploadedPath = newUploadedPath;
	}

	public boolean isFileUpload() {
		return isFileUpload;
	}

	public void setFileUpload(boolean isFileUpload) {
		this.isFileUpload = isFileUpload;
	}

}
