package it.cnr.brevetti.fatture.actionForms;

import java.math.BigDecimal;
import org.apache.struts.action.ActionForm;

public class VoceForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer fattureId;
	private Integer nsrif;
	private Integer causaliId;
	private String causaleDescrizione;
	private String n;
	private String percentuale;
	private Integer statiId;
	private String statoDescrizione;
	private BigDecimal anticipazione = BigDecimal.ZERO;
	private BigDecimal onorari = BigDecimal.ZERO;
	private BigDecimal iva = BigDecimal.ZERO;
	private BigDecimal parzialeEuro = BigDecimal.ZERO;
	private Integer imposta;
	private Long idRiga;
	
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
	public Integer getImposta() {
		return imposta;
	}
	public void setImposta(Integer imposta) {
		this.imposta = imposta;
	}
	public String getCausaleDescrizione() {
		return causaleDescrizione;
	}
	public void setCausaleDescrizione(String causaleDescrizione) {
		this.causaleDescrizione = causaleDescrizione;
	}
	public String getStatoDescrizione() {
		return statoDescrizione;
	}
	public void setStatoDescrizione(String statoDescrizione) {
		this.statoDescrizione = statoDescrizione;
	}
	public Long getIdRiga() {
		return idRiga;
	}
	public void setIdRiga(Long idRiga) {
		this.idRiga = idRiga;
	}
	

}
