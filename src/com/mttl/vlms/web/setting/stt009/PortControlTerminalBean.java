package com.mttl.vlms.web.setting.stt009;

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
import com.mttl.vlms.setting.stt009.dto.PortDto;
import com.mttl.vlms.setting.stt009.service.PortService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * PortControlTerminalBean
 * 
 * 
 *
 */
@ManagedBean(name = "PortControlTerminalBean")
@ViewScoped

public class PortControlTerminalBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = -6598961226154170581L;
	@ManagedProperty(value = "#{PortService}")
	private PortService portService;
	private PortDto portSearchDto;
	private List<PortDto> portList;
	private List<PortDto> filterPortList;
	private boolean paginatorVisible;
	/** for paging page */
	private Integer firstRowCount;

	@PostConstruct
	public void init() {
		portSearchDto = new PortDto();
		firstRowCount = (Integer) getSessionParam(CommonConstants.FIRST_ROW_COUNT);
		if (firstRowCount == null) {
			firstRowCount = 0;
		}
		searchPort();
		removeSessionParam(CommonConstants.FIRST_ROW_COUNT, CommonConstants.PORT_SHORT_NAME, CommonConstants.PORT_FULL_NAME, CommonConstants.PORT_PHONE_NO,
				CommonConstants.PORT_ADDRESS, CommonConstants.PORT_DESCRIPTION, CommonConstants.PORT_DTO_DELETE, CommonConstants.PORT_DTO_EDIT);
	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		int filterInt = getInteger(filterText);

		PortDto port = (PortDto) value;
		return port.getShortName().toLowerCase().contains(filterText) || port.getFullName().toLowerCase().contains(filterText)
				|| port.getPhoneNo().toLowerCase().contains(filterText) || port.getAddress().toLowerCase().contains(filterText)
				|| port.getDescription().toLowerCase().contains(filterText) || (port.isDisabled() ? "enable" : "disable").toString().toLowerCase().contains(filterText);
	}

	private int getInteger(String string) {
		try {
			return Integer.valueOf(string);
		} catch (NumberFormatException ex) {
			return 0;
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

	public void searchPort() {

		portList = portService.findByPortName(portSearchDto.getFullName());
		/*
		 * for (PortDto dto : portList) {
		 * 
		 * dto.setPhoneNoList(Utils.stringToList(dto.getPhoneNo().replace(",",
		 * ",\r\n")));
		 * 
		 * }
		 */

		for (PortDto dto : portList) {
			if (null != dto.getPhoneNo()) {

				dto.setPhoneNoList(Arrays.asList(StringUtils.splitPreserveAllTokens(dto.getPhoneNo(), ",")));
				dto.setPhList(dto.getPhoneNoList().toString().replace("[", ""));
				dto.setPhList(dto.getPhList().toString().replace("]", ""));
			}
		}

		if (portList.size() > 10) {
			paginatorVisible = true;
		}
	}

	public String editPort(PortDto portDto) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmPort:tblPort");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);
		putSessionParam(CommonConstants.PORT_DTO_EDIT, portDto);
		putSessionParam(CommonConstants.PORT_SHORT_NAME, this.portSearchDto.getShortName());
		putSessionParam(CommonConstants.PORT_FULL_NAME, this.portSearchDto.getFullName());
		putSessionParam(CommonConstants.PORT_PHONE_NO, this.portSearchDto.getPhoneNo());
		putSessionParam(CommonConstants.PORT_ADDRESS, this.portSearchDto.getAddress());
		putSessionParam(CommonConstants.PORT_DESCRIPTION, this.portSearchDto.getDescription());
		return "portEdit";
	}

	public String deleteConfirmPort(PortDto portDto) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmPort:tblPort");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);
		putSessionParam(CommonConstants.PORT_DTO_DELETE, portDto);
		putSessionParam(CommonConstants.PORT_SHORT_NAME, this.portSearchDto.getShortName());
		/*
		 * putSessionParam(CommonConstants.PORT_FULL_NAME,
		 * this.portSearchDto.getFullName());
		 * putSessionParam(CommonConstants.PORT_PHONE_NO,
		 * this.portSearchDto.getPhoneNo());
		 * putSessionParam(CommonConstants.PORT_ADDRESS,
		 * this.portSearchDto.getAddress());
		 * putSessionParam(CommonConstants.PORT_DESCRIPTION,
		 * this.portSearchDto.getDescription());
		 */
		return "portDeleteConfirm";
	}

	public void disabledPort(PortDto portDtoEdit) {

		portDtoEdit.setDisabled(!portDtoEdit.isDisabled());

		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmPort:tblPort");

		firstRowCount = dataTable.getFirst();

		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);

		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);

		int count = portService.updatePort(portDtoEdit, user.getUserID());

		System.out.println(count);

		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_006", portDtoEdit.getId());
		} else {
			addInfoMessage(null, "MSG_CMN_003", portDtoEdit.getFullName());
		}
		searchPort();
	}

	public PortDto getPortSearchDto() {
		return portSearchDto;
	}

	public List<PortDto> getPortList() {
		return portList;
	}

	public void setPortList(List<PortDto> portList) {
		this.portList = portList;
	}

	public boolean isPaginatorVisible() {
		return paginatorVisible;
	}

	public void setPortService(PortService portService) {
		this.portService = portService;
	}

	public Integer getFirstRowCount() {
		return firstRowCount;
	}

	public List<PortDto> getFilterPortList() {
		return filterPortList;
	}

	public void setFilterPortList(List<PortDto> filterPortList) {
		this.filterPortList = filterPortList;
	}

}