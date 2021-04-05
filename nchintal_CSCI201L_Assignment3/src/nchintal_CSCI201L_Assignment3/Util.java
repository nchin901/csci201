package nchintal_CSCI201L_Assignment3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
public class Util{
	public static String getCurrentTime(){
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss.ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = new Date();
		String time = dateFormat.format(date);
		return time;
	   }
}
