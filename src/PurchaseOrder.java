
public class PurchaseOrder {
	private int purchaseOrderID;
	private String supplierName;
	private String delivered;

	
	public int getPurchaseID(){
		return purchaseOrderID;
	}
	
	public void setPurchaseID(int purchaseID){
		purchaseOrderID = purchaseID;
	}
	
	public String getSupplier(){
		return supplierName;
	}
	
	public void setSupplier(String supplier){
		supplierName = supplier;
	}
	
	public String getDelivery(){
		return delivered;
	}
	
	public void setDelivery(String del){
		delivered = del;
	}
	
	public PurchaseOrder(int PurchaseOrderID, String supplierName, String delivered){
		this.purchaseOrderID = PurchaseOrderID;
		this.supplierName = supplierName;
		this.delivered = delivered;
	}
}
