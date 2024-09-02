package ams.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

public class Demo_Calender extends JFrame implements ActionListener
{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Demo_Calender frame = new Demo_Calender();
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
	JDateChooser dateChooser;
	public Demo_Calender() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnok = new JButton("OK");
		btnok.setBackground(Color.MAGENTA);
		btnok.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		btnok.setBounds(197, 91, 118, 32);
		contentPane.add(btnok);
		btnok.addActionListener(this);
		
		dateChooser = new JDateChooser();
		
		dateChooser.setBounds(51, 91, 118, 32);
		contentPane.add(dateChooser);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//System.out.println("clicked");
		java.util.Date d=dateChooser.getDate();
		System.out.println(d);
		java.sql.Date sd= new Date(d.getTime());// long value
		System.out.println(sd);
		
		
	}
}
