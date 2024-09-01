package ams.admin;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

import ams.gui.AllCourses;
import ams.gui.AllStudent;
import ams.gui.CoursewiseStudent;
import ams.gui.Login;

import java.awt.Frame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JComboBox;

public class Admin extends JFrame implements ActionListener,WindowListener
{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					Admin frame = new Admin();
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
	JMenuItem mi_addcourse,mi_updatecourse,mi_searchcourse;
	public Admin() 
	{
		this.addWindowListener(this);//register the listener with frame
		setIconImage(Toolkit.getDefaultToolkit().getImage(Admin.class.getResource("/ams/images/edit.png")));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Admin Frame");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCoursedetails = new JMenu("CourseDetails");
		menuBar.add(mnCoursedetails);
		
		mi_addcourse = new JMenuItem("Add");
		mi_addcourse.setIcon(new ImageIcon(Admin.class.getResource("/ams/images/add.png")));
		mi_addcourse.addActionListener(this);
		mnCoursedetails.add(mi_addcourse);
		
		mi_updatecourse = new JMenuItem("Update");
		mi_updatecourse.setIcon(new ImageIcon(Admin.class.getResource("/ams/images/update.png")));
		mi_updatecourse.addActionListener(this);
		mnCoursedetails.add(mi_updatecourse);
		
		mi_searchcourse = new JMenuItem("Search");
		mi_searchcourse.addActionListener(this);
		
		JMenuItem mi_delete = new JMenuItem("Delete");
		mi_delete.setIcon(new ImageIcon(Admin.class.getResource("/ams/images/delete.png")));
		mnCoursedetails.add(mi_delete);
		mi_delete.addActionListener(this);
		mnCoursedetails.add(mi_searchcourse);
		
		
		JMenu mnreport = new JMenu("Report");
		menuBar.add(mnreport);
		
		JMenuItem mi_allcourse = new JMenuItem("All Course");
		mnreport.add(mi_allcourse);
		mi_allcourse.addActionListener(this);
		
		JMenuItem mn_allstudent = new JMenuItem("All Students");
		mnreport.add(mn_allstudent);
		mn_allstudent.addActionListener(this);
		
		JMenuItem mn_coursewisestudent = new JMenuItem("Coursewise Student");
		mnreport.add(mn_coursewisestudent);
		mn_coursewisestudent.addActionListener(this);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 204));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		String caption = e.getActionCommand();
		switch(caption)
		{
		case "Add":
		{
			AddCourse ad=new AddCourse();
			ad.setVisible(true);
			break;
		}
		
		case "Update":
		{
			UpdateCourse uc=new UpdateCourse();
			uc.setVisible(true);
			break;
		}
		
		case "Delete":
		{
			DeleteCourse dc=new DeleteCourse();
			dc.setVisible(true);
			break;
		}
		case "Search":
		{
			SearchCourse sc=new SearchCourse();
			sc.setVisible(true);
			break;
		}
		case "All Course":
		{
			AllCourses as=new AllCourses ();
			as.setVisible(true);
			break;
		}
		case "All Students":
		{
			AllStudent as=new AllStudent();
			as.setVisible(true);
			break;
		}
		case "Coursewise Student":
		{
			CoursewiseStudent as=new CoursewiseStudent();
			as.setVisible(true);
			break;
		}
		
		
	}
		
		
  }

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		//this.dispose();
		Login l= new Login();
		l.setVisible(true);
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
