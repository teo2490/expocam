package expocam.entitybeans;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.*;

@Entity
@Table(name="Image")
public class Image implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String pic;

	@ManyToOne
	private RegisteredUser owner;
	
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getPic(){
		return this.pic;
	}
	
	public void setPic(String pic){
		this.pic = pic;
	}
	
	public RegisteredUser getOwner(){
		return this.owner;
	}
	
	public void setOwner(RegisteredUser owner){
		this.owner = owner;
	}
}
