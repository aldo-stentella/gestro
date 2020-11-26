package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Transient;

/**
 * Classe attributi comuni alle Entity di tipo titolarità
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [2014-06-10]
 *
 */
public abstract class AbstractTitolarita implements Serializable {
	private static final long serialVersionUID = 1L;

	// ============================================================
	//  tipi comuni
	// ============================================================

	public abstract Integer getId();
	public abstract void setId(Integer id);
	public abstract Integer getTipiTitolareId(); 
	public abstract void setTipiTitolareId(Integer tipiTitolareId); 
	public abstract Integer getFrkSoggettoId();
	public abstract void setFrkSoggettoId(Integer frkSoggettoId);
	public abstract BigDecimal getPercentuale();
	public abstract void setPercentuale(BigDecimal percentuale);

	// ============================================================
	//  tipi transient
	// ============================================================
	
	@Transient private Dipartimento dipartimento;
	@Transient private Inventore inventore;
	@Transient private EnteEsterno enteEsterno;

	public Dipartimento getDipartimento() {
		return dipartimento;
	}
	public void setDipartimento(Dipartimento dipartimento) {
		this.dipartimento = dipartimento;
	}
	public EnteEsterno getEnteEsterno() {
		return enteEsterno;
	}
	public void setEnteEsterno(EnteEsterno enteEsterno) {
		this.enteEsterno = enteEsterno;
	}
	public Inventore getInventore() {
		return inventore;
	}
	public void setInventore(Inventore inventore) {
		this.inventore = inventore;
	}
}
