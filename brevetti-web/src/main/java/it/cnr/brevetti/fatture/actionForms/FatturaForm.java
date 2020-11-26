package it.cnr.brevetti.fatture.actionForms;

import it.cnr.brevetti.util.UtilityFunctions;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.struts.validator.ValidatorForm;

public class FatturaForm extends ValidatorForm {
	
	private static final long serialVersionUID = 1L;
	
	SimpleDateFormat itForm = new SimpleDateFormat(UtilityFunctions.DATE_FORMAT, Locale.ITALIAN);
	private Integer id;
	private String protocollo;
	private Integer studioBrevettualeId;
	private String studioDescrizione; 
	private Date dataFattura;
	private String numFattura;
	private BigDecimal importo = BigDecimal.ZERO;
	private Date dataImpegno;
	private String mandatoProtocollo;
	private String impegnoObbligazione;
	private String note;
	private Integer updDet;
	
	private boolean readOnly;
	
	public String get_dataFattura() {
		if(dataFattura!=null)	return itForm.format(dataFattura);
		else	return "";
	}
	public void set_dataFattura(String fattura) {
		try {
			dataFattura = itForm.parse(fattura);
		} catch (ParseException e) {
			//TODO discriminare se si tratta di "" o di un errore di formato (e lanciare un errore di validation)
			dataFattura = null;
		}
	}
	public String get_dataImpegno() {
		if(dataImpegno!=null)	return itForm.format(dataImpegno);
		else	return "";
	}
	public void set_dataImpegno(String impegno) {
		try {
			dataImpegno = itForm.parse(impegno);
		} catch (ParseException e) {
			//TODO discriminare se si tratta di "" o di un errore di formato (e lanciare un errore di validation)
			dataImpegno = null;
		}
	}
	public Date getDataFattura() {
		return dataFattura;
	}
	public void setDataFattura(Date dataFattura) {
		this.dataFattura = dataFattura;
	}
	public Date getDataImpegno() {
		return dataImpegno;
	}
	public void setDataImpegno(Date dataImpegno) {
		this.dataImpegno = dataImpegno;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
	public BigDecimal getImporto() {
		return importo;
	}
	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}
	public String getNumFattura() {
		return numFattura;
	}
	public void setNumFattura(String numFattura) {
		this.numFattura = numFattura;
	}
	public String getStudioDescrizione() {
		return studioDescrizione;
	}
	public void setStudioDescrizione(String studioDescrizione) {
		this.studioDescrizione = studioDescrizione;
	}
	public boolean isReadOnly() {
		return readOnly;
	}
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	public Integer getUpdDet() {
		return updDet;
	}
	public void setUpdDet(Integer updDet) {
		this.updDet = updDet;
	}

}
