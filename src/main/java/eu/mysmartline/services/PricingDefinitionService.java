package eu.mysmartline.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import eu.mysmartline.entities.PricingDefinition;

public class PricingDefinitionService {

	/**
	 * this method is to save only the modified values form the HTML edit form
	 * 
	 * @param pricingDefinition
	 */
	public static void saveEdit(PricingDefinition pricingDefinition) {
		EntityManager em = EmfService.getEntityManager();
		Key priceKey = getKey(pricingDefinition.getLongPartId());

		em.getTransaction().begin();
		pricingDefinition.setId(priceKey);
		em.persist(pricingDefinition);
		em.getTransaction().commit();
	}

	public static Key getKey(Long priceId) {
		return KeyFactory.createKey(PricingDefinition.class.getSimpleName(),
				priceId);
	}

	/**
	 * finds the price by price name. It will brake the application if priceName
	 * is not existent
	 * 
	 * @param priceName
	 * @return PricingDefinition
	 */
	public static PricingDefinition getByName(String priceName) {
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		TypedQuery<PricingDefinition> query = em
				.createQuery(
						"select p from PricingDefinition p where p.pricingName=:thePricingName",
						PricingDefinition.class);
		query.setParameter("thePricingName", priceName);
		List<PricingDefinition> list = query.getResultList();
		return list.get(0);
	}

	public static PricingDefinition getByKey(Key key) {
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		PricingDefinition pricingDefinition = em.find(PricingDefinition.class,
				key);
		em.getTransaction().rollback();
		return pricingDefinition;
	}
}
