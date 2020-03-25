package com.mttl.vlms.report.rpt001.dto;

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
public class InventoryDto extends AbstractDto {

	private static final long serialVersionUID = 1187020691073747192L;

	private Integer id;

	private String chassisNo;

	private String model;

	private String colorCode;

	private String color;

	private String dischargePortId;

	private String orderMonth;

	private String blNo;

	private String firstVesselName;

	private String secondVesselName;

	private String cif;

	private Date prodMth;

	private String etdOrigin;
	
	private Date etdEstimatedDate;

	private Date eta;

	private Date vesselBerthingDate;

	private String agentName;

	private Date carriedOutPort;

	private String stockOf;
	
	private Date stockDate;

	private String dlrName;

	private String consigneeBl;

	private String customer;

	private String requester;

	private String locationStatus;

	private String storagePeriod;

	private String location;

	private String remark;
	
	private String shortName;
	
	private String dealerName;

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

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
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

	public Date getCarriedOutPort() {
		return carriedOutPort;
	}

	public void setCarriedOutPort(Date carriedOutPort) {
		this.carriedOutPort = carriedOutPort;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	
	public String getOrderMonth() {
		return orderMonth;
	}

	public void setOrderMonth(String orderMonth) {
		this.orderMonth = orderMonth;
	}

	public String getBlNo() {
		return blNo;
	}

	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}

	public String getFirstVesselName() {
		return firstVesselName;
	}

	public void setFirstVesselName(String firstVesselName) {
		this.firstVesselName = firstVesselName;
	}

	public Date getProdMth() {
		return prodMth;
	}

	public void setProdMth(Date prodMth) {
		this.prodMth = prodMth;
	}
	
	public String getDischargePortId() {
		return dischargePortId;
	}

	public void setDischargePortId(String dischargePortId) {
		this.dischargePortId = dischargePortId;
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

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
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

	public String getDlrName() {
		return dlrName;
	}

	public void setDlrName(String dlrName) {
		this.dlrName = dlrName;
	}

	public String getConsigneeBl() {
		return consigneeBl;
	}

	public void setConsigneeBl(String consigneeBl) {
		this.consigneeBl = consigneeBl;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getRequester() {
		return requester;
	}

	public void setRequester(String requester) {
		this.requester = requester;
	}

	public String getLocationStatus() {
		return locationStatus;
	}

	public void setLocationStatus(String locationStatus) {
		this.locationStatus = locationStatus;
	}

	public String getStoragePeriod() {
		return storagePeriod;
	}

	public void setStoragePeriod(String storagePeriod) {
		this.storagePeriod = storagePeriod;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	@Override
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
