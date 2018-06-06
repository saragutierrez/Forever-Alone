/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author luck
 */
public class Preferencias {

	private int idPreferencias;
    private String genero;
    private String escolaridade;
    private String corCabelo;
    private String corPele;
    private int idadeInicial;
    private int idadeFinal;

    public int getIdPreferencias() {
		return idPreferencias;
	}

	public void setIdPreferencias(int idPreferencias) {
		this.idPreferencias = idPreferencias;
	}

	public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCorCabelo() {
        return corCabelo;
    }

    public void setCorCabelo(String corCabelo) {
        this.corCabelo = corCabelo;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getCorPele() {
        return corPele;
    }

    public void setCorPele(String corPele) {
        this.corPele = corPele;
    }

    public int getIdadeInicial() {
        return idadeInicial;
    }

    public void setIdadeInicial(int idadeInicial) {
        this.idadeInicial = idadeInicial;
    }

    public int getIdadeFinal() {
        return idadeFinal;
    }

    public void setIdadeFinal(int idadeFinal) {
        this.idadeFinal = idadeFinal;
    }

	@Override
	public String toString() {
		return "Preferencias [idPreferencias=" + idPreferencias + ", genero=" + genero + ", escolaridade="
				+ escolaridade + ", corCabelo=" + corCabelo + ", corPele=" + corPele + ", idadeInicial=" + idadeInicial
				+ ", idadeFinal=" + idadeFinal + "]";
	}
	
}
