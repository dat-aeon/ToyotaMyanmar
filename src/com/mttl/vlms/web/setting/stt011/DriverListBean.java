package com.mttl.vlms.web.setting.stt011;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.datatable.DataTable;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.common.ViewCode;
import com.mttl.vlms.setting.stt011.dto.DriverDto;
import com.mttl.vlms.setting.stt011.service.DriverService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * DriverListBean
 * 
 * 
 *
 */
@ManagedBean(name = "DriverListBean")
@ViewScoped
public class DriverListBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -6598961227154170581L;

	@ManagedProperty(value = "#{DriverService}")
	private DriverService driverService;

	private Map<Integer, String> driverTypeMap;

	private DriverDto driverDtoSearch;

	private List<DriverDto> driverList;

	private List<DriverDto> filterDriverList;

	private boolean paginatorVisible;

	private boolean checkAutoUpdate;

	private Integer firstRowCount;

	@PostConstruct
	public void init() {
		driverDtoSearch = (DriverDto) getSessionParam(CommonConstants.DRIVER_DTO_SEARCH);
		if (null == driverDtoSearch) {
			driverDtoSearch = new DriverDto();
		}
		firstRowCount = (Integer) getSessionParam(CommonConstants.FIRST_ROW_COUNT);

		setDriverTypeMap(ViewCode.getDriverTypeList());

		if (firstRowCount == null) {
			firstRowCount = 0;
		}
		if (null != (String) getSessionParam(CommonConstants.LICENSE_EXPIRED_DRIVER_CHECK)) {
			driverDtoSearch.setLicenseExpiredDriverFlag("1");
			searchDriver();
			removeSessionParam(CommonConstants.LICENSE_EXPIRED_DRIVER_CHECK);
		} else {
			removeSessionParam(CommonConstants.DRIVER_DTO_DELETE, CommonConstants.DRIVER_DTO_EDIT, CommonConstants.DRIVER_DTO_SEARCH, CommonConstants.FIRST_ROW_COUNT);
			searchDriver();
			checkAutoUpdate = false;
		}
	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		int filterInt = getInteger(filterText);

		DriverDto driver = (DriverDto) value;
		return driver.getBarcodeId().toLowerCase().contains(filterText) || driver.getDriverName().toLowerCase().contains(filterText)
				|| driver.getPhoneNo().toLowerCase().contains(filterText) || driver.getJoinDate().toString().toLowerCase().contains(filterText)
				|| driver.getLicenseType().toLowerCase().contains(filterText) || driver.getLicenseNo().toLowerCase().contains(filterText)
				|| driver.getLicenseExpireDate().toString().toLowerCase().contains(filterText) || driver.getType().toString().toLowerCase().contains(filterText)
				|| driver.getStatus().toString().toLowerCase().contains(filterText) || (driver.isDisabled() ? "enable" : "disable").toString().toLowerCase().contains(filterText);

	}

	private int getInteger(String string) {
		try {
			return Integer.valueOf(string);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}

	public void searchDriver() {

		paginatorVisible = false;
		driverList = driverService.findDriverList(driverDtoSearch);
		for (DriverDto dto : driverList) {
			dto.setCheckExpireDate(driverService.checkExpireDate(dto.getId()));
			dto.setTypeLabel(driverTypeMap.get(dto.getType()));
			if (null != dto.getPhoneNo()) {

				dto.setPhoneNoList(Arrays.asList(StringUtils.splitPreserveAllTokens(dto.getPhoneNo(), ",")));
				dto.setPhList(dto.getPhoneNoList().toString().replace("[", ""));
				dto.setPhList(dto.getPhList().toString().replace("]", ""));
			}
		}
		if (driverList.size() > 10) {
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

	public String editDriver(DriverDto driverDto) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("driverListForm:driverTable");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);
		putSessionParam(CommonConstants.DRIVER_DTO_EDIT, driverDto);
		putSessionParam(CommonConstants.DRIVER_DTO_SEARCH, driverDto);
		return "driverEdit";
	}

	public String deleteConfirmDriver(DriverDto driverDto) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("driverListForm:driverTable");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);
		putSessionParam(CommonConstants.DRIVER_DTO_DELETE, driverDto);
		putSessionParam(CommonConstants.DRIVER_DTO_SEARCH, driverDto);
		return "driverDeleteConfirm";
	}

	public void disabledDriver(DriverDto driverDtoEdit) {

		driverDtoEdit.setDisabled(!driverDtoEdit.isDisabled());

		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("driverListForm:driverTable");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);

		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);

		int count = driverService.updateDriver(driverDtoEdit, user.getUserID());

		checkAutoUpdate = true;

		System.out.println(count);

		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_006", driverDtoEdit.getId());
		} else {
			addInfoMessage(null, "MSG_CMN_003", driverDtoEdit.getDriverName());
		}
		searchDriver();
	}

	public void changeAutoUpdate() {
		checkAutoUpdate = true;
	}

	public DriverDto getDriverDtoSearch() {
		return driverDtoSearch;
	}

	public void setDriverDtoSearch(DriverDto driverDtoSearch) {
		this.driverDtoSearch = driverDtoSearch;
	}

	public List<DriverDto> getDriverList() {
		return driverList;
	}

	public void setDriverList(List<DriverDto> driverList) {
		this.driverList = driverList;
	}

	public boolean isPaginatorVisible() {
		return paginatorVisible;
	}

	public void setDriverService(DriverService driverService) {
		this.driverService = driverService;
	}

	public Integer getFirstRowCount() {
		return firstRowCount;
	}

	public List<DriverDto> getFilterDriverList() {
		return filterDriverList;
	}

	public void setFilterDriverList(List<DriverDto> filterDriverList) {
		this.filterDriverList = filterDriverList;
	}

	public Map<Integer, String> getDriverTypeMap() {
		return driverTypeMap;
	}

	public void setDriverTypeMap(Map<Integer, String> driverTypeMap) {
		this.driverTypeMap = driverTypeMap;
	}

	public boolean isCheckAutoUpdate() {
		return checkAutoUpdate;
	}

	public void setCheckAutoUpdate(boolean checkAutoUpdate) {
		this.checkAutoUpdate = checkAutoUpdate;
	}

}
