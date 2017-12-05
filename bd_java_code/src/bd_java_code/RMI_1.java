package bd_java_code;
import java.util.*;
import java.rmi.*;
import java.sql.*;

public interface RMI_1 extends Remote{
	
	/*1 - Registar pessoas. Deve poder-se registar estudantes, docentes e funcionários. 
	Deverá guardar toda a informação pessoal que considere necessária, bem como uma password 
	(código de acesso) e o departamento/faculdade ao qual a pessoa pertence. A informação pessoal 
	deverá incluir também dados de contacto telefónico, morada, número e validade do cartão de cidadão. */
	
	// return int - retorna um numero sendo 1 - registado e 0 - não registado, podemos depois acrecentar outras opções
	// String tipo - estudante/docente/funcionario
	// String nome - nome da pessoa separado por espaço ex."XXXXXXXX XXXXXXXXXXXX XXXXXXX XXXXXXX"
	// String password - apenas números para evitar uso de símbolos que entrem em conflito com a comunicação tcp
	// String faculdade - nome da faculdade
	// String departamento - nome do departamento
	// int telefone - numero de telefone
	
	public String registar(Pessoa pessoa) throws RemoteException;
	
	
	public ArrayList <Departamento> ListDepartamentos(Faculdade faculdade) throws RemoteException;//retorna a lista de departamentos de uma faculdade
	public ArrayList <Faculdade> ListFaculdades() throws RemoteException;
	
	
	//Gerir departamentos
	public boolean addDepartamento(Departamento dep)throws RemoteException;
	public boolean removeDepartamento(Departamento dep)throws RemoteException;
	
	
	//Gerir faculdades
	public boolean addFaculdade(Faculdade fac)throws RemoteException;
	public boolean removeFaculdade(Faculdade fac)throws RemoteException;
	
	
	
	//Criar eleição; Tipo->núcleo de estudantes, conselho geral, direção de departamento, direção de faculdade
	//tipo de eleição -> NEstudante CGeral DDepartamento DFaculdade
	public String criaEleicao(String tipo, String inicio, String fim, String titulo, String descricao, int id) throws RemoteException; // retorna o número da eleição
	public boolean removeEleicao(int id_eleicao) throws RemoteException;
	public ArrayList <String> listEleicao(String inicio, String fim) throws RemoteException;//pesquisa num intervalo de tempo, para pesquisar todas deixar a null
	public ArrayList <String> listEleicao() throws RemoteException;
	

	//Gerir listas de candidatos a uma eleição
	public String addListaCandidatos(int id, int id_eleicao, String membro1, String membro2, String membro3, String membro4, String membro5) throws RemoteException;
	public boolean removeListaCandidatos(int id_LC) throws RemoteException;
	public ArrayList <String> listListasCandidatos(int id_elei)throws RemoteException;
	
	/*
	public boolean addCandidato(String Eleicao,String lista)throws RemoteException;
	public boolean removeCandidato(String Eleicao, String lista)throws RemoteException;
	public ArrayList <String> listCandidato(String Eleicao, String lista)throws RemoteException;
	*/
	
	//Gerir mesas de voto
	public String addMesaVoto(int id, int id_depart, int id_faculd, int id_elei, String user, String pass) throws RemoteException;
	public String addMesaVoto(int id, int id_faculd, int id_elei, String user, String pass) throws RemoteException;
	public boolean removeMesaVoto(int id_MV) throws RemoteException;
	public ArrayList <String> listMesaVoto(int id_elei) throws RemoteException;
	public ArrayList <String> listMesaVoto() throws RemoteException;

	//public void mensagemRealTime(int eleicao,dadosEleicoes imprimir) throws RemoteException;
	

	public String ligarServidor(String nomeMesaVoto,String passwordMesaVoto) throws RemoteException;
	
	public boolean desbloquearUser(String nomeMesaVoto, String passwordMesaVoto, int nCC, int elect) throws RemoteException;
	//verificar se o user existe nesta eleicao (exemplo na eleicao de detartamento se pertence ao departamento)
	//verificar se o user ja votou
	//e bloquear o user para que não vote em mais nenhuma mesa
	
	public boolean desbloquearVoto(String nomeMesaVoto, String passwordMesaVoto, int nCC, int id_elect) throws RemoteException;
	public boolean bloquearVoto(String nomeMesaVoto,String passwordMesaVoto, int nCC, int id_elect) throws RemoteException;

	//verificar se o user está bloqueado a esta mesa de voto
	//verificar se a password corresponde
	
	public boolean votar(String nomeMesaVoto, String passwordMesaVoto, int nCC, String passwordUser, int id_elei, int id_lista_voto) throws RemoteException;
	//verificar se o user esta bloqueado a esta mesa de voto
	//verificar se a password corresponde
	//inserir o voto
	
	//lista de departamento dando a faculdade como string 
	//fazer
	public ArrayList<String> ListDepartamentos(String faculdadeTemp) throws RemoteException;
	 
}
