/*  
 * ServiceDesk is the main interface class which allows the user the
 * select options which will alter the 
 * Objects or retrieve the information contained in the 
 * Object Classes.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ServiceDesk {

   Scanner sc = new Scanner(System.in);

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

      // Generate hardcoded Technicians
      populateTechs();

      // local variable to hold user's menu selection. Initialised to -1
      // to prevent accidental menu option by the program.
      int choice = -1;

      // displays Menu at least once and then will repeat up until
      // user chooses to quit program.

      do {
         displayBasicMenu();
         // try/catch block is used here to ensure the user enters a valid menu
         // option.
         try {
            choice = Integer.parseInt(sc.nextLine());
         } catch (NumberFormatException e) {
            System.out.println("Please enter a valid menu option!");
         }
         if (choice != 0) {
            processBasicMenu(choice);
         } else if (choice == 0) {
            System.out.println("Bye!");
         }
      } while (choice != 0);

      sc.close();
   }

   public void displayBasicMenu() {
      System.out.println("Please select from the following menu items:");
      System.out.println("1 - Set up account");
      System.out.println("2 - Log In");
      System.out.println("3 - Exit");
   }

   // processBasicMenu receives an int as a parameter from the user in the 'ServiceDesk'
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
            String newEmail = (sc.nextLine());
            System.out.println("Please Enter Your Full Name:");
            String fullName = (sc.nextLine());
            System.out.println("Please Enter Your Phone Number:");
            int number = Integer.parseInt(sc.nextLine());
            System.out.println(
                  "Please Enter Your Password (Password must be a mix of uppercase and lowercase alphanumeric characters of min length 20.):");
            String newPass = (sc.nextLine());

            HashMap<String, String> newCredentials = new HashMap<String, String>();
            newCredentials.put(newEmail, newPass); 
            Staff newStaff = new Staff(fullName, newCredentials, number); 
            staffMembers.add(newStaff);
            break;   
         // Allows the user to enter in their credentials. Checks the credentails against
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
            Staff tempStaff = null;

            for (Staff staff : staffMembers) {
               if (staff.getLogin().containsKey(email) && staff.getLogin().containsValue(pass)) {
                  staffMember = true;
                  name = staff.getName();
                  tempStaff = staff;
               }
            }

            for (Technician technicians : techs) {
               if (technicians.getLogin().containsKey(email) && technicians.getLogin().containsValue(pass)) {
                  tech = true;
                  name = technicians.getName();
               }
            }

            if (staffMember) {

               do {
                  displayStaffMenu(name);
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
                  displayTechMenu(name);
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
         // default message displayed if invalid input received from user.
         default:
            System.out.println("Invalid Input - Please try again");
      }

   }

   // displayMenu method simply displays the method in a neat
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

      // processMenu receives an int as a parameter from the user in the 'Staff'
   // method. Parameter is used as the menu selection path.
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
            
            // TODO: Logic for ticket allocation

            for (Technician tech : techs) {
               if (level == 1 && tech.getLevel() == 1) {

               } else if (level == 2 && tech.getLevel() == 2) {

               }
            }

            tickets.add(tempTicket);

            break;

         case 2:
            for (Ticket ticket : tickets) {
               if(ticket.getStatus() == Status.OPEN && ticket.getStaff() == tempStaff) {
                  System.out.println("");
                  System.out.println("" + ticket.toString());
                  System.out.println("");
               }
            }
            break;
         // default message displayed if invalid input received from user.
         default:
            System.out.println("Invalid Input - Please try again");
      }

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
      System.out.println("0 - Logout");
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
         // default message displayed if invalid input received from user.
         default:
            System.out.println("Invalid Input - Please try again");
      }

   }

   // Slows the display of the menu to allow the user to view the information
   // before continuing on. Implemented as a method to minimize code duplication.
   public void userContinue() {
      System.out.println("");
      System.out.println("Press Enter to continue");
      sc.nextLine();
   }

   public void populateTechs() {
      HashMap<String, String> map = new HashMap<String, String>();
      map.put("harry@tech.com", "Abc12345678901234567");
      Technician a = new Technician("Harry Styles", map, 1);
      this.techs.add(a);
      HashMap<String, String> map1 = new HashMap<String, String>();
      map1.put("niall@tech.com","Abc45678901234567891");
      Technician b = new Technician("Niall Horan", map1, 1);
      this.techs.add(b);
      HashMap<String, String> map2 = new HashMap<String, String>();
      map2.put("liam@tech.com","Abc78901234567891234");
      Technician c = new Technician("Liam Payne", map2, 1);
      this.techs.add(c);
      HashMap<String, String> map3 = new HashMap<String, String>();
      map3.put("louis@tech.com","Def12345678901234567");
      Technician d = new Technician("Louis Tomlinson", map3, 2);
      this.techs.add(d);
      HashMap<String, String> map4 = new HashMap<String, String>();
      map4.put("zayn@tech.com","Def4567890123456789");
      Technician e = new Technician("Zayn Malik", map4, 2);
      this.techs.add(e);
   }

   public static void main(String[] args) {
      ServiceDesk A2obj = new ServiceDesk();

   }

}