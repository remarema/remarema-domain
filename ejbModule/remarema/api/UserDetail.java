package remarema.api;

/**
 * Diese Klasse dient als DTO und enthält alle Datenfelder, die in der Datenbank
 * vorhanden sind. Des Weiteren enthält die Klasse get- und set-Methoden.
 * 
 * @author Rebecca van Langelaan
 *
 */
public class UserDetail {

	private int userID;
	public String userName;
	public String userPassword;
	public int userRights;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getUserRights() {
		return userRights;
	}

	public void setUserRights(int userRights) {
		this.userRights = userRights;
	}

}
