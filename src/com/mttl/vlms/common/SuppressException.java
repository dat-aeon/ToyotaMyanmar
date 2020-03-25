/**
 * 
 */
package com.mttl.vlms.common;

import java.util.HashMap;
import java.util.Map;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

/**
 * SuppressException
 * 
 *  
 *
 */
public class SuppressException extends CellProcessorAdaptor {

	public static Map<String, Integer> errorMessageList = new HashMap<String, Integer>();

	public SuppressException(CellProcessor next) {
		super(next);
	}

	@SuppressWarnings("unchecked")
	public Object execute(Object value, CsvContext context) {
		try {
			// attempt to execute the next processor
			return next.execute(value, context);

		} catch (SuperCsvCellProcessorException e) {
			// save the exception
			errorMessageList.put(e.getProcessor().toString(), e.getCsvContext().getColumnNumber());

			// and suppress it (null is written as "")
			return null;
		}
	}
}
