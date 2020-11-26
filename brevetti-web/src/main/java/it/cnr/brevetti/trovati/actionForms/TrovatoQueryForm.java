package it.cnr.brevetti.trovati.actionForms;

import org.apache.struts.action.ActionForm;

/**
 * @author astentella
 * 
 *
 */
public class TrovatoQueryForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private int mode = 0;
	private int nextab = 1;
	private Integer nsrif;
	private String titolo;
	private String parolaChiave;
	private Integer linguat = new Integer(1);
	private Integer linguak = new Integer(1);
	private Integer tipoTrovato;
	private String inventore;
	private String studio;
	private String numDeposito;
	private String dataDeposito1;
	private String dataDeposito2;
	private String numRilascio;
	private String dataRilascio1;
	private String dataRilascio2;
	private Integer istituto;
	private Integer dipartimento;
	private String enteEsterno;
	private String dataAbbandono1;
	private String dataAbbandono2;
	private Integer abbandono;
	private Integer cessioneDiritti = new Integer(0);
	private String utentiId;
	
	public Integer getAbbandono() {
		return abbandono;
	}
	public void setAbbandono(Integer abbandono) {
		this.abbandono = abbandono;
	}
	public String getDataAbbandono1() {
		return dataAbbandono1;
	}
	public void setDataAbbandono1(String dataAbbandono1) {
		this.dataAbbandono1 = dataAbbandono1;
	}
	public String getDataAbbandono2() {
		return dataAbbandono2;
	}
	public void setDataAbbandono2(String dataAbbandono2) {
		this.dataAbbandono2 = dataAbbandono2;
	}
	public String getDataDeposito1() {
		return dataDeposito1;
	}
	public void setDataDeposito1(String dataDeposito1) {
		this.dataDeposito1 = dataDeposito1;
	}
	public String getDataDeposito2() {
		return dataDeposito2;
	}
	public void setDataDeposito2(String dataDeposito2) {
		this.dataDeposito2 = dataDeposito2;
	}
	public String getDataRilascio1() {
		return dataRilascio1;
	}
	public void setDataRilascio1(String dataRilascio1) {
		this.dataRilascio1 = dataRilascio1;
	}
	public String getDataRilascio2() {
		return dataRilascio2;
	}
	public void setDataRilascio2(String dataRilascio2) {
		this.dataRilascio2 = dataRilascio2;
	}
	public Integer getDipartimento() {
		return dipartimento;
	}
	public void setDipartimento(Integer dipartimento) {
		this.dipartimento = dipartimento;
	}
	public String getEnteEsterno() {
		return enteEsterno;
	}
	public void setEnteEsterno(String enteEsterno) {
		this.enteEsterno = enteEsterno;
	}
	public String getInventore() {
		return inventore;
	}
	public void setInventore(String inventore) {
		this.inventore = inventore;
	}
	public Integer getIstituto() {
		return istituto;
	}
	public void setIstituto(Integer istituto) {
		this.istituto = istituto;
	}
	public String getNumDeposito() {
		return numDeposito;
	}
	public void setNumDeposito(String numDeposito) {
		this.numDeposito = numDeposito;
	}
	public String getNumRilascio() {
		return numRilascio;
	}
	public void setNumRilascio(String numRilascio) {
		this.numRilascio = numRilascio;
	}
	public String getParolaChiave() {
		return parolaChiave;
	}
	public void setParolaChiave(String parolaChiave) {
		this.parolaChiave = parolaChiave;
	}
	public String getStudio() {
		return studio;
	}
	public void setStudio(String studio) {
		this.studio = studio;
	}
	public Integer getTipoTrovato() {
		return tipoTrovato;
	}
	public void setTipoTrovato(Integer tipoTrovato) {
		this.tipoTrovato = tipoTrovato;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public Integer getLinguak() {
		return linguak;
	}
	public void setLinguak(Integer linguak) {
		this.linguak = linguak;
	}
	public Integer getLinguat() {
		return linguat;
	}
	public void setLinguat(Integer linguat) {
		this.linguat = linguat;
	}
	public int getNextab() {
		return nextab;
	}
	public void setNextab(int nextab) {
		this.nextab = nextab;
	}
	public Integer getNsrif() {
		return nsrif;
	}
	public void setNsrif(Integer nsrif) {
		this.nsrif = nsrif;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public Integer getCessioneDiritti() {
		return cessioneDiritti;
	}
	public void setCessioneDiritti(Integer cessioneDiritti) {
		this.cessioneDiritti = cessioneDiritti;
	}
	public String getUtentiId() {
		return utentiId;
	}
	public void setUtentiId(String utentiId) {
		this.utentiId = utentiId;
	}
	
}
