package it.cnr.brevetti.ejb.sigla;

import java.util.List;

import javax.ejb.Remote;

import it.cnr.brevetti.sigla.fatture.attive.FatturaAttiva;
import it.cnr.brevetti.sigla.fatture.attive.FatturaAttivaBase;

/**
 * Client per il web service di interrogazione delle fatture attive su SIGLA
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Sep 22, 2016]
 */
@Remote
public interface FattureAttiveService {
	/**
	 * restituisce la lista delle righe di fattura attiva selezionata in base ai parametri in ingresso,
	 * che indicano il CDS/UO che ha originato la fattura  (ESERCIZIO, CD_CDS_ORIGINE, CD_UO_ORIGINE, PG_FATTURA_ATTIVA)
	 * 
	 * @param esercizio	-	esercizio della fattura ricercata
	 * @param cds	-	codice CDS origine della fattura ricercata
	 * @param uo	-	codice Unità Organizzativa di origine della fattura ricercata
	 * @param pg	-	numero progressivo della fattura ricercata
	 * @return	-	lista delle righe di fattura
	 */
	List<FatturaAttiva> ricercaFatturaAttiva(Long esercizio, String cds, String uo, Long pg) throws Exception;			 
	/**
	 * restituisce la lista delle righe di fattura attiva selezionata in base ai parametri in ingresso,
	 * che indicano la chiave della fattura (ESERCIZIO, CD_CDS, CD_UNITA_ORGANIZZATIVA, PG_FATTURA_ATTIVA)
	 * 
	 * @param esercizio	-	esercizio della fattura ricercata
	 * @param cds	-	codice CDS della fattura ricercata
	 * @param uo	-	codice Unità Organizzativa della fattura ricercata
	 * @param pg	-	numero progressivo della fattura ricercata
	 * @return	-	lista delle righe di fattura
	 */	
	List<FatturaAttiva> ricercaFatturaAttivaByKey(Long esercizio,	String cds,	String uo,	Long pg) throws Exception;			 
	/**
	 * restituisce la lista di TUTTE le righe di fattura attiva se in una delle righe è presente il trovato
	 * richiesto come parametro in ingresso  
	 * 
	 * @param trovato	- progressivo del trovato ricercato
	 * @return	-	lista delle righe di fattura
	 */
	 List<FatturaAttiva> ricercaFattureAttive(Long trovato) throws Exception;			 
	/**
	 * restituisce la lista di TUTTE le righe di fattura attiva se in una delle righe è presente il trovato
	 * richiesto come parametro in ingresso
	 * 
	 * @param trovato	- progressivo del trovato ricercato
	 * @return	-	lista delle righe di fattura in versione BASE (ridotta)
	 */
	List<FatturaAttivaBase> ricercaFattureAttiveBase(Long trovato) throws Exception;
}
