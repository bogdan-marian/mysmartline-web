package eu.mysmartline.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GuidedTourController {

	@RequestMapping(value = "GuidedTour/tourHome")
	public String tourHome() {
		return "GuidedTour/tourHome";
	}
	
	@RequestMapping(value = "GuidedTour/notLogedIn")
	public String notLogedIn() {
		return "GuidedTour/notLogedIn";
	}
	
	@RequestMapping(value = "GuidedTour/noLine")
	public String noLine(){
		return "GuidedTour/noLine";
	}
}
