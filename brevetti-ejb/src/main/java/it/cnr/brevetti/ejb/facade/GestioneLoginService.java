package it.cnr.brevetti.ejb.facade;

import javax.ejb.Remote;

import it.cnr.brevetti.gas.SessioneUtente;

/**
 * Interfaccia comune di gestione del login
 * @author Aurelio D'Amico
 * @version 1.0 [14-Apr-08]
 * @version 2.0 [23-Jun-17] REST
 */
@Remote
public interface GestioneLoginService {
	public boolean verificaUtente(String uid);
	public boolean autenticaUtente(String uid, String password);
	public SessioneUtente getSessioneUtente(String uid) throws Exception;
	public SessioneUtente getSessioneUtente(String uid, String struttura) throws Exception;
	public void printSessioneUtente(String uid) throws Exception;
}
