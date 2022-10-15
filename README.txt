# SEPMA2

** TECHNICAL REQUIREMENTS **
The program is written using Java version 8.0.
If you haven't yet installed Java on your device, please download it and follow the installation guide on this link: https://www.java.com/en/download/help/windows_manual_download.html

The recommended IDE for testing is Eclipse.
You can download and install Eclipse IDE from this link: https://www.eclipse.org/downloads/packages/

** Prerequisits **
1. Have access to SEPMA2.zip file which should be contained in the assignment submission.
2. Load SEPMA2.zip file in to Eclipse
2.1 Open Eclipse IDE
2.2 Once Eclipse has loaded click on file -> Import
2.3 Expand General, select Existing Projects Into Workspace and click next
2.4 then select the archive file radio button and click browse
2.5 go to the location of where you have stored the SEPMA2.zip and double click
2.6 click finish
2.7 you should see a package called SEPMA2 in the package explorer of eclipse



** INSTRUCTIONS **

 1. Loading and running the program

Launch Eclipse IDE and select the project directory in the workspace prompt. Once you found and selected the project folder, click on 'Launch'.
In the Package Explorer, inside the SEPMA2 folder double click on the default package. In there, you will find the ServiceDesk.java file. Open the file and click on Run. Eclipse will open a Terminal and run the program in it.

 2. Main Menu

In your terminal, the running program will display four menu options:

 1 - Set up account
 2 - Log In
 3 - Reset Password
 0 - Exit

To select one of the options above, type the corresponding menu number in the console and press enter.

 2.1 Set up a new profile

This option will open when selecting '1' in the main menu prompt.
Here, the program will ask you to give four inputs: email address, full name, phone number and password. The password you insert must be a mix of uppercase and lowercase alphanumeric characters of min length 20.
If all fields are valid, a new profile is created, otherwise an 'invalid' message will appear. In both cases, you will be redirected to the main menu.

 2.2 Log in

This option will open when selecting '2' in the main menu prompt.
Here, the program will ask you to give two inputs: email address and password. If all fields are valid and the credentials match those on file you will be redirected to either the staff menu or the technician's menu based off your log in details.

A technician may log in with their credentials:

email             |     password
harry@tech.com    |     Abc12345678901234567
niall@tech.com    |     Abc45678901234567891
liam@tech.com     |     Abc78901234567891234
louis@tech.com    |     Def12345678901234567
zayn@tech.com     |     Def45678901234567890

 2.3 Reset Password

This option will open when selecting '3' in the main menu prompt.
You may reset the password for technicians by typing 'T', for staff members by typing 'S', or go back to main menu by typing 'B'.
The reset password process is the same for both selections.
You will be asked to enter your email address first, and then your phone number for verification.
If the entered combination exists in our records, you will proceed to the next step; else, you will receive an 'invalid input' message, and be redirected to the main menu.

For testing purposes, you may refer to the technicians' credentials table below:

email             |     password                |     phone number
harry@tech.com    |     Abc12345678901234567    |     041333444
niall@tech.com    |     Abc45678901234567891    |     043444555
liam@tech.com     |     Abc78901234567891234    |     044123456
louis@tech.com    |     Def12345678901234567    |     045777666
zayn@tech.com     |     Def45678901234567890    |     041987654

Once your credentials are validated, you may proceed to reset your password.
The new password you insert must be a mix of uppercase and lowercase alphanumeric characters of min length 20.
If the password inserted is not valid, you will be prompted to re-enter a valid password.

Once a valid password is detected, your password will be successfully reset, and you will be redirected to the main menu.

 2.4 Quit program

Selecting '0' in the main menu will close the program.

 3. Technician menu

After logging in (2.2), you will be able to select the option '2 - Technician Login' to access the Technician Menu feature.
In your terminal, the running program will display four technician menu options:

 1 - View Open Tickets
 2 - View All Tickets
 3 - Edit Ticket Status
 4 - Change Ticket Severity
 0 - Logout

 3.1 View Open Tickets

Planned Feature - Currently not implemented

 3.2 View All Tickets

Planned Feature - Currently not implemented

 3.3 Edit Ticket Status

