package com.mttl.vlms.web.common;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

/**
 * Manual Bean.
 *
 *  
 *
 */
@ManagedBean(name = "ManualBean")
public class ManualBean  extends BaseBean{

	/**
	 * Redirect to corresponding manual page.
	 *
	 * @param contentId
	 *            Integer
	 */
	public void redirectToManual(Integer contentId) {
		String path = "";
		if (contentId == 0) {
			path = "home.xhtml?faces-redirect=true";
		} else {
	
			//Other page.
			path = "display.xhtml?faces-redirect=true&includeViewParams=true&contentId=" + contentId;
		}

		HttpServletRequest request = (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
		String url = request.getRequestURL().toString();
		String baseURL = url.substring(0, url.length() + 1 - request.getRequestURI().length()) + "HR-UserManual/" + path;

		RequestContext.getCurrentInstance().execute("window.open('" + baseURL + "')");
	}
}