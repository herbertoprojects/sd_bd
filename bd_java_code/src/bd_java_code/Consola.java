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
		consola.menuInicial();
		consola.comunicacao = (RMI_1) Naming.lookup("rmi://192.168.0.1/rmi");
		
		}
		catch (RemoteException | MalformedURLException | NotBoundException e){
			
		}
	}
	
	public void menuInicial() throws RemoteException {
		/**
		 * menu inicial
		 * @return void saída do menu inicial
		 */
		while(true) {
			System.out.println();
			System.out.println("------------MAIN MENU-----------");
			System.out.println("1- Gerir utilizador");						//adicionar, remover, consultar
			System.out.println("2- Gerir faculdades");						//adicionar, remover, consultar
			System.out.println("3- Gerir departamentos");					//adicionar, remover, consultar
			System.out.println("4- Gerir eleições");						//criar eleição, adicionar listas, remover listas, consultar listas, consultar eleições, remover eleições
			System.out.println("5- Dados de eleições (Real Time)");			//escolher qual a eleição que está a correr e recebe eleições e começamos a receber as notificações
			System.out.println("6- Voto antecipado");						//autenticar a pessoa
			System.out.println("0- Sair");
			
			switch (textEditor.pedeNumero("Opção: ", 0, 6)) {
				case 1:
					menuUtil();	
					break;
				case 2:
					menuFac();
					break;
				case 3:
					menuDep();
					break;
				case 4:
					menuEleicoes();
					break;
				case 5:

					break;
				case 6:
					break;
				case 0:
					return;
					
			}
			textEditor.leLinha("Continuar...");
		}
	}
	
	public void menuUtil() throws RemoteException{
		while(true) {
			System.out.println();
			System.out.println("------------Sub Menu do Utilizador------------");
			System.out.println("1- Adicionar utilizador");
			System.out.println("2- Remover utilizador");
			System.out.println("3- Consultar utilizador");
			System.out.println("0- sair");
			
			switch(textEditor.pedeNumero("Opção: ", 0, 3)) {
				case 1:
					
					
					break;
				case 2:
					
					
					break;
				case 3:
					
					
					break;
					
				case 0:
					return;
				
			}
			textEditor.leLinha("Continuar...");
		}
		
	}
	
	
	public void menuFac()throws RemoteException{
		while(true) {
		 	System.out.println("------------Sub Menu das Faculdades------------");
			System.out.println("1- Adicionar faculdade");
			System.out.println("2- Remover faculdade");
			System.out.println("3- Consultar faculdade");
			System.out.println("0- Sair");
			switch(textEditor.pedeNumero("Opção: ", 0, 3)) {
				
			case 1:
				
				break;
			case 2:
				
				break;
			case 3:
				
				break;
				
			case 0:
				return;
			
			}
			textEditor.leLinha("Continuar...");
		}
	}
	
	
	private boolean consultaFac() throws RemoteException {
		

		
		return false;

	}

	public void menuDep() throws RemoteException{
		while(true) {
			System.out.println("------------Sub Menu dos Departamentos------------");
			System.out.println("1- Adicionar departamento");
			System.out.println("2- Remover departamento");
			System.out.println("3- Consultar Departamento");
			System.out.println("0- Sair");
			
			switch(textEditor.pedeNumero("Opção: ", 0, 3)) {
			
				case 1:
					
					break;
					
				case 2:
					
					break;
					
				case 3:
					
					break;
				
				case 0:
					return;
				
					
			}
			textEditor.leLinha("Continuar...");
		}
		
		
	}
	
	public void menuEleicoes() throws RemoteException {
		while(true) {
			System.out.println("------------Sub Menu das Eleições------------");
			
			System.out.println("1- Criar eleição");
			System.out.println("2- Remover eleições");
			System.out.println("3- Consultar eleições");
			System.out.println("4- Adicionar listas");
			System.out.println("5- Remover listas");
			System.out.println("6- Consultar listas");
			System.out.println("7- Adicionar mesa de voto");
			System.out.println("8- remover mesa de voto");
			System.out.println("9- Consultar mesas de voto");
			System.out.println("0- Sair");
			
			switch(textEditor.pedeNumero("Opção: ", 0, 9)) {
				case 1:
					
					break;
				case 2:
						
					break;
				case 3:
						
					break;
				case 4:
					
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;
				case 8:
					break;
				case 9:
					break;
				case 0:
					return;
			}
			textEditor.leLinha("Continuar...");
		}
		
	}

}
