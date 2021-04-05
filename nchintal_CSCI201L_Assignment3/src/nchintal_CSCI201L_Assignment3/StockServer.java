package nchintal_CSCI201L_Assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

public class StockServer extends Thread {
	private static List<ServerThread> serverThreads;
	private static Scanner scan = new Scanner(System.in);
	private static String fileName;
	private static TraderList traderList;
	private static ScheduleList scheduleList;
	private static ArrayList<Integer> tradersleft;
	private static ArrayList<Double> traderbalance;

	public StockServer(int port) {
		int numtraders = geTraderList().howManyThreads();
		try {
			ServerSocket serverSocket = new ServerSocket(3456);
			System.out.println("Listening on port 3456.");

			serverThreads = Collections.synchronizedList(new ArrayList<ServerThread>());
			System.out.println("Waiting for traders... \n");
			for (int i = 0; i < numtraders; i++) {
				Socket socket = serverSocket.accept();
				System.out.println("Connection from " + socket.getInetAddress());

				int tradersLeft = numtraders - i - 1;

				ServerThread serverThread = new ServerThread(socket, this);
				serverThreads.add(serverThread);

				serverThread.sendMessage(traderList.getInfo().get(i).getSerialNumber() + ","
						+ traderList.getInfo().get(i).getInitialBalance());
				if (i < numtraders-1) {
					System.out.println("Waiting for " + (numtraders - i) + " traders. \n");
					broadcast((tradersLeft) + "more traders needed. \n Waiting...", null);
				} else {
					System.out.println("");
				}
			}
			System.out.println("Starting service.");
			broadcastServerMessage("All traders have arrived! \n Starting service.");
			

			this.start();
			serverSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		Date start = Calendar.getInstance().getTime();
		while(!(scheduleList.getInfo().size() == 0)) {
			try {
				
				for(int i = 0; i < scheduleList.getInfo().size(); i++) {
					int nextup = Integer.parseInt(scheduleList.getInfo().get(0).getWhen());
					Date now = Calendar.getInstance().getTime();
					long diff = (now.getTime() - start.getTime()) / 1000;
					
					// wait until it is time for the next trade to be made
					while(diff < nextup) {
						diff = (Calendar.getInstance().getTime().getTime() - start.getTime()) / 1000;
						Thread.yield();
					}
				}
				
				//initialize balances and traders
				tradersleft = new ArrayList<Integer>();
				traderbalance = new ArrayList<Double>();
				
				for(int i = 0; i < numtraders; i++) {
					traderbalance.add(0.0);
					tradersleft.add(1);
				}
				
				//yield until there are traders available!
				while(numtraders == 0) {
					Thread.yield();
				}
				
				while (/*end.getTime() - st.getTime() > ct.getTime()*/ true) {
					if (scheduleList.getInfo().size() > 0) {
						for (int i = 0; i < serverThreads.size(); i++) {
							String line = serverThreads.get(i).getMessage();
							System.out.println(line);
							System.out.println(serverThreads.get(i).active == true);
							
							String[] info = line.split(",");
							tradersleft.set(i, Integer.parseInt(info[2]));
							traderbalance.set(i, Double.parseDouble(info[1]));
						}
						for (int i = 0; i < numtraders; i++) {
							if (tradersleft.get(i) == 1) {
								int w = 0;
								while (traderbalance.get(i) - (scheduleList.getInfo().get(w).getAmount()) * 
										scheduleList.getInfo().get(w).getAmount() > 0) {
									w++;
								}
								for (int p = 0; p < w; p++) {
									broadcastTo("NP," + w + "," + scheduleList.getInfo().get(i).getAmount() + "," + 
											scheduleList.getInfo().get(i).getStock() + "," + scheduleList.getInfo().get(i).getPrice(), 
											serverThreads.get(i));
								}
							}
						}
					}
					ct = new Date();
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		loadScheduleFile();
		loadTraderfile();
		StockServer server = new StockServer(3456);
	}

	// method to load schedule.csv
	private static void loadScheduleFile() {
		boolean validFile = false;
		do {
			System.out.println("What is the path of the schedule file?");
			fileName = scan.nextLine();
			try {
				setScheduleList(new ScheduleList(fileName));
				System.out.println("The file has been properly read. \n");
				validFile = true;
			} catch (ClassCastException e) {
				System.out.println("The file " + fileName + " is not formatted properly.\n");
			} catch (FileNotFoundException e) {
				System.out.println("The file " + fileName + " cannot be found.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (!validFile);
	}

	// method to load traders.csv
	private static void loadTraderfile() {
		boolean validFile = false;
		do {
			System.out.println("What is the path of the traders file?");
			fileName = scan.nextLine();
			try {
				setTraderList(new TraderList(fileName));
				System.out.println("The file has been properly read. \n");
				validFile = true;
			} catch (ClassCastException e) {
				System.out.println("The file " + fileName + " is not formatted properly. \n");
			} catch (FileNotFoundException e) {
				System.out.println("The file " + fileName + " cannot be found.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (!validFile);
	}

	public static void request(StockInfo stockInfo) throws Exception {
		String lineString = "";
		URL url = new URL(
				"https://api.tiingo.com/tiingo/daily/AMD/prices?startDate=2021-02-19&endDate=2021-02-19&token=8f2b1fe67957b9ae4983fc15d94fc1ed04b9895b");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("accept", "application/json");
		InputStream istream = connection.getInputStream();
		InputStreamReader iStreamReader = new InputStreamReader(istream);

		BufferedReader bReader = new BufferedReader(iStreamReader);
		lineString = bReader.readLine();
		lineString = lineString.substring(1, lineString.length() - 1);
		lineString = lineString.replace('\"', '\'');
		bReader.close();

		Gson gson = new Gson();
		StockInfo stock = new StockInfo();
		stock = gson.fromJson(lineString, StockInfo.class);
		System.out.println(stock.getClose());
	}

	public void broadcast(String message, ServerThread st) {
		if (message != null && message.length() > 0) {
			if (st != null) {
				System.out.println(message);
			}
			synchronized (serverThreads) {
				for (ServerThread thread : serverThreads) {
					if (st != thread) {
						thread.sendMessage(message);
					}
				}
			}
		}
	}

	public void broadcastServerMessage(String message) {
		if (message != null && message.length() > 0) {
			System.out.println(message);
			synchronized (serverThreads) {
				for (ServerThread thread : serverThreads) {
					thread.sendMessage(message);

				}
			}
		}
	}

	public static void setTraderList(TraderList traderList) {
		StockServer.traderList = traderList;
	}

	public static TraderList geTraderList() {
		return traderList;
	}

	public static void setScheduleList(ScheduleList scheduleList) {
		StockServer.scheduleList = scheduleList;
	}

	public static ScheduleList getScheduleList() {
		return scheduleList;
	}
}
