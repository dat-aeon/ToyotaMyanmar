package com.mttl.vlms.web.setting.stt013;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.setting.stt010.dto.TrailerDto;
import com.mttl.vlms.setting.stt013.dto.RoleDto;
import com.mttl.vlms.setting.stt013.service.RoleService;
import com.mttl.vlms.web.common.BaseBean;

/**
 * RoleListBean
 * 
 * 
 *
 */
@ManagedBean(name = "RoleListBean")
@ViewScoped
public class RoleListBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1335030901944769319L;

	@ManagedProperty(value = "#{RoleService}")
	private RoleService roleService;

	private String description;

	private List<RoleDto> roleList;

	private List<RoleDto> filterRoleList;

	private Integer firstRowCount;

	private boolean paginatorVisible;

	@PostConstruct
	public void init() {
		description = (String) getSessionParam(CommonConstants.DESCRIPTION);
		firstRowCount = (Integer) getSessionParam(CommonConstants.FIRST_ROW_COUNT);
		if (firstRowCount == null) {
			firstRowCount = 0;
		}
		removeSessionParam(CommonConstants.ROLE_DTO_DELETE, CommonConstants.ROLE_DTO_EDIT, CommonConstants.DESCRIPTION, CommonConstants.FIRST_ROW_COUNT);
		searchRole();
	}

	public void searchRole() {
		roleList = roleService.getUserList(description);
		if (roleList.size() > 10) {
			paginatorVisible = true;
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

	/*
	 * public String editTrailer(RoleDto roleDto) { final DataTable dataTable =
	 * (DataTable)
	 * FacesContext.getCurrentInstance().getViewRoot().findComponent(
	 * "frmRoleInfoList:userInfoList"); firstRowCount = dataTable.getFirst();
	 * putSessionParam(CommonConstants.ROLE_DTO_EDIT, roleDto);
	 * putSessionParam(CommonConstants.DESCRIPTION, description);
	 * putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount); return
	 * "roleEdit"; }
	 * 
	 * public String deleteConfirmTrailer(TrailerDto trailerDto) { final
	 * DataTable dataTable = (DataTable)
	 * FacesContext.getCurrentInstance().getViewRoot().findComponent(
	 * "trailerListForm:trailerList"); firstRowCount = dataTable.getFirst();
	 * putSessionParam(CommonConstants.TRAILER_DTO_DELETE, trailerDto);
	 * putSessionParam(CommonConstants.LICENSE_NO, licenseNo);
	 * putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount); return
	 * "trailerDeleteConfirm"; }
	 */

	/*
	 * public void disabledTrailer(TrailerDto trailerDtoEdit) { final DataTable
	 * dataTable = (DataTable)
	 * FacesContext.getCurrentInstance().getViewRoot().findComponent(
	 * "trailerListForm:trailerList"); firstRowCount = dataTable.getFirst();
	 * putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);
	 * 
	 * User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
	 * 
	 * int count = roleService.updateTrailer(trailerDtoEdit, user.getUserID());
	 * 
	 * System.out.println(count);
	 * 
	 * if (count == 0) { addErrorMessage(null, "MSG_CMN_006",
	 * trailerDtoEdit.getTrailerId()); } else { addInfoMessage(null,
	 * "MSG_CMN_003", trailerDtoEdit.getTrailerId()); } searchTrailer(); }
	 */

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		int filterInt = getInteger(filterText);

		TrailerDto trailer = (TrailerDto) value;
		return trailer.getTrailerCode().toLowerCase().contains(filterText) || trailer.getLicenseNo().toLowerCase().contains(filterText)
				|| trailer.getTrailerType().toLowerCase().contains(filterText) || trailer.getDescription().toLowerCase().contains(filterText);

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RoleDto> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleDto> roleList) {
		this.roleList = roleList;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public List<RoleDto> getFilterRoleList() {
		return filterRoleList;
	}

	public void setFilterRoleList(List<RoleDto> filterRoleList) {
		this.filterRoleList = filterRoleList;
	}

}
