import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Engine {

	// checks if password meets requirements
	public boolean PasswordIsValid(String password) {
		if (password.length() < 20)
			return false;

		String pattern = "(.*[a-z].*)(.*[A-Z].*)(.*[0-9].*)|"
				+ "(.*[A-Z].*)(.*[a-z].*)|(.*[a-z].*)(.*[0-9].*)(.*[A-Z].*)|" + "(.*[0-9].*)(.*[a-z].*)(.*[A-Z].*)";

		if (!password.matches(pattern))
			return false;

		return true;
	}

	// pre-populates Techs array
	public void populateTechs(List<Technician> techs) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("harry@tech.com", "Abc12345678901234567");
		Technician a = new Technician("Harry Styles", map, 1, "041333444");
		techs.add(a);

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("niall@tech.com", "Abc45678901234567891");
		Technician b = new Technician("Niall Horan", map1, 1, "043444555");
		techs.add(b);

		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("liam@tech.com", "Abc78901234567891234");
		Technician c = new Technician("Liam Payne", map2, 1, "044123456");
		techs.add(c);

		HashMap<String, String> map3 = new HashMap<String, String>();
		map3.put("louis@tech.com", "Def12345678901234567");
		Technician d = new Technician("Louis Tomlinson", map3, 2, "045777666");
		techs.add(d);

		HashMap<String, String> map4 = new HashMap<String, String>();
		map4.put("zayn@tech.com", "Def45678901234567890");
		Technician e = new Technician("Zayn Malik", map4, 2, "041987654");
		techs.add(e);
	}

	// Checks if the password is valid and creates a new profile if it is.
	// If it is not, it gives feedback to the user.
	public void createProfile(String newPass, String newEmail, String fullName, String number, List<Staff> staffMembers) {
		
		if (PasswordIsValid(newPass)) {

			HashMap<String, String> newCredentials = new HashMap<String, String>();
			newCredentials.put(newEmail, newPass);
			Staff newStaff = new Staff(fullName, newCredentials, number);
			staffMembers.add(newStaff);

			System.out.println("\nProfile succesfully created.\n");

		} else {
			System.out.println("\nInvalid password. Unable to create profile.\n");
		}
	}
	
	// Iterates through staff members to match credentials entered. 
	public Staff retrieveStaff(List<Staff> staffMembers, String email, String pass) {
		
		Staff temp = null;		
		for (Staff staff : staffMembers) {
			if (staff.getLogin().containsKey(email) && staff.getLogin().containsValue(pass)) {
				temp = staff;
			}
		}
		return temp;
	}
	
	// Iterates through technicians to match credentials entered. 
	public Technician retrieveTech(List<Technician> techs, String email, String pass) {
		
		Technician temp = null;		
		for (Technician technician : techs) {
			if (technician.getLogin().containsKey(email) && technician.getLogin().containsValue(pass)) {
				temp = technician;
			}
		}
		return temp;
	}
	
	// iterates through technicians to match email and phone number and returns password.
	public Technician retrieveTechToReset(List<Technician> techs, String email, String phoneNumber) {
		
		Technician tech = null;		
		for (Technician technician : techs) {
			if (technician.getLogin().containsKey(email) && technician.getPhoneNumber().equals(phoneNumber)) {
				tech = technician;
			}
		}	
		
		return tech;
	}
	
	// iterates through staff members to match email and phone number and returns password.
	public Staff retrieveStaffToReset(List<Staff> staffMembers, String email, String phoneNumber) {
		
		Staff temp = null;		
		for (Staff staff : staffMembers) {
			if (staff.getLogin().containsKey(email) && staff.getPhoneNumber().equals(phoneNumber)) {
				temp = staff;
			}
		}	
		
		return temp;
	}
	
	// MENUs - UI //

	// displays menu
	public void displayBasicMenu() {
		System.out.println("Please select from the following menu items:");
		System.out.println("1 - Set up account");
		System.out.println("2 - Log In");
		System.out.println("3 - Reset Password");
		System.out.println("0 - Exit");
	}

	// displayStaffMenu method simply displays the method in a neat
	// and easily interpreted way for the user.
	public void displayStaffMenu(String name) {
		System.out.println("");
		System.out.println("Hi " + name);
		System.out.println("");
		System.out.println("Please select from the following menu items:");
		System.out.println("1 - Submit Ticket");
		System.out.println("2 - Check Status of existing tickets");
		System.out.println("0 - Logout");
	}

	// displayMenu method simply displays the method in a neat
	// and easily interpreted way for the user.
	public void displayTechMenu(String name) {
		System.out.println("");
		System.out.println("Hi " + name);
		System.out.println("");
		System.out.println("Please select from the following menu items:");
		System.out.println("1 - View Open Tickets");
		System.out.println("2 - View All Tickets");
		System.out.println("3 - Edit Ticket Status");
		System.out.println("4 - Change Ticket Severity");
		System.out.println("0 - Logout");
	}

	// Slows the display of the menu to allow the user to view the information
	// before continuing on. Implemented as a method to minimize code duplication.
	public void userContinue(Scanner sc) {
		System.out.println("");
		System.out.println("Press Enter to continue");
		sc.nextLine();
	}

}
