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
	private productPorous productPorous;
	
	
	public enum productPorous{
		REQUIRED, APPLIED, INESSENTIAL;
	}
		
	public productPorous getPorous(){
		return productPorous;
	}
	
	public void setPorous(productPorous pPorous){
		this.productPorous = pPorous;
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
	
	//constructor for product objects
	public WarehouseProduct(String productName, double productPrice, int stockLevel, double productHeight, double productWidth, double productWeight, double productDepth){
		this.productName=productName;
		this.productPrice = productPrice;
		this.stockLevel = stockLevel;
		this.productHeight= productHeight;
		this.productWidth = productWidth;
		this.productWeight = productWeight;
		this.productDepth= productDepth;
		//this.productPorous = productPorous;
	}
	
	public String toString(){
		return "Product Name: " + this.getName() + ", Product Price: £" + this.getPrice();
	}
	
	//object test - making an object with the use of constructor above
	public static void main (String [] args){
		ArrayList<WarehouseProduct> productList= new ArrayList<WarehouseProduct>();
		WarehouseProduct prod1 = new WarehouseProduct("Garden Gnome",17.99,5,25.3,6.5,70,4);
		productList.add(prod1);
		WarehouseProduct prod2 = new WarehouseProduct("That other Gnome",17.99,5,25.3,6.5,70,4);
		productList.add(prod2);
		
		System.out.println(productList);
	}
	
}
