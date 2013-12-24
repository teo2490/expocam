package expocam.entitybeans;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="Story")
public class Story implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="S_ID")
	private int id;
	private String content;
	private int like;
	private int inappr;
	
	@ManyToOne
	private RegisteredUser author;
	
	/*
	public Story(String a){
		author.setNome(a);
		like = 0;
		inappropriate = 0;
	}
	
	public int getId(){
		return id;
	}*/
	
	public int getNumLike(){
		return like;
	}
	
	public int genNumInappr(){
		return inappr;
	}
	
	public String getContent(){
		return content;
	}
	
	public void like(){
		like = like + 1;
	}
	
	public void inappropriate(){
		inappr = inappr + 1;
	}
	
	public void setContent(String c){
		content = c;
	}
	
}