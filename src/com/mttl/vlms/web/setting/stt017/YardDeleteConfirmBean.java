package com.mttl.vlms.web.setting.stt017;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.setting.stt017.dto.YardDto;
import com.mttl.vlms.setting.stt017.service.YardService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * YardDeleteConfirmBean
 * 
 * 
 */
@ManagedBean(name = "YardDeleteConfirmBean")
@ViewScoped
public class YardDeleteConfirmBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 7173852451546888968L;

	@ManagedProperty(value = "#{YardService}")
	private YardService yardService;

	private YardDto yardDtoDelete;

	@PostConstruct
	public void init() {
		yardDtoDelete = (YardDto) getSessionParam(CommonConstants.YARD_DTO_DELETE);
		yardDtoDelete.setZoneDtoList(yardService.getZoneListByYardId(yardDtoDelete.getId()));

	}

	public void redirect() {
		YardDto yard = yardService.getYardById(yardDtoDelete.getId());
		if (yard == null) {
			putSessionParam(CommonConstants.MESSAGE_CODE, "MSG_CMN_011");
			putSessionParam(CommonConstants.MESSAGE_PARAMETER, yardDtoDelete.getName());
			redirect("yardList.xhtml?faces-redirect=true");
		}
	}

	public String deleteConfirmYard() {
		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		int count = yardService.deleteYard(yardDtoDelete, user.getUserID());
		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_007", yardDtoDelete.getName());
		} else {
			addInfoMessage(null, "MSG_CMN_004", yardDtoDelete.getName());
		}
		keepSetMessage();
		return "yardList";
	}

	public YardDto getYardDtoDelete() {
		return yardDtoDelete;
	}

	public void setYardDtoDelete(YardDto yardDtoDelete) {
		this.yardDtoDelete = yardDtoDelete;
	}

	public void setYardService(YardService yardService) {
		this.yardService = yardService;
	}
}
