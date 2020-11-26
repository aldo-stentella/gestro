package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * 
 * The primary key class for the TROVATI_VERBALI database table.
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [24-11-2017]
 *
 */
@Embeddable
public class TrovatoVerbaleKey implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="VERBALI_ID")
	private Integer verbaleId;
	private Integer nsrif;
	public TrovatoVerbaleKey() {
	}
	public TrovatoVerbaleKey(Integer verbaleId, Integer nsrif) {
		this.verbaleId = verbaleId;
		this.nsrif = nsrif;
	}
	public Integer getVerbaleId() {
		return this.verbaleId;
	}
	public void setVerbaleId(Integer verbaleId) {
		this.verbaleId = verbaleId;
	}
	public long getNsrif() {
		return this.nsrif;
	}
	public void setNsrif(Integer nsrif) {
		this.nsrif = nsrif;
	}
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TrovatoVerbaleKey)) {
			return false;
		}
		TrovatoVerbaleKey castOther = (TrovatoVerbaleKey)other;
		return 
			(this.verbaleId == castOther.verbaleId)
			&& (this.nsrif == castOther.nsrif);
	}
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.verbaleId ^ (this.verbaleId >>> 32)));
		hash = hash * prime + ((int) (this.nsrif ^ (this.nsrif >>> 32)));
		
		return hash;
	}
}