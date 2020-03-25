package com.mttl.vlms.web.operations.opr004;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.operation.opr002.dto.VehicleInfoDto;
import com.mttl.vlms.operation.opr002.service.VehicleInfoService;
import com.mttl.vlms.operation.opr004.dto.VehicleProcessInfoDto;
import com.mttl.vlms.operation.opr004.service.VehicleProcessInfoService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * VehicleProcessInfoDeleteConfirmBean
 * 
 * 
 */
@ManagedBean(name = "VehicleProcessInfoDeleteConfirmBean")
@ViewScoped
public class VehicleProcessInfoDeleteConfirmBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 7173852451546888968L;

	@ManagedProperty(value = "#{VehicleProcessInfoService}")
	private VehicleProcessInfoService vehicleProcessInfoService;

	@ManagedProperty(value = "#{VehicleInfoService}")
	private VehicleInfoService vehicleInfoService;

	private VehicleProcessInfoDto vehicleProcessInfoDtoDelete;

	@PostConstruct
	public void init() {
		vehicleProcessInfoDtoDelete = (VehicleProcessInfoDto) getSessionParam(CommonConstants.VEHICLE_INFO_DTO_DELETE);
	}

	public void redirect() {
		VehicleInfoDto vehicleInfo = vehicleInfoService.getVehicleInfoById(vehicleProcessInfoDtoDelete.getVehicleInfoId());
		if (vehicleInfo == null) {
			putSessionParam(CommonConstants.MESSAGE_CODE, "MSG_CMN_011");
			putSessionParam(CommonConstants.MESSAGE_PARAMETER, vehicleProcessInfoDtoDelete.getVehicleInfoId());
			redirect("vehicleProcessInfoList.xhtml?faces-redirect=true");
		}
	}

	public String deleteConfirmVehicleProcessInfo() {
		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		int count = vehicleProcessInfoService.deleteVehicleProcessInfo(vehicleProcessInfoDtoDelete, user.getUserID());
		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_007", vehicleProcessInfoDtoDelete.getChassisNo());
		} else {
			addInfoMessage(null, "MSG_CMN_004", vehicleProcessInfoDtoDelete.getChassisNo());
		}
		keepSetMessage();
		return "vehicleProcessInfoList";
	}

	public VehicleProcessInfoDto getVehicleProcessInfoDtoDelete() {
		return vehicleProcessInfoDtoDelete;
	}

	public void setVehicleProcessInfoDtoDelete(VehicleProcessInfoDto vehicleProcessInfoDtoDelete) {
		this.vehicleProcessInfoDtoDelete = vehicleProcessInfoDtoDelete;
	}

	public void setVehicleInfoService(VehicleInfoService vehicleInfoService) {
		this.vehicleInfoService = vehicleInfoService;
	}

	public void setVehicleProcessInfoService(VehicleProcessInfoService vehicleProcessInfoService) {
		this.vehicleProcessInfoService = vehicleProcessInfoService;
	}
}
