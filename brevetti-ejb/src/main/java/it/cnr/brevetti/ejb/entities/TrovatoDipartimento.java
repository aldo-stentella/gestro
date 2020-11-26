package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entità TROVATI_DIPARTIMENTI
 * @author Aurelio D'Amico
 * @version 1.0 [22-May-08]
 */
@Entity @Table(name="TROVATI_DIPARTIMENTI")
public class TrovatoDipartimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TrovatoDipartimentoKey key;

	public TrovatoDipartimento() {
		// empty constructor
	}
	public TrovatoDipartimento(TrovatoDipartimentoKey key) {
		this.key = key;
	}
	public TrovatoDipartimentoKey getKey() {
		return key;
	}
	public void setKey(TrovatoDipartimentoKey key) {
		this.key = key;
	}
}
