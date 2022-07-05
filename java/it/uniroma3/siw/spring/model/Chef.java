package it.uniroma3.siw.spring.model;

import java.util.List;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Data;

@Entity
@Data
public class Chef {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String surname;
	@Column(nullable=false)
	private String nationality;
	
	
	@OneToMany(mappedBy="chef")
	@LazyCollection( LazyCollectionOption.TRUE )
	private List<Buffet> buffet;
	
	 @Column(nullable = true, length = 64)
	    private String photos;
	 
	 @Transient
	    public String getPhotosImagePath() {
	        if (photos == null || id == null) return null;
	         
	        return "/chef-photos/" + id + "/" + photos;
	    }

	@Override
	public String toString() {
		return "Chef [id=" + id + ", name=" + name + ", surname=" + surname + ", nationality=" + nationality
				+ "]";
	}
	
	
	public String toStringPDF() {
		return "Chef:  " + name + "  " + surname + "       nationality:  " + nationality;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public void setPhotos(String fileName) {
		Random r  = new Random();
		int n = r.nextInt(3);
	}

	
	 
	 
}
