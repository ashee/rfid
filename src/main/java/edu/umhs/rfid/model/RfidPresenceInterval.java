package edu.umhs.rfid.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RfidPresenceInterval {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String presenceType;
    private String startTime;
    private String endTime;
    
    protected RfidPresenceInterval() {}
    
	public RfidPresenceInterval(String presenceType, String startTime, String endTime) {
		this.presenceType = presenceType;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public long getId() {
		return id;
	}

	public String getPresenceType() {
		return presenceType;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}
    
    
}
