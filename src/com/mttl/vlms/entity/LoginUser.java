package com.mttl.vlms.entity;

import java.util.Date;

public class LoginUser {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_user.user_id
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_user.password
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_user.created_user
     *
     * @mbggenerated
     */
    private Integer createdUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_user.created_date
     *
     * @mbggenerated
     */
    private Date createdDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_user.updated_user
     *
     * @mbggenerated
     */
    private Integer updatedUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_user.updated_date
     *
     * @mbggenerated
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_user.password_change_date
     *
     * @mbggenerated
     */
    private Date passwordChangeDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_user.recovery_code
     *
     * @mbggenerated
     */
    private String recoveryCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_user.recovery_code_time
     *
     * @mbggenerated
     */
    private Date recoveryCodeTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_user.site_admin_flag
     *
     * @mbggenerated
     */
    private Boolean siteAdminFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_user.user_id
     *
     * @return the value of login_user.user_id
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_user.user_id
     *
     * @param userId the value for login_user.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_user.password
     *
     * @return the value of login_user.password
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_user.password
     *
     * @param password the value for login_user.password
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_user.created_user
     *
     * @return the value of login_user.created_user
     *
     * @mbggenerated
     */
    public Integer getCreatedUser() {
        return createdUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_user.created_user
     *
     * @param createdUser the value for login_user.created_user
     *
     * @mbggenerated
     */
    public void setCreatedUser(Integer createdUser) {
        this.createdUser = createdUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_user.created_date
     *
     * @return the value of login_user.created_date
     *
     * @mbggenerated
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_user.created_date
     *
     * @param createdDate the value for login_user.created_date
     *
     * @mbggenerated
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_user.updated_user
     *
     * @return the value of login_user.updated_user
     *
     * @mbggenerated
     */
    public Integer getUpdatedUser() {
        return updatedUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_user.updated_user
     *
     * @param updatedUser the value for login_user.updated_user
     *
     * @mbggenerated
     */
    public void setUpdatedUser(Integer updatedUser) {
        this.updatedUser = updatedUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_user.updated_date
     *
     * @return the value of login_user.updated_date
     *
     * @mbggenerated
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_user.updated_date
     *
     * @param updatedDate the value for login_user.updated_date
     *
     * @mbggenerated
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_user.password_change_date
     *
     * @return the value of login_user.password_change_date
     *
     * @mbggenerated
     */
    public Date getPasswordChangeDate() {
        return passwordChangeDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_user.password_change_date
     *
     * @param passwordChangeDate the value for login_user.password_change_date
     *
     * @mbggenerated
     */
    public void setPasswordChangeDate(Date passwordChangeDate) {
        this.passwordChangeDate = passwordChangeDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_user.recovery_code
     *
     * @return the value of login_user.recovery_code
     *
     * @mbggenerated
     */
    public String getRecoveryCode() {
        return recoveryCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_user.recovery_code
     *
     * @param recoveryCode the value for login_user.recovery_code
     *
     * @mbggenerated
     */
    public void setRecoveryCode(String recoveryCode) {
        this.recoveryCode = recoveryCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_user.recovery_code_time
     *
     * @return the value of login_user.recovery_code_time
     *
     * @mbggenerated
     */
    public Date getRecoveryCodeTime() {
        return recoveryCodeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_user.recovery_code_time
     *
     * @param recoveryCodeTime the value for login_user.recovery_code_time
     *
     * @mbggenerated
     */
    public void setRecoveryCodeTime(Date recoveryCodeTime) {
        this.recoveryCodeTime = recoveryCodeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_user.site_admin_flag
     *
     * @return the value of login_user.site_admin_flag
     *
     * @mbggenerated
     */
    public Boolean getSiteAdminFlag() {
        return siteAdminFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_user.site_admin_flag
     *
     * @param siteAdminFlag the value for login_user.site_admin_flag
     *
     * @mbggenerated
     */
    public void setSiteAdminFlag(Boolean siteAdminFlag) {
        this.siteAdminFlag = siteAdminFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_user
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", password=").append(password);
        sb.append(", createdUser=").append(createdUser);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedUser=").append(updatedUser);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append(", passwordChangeDate=").append(passwordChangeDate);
        sb.append(", recoveryCode=").append(recoveryCode);
        sb.append(", recoveryCodeTime=").append(recoveryCodeTime);
        sb.append(", siteAdminFlag=").append(siteAdminFlag);
        sb.append("]");
        return sb.toString();
    }
}