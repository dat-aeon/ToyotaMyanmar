package com.mttl.vlms.web.setting.stt013;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.LazyDataModel;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.common.ViewCode;
import com.mttl.vlms.setting.stt013.dto.UserInfoDto;
import com.mttl.vlms.setting.stt013.dto.UserInfoSearchReqDto;
import com.mttl.vlms.setting.stt013.service.UserInfoService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * UserInfoListBean
 * 
 * 
 *
 */
@ManagedBean(name = "UserInfoListBean")
@ViewScoped
public class UserInfoListBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1335030901944769319L;

	@ManagedProperty(value = "#{UserInfoService}")
	private UserInfoService userInfoService;

	private String name;
	private Map<Integer, String> genderTypeMap;

	private List<UserInfoDto> userInfoList;
	private UserInfoDto userInfoSearchDto;

	private Integer firstRowCount;

	private boolean paginatorVisible;
	private List<UserInfoDto> filterUserInfoList;

	private LazyDataModel<UserInfoDto> userInfoListLazyDataModel;

	private List<UserInfoDto> userList;

	private UserInfoSearchReqDto userInfoSearchReqDto;

	private boolean checkAutoUpdate;

	@ManagedProperty(value = "#{APP_CONFIG}")
	private Properties APP_CONFIG;

	@PostConstruct
	public void init() {

		userInfoSearchReqDto = new UserInfoSearchReqDto();
		userList = new ArrayList<>();

		userInfoSearchDto = new UserInfoDto();
		setGenderTypeMap(ViewCode.getGenderList());
		String name = (String) getSessionParam(CommonConstants.NAME);
		userInfoSearchDto.setName(name);
		firstRowCount = (Integer) getSessionParam(CommonConstants.FIRST_ROW_COUNT);
		if (firstRowCount == null) {
			firstRowCount = 0;
		}

		searchUserInfo();
		checkAutoUpdate = false;
		removeSessionParam(CommonConstants.USERINFO_DTO_DELETE, CommonConstants.USERINFO_DTO_EDIT, CommonConstants.NAME, CommonConstants.FIRST_ROW_COUNT);

	}

	private void searchUserInfo() {

		// userInfoList =
		// userInfoService.findByName(userInfoSearchDto.getName());

		Integer userCount = userInfoService.getUserListCount(userInfoSearchReqDto);
		/*
		 * for (UserInfoDto dto : userInfoList) {
		 * 
		 * dto.setGenderLabel(genderTypeMap.get(dto.getGender()));
		 * 
		 * if (null != dto.getPhoneNo()) {
		 * dto.setPhoneNoList(Arrays.asList(StringUtils.splitPreserveAllTokens(
		 * dto.getPhoneNo(), ","))); }
		 */

		if (userCount > 10) {
			paginatorVisible = true;
		}

		userInfoListLazyDataModel = new UserInfoListLazyModel(userCount, userInfoSearchReqDto, userInfoService);

	}

	/*
	 * private void searchUserInfo() {
	 * 
	 * paginatorVisible = false; userInfoList =
	 * userInfoService.findByName(userInfoSearchDto.getName()); for (UserInfoDto
	 * dto : userInfoList) {
	 * 
	 * dto.setGenderLabel(genderTypeMap.get(dto.getGender()));
	 * 
	 * if (null != dto.getPhoneNo()) {
	 * dto.setPhoneNoList(Arrays.asList(StringUtils.splitPreserveAllTokens(dto.
	 * getPhoneNo(), ","))); }
	 * 
	 * }
	 * 
	 * if (userInfoList.size() > 10) { paginatorVisible = true; }
	 * 
	 * }
	 */

	/*
	 * public void searchUserInfo() { userInfoList =
	 * userInfoService.getUserList( userInfoId); if (userInfoList.size() > 10) {
	 * paginatorVisible = true; } }
	 */
	public void showMessage() {
		String messageCode = (String) getSessionParam(CommonConstants.MESSAGE_CODE);
		String messageParameter = (String) getSessionParam(CommonConstants.MESSAGE_PARAMETER);
		removeSessionParam(CommonConstants.MESSAGE_CODE, CommonConstants.MESSAGE_PARAMETER);
		if (messageCode != null && messageParameter != null) {
			addErrorMessage(null, messageCode, messageParameter);
		}
	}

	public String editUserInfo(UserInfoDto userInfoDto) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmUserInfoList:userInfoList");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.USERINFO_DTO_EDIT, userInfoDto);
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);
		putSessionParam(CommonConstants.USER_INFO_ID, this.userInfoSearchDto.getId());
		putSessionParam(CommonConstants.USER_INFO_NAME, this.userInfoSearchDto.getName());
		return "userInfoEdit";
	}

	public String deleteConfirmUserInfo(UserInfoDto userInfoDto) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmUserInfoList:userInfoList");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.USERINFO_DTO_DELETE, userInfoDto);
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);
		return "userInfoDeleteConfirm";
	}

	public void disabledUserInfo(UserInfoDto userInfoDtoEdit) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmUserInfoList:userInfoList");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);

		System.out.println(userInfoDtoEdit.getLoginId());
		userInfoDtoEdit.setDisabled(!userInfoDtoEdit.isDisabled());

		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);

		int count = userInfoService.updateUserInfo(userInfoDtoEdit, user.getUserID());

		checkAutoUpdate = true;
		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_006", userInfoDtoEdit.getLoginId());
		} else {
			addInfoMessage(null, "MSG_CMN_003", userInfoDtoEdit.getLoginId());
		}
		searchUserInfo();
	}

	/*
	 * public boolean globalFilterFunction(Object value, Object filter, Locale
	 * locale) { String filterText = (filter == null) ? null :
	 * filter.toString().trim().toLowerCase(); if (filterText == null ||
	 * filterText.equals("")) { return true; }
	 * 
	 * int filterInt = getInteger(filterText);
	 * 
	 * UserInfoDto userInfo = (UserInfoDto) value; return
	 * userInfo.getLoginId().toLowerCase().contains(filterText) ||
	 * userInfo.getName().toLowerCase().contains(filterText) ||
	 * userInfo.getBarcodeId().toLowerCase().contains(filterText) ||
	 * userInfo.getStaffId().toLowerCase().contains(filterText) ||
	 * userInfo.getNrcNo().toLowerCase().contains(filterText) ||
	 * userInfo.getPhoneNo().toLowerCase().contains(filterText) ||
	 * userInfo.getRoleName().toLowerCase().contains(filterText) ||
	 * userInfo.getDepartment().toLowerCase().contains(filterText) ||
	 * userInfo.getAddress().toLowerCase().contains(filterText) ||
	 * userInfo.getDescription().toLowerCase().contains(filterText) ||
	 * (userInfo.isDisabled() ? "enable" :
	 * "disable").toLowerCase().contains(filterText);
	 * 
	 * }
	 * 
	 * private int getInteger(String string) { try { return
	 * Integer.valueOf(string); } catch (NumberFormatException ex) { return 0; }
	 * }
	 */

	public void reset(UserInfoDto userInfoDto) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmUserInfoList:userInfoList");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);

		String defaultPassword = APP_CONFIG.getProperty("DEFAULT_PASSWORD");

		userInfoDto.setPassword(defaultPassword);

		userInfoDto.setPasswordChangeDate(null);

		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);

		int count = userInfoService.updateUserInfo(userInfoDto, user.getUserID());

		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_006", userInfoDto.getLoginId());
		} else {
			addInfoMessage(null, "MSG_CMN_003", userInfoDto.getLoginId());
		}
		searchUserInfo();

	}

	public void changeAutoUpdate() {
		checkAutoUpdate = true;
	}

	public boolean isPaginatorVisible() {
		return paginatorVisible;
	}

	public Integer getFirstRowCount() {
		return firstRowCount;
	}

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPaginatorVisible(boolean paginatorVisible) {
		this.paginatorVisible = paginatorVisible;
	}

	public List<UserInfoDto> getUserInfoList() {
		return userInfoList;
	}

	public void setUserInfoList(List<UserInfoDto> userInfoList) {
		this.userInfoList = userInfoList;
	}

	public UserInfoDto getUserInfoSearchDto() {
		return userInfoSearchDto;
	}

	public void setUserInfoSearchDto(UserInfoDto userInfoSearchDto) {
		this.userInfoSearchDto = userInfoSearchDto;
	}

	public Map<Integer, String> getGenderTypeMap() {
		return genderTypeMap;
	}

	public void setGenderTypeMap(Map<Integer, String> genderTypeMap) {
		this.genderTypeMap = genderTypeMap;
	}

	public List<UserInfoDto> getFilterUserInfoList() {
		return filterUserInfoList;
	}

	public void setFilterUserInfoList(List<UserInfoDto> filterUserInfoList) {
		this.filterUserInfoList = filterUserInfoList;
	}

	public LazyDataModel<UserInfoDto> getUserInfoListLazyDataModel() {
		return userInfoListLazyDataModel;
	}

	public void setUserInfoListLazyDataModel(LazyDataModel<UserInfoDto> userInfoListLazyDataModel) {
		this.userInfoListLazyDataModel = userInfoListLazyDataModel;
	}

	public List<UserInfoDto> getUserList() {
		return userList;
	}

	public void setUserList(List<UserInfoDto> userList) {
		this.userList = userList;
	}

	public UserInfoSearchReqDto getUserInfoSearchReqDto() {
		return userInfoSearchReqDto;
	}

	public void setUserInfoSearchReqDto(UserInfoSearchReqDto userInfoSearchReqDto) {
		this.userInfoSearchReqDto = userInfoSearchReqDto;
	}

	public Properties getAPP_CONFIG() {
		return APP_CONFIG;
	}

	public void setAPP_CONFIG(Properties aPP_CONFIG) {
		APP_CONFIG = aPP_CONFIG;
	}

	public boolean isCheckAutoUpdate() {
		return checkAutoUpdate;
	}

	public void setCheckAutoUpdate(boolean checkAutoUpdate) {
		this.checkAutoUpdate = checkAutoUpdate;
	}

}
