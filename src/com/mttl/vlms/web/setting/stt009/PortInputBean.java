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
 * PortInputBean
 * 
 * 
 */
@ManagedBean(name = "PortInputBean")
@ViewScoped
public class PortInputBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -6038234829522378054L;

	@ManagedProperty(value = "#{PortService}")
	private PortService portService;

	private PortDto portDto;

	@PostConstruct
	public void init() {
		portDto = new PortDto();
	}

	public String inputPort() {
		if (portService.checkDuplicateFullName(portDto) > 0) {
			addErrorMessage(null, "MSG_CMN_010", portDto.getFullName());
			return null;
		}
		int shortNameCount = portService.checkDuplicateShortName(null, portDto.getShortName());
		if (shortNameCount > 0) {
			addErrorMessage(null, "MSG_CMN_010", "Short Name");
			return null;
		}

		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		portDto.setPhoneNo(Utils.listToStringConcat(portDto.getPhoneNoList()));
		portService.insertPort(portDto, user.getUserID());
		addInfoMessage(null, "MSG_CMN_002", portDto.getFullName());
		keepSetMessage();
		return "portTerminalControl";
	}

	public PortDto getPortDto() {
		return portDto;
	}

	public void setPortService(PortService portService) {
		this.portService = portService;
	}

}
