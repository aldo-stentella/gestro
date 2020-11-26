package it.cnr.brevetti.ejb.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;

import it.cnr.brevetti.domain.DocumentoQuery;
import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.domain.TrovatoQuery;
import it.cnr.brevetti.ejb.entities.AbstractTitolarita;
import it.cnr.brevetti.ejb.entities.Azienda;
import it.cnr.brevetti.ejb.entities.ClassificazioneInternDep;
import it.cnr.brevetti.ejb.entities.DepEst;
import it.cnr.brevetti.ejb.entities.DepEstStato;
import it.cnr.brevetti.ejb.entities.DepEstStatoKey;
import it.cnr.brevetti.ejb.entities.Dipartimento;
import it.cnr.brevetti.ejb.entities.Documento;
import it.cnr.brevetti.ejb.entities.DocumentoInfo;
import it.cnr.brevetti.ejb.entities.EnteEsterno;
import it.cnr.brevetti.ejb.entities.Inventore;
import it.cnr.brevetti.ejb.entities.Istituto;
import it.cnr.brevetti.ejb.entities.IstitutoTrovato;
import it.cnr.brevetti.ejb.entities.IstitutoTrovatoKey;
import it.cnr.brevetti.ejb.entities.PctTipoRegionalPatent;
import it.cnr.brevetti.ejb.entities.PctTipoRegionalPatentKey;
import it.cnr.brevetti.ejb.entities.Stato;
import it.cnr.brevetti.ejb.entities.StoricoTitolarita;
import it.cnr.brevetti.ejb.entities.StudioBrevettuale;
import it.cnr.brevetti.ejb.entities.TipoDocumento;
import it.cnr.brevetti.ejb.entities.TipoEstinzione;
import it.cnr.brevetti.ejb.entities.TipoPctRegionalPatent;
import it.cnr.brevetti.ejb.entities.TipoTitolareEnum;
import it.cnr.brevetti.ejb.entities.TipoTrovato;
import it.cnr.brevetti.ejb.entities.TipoValorizzazione;
import it.cnr.brevetti.ejb.entities.Titolarita;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.ejb.entities.TrovatoDipartimento;
import it.cnr.brevetti.ejb.entities.TrovatoDipartimentoKey;
import it.cnr.brevetti.ejb.entities.TrovatoDocumento;
import it.cnr.brevetti.ejb.entities.TrovatoDocumentoKey;
import it.cnr.brevetti.ejb.entities.TrovatoInventore;
import it.cnr.brevetti.ejb.entities.TrovatoInventoreKey;
import it.cnr.brevetti.ejb.entities.TrovatoValorizzazione;
import it.cnr.brevetti.ejb.entities.TrovatoValorizzazioneKey;
import it.cnr.brevetti.ejb.entities.TrovatoVerbale;
import it.cnr.brevetti.ejb.entities.TrovatoVerbaleKey;
import it.cnr.brevetti.ejb.entities.Utente;
import it.cnr.brevetti.ejb.entities.Valorizzazione;
import it.cnr.brevetti.ejb.entities.Verbale;
import it.cnr.brevetti.ejb.entities.XQuery;
import it.cnr.brevetti.ejb.services.CommonAbstract;
import it.cnr.brevetti.ejb.services.DocumentoService;
import it.cnr.brevetti.util.Utile;

/**
 * Implementazione servizio FACADE di gestione del Trovato
 * @author Aurelio D'Amico
 * @version 1.0 [19-Nov-07]
 * @version 1.1 [03-Jan-08] impostazione del campo inventoreIndex (loadInventoreIndex)
 * @version 1.2 [10-Jan-08] implementazione metodo getIstituto
 * @version 1.3 [06-Feb-08] implementazione aggiornamento del trovato
 * @version 1.4 [17-Feb-08] implementazione ricerca avanzata
 * @version 1.5 [04-Mar-08] implementazione metodo getIdiomi
 * @version 1.6 [26-Mar-08] gestione depositi (inserimento ed aggiornamento)
 * @version 1.7 [03-Apr-08] gestione estensione
 * @version 1.8 [08-Apr-08] implementazione di getStudiBrevettualiSortedByDenominazione
 * @version 1.9 [18-Apr-08] implementazione di getStato(Integer)
 * @version 1.10 [22-Apr-08] implementazione di getTipiRegionalPatent()
 * @version 1.11 [05-May-08] implementazione di getStudioBrevettuale(Integer)
 * @version 1.12 [22-May-08] implementazione dipartimento di afferenza
 * @version 1.13 [02-Jun-08] implementazione metodo salvaInvetore(Inventore)
 * @version 1.14 [12-Feb-14] gestione tabella DepEst
 * @version 1.15 [12-Feb-14] gestione tabella StoricoTitolarita
 * @version 1.16 [03-Sep-14] aggiorna/salva associazione stati in DepEst
 * @version 1.17 [19-Nov-14] popolamento Utente & Stato
 * @version 1.18 [25-Nov-14] implementazione metodi di lettura utenti
 * @version 1.19 [12-Jan-15] popolamento Valorizzazioni
 * @version 1.20 [11-Feb-15] popolamento Valorizzazioni
 * @version 1.21 [17-Mar-15] gestione documenti
 * @version 1.22 [30-Jun-15] salvataggio lista tipiPctRegionalPatent 
 * @version 1.23 [07-Jul-15] associazione molti a molti trovati documenti 
 * @version 1.24 [12-Nov-15] gestione TipoEstinzione
 * @version 1.25 [10-Dec-15] gestione DocumentoInfo.dataDocumento
 * @version 1.26 [15-Dec-15] utilizzo di BasicAbstract
 * @version 1.27 [24-Nov-17] gestione Verbali
 */
