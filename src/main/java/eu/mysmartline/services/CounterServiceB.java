package eu.mysmartline.services;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * this class is using the low level DataStore API
 * count is not appropriate fore JPA unleash you have lest then 1000 entities 
 * to count
 * 
 * @author bogdan
 *
 */
public class CounterServiceB {
	private static UserService us = UserServiceFactory.getUserService();

	public static Long getTotalEmails() {
		String userId = us.getCurrentUser().getUserId();

		DatastoreService datastoreService = DatastoreServiceFactory
				.getDatastoreService();

		Filter filter = new FilterPredicate("userId", FilterOperator.EQUAL,
				userId);
		Query qry = new Query("CounterSpace").setFilter(filter);

		int totalCount = datastoreService.prepare(qry).countEntities(
				FetchOptions.Builder.withDefaults());

		return new Long(totalCount);
	}
	public static long getCountAllUsers(){
		DatastoreService datastoreService = DatastoreServiceFactory
				.getDatastoreService();
		Query query = new Query("SettingsUser");
		return datastoreService.prepare(query).countEntities(
				FetchOptions.Builder.withDefaults());
	}
}
