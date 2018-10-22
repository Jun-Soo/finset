/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.koscom.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DurationFieldType;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.Assert;

/**
 * Date Utility Class <br>
 * This is used to manage Date Object.
 * <br> org.anyframe.util 패키지 클래스를 목적에 맞게 수정하여 사용합니다.
 *
 * @author SoYon Lim
 * @author JongHoon Kim
 * @author HyunJung Jeong
 * @author HoYeon Lee
 * @author DoHyung Kim
 */
public class DateUtil {
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	// ~ Static fields/initializers
	// =============================================

	/** Date pattern */
	public static final String DATE_PATTERN_DASH = "yyyy-MM-dd";
	public static final String DATE_PATTERN_DOT = "yyyy.MM.dd";

	/** Time pattern */
	public static final String TIME_PATTERN = "HH:mm";

	/** Date Time pattern */
	public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/** Date HMS pattern */
	public static final String DATE_HMS_PATTERN = "yyyyMMddHHmmss";

	/** Time stamp pattern */
	public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

	/** year pattern (yyyy) */
	public static final String YEAR_PATTERN = "yyyy";

	/** month pattern (MM) */
	public static final String MONTH_PATTERN = "MM";

	/** day pattern (dd) */
	public static final String DAY_PATTERN = "dd";

	/** date pattern (yyyyMMdd) */
	public static final String DATE_PATTERN = "yyyyMMdd";

	/** hour, minute, second pattern (HHmmss) */
	public static final String TIME_HMS_PATTERN = "HHmmss";

	/** hour, minute, second pattern (HH:mm:ss) */
	public static final String TIME_HMS_PATTERN_COLONE = "HH:mm:ss";

	/**
	 * last day of each month for a common year (other than a leap year)
	 */
	private static final int[] lastDayOfMonth = { 31, 28, 31, 30, 31, 30, 31,
			31, 30, 31, 30, 31 };

	/**
	 * last day of each month for a leap year
	 */
	private static int[] lastDayOfMonthForLeapYear = { 31, 29, 31, 30, 31, 30,
			31, 31, 30, 31, 30, 31 };



	/**
	 * get current datetime
	 *
	 * @return String representing current day (yyyy-MM-dd HH:mm:ss)
	 */
	public static String getCurrentDateTime() {
		return getCurrentDateTime(DATE_TIME_PATTERN);
	}


	/**
	 * get current time
	 *
	 * @param pattern
	 *            time pattern
	 * @return String representing current time (type of pattern)
	 */
	public static String getCurrentDateTime(String pattern) {
		DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
		return fmt.print(dt);
	}

	/**
	 * get this month including this year
	 *
	 * @return String representing this month (yyyy-MM)
	 */
	public static String getThisMonth() {
		return getCurrentDateTime("yyyy-MM");
	}

	/**
	 * get this year
	 *
	 * @return String representing this year (yyyy)
	 */
	public static String getThisYear() {
		return getCurrentDateTime("yyyy");
	}

	/**
	 * return day of the week of the input data. return in abbreviation pattern
	 * for the default language of the current system.
	 *
	 * <pre>
	 * DateUtil.getDayOfWeek(&quot;2011-02-04&quot;) = &quot;Fri&quot;;
	 * </pre>
	 *
	 * @param date
	 *            date(yyyy-MM-dd)
	 * @return String day of week (shortText, Default Locale)
	 */
	public static String getDayOfWeek(String date) {
		return getDayOfWeek(date, true, LocaleContextHolder.getLocale());
	}

	/**
	 * return day of the week of the input data. return in abbreviation or full
	 * day of the week pattern for the language after getting locale info.
	 *
	 * <pre>
	 * DateUtil.getDayOfWeek(&quot;2011-02-04&quot;, true, Locale.US) = &quot;Fri&quot;;
	 * DateUtil.getDayOfWeek(&quot;2011-02-04&quot;, false, Locale.US) = &quot;Friday&quot;;
	 * </pre>
	 *
	 * @param date
	 *            date(yyyyMMdd)
	 * @param abbreviation
	 *            if <code>true</code>, return in abbreviation.
	 * @param locale
	 *            locale
	 * @return String day of week
	 */
	public static String getDayOfWeek(String date, boolean abbreviation,
			Locale locale) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
		DateTime dt = fmt.parseDateTime(date);
		DateTime.Property dayOfWeek = dt.dayOfWeek();

