import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class OrderWindow {

	private JFrame orderFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	
	//initialising and design of the main menu GUI
	private void designGUI(){
		orderFrame = new JFrame("Order Menu");
		orderFrame.setSize(400, 300);
		orderFrame.setLayout(new GridLayout(3,1));
		
		headerLabel = new JLabel ("",JLabel.CENTER);
		statusLabel = new JLabel ("",JLabel.CENTER);
		statusLabel.setSize(350, 100);
		
		orderFrame.addWindowListener(new WindowAdapter(){
			public void WindowClose(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		orderFrame.add(headerLabel);
		orderFrame.add(controlPanel);
		orderFrame.add(statusLabel);
		orderFrame.setVisible(true);
	}
	
	private void createButtons(){
		headerLabel.setText("NB Garden - Warehouse App");
		JButton orderAdd = new JButton("Add order");
		JButton orderDelete = new JButton("Delete order");
		JButton orderSave = new JButton("Save order");
		JButton orderPrint = new JButton("Print order");
				
		orderAdd.setActionCommand("Add order");
		orderDelete.setActionCommand("Delete order");
		orderSave.setActionCommand("Save order");
		orderPrint.setActionCommand("Print order");
				
		orderAdd.addActionListener(new ButtonClick());
		orderDelete.addActionListener(new ButtonClick());
		orderSave.addActionListener(new ButtonClick());
		orderPrint.addActionListener(new ButtonClick());
		
		controlPanel.add(orderAdd);
		controlPanel.add(orderDelete);
		controlPanel.add(orderSave);
		controlPanel.add(orderPrint);
				
		orderFrame.setVisible(true);		
	}
	
	
	
	private class ButtonClick implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae){
			String command = ae.getActionCommand();
			switch (command){
			case "Add order":
				try {
					AddOrder();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	public void AddOrder() throws IOException{
		WarehouseOrder test = new WarehouseOrder(0, null, null, null);
		test.addOrder();	
	}

	public OrderWindow() {
		designGUI();
		createButtons();
	}
}
