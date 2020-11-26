package it.cnr.brevetti.ejb.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.naming.AuthenticationException;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import it.cnr.brevetti.config.Configurator;
import it.cnr.brevetti.config.Settings;
import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.ejb.entities.XQuery;
import it.cnr.brevetti.gas.LdapEntry;
import it.cnr.brevetti.gas.UserParam;
import it.cnr.brevetti.gas.UtenteLdap;
import it.cnr.brevetti.gas.UtenteLdapQuery;
import it.cnr.brevetti.util.ApplicationProperties;
import it.cnr.brevetti.util.Log;

/**
 * Implementazione servizio LDAP
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [28-Apr-07]
 * @version 2.0 [22-Jun-17] adattamento BREVETTI 
 */
@Stateless
@SuppressWarnings({"rawtypes"})
public class LdapServiceBean implements LdapService {
	private static final Log log = Log.getInstance(LdapServiceBean.class);
	private static final boolean debug = true;

	@EJB Configurator config;

	private Properties ldapRead;
	private Properties ldapReadBis;
	private Properties ldapWrite;

	@PostConstruct 
    void init() {
		ldapRead = config.getLdapConfig().getRead();
		ldapReadBis = config.getLdapConfig().getReadBis();
		ldapWrite = config.getLdapConfig().getWrite();
	}

	/** ritorna l'utente con i dati trovati nel repository LDAP */
	public UtenteLdap readUtente(String utenteId) {
		return getUtenteLdap(UtenteLdap.UTENTE_ID + "=" + utenteId);
	}

	/** ritorna l'utente con i dati trovati nel repository LDAP */
	public UtenteLdap readUtenteForUpdate(String utenteId) {
//		return getUtenteLdap(UtenteLdap.UTENTE_ID + "=" + utenteId, ldapWrite);
		return getUtenteLdap(UtenteLdap.UTENTE_ID + "=" + utenteId, getWriteContext());
	}

	/** autentica l'utente utilizzando il dn e la password indicati */
	public Boolean autenticaUtente(String dn, String password) {
		debug("Entering autenticaUtente..." + dn);
		Properties env = getLoginProperties();
		env.put(LdapContext.SECURITY_PRINCIPAL, dn);
		env.put(LdapContext.SECURITY_CREDENTIALS, password);
		debug(env);
		InitialContext context = null;
		try {
			context = new InitialLdapContext(env, null);
		} catch (AuthenticationException e) {
			debug(dn);
			debug(e.getMessage());
			return false;
		} catch (NamingException e) {
			log.error(dn);
			log.error(e.getMessage());
			throw new EJBException(e);
		} finally {
			try {
				context.close();
			} catch (Throwable t) {}
		}
		return true;
	}
	public UtenteLdap findUtenteByCodiceFiscale(String codiceFiscale) {
		if (null == codiceFiscale || codiceFiscale.trim().length() < 1)
			return null;
		else
			return getUtenteLdap(UtenteLdap.CODICE_FISCALE + "="
					+ codiceFiscale);
	}

	public UtenteLdap findUtenteByMatricola(Integer matricola) {
		if (null == matricola)
			return null;
		else
			return getUtenteLdap(UtenteLdap.MATRICOLA + "="
					+ matricola.toString());
	}

	public void updateUtente(UtenteLdap utente) {
		if (null == utente)
			return;
		UtenteLdap old = readUtente(utente.getUtenteId());
		if (old.getFreeField().equalsIgnoreCase(utente.getFreeField()))
			return;
		BasicAttribute ba = new BasicAttribute(UtenteLdap.FREE_FIELD, utente
				.getFreeField());
		ModificationItem[] mods = new ModificationItem[]{new ModificationItem(
				DirContext.REPLACE_ATTRIBUTE, ba)};
		try {
			getWriteContext().modifyAttributes(utente.getDN(), mods);
		} catch (NamingException e) {
			throw new EJBException(new Exception(e));
		}
	}
	
	// ==================================================================
	// ricerca utenti
	// ==================================================================

