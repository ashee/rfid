package edu.umhs.rfid.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RfidReaderLocation {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    private String locationType;
    private String description;
    
	protected RfidReaderLocation() {
	}

	public RfidReaderLocation(String locationType, String description) {
		this.locationType = locationType;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public String getLocationType() {
		return locationType;
	}

	public String getDescription() {
		return description;
	}
    
    
}
