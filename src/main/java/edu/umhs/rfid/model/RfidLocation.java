package edu.umhs.rfid.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RfidLocation {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;	
    

}
