package expocam.sessionbeans;

import javax.ejb.Remote;

import expocam.entitybeans.Photo;

@Remote
public interface ManagerTagRemote {
	public void addNewTag(String p, String t);
}
