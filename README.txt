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

This options will open when selecting 1 in the main menu prompt.
Here, the program will ask you to give four inputs: email address, full name, phone number and password. The password you insert must be a mix of uppercase and lowercase alphanumeric characters of min length 20.
If all fields are valid, a new profile is created, otherwise an 'invalid' message will appear. In both cases, you will be redirected to the main menu.

 2.2 Log in

 2.3 Reset Password

 2.4 Quit program

 3. Technician menu 

 4. Staff menu

// any other functionalities we need to explain?


**Ticket Status**
When tickets are created by staff they are automatically given the status of open. If a technician logs into the program they can follow steps to changing the status from open to resolved, unresolved or closed.

A technician, once logged in, will be met by options that they can choose from. Option 3 is 'Edit Ticket Status'. Following this the tech will be asked for the ticket ID number which they wish to edit. If the tech enters an invalid option they will be notified and returned to the main options display. If a valid ticket is chosen then the tech will be able to choose the new status of the ticket. If an incorrect status is chosen then the tech will be notified and returned to the main display. If a correct status is chosen then the status will be changed in the system and a message will be displayed showing these changes.
