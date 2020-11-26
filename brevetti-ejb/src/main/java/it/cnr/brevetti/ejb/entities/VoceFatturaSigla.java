package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the VOCI_FATTURA_SIGLA database table.
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [2014-06-06]
 *
 */
@Entity
@Table(name="VOCI_FATTURA_SIGLA")
public class VoceFatturaSigla implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id @Column(name="VOCI_FATTURE_ID")
	private Integer id;

	@Column(name="CD_CDS")
	private String cdCds;

	@Column(name="CD_UNITA_ORGANIZZATIVA")
	private String cdUnitaOrganizzativa;

	private Long esercizio;

	@Column(name="PG_FATTURA_PASSIVA")
	private Long pgFatturaPassiva;

	@Column(name="PROGRESSIVO_RIGA")
	private Long progressivoRiga;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCdCds() {
		return cdCds;
	}

	public void setCdCds(String cdCds) {
		this.cdCds = cdCds;
	}

	public String getCdUnitaOrganizzativa() {
		return cdUnitaOrganizzativa;
	}

	public void setCdUnitaOrganizzativa(String cdUnitaOrganizzativa) {
		this.cdUnitaOrganizzativa = cdUnitaOrganizzativa;
	}

	public Long getEsercizio() {
		return esercizio;
	}

	public void setEsercizio(Long esercizio) {
		this.esercizio = esercizio;
	}

	public Long getPgFatturaPassiva() {
		return pgFatturaPassiva;
	}

	public void setPgFatturaPassiva(Long pgFatturaPassiva) {
		this.pgFatturaPassiva = pgFatturaPassiva;
	}

	public Long getProgressivoRiga() {
		return progressivoRiga;
	}

	public void setProgressivoRiga(Long progressivoRiga) {
		this.progressivoRiga = progressivoRiga;
	}	
}