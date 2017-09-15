package com.hmmes.utils;

import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

/**
 * <p>����������</p>
 * <p>�ṩ�й����ڵ�ʵ�÷�����</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: NineTowns</p>
 * @author Weiwenqi
 * @version 1.0
 *
 */

public class DateUtil
{
	static java.text.SimpleDateFormat sdfShort = new java.text.SimpleDateFormat("yyyyMMdd");
	static java.text.SimpleDateFormat sdfLong = new java.text.SimpleDateFormat("yyyy-MM-dd");
	static java.text.SimpleDateFormat sdfLongCn = new java.text.SimpleDateFormat("yyyy��MM��dd��");
	static java.text.SimpleDateFormat sdfShortU = new java.text.SimpleDateFormat("MMM dd",Locale.ENGLISH);
	static java.text.SimpleDateFormat sdfLongU = new java.text.SimpleDateFormat("MMM dd,yyyy",Locale.ENGLISH);
	static java.text.SimpleDateFormat sdfLongTime = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
	static java.text.SimpleDateFormat sdfLongTimePlus = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static java.text.SimpleDateFormat sdfShortLongTimePlusCn = new java.text.SimpleDateFormat("yyyy��MM��dd�� HH:mm");
	static java.text.SimpleDateFormat sdfLongTimePlusMill = new java.text.SimpleDateFormat("yyyyMMddHHmmssSSSS");
	static java.text.SimpleDateFormat sdfMd = new java.text.SimpleDateFormat("MM��dd��");
	private static long DAY_IN_MILLISECOND = 0x5265c00L;

	public DateUtil()
	{
	}

	/**
	 * @author Pablo
	 * Descrption:?????????getgetg get Date format Example��2008-05-15
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getDateLong(Date date)
	{
		String nowDate = "";
		try
		{	
			if(date != null)
				nowDate = sdfLong.format(date);
			return nowDate;
		}
		catch (Exception e)
		{
			System.out.println("Error at getDate:" + e.getMessage());
			return "";
		}
	}
	/**
	 * @author Pablo
	 * Descrption:?????????getgetg get Date format Example��2008��-05��-15��
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getDateLongCn(Date date)
	{
		String nowDate = "";
		try
		{	
			if(date != null)
				nowDate = sdfLongCn.format(date);
			return nowDate;
		}
		catch (Exception e)
		{
			System.out.println("Error at getDate:" + e.getMessage());
			return "";
		}
	}
	
	
	/**
	 * @author vowo
	 * Descrption:?????????getgetg get Date format Example��05��-15��
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getDateMD(Date date)
	{
		String nowDate = "";
		try
		{	
			if(date != null)
				nowDate = sdfMd.format(date);
			return nowDate;
		}
		catch (Exception e)
		{
			System.out.println("Error at getDate:" + e.getMessage());
			return "";
		}
	}
	
	/**
	 * @author Pablo
	 * Descrption:?????????getgetg get Date format Example��2008��-05��-15�� 11:05
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getDateShortLongTimeCn(Date date)
	{
		String nowDate = "";
		try
		{	
			if(date != null)
				nowDate = sdfShortLongTimePlusCn.format(date);
			return nowDate;
		}
		catch (Exception e)
		{
			System.out.println("Error at getDate:" + e.getMessage());
			return "";
		}
	}
	
	/**
	 * @author Pablo
	 * Descrption:?????????getgetg get Date format Example��Aug 28, 2007
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getDateUS(Date date)
	{
		String nowDate = "";
		try
		{	
			if(date != null)
				nowDate = sdfLongU.format(date);
			return nowDate;
		}
		catch (Exception e)
		{
			System.out.println("Error at getDate:" + e.getMessage());
			return "";
		}
	}
	
	/**
	 * @author Pablo
	 * Descrption:?????????getgetg get Date format Example��Aug 28, 2007
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getDateUSShort(Date date)
	{
		String nowDate = "";
		try
		{	
			if(date != null)
				nowDate = sdfShortU.format(date);
			return nowDate;
		}
		catch (Exception e)
		{
			System.out.println("Error at getDate:" + e.getMessage());
			return "";
		}
	}
	
	/**
	 * ��ת���������͵��ַ������ͣ�������Ϣ��ΪUK
	 * 
	 * @param 	date
	 * @param 	format
	 * @return String
	 */
	public static String getFomartDate(Date date, String format)
	{
		try
		{
			return new SimpleDateFormat(format, Locale.UK).format(date);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return (date == null) ? new Date().toString() : date.toString();
		}
	}

