package assignment2;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class StockReader extends Thread{
    private static Scanner scan;
    private static String fileName;
    private static StockList stockList;
    private static BrokerList brokerList;
    private static ConcurrentHashMap<String, Semaphore> map = new ConcurrentHashMap<String, Semaphore>();
    private static ArrayList<StockThread> handlerArrayList = new ArrayList<StockThread>();
    //hashmap that stores listing as the name of company
    //value is semaphore obj that says how many can access that
    //pass sem to thread
    
    //threads represent trades
    //trades take time
    //data structure that contains all the trades
    //
    public static void main(String[] args) {
    	StockThread st = null;
        scan = new Scanner(System.in);
        loadStocksFile();
        loadScheduleFile();
        System.out.println("Starting program execution....");
        Semaphore semaphore = new Semaphore(getBrokerList().howManyThreads());
        for(int i = 0; i < getBrokerList().getInfo().size(); i++) {
        	String tradeString = StockReader.getBrokerList().getInfo().get(i).getAmount();
        	String timeString = StockReader.getBrokerList().getInfo().get(i).getWhen();
        	String stockString = StockReader.getBrokerList().getInfo().get(i).getStock();
        	map.put(stockString, semaphore);
        	st = new StockThread(semaphore, timeString, stockString, tradeString);
        	handlerArrayList.add(st);
        }
        for(int i = 0; i < handlerArrayList.size(); i++) {
        	handlerArrayList.get(i).start();
        }
        for(StockThread s: handlerArrayList) {
        	try {
				s.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        System.out.println("All trades completed!");
    }

    private static void loadStocksFile() {
        boolean validFile = false;
        do {
            System.out.println("What is the name of file containing the company information?");
            fileName = scan.nextLine();

            try {
                stockList = new StockList(fileName);
                System.out.println("The file has been properly read.");
                validFile = true;
            } catch (IOException e) {
                System.out.println("The file " + fileName + " could not be found.\n");
            } catch (ClassCastException | JsonSyntaxException e){
                System.out.println("The file " + fileName + " is not formatted properly.\n");
            }
        }
        while (!validFile);
    }
    //method to read in the csv file
    private static void loadScheduleFile() {
    	boolean validFile = false;
    	do {
    		System.out.println("What is the name of the file containing the schedule information?");
    		fileName = scan.nextLine();
    		try {
    			setBrokerList(new BrokerList(fileName));
    			System.out.println("The file has been properly read.");
    			validFile = true;
    		}catch (ClassCastException e){
                System.out.println("The file " + fileName + " is not formatted properly.\n");
            } catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	while (!validFile);
    }
    public static void assignSemaphore() {
    	//Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	//semaphores is stock brokers and threads are the trades
    	for(Stock s: stockList.getStocks()) {
    		s.setSemaphore();
    	}
    }
    public static ConcurrentHashMap<String, Semaphore> returnMap(){
    	return map;
    }
	public static BrokerList getBrokerList() {
		return brokerList;
	}

	public static void setBrokerList(BrokerList brokerList) {
		StockReader.brokerList = brokerList;
	}

}
