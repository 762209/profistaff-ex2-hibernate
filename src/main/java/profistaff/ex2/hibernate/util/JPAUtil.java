package profistaff.ex2.hibernate.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import profistaff.ex2.hibernate.data.StudentRepository;

/**
 * This class used for connecting to the database. Base on singleton pattern
 * @author  Kambachekov Murat
 * @version 1.0
 */
public class JPAUtil {
	/** Persistence unit name */
	private static final String PERSISTENCE_UNIT_NAME = "profistaff.ex2.hibernate";
	/** Entity manager factory */
	private static EntityManagerFactory factory;
	
	/**
	 * If connection with database not existe, create new
	 * @return factory with connection to database
	 */
	public static EntityManagerFactory getEntityManagerFactory() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		return factory;
	}
	/**
	 * Close the factory, releasing any resources that it holds
	 */
	public static void shutdown() {
		if (factory != null) {
			factory.close();
		}
	}
}
