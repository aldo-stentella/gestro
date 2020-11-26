package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the TROVATI_VALORIZZAZIONI database table.
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Jan 12, 2015]
 *
 */
@Embeddable
public class TrovatoValorizzazioneKey implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer nsrif;
	@Column(name="VALORIZZAZIONI_ID")
	private Integer valid;

	public TrovatoValorizzazioneKey() {
	}
	public Integer getNsrif() {
		return this.nsrif;
	}
	public void setNsrif(Integer nsrif) {
		this.nsrif = nsrif;
	}
	public Integer getValid() {
		return this.valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nsrif == null) ? 0 : nsrif.hashCode());
		result = prime * result + ((valid == null) ? 0 : valid.hashCode());
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
		TrovatoValorizzazioneKey other = (TrovatoValorizzazioneKey) obj;
		if (nsrif == null) {
			if (other.nsrif != null)
				return false;
		} else if (!nsrif.equals(other.nsrif))
			return false;
		if (valid == null) {
			if (other.valid != null)
				return false;
		} else if (!valid.equals(other.valid))
			return false;
		return true;
	}	
}