		if (abbreviation)
			return dayOfWeek.getAsShortText(locale);
		else
			return dayOfWeek.getAsText(locale);
	}

	/**
	 * It returns the number of days between two Gregorian Calendar dates (e.g.
	 * cal1: 2005-08-15, cal2: 2005-09-14 => 30 days)
	 *
	 * @param startDate
	 *            the Calendar to calculate
	 * @param endDate
	 *            another Calendar to calculate
	 * @return days between cal1 and cal2
	 */
	public static int getDays(Calendar startDate, Calendar endDate) {
		long min = getMinutes(startDate, endDate);

		return (int) (min / (24 * 60));
	}

	/**
	 * Calculate number of days between startDate and endDate
	 *
	 * @param startDate
	 *            start date(yyyy-MM-dd)
	 * @param endDate
	 *            end date(yyyy-MM-dd)
	 * @return integer of days
	 */
	public static int getDays(String startDate, String endDate) {
		return getDays(startDate, endDate, DATE_HMS_PATTERN);
	}

	/**
	 * Calculate number of days between startDate and endDate
	 *
	 * @param startDate
	 *            start date
	 * @param endDate
	 *            end date
	 * @param datePattern
	 *            date pattern
	 * @return integer of days
	 */
	public static int getDays(String startDate, String endDate,
			String datePattern) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(datePattern);

		DateTime startDateTime = fmt.parseDateTime(startDate);
		DateTime endDateTime = fmt.parseDateTime(endDate);

		long startMillis = startDateTime.getMillis();
		long endMillis = endDateTime.getMillis();

		int startDay = (int) (startMillis / (60 * 60 * 1000 * 24));
		int endDay = (int) (endMillis / (60 * 60 * 1000 * 24));

		return endDay - startDay;
	}

	/**
	 * Compares two Dates for ordering.
	 *
	 * @param date1
	 *            Date to be compared.
	 * @param date2
	 *            another Date String to be compared. (yyyyMMdd)
	 * @return the value true if the date1 is equal to date2 or date1 is after
	 *         the date2. the value false if the date1 is before the date2.
	 */
	public static boolean greaterThan(Date date1, String date2) {
		return greaterThan(date1, date2, DATE_PATTERN);
	}

	/**
	 * Compares two Dates for ordering.
	 *
	 * @param date1
	 *            Date to be compared.
	 * @param date2
	 *            another Date String to be compared. (The pattern equals
	 *            date2pattern that input argument)
	 * @param date2pattern
	 *            String date format
	 * @return the value <code>true</code> if date1 is after the date2. the
	 *         value <code>false</code> if the date1 is equal to date2 or the
	 *         date1 is before the date2.
	 */
	public static boolean greaterThan(Date date1, String date2,
			String date2pattern) {
		Date date = stringToDate(date2, date2pattern);
		return greaterThan(date1, date);
	}

	/**
	 * Compares two Dates for ordering.
	 *
	 * @param date1
	 *            Date to be compared.
	 * @param date2
	 *            another Date to be compared.
	 * @return the value <code>true</code> if or date1 is after the date2. the
	 *         value <code>false</code> if the date1 is equal to date2 the date1
	 *         is before the date2.
	 */
	public static boolean greaterThan(Date date1, Date date2) {
		if (date1.getTime() > date2.getTime()) {
			return true;
		}
		return false;
	}

	/**
	 * Compares two Timestamps for ordering.
	 *
	 * @param timestamp1
	 *            Date to be compared.
	 * @param timestamp2
	 *            another Date to be compared.
	 * @return the value <code>true</code> if or timestamp1 is after the
	 *         timestamp2. the value <code>false</code> if the date1 is equal to
	 *         date2 the date1 is before the date2.
	 */
	public static boolean greaterThan(Timestamp timestamp1, Timestamp timestamp2) {
		if (timestamp1.getTime() > timestamp2.getTime()) {
			return true;
		}
		return false;
	}

	/**
	 * Compares two Timestamps for ordering.
	 *
	 * @param timestamp1
	 *            Timestamp to be compared.
	 * @param timestamp2
	 *            another Timestamp String to be compared. (yyyy-MM-dd
	 *            HH:mm:ss.SSS)
	 * @return the value true if the timestamp1 is equal to timestamp2 or
	 *         timestamp1 is after the timestamp2. the value false if the
	 *         timestamp1 is before the timestamp2.
	 */
	public static boolean greaterThan(Timestamp timestamp1, String timestamp2) {
		return greaterThan(timestamp1, timestamp2, TIMESTAMP_PATTERN);
	}

	/**
	 * Compares two Timestamps for ordering.
	 *
	 * @param timestamp1
	 *            Timestamp to be compared.
	 * @param timestamp2
	 *            another Timestamp String to be compared. (The pattern equals
	 *            timestamp2pattern that input argument)
	 * @param timestamp2pattern
	 *            Timestamp pattern of timestamp2
	 * @return the value <code>true</code> if date1 is after the date2. the
	 *         value <code>false</code> if the date1 is equal to date2 or the
	 *         date1 is before the date2.
	 */
	public static boolean greaterThan(Timestamp timestamp1, String timestamp2,
			String timestamp2pattern) {
		Timestamp date = stringToTimestamp(timestamp2, timestamp2pattern);
		return greaterThan(timestamp1, date);
	}

	/**
	 * getting end date intervalDays <= 40
	 *
	 * @param startDate
	 *            start day (yyyy-MM-dd)
	 * @param intervalDays
	 *            interval days
	 * @return day after interval days from start day (yyyy-MM-dd)
	 */
	public static String getEndDate(String startDate, int intervalDays) {
		StringTokenizer st = new StringTokenizer(startDate, "-");
		int year = 0;
		int mon = 0;
		int day = 0;
		for (int i = 0; st.hasMoreTokens(); i++) {
			if (i == 0) {
				year = Integer.parseInt(st.nextToken());
			}
			if (i == 1) {
				String sMon = st.nextToken();
				if (sMon.startsWith("0")) {
					sMon = sMon.substring(1);
				}

				mon = Integer.parseInt(sMon);
			}
			if (i == 2) {
				String sDay = st.nextToken();
				if (sDay.startsWith("0")) {
					sDay = sDay.substring(1);
				}
				day = Integer.parseInt(sDay);
			}
		}
		DateTime start = new DateTime(year, mon, day, 0, 0, 0, 0);

		Period p1 = new Period(20 * 86400000);
		Period p2 = new Period((intervalDays - 20) * 86400000);

		DateTime end = start.plus(p1);
		end = end.plus(p2);
		year = end.getYear();
		mon = end.getMonthOfYear();
		day = end.getDayOfMonth();
		String xMon = "";
		String xDay = "";
		if (mon < 10) {
			xMon = "0" + (new Integer(mon)).toString();
		} else {
			xMon = (new Integer(mon)).toString();
		}
		if (day < 10) {
			xDay = "0" + (new Integer(day)).toString();
		} else {
			xDay = (new Integer(day)).toString();
		}
		String endDay = (new Integer(year)).toString() + "-" + xMon + "-"
				+ xDay;
		return endDay;
	}

	/**
	 * return the date adding days to the input date. negative date will be
	 * returned by calculating the previous days of the input date.
	 *
	 * @param date
	 *            string of the date (yyyyMMdd)
	 * @param days
	 *            the amount of days to add, may be negative
	 * @return String calculated date
	 */
	public static String addDays(String date, int days) {
		if (days == 0) {
			return date;
		}
		DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
		DateTime dt = fmt.parseDateTime(date);
		DateTime subtracted = dt.withFieldAdded(DurationFieldType.days(), days);
		return fmt.print(subtracted);
	}

	/**
	 * return the date adding months to the input date. negative month will be
	 * returned by calculating the previous days of the input date.
	 *
	 * @param date
	 *            string of the date (yyyyMMdd)
	 * @param months
	 *            the amount of months to add, may be negative
	 * @return String calculated date
	 */
	public static String addMonths(String date, int months) {
		if (months == 0) {
			return date;
		}
		DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
		DateTime dt = fmt.parseDateTime(date);
		DateTime subtracted = dt.withFieldAdded(DurationFieldType.months(),
				months);
		return fmt.print(subtracted);
	}

	/**
	 * return the date adding years to the input date. negative year will be
	 * returned by calculating the previous days of the input date.
	 *
	 * @param date
	 *            string of the date (yyyyMMdd)
	 * @param years
	 *            the amount of years to add, may be negative
	 * @return String calculated date
	 */
	public static String addYears(String date, int years) {
		if (years == 0) {
			return date;
		}
		DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
		DateTime dt = fmt.parseDateTime(date);
		DateTime subtracted = dt.withFieldAdded(DurationFieldType.years(),
				years);
		return fmt.print(subtracted);
	}

	/**
	 * return date calculating years, months, days to the input date
	 *
	 * @param date
	 *            string of the date (yyyyMMdd)
	 * @param years
	 *            the amount of years to add, may be negative
	 * @param months
	 *            the amount of months to add, may be negative
	 * @param days
	 *            the amount of days to add, may be negative
	 * @return String calculated date
	 */
	public static String addYearMonthDay(String date, int years, int months,
			int days) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
		DateTime dt = fmt.parseDateTime(date);

		if (years != 0)
			dt = dt.withFieldAdded(DurationFieldType.years(), years);
		if (months != 0)
			dt = dt.withFieldAdded(DurationFieldType.months(), months);
		if (days != 0)
			dt = dt.withFieldAdded(DurationFieldType.days(), days);

		return fmt.print(dt);
	}



	/**
	 * get the first date of the month based on the input date.
	 *
	 * @param date
	 *            string of the date (yyyyMMdd)
	 * @return the new date of the first date of the month
	 */
	public static String getFirstDateOfMonth(String date) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
		DateTime dt = fmt.parseDateTime(date);
		DateTime dtRet = new DateTime(dt.getYear(), dt.getMonthOfYear(), 1, 0,
				0, 0, 0);
		return fmt.print(dtRet);
	}



	/**
	 * get the last date of the month based on the input date.
	 *
	 * @param date
	 *            string of the date (yyyyMMdd)
	 * @return the new date of the last date of the month
	 */
	public static String getLastDateOfMonth(String date) {
		String firstDateOfMonth = getFirstDateOfMonth(date);

		DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
		DateTime dt = fmt.parseDateTime(firstDateOfMonth);
		dt = dt.plusMonths(1).minusDays(1);
		return fmt.print(dt);
	}



	/**
	 * get the first day of the previous month based on the input date.
	 *
	 * @param date
	 *            string of the date (yyyyMMdd)
	 * @return the new date of the first date of the previous month
	 */
	public static String getFirstDateOfPrevMonth(String date) {
		String firstDateOfMonth = getFirstDateOfMonth(date);

		DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
		DateTime dt = fmt.parseDateTime(firstDateOfMonth);
		dt = dt.minusMonths(1);
		return fmt.print(dt);
	}


	/**
	 * get the first day of the previous month based on the input date.
	 *
	 * @param date
	 *            string of the date (yyyyMMdd)
	 *            int month
	 * @return the new date of the first date of the previous month
	 */
	public static String getFirstDateOfPrevMonth(String date, int MinusMonth) {
		String firstDateOfMonth = getFirstDateOfMonth(date);

		DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
		DateTime dt = fmt.parseDateTime(firstDateOfMonth);
		dt = dt.minusMonths(MinusMonth);
		return fmt.print(dt);
	}

	/**
	 * get the last day of the previous month based on the input date.
	 *
	 * @param date
	 *            string of the date (yyyyMMdd)
	 * @return the new date of the last date of the previous month
	 */
	public static String getLastDateOfPrevMonth(String date) {
		String firstDateOfMonth = getFirstDateOfMonth(date);

		DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
		DateTime dt = fmt.parseDateTime(firstDateOfMonth);
		dt = dt.minusDays(1);
		return fmt.print(dt);
	}

	/**
	 * check whether the input date is valid.
	 *
	 * @param date
	 *            string of the date (yyyyMMdd)
	 *
	 * @return if valid date, return <code>true</code>.
	 */
	public static boolean isDate(String date) {
		return isDate(date, DATE_PATTERN);
	}

	/**
	 * check whether the input date is valid date.
	 *
	 * @param date
	 *            string of the date
	 * @param pattern
	 *            date pattern
	 * @return return <code>true</code>if valid date and <code>false</code> if
	 *         not.
	 */
	public static boolean isDate(String date, String pattern) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
		DateTime dt = new DateTime();
		dt = fmt.parseDateTime(date);

		if (!fmt.print(dt).equals(date)) {
			return false;
		}
		return true;
	}

	/**
	 * check whether the input time is valid time.
	 *
	 * @param date
	 *            string of the time (HH:mm)
	 * @return return <code>true</code>if valid time and <code>false</code> if
	 *         not.
	 */
	public static boolean isTime(String date) {
		return isTime(date, TIME_PATTERN);
	}

	/**
	 * check whether the input hour is valid time.
	 *
	 * @param date
	 *            string of the time
	 * @param pattern
	 *            time pattern
	 * @return return <code>true</code>if valid time and <code>false</code> if
	 *         not.
	 */
	public static boolean isTime(String date, String pattern) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
		DateTime dt = new DateTime();
		dt = fmt.parseDateTime(date);

		if (!fmt.print(dt).equals(date)) {
			return false;
		}
		return true;
	}

	/**
	 * convert String to java.util.Date
	 *
	 * @param str
	 *            the String Date to be converted (yyyyMMdd)
	 * @return <code>java.util.Date</code>
	 * @deprecated Use {@link #stringToDate(String)}
	 */
	@Deprecated
	public static Date string2Date(String str) {
		return string2Date(str, DATE_PATTERN);
	}

	/**
	 * convert String to java.util.Date
	 *
	 * @param date
	 *            the String Date to be converted (yyyyMMdd)
	 * @return <code>java.util.Date</code>
	 */
	public static Date stringToDate(String date) {
		return stringToDate(date, DATE_PATTERN);
	}

	/**
	 * convert String to <code>java.util.Date</code>
	 *
	 * @param str
	 *            the String Date to be converted (The pattern equals pattern
	 *            that input argument)
	 * @param pattern
	 *            converted date pattern
	 * @return <code>java.util.Date</code>
	 * @deprecated Use {@link #stringToDate(String, String)}
	 */
	@Deprecated
	public static Date string2Date(String str, String pattern) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
		return fmt.parseDateTime(str).toDate();
	}

	/**
	 * convert String to <code>java.util.Date</code>
	 *
	 * @param date
	 *            the String Date to be converted (The pattern equals pattern
	 *            that input argument)
	 * @param pattern
	 *            converted date pattern
	 * @return <code>java.util.Date</code>
	 */
	public static Date stringToDate(String date, String pattern) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
		return fmt.parseDateTime(date).toDate();
	}

	/**
	 * convert <code>Date</code> to <code>String</code>
	 *
	 * @param Date
	 *            date
	 * @return result String (yyyyMMdd)
	 * @deprecated Use {@link #dateToString(Date)}
	 */
	@Deprecated
	public static String date2String(Date date) {
		return date2String(date, DATE_PATTERN);
	}

	/**
	 * convert <code>Date</code> to <code>String</code>
	 *
	 * @param date
	 *            date
	 * @param pattern
	 *            date pattern
	 * @return result String
	 * @deprecated Use {@link #dateToString(Date, String)}
	 */
	@Deprecated
	public static String date2String(Date date, String pattern) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
		return fmt.print(date.getTime());
	}

	/**
	 * convert <code>Date</code> to <code>String</code>
	 *
	 * @param date
	 *            date
	 * @return result String (yyyyMMdd)
	 */
	public static String dateToString(Date date) {
		return dateToString(date, DATE_PATTERN);
	}

	/**
	 * convert <code>Date</code> to <code>String</code>
	 *
	 * @param date
	 *            date
	 * @param pattern
	 *            date pattern
	 * @return result String
	 */
	public static String dateToString(Date date, String pattern) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
		return fmt.print(date.getTime());
	}

	/**
	 * convert and return the date of string type of the given pattern to
	 * user-defined pattern
	 *
	 * <pre>
	 * DateUtil.string2String("20101214", "yyyyMMdd", "yyyy-MM-dd") = "2010-12-14"
	 * DateUtil.string2String("2010.12.14", "yyyy.MM.dd", "yyyy/MM/dd") = "2010/12/14"
	 * </pre>
	 *
	 * @param str
	 *            string
	 * @param basePattern
	 *            original date format
	 * @param wantedPattern
	 *            converted date format
	 * @deprecated Use {@link #convertDateFormat(String, String, String)}
	 */
	@Deprecated
	public static String string2String(String str, String basePattern,
			String wantedPattern) {
		DateTimeFormatter basefmt = DateTimeFormat.forPattern(basePattern);
		DateTimeFormatter wantedfmt = DateTimeFormat.forPattern(wantedPattern);
		DateTime dt = basefmt.parseDateTime(str);
		return wantedfmt.print(dt);
	}

	/**
	 * convert and return the date of string type of the given pattern to
	 * user-defined pattern
	 *
	 * <pre>
	 * DateUtil.convertDateFormat("20101214", "yyyyMMdd", "yyyy-MM-dd") = "2010-12-14"
	 * DateUtil.convertDateFormat("2010.12.14", "yyyy.MM.dd", "yyyy/MM/dd") = "2010/12/14"
	 * </pre>
	 *
	 * @param date
	 *            string
	 * @param basePattern
	 *            original date format
	 * @param wantedPattern
	 *            converted date format
	 */
	public static String convertDateFormat(String date, String basePattern,
			String wantedPattern) {
		DateTimeFormatter basefmt = DateTimeFormat.forPattern(basePattern);
		DateTimeFormatter wantedfmt = DateTimeFormat.forPattern(wantedPattern);
		DateTime dt = basefmt.parseDateTime(date);
		return wantedfmt.print(dt);
	}

	/**
	 * convert String to <code>java.sql.Date</code> type
	 *
	 * @param str
	 *            the String Date to be converted (yyyyMMdd)
	 * @return <code>java.sql.Date</code>
	 * @throws <code>Exception<code> fail to convert string to SQLDate
	 * @deprecated Use {@link #stringToSQLDate(String)}
	 */
	@Deprecated
	public static java.sql.Date string2SQLDate(String str) {
		return string2SQLDate(str, DATE_PATTERN);
	}

	/**
	 * convert String to <code>java.sql.Date</code> type
	 *
	 * @param date
	 *            the String Date to be converted (yyyyMMdd)
	 * @return <code>java.sql.Date</code>
	 * @throws <code>Exception<code> fail to convert string to SQLDate
	 */
	public static java.sql.Date stringToSQLDate(String date) {
		return stringToSQLDate(date, DATE_PATTERN);
	}

	/**
	 * convert String to <code>java.sql.Date</code> type
	 *
	 * @param str
	 *            the String Date to be converted
	 * @param pattern
	 *            date pattern to be converted.
	 * @return <code>java.sql.Date</code>
	 * @deprecated Use {@link #stringToSQLDate(String, String)}
	 */
	@Deprecated
	public static java.sql.Date string2SQLDate(String str, String pattern) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
		return new java.sql.Date(fmt.parseDateTime(str).getMillis());
	}

	/**
	 * convert String to <code>java.sql.Date</code> type
	 *
	 * @param date
	 *            the String Date to be converted
	 * @param pattern
	 *            date pattern to be converted.
	 * @return <code>java.sql.Date</code>
	 */
	public static java.sql.Date stringToSQLDate(String date, String pattern) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
		return new java.sql.Date(fmt.parseDateTime(date).getMillis());
	}

	/**
	 * convert String to <code>java.sq.Timestamp</code>
	 *
	 * @param str
	 *            the String Date to be converted (yyyyMMdd)
	 * @return <code>java.sql.Timestamp</code>
	 * @deprecated Use {@link #stringToTimestamp(String)}
	 */
	@Deprecated
	public static Timestamp string2Timestamp(String str) {
		return string2Timestamp(str, DATE_PATTERN);
	}

	/**
	 * convert String to <code>java.sq.Timestamp</code>
	 *
	 * @param date
	 *            the String Date to be converted (yyyyMMdd)
	 * @return <code>java.sql.Timestamp</code>
	 */
	public static Timestamp stringToTimestamp(String date) {
		return stringToTimestamp(date, DATE_PATTERN);
	}

	/**
	 * convert String to <code>java.sq.Timestamp</code>
	 *
	 * @param str
	 *            the String Date to be converted (The pattern equals pattern
	 *            that input argument)
	 * @param pattern
	 *            converted date format
	 * @return <code>java.sql.Timestamp</code>
	 * @deprecated Use {@link #stringToTimestamp(String, String)}
	 */
	@Deprecated
	public static Timestamp string2Timestamp(String str, String pattern) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
		return new Timestamp(fmt.parseDateTime(str).getMillis());
	}

	/**
	 * convert String to <code>java.sq.Timestamp</code>
	 *
	 * @param date
	 *            the String Date to be converted (The pattern equals pattern
	 *            that input argument)
	 * @param pattern
	 *            converted date format
	 * @return <code>java.sql.Timestamp</code>
	 */
	public static Timestamp stringToTimestamp(String date, String pattern) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
		return new Timestamp(fmt.parseDateTime(date).getMillis());
	}

	/**
	 * convert <code>java.sq.Timestamp</code> to <code>String</code> type
	 *
	 * @param date
	 *            the Date to be converted
	 * @return a string representing the date (yyyyMMdd)
	 * @deprecated Use {@link #timestamp2String(Timestamp)}
	 */
	@Deprecated
	public static String timestamp2String(Timestamp date) {
		return timestamp2String(date, DATE_PATTERN);
	}

	/**
	 * convert <code>java.sq.Timestamp</code> to <code>String</code> type
	 *
	 * @param date
	 *            the Date to be converted
	 * @return a string representing the date (yyyyMMdd)
	 */
	public static String timestampToString(Timestamp date) {
		return timestampToString(date, DATE_PATTERN);
	}

	/**
	 * convert <code>java.sq.Timestamp</code> to <code>String</code> type
	 *
	 * @param date
	 *            the Date to be converted (The pattern equals pattern that
	 *            input argument)
	 * @param pattern
	 *            Date pattern
	 * @return a string representing the date
	 * @deprecated Use {@link #timestamp2String(Timestamp, String)}
	 */
	@Deprecated
	public static String timestamp2String(Timestamp date, String pattern) {
		if (date == null) {
			return "";
		}
		return date2String(date, pattern);
	}

	/**
	 * convert <code>java.sq.Timestamp</code> to <code>String</code> type
	 *
	 * @param date
	 *            the Date to be converted (The pattern equals pattern that
	 *            input argument)
	 * @param pattern
	 *            Date pattern
	 * @return a string representing the date
	 */
	public static String timestampToString(Timestamp date, String pattern) {
		if (date == null) {
			return "";
		}
		return dateToString(date, pattern);
	}

	/**
	 * convert String to <code>java.util.Calendar</code>
	 *
	 * @param str
	 *            the String Date to be converted (yyyyMMddHHmmss)
	 * @return <code>java.util.Calendar</code>
	 * @deprecated Use {@link #stringToCalendar(String)}
	 */
	@Deprecated
	public static Calendar string2Calender(String str) {
		if ((str == null) || (str.length() < 14))
			return null;

		String year = str.substring(0, 4);
		String month = str.substring(4, 6);
		String day = str.substring(6, 8);
		String hour = str.substring(8, 10);
		String minute = str.substring(10, 12);
		String second = str.substring(12, 14);

		return (new GregorianCalendar(StringUtil.string2integer(year),
				StringUtil.string2integer(month) - 1, StringUtil
						.string2integer(day), StringUtil.string2integer(hour),
				StringUtil.string2integer(minute), StringUtil
						.string2integer(second)));
	}

	/**
	 * convert String to <code>java.util.Calendar</code>
	 *
	 * @param date
	 *            the String Date to be converted (yyyyMMddHHmmss)
	 * @return <code>java.util.Calendar</code>
	 */
	public static Calendar stringToCalendar(String date) {
		if ((date == null) || (date.length() < 14))
			return null;

		String year = date.substring(0, 4);
		String month = date.substring(4, 6);
		String day = date.substring(6, 8);
		String hour = date.substring(8, 10);
		String minute = date.substring(10, 12);
		String second = date.substring(12, 14);

		return (new GregorianCalendar(NumberUtil.stringToInt(year), NumberUtil
				.stringToInt(month) - 1, NumberUtil.stringToInt(day),
				NumberUtil.stringToInt(hour), NumberUtil.stringToInt(minute),
				NumberUtil.stringToInt(second)));
	}

	/**
	 * convert <code>java.util.Calendar</code> to String
	 *
	 * @param calendar
	 *            the Calendar to be converted
	 * @return a string representing the date (yyyyMMddHHmmss)
	 * @deprecated Use {@link #calendarToString(Calendar)}
	 */
	@Deprecated
	public static String calendar2String(Calendar calendar) {
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1; // Calendar.MONTH ia
		// zero-based (Jan -> 0)
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);

		return (StringUtil.integer2string(year)
				+ StringUtil.integer2string(month)
				+ StringUtil.integer2string(day)
				+ StringUtil.integer2string(hour)
				+ StringUtil.integer2string(minute)
				+ StringUtil.integer2string(second) + "000");
	}

	/**
	 * convert <code>java.util.Calendar</code> to String
	 *
	 * @param calendar
	 *            the Calendar to be converted
	 * @return a string representing the date (yyyyMMddHHmmss)
	 */
	public static String calendarToString(Calendar calendar) {
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1; // Calendar.MONTH ia
		// zero-based (Jan -> 0)
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);

		return (NumberUtil.intToString(year) + NumberUtil.intToString(month)
				+ NumberUtil.intToString(day) + NumberUtil.intToString(hour)
				+ NumberUtil.intToString(minute)
				+ NumberUtil.intToString(second) + "000");
	}

	/**
	 * It returns the number of minutes between two Gregorian Calendar dates
	 *
	 * @param startDate
	 *            the Calendar to calculate
	 * @param endDate
	 *            another Calendar to calculate
	 * @return minutes between cal1 and cal2
	 */
	public static int getMinutes(Calendar startDate, Calendar endDate) {
		int returnValue = 0;
		if(startDate == null || endDate == null) {
			returnValue = 0;
		}
		else {
			long utc1 = startDate.getTimeInMillis();
			long utc2 = endDate.getTimeInMillis();

			long result = (utc2 - utc1) / (60 * 1000);

			returnValue = (int) result;
		}
		return returnValue;
	}

	/**
	 * It returns the number of minutes between two string type dates.
	 *
	 * @param startDate
	 *            the String Date to calculate (yyyyMMddHHmmss)
	 * @param endDate
	 *            another String Date to calculate (yyyyMMddHHmmss)
	 * @return minutes between date1 and date2
	 */
	public static int getMinutes(String startDate, String endDate) {
		Calendar cal1 = stringToCalendar(startDate);
		Calendar cal2 = stringToCalendar(endDate);

		return getMinutes(cal1, cal2);
	}

	/**
	 * get yesterday
	 *
	 * @return String representing yesterday (yyyyMMdd)
	 */
	public static String getYesterday() {
		return getYesterday(DATE_PATTERN);
	}

	/**
	 * get yesterday with pattern
	 *
	 * @param pattern
	 *            Date pattern
	 * @return String representing yesterday
	 */
	public static String getYesterday(String pattern) {
		Calendar cal = getCalendar();
		cal.roll(Calendar.DATE, -1);
		Date date = cal.getTime();
		return dateToString(date, pattern);
	}

	/**
	 * get current calendar of korea time zone
	 *
	 * @return <code>java.util.Calendar</code>
	 */
	private static Calendar getCalendar() {
		Calendar calendar = new GregorianCalendar(TimeZone
				.getTimeZone("GMT+09:00"), Locale.KOREA);
		calendar.setTime(new Date());

		return calendar;
	}

	/**
	 * find dates between startDay and endDay
	 *
	 * @param startDate
	 *            start day (yyyyMMdd)
	 * @param endDate
	 *            end day (yyyyMMdd)
	 * @return String array of dates between startDay and endDay
	 */
	public static String[] getDates(String startDate, String endDate) {
		return getDates(startDate, endDate, DATE_PATTERN);
	}

	/**
	 * find dates between startDay and endDay
	 *
	 * @param startDate
	 *            start day
	 * @param endDate
	 *            end day
	 * @param pattern
	 *            date pattern
	 * @return String array of dates between startDay and endDay
	 */
	public static String[] getDates(String startDate, String endDate,
			String pattern) {
		List<String> result = new ArrayList<String>();
		result.add(startDate);

		Calendar cal = getCalendar();
		cal.setTime(stringToDate(startDate, pattern));
		String nextDay = dateToString(cal.getTime(), pattern);

		while (!nextDay.equals(endDate)) {
			cal.add(Calendar.DATE, 1);
			nextDay = dateToString(cal.getTime(), pattern);
			result.add(nextDay);
		}
		return result.toArray(new String[0]);
	}

	/**
	 * get current date
	 * <p/>
	 * ex) String curDate = DateUtil.getCurrentDateString(); // curDate :
	 * 2009-04-28
	 *
	 * @return String representing current time (yyyyMMdd)
	 * @deprecated Use {@link #getCurrentDate()}
	 */
	@Deprecated
	public static String getCurrentDateString() {
		return getCurrentDateString(DATE_PATTERN);
	}

	/**
	 * get current date
	 * <p/>
	 * ex) String curDate = DateUtil.getCurrentDateString(); // curDate :
	 * 2009-04-28
	 *
	 * @return String representing current time (yyyyMMdd)
	 */
	public static String getCurrentDate() {
		return getCurrentDate(DATE_PATTERN);
	}

	/**
	 * get current date with pattern
	 * <p/>
	 * ex) String curDate = DateUtil.getCurrentDateString("yyyyMMdd"); //
	 * curDate : 20090428
	 *
	 * @param pattern
	 *            date pattern
	 * @return String representing current date (type of pattern)
	 * @deprecated Use {@link #getCurrentDate(String)}
	 */
	@Deprecated
	public static String getCurrentDateString(String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(new Date());
	}

	/**
	 * get current date with pattern
	 * <p/>
	 * ex) String curDate = DateUtil.getCurrentDateString("yyyyMMdd"); //
	 * curDate : 20090428
	 *
	 * @param pattern
	 *            date pattern
	 * @return String representing current date (type of pattern)
	 */
	public static String getCurrentDate(String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(new Date());
	}

	/**
	 * get {@link java.sql.Date} object of current date
	 *
	 * @return {@link java.sql.Date} object (current date)
	 */
	public static java.sql.Date getCurrentSQLDate() {
		return new java.sql.Date((new java.util.Date()).getTime());
	}

	/**
	 * get {@link java.sql.Time} object of current time
	 *
	 * @return {@link java.sql.Time} object (current time)
	 */
	public static Time getCurrentSQLTime() {
		return new Time(new Date().getTime());
	}

	/**
	 * get current time
	 *
	 * @return String (current time)
	 * @deprecated Use {@link #getCurrentTime()}
	 */
	@Deprecated
	public static String getCurrentTimeString() {
		return new Time(new Date().getTime()).toString();
	}

	/**
	 * get current time
	 *
	 * @return String (current time)
	 */
	public static String getCurrentTime() {
		return new Time(new Date().getTime()).toString();
	}

	/**
	 * get {@link java.sql.Timestamp} object of current date
	 *
	 * @return {@link java.sql.Timestamp} object (current date)
	 */
	public static Timestamp getCurrentSQLTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * get current timestamp
	 *
	 * @return String of current timestamp
	 * @deprecated Use {@link #getCurrentTimestamp()}
	 */
	@Deprecated
	public static String getCurrentTimestampString() {
		return getCurrentSQLTimestamp().toString();
	}

	/**
	 * get the current timestamp
	 *
	 * @return String of current timestamp;
	 */
	public static String getCurrentTimestamp() {
		return getCurrentSQLTimestamp().toString();
	}

	/**
	 * replace the year part of date into the specific year
	 *
	 * @param date
	 *            {@link java.util.Date} object to be changed
	 * @param year
	 *            year to replace (Integer)
	 * @return {@link java.util.Date} object that changed
	 */
	public static Date replaceYear(Date date, int year) {
		Assert.notNull(date);
		Assert.isTrue(("" + year).length() <= 4, year
				+ " must be less than 4-digit number.");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.YEAR, year);

		return calendar.getTime();
	}

	/**
	 * combine with parts of the specific date into {@link java.util.Date}
	 * object
	 *
	 * @param year
	 *            year part
	 * @param month
	 *            month part
	 * @param day
	 *            day part
	 * @param hourOfDay
	 *            hour part
	 * @param minute
	 *            minute part
	 * @param second
	 *            second part
	 * @return {@link java.util.Date} object of the specific date
	 */
	public static Date getDate(int year, int month, int day, int hourOfDay,
			int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day, hourOfDay, minute, second);

		return cal.getTime();
	}

	/**
	 * retrieve last day of month with specific date string(yyyy-MM-dd)
	 * <p/>
	 * ex) int actual = DateUtil.getLastDateOfMonthInt("2008-02-22"); // actual
	 * : 29
	 *
	 * @param date
	 *            date ( yyyy-MM-dd )
	 * @return last day of month (int)
	 */
	public static int getLastDayOfMonth(String date) {
		String examYmd = StringUtil.left(date.trim(), 8) + "01";
		// check if input date is 'yyyy-MM-dd' pattern
		Assert.isTrue(isDate(examYmd), date
				+ "must be in 'yyyy-MM-dd' pattern.");
		// get last day of the month
		return getLastDayOfMonthInt(examYmd);
	}

	/**
	 * check if the specific date is the last day of the month
	 * <p/>
	 * ex) boolean actual = DateUtil.isLastDateOfMonth("2008-02-28"); // Leap
	 * year, false <br/>
	 * boolean actual = DateUtil.isLastDateOfMonth("2008-02-29"); // Leap year,
	 * true
	 *
	 * @param date
	 *            date string(yyyy-MM-dd)
	 * @return true if is the last day of the month
	 */
	public static boolean isLastDateOfMonth(String date) {
		String examYmd = StringUtil.left(date.trim(), 8) + "01";
		int inputDay = Integer.parseInt(StringUtil.right(date.trim(), 2));

		int lastDay = getLastDayOfMonthInt(examYmd);

		if (inputDay == lastDay) {
			return true;
		} else {
			return false;
		}
	}

	private static int getLastDayOfMonthInt(String date) {
		Assert.notNull(date);
		Assert.isTrue(isDate(date), date + " must be in 'yyyy-MM-dd' pattern");
		int month = Integer.parseInt(date.substring(5, 7));
		int lastDayOfMonthValue = 0;
		if (isLeapYear(date)) {
			lastDayOfMonthValue = lastDayOfMonthForLeapYear[month - 1];
		} else {
			lastDayOfMonthValue = lastDayOfMonth[month - 1];
		}
		return lastDayOfMonthValue;

	}

	/**
	 * check if the date is a leap year
	 *
	 * @param date
	 *            date string(yyyy-MM-dd)
	 * @return true if it is a leap year
	 */
	public static boolean isLeapYear(String date) {
		Assert.hasLength(date);
		Assert.isTrue(isDate(date), date + " must be in 'yyyy-MM-dd' pattern");
		return isLeapYear(Integer.parseInt(date.substring(0, 4)));
	}

	/**
	 * check if the specific year is a leap year
	 *
	 * @param year
	 *            specific year (int)
	 * @return true if it is a leap year
	 */
	public static boolean isLeapYear(int year) {
		Assert.isTrue(year > 0, year + " must be positive.");
		return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) ? true
				: false;
	}

	 /**
     * Method Desc : 사용자포멧 변환
     * @author EKW <kwjojo@crizen.com>
     * @since 2010. 9. 28.
     * @param frForm
     * @param toForm
     * @param thisDate
     * @return
     * @throws Exception
     */
    public static String getFormattedTime(String frForm, String toForm, String thisDate) throws Exception {
   	 if(thisDate != null && !"".equals(thisDate) && frForm.length() == thisDate.length()) {
   		 SimpleDateFormat frDateFormat = new SimpleDateFormat(frForm);
   		 SimpleDateFormat toDateFormat = new SimpleDateFormat(toForm);
   		 Date d = frDateFormat.parse(thisDate);
   		 return toDateFormat.format(d);
   	 } else {
   		 return "";
   	 }
    }

	public static String formatDate(String date, String format) {
		String sFormat = format;
		String sDate = date;
		String returnValue = "";

    	if ( "".equals(StringUtil.nullToString(sFormat)) ) {
    		sFormat = DATE_PATTERN_DASH;
    	}

    	sDate = StringUtil.nullToString(sDate);

    	if ( sDate == null || sDate.equals("") ) {
    		returnValue = "";
    	}
    	else if ( sDate.equals( "00000000" ) ) {
    		returnValue = "";
    	}
    	else if ( sDate.length() < 8 ) {
    		returnValue = date;
    	}
    	else {
	    	SimpleDateFormat formatter = new SimpleDateFormat(sFormat);

	    	String formatString = formatter.format(chkDate(sDate, DATE_PATTERN));
	    	if (formatString == null || formatString == "") {
				formatString = sDate;
			}

	    	returnValue = formatString;
    	}

    	return returnValue;
    }

	public static String formatDate(String date) {
		String returnValue = "";
		if(date != null && date != "") {
			formatDate(date, DATE_PATTERN_DASH);
		}
		return returnValue;
	}

	public static String formatTime(String time, String format) {
		String returnValue = "";
		String sTime = time;
		String sFormat = format;

		if ( "".equals(StringUtil.nullToString(sFormat)) ) {
			sFormat = TIME_HMS_PATTERN_COLONE;
		}

		sTime = StringUtil.nullToString(sTime);

		if ( sTime == null || sTime.equals("") ) {
			returnValue = "";
		}
		else if ( sTime.length() < 6 ) {
			returnValue = sTime;
		}
		else {
			DateTime dt = new DateTime(convertDate(getCurrentDate()+sTime,DATE_HMS_PATTERN));
			DateTimeFormatter fmt = DateTimeFormat.forPattern(sFormat);
			returnValue = fmt.print(dt);
		}
		return returnValue;
	}

	public static String formatTime(String time) {
		String returnValue = "";
		if(time != null && time != "") {
			returnValue = formatTime(time, TIME_HMS_PATTERN_COLONE);
		}
		return returnValue;
	}

	private static Date chkDate(String inputDate, String format) {
    	String _date = null;
    	_date = inputDate;

    	if (_date == null) return null;
    	else {
    		_date = StringUtil.deleteAny(inputDate, '-');
    		_date = StringUtil.deleteAny(_date, '/');
    		_date = StringUtil.deleteAny(_date, '.');
    		_date = StringUtil.deleteAny(_date, ':');
    	}

    	SimpleDateFormat formatter = new SimpleDateFormat(format, java.util.Locale.KOREA);
    	Date date = null;

    	try {
    		date = formatter.parse(_date);
    	}
    	catch ( ParseException e ) {
    		return null;
    	}

    	if ( !formatter.format(date).equals(_date) ) return null;

    	return date;
    }

	 /**
     * @param inputDate Date 스트링문자(yyyyMMdd) - 시(23), 분(59), 초(59)으로 세팅한 값
     * @return Date
     * Method Desc : Date 스트링문자를 Date로 변환하여 리턴한다.
     */
    public static Date convertDateTo(String inputDate) {
    	return convertDate(inputDate + "235959", "yyyyMMddHHmmss");
    }

    public static Date convertDateTo(String inputDate, String inputTime) {
    	return convertDate(inputDate + inputTime, "yyyyMMddHHmmss");
    }

	public static Date convertDate(String inputDate, String format) {
    	Date date = null;
    	String sInputDate = inputDate;

    	if ( sInputDate == null ) return null;
    	else {
    		sInputDate = StringUtil.deleteAny(sInputDate, "-");
    		sInputDate = StringUtil.deleteAny(sInputDate, "/");
    		sInputDate = StringUtil.deleteAny(sInputDate, ".");
    		sInputDate = StringUtil.deleteAny(sInputDate, ":");
    	}

    	try {
    		SimpleDateFormat formatter = new SimpleDateFormat(format, java.util.Locale.KOREA);
    		date = formatter.parse(sInputDate);
    	}
    	catch ( ParseException e ) {
//    		return null;
            LogUtil.error(logger,e);
            date = null;
    	}
    	return date;
    }

	 /**
 	 * @param hhmmss
 	 * @return
 	 * Method Desc : 시분초에 대한 포맷을 맞춰 리턴한다.(HH:mm:ss)
 	 */
     public static String formatHhmmss(String hhmmss) {
     	if ( hhmmss == null || "".equals(hhmmss) ) return "";

 		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
 		return formatter.format(convertDateTo(getCurrentDate() + hhmmss, "yyyyMMddHHmmss"));
     }

     /**
      * Method Desc : startDate 와 endDate 사이의 일수를 구한다.
      * @update:
      * @param startDate
      * @param endDate
      * @return
     * @throws ParseException
      */
     public static int getDiffDayCount(String startDate, String endDate) throws ParseException {
    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	 int returnValue = 0;

    	 if(startDate != null && endDate != null && startDate != "" && endDate != "") {
    		 returnValue = (int) ((sdf.parse(endDate).getTime() - sdf.parse(startDate).getTime()) / 1000 / 60 / 60 / 24);
    	 }

    	 return returnValue;
     }

     /**
      * Method Desc : start(yyyyMMddHHmmss) 와 end(yyyyMMddHHmmss) 사이의 분을 구한다. start 가 크면 -(마이너스) 리턴
      * @param start(yyyyMMddHHmmss)
      * @param end(yyyyMMddHHmmss)
      * @return
      */
     public static long getDiffMin(String start, String end) {
    	 long returnValue = 0;

    	 if(start == null || end == null) {
    		 returnValue = 0;
    	 }
    	 else if(start.length() != 14 || start.length() != 14) {
    		 returnValue = 0;
    	 }
    	 else {
        	 SimpleDateFormat sSdf = new SimpleDateFormat("yyyyMMddHHmmss");
        	 Date startday = sSdf.parse(start, new ParsePosition(0));
        	 long startTime = startday.getTime();

        	 SimpleDateFormat eSdf = new SimpleDateFormat("yyyyMMddHHmmss");
        	 Date endDate = eSdf.parse(end, new ParsePosition(0));
        	 long endTime = endDate.getTime();

        	 // 분으로 변환
        	 long min = (endTime - startTime) / 60000;

        	 returnValue = min;
    	 }
    	 return returnValue;
     }

	public static int getManAge(String bgn) {
		logger.debug(bgn);//bgn은 이미 1991011 4자리연도포함 성별까지 들어가있다
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String curDate = df.format(new Date());
//		logger.debug(curDate);
//		if(bgn.substring(0, 1).equals("0")){
//				bgn = "20" + bgn;
//		}else{
//			bgn = "19" + bgn;
//		}
//		logger.debug(bgn);
		int manAge = Integer.parseInt(curDate.substring(0, 4)) - Integer.parseInt(bgn.substring(0, 4));
		if(Integer.parseInt(curDate.substring(4, 8)) - Integer.parseInt(bgn.substring(4, 8)) < 0){
			manAge = manAge - 1;
		}
		return manAge;
	}

	public static int getCntMonthFromCurDate(String startDt){
		int returnValue = 0;
		String curDate = DateUtil.getCurrentDateTime("yyyyMMdd");

		if(startDt != null && startDt != "" && curDate != null && curDate != "") {
			returnValue = (NumberUtil.stringToInt(curDate.substring(0, 4)) * 12 + NumberUtil.stringToInt(curDate.substring(4, 6)))
					- (NumberUtil.stringToInt(startDt.substring(0, 4)) * 12 + NumberUtil.stringToInt(startDt.substring(4, 6)));
		}
		return returnValue;
	}

	public static int getCntMonth(String startDt, String curDate){
		int returnValue = 0;

		if(startDt != null && startDt != "" && curDate != null && curDate != "") {
			returnValue = (NumberUtil.stringToInt(curDate.substring(0, 4)) * 12 + NumberUtil.stringToInt(curDate.substring(4, 6)))
			- (NumberUtil.stringToInt(startDt.substring(0, 4)) * 12 + NumberUtil.stringToInt(startDt.substring(4, 6)));
		}

		return returnValue;
	}
	//현재 월 가져오기
	public static String getMonth(){
		Calendar now = Calendar.getInstance();
		int monthN  = now.get(Calendar.MONTH)+1;
		String MON  = (monthN<10)? "0"+Integer.toString(monthN):Integer.toString(monthN) ;

		return MON;
	}
	public static String getTime(){ //년-월-일 시:분:초
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		return  formatter.format(new java.util.Date());
	}
	public static String getLastMonthDate() {
		Calendar cal = new GregorianCalendar();

		String sDay = getTime().substring(0,8);
		String sMM = "1";

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date d = df.parse(sDay, new ParsePosition(0));

		cal.setTime(d);
		cal.add(Calendar.MONTH, -Integer.parseInt(sMM));

		return df.format(cal.getTime());
	}

	/**
	 * 시간을 더해 준다
	 * @param date 더해질 시간
	 * @param hour 더할 시간
	 * @return
	 */
	public static String addHours(String date, int hour) {
		if (hour == 0) {
			return date;
		}
		DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_HMS_PATTERN);
		LocalDateTime dt = fmt.parseLocalDateTime(date);
		LocalDateTime subtracted = dt.withFieldAdded(DurationFieldType.hours(), hour);
		return fmt.print(subtracted);
	}

	//6개월전 cntMonth = 6 ==> 6개월 후 = -6
		public static String getAddMonth(int cntMonth) {
			Calendar cal = new GregorianCalendar();

			String sDay = getTime().substring(0,10);
//			String sMM = "1";

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			Date d = df.parse(sDay, new ParsePosition(0));

			cal.setTime(d);
			cal.add(Calendar.MONTH, -(cntMonth));

			return df.format(cal.getTime());
		}

		public static String getCurrentYMD(){
			Date d =new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			return sdf.format(d);
		}

		public static String getCurrentHIS(){
			Date d =new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
			return sdf.format(d);
		}
}