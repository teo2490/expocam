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
	public void reportTheStory(String id);
	public boolean storyAlreadyVoted(RegisteredUser u, String sId);
	public void addVotedStory(RegisteredUser u, String sId);
	public boolean storyAlreadyReported(RegisteredUser u, String sId);
	public void addReportedStory(RegisteredUser u, String sId);
}
