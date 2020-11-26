package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;


/**
 * The persistent class for the DEP_EST database table.
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [11-Feb-14]
 * @version 1.1 [24-Mar-14] aggiunto studioBrevettale
 * @version 1.2 [03-Apr-14] aggiunti stati e tipiPctRegionalPatent
 * @version 1.3 [06-Mag-15] aggiunto tipiDataUfficialeId
 * 
 */
@Entity
@Table(name="DEP_EST")
public class DepEst implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String SEQ = "DEP_EST_SEQ";	
	
	public static final int DEPOSITO = 1;
	public static final int PCT = 2;
	public static final int EPC = 3;

	@Id @Column(name="DEP_EST_ID")
	@SequenceGenerator(name=DepEst.SEQ, sequenceName=DepEst.SEQ, allocationSize=0)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator=DepEst.SEQ)	
	private Integer id;

	@Column(name="DATA_ABBANDONO")
	private Date dataAbbandono;
	@Column(name="DATA_DEPOSITO")
	private Date dataDeposito;
	@Column(name="DATA_PUBBLICAZIONE")
	private Date dataPubblicazione;
	@Column(name="DATA_RILASCIO_DEP")
	private Date dataRilascioDep;
	@Column(name="IDIOMI_ID")
	private Integer idiomiId;
	@Column(name="NOTA_ABBANDONO")
	private String notaAbbandono;
	private String note;
	private Integer nsrif;
	@Column(name="NUM_PUBBLICAZIONE")
	private String numPubblicazione;
	@Column(name="NUM_RILASCIO_DEP")
	private String numRilascioDep;
	@Column(name="NUMERO_DEPOSITO")
	private String numeroDeposito;
	private Integer primo;
	@Column(name="STATO_ID")
	private Integer statoId;
	@Column(name="STUDIO_BREVETTUALE_ID")
	private Integer studioBrevettualeId;
	@Column(name="TIPO_ID")
	private Integer tipoId;
	@Column(name="TIPI_DATA_UFFICIALE_ID")
	private Integer tipiDataUfficialeId;
	@Column(name="TIPI_ESTINZIONE_ID")	
	private Integer tipiEstinzioneId;

	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDataAbbandono() {
		return this.dataAbbandono;
	}
	public void setDataAbbandono(Date dataAbbandono) {
		this.dataAbbandono = dataAbbandono;
	}
	public Date getDataDeposito() {
		return this.dataDeposito;
	}
	public void setDataDeposito(Date dataDeposito) {
		this.dataDeposito = dataDeposito;
	}
	public Date getDataPubblicazione() {
		return this.dataPubblicazione;
	}
	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}
	public Date getDataRilascioDep() {
		return this.dataRilascioDep;
	}
	public void setDataRilascioDep(Date dataRilascioDep) {
		this.dataRilascioDep = dataRilascioDep;
	}
	public Integer getIdiomiId() {
		return this.idiomiId;
	}
	public void setIdiomiId(Integer idiomiId) {
		this.idiomiId = idiomiId;
	}
	public String getNotaAbbandono() {
		return this.notaAbbandono;
	}
	public void setNotaAbbandono(String notaAbbandono) {
		this.notaAbbandono = notaAbbandono;
	}
	public String getNote() {
		return this.note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getNsrif() {
		return this.nsrif;
	}
	public void setNsrif(Integer nsrif) {
		this.nsrif = nsrif;
	}
	public String getNumPubblicazione() {
		return this.numPubblicazione;
	}
	public void setNumPubblicazione(String numPubblicazione) {
		this.numPubblicazione = numPubblicazione;
	}
	public String getNumRilascioDep() {
		return this.numRilascioDep;
	}
	public void setNumRilascioDep(String numRilascioDep) {
		this.numRilascioDep = numRilascioDep;
	}
	public String getNumeroDeposito() {
		return this.numeroDeposito;
	}
	public void setNumeroDeposito(String numeroDeposito) {
		this.numeroDeposito = numeroDeposito;
	}
	public Integer getPrimo() {
		return this.primo;
	}
	public void setPrimo(Integer primo) {
		this.primo = primo;
	}
	public Integer getStatoId() {
		return this.statoId;
	}
	public void setStatoId(Integer statoId) {
		this.statoId = statoId;
	}
	public Integer getStudioBrevettualeId() {
		return this.studioBrevettualeId;
	}
	public void setStudioBrevettualeId(Integer studioBrevettualeId) {
		this.studioBrevettualeId = studioBrevettualeId;
	}
	public Integer getTipoId() {
		return this.tipoId;
	}
	public void setTipoId(Integer tipoId) {
		this.tipoId = tipoId;
	}
	public Integer getTipiDataUfficialeId() {
		return tipiDataUfficialeId;
	}
	public void setTipiDataUfficialeId(Integer tipiDataUfficialeId) {
		this.tipiDataUfficialeId = tipiDataUfficialeId;
	}
	public String getStatus(){
		Date oggi = new Date();
		if(tipoId==PCT){
			if(dataAbbandono!=null && dataAbbandono.before(oggi)){
				switch (tipiEstinzioneId) {
				case TipoEstinzione.ABBANDONO:
					return "Abandoned";
				case TipoEstinzione.PROSECUZIONE:
					return "Abandoned";
				case TipoEstinzione.CESSIONE_COTITOLARI_O:
				case TipoEstinzione.CESSIONE_INVENTORI_O:
				case TipoEstinzione.CESSIONE_TERZI_O:
				case TipoEstinzione.CESSIONE_COTITOLARI_G:
				case TipoEstinzione.CESSIONE_INVENTORI_G:
					return "Ceduto";
				default:
					break;
				} 
			}
			if(dataDeposito!=null)
				return "Pending";
		}
		if(tipoId==EPC){
			if(dataAbbandono!=null && dataAbbandono.before(oggi)){
				switch (tipiEstinzioneId) {
				case TipoEstinzione.ABBANDONO:
					return "Abandoned";
				case TipoEstinzione.PROSECUZIONE:
					return "Abandoned";
				case TipoEstinzione.RIGETTO:
					return "Rejected";
				case TipoEstinzione.CESSIONE_COTITOLARI_O:
				case TipoEstinzione.CESSIONE_INVENTORI_O:
				case TipoEstinzione.CESSIONE_TERZI_O:
				case TipoEstinzione.CESSIONE_COTITOLARI_G:
				case TipoEstinzione.CESSIONE_INVENTORI_G:
					return "Ceduto";
				default:
					break;
				}
			}
			if(dataRilascioDep!=null || StringUtils.isNotBlank(numRilascioDep))
				return "Granted";
			if(dataDeposito!=null)
				return "Pending";
		}
		if(tipoId==DEPOSITO){
			if((dataAbbandono!=null && dataAbbandono.before(oggi))) {
				switch (tipiEstinzioneId) {
				case TipoEstinzione.ABBANDONO:
					return "Abandoned";
				case TipoEstinzione.RIGETTO:
					return "Rejected";
				case TipoEstinzione.SCADENZA:
					return "Expired";
				case TipoEstinzione.CESSIONE_COTITOLARI_O:
				case TipoEstinzione.CESSIONE_INVENTORI_O:
				case TipoEstinzione.CESSIONE_TERZI_O:
				case TipoEstinzione.CESSIONE_COTITOLARI_G:
				case TipoEstinzione.CESSIONE_INVENTORI_G:
					return "Ceduto";
				default:
					break;
				}
			}
			if(dataRilascioDep!=null || StringUtils.isNotBlank(numRilascioDep))
				return "Granted";
			if(dataDeposito!=null)
				return "Pending";
		}
		return "Undefined";
	}
	
	public Integer getTipiEstinzioneId() {
		return tipiEstinzioneId;
	}
	public void setTipiEstinzioneId(Integer tipiEstinzioneId) {
		this.tipiEstinzioneId = tipiEstinzioneId;
	}

	@Transient private StudioBrevettuale studioBrevettuale;
	@Transient private List<Stato> stati;
	@Transient private TipoEstinzione tipoEstinzione;
	public List<Stato> getStati() {
		return stati;
	}
	public void setStati(List<Stato> stati) {
		this.stati = stati;
	}
	public List<TipoPctRegionalPatent> getTipiPctRegionalPatent() {
		return tipiPctRegionalPatent;
	}
	public void setTipiPctRegionalPatent(
			List<TipoPctRegionalPatent> tipiPctRegionalPatent) {
		this.tipiPctRegionalPatent = tipiPctRegionalPatent;
	}
	@Transient List<TipoPctRegionalPatent> tipiPctRegionalPatent;

	public StudioBrevettuale getStudioBrevettuale() {
		return studioBrevettuale;
	}
	public void setStudioBrevettuale(StudioBrevettuale studioBrevettuale) {
		this.studioBrevettuale = studioBrevettuale;
	}
	@Transient List<StoricoTitolarita> storicoTitolarita;
	public List<StoricoTitolarita> getStoricoTitolarita() {
		return storicoTitolarita;
	}
	public void setStoricoTitolarita(List<StoricoTitolarita> storicoTitolarita) {
		this.storicoTitolarita = storicoTitolarita;
	}
	@Transient Stato stato;	
	public Stato getStato() {
		return stato;
	}
	public void setStato(Stato stato) {
		this.stato = stato;
	}
	public TipoEstinzione getTipoEstinzione() {
		return tipoEstinzione;
	}
	public void setTipoEstinzione(TipoEstinzione tipoEstinzione) {
		this.tipoEstinzione = tipoEstinzione;
	}

	@Override
	public String toString() {
		return "DepEst [id=" + id + ", nsrif=" + nsrif + ", tipoId=" + tipoId
				+ "]";
	}		
	//==================================================================
	// metodi per la definizione dell'uguaglianza
	//==================================================================	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DepEst other = (DepEst) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}