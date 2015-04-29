package remarema.services.network;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import remarema.api.NodeDetail;
import remarema.domain.Node;

public class NodeServiceBeanTest {
	

	private NodeServiceBean serviceBean;
	
	@Before
	public void setup(){
		final Node node = new Node();

		node.setNodeName("node");
		
		serviceBean = new NodeServiceBean(){
			@Override
			Node loadNode(int nodeID) {
				return node;
			}
		};
		
	}
	@Test
	public void test() {
		final Node node = new Node();
		node.setNodeName("node");
		NodeDetail result = serviceBean.mapNodeToNodeDetail(node);
		Assert.assertEquals("node", result.getNodeName());
	}
	

}
