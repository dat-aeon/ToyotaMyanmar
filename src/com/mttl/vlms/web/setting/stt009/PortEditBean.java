package com.mttl.vlms.web.setting.stt009;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.common.Utils;
import com.mttl.vlms.setting.stt009.dto.PortDto;
import com.mttl.vlms.setting.stt009.service.PortService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * PortEditBean
 * 
 * 
 */
@ManagedBean(name = "PortEditBean")
@ViewScoped
public class PortEditBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 6273397299924214278L;

	@ManagedProperty(value = "#{PortService}")
	private PortService portService;

	private PortDto portDtoEdit;

	@PostConstruct
	public void init() {
		portDtoEdit = (PortDto) getSessionParam(CommonConstants.PORT_DTO_EDIT);
	}

	public void redirect() {
		PortDto portDto = portService.getPortById(portDtoEdit.getId());
		if (portDto == null) {
			putSessionParam(CommonConstants.MESSAGE_CODE, "MSG_CMN_011");
			putSessionParam(CommonConstants.MESSAGE_PARAMETER, portDtoEdit.getId());
			redirect("portTerminalControl.xhtml?faces-redirect=true");
		}
	}

	public String editPort() {

		if (portService.checkDuplicateFullName(portDtoEdit) > 0) {
			addErrorMessage(null, "MSG_CMN_010", portDtoEdit.getFullName());
			return null;
		}
		PortDto portDto = portService.getPortById(portDtoEdit.getId());
		if (portDto == null) {
			addErrorMessage(null, "MSG_CMN_011", portDtoEdit.getPortName());
			keepSetMessage();
			return "portTerminalControl";
		}
		if (portService.checkDuplicateShortName(portDtoEdit.getId(), portDtoEdit.getShortName()) > 0) {
			addErrorMessage(null, "MSG_CMN_010", "Short Name");
			return null;
		}

		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		portDtoEdit.setPhoneNo(Utils.listToStringConcat(portDtoEdit.getPhoneNoList()));
		int count = portService.updatePort(portDtoEdit, user.getUserID());
		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_006", portDto.getPortName());
		} else {
			addInfoMessage(null, "MSG_CMN_003", portDto.getPortName());
		}
		keepSetMessage();
		return "portTerminalControl";
	}

	public PortDto getPortDtoEdit() {
		return portDtoEdit;
	}

	public void setPortService(PortService portService) {
		this.portService = portService;
	}

}
