package com.mttl.vlms.common;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ViewCode
 * 
 * 
 *
 */
public class ViewCode {

	public static String satSun;
	public static String custom;
	public static String request;
	public static String approve;
	public static String reject;
	public static String active;
	public static String inActive;
	public static String resigned;
	public static String weekDay;
	public static String holiday;
	public static String attendance;
	public static String absence;
	public static String probationary;
	public static String contractual;
	public static String permanent;
	public static String gazette;
	public static String fullDay;
	public static String halfDay;
	public static String sunday;
	public static String monday;
	public static String tuesday;
	public static String wednesday;
	public static String thursday;
	public static String friday;
	public static String saturday;
	public static String noWeeklyOff;
	public static String january;
	public static String february;
	public static String march;
	public static String april;
	public static String may;
	public static String june;
	public static String july;
	public static String august;
	public static String september;
	public static String october;
	public static String november;
	public static String december;
	public static String saving;
	public static String checking;
	public static String others;
	public static String cancel;
	public static String notContribute;
	public static String fixedAmount;
	public static String percentage;
	public static String attendanceDeduction;
	public static String monthlyDeduction;
	public static String specialDeduction;
	public static String fixed;
	public static String rate;
	public static String businessDaysOfMonth;
	public static String allDaysOfMonth;
	public static String allDaysOfMonthExceptWeekendDay;
	public static String fixedDays;
	public static String notYet;
	public static String onProgress;
	public static String success;
	public static String fail;
	public static String project;
	public static String dependOnWorkingHour;
	public static String dependOnDutyTimeOfShiftPolicy;
	public static String paid;
	public static String unPaid;
	public static String round;
	public static String roundUp;
	public static String truncate;
	public static String fingerPrint;
	public static String webAndMobile;
	public static String firstHalf;
	public static String secondHalf;
	public static String morning;
	public static String evening;
	public static String yes;
	public static String no;
	public static String male;
	public static String female;
	public static String yearly;
	public static String attendanceAllowance;
	public static String monthlyAllowance;
	public static String specialAllowance;
	public static String percentageOnSalary;
	public static String ownDriver;
	public static String businessPartner;

