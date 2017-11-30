package bd_java_code;

public class Candidatos {
	private int id;
	private Eleicao eleicao;
	private String tipo;
	private int nVotos;
	
	public Candidatos() {
		// TODO Auto-generated constructor stub
	}
	public Eleicao getEleicao() {
		return eleicao;
	}public int getId() {
		return id;
	}public int getnVotos() {
		return nVotos;
	}public String getTipo() {
		return tipo;
	}public void setEleicao(Eleicao eleicao) {
		this.eleicao = eleicao;
	}public void setId(int id) {
		this.id = id;
	}public void setnVotos(int nVotos) {
		this.nVotos = nVotos;
	}public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
