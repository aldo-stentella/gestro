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
 * The persistent class for the STORICO_TITOLARITA database table.
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [2014-06-06]
 *
 */
@Entity
@Table(name="STORICO_TITOLARITA")
public class StoricoTitolarita extends AbstractTitolarita {
	private static final long serialVersionUID = 1L;
	public static final String SEQ = "STORICO_TITOLARITA_SEQ";
	
	@Id @GeneratedValue(generator=StoricoTitolarita.SEQ, strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name=StoricoTitolarita.SEQ, sequenceName=StoricoTitolarita.SEQ, allocationSize=0)
	@Column(name="STORICO_TITOLARITA_ID")
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

	@Column(name="DEP_EST_ID")
	private Integer depEstId;

	public Integer getDepEstId() {
		return this.depEstId;
	}
	public void setDepEstId(Integer depEstId) {
		this.depEstId = depEstId;
	}
	@Override
	public String toString() {
		return "StoricoTitolarita [id=" + id + ", depEstId="
				+ depEstId + "]";
	}	
}