package eu.mysmartline.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import eu.mysmartline.entities.ActivationItem;
import eu.mysmartline.services.ActivationItemService;
import eu.mysmartline.services.CounterServiceB;
import eu.mysmartline.services.MySecurity;

@Controller
public class DashboardController {
	private static UserService us = UserServiceFactory.getUserService();
	@RequestMapping(value = "Dashboard/overview")
	public String overview (ModelMap model){
		User user = us.getCurrentUser();
		String nickName = user.getNickname();
		String email = user.getEmail();
		model.addAttribute("nickName", nickName);
		model.addAttribute("email", email);
		Long totalEmails = CounterServiceB.getTotalEmails();
		model.addAttribute("totalEmails", totalEmails);
		
		String userId = MySecurity.getUserId();
		List <ActivationItem> items = ActivationItemService.getByUserId(userId); 
		Integer nrOfItems = items.size();
		model.addAttribute("nrOfItems", nrOfItems);
		
		return "Dashboard/overview";
	}
}
