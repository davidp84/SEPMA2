import java.time.LocalDateTime;

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
     private LocalDateTime dateTime;

     // Contructor which is called when a new object is created by the user.
     public Ticket(String description, Severity severity)
     {
        this.description = description;
        this.severity = severity;
        this.status = Status.OPEN;
        this.ticketID = this.IDTracker;
        // increments ID for next ticket
        this.IDTracker++;
        this.dateTime = dateTime.now();
     }

    public LocalDateTime getDateTime() {
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
      return status;
    }

    public void setStatus(Status status) {
      this.status = status;
    }

}
