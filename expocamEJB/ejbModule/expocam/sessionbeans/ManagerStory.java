package expocam.sessionbeans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import expocam.entitybeans.RegisteredUser;
import expocam.entitybeans.Story;

/**
 * Session Bean implementation class ManagerStory
 */
@Stateless
public class ManagerStory implements ManagerStoryRemote {

	@PersistenceContext( unitName = "expocam" )
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerStory() {
        // TODO Auto-generated constructor stub
    };

    public void newStory(String story, RegisteredUser u){
    	Story s = new Story(story, u);
    	em.persist(s);
    };
    
    public List<Story> getAllStory() {
		Query q = em.createQuery("SELECT s FROM Story s");
		List<Story> str = (List<Story>) q.getResultList();
		return str;
	}
	
	public void addOneVote(String id) {
		Query q = em.createQuery("SELECT s FROM Story s WHERE s.id = :id");
		int idInt = Integer.parseInt(id);
		q.setParameter("id", idInt);
		List<Story> ls = (List<Story>) q.getResultList();
		Story s = ls.get(0);
		s.like();
	}
	
	public void reportTheStory(String id) {
		Query q = em.createQuery("SELECT s FROM Story s WHERE s.id = :id");
		int idInt = Integer.parseInt(id);
		q.setParameter("id", idInt);
		List<Story> ls = (List<Story>) q.getResultList();
		Story s = ls.get(0);
		s.inappropriate();
	}
}
