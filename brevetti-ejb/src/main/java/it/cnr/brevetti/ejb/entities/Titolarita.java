package it.cnr.brevetti.ejb.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity bean di gestione della tabella TITOLARITA
 * @author Aldo Stentella
 * @version 1.1 [21-Dec-07] (Aurelio)
 * - Eliminata chiave complessa (esiste un ID sequenziale univoco)
 * - Eliminate liste soggetti perchè basate su errara relazione 1 a molti
 *   la relazione corretta tra titolarità e soggetto è 1 a 1
 * @version 1.2 [11-Jan-08] (Aurelio)
 * - aggiunta sequenza
 */
@Entity @Table(name="TITOLARITA")
public class Titolarita extends AbstractTitolarita {
	private static final long serialVersionUID = 1L;
	public static final String SEQ = "TITOLARITA_SEQ";

	@Id @GeneratedValue(generator=Titolarita.SEQ, strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name=Titolarita.SEQ, sequenceName=Titolarita.SEQ, allocationSize=0)
	private Integer id;
	@Column(name="TIPI_TITOLARE_ID")
	private Integer tipiTitolareId;	
	@Column(name="FRK_SOGGETTO_ID")
	private Integer frkSoggettoId;
	private BigDecimal percentuale;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTipiTitolareId() {
		return tipiTitolareId;
	}
	public void setTipiTitolareId(Integer tipiTitolareId) {
		this.tipiTitolareId = tipiTitolareId;
	}
	public Integer getFrkSoggettoId() {
		return frkSoggettoId;
	}
	public void setFrkSoggettoId(Integer frkSoggettoId) {
		this.frkSoggettoId = frkSoggettoId;
	}
	public BigDecimal getPercentuale() {
		return percentuale;
	}
	public void setPercentuale(BigDecimal percentuale) {
		this.percentuale = percentuale;
	}
	
	// ============================================================

	private Integer nsrif;
	
	public Integer getNsrif() {
		return nsrif;
	}
	public void setNsrif(Integer nsrif) {
		this.nsrif = nsrif;
	}
}