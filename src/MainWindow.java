import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;

//import WarehouseOrder.orderStatus;




import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class MainWindow extends JFrame {
	private WarehouseProduct products;
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private JLabel mainText;
	private JList list;
	
	//initialising and design of the main menu GUI
	private void designGUI(){
		mainFrame = new JFrame("Main Menu");
		mainFrame.setSize(500, 300);
		mainFrame.setLayout(new GridLayout(3,1));
		
		
		headerLabel = new JLabel ("",JLabel.CENTER);
		list = new JList();
		list.setAlignmentX(RIGHT_ALIGNMENT);
				
		mainFrame.addWindowListener(new WindowAdapter(){
			public void WindowClose(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(list);
		mainFrame.setVisible(true);

	}
	
	private void createButtons(){
		headerLabel.setText("NB Garden - Warehouse App");
		JButton productButton = new JButton("Product Menu");
		JButton orderMenuButton = new JButton("Order Menu");
		JButton orderListButton = new JButton("Order List");
		JButton productListButton = new JButton("Product List");
		
		
		productButton.setActionCommand("Product Menu");
		orderMenuButton.setActionCommand("Order Menu");
		orderListButton.setActionCommand("Order List");
		productListButton.setActionCommand("Product List");
		
		
		productButton.addActionListener(new ButtonClick());
		orderMenuButton.addActionListener(new ButtonClick());
		orderListButton.addActionListener(new ButtonClick());
		productListButton.addActionListener(new ButtonClick());
		
		controlPanel.add(productButton);
		controlPanel.add(orderMenuButton);
		controlPanel.add(orderListButton);
		controlPanel.add(productListButton);
		
		productButton.setAlignmentX(LEFT_ALIGNMENT);
		orderMenuButton.setAlignmentX(LEFT_ALIGNMENT);
		orderListButton.setAlignmentX(LEFT_ALIGNMENT);
		productListButton.setAlignmentX(LEFT_ALIGNMENT);
		
		mainFrame.setVisible(true);
			
	}
	
	private class ButtonClick implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae){
			String command = ae.getActionCommand();
			switch (command){
				
				//product menu button clicked
				case "Product Menu":
					new ProductWindow(){};
					break;
					
				//order menu button clicked
				case "Order Menu":
					new OrderWindow(){};
					break;
					
				//order list button clicked
				case "Order List": 
					DisplayOrderList();
					break;
					
				case "Product List":
					DisplayProductList();
					break;
			}
		}
	}
	
	
	
	public void DisplayOrderList(){
		JDBC jdbc = new JDBC();
		jdbc.readOrders();
		
	}
	
	public void DisplayProductList(){
		//display the products from the array
		//return the array from the warehouseProduct class
		//WarehouseProduct test = new WarehouseProduct(1,"Garden Gnome",17.99,5,25.3,6.5,70,4,WarehouseProduct.productPorous.APPLIED);
		//test.ProductList();
		JDBC jdbc = new JDBC();
		jdbc.readProducts();
		
	}
		
	public MainWindow() {
		designGUI();
		createButtons();
	}

}
