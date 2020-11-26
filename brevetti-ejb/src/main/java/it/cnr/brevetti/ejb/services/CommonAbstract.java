package it.cnr.brevetti.ejb.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.ejb.entities.Classificazione;
import it.cnr.brevetti.ejb.entities.Dipartimento;
import it.cnr.brevetti.ejb.entities.DipendenteCnr;
import it.cnr.brevetti.ejb.entities.EnteEsterno;
import it.cnr.brevetti.ejb.entities.Idioma;
import it.cnr.brevetti.ejb.entities.Inventore;
import it.cnr.brevetti.ejb.entities.Istituto;
import it.cnr.brevetti.ejb.entities.Stato;
import it.cnr.brevetti.ejb.entities.StudioBrevettuale;
import it.cnr.brevetti.ejb.entities.TipoPctRegionalPatent;
import it.cnr.brevetti.ejb.entities.XQuery;

/**
 * Implementazione dei metodi di interrogazione riutilizzabili
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [2014-06-23]
 *
 */
@SuppressWarnings("rawtypes")
public class CommonAbstract extends BasicAbstract {
	
	public Classificazione getClassificazione(Integer id) {
		return (Classificazione) service.read(Classificazione.class, id);	
	}
	/** ritorna il ramo dei parenti (es. nonno, padre, figlio) */
	public List getClassificazioniAncestors(Integer id) {
		Classificazione c = getClassificazione(id);
		if (c == null) return null;
		List<Classificazione> list = new ArrayList<Classificazione>();
		list.add(c);
		while (c.getIdPadre() != null) {
			c = getClassificazione(c.getIdPadre());
			list.add(c);
		}
		Collections.reverse(list);
		return list;
	}
	/** ritorna la lista di tutti i figli del padre indicato */
	public List getClassificazioniChildren(Integer id) {
		return service.findByQuery(XQuery.FIND_CLASSIFICAZIONI_CHILDREN, id);
	}
	/** ritorna la lista delle classificazione di livello indicato */
	public List getClassificazioniLivello(Integer id) {
		return service.findByQuery(XQuery.FIND_CLASSIFICAZIONI_BY_LIVELLO, id);
	}
	/** ritorna la lista delle classificazione di livello e tipo indicato */
	public List getClassificazioniLivello(Integer livello, Integer tipo) {
		Parametri params = Parametri.getNewParametri();
		params.add("id", livello);
		params.add("tipo", tipo);
		return service.findByQuery(XQuery.FIND_CLASSIFICAZIONI_BY_LIVELLO_AND_TIPO, params.getMap());
	}
	/**
	 * Ritorna la lista delle denominazioni degli studi brevettuali eseguendo
	 * una select distinct sulla denominazione
	 */
	public List getDenominazioniStudiBrevettuali() {
		return service.findByQuery(XQuery.FIND_DENOMINAZIONI_STUDI_BREVETTUALI);
	}
	public List getDipartimenti() {
		return service.findAll(Dipartimento.class);
	}
	public Dipartimento getDipartimento(Integer id) {
		return (Dipartimento) service.read(Dipartimento.class, id);
	}	
	public DipendenteCnr getDipendenteByMatricola(Integer matricola) {
		return (DipendenteCnr) service.findObjectByQuery(XQuery.FIND_DIPENDENTE_BY_MATRICOLA, matricola);
	}
	public EnteEsterno getEnteEsterno(Integer id) {
		return (EnteEsterno) service.read(EnteEsterno.class, id);
	}
	public List getEntiEsterni() {
		return service.findAll(EnteEsterno.class);
	}
	public Idioma getIdioma(Integer id) {
		return (Idioma) service.read(Idioma.class, id);
	}
	public List getIdiomi() {
		return service.findAll(Idioma.class);
	}
	public Inventore getInventore(Integer id) {
		return (Inventore) service.read(Inventore.class, id);
	}
	public Inventore getInventoreByMatricola(Integer matricola) {
		return (Inventore) service.findObjectByQuery(XQuery.FIND_INVENTORE_BY_MATRICOLA, matricola);
	}
	/** ritorna una lista di inventori che soddisfano il cognome o parte di esso */
	public List getInventori(String cognome) {
		return service.findByQuery(XQuery.FIND_INVENTORI_BY_COGNOME, cognome.toUpperCase());
	}
	/** ritorna la lista degli istituti non cessati (data-cessazione null) */
	public List getIstituti() {
		return service.findAll(Istituto.class);
	}
	public Istituto getIstituto(Integer id) {
		return (Istituto) service.read(Istituto.class, id);
	}
	public List getSediStudiBrevettuali(String denominazione) {
		return service.findByQuery(XQuery.FIND_SEDI_STUDI_BREVETTUALI, denominazione);
	}
	public List getStati() {
		return service.findAll(Stato.class);
	}
	public Stato getStato(Integer id) {		
		return (Stato) service.read(Stato.class, id);
	}
	public List getStudiBrevettuali() {
		return service.findAll(StudioBrevettuale.class);
	}
	public List getStudiBrevettualiSortedByDenominazione() {
		return service.findByQuery(XQuery.FIND_ALL_STUDI_BREVETTUALI_SORTED);
	}
	public StudioBrevettuale getStudioBrevettuale(Integer id) {
		return (StudioBrevettuale) service.read(StudioBrevettuale.class, id);
	}
	public List getTipiRegionalPatent() {
		return service.findAll(TipoPctRegionalPatent.class);
	}
}
