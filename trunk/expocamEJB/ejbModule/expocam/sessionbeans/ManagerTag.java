package expocam.sessionbeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
