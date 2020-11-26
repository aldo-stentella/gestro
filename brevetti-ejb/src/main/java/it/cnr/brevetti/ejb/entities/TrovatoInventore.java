package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name="TROVATI_INVENTORI")
public class TrovatoInventore implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TrovatoInventoreKey key;
	@Column(name="INVENTORE_RIFERIMENTO")
	private Integer inventoreRiferimento;
	public Integer getInventoreRiferimento() {
		return inventoreRiferimento;
	}
	public void setInventoreRiferimento(Integer inventoreRiferimento) {
		this.inventoreRiferimento = inventoreRiferimento;
	}
	public TrovatoInventoreKey getKey() {
		return key;
	}
	public void setKey(TrovatoInventoreKey key) {
		this.key = key;
	}	
}
