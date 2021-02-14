package logistex;
	import java.awt.Color;
	import java.awt.Insets;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.FocusEvent;
	import java.awt.event.FocusListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
	import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JTextField;

import com.mysql.jdbc.StringUtils;

public class add_new_product extends JFrame {

		private JButton help,enter_product,cancel;
		private boolean check=false;
		private JCheckBox high_demand = new JCheckBox();
		private JTextField name,ID,quantity ,price,VE;;//    ID?
		private JLabel name_lable,ID_lable , quantity_lable , price_lable , VE_lable,error1,done,txt_high_demand; // ID?
		public add_new_product(String s,String s1) {
			//Setup
				setTitle("Logistix");
				setLayout (null);
				setVisible(true);
				setSize(450,300);
				setLocationRelativeTo(null);
			
				
				add(high_demand);
				high_demand.setBounds(150,65,30,30);
				
				//Error
				error1 = new JLabel("Fields are invaled");
				error1.setBounds(250,100,140,30);
				error1.setVisible(false);
				error1.setForeground(Color.red);
				add(error1);
				
				//Done
				done = new JLabel("Product has been added");
				done.setBounds(250,100,140,30);
				done.setVisible(false);
				add(done);
				
				//Text fields
				name	 		 = new JTextField (s, 30 );
				ID 	 	 		 = new JTextField ("ID", 30 );
				quantity	 	 = new JTextField (s1 , 30 );
				price   		 = new JTextField ("Price", 30 );
				VE				 = new JTextField ("VE", 30 );
				
				//Text field bounds
				
				quantity.setEditable(false);
				name.setBounds   (150,20,220,30);
				quantity.setBounds(150,95,50,30);
				price.setBounds	  (150,130,50,30);
				VE.setBounds	  (150,165,50,30);
				
				
				//add(text1);
				add(name);
				add(quantity);
				add(price);
				add(VE);						
				//add(ID);							ID?
				
				//Edit status
				name.setEditable	(false);
				 
				//Button setup
				cancel = new JButton("Cancel");
				enter_product = new JButton("Enter");
				help  = new JButton("?");
				add (cancel);
				add (help);
				add (enter_product);
				
				//Button Margin
				cancel.setMargin(new Insets(0,0,0,0));
				enter_product.setMargin(new Insets(0,0,0,0));
				help.setMargin (new Insets(0,0,0,0));
				
				//Button bounds
				enter_product.setBounds(320,155,100,40);
				cancel.setBounds(175,215,100,40);
				help.setBounds(415,0,20,20);
				//Labels setup
				name_lable	   = new JLabel("Product name : ");
				quantity_lable = new JLabel("Enter quantity : ");
				price_lable	   = new JLabel("Enter price : ");
				VE_lable	   = new JLabel("Enter VE : ");
				txt_high_demand  = new JLabel(" High demand :");
				
				add(txt_high_demand);
				add(name_lable);
				add(quantity_lable);
				add(price_lable);
				add(VE_lable);
				
				//Label bounds
				txt_high_demand.setBounds(42,65,100,30);
				name_lable.setBounds(40,20,100,30);
				quantity_lable.setBounds(40,95,100,30);
				price_lable.setBounds(55,130,100,30);
				VE_lable.setBounds(68,165,100,30);

				//Text feilds actions
				fl f = new fl();
				VE.addFocusListener(f);
				quantity.addFocusListener(f);
				price.addFocusListener(f);
		
				//buttons action
				ev e = new ev();
				cancel.addActionListener(e);
				enter_product.addActionListener(e);
				help.addActionListener(e);
				
		
		}

		public class fl implements FocusListener{
			
			@Override
			public void focusGained(FocusEvent e) {

				if(e.getSource()==VE)
					VE.setText("");
				else if (e.getSource()== price)
					price.setText("");
			}

			@Override
				public void focusLost(FocusEvent e) {
			 
			}
		}


		public class ev implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				if (e.getSource()== cancel)
					dispose();
				
				else if(e.getSource() == help)
					 JOptionPane.showMessageDialog
					 (null, "Enter the price and the VE of the product\n"
					 		+ "And check the check box if the product on high demand\n"
					 		+"Press \"cancel to get back\n" 
					 		+ " \"- only enter numbers in the fields");
				
				else if(e.getSource() == enter_product) {
					check =true;
					if(price.getText().contentEquals("")||price.getText().contentEquals("Price")) 
					{	error1.setVisible(true);
						price.setBorder(BorderFactory.createLineBorder(Color.red, 1));
						check=false;
					}
					else {		
						price.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
					}

					
				    if(VE.getText().contentEquals("")||VE.getText().contentEquals("VE"))
					{		error1.setVisible(true);
							VE.setBorder(BorderFactory.createLineBorder(Color.red, 1));
							check=false;
					}
				    else
				    {VE.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
				    }
				    if(!StringUtils.isStrictlyNumeric(price.getText()))
				 	 {
				 		 error1.setVisible(true);
				 		 price.setBorder(BorderFactory.createLineBorder(Color.red, 1));
				 		 check=false;
				 	 }
					 else
					 {
						 error1.setVisible(false);
						 price.setBorder(BorderFactory.createLineBorder(Color.gray, 1));	 
					 }
				    
				    if(!StringUtils.isStrictlyNumeric(VE.getText()))
				 	 {
				    	check=false;
				 		 error1.setVisible(true);
				 		 VE.setBorder(BorderFactory.createLineBorder(Color.red, 1));
				 	 }
					 else
					 {
						 
						 error1.setVisible(false);
						 VE.setBorder(BorderFactory.createLineBorder(Color.gray, 1));	 
					 }
					if(check) {
						
						 JOptionPane.showMessageDialog(null, "Product has been added");
					
 						 int VE1,quantity1,frequency1,price1;
						 String name1;
						 name1 		= name.getText();
						 VE1		= Integer.parseInt(VE.getText());
						 quantity1	= Integer.parseInt(quantity.getText());
						 price1		=Integer.parseInt(price.getText());
						
						 
						 if(high_demand.isSelected())
							 frequency1 = 1;
						 else
							 frequency1 = 0;
						 
						 
						 product w = new product();
						 try {
							w.check_if_exist(name1, quantity1, VE1, frequency1, price1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
 					}
						
					}
				
				}	
			
			
		}
	
}
