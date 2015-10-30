package eu.mysmartline.controllers;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import eu.mysmartline.entities.Line;
import eu.mysmartline.entities.LineNumber;
import eu.mysmartline.entities.NotificationItem;
import eu.mysmartline.entities.ServicePoint;
import eu.mysmartline.models.ActivateNextNumberModel;
import eu.mysmartline.services.ActivateNextNumberService;
import eu.mysmartline.services.EmfService;
import eu.mysmartline.services.LineNumberService;
import eu.mysmartline.services.LineService;
import eu.mysmartline.services.MySecurity;
import eu.mysmartline.services.ServicePointService;

@Controller
public class ServicePointController {
	private static UserService us = UserServiceFactory.getUserService();

	@RequestMapping(value = "ServicePoint/index")
	public String index(ModelMap model) {
		/*
		 * String userId = us.getCurrentUser().getUserId(); EntityManager em =
		 * EmfService.getEntityManager(); em.getTransaction().begin();
		 * TypedQuery<ServicePoint> query = em.createQuery(
		 * "select s from ServicePoint s where s.userId = :theUserId",
		 * ServicePoint.class); query.setParameter("theUserId", userId);
		 * List<ServicePoint> servicePoint = query.getResultList();
		 * em.getTransaction().rollback();
		 */
		List<ServicePoint> servicePoint = ServicePointService.getActivePoints();
		model.addAttribute("servicePoint", servicePoint);
		return "ServicePoint/index";
	}

	@RequestMapping(value = "ServicePoint/create")
	public String create(ModelMap model) {
		ServicePoint servicePoint = new ServicePoint();
		model.addAttribute(servicePoint);
		return "ServicePoint/create";
	}

	/**
	 * TODO populate long id
	 * 
	 * @param servicePoint
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "ServicePoint/createPost", method = RequestMethod.POST)
	public String createPost(@Valid ServicePoint servicePoint,
			BindingResult bindingResult, ModelMap model) {
		if (!bindingResult.hasErrors()) {
			// set user id
			User user = UserServiceFactory.getUserService().getCurrentUser();
			servicePoint.setUserId(user.getUserId());
			EntityManager em = EmfService.getEntityManager();
			em.getTransaction().begin();
			em.persist(servicePoint);
			em.getTransaction().commit();
			// populate the long id
			em.getTransaction().begin();
			servicePoint = em.find(ServicePoint.class, servicePoint.getId());
			servicePoint.setLongPartId(servicePoint.getId().getId());
			em.getTransaction().commit();

		}
		return "redirect:index";
	}

	@RequestMapping(value = "ServicePoint/managePanel/{servicePointId}")
	public String managePanel(@PathVariable Long servicePointId, ModelMap model) {
		if (!MySecurity.canManageServicePoint(servicePointId)) {
			return "forward:/Error/securityViolation";
		}
		ServicePoint servicePoint = ServicePointService
				.getServicePoint(servicePointId);
		model.addAttribute(servicePoint);
		return "ServicePoint/managePanel";
	}

	@RequestMapping(value = "ServicePoint/archive/{servicePointId}")
	public String archive(@PathVariable Long servicePointId) {
		if (!MySecurity.canManageServicePoint(servicePointId)) {
			return "forward:/Error/securityViolation";
		}
		ServicePointService.archive(servicePointId);
		return "redirect:/ServicePoint/index";
	}

	@RequestMapping(value = "ServicePoint/notificationPanel/{servicePointId}")
	public String notificationPanel(@PathVariable String servicePointId,
			ModelMap model) {
		if (!MySecurity.canManageServicePoint(servicePointId)) {
			return "forward:/Error/securityViolation";
		}
		ServicePoint servicePoint = ServicePointService
				.getServicePoint(servicePointId);
		if (servicePoint == null) {
			return "redirect:index";
		}
		ActivateNextNumberModel activateNextNumberModel = new ActivateNextNumberModel();
		activateNextNumberModel.setServicePointId(servicePointId);

		Map<Long, String> clients = ActivateNextNumberService
				.getNotificationsetByServicePointId(servicePointId);
		// set probable next client
		for (Entry<Long, String> entry : clients.entrySet()) {
			activateNextNumberModel.setNotificationId(entry.getKey());
			break;
		}
		model.addAttribute(activateNextNumberModel);
		model.addAttribute("clients", clients);
		return "ServicePoint/notificationPanel";
	}

	@RequestMapping(value = "ServicePoint/activateNextCustomer", method = RequestMethod.POST)
	public String activateNextCustomer(
			@Valid ActivateNextNumberModel activateNextNumberModel,
			BindingResult bindingResult, ModelMap model) {
		if (bindingResult.hasErrors()) {
			System.out
					.println("debug: errors in form ServicePoint/activateNextCustomer");
			return "redirect:details/" + activateNextNumberModel.getLineId();
		}
		if (!MySecurity.canManageServicePoint(activateNextNumberModel
				.getServicePointId())) {

			return "forward:/Error/securityViolation";
		}

		Line line = LineService.getLineByNotifId(activateNextNumberModel
				.getNotificationId());
		/*activateNextNumberModel.setLineId(line.getLongPartId());
		ActivateNextNumberService.activateNextNumber(activateNextNumberModel);
		return "redirect:/ServicePoint/notificationPanel/"
				+ activateNextNumberModel.getServicePointId();*/
		throw new IllegalStateException("Please finish this");
	}
}
