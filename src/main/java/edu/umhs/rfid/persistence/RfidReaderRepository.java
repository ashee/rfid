package edu.umhs.rfid.persistence;

import org.springframework.data.repository.CrudRepository;

import edu.umhs.rfid.model.RfidReader;

public interface RfidReaderRepository extends CrudRepository<RfidReader, Long>{
}
