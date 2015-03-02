package remarema.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name="node_has_network")
public class Node_has_Network {
	
	@Column (name="nodeID")
	private int nodeID;
	
	@Column (name="networkID")
	private int networkID;
	
	

}
