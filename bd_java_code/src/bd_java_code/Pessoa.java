package bd_java_code;

public class Pessoa {
	
	private String nome;
	private int ncc;
	private String cargo;
	private String senha;
	private int telefone;
	private String morada;
	private Faculdade fac;
	private Departamento dep;
	
	public Pessoa() {
		// TODO Auto-generated constructor stub
	}
	public String getCargo() {
		return cargo;
	}public String getMorada() {
		return morada;
	}public int getNcc() {
		return ncc;
	}public String getNome() {
		return nome;
	}public String getSenha() {
		return senha;
	}public int getTelefone() {
		return telefone;
	}public Faculdade getFac() {
		return fac;
	}public Departamento getDep() {
		return dep;
	}public void setCargo(String cargo) {
		this.cargo = cargo;
	}public void setMorada(String morada) {
		this.morada = morada;
	}public void setNcc(int ncc) {
		this.ncc = ncc;
	}public void setNome(String nome) {
		this.nome = nome;
	}public void setSenha(String senha) {
		this.senha = senha;
	}public void setTelefone(int telefone) {
		this.telefone = telefone;
	}public void setFac(Faculdade fac) {
		this.fac = fac;
	}public void setDep(Departamento dep) {
		this.dep = dep;
	}
}