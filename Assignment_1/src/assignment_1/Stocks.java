package assignment_1;

import java.util.Comparator;

//import java.sql.Date;

public class Stocks implements Comparator<Stocks>{
	
	private String name;
	private String ticker;
	private String startDate;
	private String description;
	private String exchangeCode;
	
//	public Stocks(String name, String ticker, String date, String description,
//			String exchangeCode) {
//		// TODO Auto-generated constructor stub
//	}

	public String getName() {
		return name;
	}
	
	public String getTicker() {
		return ticker;
	}
	
	public String getDate() {
		return startDate;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getExchangeCode() {
		return exchangeCode;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	public void setDate(String startDate) {
		this.startDate = startDate;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}
	public int compare(Stocks a, Stocks b) {
        return a.name.compareToIgnoreCase(b.name);
    }
	public String toString() {
		return name + ticker + startDate + description + exchangeCode + description;
	}
}

