package it.cnr.brevetti.gas;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity bean di persistenza per la tabella GRUPPI_RUOLI
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [09-Mar-07]
 * @version 1.1 [23-Apr-08] gas revamp 
 */
public class Utente implements Serializable {
	private static final long serialVersionUID = 1L;
	// ==============================================================

	private Integer id;
	private String nomeUtente;
	private Integer applicazioneId;
	private Integer gruppoId;
	private Date ultimoAccesso;
	private Boolean attivo;
	private String struttura;
	
	// ==============================================================
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public Boolean getAttivo() {
		return attivo;
	}
	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}
	public Integer getGruppoId() {
		return gruppoId;
	}
	public void setGruppoId(Integer gruppoId) {
		this.gruppoId = gruppoId;
	}
	public String getStruttura() {
		return struttura;
	}
	public void setStruttura(String struttura) {
		this.struttura = struttura;
	}
	public Date getUltimoAccesso() {
		return ultimoAccesso;
	}
	public void setUltimoAccesso(Date ultimoAccesso) {
		this.ultimoAccesso = ultimoAccesso;
	}
	// ==============================================================
	
	public Integer getApplicazioneId() {
		return applicazioneId;
	}
	public void setApplicazioneId(Integer applicazioneId) {
		this.applicazioneId = applicazioneId;
	}
	public String getNomeUtente() {
		return nomeUtente;
	}
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
	public boolean isAttivo() {
		return attivo == null ? false : attivo.booleanValue();
	}
	private Gruppo gruppo;
	public Gruppo getGruppo() {
		return gruppo;
	}
	public void setGruppo(Gruppo gruppo) {
		this.gruppo = gruppo;
	}
	
	// ==============================================================

	@Override
	public String toString() {
		return id + "." + applicazioneId + "." + nomeUtente;
	}
}