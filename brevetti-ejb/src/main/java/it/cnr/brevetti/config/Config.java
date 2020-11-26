package it.cnr.brevetti.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.ldap.InitialLdapContext;

import it.cnr.brevetti.util.Log;
import it.cnr.brevetti.util.ServiceLocator;
/**
 * Configuration holder
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [03-07-2017]
 *
 */
public class Config implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Log log = Log.getInstance(Config.class);
	public static final String KEY = Config.class.getName();
	public static final char CR = '\n';
	private static Config instance;
	private Settings settings;
	private LdapConfig ldapConfig;	
	
	private Config() {}
	
	public static Config getInstance() {
		if (null==instance)	instance = new Config();
		return instance;
	}
	public void bind(Configurator c) {
		if (c!=null) {
			settings = c.getSettings();
			ldapConfig = c.getLdapConfig();
		}
		if (settings!=null && ldapConfig!=null) {
			log.info(print());
		} else {
			log.info("*** Inizializzazione fallita ***");
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String print() {
		StringBuilder sb = new StringBuilder();
		sb.append(CR+"Inizializzazione avvenuta con successo."+CR);		
		sb.append(CR+"SYSTEM PROPERTIES"+CR+CR);		
		Properties p = System.getProperties();
		List list = new ArrayList(p.keySet());
		Collections.sort(list);
		for (Object item : list) {
			String key = (String) item;
			if (key.startsWith("it.cnr")) {
				sb.append(item.toString());
				sb.append("=");
				sb.append(p.get(item).toString());
				sb.append(CR);
			}
		}
		sb.append(CR+"APPLICATION PROPERTIES"+CR+CR);		
		p = Settings.getInstance().getProperties();
		list = new ArrayList(p.keySet());
		Collections.sort(list);
		for (Object item : list) {
			sb.append(item.toString());
			sb.append("=");
			sb.append(p.get(item).toString());
			sb.append(CR);
		}
		printLdap(sb);
		return sb.toString();
	}
	private void printLdap(StringBuilder sb) {
		sb.append(CR+"LDAP (read)"+CR);
		printLdap(sb,ldapConfig.getRead());
		sb.append(CR+"LDAP (read bis)"+CR);
		printLdap(sb,ldapConfig.getReadBis());
		sb.append(CR+"LDAP (write)"+CR);
		printLdap(sb,ldapConfig.getWrite());
	}
	private void printLdap(StringBuilder sb, Properties props) {
		sb.append("URL: " + props.getProperty("java.naming.provider.url"));
		sb.append(CR);
		sb.append("principal: " + props.getProperty("java.naming.security.principal"));
		sb.append(CR);
		sb.append("connection: " );
		Context ctx = null;
		try {
			ctx = new InitialLdapContext(props,null);
			sb.append("OK"+CR);
		} catch (Exception e) {
			sb.append(e.toString());
			sb.append(CR);
		} finally {
			try {
				ctx.close();
			} catch (Exception e) {}
		}
	}
	public void reset() throws Exception {
		Configurator c = getConfigurator();
		c.initialize();
		bind(c);
	}
    private Configurator getConfigurator() throws Exception {
    	return ServiceLocator.getInstance().getConfigurator();
    }
}
