package com.mttl.vlms.web.setting.stt013;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.setting.stt013.dto.UserInfoDto;
import com.mttl.vlms.setting.stt013.service.UserInfoService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * UserInfoDeleteConfirmBean
 * 
 * 
 */
@ManagedBean(name = "UserInfoDeleteConfirmBean")
@ViewScoped
public class UserInfoDeleteConfirmBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 7173852451546888968L;

	@ManagedProperty(value = "#{UserInfoService}")
	private UserInfoService userInfoService;

	private UserInfoDto userInfoDto;

	@PostConstruct
	public void init() {
		userInfoDto = (UserInfoDto) getSessionParam(CommonConstants.USERINFO_DTO_DELETE);
	}

	public void redirect() {
		UserInfoDto userInfo = userInfoService.getUserInfoById(userInfoDto.getId());
		if (userInfo == null) {
			putSessionParam(CommonConstants.MESSAGE_CODE, "MSG_CMN_011");
			putSessionParam(CommonConstants.MESSAGE_PARAMETER, userInfoDto.getName());
			redirect("userInfoList.xhtml?faces-redirect=true");
		}
	}

	public String deleteConfirmUserInfo() {
		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);

		int count = userInfoService.deleteUserInfo(userInfoDto, user.getUserID());
		if (count > 0) {
			addInfoMessage(null, "MSG_CMN_004", userInfoDto.getName());
		} else {
			addErrorMessage(null, "MSG_CMN_007", userInfoDto.getName());
		}

		keepSetMessage();
		return "userInfoList";
	}

	public UserInfoDto getUserInfoDto() {
		return userInfoDto;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
}
