import java.util.HashMap;
import java.util.Map;

public class Staff {
  
   // String which holds the staff members's name.
   private String name;
   // Map which holds the staff's unique email and password.
   private Map<String, String> login;
   // The staff members phone number.
   private String phoneNumber;

   // Constructor which is called when a new object is created by the user.
   public Staff(String name, HashMap<String, String> login, String phoneNumber)
   {
      this.name = name;
      this.login = login;
      this.phoneNumber = phoneNumber;
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

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

   
}
