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
 * ChargesDeleteConfirmBean
 * 
 * 
 */
@ManagedBean(name = "ChargesDeleteConfirmBean")
@ViewScoped
public class ChargesDeleteConfirmBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 7173852451546888968L;

	@ManagedProperty(value = "#{ChargesService}")
	private ChargesService chargesService;

	private ChargesDto chargesDto;

	@PostConstruct
	public void init() {
		chargesDto = (ChargesDto) getSessionParam(CommonConstants.CHARGES_DTO_DELETE);
	}

	public void redirect() {
		ChargesDto charges = chargesService.getChargesById(chargesDto.getId());
		if (charges == null) {
			putSessionParam(CommonConstants.MESSAGE_CODE, "MSG_CMN_011");
			putSessionParam(CommonConstants.MESSAGE_PARAMETER, chargesDto.getTitle());
			redirect("chargeList.xhtml?faces-redirect=true");
		}
	}

	public String deleteConfirmCharges() {

		/*
		 * int usedCount = chargesService.checkChargesUsed(chargesDto.getId()); if
		 * (usedCount > 0) {
		 */
		// addErrorMessage(null, "CHARGE_DELETE_REJECT_MSG",
		// chargesDto.getTitle());
		// } else {
		// int count = chargesService.deleteCharges(chargesDto);

		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);

		chargesDto.setDeleteFlg(true);
		int updateCount = chargesService.updateCharges(chargesDto, user.getUserID());

		if (updateCount > 0) {
			addInfoMessage(null, "MSG_CMN_004", chargesDto.getTitle());
		} else {
			addErrorMessage(null, "MSG_CMN_007", chargesDto.getTitle());
		}
		// }
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
