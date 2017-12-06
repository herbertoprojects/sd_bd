package bd_java_code;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LigacaoBD ligacao = new LigacaoBD();
		ligacao.imprimeResultSet(ligacao.executaSQL("INSERT INTO Faculdade (sigla,nome) values ('FCTUC','Faculdade de Ciências e Técnologias da Universidade de Coimbra')"));
		ligacao.imprimeResultSet(ligacao.executaSQL("Select * from Faculdade"));
	}

}
