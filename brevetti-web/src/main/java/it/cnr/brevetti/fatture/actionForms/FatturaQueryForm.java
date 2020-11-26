package it.cnr.brevetti.fatture.actionForms;

import it.cnr.brevetti.util.UtilityFunctions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.struts.action.ActionForm;

public class FatturaQueryForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private int mode = 0;
	private int nextab = 1;
	private int fromList = 0;
	private Integer id;
	private String protocollo;
	private Integer studioBrevettualeId;
	private Date dataFattura1;
	private Date dataFattura2;
	private String numFattura;
	private Date dataImpegno1;
	private Date dataImpegno2;
	private String mandatoProtocollo;
	private String impegnoObbligazione;
	private Integer nsrif;
	private String idSigla;
	
	
	public String get_dataFattura1() {
		if(dataFattura1!=null)	return UtilityFunctions.itForm.format(dataFattura1);
		else return "";
	}
	public void set_dataFattura1(String fattura) throws ParseException {
		if(fattura!=null && fattura.length()>0)	dataFattura1 = UtilityFunctions.itForm.parse(fattura);
	}
	public String get_dataFattura2() {
		if(dataFattura2!=null)	return UtilityFunctions.itForm.format(dataFattura2);
		else return "";
	}
	public void set_dataFattura2(String fattura) throws ParseException {
		if(fattura!=null && fattura.length()>0)	dataFattura2 = UtilityFunctions.itForm.parse(fattura);
	}
	public String get_dataImpegno1() {
		if(dataImpegno1!=null)	return UtilityFunctions.itForm.format(dataImpegno1);
		else return "";
	}
	public void set_dataImpegno1(String Impegno) throws ParseException {
		if(Impegno!=null && Impegno.length()>0)	dataImpegno1 = UtilityFunctions.itForm.parse(Impegno);
	}
	public String get_dataImpegno2() {
		if(dataImpegno2!=null)	return UtilityFunctions.itForm.format(dataImpegno2);
		else return "";
	}
	public void set_dataImpegno2(String Impegno) throws ParseException {
		if(Impegno!=null && Impegno.length()>0)	dataImpegno2 = UtilityFunctions.itForm.parse(Impegno);
	}
	public Date getDataFattura1() {
		return dataFattura1;
	}
	public void setDataFattura1(Date dataFattura1) {
		this.dataFattura1 = dataFattura1;
	}
	public Date getDataFattura2() {
		return dataFattura2;
	}
	public void setDataFattura2(Date dataFattura2) {
		this.dataFattura2 = dataFattura2;
	}
	public Date getDataImpegno1() {
		return dataImpegno1;
	}
	public void setDataImpegno1(Date dataImpegno1) {
		this.dataImpegno1 = dataImpegno1;
	}
	public Date getDataImpegno2() {
		return dataImpegno2;
	}
	public void setDataImpegno2(Date dataImpegno2) {
		this.dataImpegno2 = dataImpegno2;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImpegnoObbligazione() {
		return impegnoObbligazione;
	}
	public void setImpegnoObbligazione(String impegnoObbligazione) {
		this.impegnoObbligazione = impegnoObbligazione;
	}
	public String getMandatoProtocollo() {
		return mandatoProtocollo;
	}
	public void setMandatoProtocollo(String mandatoProtocollo) {
		this.mandatoProtocollo = mandatoProtocollo;
	}
	public String getProtocollo() {
		return protocollo;
	}
	public void setProtocollo(String protocollo) {
		this.protocollo = protocollo;
	}
	public Integer getStudioBrevettualeId() {
		return studioBrevettualeId;
	}
	public void setStudioBrevettualeId(Integer studioBrevettualeId) {
		this.studioBrevettualeId = studioBrevettualeId;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public int getNextab() {
		return nextab;
	}
	public void setNextab(int nextab) {
		this.nextab = nextab;
	}
	public String getNumFattura() {
		return numFattura;
	}
	public void setNumFattura(String numFattura) {
		this.numFattura = numFattura;
	}
	public Integer getNsrif() {
		return nsrif;
	}
	public void setNsrif(Integer nsrif) {
		this.nsrif = nsrif;
	}
	public int getFromList() {
		return fromList;
	}
	public void setFromList(int fromList) {
		this.fromList = fromList;
	}
	public String getIdSigla() {
		return idSigla;
	}
	public void setIdSigla(String idSigla) {
		this.idSigla = idSigla;
	}
	
}
