package it.cnr.brevetti.rest;

import java.util.Properties;

import javax.ejb.EJBException;
import javax.naming.AuthenticationException;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.apache.commons.lang.StringUtils;
import org.jboss.resteasy.util.Base64;

import it.cnr.brevetti.ejb.services.LdapService;
import it.cnr.brevetti.util.ApplicationProperties;
import it.cnr.brevetti.util.ServiceLocator;

public class RestHelper {
	private static RestHelper instance;

	private RestHelper() {
		// private constructor
	}
	public static synchronized  RestHelper getInstance() {
		if (instance==null) instance = new RestHelper();
		return instance;
	}
	public String getAuthorization(final String encoded) throws Exception {
		if (StringUtils.isBlank(encoded)) return null;
		String encodedUserPassword = encoded.replaceFirst(RestNames.AUTHENTICATION_SCHEME, "");
		return new String(Base64.decode(encodedUserPassword));
	}
	public boolean isUserAllowed(final String uid, final String pass) throws Exception {
		if (StringUtils.isBlank(uid)) return false;
		if (uid.equals(ApplicationProperties.getInstance().getRestUser())) {
			return autenticaUtente(uid,pass);
		}
		return false;
	}
	/** ritorna true se la connessione con ldap riesce utilizzando le credenziali indicate 
	 * @throws Exception */
	public boolean autenticaUtente(String uid, String password) throws Exception {
		LdapService ldap = ServiceLocator.getInstance().getLdapService();
		Properties env = ldap.getReadProperties(1);
		String dn = getDN(env, uid);
		env.put(LdapContext.SECURITY_PRINCIPAL, dn);
		env.put(LdapContext.SECURITY_CREDENTIALS, password);
		InitialContext context = null;
		try {
			context = new InitialLdapContext(env, null);
		} catch (AuthenticationException e) {
			return false;
		} catch (NamingException e) {
			throw new Exception(e);
		} finally {
			try {
				context.close();
			} catch (Throwable t) {}
		}
		return true;
	}

	private String getDN(Properties env, String uid) {
		String distinguishedName  = null;
		SearchControls sc = new SearchControls();
		sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
		DirContext dc = null;
		try {
			dc = new InitialLdapContext(env, null);
			String base = ApplicationProperties.getInstance().getRootDN();
			String filter = "uid=" + uid;
			NamingEnumeration<SearchResult> ne = dc.search(base, filter, sc);
			if (ne.hasMoreElements()) {
				SearchResult sr = (SearchResult) ne.next();
				distinguishedName = sr.getName();
				if (base != null && !base.equals(""))
					distinguishedName += "," + base;
			}
		} catch (NamingException e) {
			throw new EJBException(e);
		} finally {
			try {
				dc.close();
			} catch (Throwable t) {}
		}
		return distinguishedName;		
	}	
}
