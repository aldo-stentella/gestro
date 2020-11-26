package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TrovatoInventoreKey implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer nsrif;
	@Column(name="INVENTORI_ID")
	private Integer inventoriId;
	public Integer getInventoriId() {
		return inventoriId;
	}
	public void setInventoriId(Integer inventoriId) {
		this.inventoriId = inventoriId;
	}
	public Integer getNsrif() {
		return nsrif;
	}
	public void setNsrif(Integer nsrif) {
		this.nsrif = nsrif;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inventoriId == null) ? 0 : inventoriId.hashCode());
		result = prime * result + ((nsrif == null) ? 0 : nsrif.hashCode());
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
		TrovatoInventoreKey other = (TrovatoInventoreKey) obj;
		if (inventoriId == null) {
			if (other.inventoriId != null)
				return false;
		} else if (!inventoriId.equals(other.inventoriId))
			return false;
		if (nsrif == null) {
			if (other.nsrif != null)
				return false;
		} else if (!nsrif.equals(other.nsrif))
			return false;
		return true;
	}	
}
