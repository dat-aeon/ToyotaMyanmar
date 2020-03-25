package com.mttl.vlms.operation.opr004.dto;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.mttl.vlms.common.AbstractDto;

/**
 * VehicleInfoDto
 * 
 * 
 *
 */
public class VehicleProcessInfoDto extends AbstractDto {

	private static final long serialVersionUID = 1187020691073747192L;

	private Integer id;

	private String chassisNo;

	private String model;

	private String task;

	private String driver;

	private String dlrName;

	private Integer processType;

	private Date fromEta;

	private Date toEta;

	private String driverName;

	private List<String> driverNameList;

	private String trailerType;

	// private DriverSelectDto driverSelectDto;
	//
	// private TaskSelectDto taskSelectDto;
	//
	// private YardSelectDto yardSelectDto;
	//
	// private DealerSelectDto dealerSelectDto;

	private Integer driverId;

	private Integer taskId;

	private Integer yardId;

	private Integer dealerId;

	private Integer vehicleInfoId;

	// private ProcessTypeList newProcessType;

	private Date date;

	private String agentName;

	private String orderMonth;

	private String color;

	private String yardName;

	private String colName;

	private String vehicleLicenseNo;

	private Date inspectedDate;

	private String inspectorBy;

	private boolean carBody;

	private boolean engine;

	private boolean interior;

	private String inspectRemark;

	private String taskName;

	private Integer inspectVehicleId;

	private Date pdiDate;

	public String getOrderMonth() {
		return orderMonth;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setOrderMonth(String orderMonth) {
		this.orderMonth = orderMonth;
	}

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

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDlrName() {
		return dlrName;
	}

	public void setDlrName(String dlrName) {
		this.dlrName = dlrName;
	}

	public Integer getProcessType() {
		return processType;
	}

	public void setProcessType(Integer processType) {
		this.processType = processType;
	}

	public Date getFromEta() {
		return fromEta;
	}

	public void setFromEta(Date fromEta) {
		this.fromEta = fromEta;
	}

	public Date getToEta() {
		return toEta;
	}

	public void setToEta(Date toEta) {
		this.toEta = toEta;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getYardId() {
		return yardId;
	}

	public void setYardId(Integer yardId) {
		this.yardId = yardId;
	}

	public Integer getDealerId() {
		return dealerId;
	}

	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public Integer getVehicleInfoId() {
		return vehicleInfoId;
	}

	public void setVehicleInfoId(Integer vehicleInfoId) {
		this.vehicleInfoId = vehicleInfoId;
	}

	public String getTrailerType() {
		return trailerType;
	}

	public void setTrailerType(String trailerType) {
		this.trailerType = trailerType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getYardName() {
		return yardName;
	}

	public void setYardName(String yardName) {
		this.yardName = yardName;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getVehicleLicenseNo() {
		return vehicleLicenseNo;
	}

	public void setVehicleLicenseNo(String vehicleLicenseNo) {
		this.vehicleLicenseNo = vehicleLicenseNo;
	}

	public Date getInspectedDate() {
		return inspectedDate;
	}

	public void setInspectedDate(Date inspectedDate) {
		this.inspectedDate = inspectedDate;
	}

	public String getInspectorBy() {
		return inspectorBy;
	}

	public void setInspectorBy(String inspectorBy) {
		this.inspectorBy = inspectorBy;
	}

	public boolean isCarBody() {
		return carBody;
	}

	public void setCarBody(boolean carBody) {
		this.carBody = carBody;
	}

	public boolean isEngine() {
		return engine;
	}

	public void setEngine(boolean engine) {
		this.engine = engine;
	}

	public boolean isInterior() {
		return interior;
	}

	public void setInterior(boolean interior) {
		this.interior = interior;
	}

	public String getInspectRemark() {
		return inspectRemark;
	}

	public void setInspectRemark(String inspectRemark) {
		this.inspectRemark = inspectRemark;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getInspectVehicleId() {
		return inspectVehicleId;
	}

	public void setInspectVehicleId(Integer inspectVehicleId) {
		this.inspectVehicleId = inspectVehicleId;
	}

	public List<String> getDriverNameList() {
		return driverNameList;
	}

	public void setDriverNameList(List<String> driverNameList) {
		this.driverNameList = driverNameList;
	}

	@Override
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public Date getPdiDate() {
		return pdiDate;
	}

	public void setPdiDate(Date pdiDate) {
		this.pdiDate = pdiDate;
	}

}
