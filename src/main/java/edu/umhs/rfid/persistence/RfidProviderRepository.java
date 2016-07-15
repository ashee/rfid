package edu.umhs.rfid.persistence;


import org.springframework.data.repository.CrudRepository;

import edu.umhs.rfid.model.RfidProvider;

public interface RfidProviderRepository extends CrudRepository<RfidProvider, Long>{
}
