package edu.umhs.rfid;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.umhs.rfid.model.RfidEvent;
import edu.umhs.rfid.persistence.RfidEventRepository;

@RestController
public class RfidController {

	private RfidEventRepository rfidRepository;
	
	@Autowired
	public void setRfidRepository(RfidEventRepository rfidRepository) {
		this.rfidRepository = rfidRepository;
	}


	/**
	 * @param request
	 * @param readerName
	 * @param fieldNames
	 * @param fieldValues
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void take(HttpServletRequest request,
			@RequestParam(value = "reader_Name", required = true) String readerName,
			@RequestParam(value = "field_names", required = true) String fieldNames,
			@RequestParam(value = "field_values", required = true) String fieldValues) {

		// a set of EPC tags are coming in with a newline as record separator
		// within each record, the fields are comma separated
		// see an example below (including the trailing newlines)
		// 300833B2DDD9014000000000,1/1/1970 4:37:25 AM,-75,5407
		// E104,1/1/1970 4:46:19 AM,-79,17306
		//
		//
		String records[] = fieldValues.split("\n");
		for (String record : records) {
			if (record == "") continue;
			String fields[] = record.split(",");
			RfidEvent e = new RfidEvent(readerName, 
					fields[0].trim(), 
					parseTimestamp(fields[1].trim()), 
					fields[2].trim(), 
					Long.parseLong(fields[3].trim()));
			rfidRepository.save(e);
		}				
	}

	private Timestamp parseTimestamp(String str) {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a"); 
		Timestamp ts;
		try {
			Date dt = fmt.parse(str);
			ts = new Timestamp(dt.getTime());
		} catch (ParseException e1) {
			ts = null;
		}
		return ts;
	}
}