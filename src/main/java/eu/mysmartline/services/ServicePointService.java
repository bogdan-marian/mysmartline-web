package eu.mysmartline.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.tiles.request.collection.MapEntry;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import eu.mysmartline.entities.Line;
import eu.mysmartline.entities.LineNumber;
import eu.mysmartline.entities.NotificationItem;
import eu.mysmartline.entities.ServicePoint;
import eu.mysmartline.models.ClientStatusModel;

public class ServicePointService {
	public static ServicePoint getServicePoint(String servicePointId) {
		Key serviceKey = getKey(servicePointId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		ServicePoint servicePoint = em.find(ServicePoint.class, serviceKey);
		em.getTransaction().rollback();
		return servicePoint;
	}

	public static Key getKey(String serviceId) {
		return KeyFactory.stringToKey(serviceId);
	}

	public static String getShortName(String serviceId) {
		ServicePoint servicePoint = getServicePoint(serviceId);
		return servicePoint.getShortName();
	}

	public static List<ServicePoint> getAllByLineId(String lineId) {
		Line line = LineService.getLine(lineId);
		String userId = line.getUserId();
		return getAllByUserId(userId);
	}

	public static List<ServicePoint> getAllByUserId(String userId) {
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		TypedQuery<ServicePoint> query = em.createQuery(
				"select s from ServicePoint s where s.userId = :theUserId",
				ServicePoint.class);
		query.setParameter("theUserId", userId);
		List<ServicePoint> servicePoints = query.getResultList();
		em.getTransaction().rollback();
		return servicePoints;
	}

	public static ClientStatusModel getStatusForCurrentClient(
			Long servicePointId) {
		ClientStatusModel clientStatusModel = new ClientStatusModel();
		return clientStatusModel;
	}

	public static LineNumber getCurrentNumberAtServicePoint(String servicePointId) {
		// TODO implement logic
		Key serviceKey = getKey(servicePointId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();

		em.getTransaction().rollback();
		throw new IllegalStateException("Does this needs to be implemented?");
	}

	public static List<ServicePoint> getActivePoints() {
		String userId = MySecurity.getUserId();
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		TypedQuery<ServicePoint> query = em
				.createQuery(
						"select s from ServicePoint s where s.userId = :theUserId and s.active=:vActive",
						ServicePoint.class);
		query.setParameter("theUserId", userId);
		query.setParameter("vActive", true);
		List<ServicePoint> servicePoints = query.getResultList();
		em.getTransaction().rollback();
		return servicePoints;
	}

	public static void archive(String servicePointId) {
		// TODO Auto-generated method stub
		Key serviceKey = ServicePointService.getKey(servicePointId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		ServicePoint servicePoint = em.find(ServicePoint.class, serviceKey);
		if (servicePoint == null) {
			em.getTransaction().rollback();
			return;
		}
		servicePoint.setActive(false);
		em.getTransaction().commit();
	}

	public static Map<LineNumber, NotificationItem> getWaitingClients(
			String pointId) {
		Map<LineNumber, NotificationItem> map = new LinkedHashMap<LineNumber, NotificationItem>();
		String theUserId = MySecurity.getUserId();
		if (theUserId == null){
			return map;
		}
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		TypedQuery<Line> query = em.createQuery(
				"select s from Line s where userId = :theUserId", Line.class);
		query.setParameter("theUserId", theUserId);
		List<Line> lines = query.getResultList();
		em.getTransaction().rollback();
		for (Line line : lines) {
			Map<LineNumber, NotificationItem> subMap = LineService.getWaitingClients(line.getId());
			for(Map.Entry<LineNumber, NotificationItem> entry: subMap.entrySet()){
				map.put(entry.getKey(), entry.getValue());
			}
		}
		return map;
	}
}
