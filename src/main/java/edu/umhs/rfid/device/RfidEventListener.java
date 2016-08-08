package edu.umhs.rfid.device;

import java.net.URI;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thingmagic.ReadListener;
import com.thingmagic.Reader;
import com.thingmagic.ReaderException;
import com.thingmagic.TagReadData;

import edu.umhs.rfid.model.RfidEvent;
import edu.umhs.rfid.persistence.RfidEventRepository;

@Component
class RfidEventListener implements ReadListener
{
	private static final Logger log = LoggerFactory.getLogger(RfidEventListener.class);

	private RfidEventRepository rfidRepository;
	
	@Autowired
	public void setRfidRepository(RfidEventRepository rfidRepository) {
		this.rfidRepository = rfidRepository;
	}
	
    public void tagRead(Reader r, TagReadData tr)
    {
        try
        {
            log.info(r.paramGet("/reader/uri").toString()+" Background read: " + tr.toString());
			URI readerUrl = URI.create(r.paramGet("/reader/uri").toString());
			String readerName = readerUrl.getHost();
			String ePC = tr.epcString();
			Timestamp timeStamp = new Timestamp(tr.getTime());
			String peakRSSI = String.format("%d", tr.getRssi());
			Long readCount = new Long(tr.getReadCount());
			RfidEvent e = new RfidEvent(readerName, ePC, timeStamp, peakRSSI, readCount);
			rfidRepository.save(e);            
        }
        catch (ReaderException ex)
        {
        	log.error(ex.toString());
        }
    }

}