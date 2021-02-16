package nishitachin_CSCI201L_Lab2;

public class HourlyEmployee extends Employee{
	
	public HourlyEmployee(String firstNameString, String lastNameString, String birthDateString, int id,
			String jobTitle, String companyTitle, int hr, int numhours) {
		super(firstNameString, lastNameString, birthDateString, id, jobTitle, companyTitle);
		// TODO Auto-generated constructor stub
		hourlyRate = hr;
		numberHoursPerWeek = numhours;
	}
	
	private int hourlyRate;
	private int numberHoursPerWeek;
	
	@Override
	public double getAnnualSalary() {
		// TODO Auto-generated method stub
		double annualSalary = 0;
		annualSalary = (hourlyRate * numberHoursPerWeek) * 52;
		return annualSalary;
	}
	
	public int getHourlyRate() {
		return hourlyRate;
	}
	public void setHourlyRate(int hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	public int getNumberHoursPerWeek() {
		return numberHoursPerWeek;
	}
	public void setNumberHoursPerWeek(int numberHoursPerWeek) {
		this.numberHoursPerWeek = numberHoursPerWeek;
	}
}
