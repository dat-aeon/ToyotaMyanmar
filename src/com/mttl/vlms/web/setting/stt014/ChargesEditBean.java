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
 * ChargesEditBean
 * 
 * 
 */
@ManagedBean(name = "ChargesEditBean")
@ViewScoped
public class ChargesEditBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 6273397299924214278L;

	@ManagedProperty(value = "#{ChargesService}")
	private ChargesService chargesService;

	private ChargesDto chargesDto;
	private ChargesDto chargesDtoEdit;
	private String oldChargesTitle;

	@PostConstruct
	public void init() {
		chargesDto = (ChargesDto) getSessionParam(CommonConstants.CHARGES_DTO_EDIT);
		oldChargesTitle = chargesDto.getTitle();
	}

	public void redirect() {
		ChargesDto charges = chargesService.getChargesById(chargesDto.getId());

		// If there is no data, show error message at chargesList page.
		if (charges == null) {
			putSessionParam(CommonConstants.MESSAGE_CODE, "MSG_CMN_011");
			putSessionParam(CommonConstants.MESSAGE_PARAMETER, chargesDto.getTitle());
			redirect("chargeList.xhtml?faces-redirect=true");
		}
	}

	public String updateCharges() {

		if (chargesService.checkDuplicateTitle(chargesDto) > 0) {
			addErrorMessage(null, "MSG_CMN_010", chargesDto.getTitle());
			return null;
		}
		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		int updateCount = chargesService.updateCharges(chargesDto, user.getUserID());
		if (updateCount > 0) {
			addInfoMessage(null, "MSG_CMN_003", oldChargesTitle);
		} else {
			addErrorMessage(null, "MSG_CMN_006", chargesDto.getTitle());
		}
		keepSetMessage();
		return "chargeList";
	}

	public ChargesDto getChargesDto() {
		return chargesDto;
	}

	public void setChargesService(ChargesService chargesService) {
		this.chargesService = chargesService;
	}

	public ChargesDto getChargesDtoEdit() {
		return chargesDtoEdit;
	}

	public void setChargesDtoEdit(ChargesDto chargesDtoEdit) {
		this.chargesDtoEdit = chargesDtoEdit;
	}

	public String getOldChargesTitle() {
		return oldChargesTitle;
	}

	public void setOldChargesTitle(String oldChargesTitle) {
		this.oldChargesTitle = oldChargesTitle;
	}
}