package com.mttl.vlms.operation.opr003.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.common.BasicDAO;
import com.mttl.vlms.common.BeanConverter;
import com.mttl.vlms.entity.Yard;
import com.mttl.vlms.entity.YardExample;
import com.mttl.vlms.entity.Zone;
import com.mttl.vlms.entity.ZoneColumn;
import com.mttl.vlms.entity.ZoneColumnExample;
import com.mttl.vlms.entity.ZoneExample;
import com.mttl.vlms.entity.ZoneRow;
import com.mttl.vlms.entity.ZoneRowExample;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.mapper.InspectVehicleMapper;
import com.mttl.vlms.mapper.VehicleInfoMapper;
import com.mttl.vlms.mapper.YardMapper;
import com.mttl.vlms.mapper.ZoneColumnMapper;
import com.mttl.vlms.mapper.ZoneMapper;
import com.mttl.vlms.mapper.ZoneRowMapper;
import com.mttl.vlms.operation.opr002.dto.VehicleInfoDto;
import com.mttl.vlms.operation.opr002.mapper.VehicleInfoCustomMapper;
import com.mttl.vlms.operation.opr003.mapper.ParkingLayoutCustomMapper;
import com.mttl.vlms.operation.opr003.service.ParkingLayoutService;
import com.mttl.vlms.operation.opr004.dto.InspectVehicleDto;
import com.mttl.vlms.setting.stt017.dto.ColumnDto;
import com.mttl.vlms.setting.stt017.dto.RowDto;
import com.mttl.vlms.setting.stt017.dto.YardDto;
import com.mttl.vlms.setting.stt017.dto.ZoneDto;

/**
 * ParkingLayoutDaoImpl
 * 
 * 
 *
 */
@Repository("ParkingLayoutDao")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ParkingLayoutDaoImpl extends BasicDAO implements ParkingLayoutDao {

	@Autowired
	ZoneMapper zoneMapper;

	@Autowired
	ZoneRowMapper zoneRowMapper;

	@Autowired
	ZoneColumnMapper zoneColumnMapper;

	@Autowired
	YardMapper yardMapper;

	@Autowired
	InspectVehicleMapper inspectVehicleMapper;

	@Autowired
	VehicleInfoMapper vehicleInfoMapper;

	@Autowired
	BeanConverter beanConverter;

	@Autowired
	VehicleInfoCustomMapper vehicleInfoCustomMapper;

	@Autowired
	ParkingLayoutCustomMapper parkingLayoutCustomMapper;

	@ManagedProperty(value = "#{UploadVehicleFileService}")
	private ParkingLayoutService yardService;

	@ApplyAspect
	@Override
	public List<YardDto> getYardList() throws DAOException {
		List<YardDto> yardDtoList = new ArrayList<>();
		List<Yard> yardList;
		try {

			YardExample yardExample = new YardExample();
			yardExample.createCriteria().andDeleteFlgEqualTo(false);

			yardList = yardMapper.selectByExample(yardExample);

			for (Yard yard : yardList) {

				YardDto yardDto = beanConverter.convert(yard, YardDto.class);
				yardDto.setId(yard.getId());
				yardDto.setName(yard.getName());

				yardDtoList.add(yardDto);

			}

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return yardDtoList;
	}

	@ApplyAspect
	@Override
	public List<ZoneDto> getByRowColumnEachZone(Integer yardId) throws DAOException {
		List<ZoneDto> result;
		try {
			result = parkingLayoutCustomMapper.getByRowColumnEachZone(yardId);
			System.out.println("lol");
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public List<ZoneDto> getZoneListByYardId(int yardId) throws DAOException {

		List<ZoneDto> zoneDtoList = new ArrayList<>();

		try {

			ZoneExample zoneExample = new ZoneExample();
			zoneExample.createCriteria().andDeleteFlgEqualTo(false).andYardIdEqualTo(yardId);
			zoneExample.setOrderByClause("id");

			List<Zone> zoneList = zoneMapper.selectByExample(zoneExample);

			for (Zone zone : zoneList) {

				ZoneDto zoneDto = beanConverter.convert(zone, ZoneDto.class);
				zoneDto.setRowDtoList(getRowListByZoneId(zoneDto.getId()));
				zoneDto.setRowCount(zoneDto.getRowDtoList().size());
				if (zoneDto.getRowCount() > 0) {
					zoneDto.setColumnCount(zoneDto.getRowDtoList().get(0).getColumnCount());
				}

				zoneDtoList.add(zoneDto);

			}

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}

		return zoneDtoList;
	}

	@Override
	public List<RowDto> getRowListByZoneId(int zoneId) throws DAOException {

		List<RowDto> rowDtoList = new ArrayList<>();

		try {

			ZoneRowExample zoneRowExample = new ZoneRowExample();
			zoneRowExample.createCriteria().andDeleteFlgEqualTo(false).andZoneIdEqualTo(zoneId);

			List<ZoneRow> zoneRowList = zoneRowMapper.selectByExample(zoneRowExample);

			for (ZoneRow zoneRow : zoneRowList) {
				RowDto rowDto = beanConverter.convert(zoneRow, RowDto.class);
				rowDto.setColumnDtoList(getColumnListByRowId(rowDto.getId()));
				rowDto.setColumnCount(rowDto.getColumnDtoList().size());
				rowDtoList.add(rowDto);
			}

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}

		return rowDtoList;
	}

	@Override
	public List<ColumnDto> getColumnListByRowId(int rowId) throws DAOException {

		List<ColumnDto> columnDtoList = new ArrayList<>();

		try {

			ZoneColumnExample zoneColumnExample = new ZoneColumnExample();
			zoneColumnExample.createCriteria().andDeleteFlgEqualTo(false).andRowIdEqualTo(rowId);
			zoneColumnExample.setOrderByClause("name");

			List<ZoneColumn> zoneColumnList = zoneColumnMapper.selectByExample(zoneColumnExample);

			for (ZoneColumn zoneColumn : zoneColumnList) {

				InspectVehicleDto inspectVehicleDto = new InspectVehicleDto();

				ColumnDto columnDto = new ColumnDto();

				columnDto = beanConverter.convert(zoneColumn, ColumnDto.class);
				System.out.println(zoneColumn.getId());
				inspectVehicleDto = parkingLayoutCustomMapper.getParkingSlotInfoByColumnId(zoneColumn.getId());

				if (inspectVehicleDto != null) {
					columnDto.setVehicleInfoDto(getVehicleInfoByVehicleId(inspectVehicleDto.getVehicleId(), columnDto.getId()));

				}
				columnDtoList.add(columnDto);
			}

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}

		return columnDtoList;
	}

	@Override
	public VehicleInfoDto getVehicleInfoByVehicleId(int vehicleId, int parkingId) throws DAOException {

		VehicleInfoDto vehicleif = new VehicleInfoDto();

		vehicleif = vehicleInfoCustomMapper.getVehicleInfoById(vehicleId);

		vehicleif.setParkingDate(parkingLayoutCustomMapper.getParkingInfo(parkingId));

		Date date = new Date();

		vehicleif.setParkingDuration(date.compareTo(vehicleif.getParkingDate()));

		return vehicleif;

	}

}
