package eu.mysmartline.services;

import eu.mysmartline.models.LineDetailsModel;

public class MyDebugService {
	public static LineDetailsModel buildLine(String lineName){
		LineDetailsModel lineDetails = new LineDetailsModel();
		lineDetails.setName(lineName);
		return lineDetails;
	}
}
