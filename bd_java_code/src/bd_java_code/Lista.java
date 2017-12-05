package bd_java_code;

import java.util.ArrayList;

public class Lista extends Candidatos {
	private ArrayList <Pessoa> lista_pessoas;
	public Lista() {
		// TODO Auto-generated constructor stub
		super();
	}
	public ArrayList<Pessoa> getLista_pessoas() {
		return lista_pessoas;
	}public void setLista_pessoas(ArrayList<Pessoa> lista_pessoas) {
		this.lista_pessoas = lista_pessoas;
	}
}
