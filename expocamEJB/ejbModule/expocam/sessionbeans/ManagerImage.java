package expocam.sessionbeans;

import java.sql.Blob;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import expocam.entitybeans.Image;
import expocam.entitybeans.RegisteredUser;

@Stateless
@Remote(ManagerImageRemote.class)
public class ManagerImage {
	
	@PersistenceContext(unitName = "expocam")
	private EntityManager em;
	
	private List<RegisteredUser> users;
	
	public void addImage(String name, String pic, RegisteredUser user) {
		Image img = new Image();
		img.setName(name);
		img.setPic(pic);
		img.setOwner(user);
	};
	
	public List<Image> getListMyImage(RegisteredUser u){
		Query q = em.createQuery("SELECT i FROM Image i WHERE i.owner = :email");
		q.setParameter("email", u.getEmail());
		List<Image> allImage = (List<Image>) q.getResultList();
		return allImage;
	}

}