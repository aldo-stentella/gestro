package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entità INVENTION_DISCLOSURE_DOCUMENTI
 * @author Aurelio D'Amico
 * @version 1.0 [11-Dec-15]
 */
@Entity @Table(name="INVENTION_DISCLOSURE_DOCUMENTI")
public class InventionDisclosureDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private InventionDisclosureDocumentoKey key;

	@Override
	public String toString() {
		return key.toString();
	}
	public InventionDisclosureDocumento() {
		// empty constructor
	}
	public InventionDisclosureDocumento(InventionDisclosureDocumentoKey key) {
		this.key = key;
	}
	public InventionDisclosureDocumentoKey getKey() {
		return key;
	}
	public void setKey(InventionDisclosureDocumentoKey key) {
		this.key = key;
	}
	
}
