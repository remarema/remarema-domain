package remarema.services.user;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import remarema.api.UserDetail;
import remarema.domain.User;

/**
 * Das <code>UserServiceBean</code> beinhaltet zwei wichtige Methoden, die für
 * die Anmeldung auf der Weboberfläche benötigt werden.
 * 
 * @author Rebecca van Langelaan
 */
@Stateless
@LocalBean
public class UserServiceBean {

	@PersistenceContext
	protected EntityManager em;

	/**
	 * EJB default constructor
	 */
	public UserServiceBean() {

	}

	// Dient nur zum Testen; package-protected
	UserServiceBean(EntityManager em) {
		this.em = em;
	}

	/**
	 * Die Methode <code>checkUser()</code> benötigt als Parameter ein <code>UserDetail</code>-Objekt. 
	 * Es werden der Benutzernam und das Password ausgelesen. Danach wird die Methode 
	 * {@link #findUserByNameAndPassword(String, String)} aufgerufen.
	 * 
	 * @param userDetail
	 * @return
	 * @throws UserNotFoundException
	 */
	public int checkUser(UserDetail userDetail) throws UserNotFoundException {
		String userName = userDetail.getUserName();
		String userPassword = userDetail.getUserPassword();

		return findUserByNameAndPassword(userName, userPassword);

	}

	/**
	 * Die Methode <code>findUserByNameAndPassword()</code> benötigt als
	 * Parameter den userNamen und das userPassword, beide vom Typ String. Zu
	 * Beginn wird eine Query ausgeführt, welche nach dem Benutzer und Passwort
	 * in der Datenbank sucht.
	 * 
	 * Im <code>try</code>-Block wird das Ergebnis der Query in einem neuen
	 * User-Objekt gespeichert. Dieser User wird zurückgegeben. Gibt es keinen
	 * passenden User in der Datenbank kommt man in den <code>catch</code>
	 * -Block, in dem die <code>UserNotFoundException</code> geworfen wird.
	 * 
	 * @param userName
	 * @param userPassword
	 * @return Zurückgegeben wird eine Zahl vom Typ int; diese stellt die
	 *         Userrechte dar.
	 * @throws UserNotFoundException
	 *             Wird geworfen, wenn der Benutzername oder das Passwort, mit
	 *             dem man sich einloggen möchte, nicht in der Datenbank
	 *             vorhanden ist.
	 */
	private int findUserByNameAndPassword(String userName, String userPassword)
			throws UserNotFoundException {
		TypedQuery<User> query = em
				.createQuery(
						"SELECT o FROM User o WHERE o.userName = :name AND o.userPassword = :password",
						User.class);

		query.setParameter("name", userName);
		query.setParameter("password", userPassword);

		try {
			User user = query.getSingleResult();
			return user.getUserRights();
		} catch (Exception e) {
			throw new UserNotFoundException(
					"Der angegebene User wurde nicht gefunden!");
		}
	}

}
