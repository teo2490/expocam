package expocam.sessionbeans;
import java.util.List;

import javax.ejb.Remote;

import expocam.entitybeans.Photo;
import expocam.entitybeans.RegisteredUser;

@Remote
public interface ManagerPhotoRemote {

	public void addImage(String name,  byte[] pic, RegisteredUser user);
	public List<Photo> getListMyImage(RegisteredUser u);
	public Photo getPhotoByID(int idd);
	public String getTag(String id);
	public List<String> getAllID();
}
