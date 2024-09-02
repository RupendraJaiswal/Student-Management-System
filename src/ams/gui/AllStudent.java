package ams.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import ams.dbutils.DbConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.print.PrinterException;
import java.awt.Color;

public class AllStudent extends JFrame 
{

	private JPanel contentPane;
	private JTable table;
	private Connection con;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllStudent frame = new AllStudent();
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
	public AllStudent() 
	{
		con=DbConnection.openConnection();
		setTitle("All available courses");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 692, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 644, 411);
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
	}
	public void showCourses() 
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
		String strsql="select* from student_details ";
		try 
		{
			ps=con.prepareStatement(strsql);
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
}