	public List<UtenteLdap> findUtenti(Parametri params) {
		List<UtenteLdap> temp = findUtenti(new UtenteLdapQuery(params).getQuery());
		if (null == temp) return null;
		Integer number = (Integer)params.get(UserParam.START);		
		int start = number == null ? XQuery.FIRST_RESULT : number.intValue();
		number = (Integer)params.get(UserParam.RIGHE);
		int righe = number == null ? XQuery.MAX_RESULTS : number.intValue();
		Collections.sort(temp);
		int end = Math.min(temp.size(), (start + righe));
		return temp.subList(start, end);
	}
	
	public List<UtenteLdap> findUtenti(String query) {
		debug(query);
		List<UtenteLdap> list = null;
		DirContext context = getReadContext();
		String rootDN = ApplicationProperties.getInstance().getRootDN();
		List<LdapEntry> results = null;
		try {
			results = search(context, rootDN, query, UtenteLdap.getNomiAttributi());
		} catch (NamingException e) {
			throw new EJBException(e);
		} finally {
			close(context);
		}
		if (results != null && results.size() > 0) {
			list = new ArrayList<UtenteLdap>();
			for (LdapEntry entry : results) {
				list.add(new UtenteLdap(entry));
			}
		}
		return list;
	}
	public Long countUtenti(String query) {
		List list = findUtenti(query);
		return null == list ? new Long(0) : list.size();
	}
	public Long countUtenti(Parametri params) {
		return countUtenti(new UtenteLdapQuery(params).getQuery());
	}

	// ==================================================================
	// funzioni di servizio
	// ==================================================================

	/**
	 * ritorna l'utente ldap definito dal filtro di ricerca utilizzando il
	 * contesto di sola lettura
	 */
	private UtenteLdap getUtenteLdap(String filter) {
		return getUtenteLdap(filter, getReadContext());
	}
	/**
	 * ritorna l'utente ldap utilizzando il filtro ed il contesto indicati
	 */
	private UtenteLdap getUtenteLdap(String filter, DirContext context) {
		UtenteLdap utente = null;

		String rootDN = ApplicationProperties.getInstance().getRootDN();
		List<LdapEntry> results = null;
		try {
			results = search(context, rootDN, filter, UtenteLdap
					.getNomiAttributi());
		} catch (NamingException e) {
			throw new EJBException(e);
		} finally {
			close(context);
		}
		if (results != null) {
			if (results.size() > 1) {
				throw new EJBException(
						"Ci si aspettava una occorenza invece di "
								+ results.size() + " - filtro: " + filter);
			}
			utente = new UtenteLdap(results.get(0));
		}
		return utente;
	}

	// close context quietly
	private void close(DirContext ctx) {
		debug("Entering close(DirContext)");
		if (ctx!=null)
			try {
				ctx.close();
			} catch (Throwable t) {
				log.error(t, "DirContext.close");
			}
	}
	// close enumeration quietly
	private void close(NamingEnumeration ne) {
		debug("Entering close(NamingEnumeration)");
		try {
			ne.close();
		} catch (Throwable t) {
			log.error(t, "NamingEnumeration.close");
		}
	}
	private List<LdapEntry> search(DirContext dc, String base, String filter,
			String[] attributes) throws NamingException {
		List<LdapEntry> results = new ArrayList<LdapEntry>();
		SearchControls sc = new SearchControls();
		sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
		if (attributes.length > 0) {
			sc.setReturningAttributes(attributes);
		}
		debug("Using NamingEnumeration (1)");
		NamingEnumeration ne = dc.search(base, filter, sc);
		if (false == ne.hasMoreElements()) {
			close(ne);
			return null;
		}
		while (ne.hasMore()) {
			LdapEntry entry = new LdapEntry();
			SearchResult sr = (SearchResult) ne.next();
			String name = sr.getName();
			if (base != null && !base.equals("")) {
				entry.setDN(name + "," + base);
			} else {
				entry.setDN(name);
			}
			Attributes at = sr.getAttributes();
			debug("Using NamingEnumeration (2)");
			NamingEnumeration ane = at.getAll();
			while (ane.hasMore()) {
				Attribute attr = (Attribute) ane.next();
				String attrType = attr.getID();
				NamingEnumeration values = attr.getAll();
				List<String> vals = new ArrayList<String>();
				while (values.hasMore()) {
					Object value = values.nextElement();
					if (value instanceof String) {
						vals.add((String) value);
					} else {
						vals.add(new String((byte[]) value));
					}
				}
				entry.put(attrType, vals);
			}
			results.add(entry);
			close(ane);
		}
		close(ne);
		return results;
	}

