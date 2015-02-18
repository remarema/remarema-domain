package remarema.domain.network;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "networks")
public class Network {
	
	
	@Id
	@GeneratedValue
	@Column(name="networkID")
	private int networkID;
	
	@Column(name="networkName")
	private String networkName;
	
	//@Column (name="networkIP")
	private String networkIP;
	
	
	public Network(){
	}
	
	public Network(int networkID){
		this.networkID = networkID;
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public String getNetworkIP() {
		return networkIP;
	}

	public void setNetworkIP(String networkIP) {
		this.networkIP = networkIP;
	}
}
