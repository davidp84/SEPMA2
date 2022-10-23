import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Ticket {

	// String which holds the description.
	private String description;
	// The tickets severity.
	private Severity severity;
	// The ticket status.
	private Status status;
	// Static int which is used as a template for the unique tickets ID.
	private static int IDTracker = 1000;
	// int which holds the unique ID of the ticket.
	private int ticketID;
	// Assigned technician
	private Technician technician;
	// Sets time and date of ticket creation
	private Date dateTime;
	// Staff member which submitted the ticket
	private Staff staff;
	// Time ticket was last closed
	private LocalDateTime closeTime;

	// Constructor which is called when a new object is created by the user.
	public Ticket(String description, Severity severity, Staff staff) {
		this.description = description;
		this.severity = severity;
		this.status = Status.OPEN;
		this.ticketID = Ticket.IDTracker;
		// increments ID for next ticket
		Ticket.IDTracker++;
		this.dateTime = new Date();
		this.staff = staff;
		
//		LocalDateTime myDateObj = this.dateTime;
//	    System.out.println("Before formatting: " + myDateObj);
//	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//
//	    String formattedDate = myDateObj.format(myFormatObj);
//	    System.out.println("After formatting: " + formattedDate);
	}

	public Staff getStaff() {
		return staff;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public Technician getTechnician() {
		return technician;
	}

	public void setTechnician(Technician technician) {
		this.technician = technician;
	}

	public int getTicketID() {
		return ticketID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public Status getStatus() {
		checkArchive();
		return status;
	}

	public void setStatus(Status status) {
		if (status == Status.RESOLVEDANDCLOSED || status == Status.UNRESOLVEDANDCLOSED)
			closeTime = LocalDateTime.now();
		this.status = status;
	}
	
	public void checkArchive() {
		if (status == Status.RESOLVEDANDCLOSED || status == Status.UNRESOLVEDANDCLOSED)
			if (closeTime.isAfter(LocalDateTime.now().plusDays(1)))
				this.status = Status.ARCHIVED;
	}

	public String toString() {
		checkArchive();
		String message;
		message = this.getClass().getSimpleName() + "\n";
		message += "Ticket ID - " + this.ticketID + "\n";
		message += "Issue Description - " + this.description + "\n";
		message += "Issue Status - " + this.status + "\n";
		message += "Assigned Technician - " + this.technician.getName();
		message += "\n";

		return message;
	}
}
