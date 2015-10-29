package eu.mysmartline.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import eu.mysmartline.entities.ActivationItem;
import eu.mysmartline.entities.Device;
import eu.mysmartline.entities.Line;
import eu.mysmartline.entities.LineNumber;
import eu.mysmartline.entities.NotificationItem;
import eu.mysmartline.models.AddClientToLineResponce;
import eu.mysmartline.models.ApiPrintNumberRequest;
import eu.mysmartline.models.ClientStatusModel;
import eu.mysmartline.models.LineDetailsModel;
import eu.mysmartline.models.RegistrationResultModel;

public class LineService {
	/**
	 * This function identifies the current number and builds the status model
	 * for the current number. If there is no active current number then it sets
	 * the object isActive = false. This boolean is meant to be used in the jsp
	 * for rendering decisions;
	 * 
	 * @return
	 */
	public static ClientStatusModel getStatusForCurrentClient(String lineId) {
		ClientStatusModel clientStatusModel = new ClientStatusModel();
		LineNumber currentNumber = LineService.getCurrentLineNumber(lineId);
		if (currentNumber == null) {
			clientStatusModel.setActive(false);
		} else {

			NotificationItem notificationItem = NotificationItemService.getById( currentNumber
					.getNotificationItemId());
			clientStatusModel.setNotificationId(notificationItem
					.getId());
			clientStatusModel.setActive(true);
			clientStatusModel.setLineName(LineService.getName(lineId));
			clientStatusModel.setClientNumber(LineService.getLabel(lineId)
					+ currentNumber.getNumber());
			clientStatusModel.setCurrentNumber(clientStatusModel
					.getClientNumber());
			clientStatusModel.setClientsAhead(0);
			clientStatusModel.setProbableWaitMinutes(0);
			clientStatusModel.setNotificationValue(notificationItem
					.getNotificationValue());
			clientStatusModel.setNotificationType(notificationItem
					.getNotificationType());
		}
		return clientStatusModel;
	}

	public static int getCurentNumber(String lineId) {
		// TODO implement application logic
		// get current
		LineNumber lineNumber = getCurrentLineNumber(lineId);
		if (lineNumber == null) {
			return 0;
		} else {
			return lineNumber.getNumber();
		}
	}

