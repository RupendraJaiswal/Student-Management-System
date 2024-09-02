package ams.admin;
import java.awt.BorderLayout;
import java.sql.*;
import java.awt.event.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ams.dbutils.DbConnection;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class DeleteCourse extends JFrame implements ActionListener,KeyListener,WindowListener
{

	private JPanel contentPane;
	private JTextField txtcname;
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteCourse frame = new DeleteCourse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
      void delete()
      {
    	this.addWindowListener(this);
    	setIconImage(Toolkit.getDefaultToolkit().getImage(DeleteCourse.class.getResource("/ams/images/delete.png")));
  		setTitle("Delete Course");
  		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  		setBounds(100, 100, 450, 300);
  		contentPane = new JPanel();
  		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  		setLocationRelativeTo(null);//to do child screen in center
  		setContentPane(contentPane);
  		contentPane.setLayout(null);
  		
  		JLabel lbldc = new JLabel("Delete Course Frame");
  		lbldc.setForeground(Color.RED);
  		lbldc.setFont(new Font("Times New Roman", Font.BOLD, 20));
  		lbldc.setBounds(112, 35, 217, 30);
  		contentPane.add(lbldc);
  		
  		JLabel lblcn = new JLabel("Course Name");
  		lblcn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
  		lblcn.setForeground(Color.MAGENTA);
  		lblcn.setBounds(31, 75, 129, 36);
  		contentPane.add(lblcn);
  		
  		txtcname = new JTextField();
  		txtcname.setToolTipText("Enter Course Name");
  		txtcname.setFont(new Font("Times New Roman", Font.PLAIN, 20));
  		txtcname.setBounds(170, 75, 156, 30);
  		contentPane.add(txtcname);
  		txtcname.setColumns(10);
  		
  		JButton btndelete = new JButton("Delete");
  		btndelete.setForeground(Color.RED);
  		btndelete.setIcon(new ImageIcon(DeleteCourse.class.getResource("/ams/images/delete.png")));
  		btndelete.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
  		btndelete.setBounds(156, 141, 121, 36);
  		contentPane.add(btndelete);
  		btndelete.addActionListener(this);
  		btndelete.addKeyListener(this);

      }
	/**
	 * Create the frame.
	 */
	public DeleteCourse() //constructor
	{
		con=DbConnection.openConnection();//it will connect the frame with database->admission_db
		delete();
	}

	void deletecourse()
	{
		String cname = txtcname.getText().trim();
		
		if(cname.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Please enter course");
		}
		else
		{ 
			//System.out.println(cname);
			int choice=JOptionPane.showConfirmDialog(this, "Are you sure wish to delete"+cname);
			//System.out.println(choice);
			PreparedStatement ps =null;//it is responsible for communication with database
			String str_delete="delete from course_details where course_name=?";
			if(choice==0)
			{
			try
			{
				ps=con.prepareStatement(str_delete);
				ps.setString(1, cname);
				int row_status=ps.executeUpdate();
				if(row_status>0)
				{
					JOptionPane.showMessageDialog(this, "Course deleted successfully","deletion",JOptionPane.PLAIN_MESSAGE);
				    txtcname.setText("");
				}
				else
				{
					JOptionPane.showMessageDialog(this, "No such course present","Unavailability",JOptionPane.ERROR_MESSAGE);
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	     deletecourse();
	    // System.out.println("button is being clicked");
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
			deletecourse();
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
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
