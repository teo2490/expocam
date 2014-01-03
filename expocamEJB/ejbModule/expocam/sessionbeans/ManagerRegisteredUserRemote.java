package expocam.sessionbeans;

import javax.ejb.Remote;

import expocam.entitybeans.RegisteredUser;

@Remote
public interface ManagerRegisteredUserRemote {
	public RegisteredUser checksLogin(String email, String password);
}
