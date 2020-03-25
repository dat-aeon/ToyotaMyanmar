package com.mttl.vlms.setting.stt010.service;

import java.util.List;

import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt010.dto.TrailerDto;
import com.mttl.vlms.setting.stt010.dto.TrailerSearchReqDto;

/**
 * TrailerService
 * 
 * 
 *
 */
public interface TrailerService {
	List<TrailerDto> getMainTrailerList() throws SystemException;

	List<TrailerDto> getTrailerList(TrailerSearchReqDto trailerSearchReqDto) throws SystemException;

	Integer getTrailerListCount(TrailerSearchReqDto trailerSearchReqDto) throws SystemException;

	int checkDuplicateLicenseNo(TrailerDto trailerDto) throws SystemException;

	int checkDuplicateTrailerId(TrailerDto trailerDto) throws SystemException;

	void insertTrailer(TrailerDto trailerDto, Integer createdBy) throws SystemException;

	TrailerDto getTrailerByTrailerId(Integer id) throws SystemException;

	int updateTrailer(TrailerDto trailerDto, Integer updatedBy) throws SystemException;

	int deleteTrailer(TrailerDto trailerDto, Integer updatedBy) throws SystemException;

	int disabledTrailer(TrailerDto trailerDto, Integer updatedBy) throws SystemException;

	String getNextTrailerId() throws SystemException;

}
