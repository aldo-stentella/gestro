package it.cnr.brevetti.config;

import java.util.Map.Entry;
import java.util.Properties;

import javax.naming.ldap.LdapContext;

import org.apache.commons.lang.StringUtils;
/**
 * Configurazione delle risorse LDAP 
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Jul 3, 2017]
 *
 */
public class LdapConfig  {
	private static LdapConfig instance;
	public static synchronized LdapConfig getInstance() {
		if (instance == null) {
			instance = new LdapConfig();
		}
		return instance;
	}
	private LdapConfig(){/*private empty contructor*/};
	private Properties read;
	private Properties write;
	private Properties readBis;
	
	public Properties getRead() {
		return read;
	}
	public void setRead(Properties read) {
		this.read = read;
	}
	public Properties getWrite() {
		return write;
	}
	public void setWrite(Properties write) {
		this.write = write;
	}
	public Properties getReadBis() {
		return readBis;
	}
	public void setReadBis(Properties readBis) {
		this.readBis = readBis;
	}

    @SuppressWarnings({ "rawtypes" })
	public void bind(Settings settings) throws Exception {
		Properties config =settings.getSubset("java.naming");
		if (config==null || config.isEmpty()) return;
		Properties props = null;
		for (int i = 1; i < 4; i++) {
			props = new Properties();
			for (Entry x : config.entrySet()) {
				String key = x.getKey().toString();
				String end = "." + i;
				String yek = StringUtils.remove(key, end);
				if (key.endsWith(end))
					props.put(yek, x.getValue());
			}
			if (props.isEmpty()) {
				continue;
			} else {
				// setta le costanti
				props.setProperty(LdapContext.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
				props.setProperty("com.sun.jndi.ldap.connect.pool", "true");
				props.setProperty("com.sun.jndi.ldap.connect.timeout", "5000");
				switch (i) {
					case 1: setRead(props); break;
					case 2: setReadBis(props); break;
					case 3: setWrite(props); break;
				}
			}
		}
	}
	public boolean isEmpty() {
		if ((read==null || read.isEmpty()) && (readBis==null || readBis.isEmpty()))
			return true;
		if (write==null || write.isEmpty()) return true;
		return false;
	}
}	