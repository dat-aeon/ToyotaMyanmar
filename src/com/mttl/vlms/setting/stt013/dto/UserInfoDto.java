package com.mttl.vlms.setting.stt013.dto;

import java.util.Date;
import java.util.List;

import com.mttl.vlms.common.AbstractDto;
import com.mttl.vlms.common.selectdto.RoleSelectDto;

/**
 * UserInfoDto
 * 
 * 
 *
 */
public class UserInfoDto extends AbstractDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8595701519474689172L;
	private RoleSelectDto roleDto;

	private String barcodeId;// i dun know what to change

	private Integer id;

	private String loginId;

	private String staffId;

	private String name;

	private String password;

	private boolean disabled;

	private Date joinDate;

	private String nrcNo;

	private String phoneNo;

	private Integer roleId;
	private String roleName;
	private Integer gender;
	private String genderLabel;
	private Date dateOfBirth;

	private String department;

	private String address;

	private Integer status;
	private String action;

	private String description;

	private Integer oldID;

	private List<String> phoneNoList;
	private String phList;

	private Date passwordChangeDate;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getNrcNo() {
		return nrcNo;
	}

	public void setNrcNo(String nrcNo) {
		this.nrcNo = nrcNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getBarcodeId() {
		return barcodeId;
	}

	public void setBarcodeId(String barcodeId) {
		this.barcodeId = barcodeId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RoleSelectDto getRoleDto() {
		return roleDto;
	}

	public void setRoleDto(RoleSelectDto roleDto) {
		this.roleDto = roleDto;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getOldID() {
		return oldID;
	}

	public void setOldID(Integer oldID) {
		this.oldID = oldID;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getGenderLabel() {
		return genderLabel;
	}

	public void setGenderLabel(String genderLabel) {
		this.genderLabel = genderLabel;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
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

	public Date getPasswordChangeDate() {
		return passwordChangeDate;
	}

	public void setPasswordChangeDate(Date passwordChangeDate) {
		this.passwordChangeDate = passwordChangeDate;
	}

	public String getPhList() {
		return phList;
	}

	public void setPhList(String phList) {
		this.phList = phList;
	}

}
