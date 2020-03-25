package com.mttl.vlms.web.setting.stt013;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.mttl.vlms.common.Utils;
import com.mttl.vlms.setting.stt013.dto.UserInfoDto;
import com.mttl.vlms.setting.stt013.dto.UserInfoSearchReqDto;
import com.mttl.vlms.setting.stt013.service.UserInfoService;

public class UserInfoListLazyModel extends LazyDataModel<UserInfoDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2706333151609250259L;

	private UserInfoSearchReqDto userInfoSearchReqDto;

	private UserInfoService userInfoService;
	private int rowCount;

	public UserInfoListLazyModel(int rowCount, UserInfoSearchReqDto userInfoSearchReqDto, UserInfoService userInfoService) {
		this.userInfoSearchReqDto = userInfoSearchReqDto;
		this.userInfoService = userInfoService;
		this.rowCount = rowCount;

	}

	@Override
	public Object getRowKey(UserInfoDto userInfoDto) {
		return userInfoDto.getId();
	}

	@Override
	public int getRowCount() {
		return rowCount;
	}

	@Override
	public List<UserInfoDto> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		List<UserInfoDto> userDtoList = new ArrayList<>();

		userInfoSearchReqDto = new UserInfoSearchReqDto();
		userInfoSearchReqDto.setLimit(pageSize);
		userInfoSearchReqDto.setOffSet(first);
		userInfoSearchReqDto.setSortField(sortField);
		userInfoSearchReqDto.setSortOrder(sortOrder.toString());
		userInfoSearchReqDto.setFilters(filters);

		if (userInfoSearchReqDto.getFilters().size() > 0) {
			if (userInfoSearchReqDto.getFilters().containsKey("globalFilter") && Utils.isNullAndEmpty(userInfoSearchReqDto.getFilters().get("globalFilter"))) {
				userInfoSearchReqDto.getFilters().remove("globalFilter");
			}
		}

		userDtoList = userInfoService.getUserList(userInfoSearchReqDto);

		for (UserInfoDto dto : userDtoList) {

			if (null != dto.getPhoneNo()) {
				dto.setPhoneNoList(Arrays.asList(StringUtils.splitPreserveAllTokens(dto.getPhoneNo(), ",")));
				dto.setPhList(dto.getPhoneNoList().toString().replace("[", ""));
				dto.setPhList(dto.getPhList().toString().replace("]", ""));
			}

		}
		return userDtoList;
	}

}