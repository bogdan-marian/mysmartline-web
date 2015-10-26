package eu.mysmartline.services;

import eu.mysmartline.entities.LineNumber;
import eu.mysmartline.models.ClientStatusModel;

public class ClientStatusService {
	/**
	 * +private Long notificationId; +private boolean isActive; +private String
	 * lineName; +private String clientNumber; +private String currentNumber;
	 * private int clientsAhead; private int probableWaitMinutes; private String
	 * email;
	 * 
	 * @param notificationId
	 * @return
	 */

	public static ClientStatusModel getStatusByNotificationId(
			String notificationId) {
		ClientStatusModel clientStatusModel = new ClientStatusModel();
		clientStatusModel.setNotificationId(notificationId);

		LineNumber lineNumber = NotificationItemService
				.getLineNumber(notificationId);
		String lineId = lineNumber.getLineId();

		clientStatusModel.setActive(lineNumber.isArchived());
		clientStatusModel.setLineName(LineService.getName(lineId));

		clientStatusModel.setClientNumber(LineService.getLabel(lineId)
				+ lineNumber.getNumber());
		clientStatusModel.setCurrentNumber(LineService.getLabel(lineId)
				+ LineService.getCurentNumber(lineId));
		int clientsAhead = LineNumberService
				.getClientsAhead(lineNumber.getId());
		clientStatusModel.setClientsAhead(clientsAhead);
		/**
		 * TODO implement this for the moment just multiply clients ahead with
		 * 15
		 */
		int averageWaitMinutes = (int) LineNumberService
				.getAverageDuration(lineNumber.getId());

		clientStatusModel.setProbableWaitMinutes(clientsAhead
				* averageWaitMinutes);
		clientStatusModel.setNotificationValue(NotificationItemService
				.getNotificationValue(notificationId));

		return clientStatusModel;
	}

}
