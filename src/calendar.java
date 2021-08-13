import java.util.Scanner;
import java.util.Date;
import java.util.HashMap;
import java.text.ParseException;

public class calendar {

	private static final int[] MAX_DAYS = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static final int[] LEAP_MAX_DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	
	private HashMap <Date, PlanItem> planMap;
	
	public calendar() {
		planMap = new HashMap<Date,PlanItem>();
	}
	
	/**
 * 
 * @param date ex: "2021-08-11"
 * @param plan
 * @throws ParseException 
 * 
 */
	public void registerPlan(String strDate, String plan) throws ParseException {
		PlanItem p =new PlanItem(strDate, plan);
		planMap.put(p.getDate(), p);
		
	}
	
	public PlanItem searchPlan(String strDate) {
		Date date = PlanItem.getDatefromString(strDate);
		return planMap.get(date);

	}
	public boolean isLeapYear(int year) {

		if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))
			return true;
		else
			return false;
	}

	public int getmaxDaysOfMonth(int year, int month) {
		if (isLeapYear(year)) {
			return LEAP_MAX_DAYS[month];
		}
		return MAX_DAYS[month];
	}

//	- 1일의 요일을 입력받는다.
//	- 출력한다.
//	
//	년도를 입력하세요.
//	YEAR> 2021
//	달을 입력하세요.
//	MONTH> 8
//	첫번째 요일을 입력하세요. (SU, MO, WE, TH, FR, SA)
//	WEEKDAY> WE

	public void printCalendar(int year, int month) {
		System.out.printf("   <<%4d년 %3d월>>\n", year, month);
		System.out.println(" SU MO TU WE TH FR SA");
		System.out.println("---------------------");

//		get weekday automatically
		int weekday = getWeekday(year, month, 1);

		// print blank sapce
		for (int i = 0; i < weekday; i++) {
			System.out.print("   ");
		}
		int maxDay = getmaxDaysOfMonth(year, month);
		int count = 7 - weekday;
		int delim = (count < 7) ? count : 0;

//		if (count < 7) {
//			delim = count; }
//		esle {
//			delim = 0;
//		}

//		print first line
		for (int i = 1; i <= count; i++) {
			System.out.printf("%3d", i);
		}
		System.out.println();

//		print from second line to last
		count++;
		for (int i = count; i <= maxDay; i++) {
			System.out.printf("%3d", i);
			if (i % 7 == delim)
				System.out.println();
		}
		System.out.println();
		System.out.println();
//		System.out.println(" 1  2  3  4  5  6  7 ");
//		System.out.println(" 8  9 10 11 12 13 14");
//		System.out.println("15 16 17 18 19 20 21");
//		System.out.println("22 23 24 25 26 27 28");
	}

	private int getWeekday(int year, int month, int day) {
		int syear = 1970;

		final int STANDARD_WEEKDAY = 4; // 1970/Jan/1st/Thrusday

		int count = 0;

		for (int i = syear; i < year; i++) {
			int delta = isLeapYear(i) ? 366 : 365;
			count += delta;
		}

//	System.out.println(count);
		for (int i = 1; i < month; i++) {
			int delta = getmaxDaysOfMonth(year, i);
			count += delta;
		}
		
		count += day -1; 
		
		int weekday = (count + STANDARD_WEEKDAY) % 7;
		
		return weekday;
	}

//	simple test code here
	public static void main(String[] args) throws ParseException {
		calendar cal = new calendar();
		System.out.println(cal.getWeekday(1970, 1, 1) == 4);
		System.out.println(cal.getWeekday(1971, 1, 1) == 5);
		System.out.println(cal.getWeekday(1972, 1, 1) == 6);
		System.out.println(cal.getWeekday(1973, 1, 1) == 1);
		System.out.println(cal.getWeekday(1974, 1, 1) == 2);

		cal.registerPlan("2017-06-23", "Let's a eat beef!");
		System.out.println(cal.searchPlan("2021-08-11").equals("Let's eat beef!"));

	}
	
}
