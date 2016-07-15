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
	
	public static RfidTagAssignment createProviderAssignment(String epc, Long providerId, String status, Timestamp startDate) 
		throws Exception {
		return RfidTagAssignment.createProviderAssignment(epc, providerId, status, startDate, null);
	}
	
	public static RfidTagAssignment createProviderAssignment(
			String epc, Long providerId, String status, Timestamp startDate, Timestamp endDate) 
			throws Exception {
		RfidTagAssignment a = new RfidTagAssignment();
		a.tag = RfidTag.createTag(epc, "P", providerId);
		a.status = status;
		a.startDate = startDate;
		a.endDate = endDate;
		return a;
	}

	public static RfidTagAssignment createSubjectAssignment(
			String epc, Long subjectId, String status, Timestamp startDate, Timestamp endDate) 
			throws Exception {
		RfidTagAssignment a = new RfidTagAssignment();
		a.tag = RfidTag.createTag(epc, "S", subjectId);
		a.status = status;
		a.startDate = startDate;
		a.endDate = endDate;
		return a;	
	}
}
