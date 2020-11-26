package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @version 1.1 [18-Dec-07] aggiunta sequenza (Aurelio)
 */

@Entity @Table(name="STUDI_BREVETTUALI")
public class StudioBrevettuale implements Serializable {	
	private static final long serialVersionUID = 1L;
	public static final String SEQ = "STUDI_BREVETTUALI_SEQ";	
	
	@Id @Column(name="STUDI_BREVETTUALI_ID")
	@SequenceGenerator(name=StudioBrevettuale.SEQ, sequenceName=StudioBrevettuale.SEQ, allocationSize=0)
	@GeneratedValue(generator=StudioBrevettuale.SEQ, strategy=GenerationType.SEQUENCE)		
	private Integer id;
	private String denominazione;
	private String indirizzo;
	private String cap;
	private String citta;
	@Column(name="SIGLA_CITTA")
	private String siglaCitta;
	private String telefono1;
	private String telefono2;
	private String cellulare;
	private String fax;
	@Column(name="E_MAIL")
	private String eMail ;
	@Column(name="SITO_WEB")
	private String sitoWeb ;
	@Column(name="TIPO_SOCIETA")
	private String tipoSocieta ;
	@Column(name="CONTATTO_AMM")
	private String contattoAmm ;
	@Column(name="TEL_CONT_AMM")
	private String telContAmm ;
	@Column(name="CELL_CONT_AMM")
	private String cellContAmm;
	@Column(name="CONTATTO_SCIENT")
	private String contattoScient;
	@Column(name="TEL_CONT_SCIENT")
	private String telContScient;
	@Column(name="CELL_CONT_SCIENT")
	private String cellContScient;
	@Column(name="BANCA_1")
	private String banca1;
	@Column(name="ABI_CAB_1")
	private String abiCab1;
	@Column(name="C_C1")
	private String cC1;
	@Column(name="BANCA_2")
	private String banca2;
	@Column(name="ABI_CAB_2")
	private String abiCab2;
	@Column(name="C_C2")
	private String cC2;
	@Column(name="PARTITA_IVA")
	private String partitaIva;
	private String cf;
	private Integer cir;
	private Integer status;
	
	public String getAbiCab1() {
		return abiCab1;
	}
	public void setAbiCab1(String abiCab1) {
		this.abiCab1 = abiCab1;
	}
	public String getAbiCab2() {
		return abiCab2;
	}
	public void setAbiCab2(String abiCab2) {
		this.abiCab2 = abiCab2;
	}
	public String getBanca1() {
		return banca1;
	}
	public void setBanca1(String banca1) {
		this.banca1 = banca1;
	}
	public String getBanca2() {
		return banca2;
	}
	public void setBanca2(String banca2) {
		this.banca2 = banca2;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getCC1() {
		return cC1;
	}
	public void setCC1(String cc1) {
		cC1 = cc1;
	}
	public String getCC2() {
		return cC2;
	}
	public void setCC2(String cc2) {
		cC2 = cc2;
	}
	public String getCellContAmm() {
		return cellContAmm;
	}
	public void setCellContAmm(String cellContAmm) {
		this.cellContAmm = cellContAmm;
	}
	public String getCellContScient() {
		return cellContScient;
	}
	public void setCellContScient(String cellContScient) {
		this.cellContScient = cellContScient;
	}
	public String getCellulare() {
		return cellulare;
	}
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	public Integer getCir() {
		return cir;
	}
	public void setCir(Integer cir) {
		this.cir = cir;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getContattoAmm() {
		return contattoAmm;
	}
	public void setContattoAmm(String contattoAmm) {
		this.contattoAmm = contattoAmm;
	}
	public String getContattoScient() {
		return contattoScient;
	}
	public void setContattoScient(String contattoScient) {
		this.contattoScient = contattoScient;
	}
	public String getDenominazione() {
		return denominazione;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	public String getEMail() {
		return eMail;
	}
	public void setEMail(String mail) {
		eMail = mail;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
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
	public String getPartitaIva() {
		return partitaIva;
	}
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	public String getSiglaCitta() {
		return siglaCitta;
	}
	public void setSiglaCitta(String siglaCitta) {
		this.siglaCitta = siglaCitta;
	}
	public String getSitoWeb() {
		return sitoWeb;
	}
	public void setSitoWeb(String sitoWeb) {
		this.sitoWeb = sitoWeb;
	}
	public String getTelContAmm() {
		return telContAmm;
	}
	public void setTelContAmm(String telContAmm) {
		this.telContAmm = telContAmm;
	}
	public String getTelContScient() {
		return telContScient;
	}
	public void setTelContScient(String telContScient) {
		this.telContScient = telContScient;
	}
	public String getTelefono1() {
		return telefono1;
	}
	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}
	public String getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	public String getTipoSocieta() {
		return tipoSocieta;
	}
	public void setTipoSocieta(String tipoSocieta) {
		this.tipoSocieta = tipoSocieta;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}
