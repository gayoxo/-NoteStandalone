/**
 * 
 */
package anote;

/**
 * @author Joaquin Gayoso-Cabada
 *
 */
public class UserJSon {

	private String User;
	private String Password;
	public UserJSon(String user, String password) {
		super();
		User = user;
		Password = password;
	}
	public String getUser() {
		return User;
	}
	public void setUser(String user) {
		User = user;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	
}
