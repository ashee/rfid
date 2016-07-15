package edu.umhs.rfid.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RfidSubject {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;	
    private String mrn;
	
    protected RfidSubject() {}

	public RfidSubject(String mrn) {
		super();
		this.mrn = mrn;
	}

	public long getId() {
		return id;
	}

	public String getMrn() {
		return mrn;
	}
			
}
