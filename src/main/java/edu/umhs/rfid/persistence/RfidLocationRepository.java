package edu.umhs.rfid.persistence;

import org.springframework.data.repository.CrudRepository;

import edu.umhs.rfid.model.RfidLocation;

public interface RfidLocationRepository extends CrudRepository<RfidLocation, Long>{
}
