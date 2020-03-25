package com.mttl.vlms.web;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mttl.vlms.web.common.BaseBean;

/**
 * LocaleBean
 * 
 *  
 *
 */
@ManagedBean(name = "LocaleBean")
@SessionScoped
public class LocaleBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Locale locale;

	@PostConstruct
	public void init() {
		locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
	}

	public Locale getLocale() {
		return locale;
	}

	public String getLanguage() {
		return locale.getLanguage();
	}

	public void setLanguage(String language) {
		locale = new Locale(language);
		getFacesContext().getViewRoot().setLocale(locale);
	}
}
