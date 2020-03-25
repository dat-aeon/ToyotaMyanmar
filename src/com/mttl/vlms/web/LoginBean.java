package com.mttl.vlms.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.CannotCreateTransactionException;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.common.PasswordCodecHandler;
import com.mttl.vlms.user.dto.LoginUser;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.user.service.UserService;
import com.mttl.vlms.web.common.BaseBean;

/**
 * LoginBean
 * 
 * 
 *
 */
@ManagedBean(name = "LoginBean")
@RequestScoped
public class LoginBean extends BaseBean {

	private String userId;

	private String password;

	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager;

	@ManagedProperty(value = "#{UserService}")
	private UserService userService;

	@Autowired
	private PasswordCodecHandler passwordCodecHandler;

	public String load() {
		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		if (user != null) {
			return "dashboard";
		}
		removeSessionParam(CommonConstants.LOGIN_USER);
		removeSessionParam(CommonConstants.USER_ID);
		userId = null;
		return null;
	}

	public void forgetPassword() {
		redirect("forgetPassword.xhtml?faces-redirect=true");
	}

	@ApplyAspect
	public String login() {
		try {
			System.out.println(passwordCodecHandler.encode(password));
			User sessionUser = (User) getSessionParam(CommonConstants.LOGIN_USER);
			if (sessionUser != null) {
				return "dashboard";
			}
			Authentication request = new UsernamePasswordAuthenticationToken(userId, password);
			Authentication result = authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
			LoginUser loginUser = (LoginUser) result.getPrincipal();
			putSessionParam(CommonConstants.AUTHENTICATION_USER, loginUser);
			User user = userService.findUser(userId);
			putSessionParam(CommonConstants.LOGIN_USER, user);
			putSessionParam(CommonConstants.USER_ID, userId);
		} catch (AuthenticationException e) {
			Throwable throwable = e;
			throwable = throwable.getCause();
			if (throwable instanceof CannotCreateTransactionException) {
				addErrorMessage(null, "MSG_LOG_002");
			} else {
				addErrorMessage(null, "MSG_LOG_001");
			}
			return null;
		}

		return "dashboard";
	}

	public String logout() {
		HttpSession session = (HttpSession) getFacesContext().getExternalContext().getSession(false);
		session.invalidate();
		SecurityContextHolder.clearContext();
		return "login";
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
