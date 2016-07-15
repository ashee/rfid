package edu.umhs.rfid.persistence;

import org.springframework.data.repository.CrudRepository;

import edu.umhs.rfid.model.RfidSubject;

public interface RfidSubjectRepository extends CrudRepository<RfidSubject, Long>{
}
