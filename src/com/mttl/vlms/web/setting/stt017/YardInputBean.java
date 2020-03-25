package com.mttl.vlms.web.setting.stt017;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FlowEvent;

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
 * YardInputBean
 * 
 * 
 */
@ManagedBean(name = "YardInputBean")
@ViewScoped
public class YardInputBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -237663084453901804L;

	@ManagedProperty(value = "#{YardService}")
	private YardService yardService;

	private YardDto yardDto;

	private User loginUser;

	private List<String> zoneNameList;

	@PostConstruct
	public void init() {
		yardDto = new YardDto();
		loginUser = (User) getSessionParam(CommonConstants.LOGIN_USER);
		zoneNameList = new ArrayList<String>();
	}

	public String inputYard() {

		zoneNameList = new ArrayList<String>();
		if (yardService.checkDuplicateYardName(yardDto) > 0) {
			addErrorMessage(null, "MSG_CMN_010", yardDto.getName());
			return null;
		}
		for (ZoneDto zoneDto : yardDto.getZoneDtoList()) {

			for (String zoneNameTest : zoneNameList) {
				if (zoneDto.getName().equals(zoneNameTest)) {
					addErrorMessage("Zone names of " + yardDto.getName() + " should not be same!!!");
					return null;
				}
			}
			zoneNameList.add(zoneDto.getName());
		}

		yardService.insertYard(yardDto, loginUser.getUserID());
		addInfoMessage(null, "MSG_CMN_002", yardDto.getName());

		keepSetMessage();

		return "yardList";
	}

	/*
	 * public String checkZoneName(List<ZoneDto> zoneDtoList) { for (ZoneDto
	 * zoneDto : zoneDtoList) { zoneNameList.add(zoneDto.getName()); } for
	 * (ZoneDto zoneDto : zoneDtoList) { for (String zoneName : zoneNameList) {
	 * if (zoneDto.getName().equals(zoneName)) {
	 * addErrorMessage("Zone names of " + zoneDto.getName() +
	 * " should not be same!!!"); return null; } } } return
	 * "PF('wizard').next();";
	 * 
	 * }
	 */

	public String onFollowProcess(FlowEvent event) {

		switch (event.getNewStep()) {
			case CommonConstants.YARD_INFO_TAB_ID:

				break;

			case CommonConstants.YARD_ZONE_TAB_ID:

				if (!event.getOldStep().equals(CommonConstants.YARD_ZONE_PREVIEW_TAB_ID)) {

					List<ZoneDto> zoneDtoList = new ArrayList<>();

					for (int i = 0; i < yardDto.getZoneCount(); i++) {
						ZoneDto zoneDto = new ZoneDto();
						zoneDto.setName(String.valueOf((i + 1)));
						zoneDto.setIsExtraZone(false);

						zoneDtoList.add(zoneDto);
					}
					yardDto.setZoneDtoList(zoneDtoList);
				}

				break;

			case CommonConstants.YARD_ZONE_PREVIEW_TAB_ID:

				for (ZoneDto zoneDto : yardDto.getZoneDtoList()) {

					zoneDto.setRowDtoList(new ArrayList<>());

					for (int i = 0; i < zoneDto.getRowCount(); i++) {
						RowDto rowDto = new RowDto();

						rowDto.setColumnCount(zoneDto.getColumnCount());
						rowDto.setColumnDtoList(new ArrayList<>());

						for (int j = 0; j < zoneDto.getColumnCount(); j++) {
							ColumnDto columnDto = new ColumnDto();
							String columnName = zoneDto.getName() + Utils.asciiCodeToChar(97 + j) + (i + 1);
							columnDto.setName(columnName);
							rowDto.getColumnDtoList().add(columnDto);
						}

						zoneDto.getRowDtoList().add(rowDto);
					}
				}

				break;

		}

		return event.getNewStep();

	}

	public YardDto getYardDto() {
		return yardDto;
	}

	public void setYardDto(YardDto yardDto) {
		this.yardDto = yardDto;
	}

	public void setYardService(YardService yardService) {
		this.yardService = yardService;
	}

	public List<String> getZoneNameList() {
		return zoneNameList;
	}

	public void setZoneNameList(List<String> zoneNameList) {
		this.zoneNameList = zoneNameList;
	}

}
