package beans;

public enum EHomeUsuario {

	ADMIN("AdminServlet"),
	FUNCIONARIO("FuncionarioServlet"),
	CLIENTE("ClienteServlet");
	
	private String caminho;

	private EHomeUsuario(String caminho) {
		this.setCaminho(caminho);
	}

	public String getCaminho() {
		return caminho;
	}

	private void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	
}