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
public class FluxoCaixa {

    private String tipoRelatorio;
    private int anoRelatorio;
    private int mesRelatorio;
    private ItensFluxoCaixa itensFluxoCaixa;
    
	public String getTipoRelatorio() {
		return tipoRelatorio;
	}
	
	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}
	
	public int getAnoRelatorio() {
		return anoRelatorio;
	}
	
	public void setAnoRelatorio(int anoRelatorio) {
		this.anoRelatorio = anoRelatorio;
	}
	
	public int getMesRelatorio() {
		return mesRelatorio;
	}
	
	public void setMesRelatorio(int mesRelatorio) {
		this.mesRelatorio = mesRelatorio;
	}
	
	public ItensFluxoCaixa getItensFluxoCaixa() {
		return itensFluxoCaixa;
	}
	
	public void setItensFluxoCaixa(ItensFluxoCaixa itensFluxoCaixa) {
		this.itensFluxoCaixa = itensFluxoCaixa;
	}
    
}
