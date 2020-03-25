package com.mttl.vlms.web.setting.stt010;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.mttl.vlms.common.Utils;
import com.mttl.vlms.setting.stt010.dto.TrailerDto;
import com.mttl.vlms.setting.stt010.dto.TrailerSearchReqDto;
import com.mttl.vlms.setting.stt010.service.TrailerService;

public class TrailerListLazyModel extends LazyDataModel<TrailerDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5903881949316130301L;

	private TrailerSearchReqDto trailerSearchReqDto;

	private TrailerService trailerService;
	private int rowCount;

	public TrailerListLazyModel(int rowCount, TrailerSearchReqDto trailerSearchReqDto, TrailerService trailerService) {
		this.trailerSearchReqDto = trailerSearchReqDto;
		this.trailerService = trailerService;
		this.rowCount = rowCount;

	}

	@Override
	public Object getRowKey(TrailerDto trailerDto) {
		return trailerDto.getId();
	}

	@Override
	public int getRowCount() {
		return rowCount;
	}

	@Override
	public List<TrailerDto> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		List<TrailerDto> trailerDtoList;

		trailerSearchReqDto = new TrailerSearchReqDto();
		trailerSearchReqDto.setLimit(pageSize);
		trailerSearchReqDto.setOffSet(first);
		trailerSearchReqDto.setSortField(sortField);
		trailerSearchReqDto.setSortOrder(sortOrder.toString());
		trailerSearchReqDto.setFilters(filters);

		if (trailerSearchReqDto.getFilters().size() > 0) {
			if (trailerSearchReqDto.getFilters().containsKey("globalFilter") && Utils.isNullAndEmpty(trailerSearchReqDto.getFilters().get("globalFilter"))) {
				trailerSearchReqDto.getFilters().remove("globalFilter");
			}
		}

		trailerDtoList = trailerService.getTrailerList(trailerSearchReqDto);
		for (TrailerDto dto : trailerDtoList) {
			if (null == dto.getPhotoName()) {
				dto.setCheckPhoto(true);
			} else if (dto.getPhotoName().equals("")) {
				dto.setCheckPhoto(true);
			} else
				dto.setCheckPhoto(false);
		}
		return trailerDtoList;

	}

}