package edu.umhs.rfid;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.umhs.rfid.model.RfidProvider;
import edu.umhs.rfid.model.RfidSubject;
import edu.umhs.rfid.model.RfidTag;
import edu.umhs.rfid.model.RfidTagAssignment;
import edu.umhs.rfid.persistence.RfidProviderRepository;
import edu.umhs.rfid.persistence.RfidSubjectRepository;
import edu.umhs.rfid.persistence.RfidTagAssignmentRepository;
import edu.umhs.rfid.persistence.RfidTagRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RfidApplication.class)
public class RfidApplicationTests {
	
	@Autowired
	RfidProviderRepository pr;
	
	@Autowired
	RfidSubjectRepository sr;

	@Autowired
	RfidTagRepository tr;
	
	@Autowired
	RfidTagAssignmentRepository tar;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void addProvider() {
		RfidProvider p = new RfidProvider("Tech", String.format("umid#%s", UUID.randomUUID()));
		pr.save(p);
		
		RfidProvider p2 = pr.findOne(p.getId());
		assertEquals(p.getId(), p2.getId());
	}

	@Test
	public void addSubject() {
		RfidSubject s = new RfidSubject(String.format("mrn#%s", UUID.randomUUID()));
		sr.save(s);
		
		RfidSubject s2 = sr.findOne(s.getId());
		assertEquals(s.getId(), s2.getId());
	}

	@Test
	public void addTag() throws Exception {
		RfidProvider p = new RfidProvider("Doc", String.format("umid#%s", UUID.randomUUID()));
		pr.save(p);
		
		RfidTag tag = RfidTag.createProviderTag("epc1", p.getId());
		tr.save(tag);
		
		RfidTag tag2 = tr.findOne(tag.getId());
		assertEquals(tag.getId(), tag2.getId());
	}
	
	@Test
	public void addTagAssignment() throws Exception {
		RfidSubject s = new RfidSubject(String.format("mrn#%s", UUID.randomUUID()));
		sr.save(s);
		
		RfidTag tag = RfidTag.createSubjectTag("epc1", s.getId());
		tr.save(tag);
		
		Timestamp now = new Timestamp(new Date().getTime());
		RfidTagAssignment ta = new RfidTagAssignment(tag, "A", now);
		tar.save(ta);
		
		RfidTagAssignment ta2 = tar.findOne(ta.getId());
		assertEquals(ta.getId(), ta2.getId());
	}

}
