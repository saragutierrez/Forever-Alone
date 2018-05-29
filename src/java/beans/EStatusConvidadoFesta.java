package beans;

public enum EStatusConvidadoFesta {

	ENVIADO(0,"Convite enviado"),
	ACEITO(1,"Convite aceito"),
	RECUSADO(2,"Convite recusado");
	
	private int idStatusConvidado;
	private String statusConvidado;
	
	
	private EStatusConvidadoFesta(int idStatusConvidado, String statusConvidado) {
		this.idStatusConvidado = idStatusConvidado;
		this.statusConvidado = statusConvidado;
	}

	public int getIdStatusConvidado() {
		return this.idStatusConvidado;
	}
	
	public String getStatusConvidado() {
		return this.statusConvidado;
	}
}
