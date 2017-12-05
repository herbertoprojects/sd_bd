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
		
		conectaBD();//Serve para conectar a base de dados
	}
	
	public boolean conectaBD (){
		try {
			conn = DriverManager.getConnection(baseDadosURL, baseDadosUser, baseDadosPassword);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
}
