package edu.umhs.rfid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value ="/")
class RfidHomeController {
	private static final Logger log = LoggerFactory.getLogger(RfidHomeController.class);
	
	@RequestMapping
	String index(Model model) {
		model.addAttribute("now", new java.util.Date());
		return "index";
	}

	@RequestMapping("properties")
	@ResponseBody
	java.util.Properties properties() {
		return System.getProperties();
	}
}
