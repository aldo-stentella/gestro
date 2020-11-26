package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="DIPARTIMENTI")
public class Dipartimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @Column(name="DIPARTIMENTI_ID")
	private Integer id;
	private String sigla;
	private String descrizione;
	@Column(name="CD_UO")
	private String cdUo;
	@Column(name="DATA_ISTITUZIONE")
	private Date dataIstituzione;
	@Column(name="DATA_SOPPRESSIONE")
	private Date dataSoppressione;
	@Column(name="COD_SEDE")
	private String codSede;
	private String direttore;
	private String indirizzo;
	private String citta;
	private String cap;
	private String prov;
	private String telefono;
	@Column(name="TELEFONO_2")
	private String telefono2;
	@Column(name="TELEFONO_3")
	private String telefono3;
	private String email;
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getCdUo() {
		return cdUo;
	}
	public void setCdUo(String cdUo) {
		this.cdUo = cdUo;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getCodSede() {
		return codSede;
	}
	public void setCodSede(String codSede) {
		this.codSede = codSede;
	}
	public Date getDataIstituzione() {
		return dataIstituzione;
	}
	public void setDataIstituzione(Date dataIstituzione) {
		this.dataIstituzione = dataIstituzione;
	}
	public Date getDataSoppressione() {
		return dataSoppressione;
	}
	public void setDataSoppressione(Date dataSoppressione) {
		this.dataSoppressione = dataSoppressione;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
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
	public Dipartimento() {
		// empty contructor
	}
	public Dipartimento(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Dipartimento [id=" + id + ", sigla=" + sigla + ", descrizione="
				+ descrizione + "]";
	}		
}
