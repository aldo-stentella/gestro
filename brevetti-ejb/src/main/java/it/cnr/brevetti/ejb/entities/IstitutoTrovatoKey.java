package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IstitutoTrovatoKey implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer nsrif;
	@Column(name="ISTITUTI_ID")
	private Integer istitutiId;
	public Integer getIstitutiId() {
		return istitutiId;
	}
	public void setIstitutiId(Integer istitutiId) {
		this.istitutiId = istitutiId;
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
		result = prime * result + ((istitutiId == null) ? 0 : istitutiId.hashCode());
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
		IstitutoTrovatoKey other = (IstitutoTrovatoKey) obj;
		if (istitutiId == null) {
			if (other.istitutiId != null)
				return false;
		} else if (!istitutiId.equals(other.istitutiId))
			return false;
		if (nsrif == null) {
			if (other.nsrif != null)
				return false;
		} else if (!nsrif.equals(other.nsrif))
			return false;
		return true;
	}	
}
