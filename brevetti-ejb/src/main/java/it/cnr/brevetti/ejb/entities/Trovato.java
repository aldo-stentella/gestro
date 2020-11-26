package it.cnr.brevetti.ejb.entities;

import java.util.ArrayList;
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

import it.cnr.brevetti.util.Utile;

/**
 * @version 1.1 [17-Dec-07] aggiunta sequenza
 * @version 1.2 [20-Dec-07] aggiunto campo inventoreIndex
 * @version 1.3 [03-Mar-08] modifica struttura deposito
 * @version 1.4 [03-Apr-08] gestione estensione
 * @version 1.5 [22-May-08] gestione dipartimento
 * @version 1.6 [11-Feb-14] gestione depest
 * @version 1.7 [19-Sep-14] gestione auditing
 * @version 1.8 [19-Mar-15] gestione documenti
 * @version 1.9 [24-Nov-17] gestione verbali
 */

@Entity @Table(name="TROVATI")
public class Trovato extends AuditTrail {
	private static final long serialVersionUID = 1L;
	public static final String SEQ = "TROVATI_SEQ";
	
	@SequenceGenerator(name=Trovato.SEQ, sequenceName=Trovato.SEQ, allocationSize=0)
	@Id @GeneratedValue(generator=Trovato.SEQ, strategy=GenerationType.SEQUENCE)	
	private Integer nsrif;
	private Integer respinto;
	@Column(name="NOTA_RESPINTO")	
	private String notaRespinto;
	@Column(name="STUDI_BREVETTUALI_ID")
	private Integer studiBrevettualiId;
	@Column(name="DATA_DOM_RICH_DEPOSITO")
	private Date dataDomRichDeposito;
	@Column(name="TIPI_TROVATO_ID")
	private Integer tipiTrovatoId;
	@Column(name="NUM_TROVATO_STUD_BREVETT")
	private String  numTrovatoStudBrevett;
	private String  titolo;
	private String  descrizione;
	private String  usi;
	private String  vantaggi;
	@Column(name="PAROLA_CHIAVE")
	private String  parolaChiave;
	@Column(name="TITOLO_INGLESE")
	private String  titoloInglese;
	@Column(name="DESCRIZIONE_INGLESE")
	private String  descrizioneInglese;
	@Column(name="USI_INGLESE")
	private String  usiInglese;
	@Column(name="VANTAGGI_INGLESE")
	private String  vantaggiInglese;
	@Column(name="PAROLA_CHIAVE_INGLESE")
	private String  parolaChiaveInglese;
	@Column(name="STATO_PRATICA_ID")
	private Integer statoPraticaId;
	private String  note;
	@Column(name="DATA_CDP")
	private Date dataCdp;
	@Column(name="CESSIONE_DIRITTI")
	private Integer cessioneDiritti;
	@Column(name="DIPARTIMENTI_ID")
	private Integer dipartimentiId;
	@Column(name="UTENTI_ID")
	private String utentiId;
	private Integer provvisorio;
	private Integer pubblicato;

	// ===================================================================
	
