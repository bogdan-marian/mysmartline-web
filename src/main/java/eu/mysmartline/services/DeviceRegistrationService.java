package eu.mysmartline.services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;

import eu.mysmartline.entities.Device;
import eu.mysmartline.entities.Line;
import eu.mysmartline.models.DeviceRegistrationResponceModel;
import eu.mysmartline.models.GcmActivateDevice;

public class DeviceRegistrationService {
	public static DeviceRegistrationResponceModel registerDevice(String gcmRegId) {

		DeviceRegistrationResponceModel responseModel = new DeviceRegistrationResponceModel();
		responseModel.setValidMessage(true);
		responseModel.setDatabaseError(true);
		responseModel.setRecordedInDatabase(false);
		responseModel.setActive(false);
		responseModel.setUserFrendlyName("not autorized yet");

		if (gcmRegId == null) {
			return responseModel;
		}

		/**
		 * If device not in data store then create it
		 */
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		TypedQuery<Device> query = em.createQuery(
				"select d from Device d where gcmRegId =:vGcmRegId",
				Device.class);
		query.setParameter("vGcmRegId", gcmRegId);
		List<Device> devices = query.getResultList();
		em.getTransaction().rollback();
		Device device;
		if (devices.size() == 0) {

			device = new Device();
			device.setDateCreated(new Date());
			device.setGcmRegId(gcmRegId);
			em.getTransaction().begin();
			em.persist(device);
			em.getTransaction().commit();

			// add long id
			em.getTransaction().begin();
			device = em.find(Device.class, device.getId());
			device.setLongPartId(device.getId().getId());
			device.setShortId(device.getLongPartId().toString());
			em.getTransaction().commit();
			if (em.getTransaction().isActive()) {
				// Response model describes no success
				return responseModel;
			}
		} else {
			device = devices.get(0);
		}
		responseModel.setDatabaseError(false);
		responseModel.setRecordedInDatabase(true);
		responseModel.setShortId(device.getShortId());
		return responseModel;
	}

	public static Device activateByShortId(String shortId) {
		EntityManager em = EmfService.getEntityManager();
		// if device not in dataStore return null
		em.getTransaction().begin();
		TypedQuery<Device> query = em
				.createQuery("select d from Device d where shortId =:vShortId",
						Device.class);
		query.setParameter("vShortId", shortId);
		List<Device> devices = query.getResultList();
		em.getTransaction().rollback();
		if (devices.size() == 0) {
			return null;
		}
		if (devices.get(0).getUserId() != null) {
			return null;
		}
		/**
		 * device is ready to be activated activate the device
		 */
		em.getTransaction().begin();
		Device device = em.find(Device.class, devices.get(0).getId());
		User user = UserServiceFactory.getUserService().getCurrentUser();
		device.setUserId(user.getUserId());
		em.getTransaction().commit();
		/**
		 * prepare the notification message and send the activation message to
		 * the corresponding device
		 */
		GcmActivateDevice activateObject = GcmActivateDevice
				.buildPositiveActivationMessage();
		String message = GcmService.buildGcmMessage(activateObject);
		try {
			MyHttpService.sendGcmMessage(device.getGcmRegId(), message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return device;
	}

	public static Key getKey(Long deviceId) {
		return KeyFactory.createKey(Device.class.getSimpleName(), deviceId);
	}

	public static Device getDevice(Long deviceId) {
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		Key deviceKey = getKey(deviceId);
		Device device = em.find(Device.class, deviceKey);
		em.getTransaction().rollback();
		return device;
	}

	public static Device reneme(Long deviceId, String userFrendlyName) {
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		Key deviceKey = getKey(deviceId);
		Device device = em.find(Device.class, deviceKey);
		if (device == null) {
			em.getTransaction().rollback();
		} else {
			device.setUserFrendlyName(userFrendlyName);
			em.getTransaction().commit();
		}
		return new Device();
	}

	public static List<Device> getDevices() {
		User user = UserServiceFactory.getUserService().getCurrentUser();
		String userId = user.getUserId();
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		TypedQuery<Device> query = em.createQuery(
				"select d from Device d where d.userId =:theUserId",
				Device.class);
		query.setParameter("theUserId", userId);
		List<Device> devices = query.getResultList();
		em.getTransaction().rollback();
		return devices;
	}

	public static Device getDeviceByGcmId(String deviceGcmId) {
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		TypedQuery<Device> query = em.createQuery(
				"select d from Device d where gcmRegId =:deviceGcmId",
				Device.class);
		query.setParameter("deviceGcmId", deviceGcmId);
		List<Device> devices = query.getResultList();
		em.getTransaction().rollback();
		return devices.get(0);
	}

	public static List<Device> getDevicesByUserId(String userId) {
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();

		TypedQuery<Device> query = em.createQuery(
				"select s from Device s where userId = :theUserId",
				Device.class);
		query.setParameter("theUserId", userId);
		List<Device> devices = query.getResultList();
		em.getTransaction().rollback();
		return devices;
	}

	public static List<Device> getDevicesByLineId(Long lineId) {
		/*Line line = LineService.getLine(lineId);
		String userId = line.getUserId();
		return getDevicesByUserId(userId);*/
		throw new IllegalStateException("Please finish this");
	}

	public static boolean resetDevice(String deviceGcmId) {
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		TypedQuery<Device> query = em.createQuery(
				"select d from Device d where gcmRegId =:deviceGcmId",
				Device.class);
		query.setParameter("deviceGcmId", deviceGcmId);
		List<Device> devices = query.getResultList();
		if (devices.size() > 0) {
			Device device = devices.get(0);
			em.remove(device);
		}
		em.getTransaction().commit();
		return true;
	}
	public static void resetDeviceFromServerSide(Long deviceId){
		//only delete user association
		Device device = getDevice(deviceId);
		String deviceGcmId = device.getGcmRegId();
		resetDevice(deviceGcmId);
		MyHttpService.nofityDeviceWasReset(deviceGcmId);
	}
	public static void setUserId(Long deviceId, String userId){
		Key deviceKey = getKey(deviceId);
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		Device device = em.find(Device.class, deviceKey);
		device.setUserId(userId);
		em.getTransaction().commit();
	}
	
}
