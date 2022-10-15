
/*  
 * ServiceDesk is the main interface class which allows the user the
 * select options which will alter the 
 * Objects or retrieve the information contained in the 
 * Object Classes.
 */
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ServiceDesk {

	Scanner sc = new Scanner(System.in);
	Engine engine = new Engine();

	// Array of Stock objects which holds the data for the
	// MT Company's Stock Items.
	private List<Staff> staffMembers = new ArrayList<>();
	// Array of Customer objects which holds the customer
	// information for the MT Company.
	private List<Technician> techs = new ArrayList<>();
	// Array of Customer objects which holds the customer
	// information for the MT Company.
	private List<Ticket> tickets = new ArrayList<>();

	// Main method for the class StageD which allows the user to choose a menu item
	// or quit the program. It also initializes all variables/objects in the
	// Main Class. The class also loads from a text file if one is available.
	public ServiceDesk() {

		// Generate hard-coded Technicians
		engine.populateTechs(techs);

		// local variable to hold user's menu selection. Initialized to -1
		// to prevent accidental menu option by the program.
		int choice = -1;

		// displays Menu at least once and then will repeat up until
		// user chooses to quit program.

		do {
			engine.displayBasicMenu();
			// try/catch block is used here to ensure the user enters a valid menu
			// option.
			try {
				choice = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid menu option!");
			}
			if (choice != 0 && choice <= 3) {
				processBasicMenu(choice);
			} else if (choice == 0) {
				System.out.println("Bye!");
			}
		} while (choice != 0);

		sc.close();
	}

	// processBasicMenu receives an int as a parameter from the user in the
	// 'ServiceDesk'
	// method. Parameter is used as the menu selection path.
	public void processBasicMenu(int choice) {
		// switch is used to process the retrieved menu selection from the user by
		// calling the relevant methods.
		switch (choice) {
		// Allows the user to add a stock item provided that the maximum number of
		// stock items is not reach, otherwise it displays a message to the user
		// advising them that the maximum has been reached.
		case 1:
			System.out.println("Please Enter Your Email Address:");
			String newEmail = sc.nextLine();
			System.out.println("Please Enter Your Full Name:");
			String fullName = sc.nextLine();
			System.out.println("Please Enter Your Phone Number:");
			String number = sc.nextLine();
			System.out.println(
					"Please Enter Your Password (Password must be a mix of uppercase and lowercase alphanumeric characters of min length 20.):");
			String newPass = sc.nextLine();

			engine.createProfile(newPass, newEmail, fullName, number, staffMembers);

			break;
		// Allows the user to enter in their credentials. Checks the credentials against
		// those stored in the Staff and Tech's lists. If matched it displays the
		// relevant menu's
		case 2:
			System.out.println("Please Enter Your Email Address:");
			String email = (sc.nextLine());
			System.out.println("Please Enter Your Password:");
			String pass = (sc.nextLine());
			boolean staffMember = false;
			boolean tech = false;
			String name = "";
			int option = -1;
			// Iterates through staff members to match credentials entered.
			Staff tempStaff = engine.retrieveStaff(staffMembers, email, pass);
			// If matched, staffMember boolean is set to true to indicate Staff login
			// and not tech login.
			// Staff is saved in a local variable for use below.
			if (tempStaff != null) {
				name = tempStaff.getName();
				staffMember = true;
			}
			// Iterates through technicians to match credentials entered. If matched,
			// tech boolean is set to true to indicate tech login and not staff member
			// login.
			Technician tempTech = engine.retrieveTech(techs, email, pass);
			if (tempTech != null) {
				tech = true;
				name = tempTech.getName();
			}

			// Displays relevant menu depending on whether staff member or technician has
			// logged in.
			if (staffMember) {
				do {
					engine.displayStaffMenu(name);
					// try/catch block is used here to ensure the user enters a valid menu
					// option.
					try {
						option = Integer.parseInt(sc.nextLine());
					} catch (NumberFormatException e) {
						System.out.println("Please enter a valid menu option!");
					}
					if (option != 0) {
						processStaffMenu(option, tempStaff);
					} else if (option == 0) {
						System.out.println("Bye!");
					}
				} while (option != 0);
			} else if (tech) {
				do {
					engine.displayTechMenu(name);
					// try/catch block is used here to ensure the user enters a valid menu
					// option.
					try {
						option = Integer.parseInt(sc.nextLine());
					} catch (NumberFormatException e) {
						System.out.println("Please enter a valid menu option!");
					}
					if (option != 0) {
						processTechMenu(option);
					} else if (option == 0) {
						System.out.println("Bye!");
					}
				} while (option != 0);
			} else {
				System.out.println("Invalid Input - Please try again");
			}
			break;
		// Forgotten password function. It allows users to change password by entering
		// their email and phone number.
		case 3:
			System.out.println(
					"Input 'T' to reset password for technicians, 'S' to reset password for staff members, and 'B' to go back to main menu.");
			String input = "a";
			while (!(input.equalsIgnoreCase("t") || input.equalsIgnoreCase("s") || input.equalsIgnoreCase("b"))) {
				input = sc.nextLine();
				// if the user selects t or s, proceed with an email and phone number prompt for
				// verification
				if (input.equalsIgnoreCase("t") || input.equalsIgnoreCase("s")) {
					String userEmail = null;
					String phoneNumber = null;
					System.out.println("Please enter your email address: ");
					userEmail = sc.nextLine();
					System.out.println("Please enter your phone number: ");
					phoneNumber = sc.nextLine();
					if (input.equalsIgnoreCase("t")) {
						// if the input was 't', resetting the password for a technician
						Technician toReset = engine.retrieveTechToReset(techs, userEmail, phoneNumber);
						// if a technician with matching credentials is found, proceed with the reset
						// function
						if (toReset != null) {
							String newPassword = getNewPassword();
							HashMap<String, String> credentials = new HashMap<String, String>();
							credentials.put(userEmail, newPassword);
							toReset.setLogin(credentials);
							System.out.println("Password successfully updated.");
						} else {
							System.out.println("We couldn't find any matching technician record."
									+ "\nBack to main menu.");
						}
					} else if (input.equalsIgnoreCase("s")) {
						// if the input was 's', resetting the password for a staff member
						Staff toReset = engine.retrieveStaffToReset(staffMembers, userEmail, phoneNumber);
						// if a staff member with matching credentials is found, proceed with the reset
						// function
						if (toReset != null) {
							String newPassword = getNewPassword();
							HashMap<String, String> credentials = new HashMap<String, String>();
							credentials.put(userEmail, newPassword);
							toReset.setLogin(credentials);
							System.out.println("Password successfully updated.");
						} else {
							System.out.println("We couldn't find any matching staff member record."
									+ "\nBack to main menu.");
						}
					}
					// if the user selects b, redirect them to the main menu
				} else if (input.equalsIgnoreCase("b")) {
					System.out.println("Back to main menu.");
				} else {
					System.out.println("Invalid input. Must be T/S/B. Try again.");
				}
			}
			break;
		// default message displayed if invalid input received from user.
		default:
			System.out.println("Invalid Input - Please try again");
		}
	}
	
	// prompts the user until a valid new password is entered
	public String getNewPassword() {

		String newPassword = "a";
		// keep prompting the user for as long as the password entered is not valid
		while (!engine.PasswordIsValid(newPassword)) {
			System.out.println("Please enter your new password: ");
			newPassword = sc.nextLine();
			// if the password entered isn't valid, feedback to the user
			if (!engine.PasswordIsValid(newPassword)) {
				System.out.println("Sorry, the password you entered isn't valid. "
						+ "\nYour new password must be a mix of uppercase and lowercase alphanumeric characters of min length 20.");
			}
		}
		return newPassword;
	}

	// processMenu receives an int as a parameter from the user in the 'Staff'
	// method. Parameter is used as the menu selection path. Staff member is also
	// passed to allow specific information to be retrieved for the logged in staff.
	public void processStaffMenu(int choice, Staff tempStaff) {
		// switch is used to process the retrieved menu selection from the user by
		// calling the relevant methods.
		switch (choice) {

		case 0:
			System.out.println("Bye!");
			break;
		case 1:
			System.out.println("Please Enter Description of the IT Issue");
			String issue = (sc.nextLine());
			System.out.println("Please Select Issue Severity:");
			System.out.println("1 - Low:");
			System.out.println("2 - Medium");
			System.out.println("3 - High");
			int severity = Integer.parseInt(sc.nextLine());
			System.out.println("Ticket Created");
			engine.userContinue(sc);
			Severity tempSeverity = Severity.LOW;
			int level = 1;
			if (severity == 1) {
				tempSeverity = Severity.LOW;
			} else if (severity == 2) {
				tempSeverity = Severity.MEDIUM;
			} else if (severity == 3) {
				tempSeverity = Severity.HIGH;
				level = 2;
			}
			Ticket tempTicket = new Ticket(issue, tempSeverity, tempStaff);

			tempTicket.setStatus(Status.OPEN);

			// (Greg Case & MultiplyByZer0, 2018)
			// Randomly assigns a technician to a temporary technician variable. It then
			// iterates over each
			// technician and changes technician to the one with the lowest assigned
			// tickets.
			Technician assignedTechnician = techs.get(ThreadLocalRandom.current().nextInt(0, 4 + 1));

			// While loop to make sure that randomly selected technician is the same level
			// of the ticket.
			while ((level == 2 && assignedTechnician.getLevel() == 1)
					|| (level == 1 && assignedTechnician.getLevel() == 2)) {
				assignedTechnician = techs.get(ThreadLocalRandom.current().nextInt(0, 4 + 1));
			}

			for (Technician tech : techs) {
				if (level == 1 && tech.getLevel() == 1) {
					if (tech.getAssignedTickets() < assignedTechnician.getAssignedTickets()) {
						assignedTechnician = tech;
					}
				} else if (level == 2 && tech.getLevel() == 2) {
					if (tech.getAssignedTickets() < assignedTechnician.getAssignedTickets()) {
						assignedTechnician = tech;
					}
				}
			}

			tempTicket.setTechnician(assignedTechnician);
			tickets.add(tempTicket);
			assignedTechnician.addTickets(tempTicket);

			break;

		case 2:
			// Displays a print out of the logged in staff members open tickets.
			for (Ticket ticket : tickets) {
				if (ticket.getStatus() == Status.OPEN && ticket.getStaff() == tempStaff) {
					System.out.println("");
					System.out.println("" + ticket.toString());
					System.out.println("");
				}
			}
			engine.userContinue(sc);
			break;
		// default message displayed if invalid input received from user.
		default:
			System.out.println("Invalid Input - Please try again");
		}

	}

	public void chooseTicketStatus() {
		// get input of ticket to change
		System.out.println("Please enter a ticket status to edit");
		int ticketToEditStatus = Integer.parseInt(sc.nextLine());
		// counter to track through array list looking for valid ticket ID
		int i = 0;
		// originally the element of the ticket searched is -1 which is out of bounds.
		// If element is found then we
		// store the element number here, this is used later to change the correct
		// tickets status
		int elementInList = -1;
		boolean ticketExists = false;
		while (i < tickets.size()) {
			if (tickets.get(i).getTicketID() == ticketToEditStatus) {
				ticketExists = true;
				elementInList = i;
			}
			i++;
		}
		// if ticket is found we ask the tech what status they want to change the ticket
		// to
		if (ticketExists == true) {
			System.out.println("Please select from the following status items:");
			System.out.println("1 - Open");
			System.out.println("2 - Resolved");
			System.out.println("3 - Unresolved");

			int chosenStatus = Integer.parseInt(sc.nextLine());
			changeTicketStatus(chosenStatus, elementInList);

		} // if ticket is not found then error message
		else {
			System.out.println("Ticket does not exist  with ID: " + ticketToEditStatus);
		}
	}

	// ticket status changed on correct element in arraylist
	public void changeTicketStatus(int status, int elementInList) {
		if (tickets.get(elementInList).getStatus() == Status.ARCHIVED) {
			System.out.println("Unable to change the status of an archived ticket!");
			return;
			}
		
		if (status == 1) {
			tickets.get(elementInList).setStatus(Status.OPEN);
		} else if (status == 2) {
			tickets.get(elementInList).setStatus(Status.RESOLVED);
		} else if (status == 3) {
			tickets.get(elementInList).setStatus(Status.UNRESOLVED);
		} else {
			System.out.println(status + " is not an option, status change failed");
		}
		// Confirmation message of the changes
		System.out.println("Status of ticket: " + tickets.get(elementInList).getTicketID() + " is now status "
				+ tickets.get(elementInList).getStatus());
	}

	public void chooseTicketSeverity() {
		// get input of ticket to change
		System.out.println("Please enter ticket ID to edit Severity");
		int ticketToEditStatus = Integer.parseInt(sc.nextLine());
		// counter to track through array list looking for valid ticket ID
		int i = 0;
		// originally the element of the ticket searched is -1 which is out of bounds.
		// If element is found then we
		// store the element number here, this is used later to change the correct
		// tickets status
		int elementInList = -1;
		boolean ticketExists = false;
		while (i < tickets.size()) {
			if (tickets.get(i).getTicketID() == ticketToEditStatus) {
				ticketExists = true;
				elementInList = i;
			}
			i++;
		}
		// if ticket is found we ask the tech what Severity they want to change the
		// ticket to
		if (ticketExists == true) {
			System.out.println("Please select new ticket Severity:");
			System.out.println("1 - Low");
			System.out.println("2 - Medium");
			System.out.println("3 - High");

			int chosenStatus = Integer.parseInt(sc.nextLine());
			changeTicketSeverity(chosenStatus, elementInList);

		} // if ticket is not found then error message
		else {
			System.out.println("Ticket does not exist  with ID: " + ticketToEditStatus);
		}
	}

	public void changeTicketSeverity(int status, int elementInList) {
		// int used to assign ticket to appropriate level technician.
		int level = 1;
		// Sets new severity to ticket.
		if (status == 1) {
			tickets.get(elementInList).setSeverity(Severity.LOW);
		} else if (status == 2) {
			tickets.get(elementInList).setSeverity(Severity.MEDIUM);
		} else if (status == 3) {
			tickets.get(elementInList).setSeverity(Severity.HIGH);
			level = 2;
		} else {
			System.out.println(status + " is not an option, severity change failed");
		}

		// (Greg Case & MultiplyByZer0, 2018)
		// Randomly assigns a technician to a temporary technician variable. It then
		// iterates over each
		// technician and changes technician to the one with the lowest assigned
		// tickets.
		Technician assignedTechnician = techs.get(ThreadLocalRandom.current().nextInt(0, 4 + 1));

		// While loop to make sure that randomly selected technician is the same level
		// of the ticket.
		while (level == 2 && assignedTechnician.getLevel() == 1) {
			assignedTechnician = techs.get(ThreadLocalRandom.current().nextInt(0, 4 + 1));
		}

		for (Technician tech : techs) {
			if (level == 1 && tech.getLevel() == 1) {
				if (tech.getAssignedTickets() < assignedTechnician.getAssignedTickets()) {
					assignedTechnician = tech;
				}
			} else if (level == 2 && tech.getLevel() == 2) {
				if (tech.getAssignedTickets() < assignedTechnician.getAssignedTickets()) {
					assignedTechnician = tech;
				}
			}
		}

		// Assigns new technician to ticket based off new severity level.
		tickets.get(elementInList).setTechnician(assignedTechnician);

		// Confirmation message of the changes
		System.out.println("Severity of ticket: " + tickets.get(elementInList).getTicketID() + " is now severity "
				+ tickets.get(elementInList).getSeverity());
	}

	// processMenu receives an int as a parameter from the user in the 'Staff'
	// method. Parameter is used as the menu selection path.
	public void processTechMenu(int option) {
		// switch is used to process the retrieved menu selection from the user by
		// calling the relevant methods.
		switch (option) {

		case 0:
			System.out.println("Bye!");
			break;

		case 1:

			break;

		case 2:

			break;

		case 3:
			// technician can change status of tickets
			chooseTicketStatus();
			break;
		case 4:
			// technician can change status of tickets
			chooseTicketSeverity();
			break;
		// default message displayed if invalid input received from user.
		default:
			System.out.println("Invalid Input - Please try again");
		}

	}

	public static void main(String[] args) {
		ServiceDesk A2obj = new ServiceDesk();

	}

}