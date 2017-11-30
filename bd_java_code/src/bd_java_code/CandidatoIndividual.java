package bd_java_code;

public class CandidatoIndividual {
	private Pessoa pessoa;
	private Candidatos candidato;
	
	public CandidatoIndividual() {
		// TODO Auto-generated constructor stub
	}
	public Candidatos getCandidato() {
		return candidato;
	}public Pessoa getPessoa() {
		return pessoa;
	}public void setCandidato(Candidatos candidato) {
		this.candidato = candidato;
	}public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
