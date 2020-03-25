package com.mttl.vlms.web.operations.opr002;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.operation.opr002.dto.VehicleInfoDto;
import com.mttl.vlms.operation.opr002.service.VehicleInfoService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * VehicleInfoDeleteConfirmBean
 * 
 * 
 */
@ManagedBean(name = "VehicleInfoDeleteConfirmBean")
@ViewScoped
public class VehicleInfoDeleteConfirmBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 7173852451546888968L;

	@ManagedProperty(value = "#{VehicleInfoService}")
	private VehicleInfoService vehicleInfoService;

	private VehicleInfoDto vehicleInfoDtoDelete;

	@PostConstruct
	public void init() {
		vehicleInfoDtoDelete = (VehicleInfoDto) getSessionParam(CommonConstants.VEHICLE_INFO_DTO_DELETE);
	}

	public void redirect() {
		VehicleInfoDto vehicleInfo = vehicleInfoService.getVehicleInfoById(vehicleInfoDtoDelete.getId());
		if (vehicleInfo == null) {
			putSessionParam(CommonConstants.MESSAGE_CODE, "MSG_CMN_011");
			putSessionParam(CommonConstants.MESSAGE_PARAMETER, vehicleInfoDtoDelete.getId());
			redirect("vehicleInfoList.xhtml?faces-redirect=true");
		}
	}

	public String deleteConfirmVehicleInfo() {
		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		int usedCount = vehicleInfoService.checkVehicleInfoUsed(vehicleInfoDtoDelete.getId());
		/*
		 * if (usedCount > 0) { addErrorMessage(null,
		 * "COUNTRY_DELETE_REJECT_MSG",
		 * vehicleInfoDtoDelete.getVehicleInfoName()); } else {
		 */
		int count = vehicleInfoService.deleteVehicleInfo(vehicleInfoDtoDelete, user.getUserID());
		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_007", vehicleInfoDtoDelete.getChassisNo());
		} else {
			addInfoMessage(null, "MSG_CMN_004", vehicleInfoDtoDelete.getChassisNo());
		}
		// }
		keepSetMessage();
		return "vehicleInfoList";
	}

	public VehicleInfoDto getVehicleInfoDtoDelete() {
		return vehicleInfoDtoDelete;
	}

	public void setVehicleInfoDtoDelete(VehicleInfoDto vehicleInfoDtoDelete) {
		this.vehicleInfoDtoDelete = vehicleInfoDtoDelete;
	}

	public void setVehicleInfoService(VehicleInfoService vehicleInfoService) {
		this.vehicleInfoService = vehicleInfoService;
	}
}
