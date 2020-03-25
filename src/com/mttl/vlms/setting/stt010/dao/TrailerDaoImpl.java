package com.mttl.vlms.setting.stt010.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.common.BasicDAO;
import com.mttl.vlms.common.BeanConverter;
import com.mttl.vlms.common.Utils;
import com.mttl.vlms.entity.Trailer;
import com.mttl.vlms.entity.TrailerExample;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.mapper.TrailerMapper;
import com.mttl.vlms.setting.stt010.dto.TrailerDto;
import com.mttl.vlms.setting.stt010.dto.TrailerSearchReqDto;
import com.mttl.vlms.setting.stt010.mapper.TrailerCustomMapper;

/**
 * TrailerDaoImpl
 * 
 * 
 *
 */
@Repository("TrailerDao")
@Transactional(propagation = Propagation.REQUIRED)
public class TrailerDaoImpl extends BasicDAO implements TrailerDao {

	@Autowired
	private TrailerCustomMapper trailerCustomMapper;

	@Autowired
	private TrailerMapper trailerMapper;

	@Autowired
	private BeanConverter beanConverter;

	@ApplyAspect

	@Override
	public List<TrailerDto> getMainTrailerList() throws DAOException {
		List<TrailerDto> list;
		try {
			list = trailerCustomMapper.getMainTrailerList();
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return list;
	}

	@Override
	@ApplyAspect
	public List<TrailerDto> getTrailerList(TrailerSearchReqDto trailerSearchReqDto) throws DAOException {
		List<TrailerDto> trailerList;
		try {
			trailerList = trailerCustomMapper.getTrailerList(trailerSearchReqDto.getOffSet(), trailerSearchReqDto.getLimit(), trailerSearchReqDto.getSortField(),
					trailerSearchReqDto.getSortOrder(), trailerSearchReqDto.getFilters());
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return trailerList;
	}

	@Override
	@ApplyAspect
	public Integer getTrailerListCount(TrailerSearchReqDto trailerSearchReqDto) throws DAOException {
		Integer invoiceCount;
		try {
			invoiceCount = trailerCustomMapper.getTrailerListCount(trailerSearchReqDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return invoiceCount;
	}

	@ApplyAspect
	@Override
	public void insertTrailer(TrailerDto trailerDto, Integer createdTrailer) throws DAOException {
		try {
			Date createdDate = Utils.getCurrentTime();
			Trailer trailer = beanConverter.convert(trailerDto, Trailer.class);
			trailer.setCreatedDate(createdDate);
			trailer.setUpdatedDate(createdDate);
			trailer.setCreatedBy(createdTrailer);
			trailer.setUpdatedBy(createdTrailer);
			trailerMapper.insertSelective(trailer);

			trailerDto.setId(trailer.getId());

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
	}

	@ApplyAspect
	@Override
	public TrailerDto getTrailerByTrailerId(Integer id) throws DAOException {
		TrailerDto trailerDto;
		try {
			trailerDto = trailerCustomMapper.getTrailerByTrailerId(id);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return trailerDto;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateLicenseNo(TrailerDto trailerDto) throws DAOException {
		int licenseNoCount;
		try {
			licenseNoCount = trailerCustomMapper.checkLicenseNo(trailerDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return licenseNoCount;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateTrailerId(TrailerDto trailerDto) throws DAOException {
		int trailerIdCount;
		try {
			trailerIdCount = trailerCustomMapper.checkTrailerId(trailerDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return trailerIdCount;
	}

	@ApplyAspect
	@Override
	public int updateTrailer(TrailerDto trailerDto, Integer updatedBy) throws DAOException {
		int count;
		try {
			Date updatedDate = Utils.getCurrentTime();

			Trailer trailer = beanConverter.convert(trailerDto, Trailer.class);
			trailer.setUpdatedDate(updatedDate);
			trailer.setUpdatedBy(updatedBy);
			if (null == trailer.getDescription()) {
				trailer.setDescription("");
			}
			if (null == trailer.getPhotoName()) {
				trailer.setPhotoName("");
			}

			TrailerExample example = new TrailerExample();
			example.createCriteria().andIdEqualTo(trailer.getId()).andDeleteFlgEqualTo(false);
			count = trailerMapper.updateByExampleSelective(trailer, example);

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int deleteTrailer(TrailerDto trailerDto, Integer updatedBy) throws DAOException {
		int count;
		try {
			Date updatedDate = Utils.getCurrentTime();
			Trailer trailer = beanConverter.convert(trailerDto, Trailer.class);
			trailer.setDeleteFlg(true);
			trailer.setUpdatedBy(updatedBy);
			trailer.setUpdatedDate(updatedDate);

			TrailerExample trailerExample = new TrailerExample();
			trailerExample.createCriteria().andIdEqualTo(trailer.getId()).andDeleteFlgEqualTo(false);
			count = trailerMapper.updateByExampleSelective(trailer, trailerExample);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int disabledTrailer(TrailerDto trailerDto, Integer updatedBy) throws DAOException {
		int count;
		try {
			Date updatedDate = Utils.getCurrentTime();
			Trailer trailer = beanConverter.convert(trailerDto, Trailer.class);
			trailer.setDisabled(true);
			trailer.setUpdatedBy(updatedBy);
			trailer.setUpdatedDate(updatedDate);

			TrailerExample trailerExample = new TrailerExample();
			trailerExample.createCriteria().andIdEqualTo(trailer.getId()).andDisabledEqualTo(false);
			count = trailerMapper.updateByExampleSelective(trailer, trailerExample);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public String getNextTrailerId() throws DAOException {
		String trailerCode;

		try {
			trailerCode = trailerCustomMapper.getNextTrailerId();
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return trailerCode;
	}

}
