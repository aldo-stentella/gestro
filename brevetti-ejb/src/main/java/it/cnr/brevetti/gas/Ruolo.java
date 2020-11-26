package it.cnr.brevetti.gas;

import java.io.Serializable;

/**
 * Entity bean di persistenza per la tabella PROFILI
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [29-Jan-2007]
 * @version 1.1 [23-Apr-08] gas revamp 
 */
public class Ruolo implements Serializable, Comparable<Ruolo> {
	private static final long serialVersionUID = 1L;
	// =========================================================================   
	private Integer id;
	private String nome;
	private String codice;
	private Integer applicazioneId;	
	// =========================================================================
	public Ruolo() {
		// emtpy constructor
	}
	public Ruolo(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getApplicazioneId() {
		return applicazioneId;
	}
	public void setApplicazioneId(Integer applicazioneId) {
		this.applicazioneId = applicazioneId;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	// =========================================================================

	private String dataToString() {
		StringBuffer sb = new StringBuffer();
		sb.append(id);
		sb.append('.');
		sb.append(nome);
		return sb.toString();
	}

	@Override
	public String toString() {
		return dataToString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Ruolo) {
			Ruolo that = (Ruolo)obj;
			return this.dataToString().equals(that.dataToString());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return dataToString().hashCode();
	}
	public int compareTo(Ruolo that) {
		return this.id.compareTo(that.id);	
	}	
}
