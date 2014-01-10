package expocam.sessionbeans;

import javax.ejb.Remote;

import expocam.entitybeans.RegisteredUser;

@Remote
public interface ManagerRegisteredUserRemote {
	public RegisteredUser checksLogin(String email, String password);
	public boolean esisteMail(String email);
	public void aggiungiUtente(String email, String password, String nome, String cognome);
}
