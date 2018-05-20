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
    private Date dataEncontro;
    private String localEncontro;
    private String status;

    public int getIdEncontro() {
        return idEncontro;
    }

    public void setIdEncontro(int idEncontro) {
        this.idEncontro = idEncontro;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
