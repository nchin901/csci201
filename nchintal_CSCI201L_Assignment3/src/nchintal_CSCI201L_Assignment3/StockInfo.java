package nchintal_CSCI201L_Assignment3;

import java.util.concurrent.Semaphore;

public class StockInfo {
	private String date;
	private Double open;
	private Double high;
	private Double low;
	private Double close;
	private int volume;
	private Double adjOpen;
	private Double adjClose;
	private Double adjHigh;
	private Double adjLow;
	private int adjVolume;
	private Double divCash;
	private Double splitFactor;
    private transient Semaphore brokers;
    
    public StockInfo() {
    	this.date = null;
        this.open = 0.0;
        this.high = 0.0;
        this.adjClose = 0.0;
        this.adjHigh = 0.0;
        this.volume = 0;
        this.adjVolume = 0;
        this.close = 0.0;
        this.low = 0.0;
        this.divCash = 0.0;
        this.splitFactor = 0.0;
        this.adjOpen = 0.0;
        this.adjLow = 0.0;
    }

    public StockInfo(String date, Double close, Double high, Double low, Double open, int volume, Double adjClose, Double adjHigh, Double adjLow, Double adjOpen, int adjVolume, Double divCash, Double splitFactor) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.adjClose = adjClose;
        this.adjHigh = adjHigh;
        this.volume = volume;
        this.adjVolume = adjVolume;
        this.close = close;
        this.low = low;
        this.divCash = divCash;
        this.splitFactor = splitFactor;
        this.adjOpen = adjOpen;
        this.adjLow = adjLow;
    }
   
    public Semaphore getSemaphore() {
    	return brokers;
    }
   public String getDate() {
	   return date;
   }
   
   public Double getOpen() {
	   return open;
   }
   
   public Double getHigh() {
	   return high;
   }
   
   public Double getLow() {
	   return low;
   }
   
   public Double getClose() {
	   return close;
   }
   
   public int getVolume() {
	   return volume;
   }
   
   public Double returnAdjOpen() {
	   return adjOpen;
   }
   
   public Double getAdjHigh() {
	   return adjHigh;
   }
   
   public Double getAdjLow() {
	   return adjLow;
   }
   
   public Double getAdjClose() {
	   return adjClose;
   }
   
   public int getAdjVolume() {
	   return adjVolume;
   }
   
   public Double getDiv() {
	   return divCash;
   }
   
   public Double getSplit() {
	   return splitFactor;
   }
}
