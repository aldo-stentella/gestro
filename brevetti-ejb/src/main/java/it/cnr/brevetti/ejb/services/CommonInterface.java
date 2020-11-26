package it.cnr.brevetti.ejb.services;

import java.util.List;

import it.cnr.brevetti.ejb.entities.Classificazione;
import it.cnr.brevetti.ejb.entities.Dipartimento;
import it.cnr.brevetti.ejb.entities.DipendenteCnr;
import it.cnr.brevetti.ejb.entities.EnteEsterno;
import it.cnr.brevetti.ejb.entities.Idioma;
import it.cnr.brevetti.ejb.entities.Inventore;
import it.cnr.brevetti.ejb.entities.Istituto;
import it.cnr.brevetti.ejb.entities.Stato;
import it.cnr.brevetti.ejb.entities.StudioBrevettuale;
/**
 * Interfaccia di esposizione di metodi di interrogazione riutilizzabili
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [2014-06-23]
 *
 */
@SuppressWarnings("rawtypes")
public interface CommonInterface {
	Classificazione getClassificazione(Integer id);
	List getClassificazioniAncestors(Integer id);
	List getClassificazioniChildren(Integer idPadre);
	List getClassificazioniLivello(Integer livello);
	List getClassificazioniLivello(Integer livello, Integer tipo);
	List getDenominazioniStudiBrevettuali();
	List getDipartimenti();
	Dipartimento getDipartimento(Integer id);
	DipendenteCnr getDipendenteByMatricola(Integer matricola);
	EnteEsterno getEnteEsterno(Integer id);
	List getEntiEsterni();
	Idioma getIdioma(Integer id);
	List getIdiomi();
	Inventore getInventore(Integer id);
	Inventore getInventoreByMatricola(Integer matricola);
	// aggiungere % alla fine ed usare like
	List getInventori(String cognome);
	List getIstituti();
	Istituto getIstituto(Integer id);
	List getSediStudiBrevettuali(String denominazione);
	List getStati();
	Stato getStato(Integer id);
	List getStudiBrevettuali();
	/** select distinct sulla denominazione */	
	List getStudiBrevettualiSortedByDenominazione();
	StudioBrevettuale getStudioBrevettuale(Integer id);
	List getTipiRegionalPatent();
}
