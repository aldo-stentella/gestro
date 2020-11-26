package it.cnr.brevetti.ejb.facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import it.cnr.brevetti.ejb.gas.SessioneUtenteService;
import it.cnr.brevetti.ejb.services.LdapService;
import it.cnr.brevetti.gas.SessioneUtente;
import it.cnr.brevetti.gas.UtenteLdap;

/**
 * Implementazione servizio gestione login
 * @author Aurelio D'Amico
 * @version 1.0 [14-Apr-08]
 * @version 2.0 [23-Jun-17] REST
 */
@Stateless
public class GestioneLoginServiceBean implements GestioneLoginService {

	@EJB SessioneUtenteService sessioneUtenteService;
	@EJB LdapService ldapService;
	
	/** ritorna true se l'user id indicato esiste */
	public boolean verificaUtente(String uid) {
		return ldapService.readUtente(uid) == null ? false : true;
	}
	/** ritorna true se la connessione con ldap riesce utilizzando le credenziali indicate */
	public boolean autenticaUtente(String uid, String password) {		
		UtenteLdap user = ldapService.readUtente(uid);
		return user == null ? false : ldapService.autenticaUtente(user.getDN(), password);
	}
	public SessioneUtente getSessioneUtente(String uid) throws Exception {
		return sessioneUtenteService.getSessioneUtente(uid);
	}
	public SessioneUtente getSessioneUtente(String uid, String struttura) throws Exception {
		return sessioneUtenteService.getSessioneUtente(uid, struttura);
	}
	public void printSessioneUtente(String uid) throws Exception {
		SessioneUtente su = sessioneUtenteService.getSessioneUtente(uid);
		System.out.println(su); 
	}
}
