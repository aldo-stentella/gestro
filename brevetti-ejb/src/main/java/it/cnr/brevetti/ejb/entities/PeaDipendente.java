package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="PEA_DIPENDENTE")
public class PeaDipendente implements Serializable {

	@Id
	@Column(name="DIP_ID")
	private String dipId;
	@Column(name="DIP_NOMINATIVO")
	private String dipNominativo;
	@Column(name="DIP_NCAR_COGN")
	private float dipNcarCogn;
	@Column(name="DIP_COGNOME_CON")
	private String dipCognomeCon;
	@Column(name="DIP_ST_CIVILE")
	private String dipStCivile;
	@Column(name="DIP_DEC_CIVILE")
	private Date dipDecCivile;
	@Column(name="DIP_SESSO")
	private String dipSesso;
	@Column(name="DIP_DATA_NAS")
	private Date dipDataNas;
	@Column(name="DIP_COM_NAS")
	private String dipComNas;
	@Column(name="DIP_PROV_NAS")
	private String dipProvNas;
	@Column(name="DIP_NAZ_NAS")
	private String dipNazNas;
	@Column(name="DIP_CITT_ATT")
	private String dipCittAtt;
	@Column(name="DIP_CITT_SEC")
	private String dipCittSec;
	@Column(name="DIP_RIENTRI")
	private float dipRientri;
	@Column(name="DIP_TIPO_ASS")
	private String dipTipoAss;
	@Column(name="DIP_SUP_PROVA")
	private String dipSupProva;
	@Column(name="DIP_DATA_CONC")
	private Date dipDataConc;
	@Column(name="DIP_DATA_ASS")
	private Date dipDataAss;
	@Column(name="DIP_DATA_SERV")
	private Date dipDataServ;
	@Column(name="DIP_LIQ_ACCANT")
	private float dipLiqAccant;
	@Column(name="DIP_RIF_ASS")
	private String dipRifAss;
	@Column(name="DIP_RIF_PROT")
	private String dipRifProt;
	@Column(name="DIP_RIF_CONC")
	private String dipRifConc;
	@Column(name="DIP_ST_ATTUALE")
	private String dipStAttuale;
	@Column(name="DIP_MILITARE")
	private String dipMilitare;
	@Column(name="DIP_POS_FASC")
	private String dipPosFasc;
	@Column(name="DIP_TITOLO")
	private String dipTitolo;
	@Column(name="DIP_COD_FISC")
	private String dipCodFisc;
	@Column(name="DIP_CAUSA_CESS")
	private String dipCausaCess;
	@Column(name="DIP_DATA_P_CS")
	private Date dipDataPCs;
	@Column(name="DIP_DATA_CESS")
	private Date dipDataCess;
	@Column(name="DIP_DATA_SCAD")
	private Date dipDataScad;
	@Column(name="DIP_BEN_LEGGE")
	private String dipBenLegge;
	@Column(name="RAPP_ID")
	private String rappId;
	@Column(name="DIP_PENSIONE")
	private String dipPensione;
	@Column(name="DIP_COM_RES")
	private String dipComRes;
	@Column(name="DIP_PROV_RES")
	private String dipProvRes;
	@Column(name="DIP_IND_RES")
	private String dipIndRes;
	@Column(name="DIP_CAP_RES")
	private String dipCapRes;
	@Column(name="DIP_COM_REC")
	private String dipComRec;
	@Column(name="DIP_PROV_REC")
	private String dipProvRec;
	@Column(name="DIP_IND_REC")
	private String dipIndRec;
	@Column(name="DIP_CAP_REC")
	private String dipCapRec;
	@Column(name="DIP_TEL_REC")
	private String dipTelRec;
	@Column(name="DIP_RIT_TIT")
	private String dipRitTit;
	@Column(name="DIP_OPTANTE")
	private String dipOptante;
	@Column(name="DIP_DATA_LIQ")
	private Date dipDataLiq;
	@Column(name="DIP_NOTE")
	private String dipNote;
	@Column(name="DIP_TIPO_OP")
	private String dipTipoOp;
	@Column(name="DIP_DATA_OP")
	private Date dipDataOp;
	@Column(name="DIP_TIPO_CONT")
	private String dipTipoCont;
	@Column(name="DIP_NORM_ASS")
	private String dipNormAss;
	
	
	public String getDipBenLegge() {
		return dipBenLegge;
	}
	public void setDipBenLegge(String dipBenLegge) {
		this.dipBenLegge = dipBenLegge;
	}
	public String getDipCapRec() {
		return dipCapRec;
	}
	public void setDipCapRec(String dipCapRec) {
		this.dipCapRec = dipCapRec;
	}
	public String getDipCapRes() {
		return dipCapRes;
	}
	public void setDipCapRes(String dipCapRes) {
		this.dipCapRes = dipCapRes;
	}
	public String getDipCausaCess() {
		return dipCausaCess;
	}
	public void setDipCausaCess(String dipCausaCess) {
		this.dipCausaCess = dipCausaCess;
	}
	public String getDipCittAtt() {
		return dipCittAtt;
	}
	public void setDipCittAtt(String dipCittAtt) {
		this.dipCittAtt = dipCittAtt;
	}
	public String getDipCittSec() {
		return dipCittSec;
	}
	public void setDipCittSec(String dipCittSec) {
		this.dipCittSec = dipCittSec;
	}
	public String getDipCodFisc() {
		return dipCodFisc;
	}
	public void setDipCodFisc(String dipCodFisc) {
		this.dipCodFisc = dipCodFisc;
	}
	public String getDipCognomeCon() {
		return dipCognomeCon;
	}
	public void setDipCognomeCon(String dipCognomeCon) {
		this.dipCognomeCon = dipCognomeCon;
	}
	public String getDipComNas() {
		return dipComNas;
	}
	public void setDipComNas(String dipComNas) {
		this.dipComNas = dipComNas;
	}
	public String getDipComRec() {
		return dipComRec;
	}
	public void setDipComRec(String dipComRec) {
		this.dipComRec = dipComRec;
	}
	public String getDipComRes() {
		return dipComRes;
	}
	public void setDipComRes(String dipComRes) {
		this.dipComRes = dipComRes;
	}
	public Date getDipDataAss() {
		return dipDataAss;
	}
	public void setDipDataAss(Date dipDataAss) {
		this.dipDataAss = dipDataAss;
	}
	public Date getDipDataCess() {
		return dipDataCess;
	}
	public void setDipDataCess(Date dipDataCess) {
		this.dipDataCess = dipDataCess;
	}
	public Date getDipDataConc() {
		return dipDataConc;
	}
	public void setDipDataConc(Date dipDataConc) {
		this.dipDataConc = dipDataConc;
	}
	public Date getDipDataLiq() {
		return dipDataLiq;
	}
	public void setDipDataLiq(Date dipDataLiq) {
		this.dipDataLiq = dipDataLiq;
	}
	public Date getDipDataNas() {
		return dipDataNas;
	}
	public void setDipDataNas(Date dipDataNas) {
		this.dipDataNas = dipDataNas;
	}
	public Date getDipDataOp() {
		return dipDataOp;
	}
	public void setDipDataOp(Date dipDataOp) {
		this.dipDataOp = dipDataOp;
	}
	public Date getDipDataPCs() {
		return dipDataPCs;
	}
	public void setDipDataPCs(Date dipDataPCs) {
		this.dipDataPCs = dipDataPCs;
	}
	public Date getDipDataScad() {
		return dipDataScad;
	}
	public void setDipDataScad(Date dipDataScad) {
		this.dipDataScad = dipDataScad;
	}
	public Date getDipDataServ() {
		return dipDataServ;
	}
	public void setDipDataServ(Date dipDataServ) {
		this.dipDataServ = dipDataServ;
	}
	public Date getDipDecCivile() {
		return dipDecCivile;
	}
	public void setDipDecCivile(Date dipDecCivile) {
		this.dipDecCivile = dipDecCivile;
	}
	public String getDipId() {
		return dipId;
	}
	public void setDipId(String dipId) {
		this.dipId = dipId;
	}
	public String getDipIndRec() {
		return dipIndRec;
	}
	public void setDipIndRec(String dipIndRec) {
		this.dipIndRec = dipIndRec;
	}
	public String getDipIndRes() {
		return dipIndRes;
	}
	public void setDipIndRes(String dipIndRes) {
		this.dipIndRes = dipIndRes;
	}
	public float getDipLiqAccant() {
		return dipLiqAccant;
	}
	public void setDipLiqAccant(float dipLiqAccant) {
		this.dipLiqAccant = dipLiqAccant;
	}
	public String getDipMilitare() {
		return dipMilitare;
	}
	public void setDipMilitare(String dipMilitare) {
		this.dipMilitare = dipMilitare;
	}
	public String getDipNazNas() {
		return dipNazNas;
	}
	public void setDipNazNas(String dipNazNas) {
		this.dipNazNas = dipNazNas;
	}
	public float getDipNcarCogn() {
		return dipNcarCogn;
	}
	public void setDipNcarCogn(float dipNcarCogn) {
		this.dipNcarCogn = dipNcarCogn;
	}
	public String getDipNominativo() {
		return dipNominativo;
	}
	public void setDipNominativo(String dipNominativo) {
		this.dipNominativo = dipNominativo;
	}
	public String getDipNormAss() {
		return dipNormAss;
	}
	public void setDipNormAss(String dipNormAss) {
		this.dipNormAss = dipNormAss;
	}
	public String getDipNote() {
		return dipNote;
	}
	public void setDipNote(String dipNote) {
		this.dipNote = dipNote;
	}
	public String getDipOptante() {
		return dipOptante;
	}
	public void setDipOptante(String dipOptante) {
		this.dipOptante = dipOptante;
	}
	public String getDipPensione() {
		return dipPensione;
	}
	public void setDipPensione(String dipPensione) {
		this.dipPensione = dipPensione;
	}
	public String getDipPosFasc() {
		return dipPosFasc;
	}
	public void setDipPosFasc(String dipPosFasc) {
		this.dipPosFasc = dipPosFasc;
	}
	public String getDipProvNas() {
		return dipProvNas;
	}
	public void setDipProvNas(String dipProvNas) {
		this.dipProvNas = dipProvNas;
	}
	public String getDipProvRec() {
		return dipProvRec;
	}
	public void setDipProvRec(String dipProvRec) {
		this.dipProvRec = dipProvRec;
	}
	public String getDipProvRes() {
		return dipProvRes;
	}
	public void setDipProvRes(String dipProvRes) {
		this.dipProvRes = dipProvRes;
	}
	public float getDipRientri() {
		return dipRientri;
	}
	public void setDipRientri(float dipRientri) {
		this.dipRientri = dipRientri;
	}
	public String getDipRifAss() {
		return dipRifAss;
	}
	public void setDipRifAss(String dipRifAss) {
		this.dipRifAss = dipRifAss;
	}
	public String getDipRifConc() {
		return dipRifConc;
	}
	public void setDipRifConc(String dipRifConc) {
		this.dipRifConc = dipRifConc;
	}
	public String getDipRifProt() {
		return dipRifProt;
	}
	public void setDipRifProt(String dipRifProt) {
		this.dipRifProt = dipRifProt;
	}
	public String getDipRitTit() {
		return dipRitTit;
	}
	public void setDipRitTit(String dipRitTit) {
		this.dipRitTit = dipRitTit;
	}
	public String getDipSesso() {
		return dipSesso;
	}
	public void setDipSesso(String dipSesso) {
		this.dipSesso = dipSesso;
	}
	public String getDipStAttuale() {
		return dipStAttuale;
	}
	public void setDipStAttuale(String dipStAttuale) {
		this.dipStAttuale = dipStAttuale;
	}
	public String getDipStCivile() {
		return dipStCivile;
	}
	public void setDipStCivile(String dipStCivile) {
		this.dipStCivile = dipStCivile;
	}
	public String getDipSupProva() {
		return dipSupProva;
	}
	public void setDipSupProva(String dipSupProva) {
		this.dipSupProva = dipSupProva;
	}
	public String getDipTelRec() {
		return dipTelRec;
	}
	public void setDipTelRec(String dipTelRec) {
		this.dipTelRec = dipTelRec;
	}
	public String getDipTipoAss() {
		return dipTipoAss;
	}
	public void setDipTipoAss(String dipTipoAss) {
		this.dipTipoAss = dipTipoAss;
	}
	public String getDipTipoCont() {
		return dipTipoCont;
	}
	public void setDipTipoCont(String dipTipoCont) {
		this.dipTipoCont = dipTipoCont;
	}
	public String getDipTipoOp() {
		return dipTipoOp;
	}
	public void setDipTipoOp(String dipTipoOp) {
		this.dipTipoOp = dipTipoOp;
	}
	public String getDipTitolo() {
		return dipTitolo;
	}
	public void setDipTitolo(String dipTitolo) {
		this.dipTitolo = dipTitolo;
	}
	public String getRappId() {
		return rappId;
	}
	public void setRappId(String rappId) {
		this.rappId = rappId;
	}

}
