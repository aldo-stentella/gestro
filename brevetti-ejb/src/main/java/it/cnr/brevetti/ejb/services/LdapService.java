package it.cnr.brevetti.ejb.services;

import java.util.List;
import java.util.Properties;

import javax.ejb.Remote;

import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.gas.UtenteLdap;

/**
 * Interfaccia LDAP
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [28-Apr-07]
 * @version 2.0 [22-Jun-17] adattamento BREVETTI
 */
@Remote
public interface LdapService {
	public UtenteLdap readUtente(String utenteId);
	public UtenteLdap readUtenteForUpdate(String utenteId);
	public Boolean autenticaUtente(String dn, String password);
	public UtenteLdap findUtenteByCodiceFiscale(String codiceFiscale);
	public UtenteLdap findUtenteByMatricola(Integer matricola);
	public void updateUtente(UtenteLdap utente);
	public List<UtenteLdap> findUtenti(Parametri params);
	public List<UtenteLdap> findUtenti(String query);
	public Long countUtenti(Parametri params);
	public Long countUtenti(String query);
	public boolean isConnectionClosed();
	public Properties getReadProperties(int what);
	public boolean testReadProperties(Properties env);
	

}
