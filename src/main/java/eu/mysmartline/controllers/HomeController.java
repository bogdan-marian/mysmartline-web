package eu.mysmartline.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import eu.mysmartline.services.SettingsUserService;

@Controller
public class HomeController {

	
	@RequestMapping("/")
	public String home2(){
		SettingsUserService.createUser();
		return "Home/home1";
	}
	
	
	@RequestMapping("Home/help1/{idYoutube}")
	public String help1(@PathVariable int idYoutube, ModelMap model){
		UserService userService = UserServiceFactory.getUserService();
		String logInUrl = userService.createLoginURL("/Home/help1/1");
		
		String utubeUrl ;
		switch (idYoutube) {
		case 2:
			utubeUrl = "<iframe width=\"640\" height=\"360\" src=\"//www.youtube.com/embed/zfgDoHqrxFo?rel=0&autoplay=1\" frameborder=\"0\" allowfullscreen></iframe>";
			break;

		default:
			utubeUrl = "<iframe width=\"640\" height=\"360\" src=\"//www.youtube.com/embed/jXWtcdr_Ndo?rel=0&autoplay=1\" frameborder=\"0\" allowfullscreen></iframe>";
			break;
		}
		model.addAttribute("utubeUrl", utubeUrl);
		model.addAttribute("logInUrl", logInUrl);
		return "Home/help1";
	}
}