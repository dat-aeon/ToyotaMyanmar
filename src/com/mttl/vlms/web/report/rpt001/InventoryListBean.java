package com.mttl.vlms.web.report.rpt001;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.report.rpt001.dto.InventoryDto;
import com.mttl.vlms.report.rpt001.service.InventoryService;
import com.mttl.vlms.web.common.BaseBean;

/**
 * InventoryListBean
 * 
 * 
 *
 */
@ManagedBean(name = "InventoryListBean")
@ViewScoped
public class InventoryListBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = -6598961226154170581L;
	@ManagedProperty(value = "#{InventoryService}")
	private InventoryService inventoryService;
	private InventoryDto inventorySearchDto;
	private List<InventoryDto> inventoryList;
	private List<InventoryDto> inventorySearchList;
	private boolean paginatorVisible;
	/** for paging page */
	private Integer firstRowCount;

	@PostConstruct
	public void init() {

		inventorySearchDto = new InventoryDto();
		firstRowCount = (Integer) getSessionParam(CommonConstants.FIRST_ROW_COUNT);
		if (firstRowCount == null) {
			firstRowCount = 0;
		}

		// searchInventory();
		removeSessionParam(CommonConstants.FIRST_ROW_COUNT, CommonConstants.CHASSIS_NO, CommonConstants.MODEL, CommonConstants.COLOR, CommonConstants.PORT_OF_DISCHARGE);
	}

	public void showMessage() {
		String messageCode = (String) getSessionParam(CommonConstants.MESSAGE_CODE);
		String messageParameter = (String) getSessionParam(CommonConstants.MESSAGE_PARAMETER);
		removeSessionParam(CommonConstants.MESSAGE_CODE, CommonConstants.MESSAGE_PARAMETER);
		if (messageCode != null && messageParameter != null) {
			addErrorMessage(null, messageCode, messageParameter);
		}
	}

	public void searchInventory() {
		paginatorVisible = false;

		inventoryList = inventoryService.searchByChassisNo(inventorySearchDto);

		if (inventoryList.size() > 10) {
			paginatorVisible = true;
		}
	}

	public InventoryDto getInventorySearchDto() {
		return inventorySearchDto;
	}

	public List<InventoryDto> getInventoryList() {
		return inventoryList;
	}

	public void setInventoryList(List<InventoryDto> inventoryList) {
		this.inventoryList = inventoryList;
	}

	public boolean isPaginatorVisible() {
		return paginatorVisible;
	}

	public void setInventoryService(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	public Integer getFirstRowCount() {
		return firstRowCount;
	}

	public List<InventoryDto> getInventorySearchList() {
		return inventorySearchList;
	}

	public void setInventorySearchList(List<InventoryDto> inventorySearchList) {
		this.inventorySearchList = inventorySearchList;
	}

	public void setInventorySearchDto(InventoryDto inventorySearchDto) {
		this.inventorySearchDto = inventorySearchDto;
	}

}