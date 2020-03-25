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
 * DriverInputBean
 * 
 * 
 */
@ManagedBean(name = "DriverInputBean")
@ViewScoped
public class DriverInputBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -237663084453901804L;

	@ManagedProperty(value = "#{DriverService}")
	private DriverService driverService;

	private DriverDto driverDto;

	private User loginUser;

	private List<StateDivisionInfoSelectDto> stateDivisionList;

	private Map<Integer, String> genderTypeMap;

	private Map<Integer, String> driverTypeMap;

	@PostConstruct
	public void init() {
		stateDivisionList = driverService.getStateDivisionList();
		setGenderTypeMap(ViewCode.getGenderList());
		setDriverTypeMap(ViewCode.getDriverTypeList());
		driverDto = new DriverDto();
		if (CollectionUtils.isNotEmpty(stateDivisionList)) {
			driverDto.setDriverStateDivision(stateDivisionList.get(0));
		}

		loginUser = (User) getSessionParam(CommonConstants.LOGIN_USER);
	}

	public String inputDriver() {

		if (null != driverDto.getJoinDate()) {
			if (driverDto.getDateOfBirth().compareTo(driverDto.getJoinDate()) > 0) {
				addErrorMessage("Join Date should not be early than Date of Birth");
				return null;
			}
		}

		if (null != driverDto.getLicenseExpireDate()) {
			if (driverDto.getDateOfBirth().compareTo(driverDto.getLicenseExpireDate()) > 0) {
				addErrorMessage("License Expire Date should not be early than Date of Birth");
				return null;
			}
		}

		if (!(driverDto.getStaffId().contains("MTTL") || (driverDto.getStaffId().contains("BIZP")))) {
			addErrorMessage(null, "MSG_CMN_048", driverDto.getStaffId());
			return null;
		}

		if (driverDto.getStaffId().equals("MTTL")) {
			addErrorMessage(null, "MSG_CMN_046", driverDto.getStaffId());
			return null;
		}

		if (driverService.checkDuplicateDriverCode(driverDto) > 0) {
			addErrorMessage(null, "MSG_CMN_010", driverDto.getBarcodeId());
			return null;
		}
		if (driverService.checkDuplicateStaffId(driverDto) > 0) {
			addErrorMessage(null, "MSG_CMN_010", driverDto.getStaffId());
			return null;
		}

		if (driverService.checkDuplicateDriverNRCNo(driverDto) > 0) {
			addErrorMessage(null, "MSG_CMN_010", driverDto.getNrcNo());
			return null;
		}

		if (driverService.checkDuplicateLicenseNo(driverDto) > 0) {
			addErrorMessage(null, "MSG_CMN_010", driverDto.getLicenseNo());
			return null;
		}

		driverDto.setBarcodeId("HR" + driverDto.getStaffId());
		driverDto.setPhoneNo(StringUtils.join(driverDto.getPhoneNoList(), ","));
		driverDto.setStatus(1);

		driverService.insertDriver(driverDto, loginUser.getUserID());
		addInfoMessage(null, "MSG_CMN_002", driverDto.getDriverName());
		keepSetMessage();
		return "driverList";
	}

	public void dirverTypeChange() {
		if (driverDto.getType() == 1) {
			driverDto.setStaffId(driverService.getNextBPStaffId());
			driverDto.setBarcodeId("HR" + driverDto.getStaffId());
		} else {
			driverDto.setStaffId("MTTL");
			driverDto.setBarcodeId("HR");
		}
	}

	public DriverDto getDriverDto() {
		return driverDto;
	}

	public void setDriverDto(DriverDto driverDto) {
		this.driverDto = driverDto;
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
