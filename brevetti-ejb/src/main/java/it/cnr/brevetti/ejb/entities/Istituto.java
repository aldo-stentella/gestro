package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity @Table(name="ISTITUTI")
public class Istituto implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id	@Column(name="ISTITUTI_ID")
	private Integer id;
	@Column(name="ISTITUTO_SIGLA")
	private String istitutoSigla;
	@Column(name="NOME")
	private String nome;
	@Column(name="DIPARTIMENTI_ID")
	private Integer dipartimentiId;
	private String cds;
	@Column(name="CD_UO")
	private String cdUo;
	@Column(name="SEDE_ID")
	private String sedeId;
	private String indirizzo;
	private String cap;
	private String comune;
	private String prov;
	private String telex;
	private String fax;
	private String telefono;
	@Column(name="TELEFONO_2")
	private String telefono2;
	@Column(name="TELEFONO_3")
	private String telefono3;
	private String email;
	@Column(name="SITO_WEB_OLD")
	private String sitoWebOld;
	private String direttore;
	@Column(name="CONS_SCIENTIFICO_OLD")
	private String consScientificoOld;
	@Column(name="COM_IST_OLD")
	private String comIstOld;
	@Column(name="TIT_CNR_OLD")
	private Integer  titCnrOld;
	@Column(name="DATA_INIZIO")
	private Date dataInizio;
	@Column(name="DATA_FINE")
	private Date dataFine;
	private String note;
	
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getCds() {
		return cds;
	}
	public void setCds(String cds) {
		this.cds = cds;
	}
	public String getCdUo() {
		return cdUo;
	}
	public void setCdUo(String cdUo) {
		this.cdUo = cdUo;
	}
	public String getComIstOld() {
		return comIstOld;
	}
	public void setComIstOld(String comIstOld) {
		this.comIstOld = comIstOld;
	}
	public String getComune() {
		return comune;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
	public String getConsScientificoOld() {
		return consScientificoOld;
	}
	public void setConsScientificoOld(String consScientificoOld) {
		this.consScientificoOld = consScientificoOld;
	}
	public Date getDataFine() {
		return dataFine;
	}
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	public Date getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	public Integer getDipartimentiId() {
		return dipartimentiId;
	}
	public void setDipartimentiId(Integer dipartimentiId) {
		this.dipartimentiId = dipartimentiId;
	}
	public String getDirettore() {
		return direttore;
	}
	public void setDirettore(String direttore) {
		this.direttore = direttore;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getIstitutoSigla() {
		return istitutoSigla;
	}
	public void setIstitutoSigla(String istitutoSigla) {
		this.istitutoSigla = istitutoSigla;
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
	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}
	public String getSedeId() {
		return sedeId;
	}
	public void setSedeId(String sedeId) {
		this.sedeId = sedeId;
	}
	public String getSitoWebOld() {
		return sitoWebOld;
	}
	public void setSitoWebOld(String sitoWebOld) {
		this.sitoWebOld = sitoWebOld;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	public String getTelefono3() {
		return telefono3;
	}
	public void setTelefono3(String telefono3) {
		this.telefono3 = telefono3;
	}
	public String getTelex() {
		return telex;
	}
	public void setTelex(String telex) {
		this.telex = telex;
	}
	public Integer getTitCnrOld() {
		return titCnrOld;
	}
	public void setTitCnrOld(Integer titCnrOld) {
		this.titCnrOld = titCnrOld;
	}
}
