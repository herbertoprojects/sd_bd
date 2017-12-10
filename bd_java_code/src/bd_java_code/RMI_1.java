package bd_java_code;
import java.util.*;
import java.rmi.*;

public interface RMI_1 extends Remote{
	
	public boolean registaPessoa(Pessoa pessoa) throws RemoteException;
	public boolean modificaPessoa(Pessoa pessoa) throws RemoteException;
	public boolean removePessoa(Pessoa pessoa) throws RemoteException;
	public Pessoa procuraPessoa(int ncc)throws RemoteException;
	
	public ArrayList <Departamento> ListDepartamentos(Faculdade faculdade) throws RemoteException;
	public boolean addDepartamento(Departamento dep)throws RemoteException;
	public boolean removeDepartamento(Departamento dep)throws RemoteException;
	public Departamento procuraDepartamento(String siglaDep, String siglaFac) throws RemoteException;
	
	public ArrayList <Faculdade> ListFaculdades() throws RemoteException;
	public boolean addFaculdade(Faculdade fac)throws RemoteException;
	public boolean removeFaculdade(Faculdade fac)throws RemoteException;
	public Faculdade procuraFaculdade(String sigla)throws RemoteException;
	
	
	public boolean novaEleicao(Eleicao eleicao) throws RemoteException;
	public boolean removerEleicao(Eleicao eleicao) throws RemoteException;
	public ArrayList <Eleicao> listaEleicao() throws RemoteException;
	public Eleicao procuraEleicao(int id) throws RemoteException;
	
	
	public boolean addCandidato(Candidatos candidato, Eleicao eleicao) throws RemoteException;
	public boolean removeCandidato(Candidatos candidato, Eleicao eleicao) throws RemoteException;
	public ArrayList<Candidatos>listaCandidatos(Eleicao eleicao) throws RemoteException;
	public Lista procuraLista(Eleicao eleicao,int candidato) throws RemoteException;
	public CandidatoIndividual procuraIndividual(Eleicao eleicao, int candidato) throws RemoteException;

	public boolean addMesaVoto(MesaVoto mesaVoto) throws RemoteException;
	public boolean removeMesaVoto(MesaVoto mesaVoto) throws RemoteException;
	public ArrayList <MesaVoto> listMesaVoto(Eleicao eleicao,Departamento dep) throws RemoteException;
	
	public boolean addPessoaLista(PessoaLista pessoaLista,Lista lista) throws RemoteException;
	public boolean removePessoaLista(PessoaLista pessoaLista,Lista lista) throws RemoteException;
	public ArrayList <PessoaLista> listaPessoaLista(Lista lista) throws RemoteException;
	
	
	//Ainda não estao modificadas, ainda para pensar
	public String ligarServidor(String nomeMesaVoto,String passwordMesaVoto) throws RemoteException;
	public boolean desbloquearUser(String nomeMesaVoto, String passwordMesaVoto, int nCC, int elect) throws RemoteException;
	public boolean desbloquearVoto(String nomeMesaVoto, String passwordMesaVoto, int nCC, int id_elect) throws RemoteException;
	public boolean bloquearVoto(String nomeMesaVoto,String passwordMesaVoto, int nCC, int id_elect) throws RemoteException;
	public boolean votar(String nomeMesaVoto, String passwordMesaVoto, int nCC, String passwordUser, int id_elei, int id_lista_voto) throws RemoteException;
	 
}
