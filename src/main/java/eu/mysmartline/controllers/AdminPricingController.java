package eu.mysmartline.controllers;

import java.util.List;

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

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import eu.mysmartline.entities.PricingDefinition;
import eu.mysmartline.services.EmfService;
import eu.mysmartline.services.PricingDefinitionService;

@Controller
public class AdminPricingController {

	@RequestMapping(value = "AdminPricing/index")
	public String index(ModelMap model) {
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		TypedQuery<PricingDefinition> query = em.createQuery(
				"select p from PricingDefinition p ", PricingDefinition.class);
		List<PricingDefinition> definition = query.getResultList();
		em.getTransaction().rollback();
		model.addAttribute("definition", definition);

		return "AdminPricing/index";
	}

	@RequestMapping(value = "AdminPricing/create")
	public String create(Model model) {
		PricingDefinition pricingDefinition = new PricingDefinition();
		model.addAttribute(pricingDefinition);
		return "AdminPricing/create";
	}

	@RequestMapping(value = "AdminPricing/createPost", method = RequestMethod.POST)
	public String createPost(@Valid PricingDefinition pricingDefinition,
			BindingResult bindingResult, ModelMap model) {
		if (!bindingResult.hasErrors()) {
			// persist the new PricingDefinition
			EntityManager em = EmfService.getEntityManager();
			em.getTransaction().begin();
			em.persist(pricingDefinition);
			em.getTransaction().commit();

			// populate the longId
			em.getTransaction().begin();
			PricingDefinition pricing = em.find(PricingDefinition.class,
					pricingDefinition.getId());
			pricing.setLongPartId(pricing.getId().getId());
			em.getTransaction().commit();
		}
		return "redirect:index";
	}

	@RequestMapping(value = "AdminPricing/edit/{priceId}")
	public String edit(@PathVariable Long priceId, ModelMap model) {
		Key priceKey = KeyFactory.createKey(
				PricingDefinition.class.getSimpleName(), priceId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		PricingDefinition pricingDefinition = em.find(PricingDefinition.class,
				priceKey);
		em.getTransaction().rollback();
		model.addAttribute(pricingDefinition);
		return "AdminPricing/edit";
	}
	
	@RequestMapping(value = "AdminPricing/editPost", method = RequestMethod.POST)
	public String editPost(@Valid PricingDefinition	pricingDefinition, BindingResult bindingResult,
			ModelMap model){
		if (bindingResult.hasErrors()){
			return "forward:/Error/defaultError";
		}
		
		PricingDefinitionService.saveEdit(pricingDefinition);
		return "forward:/AdminPricing/index";
	}
}
