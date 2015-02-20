package remarema.domain;

import java.util.*;

import javax.persistence.*;

public class JPASetup {
	private static final JPASetup DEFAULT_CONTAINER = new JPASetup();
	private static final String DATABASE_URL = "jdbc:h2:mem:remarema;MODE=MYSQL;DB_CLOSE_DELAY=-1";
	private static final String DRIVER_NAME = "org.h2.Driver";

	private EntityManagerFactory emf;

	public static final JPASetup getDefault() {
		return DEFAULT_CONTAINER;
	}

	private JPASetup() {
		emf = initEMF();
	}

	public EntityManager createEntityManager() {
		return emf.createEntityManager();
	}

	static EntityManagerFactory initEMF() {

		Map<String, String> properties = new HashMap<String, String>();
		// properties.put(TRANSACTION_TYPE,
		// PersistenceUnitTransactionType.RESOURCE_LOCAL.name());

		properties.put("javax.persistence.jdbc.url", DATABASE_URL);
		properties.put("javax.persistence.jdbc.user", "sa");
		properties.put("javax.persistence.jdbc.driver", DRIVER_NAME);

		properties.put("openjpa.TransactionMode", "local");
		properties.put("openjpa.RuntimeUnenhancedClasses", "supported");
		properties.put("openjpa.ConnectionFactoryMode", "local");

		properties.put("openjpa.Log", "SQL=TRACE");
		properties.put("openjpa.jdbc.DBDictionary", "mysql");

		properties.put("openjpa.jdbc.SynchronizeMappings",
				"buildSchema(SchemaAction='createDB,add',ForeignKeys=true)");

		return Persistence.createEntityManagerFactory("openjpa", properties);
	}

}
