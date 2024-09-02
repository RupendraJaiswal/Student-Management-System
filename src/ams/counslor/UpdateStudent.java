package ams.counslor;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ams.admin.UpdateCourse;
import ams.dbutils.DbConnection;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class UpdateStudent extends JFrame implements ActionListener,KeyListener,WindowListener,ItemListener
{

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtphone;
	private JTextField txtemail;
	private JTextArea txtaddress;
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateStudent frame = new UpdateStudent();
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
	JComboBox cmbid;
	void createComponent()
	{
		this.addWindowListener(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateCourse.class.getResource("/ams/images/update.png")));
		setTitle("Update student Details");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 595, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	    cmbid = new JComboBox();
		cmbid.setModel(new DefaultComboBoxModel(new String[] {" ---Select ID---"}));
		cmbid.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		cmbid.setBounds(217, 15, 154, 30);
		fillCombo();
		cmbid.addItemListener(this);
		contentPane.add(cmbid);
		
		JLabel lblsid = new JLabel("Student ID");
		lblsid.setBackground(Color.MAGENTA);
		lblsid.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblsid.setBounds(42, 22, 114, 17);
		contentPane.add(lblsid);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(42, 74, 114, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblPhone.setBounds(42, 128, 114, 30);
		contentPane.add(lblPhone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblEmail.setBounds(42, 168, 114, 30);
		contentPane.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblAddress.setBounds(42, 222, 114, 30);
		contentPane.add(lblAddress);
		
		txtname = new JTextField();
		txtname.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		txtname.setEditable(false);
		txtname.setBounds(217, 77, 154, 30);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		txtphone = new JTextField();
		txtphone.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		txtphone.setColumns(10);
		txtphone.setBounds(217, 128, 154, 30);
		contentPane.add(txtphone);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		txtemail.setColumns(10);
		txtemail.setBounds(217, 171, 154, 30);
		contentPane.add(txtemail);
		
		txtaddress = new JTextArea();
		txtaddress.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		txtaddress.setBounds(217, 227, 164, 72);
		contentPane.add(txtaddress);
		
		JButton btnupdate = new JButton("UPDATE");
		btnupdate.setBackground(Color.CYAN);
		btnupdate.setForeground(Color.MAGENTA);
		btnupdate.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		btnupdate.setBounds(189, 330, 141, 37);
		contentPane.add(btnupdate);
		btnupdate.addActionListener(this);
		btnupdate.addKeyListener(this);
		
	}
	void update()
	{
		String phone= txtphone.getText().trim();
		String email =txtemail.getText().trim();
		String address = txtaddress.getText().trim();
		
		
		if(Sid.equalsIgnoreCase("---Select Course---"))
		{
			JOptionPane.showMessageDialog(this, "please enter valid course");
		}
		else
		{
			PreparedStatement ps=null;
			String strupdate="update student_details set phone=?,email=?,address=? where id=?";
			try
			{
				ps=con.prepareStatement(strupdate);
				ps.setString(1,phone);
				ps.setString(2,email);
				ps.setString(3,address);
				ps.setString(4,Sid);
				int status =ps.executeUpdate();
				if(status>0)
					JOptionPane.showMessageDialog(this,Sid+" Student update Successfully" );
				
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
		String strsql="select id from student_details";
		try
		{
			ps=con.prepareStatement(strsql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				String id=rs.getString("id");
				//System.out.println(name);
				cmbid.addItem(id);//add data into combo box
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
	public UpdateStudent() {
		con=DbConnection.openConnection();
		createComponent();
	}

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
	System.out.println("Connection is being closed");
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
String Sid;
@Override
public void itemStateChanged(ItemEvent e) 
{
	// TODO Auto-generated method stub
	
	int state =e.getStateChange();
	
	
	if (state ==1)
	{
		Sid=(String)cmbid.getSelectedItem();//fetch the value from combo box
		if(Sid.equalsIgnoreCase("---Select Course---"))
		{
			JOptionPane.showMessageDialog(this, "please enter valid course");
		}
		//type cast with string because we have added string items
		
		else
		{
		System.out.println(Sid);
	    PreparedStatement ps=null;
		ResultSet rs=null;
		String strsearch="select*from student_details where id=?";
		try 
		{
			ps=con.prepareStatement(strsearch);
			ps.setString(1,Sid);
			rs=ps.executeQuery();
			
			rs.next();// it will move the cursor inside the dataset 
			String name =rs.getString("name");
			String phone=rs.getString("phone");
			String email=rs.getString("email");
			String address=rs.getString("address");
			txtname.setText(name);
			txtphone.setText(phone);
			txtemail.setText(email);
			txtaddress.setText(address);
			
			
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
