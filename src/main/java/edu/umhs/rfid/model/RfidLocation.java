package edu.umhs.rfid.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RfidLocation {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;	
    
    private String locationType;
    private String name;
    private String description;
    
	protected RfidLocation() {
		// TODO Auto-generated constructor stub
	}

	public RfidLocation(String locationType, String name, String description) {
		this.locationType = locationType;
		this.name = name;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public String getLocationType() {
		return locationType;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
