package logistex;
	import java.awt.*;
	import java.awt.event.*  ;
	import javax.swing.*;
public class main_frame extends JFrame  {
	


		//Atributes 
		protected JButton employee,costumer,help;
		
		
		
		public main_frame() {
			//setup
			setLayout (null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			setSize(350,150);
			setTitle("Logistix");  
			setLocationRelativeTo(null);
			
			
			//buttons
			employee = new JButton("Employee");
			costumer = new JButton("Costumer");
			help = new JButton("?");
			add (employee);
		 	add (costumer);
			add (help);

			//Button Margin
			employee.setMargin(new Insets(0,0,0,0));
			costumer.setMargin(new Insets(0,0,0,0));
			help.setMargin	  (new Insets(0,0,0,0));
			
			//Button bounds
			employee.setBounds(185, 50, 100, 40); 
			costumer.setBounds(65,50,100,40); 
			help.setBounds(315,0,20,20);
			
			
			//events
			ev e = new ev();
			employee.addActionListener(e) ;
	 	 	costumer.addActionListener(e);
	 	 	help.addActionListener(e);
	 	 	
		}
		
	 public class ev implements ActionListener  {

		 public void actionPerformed(ActionEvent e){
			
			 if(e.getSource()==costumer)
			{ 
				 costumer c = new costumer();
				 dispose();
				
		
			}
			
			 else if (e.getSource()==employee)
				 {
					dispose();	
				Employee f= new Employee();
				 }
			 else {



				 JOptionPane.showMessageDialog(null, "To buy products : Go to \"customer\"\n to get products info or to enter new products go to \"Employee\"");
			 }
		 }}
	 
	  
	
}
