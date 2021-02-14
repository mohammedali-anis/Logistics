package logistex;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class find_shelf extends JFrame {
	private JButton next,previous,help;
	private JLabel txt_quantity , txt_storing_shelf,shelf_num ;
	private JTextField  storing_shelf , quantity ,product_name;
	private  int i =0 ;
	private  int counter;
	
	product w = new product();
	private String[] shelf_array=new String[200];
	private int[] quantity_array=new int[200];
	public find_shelf(String s) throws SQLException {
		 

		
		
		//Setup
		setTitle("Logistix");
		setLayout (null);
		setVisible(true);
		setSize(300,250);
		setLocationRelativeTo(null);
		
		//Button setup
		next 	 = new JButton ("Next");
		previous = new JButton ("Previous");
		help 	 = new JButton ("?");
		
		add(help);
		add(next);
		add(previous);
		
		help.setMargin(new Insets(0,0,0,0));
	
		//Text fields setup
		storing_shelf = new JTextField("");
		quantity	  = new JTextField("");
		product_name  = new JTextField(s);
		
		product_name.setEditable(false);
		quantity.setEditable(false);
		storing_shelf.setEditable(false);
		
		add(storing_shelf);
		add(quantity);
		add(product_name);
		
		//Labels setup
		txt_storing_shelf = new JLabel("Storing shelf : ");
		txt_quantity	  = new JLabel("Quantity : ");
		shelf_num 		  = new JLabel("First");
		add(txt_storing_shelf);
		add(txt_quantity);
		add(shelf_num);
		
		//Buttons bounds
		help.setBounds(265,0,20,20);
		next.setBounds(45,175,100,30);
		previous.setBounds(155,175,100,30);
		
		//Text field bounds
		storing_shelf.setBounds(135,75,100,30);
		quantity.setBounds(135,115,100,30);
		product_name.setBounds(100,15,100,30);
		
		//Label bounds
		txt_storing_shelf.setBounds(15,75,100,30);
		txt_quantity.setBounds(42,115,100,30);
		shelf_num.setBounds(263,170,100,40);
		//Button action
		ev e = new ev();
		next.addActionListener(e);
		previous.addActionListener(e);
		help.addActionListener(e);
		
		
		counter=w.find_product_placment(product_name.getText());
		
		
		
		
		 shelf_array = w.get_shelf_array();
		 quantity_array = w.get_quantity_array();
		
			quantity.setText(""+quantity_array[i]);
			storing_shelf.setText(shelf_array[i]);
	
	}
	 public class ev implements ActionListener  {

		 public void actionPerformed(ActionEvent e){
			 
			 
		
			 
			
			 if(e.getSource()==help)
				 JOptionPane.showMessageDialog(null,"Press \nnext\n to find the next shelf\n"
				 		+ "press \"previous\" to find the previous shelf");
			
			 else if(e.getSource() == next)
			 {
				
					if(i+2!=counter)
					{
						 i++;
						 quantity.setText(""+quantity_array[i]);
						 storing_shelf.setText(shelf_array[i]);

						if(i+2==counter)
						{
							shelf_num.setText("Last");
						}
						else if(i>0)
							shelf_num.setText(""+(i+1));
						
					}
			 }
			 else if(e.getSource()== previous)
			 {
				
					if(i>0)
					{
						 i--;
							quantity.setText(""+quantity_array[i]);
							storing_shelf.setText(shelf_array[i]);
							
							if(i==0)
							{
								shelf_num.setText("First");
								}
							else if(i>0)
								shelf_num.setText(""+(i+1));
							
					
					}
			 }
		 }
		 
	 
	 
	 }
}
