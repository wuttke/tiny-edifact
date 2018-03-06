package eu.wuttke.tinyedifact.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
    private static DateFormat SHORT_DATE_FORMAT = new SimpleDateFormat("yyMMdd");
    private static DateFormat SHORT_TIME_FORMAT = new SimpleDateFormat("HHmm");

	public synchronized static String formatShortDate(Date date) {
		return SHORT_DATE_FORMAT.format(date);
	}

	public synchronized static String formatShortTime(Date time) {
		return SHORT_TIME_FORMAT.format(time);
	}

}
