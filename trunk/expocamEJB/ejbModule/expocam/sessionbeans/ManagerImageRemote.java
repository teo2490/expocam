package expocam.sessionbeans;

import java.sql.Blob;
import java.util.List;

import javax.ejb.Remote;

import expocam.entitybeans.Image;
import expocam.entitybeans.RegisteredUser;

@Remote
public interface ManagerImageRemote {
	public void addImage(String name, String pic, RegisteredUser user);
	public List<Image> getListMyImage(RegisteredUser u);
}
