package com.mttl.vlms.web.dashboard.dsb001;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.MeterGaugeChartModel;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.dashboard.dsb001.service.YardMeterService;
import com.mttl.vlms.setting.stt017.dto.YardDto;
import com.mttl.vlms.web.common.BaseBean;

/**
 * YardListBean
 * 
 * 
 *
 */
@ManagedBean(name = "YardMeterListBean")
@ViewScoped
public class YardMeterListBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -6598961227154170581L;

	@ManagedProperty(value = "#{YardMeterService}")
	private YardMeterService yardMeterService;
	private List<YardDto> yardList;
	private MeterGaugeChartModel meterGauge;
	private int totalSpace;
	private int result;
	private int etaVehicleNo;
	private int audioInstallVehicle;
	private int pdiVehicle;
	private int licenseExpireDriver;

	@PostConstruct
	public void init() {
		yardList = yardMeterService.findYardList();
		result = yardMeterService.searchYard();
		result = (result / 3) + 1;
	}

	public MeterGaugeChartModel getMeterGauge(YardDto yardDto) {
		List<Number> intervals = new ArrayList<>();
		int totalSpace;
		int restSpace;
		int space;

		totalSpace = yardMeterService.findTotalSpace(yardDto.getId());
		restSpace = yardMeterService.findRestSpace(yardDto.getId());
		space = yardMeterService.findSpace(yardDto.getId());
		intervals.add(restSpace);
		intervals.add(totalSpace);
		totalSpace = 0;// default Value
		restSpace = 0;// default Value
		meterGauge = new MeterGaugeChartModel(space, intervals);
		meterGauge.setSeriesColors("6bd16e,bd2424");
		meterGauge.setTitle(yardDto.getName());
		meterGauge.setResetAxesOnResize(true);
		meterGauge.setMouseoverHighlight(true);
		return meterGauge;
	}

	public String getViewList(YardDto yardDto) {
		removeSessionParam(CommonConstants.YARD_ID);
		putSessionParam(CommonConstants.YARD_ID, yardDto.getId());
		return "parkingLayoutList";

	}

	public String todayArrivedVehicleIds() {
		removeSessionParam(CommonConstants.TODAY_ARRIVED_VEHICLE_CHECK);
		putSessionParam(CommonConstants.TODAY_ARRIVED_VEHICLE_CHECK, "1");
		return "vehicleInfoList";
	}

	public String audioInstalledVehicleIds() {
		removeSessionParam(CommonConstants.AUDIO_INSTALLED_VEHICLE_CHECK);
		putSessionParam(CommonConstants.AUDIO_INSTALLED_VEHICLE_CHECK, "2");
		return "vehicleInfoList";
	}

	public String pdiRequiredVehicleIds() {
		removeSessionParam(CommonConstants.PDI_REQUIRED_VEHICLE_CHECK);
		putSessionParam(CommonConstants.PDI_REQUIRED_VEHICLE_CHECK, "3");
		return "vehicleInfoList";
	}

	public String licenseExpiredDriverList() {
		removeSessionParam(CommonConstants.LICENSE_EXPIRED_DRIVER_CHECK);
		putSessionParam(CommonConstants.LICENSE_EXPIRED_DRIVER_CHECK, "yes");
		return "driverList";
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public List<YardDto> getYardList() {
		return yardList;
	}

	public void setYardList(List<YardDto> yardList) {
		this.yardList = yardList;
	}

	public int getTotalSpace() {
		return totalSpace;
	}

	public void setTotalSpace(int totalSpace) {
		this.totalSpace = totalSpace;
	}

	public int getEtaVehicleNo() {
		etaVehicleNo = yardMeterService.getEtaVehicleNo();
		return etaVehicleNo;
	}

	public void setEtaVehicleNo(int etaVehicleNo) {
		this.etaVehicleNo = etaVehicleNo;
	}

	public YardMeterService getYardMeterService() {
		return yardMeterService;
	}

	public void setYardMeterService(YardMeterService yardMeterService) {
		this.yardMeterService = yardMeterService;
	}

	public int getAudioInstallVehicle() {
		audioInstallVehicle = yardMeterService.getAudioInstallVehicle();
		return audioInstallVehicle;
	}

	public void setAudioInstallVehicle(int audioInstallVehicle) {
		this.audioInstallVehicle = audioInstallVehicle;
	}

	public int getPdiVehicle() {
		pdiVehicle = yardMeterService.getPdiVehicle();
		return pdiVehicle;
	}

	public void setPdiVehicle(int pdiVehicle) {
		this.pdiVehicle = pdiVehicle;
	}

	public int getLicenseExpireDriver() {
		licenseExpireDriver = yardMeterService.countLicenseExpireDriver();
		return licenseExpireDriver;
	}

	public void setLicenseExpireDriver(int licenseExpireDriver) {
		this.licenseExpireDriver = licenseExpireDriver;
	}

}
