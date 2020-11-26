package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * 
 * The persistent class for the TROVATI_VERBALI database table.
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [24-11-2017]
 *
 */
@Entity
@Table(name="TROVATI_VERBALI")
public class TrovatoVerbale implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TrovatoVerbaleKey key;
	private String azione;
	private Integer rifiuto;
	public TrovatoVerbaleKey getKey() {
		return this.key;
	}
	public void setKey(TrovatoVerbaleKey key) {
		this.key = key;
	}
	public String getAzione() {
		return this.azione;
	}
	public void setAzione(String azione) {
		this.azione = azione;
	}
	public Integer getRifiuto() {
		return this.rifiuto;
	}
	public void setRifiuto(Integer rifiuto) {
		this.rifiuto = rifiuto;
	}
}