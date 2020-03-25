package com.mttl.vlms.web.setting.stt012;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.datatable.DataTable;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.common.Utils;
import com.mttl.vlms.setting.stt012.dto.DealerDto;
import com.mttl.vlms.setting.stt012.service.DealerService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * DealerListBean
 * 
 * 
 *
 */
@ManagedBean(name = "DealerListBean")
@ViewScoped
public class DealerListBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -6598961227154170581L;

	@ManagedProperty(value = "#{DealerService}")
	private DealerService dealerService;

	private String dealerName;

	private List<DealerDto> dealerList;

	private List<DealerDto> filterDealerList;

	private boolean paginatorVisible;

	private Integer firstRowCount;

	private boolean checkAutoUpdate;

	@PostConstruct
	public void init() {
		dealerName = (String) getSessionParam(CommonConstants.DEALER_NAME);
		firstRowCount = (Integer) getSessionParam(CommonConstants.FIRST_ROW_COUNT);
		if (firstRowCount == null) {
			firstRowCount = 0;
		}
		removeSessionParam(CommonConstants.DEALER_DTO_DELETE, CommonConstants.DEALER_DTO_EDIT, CommonConstants.DEALER_NAME, CommonConstants.FIRST_ROW_COUNT);
		searchDealer();
		checkAutoUpdate = false;
	}

	public void searchDealer() {
		paginatorVisible = false;
		dealerList = dealerService.findByDealerName(dealerName);
		for (DealerDto dto : dealerList) {
			if (null != dto.getPhoneNo() && null != dto.getContactPhoneNo()) {

				dto.setPhoneNoList(Arrays.asList(StringUtils.splitPreserveAllTokens(dto.getPhoneNo(), ",")));
				dto.setPhList(dto.getPhoneNoList().toString().replace("[", ""));
				dto.setPhList(dto.getPhList().toString().replace("]", ""));
				// dto.setContactPhoneNoList(Utils.stringToList(dto.getContactPhoneNo()));
				dto.setContactPhoneNoList(Arrays.asList(StringUtils.splitPreserveAllTokens(dto.getContactPhoneNo(), ",")));
				dto.setContactList(dto.getContactPhoneNoList().toString().replace("[", ""));
				dto.setContactList(dto.getPhList().toString().replace("]", ""));
			}
		}
		if (dealerList.size() > 10) {
			paginatorVisible = true;
		}
	}

	public void showMessage() {
		String messageCode = (String) getSessionParam(CommonConstants.MESSAGE_CODE);
		String messageParameter = (String) getSessionParam(CommonConstants.MESSAGE_PARAMETER);
		removeSessionParam(CommonConstants.MESSAGE_CODE, CommonConstants.MESSAGE_PARAMETER);
		if (messageCode != null && messageParameter != null) {
			addErrorMessage(null, messageCode, messageParameter);
		}
	}

	public String editDealer(DealerDto dealerDto) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmDealerList:tblDealerList");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);
		putSessionParam(CommonConstants.DEALER_DTO_EDIT, dealerDto);
		putSessionParam(CommonConstants.DEALER_NAME, dealerName);
		return "dealerEdit";
	}

	public String deleteConfirmDealer(DealerDto dealerDto) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmDealerList:tblDealerList");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);
		putSessionParam(CommonConstants.DEALER_DTO_DELETE, dealerDto);
		putSessionParam(CommonConstants.DEALER_NAME, dealerName);
		return "dealerDeleteConfirm";
	}

	public void disabledDealer(DealerDto dealerDtoEdit) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmDealerList:tblDealerList");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);

		System.out.println(dealerDtoEdit.getId());
		dealerDtoEdit.setDisabled(!dealerDtoEdit.isDisabled());

		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);

		int count = dealerService.updateDealer(dealerDtoEdit, user.getUserID());

		checkAutoUpdate = true;

		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_006", dealerDtoEdit.getDealerName());
		} else {
			addInfoMessage(null, "MSG_CMN_003", dealerDtoEdit.getDealerName());
		}
		searchDealer();
	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		DealerDto dealer = (DealerDto) value;
		return dealer.getDealerCode().toLowerCase().contains(filterText) || dealer.getDealerName().toLowerCase().contains(filterText)
				|| dealer.getPhoneNo().toLowerCase().contains(filterText) || dealer.getContactEmail().toLowerCase().contains(filterText)
				|| dealer.getContactName().toLowerCase().contains(filterText) || dealer.getContactPhoneNo().toLowerCase().contains(filterText)
				|| dealer.getContactPosition().toLowerCase().contains(filterText) || dealer.getContractStartDate().toString().contains(filterText)
				|| Utils.formatByPattern(dealer.getContractStartDate(), CommonConstants.DEFAULT_DATE_FORMAT).contains(filterText)
				|| Utils.formatByPattern(dealer.getContractEndDate(), CommonConstants.DEFAULT_DATE_FORMAT).contains(filterText)
				|| dealer.getContractEndDate().toString().contains(filterText) || (dealer.isDisabled() ? "enable" : "disable").toLowerCase().contains(filterText);

	}

	public void changeAutoUpdate() {
		checkAutoUpdate = true;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public List<DealerDto> getDealerList() {
		return dealerList;
	}

	public void setDealerList(List<DealerDto> dealerList) {
		this.dealerList = dealerList;
	}

	public boolean isPaginatorVisible() {
		return paginatorVisible;
	}

	public void setDealerService(DealerService dealerService) {
		this.dealerService = dealerService;
	}

	public Integer getFirstRowCount() {
		return firstRowCount;
	}

	public List<DealerDto> getFilterDealerList() {
		return filterDealerList;
	}

	public void setFilterDealerList(List<DealerDto> filterDealerList) {
		this.filterDealerList = filterDealerList;
	}

	public boolean isCheckAutoUpdate() {
		return checkAutoUpdate;
	}

	public void setCheckAutoUpdate(boolean checkAutoUpdate) {
		this.checkAutoUpdate = checkAutoUpdate;
	}

}
