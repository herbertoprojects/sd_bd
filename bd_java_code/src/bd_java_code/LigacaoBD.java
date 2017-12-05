package bd_java_code;

import java.sql.*;

public class LigacaoBD {
	
	private Connection conn;
	private String baseDadosURL;
	private String baseDadosUser;
	private String baseDadosPassword;
	
	public LigacaoBD() {
		// TODO Auto-generated constructor stub
		baseDadosURL = "jdbc:oracle:thin:@localhost:1521:xe";
		baseDadosPassword = "proj";
		baseDadosUser = "proj";
		
		System.out.println(conectaBD());//Serve para conectar a base de dados
	}
	
	public boolean conectaBD (){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			return (conn = DriverManager.getConnection(baseDadosURL, baseDadosUser, baseDadosPassword)) != null;
		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			  System.out.println(e.getMessage());
			  return false;
		}
	}
	
	

}
