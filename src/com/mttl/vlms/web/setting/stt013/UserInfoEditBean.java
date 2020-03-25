package com.mttl.vlms.web.setting.stt013;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.common.ViewCode;
import com.mttl.vlms.common.selectdto.RoleSelectDto;
import com.mttl.vlms.setting.stt013.dto.UserInfoDto;
import com.mttl.vlms.setting.stt013.service.UserInfoService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * UserInfoEditBean
 * 
 * 
 */
@ManagedBean(name = "UserInfoEditBean")
@ViewScoped
public class UserInfoEditBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 6273397299924214278L;

	@ManagedProperty(value = "#{UserInfoService}")
	private UserInfoService userInfoService;
	private List<RoleSelectDto> roleList;
	private UserInfoDto userInfoDtoEdit;
	private Map<Integer, String> genderTypeMap;

	@PostConstruct
	public void init() {
		userInfoDtoEdit = (UserInfoDto) getSessionParam(CommonConstants.USERINFO_DTO_EDIT);
		setGenderTypeMap(ViewCode.getGenderList());

	}

	public void redirect() {
		UserInfoDto userInfoDto = userInfoService.getUserInfoById(userInfoDtoEdit.getId());
		if (userInfoDto == null) {
			putSessionParam(CommonConstants.MESSAGE_CODE, "MSG_CMN_011");
			putSessionParam(CommonConstants.MESSAGE_PARAMETER, userInfoDtoEdit.getId());
			redirect("userInfoList.xhtml?faces-redirect=true");
		}
	}

	public String editUserInfo() {
		userInfoDtoEdit.setPhoneNo(StringUtils.join(userInfoDtoEdit.getPhoneNoList(), ","));
		if (userInfoService.checkLoginIdDuplicateCount(userInfoDtoEdit.getId(), userInfoDtoEdit.getLoginId()) > 0) {
			addErrorMessage(null, "MSG_CMN_010", "Login ID");
			return null;
		}
		if (userInfoService.checkDuplicateUserInfoCode(userInfoDtoEdit.getId(), userInfoDtoEdit.getBarcodeId()) > 0) {
			addErrorMessage(null, "MSG_CMN_010", "Barcode ID");
			return null;
		}
		if (userInfoService.checkDuplicateStaffId(userInfoDtoEdit.getId(), userInfoDtoEdit.getStaffId()) > 0) {
			addErrorMessage(null, "MSG_CMN_010", "Staff ID");
			return null;
		}

		if (userInfoService.checkDuplicateNrcNo(userInfoDtoEdit.getId(), userInfoDtoEdit.getNrcNo()) > 0) {
			addErrorMessage(null, "MSG_CMN_010", "NRC No");
			return null;
		}

		if (userInfoDtoEdit.getDateOfBirth().compareTo(userInfoDtoEdit.getJoinDate()) > 0) {
			addErrorMessage("Join Date should not be early than Date of Birth");
			return null;
		}

		if (userInfoDtoEdit.getStaffId().equals("MTTL")) {
			addErrorMessage(null, "MSG_CMN_046", userInfoDtoEdit.getStaffId());
			return null;
		}
		if (!userInfoDtoEdit.getStaffId().contains("MTTL")) {
			addErrorMessage("Staff Id should start with MTTL");
			return null;
		}

		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		int count = userInfoService.updateUserInfo(userInfoDtoEdit, user.getUserID());
		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_006", userInfoDtoEdit.getName());
		} else {
			addInfoMessage(null, "MSG_CMN_003", userInfoDtoEdit.getName());
		}
		keepSetMessage();
		return "userInfoList";
	}

	public UserInfoDto getUserInfoDtoEdit() {
		return userInfoDtoEdit;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public List<RoleSelectDto> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleSelectDto> roleList) {
		this.roleList = roleList;
	}

	public Map<Integer, String> getGenderTypeMap() {
		return genderTypeMap;
	}

	public void setGenderTypeMap(Map<Integer, String> genderTypeMap) {
		this.genderTypeMap = genderTypeMap;
	}

}
