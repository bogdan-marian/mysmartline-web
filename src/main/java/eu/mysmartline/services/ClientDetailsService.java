package eu.mysmartline.services;

import eu.mysmartline.models.ClientDetailsModel;

public class ClientDetailsService {
	public static ClientDetailsModel getForNewClientByLineId(String lineId){
		/**
		 * The ClientDetailsModel is used to collect the information
		 * that will be presented to the next potential client.
		 */
		ClientDetailsModel clientDetails = new ClientDetailsModel();
		/*clientDetails.setLineId(lineId);
		clientDetails.setEmail(MySecurity.getCurrentUserEmail());
		clientDetails.setLineName(LineService.getName(lineId));
		clientDetails.setCurrentNumber(LineService.getPrintedCurrentNumber(lineId));
		clientDetails.setNextProbableNumber(LineService.getPrintedNextProbableNumber(lineId));
		clientDetails.setClientsAhead(LineService.getNumberOfClientsWaiting(lineId));
		clientDetails.setProbableWaitTime(LineService.getProbableWaitMinutes(lineId));*/
		
		//return clientDetails;
		throw new IllegalStateException("Please finish this");
	}
}
