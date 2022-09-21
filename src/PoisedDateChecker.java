import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * 
 * @author Dean Gulston
 * @version 1.0
 *
 */
public class PoisedDateChecker {

	/**
	 * Determines whether a given string is a date in the format YYYY/MM/DD.
	 * @param strDate: String - String that is being checked if it is a date.
	 * @return boolIsDate: boolean - Returns true if the string is a date in the format
	 * YYYY/MM/DD, and false if not.
	 */
	public static boolean isDate(String strDate) {
		boolean boolIsDate = true;
		
		// StringTokenizer uses "/" as a delimiter to distinguish year, month and day
		// in the string date.
		StringTokenizer tokenizer = new StringTokenizer(strDate, "/");
		
		try {
			String strYear = tokenizer.nextToken();
			String strMonth = tokenizer.nextToken();
			String strDay = tokenizer.nextToken();
			
			int year = Integer.parseInt(strYear);
			int month = Integer.parseInt(strMonth);
			int day = Integer.parseInt(strDay);
			
			// If the year does not have 4 digits, the month does not have 2 digits or the
			// day does not have 2 digits, then the string is not a date.
			if(strYear.length() != 4 || strMonth.length() != 2 || strDay.length() != 2) {
				boolIsDate = false;
			}
			
			// If the year, month or day is out of range, then the string is not a date.
			if(year < 0 || year > 9999 || month < 1 || month > 12 || day < 1 || day > 31) {
				boolIsDate = false;
			}
			
			// If the day is greater than 30 for month 4, 6, 9 or 11, then the string is
			// not a date.
			if(month == 4 || month == 6 || month == 9 || month == 11) {
				if(day > 30) {
					boolIsDate = false;
				}
			}
			
			// If the year is a leap year and the day is greater than 29 for month 2, then
			// the string is not a date.
			if(isLeapYear(year) && month == 2 && day > 29) {
				boolIsDate = false;
			}
			
			// If the year is not a leap year and the day is greater than 28 for month 2, then
			// the string is not a date.
			if(!isLeapYear(year) && month == 2 && day > 28) {
				boolIsDate = false;
			}
			
			// If the string has more than 3 tokens, the string is not a date.
			if(tokenizer.hasMoreTokens()) {
				boolIsDate = false;
			}
			
		}
		catch (NoSuchElementException e) {
			// If the string has less than 3 tokens, the string is not a date.
			boolIsDate = false;
		}
		catch (NumberFormatException e) {
			// If the year, month or day are not integers, the string is not a date.
			boolIsDate = false;
		}
		
		return boolIsDate;
	}
	
	/**
	 * Determines if a year is a leap year.
	 * @param year: int - The year that is being checked if it is a leap year.
	 * @return boolIsLeapYear: boolean - Returns true if the year is a leap and
	 * false if not.
	 */
	public static boolean isLeapYear(int year) {
		boolean boolIsLeapYear = false;
		
		if(year % 4 == 0) {
			
			if(year % 100 == 0) {
				
				if(year % 400 == 0) {
					boolIsLeapYear = true;
				}
				
			}
			else {
				boolIsLeapYear = true;
			}
			
		}
		
		return boolIsLeapYear;
	}
	
}
