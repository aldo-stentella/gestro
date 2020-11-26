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
 * Entity bean per la gestione della tabella ENTI_ESTERNI
 * @author Aldo Stentella
 * @version 1.1 [14-Jan-08] (aurelio)
 * - aggiunta sequenza
 */
@Entity @Table(name="ENTI_ESTERNI")
public class EnteEsterno implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String SEQ = "ENTI_ESTERNI_SEQ";
	
	@Id @Column(name="ENTI_ESTERNI_ID")
	@SequenceGenerator(name=EnteEsterno.SEQ, sequenceName=EnteEsterno.SEQ, allocationSize=0)
	@GeneratedValue(generator=EnteEsterno.SEQ, strategy=GenerationType.SEQUENCE)	
	private Integer id;
	private String nome;
	private String struttura;
	private String sigla;
	private String indirizzo;
	private String cap;
	private String localita;
	private String provincia;
	private String regione;
	@Column(name="STATO_ID")
	private Integer statoId;
	private String telefono;
	private String telex;
	private String fax;
	private String email;
	@Column(name="SITO_WEB")
	private String sitoWeb;
	@Column(name="PARTITA_IVA")
	private String partitaIva;
	private String note;
	private Integer status;
	
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
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
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getRegione() {
		return regione;
	}
	public void setRegione(String regione) {
		this.regione = regione;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
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
	public String getStruttura() {
		return struttura;
	}
	public void setStruttura(String struttura) {
		this.struttura = struttura;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
