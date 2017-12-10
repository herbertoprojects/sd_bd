package bd_java_code;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Consola extends UnicastRemoteObject {
	
	private static final long serialVersionUID = -2292307713953902247L;
	private RMI_1 comunicacao;
	public int portoConsola = 9000;
	
	public Consola () throws RemoteException {
		super();
		
	}

	public static void main(String[] args) {
		
		try {
			Consola consola = new Consola();
			consola.comunicacao = (RMI_1) Naming.lookup("rmi://localhost:"+consola.portoConsola+"/rmi");
			consola.menuInicial();
		}
		catch (RemoteException | MalformedURLException | NotBoundException e){
			System.out.println("Erro ligacao RMI");
			
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
			System.out.println("4- Gerir eleições");						//criar eleição, adicionar listas, remover listas, consultar listas, consultar eleições, remover eleições			System.out.println("6- Voto antecipado");						//autenticar a pessoa
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
					Pessoa pessoa = pedePessoa();
					if(pessoa!=null){
						if(comunicacao.registaPessoa(pessoa)){
							System.out.println("Utilizador Adicionado");
						}
						else{
							System.out.println("Utilizador Não Adicionado");
						}
					}
					
					break;
				case 2:
					int ncc = textEditor.pedeNumero("Ncc: ", 9999999, 100000000);
					if(comunicacao.removePessoa(comunicacao.procuraPessoa(ncc))){
						System.out.println("Utilizador Removido");
					}else{
						System.out.println("Utilizador não Removido");
					}
					break;
				case 3:
					int ncc1 = textEditor.pedeNumero("Ncc: ", 9999999, 100000000);
					Pessoa pessoa1 = comunicacao.procuraPessoa(ncc1);
					System.out.println("Nome: "+pessoa1.getNome());
					System.out.println("Departamento: " +pessoa1.getDep()!=null?pessoa1.getDep().getNome():"Sem Departamento");
					System.out.println("Faculdade: " +pessoa1.getFac()!=null?pessoa1.getFac().getNome():"Sem Faculdade");

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
				Faculdade fac1 = pedeFaculdade();
				if(fac1!=null){
					if(comunicacao.addFaculdade(fac1)){
						System.out.println("Faculdade adicionada com sucesso");
					}else{
						System.out.println("Faculdade não adicionada");
					}
				}
				break;
			case 2:
				Faculdade fac = escolheFaculdade();
				if(fac!=null){
					if(comunicacao.removeFaculdade(fac)){
						System.out.println("Faculdade Removida");
					}else{
						System.out.println("Faculdade não removida");
					}
				}
				
				break;
			case 3:
				for(Faculdade facTemp:comunicacao.ListFaculdades()){
					System.out.printf("%s %s\n", facTemp.getSigla(),facTemp.getNome());
				}
				break;
				
			case 0:
				return;
			
			}
			textEditor.leLinha("Continuar...");
		}
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
					Departamento dep = pedeDepartamento();
					if(dep!=null){
						if(comunicacao.addDepartamento(dep)){
							System.out.println("Departamento adicionado");
						}else{
							System.out.println("Departamento não adicionado");
						}
					}
					break;
				case 2:
					Departamento dep1 = escolheDepartamento();
					if(dep1!=null){
						if(comunicacao.removeDepartamento(dep1)){
							System.out.println("Departamento removido com sucesso");
						}
						else{
							System.out.println("Departemnto não removido");
						}
					}
					break;
					
				case 3:
					for(Faculdade facTemp:comunicacao.ListFaculdades()){
						System.out.printf("%s %s\n", facTemp.getSigla(),facTemp.getNome());
						for(Departamento depTemp:comunicacao.ListDepartamentos(facTemp)){
							System.out.printf("    %s %s\n", depTemp.getSigla(),depTemp.getNome());
						}
						System.out.println("");
					}
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
					//TODO
					break;
				case 2:
					//TODO
					break;
				case 3:
					//TODO
					break;
				case 4:
					//TODO
					break;
				case 5:
					//TODO
					break;
				case 6:
					//TODO
					break;
				case 7:
					//TODO
					break;
				case 8:
					//TODO
					break;
				case 9:
					//TODO
					break;
				case 0:
					return;
			}
			textEditor.leLinha("Continuar...");
		}
		
	}
	
	public Pessoa pedePessoa() throws RemoteException{
		Pessoa pessoa = new Pessoa();
		pessoa.setNcc(textEditor.pedeNumero("Ncc: ", 9999999, 100000000));
		pessoa.setNome(textEditor.leLinha("Nome: "));
		pessoa.setMorada(textEditor.leLinha("Morada: "));
		pessoa.setSenha(textEditor.leLinha("Senha: "));
		pessoa.setTelefone(textEditor.pedeNumero("Telefone: ", 99999999, 1000000000));
		pessoa.setDep(escolheDepartamento());
		pessoa.setFac(pessoa.getDep().getFac());
		System.out.println("Cargo: ");
		System.out.println("1- Professor");
		System.out.println("2- Aluno");
		System.out.println("3- Funcionario");
		switch (textEditor.pedeNumero("opção:" , 1, 3)) {
			case 1:
				pessoa.setCargo("professor");
				break;
			case 2:
				pessoa.setCargo("aluno");
				break;
			case 3:
				pessoa.setCargo("funcionario");
				break;
		}
		System.out.println("1- Confirmar");
		System.out.println("0- Cancelar");
		if(textEditor.pedeNumero("opção: ", 0, 1)==1){
			return pessoa;
		}
		return null;
	}
	public Faculdade pedeFaculdade(){
		Faculdade fac = new Faculdade();
		fac.setSigla(textEditor.leLinha("Sigla: "));
		fac.setNome(textEditor.leLinha("Nome: "));
		System.out.println("1- Confirmar");
		System.out.println("0- Cancelar");
		if(textEditor.pedeNumero("opção: ", 0, 1)==1){
			return fac;
		}
		return null;
	}
	public Departamento pedeDepartamento() throws RemoteException{
		Departamento dep = new Departamento();
		dep.setSigla(textEditor.leLinha("Sigla: "));
		dep.setNome(textEditor.leLinha("Nome: "));
		dep.setFac(escolheFaculdade());
		System.out.println("1- Confirmar");
		System.out.println("0- Cancelar");
		if(textEditor.pedeNumero("opção: ", 0, 1)==1){
			return dep;
		}
		return null;
	}
	public Faculdade escolheFaculdade() throws RemoteException{
		int num = 0;
		ArrayList<Faculdade> faculdades = comunicacao.ListFaculdades();
		for(Faculdade facTemp:faculdades){
			System.out.println(num+"- "+facTemp.getSigla()+" "+facTemp.getNome());
			num ++;
		}
		return faculdades.get(textEditor.pedeNumero("Numero: ",0, num-1));
	}
	public Departamento escolheDepartamento() throws RemoteException{
		int num = 0;
		Faculdade fac = escolheFaculdade();
		ArrayList<Departamento> departamentos = comunicacao.ListDepartamentos(fac);
		for(Departamento depTemp:departamentos){
			System.out.printf("%d- %s %s\n",num, depTemp.getSigla(),depTemp.getNome());
			num++;
		}
		return departamentos.get(textEditor.pedeNumero("Numero: ", 0, num-1));
	}

}
