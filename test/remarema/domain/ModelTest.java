package remarema.domain;

import javax.persistence.EntityManager;

import org.junit.Test;

import remarema.domain.node.Node;

public class ModelTest {

	@Test
	public void test() {
		EntityManager entityManager = JPASetup.getDefault().createEntityManager();
		entityManager.find(Node.class, 1);
	}

}
