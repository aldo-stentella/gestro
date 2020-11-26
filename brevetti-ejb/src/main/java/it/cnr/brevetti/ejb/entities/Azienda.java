package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity @Table(name="AZIENDE")
@SequenceGenerator(name=Azienda.SEQ, sequenceName=Azienda.SEQ, allocationSize=0)
public class Azienda implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String SEQ = "AZIENDE_SEQ";
	
	@Id @Column(name="AZIENDE_ID")
	@GeneratedValue(generator=Azienda.SEQ, strategy=GenerationType.SEQUENCE)	
	private Integer id;
	private String tipo;
	private String nome;
	private String indirizzo;
	private String cap;
	private String localita;
	private String comune;
	private String regione;
	@Column(name="STATO_ID")
	private Integer statoId;
	private String referente;
	private String rappresentante;
	@Column(name="RUOLO_RAPPRESENTANTE")
	private String ruoloRappresentante;
	private String telefono;
	private String telex;
	private String fax;
	@Column(name="PARTITA_IVA")
	private String partitaIva;
	@Column(name="SITO_WEB")
	private String sitoWeb;
	private String settore;
	@Column(name="CONTATT_SCIENT")
	private String contattScient;
	@Column(name="TEL_CONTATT_SCIENT")
	private String telContattScient;
	@Column(name="CELL_CONTATT_SCIENT")
	private String cellContattScient;
	@Column(name="FAX_CONTATT_SCIENT")
	private String faxContattScient;
	@Column(name="EMAIL_CONTATT_SCIENT")
	private String emailContattScient;
	@Column(name="CONTATT_LEGALE")
	private String contattLegale;
	@Column(name="TEL_CONTATT_LEGALE")
	private String telContattLegale;
	@Column(name="CELL_CONTATT_LEGALE")
	private String cellContattLegale;
	@Column(name="FAX_CONTATT_LEGALE")
	private String faxContattLegale;
	@Column(name="EMAIL_CONTATT_LEGALE")
	private String emailContattLegale;
	@Column(name="CONTATT_AMMINIS")
	private String contattAmminis;
	@Column(name="TEL_CONTATT_AMMINIS")
	private String telContattAmminis;
	@Column(name="CELL_CONTATT_AMMINIS")
	private String cellContattAmminis;
	@Column(name="FAX_CONTATT_AMMINIS")
	private String faxContattAmminis;
	@Column(name="EMAIL_CONTATT_AMMINIS")
	private String emailContattAmminis;
	private String note;
	private Integer status;
	
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getCellContattAmminis() {
		return cellContattAmminis;
	}
	public void setCellContattAmminis(String cellContattAmminis) {
		this.cellContattAmminis = cellContattAmminis;
	}
	public String getCellContattLegale() {
		return cellContattLegale;
	}
	public void setCellContattLegale(String cellContattLegale) {
		this.cellContattLegale = cellContattLegale;
	}
	public String getCellContattScient() {
		return cellContattScient;
	}
	public void setCellContattScient(String cellContattScient) {
		this.cellContattScient = cellContattScient;
	}
	public String getComune() {
		return comune;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
	public String getContattAmminis() {
		return contattAmminis;
	}
	public void setContattAmminis(String contattAmminis) {
		this.contattAmminis = contattAmminis;
	}
	public String getContattLegale() {
		return contattLegale;
	}
	public void setContattLegale(String contattLegale) {
		this.contattLegale = contattLegale;
	}
	public String getContattScient() {
		return contattScient;
	}
	public void setContattScient(String contattScient) {
		this.contattScient = contattScient;
	}
	public String getEmailContattAmminis() {
		return emailContattAmminis;
	}
	public void setEmailContattAmminis(String emailContattAmminis) {
		this.emailContattAmminis = emailContattAmminis;
	}
	public String getEmailContattLegale() {
		return emailContattLegale;
	}
	public void setEmailContattLegale(String emailContattLegale) {
		this.emailContattLegale = emailContattLegale;
	}
	public String getEmailContattScient() {
		return emailContattScient;
	}
	public void setEmailContattScient(String emailContattScient) {
		this.emailContattScient = emailContattScient;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getFaxContattAmminis() {
		return faxContattAmminis;
	}
	public void setFaxContattAmminis(String faxContattAmminis) {
		this.faxContattAmminis = faxContattAmminis;
	}
	public String getFaxContattLegale() {
		return faxContattLegale;
	}
	public void setFaxContattLegale(String faxContattLegale) {
		this.faxContattLegale = faxContattLegale;
	}
	public String getFaxContattScient() {
		return faxContattScient;
	}
	public void setFaxContattScient(String faxContattScient) {
		this.faxContattScient = faxContattScient;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getLocalita() {
		return localita;
	}
	public void setLocalita(String localita) {
		this.localita = localita;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPartitaIva() {
		return partitaIva;
	}
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	public String getRappresentante() {
		return rappresentante;
	}
	public void setRappresentante(String rappresentante) {
		this.rappresentante = rappresentante;
	}
	public String getReferente() {
		return referente;
	}
	public void setReferente(String referente) {
		this.referente = referente;
	}
	public String getRegione() {
		return regione;
	}
	public void setRegione(String regione) {
		this.regione = regione;
	}
	public String getRuoloRappresentante() {
		return ruoloRappresentante;
	}
	public void setRuoloRappresentante(String ruoloRappresentante) {
		this.ruoloRappresentante = ruoloRappresentante;
	}
	public String getSettore() {
		return settore;
	}
	public void setSettore(String settore) {
		this.settore = settore;
	}
	public String getSitoWeb() {
		return sitoWeb;
	}
	public void setSitoWeb(String sitoWeb) {
		this.sitoWeb = sitoWeb;
	}
	public Integer getStatoId() {
		return statoId;
	}
	public void setStatoId(Integer statoId) {
		this.statoId = statoId;
	}
	public String getTelContattAmminis() {
		return telContattAmminis;
	}
	public void setTelContattAmminis(String telContattAmminis) {
		this.telContattAmminis = telContattAmminis;
	}
	public String getTelContattLegale() {
		return telContattLegale;
	}
	public void setTelContattLegale(String telContattLegale) {
		this.telContattLegale = telContattLegale;
	}
	public String getTelContattScient() {
		return telContattScient;
	}
	public void setTelContattScient(String telContattScient) {
		this.telContattScient = telContattScient;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTelex() {
		return telex;
	}
	public void setTelex(String telex) {
		this.telex = telex;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}
