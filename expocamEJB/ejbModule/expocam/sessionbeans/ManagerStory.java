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
	
	public boolean storyAlreadyVoted(RegisteredUser u, String sId) {
		Query q = em.createQuery("SELECT s FROM Story s WHERE s.id = :st_id AND :user MEMBER OF s.voter");
		q.setParameter("user", u);
		q.setParameter("st_id", Integer.parseInt(sId));
		//int sIdInt = Integer.parseInt(sId);
		List<Story> storyVoted = (List<Story>) q.getResultList();
		if(!storyVoted.isEmpty()){
			return true;
		} else {
			return false;
		}
	}
	
	public void addVotedStory(RegisteredUser u, String sId){
		Query q = em.createNativeQuery("INSERT INTO `expocam`.`USER_VOTED_STORY` (`RegisteredUser_ID`, `Story_ID`) VALUES (:email, :id);");
		q.setParameter("email", u.getEmail());
		q.setParameter("id", sId);
		q.executeUpdate();
	}
	
	public boolean storyAlreadyReported(RegisteredUser u, String sId) {
		Query q = em.createQuery("SELECT s FROM Story s WHERE s.id = :st_id AND :user MEMBER OF s.reporter");
		q.setParameter("user", u);
		q.setParameter("st_id", Integer.parseInt(sId));
		List<Story> storyReported = (List<Story>) q.getResultList();
		if(!storyReported.isEmpty()){
			return true;
		} else {
			return false;
		}
	}
	
	public void addReportedStory(RegisteredUser u, String sId){
		Query q = em.createNativeQuery("INSERT INTO `expocam`.`USER_REPORTED_STORY` (`RegisteredUser_ID`, `Story_ID`) VALUES (:email, :id);");
		q.setParameter("email", u.getEmail());
		q.setParameter("id", sId);
		q.executeUpdate();
	}
}
