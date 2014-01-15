package expocam.sessionbeans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import expocam.entitybeans.Photo;
import expocam.entitybeans.Tag;

/**
 * Session Bean implementation class ManagerTag
 */
@Stateless
public class ManagerTag implements ManagerTagRemote {
	
	@PersistenceContext( unitName = "expocam" )
	private EntityManager em;
    
	public void addNewTag(String p, String t) {
		int intP = Integer.parseInt(p); 
		Tag tag = new Tag(intP, t);
		em.persist(tag);
	}
	
	public List<String> getTag(String id){
		int idd = Integer.parseInt(id);
		Query q = em.createQuery("SELECT i.tagPhoto FROM Tag i WHERE i.idPhoto = :idd");
		q.setParameter("idd", idd); 
		List<String> allTag = (List<String>) q.getResultList();
		return allTag;
	}

}
