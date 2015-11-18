package eu.mysmartline.services;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class AppTools {
	public static Key getKey(String id) {
		return KeyFactory.stringToKey(id);
	}
}
