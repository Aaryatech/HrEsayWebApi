package com.ats.hrmgt.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ats.hrmgt.model.EmpSalaryInfo;

public class DateConvertor {

	
		public static String convertToYMD(String date) {
			
			String convertedDate=null;
			try {
				SimpleDateFormat ymdSDF = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat dmySDF = new SimpleDateFormat("dd-MM-yyyy");
				Date dmyDate = dmySDF.parse(date);
				
				convertedDate=ymdSDF.format(dmyDate);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return convertedDate;

		}
		
	public static String convertToDMY(String utildate) {
			
			String convertedDate=null;
			try {
				SimpleDateFormat ymdSDF = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat ymdSDF2 = new SimpleDateFormat("yyyy-MM-dd");

				
				SimpleDateFormat dmySDF = new SimpleDateFormat("dd-MM-yyyy");

				Date ymdDate = ymdSDF2.parse(utildate);
				
				convertedDate=dmySDF.format(ymdDate);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return convertedDate;

		}
		
	public static java.sql.Date convertToSqlDate(String date) {
			
			java.sql.Date convertedDate=null;
			try {
				SimpleDateFormat ymdSDF = new SimpleDateFormat("yyyy-mm-dd");
				SimpleDateFormat dmySDF = new SimpleDateFormat("dd-MM-yyyy");

				Date dmyDate = dmySDF.parse(date);
				
				System.out.println("converted util date commons "+dmyDate);

				
				

				convertedDate=new java.sql.Date(dmyDate.getTime());
				System.out.println("converted sql date commons "+convertedDate);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return convertedDate;

		}

	
	
	public static List<String> getAllMonth(Date x) {
		
 		List<String> dateList = new ArrayList<String>();
		try {
			Date date = new Date();
			SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
			String currDate = sf1.format(date);

			SimpleDateFormat sf = new SimpleDateFormat("MM-yyyy");
 
			LocalDate localDate = LocalDate.parse((currDate));
			LocalDate oneMonthLater = localDate.plusMonths(1);
			String joinnigDate = sf.format(x);
 
			String currDateNew = String.valueOf(oneMonthLater.getMonthValue()).concat("-")
					.concat(String.valueOf(oneMonthLater.getYear()));

		 
			DateFormat formater = new SimpleDateFormat("MM-yyyy");

			Calendar beginCalendar = Calendar.getInstance();
			Calendar finishCalendar = Calendar.getInstance();
			try {
				beginCalendar.setTime(formater.parse(joinnigDate));
				finishCalendar.setTime(formater.parse(currDateNew));
			} catch (Exception e) {
				e.printStackTrace();
			}

		
			while (beginCalendar.before(finishCalendar)) {
				// add one month to date per loop
				String date1 = formater.format(beginCalendar.getTime()).toUpperCase();
				System.out.println(date1);
				dateList.add(date1);
				beginCalendar.add(Calendar.MONTH, 1);
			}

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dateList;

	}

		
		

	}


