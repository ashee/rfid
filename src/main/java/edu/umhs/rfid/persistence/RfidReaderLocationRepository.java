package edu.umhs.rfid.persistence;

import org.springframework.data.repository.CrudRepository;

import edu.umhs.rfid.model.RfidReaderLocation;

public interface RfidReaderLocationRepository extends CrudRepository<RfidReaderLocation, Long>{
}
