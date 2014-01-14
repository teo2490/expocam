package expocam.entitybeans;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Photo implements Serializable{

	private static final long serialVersionUID = -7569667778615385681L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	
	@Lob @Basic(fetch=FetchType.LAZY)
	private byte[] image;
	
	@ManyToOne
	private RegisteredUser owner;
	
	public Photo() {}
	
	public Photo(byte[] image) {
		this.image = image.clone();
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public byte[] getImage() {
		return image;
	}
	
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public RegisteredUser getOwner(){
		return this.owner;
	}
	
	public void setOwner(RegisteredUser owner){
		this.owner = owner;
	}
}
