/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;

/**
 *
 * @author luck
 */
public class Encontro {

	private int idEncontro;
	private int idSolicitante;
	private int idSolicitado;
    private Date dataEncontro;
    private String localEncontro;
    private EStatusEncontro status;

    public int getIdEncontro() {
        return idEncontro;
    }

    public void setIdEncontro(int idEncontro) {
        this.idEncontro = idEncontro;
    }

    public int getIdSolicitante() {
		return idSolicitante;
	}

	public void setIdSolicitante(int idSolicitante) {
		this.idSolicitante = idSolicitante;
	}

	public int getIdSolicitado() {
		return idSolicitado;
	}

	public void setIdSolicitado(int idSolicitado) {
		this.idSolicitado = idSolicitado;
	}

	public Date getDataEncontro() {
        return dataEncontro;
    }

    public void setDataEncontro(Date dataEncontro) {
        this.dataEncontro = dataEncontro;
    }

    public String getLocalEncontro() {
        return localEncontro;
    }

    public void setLocalEncontro(String localEncontro) {
        this.localEncontro = localEncontro;
    }

	public EStatusEncontro getStatus() {
		return status;
	}

	public void setStatus(EStatusEncontro status) {
		this.status = status;
	}

}
