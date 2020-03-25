package com.mttl.vlms.setting.stt011.dto;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.mttl.vlms.common.AbstractDto;
import com.mttl.vlms.common.selectdto.StateDivisionInfoSelectDto;

/**
 * DriverDto
 * 
 * 
 *
 */
public class DriverDto extends AbstractDto {

	private static final long serialVersionUID = -943346434188490998L;

	private Integer id;

	private String barcodeId;

	private String staffId;

	private String driverName;

	private Integer gender;

	private String genderLabel;

	private String nrcNo;

	private Date dateOfBirth;

	private Date joinDate;

	private Double basicSalary;

	private String licenseType;

	private String licenseNo;

	private Date licenseExpireDate;

	private String phoneNo;

	private List<String> phoneNoList;

	private String address;

	private Integer status;

	private Integer type;

	private String typeLabel;

	private boolean disabled;

	private Integer division;

	private Integer checkExpireDate;

	private StateDivisionInfoSelectDto driverStateDivision;

	private String phList;

	private String licenseExpiredDriverFlag;

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getGenderLabel() {
		return genderLabel;
	}

	public void setGenderLabel(String genderLabel) {
		this.genderLabel = genderLabel;
	}

	public String getNrcNo() {
		return nrcNo;
	}

	public void setNrcNo(String nrcNo) {
		this.nrcNo = nrcNo;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(Double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public Date getLicenseExpireDate() {
		return licenseExpireDate;
	}

	public void setLicenseExpireDate(Date licenseExpireDate) {
		this.licenseExpireDate = licenseExpireDate;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeLabel() {
		return typeLabel;
	}

	public void setTypeLabel(String typeLabel) {
		this.typeLabel = typeLabel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBarcodeId() {
		return barcodeId;
	}

	public void setBarcodeId(String barcodeId) {
		this.barcodeId = barcodeId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Integer getDivision() {
		return division;
	}

	public void setDivision(Integer division) {
		this.division = division;
	}

	public StateDivisionInfoSelectDto getDriverStateDivision() {
		return driverStateDivision;
	}

	public void setDriverStateDivision(StateDivisionInfoSelectDto driverStateDivision) {
		this.driverStateDivision = driverStateDivision;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public List<String> getPhoneNoList() {
		return phoneNoList;
	}

	public void setPhoneNoList(List<String> phoneNoList) {
		this.phoneNoList = phoneNoList;
	}

	public Integer getCheckExpireDate() {
		return checkExpireDate;
	}

	public void setCheckExpireDate(Integer checkExpireDate) {
		this.checkExpireDate = checkExpireDate;
	}

	public String getLicenseExpiredDriverFlag() {
		return licenseExpiredDriverFlag;
	}

	public void setLicenseExpiredDriverFlag(String licenseExpiredDriverFlag) {
		this.licenseExpiredDriverFlag = licenseExpiredDriverFlag;
	}

	@Override
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public String getPhList() {
		return phList;
	}

	public void setPhList(String phList) {
		this.phList = phList;
	}

}
