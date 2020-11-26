package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
/**
 * Chiave per l'Entità InventionDisclosureDocumento
 * @author Aurelio D'Amico
 * @version 1.0 [11-Dec-15]
 */
@Embeddable
public class InventionDisclosureDocumentoKey implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="INVENTION_DISCLOSURE_ID")
	private Integer inventionDisclosureId;
	@Column(name="DOCUMENTI_ID")
	private Integer documentoId;
	
	public Integer getInventionDisclosureId() {
		return inventionDisclosureId;
	}
	public void setInventionDisclosureId(Integer inventionDisclosureId) {
		this.inventionDisclosureId = inventionDisclosureId;
	}
	public Integer getDocumentoId() {
		return documentoId;
	}
	public void setDocumentoId(Integer documentoId) {
		this.documentoId = documentoId;
	}
	public InventionDisclosureDocumentoKey() {
		// empty contructor
	}
	public InventionDisclosureDocumentoKey(Integer inventionDisclosureId, Integer documentoId) {
		this.inventionDisclosureId = inventionDisclosureId;
		this.documentoId = documentoId;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((documentoId == null) ? 0 : documentoId.hashCode());
		result = PRIME * result + ((inventionDisclosureId == null) ? 0 : inventionDisclosureId.hashCode());
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
		final InventionDisclosureDocumentoKey other = (InventionDisclosureDocumentoKey) obj;
		if (documentoId == null) {
			if (other.documentoId != null)
				return false;
		} else if (!documentoId.equals(other.documentoId))
			return false;
		if (inventionDisclosureId == null) {
			if (other.inventionDisclosureId != null)
				return false;
		} else if (!inventionDisclosureId.equals(other.inventionDisclosureId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "InventionDisclosureDocumentoKey [inventionDisclosureId="
				+ inventionDisclosureId + ", documentoId=" + documentoId + "]";
	}	
}
