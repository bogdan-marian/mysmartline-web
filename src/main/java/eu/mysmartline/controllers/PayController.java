package eu.mysmartline.controllers;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.datastore.Key;

import eu.mysmartline.entities.ActivationItem;
import eu.mysmartline.entities.Line;
import eu.mysmartline.entities.Order;
import eu.mysmartline.entities.PricingDefinition;
import eu.mysmartline.models.OrderModel;
import eu.mysmartline.services.ActivationItemService;
import eu.mysmartline.services.EmfService;
import eu.mysmartline.services.LineService;
import eu.mysmartline.services.MySecurity;
import eu.mysmartline.services.OrderService;
import eu.mysmartline.services.PricingDefinitionService;

@Controller
public class PayController {
	@RequestMapping(value = "Pay/oneLineOneMonth")
	public String oneLineOneMonth(ModelMap model) {
		PricingDefinition price = PricingDefinitionService
				.getByName("oneLineOneMonthV1");
		OrderModel orderModel = buildOrderModel(price);
		

		System.out.println("Price = " + orderModel.getPricingValue());

		model.addAttribute(orderModel);
		return "Pay/oneLineOneMonth";
	}
	
	@RequestMapping(value = "Pay/oneLineOneYear")
	public String oneLineOneYear(ModelMap model) {
		PricingDefinition price = PricingDefinitionService
				.getByName("oneLineOneYearV1");
		OrderModel orderModel = buildOrderModel(price);
		
		System.out.println("Price = " + orderModel.getPricingValue());

		model.addAttribute(orderModel);
		return "Pay/oneLineOneYear";
	}
	@RequestMapping(value = "Pay/fiveLinesOneMonth")
	public String fiveLinesOneMonth(ModelMap model) {
		PricingDefinition price = PricingDefinitionService
				.getByName("fiveLinesOneMonthV1");
		OrderModel orderModel = buildOrderModel(price);
		

		System.out.println("Price = " + orderModel.getPricingValue());

		model.addAttribute(orderModel);
		return "Pay/fiveLinesOneMonth";
	}
	@RequestMapping(value = "Pay/fiveLinesOneYear")
	public String fiveLinesOneYear(ModelMap model) {
		PricingDefinition price = PricingDefinitionService
				.getByName("fiveLinesOneYearV1");
		OrderModel orderModel = buildOrderModel(price);
		

		System.out.println("Price = " + orderModel.getPricingValue());

		model.addAttribute(orderModel);
		return "Pay/fiveLinesOneYear";
	}
	
	private OrderModel buildOrderModel(PricingDefinition price){
		OrderModel orderModel = new OrderModel();
		orderModel.setPriceDefinitionId(price.getId());
		orderModel.setPricingValue(price.getPriceInEuro());
		orderModel.setNrOfPricingUnits(1);
		return orderModel;
	}
	

	@RequestMapping(value = "Pay/orderPost", method = RequestMethod.POST)
	public String orderPost(@Valid OrderModel orderModel,
			BindingResult bindingResult, ModelMap model) {
		if (!bindingResult.hasErrors()) {
			// persist the new Order
			EntityManager em = EmfService.getEntityManager();
			em.getTransaction().begin();
			Order order = new Order();

			Key priceKey = PricingDefinitionService.getKey(orderModel
					.getPriceDefinitionId());
			order.setPricingDefinitionId(priceKey);
			order.setNrOfPricingUnits(orderModel.getNrOfPricingUnits());
			em.persist(order);
			em.getTransaction().commit();

			// get the PricingDefinition
			em.getTransaction().begin();
			PricingDefinition price = em
					.find(PricingDefinition.class, priceKey);
			em.getTransaction().rollback();

			// populate the long and compute values
			em.getTransaction().begin();
			Order myOrder = em.find(Order.class, order.getId());
			

			myOrder.setPayServiceName("PayPall");

			myOrder.setPricingName(price.getPricingName());
			myOrder.setPricingValue(price.getPriceInEuro());
			myOrder.setOrderValue(myOrder.getNrOfPricingUnits()
					* price.getPriceInEuro());
			myOrder.setDateOfOrder(new Date());

			myOrder.setUserId(MySecurity.getUserId());
			em.getTransaction().commit();

			// Send the order to pay service

			/**
			 * when the order is payed process the order for the moment just
			 * assume that the order was processed and if wee contact the pay
			 * service API wee will get the positive response Until wee do not
			 * use a payment service wee will just redirect to the process order
			 * page
			 */
			// redirect
			String redirectionUrl = "/Pay/processOrder/"
					+ myOrder.getPayServiceId();
			return "redirect:" + redirectionUrl;
		}
		return "Pay/orderPost";
	}

	@RequestMapping(value = "Pay/processOrder/{payServiceId}")
	public String processOrder(@PathVariable String payServiceId, ModelMap model) {
		// TODO verify that the order was payed

		// Process the order
		OrderService.processOrder(payServiceId);

		// check the status
		Order order = OrderService.getByPayServiceId(payServiceId);
		model.addAttribute(order);
		if (order.isProcessed()) {
			return "Pay/processSuccessful";
		} else {
			return "Pay/processNotsuccessful";
		}
	}
	
	@RequestMapping(value = "Pay/useAllItems/{orderId}")
	public String useAllItems(@PathVariable String orderId, ModelMap model) {
		List<ActivationItem> activationItems = ActivationItemService.getActiveKeysByOrderId(orderId);
		String userId = MySecurity.getUserId();
		List<Line> lines = LineService.getLinesByUserId(userId);
		if (lines.size()== 0){
			return "redirect:/Line/create";
		}
				
		int i=0;
		int k=0;
		while (i<activationItems.size() ){
			ActivationItem activationItem = activationItems.get(i);
			Line line = lines.get(k);
			ActivationItemService.proces(activationItem, line);
			i++;
			k++;
			if (k==lines.size()){
				//reset the lines
				k= 0;
			}
		}
		return "Pay/useAllItems";
	}

	/*
	 * @RequestMapping(value = "Pay/testSlider") public String
	 * testSlider(ModelMap model) { PricingDefinition price =
	 * PricingDefinitionService.getByName("oneLineOneMonthV1"); OrderModel
	 * orderModel = new OrderModel();
	 * 
	 * orderModel.setPriceDefinitionId(price.getLongPartId());
	 * orderModel.setPricingValue(price.getPriceInEuro());
	 * orderModel.setNrOfPricingUnits(1);
	 * 
	 * System.out.println("Price = " +orderModel.getPricingValue());
	 * 
	 * model.addAttribute(orderModel); return "Pay/testSlider"; }
	 */

}