	/**
	 * Descrption:ȡ�õ�ǰ����ʱ��,��ʽΪ:YYYYMMDDHHMISS
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowLongTime() throws Exception
	{
		String nowTime = "";
		try
		{
			java.sql.Date date = null;
			date = new java.sql.Date(new java.util.Date().getTime());
			nowTime = sdfLongTime.format(date);
			return nowTime;
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	/**
	 * Descrption:ȡ�õ�ǰ����,��ʽΪ:YYYYMMDD
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowShortDate() throws Exception
	{
		String nowDate = "";
		try
		{
			java.sql.Date date = null;
			date = new java.sql.Date(new java.util.Date().getTime());
			nowDate = sdfShort.format(date);
			return nowDate;
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	/**
	 * Descrption:ȡ�õ�ǰ����,��ʽΪ:YYYY-MM-DD
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowFormateDate() throws Exception
	{
		String nowDate = "";
		try
		{
			java.sql.Date date = null;
			date = new java.sql.Date(new java.util.Date().getTime());
			nowDate = sdfLong.format(date);
			return nowDate;
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	/**
	 * Descrption:ȡ�õ�ǰ����,��ʽΪ:yyyy-MM-dd HH:mm:ss
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowPlusTime() throws Exception
	{
		String nowDate = "";
		try
		{
			java.sql.Date date = null;
			date = new java.sql.Date(new java.util.Date().getTime());
			nowDate = sdfLongTimePlus.format(date);
			return nowDate;
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	/**
	 * Descrption:ȡ�õ�ǰ����,��ʽΪ:yyyy-MM-dd HH:mm:ss
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getPlusTime(Date date) throws Exception
	{
		if(date == null ) return null;
		try
		{
			String nowDate = sdfLongTimePlus.format(date);
			return nowDate;
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	/**
	 * Descrption:ȡ�õ�ǰ����,��ʽΪ:yyyy-MM-dd HH:mm:ss
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getPlusTime2(Date date)
	{
		
		if(date == null ) return null;
		try
		{
			String nowDate = sdfLongTimePlus.format(date);
			return nowDate;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * Descrption:ȡ�õ�ǰ���ڵ����뼫,��ʽΪ:yyyyMMddHHmmssSSSS
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowPlusTimeMill() throws Exception
	{
		String nowDate = "";
		try
		{
			java.sql.Date date = null;
			date = new java.sql.Date(new java.util.Date().getTime());
			nowDate = sdfLongTimePlusMill.format(date);
			return nowDate;
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	/**
	 * �õ���ǰ���ֵ:1900
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowYear() throws Exception
	{
		String nowYear = "";
		try
		{
			String strTemp = getNowLongTime();
			nowYear = strTemp.substring(0, 4);
			return nowYear;
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	/**
	 * �õ���ǰ�·�ֵ:12
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowMonth() throws Exception
	{
		String nowMonth = "";
		try
		{
			String strTemp = getNowLongTime();
			nowMonth = strTemp.substring(4, 6);
			return nowMonth;
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	/**
	 * �õ���ǰ����ֵ:30
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowDay() throws Exception
	{
		String nowDay = "";
		try
		{
			String strTemp = getNowLongTime();
			nowDay = strTemp.substring(6, 8);
			return nowDay;
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	/**
	 * �õ���ǰСʱֵ:23
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowHour() throws Exception
	{
		String nowHour = "";
		try
		{
			String strTemp = getNowPlusTimeMill();
			nowHour = strTemp.substring(8, 10);
			return nowHour;
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	/**
	 * ������������ʱ����
	 * @param _second ����
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getTimeBySecond(String _second) throws Exception
	{
		String returnTime = "";
		long longHour = 0;
		long longMinu = 0;
		long longSec = 0;
		try
		{
			longSec = Long.parseLong(_second);
			if (longSec == 0)
			{
				returnTime = "0ʱ0��0��";
				return returnTime;
			}
			longHour = longSec / 3600; //ȡ��Сʱ��
			longSec = longSec % 3600; //ȡ�����µ���
			longMinu = longSec / 60; //ȡ�÷���
			longSec = longSec % 60; //ȡ�����µ���
			returnTime = longHour + "ʱ" + longMinu + "��" + longSec + "��";
			return returnTime;
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	/**
	 * pablo
	 * ���ݺ���������ʱ�������
	 * @param _second ����
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getTimeBySecond(long ms_second) throws Exception
	{
		String returnTime = "";
		long longHour = 0;
		long longMinu = 0;
		long longSec = 0;
		long longMs = ms_second;
		try
		{
			if (longMs == 0)
			{
				returnTime = "0ʱ0��0��0����";
				return returnTime;
			}
			longHour = longMs / 3600000; //ȡ��Сʱ��
			longMs = longMs % 3600000; //ȡ�����µĺ���
			longMinu = longMs / 60000; //ȡ�÷���
			longMs = longMs % 60000; //ȡ�����µĺ���
			longSec = longMs / 1000; //ȡ�����µ���
			longMs = longMs % 1000; //ȡ�����µĺ���
			returnTime = longHour + "ʱ" + longMinu + "��" + longSec + "��" + longMs + "����";
			return returnTime;
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	/**
	 * �õ������е����
	 *
	 * @param date ����
	 * @return yyyy��ʽ�����
	 */
	public static int convertDateToYear(Date date)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy", new DateFormatSymbols());
		return Integer.parseInt(df.format(date));
	}

	/**
	 * �õ�������������ɵ��ַ���
	 *
	 * @param d ����
	 * @return yyyyMM��ʽ�������ַ���
	 */
	public static String convertDateToYearMonth(Date d)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM", new DateFormatSymbols());
		return df.format(d);
	}

	/**
	 * �õ���������������ɵ��ַ���
	 *
	 * @param d ����
	 * @return yyyyMMdd��ʽ���������ַ���
	 */
	public static String convertDateToYearMonthDay(Date d)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", new DateFormatSymbols());
		return df.format(d);
	}
	/**
	 * �õ������е��·�
	 *
	 * @param date ����
	 * @return yyyy��ʽ�����
	 */
	public static String convertDateToMonth(Date d)
	{
		SimpleDateFormat df = new SimpleDateFormat("MM", new DateFormatSymbols());
		return df.format(d);
	}
	/**
	 * �õ������е���
	 *
	 * @param date ����
	 * @return yyyy��ʽ�����
	 */
	public static String convertDateToDay(Date d)
	{
		SimpleDateFormat df = new SimpleDateFormat("dd", new DateFormatSymbols());
		return df.format(d);
	}
	
	
	
	
	/**
	 * �õ������е�Сʱ
	 *
	 * @param date ����
	 * @return HH��ʽ��Сʱ
	 */
	public static String convertDateToHour(Date d)
	{
		SimpleDateFormat df = new SimpleDateFormat("HH", new DateFormatSymbols());
		return df.format(d);
	}
	
	/**
	 * �õ������еķ���
	 *
	 * @param date ����
	 * @return mm��ʽ�ķ���
	 */
	public static String convertDateToMinute(Date d)
	{
		SimpleDateFormat df = new SimpleDateFormat("mm", new DateFormatSymbols());
		return df.format(d);
	}
	/**
	 * ��ȡ��ǰ����Ϊ������
	 *
	 * @return ��ǰ���ڣ�java.util.Date����
	 */
	public static Date getCurrentDate()
	{
		Calendar cal = Calendar.getInstance();

		//String currentDate = null;
		Date d = cal.getTime();

		return d;
	}

	/**
	 * ��ȡ��ǰ���µ��ַ���
	 *
	 * @return ��ǰ���£�yyyyMM��ʽ
	 */
	public static String getCurrentYearMonth()
	{
		Calendar cal = Calendar.getInstance();
		String currentYear = (new Integer(cal.get(Calendar.YEAR))).toString();
		String currentMonth = null;
		if (cal.get(Calendar.MONTH) < 9)
			currentMonth = "0" + (new Integer(cal.get(Calendar.MONTH) + 1)).toString();
		else
			currentMonth = (new Integer(cal.get(Calendar.MONTH) + 1)).toString();
		return (currentYear + currentMonth);
	}

	/**
	 * ��ȡ��ǰ��Ϊ����
	 *
	 * @return ��ȡ��ǰ�����е��꣬int��
	 */
	public static int getCurrentYear()
	{
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		return currentYear;
	}
	/**
	 * �����ĸ�ʽ��ʱ����,��ʱ����б�׼����ʽ,�����ڽ�ǰ̨��������ڸ�ʽ��Ϊʵ�ʿ��е�����
	 * �罫20050600��ʽ��Ϊ20050601,��20050631��ʽ��Ϊ20050630
	 * @param _dateTime �����ԭʱ�䴮
	 * @param _format ��ʽ��,YYYYMMDDHH24MISS,YYYYMMDDHH12MISS
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String formatDateTime(String _dateTime, String _format) throws Exception
	{
		String returnValue = "";
		String formatString = _format.toUpperCase();
		String strYear = "";
		String strMonth = "";
		String strDay = "";
		String strHour = "";
		String strMinu = "";
		String strSec = "";
		int hourType = 12; //12Сʱ��,24Сʱ��
		int yearType = 1; //1Ϊƽ��,2Ϊ����
		try
		{
			if (formatString.indexOf("YYYY") >= 0)
			{
				int tempBeginPlace = formatString.indexOf("YYYY");
				int temEndPlace = tempBeginPlace + 4;
				strYear = _dateTime.substring(tempBeginPlace, temEndPlace);
			}
			if (formatString.indexOf("MM") >= 0)
			{
				int tempBeginPlace = formatString.indexOf("MM");
				int temEndPlace = tempBeginPlace + 2;
				strMonth = _dateTime.substring(tempBeginPlace, temEndPlace);
			}
			if (formatString.indexOf("DD") >= 0)
			{
				int tempBeginPlace = formatString.indexOf("DD");
				int temEndPlace = tempBeginPlace + 2;
				strDay = _dateTime.substring(tempBeginPlace, temEndPlace);
			}
			if (formatString.indexOf("HH24") >= 0)
			{
				int tempBeginPlace = formatString.indexOf("HH24");
				int temEndPlace = tempBeginPlace + 2;
				strHour = _dateTime.substring(tempBeginPlace, temEndPlace);
				formatString = formatString.replaceAll("24", "");
				//Ϊ�˱���λ��һ��,ȥ��24
				hourType = 24;
			}
			else if (formatString.indexOf("HH12") >= 0)
			{
				int tempBeginPlace = formatString.indexOf("HH12");
				int temEndPlace = tempBeginPlace + 2;
				strHour = _dateTime.substring(tempBeginPlace, temEndPlace);
				formatString = formatString.replaceAll("12", "");
				//Ϊ�˱���λ��һ��,ȥ��12
				hourType = 12;
			}
			else if (formatString.indexOf("HH") >= 0)
			{
				int tempBeginPlace = formatString.indexOf("HH");
				int temEndPlace = tempBeginPlace + 2;
				strHour = _dateTime.substring(tempBeginPlace, temEndPlace);
				hourType = 12; //���δָ��Сʱ��,��Ĭ��Ϊ12Сʱ��;
			}
			if (formatString.indexOf("MI") >= 0)
			{
				int tempBeginPlace = formatString.indexOf("MI");
				int temEndPlace = tempBeginPlace + 2;
				strMinu = _dateTime.substring(tempBeginPlace, temEndPlace);
			}
			if (formatString.indexOf("SS") >= 0)
			{
				int tempBeginPlace = formatString.indexOf("SS");
				int temEndPlace = tempBeginPlace + 2;
				strSec = _dateTime.substring(tempBeginPlace, temEndPlace);
			}

			//�ж��Ƿ�������
			if (!strYear.equals(""))
			{
				int intYear = Integer.parseInt(strYear);
				//�ܱ�4�����������ܱ�100������ �ܱ�4���������ܱ�400
				if (intYear % 4 == 0)
				{
					if (intYear % 100 != 0)
					{
						yearType = 2;
					}
				}
				if (intYear % 4 == 0)
				{
					if (intYear % 400 == 0)
					{
						yearType = 2;
					}
				}
			}
			//��ʽ����
			if (!strMonth.equals(""))
			{
				int intMonth = Integer.parseInt(strMonth);
				if (intMonth == 0)
				{
					strMonth = "01";
					intMonth = 1;
				}
				if (intMonth > 12)
				{
					strMonth = "12";
					intMonth = 12;
				}
			}

			//��ʽ����
			if (!strDay.equals(""))
			{
				int intDay = Integer.parseInt(strDay);
				if (intDay == 0)
				{
					strDay = "01";
					intDay = 1;
				}
				if (intDay > 31)
				{
					strDay = "31";
					intDay = 31;
				}
				if ((strMonth.equals("01"))
					|| (strMonth.equals("03"))
					|| (strMonth.equals("05"))
					|| (strMonth.equals("07"))
					|| (strMonth.equals("08"))
					|| (strMonth.equals("10"))
					|| (strMonth.equals("12")))
				{
					if (intDay > 31)
					{
						strDay = "31";
						intDay = 31;
					}
				}
				if ((strMonth.equals("02"))
					|| (strMonth.equals("04"))
					|| (strMonth.equals("06"))
					|| (strMonth.equals("09"))
					|| (strMonth.equals("11")))
				{
					if (intDay > 30)
					{
						strDay = "30";
						intDay = 30;
					}
					if (strMonth.equals("02"))
					{ //��2�µ��ر���
						if (yearType == 2)
						{
							if (intDay > 29)
							{
								strDay = "29";
								intDay = 29;
							}
						}
						else
						{
							if (intDay > 28)
							{
								strDay = "28";
								intDay = 28;
							}
						}
					}
				}

				//��ʽ��Сʱ
				if (!strHour.equals(""))
				{
					int intHour = Integer.parseInt(strHour);
					if (intHour > 24)
					{
						strHour = "24";
						intHour = 24;
					}
					if (hourType == 12)
					{
						if (intHour == 0)
						{
							intHour = 1;
							strHour = "01";
						}
						if (intHour > 12)
						{
							intHour = intHour - 12;
							strHour = "0" + intHour;
						}
					}
					else
					{
						if (intHour > 23)
						{
							intHour = 23;
							strHour = "23";
						}
					}
				}
				//��ʽ����
				if (!strMinu.equals(""))
				{
					int intMinu = Integer.parseInt(strMinu);
					if (intMinu > 59)
					{
						strMinu = "59";
						intMinu = 59;
					}
				}
				//��ʽ����
				if (!strSec.equals(""))
				{
					int intSec = Integer.parseInt(strSec);
					if (intSec > 59)
					{
						strSec = "59";
						intSec = 59;
					}
				}
			}
			returnValue = strYear + strMonth + strDay + strHour + strMinu + strSec;
			return returnValue;
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	/**
	 * ��ָ����ʽ���ַ���ת��Ϊ������
	 *
	 * @param strDate - ����
	 * @param oracleFormat  --oracle�����ڸ�ʽ
	 * @return ת���õ�������
	 */
	public static Date stringToDate(String strDate, String oracleFormat)
	{
		if (strDate == null)
			return null;
		Hashtable h = new Hashtable();
		String javaFormat = new String();
		String s = oracleFormat.toLowerCase();
		if (s.indexOf("yyyy") != -1)
			h.put(new Integer(s.indexOf("yyyy")), "yyyy");
		else if (s.indexOf("yy") != -1)
			h.put(new Integer(s.indexOf("yy")), "yy");
		if (s.indexOf("mm") != -1)
			h.put(new Integer(s.indexOf("mm")), "MM");

		if (s.indexOf("dd") != -1)
			h.put(new Integer(s.indexOf("dd")), "dd");
		if (s.indexOf("hh24") != -1)
			h.put(new Integer(s.indexOf("hh24")), "HH");
		if (s.indexOf("mi") != -1)
			h.put(new Integer(s.indexOf("mi")), "mm");
		if (s.indexOf("ss") != -1)
			h.put(new Integer(s.indexOf("ss")), "ss");

		int intStart = 0;
		while (s.indexOf("-", intStart) != -1)
		{
			intStart = s.indexOf("-", intStart);
			h.put(new Integer(intStart), "-");
			intStart++;
		}

		intStart = 0;
		while (s.indexOf("/", intStart) != -1)
		{
			intStart = s.indexOf("/", intStart);
			h.put(new Integer(intStart), "/");
			intStart++;
		}

		intStart = 0;
		while (s.indexOf(" ", intStart) != -1)
		{
			intStart = s.indexOf(" ", intStart);
			h.put(new Integer(intStart), " ");
			intStart++;
		}

		intStart = 0;
		while (s.indexOf(":", intStart) != -1)
		{
			intStart = s.indexOf(":", intStart);
			h.put(new Integer(intStart), ":");
			intStart++;
		}

		if (s.indexOf("��") != -1)
			h.put(new Integer(s.indexOf("��")), "��");
		if (s.indexOf("��") != -1)
			h.put(new Integer(s.indexOf("��")), "��");
		if (s.indexOf("��") != -1)
			h.put(new Integer(s.indexOf("��")), "��");
		if (s.indexOf("ʱ") != -1)
			h.put(new Integer(s.indexOf("ʱ")), "ʱ");
		if (s.indexOf("��") != -1)
			h.put(new Integer(s.indexOf("��")), "��");
		if (s.indexOf("��") != -1)
			h.put(new Integer(s.indexOf("��")), "��");

		int i = 0;
		while (h.size() != 0)
		{
			Enumeration e = h.keys();
			int n = 0;
			while (e.hasMoreElements())
			{
				i = ((Integer) e.nextElement()).intValue();
				if (i >= n)
					n = i;
			}
			String temp = (String) h.get(new Integer(n));
			h.remove(new Integer(n));

			javaFormat = temp + javaFormat;
		}
		SimpleDateFormat df = new SimpleDateFormat(javaFormat);

		Date myDate = new Date();
		try
		{
			myDate = df.parse(strDate);
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			return null;
		}

		return myDate;
	}

	/**
	 * ��ȡ�����ʽ�������ַ������ַ�����ѭOracle��ʽ
	 *
	 * @param d -  ����
	 * @param format -  ָ�����ڸ�ʽ����ʽ��д��ΪOracle��ʽ
	 * @return ��ָ�������ڸ�ʽת����������ַ���
	 */
	public static String dateToString(Date d, String format)
	{
		if (d == null)
			return "";
		Hashtable h = new Hashtable();
		String javaFormat = new String();
		String s = format.toLowerCase();
		if (s.indexOf("yyyy") != -1)
			h.put(new Integer(s.indexOf("yyyy")), "yyyy");
		else if (s.indexOf("yy") != -1)
			h.put(new Integer(s.indexOf("yy")), "yy");
		if (s.indexOf("mm") != -1)
			h.put(new Integer(s.indexOf("mm")), "MM");

		if (s.indexOf("dd") != -1)
			h.put(new Integer(s.indexOf("dd")), "dd");
		if (s.indexOf("hh24") != -1)
			h.put(new Integer(s.indexOf("hh24")), "HH");
		if (s.indexOf("mi") != -1)
			h.put(new Integer(s.indexOf("mi")), "mm");
		if (s.indexOf("ss") != -1)
			h.put(new Integer(s.indexOf("ss")), "ss");

		int intStart = 0;
		while (s.indexOf("-", intStart) != -1)
		{
			intStart = s.indexOf("-", intStart);
			h.put(new Integer(intStart), "-");
			intStart++;
		}

		intStart = 0;
		while (s.indexOf("/", intStart) != -1)
		{
			intStart = s.indexOf("/", intStart);
			h.put(new Integer(intStart), "/");
			intStart++;
		}

		intStart = 0;
		while (s.indexOf(" ", intStart) != -1)
		{
			intStart = s.indexOf(" ", intStart);
			h.put(new Integer(intStart), " ");
			intStart++;
		}

		intStart = 0;
		while (s.indexOf(":", intStart) != -1)
		{
			intStart = s.indexOf(":", intStart);
			h.put(new Integer(intStart), ":");
			intStart++;
		}

		if (s.indexOf("��") != -1)
			h.put(new Integer(s.indexOf("��")), "��");
		if (s.indexOf("��") != -1)
			h.put(new Integer(s.indexOf("��")), "��");
		if (s.indexOf("��") != -1)
			h.put(new Integer(s.indexOf("��")), "��");
		if (s.indexOf("ʱ") != -1)
			h.put(new Integer(s.indexOf("ʱ")), "ʱ");
		if (s.indexOf("��") != -1)
			h.put(new Integer(s.indexOf("��")), "��");
		if (s.indexOf("��") != -1)
			h.put(new Integer(s.indexOf("��")), "��");

		int i = 0;
		while (h.size() != 0)
		{
			Enumeration e = h.keys();
			int n = 0;
			while (e.hasMoreElements())
			{
				i = ((Integer) e.nextElement()).intValue();
				if (i >= n)
					n = i;
			}
			String temp = (String) h.get(new Integer(n));
			h.remove(new Integer(n));

			javaFormat = temp + javaFormat;
		}
		SimpleDateFormat df = new SimpleDateFormat(javaFormat, new DateFormatSymbols());

		return df.format(d);
	}

	/**
	 * ��ȡ�����ʽ�������ַ������ַ�����ѭOracle��ʽ
	 *
	 * @param d - ����
	 * @param format - ָ�����ڸ�ʽ����ʽ��д��ΪOracle��ʽ
	 * @return ��ָ�������ڸ�ʽת����������ַ���
	 */
	public static String getDate(Date d, String format)
	{
		if (d == null)
			return "";
		Hashtable h = new Hashtable();
		String javaFormat = new String();
		String s = format.toLowerCase();
		if (s.indexOf("yyyy") != -1)
			h.put(new Integer(s.indexOf("yyyy")), "yyyy");
		else if (s.indexOf("yy") != -1)
			h.put(new Integer(s.indexOf("yy")), "yy");
		if (s.indexOf("mm") != -1)
			h.put(new Integer(s.indexOf("mm")), "MM");

		if (s.indexOf("dd") != -1)
			h.put(new Integer(s.indexOf("dd")), "dd");
		if (s.indexOf("hh24") != -1)
			h.put(new Integer(s.indexOf("hh24")), "HH");
		if (s.indexOf("mi") != -1)
			h.put(new Integer(s.indexOf("mi")), "mm");
		if (s.indexOf("ss") != -1)
			h.put(new Integer(s.indexOf("ss")), "ss");

		int intStart = 0;
		while (s.indexOf("-", intStart) != -1)
		{
			intStart = s.indexOf("-", intStart);
			h.put(new Integer(intStart), "-");
			intStart++;
		}

		intStart = 0;
		while (s.indexOf("/", intStart) != -1)
		{
			intStart = s.indexOf("/", intStart);
			h.put(new Integer(intStart), "/");
			intStart++;
		}

		intStart = 0;
		while (s.indexOf(" ", intStart) != -1)
		{
			intStart = s.indexOf(" ", intStart);
			h.put(new Integer(intStart), " ");
			intStart++;
		}

		intStart = 0;
		while (s.indexOf(":", intStart) != -1)
		{
			intStart = s.indexOf(":", intStart);
			h.put(new Integer(intStart), ":");
			intStart++;
		}

		if (s.indexOf("��") != -1)
			h.put(new Integer(s.indexOf("��")), "��");
		if (s.indexOf("��") != -1)
			h.put(new Integer(s.indexOf("��")), "��");
		if (s.indexOf("��") != -1)
			h.put(new Integer(s.indexOf("��")), "��");
		if (s.indexOf("ʱ") != -1)
			h.put(new Integer(s.indexOf("ʱ")), "ʱ");
		if (s.indexOf("��") != -1)
			h.put(new Integer(s.indexOf("��")), "��");
		if (s.indexOf("��") != -1)
			h.put(new Integer(s.indexOf("��")), "��");

		int i = 0;
		while (h.size() != 0)
		{
			Enumeration e = h.keys();
			int n = 0;
			while (e.hasMoreElements())
			{
				i = ((Integer) e.nextElement()).intValue();
				if (i >= n)
					n = i;
			}
			String temp = (String) h.get(new Integer(n));
			h.remove(new Integer(n));

			javaFormat = temp + javaFormat;
		}
		SimpleDateFormat df = new SimpleDateFormat(javaFormat, new DateFormatSymbols());

		return df.format(d);
	}
	/**
	 * �������֤�����ȡ����
	 *
	 * @param id ���֤��
	 * @throws Exception ���֤�Ŵ���ʱ����
	 * @return int - ����
	 */
	public static int getAge(String id) throws Exception
	{
		int age = -1;
		int length = id.length();
		String birthday = "";
		if (length == 15)
		{
			birthday = id.substring(6, 8);
			birthday = "19" + birthday;
		}
		else if (length == 18)
		{
			birthday = id.substring(6, 10);
		}
		else
		{
			throw new Exception("��������֤��");
		}
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		age = currentYear - (new Integer(birthday)).intValue();
		return age;
	}

	/**
	 * ���������ȡ�������
	 *
	 * @param age int ����
	 * @return Date - �������
	 */
	public static java.sql.Date getDateByAge(int age)
	{
		Calendar calendar = Calendar.getInstance(Locale.CHINESE);
		long current = calendar.getTimeInMillis();
		calendar.set(calendar.get(Calendar.YEAR) - age, calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
		return new java.sql.Date((calendar.getTimeInMillis()));
	}

	/**
	 * �Ƚ���������(�����ͣ���ʽΪYYYYMM)֮������·�
	 *
	 * @param dealMonth - ��ʼ����
	 * @param alterMonth - ��������
	 * @return alterMonth-dealMonth��������
	 */
	public static int calBetweenTwoMonth(String dealMonth, String alterMonth)
	{
		int length = 0;
		if ((dealMonth.length() != 6) || (alterMonth.length() != 6))
		{
			//�Ƚ������ַ����ĳ��Ȳ���ȷ
			length = -1;

		}
		else
		{
			int dealInt = Integer.parseInt(dealMonth);
			int alterInt = Integer.parseInt(alterMonth);
			if (dealInt < alterInt)
			{
				//��һ�����±���Ӧ���ڻ���ڵڶ������±���
				length = -2;
			}
			else
			{
				int dealYearInt = Integer.parseInt(dealMonth.substring(0, 4));
				int dealMonthInt = Integer.parseInt(dealMonth.substring(4, 6));
				int alterYearInt = Integer.parseInt(alterMonth.substring(0, 4));
				int alterMonthInt = Integer.parseInt(alterMonth.substring(4, 6));
				length = (dealYearInt - alterYearInt) * 12 + (dealMonthInt - alterMonthInt);
			}
		}

		return length;
	}

	/**
	 * �õ���������֮����������
	 *
	 * @param newDate �������
	 * @param oldDate С������
	 * @return newDate-oldDate��������
	 */
	public static int daysBetweenDates(Date newDate, Date oldDate)
	{
		int days = 0;
		Calendar calo = Calendar.getInstance();
		Calendar caln = Calendar.getInstance();
		calo.setTime(oldDate);
		caln.setTime(newDate);
		int oday = calo.get(Calendar.DAY_OF_YEAR);
		int nyear = caln.get(Calendar.YEAR);
		int oyear = calo.get(Calendar.YEAR);
		while (nyear > oyear)
		{
			calo.set(Calendar.MONTH, 11);
			calo.set(Calendar.DATE, 31);
			days = days + calo.get(Calendar.DAY_OF_YEAR);
			oyear = oyear + 1;
			calo.set(Calendar.YEAR, oyear);
		}
		int nday = caln.get(Calendar.DAY_OF_YEAR);
		days = days + nday - oday;

		return days;
	}

	/**
	 * ȡ����ԭ�������һ�����������ڣ�����Date������
	 *
	 * @param date ԭ����
	 * @param intBetween ��������
	 * @return date����intBetween��������
	 */
	public static Date getDateBetween(Date date, int intBetween)
	{
		Calendar calo = Calendar.getInstance();
		calo.setTime(date);
		calo.add(Calendar.DATE, intBetween);
		return calo.getTime();
	}

	/**
	 * ��ָ����ʽȡ����ԭ�������һ�����������ڣ�����String������
	 *
	 * @param date ԭ����
	 * @param intBetween ��������
	 * @param strFromat �������ڵĸ�ʽ
	 * @return date����intBetween��������
	 */
	public static String getDateBetween_String(Date date, int intBetween, String strFromat)
	{
		Date dateOld = getDateBetween(date, intBetween);
		return getDate(dateOld, strFromat);
	}

	/**
	 * �õ����������ַ�������1�º�������ַ���
	 *
	 * @param yearMonth  yyyyMM��ʽ
	 * @return yearMonth����һ���º�����ڣ�yyyyMM��ʽ
	 */
	public static String increaseYearMonth(String yearMonth)
	{
		int year = (new Integer(yearMonth.substring(0, 4))).intValue();
		int month = (new Integer(yearMonth.substring(4, 6))).intValue();
		month = month + 1;
		if (month <= 12 && month >= 10)
			return yearMonth.substring(0, 4) + (new Integer(month)).toString();
		else if (month < 10)
			return yearMonth.substring(0, 4) + "0" + (new Integer(month)).toString();
		else
			//if(month>12)
			return (new Integer(year + 1)).toString() + "0" + (new Integer(month - 12)).toString();

	}

	/**
	 * �õ����������ַ�������ָ��������������ַ���
	 *
	 * @param yearMonth   yyyyMM��ʽ����
	 * @param addMonth    ����ָ������
	 * @return  yearMonth ����addMonth���º�����ڣ�yyyyMM��ʽ
	 */
	public static String increaseYearMonth(String yearMonth, int addMonth)
	{
		int year = (new Integer(yearMonth.substring(0, 4))).intValue();
		int month = (new Integer(yearMonth.substring(4, 6))).intValue();
		month = month + addMonth;
		year = year + month / 12;
		month = month % 12;
		if (month <= 12 && month >= 10)
			return year + (new Integer(month)).toString();
		else
			return year + "0" + (new Integer(month)).toString();

	}

	/**
	 * �õ����������ַ�����ȥ1�º�������ַ���
	 *
	 * @param yearMonth -   yyyyMM��ʽ
	 * @return - yearMonth����һ���µ����ڣ�yyyyMM��ʽ
	 */
	public static String descreaseYearMonth(String yearMonth)
	{
		int year = (new Integer(yearMonth.substring(0, 4))).intValue();
		int month = (new Integer(yearMonth.substring(4, 6))).intValue();
		month = month - 1;
		if (month >= 10)
			return yearMonth.substring(0, 4) + (new Integer(month)).toString();
		else if (month > 0 && month < 10)
			return yearMonth.substring(0, 4) + "0" + (new Integer(month)).toString();
		else
			//if(month>12)
			return (new Integer(year - 1)).toString() + (new Integer(month + 12)).toString();

	}
	/**
	 * �Ƚ��������������ڵĴ�С�����ڸ�ʽΪyyyyMM �����ִ�6λ��ǰ4�����꣬��2�����£� <br>
	 * IF ��һ�������ʱ�� > �ڶ��������ʱ�䣬�����棬ELSE ���ؼ� <br>
	 *
	 * @param s1 ����1
	 * @param s2 ����2
	 * @return boolean ���s1���ڵ���s2�򷵻��棬���򷵻ؼ�
	 */
	public static boolean yearMonthGreatEqual(String s1, String s2)
	{
		String temp1 = s1.substring(0, 4);
		String temp2 = s2.substring(0, 4);
		String temp3 = s1.substring(4, 6);
		String temp4 = s2.substring(4, 6);

		if (Integer.parseInt(temp1) > Integer.parseInt(temp2))
			return true;
		else if (Integer.parseInt(temp1) == Integer.parseInt(temp2))
		{
			if (Integer.parseInt(temp3) >= Integer.parseInt(temp4))
				return true;
			else
				return false;
		}
		else
			return false;
	}

	/**
	 * �Ƚ��������������ڵĴ�С�����ڸ�ʽΪyyyyMM �����ִ�6λ��ǰ4�����꣬��2�����£� <br>
	 * IF ��һ�������ʱ�� > �ڶ��������ʱ�䣬�����棬ELSE ���ؼ� <br>
	 *
	 * @param s1 ����1
	 * @param s2 ����2
	 * @return boolean ���s1����s2�򷵻��棬���򷵻ؼ�
	 */
	public static boolean yearMonthGreater(String s1, String s2)
	{
		String temp1 = s1.substring(0, 4);
		String temp2 = s2.substring(0, 4);
		String temp3 = s1.substring(4, 6);
		String temp4 = s2.substring(4, 6);

		if (Integer.parseInt(temp1) > Integer.parseInt(temp2))
			return true;
		else if (Integer.parseInt(temp1) == Integer.parseInt(temp2))
		{
			if (Integer.parseInt(temp3) > Integer.parseInt(temp4))
				return true;
			else
				return false;
		}
		else
			return false;
	}

	/**
	 * ������������ת����OracleҪ��ı�׼��ʽ���ַ���
	 * @param date ����
	 * @return ��ʽ������ַ���
	 */
	public static String getOracleFormatDateStr(Date date)
	{
		return getDate(date, "YYYY-MM-DD HH24:MI:SS");
	}

	/**
	 * �ִ�6λ��ǰ4�����꣬��2�����£�
	 * ���ظ��������е��·��е����һ�� param term(YYYYMMDD)
	 *
	 * @param term - ���£���ʽΪyyyyMM
	 * @return String ָ�������и��·ݵ�����
	 */
	public static String getLastDay(String term)
	{

		int getYear = Integer.parseInt(term.substring(0, 4));
		int getMonth = Integer.parseInt(term.substring(4, 6));

		String getLastDay = "";

		if (getMonth == 2)
		{
			if (getYear % 4 == 0 && getYear % 100 != 0 || getYear % 400 == 0)
			{
				getLastDay = "29";
			}
			else
			{
				getLastDay = "28";
			}
		}
		else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11)
		{
			getLastDay = "30";
		}
		else
		{
			getLastDay = "31";
		}
		return String.valueOf(getYear) + "��" + String.valueOf(getMonth) + "��" + getLastDay + "��";
	}

	/**
	 * ������������(���磺200206)֮���������������¸�ʽΪyyyyMM
	 *
	 * @param strDateBegin - String
	 * @param strDateEnd String
	 * @return String strDateEnd-strDateBegin��������
	 */
	public static String getMonthBetween(String strDateBegin, String strDateEnd)
	{
		try
		{
			int intMonthBegin;
			int intMonthEnd;
			String strOut;
			if (strDateBegin.equals("")
				|| strDateEnd.equals("")
				|| strDateBegin.length() != 6
				|| strDateEnd.length() != 6)
				strOut = "";
			else
			{
				intMonthBegin =
					Integer.parseInt(strDateBegin.substring(0, 4)) * 12
						+ Integer.parseInt(strDateBegin.substring(4, 6));
				intMonthEnd =
					Integer.parseInt(strDateEnd.substring(0, 4)) * 12 + Integer.parseInt(strDateEnd.substring(4, 6));
				strOut = String.valueOf(intMonthBegin - intMonthEnd);
			}
			return strOut;
		}
		catch (Exception e)
		{
			return "0";
		}
	}

	/**
	 * ��yyyyMMDD��ʽ������ת��Ϊyyyy-MM-DD��ʽ������ ���ش�'-'������(���磺20020612 ת��Ϊ 2002-06-12)
	 *
	 * @param strDate String yyyyMMDD��ʽ������
	 * @return String - yyyy-MM-DD��ʽ������
	 */
	public static String getStrHaveAcross(String strDate)
	{
		try
		{
			return strDate.substring(0, 4) + "-" + strDate.substring(4, 6) + "-" + strDate.substring(6, 8);
		}
		catch (Exception e)
		{
			return strDate;
		}
	}

	/**
	 * ȡ�õ�ǰ���ڵ���һ���µĵ�һ�� add by yaojp 2002-10-08
	 *
	 * @return ��ǰ���ڵ��¸��µĵ�һ�죬��ʽΪyyyyMMDD
	 */
	public static String getFirstDayOfNextMonth()
	{
		try
		{
			return increaseYearMonth(getNowShortDate().substring(0, 6)) + "01";
		}
		catch (Exception e)
		{
			return "";
		}
	}
	/**
	 * ȡ�õ�ǰ���ڵ���һ���µĵ�һ�� add by zhouning 2006-09-13
	 *
	 * @return ��ǰ���ڵ��¸��µĵ�һ�죬��ʽΪyyyyMMDD
	 */
	public static String getFirstDayOfThisMonth()
	{
		try
		{
			return getNowShortDate().substring(0, 6) + "01";
		}
		catch (Exception e)
		{
			return "";
		}
	}
	/**
	   * ��yyyyMM��ʽת����yyyy��MM�¸�ʽ
	   * @param yearMonth �������͵��ַ���
	   * @return String
	   */
	public static String getYearAndMonth(String yearMonth)
	{
		if (null == yearMonth)
			return "";
		String ym = yearMonth.trim();
		if (6 != ym.length())
			return ym;
		String year = ym.substring(0, 4);
		String month = ym.substring(4);
		return new StringBuffer(year).append("��").append(month).append("��").toString();
	}

	/**
	* �������Integer���͵�����ת����"X��X��"��ʽ���ַ���
	* @param month Integer
	* @return String
	*/
	public static String month2YearMonth(String month)
	{
		String yearMonth = "";
		int smonth = 0;
		int year = 0;
		int rmonth = 0;

		if ((null == month) || ("0".equals(month)) || "".equals(month.trim()))
		{
			return "0��";
		}

		smonth = Integer.parseInt(month);
		year = smonth / 12;
		rmonth = smonth % 12;

		if (year > 0)
		{
			yearMonth = year + "��";
		}
		if (rmonth > 0)
		{
			yearMonth += rmonth + "����";
		}

		return yearMonth;
	}

	/**
	 * ��yyyyMM��ʽת����yyyy��MM�¸�ʽ
	 * @param month ��
	 * @return ���������͸�ʽ������
	 */
	public static String getYearMonthByMonth(String month)
	{
		if (null == month)
			return null;
		String ym = month.trim();
		if (6 != ym.length())
			return ym;
		String year = ym.substring(0, 4);
		String month1 = ym.substring(4);
		return new StringBuffer(year).append("��").append(month).append("��").toString();
	}

	/**
	 * �õ���date����ָ���������date
	 *
	 * @param date ����
	 * @param intBetween ���ӵ��·�
	 * @return date ����intBetween�����������
	 */
	public static Date increaseMonth(Date date, int intBetween)
	{
		Calendar calo = Calendar.getInstance();
		calo.setTime(date);
		calo.add(Calendar.MONTH, intBetween);
		return calo.getTime();
	}

	/**
		 * �õ���date����ָ���������date
		 *
		 * @param date ����
		 * @param intBetween ���ӵ�����
		 * @return date ����intBetween�����������
		 */
	public static Date increaseDay(Date date, int intBetween)
	{
		Calendar calo = Calendar.getInstance();
		calo.setTime(date);
		calo.add(Calendar.DATE, intBetween);
		return calo.getTime();
	}
	/**
	 * �õ���date����ָ���������date
	 * @param date ����
	 * @param intBetween ���ӵ�����
	 * @return date����intBetween�����������
	 */
	public static Date increaseYear(Date date, int intBetween)
	{
		Calendar calo = Calendar.getInstance();
		calo.setTime(date);
		calo.add(Calendar.YEAR, intBetween);
		return calo.getTime();
	}
	/**
	 * �Ƚ�����ʱ���Ⱥ�
	 * @param str1 ������ַ���
	 * @param str2 ������ַ���
	 * @return int negative integer, zero, or a positive integer as str1 is less than, equal to, or greater than str2
	 * */
	public static int compareDate(String str1, String str2)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date1 = null, date2 = null;
		try
		{
			date1 = formatter.parse(str1);
			date2 = formatter.parse(str2);
		}
		catch (ParseException ex)
		{
			ex.printStackTrace();
		}
		return date1.compareTo(date2);
	}

	public static int compareDate(String str1, Date date2)
	{
		Date date1 = getDateByString(str1);
		return date1.compareTo(date2);
	}
	
	public static int compareDate(String format,String str1, Date date2)
	{

		Date date1 = null;
		try {
			date1 = fromStringToDate(format,str1);
		} catch (ParseException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return date1.compareTo(date2);
	}

	/**
	 * ���ݴ���������ַ���ת������Ӧ�����ڶ�������ַ���Ϊ�ջ򲻷������ڸ�
	 * ʽ���򷵻ص�ǰʱ�䡣
	 * @param strDate String �����ַ���
	 * @return java.sql.Timestamp ���ڶ���
	 * */
	public static java.sql.Timestamp getDateByString(String strDate)
	{
		if (strDate.trim().equals(""))
		{
			return new java.sql.Timestamp(System.currentTimeMillis());
		}
		try
		{
			strDate = getFormattedDate(strDate, "yyyy-MM-dd HH:mm:ss") + ".000000000";
			return java.sql.Timestamp.valueOf(strDate);
		}
		catch (Exception ex)
		{
			return new java.sql.Timestamp(System.currentTimeMillis());
		}
	}

	public static java.sql.Timestamp getNextMonyDate(String strDate)
	{
		try
		{
			int iYear = StringUtil.getStrToInt(getFormattedDate(strDate, "yyyy"));
			int iMonth = StringUtil.getStrToInt(getFormattedDate(strDate, "MM"));
			if (iMonth == 12)
			{
				iYear = iYear + 1;
			}
			else
			{
				iMonth = iMonth + 1;
			}
			String strMonth = Integer.toString(iMonth);
			if (strMonth.length() == 1)
			{
				strMonth = "0" + strMonth;
			}
			strDate = Integer.toString(iYear) + "/" + strMonth + "/01";
			return getDateByString(strDate);
		}
		catch (Exception ex)
		{
			return getDateByString(strDate);
		}
	}
//	/**
//	 * ���ݲ������ƣ���request������ȡ���ò��������Ѹò���ת����GB2312������ַ�����
//	 * @param request �������
//	 * @param strParamName ��������
//	 * @return java.sql.Date ת����Ĳ���ֵ
//	 * */
//	public static java.sql.Timestamp getDateFromReqParam(HttpServletRequest request, String strParamName)
//	{
//		String strStr = StringUtil.getNotNullStr(request.getParameter(strParamName));
//		return getDateByString(strStr);
//	}
	/**
	 * �õ���ǰ���ڣ���ʽyyyy-MM-dd��
	 * @return String ��ʽ���������ַ���
	 */
	public static String getCurrDate()
	{
		return getFormattedDate(getDateByString(""));
	}

	/**
	 * �õ���ǰ���ڣ���ʽyyyy-MM-dd��
	 * @return String ��ʽ���������ַ���
	 */
	public static String getToday()
	{
		Date cDate = new Date();
		SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return cSimpleDateFormat.format(cDate);
	}

	/**
	 * �õ���ǰ���ڣ���ʽyyyy-MM-dd��
	 * @return String ��ʽ���������ַ���
	 */
	public static String getYesterday()
	{
		Date cDate = new Date();
		cDate.setTime(cDate.getTime() - 24 * 3600 * 1000);
		SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return cSimpleDateFormat.format(cDate);
	}

	/**
	 * �õ���ǰ���ڣ���ʽyyyy-MM-dd��
	 * @return String ��ʽ���������ַ���
	 */
	public static String getTomorrow()
	{
		Date cDate = new Date();
		cDate.setTime(cDate.getTime() + 24 * 3600 * 1000);
		SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return cSimpleDateFormat.format(cDate);
	}

	/**����Ĭ�ϵĹ�����Ч��ʱ�䣬1900/01/01��
	 * @return String Ĭ�ϵ�ʵЧʱ���ַ���
	 * */
	public static String getDefaultValidDate()
	{
		return "1900-01-01";
	}

	/**����Ĭ�ϵĹ���ʧЧ��ʱ�䣬2099/12/31��
	 * @return String Ĭ�ϵ�ʵЧʱ���ַ���
	 * */
	public static String getDefaultExpireDate()
	{
		return "2099-12-31";
	}
	/**
	 * �õ���ǰ����ʱ��,��ʽΪyyyy-MM-dd hh:mm:ss.
	 * @return String
	 */
	public static String getCurrDateTime()
	{
		java.sql.Timestamp date = new java.sql.Timestamp(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return formatter.format(date);
	}

	/**
	 * �õ�ָ�������ڣ���һ��������������(��yyyy/MM/dd��ʽ��ʾ)����Ϊ("yyyy/MM/dd",1,3,9)
	 * @param strFormat strFormat
	 * @param iYear iYear
	 * @param iMonth iMonth
	 * @param iDate iDate
	 * @return String
	 */
	public static String getSpecDate(String strFormat, int iYear, int iMonth, int iDate)
	{
		Calendar rightNow = Calendar.getInstance();
		rightNow.set(Calendar.YEAR, rightNow.get(Calendar.YEAR) + iYear);
		rightNow.set(Calendar.MONTH, rightNow.get(Calendar.MONTH) + iMonth);
		rightNow.set(Calendar.DATE, rightNow.get(Calendar.DATE) + iDate);
		SimpleDateFormat df = new SimpleDateFormat(strFormat);
		return df.format(rightNow.getTime());
	}
	/**
	 * ������������ַ�������Ĭ�ϵĸ�ʽyyyy-MM-dd HH:mm:ssת����
	 * @param strDate String ��Ҫ���и�ʽ���������ַ���
	 * @return String ������ʽ������ַ���
	 */
	public static String getDefaultFormattedDate(String strDate)
	{
		return getFormattedDate(strDate, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * ����������ڽ���Ĭ�ϵĸ�ʽyyyy-MM-dd HH:mm:ssת����
	 * @param dtDate ��Ҫ���и�ʽ��������
	 * @return String ������ʽ������ַ���
	 */
	public static String getDefaultFormattedDate(java.sql.Timestamp dtDate)
	{
		return getFormattedDate(dtDate, "yyyy-MM-dd HH:mm:ss");
	}

	public static java.sql.Timestamp getNullBirthDay()
	{
		return new java.sql.Timestamp(0);
	}

	/**
	 * ������������ַ�������Ĭ�ϵĸ�ʽyyyy-MM-ddת��.
	 * @param strDate String ��Ҫ���и�ʽ���������ַ���
	 * @return String ������ʽ������ַ���
	 */
	public static String getFormattedDate(String strDate)
	{
		return getFormattedDate(strDate, "yyyy-MM-dd");
	}

	/**
	 * ������������ַ������и�ʽ��,����������0000/00/00 00:00:00�򷵻ؿմ�.
	 * @param strDate String ��Ҫ���и�ʽ���������ַ���
	 * @param strFormatTo String Ҫת�������ڸ�ʽ
	 * @return String ������ʽ������ַ���
	 */
	public static String getFormattedDate(String strDate, String strFormatTo)
	{
		if (strDate == null || strDate.trim().equals(""))
		{
			return "";
		}
		strDate = strDate.replace('/', '-');
		strFormatTo = strFormatTo.replace('/', '-');
		if (strDate.equals("0000-00-00 00:00:00") || strDate.equals("1800-01-01 00:00:00"))
		{
			return "";
		}
		String formatStr = strFormatTo; //"yyyyMMdd";
		if (strDate == null || strDate.trim().equals(""))
		{
			return "";
		}
		switch (strDate.trim().length())
		{
			case 6 :
				if (strDate.substring(0, 1).equals("0"))
				{
					formatStr = "yyMMdd";
				}
				else
				{
					formatStr = "yyyyMM";
				}
				break;
			case 8 :
				formatStr = "yyyyMMdd";
				break;
			case 10 :
				if (strDate.indexOf("-") == -1)
				{
					formatStr = "yyyy/MM/dd";
				}
				else
				{
					formatStr = "yyyy-MM-dd";
				}
				break;
			case 11 :
				if (strDate.getBytes().length == 14)
				{
					formatStr = "yyyy��MM��dd��";
				}
				else
				{
					return "";
				}
			case 14 :
				formatStr = "yyyyMMddHHmmss";
				break;
			case 19 :
				if (strDate.indexOf("-") == -1)
				{
					formatStr = "yyyy/MM/dd HH:mm:ss";
				}
				else
				{
					formatStr = "yyyy-MM-dd HH:mm:ss";
				}
				break;
			case 21 :
				if (strDate.indexOf("-") == -1)
				{
					formatStr = "yyyy/MM/dd HH:mm:ss.S";
				}
				else
				{
					formatStr = "yyyy-MM-dd HH:mm:ss.S";
				}
				break;
			default :
				return strDate.trim();
		}
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(formatter.parse(strDate));
			formatter = new SimpleDateFormat(strFormatTo);
			return formatter.format(calendar.getTime());
		}
		catch (Exception e)
		{
			//Common.printLog("ת�������ַ�����ʽʱ����;" + e.getMessage());
			return "";
		}
	}

	/**
	 * ����������ڰ���Ĭ�ϵĸ�ʽyyyy-MM-ddת��.
	 * @param dtDate ��Ҫ���и�ʽ���������ַ���
	 * @return String ������ʽ������ַ���
	 */
	public static String getFormattedDate(java.sql.Timestamp dtDate)
	{
		return getFormattedDate(dtDate, "yyyy-MM-dd");
	}

	/**
	 * ����������ڽ��и�ʽ��, ��������������null�򷵻ؿմ�.
	 * @param dtDate java.sql.Timestamp ��Ҫ���и�ʽ���������ַ���
	 * @param strFormatTo String Ҫת�������ڸ�ʽ
	 * @return String ������ʽ������ַ���
	 */
	public static String getFormattedDate(java.sql.Timestamp dtDate, String strFormatTo)
	{
		if (dtDate == null)
		{
			return "";
		}
		if (dtDate.equals(new java.sql.Timestamp(0)))
		{
			return "";
		}
		strFormatTo = strFormatTo.replace('/', '-');
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
			if (Integer.parseInt(formatter.format(dtDate)) < 1900)
			{
				return "";
			}
			else
			{
				formatter = new SimpleDateFormat(strFormatTo);
				return formatter.format(dtDate);
			}
		}
		catch (Exception e)
		{
			//Common.printLog("ת�������ַ�����ʽʱ����;" + e.getMessage());
			return "";
		}
	}
	/**
	 * ������ת����hh:mm:ss��ʽ
	 * @param lSecond long
	 * @return String
	 * */
	public static String getTimeFormat(long lSecond)
	{
		String szTime = new String();

		if (lSecond <= 0)
		{
			szTime = "00" + ":" + "00" + ":" + "00";
		}
		else
		{
			long hour = lSecond / 3600;
			long minute = (lSecond - hour * 3600) / 60;
			long second = (lSecond - hour * 3600 - minute * 60);

			if (hour <= 0)
			{
				szTime = "00";
			}
			else if (hour < 10)
			{
				szTime = "0" + String.valueOf(hour);
			}
			else
			{
				szTime = String.valueOf(hour);
			}
			szTime = szTime + ":";

			if (minute <= 0)
			{
				szTime = szTime + "00";
			}
			else if (minute < 10)
			{
				szTime = szTime + "0" + String.valueOf(minute);
			}
			else
			{
				szTime = szTime + String.valueOf(minute);
			}
			szTime = szTime + ":";

			if (second <= 0)
			{
				szTime = szTime + "00";
			}
			else if (second < 10)
			{
				szTime = szTime + "0" + String.valueOf(second);
			}
			else
			{
				szTime = szTime + String.valueOf(second);
			}
		}

		return szTime;
	}
	public static String getFormattedDateUtil(java.util.Date dtDate, String strFormatTo)
	{
		if (dtDate == null)
		{
			return "";
		}
		strFormatTo = strFormatTo.replace('/', '-');
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat(strFormatTo);
			return formatter.format(dtDate);
		}
		catch (Exception e)
		{
			//Common.printLog("ת�������ַ�����ʽʱ����;" + e.getMessage());
			return "";
		}
	}
	/**
	 * �ó���������֮��ļ������
	 * @param strFromDate ��ʽΪyyyyMMdd
	 * @param strToDate  ��ʽΪyyyyMMdd
	 * @return int
	 */
	public static int getBetweenDays(String strFromDate, String strToDate)
	{
		try
		{
			Calendar clFrom = new GregorianCalendar();
			int iYear = Integer.parseInt(strFromDate.substring(0, 4));
			int iMonth = Integer.parseInt(strFromDate.substring(4, 6));
			int iDay = Integer.parseInt(strFromDate.substring(6, 8));
			clFrom.set(iYear, iMonth, iDay, 0, 0, 0);
			Calendar clTo = new GregorianCalendar();
			iYear = Integer.parseInt(strToDate.substring(0, 4));
			iMonth = Integer.parseInt(strToDate.substring(4, 6));
			iDay = Integer.parseInt(strToDate.substring(6, 8));
			clTo.set(iYear, iMonth, iDay, 0, 0, 0);
			long llTmp = clTo.getTime().getTime() - clFrom.getTime().getTime();
			return new Long(llTmp / 1000 / 3600 / 24).intValue();
		}
		catch (Exception e)
		{
			return Integer.MIN_VALUE;
		}
	}

	//ԭDateUtil����
	private static DateUtil instance = null;

	private static final Locale local = Locale.ENGLISH;

	public static synchronized DateUtil getInstance()
	{
		if (instance == null)
		{
			instance = new DateUtil();
		}
		return instance;
	}
	public static final long millisInDay = 86400000;

	// some static date formats
	private static SimpleDateFormat[] mDateFormats = loadDateFormats();

	private static final SimpleDateFormat mFormat8chars = new SimpleDateFormat("yyyyMMdd");

	private static final SimpleDateFormat mFormatIso8601Day = new SimpleDateFormat("yyyy-MM-dd");

	private static final SimpleDateFormat mFormatIso8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

	//http://www.w3.org/Protocols/rfc822/Overview.html#z28
	private static final SimpleDateFormat mFormatRfc822 = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z");

	private static final SimpleDateFormat mFormatTradeEasy = new SimpleDateFormat("MM/dd/yyyy HH:mm");

	private static final SimpleDateFormat mFormatTradeEasyMMddyyyy = new SimpleDateFormat("MM/dd/yyyy");

	//add by huyanzhi
	private static final SimpleDateFormat mFormatTradeEasyProduct = new SimpleDateFormat("dd/MM/yyyy");
	//end

	private static final SimpleDateFormat mFormatExpire = new SimpleDateFormat("MMMM dd, yyyy", local);

	private static SimpleDateFormat[] loadDateFormats()
	{
		SimpleDateFormat[] temp = {
			//new SimpleDateFormat("MM/dd/yyyy hh:mm:ss.SSS a"),
			new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy"),
			// standard Date.toString() results
			new SimpleDateFormat("M/d/yy hh:mm:ss"),
				new SimpleDateFormat("M/d/yyyy hh:mm:ss"),
				new SimpleDateFormat("M/d/yy hh:mm a"),
				new SimpleDateFormat("M/d/yyyy hh:mm a"),
				new SimpleDateFormat("M/d/yy HH:mm"),
				new SimpleDateFormat("M/d/yyyy HH:mm"),
				new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"),
				new SimpleDateFormat("yy-MM-dd HH:mm:ss.SSS"),
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"),
			// standard Timestamp.toString() results
			new SimpleDateFormat("M-d-yy HH:mm"),
				new SimpleDateFormat("M-d-yyyy HH:mm"),
				new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS"),
				new SimpleDateFormat("M/d/yy"),
				new SimpleDateFormat("M/d/yyyy"),
				new SimpleDateFormat("M-d-yy"),
				new SimpleDateFormat("M-d-yyyy"),
				new SimpleDateFormat("MMMM d, yyyyy"),
				new SimpleDateFormat("MMM d, yyyyy")};

		return temp;
	}
	//-----------------------------------------------------------------------
	/**
	 * Gets the array of SimpleDateFormats that DateUtil knows about.
	**/
	private static SimpleDateFormat[] getFormats()
	{
		return mDateFormats;
	}

	//-----------------------------------------------------------------------
	/**
	 * Returns a Date set to the last possible millisecond of the day, just
	 * before midnight. If a null day is passed in, a new Date is created.
	 * midnight (00m 00h 00s)
	 */
	public static Date getEndOfDay(Date day)
	{
		return getEndOfDay(day, Calendar.getInstance());
	}
	public static Date getEndOfDay(Date day, Calendar cal)
	{
		if (day == null)
			day = new Date();
		cal.setTime(day);
		cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
		return cal.getTime();
	}

	//-----------------------------------------------------------------------
	/**
	 * Returns a Date set to the first possible millisecond of the day, just
	 * after midnight. If a null day is passed in, a new Date is created.
	 * midnight (00m 00h 00s)
	 */
	public static Date getStartOfDay(Date day)
	{
		return getStartOfDay(day, Calendar.getInstance());
	}
	/**
	 * Returns a Date set to the first possible millisecond of the day, just
	 * after midnight. If a null day is passed in, a new Date is created.
	 * midnight (00m 00h 00s)
	 */
	public static Date getStartOfDay(Date day, Calendar cal)
	{
		if (day == null)
			day = new Date();
		cal.setTime(day);
		cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
		return cal.getTime();
	}

	/**
	 * Returns a Date set just to Noon, to the closest possible millisecond
	 * of the day. If a null day is passed in, a new Date is created.
	 * nnoon (00m 12h 00s)
	 */
	public static Date getNoonOfDay(Date day, Calendar cal)
	{
		if (day == null)
			day = new Date();
		cal.setTime(day);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
		return cal.getTime();
	}

	/**
	 * ���ݴ���������ַ���ת������Ӧ�����ڶ�������ַ���Ϊ�ջ򲻷������ڸ�
	 * ʽ���򷵻ص�ǰʱ�䡣
	 * @param strDate String �����ַ���
	 * @return java.util.Date ���ڶ���
	 * */
	public static java.util.Date getDateFromString(String strDate)
	{
		if ( StringUtils.isEmpty(strDate))
		{
			return new java.util.Date(System.currentTimeMillis());
		}
		try
		{
			return sdfLongTimePlus.parse(strDate);
		}
		catch (Exception ex)
		{
			return new java.sql.Timestamp(System.currentTimeMillis());
		}
	}
	
	//-----------------------------------------------------------------------
	public static Date parseFromFormats(String aValue)
	{
		if (StringUtil.isEmpty(aValue))
			return null;

		// get DateUtil's formats
		SimpleDateFormat formats[] = DateUtil.getFormats();
		if (formats == null)
			return null;

		// iterate over the array and parse
		Date myDate = null;
		for (int i = 0; i < formats.length; i++)
		{
			try
			{
				myDate = DateUtil.parse(aValue, formats[i]);
				//if (myDate instanceof Date)
				return myDate;
			}
			catch (Exception e)
			{
				// do nothing because we want to try the next
				// format if current one fails
			}
		}
		// haven't returned so couldn't parse
		return null;
	}

	//-----------------------------------------------------------------------
	public static java.sql.Timestamp parseTimestampFromFormats(String aValue)
	{
		if (StringUtil.isEmpty(aValue))
			return null;

		// call the regular Date formatter
		Date myDate = DateUtil.parseFromFormats(aValue);
		if (myDate != null)
			return new java.sql.Timestamp(myDate.getTime());
		return null;
	}
	//-----------------------------------------------------------------------
	/**
	 * Returns a java.sql.Timestamp equal to the current time
	**/
	public static java.sql.Timestamp now()
	{
		return new java.sql.Timestamp(new java.util.Date().getTime());
	}

	//-----------------------------------------------------------------------
	/**
	 * Returns a string the represents the passed-in date parsed
	 * according to the passed-in format.  Returns an empty string
	 * if the date or the format is null.
	**/
	public static String format(Date aDate, SimpleDateFormat aFormat)
	{
		if (aDate == null || aFormat == null)
		{
			return "";
		}
		synchronized (aFormat)
		{
			return aFormat.format(aDate);
		}
	}

	//-----------------------------------------------------------------------
	/**
	 * Tries to take the passed-in String and format it as a date string in the
	 * the passed-in format.
	**/
	public static String formatDateString(String aString, SimpleDateFormat aFormat)
	{
		if (StringUtil.isEmpty(aString) || aFormat == null)
			return "";
		try
		{
			java.sql.Timestamp aDate = parseTimestampFromFormats(aString);
			if (aDate != null)
			{
				return DateUtil.format(aDate, aFormat);
			}
		}
		catch (Exception e)
		{
			// Could not parse aString.
		}
		return "";
	}

	//-----------------------------------------------------------------------
	/**
	 * Returns a Date using the passed-in string and format.  Returns null if the string
	 * is null or empty or if the format is null.  The string must match the format.
	**/
	public static Date parse(String aValue, SimpleDateFormat aFormat) throws ParseException
	{
		if (StringUtil.isEmpty(aValue) || aFormat == null)
		{
			return null;
		}

		return aFormat.parse(aValue);
	}

	//-----------------------------------------------------------------------
	/**
	 * Returns true if endDate is after startDate or if startDate equals endDate
	 * or if they are the same date.  Returns false if either value is null.
	**/
	public static boolean isValidDateRange(Date startDate, Date endDate)
	{
		return isValidDateRange(startDate, endDate, true);
	}

	//-----------------------------------------------------------------------
	/**
	 * Returns true if endDate is after startDate or if startDate equals endDate.
	 * Returns false if either value is null.  If equalOK, returns true if the
	 * dates are equal.
	**/
	public static boolean isValidDateRange(Date startDate, Date endDate, boolean equalOK)
	{
		// false if either value is null
		if (startDate == null || endDate == null)
		{
			return false;
		}

		if (equalOK)
		{
			// true if they are equal
			if (startDate.equals(endDate))
			{
				return true;
			}
		}

		// true if endDate after startDate
		if (endDate.after(startDate))
		{
			return true;
		}

		return false;
	}

	//-----------------------------------------------------------------------
	// returns full timestamp format
	public static java.text.SimpleDateFormat defaultTimestampFormat()
	{
		return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	}

	//-----------------------------------------------------------------------
	// convenience method returns minimal date format
	public static java.text.SimpleDateFormat get8charDateFormat()
	{
		return DateUtil.mFormat8chars;
	}

	//-----------------------------------------------------------------------
	// convenience method returns minimal date format
	public static java.text.SimpleDateFormat defaultDateFormat()
	{
		return DateUtil.friendlyDateFormat(true);
	}

	//-----------------------------------------------------------------------
	// convenience method
	public static String defaultTimestamp(Date date)
	{
		return DateUtil.format(date, DateUtil.defaultTimestampFormat());
	}

	//-----------------------------------------------------------------------
	// convenience method
	public static String defaultDate(Date date)
	{
		return DateUtil.format(date, DateUtil.defaultDateFormat());
	}

	//-----------------------------------------------------------------------
	// convenience method returns long friendly timestamp format
	public static java.text.SimpleDateFormat friendlyTimestampFormat()
	{
		return new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	}

	//-----------------------------------------------------------------------
	// convenience method returns long friendly formatted timestamp
	public static String friendlyTimestamp(Date date)
	{
		return DateUtil.format(date, DateUtil.friendlyTimestampFormat());
	}

	//-----------------------------------------------------------------------
	// convenience method returns long friendly formatted timestamp
	public static String format8chars(Date date)
	{
		return DateUtil.format(date, mFormat8chars);
	}

	//-----------------------------------------------------------------------
	// convenience method returns long friendly formatted timestamp
	public static String formatIso8601Day(Date date)
	{
		return DateUtil.format(date, mFormatIso8601Day);
	}
	public static String formatIso8601Day(Timestamp timestamp)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestamp.getTime());
		return DateUtil.format(calendar.getTime(), mFormatIso8601Day);
	}
	public static String formatTradeEasy(Date date)
	{
		return DateUtil.format(date, mFormatTradeEasy);
	}
	//add by huyanzhi
	public static String formatTradeEasyProduct(Date date)
	{
		return DateUtil.format(date, mFormatTradeEasyProduct);
	}
	//

	public static String formatFormatTradeEasyMMddyyyy(Date date)
	{
		return DateUtil.format(date, mFormatTradeEasyMMddyyyy);
	}

	public static String formatTradeEasy(Timestamp timestamp)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestamp.getTime());
		return DateUtil.format(calendar.getTime(), mFormatTradeEasy);
	}
	//-----------------------------------------------------------------------
	public static String formatRfc822(Date date)
	{
		return DateUtil.format(date, mFormatRfc822);
	}

	public static String formatExpire(Date date)
	{
		return DateUtil.format(date, mFormatExpire);
	}

	//-----------------------------------------------------------------------
	// This is a hack, but it seems to work
	public static String formatIso8601(Date date)
	{
		if (date == null)
			return "";

		// Add a colon 2 chars before the end of the string
		// to make it a valid ISO-8601 date.

		String str = DateUtil.format(date, mFormatIso8601);
		StringBuffer sb = new StringBuffer();
		sb.append(str.substring(0, str.length() - 2));
		sb.append(":");
		sb.append(str.substring(str.length() - 2));
		return sb.toString();
	}

	//-----------------------------------------------------------------------
	// convenience method returns minimal date format
	public static java.text.SimpleDateFormat minimalDateFormat()
	{
		return DateUtil.friendlyDateFormat(true);
	}

	//-----------------------------------------------------------------------
	// convenience method using minimal date format
	public static String minimalDate(Date date)
	{
		return DateUtil.format(date, DateUtil.minimalDateFormat());
	}

	//-----------------------------------------------------------------------
	// convenience method that returns friendly data format
	// using full month, day, year digits.
	public static java.text.SimpleDateFormat fullDateFormat()
	{
		return DateUtil.friendlyDateFormat(false);
	}

	//-----------------------------------------------------------------------
	public static String fullDate(Date date)
	{
		return DateUtil.format(date, DateUtil.fullDateFormat());
	}

	//-----------------------------------------------------------------------
	/** Returns a "friendly" date format.
	 *  @param mimimalFormat Should the date format allow single digits.
	**/
	public static java.text.SimpleDateFormat friendlyDateFormat(boolean minimalFormat)
	{
		if (minimalFormat)
		{
			return new java.text.SimpleDateFormat("d.M.yy");
		}

		return new java.text.SimpleDateFormat("dd.MM.yyyy");
	}

	//-----------------------------------------------------------------------
	/**
	 * Format the date using the "friendly" date format.
	 */
	public static String friendlyDate(Date date, boolean minimalFormat)
	{
		return DateUtil.format(date, DateUtil.friendlyDateFormat(minimalFormat));
	}

	//-----------------------------------------------------------------------
	// convenience method
	public static String friendlyDate(Date date)
	{
		return DateUtil.format(date, DateUtil.friendlyDateFormat(true));
	}

	public static Date parseFormatIso8601Date(String date) throws Exception
	{
		Date returnDate = null;
		try
		{
			returnDate = mFormatIso8601Day.parse(date);
		}
		catch (Exception e)
		{
			throw e;
		}
		return returnDate;
	}
	//add by huyanzhi
