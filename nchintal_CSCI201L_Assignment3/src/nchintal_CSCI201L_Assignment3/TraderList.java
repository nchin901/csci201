package nchintal_CSCI201L_Assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TraderList {
	private static ArrayList<TraderInfo> traders = new ArrayList<TraderInfo>();

	public TraderList(String fileName) throws FileNotFoundException, IOException {
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
		TraderInfo traderInfo = null;
		for (int i = 0; i < s.length; i += 2) {
			Double serialString = null;
			Double balanceString = null;
			for (int j = 0; j < 2; j++) {
				if (j % 2 == 0) {
					serialString = Double.valueOf(s[i]);
					// System.out.println(timeString);
				} else {
					balanceString = Double.valueOf(s[i + 1]);
					// System.out.println(companyString);
				}
			}
			traderInfo = new TraderInfo(serialString, balanceString);
			traders.add(traderInfo);
		}
	}

	public ArrayList<TraderInfo> getInfo() {
		return traders;
	}

	public int howManyThreads() {
		// TODO Auto-generated method stub
		return traders.size();
	}
}
