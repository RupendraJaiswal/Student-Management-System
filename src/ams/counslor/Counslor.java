package ams.counslor; 

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

import ams.admin.AddCourse;
import ams.admin.DeleteCourse;
import ams.admin.SearchCourse;
import ams.admin.UpdateCourse;
import ams.gui.AllCourses;
import ams.gui.AllStudent;
import ams.gui.CoursewiseStudent;

import java.awt.Frame;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.event.*;


public class Counslor extends JFrame implements WindowListener,ActionListener
{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Counslor frame = new Counslor();
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
	public Counslor() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Counslor Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnEnrollment = new JMenu("Enrollment");
		mnEnrollment.setIcon(new ImageIcon(Counslor.class.getResource("/ams/images/enrollment.png")));
		menuBar.add(mnEnrollment);
		
		JMenuItem mi_add = new JMenuItem("Add");
		mi_add.setIcon(new ImageIcon(Counslor.class.getResource("/ams/images/add.png")));
		mnEnrollment.add(mi_add);
		mi_add.addActionListener(this);
		
		JMenuItem mi_update = new JMenuItem("Update");
		mi_update.setIcon(new ImageIcon(Counslor.class.getResource("/ams/images/update.png")));
		mnEnrollment.add(mi_update);
		mi_update.addActionListener(this);
		
		
		JMenuItem mi_delete = new JMenuItem("Delete");
		mnEnrollment.add(mi_delete);
		mi_delete.addActionListener(this);
		
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
		
		JMenu mnSearch = new JMenu("Search");
		mnSearch.setIcon(new ImageIcon(Counslor.class.getResource("/ams/images/search.png")));
		menuBar.add(mnSearch);
		mnSearch.addActionListener(this);
		
		JMenuItem mi_course = new JMenuItem("Course");
		mnSearch.add(mi_course);
		mi_course.addActionListener(this);
		
		JMenuItem mi_id = new JMenuItem("ID");
		mnSearch.add(mi_id);
		mi_id.addActionListener(this);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 204));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
		String caption = e.getActionCommand();
		switch(caption)
		{
		case "Add":
		{
			AddStudent st=new AddStudent();
			st.setVisible(true);
			break;
		}
		case "Update":
		{
			UpdateStudent us=new UpdateStudent();
			us.setVisible(true);
			break;
		}
		
		
		case "Delete":
		{
			DeleteStudent ds=new DeleteStudent();
			ds.setVisible(true);
			break;
		}
		case "Course":
		{
			SearchCourse sc=new SearchCourse();
			sc.setVisible(true);
			break;
		}
		case "ID":
		{
			SearchStudent sc=new SearchStudent();
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
}
