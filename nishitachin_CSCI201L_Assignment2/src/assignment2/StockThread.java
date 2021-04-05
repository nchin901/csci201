package assignment2;

//import assignment2.StockReader;
import java.util.concurrent.Semaphore;

public class StockThread extends Thread {
	// how does stockthread get ref to semaphore
	// pass in stock as mem variable
	private Semaphore semaphore;
	private String tradeString;
	private String stockString;
	private String sleepString;

	public StockThread(Semaphore sem, String sleep, String stock, String trade) {
		this.semaphore = sem;
		this.sleepString = sleep;
		this.tradeString = trade;
		this.stockString = stock;
	}

	public void run() {
		// positive amount is a buy and a negative amount is a sell
		//LocalTime i1 = LocalTime.of(00, 00, 00, 00);
		try {
			long sLong = Long.parseLong(sleepString) * 1000;
			Thread.sleep(sLong);
			semaphore.acquire();
			if (tradeString.startsWith("-")) {
				System.out.println(
						"[" +  Util.getCurrentTime() + "]" + " Starting sale of " + tradeString.substring(1) + " stocks of " + stockString);
				Thread.sleep(1000);
				//LocalTime i4 = LocalTime.now();
				System.out.println(
						"[" +  Util.getCurrentTime() + "]"  + " Finished sale of " + tradeString.substring(1) + " stocks of " + stockString);
			} else {
				System.out.println(
						"[" +  Util.getCurrentTime() + "]" + " Starting purchase of " + tradeString + " stocks of " + stockString);
				Thread.sleep(1000);
				//LocalTime i4 = LocalTime.now();
				System.out.println(
						"[" +  Util.getCurrentTime() + "]"  + " Finished purchase of " + tradeString + " stocks of " + stockString);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//System.out.println("All trades completed!");
			semaphore.release();
		}
		
	}
}
