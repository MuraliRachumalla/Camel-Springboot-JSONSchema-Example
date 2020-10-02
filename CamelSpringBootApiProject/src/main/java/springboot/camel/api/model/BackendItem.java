package springboot.camel.api.model;

public class BackendItem {

	private int backendId;
	private String backendName;
	private double backendPrice;
	
	
	
	public BackendItem(int backendId, String backendName, double backendPrice) {
		super();
		this.backendId = backendId;
		this.backendName = backendName;
		this.backendPrice = backendPrice;
	}
	
	public int getBackendId() {
		return backendId;
	}
	public void setBackendId(int backendId) {
		this.backendId = backendId;
	}
	public String getBackendName() {
		return backendName;
	}
	public void setBackendName(String backendName) {
		this.backendName = backendName;
	}
	public double getBackendPrice() {
		return backendPrice;
	}
	public void setBackendPrice(double backendPrice) {
		this.backendPrice = backendPrice;
	}
	
}
