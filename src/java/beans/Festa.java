/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.beans;

import java.util.Date;

/**
 *
 * @author luck
 */
public class Festa {

    private int idFesta;
    private String localFesta;
    private Date dataFesta;
    private String temaFesta;

    public int getIdFesta() {
        return idFesta;
    }

    public void setIdFesta(int idFesta) {
        this.idFesta = idFesta;
    }

    public String getLocalFesta() {
        return localFesta;
    }

    public void setLocalFesta(String localFesta) {
        this.localFesta = localFesta;
    }

    public Date getDataFesta() {
        return dataFesta;
    }

    public void setDataFesta(Date dataFesta) {
        this.dataFesta = dataFesta;
    }

    public String getTemaFesta() {
        return temaFesta;
    }

    public void setTemaFesta(String temaFesta) {
        this.temaFesta = temaFesta;
    }

}
