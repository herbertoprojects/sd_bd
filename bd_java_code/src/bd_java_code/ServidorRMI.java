package bd_java_code;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServidorRMI extends UnicastRemoteObject implements RMI_1 {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	LigacaoBD ligacao;

	protected ServidorRMI() throws RemoteException {
		super();
		ligacao = new LigacaoBD();
		
		Eleicao eleicao = new Eleicao();
		eleicao.setTitulo("teste");
		eleicao.setDescricao("teste v2");
		eleicao.setDataInicio("2017:12:09 01:59");
		eleicao.setDataFim("2017:12:12 01:48");
		eleicao.setnVotoBNA(10);
		eleicao.setTipo("nucleo");
		
		this.novaEleicao(eleicao);
	}

	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		ServidorRMI server = new ServidorRMI();
	}
	
	@Override
	public boolean registaPessoa(Pessoa pessoa) throws RemoteException {
		
		String comando = "INSERT INTO Pessoa values ('";
		comando += pessoa.getNcc();//ncc
		comando += "',";
		if(pessoa.getFac()!=null){
			comando += "'";
			comando += pessoa.getFac().getSigla();//faculdade
			comando += "',";
		}else{
			comando += "null,";
		}
		if(pessoa.getDep()!=null){
			comando += "'";
			comando += pessoa.getDep().getSigla();//departamento
			comando += "',";
			if(pessoa.getDep().getFac()!=null){
				comando += "'";
				comando += pessoa.getDep().getFac().getSigla();//departamento->faculdade
				comando += "','";
			}else{
				comando += "null,'";
			}
		}else{
			comando += "null,null,'";
		}
		comando += pessoa.getCargo();//cargo
		comando += "','";
		comando += pessoa.getNome();//Nome
		comando += "','";
		comando += pessoa.getSenha();//Senha
		comando += "','";
		comando += pessoa.getTelefone();//telefone
		comando += "','";
		comando += pessoa.getMorada();//morada
		comando += "')";
		return ligacao.executaUpdateSQL(comando);

	}

	@Override
	public boolean modificaPessoa(Pessoa pessoa) throws RemoteException {
		
		String comando = "UPDATE Pessoa SET ";
		if(pessoa.getFac()!=null){
			comando += "Faculdade = '";
			comando += pessoa.getFac().getSigla();
			comando += "',";
		}else{
			comando += "Faculdade = null,";
		}
		
		if(pessoa.getDep()!=null){
			comando += "DepartamentoNome= '";
			comando += pessoa.getDep().getSigla();
			comando += "',";
			if(pessoa.getDep().getFac()!=null){
				comando += "DepartamentoFaculdadesigla = '";
				comando += pessoa.getDep().getFac().getSigla();
				comando += "',";
			}else{
				comando += "DepartamentoFaculdadesigla = null,";
			}
			
		}else{
			comando += "DepartamentoNome= null , DepartamentoFaculdadesigla = null, ";
		}
		comando += "tipo = '";
		comando += pessoa.getCargo();
		comando += "', Nome = '";
		comando += pessoa.getNome();
		comando += "', senha= '";
		comando += pessoa.getSenha();
		comando += "', telefone = ";
		comando += pessoa.getTelefone();
		comando += ", morada = '";
		comando += pessoa.getMorada();
		comando += "' where NCC = ";
		comando += pessoa.getNcc();
		
		return(ligacao.executaUpdateSQL(comando));
	}

	@Override
	public boolean removePessoa(Pessoa pessoa) throws RemoteException {
		String comando = "DELETE FROM Pessoa WHERE NCC = "+pessoa.getNcc();
		return (ligacao.executaUpdateSQL(comando));
	}
	
	@Override
	public Pessoa procuraPessoa(int ncc)throws RemoteException{
		String comando = "select * from pessoa where ncc = "+ncc;
		ResultSet res = ligacao.executaSQL(comando);
		if(res==null){return null;}
		try{
			if (res.next()) {
	            Pessoa pessoa = new Pessoa();
	            pessoa.setNcc(ncc);
	            pessoa.setFac(procuraFaculdade(res.getString(2)));
	            pessoa.setDep(procuraDepartamento(res.getString(3),res.getString(4)));
	            pessoa.setCargo(res.getString(5));
	            pessoa.setNome(res.getString(6));
	            pessoa.setSenha(res.getString(7));
	            pessoa.setTelefone(res.getInt(8));
	            pessoa.setMorada(res.getString(9));
	            
	            res.close();
	            return pessoa;
	        }else{
	        	res.close();
	        	return null;
	        }
			
		}catch(SQLException e){
			try{res.close();}catch(Exception e1){}
			return null;
		}
	}

	@Override
	public ArrayList<Departamento> ListDepartamentos(Faculdade faculdade) throws RemoteException {
		String comando;
		if(faculdade==null){
			comando = "Select * from departamento";
		}else{
			comando = "Select * from departamento where FAC = '"+faculdade.getSigla()+"'";
		}
		
		ResultSet res = ligacao.executaSQL(comando);
		if(res==null){return null;}
		try{
			ArrayList<Departamento> listaDepartamentos = new ArrayList<Departamento>();
			Departamento depTemp;
			while(res.next()){
				System.out.println(res.getString(1));
				depTemp = new Departamento();
				depTemp.setSigla(res.getString(1));
				depTemp.setFac(procuraFaculdade(res.getString(2)));
				depTemp.setNome(res.getString(3));
				listaDepartamentos.add(depTemp);
			}
			res.close();
			return listaDepartamentos;
			
		}catch(SQLException e){
			try{res.close();}catch(Exception e1){}
			return null;
		}
	}

	@Override
	public boolean addDepartamento(Departamento dep) throws RemoteException {
		String comando = "INSERT INTO departamento values ('";
		comando += dep.getSigla();
		comando += "','";
		comando += dep.getFac().getSigla();
		comando += "','";
		comando += dep.getNome();
		comando += "')";
		return ligacao.executaUpdateSQL(comando);
	}

	@Override
	public boolean removeDepartamento(Departamento dep) throws RemoteException {
		String comando = "DELETE FROM Departamento WHERE sigla = '"+dep.getSigla()+"'";
		return ligacao.executaUpdateSQL(comando);
	}
	@Override
	public Departamento procuraDepartamento(String siglaDep, String siglaFac) throws RemoteException {
		String comando = "Select * from departamento where sigla = '"+siglaDep+"' and fac = '"+siglaFac+"'";
		ResultSet res = ligacao.executaSQL(comando);
		if(res==null){return null;}
		try{
			if(res.next()){
				Departamento dep = new Departamento();
				dep.setSigla(res.getString(1));
				dep.setFac(procuraFaculdade(res.getString(2)));
				dep.setNome(res.getString(3));
				res.close();
				return dep;
			}else{
				res.close();
				return null;
			}
		}catch(SQLException e){
			try{res.close();}catch(Exception e1){}
			return null;
		}
	}

	@Override
	public ArrayList<Faculdade> ListFaculdades() throws RemoteException {
		String comando = "Select * from faculdade";
		ResultSet res = ligacao.executaSQL(comando);
		if(res==null){return null;}
		try{
			ArrayList<Faculdade> listaFaculdades = new ArrayList<Faculdade>();
			Faculdade facTemp;
			while(res.next()){
				facTemp = new Faculdade();
				facTemp.setSigla(res.getString(1));
				facTemp.setNome(res.getString(2));
				listaFaculdades.add(facTemp);
			}
			res.close();
			return listaFaculdades;
			
		}catch(SQLException e){
			try{res.close();}catch(Exception e1){}
			return null;
		}
	}

	@Override
	public boolean addFaculdade(Faculdade fac) throws RemoteException {
		String comando = "INSERT INTO faculdade values ('";
		comando += fac.getSigla();
		comando += "','";
		comando += fac.getNome();
		comando += "')";
		return ligacao.executaUpdateSQL(comando);
	}

	@Override
	public boolean removeFaculdade(Faculdade fac) throws RemoteException {
		String comando = "DELETE FROM Faculdade WHERE sigla = '"+fac.getSigla()+"'";
		return ligacao.executaUpdateSQL(comando);
	}
	@Override
	public Faculdade procuraFaculdade(String sigla) throws RemoteException {
		String comando = "Select * from faculdade where sigla = '"+sigla+"'";
		ResultSet res = ligacao.executaSQL(comando);
		if(res==null){return null;}
		try{
			if(res.next()){
				Faculdade fac = new Faculdade();
				fac.setSigla(res.getString(1));
				fac.setNome(res.getString(2));
				res.close();
				return fac;
			}else{
				res.close();
				return null;
			}
		}catch(SQLException e){
			try{res.close();}catch(Exception e1){}
			return null;
		}
	}

	@Override
	public boolean novaEleicao(Eleicao eleicao) throws RemoteException {
		String comando = "INSERT INTO Eleicao VALUES (SEQ_ELEICAO.NEXTVAL,'";
		comando += eleicao.getTipo();
		comando += "', TO_TIMESTAMP ('";
		comando += eleicao.getDataInicio();
		comando += "','yyyy:mm:dd hh:mi'),TO_TIMESTAMP ('";
		comando += eleicao.getDataFim();
		comando += "','yyyy:mm:dd hh:mi'),'";
		comando += eleicao.getTitulo();
		comando += "','";
		comando += eleicao.getDescricao();
		comando += "',";
		comando += eleicao.getnVotoBNA();
		comando += ")";
		return ligacao.executaUpdateSQL(comando);

	}

	@Override
	public boolean removerEleicao(Eleicao eleicao) throws RemoteException {
		String comando = "Delete from eleicao where id = " + eleicao.getId();	
		return ligacao.executaUpdateSQL(comando);
	}

	@Override
	public ArrayList<Eleicao> listaEleicao() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Eleicao> listaEleicao(String inicio, String fim) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addCandidato(Candidatos candidato) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeCandidato(Candidatos candidato) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Candidatos> listaCandidatos(Eleicao eleicao) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addMesaVoto(MesaVoto mesaVoto) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeMesaVoto(MesaVoto mesaVoto) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<MesaVoto> listMesaVoto(Eleicao eleicao) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ligarServidor(String nomeMesaVoto, String passwordMesaVoto) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean desbloquearUser(String nomeMesaVoto, String passwordMesaVoto, int nCC, int elect)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean desbloquearVoto(String nomeMesaVoto, String passwordMesaVoto, int nCC, int id_elect)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean bloquearVoto(String nomeMesaVoto, String passwordMesaVoto, int nCC, int id_elect)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean votar(String nomeMesaVoto, String passwordMesaVoto, int nCC, String passwordUser, int id_elei,
			int id_lista_voto) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
