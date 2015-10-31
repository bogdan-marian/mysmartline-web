package eu.mysmartline.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import eu.mysmartline.entities.ActivationItem;
import eu.mysmartline.entities.Line;
import eu.mysmartline.models.ActivateNextNumberModel;
import eu.mysmartline.models.ClientStatusModel;
import eu.mysmartline.models.ExtentionModel;
import eu.mysmartline.models.LineModel;
import eu.mysmartline.services.ActivateNextNumberService;
import eu.mysmartline.services.ActivationItemService;
import eu.mysmartline.services.EmfService;
import eu.mysmartline.services.LineService;
import eu.mysmartline.services.MySecurity;

@Controller
public class LineController {
	private static UserService us = UserServiceFactory.getUserService();

	@RequestMapping(value = "Line/index")
	public String index(ModelMap model) {
		String userId = us.getCurrentUser().getUserId();
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();

		TypedQuery<Line> query = em.createQuery(
				"select s from Line s where userId = :theUserId", Line.class);
		query.setParameter("theUserId", userId);
		List<Line> line = query.getResultList();
		em.getTransaction().rollback();
		model.addAttribute("line", line);
		System.out.println("End of controller. Everithing is good");
		return "Line/index";
	}

	@RequestMapping(value = "Line/details/{id}", method = RequestMethod.GET)
	public String details(@PathVariable String id, ModelMap model) {
		if (!MySecurity.canManageLine(id)) {
			return "forward:/Error/securityViolation";
		}
		/*Key key = KeyFactory.createKey(Line.class.getSimpleName(), lineId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		Line line = em.find(Line.class, key);*/
		Line line = LineService.getLine(id);
		//em.getTransaction().rollback();
		if (line != null) {
			model.addAttribute(line);
			ClientStatusModel currentClient = LineService
					.getStatusForCurrentClient(id);
			model.addAttribute("currentClient", currentClient);

			ActivateNextNumberModel activateNextNumberModel = new ActivateNextNumberModel();
			activateNextNumberModel.setLineId(id);
			
			Map<String, String> clients = ActivateNextNumberService
					.getNotificationSetByLineId(id);
			//set probable next client
			for(Entry<String, String> entry :clients.entrySet()){
				activateNextNumberModel.setNotificationId(entry.getKey());
				break;
			}

			// populate service points
			Map<String, String> newServicePoints = ActivateNextNumberService
					.getActiveServicePointsMap();
			// set first service point
			for (Entry<String, String> entry : newServicePoints.entrySet()) {
				activateNextNumberModel.setServicePointId(entry.getKey());
				break;
			}

			model.addAttribute("newServicePoints", newServicePoints);
			model.addAttribute(activateNextNumberModel);
			model.addAttribute("clients", clients);
			return "Line/details";
		} else {
			return "redirect:index";
		}
	}

	@RequestMapping(value = "Line/activateNextCustomer", method = RequestMethod.POST)
	public String activateNextCustomer(
			@Valid ActivateNextNumberModel activateNextNumberModel,
			BindingResult bindingResult, ModelMap model) {
		if (!MySecurity.canManageLine(activateNextNumberModel.getLineId())) {
			return "forward:/Error/securityViolation";
		}

		if (!bindingResult.hasErrors()) {
			// no errors so wee proceed
			ActivateNextNumberService
					.activateNextNumber(activateNextNumberModel);
		} else {
			System.out.println("debug: errors in form activateNextCustomer");
		}
		return "redirect:details/" + activateNextNumberModel.getLineId();
	}

