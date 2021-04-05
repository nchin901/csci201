package assignment2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BrokerList {
	private ArrayList<BrokerInfo> threads = new ArrayList<BrokerInfo>();

	public BrokerList(String fileName) throws FileNotFoundException, IOException{
		FileReader fileReader;
		String lineString = "";
		String splitByString = ",";
		fileReader = new FileReader(fileName);
		BufferedReader bReader = new BufferedReader(fileReader);
		while((lineString = bReader.readLine()) != null) {
			String [] scheduleStrings = lineString.split(splitByString);
			setInfo(scheduleStrings);
		}bReader.close();	
	}
	
	public void setInfo(String [] s) {
		BrokerInfo brokerInfo = null;
		for(int i = 0; i < s.length; i+=3) {
			String timeString = null;
			String companyString = null;
			String tradeString = null;
			for(int j = 0; j < 3; j++) {
				if(j % 3 == 0) {
					timeString = s[i];
					//System.out.println(timeString);
				}
				else if(j % 3 == 1) {
					companyString = s[i+1];
					//System.out.println(companyString);
				}
				else {
					tradeString = s[i+2];
					//System.out.println(tradeString);
				}
			}
			brokerInfo= new BrokerInfo(timeString, companyString, tradeString);
			//System.out.println(brokerInfo.getAmount());
			threads.add(brokerInfo);
		}
	}
	
	public ArrayList<BrokerInfo> getInfo(){
		return threads;
	}
	
	public Integer howManyThreads() {
		//System.out.println(threads.size());
		return threads.size();
	}
}
