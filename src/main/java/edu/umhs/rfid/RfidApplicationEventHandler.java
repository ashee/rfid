package edu.umhs.rfid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import edu.umhs.rfid.device.RfidReader;

@Component
public class RfidApplicationEventHandler {
	@Autowired
	RfidReader r;
	
    @EventListener({ContextRefreshedEvent.class})
    void contextRefreshedEvent() throws Exception {
       r.init();
    }
}
