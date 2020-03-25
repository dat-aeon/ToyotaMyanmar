package com.mttl.vlms.web.setting.stt010;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.common.Utils;
import com.mttl.vlms.setting.stt010.dto.TrailerDto;
import com.mttl.vlms.setting.stt010.dto.TrailerSearchReqDto;
import com.mttl.vlms.setting.stt010.service.TrailerService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * TrailerListBean
 * 
 * 
 *
 */
@ManagedBean(name = "TrailerListBean")
@ViewScoped
public class TrailerListBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1864227734536069210L;

	@ManagedProperty(value = "#{TrailerService}")
	private TrailerService trailerService;

	private String licenseNo;

	private List<TrailerDto> trailerList;

	private List<TrailerDto> filterTrailerList;

	private Integer firstRowCount;

	private boolean paginatorVisible;

	private LazyDataModel<TrailerDto> trailerListLazyDataModel;

	private TrailerSearchReqDto trailerSearchReqDto;

	private List<DefaultStreamedContent> images;

	@ManagedProperty(value = "#{APP_CONFIG}")
	private Properties APP_CONFIG;

	private String sourcePath;

	@PostConstruct
	public void init() {

		trailerSearchReqDto = new TrailerSearchReqDto();
		trailerList = new ArrayList<>();

		licenseNo = (String) getSessionParam(CommonConstants.LICENSE_NO);
		firstRowCount = (Integer) getSessionParam(CommonConstants.FIRST_ROW_COUNT);
		if (firstRowCount == null) {
			firstRowCount = 0;
		}
		removeSessionParam(CommonConstants.TRAILER_DTO_DELETE, CommonConstants.TRAILER_DTO_EDIT, CommonConstants.LICENSE_NO, CommonConstants.FIRST_ROW_COUNT);

		searchTrailer();
	}

	public void searchTrailer() {

		Integer trailerCount = trailerService.getTrailerListCount(trailerSearchReqDto);
		if (trailerCount > 10) {
			paginatorVisible = true;
		}
		trailerListLazyDataModel = new TrailerListLazyModel(trailerCount, trailerSearchReqDto, trailerService);

		trailerList = trailerService.getMainTrailerList();

		for (TrailerDto dto : trailerList) {
			if (null != dto.getPhotoName()) {
				dto.setUploadedPhoto(Arrays.asList(StringUtils.splitPreserveAllTokens(dto.getPhotoName(), ",")));
				dto.setPhotoList(dto.getUploadedPhoto().toString().replace("[", ""));
				dto.setPhotoList(dto.getPhotoList().toString().replace("]", ""));
			}
		}
	}

	public void showMessage() {
		String messageCode = (String) getSessionParam(CommonConstants.MESSAGE_CODE);
		String messageParameter = (String) getSessionParam(CommonConstants.MESSAGE_PARAMETER);
		removeSessionParam(CommonConstants.MESSAGE_CODE, CommonConstants.MESSAGE_PARAMETER);
		if (messageCode != null && messageParameter != null) {
			addErrorMessage(null, messageCode, messageParameter);
		}
	}

	public void showPicture(TrailerDto trailerDto) throws IOException {

		sourcePath = Utils.getSystemPath() + "temp//" + "trailer-saved-photos//" + trailerDto.getBarcodeId().toString();
		images = new ArrayList<DefaultStreamedContent>();
		if (null != trailerDto.getPhotoName()) {
			List<String> allFiles = Arrays.asList(StringUtils.splitPreserveAllTokens(trailerDto.getPhotoName(), ","));
			for (String name : allFiles) {
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

	public String changePhotoLabel(TrailerDto trailerDto) {
		if (trailerDto.isCheckPhoto() == false) {
			return "View";
		} else {
			return "No Photos";
		}
	}

	public String editTrailer(TrailerDto trailerDto) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmTailerList:trailerList");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.TRAILER_DTO_EDIT, trailerDto);
		putSessionParam(CommonConstants.LICENSE_NO, licenseNo);
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);
		return "trailerEdit";
	}

	public String deleteConfirmTrailer(TrailerDto trailerDto) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmTailerList:trailerList");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.TRAILER_DTO_DELETE, trailerDto);
		putSessionParam(CommonConstants.LICENSE_NO, licenseNo);
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);
		return "trailerDeleteConfirm";
	}

	public void disabledTrailer(TrailerDto trailerDtoEdit) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmTailerList:trailerList");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);

		System.out.println(trailerDtoEdit.getTrailerCode());
		trailerDtoEdit.setDisabled(!trailerDtoEdit.isDisabled());

		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);

		int count = trailerService.updateTrailer(trailerDtoEdit, user.getUserID());

		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_006", trailerDtoEdit.getTrailerCode());
		} else {
			addInfoMessage(null, "MSG_CMN_003", trailerDtoEdit.getTrailerCode());
		}
		searchTrailer();
	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		int filterInt = getInteger(filterText);

		TrailerDto trailer = (TrailerDto) value;
		return trailer.getTrailerCode().toLowerCase().contains(filterText) || trailer.getLicenseNo().toLowerCase().contains(filterText)
				|| trailer.getTrailerType().toLowerCase().contains(filterText) || trailer.getDescription().toLowerCase().contains(filterText)
				|| (trailer.isDisabled() ? "enable" : "disable").toLowerCase().contains(filterText);

	}

	private int getInteger(String string) {
		try {
			return Integer.valueOf(string);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}

	public boolean isPaginatorVisible() {
		return paginatorVisible;
	}

	public Integer getFirstRowCount() {
		return firstRowCount;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public List<TrailerDto> getTrailerList() {
		return trailerList;
	}

	public void setTrailerList(List<TrailerDto> trailerList) {
		this.trailerList = trailerList;
	}

	public void setTrailerService(TrailerService trailerService) {
		this.trailerService = trailerService;
	}

	public List<TrailerDto> getFilterTrailerList() {
		return filterTrailerList;
	}

	public void setFilterTrailerList(List<TrailerDto> filterTrailerList) {
		this.filterTrailerList = filterTrailerList;
	}

	public LazyDataModel<TrailerDto> getTrailerListLazyDataModel() {
		return trailerListLazyDataModel;
	}

	public void setTrailerListLazyDataModel(LazyDataModel<TrailerDto> trailerListLazyDataModel) {
		this.trailerListLazyDataModel = trailerListLazyDataModel;
	}

	public TrailerSearchReqDto getTrailerSearchReqDto() {
		return trailerSearchReqDto;
	}

	public void setTrailerSearchReqDto(TrailerSearchReqDto trailerSearchReqDto) {
		this.trailerSearchReqDto = trailerSearchReqDto;
	}

	public Properties getAPP_CONFIG() {
		return APP_CONFIG;
	}

	public void setAPP_CONFIG(Properties aPP_CONFIG) {
		APP_CONFIG = aPP_CONFIG;
	}

	public List<DefaultStreamedContent> getImages() {
		return images;
	}

	public void setImages(List<DefaultStreamedContent> images) {
		this.images = images;
	}

	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

}
