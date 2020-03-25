package com.mttl.vlms.web.setting.stt014;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.setting.stt014.dto.ChargesDto;
import com.mttl.vlms.setting.stt014.service.ChargesService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * ChargesInputBean
 * 
 * 
 */
@ManagedBean(name = "ChargesInputBean")
@ViewScoped
public class ChargesInputBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -5038234829522378054L;

	@ManagedProperty(value = "#{ChargesService}")
	private ChargesService chargesService;

	private ChargesDto chargesDto;

	@PostConstruct
	public void init() {
		chargesDto = new ChargesDto();
	}

	public String inputCharges() {
		if (chargesService.checkDuplicateTitle(chargesDto) > 0) {
			addErrorMessage(null, "MSG_CMN_010", chargesDto.getTitle());
			return null;
		}
		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		chargesService.insertCharges(chargesDto, user.getUserID());
		addInfoMessage(null, "MSG_CMN_002", chargesDto.getTitle());
		keepSetMessage();
		return "chargeList";
	}

	public ChargesDto getChargesDto() {
		return chargesDto;
	}

	public void setChargesService(ChargesService chargesService) {
		this.chargesService = chargesService;
	}

}
