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
 * VehicleInfoEditBean
 * 
 * 
 */
@ManagedBean(name = "VehicleInfoEditBean")
@ViewScoped
public class VehicleInfoEditBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 6273397299924214278L;

	@ManagedProperty(value = "#{VehicleInfoService}")
	private VehicleInfoService vehicleInfoService;

	private VehicleInfoDto vehicleInfoDtoEdit;

	@PostConstruct
	public void init() {
		vehicleInfoDtoEdit = (VehicleInfoDto) getSessionParam(CommonConstants.VEHICLE_INFO_DTO_EDIT);
	}

	public void redirect() {
		VehicleInfoDto vehicleInfoDto = vehicleInfoService.getVehicleInfoById(vehicleInfoDtoEdit.getId());
		if (vehicleInfoDto == null) {
			putSessionParam(CommonConstants.MESSAGE_CODE, "MSG_CMN_011");
			putSessionParam(CommonConstants.MESSAGE_PARAMETER, vehicleInfoDtoEdit.getId());
			redirect("vehicleInfoList.xhtml?faces-redirect=true");
		}
	}
	
	

	public String editVehicleInfo() {

		if (vehicleInfoService.checkDuplicateChassisNo(vehicleInfoDtoEdit) > 0) {
			addErrorMessage(null, "MSG_CMN_010", vehicleInfoDtoEdit.getChassisNo());
			return null;
		}
		VehicleInfoDto vehicleInfoDto = vehicleInfoService.getVehicleInfoById(vehicleInfoDtoEdit.getId());
		if (vehicleInfoDto == null) {
			addErrorMessage(null, "MSG_CMN_011", vehicleInfoDtoEdit.getChassisNo());
			keepSetMessage();
			return "vehicleInfoList";
		}
		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		int count = vehicleInfoService.updateVehicleInfo(vehicleInfoDtoEdit, user.getUserID());
		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_006", vehicleInfoDto.getChassisNo());
		} else {
			addInfoMessage(null, "MSG_CMN_003", vehicleInfoDto.getChassisNo());
		}
		keepSetMessage();
		return "vehicleInfoList";
	}

	public VehicleInfoDto getVehicleInfoDtoEdit() {
		return vehicleInfoDtoEdit;
	}

	public void setVehicleInfoService(VehicleInfoService vehicleInfoService) {
		this.vehicleInfoService = vehicleInfoService;
	}

}
