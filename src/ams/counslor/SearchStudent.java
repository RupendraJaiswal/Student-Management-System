package ams.counslor;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ams.dbutils.DbConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.sql.*;

public class SearchStudent extends JFrame implements ActionListener,KeyListener,WindowListener
{

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtid;
	private JTextField txtemail;
	private JTextField txtphone;
	private JTextField txtcourse;
	private JTextArea txtaddress;
	private Connection con;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchStudent frame = new SearchStudent();
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
    void createcomponent()
    {
    	setTitle("Search the Student");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 548, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblname = new JLabel("Name");
		lblname.setBounds(10, 31, 67, 30);
		lblname.setBackground(Color.MAGENTA);
		lblname.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		contentPane.add(lblname);
		
		JLabel lblid = new JLabel("ID");
		lblid.setBounds(282, 31, 35, 30);
		lblid.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		contentPane.add(lblid);
		
		txtname = new JTextField();
		txtname.setBounds(96, 31, 159, 30);
		txtname.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		txtname.setToolTipText("Enter student name");
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		txtid = new JTextField();
		txtid.setBounds(343, 31, 136, 30);
		txtid.setToolTipText("Enter student ID");
		txtid.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		txtid.setColumns(10);
		contentPane.add(txtid);
		
		JButton btnsearch = new JButton("Search");
		btnsearch.setBounds(245, 71, 103, 30);
		btnsearch.setBackground(Color.GREEN);
		btnsearch.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		contentPane.add(btnsearch);
		btnsearch.addActionListener(this);
		btnsearch.addKeyListener(this);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 123, 67, 30);
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblEmail.setBackground(Color.MAGENTA);
		contentPane.add(lblEmail);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(10, 159, 67, 30);
		lblPhone.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblPhone.setBackground(Color.MAGENTA);
		contentPane.add(lblPhone);
		
		JLabel lblCourse = new JLabel("Course");
		lblCourse.setBounds(10, 197, 67, 30);
		lblCourse.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblCourse.setBackground(Color.MAGENTA);
		contentPane.add(lblCourse);
		
		txtemail = new JTextField();
		txtemail.setEditable(false);
		txtemail.setToolTipText("");
		txtemail.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		txtemail.setColumns(10);
		txtemail.setBounds(96, 117, 159, 30);
		contentPane.add(txtemail);
		
		txtphone = new JTextField();
		txtphone.setEditable(false);
		txtphone.setToolTipText("");
		txtphone.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		txtphone.setColumns(10);
		txtphone.setBounds(96, 159, 159, 30);
		contentPane.add(txtphone);
		
		txtcourse = new JTextField();
		txtcourse.setEditable(false);
		txtcourse.setToolTipText("");
		txtcourse.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		txtcourse.setColumns(10);
		txtcourse.setBounds(96, 197, 159, 30);
		contentPane.add(txtcourse);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblAddress.setBackground(Color.MAGENTA);
		lblAddress.setBounds(10, 247, 67, 30);
		contentPane.add(lblAddress);
		
	    txtaddress = new JTextArea();
	    txtaddress.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		txtaddress.setEditable(false);
		txtaddress.setBounds(96, 252, 364, 91);
		contentPane.add(txtaddress);
    }
    
    
    void checkStudent()
    {
    	String name=txtname.getText().trim();
    	String ID=txtid.getText().trim();
    
    	 
    	if(name.isEmpty()||ID.isEmpty())
    	{
    		JOptionPane.showMessageDialog(this, "All fields are mandatery");
    		
    	}
    	else
    	{
    		PreparedStatement ps=null;
    		ResultSet rs=null;
    		String strsearch="select*from student_details where name=? and id=?";
    		try 
    		{
    			ps=con.prepareStatement(strsearch);
    			ps.setString(1,name);
    			ps.setString(2, ID);
    			rs=ps.executeQuery();
    			
    			if(rs.next())
    			{
    				String email=rs.getString("email");
    				String phone=rs.getString("phone");
    				String course=rs.getString("course");
    				String address=rs.getString("address");
    				
    				txtcourse.setText(course);
    		        txtphone.setText(phone);
    		        txtemail.setText(email);
    		        txtaddress.setText(address);
    				
    			}
    			else
    			{
    				JOptionPane.showMessageDialog(this, "No such Student available");
    				
    			}
				
			} 
    		catch (SQLException se) 
    		{
				// TODO: handle exception
    			se.printStackTrace();
			}
    		finally
    		{
    			try 
    			{
					if(ps!=null)
						ps.close();
					if(rs!=null)
						rs.close();
					
				} 
    			catch (SQLException se) 
    			{
					// TODO: handle exception
    				se.printStackTrace();
				}
    		}
    		
    	}
	}
    
    
    
	/**
	 * Create the frame.
	 */
	public SearchStudent() 
	{
		con=DbConnection.openConnection();
		setType(Type.UTILITY);
		setResizable(false);

		createcomponent();
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		DbConnection.closeConnection();
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code=e.getKeyCode();//it return the AScII Value
		//System.out.println(code);
		if(code==10)//enter key code is 10
		{
			checkStudent();
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		checkStudent();
		
	}
}