Inputing '3' in the technicians menu will take you to the edit Ticket System. You will be asked for the ticket ID number which you wish to edit. If you enter an invalid option you will be notified and returned to the main options display.
If a valid ticket is chosen then you will be able to choose the new status of the ticket. If an incorrect status is chosen then you will be notified and returned to the main display. If a correct status is chosen then the status
will be changed in the system and a message will be displayed showing these changes. A Ticket can have a status of 'open','closed and resolved', 'closed and unresolved' or 'archived'.

 3.4 Change Ticket Severity

Inputing '4' will allow you (the logged in technician) to change the severity of an existing ticket. You will first be prompted to enter in the ticket ID of the ticket you wish to make the change, and then you will have to select a new ticket severity.
The option for ticket severity are:

1 - Low
2 - Medium
3 - High

Once the ticket severity has been chosen the ticket will be reassigned based off the new ticket severity and the new ticket severity will be displayed on the console.

 3.5 Logout

Selecting '0' in the technicians menu will log you out and return to the main menu.

 4. Staff menu

After logging in (2.2), you will be able to select the option '1 - Staff Login' to access the Staff Menu feature.
In your terminal, the running program will display three staff menu options:

 1 - Submit Ticket
 2 - Check status of existing tickets
 0 - Logout

 4.1 Submit Ticket

Selecting '1' will prompt you to enter a description of the IT Issue. Once entered it will then prompt you to select the severity of the IT Issue. The severity is selected by choosing '1' for low, '2' for medium and '3' for high.
This will create a ticket and assign it to a technician.

 4.2 Check status of existing tickets

Inputing '2' in the staff menu will display all of your open tickets.

 4.3 Logout

Selecting '0' in the staff menu will log you out and return to the main menu.


 5. Tickets

Tickets are the IT Issues logged by staff to technicians

 5.1 Ticket Status

When tickets are created by staff they are automatically given the status of open. If a technician logs into the program they can follow steps to changing the status from 'open' to 'closed and resolved', 'closed and unresolved' or 'archived'.

 5.2 Ticket Severity

Tickets are given a severity to signal the severity of the IT Issue faced. Ticket severities are:

1 - Low
2 - Medium
3 - High

 5.3 Assigned Technician

A ticket will have an assigned technician. The assigned technician will attempt to resolve the IT Issue. Tickets with a Severity of High will be assigned to a level 2 technician, otherwise all other tickets are assigned to Level 1 technicians.

 5.4 Ticket ID

A ticket will automatically be assigned a unique ID.


