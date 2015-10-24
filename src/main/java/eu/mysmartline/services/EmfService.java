package eu.mysmartline.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EmfService {
	private static final EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("mysmart-provider");
	
	
	private EmfService(){}
	
	public static EntityManager getEntityManager(){
		return emf.createEntityManager();
	}
}
