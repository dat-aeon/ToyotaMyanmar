package com.mttl.vlms.web.operations.opr003;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.operation.opr003.dto.ParkingInfoDto;
import com.mttl.vlms.operation.opr003.dto.ParkingLayoutDto;
import com.mttl.vlms.operation.opr003.service.ParkingLayoutService;
import com.mttl.vlms.setting.stt017.dto.ColumnDto;
import com.mttl.vlms.setting.stt017.dto.RowDto;
import com.mttl.vlms.setting.stt017.dto.YardDto;
import com.mttl.vlms.setting.stt017.dto.ZoneDto;
import com.mttl.vlms.web.common.BaseBean;

/**
 * YardListBean
 * 
 * 
 *
 */
@ManagedBean(name = "ParkingLayoutListBean")
@ViewScoped
public class ParkingLayoutListBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -6598961227154170581L;

	@ManagedProperty(value = "#{ParkingLayoutService}")
	private ParkingLayoutService parkingLayoutService;

	private List<YardDto> yardList;

	private ParkingLayoutDto parkingLayoutdto;

	ParkingLayoutDto parkingLayoutDto;

	private Integer color;

	private List<ZoneDto> yardEditList;

	private List<ParkingInfoDto> parkingInfoDtoList;

	private YardDto yardDtoEdit;

	private List<ZoneDto> zoneDtoList;

	private YardDto backUpYardDtoEdit;

	@PostConstruct
	public void init() {
		yardList = parkingLayoutService.getYardList();
		parkingLayoutdto = new ParkingLayoutDto();
		if (yardList.size() < 1) {
			addInfoMessage("There is no registered yard.");
		} else {
			parkingLayoutdto.setYardDto(yardList.get(0));
			if (null != getSessionParam(CommonConstants.YARD_ID)) {
				YardDto temp = yardList.stream().filter(yard -> getSessionParam(CommonConstants.YARD_ID).equals(yard.getId())).findAny().orElse(null);
				parkingLayoutdto.setYardDto(temp);
				searchYard();
			}
		}

		System.out.println("haha");

	}

	public void searchYard() {

		float colCount = 0;
		float avaliableCount = 0;
		float occupiedCount = 0;
		float reservedCount = 0;
		float slotDamageCount = 0;
		float overFlow = 0;

		zoneDtoList = parkingLayoutService.getZoneListByYardId(parkingLayoutdto.getYardDto().getId());

		System.out.println("lol");

		for (ZoneDto zone : zoneDtoList) {

			if (zone.getIsExtraZone() == true) {
				overFlow++;
			}

			colCount = colCount + zone.getColumnCount() * zone.getRowCount();
			System.out.println(colCount);
			for (RowDto row : zone.getRowDtoList()) {

				for (ColumnDto column : row.getColumnDtoList()) {

					if (zone.getIsExtraZone() == false) {

						if (column.getStatus() == 0) {
							avaliableCount++;

						} else if (column.getStatus() == 1) {
							occupiedCount++;
						} else if (column.getStatus() == 2) {
							reservedCount++;
						} else if (column.getStatus() == 3) {
							slotDamageCount++;
						}
					} else {

						if (column.getStatus() == 0) {
							avaliableCount++;
						} else if (column.getStatus() == 1) {
							occupiedCount++;
						} else if (column.getStatus() == 2) {
							reservedCount++;
						} else if (column.getStatus() == 3) {
							slotDamageCount++;
						}

					}
				}

			}

		}
		parkingInfoDto(colCount, avaliableCount, occupiedCount, reservedCount, slotDamageCount, overFlow);
		removeSessionParam(CommonConstants.YARD_ID);
	}

	public List<ParkingInfoDto> getParkingInfoDtoList() {
		return parkingInfoDtoList;
	}

	public void setParkingInfoDtoList(List<ParkingInfoDto> parkingInfoDtoList) {
		this.parkingInfoDtoList = parkingInfoDtoList;
	}

	public List<ParkingInfoDto> parkingInfoDto(float columnCount, float avaliableCount, float occupiedCount, float reservedCount, float slotDamageCount, float overFlowCount) {
		parkingInfoDtoList = new ArrayList<ParkingInfoDto>();
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(0);
		df.setRoundingMode(RoundingMode.HALF_UP);
		float avaliable = (avaliableCount / (float) columnCount) * 100;
		float occupied = (occupiedCount / (float) columnCount) * 100;
		float reserved = (reservedCount / (float) columnCount) * 100;
		float slotDamage = (slotDamageCount / (float) columnCount) * 100;
		// float overFlow = (overFlowCount / (float) columnCount) * 100;

		int count = 1;
		while (count < 7) {
			System.out.println(avaliableCount);
			ParkingInfoDto parkingInfoDto = new ParkingInfoDto();
			switch (count) {
				case 1:
					parkingInfoDto.setPercentage("(" + df.format(avaliable) + "%)");
					parkingInfoDto.setTotal(avaliableCount);
					parkingInfoDto.setType("Available");
					parkingInfoDto.setColor(0);
					break;
				case 2:
					parkingInfoDto.setPercentage("(" + df.format(occupied) + "%)");
					parkingInfoDto.setTotal(occupiedCount);
					parkingInfoDto.setType("Occupied");
					parkingInfoDto.setColor(1);
					break;
				case 3:
					parkingInfoDto.setPercentage("(" + df.format(reserved) + "%)");
					parkingInfoDto.setTotal(reservedCount);
					parkingInfoDto.setType("Reserve");
					parkingInfoDto.setColor(2);
					break;
				case 4:
					parkingInfoDto.setPercentage("(" + df.format(slotDamage) + "%)");
					parkingInfoDto.setTotal(slotDamageCount);
					parkingInfoDto.setType("Slot Damage");
					parkingInfoDto.setColor(3);
					break;
				case 5:
					parkingInfoDto.setPercentage("(" + 100 + "%)");
					parkingInfoDto.setTotal(columnCount);
					parkingInfoDto.setType("Total");
					parkingInfoDto.setColor(5);
					break;

				case 6: // parkingInfoDto.setPercentage(overFlow);
					parkingInfoDto.setTotal(overFlowCount);
					parkingInfoDto.setType("ExtraZone");
					parkingInfoDto.setColor(4);
					break;

			}

			parkingInfoDtoList.add(parkingInfoDto);

			count++;
		}

		return parkingInfoDtoList;
	}

	public void showMessage() {
		String messageCode = (String) getSessionParam(CommonConstants.MESSAGE_CODE);
		String messageParameter = (String) getSessionParam(CommonConstants.MESSAGE_PARAMETER);
		removeSessionParam(CommonConstants.MESSAGE_CODE, CommonConstants.MESSAGE_PARAMETER);
		if (messageCode != null && messageParameter != null) {
			addErrorMessage(null, messageCode, messageParameter);
		}
	}

	public ParkingLayoutDto getParkingLayoutdto() {
		return parkingLayoutdto;
	}

	public void setParkingLayoutdto(ParkingLayoutDto parkingLayoutdto) {
		this.parkingLayoutdto = parkingLayoutdto;
	}

	public ParkingLayoutService getParkingLayoutService() {
		return parkingLayoutService;
	}

	public void setParkingLayoutService(ParkingLayoutService parkingLayoutService) {
		this.parkingLayoutService = parkingLayoutService;
	}

	public List<YardDto> getYardList() {
		return yardList;
	}

	public void setYardList(List<YardDto> yardList) {
		this.yardList = yardList;
	}

	public ParkingLayoutDto getParkingLayoutDto() {
		return parkingLayoutDto;
	}

	public void setParkingLayoutDto(ParkingLayoutDto parkingLayoutDto) {
		this.parkingLayoutDto = parkingLayoutDto;
	}

	public List<ZoneDto> getYardEditList() {
		return yardEditList;
	}

	public void setYardEditList(List<ZoneDto> yardEditList) {
		this.yardEditList = yardEditList;
	}

	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public YardDto getBackUpYardDtoEdit() {
		return backUpYardDtoEdit;
	}

	public void setBackUpYardDtoEdit(YardDto backUpYardDtoEdit) {
		this.backUpYardDtoEdit = backUpYardDtoEdit;
	}

	public YardDto getYardDtoEdit() {
		return yardDtoEdit;
	}

	public void setYardDtoEdit(YardDto yardDtoEdit) {
		this.yardDtoEdit = yardDtoEdit;
	}

	public List<ZoneDto> getZoneDtoList() {
		return zoneDtoList;
	}

	public void setZoneDtoList(List<ZoneDto> zoneDtoList) {
		this.zoneDtoList = zoneDtoList;
	}

}
