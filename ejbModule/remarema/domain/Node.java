package remarema.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="nodes")
public class Node implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	
	private String nodeName;
	private String nodeCredential;
	
	public Node(){
		
	}
	public Node(int id){
		this.id = id;
	}
	
	public int getID(){
		return id;
	}
	public void setID(int id){
		this.id = id;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeCredential() {
		return nodeCredential;
	}
	public void setNodeCredential(String nodeCredential) {
		this.nodeCredential = nodeCredential;
	}
}
