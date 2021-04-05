package nchintal_CSCI201L_Assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ScheduleList {
	
	private static ArrayList<ScheduleInfo> schedules = new ArrayList<ScheduleInfo>();

	public ScheduleList(String fileName) throws FileNotFoundException, IOException {
		FileReader fileReader;
		String lineString = "";
		String splitByString = ",";
		fileReader = new FileReader(fileName);
		BufferedReader bReader = new BufferedReader(fileReader);
		while ((lineString = bReader.readLine()) != null) {
			String[] scheduleStrings = lineString.split(splitByString);
			setInfo(scheduleStrings);
		}
		bReader.close();
	}

	public void setInfo(String[] s) {
		ScheduleInfo scheduleInfo = null;
		
		for (int i = 0; i < s.length; i += 4) {
			
			String timeString = null;
			String companyString = null;
			String tradeString = null;
			String dateString = null;
			
			for (int j = 0; j < 4; j++) {
				
				if (j % 4 == 0) {
					timeString = s[i];
					
				} else if (j % 4 == 1) {
					companyString = s[i + 1];
					
				} else if (j % 4 == 2) {
					tradeString = s[i + 2];
					
				} else {
					dateString = s[i + 3];
				}
			}
			scheduleInfo = new ScheduleInfo(timeString, companyString, tradeString, dateString);
			schedules.add(scheduleInfo);
		}
	}

	public ArrayList<ScheduleInfo> getInfo() {
		return schedules;
	}

	public static int howManyThreads() {
		return schedules.size();
	}
}
