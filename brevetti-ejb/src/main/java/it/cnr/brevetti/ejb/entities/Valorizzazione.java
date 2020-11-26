package it.cnr.brevetti.ejb.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the VALORIZZAZIONI database table.
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Jan 12, 2015]
 *
 */
@Entity @Table(name="VALORIZZAZIONI")
public class Valorizzazione extends AuditTrail {
	private static final long serialVersionUID = 1L;
	public static final String SEQ = "VALORIZZAZIONI_SEQ";
	
	@Id
	@SequenceGenerator(name=Valorizzazione.SEQ, sequenceName=Valorizzazione.SEQ, allocationSize=0)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator=Valorizzazione.SEQ)
	@Column(name="VALORIZZAZIONI_ID")
	private Integer id;
	private String altro;
	@Column(name="AZIENDE_ID")
	private Integer aziendaId;
	private Date fine;
	private Date inizio;
	@Column(name="NOME_PROGETTO")
	private String nomeProgetto;
	private String note;
	private String referente;
	@Column(name="TIPI_VALORIZZAZIONE_ID")
	private Integer tipoValorizzazioneId;

	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAltro() {
		return this.altro;
	}
	public void setAltro(String altro) {
		this.altro = altro;
	}
	public Integer getAziendaId() {
		return this.aziendaId;
	}
	public void setAziendaId(Integer aziendaId) {
		this.aziendaId = aziendaId;
	}
	public Date getFine() {
		return this.fine;
	}
	public void setFine(Date fine) {
		this.fine = fine;
	}
	public Date getInizio() {
		return this.inizio;
	}
	public void setInizio(Date inizio) {
		this.inizio = inizio;
	}
	public String getNomeProgetto() {
		return this.nomeProgetto;
	}
	public void setNomeProgetto(String nomeProgetto) {
		this.nomeProgetto = nomeProgetto;
	}
	public String getNote() {
		return this.note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getReferente() {
		return this.referente;
	}
	public void setReferente(String referente) {
		this.referente = referente;
	}
	public Integer getTipoValorizzazioneId() {
		return this.tipoValorizzazioneId;
	}
	public void setTipoValorizzazioneId(Integer tipoValorizzazioneId) {
		this.tipoValorizzazioneId = tipoValorizzazioneId;
	}
	
	// transient
	@Transient private Azienda azienda;
	@Transient private TipoValorizzazione tipo;
	@Transient private List<Trovato> trovati;

	public Azienda getAzienda() {
		return azienda;
	}
	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}
	public TipoValorizzazione getTipo() {
		return tipo;
	}
	public void setTipo(TipoValorizzazione tipo) {
		this.tipo = tipo;
	}
	public List<Trovato> getTrovati() {
		return trovati;
	}
	public void setTrovati(List<Trovato> trovati) {
		this.trovati = trovati;
	}
	@Override
	public String toString() {
		return "Valorizzazione [id=" + id + "]";
	}	
}