package edu.umhs.rfid;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.umhs.rfid.model.RfidSubject;
import edu.umhs.rfid.persistence.RfidSubjectRepository;

@Controller
@RequestMapping(value ="/setup")
class RfidSetupController {
	
	@Autowired
	RfidSubjectRepository subjectRepository;
	
	@RequestMapping
	String index(Model model) {
		model.addAttribute("now", LocalDateTime.now());
		return "index";
	}

	@RequestMapping( value = "subjects")
	String subjects(Model model) {
		Iterable<RfidSubject> subjects = subjectRepository.findAll();
		model.addAttribute("subjects", subjects);
		return "subjects";
	}

	@RequestMapping("properties")
	@ResponseBody
	java.util.Properties properties() {
		return System.getProperties();
	}
}
