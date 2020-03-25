package com.mttl.vlms.web.setting.stt009;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.setting.stt009.dto.PortDto;
import com.mttl.vlms.setting.stt009.service.PortService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * PortDeleteConfirmBean
 * 
 * 
 */
@ManagedBean(name = "PortDeleteConfirmBean")
@ViewScoped
public class PortDeleteConfirmBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 7173852451546888968L;

	@ManagedProperty(value = "#{PortService}")
	private PortService portService;

	private PortDto portDtoDelete;

	@PostConstruct
	public void init() {
		portDtoDelete = (PortDto) getSessionParam(CommonConstants.PORT_DTO_DELETE);
	}

	public void redirect() {
		PortDto port = portService.getPortById(portDtoDelete.getId());
		if (port == null) {
			putSessionParam(CommonConstants.MESSAGE_CODE, "MSG_CMN_011");
			putSessionParam(CommonConstants.MESSAGE_PARAMETER, portDtoDelete.getId());
			redirect("portTerminalControl.xhtml?faces-redirect=true");
		}
	}

	public String deleteConfirmPort() {
		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		int usedCount = portService.checkPortUsed(portDtoDelete.getId());
		/*
		 * if (usedCount > 0) { addErrorMessage(null,
		 * "COUNTRY_DELETE_REJECT_MSG", portDtoDelete.getPortName()); } else {
		 */
		int count = portService.deletePort(portDtoDelete, user.getUserID());
		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_007", portDtoDelete.getPortName());
		} else {
			addInfoMessage(null, "MSG_CMN_004", portDtoDelete.getPortName());
		}
		// }
		keepSetMessage();
		return "portTerminalControl";
	}

	public PortDto getPortDtoDelete() {
		return portDtoDelete;
	}

	public void setPortDtoDelete(PortDto portDtoDelete) {
		this.portDtoDelete = portDtoDelete;
	}

	public void setPortService(PortService portService) {
		this.portService = portService;
	}
}
