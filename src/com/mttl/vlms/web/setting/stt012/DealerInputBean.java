package com.mttl.vlms.web.setting.stt012;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.common.Utils;
import com.mttl.vlms.setting.stt012.dto.DealerDto;
import com.mttl.vlms.setting.stt012.service.DealerService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * DealerInputBean
 * 
 * 
 */
@ManagedBean(name = "DealerInputBean")
@ViewScoped
public class DealerInputBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -237663084453901804L;

	@ManagedProperty(value = "#{DealerService}")
	private DealerService dealerService;

	private DealerDto dealerDto;

	private User loginUser;

	@PostConstruct
	public void init() {
		dealerDto = new DealerDto();
		loginUser = (User) getSessionParam(CommonConstants.LOGIN_USER);
	}

	public String inputDealer() {
		dealerDto.setPhoneNo(StringUtils.join(dealerDto.getPhoneNoList(), ","));
		dealerDto.setContactPhoneNo(StringUtils.join(dealerDto.getContactPhoneNoList(), ","));

		if (dealerDto.getContractStartDate().compareTo(dealerDto.getContractEndDate()) > 0) {
			addErrorMessage("End Date should not be early than Start Date");
			return null;

		}
		if (dealerService.checkDuplicateDealerCode(dealerDto) > 0) {
			addErrorMessage(null, "MSG_CMN_010", dealerDto.getDealerCode());
			return null;
		}
		dealerDto.setPhoneNo(Utils.listToStringConcat(dealerDto.getPhoneNoList()));
		dealerDto.setContactPhoneNo(Utils.listToStringConcat(dealerDto.getContactPhoneNoList()));
		dealerService.insertDealer(dealerDto, loginUser.getUserID());
		addInfoMessage(null, "MSG_CMN_002", dealerDto.getDealerName());
		keepSetMessage();
		return "dealerList";
	}

	public DealerDto getDealerDto() {
		return dealerDto;
	}

	public void setDealerDto(DealerDto dealerDto) {
		this.dealerDto = dealerDto;
	}

	public void setDealerService(DealerService dealerService) {
		this.dealerService = dealerService;
	}
}
