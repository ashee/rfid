package edu.umhs.rfid.device;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.thingmagic.Reader;
import com.thingmagic.SimpleReadPlan;
import com.thingmagic.TMConstants;
import com.thingmagic.TagProtocol;

@Component
public class RfidReader
{
	private static final Logger log = LoggerFactory.getLogger(RfidReader.class);
	
	@Autowired
	RfidTagReadExceptionReceiver exceptionListener;
	
	@Autowired
	RfidEventListener rl;
	
	String urls[] = { 
//			"llrp://10.21.237.144:5084/",
//			"llrp://10.21.236.192:5084/"
			"llrp://10.21.236.41:5084/"
	};
    
	Reader[] readers = new Reader[urls.length];
    
	@Async
    public void init() throws Exception {
        int i =0;
        for (String url: urls)
        {
            readers[i] = Reader.create(url);
            try {
				readers[i].connect();
			} catch (Exception e) {
				log.error(e.toString(),e);
				continue;
			}
            if (Reader.Region.UNSPEC == (Reader.Region) readers[i].paramGet("/reader/region/id"))
            {
                Reader.Region[] supportedRegions = (Reader.Region[]) readers[i].paramGet(TMConstants.TMR_PARAM_REGION_SUPPORTEDREGIONS);
                if (supportedRegions.length < 1)
                {
                    throw new Exception("Reader doesn't support any regions");
                }
                else
                {
                    readers[i].paramSet("/reader/region/id", supportedRegions[0]);
                }
            }

            SimpleReadPlan plan = new SimpleReadPlan(null /* int[] antenna list */, TagProtocol.GEN2, null, null, 1000);
            readers[i].paramSet(TMConstants.TMR_PARAM_READ_PLAN, plan);
           
            // Create and add tag listener
            readers[i].addReadListener(rl);

            readers[i].addReadExceptionListener(exceptionListener);

            // search for tags in the background
            readers[i].startReading();
            i++;
        }
    }
    
    public void destroy() {
        for (Reader r : readers)
        {
            r.stopReading();
            r.destroy();
        }
    }

}