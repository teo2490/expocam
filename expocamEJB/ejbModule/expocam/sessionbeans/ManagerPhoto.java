package expocam.sessionbeans;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import expocam.entitybeans.Photo;
import expocam.entitybeans.RegisteredUser;

/**
 * Session Bean implementation class ManagerPhoto
 */
@Stateless
@Remote(ManagerPhotoRemote.class)
public class ManagerPhoto implements ManagerPhotoRemote {


	@PersistenceContext(unitName = "expocam")
	private EntityManager em;
	private List<RegisteredUser> users;
    /**
     * Default constructor. 
     */
    public ManagerPhoto() {
        // TODO Auto-generated constructor stub
    }
     
    public void addImage(String name,  byte[] pic, RegisteredUser user) {
		Photo img = new Photo(pic);
		img.setName(name);
		//img.setImage(pic);
		img.setOwner(user);
		em.persist(img);
	};
	
	public List<Photo> getListMyImage(RegisteredUser u){
		Query q = em.createQuery("SELECT i FROM Photo i WHERE i.owner = :email");
		q.setParameter("email", u.getEmail());
		List<Photo> allImage = (List<Photo>) q.getResultList();
		return allImage;
	}
	
	public Photo getPhotoByID(int idd){
		Query q = em.createQuery("SELECT i FROM Photo i WHERE i.id = :ident");
		q.setParameter("ident", idd);
		List<Photo> allImage = (List<Photo>) q.getResultList();
		return allImage.get(0);
	}


}
