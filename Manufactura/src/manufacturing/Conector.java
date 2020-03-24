package manufacturing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conector {

	//Atribustos de clase
	
	private static Connection con;
	private static Conector INSTANCE = null;
	
	//Constructor
	
	private Conector(){
		
	}
	
	//Crear instancia
	
	private synchronized static void crearInstancia() {
		if (INSTANCE == null)	{
			INSTANCE = new Conector();
			crearConexion();
		}
		
	}
	
	// Obtener instancia
	
	public static Conector getInstancia(){
		if (INSTANCE == null)	{
			crearInstancia();
		}
		return INSTANCE;
	}
	
	//Crear conexion
	
	private static void crearConexion() {
		String host = "127.0.0.1";
		String user = "root";
		String password = "familiadenico";
		String dataBase = "manufacturing";
		try {
			//Importando la libreria de conexion
			Class.forName("com.mysql.jdbc.Driver");
			String urlConexion = "jdbc:mysql://"+host+"/"+dataBase+"?user="+user+"&password="+password;
			con = DriverManager.getConnection(urlConexion);
			System.out.println("Lo lograste :)");
			
		}catch(Exception e) {
			System.out.println("Error al conectar la base de datos :(");
		}
		
	}
	
	public ArrayList<String> getNames() throws SQLException {
		ArrayList<String> listaNames = new ArrayList<String>(); 
		PreparedStatement ps = con.prepareStatement("Select name from clients \r\n" + 
				"inner join orders on clients.username = orders.clientusername\r\n" + 
				"where orders.status = 'Done'");
		ResultSet rs = ps.executeQuery();
		System.out.println("\nConsulta 1");
		while(rs.next()) {
			listaNames.add(rs.getString("name"));
		}
		rs.close();
		return listaNames;
	}
	
	
	public ArrayList<String> getCostAndTransport() throws SQLException {
		ArrayList<String> listaCostTransport = new ArrayList<String>(); 
		PreparedStatement ps = con.prepareStatement("Select amount, modeoftransport from invoice\r\n" + 
				"inner join delivery on delivery.orderid = invoice.orderid\r\n" + 
				"inner join clients on delivery.clntusername = clients.username\r\n" + 
				"where clients.name = 'New Client 1';");
		ResultSet rs = ps.executeQuery();
		System.out.println("\nConsulta 2");
		while(rs.next()) {
			listaCostTransport.add(rs.getString("amount")+" "+ rs.getString("modeoftransport"));
		}
		rs.close();
		return listaCostTransport;
	}
	
	public ArrayList<String> getEmails() throws SQLException {
		ArrayList<String> listaEmails = new ArrayList<String>(); 
		PreparedStatement ps = con.prepareStatement("Select email from employee\r\n" + 
				"left join attendance on employee.username = attendance.empusername\r\n" + 
				"where value is null;");
		ResultSet rs = ps.executeQuery();
		System.out.println("\nConsulta 3");
		while(rs.next()) {
			listaEmails.add(rs.getString("email"));
		}
		rs.close();
		return listaEmails;
	}
}