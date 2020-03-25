package com.mttl.vlms.setting.stt017.dao;

import java.util.ArrayList;
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
import com.mttl.vlms.entity.Yard;
import com.mttl.vlms.entity.YardExample;
import com.mttl.vlms.entity.Zone;
import com.mttl.vlms.entity.ZoneColumn;
import com.mttl.vlms.entity.ZoneColumnExample;
import com.mttl.vlms.entity.ZoneExample;
import com.mttl.vlms.entity.ZoneRow;
import com.mttl.vlms.entity.ZoneRowExample;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.mapper.YardMapper;
import com.mttl.vlms.mapper.ZoneColumnMapper;
import com.mttl.vlms.mapper.ZoneMapper;
import com.mttl.vlms.mapper.ZoneRowMapper;
import com.mttl.vlms.operation.opr002.dto.VehicleInfoDto;
import com.mttl.vlms.operation.opr002.mapper.VehicleInfoCustomMapper;
import com.mttl.vlms.operation.opr003.mapper.ParkingLayoutCustomMapper;
import com.mttl.vlms.operation.opr004.dto.InspectVehicleDto;
import com.mttl.vlms.setting.stt017.dto.ColumnDto;
import com.mttl.vlms.setting.stt017.dto.RowDto;
import com.mttl.vlms.setting.stt017.dto.YardDto;
import com.mttl.vlms.setting.stt017.dto.ZoneDto;
import com.mttl.vlms.setting.stt017.mapper.YardCustomMapper;

/**
 * YardDaoImpl
 * 
 * 
 *
 */
@Repository("YardDao")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
public class YardDaoImpl extends BasicDAO implements YardDao {

	@Autowired
	YardCustomMapper yardCustomMapper;

	@Autowired
	ZoneMapper zoneMapper;

	@Autowired
	ZoneRowMapper zoneRowMapper;

	@Autowired
	ZoneColumnMapper zoneColumnMapper;

	@Autowired
	YardMapper yardMapper;

	@Autowired
	BeanConverter beanConverter;

	@Autowired
	ParkingLayoutCustomMapper parkingLayoutCustomMapper;

	@Autowired
	VehicleInfoCustomMapper vehicleInfoCustomMapper;

