package it.cnr.brevetti.ejb.gas;


import javax.ejb.Remote;

import it.cnr.brevetti.gas.SessioneUtente;

/**
 * Interfaccia per la gestione della sessione utente
 * @author Aurelio D'Amico
 * @version 1.0 [16-May-08]
 * @version 2.0 [23-Jun-17] REST
 */
@Remote
public interface SessioneUtenteService {
	public SessioneUtente getSessioneUtente(String uid) throws Exception;
	public SessioneUtente getSessioneUtente(String uid, String struttura) throws Exception;
}
