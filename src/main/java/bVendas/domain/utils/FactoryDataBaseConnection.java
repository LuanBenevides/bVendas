package bVendas.domain.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryDataBaseConnection {

	private static EntityManagerFactory factory;
	
	static {
		if(factory == null) {
			factory = Persistence.createEntityManagerFactory("bVendas");
		}
	}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}
