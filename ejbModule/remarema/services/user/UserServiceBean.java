package remarema.services.user;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import remarema.api.UserDetail;
import remarema.domain.User;

@Stateless
@LocalBean
public class UserServiceBean {

	@PersistenceContext
	protected EntityManager em;

	public UserServiceBean() {

	}

	public UserServiceBean(EntityManager em) {
		this.em = em;
	}

	public int checkUser(UserDetail userDetail) throws UserNotFoundException {
		String userName = userDetail.getUserName();
		String userPassword = userDetail.getUserPassword();

		return findUserByNameAndPassword(userName, userPassword);

	}

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
