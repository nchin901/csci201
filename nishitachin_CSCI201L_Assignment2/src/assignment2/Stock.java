package assignment2;

import java.util.concurrent.Semaphore;

public class Stock {
    private String name;
    private String ticker;
    private String startDate;
    private Integer stockBrokers;
    private String description;
    private String exchangeCode;
    private transient Semaphore brokers;

    public Stock(String name, String ticker, String startDate, Integer stockBrokers, String exchangeCode, String description) {
        this.name = name;
        this.ticker = ticker;
        this.startDate = startDate;
        this.stockBrokers = stockBrokers;
        this.description = description;
        this.exchangeCode = exchangeCode;
    }
    public void setSemaphore() {
    	this.brokers = new Semaphore(stockBrokers);
    }
    public Semaphore getSemaphore() {
    	return brokers;
    }
    public String getName() {
        return name;
    }

    public String getTicker() {
        return ticker;
    }

    public String getStartDate() {
        return startDate;
    }
    
    public Integer getStockBrokers() {
    	return stockBrokers;
    }

    public String getDescription() {
        return description;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }
}
