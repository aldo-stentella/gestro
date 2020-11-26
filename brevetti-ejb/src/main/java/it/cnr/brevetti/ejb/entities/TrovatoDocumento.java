package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entità TROVATI_DOCUMENTI
 * @author Aurelio D'Amico
 * @version 1.0 [03-Jul-15]
 */
@Entity @Table(name="TROVATI_DOCUMENTI")
public class TrovatoDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TrovatoDocumentoKey key;

	@Override
	public String toString() {
		return "TrovatoDocumento [key=" + key.toString() + "]";
	}
	public TrovatoDocumento() {
		// empty constructor
	}
	public TrovatoDocumento(TrovatoDocumentoKey key) {
		this.key = key;
	}
	public TrovatoDocumentoKey getKey() {
		return key;
	}
	public void setKey(TrovatoDocumentoKey key) {
		this.key = key;
	}
}
