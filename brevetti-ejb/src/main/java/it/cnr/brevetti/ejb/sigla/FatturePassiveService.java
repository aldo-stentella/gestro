package it.cnr.brevetti.ejb.sigla;

import java.util.List;

import javax.ejb.Remote;

import it.cnr.brevetti.sigla.fatture.passive.FatturaPassiva;
import it.cnr.brevetti.sigla.fatture.passive.FatturaPassivaBase;

/**
 * Client per il web service di interrogazione delle fatture passive su SIGLA
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Sep 20, 2016]
 */
@Remote
public interface FatturePassiveService {
	/**
	 * restituisce la lista delle righe di fattura passiva selezionata in base ai parametri in ingresso,
	 * che indicano il CDS/UO che ha originato la fattura  (ESERCIZIO, CD_CDS_ORIGINE, CD_UO_ORIGINE, PG_FATTURA_ATTIVA)
	 * 
	 * @param esercizio	-	esercizio della fattura ricercata
	 * @param cds	-	codice CDS origine della fattura ricercata
	 * @param uo	-	codice Unità Organizzativa di origine della fattura ricercata
	 * @param pg	-	numero progressivo della fattura ricercata
	 * @return	-	lista delle righe di fattura
	 */
	List<FatturaPassiva> ricercaFatturaPassiva(Long esercizio, String cds, String uo, Long pg) throws Exception;
	/**
	 * restituisce la lista delle righe di fattura passiva selezionata in base ai parametri in ingresso,
	 * che indicano la chiave della fattura (ESERCIZIO, CD_CDS, CD_UNITA_ORGANIZZATIVA, PG_FATTURA_ATTIVA)
	 * 
	 * @param esercizio	-	esercizio della fattura ricercata
	 * @param cds	-	codice CDS della fattura ricercata
	 * @param uo	-	codice Unità Organizzativa della fattura ricercata
	 * @param pg	-	numero progressivo della fattura ricercata
	 * @return	-	lista delle righe di fattura
	 */
	List<FatturaPassiva> ricercaFatturaPassivaByKey(Long esercizio, String cds, String uo, Long pg) throws Exception;
	/**
	 * restituisce la lista di TUTTE le righe di fattura passiva se in una delle righe è presente il trovato
	 * richiesto come parametro in ingresso  
	 * 
	 * @param trovato	- progressivo del trovato ricercato
	 * @return	-	lista delle righe di fattura
	 */
	List<FatturaPassiva> ricercaFatturePassive(Long trovato) throws Exception;
	/**
	 * restituisce la lista di TUTTE le righe di fattura passiva se in una delle righe è presente il trovato
	 * richiesto come parametro in ingresso
	 * 
	 * @param trovato	- progressivo del trovato ricercato
	 * @return	-	lista delle righe di fattura in versione BASE (ridotta)
	 * @throws Exception 
	 */	
	List<FatturaPassivaBase> ricercaFatturePassiveBase(Long trovato) throws Exception;	
}
