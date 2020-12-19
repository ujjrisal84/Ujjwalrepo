package th.mobiletechnologies.model;

import java.util.Date;
// @author Ujjwal
//Define a customer class with the different properties and with the setters and getters methods. 
public class Customer {

	public String msIsDn;
	private String simType;
	private String name;
	private String dateOfBirth;
	private String gender;
	private String address;
	private String idNumber;

	

	public String getMsIsDn() {
		return msIsDn;
	}

	public void setMsIsDn(String msIsDn) {
		this.msIsDn = msIsDn;
	}

	public String getSimType() {
		return simType;
	}

	public void setSimType(String simType) {
		this.simType = simType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	public static void main(String[] args) {
		Customer cust = new Customer();
		cust.setIdNumber("123a");
		cust.setName("Ujjwal");
		//cust.msIsDn="";
		//cust.msIsDn.toString();
		Customer cust1 = new Customer();
		cust1.setIdNumber("123a");
		cust1.setName("Ujjwal");
		
		if(cust1.equals(cust)) {
			System.out.println("true");
		}
		
		int hashCodeCust = 123;
		int hashCodeCust1 = 123;
		
		if (hashCodeCust1==hashCodeCust) {
			System.out.println("Two objects are equal");
		}
		else {
		System.out.println("not equal");
		}
		
		
		
	}
}


