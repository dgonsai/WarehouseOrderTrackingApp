
public class PuchaseOrder {
	private int purchaseOrderID;
	private String supplierName;
	
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
	

	
	public PuchaseOrder(int PurchaseOrderID, String supplierName, int quantity){
		this.purchaseOrderID = PurchaseOrderID;
		this.supplierName = supplierName;
	
	}
}
