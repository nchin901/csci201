package nishitachin_CSCI201L_Lab2;

public class SalariedEmployee extends Employee{
	
	public SalariedEmployee(String firstNameString, String lastNameString, String birthDateString, int id,
			String jobTitle, String companyTitle, double as) {
		super(firstNameString, lastNameString, birthDateString, id, jobTitle, companyTitle);
		// TODO Auto-generated constructor stub
		annualSalary = as;
	}
	private double annualSalary;

	@Override
	public double getAnnualSalary() {
		// TODO Auto-generated method stub
		return annualSalary;
	}
	public void setAnnualSalary(double annualSalary) {
		this.annualSalary = annualSalary;
	}
}
