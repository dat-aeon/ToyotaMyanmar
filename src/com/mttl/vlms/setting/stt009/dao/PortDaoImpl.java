package com.mttl.vlms.setting.stt009.dao;

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
import com.mttl.vlms.common.selectdto.PortSelectDto;
import com.mttl.vlms.common.selectmapper.SelectMapper;
import com.mttl.vlms.entity.PortTerminal;
import com.mttl.vlms.entity.PortTerminalExample;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.mapper.PortTerminalMapper;
import com.mttl.vlms.setting.stt009.dto.PortDto;
import com.mttl.vlms.setting.stt009.mapper.PortCustomMapper;

/**
 * PortDaoImpl
 * 
 * 
 *
 */
@Repository("PortDao")
@Transactional(propagation = Propagation.REQUIRED)
public class PortDaoImpl extends BasicDAO implements PortDao {

	@Autowired
	PortCustomMapper portCustomMapper;

	@Autowired
	PortTerminalMapper portMapper;

	@Autowired
	private SelectMapper selectMapper;

	@Autowired
	BeanConverter beanConverter;

	/*
	 * @ApplyAspect
	 * 
	 * @Override public int checkDuplicateShortName(PortDto portDto) throws
	 * DAOException {
	 * 
	 * int portCodeCount; try { portCodeCount =
	 * portCustomMapper.checkShortName(portDto); } catch (RuntimeException e) {
	 * throw translate(e.getMessage(), e); } return portCodeCount; }
	 */
	@ApplyAspect
	@Override
	public int checkDuplicateFullName(PortDto portDto) throws DAOException {
		int portNameCount;
		try {
			portNameCount = portCustomMapper.checkPortName(portDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return portNameCount;
	}

	@ApplyAspect
	@Override
	public List<PortDto> searchByPortName(String portName) throws DAOException {
		List<PortDto> result;
		try {
			result = portCustomMapper.searchByPortName(portName);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public int checkPortUsed(Integer id) throws DAOException {
		int portNameCount;
		try {
			portNameCount = portCustomMapper.checkPortUsed(id);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return portNameCount;
	}

	@ApplyAspect
	@Override
	public void insertPort(PortDto portDto, Integer createdUser) throws DAOException {
		try {
			Date createdDate = Utils.getCurrentTime();
			PortTerminal port = beanConverter.convert(portDto, PortTerminal.class);
			port.setCreatedDate(createdDate);
			port.setCreatedUser(createdUser);
			portMapper.insertSelective(port);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
	}

	@ApplyAspect
	@Override
	public PortDto getPortById(Integer portId) throws DAOException {
		PortDto portDto;
		try {
			portDto = portCustomMapper.getPortById(portId);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return portDto;
	}

	@ApplyAspect
	@Override
	public int updatePort(PortDto portDto, Integer updatedUser) throws DAOException {
		int count;
		try {
			Date updatedDate = Utils.getCurrentTime();
			portDto.setShortName(portDto.getShortName());
			portDto.setPortName(portDto.getPortName());
			PortTerminal port = beanConverter.convert(portDto, PortTerminal.class);
			port.setUpdatedDate(updatedDate);
			port.setUpdatedUser(updatedUser);
			PortTerminalExample example = new PortTerminalExample();
			example.createCriteria().andIdEqualTo(port.getId()).andDeleteFlgEqualTo(false);
			if (null == port.getAddress()) {
				port.setAddress("");
			}
			if (null == port.getDescription()) {
				port.setDescription("");
			}
			count = portMapper.updateByExampleSelective(port, example);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int deletePort(PortDto portDto, Integer updatedUser) throws DAOException {
		int count;
		try {
			Date updatedDate = Utils.getCurrentTime();
			PortTerminal port = beanConverter.convert(portDto, PortTerminal.class);
			port.setDeleteFlg(true);
			port.setUpdatedUser(updatedUser);
			port.setUpdatedDate(updatedDate);
			PortTerminalExample portExample = new PortTerminalExample();
			portExample.createCriteria().andIdEqualTo(port.getId()).andDeleteFlgEqualTo(false);
			count = portMapper.updateByExampleSelective(port, portExample);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public List<PortSelectDto> getPortList() throws DAOException {
		List<PortSelectDto> portList;
		try {
			portList = selectMapper.getPortList();
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return portList;
	}

	@Override
	public int checkDuplicateShortName(Integer id, String shortName) throws DAOException {
		int idCount;
		try {
			idCount = portCustomMapper.checkDuplicateShortName(id, shortName);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return idCount;

	}
}
