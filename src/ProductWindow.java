import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.util.ArrayList;


public class ProductWindow extends JFrame{
	private WarehouseProduct products;
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JPanel controlPanel;
	private JTextArea mainText;
	
	
	private void designGUI(){
		
		mainFrame = new JFrame("Main Menu");
		mainFrame.setSize(500, 300);
		mainFrame.setLayout(new GridLayout(3,1));
				
		headerLabel = new JLabel ("",JLabel.CENTER);
		mainText = new JTextArea();
		mainText.setAlignmentX(RIGHT_ALIGNMENT);
		
				
		mainFrame.addWindowListener(new WindowAdapter(){
			public void WindowClose(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(mainText);
		mainFrame.setVisible(true);

	}
	
	private void createButtons(){
		headerLabel.setText("NB Garden - Warehouse App");
		JButton productAdd = new JButton("Add Product");
		JButton productDelete = new JButton("Delete Product");
		JButton productSave = new JButton("Save Product");
		JButton productPrint = new JButton("Print Product");
				
		productAdd.setActionCommand("Add Product");
		productDelete.setActionCommand("Delete Product");
		productSave.setActionCommand("Save Product");
		productPrint.setActionCommand("Print Product");
				
		productAdd.addActionListener(new ButtonClick());
		productDelete.addActionListener(new ButtonClick());
		productSave.addActionListener(new ButtonClick());
		productPrint.addActionListener(new ButtonClick());
		
		controlPanel.add(productAdd);
		controlPanel.add(productDelete);
		controlPanel.add(productSave);
		controlPanel.add(productPrint);
		
		productAdd.setAlignmentX(LEFT_ALIGNMENT);
		productDelete.setAlignmentX(LEFT_ALIGNMENT);
		productSave.setAlignmentX(LEFT_ALIGNMENT);
		productPrint.setAlignmentX(LEFT_ALIGNMENT);
		
		mainFrame.setVisible(true);		
	}
	
	private class ButtonClick implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae){
			String command = ae.getActionCommand();
			
			switch (command){
				
				
				case "Add Product": 
					break;
					
				
				case "Delete Product":
					break;
					
				
				case "Save Product": 
					
					break;
					
				case "Print Product":
					
					break;
			}
		}

	}
		
	public ProductWindow() {
		designGUI();
		createButtons();
	}
}