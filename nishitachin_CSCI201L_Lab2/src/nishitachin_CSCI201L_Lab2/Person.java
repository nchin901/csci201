package nishitachin_CSCI201L_Lab2;

public class Person {

	private String firstNameString;
	private String lastNameString;
	private String birthDateString;
	
	public Person(String firstname, String lastname, String birthdate) {
		//constructor
		firstNameString = firstname;
		lastNameString = lastname;
		birthDateString = birthdate;
	}
	
	public String getFirstName() {
		return firstNameString;
	}
	
	public void setFirstName(String firstNameString) {
		this.firstNameString = firstNameString;
	}
	
	public String getLastName() {
		return lastNameString;
	}
	
	public void setLastName(String lastNameString) {
		this.lastNameString = lastNameString;
	}
	
	public String getBirthDate() {
		return birthDateString;
	}
	
	public void setBirthDate(String birthDateString) {
		this.birthDateString = birthDateString;
	}
}
