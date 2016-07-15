package edu.umhs.rfid.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RfidTag {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    private String epc;
	private String taggableType;
    private Long subjectId;
    private Long providerId;
    
    public long getId() {
		return id;
	}

	public String getEpc() {
		return epc;
	}

	public String getTaggableType() {
		return taggableType;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public Long getProviderId() {
		return providerId;
	}

	protected RfidTag() {}
	
	public static RfidTag createTaggable(String epc, String taggableType, Long taggedObject) throws Exception {
		RfidTag taggable = new RfidTag();
		taggable.epc = epc;
		taggable.taggableType = taggableType;
		switch (taggableType) {
		case "S":
			taggable.subjectId = taggedObject;
			break;
		case "P":
			taggable.providerId = taggedObject;
			break;
		default:
			throw new Exception(String.format("Unexcepted taggableType '%s'", taggableType));

		}
		return taggable;
	}
}
