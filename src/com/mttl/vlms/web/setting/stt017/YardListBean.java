package com.mttl.vlms.web.setting.stt017;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.springframework.beans.factory.annotation.Autowired;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.setting.stt017.dto.ColumnDto;
import com.mttl.vlms.setting.stt017.dto.RowDto;
import com.mttl.vlms.setting.stt017.dto.YardDto;
import com.mttl.vlms.setting.stt017.dto.ZoneDto;
import com.mttl.vlms.setting.stt017.mapper.YardCustomMapper;
import com.mttl.vlms.setting.stt017.service.YardService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * YardListBean
 * 
 * 
 *
 */
@ManagedBean(name = "YardListBean")
@ViewScoped
public class YardListBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -6598961227154170581L;

	@ManagedProperty(value = "#{YardService}")
	private YardService yardService;

	@Autowired
	private YardCustomMapper yardCustomMapper;

	private String yardName;

	private List<ZoneDto> yardEditList;

	private List<YardDto> yardList;

	@Autowired
	private YardEditBean yardEditBean;

	private boolean paginatorVisible;

	private Integer firstRowCount;

	@PostConstruct
	public void init() {

		firstRowCount = (Integer) getSessionParam(CommonConstants.FIRST_ROW_COUNT);
		if (firstRowCount == null) {
			firstRowCount = 0;
		}
		removeSessionParam(CommonConstants.YARD_DTO_DELETE, CommonConstants.YARD_DTO_EDIT, CommonConstants.FIRST_ROW_COUNT);
		searchYard();
	}

	public void searchYard() {
		paginatorVisible = false;

		yardList = yardService.getYardList();

		if (yardList.size() > 10) {
			paginatorVisible = true;
		}
	}

	public void disabledYard(YardDto yardDtoEdit) {
		int count = 0;
		boolean parkedCarChk = false;
		List<ZoneDto> zoneUpdateDtoList;
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("yardListForm:yardTable");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);

		System.out.println(yardDtoEdit.getId());
		yardDtoEdit.setDisabled(!yardDtoEdit.isDisabled());

		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);

		zoneUpdateDtoList = yardService.getZoneListByYardId(yardDtoEdit.getId());

		for (ZoneDto zoneDto : zoneUpdateDtoList) {
			for (RowDto rowDto : zoneDto.getRowDtoList()) {
				for (ColumnDto colDto : rowDto.getColumnDtoList()) {
					if (colDto.getVehicleInfoDto() != null && yardDtoEdit.isDisabled() == true) {
						parkedCarChk = true;
					}
				}
			}
		}

		if (!parkedCarChk) {
			count = yardService.updateYard(yardDtoEdit, user.getUserID(), zoneUpdateDtoList);
			if (count == 0) {
				addErrorMessage(null, "MSG_CMN_006", yardDtoEdit.getName());
			} else {
				addInfoMessage(null, "MSG_CMN_003", yardDtoEdit.getName());
			}
		} else {
			addErrorMessage("Parked car existed. Can't Disable.");
		}

		searchYard();
	}

	public void showMessage() {
		String messageCode = (String) getSessionParam(CommonConstants.MESSAGE_CODE);
		String messageParameter = (String) getSessionParam(CommonConstants.MESSAGE_PARAMETER);
		removeSessionParam(CommonConstants.MESSAGE_CODE, CommonConstants.MESSAGE_PARAMETER);
		if (messageCode != null && messageParameter != null) {
			addErrorMessage(null, messageCode, messageParameter);
		}
	}

	public String editYard(YardDto yardDto) {

		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("yardListForm:yardTable");
		firstRowCount = dataTable.getFirst();

		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);
		putSessionParam(CommonConstants.YARD_DTO_EDIT, yardDto);

		return "yardEdit";
	}

	public String deleteConfirmYard(YardDto yardDto) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("yardListForm:yardTable");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);
		putSessionParam(CommonConstants.YARD_DTO_DELETE, yardDto);

		return "yardDeleteConfirm";
	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		int filterInt = getInteger(filterText);

		YardDto yard = (YardDto) value;
		return yard.getName().toLowerCase().contains(filterText) || yard.getAddress().toLowerCase().contains(filterText) || yard.getDescription().toLowerCase().contains(filterText)
				|| (yard.isDisabled() ? "enable" : "disable").toLowerCase().contains(filterText);

	}

	private int getInteger(String string) {
		try {
			return Integer.valueOf(string);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}

	public String getYardName() {
		return yardName;
	}

	public void setYardName(String yardName) {
		this.yardName = yardName;
	}

	public List<YardDto> getYardList() {
		return yardList;
	}

	public void setYardList(List<YardDto> yardList) {
		this.yardList = yardList;
	}

	public boolean isPaginatorVisible() {
		return paginatorVisible;
	}

	public void setYardService(YardService yardService) {
		this.yardService = yardService;
	}

	public Integer getFirstRowCount() {
		return firstRowCount;
	}

	public List<ZoneDto> getYardEditList() {
		return yardEditList;
	}

	public void setYardEditList(List<ZoneDto> yardEditList) {
		this.yardEditList = yardEditList;
	}

	public YardCustomMapper getYardCustomMapper() {
		return yardCustomMapper;
	}

	public void setYardCustomMapper(YardCustomMapper yardCustomMapper) {
		this.yardCustomMapper = yardCustomMapper;
	}

	public YardEditBean getYardEditBean() {
		return yardEditBean;
	}

	public void setYardEditBean(YardEditBean yardEditBean) {
		this.yardEditBean = yardEditBean;
	}

}
