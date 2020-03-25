package com.mttl.vlms.web.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;

import com.mttl.vlms.exception.SystemException;

/**
 * Base Bean
 * 
 * 
 *
 */
public class BaseBean {

	@Resource
	private Properties APP_CONFIG;

	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	protected Application getApplicationContext() {
		return getFacesContext().getApplication();
	}

	protected Map<String, Object> getRequestMap() {
		return getFacesContext().getExternalContext().getRequestMap();
	}

	protected Flash getFlash() {
		return getFacesContext().getExternalContext().getFlash();
	}

	protected Map<String, Object> getSessionMap() {
		return getFacesContext().getExternalContext().getSessionMap();
	}

	protected ResourceBundle getBundle() {
		return ResourceBundle.getBundle(getApplicationContext().getMessageBundle(), getFacesContext().getViewRoot().getLocale());
	}

	protected ResourceBundle getResourceBundle() {
		return ResourceBundle.getBundle("LANGUAGES", getFacesContext().getViewRoot().getLocale());
	}

	protected String getSystemPath() {
		Object context = FacesContext.getCurrentInstance().getExternalContext().getContext();
		String systemPath = ((ServletContext) context).getRealPath("/");
		return systemPath;
	}

	protected String getMessage(String id, Object... params) {
		String text = null;
		try {
			text = getBundle().getString(id);
		} catch (MissingResourceException e) {
			text = "!! key " + id + " not found !!";
		}
		if (params != null) {
			MessageFormat mf = new MessageFormat(text);
			text = mf.format(params, new StringBuffer(), null).toString();
		}
		return text;
	}

	protected String getLabel(String id, Object... params) {
		String text = null;
		try {
			text = getResourceBundle().getString(id);
		} catch (MissingResourceException e) {
			text = "!! key " + id + " not found !!";
		}
		if (params != null) {
			MessageFormat mf = new MessageFormat(text);
			text = mf.format(params, new StringBuffer(), null).toString();
		}
		return text;
	}

	protected void addWranningMessage(String id, String errorCode, Object... params) {
		String message = getMessage(errorCode, params);
		getFacesContext().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_WARN, message, ""));
	}

	protected void addInfoMessage(String id, String errorCode, Object... params) {
		String message = getMessage(errorCode, params);
		getFacesContext().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}

	protected void addErrorMessage(String id, String errorCode, Object... params) {
		String message = getMessage(errorCode, params);
		getFacesContext().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}

	protected void addErrorMessage(String message) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
	}

	protected void addInfoMessage(String message) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
	}

	protected void addWranningMessage(String message) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, message));
	}

	protected void addErrorByMessage(String id, String message) {
		getFacesContext().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}

	protected void addInfoByMessage(String id, String message) {
		getFacesContext().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}

	protected void addWranningByMessage(String id, String message) {
		getFacesContext().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_WARN, message, message));
	}

	protected void putRequestParam(String key, Object obj) {
		getRequestMap().put(key, obj);
	}

	protected Object getRequestParam(String key) {
		return getRequestMap().get(key);
	}

	protected void putFlashParam(String key, Object obj) {
		getFlash().put(key, obj);
	}

	protected void keepFlashParam(String key) {
		getFlash().keep(key);
	}

	protected void keepSetMessage() {
		getFlash().setKeepMessages(true);
	}

	protected Object getFlashParam(String key) {
		return getFlash().get(key);
	}

	protected void removeSessionParam(String... keys) {
		for (String key : keys) {
			if (getSessionMap().get(key) != null) {
				getSessionMap().remove(key);
			}
		}
	}

	protected void putSessionParam(String key, Object obj) {
		getSessionMap().put(key, obj);
	}

	protected Object getSessionParam(String key) {
		return getSessionMap().get(key);
	}

	protected boolean isPostBack() {
		return getFacesContext().isPostback();
	}

	protected void handleSysException(SystemException systemException) {
		String errorCode = systemException.getErrorCode();
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorCode, ""));
	}

	protected String getPageInfo(int totalRecord) {
		String result = "";
		int state = 100;
		int base = 0;
		int lastValue = 0;
		int index = 1;
		while (true) {
			if (state >= totalRecord) {
				base = state / 10;
				break;
			}
			state += 100;
		}
		do {
			lastValue = base * index;
			result = result + lastValue + ",";
			index++;
		} while (lastValue < totalRecord);
		return result.substring(0, result.length() - 1);
	}

	protected void redirect(String url) {
		try {
			getFacesContext().getExternalContext().redirect(url);
		} catch (IOException e) {
			throw new AssertionError();
		}
	}

	protected String uploadForUserManual(FileUploadEvent event) {
		String uploadPath = APP_CONFIG.getProperty("EDITOR_PHYSICAL_PATH");
		String uploadName = FilenameUtils.getName((String) event.getFile().getFileName());
		String extension = "." + FilenameUtils.getExtension((String) uploadName);
		String fileName = String.valueOf(System.currentTimeMillis()) + extension;
		String fullFilePath = String.valueOf(uploadPath) + File.separator + fileName;
		try {
			byte[] bytes = event.getFile().getContents();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fullFilePath)));
			stream.write(bytes);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String imagePath = String.valueOf(APP_CONFIG.getProperty("EDITOR_UPLOAD_PATH")) + File.separator + fileName;
		String imgTag = "<img src=" + imagePath + " />";
		return imgTag;
	}

	protected String getAppConfigValue(String key) {
		return APP_CONFIG.getProperty(key);
	}
}
