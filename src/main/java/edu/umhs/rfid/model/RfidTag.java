package edu.umhs.rfid.model;

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
	private String tagType;
    private Long subjectId;
    private Long providerId;
    
    public long getId() {
		return id;
	}

	public String getEpc() {
		return epc;
	}

	public String getTagType() {
		return tagType;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public Long getProviderId() {
		return providerId;
	}

	protected RfidTag() {}
	
	public static RfidTag createTag(String epc, String tagType, Long taggedObject) throws Exception {
		RfidTag taggable = new RfidTag();
		taggable.epc = epc;
		taggable.tagType = tagType;
		switch (tagType) {
		case "S":
			taggable.subjectId = taggedObject;
			break;
		case "P":
			taggable.providerId = taggedObject;
			break;
		default:
			throw new Exception(String.format("Unexcepted tagType '%s'", tagType));

		}
		return taggable;
	}
}
