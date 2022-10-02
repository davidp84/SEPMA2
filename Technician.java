import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Technician {
  
     // String which holds the staff members's name.
     private String name;
     // Map which holds the staff's unique email and password.
     private Map<String, String> login;
     // The techs level.
     private int level;
     // Assigned tickets.
     private List<Ticket> tickets = new ArrayList<>();

  
     // Constructor which is called when a new object is created by the user.
     public Technician(String name, HashMap<String, String> login, int level)
     {
        this.name = name;
        this.login = login;
        this.level = level;
     }


    public String getName() {
      return name;
    }


    public void setName(String name) {
      this.name = name;
    }


    public Map<String, String> getLogin() {
      return login;
    }


    public void setLogin(Map<String, String> login) {
      this.login = login;
    }


    public int getLevel() {
      return level;
    }


    public void setLevel(int level) {
      this.level = level;
    }


    public List<Ticket> getTickets() {
      return tickets;
    }

    // Add ticket to List
    public void addTickets(Ticket ticket) {
      this.tickets.add(ticket);
    }

    public int getAssignedTickets() {
      return tickets.size();
    }



}
