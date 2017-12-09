package bd_java_code;

public class PessoaLista {
	private Pessoa pessoa;
	private String Cargo;
	
	public PessoaLista() {
		// TODO Auto-generated constructor stub
	}public String getCargo() {
		return Cargo;
	}public Pessoa getPessoa() {
		return pessoa;
	}public void setCargo(String cargo) {
		Cargo = cargo;
	}public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
