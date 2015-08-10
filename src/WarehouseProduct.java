import java.util.ArrayList;

public class WarehouseProduct {
	private int productID;
	private String productName;
	private double productPrice;
	private int stockLevel;
	private double productHeight;
	private double productWidth;
	private double productWeight;
	private double productDepth;
	private boolean productPorous;
	private int xLoc;
	private int yLoc;
	ArrayList<WarehouseProduct> productList= new ArrayList<WarehouseProduct>();
		
	public boolean getPorous(){
		return productPorous;
	}
	
	public void setPorous(boolean pPorous){
		this.productPorous = pPorous;
	}
	
	public int getID(){
		return productID;
	}
	
	public void setID(int pID){
		productID=pID;
	}
	
	public String getName(){
		return productName;
	}
	
	public void setName(String pName){
		productName=pName;
	}
	
	public double getPrice(){
		return productPrice;
	}
	
	public void setPrice(double pPrice){
		productPrice=pPrice;
	}
	
	public int getStock(){
		return stockLevel;
	}
	
	public void setStock(int pStock){
		stockLevel=pStock;
	}
	
	public double getHeight(){
		return productHeight;
	}
	
	public void setHeight(double pHeight){
		productHeight=pHeight;
	}
	
	public double getWidth(){
		return productWidth;
	}
	
	public void setWidth(double pWidth){
		productWidth=pWidth;
	}
	
	public double getWeight(){
		return productWeight;
	}
	
	public void setWeight(double pWeight){
		productWeight=pWeight;
	}
	
	public double getDepth(){
		return productDepth;
	}
	
	public void setDepth(double pDepth){
		productDepth=pDepth;
	}
	
	public int getX(){
		return xLoc;
	}
	
	public void setX(int pXLocation){
		xLoc = pXLocation;
	}
	
	//constructor for product objects
	public WarehouseProduct(int productID, String productName, double productPrice, int stockLevel, double productHeight, double productWidth, double productWeight, double productDepth, boolean productPorous, int xLoc, int yLoc){
		this.productID=productID;
		this.productName=productName;
		this.productPrice = productPrice;
		this.stockLevel = stockLevel;
		this.productHeight= productHeight;
		this.productWidth = productWidth;
		this.productWeight = productWeight;
		this.productDepth= productDepth;
		this.productPorous = productPorous;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
	}
	
	public String toString(){
		return "Product ID: " + this.getID()+ ", Product Name: " + this.getName() + ", Product Price: £" + this.getPrice()+ ", Stock Level: " + this.getStock() + ", Porous Ware?: " + this.getPorous() +"\n";
	}
	

	public void ProductList(){
		JDBC jdbc = new JDBC();
		jdbc.readProducts();
		
		
		System.out.println(productList);
	}
	
}
