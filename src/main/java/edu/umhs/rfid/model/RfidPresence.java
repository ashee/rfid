package edu.umhs.rfid.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RfidPresence {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String readerName;
    private String epc;
    private long intervalId;
    
	protected RfidPresence() {
		// TODO Auto-generated constructor stub
	}

	protected RfidPresence(String readerName, String epc, long intervalId) {
		this.readerName = readerName;
		this.epc = epc;
		this.intervalId = intervalId;
	}

	public long getId() {
		return id;
	}

	public String getReaderName() {
		return readerName;
	}

	public String getEpc() {
		return epc;
	}

	public long getIntervalId() {
		return intervalId;
	}
  
}
