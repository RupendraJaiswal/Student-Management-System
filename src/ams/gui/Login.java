package ams.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import ams.admin.Admin;
import ams.counslor.Counslor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.*;


public class Login extends JFrame implements ActionListener,KeyListener
{

	private JPanel contentPane;
	private JTextField txtuserid;
	private JPasswordField txtuserpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void createComponents() 
	{
		setTitle("LoginFrame\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 425);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblid = new JLabel("UserID");
		lblid.setIcon(new ImageIcon(Login.class.getResource("/ams/images/userid.png")));
		lblid.setForeground(new Color(0, 0, 0));
		lblid.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblid.setBounds(54, 45, 132, 36);
		contentPane.add(lblid);
		
		txtuserid = new JTextField();
		txtuserid.setForeground(new Color(0, 0, 0));
		txtuserid.setBackground(new Color(255, 255, 204));
		txtuserid.setToolTipText("Enter UserId");
		txtuserid.setBounds(221, 53, 180, 34);
		contentPane.add(txtuserid);
		txtuserid.setColumns(10);
		
		JLabel lblpass = new JLabel("UserPassword");
		lblpass.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblpass.setToolTipText("User Password");
		lblpass.setBounds(30, 110, 180, 37);
		contentPane.add(lblpass);
		
		txtuserpass = new JPasswordField();
		txtuserpass.setBackground(new Color(255, 255, 204));
		txtuserpass.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		txtuserpass.setToolTipText("Enter Password");
		txtuserpass.setBounds(221, 112, 180, 36);
		contentPane.add(txtuserpass);
		
		JButton btnsubmit = new JButton("Submit");
		btnsubmit.setBackground(new Color(102, 204, 204));
		btnsubmit.setForeground(new Color(0, 0, 51));
		btnsubmit.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		btnsubmit.addActionListener(this);
		btnsubmit.addKeyListener(this);//registering the key listener
		btnsubmit.setBounds(185, 169, 138, 45);
		contentPane.add(btnsubmit);
		
		
		ImageIcon ic =new ImageIcon(Login.class.getResource("/ams/images/logo.png"));
		Image i2=ic.getImage().getScaledInstance(132,127,Image.SCALE_DEFAULT);
		ImageIcon ic1=new ImageIcon(i2);
		JLabel lblimg=new JLabel(ic1);
		
		
		
		lblimg.setBounds(440, 45, 132, 127);
		contentPane.add(lblimg);
		
		
	
	}
	public Login() 
	{
		createComponents();//calling method to show the components
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("button clicked");
		//JOptionPane.showMessageDialog(this, "hello admin");
		//JOptionPane.showConfirmDialog(this, "Are you sure to Login");
		//JOptionPane.showInputDialog(this,"Enter your age");
		
		login();
		
	}
	
	void login()
	{
		String id = txtuserid.getText().trim();
		char[]password=txtuserpass.getPassword();
		String pass=String.valueOf(password);
		
		
	if(id.isEmpty()||pass.isEmpty())
	{
		JOptionPane.showMessageDialog(this, "Please Enter userid/Password");
	}
	
	else 
		{
		if (id.equalsIgnoreCase("Scott")&&pass.equals("scott123"))
		
	     {
			Admin ad=new Admin();
			ad.setVisible(true);
	     }
		
	     
		else 
		{
			if(id.equalsIgnoreCase("Rupendra")&&pass.equals("rupendra123"))
			
		      {
				Counslor c=new Counslor();
				c.setVisible(true);
		      }
		    else
			   {
			      JOptionPane.showMessageDialog(this, "Invalid UserId/Password");
			   }
		}
	
	   }
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int code=e.getKeyCode();//it return the AScII Value
		//System.out.println(code);
		if(code==10)//enter key code is 10
		{
			login();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
	

