package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
/**
 * Chiave univoca per l'entità DepEstStato
 * @author Aurelio D'Amico
 * @version 1.0 [3-Apr-14]
 */

@Embeddable
public class DepEstStatoKey implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name="DEP_EST_ID")
	private Integer depEstId;
	@Column(name="STATI_ID")
	private Integer statoId;
	public Integer getDepEstId() {
		return depEstId;
	}
	public void setDepEstId(Integer depEstId) {
		this.depEstId = depEstId;
	}
	public Integer getStatoId() {
		return statoId;
	}
	public void setStatoId(Integer statoId) {
		this.statoId = statoId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((depEstId == null) ? 0 : depEstId.hashCode());
		result = prime * result + ((statoId == null) ? 0 : statoId.hashCode());
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
		DepEstStatoKey other = (DepEstStatoKey) obj;
		if (depEstId == null) {
			if (other.depEstId != null)
				return false;
		} else if (!depEstId.equals(other.depEstId))
			return false;
		if (statoId == null) {
			if (other.statoId != null)
				return false;
		} else if (!statoId.equals(other.statoId))
			return false;
		return true;
	}	
}
