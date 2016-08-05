package edu.umhs.rfid.device;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class RfidDeviceController {
	private static final Logger log = LoggerFactory.getLogger(RfidDeviceController.class);
	RfidReader r;
	
//	@Async("threadPoolTaskExecutor")
	public void init() {
		try {
			System.out.println("----------->" + log.getClass().getName());
			r = new RfidReader();
			r.init();
		} catch (Exception ex) {
			log.error(ex.toString(), ex);
		}
	}
	
	@PreDestroy
	public void destroy() {
		try {
			r.destroy();
		} catch (Exception ex) {
			
		}		
	}

}
