package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the TROVATI_VALORIZZAZIONI database table.
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Jan 12, 2015]
 *
 */
@Entity
@Table(name="TROVATI_VALORIZZAZIONI")
public class TrovatoValorizzazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TrovatoValorizzazioneKey key;
	
	public TrovatoValorizzazioneKey getKey() {
		return this.key;
	}
	public void setKey(TrovatoValorizzazioneKey key) {
		this.key = key;
	}
}