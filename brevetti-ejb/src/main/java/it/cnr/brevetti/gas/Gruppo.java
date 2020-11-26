package it.cnr.brevetti.gas;

import java.io.Serializable;
import java.util.List;

/**
 * Entity bean di persistenza per la tabella GRUPPI
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [09-Mar-2007]
 * @version 1.1 [23-Apr-08] gas revamp
 */
public class Gruppo implements Serializable, Comparable<Gruppo> {
	private static final long serialVersionUID = 1L;
	// =====================================================================	
	private Integer id;
	private Integer applicazioneId;
	private String nome;
	// =====================================================================

	public Integer getApplicazioneId() {
		return applicazioneId;
	}

	public void setApplicazioneId(Integer applicazioneId) {
		this.applicazioneId = applicazioneId;
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

	// =====================================================================
	//  Transient attributes
	// =====================================================================
	private List<Ruolo> ruoli;
	public List<Ruolo> getRuoli() {
		return ruoli;
	}
	public void setRuoli(List<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

	// =====================================================================
	//  Routines
	// =====================================================================
	private String dataToString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getId().toString());
		sb.append('.');
		sb.append(getNome());
		return sb.toString();
	}

	@Override
	public String toString() {
		return dataToString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Gruppo) {
			Gruppo that = (Gruppo)obj;
			return this.dataToString().equals(that.dataToString());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return dataToString().hashCode();
	}

	public int compareTo(Gruppo that) {
		return this.id.compareTo(that.id);
	}
	
}
