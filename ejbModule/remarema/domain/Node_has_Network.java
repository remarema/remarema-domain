package remarema.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name="node_has_network")
public class Node_has_Network {
	
	@Column (name="nodes_nodeID")
	private int nodes_nodeID;
	
	@Column (name="networks_networkID")
	private int networks_networkID;
	
	

}
