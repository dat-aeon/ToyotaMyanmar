package com.mttl.vlms.operation.opr002.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.mttl.vlms.common.AbstractDto;

/**
 * VehicleInfoDto
 * 
 * 
 *
 */
public class VehicleInfoDto extends AbstractDto {

	private static final long serialVersionUID = 1187020691073747192L;

	private Integer id;

	private String chassisNo;

	private String model;

	private String color;

	private String colorCode;

	private Integer dischargePortId;

	private String firstVesselName;

	private String secondVesselName;

	private BigDecimal cif;

	private String etdOrigin;

	private Date etdEstimatedDate;

	private Date etdSin;

	private Date eta;

	private Date vesselBerthingDate;

	private Date fromVessel;

	private Date toVessel;

	private Integer portCarriedUnit;

	private Date fromCarried;

	private Date toCarried;

	private Integer dealerId;

	private String consigneeOnBl;

	private String orderBy;

	private Date prodMth;

	private String agentName;

	private Date portCarriedOutDate;

	private String portCarriedOutRemark;

	private String stockOf;

	private Date stockDate;

	private Date purchasePermitDate;

	private Date audioMailDate;

	private Date audioInstallDate;

	private String remark;

	private boolean disabled;

	private boolean audioInstallRequired;

	private String licenseNo;

	private String shortName;

	private String dealerName;

	private Date parkingDate;

	private Integer parkingDuration;

	private Date orderMonth;

	private Date dischargePortPlanDate;

	private Date dischargePortActualDate;

	private String audioInstalled;

	private String status;

	private boolean delivered;

	private String newDelivered;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChassisNo() {
		return chassisNo;
	}

	public void setChassisNo(String chassisNo) {
		this.chassisNo = chassisNo;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSecondVesselName() {
		return secondVesselName;
	}

	public void setSecondVesselName(String secondVesselName) {
		this.secondVesselName = secondVesselName;
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

	public Integer getPortCarriedUnit() {
		return portCarriedUnit;
	}

	public void setPortCarriedUnit(Integer portCarriedUnit) {
		this.portCarriedUnit = portCarriedUnit;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getFirstVesselName() {
		return firstVesselName;
	}

	public void setFirstVesselName(String firstVesselName) {
		this.firstVesselName = firstVesselName;
	}

	public String getEtdOrigin() {
		return etdOrigin;
	}

	public void setEtdOrigin(String etdOrigin) {
		this.etdOrigin = etdOrigin;
	}

	public Date getEtdEstimatedDate() {
		return etdEstimatedDate;
	}

	public void setEtdEstimatedDate(Date etdEstimatedDate) {
		this.etdEstimatedDate = etdEstimatedDate;
	}

	public Date getEtdSin() {
		return etdSin;
	}

	public void setEtdSin(Date etdSin) {
		this.etdSin = etdSin;
	}

	public Integer getDischargePortId() {
		return dischargePortId;
	}

	public void setDischargePortId(Integer dischargePortId) {
		this.dischargePortId = dischargePortId;
	}

	public Integer getDealerId() {
		return dealerId;
	}

	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}

	public String getConsigneeOnBl() {
		return consigneeOnBl;
	}

	public void setConsigneeOnBl(String consigneeOnBl) {
		this.consigneeOnBl = consigneeOnBl;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Date getProdMth() {
		return prodMth;
	}

	public void setProdMth(Date prodMth) {
		this.prodMth = prodMth;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public Date getPortCarriedOutDate() {
		return portCarriedOutDate;
	}

	public void setPortCarriedOutDate(Date portCarriedOutDate) {
		this.portCarriedOutDate = portCarriedOutDate;
	}

	public String getPortCarriedOutRemark() {
		return portCarriedOutRemark;
	}

	public void setPortCarriedOutRemark(String portCarriedOutRemark) {
		this.portCarriedOutRemark = portCarriedOutRemark;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getFromVessel() {
		return fromVessel;
	}

	public void setFromVessel(Date fromVessel) {
		this.fromVessel = fromVessel;
	}

	public Date getToVessel() {
		return toVessel;
	}

	public void setToVessel(Date toVessel) {
		this.toVessel = toVessel;
	}

	public Date getFromCarried() {
		return fromCarried;
	}

	public void setFromCarried(Date fromCarried) {
		this.fromCarried = fromCarried;
	}

	public Date getToCarried() {
		return toCarried;
	}

	public void setToCarried(Date toCarried) {
		this.toCarried = toCarried;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	@Override
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public boolean isAudioInstallRequired() {
		return audioInstallRequired;
	}

	public void setAudioInstallRequired(boolean audioInstallRequired) {
		this.audioInstallRequired = audioInstallRequired;
	}

	public Date getParkingDate() {
		return parkingDate;
	}

	public void setParkingDate(Date parkingDate) {
		this.parkingDate = parkingDate;
	}

	public Integer getParkingDuration() {
		return parkingDuration;
	}

	public void setParkingDuration(Integer parkingDuration) {
		this.parkingDuration = parkingDuration;
	}

	public Date getOrderMonth() {
		return orderMonth;
	}

	public void setOrderMonth(Date orderMonth) {
		this.orderMonth = orderMonth;
	}

	public Date getDischargePortPlanDate() {
		return dischargePortPlanDate;
	}

	public String getAudioInstalled() {
		return audioInstalled;
	}

	public void setAudioInstalled(String audioInstalled) {
		this.audioInstalled = audioInstalled;
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

	public Date getPurchasePermitDate() {
		return purchasePermitDate;
	}

	public void setPurchasePermitDate(Date purchasePermitDate) {
		this.purchasePermitDate = purchasePermitDate;
	}

	public Date getAudioMailDate() {
		return audioMailDate;
	}

	public void setAudioMailDate(Date audioMailDate) {
		this.audioMailDate = audioMailDate;
	}

	public Date getAudioInstallDate() {
		return audioInstallDate;
	}

	public void setAudioInstallDate(Date audioInstallDate) {
		this.audioInstallDate = audioInstallDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getCif() {
		return cif;
	}

	public void setCif(BigDecimal cif) {
		this.cif = cif;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	public String getNewDelivered() {
		return newDelivered;
	}

	public void setNewDelivered(String newDelivered) {
		this.newDelivered = newDelivered;
	}

}
