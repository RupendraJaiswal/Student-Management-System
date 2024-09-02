package ams.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.ImageIcon;

public class SplashScreen {

	private JFrame frmWelcome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try 
				{
					SplashScreen window = new SplashScreen();
					window.frmWelcome.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public void showFrame()
	{
		Thread t=new Thread(
				new Runnable() {
					
					@Override
					public void run()
					{
						
						try 
						{
							Thread.sleep(3000);
							Login login=new Login();
							login.setVisible(true);
							frmWelcome.dispose();
						}
						catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				);
		t.start();//start the thread
		
	}

	/**
	 * Create the application.
	 */
	public SplashScreen()
	{
		initialize();
		showFrame();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWelcome = new JFrame();
		frmWelcome.setTitle("Welcome ");
		frmWelcome.setBounds(100, 100, 760, 489);
		frmWelcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWelcome.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("!!!!AMS Welcomes You!!!!");
		lblNewLabel.setForeground(Color.MAGENTA);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(335, 20, 411, 35);
		frmWelcome.getContentPane().add(lblNewLabel);
		
		ImageIcon ic= new ImageIcon(SplashScreen.class.getResource("/ams/images/welcomeimg.jpg"));
		Image i2=ic.getImage().getScaledInstance(746,452,Image.SCALE_DEFAULT);
		ImageIcon ic1=new ImageIcon(i2);
		
		
		JLabel lblNewLabel_1 = new JLabel(ic1);
		
		lblNewLabel_1.setBounds(0, 0, 746, 452);
		frmWelcome.getContentPane().add(lblNewLabel_1);
	}

}
