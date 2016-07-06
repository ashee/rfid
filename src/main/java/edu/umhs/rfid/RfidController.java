package edu.umhs.rfid;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RfidController {

	private RfidEventRepository rfidRepository;
	
	@Autowired
	public void setRfidRepository(RfidEventRepository rfidRepository) {
		this.rfidRepository = rfidRepository;
	}


@RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody String take(HttpServletRequest request, 
    		@RequestParam(value="reader_Name", required=true) String readerName,
    		@RequestParam(value="field_names", required=true) String fieldNames,
    		@RequestParam(value="field_values", required=true) String fieldValues
    		) {

	  String fields[] = fieldValues.split(",");
	  RfidEvent e = new RfidEvent(readerName,fields[0], fields[1], fields[2], fields[3]);
	  System.out.println(e.toString());
	  rfidRepository.save(e);
	  return "ok";
    }
}