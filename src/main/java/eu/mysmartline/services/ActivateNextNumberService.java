package eu.mysmartline.services;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

import eu.mysmartline.entities.LineNumber;
import eu.mysmartline.entities.NotificationItem;
import eu.mysmartline.entities.ServicePoint;
import eu.mysmartline.models.ActivateNextNumberModel;

public class ActivateNextNumberService {

	/**
	 * build a notification set by line id in this set wee have the following:
	 * key is the notification id value is a string composed of Number an
	 * notification value
	 */
	public static Map<String, String> getNotificationSetByLineId(String lineId) {

		String label = LineService.getLabel(lineId);
		Map<LineNumber, NotificationItem> clients = LineService
				.getWaitingClients(lineId);
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (Map.Entry<LineNumber, NotificationItem> entry : clients.entrySet()) {
			map.put(entry.getValue().getId(), label
					+ entry.getKey().getNumber() + " ("
					+ entry.getValue().getNotificationValue() + ")");
		}
		return map;
	}

	public static Map<String, String> getNotificationsetByServicePointId(
			String pointId) {
		Map<LineNumber, NotificationItem> clients = ServicePointService
				.getWaitingClients(pointId);
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (Map.Entry<LineNumber, NotificationItem> entry : clients.entrySet()) {
			String label = LineService.getLine(entry.getKey().getLineId()).getLineLabel();
			String notifId = entry.getValue().getId();
			int clientNumber = entry.getKey().getNumber();
			String notifValue = " (" + entry.getValue().getNotificationValue()
					+ ")";

			map.put(notifId, label + clientNumber + notifValue);
		}
		return map;
	}

	public static Set<Map.Entry<String, String>> getServiceSetByLineId(String lineId) {
		List<ServicePoint> servicePoints = ServicePointService
				.getAllByLineId(lineId);

		Map<String, String> map2 = new LinkedHashMap<String, String>();
		for (ServicePoint servicePoint : servicePoints) {
			map2.put(servicePoint.getId(), servicePoint.getShortName());

		}

		return map2.entrySet();
	}

	public static Map<String, String> getActiveServicePointsMap() {
		Map<String, String> map = new HashMap<String, String>();
		List<ServicePoint> servicePoints = ServicePointService
				.getActivePoints();
		for (ServicePoint servicePoint : servicePoints) {
			map.put(servicePoint.getId(), servicePoint.getShortName());
		}
		return map;
	}

	public static void activateNextNumber(ActivateNextNumberModel model) {

		// locate the activated item
		Map<LineNumber, NotificationItem> map = LineService
				.getWaitingClients(model.getLineId());
		for (Map.Entry<LineNumber, NotificationItem> entry : map.entrySet()) {
			if (entry.getValue().getId() == model.getNotificationId()) {
				// archive current number
				LineNumber currentNumber = LineService
						.getCurrentLineNumber(model.getLineId());
				EntityManager em = EmfService.getEntityManager();
				if (currentNumber != null) {
					// item located it will be archived
					em.getTransaction().begin();
					String currentKey = currentNumber.getId();
					currentNumber = em.find(LineNumber.class, currentKey);
					currentNumber.setArchived(true);
					currentNumber.setCurrent(false);
					currentNumber.setDateArchived(new Date());
					/**
					 * TODO calculate time the previous client took to serve.
					 * create special service for this and pas current date time
					 * to the service.
					 */
					// copute duration
					if ((currentNumber.getDateActivated() != null)
							&& (currentNumber.getDateArchived() != null)) {
						DateTime start =new DateTime(currentNumber.getDateActivated());
						DateTime end = new DateTime(currentNumber.getDateArchived());
						Interval interval = new Interval(start, end);
						Duration duration = interval.toDuration();
						currentNumber.setDurationInMiliseconds(duration.getMillis());
						//compute same day
						int startYear = start.getYear();
						int startDay = start.getDayOfYear();
						int endYear = end.getYear();
						int endDay = end.getDayOfYear();
						if ((startYear == endYear)&& (startDay == endDay)){
							currentNumber.setSameDay(true);
						}
					}
					String notifItemId = currentNumber.getNotificationItemId();
					NotificationItem oldCurent = NotificationItemService.getById(notifItemId);
					em.getTransaction().commit();

					// send thank you notification to oldCurent
					if (oldCurent.getNotificationType().equals("email")) {
						MailService.sendThankYou(oldCurent.getId());
					}
				}
				// configure new customer and servicePoint
				ServicePoint servicePoint = ServicePointService
						.getServicePoint(model.getServicePointId());
				em.getTransaction().begin();
				String newKey = entry.getKey().getId();
				String serviceKey = servicePoint.getId();
				servicePoint = em.find(ServicePoint.class, serviceKey);
				LineNumber newCurrent = em.find(LineNumber.class, newKey);

				newCurrent.setCurrent(true);
				if (servicePoint != null) {
					newCurrent.setServicePointId(servicePoint.getId());
					newCurrent.setDateActivated(new Date());
				}
				String newCustomerId = newCurrent.getNotificationItemId();
				NotificationItem newCustomer = NotificationItemService.getById(newCustomerId);
				em.getTransaction().commit();
				// set service point

				// send notification to newCurrent
				MailService.sendMessageToNewCustomer(
						newCustomer.getId(), model.getServicePointId());

				// send notifications to valid waiting clients
				Queue queue = QueueFactory.getDefaultQueue();

				TaskOptions taskOptions = TaskOptions.Builder.withUrl(
						"/LongTasks/sendMessagesToWaitingClients/"
								+ model.getLineId()).method(Method.GET);
				queue.add(taskOptions);
				// send notification to all devices
				MyHttpService.notifyDevicesCurrentNumberChanjed(
						model.getLineId(), model.getServicePointId());

				break;
			}
		}
	}
}
