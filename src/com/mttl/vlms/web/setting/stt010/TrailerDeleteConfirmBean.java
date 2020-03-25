package com.mttl.vlms.web.setting.stt010;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.common.Utils;
import com.mttl.vlms.setting.stt010.dto.TrailerDto;
import com.mttl.vlms.setting.stt010.service.TrailerService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;
import com.mttl.vlms.web.common.FileHandler;

/**
 * TrailerDeleteConfirmBean
 * 
 * 
 */
@ManagedBean(name = "TrailerDeleteConfirmBean")
@ViewScoped
public class TrailerDeleteConfirmBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2782943665062516109L;

	@ManagedProperty(value = "#{TrailerService}")
	private TrailerService trailerService;

	private TrailerDto trailerDeleteDto;

	@ManagedProperty(value = "#{APP_CONFIG}")
	private Properties APP_CONFIG;

	@PostConstruct
	public void init() {
		trailerDeleteDto = (TrailerDto) getSessionParam(CommonConstants.TRAILER_DTO_DELETE);
	}

	public void redirect() {
		TrailerDto trailerDto = trailerService.getTrailerByTrailerId(trailerDeleteDto.getId());
		if (trailerDto == null) {
			putSessionParam(CommonConstants.MESSAGE_CODE, "MSG_CMN_011");
			putSessionParam(CommonConstants.MESSAGE_PARAMETER, trailerDeleteDto.getTrailerCode());
			redirect("trailerList.xhtml?faces-redirect=true");
		}
	}

	public String deleteConfirmTrailer() {
		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		int count = trailerService.deleteTrailer(trailerDeleteDto, user.getUserID());
		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_007", trailerDeleteDto.getTrailerCode());
		} else {
			addInfoMessage(null, "MSG_CMN_004", trailerDeleteDto.getTrailerCode());
		}

		keepSetMessage();
		deleteFolder(trailerDeleteDto);
		return "trailerList";
	}

	public void deleteFolder(TrailerDto trailerDeleteDto) {
		String destPath = APP_CONFIG.getProperty("IMAGE_BASE_FILEPATH") + APP_CONFIG.getProperty("TRAILER_IMAGES_FOLDER") + trailerDeleteDto.getId().toString();
		Utils.createDirectory(destPath);
		try {
			FileHandler.forceDelete(destPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setTrailerService(TrailerService trailerService) {
		this.trailerService = trailerService;
	}

	public TrailerDto getTrailerDeleteDto() {
		return trailerDeleteDto;
	}

	public void setTrailerDeleteDto(TrailerDto trailerDeleteDto) {
		this.trailerDeleteDto = trailerDeleteDto;
	}

	public Properties getAPP_CONFIG() {
		return APP_CONFIG;
	}

	public void setAPP_CONFIG(Properties aPP_CONFIG) {
		APP_CONFIG = aPP_CONFIG;
	}

}
