
/*
 * ServiceDesk is the main interface class which allows the user the
 * select options which will alter the
 * Objects or retrieve the information contained in the
 * Object Classes.
 */
import java.util.HashMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
	// System owner instance
	private SystemOwner owner = new SystemOwner();

	// Main method for the class StageD which allows the user to choose a menu item
	// or quit the program. It also initializes all variables/objects in the
	// Main Class. The class also loads from a text file if one is available.
	public ServiceDesk() throws ParseException {

		// Generate hard-coded Technicians
		engine.populateTechs(techs);

		/* START: add a staff member / ticket for testing - RBULLOCK 22/10/2022 */
		// HashMap<String, String> map5 = new HashMap<String, String>();
		// map5.put("abc@abc.com", "Ilovefixingpumps12345");
		// Staff a = new Staff("Ryan", map5, "0123456789");
		// staffMembers.add(a);
		// Ticket tik = new Ticket("sad", Severity.LOW, staffMembers.get(0));
		// tik.setTechnician(techs.get(0));
		// tickets.add(tik);
		// techs.get(0).addTickets(tik);
		/* END: add a staff member / ticket for testing - RBULLOCK 22/10/2022 */

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
			if (choice != 0 && choice <= 5) {
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
	public void processBasicMenu(int choice) throws ParseException {
		// switch is used to process the retrieved menu selection from the user by
		// calling the relevant methods.
		switch (choice) {
		// Allows the user to add a stock item provided that the maximum number of
		// stock items is not reach, otherwise it displays a message to the user
		// advising them that the maximum has been reached.
		case 1:
			boolean validPhoneNumber = true;
			System.out.println("Please Enter Your Email Address:");
			String newEmail = sc.nextLine();
			System.out.println("Please Enter Your Full Name:");
			String fullName = sc.nextLine();
			while (!engine.nameIsValid(fullName)) {
				System.out.println("Please Enter Your Full Name:");
				fullName = sc.nextLine();
			}
			System.out.println("Please Enter Your Phone Number:");
			String number = sc.nextLine();
			if (number.length() != 10 || !engine.isNumeric(number)) {
				validPhoneNumber = false;
			}
			while (validPhoneNumber == false) {
				System.out.println("Please Enter A Valid Phone Number (10 Digits):");
				number = sc.nextLine();
				if (number.length() == 10 && engine.isNumeric(number)) {
					validPhoneNumber = true;
				}
			}
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
					// reset option when menu is displayed
					option = -1;
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
					// reset option when menu is displayed
					option = -1;
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
				boolean emailExists = false;
				for (int i = 0; i < staffMembers.size(); i++) {
					if (staffMembers.get(i).getLogin().containsKey(email)) {
						emailExists = true;

					}
				}
				if (emailExists) {
					loginRetry(email);
				} else {
					System.out.println("Invalid Input - Please try again");
				}

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
							System.out.println(
									"We couldn't find any matching technician record." + "\nBack to main menu.");
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
							System.out.println(
									"We couldn't find any matching staff member record." + "\nBack to main menu.");
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
		// system owner login
		case 4:
			System.out.println("Please Enter Username:");
			String username = (sc.nextLine());
			System.out.println("Please Enter Password:");
			String password = (sc.nextLine());

			// if the credentials match, proceed
			if (owner.checkCredentials(username, password)) {
				// proceed
				System.out.println("System owner succesfully logged in.");
				// display menu with only one option
				int selection = -1;
				do {
					engine.displaySystemOwnerMenu();
					// try/catch block is used here to ensure the user enters a valid menu
					// option.
					try {
						selection = Integer.parseInt(sc.nextLine());
					} catch (NumberFormatException e) {
						System.out.println("Please enter a valid menu option!");
					}
					if (selection == 1) {
						generateReport();
					} else if (selection == 0) {
						System.out.println("Bye!");
					}
				} while (selection != 0);
			} else {
				System.out.println("Invalid input. Back to main menu.");
			}
			break;
		// default message displayed if invalid input received from user.
		default:
			System.out.println("Invalid Input - Please try again");
		}
	}

	// generates report - system owner
	public void generateReport() throws ParseException {
		int openTickets = 0;
		int totalTickets = 0;
		int closedAndResolvedTickets = 0;
		int closedAndUnresolvedTickets = 0;
		System.out.println("Create a report");
		System.out.println("Y/N");
		String reportInput;
		reportInput = sc.nextLine();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		DateTimeFormatter localFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		if (reportInput.toUpperCase().contains("Y")) {
			String reportTimeDependent = "";
			System.out.println("Would you like to specify the report time period? (Y/N)");
			reportTimeDependent = sc.nextLine();

			if (reportTimeDependent.toUpperCase().contains("N")) {
				System.out.println("Report generating");
				Report report = new Report(tickets);
				if (tickets.size() > 0) {

					for (Ticket ticket : tickets) {

						System.out.println("");
						System.out.print("" + ticket.toString());
						System.out.println("Severity - " + ticket.getSeverity());
						System.out.println("Creator - " + ticket.getStaff().getName());
						System.out.println("Time of submission - " + formatter.format(ticket.getDateTime()));
						if(ticket.getStatus()==Status.RESOLVEDANDCLOSED || ticket.getStatus()==Status.UNRESOLVEDANDCLOSED) {
							System.out.println("Time closed - " + ticket.getCloseTime().format(localFormatter));
						}else {
						System.out.println("Time closed - N/A");
						}
						System.out.println("");
						if(ticket.getStatus()==Status.OPEN) {
							openTickets++;
						}else if(ticket.getStatus()==Status.RESOLVEDANDCLOSED) {
							closedAndResolvedTickets++;
						}else if(ticket.getStatus()==Status.UNRESOLVEDANDCLOSED) {
							closedAndUnresolvedTickets++;
						}
						totalTickets++;
						System.out.println("------Overview------");
						System.out.println("Total Tickets: " + totalTickets);
						System.out.println("Open Tickets: " + openTickets);
						System.out.println("Closed and Resolved Tickets: " + closedAndResolvedTickets);
						System.out.println("Closed and Unresolved Tickets: " + closedAndUnresolvedTickets);
						System.out.println("");

					}
				} else {
					System.out.println("No tickets exist");
				}
			} else {
				System.out.println("Enter a start time (dd-MM-yyyy HH:mm:ss)");
				String startTime = sc.nextLine();
				Date startDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(startTime);
				System.out.println(startDate.toString());

				System.out.println("Enter a end time (dd-MM-yyyy HH:mm:ss)");
				String endTime = sc.nextLine();
				Date endDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(endTime);
				System.out.println(endDate.toString());
				
				
				if (startDate.compareTo(endDate) < 0) {
					System.out.println("");
					System.out.println("------Report------");
					System.out.println("");

					for (Ticket ticket : tickets) {
						if (ticket.getDateTime().compareTo(startDate) > 0
								&& ticket.getDateTime().compareTo(endDate) < 0) {
							System.out.println("");
							System.out.print("" + ticket.toString());
							System.out.println("Severity - " + ticket.getSeverity());
							System.out.println("Creator - " + ticket.getStaff().getName());
							System.out.println("Time of submission - " + formatter.format(ticket.getDateTime()));
							if(ticket.getStatus()==Status.RESOLVEDANDCLOSED || ticket.getStatus()==Status.UNRESOLVEDANDCLOSED) {
								System.out.println("Time closed - " + ticket.getCloseTime().format(localFormatter));
							}else {
							System.out.println("Time closed - N/A");
							}
							System.out.println("");
							if(ticket.getStatus()==Status.OPEN) {
								openTickets++;
							}else if(ticket.getStatus()==Status.RESOLVEDANDCLOSED) {
								closedAndResolvedTickets++;
							}else if(ticket.getStatus()==Status.UNRESOLVEDANDCLOSED) {
								closedAndUnresolvedTickets++;
							}
							totalTickets++;
						}
					}
					if (totalTickets == 0) {
						System.out.println("No tickets for this time period");
						System.out.println("");
					}
					System.out.println("------Overview------");
					System.out.println("Total Tickets: " + totalTickets);
					System.out.println("Open Tickets: " + openTickets);
					System.out.println("Closed and Resolved Tickets: " + closedAndResolvedTickets);
					System.out.println("Closed and Unresolved Tickets: " + closedAndUnresolvedTickets);
					System.out.println("");
				} else {
					System.out.println("Start date must be before end date. Error!");
					System.out.println("");
				}
			}
		} else {
			System.out.println("Report not generating");
			System.out.println("");
		}

	}

	public void loginRetry(String email) {

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
			loginRetry(email);
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
			boolean ticketSeverityValid = false;
			boolean ticketDescValid = false;
			String desc = null;
			while (!ticketDescValid) {
				System.out.println("Please Enter Description of the IT Issue");
				desc = sc.nextLine();
				if (desc != null && desc.length() != 0) {
					ticketDescValid = true;
				}
			}

			int severityInt = 4;

			while (!ticketSeverityValid) {
				System.out.println("Please Select Issue Severity:");
				System.out.println("1 - Low");
				System.out.println("2 - Medium");
				System.out.println("3 - High");
				String severity = sc.nextLine();

				if (engine.isNumeric(severity)) {
					severityInt = Integer.parseInt(severity);
					if (severityInt <= 3 && severityInt >= 1) {
						ticketSeverityValid = true;

					}
				}
			}

			System.out.println("Ticket Created");
			engine.userContinue(sc);
			Severity tempSeverity = Severity.LOW;
			int level = 1;
			if (severityInt == 1) {
				tempSeverity = Severity.LOW;
			} else if (severityInt == 2) {
				tempSeverity = Severity.MEDIUM;
			} else if (severityInt == 3) {
				tempSeverity = Severity.HIGH;
				level = 2;
			} else {
				break;
			}
			Ticket tempTicket = new Ticket(desc, tempSeverity, tempStaff);

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
			boolean noTicket = true;
			// Displays a print out of the logged in staff members open tickets.
			for (Ticket ticket : tickets) {
				if (ticket.getStatus() == Status.OPEN && ticket.getStaff() == tempStaff) {
					System.out.println("");
					System.out.print("" + ticket.toString());
					System.out.println("Severity - " + ticket.getSeverity());
					System.out.println("");
					noTicket = false;
				}
			}
			if (noTicket) {
				System.out.println("No Current Tickets Open");
			}
			engine.userContinue(sc);
			break;
		// default message displayed if invalid input received from user.
		default:
			System.out.println("Invalid Input - Please try again");
		}

	}

	// method to choose ticket seperated from change status code
	// so that we can reuse this code for changing severity
	public int chooseTicketIDToEdit() {
		// get input of ticket to change
		System.out.println("Please enter a ticket number to edit");
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
		if (ticketExists == true) {

			return elementInList;
		} // if ticket is not found then error message
		else {

			System.out.println("Ticket does not exist  with ID: " + ticketToEditStatus);
			return elementInList;
		}

	}

	public void chooseTicketStatus(int elementInList) {

		// if ticket is found we ask the tech what status they want to change the ticket
		// to

		System.out.println("Please select from the following status items:");
		System.out.println("1 - Open");
		System.out.println("2 - Resolved and Closed");
		System.out.println("3 - Unresolved and Closed");

		int chosenStatus = Integer.parseInt(sc.nextLine());
		changeTicketStatus(chosenStatus, elementInList);

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
			tickets.get(elementInList).setStatus(Status.RESOLVEDANDCLOSED);
		} else if (status == 3) {

			tickets.get(elementInList).setStatus(Status.UNRESOLVEDANDCLOSED);

		} else {
			System.out.println(status + " is not an option, status change failed");
		}
		// Confirmation message of the changes
		System.out.println("Status of ticket: " + tickets.get(elementInList).getTicketID() + " is now status "
				+ tickets.get(elementInList).getStatus().toString().replace("and", " and "));
	}

	public void chooseTicketSeverity(int elementInList) {
		/*
		 * START: Removed code as it is not needed and casuing errors - RBULLOCK
		 * 22/10/2022
		 */
		// get input of ticket to change
		// System.out.println("Please enter ticket ID to edit Severity");
		// int ticketToEditStatus = -1;
		// try {
		// ticketToEditStatus = Integer.parseInt(sc.nextLine());
		// } catch (NumberFormatException e) {
		// System.out.println("Please enter a valid menu option!");
		// }

		// counter to track through array list looking for valid ticket ID
		// int i = 0;
		// originally the element of the ticket searched is -1 which is out of bounds.
		// If element is found then we
		// store the element number here, this is used later to change the correct
		// tickets status
		// int elementInList = -1;
		// boolean ticketExists = false;
		// while (i < tickets.size()) {
		// if (tickets.get(i).getTicketID() == ticketToEditStatus) {
		// ticketExists = true;
		// elementInList = i;
		// }
		// i++;
		// }
		// if ticket is found we ask the tech what Severity they want to change the
		// ticket to
		// if (ticketExists == true) {
		/*
		 * END: Removed code as it is not needed and casuing errors - RBULLOCK
		 * 22/10/2022
		 */
		System.out.println("Please select new ticket Severity:");
		System.out.println("1 - Low");
		System.out.println("2 - Medium");
		System.out.println("3 - High");

		int chosenStatus = Integer.parseInt(sc.nextLine());
		changeTicketSeverity(chosenStatus, elementInList);

		// } // if ticket is not found then error message
		// else {
		// System.out.println("Ticket does not exist with ID: " + ticketToEditStatus);
		// }
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
		boolean noTicket = true;
		switch (option) {

		case 0:
			System.out.println("Bye!");
			break;

		case 1:

			// Displays a print out of the logged in staff members open tickets.
			for (Ticket ticket : tickets) {
				if (ticket.getStatus() == Status.OPEN) {
					System.out.println("");
					System.out.println("" + ticket.toString());
					System.out.println("");
					noTicket = false;
				}
			}
			if (noTicket) {
				System.out.println("No Current Tickets Open");
			}
			break;

		case 2:

			// Displays a print out of the logged in staff members open tickets.
			for (Ticket ticket : tickets) {
				if (tickets.size() > 0) {
					System.out.println("");
					System.out.println("" + ticket.toString());
					System.out.println("");
					noTicket = false;
				}
			}
			if (noTicket) {
				System.out.println("No Current Tickets Open");
			}
			engine.userContinue(sc);

			// default message displayed if invalid input received from user.

			break;

		case 3:
			processTechTicketMenu();

			// technician can change status of tickets

			break;
		// case 4:
		// technician can change status of tickets
		// chooseTicketSeverity();
		// break;
		// default message displayed if invalid input received from user.
		default:
			System.out.println("Invalid Input - Please try again");
		}

	}

	// new ticket menu processing
	public void processTechTicketMenu() {

		int option = -1;
		do {
			engine.displayTechTicketMenu();
			try {
				option = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid menu option!");
			}
			int ticketElementInList;

			switch (option) {

			case 0:
				System.out.println("Bye!");
				break;

			case 1:
				// first we get validate the ticket and get the element in list
				// then we can change the status
				ticketElementInList = chooseTicketIDToEdit();
				if (ticketElementInList >= 0) {
					chooseTicketStatus(ticketElementInList);
					option = 0;
				} else {
					// if ticket is not valid then we redisplay the techs menu
					engine.displayTechMenu();
				}

				break;

			case 2:
				// first we get validate the ticket and get the element in list
				// then we can change the severity
				ticketElementInList = chooseTicketIDToEdit();
				if (ticketElementInList >= 0) {
					chooseTicketSeverity(ticketElementInList);
					option = 0;
				} else {
					// if ticket is not valid then we redisplay the techs menu
					engine.displayTechMenu();
				}

				break;

			// default message displayed if invalid input received from user.
			default:
				System.out.println("Invalid Input - Please try again");
			}
		} while (option != 0);

	}

	public static void main(String[] args) throws ParseException {
		ServiceDesk A2obj = new ServiceDesk();

	}

}