@Stateless
@SuppressWarnings({"unchecked", "rawtypes"})
public class GestioneTrovatoServiceBean extends CommonAbstract implements GestioneTrovatoService {

	private @EJB DocumentoService docService;
	
	public List getTipiTrovato() {
		return findAll(TipoTrovato.class);
	}
	public TipoTrovato getTipoTrovato(Integer id) {
		return (TipoTrovato) find(TipoTrovato.class, id);
	}
	
	// ===============================================================
	//  LETTURA DEL TROVATO (e di tutti gli oggetti contenuti)
	// ===============================================================	

	public Trovato getTrovato(Integer id, Integer dipartimentoId) {
		Trovato trovato = (Trovato) find(Trovato.class, id);
		if (trovato == null) return null;
		Integer nsrif = trovato.getNsrif();
		Integer key = trovato.getStudiBrevettualiId();
		if (key != null) {
			StudioBrevettuale studioBrevettuale = getStudioBrevettuale(key);
			trovato.setStudioBrevettuale(studioBrevettuale);
		}
		trovato.setIstituti(getIstituti(nsrif));
		trovato.setInventori(getInventori(nsrif));
		trovato.setClassificazioniInternazionali(getClassificazioniInternDep(nsrif));
		trovato.setTitolarita(getTitolarita(nsrif));

		if (trovato.getClassificazioniInternazionali() != null)
			loadClassificazioni(trovato.getClassificazioniInternazionali());
		if (trovato.getTitolarita() != null)
			loadAbstractTitolarita(trovato.getTitolarita());
		
		trovato.setInventoreIndex(loadInventoreIndex(trovato));
		trovato.setDipartimento(loadDipartimento(id, dipartimentoId));
		if (trovato.getDipartimentiId()!=null)
			trovato.setDipartimentoTematico(getDipartimento(trovato.getDipartimentiId()));
		
		trovato.setDepEst(loadDepEst(nsrif)); //ver1.14
		
		//ver1.17
		if (Utile.isNotBlankOrNull(trovato.getUtentiId())) {
			trovato.setUtente((Utente) find(Utente.class, trovato.getUtentiId()));
		}
		
		trovato.setValorizzazioni(getValorizzazioni(nsrif)); //ver1.18
		
		trovato.setDocumenti(getDocumentiInfo(nsrif));
		
		trovato.setVerbali(getVerbali(nsrif)); //ver1.27

		return trovato;
	}
	private void loadClassificazioni(List<ClassificazioneInternDep> list) {
		Integer id = null;
		for (ClassificazioneInternDep dep : list) {
			id = dep.getClassificazioniId();
			if (id != null) {
				dep.setClassificazione(getClassificazione(id));
			}
		}
	}
	//ver1.15
	private void loadAbstractTitolarita(List list) {
		for (Object x : list) {
			AbstractTitolarita tit = (AbstractTitolarita) x;
			Integer id = tit.getFrkSoggettoId();
			if (id==null) continue;
			switch(TipoTitolareEnum.values()[tit.getTipiTitolareId()]) {
				case DIPARTIMENTO :
					tit.setDipartimento(getDipartimento(id));
					break;
				case INVENTORE :
					tit.setInventore(getInventore(id));
					break;
				case ENTE_ESTERNO :
					tit.setEnteEsterno(getEnteEsterno(id));
					break;
			default:
				break;
			}
		}
	}
	private Integer loadInventoreIndex(Trovato trovato) {
		TrovatoInventore ti = getInventoreRiferimento(trovato.getNsrif());
		if (ti != null) {
			List<Inventore> list = trovato.getInventori();
			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					Inventore item = list.get(i);
					if (item.getId().equals(ti.getKey().getInventoriId()))
						return i;
				}
			}
		}
		return null;
	}
	private TrovatoInventore getInventoreRiferimento(Integer id) {
		return (TrovatoInventore) findObjectByQuery(XQuery.FIND_INVENTORE_RIFERIMENTO, id);
	}
	private Dipartimento loadDipartimento(Integer nsrif, Integer did) {
		if (did == null) return null;
		TrovatoDipartimentoKey key = new TrovatoDipartimentoKey(nsrif, did);
		TrovatoDipartimento td = getTrovatoDipartimento(key);
		return td == null ? null : getDipartimento(did);
	}
	// ===============================================================
	//  SALVATAGGIO DEL TROVATO (e di tutti gli oggetti contenuti)
	// ===============================================================
	
	public Trovato salvaTrovato(Trovato trovato) {
		if (trovato == null) return null;
		trovato.setNsrif(null);
		// salva prima lo studio brevettuale in modo da recuperare il suo numero progressivo
		salvaStudioBrevettuale(trovato);		
		Trovato t = (Trovato) create(trovato);
		salvaInventori(t);
		salvaIstituti(t);
		salvaClassificazioniInternDep(t);
		salvaTitolarita(t);
		salvaTrovatoDipartimento(t, null);
		salvaDepEst(t); //ver1.14
		salvaDocumenti(t); //ver1.21
		return t;
	}
	// se d = null arriva da salva trovato altrimenti arriva da salva titolarità
	private void salvaTrovatoDipartimento(Trovato t, Dipartimento d) {
		Dipartimento dip = (d == null ? t.getDipartimento() : d);
		if (dip == null) return;
		if (dip.getId() == null) return;
		TrovatoDipartimentoKey key = new TrovatoDipartimentoKey();
		key.setNsrif(t.getNsrif());
		key.setDipartimentiId(dip.getId());
		if (getTrovatoDipartimento(key) == null)
			create(new TrovatoDipartimento(key));	
	}
	private TrovatoDipartimento getTrovatoDipartimento(TrovatoDipartimentoKey id) {		
		return (TrovatoDipartimento) find(TrovatoDipartimento.class, id);
	}
	private void salvaStudioBrevettuale(Trovato t) {
		if (t.getStudioBrevettuale() != null) {
			StudioBrevettuale sb = salvaStudioBrevettuale(t.getStudioBrevettuale());
			t.setStudioBrevettuale(sb);
			t.setStudiBrevettualiId(sb.getId());			
		}
	}
	private StudioBrevettuale salvaStudioBrevettuale(StudioBrevettuale sb) {
		return (StudioBrevettuale) (sb.getId() == null ? create(sb) : sb);		
	}
	private void salvaInventori(Trovato t) {
		List<Inventore> list = t.getInventori();
		if (list == null) return;
		int index = 0;
		for (Inventore inventore : list) {
			if (inventore.getId() == null) {
				Inventore i = (Inventore) create(inventore);
				inventore.setId(i.getId());
			}
			TrovatoInventoreKey key = new TrovatoInventoreKey();
			key.setNsrif(t.getNsrif());
			key.setInventoriId(inventore.getId());
			TrovatoInventore ti = new TrovatoInventore();
			ti.setKey(key);
			ti.setInventoreRiferimento(0);
			if (t.getInventoreIndex() != null)
				if (t.getInventoreIndex().intValue() == index)
					ti.setInventoreRiferimento(1);
			create(ti);
			++index;
		}
	}
	private void salvaIstituti(Trovato t) {
		List<Istituto> list = t.getIstituti();
		if (list == null) return;
		for (Istituto istituto : list) {
			IstitutoTrovatoKey key = new IstitutoTrovatoKey();			
			key.setNsrif(t.getNsrif());
			IstitutoTrovato it = new IstitutoTrovato();
			it.setKey(key);
			key.setIstitutiId(istituto.getId());
			create(it);
		}
	}
	private void salvaClassificazioniInternDep(Trovato t) {
		List<ClassificazioneInternDep> list = t.getClassificazioniInternazionali();
		if (list == null) return;
		for (ClassificazioneInternDep dep : list) {
			dep.setClassificazioneInternDepId(null);
			dep.setNsrif(t.getNsrif());
			create(dep);
		}		
	}
	private void salvaTitolarita(Trovato trovato) {
		List<Titolarita> list = trovato.getTitolarita();
		if (list == null) return;
		for (Titolarita x : list) {
			popolaAbstractTitolarita(x);
			if (x.getDipartimento()!=null)
				salvaTrovatoDipartimento(trovato, x.getDipartimento());
			x.setNsrif(trovato.getNsrif());
			x.setId(null);
			create(x);
		}
	}
	private void salvaStoricoTitolarita(DepEst depest) {
		List<StoricoTitolarita> list = depest.getStoricoTitolarita();
		if (list == null) return;
		for (StoricoTitolarita x : list) {
			popolaAbstractTitolarita(x);
			x.setDepEstId(depest.getId());
			x.setId(null);
			create(x);
		}
	}
	//ver1.15
	private void popolaAbstractTitolarita(AbstractTitolarita tit) {
		EnteEsterno est = tit.getEnteEsterno();
		Dipartimento dip = tit.getDipartimento();
		Inventore inv = tit.getInventore();
		// l'ente esterno potrebbe essere un nuovo inserimento
		// quindi il suo indice deve essere generato
		if (est != null && est.getId() == null) {
			est = (EnteEsterno) create(est);
		}			
		if (est != null) {
			tit.setFrkSoggettoId(est.getId());
			tit.setTipiTitolareId(TipoTitolareEnum.ENTE_ESTERNO.ordinal());
		} else if (dip != null) {
			tit.setFrkSoggettoId(dip.getId());
			tit.setTipiTitolareId(TipoTitolareEnum.DIPARTIMENTO.ordinal());
		} else if (inv != null) {
			tit.setFrkSoggettoId(inv.getId());
			tit.setTipiTitolareId(TipoTitolareEnum.INVENTORE.ordinal());
		}		
	}
	//ver1.14
	private void salvaDepEst(Trovato t) {
		List<DepEst> list = t.getDepEst();
		if (list == null) return;
		for (DepEst de : list) {
			DepEst x = de;
			if (x.getId() == null) {
				x.setNsrif(t.getNsrif());
				x = (DepEst) create(x);
				salvaStoricoTitolarita(x); //ver1.15
				salvaStati(x); //ver1.16
				salvaPctTipiRegionalPatent(x); //ver1.22
			}
		}
	}
	// ===============================================================
	//  AGGIORNAMENTO DEL TROVATO (e di tutti gli oggetti contenuti)
	// ===============================================================
	
	public Trovato aggiornaTrovato(Trovato trovato) {
		if (trovato == null) return null;
		if (trovato.getNsrif() == null)
			throw new EJBException("errore aggiornaTrovato: trovato.nsrif = NULL");
		salvaStudioBrevettuale(trovato);		
		aggiornaInventori(trovato);
		aggiornaIstituti(trovato);
		aggiornaClassificazioniInternDep(trovato);
		aggiornaTitolarita(trovato);
		aggiornaDepEst(trovato); //ver1.14
		aggiornaDocumenti(trovato); //ver1.21
		return (Trovato) update(trovato);
	}
	private void aggiornaInventori(Trovato t) {
		if (t.getInventori() == null) return;
		deleteAll(XQuery.DELETE_TROVATI_INVENTORI, t);
		if (!(t.getInventori().isEmpty()))
			salvaInventori(t);		
	}
	private void aggiornaIstituti(Trovato t) {
		if (t.getIstituti() == null) return;
		deleteAll(XQuery.DELETE_ISTITUTI_TROVATI, t);
		if (!(t.getIstituti().isEmpty()))
			salvaIstituti(t);		
	}
	private void aggiornaClassificazioniInternDep(Trovato t) {
		if (t.getClassificazioniInternazionali() == null) return;
		deleteAll(XQuery.DELETE_CLASSIFICAZIONI_INTERNAZIONALI, t);
		if (!(t.getClassificazioniInternazionali().isEmpty()))
			salvaClassificazioniInternDep(t);
	}
	private void aggiornaTitolarita(Trovato t) {
		if (t.getTitolarita() == null) return;
		deleteAll(XQuery.DELETE_TITOLARITA, t);
		if (!(t.getTitolarita().isEmpty()))
			salvaTitolarita(t);
	}
	//ver1.15
	private void aggiornaStoricoTitolarita(DepEst x) {
		if (x==null || x.getStoricoTitolarita()==null) return;
		executeQuery(XQuery.DELETE_STORICO_TITOLARITA, x.getId());
		if (!(x.getStoricoTitolarita().isEmpty()))
			salvaStoricoTitolarita(x);
	}
	//ver1.14
	/*
	 * paradigma: 
	 * se la lista dei depest è nulla significa non fare modifiche
	 * altrimenti elimina i depest in DB assenti nel trovato 
	 */
	private void aggiornaDepEst(Trovato t) {
		List<DepEst> list = t.getDepEst();
		if (list==null) return;
		List<DepEst> righe = loadDepEst(t.getNsrif());
		if (righe==null) righe = new ArrayList<DepEst>();
		if (Utile.isEmptyOrNull(righe) && Utile.isEmptyOrNull(list)) return;
		// rimuove le righe assenti in list
		for (DepEst x : righe) {
			if (! list.contains(x) ) {
				executeQuery(XQuery.DELETE_STORICO_TITOLARITA, x.getId());
				executeQuery(XQuery.DELETE_DEPEST_STATI, x.getId()); //ver1.16
				executeQuery(XQuery.DELETE_PCT_TIPI_REGIONAL_PATENT, x.getId()); //ver1.16
				delete(x);
			}
		}
		// crea o aggiorna i depest presenti in list
		for (DepEst de : list) {
			DepEst x = de;
			x.setNsrif(t.getNsrif());
			if (x.getId() == null) {
				x = (DepEst) create(x);
				aggiornaStoricoTitolarita(x); //ver1.15
			} else {
				x = (DepEst) update(x);
				aggiornaStoricoTitolarita(x); //ver1.15
			}			
			aggiornaStati(x); //ver1.16
			aggiornaPctTipiRegionalPatent(x); //ver1.22
		}		
	}
	//ver1.22
	private void aggiornaDocumenti(Trovato t) {
		// aggiornamento associazione by nsrif
		List<DocumentoInfo> list = t.getDocumenti();
		if (list==null) return; // nothing to do
		List<Integer> righe=findByQuery(XQuery.FIND_TROVATI_DOCUMENTI_BY_NSRIF, t.getNsrif());
		deleteAll(XQuery.DELETE_TROVATI_DOCUMENTI_BY_NSRIF, t);
		if (!list.isEmpty()) salvaDocumenti(t);
		if (Utile.isEmptyOrNull(righe)) return;
		for (Integer id : righe) {
			TrovatoDocumentoKey key = new TrovatoDocumentoKey(t.getNsrif(), id);
			TrovatoDocumento td = (TrovatoDocumento) find(TrovatoDocumento.class, key);
			if (td != null)	continue; // esiste l'associazione
			Long count = (Long) findObjectByQuery(XQuery.COUNT_TROVATI_DOCUMENTI_BY_DOCID, id);
			if (count==0) delDocumentoInfo(id); // non esistono altre associazioni
		}
	}
	//ver1.22
	private void salvaDocumenti(Trovato t) {
		List<DocumentoInfo> list = t.getDocumenti();
		if (Utile.isEmptyOrNull(list)) return;
		for (DocumentoInfo di : list) {
			DocumentoInfo x = docService.salvaDocumentoInfo(di);
			TrovatoDocumentoKey key = new TrovatoDocumentoKey();
			key.setNsrif(t.getNsrif());
			key.setDocumentoId(x.getDocumentoId());
			create(new TrovatoDocumento(key));
		}
	}	
	private void deleteAll(String q, Trovato t) {
		deleteAll(q, t.getNsrif());	
	}

	// ===============================================================
	//  RICERCA AVANZATA DEL TROVATO
	// ===============================================================

	/**
	 * Ritorna una lista di trovati che soddisfano il criterio di selezione
	 * La lista contiene solo i dati minimali atti al riconoscimento del trovato
	 * comunque nel caso che il risultato sia singolo (size=1) il trovato
	 * contenuto viene restituito per convenienza nella sua forma completa
	 */
	public List getTrovati(Parametri p) {
		if (p == null || p.isEmpty()) return null;		
		String sql = new TrovatoQuery().getQuery(p);
		List list = find(sql);
		if (list != null && list.size() == 1) {
			Integer did = (Integer) p.get(TrovatoQuery.AFFERENZA_ID);
			list.set(0, getTrovato(((Trovato) list.get(0)).getNsrif(),did));
		}
		return list;
	}
	public Inventore salvaInventore(Inventore inventore) {
		if (inventore.getId() == null)
			return (Inventore) create(inventore);
		else
			return (Inventore) update(inventore);
	}
	public Object countTrovatiByClassificazione(String codice) {
		//COUNT_TROVATI_BY_CLASSIFICAZIONE sostituita poichè poco significativa per le statistiche
		return findObjectByQuery(XQuery.COUNT_TROVATI_BY_CLASSIFICAZIONE_SUBTREE, codice);
	}
	public List countTrovatiByDipartimento() {
		return findByQuery(XQuery.COUNT_TROVATI_BY_DIPARTIMENTO);
	}

	public List getTrovatiByUo(String uo) {
		return getList(XQuery.FIND_TROVATI_BY_UO, uo);
	}
	public List getIstituti(Integer nsrif) {
		return getList(XQuery.FIND_ISTITUTI_BY_NSRIF, nsrif);
	}
	public List getInventori(Integer nsrif) {
		return getList(XQuery.FIND_INVENTORI_BY_NSRIF, nsrif);
	}
	public List getClassificazioniInternDep(Integer nsrif) {
		return getList(XQuery.FIND_CLASSIFICAZIONI_INTERN_DEP_BY_NSRIF, nsrif);
	}
	public List getTitolarita(Integer nsrif) {
		return getList(XQuery.FIND_TITOLARITA_BY_NSRIF, nsrif);
	}
	//ver1.18
	public List<Valorizzazione> getValorizzazioni(Integer nsrif) {
		return getList(XQuery.FIND_VALORIZZAZIONI_BY_NSRIF, nsrif);
	}
	//ver1.21
	private List<DocumentoInfo> getDocumentiInfo(Integer nsrif) {
		return getDocumentiInfo(Parametri.getNewParametri(DocumentoQuery.NSRIF, nsrif));
	}	

	//ver1.14
	/** ritorna depositi ed estensioni associati al trovato specificato */	
	public List<DepEst> loadDepEst(Integer nsrif) {
		List<DepEst> list = getList(XQuery.FIND_DEP_EST_BY_NSRIF, nsrif);
		if (Utile.isEmptyOrNull(list)) return null;
	    for (DepEst x : list) {
	    	// caricamento dello studio brevettuale
	    	Integer id = x.getStudioBrevettualeId();
			if (id!=null)
				x.setStudioBrevettuale((StudioBrevettuale) find(StudioBrevettuale.class, id));
			// cariramento dei tipiPctRegionalPatent
			if (DepEst.PCT == x.getTipoId()) {
				x.setTipiPctRegionalPatent(getList(XQuery.FIND_TIPI_REGIONAL_PATENT_BY_PCT, x.getId()));
			}
			// caricamento degli stati
			x.setStati(getList(XQuery.FIND_STATI_BY_DEP_EST, x.getId()));
			// caricamento delle titolarita
			x.setStoricoTitolarita(getList(XQuery.FIND_STORICO_TITOLARITA, x.getId()));
			if (x.getStoricoTitolarita()!=null)
				loadAbstractTitolarita(x.getStoricoTitolarita());  //ver1.15
			if (x.getStatoId()!=null)
				x.setStato((Stato) find(Stato.class, x.getStatoId())); //ver1.17
			// caricamento TipoEstinzione ver1.24
			if (x.getTipiEstinzioneId()!=null)
				x.setTipoEstinzione((TipoEstinzione) find(TipoEstinzione.class, x.getTipiEstinzioneId()));
		}
	    return list;
	}
	//ver1.16
	private void salvaStati(DepEst de) {
		if (Utile.isEmptyOrNull(de.getStati())) return;
		for (Stato s : de.getStati()) {
			DepEstStatoKey key = new DepEstStatoKey();
			key.setDepEstId(de.getId());
			key.setStatoId(s.getId());
			create(new DepEstStato(key));
		}
	}
	//ver1.16
	private void aggiornaStati(DepEst de) {
		List<Stato> saved = getList(XQuery.FIND_STATI_BY_DEP_EST, de.getId());
		if (saved==null) saved = new ArrayList<Stato>();
		List<Stato> stati = de.getStati();
		if (stati==null) stati = new ArrayList<Stato>();
		if (Utile.isEmptyOrNull(saved) && Utile.isEmptyOrNull(stati)) return;
		// rimuove dal DB le associazioni assenti nell'entity (stati)
		for (Stato s : saved) {
			if (!stati.contains(s)) {
				DepEstStatoKey key = new DepEstStatoKey();
				key.setDepEstId(de.getId());
				key.setStatoId(s.getId());
				delete(new DepEstStato(key));
			}
		}
		// rilettura dopo eliminazione
		saved = getList(XQuery.FIND_STATI_BY_DEP_EST, de.getId());
		if (saved==null) saved = new ArrayList<Stato>();
		// crea le associazioni per gli stati assenti in DB (saved)
		for (Stato s : stati) {
			if (!saved.contains(s)) {
				DepEstStatoKey key = new DepEstStatoKey();
				key.setDepEstId(de.getId());
				key.setStatoId(s.getId());
				create(new DepEstStato(key));
			}
		}
	}
	//ver1.22 (associazioni DepEst:PctTipoRegionalPatent)
	private void salvaPctTipiRegionalPatent(DepEst x) {
		if (! (DepEst.PCT == x.getTipoId())) return; 		
		if (Utile.isEmptyOrNull(x.getTipiPctRegionalPatent())) return;
		for (TipoPctRegionalPatent pct : x.getTipiPctRegionalPatent()) {
			PctTipoRegionalPatentKey key = new PctTipoRegionalPatentKey();
			key.setPctId(x.getId());
			key.setTipoId(pct.getId());
			create(new PctTipoRegionalPatent(key));
		}
	}
	//ver1.22 (associazioni DepEst:PctTipoRegionalPatent)
	private void aggiornaPctTipiRegionalPatent(DepEst x) {
		if (! (DepEst.PCT == x.getTipoId())) return; 		
		List<TipoPctRegionalPatent> saved = getList(XQuery.FIND_TIPI_REGIONAL_PATENT_BY_PCT, x.getId());
		if (saved==null) saved = new ArrayList<TipoPctRegionalPatent>();
		List<TipoPctRegionalPatent> tipi = x.getTipiPctRegionalPatent();
		if (tipi==null) tipi = new ArrayList<TipoPctRegionalPatent>();
		if (Utile.isEmptyOrNull(saved) && Utile.isEmptyOrNull(tipi)) return;
		// rimuove dal DB le associazioni assenti nell'entity (tipi)
		for (TipoPctRegionalPatent t : saved) {
			if (!tipi.contains(t)) {
				PctTipoRegionalPatentKey key = new PctTipoRegionalPatentKey();
				key.setPctId(x.getId());
				key.setTipoId(t.getId());
				delete(new PctTipoRegionalPatent(key));
			}
		}
		// rilettura dopo eliminazione
		saved = getList(XQuery.FIND_TIPI_REGIONAL_PATENT_BY_PCT, x.getId());
		if (saved==null) saved = new ArrayList<TipoPctRegionalPatent>();
		// crea le associazioni per i tipi assenti in DB (saved)
		for (TipoPctRegionalPatent t : tipi) {
			if (!saved.contains(t)) {
				PctTipoRegionalPatentKey key = new PctTipoRegionalPatentKey();
				key.setPctId(x.getId());
				key.setTipoId(t.getId());
				create(new PctTipoRegionalPatent(key));
			}
		}
	}

	public List<TrovatoDipartimento> getTrovatoDipartimento(Integer id) {
		return findByQuery(XQuery.FIND_TROVATO_DIPARTIMENTO, id);
	}
	public List<Utente> getUtenti() {
		return findAll(Utente.class);
	}
	public Utente getUtente(String id) {		
		return (Utente) find(Utente.class, id);
	}
	public Valorizzazione getValorizzazione(Integer id) {
		Valorizzazione v = (Valorizzazione) find(Valorizzazione.class, id);
		v.setTrovati(findByQuery(XQuery.FIND_TROVATI_BY_VALID, id));
		if (v.getAziendaId()!=null)
			v.setAzienda((Azienda) find(Azienda.class, v.getAziendaId()));
		if (v.getTipoValorizzazioneId()!=null) {
			v.setTipo((TipoValorizzazione) find(TipoValorizzazione.class, v.getTipoValorizzazioneId()));
		}
		return v;
	}
	//ver1.20
	public Valorizzazione salvaValorizzazione(Valorizzazione valorizzazione) {
		List<Trovato> trovati = valorizzazione.getTrovati();
		Azienda azienda = valorizzazione.getAzienda();
		Valorizzazione v = null;
		if (valorizzazione.getId()==null) {
			v = (Valorizzazione) create(valorizzazione);
		} else {
			v = (Valorizzazione) update(valorizzazione);
		}
		if(trovati!=null){
			executeQuery(XQuery.DELETE_TROVATI_VALORIZZAZIONI, v.getId());
			if (Utile.isNotEmptyOrNull(trovati)) {
				for (Trovato x : trovati) {
					TrovatoValorizzazioneKey key = new TrovatoValorizzazioneKey();
					key.setValid(v.getId());
					key.setNsrif(x.getNsrif());
					TrovatoValorizzazione tv = new TrovatoValorizzazione();
					tv.setKey(key);
					create(tv);
				}
			}
			v.setTrovati(trovati);
		}
		if (azienda!=null) {
			if (azienda.getId()==null)
				azienda = (Azienda) create(azienda);
			else
				azienda = (Azienda) update(azienda);
		} else {
			azienda = getAzienda(v.getAziendaId());
		}
		v.setAzienda(azienda);
		v.setTipo((TipoValorizzazione) find(TipoValorizzazione.class, v.getTipoValorizzazioneId()));
		return v;
	}
	public List<Azienda> getAziende() {
		return findAll(Azienda.class);
	}
	public Azienda getAzienda(Integer id) {
		return (Azienda) find(Azienda.class, id);
	}
	public List<TipoValorizzazione> getTipiValorizzazione() {
		return (List<TipoValorizzazione>) findAll(TipoValorizzazione.class);
	}
	//ver.1.21
	public List<TipoDocumento> getTipiDocumento() {
		return (List<TipoDocumento>) findAll(TipoDocumento.class);
	}
	public List<DocumentoInfo> getDocumentiInfo(Parametri p) {
		List<DocumentoInfo> list = null;
		List<Integer> ids = find(new DocumentoQuery().getQuery(p));
		if(Utile.isNotEmptyOrNull(ids)) { 
			list = new ArrayList<DocumentoInfo>();
			for(Integer x: ids) {
				list.add(getDocumentoInfo(x));
			}
		}
		return list; 
	}
	//ver 1.22
	public DocumentoInfo getDocumentoInfo(Integer id) {
		DocumentoInfo x = (DocumentoInfo) find(DocumentoInfo.class, id);
		if(x.getTipoDocumentoId()!=null)
			x.setTipoDocumento((TipoDocumento) find(TipoDocumento.class, x.getTipoDocumentoId()));
		x.setTrovati(findByQuery(XQuery.FIND_TROVATI_BY_DOCID, x.getDocumentoId()));
		return x;
	}
	public void delDocumentoInfo(Integer id) {
		if (id==null) return;
		executeQuery(XQuery.DELETE_TROVATI_DOCUMENTI_BY_DOCID, id);		
		DocumentoInfo x = (DocumentoInfo) find(DocumentoInfo.class, id);
		if (x!=null) delete(x);
		Documento y = (Documento) find(Documento.class, id);
		if (y!=null) docService.delete(id);
	}
	public DocumentoInfo salvaDocumentoInfo(DocumentoInfo doc) {
		// DocumentoInfo è sempre associato ad un documento già esistente via documentoId
		DocumentoInfo x = docService.salvaDocumentoInfo(doc);
		executeQuery(XQuery.DELETE_TROVATI_DOCUMENTI_BY_DOCID, x.getDocumentoId());
		if (doc.getTrovati()==null) return doc; // nessuna nuova associazione 
		if (doc.getTrovati().isEmpty()) return doc; // nessuna associazione
		for (Trovato t : doc.getTrovati()) {
			TrovatoDocumentoKey key = new TrovatoDocumentoKey();
			key.setDocumentoId(x.getDocumentoId());
			key.setNsrif(t.getNsrif());
			TrovatoDocumento td = new TrovatoDocumento(key);
			create(td);
		}
		return doc;
	}
	public List<TipoEstinzione> getTipiEstinzione() {
		return findAll(TipoEstinzione.class);
	}
	// ver 1.27
	public Verbale getVerbale(Integer id) {
		Verbale v = (Verbale) find(Verbale.class, id);
		List<Trovato> trovati = findByQuery(XQuery.FIND_TROVATI_BY_VERBALE, id);
		if (Utile.isNotEmptyOrNull(trovati)) {
			for (Trovato x : trovati) {
				TrovatoVerbaleKey key = new TrovatoVerbaleKey(id, x.getNsrif());
				TrovatoVerbale tv = (TrovatoVerbale) find(TrovatoVerbale.class, key);
				if (tv!=null) {
					x.setAzione(tv.getAzione());
					x.setRifiuto(tv.getRifiuto());
				}
			}
		}
		v.setTrovati(trovati);
		return v;
	}
	public List<Verbale> getVerbali(Integer nsrif) {
		List<Verbale> list = getList(XQuery.FIND_VERBALI_BY_NSRIF, nsrif);
		if (Utile.isEmptyOrNull(list)) return null;
		for (Verbale x : list) {
			TrovatoVerbaleKey key = new TrovatoVerbaleKey(x.getId(), nsrif);
			TrovatoVerbale tv = (TrovatoVerbale) find(TrovatoVerbale.class, key);
			if (tv!=null) {
				x.setAzione(tv.getAzione());
				x.setRifiuto(tv.getRifiuto());	
			}
		}
		return list;
	}
	// TODO settare i campi azione rifiuto da transienti nei trovati
	public Verbale salvaVerbale(Verbale verbale) {
		List<Trovato> trovati = verbale.getTrovati();
		Verbale v = null;
		if (verbale.getId()==null) {
			v = (Verbale) create(verbale);
		} else {
			v = (Verbale) update(verbale);
		}
		if(trovati!=null){
			executeQuery(XQuery.DELETE_TROVATI_VERBALI, v.getId());
			if (Utile.isNotEmptyOrNull(trovati)) {
				for (Trovato x : trovati) {
					TrovatoVerbaleKey key = new TrovatoVerbaleKey();
					key.setVerbaleId(v.getId());
					key.setNsrif(x.getNsrif());
					TrovatoVerbale tv = new TrovatoVerbale();
					tv.setKey(key);
					tv.setAzione(x.getAzione());
					tv.setRifiuto(x.getRifiuto());
					create(tv);
				}
			}
			v.setTrovati(trovati);
		}
	return v;	
	}
}
