package com.mttl.vlms.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "guestPreferences")
@SessionScoped
public class GuestPreferences {
	private String layout = "bluegrey";

	private String theme = "bluegrey";

	private boolean darkMenu = true;

	private boolean orientationRTL;

	private boolean horizontal = false;

	private boolean layoutWapperStatic;

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public boolean isDarkMenu() {
		return darkMenu;
	}

	public void setDarkMenu(boolean darkMenu) {
		this.darkMenu = darkMenu;
	}

	public boolean isOrientationRTL() {
		return orientationRTL;
	}

	public void setOrientationRTL(boolean orientationRTL) {
		this.orientationRTL = orientationRTL;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	public boolean isLayoutWapperStatic() {
		return layoutWapperStatic;
	}

	public void setLayoutWapperStatic(boolean layoutWapperStatic) {
		this.layoutWapperStatic = layoutWapperStatic;
	}
}
