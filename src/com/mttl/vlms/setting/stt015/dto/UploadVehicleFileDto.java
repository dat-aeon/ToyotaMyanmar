package com.mttl.vlms.setting.stt015.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.mttl.vlms.common.AbstractDto;

/**
 * UploadVehicleFileDto
 * 
 * 
 *
 */
public class UploadVehicleFileDto extends AbstractDto {

	private static final long serialVersionUID = 1187020691073747092L;

	private Integer id;

	private String model;

	private String chassisNo;

	private String color;

	private String colorCode;

	private String disChargePortName;

	private String dischargePortId;

	private Date dischargePortPlanDate;

	private Date dischargePortActualDate;

	private Date etdOrigin;

	private Date etdEstimateDate;

	private Date etdSin;

	private Date eta;

	private Date vesselBerthingDate;

	private String dlrName;

	private String consigneeOnBl;

	private Date orderMonth;

	private String orderBy;

	private String firstVessel;

	private String secondVessel;

	private Date prodMth;

	private String agentName;

	private Date carriedOutUnitsFromPortDate;

	private String carriedOutUnitsFromPortRemark;

	private String stockOf;

	private Date stockDate;

	private Date purchasePermitDate;

	private Date audioMaileDate;

	private Date audioInstallDate;

	private String remark;

	private Date date;

	private boolean audioInstallRequired;

	private String dealerCode;

	private String procType;

	private int rowNumToDel;

	private String failedReason;

	private boolean delivered;

	@Override
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getChassisNo() {
		return chassisNo;
	}

	public void setChassisNo(String chassisNo) {
		this.chassisNo = chassisNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSecondVessel() {
		return secondVessel;
	}

	public void setSecondVessel(String secondVessel) {
		this.secondVessel = secondVessel;
	}

	public Date getEtdOrigin() {
		return etdOrigin;
	}

	public void setEtdOrigin(Date etdOrigin) {
		this.etdOrigin = etdOrigin;
	}

	public Date getEta() {
		return eta;
	}

	public void setEta(Date eta) {
		this.eta = eta;
	}

	public Date getVesselBerthingDate() {
		return vesselBerthingDate;
	}

	public void setVesselBerthingDate(Date vesselBerthingDate) {
		this.vesselBerthingDate = vesselBerthingDate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getConsigneeOnBl() {
		return consigneeOnBl;
	}

	public void setConsigneeOnBl(String consigneeOnBl) {
		this.consigneeOnBl = consigneeOnBl;
	}

	public String getDischargePortId() {
		return dischargePortId;
	}

	public void setDischargePortId(String dischargePortId) {
		this.dischargePortId = dischargePortId;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public Date getEtdEstimateDate() {
		return etdEstimateDate;
	}

	public void setEtdEstimateDate(Date etdEstimateDate) {
		this.etdEstimateDate = etdEstimateDate;
	}

	public Date getEtdSin() {
		return etdSin;
	}

	public void setEtdSin(Date etdSin) {
		this.etdSin = etdSin;
	}

	public String getFirstVessel() {
		return firstVessel;
	}

	public void setFirstVessel(String firstVessel) {
		this.firstVessel = firstVessel;
	}

	public Date getProdMth() {
		return prodMth;
	}

	public void setProdMth(Date prodMth) {
		this.prodMth = prodMth;
	}

	public Date getCarriedOutUnitsFromPortDate() {
		return carriedOutUnitsFromPortDate;
	}

	public void setCarriedOutUnitsFromPortDate(Date carriedOutUnitsFromPortDate) {
		this.carriedOutUnitsFromPortDate = carriedOutUnitsFromPortDate;
	}

	public String getCarriedOutUnitsFromPortRemark() {
		return carriedOutUnitsFromPortRemark;
	}

	public void setCarriedOutUnitsFromPortRemark(String carriedOutUnitsFromPortRemark) {
		this.carriedOutUnitsFromPortRemark = carriedOutUnitsFromPortRemark;
	}

	public String getStockOf() {
		return stockOf;
	}

	public void setStockOf(String stockOf) {
		this.stockOf = stockOf;
	}

	public Date getStockDate() {
		return stockDate;
	}

	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getDlrName() {
		return dlrName;
	}

	public void setDlrName(String dlrName) {
		this.dlrName = dlrName;
	}

	public String getDealerCode() {
		return dealerCode;
	}

	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}

	public String getDisChargePortName() {
		return disChargePortName;
	}

	public void setDisChargePortName(String disChargePortName) {
		this.disChargePortName = disChargePortName;
	}

	public String getProcType() {
		return procType;
	}

	public void setProcType(String procType) {
		this.procType = procType;
	}

	public int getRowNumToDel() {
		return rowNumToDel;
	}

	public void setRowNumToDel(int rowNumToDel) {
		this.rowNumToDel = rowNumToDel;
	}

	public String getFailedReason() {
		return failedReason;
	}

	public void setFailedReason(String failedReason) {
		this.failedReason = failedReason;
	}

	public Date getDischargePortPlanDate() {
		return dischargePortPlanDate;
	}

	public void setDischargePortPlanDate(Date dischargePortPlanDate) {
		this.dischargePortPlanDate = dischargePortPlanDate;
	}

	public Date getDischargePortActualDate() {
		return dischargePortActualDate;
	}

	public void setDischargePortActualDate(Date dischargePortActualDate) {
		this.dischargePortActualDate = dischargePortActualDate;
	}

	public Date getOrderMonth() {
		return orderMonth;
	}

	public void setOrderMonth(Date orderMonth) {
		this.orderMonth = orderMonth;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isAudioInstallRequired() {
		return audioInstallRequired;
	}

	public void setAudioInstallRequired(boolean audioInstallRequired) {
		this.audioInstallRequired = audioInstallRequired;
	}

	public Date getPurchasePermitDate() {
		return purchasePermitDate;
	}

	public void setPurchasePermitDate(Date purchasePermitDate) {
		this.purchasePermitDate = purchasePermitDate;
	}

	public Date getAudioMaileDate() {
		return audioMaileDate;
	}

	public void setAudioMaileDate(Date audioMaileDate) {
		this.audioMaileDate = audioMaileDate;
	}

	public Date getAudioInstallDate() {
		return audioInstallDate;
	}

	public void setAudioInstallDate(Date audioInstallDate) {
		this.audioInstallDate = audioInstallDate;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

}
