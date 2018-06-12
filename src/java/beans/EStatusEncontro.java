package beans;

public enum EStatusEncontro {

	SOLICITADO(0,"Encontro solicitado"),
	ACEITO(1,"Encontro aceito"),
	RECUSADO(2,"Encontro recusado");
	
	private int idStatusEncontro;
	private String statusEncontro;
	
	
	private EStatusEncontro(int idStatusEncontro, String statusEncontro) {
		this.idStatusEncontro = idStatusEncontro;
		this.statusEncontro = statusEncontro;
	}

	public int getIdStatusEncontro() {
		return this.idStatusEncontro;
	}
	
	public String getStatusEncontro() {
		return this.statusEncontro;
	}
}
