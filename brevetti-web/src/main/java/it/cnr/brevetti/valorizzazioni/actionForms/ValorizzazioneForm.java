package it.cnr.brevetti.valorizzazioni.actionForms;

import java.text.ParseException;
import java.util.Date;

import it.cnr.brevetti.util.UtilityFunctions;
import it.cnr.brevetti.validators.AbstractValidatorForm;

public class ValorizzazioneForm extends AbstractValidatorForm {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String altro;
	private Integer aziendaId;
	private String aziendaNome;
	private String aziendaCitta;
	private String aziendaRegione;
	private String aziendaTipo;
	private String dataFine;
	private String dataInizio;
	private String nomeProgetto;
	private String note;
	private String referente;
	private Integer tipoValorizzazioneId;
	private String tipoValorizzazioneNome;
	private String nCluster;
	private String xCluster;
	private String updCluster;
	private String utcr;
	private String utva;
	private String dataCreazione;
	private String dataVariazione;
	private Integer rev;
	private Integer updDoc;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAltro() {
		return altro;
	}
	public void setAltro(String altro) {
		this.altro = altro;
	}
	public Integer getAziendaId() {
		return aziendaId;
	}
	public void setAziendaId(Integer aziendaId) {
		this.aziendaId = aziendaId;
	}
	public String getAziendaNome() {
		return aziendaNome;
	}
	public void setAziendaNome(String aziendaNome) {
		this.aziendaNome = aziendaNome;
	}
	public String getAziendaCitta() {
		return aziendaCitta;
	}
	public void setAziendaCitta(String aziendaCitta) {
		this.aziendaCitta = aziendaCitta;
	}
	public String getDataFine() {
		return dataFine;
	}
	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}
	public Date getFine() {
		try {
			return UtilityFunctions.itForm.parse(dataFine);
		} catch (ParseException e) {
			return null;
		} catch (NullPointerException e) {
			return null;
		}
	}
	public void setFine(Date fine) {
		try {
			dataFine = UtilityFunctions.itForm.format(fine);
		} catch (NullPointerException e) {
			dataFine = null;
		}
	}
	public String getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}
	public Date getInizio() {
		try {
			return UtilityFunctions.itForm.parse(dataInizio);
		} catch (ParseException e) {
			return null;
		} catch (NullPointerException e) {
			return null;
		}
	}
	public void setInizio(Date inizio) {
		try {
			dataInizio = UtilityFunctions.itForm.format(inizio);
		} catch (NullPointerException e) {
			dataInizio = null;
		}
	}
	public String getNomeProgetto() {
		return nomeProgetto;
	}
	public void setNomeProgetto(String nomeProgetto) {
		this.nomeProgetto = nomeProgetto;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getReferente() {
		return referente;
	}
	public void setReferente(String referente) {
		this.referente = referente;
	}
	public Integer getTipoValorizzazioneId() {
		return tipoValorizzazioneId;
	}
	public void setTipoValorizzazioneId(Integer tipoValorizzazioneId) {
		this.tipoValorizzazioneId = tipoValorizzazioneId;
	}
	public String getTipoValorizzazioneNome() {
		return tipoValorizzazioneNome;
	}
	public void setTipoValorizzazioneNome(String tipoValorizzazioneNome) {
		this.tipoValorizzazioneNome = tipoValorizzazioneNome;
	}
	public String getAziendaRegione() {
		return aziendaRegione;
	}
	public void setAziendaRegione(String aziendaRegione) {
		this.aziendaRegione = aziendaRegione;
	}
	public String getnCluster() {
		return nCluster;
	}
	public void setnCluster(String nCluster) {
		this.nCluster = nCluster;
	}
	public String getUpdCluster() {
		return updCluster;
	}
	public void setUpdCluster(String updCluster) {
		this.updCluster = updCluster;
	}
	public String getAziendaTipo() {
		return aziendaTipo;
	}
	public void setAziendaTipo(String aziendaTipo) {
		this.aziendaTipo = aziendaTipo;
	}
	public String getUtcr() {
		return utcr;
	}

	public void setUtcr(String utcr) {
		this.utcr = utcr;
	}

	public String getUtva() {
		return utva;
	}

	public void setUtva(String utva) {
		this.utva = utva;
	}

	public Date getDacr() {
		try {
			return UtilityFunctions.itForm.parse(dataCreazione);
		} catch (ParseException e) {
			return null;
		} catch (NullPointerException e) {
			return null;
		}
	}

	public void setDacr(Date dacr) {
		try {
			dataCreazione = UtilityFunctions.itForm.format(dacr);
		} catch (NullPointerException e) {
			dataCreazione = null;
		}
	}
	
	public String getDataCreazione() {
		return dataCreazione;
	}
	
	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Date getDuva() {
		try {
			return UtilityFunctions.itForm.parse(dataVariazione);
		} catch (ParseException e) {
			return null;
		} catch (NullPointerException e) {
			return null;
		}
	}

	public void setDuva(Date duva) {
		try {
			dataVariazione = UtilityFunctions.itForm.format(duva);
		} catch (NullPointerException e) {
			dataVariazione = null;
		}
	}
	
	public String getDataVariazione() {
		return dataVariazione;
	}
	
	public void setDataVariazione(String dataVariazione) {
		this.dataVariazione = dataVariazione;
	}

	public Integer getRev() {
		return rev;
	}

	public void setRev(Integer rev) {
		this.rev = rev;
	}
	public Integer getUpdDoc() {
		return updDoc;
	}
	public void setUpdDoc(Integer updDoc) {
		this.updDoc = updDoc;
	}
	public String getxCluster() {
		return xCluster;
	}
	public void setxCluster(String xCluster) {
		this.xCluster = xCluster;
	}
	
}
