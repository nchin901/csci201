package nchintal_CSCI201L_Assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class StockClient extends Thread{
	private BufferedReader br;
	private PrintWriter pw;
	private ArrayList<ScheduleInfo> schedule;
	private ObjectInputStream is;
	
	public StockClient(String hostname, int port) {
		try {
			Socket s = new Socket(hostname, port);
			
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream());
			
			this.start();
			Scanner scan = new Scanner(System.in);
			
			while(true) {
				String line = scan.nextLine();
				pw.println("Donald: " + line);
				pw.flush();
			}
			
		} catch (IOException ioe) {
			System.out.println("ioe in ChatClient constructor: " + ioe.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public void run() {
		try {
			while(true) {
				String line = br.readLine();
				if (line == "continue") {
					schedule = (ArrayList<ScheduleInfo>) is.readObject();
				}
			}
		} catch (IOException | ClassNotFoundException ioe) {
			System.out.println("ioe in ChatClient.run(): " + ioe.getMessage());
		}
	}
	public static void main(String [] args) {
		System.out.println("Welcome to SalStocks V2.0! \n");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter host name: ");
		String hostname = scanner.nextLine();
		
		System.out.println("Enter port number: ");
		int port = scanner.nextInt();
		
		@SuppressWarnings("unused")
		StockClient sc = new StockClient(hostname, port);
		scanner.close();
	}
}
