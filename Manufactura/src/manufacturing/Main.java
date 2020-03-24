package manufacturing;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		System.out.println("Probando");
		
		Conector instancia = Conector.getInstancia();
		
		try {
			ArrayList<String> listNames = instancia.getNames();
			for (String name:listNames) {
				System.out.println(name);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		
		try {
			ArrayList<String> listCostTransport = instancia.getCostAndTransport();
			for (String CostTransport:listCostTransport) {
				System.out.println(CostTransport);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	
		
		try {
			ArrayList<String> listEmails = instancia.getEmails();
			for (String Email:listEmails) {
				System.out.println(Email);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
