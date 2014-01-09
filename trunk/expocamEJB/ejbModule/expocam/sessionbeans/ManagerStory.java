package expocam.sessionbeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
