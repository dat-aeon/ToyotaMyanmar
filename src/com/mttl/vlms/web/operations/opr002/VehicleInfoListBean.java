package com.mttl.vlms.web.operations.opr002;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.model.LazyDataModel;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.operation.opr002.dto.VehicleInfoDto;
import com.mttl.vlms.operation.opr002.dto.VehicleInfoSearchReqDto;
import com.mttl.vlms.operation.opr002.service.VehicleInfoService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * VehicleInfoListBean
 * 
 * 
 *
 */
@ManagedBean(name = "VehicleInfoListBean")
@ViewScoped
public class VehicleInfoListBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = -6598961226154170581L;
	@ManagedProperty(value = "#{VehicleInfoService}")
	private VehicleInfoService vehicleInfoService;
	private VehicleInfoDto vehicleInfoSearchDto;
	private List<VehicleInfoDto> vehicleInfoList;
	private List<VehicleInfoDto> vehicleInfoTempList;
	private List<VehicleInfoDto> vehicleInfoSearchList;
	private List<String> completeChassisNo;
	private List<VehicleInfoDto> portList;
	private List<VehicleInfoDto> dealerList;
	private List<String> statusList;
	private VehicleInfoDto vehicleInfoEdit;
	private boolean paginatorVisible;
	private LazyDataModel<VehicleInfoDto> vehicleInfoListLazyDataModel;
	private VehicleInfoSearchReqDto vehicleInfoSearchReqDto;
	private Integer firstRowCount;
	private boolean fullAccessFlag;
	private boolean checkAutoUpdate;

	@PostConstruct
	public void init() {
		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		if (user.getRoleID() == CommonConstants.ADMIN_ROLE || user.getRoleID() == CommonConstants.MANAGER_ROLE) {
			fullAccessFlag = true;
		} else {
			fullAccessFlag = false;
		}
		if (null != getSessionParam(CommonConstants.TODAY_ARRIVED_VEHICLE_CHECK)) {
			vehicleInfoSearchReqDto = new VehicleInfoSearchReqDto();
			vehicleInfoSearchDto = new VehicleInfoDto();
			vehicleInfoSearchDto.setId(1);
			putSessionParam(CommonConstants.VEHICLE_INFO_SEARCH, this.vehicleInfoSearchDto);
			searchVehicleInfo();
			removeSessionParam(CommonConstants.TODAY_ARRIVED_VEHICLE_CHECK);
		} else if (null != getSessionParam(CommonConstants.AUDIO_INSTALLED_VEHICLE_CHECK)) {
			vehicleInfoSearchReqDto = new VehicleInfoSearchReqDto();
			vehicleInfoSearchDto = new VehicleInfoDto();
			vehicleInfoSearchDto.setId(2);
			putSessionParam(CommonConstants.VEHICLE_INFO_SEARCH, this.vehicleInfoSearchDto);
			searchVehicleInfo();
			removeSessionParam(CommonConstants.AUDIO_INSTALLED_VEHICLE_CHECK);
		} else if (null != getSessionParam(CommonConstants.PDI_REQUIRED_VEHICLE_CHECK)) {
			vehicleInfoSearchReqDto = new VehicleInfoSearchReqDto();
			vehicleInfoSearchDto = new VehicleInfoDto();
			vehicleInfoSearchDto.setId(3);
			putSessionParam(CommonConstants.VEHICLE_INFO_SEARCH, this.vehicleInfoSearchDto);
			searchVehicleInfo();
			removeSessionParam(CommonConstants.PDI_REQUIRED_VEHICLE_CHECK);
		} else {
			vehicleInfoSearchReqDto = new VehicleInfoSearchReqDto();
			vehicleInfoEdit = new VehicleInfoDto();
			vehicleInfoSearchDto = (VehicleInfoDto) getSessionParam(CommonConstants.VEHICLE_INFO_SEARCH);
			if (vehicleInfoSearchDto == null) {
				vehicleInfoSearchDto = new VehicleInfoDto();
			} else {
				searchVehicleInfo();
			}
		}
		vehicleInfoSearchDto = new VehicleInfoDto();
		firstRowCount = (Integer) getSessionParam(CommonConstants.FIRST_ROW_COUNT);
		if (firstRowCount == null) {
			firstRowCount = 0;
		}
		portList = vehicleInfoService.getPortList();
		dealerList = vehicleInfoService.getDealerList();
		statusList = vehicleInfoService.getStatusList();
		checkAutoUpdate = false;
		removeSessionParam(CommonConstants.FIRST_ROW_COUNT, CommonConstants.CHASSIS_NO, CommonConstants.MODEL, CommonConstants.COLOR, CommonConstants.PORT_OF_DISCHARGE,
				CommonConstants.VEHICLE_INFO_DTO_DELETE);
	}

	public void showMessage() {
		String messageCode = (String) getSessionParam(CommonConstants.MESSAGE_CODE);
		String messageParameter = (String) getSessionParam(CommonConstants.MESSAGE_PARAMETER);
		removeSessionParam(CommonConstants.MESSAGE_CODE, CommonConstants.MESSAGE_PARAMETER);
		if (messageCode != null && messageParameter != null) {
			addErrorMessage(null, messageCode, messageParameter);
		}
	}

	public String clear() {
		PrimeFaces.current().resetInputs("frmVehicleInfo:aplSearch");
		return "vehicleInfoList";
	}

	public void searchVehicleInfo() {
		paginatorVisible = false;
		if (vehicleInfoSearchDto.getDealerName() == "") {
			vehicleInfoSearchDto.setDealerName(null);
		}
		if (vehicleInfoSearchDto.getChassisNo() == "") {
			vehicleInfoSearchDto.setChassisNo(null);
		}
		if (vehicleInfoSearchDto.getAgentName() == "") {
			vehicleInfoSearchDto.setAgentName(null);
		}
		if (vehicleInfoSearchDto.getStatus() == "") {
			vehicleInfoSearchDto.setStatus(null);
		}

		putSessionParam(CommonConstants.VEHICLE_INFO_SEARCH, this.vehicleInfoSearchDto);

		vehicleInfoList = vehicleInfoService.searchAfterUpdate(vehicleInfoSearchDto);
		if (vehicleInfoList.size() > 10) {
			paginatorVisible = true;
		}
		vehicleInfoListLazyDataModel = new VehicleInfoListLazyModel(vehicleInfoList.size(), vehicleInfoSearchReqDto, vehicleInfoService, vehicleInfoSearchDto);

	}

	public String changeUpdate() {
		if (vehicleInfoTempList == null) {
		} else {
			User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
			VehicleInfoDto vehicleInfoDto;
			for (int i = 0; i < vehicleInfoTempList.size(); i++) {
				vehicleInfoDto = vehicleInfoTempList.get(i);
				if (vehicleInfoService.checkDuplicateChassisNo(vehicleInfoDto) > 0) {
					addErrorMessage(null, "MSG_CMN_010", vehicleInfoDto.getChassisNo());
					keepSetMessage();
					searchVehicleInfo();
					return null;
				}
				if (vehicleInfoService.checkDuplicateLicenseNo(vehicleInfoDto) > 0) {
					addErrorMessage(null, "MSG_CMN_010", vehicleInfoDto.getLicenseNo());
					keepSetMessage();
					searchVehicleInfo();
					return null;
				}
				vehicleInfoService.changeUpdate(vehicleInfoDto, user.getUserID());
			}
			addInfoMessage(null, "MSG_CMN_003", "Table");
			keepSetMessage();
			vehicleInfoTempList = new ArrayList<VehicleInfoDto>();
		}
		return "vehicleInfoList";

	}

	public void addTemp(VehicleInfoDto vehicleInfoDtoTemp) {
		if (vehicleInfoService.checkDuplicateChassisNo(vehicleInfoDtoTemp) > 0) {
			addErrorMessage(null, "MSG_CMN_010", vehicleInfoDtoTemp.getChassisNo());
			keepSetMessage();
		}
		if (vehicleInfoService.checkDuplicateLicenseNo(vehicleInfoDtoTemp) > 0) {
			addErrorMessage(null, "MSG_CMN_010", vehicleInfoDtoTemp.getLicenseNo());
			keepSetMessage();
		}
		if (vehicleInfoTempList != null) {
			Boolean getFlg = false;

			for (int i = 0; i < vehicleInfoTempList.size(); i++) {
				if (vehicleInfoTempList.get(i).getId() == vehicleInfoDtoTemp.getId()) {
					vehicleInfoTempList.set(i, vehicleInfoDtoTemp);
					getFlg = true;
				}
			}
			if (getFlg == false) {
				vehicleInfoTempList.add(vehicleInfoDtoTemp);
			}
		} else {
			vehicleInfoTempList = new ArrayList<VehicleInfoDto>();
			vehicleInfoTempList.add(vehicleInfoDtoTemp);
		}
	}

	public String deleteConfirmVehicleInfo(VehicleInfoDto vehicleInfoDto) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmVehicleInfo:tblVehicleInfo");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);
		putSessionParam(CommonConstants.VEHICLE_INFO_DTO_DELETE, vehicleInfoDto);
		putSessionParam(CommonConstants.CHASSIS_NO, this.vehicleInfoSearchDto.getChassisNo());
		return "vehicleInfoDeleteConfirm";
	}

	public void disabledVehicleInfo(VehicleInfoDto vehicleInfoDtoEdit) {

		vehicleInfoDtoEdit.setDisabled(!vehicleInfoDtoEdit.isDisabled());

		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmVehicleInfo:tblVehicleInfo");

		firstRowCount = dataTable.getFirst();

		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);

		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);

		int count = vehicleInfoService.updateVehicleInfo(vehicleInfoDtoEdit, user.getUserID());

		checkAutoUpdate = true;

		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_006", vehicleInfoDtoEdit.getChassisNo());
		} else {
			addInfoMessage(null, "MSG_CMN_003", vehicleInfoDtoEdit.getChassisNo());
		}
		searchVehicleInfo();
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public List<String> completeChassisNo(String chassisNo) {
		String queryLowerCase = chassisNo.toLowerCase();
		List<String> allChassisNo = vehicleInfoService.getChassisNoList(chassisNo);
		return allChassisNo.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
	}

	public void changeAutoUpdate() {
		checkAutoUpdate = true;
	}

	public List<String> getCompleteChassisNo() {
		return completeChassisNo;
	}

	public void setCompleteChassisNo(List<String> completeChassisNo) {
		this.completeChassisNo = completeChassisNo;
	}

	public VehicleInfoDto getVehicleInfoSearchDto() {
		return vehicleInfoSearchDto;
	}

	public List<VehicleInfoDto> getVehicleInfoList() {
		return vehicleInfoList;
	}

	public void setVehicleInfoList(List<VehicleInfoDto> vehicleInfoList) {
		this.vehicleInfoList = vehicleInfoList;
	}

	public boolean isPaginatorVisible() {
		return paginatorVisible;
	}

	public void setVehicleInfoService(VehicleInfoService vehicleInfoService) {
		this.vehicleInfoService = vehicleInfoService;
	}

	public Integer getFirstRowCount() {
		return firstRowCount;
	}

	public List<VehicleInfoDto> getVehicleInfoSearchList() {
		return vehicleInfoSearchList;
	}

	public void setVehicleInfoSearchList(List<VehicleInfoDto> vehicleInfoSearchList) {
		this.vehicleInfoSearchList = vehicleInfoSearchList;
	}

	public void setVehicleInfoSearchDto(VehicleInfoDto vehicleInfoSearchDto) {
		this.vehicleInfoSearchDto = vehicleInfoSearchDto;
	}

	public List<VehicleInfoDto> getPortList() {
		return portList;
	}

	public void setPortList(List<VehicleInfoDto> portList) {
		this.portList = portList;
	}

	public List<VehicleInfoDto> getDealerList() {
		return dealerList;
	}

	public void setDealerList(List<VehicleInfoDto> dealerList) {
		this.dealerList = dealerList;
	}

	public List<VehicleInfoDto> getVehicleInfoTempList() {
		return vehicleInfoTempList;
	}

	public void setVehicleInfoTempList(List<VehicleInfoDto> vehicleInfoTempList) {
		this.vehicleInfoTempList = vehicleInfoTempList;
	}

	public VehicleInfoDto getVehicleInfoEdit() {
		return vehicleInfoEdit;
	}

	public void setVehicleInfoEdit(VehicleInfoDto vehicleInfoEdit) {
		this.vehicleInfoEdit = vehicleInfoEdit;
	}

	public void setPaginatorVisible(boolean paginatorVisible) {
		this.paginatorVisible = paginatorVisible;
	}

	public LazyDataModel<VehicleInfoDto> getVehicleInfoListLazyDataModel() {
		return vehicleInfoListLazyDataModel;
	}

	public void setVehicleInfoListLazyDataModel(LazyDataModel<VehicleInfoDto> vehicleInfoListLazyDataModel) {
		this.vehicleInfoListLazyDataModel = vehicleInfoListLazyDataModel;
	}

	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	public boolean isFullAccessFlag() {
		return fullAccessFlag;
	}

	public void setFullAccessFlag(boolean fullAccessFlag) {
		this.fullAccessFlag = fullAccessFlag;
	}

	public boolean isCheckAutoUpdate() {
		return checkAutoUpdate;
	}

	public void setCheckAutoUpdate(boolean checkAutoUpdate) {
		this.checkAutoUpdate = checkAutoUpdate;
	}

}