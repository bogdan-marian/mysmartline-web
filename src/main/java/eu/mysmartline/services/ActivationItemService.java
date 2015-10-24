package eu.mysmartline.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import eu.mysmartline.entities.ActivationItem;
import eu.mysmartline.entities.Line;

public class ActivationItemService {
	public static void newItem(Key orderKey, Integer nrOfMonts) {
		ActivationItem activationItem = new ActivationItem();
		activationItem.setUserId(MySecurity.getUserId());
		activationItem.setOrderKey(orderKey);
		activationItem.setNrOfMonts(nrOfMonts);

		// persist the entity
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		em.persist(activationItem);
		em.getTransaction().commit();

		// populate the longId
		em.getTransaction().begin();
		ActivationItem item = em.find(ActivationItem.class,
				activationItem.getId());
		item.setLongPartId(item.getId().getId());
		em.getTransaction().commit();
	}

	/**
	 * it returns a list with the active activation items of the current user
	 * for a specific order
	 * 
	 * @param orderId
	 * @return
	 */
	public static List<ActivationItem> getActiveKeysByOrderId(Long orderId) {
		Key orderKey = OrderService.getKey(orderId);
		String userId = MySecurity.getUserId();

		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();

		TypedQuery<ActivationItem> query = em
				.createQuery(
						"select a from ActivationItem a where userId = :theUserId and orderKey = :theOrderKey",
						ActivationItem.class);
		query.setParameter("theUserId", userId);
		query.setParameter("theOrderKey", orderKey);
		List<ActivationItem> activationItems = query.getResultList();
		em.getTransaction().rollback();
		return activationItems;
	}

	public static void proces(ActivationItem activationItem, Line line) {
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		line = em.find(Line.class, line.getId());
		line.setValidUntil(getValidDate(line.getValidUntil(),
				activationItem.getNrOfMonts()));
		em.getTransaction().commit();

		em.getTransaction().begin();
		activationItem = em.find(ActivationItem.class, activationItem.getId());
		activationItem.setArchived(true);
		em.getTransaction().commit();
	}

	private static Date getValidDate(Date date, Integer nrOfMonths) {
		if (date == null) {
			date = new Date();
		}
		if (date.compareTo(new Date())<0){
			date = new Date();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, nrOfMonths);
		return calendar.getTime();
	}

	public static boolean userHasActivationItems() {
		String userId = MySecurity.getUserId();
		List<ActivationItem> activationItems = getByUserId(userId);
		if (activationItems.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	public static List<ActivationItem> getByUserId(String userId){
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();

		TypedQuery<ActivationItem> query = em
				.createQuery(
						"select a from ActivationItem a where userId = :theUserId and archived = :theArchived",
						ActivationItem.class);
		query.setParameter("theUserId", userId);
		query.setParameter("theArchived", false);
		return query.getResultList();
	}
	public static ActivationItem getByLongId(Long longId){
		Key key = getKey(longId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		ActivationItem activationItem = em.find(ActivationItem.class, key);
		em.getTransaction().rollback();
		return activationItem;
	}
	public static Key getKey(Long longId) {
		return KeyFactory.createKey(ActivationItem.class.getSimpleName(), longId);
	}
	
	public static void createBetaItem(){
		String userId = MySecurity.getUserId();
		
		ActivationItem activationItem = new ActivationItem();
		activationItem.setUserId(userId);
		activationItem.setNrOfMonts(1);
		
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		em.persist(activationItem);
		em.getTransaction().commit();
		
		//populate the longId;
		em.getTransaction().begin();
		activationItem = em.find(ActivationItem.class, activationItem.getId());
		activationItem.setLongPartId(activationItem.getId().getId());
		em.getTransaction().commit();
	}
}
