package expocam.sessionbeans;

import java.sql.Blob;
import java.util.ArrayList;
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
		Query q = em.createQuery("SELECT i FROM Photo i WHERE i.owner = :owner");
		q.setParameter("owner", u); 
		List<Photo> allImage = (List<Photo>) q.getResultList();
		return allImage;
	}
	
	public Photo getPhotoByID(int idd){
		Query q = em.createQuery("SELECT i FROM Photo i WHERE i.id = :ident");
		q.setParameter("ident", idd);
		List<Photo> allImage = (List<Photo>) q.getResultList();
		return allImage.get(0);
	}
	
	public String getTag(String id){
		int idd = Integer.parseInt(id);
		Query q = em.createQuery("SELECT i.name FROM Photo i WHERE i.id = :idd");
		q.setParameter("idd", idd);
		List<String> allTag = (List<String>) q.getResultList();
		return allTag.get(0);
	}
	
	public List<byte []> getPhoto(String id){
		int idd = Integer.parseInt(id);
		Query q = em.createQuery("SELECT i.image FROM Photo i WHERE i.id = :idd");
		q.setParameter("idd", idd);
		List<byte []> allPhoto = (List<byte []>) q.getResultList();
		return allPhoto;
	}
	
	public List<String> getAllID(){
		Query q = em.createQuery("SELECT i.id FROM Photo i");
		List<Integer> allID = (List<Integer>) q.getResultList();
		List<String> allSID = new ArrayList<String>();
		for(int i=0; i<allID.size(); i++){
			allSID.add(i, allID.get(i).toString());
		}
		return allSID;
	}


}
