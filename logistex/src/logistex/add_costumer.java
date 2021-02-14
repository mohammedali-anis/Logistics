package logistex;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;

import javax.swing.*;

import logistex.costumer.ev;
import logistex.costumer.fl;

public class add_costumer extends JFrame {

	//Atribute
	private JButton signup = new JButton("Sign Up");
	private JButton Cancel = new JButton("Cancel");
	private JButton help = new JButton("?");
	private JButton back = new JButton("Back");
	private JTextField password = new JTextField("Password");
	private JTextField username = new JTextField("Username");
	private JLabel txt_username= new JLabel("username :");
	private JLabel txt_password = new JLabel(" Passwor :");
	private JLabel error1= new JLabel("Invalid fields");
	private JLabel error2= new JLabel("Username exists");
	private boolean check=false;
	private boolean check_valid = false;
	private JCheckBox checkbox = new JCheckBox("Premium");
	public add_costumer() {
		
		//SetUP
		setTitle("Sign Up");
		setLayout (null);
		setVisible(true);
		setSize(300,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		error2.setBounds(115,7,100,30);
		error2.setVisible(false);
		error2.setForeground(Color.red);
		
		error1.setBounds(115,7,100,30);
		error1.setVisible(false);
		error1.setForeground(Color.red);
		add(error2);
		add(error1);
		add(password);
		add(username);
		add(txt_username);
		add(txt_password);
		add(checkbox);
		add(help);
		add(signup);
		add(Cancel);
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
		Cancel.setBounds(45,125, 100, 30);
		help.setBounds(265,0,20,20);
		back.setBounds(0,0,40,30);
		checkbox.setBounds(216,85,100,30);
		
		
		//actions
		ev e = new ev();
		signup.addActionListener(e);
		help.addActionListener(e);
		back.addActionListener(e);
		Cancel.addActionListener(e);
		

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
			check = true;
			error2.setVisible(false);
			if(e.getSource()==Cancel)
			{
				dispose();
				costumer w = new costumer();
			}
			
			else if(e.getSource()== signup)
			{
				
				if(username.getText().equals("")||username.getText().equals("Username"))
				{
					check=false;
					 error1.setVisible(true);
						
					 username.setBorder(BorderFactory.createLineBorder(Color.red, 1));
					
				}
				
				if(password.getText().equals("")||password.getText().equals("Password"))
				{
					check=false;
					 error1.setVisible(true);
						
					 password.setBorder(BorderFactory.createLineBorder(Color.red, 1));
					
				}
				//heeeeeeeeeeee
				if(check)
				{
					try {
						check_valid= data_manager.get_costumer(username.getText());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(check_valid)
					{
						int x;
						if(checkbox.isSelected())
							x=1;
						else
							x=0;
					try {
						data_manager.add_costumer(username.getText(), password.getText(), x);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					}
					else
					{error2.setVisible(true);
					error1.setVisible(false);}		
				}
				//heeeeeeeeeeeee
				//order w= new order(s);
			}
			
			else if(e.getSource()== help)
			{	
				JOptionPane.showMessageDialog
				(null, "Creat new account by putting a usser name and password by your choosing\n"
						+ "Then click on sign up \n"
						+ "-dont leave empty fields\n"
						+ "Evry User name must be uniq\n");
			}
			
			else if(e.getSource()==back)
			{
				main_frame w = new main_frame();
				dispose();
			}
		}
		
			}	}
		
		