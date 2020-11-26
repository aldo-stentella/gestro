package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Chiave univoca per l'entità d'associazione PctTipoRegionalPatent
 * @author Aurelio D'Amico
 * @version 1.0 [2-Apr-08]
 */
@Embeddable
public class PctTipoRegionalPatentKey implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name="PCT_ID")
	private Integer pctId;
	@Column(name="TIPI_PCT_REGIONAL_PATENT_ID")
	private Integer tipoId;
	public Integer getTipoId() {
		return tipoId;
	}
	public void setTipoId(Integer tipoId) {
		this.tipoId = tipoId;
	}
	public Integer getPctId() {
		return pctId;
	}
	public void setPctId(Integer pctId) {
		this.pctId = pctId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pctId == null) ? 0 : pctId.hashCode());
		result = prime * result + ((tipoId == null) ? 0 : tipoId.hashCode());
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
		PctTipoRegionalPatentKey other = (PctTipoRegionalPatentKey) obj;
		if (pctId == null) {
			if (other.pctId != null)
				return false;
		} else if (!pctId.equals(other.pctId))
			return false;
		if (tipoId == null) {
			if (other.tipoId != null)
				return false;
		} else if (!tipoId.equals(other.tipoId))
			return false;
		return true;
	}	
}
