package eu.mysmartline.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import eu.mysmartline.services.CounterServiceB;

@Controller
public class AdminController {
	@RequestMapping("Admin/index")
	public String index(){
		return "Admin/index";
	}
	
	@RequestMapping("Admin/usersPanel")
	public String usersPanel(ModelMap model){
		long allUsers = CounterServiceB.getCountAllUsers();
		model.addAttribute("allUsers", allUsers);
		return "Admin/usersPanel";
	}
}
