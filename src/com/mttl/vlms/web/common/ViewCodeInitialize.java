package com.mttl.vlms.web.common;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.mttl.vlms.common.ViewCode;

@Component
public class ViewCodeInitialize extends BaseBean implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private MessageSource messageSource;

	public void onApplicationEvent(ContextRefreshedEvent event) {
		Locale locale = getFacesContext().getViewRoot().getLocale();
		ViewCode.attendance = messageSource.getMessage("ATTENDANCE", null, locale);
		ViewCode.yes = messageSource.getMessage("BOOLEAN_YES_LABEL", null, locale);
		ViewCode.no = messageSource.getMessage("BOOLEAN_NO_LABEL", null, locale);
		ViewCode.male = messageSource.getMessage("MALE", null, locale);
		ViewCode.female = messageSource.getMessage("FEMALE", null, locale);
		ViewCode.yearly = messageSource.getMessage("YEARLY", null, locale);
		ViewCode.ownDriver = messageSource.getMessage("OWN_DRIVER", null, locale);
		ViewCode.businessPartner = messageSource.getMessage("BUSINESS_PARTNER", null, locale);
	}
}
