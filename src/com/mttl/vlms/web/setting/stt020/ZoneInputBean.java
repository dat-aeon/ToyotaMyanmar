package com.mttl.vlms.web.setting.stt020;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FlowEvent;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.setting.stt017.dto.YardDto;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * YardInputBean
 * 
 * 
 */
@ManagedBean(name = "ZoneInputBean")
@ViewScoped
public class ZoneInputBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -237663084453901804L;

	/*
	 * @ManagedProperty(value = "#{YardService}") private YardService
	 * yardService;
	 */

	private YardDto yardDto;

	private List<String> zones;

	private User loginUser;

	private Integer zone;

	private boolean skip;

	HashMap<String, String> zoneDetailList = new HashMap<String, String>();

	/* private List<StateDivisionInfoSelectDto> yardList; */

	@PostConstruct
	public void init() {
		/* yardList = yardService.getYardList(); */
		yardDto = new YardDto();
		loginUser = (User) getSessionParam(CommonConstants.LOGIN_USER);
	}

	/*
	 * public String inputYard() { if
	 * (yardService.checkDuplicateYardCode(yardDto) > 0) { addErrorMessage(null,
	 * "MSG_CMN_010", yardDto.getBarcodeId()); return null; } if
	 * (yardService.checkDuplicateYardName(yardDto) > 0) { addErrorMessage(null,
	 * "MSG_CMN_010", yardDto.getYardName()); return null; }
	 * yardService.insertYard(yardDto, loginUser.getUserID());
	 * addInfoMessage(null, "MSG_CMN_002", yardDto.getYardName());
	 * keepSetMessage(); return "yardList"; }
	 */

	public String onFollowProcess(FlowEvent event) {

		System.out.println(event.getNewStep());

		String currentStepId = event.getNewStep();

		if ("zoneDetail".equals(currentStepId)) {
			zones = new ArrayList<>();
			for (int i = 1; i <= zone; i++) {
				zones.add("Zone " + i);
			}
		}

		System.out.println(currentStepId);

		return event.getNewStep();

	}

	public void tempSaveZoneList() {
		System.out.println("in");
	}

	public YardDto getYardDto() {
		return yardDto;
	}

	public void setYardDto(YardDto yardDto) {
		this.yardDto = yardDto;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public Integer getZone() {
		return zone;
	}

	public void setZone(Integer zone) {
		this.zone = zone;
	}

	public List<String> getZones() {
		return zones;
	}

	public void setZones(List<String> zones) {
		this.zones = zones;
	}

	/*
	 * public void setYardService(YardService yardService) { this.yardService =
	 * yardService; }
	 */

	/*
	 * public List<StateDivisionInfoSelectDto> getYardList() { return yardList;
	 * }
	 * 
	 * public void setYardList(List<StateDivisionInfoSelectDto> yardList) {
	 * this.yardList = yardList; }
	 */
}
