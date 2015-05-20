package remarema.domain;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import remarema.api.network.NodeDetail;
import remarema.services.network.NodeServiceBean;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NodeTest {
	EntityManager entityManager = JPASetup.getDefault().createEntityManager();
	NodeServiceBean serviceNode = new NodeServiceBean(entityManager);
	Node n;

	@Test
	public void a_erstelleNode() {
		entityManager.getTransaction().begin();
		n = serviceNode.createNode(new NodeDetail());
		n.setNodeName("Client001");
		n.setNodeIP("192.168.1.1");
		entityManager.getTransaction().commit();
		System.out.println("Persisted " + n);
	}

	@Test
	public void b_erstelleNode2() {
		entityManager.getTransaction().begin();
		n = serviceNode.createNode(new NodeDetail());
		n.setNodeName("Client002");
		n.setNodeIP("10.0.0.1");
		n.setNodeNetwork(null);
		entityManager.getTransaction().commit();
		System.out.println("Persisted " + n);
	}

	@Test
	public void c_test() {
		entityManager.find(Node.class, 1);
	}

	@Test
	public void d_findNode() {
		// find a specific node
		NodeDetail nd = new NodeDetail();
		nd.setNodeID(1);
		n = serviceNode.findNode(nd);
		System.out.println("Found" + n);
	}


	@Test
	public void e_findNodes() {
		// find all nodes
		List<Node> nodes = serviceNode.findAllNodes();
		for (Node node : nodes) {
			System.out.println("Found Node: " + node);
		}
	}

	@Test
	public void f_Nodes_Array_ausgeben() {
		int AnzahlNodes = serviceNode.findAnzahlNodes();
		String[][][] array = new String[AnzahlNodes][AnzahlNodes][AnzahlNodes];
		array = serviceNode.nodesArray();

		for (int i = 1; i < AnzahlNodes; i++) {
			System.out.println("Ausgabe Node: " + array[i][0][0] + ", "
					+ array[i][i][0] + ", " + array[i][i][i]);
		}
	}
	
	 @Test 
	 public void g_loesche_Node(){
		 entityManager.getTransaction().begin(); 
		 NodeDetail nd = new NodeDetail();
		 nd.setNodeID(51);
		 serviceNode.removeNode(nd); 
		 entityManager.getTransaction().commit();
		 System.out.println("Removed Node 51"); 
	 }
	 

	@Test
	public void g_nodes_liste_ausgeben() {
		f_Nodes_Array_ausgeben();
	}


	
	@Test
	public void i_suche_node_per_id(){
		NodeDetail nd = new NodeDetail();
		nd.setNodeID(52);
		
		serviceNode.getNodeDetailForNodeID(nd);
		System.out.println(nd);
	}
	
	
	
	
	

	

}
