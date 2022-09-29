/*  
 * ServiceDesk is the main interface class which allows the user the
 * select options which will alter the 
 * Objects or retrieve the information contained in the 
 * Object Classes.
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ServiceDesk {

   Scanner sc = new Scanner(System.in);

   // Array of Stock objects which holds the data for the
   // MT Company's Stock Items.
   private List<Staff> staffMembers;
   // Array of Customer objects which holds the customer
   // information for the MT Company.
   private List<Technician> techs;
   // Array of Customer objects which holds the customer
   // information for the MT Company.
   private List<Ticket> tickets;


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
            // TODO: Create Account 
            break;
         // Allows the user to add a new customer to the system provided that the
         // maximum number of customers is not reach, otherwise it displays a
         // message to the user advising them that the maximum has been reached.
         case 2:
            // TODO: Create Login Logic
            break;
         // default message displayed if invalid input received from user.
         default:
            System.out.println("Invalid Input - Please try again");
      }

   }

   // displayMenu method simply displays the method in a neat
   // and easily interpreted way for the user.
   public void displayStaffMenu() {
      System.out.println("Please select from the following menu items:");
      System.out.println("1 - Submit Ticket");
      System.out.println("2 - Check Status of existing ticket");
      System.out.println("0 - Logout");
   }

      // processMenu receives an int as a parameter from the user in the 'Staff'
   // method. Parameter is used as the menu selection path.
   public void processStaffMenu(int choice) {
      // switch is used to process the retrieved menu selection from the user by
      // calling the relevant methods.
      switch (choice) {

         case 0:

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

   // displayMenu method simply displays the method in a neat
   // and easily interpreted way for the user.
   public void displayTechMenu() {
      System.out.println("Please select from the following menu items:");
      System.out.println("1 - View Open Tickets");
      System.out.println("2 - View All Tickets");
      System.out.println("0 - Logout");
   }

   // processMenu receives an int as a parameter from the user in the 'Staff'
   // method. Parameter is used as the menu selection path.
   public void processTechMenu(int choice) {
      // switch is used to process the retrieved menu selection from the user by
      // calling the relevant methods.
      switch (choice) {

         case 0:

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
      map.clear();
      map.put("niall@tech.com","Abc45678901234567891");
      Technician b = new Technician("Niall Horan", map, 1);
      map.clear();
      map.put("liam@tech.com","Abc78901234567891234");
      Technician c = new Technician("Liam Payne", map, 1);
      map.clear();
      map.put("louis@tech.com","Def12345678901234567");
      Technician d = new Technician("Louis Tomlinson", map, 2);
      map.clear();
      map.put("zayn@tech.com","Def4567890123456789");
      Technician e = new Technician("Zayn Malik", map, 2);
      this.techs.add(a);
      this.techs.add(b);
      this.techs.add(c);
      this.techs.add(d);
      this.techs.add(e);
   }

   public static void main(String[] args) {
      ServiceDesk A2obj = new ServiceDesk();

   }

}