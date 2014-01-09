package expocam.entitybeans;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Story implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String content;
	private int iLike;
	private int inappr;
	
	/*@ManyToOne*/
	@ManyToOne
	private RegisteredUser author;
	
	
	public Story(String c, RegisteredUser user){
		content = c;
		iLike = 0;
		inappr = 0;
		author = user;
	}
	
	public int getId(){
		return id;
	}
	
	public int getNumLike(){
		return iLike;
	}
	
	public int genNumInappr(){
		return inappr;
	}
	
	public String getContent(){
		return content;
	}
	
	public void like(){
		iLike = iLike + 1;
	}
	
	public void inappropriate(){
		inappr = inappr + 1;
	}
	
	public void setContent(String c){
		content = c;
	}
	
}