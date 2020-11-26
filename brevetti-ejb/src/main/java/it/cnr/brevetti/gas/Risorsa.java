package it.cnr.brevetti.gas;

import java.io.Serializable;

/**
 * Entità di persistenza per la tabella RISORSE
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [23-Apr-08]
 */
public class Risorsa implements Serializable, Comparable<Risorsa> {
	private static final long serialVersionUID = 1L;
	// =====================================================================
	private Integer id;
	private Integer applicazioneId;
	private Integer ruoloId;
	private String nome;
	private String note;
	// =====================================================================
	public Integer getApplicazioneId() {
		return applicazioneId;
	}
	public void setApplicazioneId(Integer applicazioneId) {
		this.applicazioneId = applicazioneId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRuoloId() {
		return ruoloId;
	}
	public void setRuoloId(Integer ruoloId) {
		this.ruoloId = ruoloId;
	}
	public String toString() {
		return id + "." + nome;		
	}
	public int compareTo(Risorsa that) {
		return this.id.compareTo(that.id);	
	}
	// =====================================================================
	public boolean isAbsolute() {
		return nome == null ? false : nome.contains("://");
	}
	private Ruolo ruolo;
	public Ruolo getRuolo() {
		return ruolo;
	}
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
}
