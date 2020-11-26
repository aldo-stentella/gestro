package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
/**
 * Chiave per l'Entità TROVATI_DIPARTIMENTI
 * @author Aurelio D'Amico
 * @version 1.0 [22-May-08]
 */
@Embeddable
public class TrovatoDipartimentoKey implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer nsrif;
	@Column(name="DIPARTIMENTI_ID")
	private Integer dipartimentiId;
	
	public TrovatoDipartimentoKey() {
		// empty contructor
	}
	public TrovatoDipartimentoKey(Integer nsrif, Integer dipartimentiId) {
		this.nsrif = nsrif;
		this.dipartimentiId = dipartimentiId;
	}
	public Integer getDipartimentiId() {
		return dipartimentiId;
	}
	public void setDipartimentiId(Integer dipartimentiId) {
		this.dipartimentiId = dipartimentiId;
	}
	public Integer getNsrif() {
		return nsrif;
	}
	public void setNsrif(Integer nsrif) {
		this.nsrif = nsrif;
	}
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((dipartimentiId == null) ? 0 : dipartimentiId.hashCode());
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
		final TrovatoDipartimentoKey other = (TrovatoDipartimentoKey) obj;
		if (dipartimentiId == null) {
			if (other.dipartimentiId != null)
				return false;
		} else if (!dipartimentiId.equals(other.dipartimentiId))
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
		return "TrovatoDipartimentoKey [nsrif=" + nsrif + ", dipartimentiId="
				+ dipartimentiId + "]";
	}	
}
