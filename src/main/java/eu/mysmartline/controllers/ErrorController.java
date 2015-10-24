package eu.mysmartline.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
	
	@RequestMapping(value = "Error/defaultError")
	public String defaultError(){
		return "Error/defaultError";
	}
	
	@RequestMapping(value = "Error/securityViolation")
	public String securityViolation(){
		return "Error/securityViolation";
	}
	@RequestMapping(value = "Error/numberAllreadyConverted")
	public String numberAllreadyConverted(){
		return "Error/numberAllreadyConverted";
	}
}
