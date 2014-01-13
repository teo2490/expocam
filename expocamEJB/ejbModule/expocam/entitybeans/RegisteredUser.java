package expocam.entitybeans;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="RegisteredUser")
public class RegisteredUser implements Serializable{
	 
	@Id
	private String email;
	private String password;
	private String nome;
	private String cognome;
	//private String url; //Parte commentata perché riguardante l'immagine del profilo (possibile aggiungere in seguito)
	
	//Storie scritte
	@OneToMany(mappedBy="author")
	private Set<Story> story;
	
	//Immagini caricate
	@OneToMany(fetch = FetchType.EAGER, mappedBy="owner", cascade = CascadeType.ALL)
	private List<Image> image;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		    name="USER_VOTED_STORY",
		    joinColumns=
		        {@JoinColumn(name="RegisteredUser_ID"/*, referencedColumnName="U_ID"*/)},
		    inverseJoinColumns=
		        {@JoinColumn(name="Story_ID"/*, referencedColumnName="A_ID"*/)}
		)
	private Set<Story> votedStory;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		    name="USER_REPORTED_STORY",
		    joinColumns=
		        {@JoinColumn(name="RegisteredUser_ID"/*, referencedColumnName="U_ID"*/)},
		    inverseJoinColumns=
		        {@JoinColumn(name="Story_ID"/*, referencedColumnName="A_ID"*/)}
		)
	private Set<Story> reportedStory;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPsw() {
		return password;
	}

	public void setPsw(String psw) {
		this.password = psw;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	//Parte commentata perché riguardante l'immagine del profilo (possibile aggiungere in seguito)
	/*public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}*/
	
	public Set<Story> getStory(){
		return this.story;
	}
	
	public void setStory(Set<Story> s){
		this.story = s;
	}
	
	public List<Image> getImage(){
		return this.image;
	}
	
	public void setVotedStory(Set<Story> vs) {
		this.votedStory = vs;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegisteredUser other = (RegisteredUser) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
}
