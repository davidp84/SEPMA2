# SEPMA2

** TECHNICAL REQUIREMENTS **
The program is written using Java version 8.0. 
If you haven't yet installed Java on your device, please download it and follow the installation guide on this link: https://www.java.com/en/download/help/windows_manual_download.html

The recommended IDE for testing is Eclipse.
You can download and install Eclipse IDE from this link: https://www.eclipse.org/downloads/packages/


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
Here, the program will ask you to give two inputs: email address and password. It will then prompt you to select either '1' for staff login or '2' for the technicians login. Each login will take you to the respective menu. 
If all fields are valid and the credentials match those on file you will be redirected to the menu which was selected.

A technician may log in with their credentials:

email             |     password
harry@tech.com    |     Abc12345678901234567
niall@tech.com    |     Abc45678901234567891
liam@tech.com     |     Abc78901234567891234
louis@tech.com    |     Def12345678901234567
zayn@tech.com     |     Def45678901234567890

 2.3 Reset Password

This option will open when selecting '3' in the main menu prompt.
Resetting your password will prompt you to enter 'y' if you have forgotten your password, or 'n' if you have not forgotten your password and perhaps accidentally entered in to the reset password menu option.
User's are currently not able to reset their password, this feature will be added shortly.

 2.4 Quit program

Selecting '0' in the main menu will close the program.

 3. Technician menu 

After logging in (2.2), you will be able to select the option '2 - Technician Login' to access the Technician Menu feature.
In your terminal, the running program will display four technician menu options:

 1 - View Open Tickets
 2 - View All Tickets
 3 - Edit Ticket Status
 0 - Logout

 3.1 View Open Tickets

Planned Feature - Currently not implemented

 3.2 View All Tickets

Planned Feature - Currently not implemented

 3.3 Edit Ticket Status

Inputing '3' in the technicians menu will take you to the edit Ticket System. You will be asked for the ticket ID number which you wish to edit. If you enter an invalid option you will be notified and returned to the main options display. 
If a valid ticket is chosen then you will be able to choose the new status of the ticket. If an incorrect status is chosen then you will be notified and returned to the main display. If a correct status is chosen then the status 
will be changed in the system and a message will be displayed showing these changes. A Ticket can have a status of 'open','closed and resolved', 'closed and unresolved' or 'archived'. 

 3.4 Logout

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
 1.1 Program will crash when entering a non-number in the 'Phone Number' field.
 1.2 There is currently no validation performed on email addresses.
 1.3 It is possible to create multiple users with the same username (no unique constraint).
 1.4 Entering an invalid password returns user to main menu rather than prompting to try again.

 2. Ticket creation
 2.1 No validation on ticket severity - any number (including negative) is accepted, program crashes if another character is entered.
 2.2 "Low" severity has an extraneous colon
 2.3 If no option is selected in the staff user interface (i.e. Enter is pressed on a blank line), program will prompt for an input, then proceed with whatever option was chosen previously.

 3. View Ticket Status
 3.1 If there are no open tickets, UI simply says press "Enter to continue" and does not indicate that there are currently no open tickets.

 4. Reset Password
 4.1 Flow not currently implemented.