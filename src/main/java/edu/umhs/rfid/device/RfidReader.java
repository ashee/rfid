package edu.umhs.rfid.device;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.thingmagic.ReadExceptionListener;
import com.thingmagic.ReadListener;
import com.thingmagic.Reader;
import com.thingmagic.ReaderException;
import com.thingmagic.SimpleReadPlan;
import com.thingmagic.TMConstants;
import com.thingmagic.TagProtocol;
import com.thingmagic.TagReadData;


public class RfidReader
{
	private static final Logger log = LoggerFactory.getLogger(RfidReader.class);
	
	String urls[] = {"llrp://10.21.237.144:5084/"};
    Reader[] readers = new Reader[urls.length];
    
    public void init() throws Exception {
    	System.out.println("================== yes ===========!!");
        int i =0;
        for (String url: urls)
        {
            readers[i] = Reader.create(url);
            try {
				readers[i].connect();
			} catch (Exception e) {
				log.error(e.toString(),e);
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
            ReadListener rl = new PrintListener();
            readers[i].addReadListener(rl);

            ReadExceptionListener exceptionListener = new TagReadExceptionReceiver();
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
            // Shut down reader
            r.destroy();
        }
    }

    static class PrintListener implements ReadListener
    {

        public void tagRead(Reader r, TagReadData tr)
        {
            try
            {
                log.info(r.paramGet("/reader/uri").toString()+" Background read: " + tr.toString());
            }
            catch (ReaderException ex)
            {
            	System.out.println("+++++++++++++++++ here ");
            	log.error(ex.toString());
            }
        }

    }

    static class TagReadExceptionReceiver implements ReadExceptionListener
    {

        public void tagReadException(com.thingmagic.Reader r, ReaderException re)
        {
            System.out.println("Reader Exception: " + re.getMessage());
            if (re.getMessage().equals("Connection Lost"))
            {
//                System.exit(1);
            }
        }
    }
}