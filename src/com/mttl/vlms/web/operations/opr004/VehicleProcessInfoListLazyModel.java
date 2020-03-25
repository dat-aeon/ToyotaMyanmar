package com.mttl.vlms.web.operations.opr004;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.mttl.vlms.common.Utils;
import com.mttl.vlms.operation.opr004.dto.VehicleProcessInfoDto;
import com.mttl.vlms.operation.opr004.dto.VehicleProcessInfoSearchReqDto;
import com.mttl.vlms.operation.opr004.service.VehicleProcessInfoService;

public class VehicleProcessInfoListLazyModel extends LazyDataModel<VehicleProcessInfoDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2706333151609250259L;

	private VehicleProcessInfoSearchReqDto vehicleProcessInfoSearchReqDto;

	private VehicleProcessInfoDto vehicleProcessInfoDto;

	private boolean paginatorVisible;

	private VehicleProcessInfoListBean vehicleProcessInfoListBean;

	private VehicleProcessInfoService vehicleProcessInfoService;
	private int rowCount;

	public VehicleProcessInfoListLazyModel(int rowCount, VehicleProcessInfoSearchReqDto vehicleProcessInfoSearchReqDto, VehicleProcessInfoService vehicleProcessInfoService,
			VehicleProcessInfoDto vehicleProcessInfoDto) {
		this.vehicleProcessInfoSearchReqDto = vehicleProcessInfoSearchReqDto;
		this.vehicleProcessInfoService = vehicleProcessInfoService;
		this.rowCount = rowCount;
		this.vehicleProcessInfoDto = vehicleProcessInfoDto;
		// this.datasource = datasource;

	}

	@Override
	public Object getRowKey(VehicleProcessInfoDto vehicleProcessInfoDto) {
		return vehicleProcessInfoDto.getId();
	}

	@Override
	public int getRowCount() {
		return rowCount;
	}

	@Override
	public List<VehicleProcessInfoDto> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		List<VehicleProcessInfoDto> vehicleProcessInfoDtoList = new ArrayList<>();
		vehicleProcessInfoListBean = new VehicleProcessInfoListBean();
		/*
		 * for (UserInfoDto userInfoDto : datasource) { boolean match = true;
		 * 
		 * if (filters != null) { for (Iterator<String> it =
		 * filters.keySet().iterator(); it.hasNext();) { try { String
		 * filterProperty = it.next(); Object filterValue =
		 * filters.get(filterProperty); String fieldValue =
		 * String.valueOf(userInfoDto.getClass().getField(filterProperty).get(
		 * userInfoDto));
		 * 
		 * if (filterValue == null ||
		 * fieldValue.startsWith(filterValue.toString())) { match = true; } else
		 * { match = false; break; } } catch (Exception e) { match = false; } }
		 * }
		 * 
		 * if (match) { userDtoList.add(userInfoDto); } }
		 */

		vehicleProcessInfoSearchReqDto = new VehicleProcessInfoSearchReqDto();
		vehicleProcessInfoSearchReqDto.setLimit(pageSize);
		vehicleProcessInfoSearchReqDto.setOffSet(first);
		vehicleProcessInfoSearchReqDto.setSortField(sortField);
		vehicleProcessInfoSearchReqDto.setSortOrder(sortOrder.toString());
		vehicleProcessInfoSearchReqDto.setFilters(filters);
		/*
		 * vehicleProcessInfoSearchReqDto.setVehicleProcessInfoDto(
		 * vehicleProcessInfoSearchReqDto.getVehicleProcessInfoDto());
		 */

		if (vehicleProcessInfoSearchReqDto.getFilters().size() > 0) {
			if (vehicleProcessInfoSearchReqDto.getFilters().containsKey("globalFilter") && Utils.isNullAndEmpty(vehicleProcessInfoSearchReqDto.getFilters().get("globalFilter"))) {
				vehicleProcessInfoSearchReqDto.getFilters().remove("globalFilter");
			}
		}

		System.out.println("filter value " + filters);

		vehicleProcessInfoDtoList = vehicleProcessInfoService.searchByChassisNo(vehicleProcessInfoSearchReqDto, vehicleProcessInfoDto);

		if (vehicleProcessInfoDtoList.size() > 10) {
			paginatorVisible = true;
			vehicleProcessInfoListBean.setPaginatorVisible(paginatorVisible);
		}
		return vehicleProcessInfoDtoList;
	}

	public VehicleProcessInfoDto getVehicleProcessInfoDto() {
		return vehicleProcessInfoDto;
	}

	public void setVehicleProcessInfoDto(VehicleProcessInfoDto vehicleProcessInfoDto) {
		this.vehicleProcessInfoDto = vehicleProcessInfoDto;
	}

	public boolean isPaginatorVisible() {
		return paginatorVisible;
	}

	public void setPaginatorVisible(boolean paginatorVisible) {
		this.paginatorVisible = paginatorVisible;
	}

	public VehicleProcessInfoListBean getVehicleProcessInfoListBean() {
		return vehicleProcessInfoListBean;
	}

	public void setVehicleProcessInfoListBean(VehicleProcessInfoListBean vehicleProcessInfoListBean) {
		this.vehicleProcessInfoListBean = vehicleProcessInfoListBean;
	}

}