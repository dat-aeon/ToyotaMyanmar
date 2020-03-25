/**
 * 
 */
package com.mttl.vlms.common;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * CurrencyConverter
 * 
 *  
 *
 */
@FacesConverter("CurrencyConverter")
public class CurrencyConverter implements Converter {

	private String pattern;

	private int decimalFormatType;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return arg2;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 != null) {
			DecimalFormat decimalformat = new DecimalFormat(pattern);

			// If decimalFormatType is round.
			if (decimalFormatType == 0) {
				decimalformat.setRoundingMode(RoundingMode.HALF_UP);

				// If decimal format type is round up.
			} else if (decimalFormatType == 1) {
				decimalformat.setRoundingMode(RoundingMode.UP);

				// If decimal format type is truncate.
			} else {
				decimalformat.setRoundingMode(RoundingMode.FLOOR);
			}
			return decimalformat.format(arg2);
		}
		return "";
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public int getDecimalFormatType() {
		return decimalFormatType;
	}

	public void setDecimalFormatType(int decimalFormatType) {
		this.decimalFormatType = decimalFormatType;
	}
}
