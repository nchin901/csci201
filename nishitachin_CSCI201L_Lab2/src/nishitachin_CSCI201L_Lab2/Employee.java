package nishitachin_CSCI201L_Lab2;


public abstract class Employee extends Person{
	
	public Employee(String firstNameString, String lastNameString, String birthDateString, int i, String jobTitle, String companyTitle) {
		//constructor
		super(firstNameString, lastNameString, birthDateString);
		id = i;
		jobTitleString = jobTitle;
		companyString = companyTitle;
	}
	
	private int id;
	private String jobTitleString;
	private String companyString;
	
	public int getEmployeeID() {
		return id;
	}
	public void setEmployeeID(int id) {
		this.id = id;
	}
	
	public String getJobTitle() {
		return jobTitleString;
	}
	public void setJobTitle(String jobTitleString) {
		this.jobTitleString = jobTitleString;
	}
	
	public String getCompany() {
		return companyString;
	}
	public void setCompany(String companyString) {
		this.companyString = companyString;
	}
	public abstract double getAnnualSalary();
}
