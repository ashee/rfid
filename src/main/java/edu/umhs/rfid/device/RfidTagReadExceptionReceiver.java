package edu.umhs.rfid.device;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.thingmagic.ReadExceptionListener;
import com.thingmagic.ReaderException;

@Component
class RfidTagReadExceptionReceiver implements ReadExceptionListener
{
	private static final Logger log = LoggerFactory.getLogger(RfidTagReadExceptionReceiver.class);

    public void tagReadException(com.thingmagic.Reader r, ReaderException re)
    {
    	log.info(re.getMessage(), re);
    }
}