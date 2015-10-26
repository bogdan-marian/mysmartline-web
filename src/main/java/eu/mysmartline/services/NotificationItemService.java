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
		em.getTransaction().commit();
		return notificationItem;
	}

	public static Key getKey(String notificationId) {
		/*return KeyFactory.createKey(NotificationItem.class.getSimpleName(),
				notificationId);*/
		return KeyFactory.stringToKey(notificationId);
	}

	public static LineNumber getLineNumber(String notificationId) {
		Key notifKey = getKey(notificationId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		NotificationItem notificationItem = em.find(NotificationItem.class,
				notifKey);
		em.getTransaction().rollback();
		LineNumber lineNumber = LineNumberService.getById(notificationItem.getLineNumberId());
		return lineNumber;
	}

	public static String getNotificationValue(String notificationId) {
		Key notifKey = getKey(notificationId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		NotificationItem notificationItem = em.find(NotificationItem.class,
				notifKey);
		em.getTransaction().rollback();
		return notificationItem.getNotificationValue();
	}
	public static String getLineName(String notificationId){
		LineNumber lineNumber = getLineNumber(notificationId);
		
		return LineService.getName(lineNumber.getLineId());
	}
	public static String getLineUserId(String notificationId){
		LineNumber lineNumber = getLineNumber(notificationId);
		Line line = LineService.getLine(lineNumber.getLineId());
		
		return line.getUserId();
	}
	
	public static String getClientUrl(String notificationId){
		return MyKeys.PROPERTY_HOME +"ClientPanel/viewStatus/"+notificationId;
	}
	public static int getClientsAhead(String notificationId){
		Key notifKey = getKey(notificationId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		NotificationItem notificationItem = em.find(NotificationItem.class,
				notifKey);
		em.getTransaction().rollback();
		String lineNumberId = notificationItem.getLineNumberId();
		LineNumber lineNumber= LineNumberService.getById(lineNumberId);
		return LineNumberService.getClientsAhead(lineNumber.getId());
	}
	
	public static NotificationItem getById(String notificationItemId){
		Key key = getKey(notificationItemId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		NotificationItem notificationItem = em.find(NotificationItem.class, key);
		em.getTransaction().rollback();
		return notificationItem;
	}
}