package ams.admin;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Toolkit;
import javax.swing.border.LineBorder;

import ams.dbutils.DbConnection;
import ams.gui.CustomLookAndFeel;


import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.*;
import javax.swing.ButtonGroup;
import java.sql.*;

public class AddCourse extends JFrame implements ActionListener,KeyListener,WindowListener
{

	private JPanel contentPane;
	private JTextField txtcname;
	private JTextField txtfees;
	private JTextField txtdur;
	private final ButtonGroup weekend_group = new ButtonGroup();
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomLookAndFeel.setLookAndFeel();
					AddCourse frame = new AddCourse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	void insertcourse()
	{
		String cfees = txtfees.getText().trim();
		String cduration =txtdur.getText().trim();
		String cname =txtcname.getText().trim();
		
		// how  to fetch value from radio button
		String status="";
		if(rdyes.isSelected())
		{
			status=rdyes.getText();
		}
		if(rdno.isSelected())
		{
			status=rdno.getText();
		}
		if(cfees.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Please enter fees","MandatoryField",JOptionPane.ERROR_MESSAGE);
			
		}
		
		else  if(cduration.isEmpty())
		    {
			   JOptionPane.showMessageDialog(this, "Please enter duration","MandatoryField",JOptionPane.ERROR_MESSAGE);
		    }
		else if (cname.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Please enter course name","MandatoryField",JOptionPane.ERROR_MESSAGE);
		}
		else if(status.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Please Select radio button","MandatoryField",JOptionPane.ERROR_MESSAGE);
		}
		else {
			//System.out.println("Course name is "+cname);
			//System.out.println("Course Fess is "+cfees);
			//System.out.println("Course duration is "+cduration);
			
			PreparedStatement ps =null;//it is responsible for communication with database
			String str_insert="insert into course_details(course_name, course_fees, course_duration, weekendavailability)values(?,?,?,?)";
			try 
			{
				ps=con.prepareStatement(str_insert);//it passes the query to RDBMS and RDBMS compiler->compile the query and stores the query into buffer and assign the address or reference to ps
				ps.setString(1, cname);
				ps.setInt(2, Integer.parseInt(cfees));
				ps.setString(3, cduration);
				ps.setString(4, status);
				
				int row_status=ps.executeUpdate();//it will again  passes the query to dbms
				System.out.println("insert status "+row_status);
				if(row_status>0)
				{
					JOptionPane.showMessageDialog(this, "Course added successfully");
					txtcname.setText("");
					txtfees.setText("");
					txtdur.setText("");
					weekend_group.clearSelection();
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
	/**
	 * Create the frame.
	 */
	JRadioButton rdyes,rdno;//gloablised the radio button
	public AddCourse() 
	{
		con=DbConnection.openConnection();//it will connect the frame with database->admission_db
		this.addWindowListener(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddCourse.class.getResource("/ams/images/add.png")));
		setTitle("Add course");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new LineBorder(Color.RED, 3));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblcn = new JLabel("Course Name");
		lblcn.setBackground(Color.WHITE);
		lblcn.setForeground(Color.BLUE);
		lblcn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblcn.setBounds(35, 36, 123, 30);
		contentPane.add(lblcn);
		
		txtcname = new JTextField();
		txtcname.setToolTipText("Enter course name");
		txtcname.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtcname.setForeground(Color.BLACK);
		txtcname.setBounds(184, 37, 156, 27);
		contentPane.add(txtcname);
		txtcname.setColumns(10);
		
		JLabel lblfees = new JLabel("Fees");
		lblfees.setForeground(Color.BLUE);
		lblfees.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblfees.setBounds(35, 87, 123, 30);
		contentPane.add(lblfees);
		
		txtfees = new JTextField();
		txtfees.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtfees.setToolTipText("Enter fee amount");
		txtfees.setBounds(184, 87, 156, 27);
		contentPane.add(txtfees);
		txtfees.setColumns(10);
		
		JLabel lbldur = new JLabel("Duration");
		lbldur.setForeground(Color.BLUE);
		lbldur.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbldur.setBounds(35, 136, 123, 30);
		contentPane.add(lbldur);
		
		txtdur = new JTextField();
		txtdur.setToolTipText("Enter duration of course");
		txtdur.setForeground(Color.BLACK);
		txtdur.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtdur.setBounds(184, 136, 156, 30);
		contentPane.add(txtdur);
		txtdur.setColumns(10);
		
		JLabel lblavail = new JLabel("Weekend available?");
		lblavail.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblavail.setForeground(Color.BLUE);
		lblavail.setBounds(35, 176, 156, 30);
		contentPane.add(lblavail);
		
		rdyes = new JRadioButton("Yes");
		weekend_group.add(rdyes);
		rdyes.setFont(new Font("Times New Roman", Font.BOLD, 20));
		rdyes.setBounds(184, 180, 57, 21);
		contentPane.add(rdyes);
		
		rdno = new JRadioButton("No");
		weekend_group.add(rdno);
		rdno.setFont(new Font("Times New Roman", Font.BOLD, 20));
		rdno.setBounds(269, 180, 49, 23);
		contentPane.add(rdno);
		
		JButton btnsubmit = new JButton("Submit");
		btnsubmit.setIcon(new ImageIcon(AddCourse.class.getResource("/ams/images/submit.png")));
		btnsubmit.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnsubmit.setBounds(152, 216, 123, 37);
		contentPane.add(btnsubmit);
		btnsubmit.addActionListener(this);
		btnsubmit.addKeyListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		insertcourse();
		
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		int code=e.getKeyCode();//it return the ASCII Value
		//System.out.println(code);
		if(code==10)//enter key code is 10
		{
			insertcourse();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
	DbConnection.closeConnection();
	System.out.println("connection is being close....");
		
	
		
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
