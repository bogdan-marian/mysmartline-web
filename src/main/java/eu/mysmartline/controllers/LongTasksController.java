package eu.mysmartline.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.mysmartline.entities.LineNumber;
import eu.mysmartline.entities.NotificationItem;
import eu.mysmartline.models.MyKeys;
import eu.mysmartline.services.LineService;
import eu.mysmartline.services.MailService;

@Controller
public class LongTasksController {

	@ResponseBody
	@RequestMapping(value = "LongTasks/sendMessagesToWaitingClients/{lineId}", method = RequestMethod.GET)
	public void sendMessagesToWaitingClients(@PathVariable String lineId) {
		Map<LineNumber, NotificationItem> waitingClients = LineService
				.getWaitingClients(lineId);
		int i = 0;
		for (Map.Entry<LineNumber, NotificationItem> client : waitingClients
				.entrySet()) {
			Integer before = client.getValue().getNotifyBefore();
			if (before == null){
				before = MyKeys.NOTIFY_BEFORE;
			}
			if (before >= i) {
				if (client.getValue().getNotificationType().equals("email")) {
					MailService.sendUpdateMessage(client.getValue()
							.getId());
				}
			}
			i++;
		}
		//System.out.println("debug:LongTasksController send notification and i =" + i);
	}
}
