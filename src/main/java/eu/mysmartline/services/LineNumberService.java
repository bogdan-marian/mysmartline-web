package eu.mysmartline.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.joda.time.Duration;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import eu.mysmartline.entities.Line;
import eu.mysmartline.entities.LineNumber;

public class LineNumberService {
	public static int getClientsAhead(String lineNumberId) {
		Key key = LineNumberService.getKey(lineNumberId);
		
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		LineNumber lineNumber = em.find(LineNumber.class, key);
		String lineId = lineNumber.getLineId();

		TypedQuery<LineNumber> query = em
				.createQuery(
						"select n from LineNumber n where n.lineId = :vLineId and n.isArchived = :vIsArchived and n.isCurrent =:vIsCurrent order by n.dateAsigned asc",
						LineNumber.class);
		query.setParameter("vLineId", lineId);
		query.setParameter("vIsArchived", false);
		query.setParameter("vIsCurrent", false);
		List<LineNumber> numbers = query.getResultList();
		em.getTransaction().rollback();
		int i = 0;
		boolean notInLine = true;
		for (LineNumber number : numbers) {
			if (lineNumber.getId().equals(number.getId())) {
				notInLine = false;
				break;
			}
			i++;
		}
		if (notInLine) {
			return 0;
		}
		return i;
	}

	public static Key getKey(String id) {
		return KeyFactory.stringToKey(id);
	}

	/**
	 * returns average duration in minutes
	 * 
	 * @param numberKey
	 * @return
	 */
	public static long getAverageDuration(String lineNumberId) {
		Key key = LineNumberService.getKey(lineNumberId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		LineNumber lineNumber = em.find(LineNumber.class, key);
		String vLineId = lineNumber.getLineId();
		TypedQuery<LineNumber> query = em
				.createQuery(
						"select n from LineNumber n where  n.lineId = :vLineId and n.isArchived = :vIsArchived and n.sameDay = sameDay   order by n.dateArchived desc",
						LineNumber.class);
		query.setParameter("lineId", vLineId);
		query.setParameter("vIsArchived", true);
		query.setParameter("sameDay", true);
		query.setMaxResults(10);
		List<LineNumber> numbers = query.getResultList();
		em.getTransaction().rollback();
		if (numbers.size() == 0) {
			return 0;
		}
		long sum = 0;
		long count = 0;
		for (LineNumber number : numbers) {
			if (number.getDurationInMiliseconds() != null) {
				sum = sum + number.getDurationInMiliseconds();
				count ++;
			}
		}
		if (count == 0) return 0;
		sum = sum / count;
		Duration duration = new Duration(sum);
		return duration.getStandardMinutes();
	}

	public static LineNumber getById(String lineNumberId) {
		Key key = getKey(lineNumberId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		LineNumber lineNumber = em.find(LineNumber.class, key);
		em.getTransaction().rollback();
		return lineNumber;
	}
	
}
