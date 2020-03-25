package com.mttl.vlms.web.setting.stt011;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.common.Utils;
import com.mttl.vlms.setting.stt011.dto.DriverDto;
import com.mttl.vlms.setting.stt011.service.DriverService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * DriverDeleteConfirmBean
 * 
 * 
 */
@ManagedBean(name = "DriverDeleteConfirmBean")
@ViewScoped
public class DriverDeleteConfirmBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 7173852451546888968L;

	@ManagedProperty(value = "#{DriverService}")
	private DriverService driverService;

	private DriverDto driverDtoDelete;

	@PostConstruct
	public void init() {
		driverDtoDelete = (DriverDto) getSessionParam(CommonConstants.DRIVER_DTO_DELETE);
	}

	public void redirect() {
		DriverDto driver = driverService.getDriverById(driverDtoDelete.getId());
		if (driver == null) {
			putSessionParam(CommonConstants.MESSAGE_CODE, "MSG_CMN_011");
			putSessionParam(CommonConstants.MESSAGE_PARAMETER, driverDtoDelete.getDriverName());
			redirect("driverList.xhtml?faces-redirect=true");
		}
	}

	public String deleteConfirmDriver() {
		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		int usedCount = driverService.checkDriverUsed(driverDtoDelete.getId());
		// if (usedCount > 0) {
		// addErrorMessage(null, "DRIVER_DELETE_REJECT_MSG",
		// driverDtoDelete.getDriverName());
		// } else {
		driverDtoDelete.setPhoneNo(Utils.listToStringConcat(driverDtoDelete.getPhoneNoList()));
		int count = driverService.deleteDriver(driverDtoDelete, user.getUserID());
		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_007", driverDtoDelete.getDriverName());
		} else {
			addInfoMessage(null, "MSG_CMN_004", driverDtoDelete.getDriverName());
		}
		// }
		keepSetMessage();
		return "driverList";
	}

	public DriverDto getDriverDtoDelete() {
		return driverDtoDelete;
	}

	public void setDriverDtoDelete(DriverDto driverDtoDelete) {
		this.driverDtoDelete = driverDtoDelete;
	}

	public void setDriverService(DriverService driverService) {
		this.driverService = driverService;
	}
}
