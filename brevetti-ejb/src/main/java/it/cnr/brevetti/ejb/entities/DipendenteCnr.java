package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity bean per la gestione della vista V_DIPENDENTI_CNR
 * @author Aurelio D'Amico
 * @version 1.0 [27-May-08]
 */

@Entity @Table(name="V_DIPENDENTI_CNR")
public class DipendenteCnr implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @Column(name="MATRICOLA_CNR")
	private Integer matricolaCnr;
	@Column(name="INVENTORI_ID")
	private Integer id;
	private String tipo;
	private String titolo;
	private String nome;
	private String cognome;
	private String indirizzo;
	private String cap;
	private String citta;
	private String telefono;
	private String cellulare;
	private String fax;
	private String email;
	@Column(name="COD_ANAGRAFICA_SIGLA")
	private String codAnagraficaSigla;
	@Column(name="COD_TERZO_SIGLA")
	private String codTerzoSigla;
	private String banca;
	private String abi;
	private String cab;
	private String cc;
	@Column(name="ENTE_RECAPITO")
	private String enteRecapito;
	@Column(name="STRUTTURA_ENTE_RECAPITO")
	private String strutturaEnteRecapito;
	@Column(name="SEZIONI_ID")
	private Integer sezioniId;
	@Column(name="ISTITUTI_ID")
	private Integer istitutiId;
	@Column(name="OLD_SYSTEM")
	private Integer oldSystem;
	@Column(name="DATA_CESSAZIONE")
	private Date dataCessazione;
	
	public String getAbi() {
		return abi;
	}
	public void setAbi(String abi) {
		this.abi = abi;
	}
	public String getBanca() {
		return banca;
	}
	public void setBanca(String banca) {
		this.banca = banca;
	}
	public String getCab() {
		return cab;
	}
	public void setCab(String cab) {
		this.cab = cab;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getCellulare() {
		return cellulare;
	}
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getCodAnagraficaSigla() {
		return codAnagraficaSigla;
	}
	public void setCodAnagraficaSigla(String codAnagraficaSigla) {
		this.codAnagraficaSigla = codAnagraficaSigla;
	}
	public String getCodTerzoSigla() {
		return codTerzoSigla;
	}
	public void setCodTerzoSigla(String codTerzoSigla) {
		this.codTerzoSigla = codTerzoSigla;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEnteRecapito() {
		return enteRecapito;
	}
	public void setEnteRecapito(String enteRecapito) {
		this.enteRecapito = enteRecapito;
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
	public Integer getIstitutiId() {
		return istitutiId;
	}
	public void setIstitutiId(Integer istitutiId) {
		this.istitutiId = istitutiId;
	}
	public Integer getMatricolaCnr() {
		return matricolaCnr;
	}
	public void setMatricolaCnr(Integer matricolaCnr) {
		this.matricolaCnr = matricolaCnr;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getOldSystem() {
		return oldSystem;
	}
	public void setOldSystem(Integer oldSystem) {
		this.oldSystem = oldSystem;
	}
	public Integer getSezioniId() {
		return sezioniId;
	}
	public void setSezioniId(Integer sezioniId) {
		this.sezioniId = sezioniId;
	}
	public String getStrutturaEnteRecapito() {
		return strutturaEnteRecapito;
	}
	public void setStrutturaEnteRecapito(String strutturaEnteRecapito) {
		this.strutturaEnteRecapito = strutturaEnteRecapito;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public Date getDataCessazione() {
		return dataCessazione;
	}
	public void setDataCessazione(Date dataCessazione) {
		this.dataCessazione = dataCessazione;
	}
	@Override
	public String toString() {
		return "DipendenteCnr [id=" + id + ", matricolaCnr=" + matricolaCnr
				+ ", nome=" + nome + ", cognome=" + cognome + "]";
	}
}
