package remarema.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Diese Klasse wurde mit <code>@Entity</code> annotiert und stellt somit eine
 * Tabelle in der Datenbank dar. Sie enhält alle Datenfelder der Tabelle
 * <code>user</code>. Des Weiteren gibt es diverse get- und
 * set-Methoden.
 * 
 * @author Rebecca van Langelaan
 *
 */
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue()
	@Column(name = "userID")
	private int userID;

	@Column(name = "userName")
	private String userName;

	@Column(name = "userPassword")
	private String userPassword;

	@Column(name = "userRights")
	private int userRights;

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
