package com.mttl.vlms.web.setting.stt012;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.setting.stt012.dto.DealerDto;
import com.mttl.vlms.setting.stt012.service.DealerService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * DealerDeleteConfirmBean
 * 
 * 
 */
@ManagedBean(name = "DealerDeleteConfirmBean")
@ViewScoped
public class DealerDeleteConfirmBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 7173852451546888968L;

	@ManagedProperty(value = "#{DealerService}")
	private DealerService dealerService;

	private DealerDto dealerDtoDelete;

	@PostConstruct
	public void init() {
		dealerDtoDelete = (DealerDto) getSessionParam(CommonConstants.DEALER_DTO_DELETE);
	}

	public void redirect() {
		DealerDto dealer = dealerService.getDealerById(dealerDtoDelete.getId());
		if (dealer == null) {
			putSessionParam(CommonConstants.MESSAGE_CODE, "MSG_CMN_011");
			putSessionParam(CommonConstants.MESSAGE_PARAMETER, dealerDtoDelete.getDealerName());
			redirect("dealerList.xhtml?faces-redirect=true");
		}
	}

	public String deleteConfirmDealer() {
		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);

		int count = dealerService.deleteDealer(dealerDtoDelete, user.getUserID());
		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_007", dealerDtoDelete.getDealerName());
		} else {
			addInfoMessage(null, "MSG_CMN_004", dealerDtoDelete.getDealerName());
		}
		// }
		keepSetMessage();
		return "dealerList";
	}

	public DealerDto getDealerDtoDelete() {
		return dealerDtoDelete;
	}

	public void setDealerDtoDelete(DealerDto dealerDtoDelete) {
		this.dealerDtoDelete = dealerDtoDelete;
	}

	public void setDealerService(DealerService dealerService) {
		this.dealerService = dealerService;
	}
}
