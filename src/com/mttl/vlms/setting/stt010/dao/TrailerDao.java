package com.mttl.vlms.setting.stt010.dao;

import java.util.List;

import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.setting.stt010.dto.TrailerDto;
import com.mttl.vlms.setting.stt010.dto.TrailerSearchReqDto;

/**
 * TrailerDao
 * 
 * 
 *
 */
public interface TrailerDao {

	List<TrailerDto> getMainTrailerList() throws DAOException;

	List<TrailerDto> getTrailerList(TrailerSearchReqDto trailerSearchReqDto) throws DAOException;

	Integer getTrailerListCount(TrailerSearchReqDto trailerSearchReqDto) throws DAOException;

	void insertTrailer(TrailerDto TrailerDto, Integer createdTrailer) throws DAOException;

	TrailerDto getTrailerByTrailerId(Integer trailerId) throws DAOException;

	int updateTrailer(TrailerDto trailerDto, Integer updatedBy) throws DAOException;

	int deleteTrailer(TrailerDto trailerDto, Integer updatedBy) throws DAOException;

	int checkDuplicateLicenseNo(TrailerDto TrailerDto) throws DAOException;

	int checkDuplicateTrailerId(TrailerDto TrailerDto) throws DAOException;

	int disabledTrailer(TrailerDto trailerDto, Integer updatedBy) throws DAOException;

	String getNextTrailerId() throws DAOException;

}