	// ---------------------------------------------------------------------------------------
	// ver1.3 monitor della connessione
	// ---------------------------------------------------------------------------------------
	private static int count = 0;
	@Resource
	javax.ejb.TimerService timerService;

	public void createTimer() {
		long ciclo = 1000 * 60 * 90;
		log.info("Entering createTimer() cicle=" + ciclo + "ms");
		timerService.createTimer(1000, ciclo, null);
	}
	@Timeout
	public void timeout(Timer timer) {
		log.info(new Date() + " Entering timeout (" + ++count + ")");
		UtenteLdap utente = readUtente(Settings.getInstance().get("it.cnr.brevetti.ldap.uid"));
		if (null == utente) {
			stopTimer();
			log.debug("LdapServiceBean.timeout: utente NULL (timer stopped)");
		}
	}

	public void stopTimer() {
		log.debug("Entering stopTimer()");
		count = 0;
		for (Iterator iter = timerService.getTimers().iterator(); iter.hasNext();) {
			Timer timer = (Timer) iter.next();
			timer.cancel();
		}
	}
	// ---------------------------------------------------------------------------------------
	// ver1.4 gestione manuale dei context
	// ---------------------------------------------------------------------------------------
	private LdapContext getReadContext() {
		debug("Entering getReadContext");
		debug(ldapRead);
		try {
			return new InitialLdapContext(ldapRead,null);
		} catch (NamingException e) {
			try {
				return new InitialLdapContext(ldapReadBis,null);
			} catch (NamingException e1) {
				log.error(e, "Creating read context");
				throw new EJBException(e);
			}
		}		
	}
	private LdapContext getWriteContext() {
		debug("Entering getWriteContext");
		debug(ldapWrite);
		try {
			return new InitialLdapContext(ldapWrite,null);
		} catch (NamingException e) {
			log.error(e, "Creating write context");
			throw new EJBException(e);
		}		
	}
	private Properties getLoginProperties() {
		Properties env =  new Properties();
		env.putAll(getReadProperties());
		env.remove(LdapContext.SECURITY_PRINCIPAL);
		env.remove(LdapContext.SECURITY_CREDENTIALS);
		return env;
	}	
	private Properties getReadProperties() {
		LdapContext ic = null;
		try {
			ic = new InitialLdapContext(ldapRead,null);
			if (ic != null) close(ic);
			return ldapRead;
		} catch (NamingException e) {
			// since the primary set of properties did not work
			// returns the secondary set of properties
			return ldapReadBis;
		}
	}

	private void debug(Object object) {
		if (debug) log.debug(object.toString());
	}

	/** ritorna vero nel caso non si riesca a connettersi ad LDAP */
	public boolean isConnectionClosed() {
		LdapContext ic = null;
		try {
			ic = new InitialLdapContext(ldapRead,null);
			return false;
		} catch (NamingException e) {
			try {
				ic = new InitialLdapContext(ldapReadBis,null);
				return false;
			} catch (NamingException e1) {
				return true;
			} finally {
				close(ic);
			}
		} finally {
			close(ic);
		}
	}

	public Properties getReadProperties(int what) {
		switch (what) {
			case 1: return ldapRead; 
			case 2: return ldapReadBis; 
			default: return null;
		}
	}

	public boolean testReadProperties(Properties env) {
		LdapContext ic = null;
		try {
			ic = new InitialLdapContext(env,null);
			if (ic != null) close(ic);
			return true;
		} catch (NamingException e) {
			return false; 
		}
	}
}
