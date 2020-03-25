package com.mttl.vlms.web.setting.stt014;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.setting.stt014.dto.ChargesDto;
import com.mttl.vlms.setting.stt014.service.ChargesService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * ChargesListBean
 * 
 * 
 *
 */
@ManagedBean(name = "ChargesListBean")
@ViewScoped
public class ChargesListBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = -6598961227154170581L;
	@ManagedProperty(value = "#{ChargesService}")
	private ChargesService chargesService;
	private ChargesDto chargesSearchDto;
	private List<ChargesDto> chargeList;
	private List<ChargesDto> filterChargesList;
	private boolean paginatorVisible;
	/** for paging page */
	private Integer firstRowCount;

	@PostConstruct
	public void init() {
		chargesSearchDto = new ChargesDto();
		String chargesName = (String) getSessionParam(CommonConstants.CHARGES_TITLE);
		chargesSearchDto.setTitle(chargesName);
		firstRowCount = (Integer) getSessionParam(CommonConstants.FIRST_ROW_COUNT);
		if (firstRowCount == null) {
			firstRowCount = 0;
		}
		searchCharges();
		removeSessionParam(CommonConstants.FIRST_ROW_COUNT, CommonConstants.CHARGES_TITLE, CommonConstants.CHARGES_DTO_DELETE, CommonConstants.CHARGES_DTO_EDIT);
	}

	public void showMessage() {
		String messageCode = (String) getSessionParam(CommonConstants.MESSAGE_CODE);
		String messageParameter = (String) getSessionParam(CommonConstants.MESSAGE_PARAMETER);
		removeSessionParam(CommonConstants.MESSAGE_CODE, CommonConstants.MESSAGE_PARAMETER);
		if (messageCode != null && messageParameter != null) {
			addErrorMessage(null, messageCode, messageParameter);
		}
	}

	public void disabledCharges(ChargesDto chargesDtoEdit) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmChargeList:chargeList");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);

		System.out.println(chargesDtoEdit.getId());
		chargesDtoEdit.setDisabled(!chargesDtoEdit.isDisabled());

		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);

		int count = chargesService.updateCharges(chargesDtoEdit, user.getUserID());

		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_006", chargesDtoEdit.getTitle());
		} else {
			addInfoMessage(null, "MSG_CMN_003", chargesDtoEdit.getTitle());
		}
		searchCharges();
	}

	public void searchCharges() {
		paginatorVisible = false;
		chargeList = chargesService.findByTitle(chargesSearchDto.getTitle());
		if (chargeList.size() > 10) {
			paginatorVisible = true;
		}
	}

	public String editCharges(ChargesDto chargesDto) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmChargeList:chargeList");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);
		putSessionParam(CommonConstants.CHARGES_DTO_EDIT, chargesDto);
		putSessionParam(CommonConstants.CHARGES_TITLE, this.chargesSearchDto.getTitle());
		return "chargeEdit";
	}

	public String deleteConfirmCharges(ChargesDto chargesDto) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmChargeList:chargeList");// frmChargeList:tblCharges
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);
		putSessionParam(CommonConstants.CHARGES_DTO_DELETE, chargesDto);
		putSessionParam(CommonConstants.CHARGES_TITLE, this.chargesSearchDto.getTitle());
		return "chargeDeleteConfirm";
	}

	public ChargesDto getChargesSearchDto() {
		return chargesSearchDto;
	}

	public boolean isPaginatorVisible() {
		return paginatorVisible;
	}

	public void setChargesService(ChargesService chargesService) {
		this.chargesService = chargesService;
	}

	public Integer getFirstRowCount() {
		return firstRowCount;
	}

	public List<ChargesDto> getFilterChargesList() {
		return filterChargesList;
	}

	public void setFilterChargesList(List<ChargesDto> filterChargesList) {
		this.filterChargesList = filterChargesList;
	}

	public List<ChargesDto> getChargeList() {
		return chargeList;
	}

	public void setChargeList(List<ChargesDto> chargeList) {
		this.chargeList = chargeList;
	}
}