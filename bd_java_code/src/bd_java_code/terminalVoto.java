package bd_java_code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;

import org.omg.Messaging.SyncScopeHelper;

public class terminalVoto extends Thread {
	public ServidorTCP server;
	private Pessoa pessoa;
	private Socket socketTerminal;
	
	private BufferedReader readMesa;
	private PrintWriter writeMesa;
	
	public terminalVoto(ServidorTCP server,Socket socket) {
		// TODO Auto-generated constructor stub
		this.server = server;
		this.socketTerminal = socket;
		this.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Nova thread");
		synchronized (server) {
			if(server.terminaisDisponiveis==null || server.pessoasParaVotar==null){
				return;
			}
			if(server.terminaisDisponiveis.size()>0){
				this.socketTerminal = server.terminaisDisponiveis.remove(0);
				if(server.pessoasParaVotar.size()>0){
					this.pessoa = server.pessoasParaVotar.remove(0);
					comunicacao();
				}
				else{
					server.terminaisDisponiveis.add(this.socketTerminal);
					return;
				}
			}
			
		}
		
		return;
		
	}
	
	private void comunicacao(){
		try {
			readMesa = new BufferedReader(new InputStreamReader(this.socketTerminal.getInputStream()));
			writeMesa = new PrintWriter(this.socketTerminal.getOutputStream(), true);
			
			writeMesa.println("Utilizador: "+pessoa.getNcc());
			writeMesa.print("Senha: ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