** KNOWN ISSUES **

 1. Staff Account Setup
 1.1 Program will crash when entering a non-number in the 'Phone Number' field. - Fixed as of 15/OCT/2022 (RBullock Testing)(Previous tester ACameron)
 1.2 There is currently no validation performed on email addresses. - Not fixed as of 15/OCT/2022 (RBullock Testing)(Previous tester ACameron)
 1.3 It is possible to create multiple users with the same username (no unique constraint). Not fixed as of 15/OCT/2022 (RBullock Testing)(Previous tester ACameron)
 1.4 Entering an invalid password returns user to main menu rather than prompting to try again. Not fixed as of 15/OCT/2022 (RBullock Testing)(Previous tester ACameron)
 1.5 Full name allows numbers to be entered - Not fixed as of 15/OCT/2022 (RBullock Testing)
 1.6 Phone number allows for greater then 10 digits - Not fixed as of 15/OCT/2022 (RBullock Testing)
 1.7 all user prompts should repeat until "exit" or "x" or blank is typed or correct input is typed - Not fixed as of 15/OCT/2022 (RBullock Testing)
 1.8 Password is not hidden when typed - Not fixed as of 15/OCT/2022 (RBullock Testing)


 2. Staff ticket creation
 2.1 No validation on ticket severity - any number (including negative) is accepted - Not fixed as of 15/OCT/2022 (RBullock Testing)(Previous tester ACameron)
 2.2 No validation on ticket severity - program crashes if a letter or blank is entered. - Not fixed as of 15/OCT/2022 (RBullock Testing)(Previous tester ACameron)
 2.3 Can create blank Description in ticket - Not fixed as of 16/OCT/2022 (RBullock Testing)
 
 
 3. Reset Password
 3.1 Flow not currently implemented. Fixed as of 15/OCT/2022 (RBullock Testing)(Previous tester ACameron)
 3.2 all user prompts should repeat until "exit" or "x" or blank is typed or correct input is typed - Not fixed as of 15/OCT/2022 (RBullock Testing)
 3.3 Phone number allows letters Not fixed as of 15/OCT/2022 (RBullock Testing)

 4. Login Menu
 4.1 Login feedback says invalid input, when it should say incorrect username or password. - Not fixed as of 15/OCT/2022 (RBullock Testing)
 4.2 all user prompts should repeat until "exit" or "x" or blank is typed or correct input is typed - Not fixed as of 15/OCT/2022 (RBullock Testing)
 4.3 main menu does not have a enter input display text. example (Selection:) - Not fixed as of 15/OCT/2022 (RBullock Testing)
 4.4 There is currently no validation performed on email addresses - Not fixed as of 15/OCT/2022 (RBullock Testing)
 4.5 Password is not hidden when typed. - Not fixed as of 15/OCT/2022 (RBullock Testing)
 
 5. Main Menu
 5.1 main menu does not have a enter input display text. example (Selection:) - Not fixed as of 15/OCT/2022 (RBullock Testing)
 5.2 When input is not correct an available choice there is no error message. - Not fixed as of 15/OCT/2022 (RBullock Testing)
 
 6. Staff menu
 6.1 If no option is selected in the staff user interface (i.e. Enter is pressed on a blank line), program will prompt for an input, 
 then proceed with whatever option was chosen previously. - Not fixed as of 15/OCT/2022 (RBullock Testing)(Previous tester ACameron)
 
 7. Staff Check ticket status
 7.1 Does not show severity of ticket - Not fixed as of 15/OCT/2022 (RBullock Testing)
 
 8.Tech Menu
 8.1 If tech has no open tickets there is no message saying no tickets available - Not fixed as of 15/OCT/2022 (RBullock Testing)
 8.2 view all tickets does not have a message say no ticket. - Not fixed as of 15/OCT/2022 (RBullock Testing)
 8.3 edit ticket menu crashes when letter is typed - Not fixed as of 15/OCT/2022 (RBullock Testing)
 8.3  A) created 9 ticket with different levels of severity - Not fixed as of 15/OCT/2022 (RBullock Testing)
      B) Check tickets has been assigned to Harry Styles which was 2 tickets
 	  C) logged in to systems as Harry Styles
 	  D) Choose options 1 and 2 but no tickets displayed
 	  E) Logged in as Niall Horan and had same issue.  
 8.4 Invalid selection on edit ticket menu goes back to main tech menu,should stay on edit ticket menu - Not fixed as of 15/OCT/2022 (RBullock Testing)
 8.5 Edit ticket status open display prompt for "Please enter a ticket status to edit" should be ticket ID number - Not fixed as of 15/OCT/2022 (RBullock Testing)
 8.6 Please enter a ticket status to edit prompt crashes with blank input and letters - Not fixed as of 15/OCT/2022 (RBullock Testing)
 8.7 Techs can change ticket not assigned to them (status and Severity) - Not fixed as of 15/OCT/2022 (RBullock Testing)
 8.8 string need to have spaces Status of ticket: 1005 is now status Resolvedandclosed - Not fixed as of 15/OCT/2022 (RBullock Testing)
 8.9 selecting a status to change for a ticket crashes on blank and letters - Not fixed as of 15/OCT/2022 (RBullock Testing)
 8.10 Edit Ticket Severity -> prompt string "Please enter a ticket status to edit" should be Ticket ID - Not fixed as of 15/OCT/2022 (RBullock Testing)
 8.11 All issues with ticket status options effecting servrity. e.g 8.7,8.8, 8.9. - Not fixed as of 15/OCT/2022 (RBullock Testing)

 ** TESTING NOTES **
 1. Staff Account Setup
 1.1 Staff Account Setup should have examples in prompt string or have a /help option for examples of inputs excepted.
 
 **IMPROVEMENT NOTES **
 1.Reset password
 1.1 Ask for users username and then lookup both staff members and technician members lists and then reset password.
 2. Tech Editing Ticket
 2.1 Show list of available tickets that can be editable both status and severity