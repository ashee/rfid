package edu.umhs.rfid.persistence;

import org.springframework.data.repository.CrudRepository;

import edu.umhs.rfid.model.RfidPresence;

public interface RfidPresenceRepository extends CrudRepository<RfidPresence, Long>{
}
