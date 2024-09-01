package ams.counslor;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ams.dbutils.DbConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DeleteStudent extends JFrame implements ActionListener,KeyListener,WindowListener
{

	private JPanel contentPane;
	private JTextField txtphone;
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteStudent frame = new DeleteStudent();
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
	public DeleteStudent() {
		con=DbConnection.openConnection();
		setTitle("Delete Student ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblphone = new JLabel("Phone No");
		lblphone.setBackground(Color.DARK_GRAY);
		lblphone.setForeground(Color.GRAY);
		lblphone.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblphone.setBounds(41, 33, 103, 36);
		contentPane.add(lblphone);
		
		txtphone = new JTextField();
		txtphone.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		txtphone.setToolTipText("Enter phone number");
		txtphone.setBounds(154, 41, 244, 25);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		JButton btndelete = new JButton("Delete");
		btndelete.setBackground(Color.PINK);
		btndelete.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		btndelete.setBounds(144, 108, 103, 25);
		contentPane.add(btndelete);
		btndelete.addActionListener(this);
	}
    void deleteStudent()
    {
    	String phone=txtphone.getText().trim();
    	if(phone.isEmpty())
    	{
    		JOptionPane.showMessageDialog(this, "Please enter Phone No");
    	}
    	else
    	{
    		int choice=JOptionPane.showConfirmDialog(this, "Are you sure wish to delete"+phone);
			//System.out.println(choice);
			PreparedStatement ps =null;//it is responsible for communication with database
			String str_delete="delete from student_details where phone=?";
			if(choice==0)
			{
			try
			{
				ps=con.prepareStatement(str_delete);
				ps.setString(1, phone);
				int row_status=ps.executeUpdate();
				if(row_status>0)
				{
					JOptionPane.showMessageDialog(this, "Student  deleted successfully","deletion",JOptionPane.PLAIN_MESSAGE);
				    txtphone.setText("");
				}
				else
				{
					JOptionPane.showMessageDialog(this, "No such Phone present","Unavailability",JOptionPane.ERROR_MESSAGE);
				}
			}
			catch (SQLException se) 
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
						
						e.printStackTrace();
					}
				}
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
			deleteStudent();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		deleteStudent();
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		DbConnection.closeConnection();
		System.out.println("connection is being closing....");
		
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
