package com.mttl.vlms.setting.stt012.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mttl.vlms.setting.stt012.dto.DealerDto;

/**
 * DealerCustomMapper
 * 
 * 
 *
 */
public interface DealerCustomMapper {
	List<DealerDto> searchByDealerName(@Param("dealerName") String dealerName);

	int checkDealerName(@Param("dealerDto") DealerDto dealerDto);

	int checkDealerCode(@Param("dealerDto") DealerDto dealerDto);

	DealerDto getDealerById(@Param("id") Integer dealerId);

}
