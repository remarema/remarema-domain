package remarema.services.user;

/**
 * Dies ist eine selbst definierte Exception. Die Exception wird geworfen, wenn
 * man sich auf der Login-Seite mit einem falschen Benutzernamen oder Passwort
 * anmeldet.
 * 
 * @author Rebecca van Langelaan
 *
 */
public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String s) {
		super(s);
	}

}