	public Integer getCessioneDiritti() {
		return cessioneDiritti;
	}
	public void setCessioneDiritti(Integer cessioneDiritti) {
		this.cessioneDiritti = cessioneDiritti;
	}
	public Date getDataCdp() {
		return dataCdp;
	}
	public void setDataCdp(Date dataCdp) {
		this.dataCdp = dataCdp;
	}
	public Date getDataDomRichDeposito() {
		return dataDomRichDeposito;
	}
	public void setDataDomRichDeposito(Date dataDomRichDeposito) {
		this.dataDomRichDeposito = dataDomRichDeposito;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getDescrizioneInglese() {
		return descrizioneInglese;
	}
	public void setDescrizioneInglese(String descrizioneInglese) {
		this.descrizioneInglese = descrizioneInglese;
	}
	public String getNotaRespinto() {
		return notaRespinto;
	}
	public void setNotaRespinto(String notaRespinto) {
		this.notaRespinto = notaRespinto;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getNsrif() {
		return nsrif;
	}
	public void setNsrif(Integer nsrif) {
		this.nsrif = nsrif;
	}
	public String getNumTrovatoStudBrevett() {
		return numTrovatoStudBrevett;
	}
	public void setNumTrovatoStudBrevett(String numTrovatoStudBrevett) {
		this.numTrovatoStudBrevett = numTrovatoStudBrevett;
	}
	public String getParolaChiave() {
		return parolaChiave;
	}
	public void setParolaChiave(String parolaChiave) {
		this.parolaChiave = parolaChiave;
	}
	public String getParolaChiaveInglese() {
		return parolaChiaveInglese;
	}
	public void setParolaChiaveInglese(String parolaChiaveInglese) {
		this.parolaChiaveInglese = parolaChiaveInglese;
	}
	public Integer getRespinto() {
		return respinto;
	}
	public void setRespinto(Integer respinto) {
		this.respinto = respinto;
	}
	public Integer getStatoPraticaId() {
		return statoPraticaId;
	}
	public void setStatoPraticaId(Integer statoPraticaId) {
		this.statoPraticaId = statoPraticaId;
	}
	public Integer getStudiBrevettualiId() {
		return studiBrevettualiId;
	}
	public void setStudiBrevettualiId(Integer studiBrevettualiId) {
		this.studiBrevettualiId = studiBrevettualiId;
	}
	public Integer getTipiTrovatoId() {
		return tipiTrovatoId;
	}
	public void setTipiTrovatoId(Integer tipiTrovatoId) {
		this.tipiTrovatoId = tipiTrovatoId;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getTitoloInglese() {
		return titoloInglese;
	}
	public void setTitoloInglese(String titoloInglese) {
		this.titoloInglese = titoloInglese;
	}
	public String getUsi() {
		return usi;
	}
	public void setUsi(String usi) {
		this.usi = usi;
	}
	public String getUsiInglese() {
		return usiInglese;
	}
	public void setUsiInglese(String usiInglese) {
		this.usiInglese = usiInglese;
	}
	public String getVantaggi() {
		return vantaggi;
	}
	public void setVantaggi(String vantaggi) {
		this.vantaggi = vantaggi;
	}
	public String getVantaggiInglese() {
		return vantaggiInglese;
	}
	public void setVantaggiInglese(String vantaggiInglese) {
		this.vantaggiInglese = vantaggiInglese;
	}
	public void setDipartimentiId(Integer dipartimentiId) {
		this.dipartimentiId = dipartimentiId;
	}
	public Integer getDipartimentiId() {
		return dipartimentiId;
	}
	public String getUtentiId() {
		return utentiId;
	}

	public void setUtentiId(String utentiId) {
		this.utentiId = utentiId;
	}	
	public Integer getPubblicato() {
		return pubblicato;
	}
	public void setPubblicato(Integer pubblicato) {
		this.pubblicato = pubblicato;
	}
	public Integer getProvvisorio() {
		return provvisorio;
	}
	public void setProvvisorio(Integer provvisorio) {
		this.provvisorio = provvisorio;
	}	
	
	// ============================================================
	//  tipi transient
	// ============================================================
	
	@Transient private List<Istituto> istituti;
	@Transient private List<Inventore> inventori;
	@Transient private List<Titolarita> titolarita;
	@Transient private List<ClassificazioneInternDep> classificazioniInternazionali;
	@Transient private StudioBrevettuale studioBrevettuale;
	@Transient private Integer inventoreIndex;
	@Transient private Dipartimento dipartimento;
	@Transient private Dipartimento dipartimentoTematico;
	@Transient private Utente utente;
	@Transient private List<Valorizzazione> valorizzazioni;
	
	public StudioBrevettuale getStudioBrevettuale() {
		return studioBrevettuale;
	}
	public void setStudioBrevettuale(StudioBrevettuale studioBrevettuale) {
		this.studioBrevettuale = studioBrevettuale;
	}
	public List<ClassificazioneInternDep> getClassificazioniInternazionali() {
		return classificazioniInternazionali;
	}
	public void setClassificazioniInternazionali(
			List<ClassificazioneInternDep> classificazioniInternazionali) {
		this.classificazioniInternazionali = classificazioniInternazionali;
	}
	public List<Inventore> getInventori() {
		return inventori;
	}
	public void setInventori(List<Inventore> inventori) {
		this.inventori = inventori;
	}
	public List<Istituto> getIstituti() {
		return istituti;
	}
	public void setIstituti(List<Istituto> istituti) {
		this.istituti = istituti;
	}
	public List<Titolarita> getTitolarita() {
		return titolarita;
	}
	public void setTitolarita(List<Titolarita> titolarita) {
		this.titolarita = titolarita;
	}
	/** ritorna la posizione dell'inventore di riferimento nella lista inventori */
	public Integer getInventoreIndex() {
		return inventoreIndex;
	}
	/** setta la posizione dell'inventore di riferimento nella lista inventori */
	public void setInventoreIndex(Integer inventoreIndex) {
		this.inventoreIndex = inventoreIndex;
	}
	public Dipartimento getDipartimento() {
		return dipartimento;
	}
	public void setDipartimento(Dipartimento dipartimento) {
		this.dipartimento = dipartimento;
	}
	public void setDipartimentoTematico(Dipartimento dipartimento) {
		this.dipartimentoTematico = dipartimento;
	}
	public Dipartimento getDipartimentoTematico() {
		return dipartimentoTematico;
	}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}	
	public List<Valorizzazione> getValorizzazioni() {
		return valorizzazioni;
	}
	public void setValorizzazioni(List<Valorizzazione> valorizzazioni) {
		this.valorizzazioni = valorizzazioni;
	}
	// metodi di gestione dell'uguaglianza tra trovati
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((nsrif == null) ? 0 : nsrif.hashCode());
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
		final Trovato other = (Trovato) obj;
		if (nsrif == null) {
			if (other.nsrif != null)
				return false;
		} else if (!nsrif.equals(other.nsrif))
			return false;
		return true;
	}
	
	//version 1.6 [11-Feb-14] gestione depest
	
	@Transient private List<DepEst> depEst;
	public List<DepEst> getDepEst() {
		return depEst;
	}
	public void setDepEst(List<DepEst> depest) {
		this.depEst = depest;
	}
	public List<DepEst> getList(Integer tipo) {
		if (Utile.isEmptyOrNull(depEst)) return null;
		List<DepEst> list = new ArrayList<DepEst>();
		for (DepEst x : depEst) {
			if (tipo != null && tipo.equals(x.getTipoId()))
				list.add(x);					
		}
		return Utile.isEmptyOrNull(list) ? null : list;
	}
	@Override
	public String toString() {
		return "Trovato [nsrif=" + nsrif + "]";
	}

	// version 1.8 [19-Mar-15] gestione documenti
	@Transient private List<DocumentoInfo> documenti;
	public List<DocumentoInfo> getDocumenti() {
		return documenti;
	}
	public void setDocumenti(List<DocumentoInfo> documenti) {
		this.documenti = documenti;
	}
	// version 1.9 [24-Nov-17] gestione verbali
	@Transient String azione;
	@Transient Integer rifiuto;	
	@Transient private List<Verbale> verbali;
	public String getAzione() {
		return azione;
	}
	public void setAzione(String azione) {
		this.azione = azione;
	}
	public Integer getRifiuto() {
		return rifiuto;
	}
	public void setRifiuto(Integer rifiuto) {
		this.rifiuto = rifiuto;
	}
	public List<Verbale> getVerbali() {
		return verbali;
	}
	public void setVerbali(List<Verbale> verbali) {
		this.verbali = verbali;
	}	
}
