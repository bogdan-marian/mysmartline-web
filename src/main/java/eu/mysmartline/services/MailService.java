package eu.mysmartline.services;

import eu.mysmartline.models.MyKeys;

public class MailService {
	public static void sendConfirmRegistration(String notificationId) {
		
		String emailValue = NotificationItemService
				.getNotificationValue(notificationId);
		// count the message
		String userId = NotificationItemService.getLineUserId(notificationId);
		
		CounterService.addEmail(emailValue, MyKeys.MESSAGE_TYPE_EMAIL, userId);
		// if it is a test then do not send the email;
		System.out.println("==============================Prepare for test_domain = ");
		
		if (emailValue.contains(MyKeys.TEST_DOMAIN)) {
			// test mail do nothing
			System.out.println("Debug:Nothing to fix hear (just a simple return) ====================================================================================Debug4");
			return;
		}
		System.out.println("Debug: I'm confused ====================================================================================Debug4");
		
		Sendgrid mail = newSendgrid();
		mail.setTo(emailValue)
				.setFrom("my-smart-line-service@mysmartline.eu")
				.setSubject("Succesfull regitration")
				.setText(
						"Congratulation you have bean succesfuly regitered in line\n"
								+ "\tline name: "
								+ NotificationItemService
										.getLineName(notificationId)
								+ "\n"
								+ "\tyour number: "
								+ NotificationItemService.getLineNumber(
										notificationId).getNumber()
								+ "\n"
								+ "view status at: "
								+ NotificationItemService
										.getClientUrl(notificationId));

		mail.send();

	}

	public static void sendThankYou(String notificationId) {

		String emailValue = NotificationItemService
				.getNotificationValue(notificationId);
		// count the message
		String userId = NotificationItemService.getLineUserId(notificationId);
		CounterService.addEmail(emailValue, MyKeys.MESSAGE_TYPE_EMAIL, userId);

		// if it is a test then do not send the email;
		if (emailValue.contains(MyKeys.TEST_DOMAIN)) {
			return;
		}
		Sendgrid mail = newSendgrid();
		mail.setTo(emailValue)
				.setFrom("my-smart-line-service@mysmartline.eu")
				.setSubject("Thank you")
				.setText(
						NotificationItemService.getLineName(notificationId)
								+ "\nis thanking you for trusting us and for using our new cloud line management system\n"
								+ "view status at: "
								+ NotificationItemService
										.getClientUrl(notificationId));

		mail.send();

	}

	public static void sendMessageToNewCustomer(String notificationId,
			Long servicePointId) {
		String servicePoint = "";
		if (servicePointId != null) {
			servicePoint = "at "
					+ ServicePointService.getShortName(servicePointId);
		}
		String emailValue = NotificationItemService
				.getNotificationValue(notificationId);
		// count the message
		String userId = NotificationItemService.getLineUserId(notificationId);
		CounterService.addEmail(emailValue, MyKeys.MESSAGE_TYPE_EMAIL, userId);

		// if it is a test then do not send the email;
		if (emailValue.contains(MyKeys.TEST_DOMAIN)) {
			return;
		}
		Sendgrid mail = newSendgrid();
		mail.setTo(emailValue)
				.setFrom("my-smart-line-service@mysmartline.eu")
				.setSubject("It is your turn")
				.setText(
						"It is your turn to be served at "
								+ NotificationItemService
										.getLineName(notificationId)
								+ "\nPlease procede "
								+ servicePoint
								+ "\n"
								+ "view status at: "
								+ NotificationItemService
										.getClientUrl(notificationId));

		mail.send();

	}

	public static void sendUpdateMessage(String notificationId) {

		Integer ahead = NotificationItemService.getClientsAhead(notificationId);

		String emailValue = NotificationItemService
				.getNotificationValue(notificationId);
		// count the message
		String userId = NotificationItemService.getLineUserId(notificationId);
		CounterService.addEmail(emailValue, MyKeys.MESSAGE_TYPE_EMAIL, userId);

		// if it is a test then do not send the email;
		if (emailValue.contains(MyKeys.TEST_DOMAIN)) {
			return;
		}
		Sendgrid mail = newSendgrid();
		mail.setTo(emailValue)
				.setFrom("my-smart-line-service@mysmartline.eu")
				.setSubject(""+ ahead + " clients ahead")
				.setText(
						"There are "
								+ ahead
								+ " clients ahead at "
								+ NotificationItemService
										.getLineName(notificationId)

								+ "\nPlease consider evaluating the time you kneed to reach service area\n "
								+ "view status at: "
								+ NotificationItemService
										.getClientUrl(notificationId));

		mail.send();
	}

	private static Sendgrid newSendgrid() {
		return new Sendgrid(MyKeys.SENDGRID_USERNAME, MyKeys.SENDGRID_PASSWORD);
	}
}
