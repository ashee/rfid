package edu.umhs.rfid.persistence;

import org.springframework.data.repository.CrudRepository;

import edu.umhs.rfid.model.RfidTagAssignment;

public interface RfidTagAssignmentRepository extends CrudRepository<RfidTagAssignment, Long>{
}
