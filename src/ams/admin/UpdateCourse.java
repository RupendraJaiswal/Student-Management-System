package ams.admin;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ams.dbutils.DbConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.sql.*;
public class UpdateCourse extends JFrame implements ActionListener,KeyListener,WindowListener,ItemListener
{

	private JPanel contentPane;
	private JTextField txtfees;
	private JTextField txtduration;
	private Connection con;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCourse frame = new UpdateCourse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	JComboBox cmbcourse;
	
	void createcomponent() //creating component
	{
		this.addWindowListener(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateCourse.class.getResource("/ams/images/update.png")));
		setTitle("Update Course");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblucn = new JLabel("Update Course Details");
		lblucn.setForeground(Color.RED);
		lblucn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblucn.setBounds(132, 24, 199, 27);
		contentPane.add(lblucn);
		
		cmbcourse = new JComboBox();
		cmbcourse.setModel(new DefaultComboBoxModel(new String[] {"---Select Course---"}));
		cmbcourse.setBounds(142, 61, 203, 27);
		fillCombo();
		cmbcourse.addItemListener(this);//register the listener
		contentPane.add(cmbcourse);
		
		JLabel lblfees = new JLabel("Fees");
		lblfees.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblfees.setForeground(Color.BLUE);
		lblfees.setBounds(64, 98, 45, 27);
		contentPane.add(lblfees);
		
		txtfees = new JTextField();
		txtfees.setToolTipText("Enter Fees");
		txtfees.setForeground(new Color(0, 0, 0));
		txtfees.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		txtfees.setBounds(152, 98, 184, 25);
		contentPane.add(txtfees);
		txtfees.setColumns(10);
		
		JLabel lbdduration = new JLabel("Duration");
		lbdduration.setForeground(Color.BLUE);
		lbdduration.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lbdduration.setBounds(64, 147, 77, 27);
		contentPane.add(lbdduration);
		
		txtduration = new JTextField();
		txtduration.setToolTipText("Enter Duration of Course");
		txtduration.setForeground(Color.BLACK);
		txtduration.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		txtduration.setColumns(10);
		txtduration.setBounds(152, 147, 184, 25);
		contentPane.add(txtduration);
		
		JButton btnupdate = new JButton("Update");
		btnupdate.setBackground(Color.CYAN);
		btnupdate.setForeground(Color.MAGENTA);
		btnupdate.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		btnupdate.setBounds(152, 201, 103, 27);
		contentPane.add(btnupdate);
		btnupdate.addActionListener(this);
		btnupdate.addKeyListener(this);
		
	}
	void update()
	{
		String fees = txtfees.getText().trim();
		String duration =txtduration.getText().trim();
		
		if(fees.isEmpty()||duration.isEmpty()||cname.equalsIgnoreCase("---Select Course---"))
		{
			JOptionPane.showMessageDialog(this, "Please enter vaild data for updation");
		}
		else
		{
			PreparedStatement ps=null;
			String strupdate="update course_details set course_fees=?,course_duration=? where course_name=?";
			try
			{
				ps=con.prepareStatement(strupdate);
				ps.setInt(1, Integer.parseInt(fees));
				ps.setString(2,duration);
				ps.setString(3,cname);
				int status =ps.executeUpdate();
				if(status>0)
					JOptionPane.showMessageDialog(this,cname+" Course update Successfully" );
				
			} 
			catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			}
			finally {
				try {
					if (ps!=null)
						ps.close();
					
				} catch (SQLException se) {
					// TODO: handle exception
					se.printStackTrace();
				}
			}
		}
	}
	
	
	public void fillCombo()
	{
		PreparedStatement ps=null;
		ResultSet rs=null;//it will hold the reference of the resultant data return by select query
		String strsql="select course_name from course_details";
		try
		{
			ps=con.prepareStatement(strsql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				String name=rs.getString("course_name");
				//System.out.println(name);
				cmbcourse.addItem(name);//add data into combo box
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
			} catch (SQLException se) 
			{
				// TODO: handle exception
				se.printStackTrace();
			}
		}
		
	}
	/**
	 * Create the frame.
	 */
	public UpdateCourse() 
	{
		con=DbConnection.openConnection();//it will connect the frame with database->admission_db
		
		createcomponent();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		update();
		
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
			update();
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
	String cname;
	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		// TODO Auto-generated method stub
		
		int state =e.getStateChange();
		
		
		if (state ==1)
		{
			cname=(String)cmbcourse.getSelectedItem();//fetch the value from combo box
			if(cname.equalsIgnoreCase("---Select Course---"))
			{
				JOptionPane.showMessageDialog(this, "please enter valid course");
			}
			//type cast with string because we have added string items
			
			else
			{
			System.out.println(cname);
		    PreparedStatement ps=null;
    		ResultSet rs=null;
    		String strsearch="select*from course_details where course_name=?";
    		try 
    		{
    			ps=con.prepareStatement(strsearch);
    			ps.setString(1,cname);
    			rs=ps.executeQuery();
    			
    			rs.next();// it will move the cursor inside the dataset 
    			String fees =rs.getString("course_fees");
    			String duration=rs.getString("course_duration");
    			txtfees.setText(fees);
    			txtduration.setText(duration);
    			
				
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
			
		}
		
	}