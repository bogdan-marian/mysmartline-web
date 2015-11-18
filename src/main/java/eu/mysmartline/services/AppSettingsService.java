package eu.mysmartline.services;

import javax.persistence.EntityManager;

import eu.mysmartline.entities.AppSettings;
import eu.mysmartline.models.MyKeys;

public class AppSettingsService {
	private static AppSettings sendGridUser;
	private static AppSettings sendGridPassword;

	public static AppSettings getSendgridUsername() {
		if (AppSettingsService.sendGridUser != null) {
			return AppSettingsService.sendGridUser;
		}
		AppSettingsService.sendGridUser = getAppSettingsByKey(MyKeys.SENDGRID_USERNAME);
		return AppSettingsService.sendGridUser;
	}

	public static AppSettings getSendgridPassword() {
		if (AppSettingsService.sendGridPassword != null){
			return AppSettingsService.sendGridPassword;
		}
		AppSettingsService.sendGridPassword = getAppSettingsByKey(MyKeys.SENDGRID_PASSWORD);
		return AppSettingsService.sendGridPassword;
	}

	/**
	 * It looks in the dataStore and retrieves the AppSettings by keyId if the
	 * value does not exist it will look in the runtime variable store it in
	 * dataStore and then will return the entity.
	 * 
	 * @param keyId
	 * @return
	 */
	private static AppSettings getAppSettingsByKey(String keyId) {
//		AppSettings debugSetting = new AppSettings();
//		debugSetting.setId("debug-setting");
//		debugSetting.setValue("debug-value");
		
		System.out.println("Debug: getAppSettingsByKey: " + keyId);
		//Key key = KeyFactory.stringToKey(keyId);
		String key = keyId;
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		AppSettings appSettings = em.find(AppSettings.class, key);
		em.getTransaction().rollback();
		if (appSettings != null) {
			System.out.println("Debug: Key found in datastore: ");
			System.out.println("Debug: old datastore keyId: "+ appSettings.getId() );
			System.out.println("Debug: old datastore keyValue: "+ appSettings.getValue());
			return appSettings;
		}
		
		//populate datastore
		String value = System.getenv(keyId);

		appSettings = new AppSettings();
		appSettings.setId(keyId);
		appSettings.setValue(value);

		em.getTransaction().begin();
		em.persist(appSettings);
		em.getTransaction().commit();
		System.out.println("Debug: key is ok: " + keyId + " " + value);
		return appSettings;
		
//		System.out.println("Debug: keyId: " + debugSetting.getId());
//		System.out.println("Debug: keyValue: " + debugSetting.getValue());
//		return debugSetting;
	}
}
