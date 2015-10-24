package eu.mysmartline.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PricingController {
	
	@RequestMapping(value = "Pricing/index")
	public String index(ModelMap model){
		return "Pricing/index";
	}
	
	@RequestMapping(value = "Pricing/oneLine")
	public String oneLine(ModelMap model){
		return "Pricing/oneLine";
	}
	@RequestMapping(value = "Pricing/fiveLines")
	public String fiveLines(ModelMap model){
		return "Pricing/fiveLines";
	}
	
}
