import java.util.ArrayList;
import java.util.List;

public class SystemOwner {

	// String which holds the system owner's name.
	private final String username;
	// String which holds the system owner's password.
	private final String password;
	private Report report;

	public SystemOwner() {
		this.username = "admin";
		this.password = "admin";
	}

	// checks that the credentials in parameter match the built in information
	public boolean checkCredentials(String username, String password) {

		if (username.equals(this.username) && password.equals(this.password))
			return true;
		else
			return false;
	}

}
