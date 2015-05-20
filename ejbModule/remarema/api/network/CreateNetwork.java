package remarema.api.network;

import javax.validation.constraints.NotNull;

public class CreateNetwork {
	@NotNull
	private String networkName;

	@NotNull
	private String parentNetworkName;

	public String getNetworkName() {
		return networkName;
	}

	public String getParentNetworkName() {
		return parentNetworkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public void setParentNetworkName(String parentNetworkName) {
		this.parentNetworkName = parentNetworkName;
	}

}
