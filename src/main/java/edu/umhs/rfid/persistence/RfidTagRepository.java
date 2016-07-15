package edu.umhs.rfid.persistence;

import org.springframework.data.repository.CrudRepository;

import edu.umhs.rfid.model.RfidTag;

public interface RfidTagRepository extends CrudRepository<RfidTag, Long>{
}
