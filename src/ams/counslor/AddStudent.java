package ams.counslor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ams.dbutils.DbConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.*;

public class AddStudent extends JFrame implements ActionListener,KeyListener
{

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtemail;
	private JTextField txtphone;
	private JTextField txtcourse;
	private JTextArea txtaddress;
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudent frame = new AddStudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	void createcomponent()
	{
		setTitle("Add student");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 512, 345);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblname = new JLabel("Name");
		lblname.setBounds(30, 34, 66, 24);
		lblname.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		contentPane.add(lblname);
		
		JLabel lblemail = new JLabel("Email");
		lblemail.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblemail.setBounds(30, 68, 66, 24);
		contentPane.add(lblemail);
		
		JLabel lblphone = new JLabel("Phone");
		lblphone.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		lblphone.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblphone.setBounds(30, 102, 66, 24);
		contentPane.add(lblphone);
		
		JLabel lblcourse = new JLabel("Course");
		lblcourse.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblcourse.setBounds(30, 136, 66, 24);
		contentPane.add(lblcourse);
		
		JLabel lbladdress = new JLabel("Address");
		lbladdress.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lbladdress.setBounds(30, 170, 66, 24);
		contentPane.add(lbladdress);
		
		txtname = new JTextField();
		txtname.setToolTipText("Enter name");
		txtname.setBounds(146, 39, 190, 19);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		txtemail = new JTextField();
		txtemail.setToolTipText("Enter Email");
		txtemail.setColumns(10);
		txtemail.setBounds(146, 73, 190, 19);
		contentPane.add(txtemail);
		
		txtphone = new JTextField();
		txtphone.setToolTipText("Enter phone");
		txtphone.setColumns(10);
		txtphone.setBounds(146, 107, 190, 19);
		contentPane.add(txtphone);
		
		txtcourse = new JTextField();
		txtcourse.setToolTipText("Enter course");
		txtcourse.setColumns(10);
		txtcourse.setBounds(146, 141, 190, 19);
		contentPane.add(txtcourse);
		
		txtaddress = new JTextArea();
		txtaddress.setToolTipText("enter address");
		txtaddress.setBounds(146, 172, 190, 46);
		contentPane.add(txtaddress);
		
		JButton btnsubmit = new JButton("Submit");
		btnsubmit.setForeground(Color.BLUE);
		btnsubmit.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnsubmit.setBackground(Color.PINK);
		btnsubmit.setBounds(189, 244, 111, 21);
		contentPane.add(btnsubmit);
		btnsubmit.addActionListener(this);
	}
	/**
	 * Create the frame.
	 */
	public AddStudent() 
	{   
		con=DbConnection.openConnection();//it will connect the frame with database->admission_db
		
		createcomponent();
	}
	void check()
	{
		String name=txtname.getText().trim();
		String email=txtemail.getText().trim();
		String phone=txtphone.getText().trim();
		String course=txtcourse.getText().trim();
		String address=txtaddress.getText().trim();
		if(name.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Please enter Name");
		}
		else if(email.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Please enter Email");
		}
		else if(phone.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Please enter phone");
		}
		else if(course.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Please enter course");
		}
		else if(address.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Please enter address");
		}
		else
		{
			PreparedStatement ps=null;
			String str_insert="insert into student_details(name, email, phone, course,address)values(?,?,?,?,?)";
		try
		{
			ps=con.prepareStatement(str_insert);//it passes the query to RDBMS and RDBMS compiler->compile the query and stores the query into buffer and assign the address or reference to ps
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3,phone);
			ps.setString(4,course);
			ps.setString(5,address);
			
			int row_status=ps.executeUpdate();//it will again  passes the query to dbms
			System.out.println("insert status "+row_status);
			if(row_status>0)
			{
				JOptionPane.showMessageDialog(this, "Student added successfully");
				txtname.setText("");
				txtemail.setText("");
				txtcourse.setText("");
				txtaddress.setText("");
				txtphone.setText("");
			}
			
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			
		}
		finally
		{
			if(ps!=null)
			{
				try 
				{
					ps.close();
				} 
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		}
	}
}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		check();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code=e.getKeyCode();
		if(code==10)
		{
			check();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
