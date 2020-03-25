package com.mttl.vlms.web.setting.stt013;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.common.ViewCode;
import com.mttl.vlms.common.selectdto.RoleSelectDto;
import com.mttl.vlms.setting.stt013.dto.UserInfoDto;
import com.mttl.vlms.setting.stt013.service.UserInfoService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * UserInfoInputBean
 * 
 * 
 */
@ManagedBean(name = "UserInfoInputBean")
@ViewScoped
public class UserInfoInputBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{UserInfoService}")
	private UserInfoService userInfoService;

	private UserInfoDto userInfoDto;

	private User loginUser;
	private List<RoleSelectDto> roleList;

	private Map<Integer, String> genderTypeMap;

	@PostConstruct
	public void init() {
		roleList = userInfoService.getRoleList();
		setGenderTypeMap(ViewCode.getGenderList());
		userInfoDto = new UserInfoDto();
		userInfoDto.setStaffId("MTTL");
		userInfoDto.setBarcodeId("HR");

		/*
		 * trailerDto.setTrailerCode(trailerService.getNextTrailerId());
		 * trailerDto.setBarcodeId(trailerDto.getTrailerCode());
		 */
		if (CollectionUtils.isNotEmpty(roleList)) {
			userInfoDto.setRoleDto(roleList.get(0));
		}
		loginUser = (User) getSessionParam(CommonConstants.LOGIN_USER);

	}

	public String inputUserInfo() {

		/*
		 * if (userInfoService.checkDuplicateNrcNo(userInfoDto) > 0) {
		 * addErrorMessage(null, "MSG_CMN_010", userInfoDto.getNrcNo()); return
		 * null; }
		 */

		userInfoDto.setPhoneNo(StringUtils.join(userInfoDto.getPhoneNoList(), ","));

		int staffIdCount = userInfoService.checkDuplicateStaffId(null, userInfoDto.getStaffId());
		if (staffIdCount > 0) {
			addErrorMessage(null, "MSG_CMN_010", "Staff ID");
			return null;
		}
		int countId = userInfoService.checkDuplicateUserInfoCode(null, userInfoDto.getBarcodeId());
		if (countId > 0) {
			addErrorMessage(null, "MSG_CMN_010", "Barcode ID");
			return null;
		}
		// userInfoDto.setBarcodeId("immortalzeze");
		int countId1 = userInfoService.checkLoginIdDuplicateCount(null, userInfoDto.getLoginId());
		if (countId1 > 0) {
			addErrorMessage(null, "MSG_CMN_010", "Login ID");
			return null;
		}
		int nrcCount = userInfoService.checkDuplicateNrcNo(null, userInfoDto.getNrcNo());
		if (nrcCount > 0) {
			addErrorMessage(null, "MSG_CMN_010", "Nrc No");
			return null;
		}

		if (userInfoDto.getDateOfBirth().compareTo(userInfoDto.getJoinDate()) > 0) {
			addErrorMessage("Join Date should not be early than Date of Birth");
			return null;
		}

		if (userInfoDto.getStaffId().equals("MTTL")) {
			addErrorMessage(null, "MSG_CMN_046", userInfoDto.getStaffId());
			return null;
		}
		if (!userInfoDto.getStaffId().contains("MTTL")) {
			addErrorMessage("Staff Id should start with MTTL");
			return null;
		}

		userInfoDto.setBarcodeId("HR" + userInfoDto.getStaffId());

		userInfoDto.setRoleId(userInfoDto.getRoleId());
		userInfoService.insertUserInfo(userInfoDto, loginUser.getUserID());
		addInfoMessage(null, "MSG_CMN_002", userInfoDto.getName());
		keepSetMessage();
		return "userInfoList";

	}

	public UserInfoDto getUserInfoDto() {
		return userInfoDto;
	}

	public void setUserInfoDto(UserInfoDto userInfoDto) {
		this.userInfoDto = userInfoDto;
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
