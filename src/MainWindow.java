import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class MainWindow extends JFrame {
	private WarehouseProduct products;
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private JLabel mainText;
	
	//initialising and design of the main menu GUI
	private void designGUI(){
		mainFrame = new JFrame("Main Menu");
		mainFrame.setSize(400, 300);
		mainFrame.setLayout(new GridLayout(3,1));
		
		headerLabel = new JLabel ("",JLabel.CENTER);
		statusLabel = new JLabel ("",JLabel.CENTER);
		statusLabel.setSize(350, 100);
		
		mainFrame.addWindowListener(new WindowAdapter(){
			public void WindowClose(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);

	}
	
	private void createButtons(){
		headerLabel.setText("NB Garden - Warehouse App");
		JButton productButton = new JButton("Product Menu");
		JButton orderButton = new JButton("Order Menu");
		JButton empButton = new JButton("Employee Menu");
		
		productButton.setActionCommand("Product Menu");
		orderButton.setActionCommand("Order Menu");
		empButton.setActionCommand("Employee Menu");
		
		
		productButton.addActionListener(new ButtonClick());
		orderButton.addActionListener(new ButtonClick());
		empButton.addActionListener(new ButtonClick());
		
		
		controlPanel.add(productButton);
		controlPanel.add(orderButton);
		controlPanel.add(empButton);
		mainFrame.setVisible(true);
	}
	
	private class ButtonClick implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae){
			String command = ae.getActionCommand();
			switch (command){
				case "Product Menu": 
					System.out.println("Product List");
					break;
				
				case "Order Menu": 
					OrderList();
					
					break;
				
				case "Employee List": 
					System.out.println("Employee List");
					break;
					
				case "Search Order":
			}
		}
	}
	
	public void OrderList(){
		WarehouseOrder test = new WarehouseOrder(0, null, null, null);
		test.OrderList();
	}
	
	public MainWindow() {
		designGUI();
		createButtons();
	}

}
