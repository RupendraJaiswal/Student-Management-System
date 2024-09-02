package ams.dbutils;
import java.sql.*;
public class DbConnection 
{
	private static Connection con;
	public static Connection openConnection ()
	{
		try 
		{
		
			Class.forName("com.mysql.cj.jdbc.Driver");//it is used to create object of driver class
			//factory method-> is used to instantiate the class
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/admission_db","root","root");
		} 
		
		catch(ClassNotFoundException|SQLException cse)
		{
			cse.printStackTrace();
		}
		return con;
	}

	/*public static void main(String[] args)
	{
		
		Connection con= openConnection();
		System.out.println(con);
	}
	*/
	public static void closeConnection()
	{
		try {
			if (con!=null)
				con.close();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
	}
	
}
