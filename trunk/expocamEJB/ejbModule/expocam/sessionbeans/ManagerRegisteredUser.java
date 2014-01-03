package expocam.sessionbeans;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import expocam.entitybeans.RegisteredUser;

@Stateless
@Remote(ManagerRegisteredUserRemote.class)
public class ManagerRegisteredUser {
	
	@PersistenceContext(unitName = "expocam")
	private EntityManager em;
	
	private List<RegisteredUser> users;
	
	public RegisteredUser checksLogin(String email, String password) {
		Query q = em.createQuery("SELECT u FROM RegisteredUser u WHERE u.email = :email AND u.password = :password");
		q.setParameter("email", email);
		q.setParameter("password", password);
		users = (List<RegisteredUser>) q.getResultList();
		if (users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}

}
