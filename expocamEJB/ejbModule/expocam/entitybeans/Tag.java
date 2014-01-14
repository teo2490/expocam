package expocam.entitybeans;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="Tag")
public class Tag implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int idPhoto;
	private String tagPhoto;
	
	public Tag(){}
	
	public Tag(int idP, String tagP){
		this.idPhoto = idP;
		this.tagPhoto = tagP;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setid(int id) {
		this.id = id;
	}
	
	public int getIdPhoto() {
		return this.idPhoto;
	}
	
	public void setIdPhoto(int id) {
		this.idPhoto = id;
	}
	
	public String getTag() {
		return this.tagPhoto;
	}
	
	public void setTag(String tag) {
		this.tagPhoto = tag;
	}
	
}
