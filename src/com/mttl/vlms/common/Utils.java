package com.mttl.vlms.common;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;

import com.mttl.vlms.exception.SystemException;

/**
 * Utils
 * 
 * 
 *
 */
public class Utils {

	public static Date getCurrentTime() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * Convert date into custom pattern String.
	 */
	public static String formatByPattern(Date input, String pattern) {

		// Empty value check.
		if (input == null) {
			return null;
		}

		if (pattern == null || "".equals(pattern)) {
			return null;
		}

		// Convert process.
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		return sdf.format(input);
	}

	/**
	 * Get the current year and month in string form.
	 */
	public static String getCurrentDateInString() throws SystemException {
		DateFormat df = new SimpleDateFormat("yyyyMM");
		String currentDate = df.format(Calendar.getInstance().getTime());
		return currentDate;
	}

	/**
	 * Get DateTime as String.
	 */
	public static String getDateTimeString(Date date) throws SystemException {
		DateFormat df = new SimpleDateFormat("HH:mm");
		String currentDate = df.format(date);
		return currentDate;
	}

	/**
	 * Get the last date of last month.
	 */
	public static Date getLastMonthEndDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	public static Date getFirstDayOfMonth(Date criDate) {
		Date firstDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(criDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		firstDate = cal.getTime();

		return firstDate;
	}

	public static Date getCurrentMonthLastDate(Date updateDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(updateDate);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	public static Date getNextMonthFirstDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	public static Date getPreviousMonthLastDayOfInputDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	public static String getURLEncodeFileName(String reportFileName) {
		String escapedFilename = null;
		StringBuilder fileName = null;
		try {
			// Encoding
			escapedFilename = URLEncoder.encode(reportFileName, "UTF-8").replaceAll("\\+", "%20");
			fileName = new StringBuilder(escapedFilename);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return fileName.toString();
	}

	public static String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 12) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	private static byte[] fixSecret(String s, int length) throws UnsupportedEncodingException {
		if (s.length() < length) {
			int missingLength = length - s.length();
			for (int i = 0; i < missingLength; i++) {
				s += " ";
			}
		}
		return s.substring(0, length).getBytes("UTF-8");
	}

	public static String getSystemPath() {
		Object context = FacesContext.getCurrentInstance().getExternalContext().getContext();
		String systemPath = ((ServletContext) context).getRealPath("/");
		return systemPath;
	}

	public static String getMonthZawgyi(int month) {
		String zawgyiMonth = new String();
		switch (month) {
			case 1:
				zawgyiMonth = "\u1007\u1014\u1039\u1014\u101D\u102B\u101B\u102E\u101C";
				break;
			case 2:
				zawgyiMonth = "\u1031\u1016\u1031\u1016\u102C\u1039\u101D\u102B\u101B\u102E\u101C";
				break;
			case 3:
				zawgyiMonth = "\u1019\u1010\u1039\u101C";
				break;
			case 4:
				zawgyiMonth = "\u1027\u107F\u1015\u102E\u101C";
				break;
			case 5:
				zawgyiMonth = "\u1031\u1019\u101C";
				break;
			case 6:
				zawgyiMonth = "\u1007\u103C\u1014\u1039\u101C";
				break;
			case 7:
				zawgyiMonth = "\u1007\u1030\u101C\u102D\u102F\u1004\u1039\u101C";
				break;
			case 8:
				zawgyiMonth = "\u107E\u101E\u1002\u102F\u1010\u1039\u101C";
				break;
			case 9:
				zawgyiMonth = "\u1005\u1000\u1039\u1010\u1004\u1039\u1018\u102C\u101C";
				break;
			case 10:
				zawgyiMonth = "\u1031\u1021\u102C\u1000\u1039\u1010\u102D\u102F\u1018\u102C\u101C";
				break;
			case 11:
				zawgyiMonth = "\u108F\u102D\u102F\u101D\u1004\u1039\u1018\u102C\u101C";
				break;
			case 12:
				zawgyiMonth = "\u1012\u102E\u1007\u1004\u1039\u1018\u102C\u101C";
				break;

			default:
				break;
		}
		return zawgyiMonth;
	}

	public static String numberToZawgyi(String number) {
		char[] chars = new char[number.length()];
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			ch = (char) (ch + 0x1040 - '0');
			chars[i] = ch;
		}
		return new String(chars);
	}

	public static boolean isNullAndEmpty(Object obj) {
		return null == obj || "" == obj.toString();
	}

	public static char asciiCodeToChar(int asciiCode) {
		return (char) asciiCode;
	}

	public static void copyFile(File source, File dest) throws IOException {
		FileUtils.copyFile(source, dest);
	}

	public static String listToStringConcat(List<String> listString) {
		String returnValue = null;

		if (null != listString && !listString.isEmpty()) {
			returnValue = String.join(",", listString);
		}

		return returnValue;
	}

	public static List<String> stringToList(String str) {
		List<String> stringList = null;

		if (null != str) {
			stringList = Arrays.asList(str.split(",", -1));
		}

		return stringList;
	}

	public static Date getEndTimeOfDate(Date date) {

		if (date == null) {
			return null;
		}

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);

		return c.getTime();
	}

	public static boolean createDirectory(String directory) {

		boolean bolRet = true;

		Path path = Paths.get(directory);
		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// fail to create directory
				bolRet = false;
			}
		}

		return bolRet;

	}

}