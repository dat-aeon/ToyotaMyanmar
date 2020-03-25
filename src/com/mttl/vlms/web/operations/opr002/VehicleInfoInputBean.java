package com.mttl.vlms.web.operations.opr002;

import java.io.Serializable;
import java.util.List;

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
 * VehicleInfoInputBean
 * 
 * 
 */
@ManagedBean(name = "VehicleInfoInputBean")
@ViewScoped
public class VehicleInfoInputBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -6038234829522378054L;

	@ManagedProperty(value = "#{VehicleInfoService}")
	private VehicleInfoService vehicleInfoService;
	private List<VehicleInfoDto> portList;
	private List<VehicleInfoDto> dealerList;
	private VehicleInfoDto vehicleInfoDto;

	@PostConstruct
	public void init() {
		vehicleInfoDto = new VehicleInfoDto();
		portList = vehicleInfoService.getPortList();
		dealerList = vehicleInfoService.getDealerList();
	}

	public String inputVehicleInfo() {
		if (vehicleInfoService.checkDuplicateChassisNo(vehicleInfoDto) > 0) {
			addErrorMessage(null, "MSG_CMN_010", vehicleInfoDto.getChassisNo());
			return null;
		}
		if (vehicleInfoService.checkDuplicateLicenseNo(vehicleInfoDto) > 0) {
			addErrorMessage(null, "MSG_CMN_010", vehicleInfoDto.getLicenseNo());
			return null;
		}

		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		vehicleInfoService.insertVehicleInfo(vehicleInfoDto, user.getUserID());
		addInfoMessage(null, "MSG_CMN_002", vehicleInfoDto.getChassisNo());
		keepSetMessage();
		return "vehicleInfoList";
	}

	public VehicleInfoDto getVehicleInfoDto() {
		return vehicleInfoDto;
	}

	public void setVehicleInfoService(VehicleInfoService vehicleInfoService) {
		this.vehicleInfoService = vehicleInfoService;
	}

	public List<VehicleInfoDto> getPortList() {
		return portList;
	}

	public void setPortList(List<VehicleInfoDto> portList) {
		this.portList = portList;
	}

	public List<VehicleInfoDto> getDealerList() {
		return dealerList;
	}

	public void setDealerList(List<VehicleInfoDto> dealerList) {
		this.dealerList = dealerList;
	}

}
