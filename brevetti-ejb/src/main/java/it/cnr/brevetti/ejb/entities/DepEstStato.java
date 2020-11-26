package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Bean per la gestione della tabella DEP_EST_STATI
 * @author Aurelio D'Amico
 * @version 1.0 [3-Apr-14]
 */
@Entity @Table(name="DEP_EST_STATI")
public class DepEstStato implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private DepEstStatoKey key;

	public DepEstStato() {/*default contructor*/}
		

	public DepEstStato(DepEstStatoKey key) {
		this.key = key;
	}

	public DepEstStatoKey getKey() {
		return key;
	}

	public void setKey(DepEstStatoKey key) {
		this.key = key;
	}

}
