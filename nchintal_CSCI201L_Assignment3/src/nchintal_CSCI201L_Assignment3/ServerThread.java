package nchintal_CSCI201L_Assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


public class ServerThread extends Thread {
	private PrintWriter pw;
	private BufferedReader br;
	private ObjectOutputStream os;
	private StockServer stockServer;
	public Boolean active;

	public ServerThread(Socket socket, StockServer server) {
		try {
			this.active = false;
			this.stockServer = server;
			this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.pw = new PrintWriter(socket.getOutputStream());
			this.os = new ObjectOutputStream(socket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.out.println("ioe in serverthread constructor" + e.getMessage());
		}
	}

	public void sendMessage(String message) {
		pw.println(message);
		pw.flush();
	}
	
	public void sendObject(ScheduleList scheduleList) throws IOException {
		os.writeObject(scheduleList);
		os.flush();
	}
	public String getMessage() {
		String line = "";
		try {
			line = br.readLine();
		}
		catch (Exception E) {
			System.out.println("Issue with getting message from client");
		}
		return line;
	}
	public void run() {
		try {
			while(true) {
				String line = br.readLine();
			}
		}
		catch(IOException ioe) {
			//thrown when a client disconnects
			System.out.println("ioe in ServerThread.run(): " + ioe.getMessage());
		}
	}

	
}