/**
	public static String addDate(String date, String type, int into) throws Exception
	{
		String Sdate = "";
		try
		{
			GregorianCalendar grc = new GregorianCalendar();
			grc.setTime(new Date(date));
			if (type.equals("D"))
			{
				grc.add(GregorianCalendar.DATE, into);
			}
			else if (type.equals("M"))
			{
				grc.add(GregorianCalendar.MONTH, into);
			}
			else if (type.equals("Y"))
			{
				grc.add(GregorianCalendar.YEAR, into);
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Sdate = new String(formatter.format(grc.getTime()));
		}
		catch (Exception e)
		{
			throw e;
		}
		return Sdate;
	}
*/
/**
	public static String addDate(String date, String into) throws Exception
	{
		String Sdate = "";
		try
		{
			date = date.replaceAll("-", "/");
			date = date.substring(0, date.length() - 2);
			GregorianCalendar grc = new GregorianCalendar();
			grc.setTime(new Date(date));
			grc.add(GregorianCalendar.DATE, Integer.parseInt(into));
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Sdate = new String(formatter.format(grc.getTime()));
		}
		catch (Exception e)
		{
			throw e;
		}
		return Sdate;
	}
*/
	public static String formatDate(Date date, String pattern)
	{
		if (pattern == null || pattern.equals("") || pattern.equals("null"))
		{
			pattern = "yyyy-MM-dd";
		}
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	//
/**
	public static String addValidateDate(String date, String into) throws Exception
	{
		String Sdate = "";
		try
		{
			date = date.replaceAll("-", "/");
			date = date.substring(0, date.length() - 2);
			GregorianCalendar grc = new GregorianCalendar();
			grc.setTime(new Date(date));
			grc.add(GregorianCalendar.DATE, Integer.parseInt(into));
			Sdate = new String(mFormatExpire.format(grc.getTime()));
		}
		catch (Exception e)
		{
			throw e;
		}
		return Sdate;
	}
*/	
	public static String addDayToStringDate(String formate,String strDate, String days) {
		String stringDate = null;
		try {
			Date date = fromStringToDate(formate,strDate);
			long now = date.getTime() + (long) Integer.parseInt(days) * DAY_IN_MILLISECOND;
			 
			stringDate =  getFomartDate(new Date(now),formate);
			
		} catch (ParseException e) {

			e.printStackTrace();
		}
		
		return stringDate;
	}

	public static Date addDayToStringDate2(String formate,String strDate, String days) {
		Date date = null;
		try {
			date = fromStringToDate(formate,strDate);
			long now = date.getTime() + (long) Integer.parseInt(days) * DAY_IN_MILLISECOND;
			 
			date =  new Date(now);
			
		} catch (ParseException e) {

			e.printStackTrace();
		}
		
		return date;
	}
	
	public static Date dateDayAdd(Date date, int days) {
		long now = date.getTime() + (long) days * DAY_IN_MILLISECOND;
		return new Date(now);
	}
	/**
    *
    * �ַ�����ʽת��ΪDate����
    * String���Ͱ���format��ʽתΪDate����
    **/
    public static Date fromStringToDate(String format,String dateTime) throws ParseException{
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        date = sdf.parse(dateTime);
        return date;        
    }
    
	/**
    *
    * �ַ�����ʽת��ΪDate����
    * String���Ͱ���format��ʽתΪDate����
    **/
    public static Date fromStringToDate(Date date) throws ParseException
    {
        return sdfLongTimePlus.parse(sdfLongTimePlus.format(date));
    }

	public static void main(String[] args)
	{
		try
		{
			System.out.println(DateUtil.toDayToStr("yyyy��MM��dd��"));
			
			//			System.out.println("�ҵ����䣺" + DateUtil.getAge("210202720508171"));
			//			System.out.println("�ҵ����䣺" + DateUtil.getAge("210202197205081712"));
			//			int x = 31;
			//			java.sql.Date date = DateUtil.getDateByAge(x);
			//			System.out.println("31���˵ĳ������ڣ�" + date.toString());
			//			System.out.println(
			//				"========getYearMonth:" + DateUtil.getYearAndMonth("200406"));
			//			System.out.println("====" + DateUtil.month2YearMonth("15"));
			//			System.out.println(
			//				"========increaseMonth: "
			//					+ DateUtil.increaseMonth(DateUtil.getCurrentDate(), 2));

			//						Date data = new Date();
			//						System.out.println(data);
			//						System.out.println(
			//							"����==" + DateUtil.dateToString(data, "YYYY-MM-DD HH24:MI:SS"));

			//			Date newdate = new Date();
			//			System.out.println(DateUtil.parseFromFormats(sdata));
			//			String sdata = "2005-01-01";
			//			System.out.println(
			//				DateUtil.stringToDate(sdata, "YYYY-MM-DD"));
			//			Date nowDate1 = new Date();
			//			Date nowDate2 = new Date();
			//			System.out.println("nowDate1" + nowDate1);
			//			System.out.println("nowDate2" + nowDate2);
			//			System.out.println(DateUtil.daysBetweenDates(nowDate1,nowDate2));
			//			System.out.println(
			//				"����50��==" + DateUtil.getSpecDate("yyyy/MM/dd", 0, 0, 50));
			//			System.out.println(
			//				DateUtil.getDefaultFormattedDate(
			//					DateUtil.getSpecDate("yyyy/MM/dd", 0, 0, 50)));
			//			System.out.println(
			//				"add 50 days" + DateUtil.increaseDay(new Date(), 365));
			System.out.println(getDateShortLongTimeCn(new Date()));
			System.out.println(null + "1");
			System.out.println(convertDateToDay(new Date()));
			Date nows =new Date();
			System.out.println("============"+getDateLongCn(nows));
			System.out.println("============0000000000000000000000000000000");
			System.out.println("============stringToDate="+stringToDate("2009-11-18 19:14:31","yyyy-MM-dd h24:mi:ss"));
			
			
			System.out.println("============getDateFromString="+getDateFromString("2009-11-18 19:14:31"));
			
			
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * 
	 * <br>
	 * <b>���ܣ�</b>����ʱ����:HHmmss<br>
	 * <b>���ߣ�</b>�����<br>
	 * <b>���ڣ�</b> Aug 26, 2011 <br>
	 * @param date
	 * @return 
	 */
	public static Integer getTimeFormatIntger(Date date){
		if(date == null){
			return 0;
		}
		String strTemp = DateUtil.getFomartDate(date, "yyyyMMddHHmmss");
		String nowTime = strTemp.substring(8,14);
		return Integer.valueOf(nowTime); 
	}

	public static String getNowDayStr(Date date){
		if(date == null){
			return "";
		}
		
		Calendar c=Calendar.getInstance();
		int i =  c.get(Calendar.DAY_OF_WEEK);
		System.out.println(i);
//		String strTemp = DateUtil.getFomartDate(date, "yyyyMMddHHmmss");
//		String nowTime = strTemp.substring(8,14);
		return "";
	}
	
	
	 /**
     * @param date   
     * @param format ���ڸ�ʽ
     * @return String
     * @author zhangyong
     * @return String 
     */
	public static String toDayToStr(String format) {
		try {
			Date now = new Date();
			return DateToStr( now,format) +" "+ getWeekOfDate(now);
		} catch (Exception e) {
    		System.out.println("Date ת String ����ʧ��: " + e);
			return null;
		}
	}
	
	 /**
     * @param date   
     * @param format ���ڸ�ʽ
     * @return String
     * @author zhangyong
     * @return String 
     */
	public static String DateToStr(Date date, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} catch (Exception e) {
    		System.out.println("Date ת String ����ʧ��: " + e);
			return null;
		}
	}
	
	
	
	
	
	
	/**
	 * @author zhangyong
	 * @return DATE �ͼӾ��������
	 * 
	 * @param Date date, int days*/
	public static Date dateAddDays(Date date, int days) {
		long now = date.getTime() + (long) days * DAY_IN_MILLISECOND;
		return new Date(now);
	}
	
	
	
	/**@return ��DATE ת�����ַ������ڸ�ʽ
	 * @author zhangyong
	 * @param Date date,String fFormatStr eg:yyyy-MM-dd HH:mm:ss */
	public static String dateTypeToString(Date date,String fFormatStr){
		//yyyy-MM-dd HH:mm:ss
		SimpleDateFormat dateformat=new SimpleDateFormat(fFormatStr);
		String strDate=dateformat.format(date);
		return strDate;
	}
	
	/**@param yyyy-MM-dd 
	 * @author zhangyong
	 * @��ȡ��ǰ��ϵͳʱ�䣬�����չ̶��ĸ�ʽ��ʼ��*/
	public static String getStringOfNowDate(String fFormatStr){
		String nowDateString=dateTypeToString(new Date(),fFormatStr);
		return nowDateString;
	}
	
	
	/** 
	 * @ author zhangyong
	 * @ ��ȡ���µĵ�һ�죬2009-05-01*/
	public static String getStringOfFirstDayInMonth() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String temp = sdf.format(date);
		String firstDayInMoth = "";
		firstDayInMoth = temp + "-01";
		
		return firstDayInMoth;

	}
	
	
	
	 /**
     * ��ȡ��ǰ���������ڼ�<br>
     * 
     * @param dt
     * @return ��ǰ���������ڼ�
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"������", "����һ", "���ڶ�", "������", "������", "������", "������"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

   
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }
}
