package edu.umhs.rfid.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RfidTagAssignment {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;	
    
    @ManyToOne
    @JoinColumn(name="tag_id")
    RfidTag tag;

    String status;
	Timestamp startDate;
	Timestamp endDate;
	
	protected RfidTagAssignment() {}

	public RfidTagAssignment(RfidTag tag, String status, Timestamp startDate, Timestamp endDate) {
		this.tag = tag;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public RfidTagAssignment(RfidTag tag, String status, Timestamp startDate) {
		this(tag,status,startDate,null);
	}

	public long getId() {
		return id;
	}

	public RfidTag getTag() {
		return tag;
	}

	public String getStatus() {
		return status;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}
	
	
}
