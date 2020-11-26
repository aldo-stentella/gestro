package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Bean per la gestione della tabella d'associazione PCT_TIPI_REGIONAL_PATENT
 * @author Aurelio D'Amico
 * @version 1.0 [2-Apr-08]
 */
@Entity @Table(name="PCT_TIPI_REGIONAL_PATENT")
public class PctTipoRegionalPatent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PctTipoRegionalPatentKey key;

	public PctTipoRegionalPatent() {}

	public PctTipoRegionalPatent(PctTipoRegionalPatentKey key) {
		this.key = key;
	}

	public PctTipoRegionalPatentKey getKey() {
		return key;
	}

	public void setKey(PctTipoRegionalPatentKey key) {
		this.key = key;
	}
	
}
