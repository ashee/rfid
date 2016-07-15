package edu.umhs.rfid.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RfidTag {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	protected RfidTag() {
	}

	private static RfidTag createTag(String epc, String tagType, Long taggedId) throws Exception {
		RfidTag tag = new RfidTag();
		tag.epc = epc;
		tag.tagType = tagType;
		switch (tagType) {
		case "P":
			tag.providerId = taggedId;
			break;
		case "S":
			tag.subjectId = taggedId;
			break;
		default:
			throw new Exception(String.format("Invalid tagType: '%s'", tagType));
		}
		return tag;
	}

	public static RfidTag createProviderTag(String epc, Long providerId) throws Exception {
		return RfidTag.createTag(epc, "P", providerId);
	}

	public static RfidTag createSubjectTag(String epc, Long subjectId) throws Exception {
		return RfidTag.createTag(epc, "S", subjectId);
	}
}
