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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import logistex.Employee.ev;
import logistex.Employee.fl;

//import gui.add_new_product.ev;

public class costumer extends JFrame {

	//Atribute
	private JButton signup = new JButton("Sign Up");
	private JButton signin = new JButton("Sign In");
	private JButton help = new JButton("?");
	private JButton back = new JButton("Back");
	private JTextField password = new JTextField("Password");
	private JTextField username = new JTextField("Username");
	private JLabel txt_username= new JLabel("username :");
	private JLabel txt_password = new JLabel(" Passwor :");
	private JLabel error1= new JLabel("Invalid fields");
	private JLabel error2= new JLabel("Invaled username or Password");
	private boolean check = false;
	private boolean check_valid=false;
	public costumer() {
		
		//SetUP
		setTitle("Sign IN");
		setLayout (null);
		setVisible(true);
		setSize(300,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		error1.setBounds(115,7,100,30);
		error1.setVisible(false);
		error1.setForeground(Color.red);
		add(error1);
		error2.setBounds(70,7,180,30);
		error2.setVisible(false);
		error2.setForeground(Color.red);
		add(error2);
		add(password);
		add(username);
		add(txt_username);
		add(txt_password);
		add(help);
		add(signup);
		add(signin);
		add(back);
		// merge
		help.setMargin (new Insets(0,0,0,0));
		back.setMargin (new Insets(0,0,0,0));
		//button bounds
		username.setBounds(80,40,140,30);
		password.setBounds(80,85, 140, 30);
		signup.setBounds(155,125,100,30);
		txt_password.setBounds(10,85,100,30);
		txt_username.setBounds(13,40,100,30);
		signin.setBounds(45,125, 100, 30);
		help.setBounds(265,0,20,20);
		back.setBounds(0,0,40,30);
	
		
		
		//actions
		ev e = new ev();
		signup.addActionListener(e);
		help.addActionListener(e);
		back.addActionListener(e);
		signin.addActionListener(e);
		

		fl f = new fl();

		password.addFocusListener(f);
		username.addFocusListener(f);
	}
	

	public class fl implements FocusListener{
		
		@Override
		public void focusGained(FocusEvent e) {
			if(e.getSource()==password)
				password.setText("");
			else if (e.getSource()==username)
				username.setText("");
		}
	
		@Override
			public void focusLost(FocusEvent e) {
		 
		}}


	
	public class ev implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			error2.setVisible(false);
			if(e.getSource()==signup)
			{
				dispose();
				add_costumer w = new add_costumer();
			}
			
			else if(e.getSource()== signin)
			{
				check=true;
				if(username.getText().equals("")||username.getText().equals("Username"))
				{
					check = false;
					
					 error1.setVisible(true);
						
					 username.setBorder(BorderFactory.createLineBorder(Color.red, 1));
					
				}
				else {
					 username.setBorder(BorderFactory.createLineBorder(Color.gray, 1));	 
				}
				
				if(password.getText().equals("")||password.getText().equals("Password"))
				{
					check = false;
					 error1.setVisible(true);
						
					 password.setBorder(BorderFactory.createLineBorder(Color.red, 1));
					
				}
				else
				{
					password.setBorder(BorderFactory.createLineBorder(Color.gray, 1));	 
			
				}
				
				//heeeeeeeeeeee
				if(check)
				{
					error1.setVisible(false);
					try {
						check_valid=data_manager.sign_in(username.getText(),password.getText());
					}
					
					catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(check_valid)
					{
						try {
							order w= new order(username.getText());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dispose();
					}
					else {
						error2.setVisible(true);
					}
				}
				//heeeeeeeeeeeee
				
			}
			
			else if(e.getSource()== help)
			{	
				JOptionPane.showMessageDialog
				(null, "Enter username and ppassword if you have an account to sign in\n"
						+ "If you dont have an account you can creat one with sign up\n"
						+ "Don't leave empty fields");
			}
			
			else if(e.getSource()==back)
			{
				main_frame w = new main_frame();
				dispose();
			}
		}
		
			}	
		
		
	
}