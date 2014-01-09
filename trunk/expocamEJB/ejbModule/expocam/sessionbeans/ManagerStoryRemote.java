package expocam.sessionbeans;
import javax.ejb.Remote;

import expocam.entitybeans.RegisteredUser;

@Remote
public interface ManagerStoryRemote {
	public void newStory(String s, RegisteredUser u);
}
