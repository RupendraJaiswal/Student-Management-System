package ams.admin;

import java.awt.BorderLayout;
import java.sql.*;
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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Window.Type;

public class SearchCourse extends JFrame implements ActionListener,KeyListener,WindowListener
{

	private JPanel contentPane;
	private JTextField txtcname;
	private JTextField txtfees;
	private JTextField txtduration;
	private JTextField txtavail;
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchCourse frame = new SearchCourse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	void searchCourse()
	{
		setTitle("Search Course");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 587, 298);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblcname = new JLabel("Course Name");
		lblcname.setBackground(Color.PINK);
		lblcname.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblcname.setBounds(10, 29, 131, 27);
		contentPane.add(lblcname);
		
		JLabel lblfees = new JLabel("Fees");
		lblfees.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblfees.setBackground(Color.PINK);
		lblfees.setBounds(10, 80, 85, 27);
		contentPane.add(lblfees);
		
		JLabel lblduration = new JLabel("Duration");
		lblduration.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblduration.setBackground(Color.PINK);
		lblduration.setBounds(10, 117, 85, 27);
		contentPane.add(lblduration);
		
		JLabel lblavail = new JLabel("Available on weekend");
		lblavail.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblavail.setBackground(Color.PINK);
		lblavail.setBounds(0, 164, 183, 27);
		contentPane.add(lblavail);
		
		txtcname = new JTextField();
		txtcname.setToolTipText("Enter course name");
		txtcname.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		txtcname.setBounds(193, 29, 196, 25);
		contentPane.add(txtcname);
		txtcname.setColumns(10);
		
		txtfees = new JTextField();
		txtfees.setEditable(false);
		txtfees.setToolTipText("");
		txtfees.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		txtfees.setColumns(10);
		txtfees.setBounds(193, 71, 196, 25);
		contentPane.add(txtfees);
		
		txtduration = new JTextField();
		txtduration.setEditable(false);
		txtduration.setToolTipText("");
		txtduration.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		txtduration.setColumns(10);
		txtduration.setBounds(193, 117, 196, 25);
		contentPane.add(txtduration);
		
		txtavail = new JTextField();
		txtavail.setEditable(false);
		txtavail.setToolTipText("");
		txtavail.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		txtavail.setColumns(10);
		txtavail.setBounds(193, 164, 196, 25);
		contentPane.add(txtavail);
		
		JButton btnsearch = new JButton("Search");
		btnsearch.setBackground(Color.PINK);
		btnsearch.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		btnsearch.setBounds(454, 29, 109, 27);
		contentPane.add(btnsearch);
		btnsearch.addActionListener(this);
		btnsearch.addKeyListener(this);
	}
	/**
	 * Create the frame.
	 */
	public SearchCourse() 
	{
		setType(Type.UTILITY);
		setResizable(false);
		con=DbConnection.openConnection();
		searchCourse();
	}

	void checkCourse()
	{
		String cname=txtcname.getText().trim();
    	if(cname.isEmpty())
    	{
    		JOptionPane.showMessageDialog(this, "Please enter course name");
    	}
    	else
    	{
    		PreparedStatement ps=null;
    		ResultSet rs=null;
    		String strsearch="select*from course_details where course_name=?";
    		try 
    		{
    			ps=con.prepareStatement(strsearch);
    			ps.setString(1,cname);
    			rs=ps.executeQuery();
    			
    			if(rs.next())
    			{
    				String fees=rs.getString("course_fees");
    				String duration=rs.getString("course_duration");
    				String weekend=rs.getString("weekendavailability");
    				txtavail.setText(weekend);
    		        txtduration.setText(duration);
    		        txtfees.setText(fees);
    				
    			}
    			else
    			{
    				JOptionPane.showMessageDialog(this, "No such course available");
    				
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
			checkCourse();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		txtavail.setText("");
		txtduration.setText("");
		txtfees.setText("");
		checkCourse();
		
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
}
