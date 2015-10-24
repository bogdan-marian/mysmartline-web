package eu.mysmartline.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import eu.mysmartline.entities.Order;
import eu.mysmartline.entities.PricingDefinition;

public class OrderService {
	public static void processOrder(String payServiceId) {
		// select the order
		String userId = MySecurity.getUserId();
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();

		TypedQuery<Order> query = em
				.createQuery(
						"select o from Order o where userId = :theUserId and payServiceId = :thePayServiceId and dateOfPayment IS NULL",
						Order.class);
		query.setParameter("theUserId", userId);
		query.setParameter("thePayServiceId", payServiceId);
		List<Order> orders = query.getResultList();
		em.getTransaction().rollback();

		// Select only the fist order
		Order order = orders.get(0);
		// only process if the order is not processed
		if (!order.isProcessed()) {
			PricingDefinition pricingDefinition = PricingDefinitionService
					.getByKey(order.getPricingDefinitionId());
			Integer nrOfMonts = pricingDefinition.getMonthsValid();
			Integer nrOfItems = order.getNrOfPricingUnits() * pricingDefinition.getNrOfLines();
			for (int i = 0; i < nrOfItems; i++) {
				//TODO create a push queue to do this
				ActivationItemService.newItem(order.getId(), nrOfMonts);
			}
			
			em.getTransaction().begin();
			order = em.find(Order.class, order.getId());
			order.setProcessed(true);
			em.getTransaction().commit();
		}
	}
	public static Order getByPayServiceId(String payServiceId){
		String userId = MySecurity.getUserId();
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();

		TypedQuery<Order> query = em
				.createQuery(
						"select o from Order o where userId = :theUserId and payServiceId = :thePayServiceId",
						Order.class);
		query.setParameter("theUserId", userId);
		query.setParameter("thePayServiceId", payServiceId);
		List<Order> orders = query.getResultList();
		em.getTransaction().rollback();
		return orders.get(0);
	}
	
	public static Key getKey(Long orderId){
		return KeyFactory.createKey(Order.class.getSimpleName(), orderId);
	}
}
