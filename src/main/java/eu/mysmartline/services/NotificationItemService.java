package eu.mysmartline.services;

import javax.persistence.EntityManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import eu.mysmartline.entities.Line;
import eu.mysmartline.entities.LineNumber;
import eu.mysmartline.entities.NotificationItem;
import eu.mysmartline.models.MyKeys;

public class NotificationItemService {
	public static NotificationItem createNew() {
		NotificationItem notificationItem = new NotificationItem();
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		em.persist(notificationItem);
		em.getTransaction().commit();
		em.getTransaction().begin();
		notificationItem = em.find(NotificationItem.class,
				notificationItem.getId());
		notificationItem.setLongPartId(notificationItem.getId().getId());
		em.getTransaction().commit();
		return notificationItem;
	}

	public static Key getKey(Long notificationId) {
		return KeyFactory.createKey(NotificationItem.class.getSimpleName(),
				notificationId);
	}

	public static LineNumber getLineNumber(Long notificationId) {
		Key notifKey = getKey(notificationId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		NotificationItem notificationItem = em.find(NotificationItem.class,
				notifKey);
		em.getTransaction().rollback();
		return notificationItem.getLineNumber();
	}

	public static String getNotificationValue(Long notificationId) {
		Key notifKey = getKey(notificationId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		NotificationItem notificationItem = em.find(NotificationItem.class,
				notifKey);
		em.getTransaction().rollback();
		return notificationItem.getNotificationValue();
	}
	public static String getLineName(Long notificationId){
		LineNumber lineNumber = getLineNumber(notificationId);
		Line line = lineNumber.getLine();
		
		//return LineService.getName(line.getLongPartId());
		throw new IllegalStateException("Please finish this");
	}
	public static String getLineUserId(Long notificationId){
		LineNumber lineNumber = getLineNumber(notificationId);
		Line line = lineNumber.getLine();
		
		return line.getUserId();
	}
	
	public static String getClientUrl(Long notificationId){
		return MyKeys.PROPERTY_HOME +"ClientPanel/viewStatus/"+notificationId;
	}
	public static int getClientsAhead(Long notificationId){
		Key notifKey = getKey(notificationId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		NotificationItem notificationItem = em.find(NotificationItem.class,
				notifKey);
		LineNumber lineNumber= notificationItem.getLineNumber();
		em.getTransaction().rollback();
		
		return LineNumberService.getClientsAhead(lineNumber.getId());
	}
	public static NotificationItem getByLongId(Long longId){
		Key key = getKey(longId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		NotificationItem notificationItem = em.find(NotificationItem.class, key);
		em.getTransaction().rollback();
		return notificationItem;
	}
}