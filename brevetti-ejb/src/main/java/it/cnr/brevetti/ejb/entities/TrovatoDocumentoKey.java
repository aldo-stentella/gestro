package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
/**
 * Chiave per l'Entità TROVATI_DOCUMENTI
 * @author Aurelio D'Amico
 * @version 1.0 [03-Jul-15]
 */
@Embeddable
public class TrovatoDocumentoKey implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer nsrif;
	@Column(name="DOCUMENTI_ID")
	private Integer documentoId;
	
	public Integer getNsrif() {
		return nsrif;
	}
	public void setNsrif(Integer nsrif) {
		this.nsrif = nsrif;
	}
	public Integer getDocumentoId() {
		return documentoId;
	}
	public void setDocumentoId(Integer documentoId) {
		this.documentoId = documentoId;
	}
	public TrovatoDocumentoKey() {
		// empty contructor
	}
	public TrovatoDocumentoKey(Integer nsrif, Integer documentoId) {
		this.nsrif = nsrif;
		this.documentoId = documentoId;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((documentoId == null) ? 0 : documentoId.hashCode());
		result = PRIME * result + ((nsrif == null) ? 0 : nsrif.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TrovatoDocumentoKey other = (TrovatoDocumentoKey) obj;
		if (documentoId == null) {
			if (other.documentoId != null)
				return false;
		} else if (!documentoId.equals(other.documentoId))
			return false;
		if (nsrif == null) {
			if (other.nsrif != null)
				return false;
		} else if (!nsrif.equals(other.nsrif))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TrovatoDocumentoKey [nsrif=" + nsrif + ", documentoId="
				+ documentoId + "]";
	}	
}
