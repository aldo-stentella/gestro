package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="PEG_SEDE")
public class PegSede implements Serializable {

	@Id
	@Column(name="SEDE_ID")
	private String sedeId;
	@Column(name="SED_DIREZIONE")
	private String sedDirezione;
	@Column(name="SED_INDIRIZZO")
	private String sedIndirizzo;
	@Column(name="SED_COD_CITTA")
	private String sedCodCitta;
	@Column(name="SED_CAP")
	private String sedCap;
	@Column(name="SED_PROV")
	private String sedProv;
	@Column(name="SED_TELEX")
	private String sedTelex;
	@Column(name="SED_FAX")
	private String sedFax;
	@Column(name="SED_TELEFONO")
	private String sedTelefono;
	@Column(name="SED_TELEFONO_2")
	private String sedTelefono2;
	@Column(name="SED_TELEFONO_3")
	private String sedTelefono3;
	@Column(name="SED_EMAIL")
	private String sedEmail;
	@Column(name="SED_DATA_IST")
	private Date sedDataIst;
	@Column(name="SED_DATA_DIS")
	private Date sedDataDis;
	@Column(name="SED_TIPO_OP")
	private String sedTipoOp;
	@Column(name="SED_DATA_OP")
	private Date sedDataOp;
	@Column(name="SED_RSU")
	private String sedRsu;
	@Column(name="SED_POSIZIONE")
	private String sedPosizione;
	@Column(name="SED_TIT")
	private String sedTit;
	@Column(name="TIT_CA")
	private String titCa;
	
	
	public String getSedCap() {
		return sedCap;
	}
	public void setSedCap(String sedCap) {
		this.sedCap = sedCap;
	}
	public String getSedCodCitta() {
		return sedCodCitta;
	}
	public void setSedCodCitta(String sedCodCitta) {
		this.sedCodCitta = sedCodCitta;
	}
	public Date getSedDataDis() {
		return sedDataDis;
	}
	public void setSedDataDis(Date sedDataDis) {
		this.sedDataDis = sedDataDis;
	}
	public Date getSedDataIst() {
		return sedDataIst;
	}
	public void setSedDataIst(Date sedDataIst) {
		this.sedDataIst = sedDataIst;
	}
	public Date getSedDataOp() {
		return sedDataOp;
	}
	public void setSedDataOp(Date sedDataOp) {
		this.sedDataOp = sedDataOp;
	}
	public String getSedDirezione() {
		return sedDirezione;
	}
	public void setSedDirezione(String sedDirezione) {
		this.sedDirezione = sedDirezione;
	}
	public String getSedeId() {
		return sedeId;
	}
	public void setSedeId(String sedeId) {
		this.sedeId = sedeId;
	}
	public String getSedEmail() {
		return sedEmail;
	}
	public void setSedEmail(String sedEmail) {
		this.sedEmail = sedEmail;
	}
	public String getSedFax() {
		return sedFax;
	}
	public void setSedFax(String sedFax) {
		this.sedFax = sedFax;
	}
	public String getSedIndirizzo() {
		return sedIndirizzo;
	}
	public void setSedIndirizzo(String sedIndirizzo) {
		this.sedIndirizzo = sedIndirizzo;
	}
	public String getSedPosizione() {
		return sedPosizione;
	}
	public void setSedPosizione(String sedPosizione) {
		this.sedPosizione = sedPosizione;
	}
	public String getSedProv() {
		return sedProv;
	}
	public void setSedProv(String sedProv) {
		this.sedProv = sedProv;
	}
	public String getSedRsu() {
		return sedRsu;
	}
	public void setSedRsu(String sedRsu) {
		this.sedRsu = sedRsu;
	}
	public String getSedTelefono() {
		return sedTelefono;
	}
	public void setSedTelefono(String sedTelefono) {
		this.sedTelefono = sedTelefono;
	}
	public String getSedTelefono2() {
		return sedTelefono2;
	}
	public void setSedTelefono2(String sedTelefono2) {
		this.sedTelefono2 = sedTelefono2;
	}
	public String getSedTelefono3() {
		return sedTelefono3;
	}
	public void setSedTelefono3(String sedTelefono3) {
		this.sedTelefono3 = sedTelefono3;
	}
	public String getSedTelex() {
		return sedTelex;
	}
	public void setSedTelex(String sedTelex) {
		this.sedTelex = sedTelex;
	}
	public String getSedTipoOp() {
		return sedTipoOp;
	}
	public void setSedTipoOp(String sedTipoOp) {
		this.sedTipoOp = sedTipoOp;
	}
	public String getSedTit() {
		return sedTit;
	}
	public void setSedTit(String sedTit) {
		this.sedTit = sedTit;
	}
	public String getTitCa() {
		return titCa;
	}
	public void setTitCa(String titCa) {
		this.titCa = titCa;
	}

}
