package edu.umhs.rfid.persistence;

import org.springframework.data.repository.CrudRepository;

import edu.umhs.rfid.model.RfidEvent;

public interface RfidEventRepository extends CrudRepository<RfidEvent, Long>{
}
