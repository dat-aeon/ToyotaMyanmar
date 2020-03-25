package com.mttl.vlms.web.setting.stt012;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.setting.stt012.dto.DealerDto;
import com.mttl.vlms.setting.stt012.service.DealerService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * DealerEditBean
 * 
 * 
 */
@ManagedBean(name = "DealerEditBean")
@ViewScoped
public class DealerEditBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 6273397299924214278L;

	@ManagedProperty(value = "#{DealerService}")
	private DealerService dealerService;

	private DealerDto dealerDtoEdit;

	@PostConstruct
	public void init() {
		dealerDtoEdit = (DealerDto) getSessionParam(CommonConstants.DEALER_DTO_EDIT);
		System.out.println("lol");
	}

	public void redirect() {
		DealerDto dealerDto = dealerService.getDealerById(dealerDtoEdit.getId());
		if (dealerDto == null) {
			putSessionParam(CommonConstants.MESSAGE_CODE, "MSG_CMN_011");
			putSessionParam(CommonConstants.MESSAGE_PARAMETER, dealerDtoEdit.getDealerCode());
			redirect("dealerList.xhtml?faces-redirect=true");
		}
	}

	public String editDealer() {
		dealerDtoEdit.setPhoneNo(StringUtils.join(dealerDtoEdit.getPhoneNoList(), ","));
		dealerDtoEdit.setContactPhoneNo(StringUtils.join(dealerDtoEdit.getContactPhoneNoList(), ","));

		if (dealerDtoEdit.getContractStartDate().compareTo(dealerDtoEdit.getContractEndDate()) > 0) {
			addErrorMessage("End Date should not be early than Start Date");
			return null;

		}
		if (dealerService.checkDuplicateDealerCode(dealerDtoEdit) > 0) {
			addErrorMessage(null, "MSG_CMN_010", dealerDtoEdit.getDealerName());
			return null;
		}

		DealerDto dealerDto = dealerService.getDealerById(dealerDtoEdit.getId());
		if (dealerDto == null) {
			addErrorMessage(null, "MSG_CMN_011", dealerDtoEdit.getDealerName());
			keepSetMessage();
			return "dealerList";
		}
		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		// dealerDtoEdit.setPhoneNo(Utils.listToStringConcat(dealerDtoEdit.getPhoneNoList()));
		// dealerDtoEdit.setContactPhoneNo(Utils.listToStringConcat(dealerDtoEdit.getContactPhoneNoList()));
		int count = dealerService.updateDealer(dealerDtoEdit, user.getUserID());
		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_006", dealerDto.getDealerName());
		} else {
			addInfoMessage(null, "MSG_CMN_003", dealerDto.getDealerName());
		}
		keepSetMessage();
		return "dealerList";
	}

	public DealerDto getDealerDtoEdit() {
		return dealerDtoEdit;
	}

	public void setDealerService(DealerService dealerService) {
		this.dealerService = dealerService;
	}
}
