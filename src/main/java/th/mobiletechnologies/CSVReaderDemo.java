package th.mobiletechnologies;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.CSVReader;

import th.mobiletechnologies.model.Customer;

//@author Ujjwal

public class CSVReaderDemo {
	private static final String filepath = "C:\\Users\\Ujjwal\\Ripervalley\\Mobile_Technologies\\MSISDN.txt";

	public static void main(String[] args) throws IOException {

		// Define an ArrayList inorder to store the customer objects.
		List<Customer> custList = new ArrayList<Customer>();

		CSVReader reader = null;
		try {

			// Read the CSV file
			reader = new CSVReader(
					new FileReader("C:\\Users\\Ujjwal\\Ripervalley\\Mobile_Technologies\\mobile-technologies.csv"));
			String[] nextLine;

			// read one line at a time
			while ((nextLine = reader.readNext()) != null) {
				if (nextLine.length > 1) {
					Customer c = new Customer();
					c.setMsIsDn(nextLine[0]);
					c.setSimType(nextLine[1]);
					c.setName(nextLine[2]);
					c.setDateOfBirth(nextLine[3]);
					c.setGender(nextLine[4]);
					c.setAddress(nextLine[5]);
					c.setIdNumber(nextLine[6]);
					// Add the customer object to the ArrayList.
					custList.add(c);
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		// Validate the customer list by calling different validators
		for (Customer customer : custList) {
			if (!isValidName(customer.getName())) {
				System.out.println("Invalid Name " + customer.getName());
			} else if (!isValidDate(customer.getDateOfBirth())) {
				System.out.println("Invalid Date of birth of " + customer.getName());
			} //else if (!isValidGender(customer.getGender())) {
				//System.out.println("Invalid Gender of " + customer.getName());
			//} 
		else if (!isValidAddress(customer.getAddress())) {
				System.out.println("Invalid Address of " + customer.getName());
			} else if (!isValidIdNumber(customer.getIdNumber())) {
				System.out.println("Invalid Id Number of " + customer.getName());
			} else if (!isValidSimType(customer.getSimType())) {
				System.out.println("Invalid SIM Type of " + customer.getName());
			} else if (!isValidMSISDN(customer.getMsIsDn())) {
				System.out.println("Invalid MSISDN Number of ".concat(customer.getGender()));
			} else if (isDuplicateMSISDN(customer.getMsIsDn(), custList)) {
				System.out.println("Duplicate MSISDN Number of" + customer.getName());
			} else {
				// Add customer record to file.
				addRecordToFile(customer);
			}

		}
	}

	/**
	 * Method to write Object to file
	 */
	private static void addRecordToFile(Customer customer) {
		Path path = Paths.get(filepath);
		try {
			if (!Files.exists(path)) {
				Files.createDirectories(path);
			}
			// Write to the file the customer object's properties by receiving the byte
			// array
			Files.write(path, getObjectBytes(customer), StandardOpenOption.APPEND);
			sendSMS(customer);
		} catch (Exception exception) {
			exception.getStackTrace();
		}
	}

	// Method definition to concatenate the customer objects
	private static byte[] getObjectBytes(Customer cust) {
		return cust.getMsIsDn().concat(" ").concat(cust.getSimType()).concat(" ").concat(cust.getName()).concat(" ")
				.concat(cust.getDateOfBirth()).concat(" ").concat(cust.getGender()).concat(" ")
				.concat(cust.getAddress()).concat(" ").concat(cust.getIdNumber()).concat("\n").getBytes();

	}

	/**
	 * Method to send SMS gender wise
	 *
	 * @param customer
	 */
	private static void sendSMS(Customer customer) {
		if (customer.getGender().equals("M"))
			System.out.println(customer.getName()+"Your registration successfully completed.");

		else
			System.out.println(customer.getName()+ "Your registration successfully completed.");

	}

	/**
	 * Method for Name Validation
	 *
	 * @param name
	 * @return true/false
	 */
	private static boolean isValidName(String name) {
		if (!StringUtils.isEmpty(name)) {
			Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
			Matcher matcher = pattern.matcher(name);
			return matcher.find();
		}
		return false;
	}

	/**
	 * Method for DOB Validation
	 *
	 * @param dateStr
	 * @return true/false
	 */

	private static boolean isValidDate(String dateStr) {
		if (!StringUtils.isEmpty(dateStr)) {
			DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String todayDate = sdf.format(new Date());
			sdf.setLenient(false);
			try {
				return sdf.parse(dateStr).getTime() <= sdf.parse(todayDate).getTime();
			} catch (ParseException e) {
				return false;
			}
		}
		return false;
	}

	/**
	 * Method for Gender Validation
	 *
	 * @param gender
	 * @return true/false
	 */
	private static boolean isValidGender(String gender) {
		if (!StringUtils.isEmpty(gender))
			return gender.equals("M") || gender.equals("F");
		return false;
	}

	/**
	 * Method for address validation
	 *
	 * @param address
	 * @return true/false
	 */
	private static boolean isValidAddress(String address) {
		if (!StringUtils.isEmpty(address))
			return address.length() >= 20;
		return false;
	}

	/**
	 * Method for ID NUmber validation
	 *
	 * @param idNumber
	 * @return true/false
	 */

	private static boolean isValidIdNumber(String idNumber) {

		if (!StringUtils.isEmpty(idNumber)) {

			int digitCount = 0;
			int charCount = 0;
			char[] chars = idNumber.toCharArray();

			for (char c : chars) {
				if (Character.isDigit(c)) {

					digitCount++;
				} else {

					charCount++;

				}

				if (digitCount > 0 && charCount > 0) {
					return true;
				}
			}

			return false;
		}
		return false;
	}

	/**
	 * Method for SIM Type validation
	 *
	 * @param simType
	 * @return true/false
	 */
	private static boolean isValidSimType(String simType) {
		if (!StringUtils.isEmpty(simType))
			return simType.equals("prepaid") || simType.equals("postpaid");
		return false;
	}

	/**
	 * Method to check Valid MSISDN
	 *
	 * @param msisdn
	 * @return true/false
	 */
	private static boolean isValidMSISDN(String msisdn) {
		if (!StringUtils.isEmpty(msisdn))
			return msisdn.contains("+66");
		return false;
	}

	/**
	 * Method to check duplicate MSISDN
	 *
	 * @param msisdn
	 * @param customers
	 * @return true/false
	 */

	private static boolean isDuplicateMSISDN(String msisdn, List<Customer> customers) {
		if (!StringUtils.isEmpty(msisdn)) {
			int msisdnCount = 0;
			for (Customer customer : customers) {
				if (customer.getMsIsDn().equals(msisdn)) {
					msisdnCount++;
				}
				if (msisdnCount >= 2) {
					return true;
				}
			}
			return false;
		}
		return false;
	}
}