	@RequestMapping(value = "Line/create")
	public String create(Model model) {
		String userId = MySecurity.getUserId();
		List<ActivationItem> items = ActivationItemService.getByUserId(userId);
		if (items.size() == 0) {
			/*return "redirect:/Pricing/index";*/
			ActivationItemService.createBetaItem();
			items = ActivationItemService.getByUserId(userId);
		}
		Line line = new Line();

		// use the lineModel();
		LineModel lineModel = new LineModel();
		lineModel.setLine(line);

		Map<String, String> activationItems = new LinkedHashMap<String, String>();
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		for (ActivationItem item : items) {
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, item.getNrOfMonts());
			activationItems.put(item.getId(), calendar.getTime()
					.toString());
		}
		ActivationItem firstItem = items.get(0);
		lineModel.setActivationItem(firstItem.getId());
		model.addAttribute(lineModel);
		model.addAttribute("activationItems", activationItems);
		return "Line/create";
	}

	@RequestMapping(value = "Line/createPost", method = RequestMethod.POST)
	public String createPost(@Valid LineModel lineModel,
			BindingResult bindingResult, ModelMap model) {
		User user = UserServiceFactory.getUserService().getCurrentUser();
		if (user == null) {
			return "forward:/Error/securityViolation";
		}
		if (!bindingResult.hasErrors()) {
			ActivationItem item = ActivationItemService.getById(lineModel
					.getActivationItem());
			if (item.isArchived()) {
				return "forward:/Error/securityViolation";
			}
			Line line = lineModel.getLine();
			line.setUserId(MySecurity.getUserId());
			LineService.createLine(line, item);
		}
		return "redirect:/Line/index";
	}

	@RequestMapping(value = "Line/archive/{lineId}", method = RequestMethod.GET)
	public String archive(@PathVariable String lineId, ModelMap model) {
		if (!MySecurity.canManageLine(lineId)) {
			return "forward:/Error/securityViolation";
		}
		LineService.archive(lineId);
		return "redirect:/Line/index";
	}

	@RequestMapping(value = "Line/managePanel/{lineId}")
	public String managePanel(@PathVariable String lineId, ModelMap model) {
		if (!MySecurity.canManageLine(lineId)) {
			return "forward:/Error/securityViolation";
		}
		Line line = LineService.getLine(lineId);
		model.addAttribute(line);
		return "Line/managePanel";
	}

	@RequestMapping(value = "Line/resetNumbers/{lineId}")
	public String resetNumbers(@PathVariable String lineId, ModelMap model) {
		LineService.resetNumbers(lineId);
		return "redirect:/Line/details/" + lineId;
	}

	@RequestMapping(value = "Line/edit/{lineId}")
	public String edit(@PathVariable String lineId, ModelMap model) {
		if (!MySecurity.canManageLine(lineId)) {
			return "forward:/Error/securityViolation";
		}
		Line line = LineService.getLine(lineId);
		model.addAttribute(line);
		return "Line/edit";
	}

	@RequestMapping(value = "Line/editPost", method = RequestMethod.POST)
	public String editPost(@Valid Line line, BindingResult bindingResult,
			ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "forward:/Error/defaultError";
		}
		if (!MySecurity.canManageLine(line.getId())) {
			return "forward:/Error/securityViolation";
		}
		LineService.saveEdit(line);
		return "forward:/Line/index";
	}
	
	@RequestMapping(value = "Line/webRegistrationPage/{lineId}")
	public String webRegistrationPage(@PathVariable String lineId, ModelMap model){
		if (!MySecurity.canManageLine(lineId)) {
			return "forward:/Error/securityViolation";
		}
		String qrText = "http://mysmartline.eu/LineRegistration/readClientDetails/"+lineId;
		model.addAttribute("qrText",  qrText);
		model.addAttribute("lineId", lineId);
		return "Line/webRegistrationPage";
	}

	@RequestMapping(value = "Line/extendLine/{lineId}")
	public String extendLine(@PathVariable String lineId, ModelMap model) {
		if (!MySecurity.canManageLine(lineId)) {
			return "forward:/Error/securityViolation";
		}

		List<ActivationItem> items = ActivationItemService
				.getByUserId(MySecurity.getUserId());
		if (items.size() == 0) {
			return "redirect:/Pricing/index";
		}
		Line line = LineService.getLine(lineId);
		Map<String, String> activationItems = new LinkedHashMap<String, String>();
		Date date = line.getValidUntil();
		Calendar calendar = Calendar.getInstance();
		for (ActivationItem item : items) {
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, item.getNrOfMonts());
			activationItems.put(item.getId(), calendar.getTime()
					.toString());
		}
		ActivationItem firstItem = items.get(0);
		String lineName = line.getLineName();

		ExtentionModel extentionModel = new ExtentionModel();
		extentionModel.setLineId(lineId);
		extentionModel.setActivationItemId(firstItem.getId());

		model.addAttribute(extentionModel);
		model.addAttribute("activationItems", activationItems);
		model.addAttribute("lineName", lineName);

		return "Line/extendLine";
	}

	@RequestMapping(value = "Line/extendLinePost", method = RequestMethod.POST)
	public String extendLinePost(@Valid ExtentionModel extentionModel,
			BindingResult bindingResult, ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "forward:/Error/defaultError";
		}
		if (!MySecurity.canManageLine(extentionModel.getLineId())) {
			return "forward:/Error/securityViolation";
		}
		Line line = LineService.getLine(extentionModel.getLineId());
		ActivationItem item = ActivationItemService.getById(extentionModel
				.getActivationItemId());
		if (item.isArchived()) {
			return "forward:/Error/defaultError";
		}
		ActivationItemService.proces(item, line);
		return "redirect:/Line/index";
	}
}