	public static Map<Integer, String> getAllowanceType() {
		Map<Integer, String> allowanceType = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;
			{
				put(0, attendanceAllowance);
				put(1, monthlyAllowance);
				put(2, specialAllowance);
			}
		};
		return allowanceType;
	}

	/** Income tax respite type */
	public static Map<Integer, String> getRespiteType() {
		Map<Integer, String> respiteType = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;
			{
				put(0, fixedAmount);
				put(1, percentageOnSalary);
			}
		};
		return respiteType;
	}

	/**
	 * Bank account type
	 */
	public static Map<Integer, String> getAccountType() {
		Map<Integer, String> accountType = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;
			{
				put(0, saving);
				put(1, checking);
				put(2, others);
			}
		};
		return accountType;
	}

	/**
	 * Attendance or Leave Status
	 */
	public static Map<Integer, String> getStatus() {
		Map<Integer, String> status = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;

			{
				put(1, request);
				put(2, approve);
				put(3, reject);
			}
		};
		return status;
	}

	public static Map<Integer, String> getEmployeeStatus() {
		Map<Integer, String> employeeStatus = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;

			{
				put(0, active);
				put(1, inActive);
				put(2, resigned);
			}
		};
		return employeeStatus;
	}

	public static Map<Integer, String> getAttendanceCategory() {
		Map<Integer, String> attendanceCategory = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;

			{
				put(0, weekDay);
				put(1, holiday);
			}
		};
		return attendanceCategory;
	}

	public static Map<Integer, String> getAttendanceType() {
		Map<Integer, String> attendanceType = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;

			{
				put(0, attendance);
				put(1, absence);
			}
		};
		return attendanceType;
	}

	public static Map<Integer, String> getContractType() {
		Map<Integer, String> contractType = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;

			{
				put(1, probationary);
				put(2, contractual);
				put(3, permanent);
			}
		};
		return contractType;
	}

	public static Map<Integer, String> getHoliday() {
		Map<Integer, String> holiday = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;

			{
				put(0, gazette);
				put(1, custom);
			}
		};
		return holiday;
	}

	public static Map<Integer, String> getWeeklyOffDay() {
		Map<Integer, String> weeklyOffDay = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;

			{
				put(0, sunday);
				put(1, monday);
				put(2, tuesday);
				put(3, wednesday);
				put(4, thursday);
				put(5, friday);
				put(6, saturday);
				put(7, noWeeklyOff);

			}
		};
		return weeklyOffDay;
	}

	public static Map<Integer, String> getDurationOff() {
		Map<Integer, String> durationOff = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;

			{
				put(0, fullDay);
				put(1, halfDay);
			}
		};
		return durationOff;
	}

	/**
	 * Weekly Off Type
	 */
	public static Map<Integer, String> getWeekType() {
		Map<Integer, String> weekType = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;

			{
				put(0, satSun);
				put(1, custom);
			}
		};
		return weekType;
	}

	public static Map<Integer, Integer> getYear() {
		Map<Integer, Integer> year = new LinkedHashMap<Integer, Integer>() {
			private static final long serialVersionUID = 1L;
			{
				Integer calendar = Calendar.getInstance().get(Calendar.YEAR);
				put(calendar, calendar);
				put(calendar - 1, calendar - 1);
				put(calendar - 2, calendar - 2);
			}
		};
		return year;
	}

	public static Map<Integer, String> getMonth() {
		Map<Integer, String> month = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;
			{
				put(1, january);
				put(2, february);
				put(3, march);
				put(4, april);
				put(5, may);
				put(6, june);
				put(7, july);
				put(8, august);
				put(9, september);
				put(10, october);
				put(11, november);
				put(12, december);
			}
		};
		return month;
	}

	public static Map<Integer, String> getResignstatus() {
		Map<Integer, String> resignStatus = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;

			{
				put(1, request);
				put(2, approve);
				put(3, cancel);
			}
		};
		return resignStatus;
	}

	public static Map<Integer, String> getContributeType() {
		Map<Integer, String> contributeType = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;

			{
				put(0, notContribute);
				put(1, fixedAmount);
				put(2, percentage);
			}
		};
		return contributeType;
	}

	public static Map<Integer, String> getPaymentType() {
		Map<Integer, String> paymentType = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;

			{
				put(0, fixed);
				put(1, percentage);
				put(2, rate);
			}
		};
		return paymentType;
	}

	public static Map<Integer, String> getDeductionType() {
		Map<Integer, String> deductionType = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;

			{
				put(0, attendanceDeduction);
				put(1, monthlyDeduction);
				put(2, specialDeduction);
			}
		};
		return deductionType;
	}

	public static Map<Integer, String> getOneDaySalaryPaymentType() {
		Map<Integer, String> oneDaySalaryPaymentType = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;

			{
				put(0, businessDaysOfMonth);
				put(1, allDaysOfMonth);
				put(2, allDaysOfMonthExceptWeekendDay);
				put(3, fixedDays);
			}
		};
		return oneDaySalaryPaymentType;
	}

	public static Map<Integer, String> getProjectStatus() {
		Map<Integer, String> projectStatus = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;
			{
				put(0, notYet);
				put(1, onProgress);
				put(2, success);
				put(3, fail);
			}
		};
		return projectStatus;
	}

	public static Map<Integer, String> getCalculationAttendanceType() {
		Map<Integer, String> calculationAttendanceType = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;
			{
				put(0, dependOnWorkingHour);
				put(1, dependOnDutyTimeOfShiftPolicy);

			}
		};
		return calculationAttendanceType;
	}

	public static Map<Integer, String> getDecimalFormatType() {
		Map<Integer, String> decimalFormatType = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;
			{
				put(0, round);
				put(1, roundUp);
				put(2, truncate);

			}
		};
		return decimalFormatType;
	}

	public static Map<Integer, Integer> getDecimalPlace() {
		Map<Integer, Integer> decimalPlace = new HashMap<Integer, Integer>() {
			private static final long serialVersionUID = 1L;
			{
				put(0, 0);
				put(1, 1);
				put(2, 2);
				put(3, 3);

			}
		};
		return decimalPlace;
	}

	public static Map<Boolean, String> getPaidLeaveTypeName() {
		Map<Boolean, String> leaveType = new HashMap<Boolean, String>() {
			private static final long serialVersionUID = 1L;
			{
				put(true, paid);
				put(false, unPaid);
			}
		};
		return leaveType;
	}

	public static Map<Integer, String> getLeaveFormatType() {
		Map<Integer, String> leaveFormatType = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;
			{
				put(0, fullDay);
				put(1, halfDay);

			}
		};
		return leaveFormatType;
	}

	public static Map<Integer, String> getHalfDayLeaveFormatType() {
		Map<Integer, String> halfDayLeaveFormatType = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;
			{
				put(0, firstHalf);
				put(1, secondHalf);

			}
		};
		return halfDayLeaveFormatType;
	}

	/**
	 * Get Overtime Approve Status Type.
	 */
	public static Map<Integer, String> getOtApproveStatus() {
		Map<Integer, String> otAppriveStatus = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;
			{
				put(0, yes);
				put(1, no);
			}
		};
		return otAppriveStatus;
	}

	/**
	 * Get Overtime Status Type.
	 */
	public static Map<Integer, String> getOtStatus() {
		Map<Integer, String> otStatus = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;
			{
				put(1, request);
				put(2, approve);
				put(3, reject);
			}
		};
		return otStatus;
	}

	public static Map<Integer, String> getMorningEveningStatus() {
		Map<Integer, String> morningEveningStatus = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;
			{
				put(1, morning);
				put(2, evening);
			}
		};
		return morningEveningStatus;
	}

	public static Map<Integer, String> getDateFormatTypeList() {
		Map<Integer, String> dateFormatTypeList = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;
			{
				put(0, "dd/MM/yyyy");
				put(1, "yyyy/MM/dd");
				put(2, "MMM dd,yyyy");
			}
		};
		return dateFormatTypeList;
	}

	public static Map<Integer, String> getGenderList() {
		Map<Integer, String> genderList = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;
			{
				put(0, male);
				put(1, female);
			}
		};
		return genderList;
	}

	public static Map<Integer, String> getFiscalYearPeriodType() {
		Map<Integer, String> periodType = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;
			{
				put(0, yearly);
				put(1, custom);

			}
		};
		return periodType;
	}

	public static Map<Integer, String> getDriverTypeList() {
		Map<Integer, String> genderList = new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;
			{
				put(0, ownDriver);
				put(1, businessPartner);
			}
		};
		return genderList;
	}
}