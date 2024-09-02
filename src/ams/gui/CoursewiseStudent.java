package ams.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import ams.dbutils.DbConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CoursewiseStudent extends JFrame  implements ActionListener
{

	private JPanel contentPane;
	private JTable table;
	private Connection con;
	private JTextField txtcourse;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoursewiseStudent frame = new CoursewiseStudent();
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
	public CoursewiseStudent() 
	{
		con=DbConnection.openConnection();
		setTitle("All available student courseswise");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 692, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 85, 644, 336);
		contentPane.add(scrollPane);
		
		table = new JTable();
		JTableHeader header = table.getTableHeader();//we are getting the reference of TableHeader
		header.setBackground(Color.cyan);
		header.setForeground(Color.blue);
		header.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
		
		table.setForeground(new Color(0, 0, 0));
		table.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		showCourses();
		scrollPane.setViewportView(table);
		
		JLabel lblcs = new JLabel("Coursewise ");
		lblcs.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblcs.setBounds(10, 41, 106, 24);
		contentPane.add(lblcs);
		
		txtcourse = new JTextField();
		txtcourse.setToolTipText("Enter course name ");
		txtcourse.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		txtcourse.setBounds(141, 46, 176, 19);
		contentPane.add(txtcourse);
		txtcourse.setColumns(10);
		
		JButton btngo = new JButton("GO");
		btngo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		btngo.setBounds(372, 41, 106, 24);
		contentPane.add(btngo);
		btngo.addActionListener(this);
	}
	public void showCourses() 
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
		String strsql="select* from student_details where course=?";
		try 
		{
			ps=con.prepareStatement(strsql);
			ps.setString(1,cname);
			rs=ps.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
		/*	try {
				table.print();
				
			} catch (PrinterException e) {
				// TODO: handle exception
				e.printStackTrace();
			}*/

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
			    se.printStackTrace();
				// TODO: handle exception
			}
		}
	}
	String cname;
	void check()
	{
		cname=txtcourse.getText().trim();
		if(cname.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Please select a course");
		}
		else
		{
			showCourses();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		check();
		
	}
}
