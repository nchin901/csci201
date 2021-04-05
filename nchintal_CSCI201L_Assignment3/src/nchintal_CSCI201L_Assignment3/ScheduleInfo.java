package nchintal_CSCI201L_Assignment3;

public class ScheduleInfo {
	private String when;
	private String stock;
	private String amount;
	private String date;
	
	public ScheduleInfo(String when, String stock, String amount, String date) {
		this.when = when;
		this.amount = amount;
		this.stock = stock;
		this.date = date;
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
	public String getDate() {
		return date;
	}
}
