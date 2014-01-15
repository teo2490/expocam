package expocam.sessionbeans;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.Query;

import expocam.entitybeans.Photo;

@Remote
public interface ManagerTagRemote {
	public void addNewTag(String p, String t);
	public List<String> getTag(String id);
}
