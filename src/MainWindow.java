import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;



public class MainWindow extends JFrame {
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JPanel controlPanel;
	private JList list;
	
		
	//initialising and design of the main menu GUI
	private void designGUI(){
		mainFrame = new JFrame("Main Menu");
		mainFrame.setSize(500, 400);
		mainFrame.setLayout(new GridLayout(3,1));
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		headerLabel = new JLabel ("",JLabel.CENTER);
		list = new JList<String>(listModel);
		add (new JScrollPane(list));
		list.setVisibleRowCount(4);
				
		mainFrame.addWindowListener(new WindowAdapter(){
			public void WindowClose(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		controlPanel.add(list);
		mainFrame.add(list);
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);

	}
	
	private void createButtons(){
		headerLabel.setText("NB Garden - Warehouse App");
		JButton poListButton = new JButton("Purchase Order Menu");
		JButton orderMenuButton = new JButton("Order Menu");
		JButton orderListButton = new JButton("Order List");
		JButton productListButton = new JButton("Product List");
		JButton addPOButton = new JButton("Add New Purchase Order");
		JButton editPOButton = new JButton("Add items to existing Purchase Order");
		JButton changeStatus = new JButton("Change Status");
				
		poListButton.setActionCommand("Purchase Order List");
		orderMenuButton.setActionCommand("View Items in order");
		orderListButton.setActionCommand("Order List");
		productListButton.setActionCommand("Product List");
		addPOButton.setActionCommand("Add New Purchase Order");
		editPOButton.setActionCommand("Edit Purchase Order");
		changeStatus.setActionCommand("Change Order Status");
				
		poListButton.addActionListener(new ButtonClick());
		orderMenuButton.addActionListener(new ButtonClick());
		orderListButton.addActionListener(new ButtonClick());
		productListButton.addActionListener(new ButtonClick());
		addPOButton.addActionListener(new ButtonClick());
		editPOButton.addActionListener(new ButtonClick());
		changeStatus.addActionListener(new ButtonClick());
		
		controlPanel.add(poListButton);
		controlPanel.add(orderMenuButton);
		controlPanel.add(orderListButton);
		controlPanel.add(productListButton);
		controlPanel.add(addPOButton);
		controlPanel.add(editPOButton);
		controlPanel.add(changeStatus);
		
		poListButton.setAlignmentX(LEFT_ALIGNMENT);
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
				case "Purchase Order List":
					DisplayPurchaseOrderList();
					break;
					
				//order menu button clicked
				case "View Items in order":
					DisplayOrderLine();
					break;
					
				//order list button clicked
				case "Order List": 
					DisplayOrderList();
					break;
					
				case "Product List":
					DisplayProductList();
					break;
				
				case "Add New Purchase Order":
					addPurchaseOrder();
					break;
					
				case "Edit Purchase Order":
					addPOProducts();
					break;
					
				case "Change order status":
					break;
			}
		}
	}
	
	public void DisplayOrderList(){
		JDBC jdbc = new JDBC();
		jdbc.readOrders();
		
		DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();	
		listModel.clear();
		
		for (String orderString: jdbc.readOrders()){
			listModel.addElement(orderString);
		}
		
		list.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
				list = (JList)event.getSource();
				if (event.getClickCount()==2){
					int index = list.locationToIndex(event.getPoint());
					new OrderWindow(){};
					
					Object item = listModel.getElementAt(index);;
					list.ensureIndexIsVisible(index);
					System.out.println("Double clicked on " + item);
				}
			}	
		});
		
	}
	
	public void DisplayProductList(){
		JDBC jdbc = new JDBC();
		jdbc.readProducts();
		
		DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();	
		listModel.clear();
		
		for (String productString: jdbc.readProducts()){
			listModel.addElement(productString);
		}
		list.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
				list = (JList)event.getSource();
				if (event.getClickCount()==2){
					new ProductWindow(){};
					
				}
			}	
		});		
	}
	
	public void DisplayOrderLine(){
		JDBC jdbc = new JDBC();
		
		jdbc.readOrderLine();
		
		DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();	
		listModel.clear();
		for (String productString: jdbc.readOrderLine()){
			listModel.addElement(productString);
		}		
	}
	
	public void DisplayPurchaseOrderList(){
		JDBC jdbc = new JDBC();
		
		jdbc.readPurchaseOrder();
		
		DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();	
		listModel.clear();
		for (String productString: jdbc.readPurchaseOrder()){
			listModel.addElement(productString);
		}		
	}
	public void addPurchaseOrder(){
		JDBC jdbc = new JDBC();
		jdbc.addPurchaseOrder();
		
		String option = JOptionPane.showInputDialog("Would you like to add products to a purchase order? (y/n)");
		System.out.println(option);
		if (option.equals("y")){
			System.out.println(option);
			addPOProducts();
		}
		else{
			return;
		}
	}
	
	public void addPOProducts(){
		JDBC jdbc = new JDBC();
		jdbc.addToPO();
	}
	
	public MainWindow() {
		designGUI();
		createButtons();
	}

}
