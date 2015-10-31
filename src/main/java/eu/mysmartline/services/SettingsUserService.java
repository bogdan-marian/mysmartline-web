package eu.mysmartline.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import eu.mysmartline.entities.SettingsUser;

public class SettingsUserService {
	public static void createUser() {
		UserService userService = UserServiceFactory.getUserService();
		if (userService.isUserLoggedIn()) {
			String userId = userService.getCurrentUser().getUserId();
			System.out.println("Debug: ======================= userId = " + userId);
			if (!userInDataStore(userId)) {
				// add the user
				EntityManager em = EmfService.getEntityManager();
				em.getTransaction().begin();
				SettingsUser user = new SettingsUser();
				user.setUserId(userId);
				user.setUserEmail(userService.getCurrentUser().getEmail());
				try {
					em.persist(user);
					em.getTransaction().commit();
				} finally {
					if (em.getTransaction().isActive()) {
						em.getTransaction().rollback();
					}
				}
			}
		}
	}
	
	private static boolean userInDataStore(String userId) {
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		TypedQuery<SettingsUser> query = em.createQuery(
				"select s from SettingsUser s where userId = :theUserId",
				SettingsUser.class);
		query.setParameter("theUserId", userId);
		List<SettingsUser> users = query.getResultList();
		em.getTransaction().rollback();
		if (users.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * return the user by email and null if no user with this email
	 * @param email is the email used to find the user
	 * @return the user that has the associated email or null
	 */
	public static SettingsUser getByEmail(String email){
		EntityManager em = EmfService.getEntityManager();
		em.getTransaction().begin();
		TypedQuery<SettingsUser> query = em.createQuery(
				"select s from SettingsUser s where s.userEmail=:vEmail",
				SettingsUser.class);
		query.setParameter("vEmail", email);
		List<SettingsUser> settings = query.getResultList();
		em.getTransaction().rollback();
		if (settings.size()>0){
			return settings.get(0);
		}
		else{
			return null;
		}
		
	}
}
