package it.cnr.brevetti.sigla;

import java.io.Serializable;

/**
 * Rappresenta il context da inviare a sigla
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Sep 19, 2016]
 *
 */
public class JsonContext implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer esercizio;
	private String cd_unita_organizzativa;
	private String cd_cds;
	private String cd_cdr;
	
	//{"esercizio":2016,"cd_unita_organizzativa":"999.000","cd_cds":"999","cd_cdr":"999.000.000"}}
	public JsonContext() {
		setEsercizio(2016);
		setCd_unita_organizzativa("999.000");
		setCd_cds("999");
		setCd_cdr("999.000.000");
	}
	public JsonContext(Integer esercizio) {
		this();
		this.setEsercizio(esercizio);
	}
	public Integer getEsercizio() {
		return esercizio;
	}
	public void setEsercizio(Integer esercizio) {
		this.esercizio = esercizio;
	}
	public String getCd_unita_organizzativa() {
		return cd_unita_organizzativa;
	}
	public void setCd_unita_organizzativa(String cd_unita_organizzativa) {
		this.cd_unita_organizzativa = cd_unita_organizzativa;
	}
	public String getCd_cds() {
		return cd_cds;
	}
	public void setCd_cds(String cd_cds) {
		this.cd_cds = cd_cds;
	}
	public String getCd_cdr() {
		return cd_cdr;
	}
	public void setCd_cdr(String cd_cdr) {
		this.cd_cdr = cd_cdr;
	}
	
}
