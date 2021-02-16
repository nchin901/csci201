package nishitachin_CSCI201L_Lab2;

public class CommissionEmployee extends SalariedEmployee{
	
	public CommissionEmployee(String firstNameString, String lastNameString, String birthDateString, int id,
			String jobTitle, String companyTitle, double as, double st, double cp) {
		super(firstNameString, lastNameString, birthDateString, id, jobTitle, companyTitle, as);
		// TODO Auto-generated constructor stub
		salesTotal = st;
		commissionPercentage = cp;
	}
	private double salesTotal;
	private double commissionPercentage;
	
	public double getSalesTotal() {
		return salesTotal;
	}
	
	public void setSalesTotal(double salesTotal) {
		this.salesTotal = salesTotal;
	}
	
	public double getCommissionPercentage() {
		return commissionPercentage;
	}
	
	public void setCommissionPercentage(double commissionPercentage) {
		this.commissionPercentage = commissionPercentage;
	}
	public double getAnnualSalary() {
		double annualSalary = salesTotal * commissionPercentage;
		return annualSalary;
	}
}
