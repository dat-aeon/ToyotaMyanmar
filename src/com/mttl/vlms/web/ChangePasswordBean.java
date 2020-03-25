package com.mttl.vlms.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.CannotCreateTransactionException;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.common.PasswordCodecHandler;
import com.mttl.vlms.setting.stt013.service.UserInfoService;
import com.mttl.vlms.user.dto.LoginUser;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * ChangePasswordBean
 * 
 * 
 *
 */
@ManagedBean(name = "ChangePasswordBean")
@RequestScoped
public class ChangePasswordBean extends BaseBean {
	private String currentPassword;
	private String newPassword;
	private String confirmPassword;

	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager;

	@ManagedProperty(value = "#{UserInfoService}")
	private UserInfoService userService;

	@Autowired
	private PasswordCodecHandler passwordCodecHandler;

	@ApplyAspect
	public String changePassword() {
		try {

			String userId = (String) getSessionParam(CommonConstants.USER_ID);
			Authentication request = new UsernamePasswordAuthenticationToken(userId, currentPassword);
			authenticationManager.authenticate(request);
			if (newPassword.equals(confirmPassword)) {
				LoginUser loginUser = (LoginUser) getSessionParam(CommonConstants.AUTHENTICATION_USER);
				User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
				loginUser.setPassword(newPassword);
				userService.updateUser(loginUser, user, user.getUserID());
				addInfoMessage(null, "MSG_LOG_004");
				keepSetMessage();
				return "changePassword";
			}
		} catch (AuthenticationException e) {
			Throwable throwable = e;
			throwable = throwable.getCause();
			if (throwable instanceof CannotCreateTransactionException) {
				addErrorMessage(null, "MSG_LOG_002");
			} else {
				addErrorMessage(null, "MSG_MNU_001");
			}
			return null;
		}
		return "changePassword";

	}

	public String reset() {
		PrimeFaces.current().resetInputs("changePasswordForm");
		return "changePassword";
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public PasswordCodecHandler getPasswordCodecHandler() {
		return passwordCodecHandler;
	}

	public void setPasswordCodecHandler(PasswordCodecHandler passwordCodecHandler) {
		this.passwordCodecHandler = passwordCodecHandler;
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public UserInfoService getUserService() {
		return userService;
	}

	public void setUserService(UserInfoService userService) {
		this.userService = userService;
	}

}
