package com.mttl.vlms.web.operations.opr002;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.mttl.vlms.common.Utils;
import com.mttl.vlms.operation.opr002.dto.VehicleInfoDto;
import com.mttl.vlms.operation.opr002.dto.VehicleInfoSearchReqDto;
import com.mttl.vlms.operation.opr002.service.VehicleInfoService;

public class VehicleInfoListLazyModel extends LazyDataModel<VehicleInfoDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2706333151609250259L;

	private VehicleInfoSearchReqDto vehicleInfoSearchReqDto;

	private VehicleInfoDto vehicleInfoDto;

	private boolean paginatorVisible;

	private VehicleInfoListBean vehicleInfoListBean;

	private VehicleInfoService vehicleInfoService;
	private int rowCount;

	public VehicleInfoListLazyModel(int rowCount, VehicleInfoSearchReqDto vehicleInfoSearchReqDto, VehicleInfoService vehicleInfoService, VehicleInfoDto vehicleInfoDto) {
		this.vehicleInfoSearchReqDto = vehicleInfoSearchReqDto;
		this.vehicleInfoService = vehicleInfoService;
		this.rowCount = rowCount;
		this.vehicleInfoDto = vehicleInfoDto;

	}

	@Override
	public Object getRowKey(VehicleInfoDto vehicleInfoDto) {
		return vehicleInfoDto.getId();
	}

	@Override
	public int getRowCount() {
		return rowCount;
	}

	@Override
	public List<VehicleInfoDto> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		List<VehicleInfoDto> vehicleInfoDtoList = new ArrayList<>();

		vehicleInfoSearchReqDto = new VehicleInfoSearchReqDto();
		vehicleInfoSearchReqDto.setLimit(pageSize);
		vehicleInfoSearchReqDto.setOffSet(first);
		vehicleInfoSearchReqDto.setSortField(sortField);
		vehicleInfoSearchReqDto.setSortOrder(sortOrder.toString());
		vehicleInfoSearchReqDto.setFilters(filters);

		if (vehicleInfoSearchReqDto.getFilters().size() > 0) {
			if (vehicleInfoSearchReqDto.getFilters().containsKey("globalFilter") && Utils.isNullAndEmpty(vehicleInfoSearchReqDto.getFilters().get("globalFilter"))) {
				vehicleInfoSearchReqDto.getFilters().remove("globalFilter");
			}
		}

		vehicleInfoDtoList = vehicleInfoService.searchVehicleInfoList(vehicleInfoSearchReqDto, vehicleInfoDto);

		return vehicleInfoDtoList;
	}

	public VehicleInfoDto getVehicleInfoDto() {
		return vehicleInfoDto;
	}

	public void setVehicleInfoDto(VehicleInfoDto vehicleInfoDto) {
		this.vehicleInfoDto = vehicleInfoDto;
	}

	public boolean isPaginatorVisible() {
		return paginatorVisible;
	}

	public void setPaginatorVisible(boolean paginatorVisible) {
		this.paginatorVisible = paginatorVisible;
	}

	public VehicleInfoListBean getVehicleInfoListBean() {
		return vehicleInfoListBean;
	}

	public void setVehicleInfoListBean(VehicleInfoListBean vehicleInfoListBean) {
		this.vehicleInfoListBean = vehicleInfoListBean;
	}
}