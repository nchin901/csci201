package nchintal_CSCI201L_Assignment3;

public class TraderInfo {
	private Double serialNumber;
	private Double initialBalance;
	
	public TraderInfo(Double serialNumber, Double initialBalance) {
		this.serialNumber = serialNumber;
		this.initialBalance = initialBalance;
	}
	public Double getSerialNumber() {
		return serialNumber;
	}
	
	public Double getInitialBalance() {
		return initialBalance;
	}
}