	public static LineNumber getCurrentLineNumber(String lineId) {
		// get current
		
		Key lineKey = getKey(lineId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		Line line = em.find(Line.class, lineKey);
		TypedQuery<LineNumber> query = em
				.createQuery(
						"select n from LineNumber n where lineId=:theLineId and isCurrent=:vIsCurrent",
						LineNumber.class);
		query.setParameter("theLineId", lineId);
		query.setParameter("vIsCurrent", true);
		List<LineNumber> lineNumbers = query.getResultList();
		em.getTransaction().rollback();
		if (lineNumbers.size() > 0) {
			LineNumber lineNumber = lineNumbers.get(0);
			return lineNumber;
		}

		// if not current get last archived
		em.getTransaction().begin();
		query = em
				.createQuery(
						"select n from LineNumber n where n.lineId=:theLineId and n.dateArchived is not null order by n.dateArchived desc",
						LineNumber.class);
		query.setParameter("theLineId", lineId);
		query.setMaxResults(1);
		lineNumbers = query.getResultList();
		em.getTransaction().rollback();
		System.out.println("Debug item:-------------------------- getCurrentLineNumber");
		if (lineNumbers.size() > 0) {
			LineNumber lineNumber = lineNumbers.get(0);
			return lineNumber;
			/**
			 * TODO compute if the number was reset automatically by day or
			 * manual by owner
			 */
		}
		return null;
	}

	/**
	 * @param lineId
	 * @return the next probable number
	 */
	public static int getNextProbableNumber(String lineId) {
		Key lineKey = getKey(lineId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		Line line = em.find(Line.class, lineKey);
		if (line.isResetNumbers()) {
			return 1;
		}
		TypedQuery<LineNumber> query = em
				.createQuery(
						"select n from LineNumber n where n.lineId=:theLineId order by n.dateAsigned desc",
						LineNumber.class);
		query.setParameter("theLineId", lineId);
		query.setMaxResults(1);
		List<LineNumber> lineNumbers = query.getResultList();
		em.getTransaction().rollback();
		if (lineNumbers.size() > 0) {
			return lineNumbers.get(0).getNumber() + 1;
		} else {
			return 1;
		}
	}

	public static String getPrintedNextProbableNumber(String lineId) {
		return getLabel(lineId) + getNextProbableNumber(lineId);
	}

	public static String getPrintedCurrentNumber(String lineId) {
		return getLabel(lineId) + getCurentNumber(lineId);
	}

	public static String getLabel(String lineId) {
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		Line line = em.find(Line.class, getKey(lineId));
		em.getTransaction().rollback();
		if (line != null) {
			return line.getLineLabel();
		} else
			return "";
	}

	public static String getName(String id) {
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		Line line = em.find(Line.class, getKey(id));
		em.getTransaction().rollback();
		if (line != null) {
			return line.getLineName();
		} else
			return "";
	}

	public static Key getKey(String id) {
		return KeyFactory.stringToKey(id);
	}

	public static int getNumberOfClientsWaiting(String id) {
		Key lineKey = getKey(id);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		Line line = em.find(Line.class, lineKey);
		TypedQuery<LineNumber> query = em
				.createQuery(
						"select n from LineNumber n where n.lineId=:pLine and isArchived=:pIsArchived and isCurrent=:pIsCurrent",
						LineNumber.class);
		query.setParameter("pLine", id);
		query.setParameter("pIsArchived", false);
		query.setParameter("pIsCurrent", false);
		List<LineNumber> lineNumbers = query.getResultList();
		em.getTransaction().rollback();
		return lineNumbers.size();
	}

	public static int getProbableWaitMinutes(String lineId) {
		/**
		 * TODO implement this for the moment just multiply clients ahead with
		 * 15
		 */
		return getNumberOfClientsWaiting(lineId) * 15;
	}

	public static RegistrationResultModel registerNewClient(String lineId,
			String notifType, String notifValue) {
		EntityManager em = EmfService.getEntityManager();

		// <task 1>find out if the user is already in line
		// build the line id
		Key lineKey = getKey(lineId);

		em.getTransaction().begin();
		Line line = em.find(Line.class, lineKey);
		em.getTransaction().rollback();
		
		
		
		em.getTransaction().begin();
		TypedQuery<LineNumber> query1 = em
				.createQuery(
						"select n from LineNumber n where lineId = :theLineId and isArchived = :vIsArchived",
						LineNumber.class);
		query1.setParameter("theLineId", lineId);
		query1.setParameter("vIsArchived", false);
		List<LineNumber> lineNumbers = query1.getResultList();
		em.getTransaction().rollback();
				
		boolean emailInLine = false;
		// detect if user already in line if it is not a print ticket
		if (!notifType.equals("print")) {
			for (LineNumber lineNumber2 : lineNumbers) {
				em.getTransaction().begin();
				
				String notificationItemId = lineNumber2.getNotificationItemId();
				NotificationItem notificationItem = NotificationItemService.getById(notificationItemId);
				if (notificationItem.getNotificationValue()
						.equals(notifValue)) {
					emailInLine = true;
					break;
				}
				em.getTransaction().rollback();
			}
		}
		if (emailInLine) {
			// return error because user is in line
			return new RegistrationResultModel(
					RegistrationResultModel.ReasonNotSuccesfull.ALREADY_IN_LINE);
		} else {

		}
		
		// user is not in line so you have to add him
		// ------- create a number---
		int number = getNextProbableNumber(lineId);
		System.out.println("Next probable number = " + number);

		LineNumber lineNumber = new LineNumber();
		lineNumber.setNumber(number);
		lineNumber.setLineId(lineId);
		em.getTransaction().begin();
		//line.getLineNumbers().add(lineNumber);
		em.persist(lineNumber);
		em.getTransaction().commit();

		// ---end create a number ---
		// --------- create a notification -----------
		NotificationItem notificationItem = NotificationItemService.createNew();
		em.getTransaction().begin();
		notificationItem = em.find(NotificationItem.class,
				notificationItem.getId());
		
		notificationItem.setNotificationType("email");
		if (notifType != null){
			notificationItem.setNotificationType(notifType);
		}
		
		notificationItem.setNotificationValue(notifValue);
		notificationItem.setNotifyBefore(line.getNotifyBefore());
		// @Unowned relations
		lineNumber.setNotificationItemId(notificationItem.getId());
		notificationItem.setLineNumberId(lineNumber.getId());
		em.getTransaction().commit();
		// -----------end create a notification -----
		// if line was reset disable reset
		disableReset(lineId);
		
		// notify the clinet if hotification value = email;
		if (notificationItem.getNotificationType().equals("email")) {
			System.out.println("MailService.sendConfirmRegistration =============================");
			MailService.sendConfirmRegistration(notificationItem
					.getId());
			System.out.println("Mail OK============================ =============================");
		}
		return new RegistrationResultModel(notificationItem.getId());
	}

	public static boolean convertNumber(String notificationId, String notifType,
			String notifValue) {

		// find the notification item and set the email
		Key key = NotificationItemService.getKey(notificationId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		NotificationItem item = em.find(NotificationItem.class, key);
		item.setNotificationType(notifType);
		item.setNotificationValue(notifValue);
		em.getTransaction().commit();
		if (em.getTransaction().isActive()) {
			em.getTransaction().rollback();
			return false;
		}
		// notify the clinet if hotification value = email;
		if (item.getNotificationType().equals("email")) {
			MailService.sendConfirmRegistration(item.getId());
		}
		return true;
	}

	/**
	 * -Verify if the deviceGcmId is associated with the lineId Create number
	 * Create conversion url/ possibility to use user id Set notification type
	 * to print Populate the ApiPrintNumberResponce return the response
	 * 
	 * @param ApiPrintNumberRequest
	 */
	public static AddClientToLineResponce registerNewPrintNumber(
			ApiPrintNumberRequest printRequest) {
		/*if (!printRequestIsLegal(printRequest)) {
			AddClientToLineResponce failResponce = new AddClientToLineResponce();
			failResponce.setSuccess(false);
			return failResponce;
		}
		Long lineId = Long.parseLong(printRequest.getLineId());
		RegistrationResultModel registrationResultModel = registerNewClient(
				lineId, "print", "print");
		AddClientToLineResponce goodResponce = new AddClientToLineResponce();
		goodResponce.setSuccess(registrationResultModel
				.isSuccesfullRegistration());
		goodResponce.setNotificationType("print");
		goodResponce.setNotificationValue("print");
		String lineName = getName(lineId);
		goodResponce.setLineName(lineName);
		Integer clientNumber = NotificationItemService.getLineNumber(
				registrationResultModel.getLongNotificationItemId())
				.getNumber();
		goodResponce.setClientDisplayNumber(getLabel(lineId) + clientNumber);
		goodResponce.setNotificationPanelId(registrationResultModel
				.getLongNotificationItemId());
		return goodResponce;*/
			
		throw new UnsupportedOperationException("Please finnish this");
	}

	public static Map<LineNumber, NotificationItem> getWaitingClients(
			String lineId) {
		// I should allso order by date;
		Map<LineNumber, NotificationItem> map = new LinkedHashMap<LineNumber, NotificationItem>();
		Key lineKey = getKey(lineId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		Line line = em.find(Line.class, lineKey);

		TypedQuery<LineNumber> query = em
				.createQuery(
						"select n from LineNumber n where n.line=:theLine and n.isCurrent=:vIsCurrent and n.isArchived=:vIsArchived order by n.dateAsigned asc",
						LineNumber.class);
		query.setParameter("theLine", line);
		query.setParameter("vIsCurrent", false);
		query.setParameter("vIsArchived", false);
		List<LineNumber> lineNumbers = query.getResultList();
		em.getTransaction().rollback();

		for (LineNumber number : lineNumbers) {
			NotificationItem notificationItem = NotificationItemService.getById(number.getNotificationItemId());
			em.getTransaction().begin();
			map.put(number, notificationItem);
			em.getTransaction().rollback();
		}
		return map;
	}

	public static List<LineDetailsModel> getLineDetailsModelListByDeviceGcmId(
			String deviceGcmId) {
		/*Device device = DeviceRegistrationService.getDeviceByGcmId(deviceGcmId);
		String userId = device.getUserId();
		List<Line> lines = getLinesByUserId(userId);
		List<LineDetailsModel> result = new ArrayList<LineDetailsModel>();
		for (Line line : lines) {
			LineDetailsModel details = new LineDetailsModel();
			details.setName(line.getLineName());
			details.setRegistrationUrl("LineRegistration/readClientDetails/"
					+ line.getId());
			details.setId(line.getId());
			result.add(details);
		}
		return result;*/
		throw new UnsupportedOperationException("Please finish this");
	}

	public static List<Line> getLinesByUserId(String userId) {
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();

		TypedQuery<Line> query = em.createQuery(
				"select s from Line s where userId = :theUserId", Line.class);
		query.setParameter("theUserId", userId);
		List<Line> lines = query.getResultList();
		em.getTransaction().rollback();
		return lines;
	}

	public static Line getLine(String id) {
		Key lineKey = getKey(id);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		Line line = em.find(Line.class, lineKey);
		em.getTransaction().rollback();
		return line;
	}

	private static boolean printRequestIsLegal(
			ApiPrintNumberRequest printRequest) {

		/*Device device = DeviceRegistrationService.getDeviceByGcmId(printRequest
				.getPropertyRegId());
		Line line = getLine(Long.parseLong(printRequest.getLineId()));
		String deviceUser = device.getUserId();
		String lineUser = line.getUserId();

		// return the legality of the request as boolean
		return deviceUser.equals(lineUser);*/
		throw new UnsupportedOperationException("Please finish this");
	}

	public static void archive(String lineId) {
		Key lineKey = getKey(lineId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		Line line = em.find(Line.class, lineKey);
		if (line != null) {
			line.setArchivedUserId(line.getUserId());
			line.setUserId(null);
			em.getTransaction().commit();
		} else {
			em.getTransaction().rollback();
		}
	}

	public static void resetNumbers(String lineId) {
		Key lineKey = getKey(lineId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		Line line = em.find(Line.class, lineKey);
		line.setResetNumbers(true);
		em.getTransaction().commit();
	}

	private static void disableReset(String lineId) {
		Key lineKey = getKey(lineId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		Line line = em.find(Line.class, lineKey);
		if (line.isResetNumbers()) {
			line.setResetNumbers(false);
			em.getTransaction().commit();
		} else {
			em.getTransaction().rollback();
		}
	}

	/**
	 * this method is to save only the modified values form the HTML edit form
	 * 
	 * @param line
	 */
	public static void saveEdit(Line line) {
		EntityManager em = EmfService.getEntityManager();
		Key lineKey = getKey(line.getId());
		em.getTransaction().begin();
		Line vipLine = em.find(Line.class, lineKey);
		vipLine.setLineName(line.getLineName());
		vipLine.setLineLabel(line.getLineLabel());
		vipLine.setNotifyBefore(line.getNotifyBefore());
		em.getTransaction().commit();
	}

	public static void createLine(Line line, ActivationItem item) {
		// persist the line
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		em.persist(line);
		em.getTransaction().commit();

		// populate the longId;
		/*em.getTransaction().begin();
		line = em.find(Line.class, line.getId());
		line.setLongPartId(line.getId().getId());
		em.getTransaction().commit();*/

		ActivationItemService.proces(item, line);
	}
	public static Line getLineByNotifId(String notifId){
		NotificationItem notificationItem = NotificationItemService.getById(notifId);
		LineNumber lineNumber = LineNumberService.getById( notificationItem.getLineNumberId());
		
		String lineId = lineNumber.getLineId();
		Line line = LineService.getLine(lineId);
		return line;
	}
}
