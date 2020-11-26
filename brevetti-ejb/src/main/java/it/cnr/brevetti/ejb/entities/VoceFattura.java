package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @version 1.1 [19-Jan-09] completamento entita' by Aurelio
 */
@Entity @Table(name="VOCI_FATTURA")
public class VoceFattura implements Serializable{	
	private static final long serialVersionUID = 1L;
	public static final String SEQ = "VOCI_FATTURA_SEQ";
	
	@Id @Column(name="VOCI_FATTURE_ID")
	@SequenceGenerator(name=VoceFattura.SEQ, sequenceName=VoceFattura.SEQ, allocationSize=0)
	@GeneratedValue(generator=VoceFattura.SEQ, strategy=GenerationType.SEQUENCE)	
	private Integer id;
	@Column(name="FATTURE_ID")
	private Integer fattureId;
	private Integer nsrif;
	@Column(name="CAUSALI_ID")
	private Integer causaliId;
	private String n;
	private String percentuale;
	@Column(name="STATI_ID")
	private Integer statiId;
	private BigDecimal anticipazione;
	private BigDecimal onorari;
	private BigDecimal iva;
	@Column(name="PARZIALE_EURO")
	private BigDecimal parzialeEuro;
	
	public BigDecimal getAnticipazione() {
		return anticipazione;
	}
	public void setAnticipazione(BigDecimal anticipazione) {
		this.anticipazione = anticipazione;
	}
	public Integer getCausaliId() {
		return causaliId;
	}
	public void setCausaliId(Integer causaliId) {
		this.causaliId = causaliId;
	}
	public Integer getFattureId() {
		return fattureId;
	}
	public void setFattureId(Integer fattureId) {
		this.fattureId = fattureId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getIva() {
		return iva;
	}
	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}
	public String getN() {
		return n;
	}
	public void setN(String n) {
		this.n = n;
	}
	public Integer getNsrif() {
		return nsrif;
	}
	public void setNsrif(Integer nsrif) {
		this.nsrif = nsrif;
	}
	public BigDecimal getOnorari() {
		return onorari;
	}
	public void setOnorari(BigDecimal onorari) {
		this.onorari = onorari;
	}
	public BigDecimal getParzialeEuro() {
		return parzialeEuro;
	}
	public void setParzialeEuro(BigDecimal parzialeEuro) {
		this.parzialeEuro = parzialeEuro;
	}
	public String getPercentuale() {
		return percentuale;
	}
	public void setPercentuale(String percentuale) {
		this.percentuale = percentuale;
	}
	public Integer getStatiId() {
		return statiId;
	}
	public void setStatiId(Integer statiId) {
		this.statiId = statiId;
	}

	// dati transienti
	@Transient Trovato trovato;
	@Transient Causale causale;
	@Transient Stato stato;
	@Transient VoceFatturaSigla voceFatturaSigla;

	public Causale getCausale() {
		return causale;
	}
	public void setCausale(Causale causale) {
		this.causale = causale;
	}
	public Stato getStato() {
		return stato;
	}
	public void setStato(Stato stato) {
		this.stato = stato;
	}
	public Trovato getTrovato() {
		return trovato;
	}
	public void setTrovato(Trovato trovato) {
		this.trovato = trovato;
	}	
	public VoceFatturaSigla getVoceFatturaSigla() {
		return voceFatturaSigla;
	}
	public void setVoceFatturaSigla(VoceFatturaSigla voceFatturaSigla) {
		this.voceFatturaSigla = voceFatturaSigla;
	}
}