	@ApplyAspect
	@Override
	public List<YardDto> searchByYardName(String name) throws DAOException {
		List<YardDto> result;
		try {
			result = yardCustomMapper.searchByYardName(name);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public List<ZoneDto> searchByYardFkId(Integer yardFkId) throws DAOException {
		List<ZoneDto> result;
		try {
			result = yardCustomMapper.searchByYardFkId(yardFkId);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public List<ZoneDto> getByRowColumnEachZone(Integer yardId) throws DAOException {
		List<ZoneDto> result;
		try {
			result = yardCustomMapper.getByRowColumnEachZone(yardId);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateYardName(YardDto yardDto) throws DAOException {
		int yardNameCount;
		try {
			yardNameCount = yardCustomMapper.checkYardName(yardDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return yardNameCount;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateYardCode(YardDto yardDto) throws DAOException {
		int yardCodeCount;
		try {
			yardCodeCount = yardCustomMapper.checkYardCode(yardDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return yardCodeCount;
	}

	@ApplyAspect
	@Override
	public int checkYardUsed(Integer id) throws DAOException {
		int yardNameCount;
		try {
			yardNameCount = yardCustomMapper.checkYardUsed(id);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return yardNameCount;
	}

	@ApplyAspect
	@Override
	public void insertYard(YardDto yardDto, Integer createdUser) throws DAOException {

		try {
			Date createdDate = Utils.getCurrentTime();
			Yard yard = beanConverter.convert(yardDto, Yard.class);

			yard.setCreatedDate(createdDate);
			yard.setCreatedUser(createdUser);

			yardMapper.insertSelective(yard);

			yardDto.setId(yard.getId());

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
	}

	@ApplyAspect
	@Override
	public YardDto getYardById(Integer yardId) throws DAOException {
		YardDto yardDto;
		try {
			yardDto = yardCustomMapper.getYardById(yardId);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return yardDto;
	}

	@ApplyAspect
	@Override
	public int updateYard(YardDto yardDto, Integer updatedUser, List<ZoneDto> zonUpdateDtoList) throws DAOException {
		int count;

		try {
			Date updatedDate = Utils.getCurrentTime();
			yardDto.setId(yardDto.getId());
			yardDto.setName(yardDto.getName());
			Yard yard = beanConverter.convert(yardDto, Yard.class);
			yard.setUpdatedDate(updatedDate);
			yard.setUpdatedUser(updatedUser);
			if (null == yard.getDescription()) {
				yard.setDescription("");
			}
			YardExample example = new YardExample();
			example.createCriteria().andIdEqualTo(yard.getId()).andDeleteFlgEqualTo(false);
			count = yardMapper.updateByExampleSelective(yard, example);

			List<ZoneDto> zoneDtoToInsert = new ArrayList<>();
			List<ZoneDto> zoneDtoToUpdate = new ArrayList<>();
			List<ZoneDto> zoneDtoToDelete = new ArrayList<>();

			List<ColumnDto> columnDtoToInsert = new ArrayList<>();
			List<ColumnDto> columnDtoToUpdate = new ArrayList<>();
			List<ColumnDto> columnDtoToDelete = new ArrayList<>();

			for (ZoneDto zoneDto : zonUpdateDtoList) {

				if (zoneDto.getId() == null || zoneDto.getYardId() == null) {
					zoneDto.setYardId(yard.getId());
					zoneDtoToInsert.add(zoneDto);
				} else if (zoneDto.getDisabled() != null || zoneDto.getIsExtraZone() != null) {
					if (yardDto.isDisabled() == true || zoneDto.getDisabled() == true) {
						zoneDto.setDisabled(true);
					} else {
						zoneDto.setDisabled(false);
					}

					zoneDtoToUpdate.add(zoneDto);

				} else if (zoneDto.getDeleteFlg() == true) {
					zoneDtoToDelete.add(zoneDto);
				}

				for (RowDto rowDto : zoneDto.getRowDtoList()) {

					for (ColumnDto columnDto : rowDto.getColumnDtoList()) {

						if (columnDto.getId() == null && zoneDto.getId() != null) {
							columnDto.setYardId(yard.getId());
							columnDto.setZoneId(zoneDto.getId());
							columnDto.setRowId(rowDto.getId());
							columnDtoToInsert.add(columnDto);
						} else if (columnDto.getDisabled() != null || zoneDto.getDisabled() != null) {
							if (yardDto.isDisabled() == true || zoneDto.getDisabled() == true || columnDto.getDisabled() == true) {
								columnDto.setDisabled(true);
							} else {
								columnDto.setDisabled(false);
							}
							columnDtoToUpdate.add(columnDto);
						} else if (columnDto.getDeleteFlg() != null) {
							if (columnDto.getDeleteFlg() == true) {
								columnDtoToDelete.add(columnDto);
							}
						}

					}
				}

			}
			System.out.println("column To delete" + columnDtoToDelete.size());
			if (zoneDtoToInsert.size() > 0) {
				insertZone(zoneDtoToInsert, updatedUser);
			}
			if (zoneDtoToUpdate.size() > 0) {
				updateZone(updatedUser, zoneDtoToUpdate);
			}
			if (zoneDtoToDelete.size() > 0) {
				deleteZone(zoneDtoToDelete, updatedUser);
			}

			if (columnDtoToInsert.size() > 0) {
				insertColumn(columnDtoToInsert, updatedUser);
			}
			if (columnDtoToUpdate.size() > 0) {
				updateColumn(updatedUser, columnDtoToUpdate);
			}
			if (columnDtoToDelete.size() > 0) {
				deleteColumn(updatedUser, columnDtoToDelete);
			}

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int updateZone(Integer updatedUser, List<ZoneDto> zoneUpdateDtoList) throws DAOException {
		int count = 0;
		Date updatedDate = Utils.getCurrentTime();
		for (ZoneDto zoneDto : zoneUpdateDtoList) {

			Zone zone = beanConverter.convert(zoneDto, Zone.class);
			zone.setUpdatedDate(updatedDate);
			zone.setUpdatedUser(updatedUser);
			ZoneExample zoneExample = new ZoneExample();
			zoneExample.createCriteria().andIdEqualTo(zoneDto.getId()).andYardIdEqualTo(zone.getYardId()).andDeleteFlgEqualTo(false);
			count = zoneMapper.updateByExampleSelective(zone, zoneExample);
		}
		return count;

	}

	@ApplyAspect
	@Override
	public int updateRow(Integer updatedUser, List<RowDto> zoneUpdateDtoList) throws DAOException {
		int count = 0;
		Date updatedDate = Utils.getCurrentTime();

		for (RowDto rowDto : zoneUpdateDtoList) {

			ZoneRow row = beanConverter.convert(rowDto, ZoneRow.class);
			row.setUpdatedDate(updatedDate);
			row.setUpdatedUser(updatedUser);
			ZoneRowExample zoneRowExample = new ZoneRowExample();
			zoneRowExample.createCriteria().andIdEqualTo(rowDto.getId());
			count = zoneRowMapper.updateByExample(row, zoneRowExample);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int updateColumn(Integer updatedUser, List<ColumnDto> zoneUpdateDtoList) throws DAOException {
		int count = 0;
		try {
			Date updatedDate = Utils.getCurrentTime();
			for (ColumnDto columnDto : zoneUpdateDtoList) {
				ZoneColumn zoneColumn = beanConverter.convert(columnDto, ZoneColumn.class);
				zoneColumn.setDisabled(columnDto.getDisabled());
				zoneColumn.setUpdatedUser(updatedUser);
				zoneColumn.setUpdatedDate(updatedDate);
				ZoneColumnExample zoneColumnExample = new ZoneColumnExample();
				if (zoneColumn.getId() == null) {
					System.out.println("haha");
				}
				zoneColumnExample.createCriteria().andIdEqualTo(zoneColumn.getId()).andDeleteFlgEqualTo(false);
				count = zoneColumnMapper.updateByExampleSelective(zoneColumn, zoneColumnExample);
			}
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int deleteColumn(Integer updatedUser, List<ColumnDto> zoneUpdateDtoList) throws DAOException {
		int count = 0;
		try {
			Date updatedDate = Utils.getCurrentTime();
			for (ColumnDto columnDto : zoneUpdateDtoList) {
				ZoneColumn zoneColumn = beanConverter.convert(columnDto, ZoneColumn.class);
				zoneColumn.setDeleteFlg(true);
				zoneColumn.setUpdatedUser(updatedUser);
				zoneColumn.setUpdatedDate(updatedDate);
				ZoneColumnExample zoneColumnExample = new ZoneColumnExample();
				zoneColumnExample.createCriteria().andIdEqualTo(zoneColumn.getId()).andDeleteFlgEqualTo(false);
				count = zoneColumnMapper.updateByExampleSelective(zoneColumn, zoneColumnExample);
			}
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int deleteRow(Integer updatedUser, List<RowDto> zoneUpdateDtoList) throws DAOException {
		int count = 0;
		try {
			Date updatedDate = Utils.getCurrentTime();
			for (RowDto rowDto : zoneUpdateDtoList) {
				ZoneRow zoneRow = beanConverter.convert(rowDto, ZoneRow.class);
				zoneRow.setDeleteFlg(true);
				zoneRow.setUpdatedUser(updatedUser);
				zoneRow.setUpdatedDate(updatedDate);
				ZoneRowExample zoneRowExample = new ZoneRowExample();
				zoneRowExample.createCriteria().andIdEqualTo(zoneRow.getId()).andDeleteFlgEqualTo(false);
				count = zoneRowMapper.updateByExampleSelective(zoneRow, zoneRowExample);
				deleteColumn(updatedUser, rowDto.getColumnDtoList());
			}
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int deleteYard(YardDto yardDto, Integer updatedUser) throws DAOException {
		int count;
		try {
			Date updatedDate = Utils.getCurrentTime();
			Yard yard = beanConverter.convert(yardDto, Yard.class);
			yard.setDeleteFlg(true);
			yard.setUpdatedUser(updatedUser);
			yard.setUpdatedDate(updatedDate);
			YardExample yardExample = new YardExample();
			yardExample.createCriteria().andIdEqualTo(yard.getId()).andDeleteFlgEqualTo(false);
			count = yardMapper.updateByExampleSelective(yard, yardExample);
			deleteZone(yardDto.getZoneDtoList(), updatedUser);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	public void deleteZone(List<ZoneDto> zoneDeleteDtoList, Integer updatedUser) throws DAOException {
		try {
			Date updatedDate = Utils.getCurrentTime();
			for (ZoneDto zoneDto : zoneDeleteDtoList) {
				zoneDto.setId(zoneDto.getId());
				Zone zone = beanConverter.convert(zoneDto, Zone.class);
				zone.setDeleteFlg(true);
				zone.setUpdatedUser(updatedUser);
				zone.setUpdatedDate(updatedDate);
				ZoneExample zoneExample = new ZoneExample();
				zoneExample.createCriteria().andIdEqualTo(zone.getId()).andDeleteFlgEqualTo(false);
				zoneMapper.updateByExampleSelective(zone, zoneExample);
				deleteRow(updatedUser, zoneDto.getRowDtoList());
			}
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
	}

	@ApplyAspect
	@Override
	public List<YardDto> getYardList() throws DAOException {
		List<YardDto> yardDtoList = new ArrayList<YardDto>();
		int zoneCount = 0;

		try {

			YardExample yardExample = new YardExample();
			yardExample.createCriteria().andDeleteFlgEqualTo(false);
			yardExample.setOrderByClause("name");

			List<Yard> yardList = yardMapper.selectByExample(yardExample);

			for (Yard yard : yardList) {

				ZoneExample zoneExample = new ZoneExample();
				zoneExample.createCriteria().andDeleteFlgEqualTo(false).andYardIdEqualTo(yard.getId());

				zoneCount = zoneMapper.countByExample(zoneExample);

				YardDto yardDto = beanConverter.convert(yard, YardDto.class);
				yardDto.setZoneCount(zoneCount);

				yardDtoList.add(yardDto);

			}

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}

		return yardDtoList;
	}

	@ApplyAspect
	@Override
	public void insertZone(List<ZoneDto> zoneDtoList, Integer createdUser) throws DAOException {

		try {
			Date createdDate = Utils.getCurrentTime();

			for (ZoneDto zoneDto : zoneDtoList) {
				Zone zone = beanConverter.convert(zoneDto, Zone.class);
				zone.setCreatedUser(createdUser);
				zone.setUpdatedUser(createdUser);
				zone.setCreatedDate(createdDate);
				zone.setUpdatedDate(createdDate);

				zoneMapper.insertSelective(zone);
				zoneDto.setId(zone.getId());

				for (RowDto rowDto : zoneDto.getRowDtoList()) {
					rowDto.setYardId(zoneDto.getYardId());
					rowDto.setZoneId(zone.getId());
				}

				insertRow(zoneDto.getRowDtoList(), createdUser);
			}

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}

	}

	@ApplyAspect
	@Override
	public void insertRow(List<RowDto> rowDtoList, Integer createdUser) throws DAOException {

		try {
			Date createdDate = Utils.getCurrentTime();

			for (RowDto rowDto : rowDtoList) {
				ZoneRow zoneRow = beanConverter.convert(rowDto, ZoneRow.class);
				zoneRow.setCreatedUser(createdUser);
				zoneRow.setUpdatedUser(createdUser);
				zoneRow.setCreatedDate(createdDate);
				zoneRow.setUpdatedDate(createdDate);

				zoneRowMapper.insertSelective(zoneRow);
				rowDto.setId(zoneRow.getId());

				for (ColumnDto columnDto : rowDto.getColumnDtoList()) {
					columnDto.setRowId(zoneRow.getId());
					columnDto.setZoneId(rowDto.getZoneId());
					columnDto.setYardId(rowDto.getYardId());
				}

				insertColumn(rowDto.getColumnDtoList(), createdUser);

			}

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
	}

	@ApplyAspect
	@Override
	public void insertColumn(List<ColumnDto> columnDtoList, Integer createdUser) throws DAOException {
		try {
			Date createdDate = Utils.getCurrentTime();

			for (ColumnDto columnDto : columnDtoList) {
				ZoneColumn zoneColumn = beanConverter.convert(columnDto, ZoneColumn.class);
				zoneColumn.setCreatedUser(createdUser);
				zoneColumn.setUpdatedUser(createdUser);
				zoneColumn.setCreatedDate(createdDate);
				zoneColumn.setUpdatedDate(createdDate);

				zoneColumnMapper.insertSelective(zoneColumn);

				columnDto.setId(zoneColumn.getId());

			}

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
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

		InspectVehicleDto inspectVehicleDto = new InspectVehicleDto();

		try {

			ZoneColumnExample zoneColumnExample = new ZoneColumnExample();
			zoneColumnExample.createCriteria().andDeleteFlgEqualTo(false).andRowIdEqualTo(rowId);
			zoneColumnExample.setOrderByClause("name");

			List<ZoneColumn> zoneColumnList = zoneColumnMapper.selectByExample(zoneColumnExample);

			for (ZoneColumn zoneColumn : zoneColumnList) {
				ColumnDto columnDto = beanConverter.convert(zoneColumn, ColumnDto.class);
				Zone zone = zoneMapper.selectByPrimaryKey(columnDto.getZoneId());

				String aa = columnDto.getName().replaceFirst(zone.getName(), "");
				char character = aa.replaceAll("\\d", "").charAt(0);
				System.out.println(aa.replaceAll("\\d", "").charAt(0));
				int ascii = (int) character;
				columnDto.setColumn_name(ascii - 32);
				System.out.println("after replace all " + ascii);
				System.out.println("columnName is " + columnDto.getColumn_name());

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
