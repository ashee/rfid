package edu.umhs.rfid.persistence;

import org.springframework.data.repository.CrudRepository;

import edu.umhs.rfid.model.RfidPresenceInterval;

public interface RfidPresenceIntervalRepository extends CrudRepository<RfidPresenceInterval, Long>{
}
