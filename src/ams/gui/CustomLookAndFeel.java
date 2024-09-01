package ams.gui;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class CustomLookAndFeel 
{


public static void setLookAndFeel()
{
	try 
	{
		/*for(LookAndFeelInfo lf:UIManager.getInstalledLookAndFeels())
		{
			System.out.println(lf.getClassName());
		}*/
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	} 
	catch (Exception e) 
	{
		// TODO: handle exception
		e.printStackTrace();
	}
}
/*public static void main(String[] args) 
{
	setLookAndFeel();
}*/
}