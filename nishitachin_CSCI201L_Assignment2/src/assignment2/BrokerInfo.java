package assignment2;

public class BrokerInfo {
	private String when;
	private String stock;
	private String amount;
	
	public BrokerInfo(String when, String stock, String amount) {
		this.when = when;
		this.amount = amount;
		this.stock = stock;
	}
	public String getWhen() {
		return when;
	}
	public String getAmount() {
		return amount;
	}
	public String getStock() {
		return stock;
	}
}
