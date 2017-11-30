package bd_java_code;

public class Departamento {
	private String sigla;
	private String nome;
	private Faculdade fac;
	
	public Departamento() {
		// TODO Auto-generated constructor stub
	}
	public Faculdade getFac() {
		return fac;
	}public String getNome() {
		return nome;
	}public String getSigla() {
		return sigla;
	}public void setFac(Faculdade fac) {
		this.fac = fac;
	}public void setNome(String nome) {
		this.nome = nome;
	}public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
