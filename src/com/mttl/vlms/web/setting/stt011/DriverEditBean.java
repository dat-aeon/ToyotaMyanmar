package com.mttl.vlms.web.setting.stt011;

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
import com.mttl.vlms.common.selectdto.StateDivisionInfoSelectDto;
import com.mttl.vlms.setting.stt011.dto.DriverDto;
import com.mttl.vlms.setting.stt011.service.DriverService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * DriverEditBean
 * 
 * 
 */
@ManagedBean(name = "DriverEditBean")
@ViewScoped
public class DriverEditBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 6273397299924214278L;

	@ManagedProperty(value = "#{DriverService}")
	private DriverService driverService;

	private DriverDto driverDtoEdit;

	private List<StateDivisionInfoSelectDto> stateDivisionList;

	private Map<Integer, String> genderTypeMap;

	private Map<Integer, String> driverTypeMap;

	@PostConstruct
	public void init() {
		stateDivisionList = driverService.getStateDivisionList();
		setGenderTypeMap(ViewCode.getGenderList());
		setDriverTypeMap(ViewCode.getDriverTypeList());
		driverDtoEdit = new DriverDto();
		if (CollectionUtils.isNotEmpty(stateDivisionList)) {
			driverDtoEdit.setDriverStateDivision(stateDivisionList.get(0));
		}
		driverDtoEdit = (DriverDto) getSessionParam(CommonConstants.DRIVER_DTO_EDIT);
	}

	public void redirect() {

		DriverDto driverDto = driverService.getDriverById(driverDtoEdit.getId());
		if (driverDto == null) {
			putSessionParam(CommonConstants.MESSAGE_CODE, "MSG_CMN_011");
			putSessionParam(CommonConstants.MESSAGE_PARAMETER, driverDtoEdit.getBarcodeId());
			redirect("driverList.xhtml?faces-redirect=true");
		}
	}

	public String editDriver() {
		if (null != driverDtoEdit.getJoinDate()) {
			if (driverDtoEdit.getDateOfBirth().compareTo(driverDtoEdit.getJoinDate()) > 0) {
				addErrorMessage("Join Date should not be early than Date of Birth");
				return null;
			}
		}

		if (null != driverDtoEdit.getLicenseExpireDate()) {
			if (driverDtoEdit.getDateOfBirth().compareTo(driverDtoEdit.getLicenseExpireDate()) > 0) {
				addErrorMessage("License Expire Date should not be early than Date of Birth");
				return null;
			}
		}

		if (!(driverDtoEdit.getStaffId().contains("MTTL") || (driverDtoEdit.getStaffId().contains("BIZP")))) {
			addErrorMessage(null, "MSG_CMN_048", driverDtoEdit.getStaffId());
			return null;
		}

		if (driverDtoEdit.getStaffId().equals("MTTL")) {
			addErrorMessage(null, "MSG_CMN_046", driverDtoEdit.getStaffId());
			return null;
		}

		if (driverService.checkDuplicateDriverCode(driverDtoEdit) > 0) {
			addErrorMessage(null, "MSG_CMN_010", driverDtoEdit.getBarcodeId());
			return null;
		}
		if (driverService.checkDuplicateStaffId(driverDtoEdit) > 0) {
			addErrorMessage(null, "MSG_CMN_010", driverDtoEdit.getStaffId());
			return null;
		}
		if (driverService.checkDuplicateDriverNRCNo(driverDtoEdit) > 0) {
			addErrorMessage(null, "MSG_CMN_010", driverDtoEdit.getNrcNo());
			return null;
		}
		if (driverService.checkDuplicateLicenseNo(driverDtoEdit) > 0) {
			addErrorMessage(null, "MSG_CMN_010", driverDtoEdit.getLicenseNo());
			return null;
		}

		DriverDto driverDto = driverService.getDriverById(driverDtoEdit.getId());
		if (driverDto == null) {
			addErrorMessage(null, "MSG_CMN_011", driverDtoEdit.getDriverName());
			return "driverList";
		}

		driverDtoEdit.setPhoneNo(StringUtils.join(driverDtoEdit.getPhoneNoList(), ","));
		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		int count = driverService.updateDriver(driverDtoEdit, user.getUserID());
		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_006", driverDto.getDriverName());
		} else {
			addInfoMessage(null, "MSG_CMN_003", driverDto.getDriverName());
		}
		keepSetMessage();
		return "driverList";
	}

	public DriverDto getDriverDtoEdit() {
		return driverDtoEdit;
	}

	public void setDriverService(DriverService driverService) {
		this.driverService = driverService;
	}

	public Map<Integer, String> getGenderTypeMap() {
		return genderTypeMap;
	}

	public void setGenderTypeMap(Map<Integer, String> genderTypeMap) {
		this.genderTypeMap = genderTypeMap;
	}

	public Map<Integer, String> getDriverTypeMap() {
		return driverTypeMap;
	}

	public void setDriverTypeMap(Map<Integer, String> driverTypeMap) {
		this.driverTypeMap = driverTypeMap;
	}

	public List<StateDivisionInfoSelectDto> getStateDivisionList() {
		return stateDivisionList;
	}

	public void setStateDivisionList(List<StateDivisionInfoSelectDto> stateDivisionList) {
		this.stateDivisionList = stateDivisionList;
	}
}
