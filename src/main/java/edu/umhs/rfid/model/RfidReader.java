package edu.umhs.rfid.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RfidReader {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;	
    private String readerType;
    private String name;
    private String description;
    
	protected RfidReader() {
		// TODO Auto-generated constructor stub
	}

	public RfidReader(String readerType, String readerName, String description) {
		this.readerType = readerType;
		this.name = readerName;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public String getReaderType() {
		return readerType;
	}

	public String getReaderName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
