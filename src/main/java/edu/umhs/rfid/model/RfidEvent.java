package edu.umhs.rfid.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RfidEvent {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;	
    
    public String readerName;
	public String EPC;
	public Timestamp timeStamp;
	public String peakRSSI;
	public Long readCount;
	
    protected RfidEvent() {}
	
	public RfidEvent(String readerName, String ePC, Timestamp timeStamp, String peakRSSI, Long readCount) {
		this.readerName = readerName;
		this.EPC = ePC;
		this.timeStamp = timeStamp;
		this.peakRSSI = peakRSSI;
		this.readCount = readCount;
	}
	
	@Override
	public String toString() {
		return "RfidEvent [readerName=" + readerName + ", EPC=" + EPC + ", timeStamp=" + timeStamp + ", peakRSSI="
				+ peakRSSI + ", readCount=" + readCount + "]";
	}
		
}
