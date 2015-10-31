package eu.mysmartline.services;

import java.util.Date;

import javax.persistence.EntityManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import eu.mysmartline.entities.CounterSpace;

public class CounterService {
	
	public static void addEmail(String receaver, String type, String userId) {
		String newCounterId = createCounterSpace();
		Key key = KeyFactory.stringToKey(newCounterId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		CounterSpace counterSpace = em.find(CounterSpace.class, key);
		counterSpace.setType(type);
		counterSpace.setReceaver(receaver);
		counterSpace.setUserId(userId);
		em.getTransaction().commit();
	}

	private static String createCounterSpace() {
		CounterSpace counter = new CounterSpace();
		counter.setDateSent(new Date());

		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		em.persist(counter);
		em.getTransaction().commit();
		//populate the long id
		return counter.getId();
	}
	
}
