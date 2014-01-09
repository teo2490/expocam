package expocam.sessionbeans;
import java.util.List;

import javax.ejb.Remote;

import expocam.entitybeans.RegisteredUser;
import expocam.entitybeans.Story;

@Remote
public interface ManagerStoryRemote {
	public void newStory(String s, RegisteredUser u);
	public List<Story> getAllStory();
	public void addOneVote(String id);
}
