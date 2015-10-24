package eu.mysmartline.models;

public class MyKeys {
	/**
	 * this is the root url of the app.
	 * http://mysmartline-web.appspot.com/
	 * http://www.mysmartline.eu/
	 */
	public static final String PROPERTY_HOME = "http://mysmartline.eu/";
	
	/**
	 * bogdan.oloeriu
	 * Informatica1
	 * 
	 * cloudbees_bogdan-oloeriu
	 * zcvfarm3
	 */
	public static  String SENDGRID_USERNAME = getSendGridUserName();
	public static  String SENDGRID_PASSWORD = getSendGridPassword();
	public static  Integer NOTIFY_BEFORE = 4;
	public static  String TEST_DOMAIN = "@testlinetest.eu";
	
	public static final String MESSAGE_TYPE_EMAIL = "email";
	public static final String MESSAGE_TYPE_SMS = "sms";
	
	private static String getSendGridUserName(){
		throw new IllegalStateException("Please implement this");
	}
	private static String getSendGridPassword(){
		throw new IllegalStateException("Please implement this");
	}
}
