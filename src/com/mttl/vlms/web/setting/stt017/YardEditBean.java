package com.mttl.vlms.web.setting.stt017;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.common.Utils;
import com.mttl.vlms.setting.stt017.dto.ColumnDto;
import com.mttl.vlms.setting.stt017.dto.RowDto;
import com.mttl.vlms.setting.stt017.dto.YardDto;
import com.mttl.vlms.setting.stt017.dto.ZoneDto;
import com.mttl.vlms.setting.stt017.service.YardService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * YardEditBean
 * 
 * 
 */
@ManagedBean(name = "YardEditBean")
@ViewScoped
public class YardEditBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 6273397299924214278L;

	@ManagedProperty(value = "#{YardService}")
	private YardService yardService;

	private YardDto yardDtoEdit;

	private YardDto backUpYardDtoEdit;

	private ZoneDto zoneDto;

	private List<String> zoneNameList;

	private boolean duplicateFlag;

	@PostConstruct
	public void init() {

		yardDtoEdit = (YardDto) getSessionParam(CommonConstants.YARD_DTO_EDIT);
		yardDtoEdit.setZoneDtoList(yardService.getZoneListByYardId(yardDtoEdit.getId()));

		backUpYardDtoEdit = SerializationUtils.clone(yardDtoEdit);
		zoneNameList = new ArrayList<String>();
		zoneDto = new ZoneDto();
		for (ZoneDto zoneDto : yardDtoEdit.getZoneDtoList()) {
			zoneNameList.add(zoneDto.getName());
		}
		duplicateFlag = false;

	}

	public void redirect() {
		YardDto yardDto = yardService.getYardById(yardDtoEdit.getId());
		if (yardDto == null) {
			putSessionParam(CommonConstants.MESSAGE_CODE, "MSG_CMN_011");
			putSessionParam(CommonConstants.MESSAGE_PARAMETER, yardDtoEdit.getName());
			redirect("yardList.xhtml?faces-redirect=true");
		}
	}

	public MenuModel zoneContextMenu(ZoneDto zoneDto) {

		System.out.println(zoneDto.getName());

		String disabledLable = zoneDto.getDisabled() ? getLabel("ENABLE_LABEL") : getLabel("DISABLE_LABEL");
		String disabledIcon = "fa fa-fw " + (zoneDto.getDisabled() ? "fa-toggle-on fa-flip-horizontal red" : "fa-toggle-on green") + " fa-lg";

		String extraLable = getLabel("MARK_AS_LABEL") + " " + (zoneDto.getIsExtraZone() ? getLabel("NORMAL_LABEL") : getLabel("EXTRA_LABEL"));
		String extraIcon = "ui-icon-" + (zoneDto.getIsExtraZone() ? "star-border" : "star");

		MenuModel model = new DefaultMenuModel();

		// Zone submenu
		DefaultSubMenu zoneSubMenu = new DefaultSubMenu(getLabel("YARD_ZONE_LABEL") + " " + zoneDto.getName());

		// Delete Zone Menu
		DefaultMenuItem item = new DefaultMenuItem(getLabel("DELETE_LABEL"));
		item.setIcon("ui-icon-indeterminate-check-box");
		item.setCommand("#{zoneDto.setDeleteFlg(true)}");
		item.setUpdate("editYardForm:previewPanel");
		item.setProcess("editYardForm:previewPanel");
		zoneSubMenu.addElement(item);

		// Disabled/Endabled Zone Menu
		item = new DefaultMenuItem(disabledLable);
		item.setIcon(disabledIcon);
		item.setCommand("#{zoneDto.setDisabled(not zoneDto.getDisabled())}");
		item.setUpdate("editYardForm:previewPanel");
		item.setProcess("editYardForm:previewPanel");
		zoneSubMenu.addElement(item);

		// Extra Zone Menu
		item = new DefaultMenuItem(extraLable);
		item.setIcon(extraIcon);
		item.setCommand("#{zoneDto.setIsExtraZone(not zoneDto.getIsExtraZone())}");
		item.setUpdate("editYardForm:previewPanel");
		item.setProcess("editYardForm:previewPanel");
		zoneSubMenu.addElement(item);

		model.addElement(zoneSubMenu);

		if (!zoneDto.getDisabled()) {

			// Column submenu
			DefaultSubMenu columnSubMenu = new DefaultSubMenu(getLabel("COLUMN_LABEL"));

			// Add column menu
			item = new DefaultMenuItem("Add New Column");
			item.setIcon("ui-icon-add-box");
			item.setCommand("#{YardEditBean.addColumn(zoneDto)}");
			item.setUpdate("editYardForm:previewPanel");
			item.setProcess("editYardForm:previewPanel");
			columnSubMenu.addElement(item);

			// Remove column menu
			int i = 0;
			for (ColumnDto columnDto : zoneDto.getRowDtoList().get(0).getColumnDtoList()) {
				if (BooleanUtils.isFalse(columnDto.getDeleteFlg())) {
					item = new DefaultMenuItem("Delete Column " + Utils.asciiCodeToChar(65 + i));
					item.setIcon("ui-icon-indeterminate-check-box");
					item.setCommand("#{YardEditBean.deleteColumn(zoneDto," + i + ")}");
					item.setUpdate("editYardForm:previewPanel");
					item.setProcess("editYardForm:previewPanel");
					columnSubMenu.addElement(item);
				}
				i++;
			}

			model.addElement(columnSubMenu);
		}

		model.generateUniqueIds();

		return model;
	}

	public MenuModel columnContextMenu(ColumnDto columnDto) {

		if (columnDto.getDisabled() == null) {
			columnDto.setDisabled(false);
		}
		if (columnDto.getStatus() == null) {
			columnDto.setStatus(0);
		}
		String disabledLable = columnDto.getDisabled() ? getLabel("ENABLE_LABEL") : getLabel("DISABLE_LABEL");
		String disabledIcon = "fa fa-fw " + (columnDto.getDisabled() ? "fa-toggle-on fa-flip-horizontal red" : "fa-toggle-on green") + " fa-lg";

		String damageLable = getLabel("MARK_AS_LABEL") + " " + (3 == columnDto.getStatus() ? getLabel("FIXED_LABEL") : getLabel("DAMAGED_LABEL"));
		String damageIcon = "ui-icon-" + (3 == columnDto.getStatus() ? "thumb-down" : "thumb-up");
		String damageCommand = "#{columnDto.setStatus(" + (3 == columnDto.getStatus() ? 0 : 3) + ")}";

		MenuModel model = new DefaultMenuModel();

		// Delete Zone Menu
		DefaultMenuItem item = new DefaultMenuItem(damageLable);
		item.setIcon(damageIcon);
		item.setCommand(damageCommand);
		item.setUpdate("editYardForm:previewPanel");
		item.setProcess("editYardForm:previewPanel");
		model.addElement(item);

		// Disabled/Endabled Zone Menu
		item = new DefaultMenuItem(disabledLable);
		item.setIcon(disabledIcon);
		item.setCommand("#{columnDto.setDisabled(not columnDto.getDisabled())}");
		item.setUpdate("editYardForm:previewPanel");
		item.setProcess("editYardForm:previewPanel");
		model.addElement(item);
		model.generateUniqueIds();

		return model;
	}

	public void addZone() {
		duplicateFlag = false;
		zoneDto.setRowDtoList(new ArrayList<>());
		zoneDto.setDeleteFlg(false);
		zoneDto.setDisabled(false);
		for (String zoneName : zoneNameList) {
			if (zoneDto.getName().equals(zoneName)) {
				addErrorMessage(null, "MSG_CMN_010", zoneDto.getName());
				duplicateFlag = true;
			}
		}
		if (duplicateFlag == false) {
			for (int i = 0; i < zoneDto.getRowCount(); i++) {
				RowDto rowDto = new RowDto();

				rowDto.setColumnCount(zoneDto.getColumnCount());
				rowDto.setColumnDtoList(new ArrayList<>());

				for (int j = 0; j < zoneDto.getColumnCount(); j++) {
					ColumnDto columnDto = new ColumnDto();
					String columnName = zoneDto.getName() + Utils.asciiCodeToChar(97 + j) + (i + 1);
					columnDto.setName(columnName);
					columnDto.setColumn_name(64 + zoneDto.getColumnCount());
					rowDto.getColumnDtoList().add(columnDto);
				}

				zoneDto.getRowDtoList().add(rowDto);

			}
			zoneNameList.add(zoneDto.getName());
			yardDtoEdit.getZoneDtoList().add(zoneDto);
			yardDtoEdit.setZoneCount(yardDtoEdit.getZoneCount() + 1);

			PrimeFaces.current().executeScript("PF('wvDlgNewZone').hide();");
			PrimeFaces.current().ajax().update("editYardForm");

			zoneDto = new ZoneDto();

		}
	}

	public void addColumn(ZoneDto zoneDto) {

		zoneDto.setColumnCount(zoneDto.getColumnCount() + 1);
		String tempName = zoneDto.getName() + Utils.asciiCodeToChar(96 + zoneDto.getColumnCount());

		int i = 1;
		int disableColCount = 0;
		for (RowDto rowDto : zoneDto.getRowDtoList()) {
			for (ColumnDto colDto : rowDto.getColumnDtoList()) {
				if (colDto.getDeleteFlg() == null || colDto.getDeleteFlg() == false) {
					disableColCount++;
				}
			}
			if (disableColCount == 4) {
				addErrorMessage("Column count must be less than 5");
			} else {
				ColumnDto columnDto = new ColumnDto();
				columnDto.setColumn_name(64 + zoneDto.getColumnCount());
				columnDto.setName(tempName + i);
				columnDto.setDeleteFlg(false);
				columnDto.setDisabled(false);

				rowDto.getColumnDtoList().add(columnDto);
				rowDto.setColumnCount(rowDto.getColumnCount() + 1);
				i++;
			}

		}

	}

	public void deleteColumn(ZoneDto zoneDto, int index) {

		boolean errChk = false;

		for (RowDto rowDto : zoneDto.getRowDtoList()) {

			if (rowDto.getColumnDtoList().get(index).getVehicleInfoDto() != null) {
				errChk = true;
			}
		}
		if (!errChk) {
			for (RowDto rowDto : zoneDto.getRowDtoList()) {
				rowDto.getColumnDtoList().get(index).setDeleteFlg(true);
			}
		} else {
			addErrorMessage("Can't delete because parked car exist.");

		}
		PrimeFaces.current().ajax().update("editYardForm");
	}

	public String editYard() {

		if (yardService.checkDuplicateYardName(yardDtoEdit) > 0) {
			addErrorMessage(null, "MSG_CMN_010", yardDtoEdit.getName());
			return null;
		}

		YardDto yardDto = yardService.getYardById(yardDtoEdit.getId());
		if (yardDto == null) {
			addErrorMessage(null, "MSG_CMN_011", yardDtoEdit.getName());
			keepSetMessage();
			return "yardList";
		}

		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);

		int count = 0;

		count = yardService.updateYard(yardDtoEdit, user.getUserID(), yardDtoEdit.getZoneDtoList());

		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_006", yardDto.getName());
		} else {
			addInfoMessage(null, "MSG_CMN_003", yardDto.getName());
		}

		keepSetMessage();

		return "yardList";
	}

	public YardDto getYardDtoEdit() {
		return yardDtoEdit;
	}

	public void setYardService(YardService yardService) {
		this.yardService = yardService;
	}

	public YardDto getBackUpYardDtoEdit() {
		return backUpYardDtoEdit;
	}

	public void setBackUpYardDtoEdit(YardDto backUpYardDtoEdit) {
		this.backUpYardDtoEdit = backUpYardDtoEdit;
	}

	public ZoneDto getZoneDto() {
		return zoneDto;
	}

	public void setZoneDto(ZoneDto zoneDto) {
		this.zoneDto = zoneDto;
	}

	public boolean isDuplicateFlag() {
		return duplicateFlag;
	}

	public void setDuplicateFlag(boolean duplicateFlag) {
		this.duplicateFlag = duplicateFlag;
	}

}
