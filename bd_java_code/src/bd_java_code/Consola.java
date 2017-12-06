package bd_java_code;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Consola extends UnicastRemoteObject {
	
	private RMI_1 comunicacao;
	
	public Consola () throws RemoteException {
		super();
		
	}

	public static void main(String[] args) {
		
		try {
		Consola consola = new Consola();
		consola.comunicacao = (RMI_1) Naming.lookup("rmi://192.168.0.1/rmi");
		}
		catch (RemoteException | MalformedURLException | NotBoundException e){
			
		}
		
	}

}
