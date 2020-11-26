package it.cnr.brevetti.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Generalizzazione dei metodi core
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [6-Jul-07]
 */
public abstract class AbstractServiceLocator {
	public static final boolean LOCAL = true;
	public static final boolean REMOTE = false;
	private static String prefix = null;
	private InitialContext context = null;
	public AbstractServiceLocator(String prefix) throws ServiceLocatorException {
		if (prefix == null || prefix.trim().length() == 0)
			AbstractServiceLocator.prefix = "";
		else
			AbstractServiceLocator.prefix = prefix;
		
		try {
			context = new InitialContext();
		} catch (NamingException e) {
			throw new ServiceLocatorException(e);
		}		
	}
	/** ritorna il servizio corrispondente al nome JNDI indicato */
	public Object getService(String id) throws ServiceLocatorException {
		try {
			return context.lookup(id);
		} catch (NamingException e) {
			throw new ServiceLocatorException(e);
		}
	}
	/** ritorna l'interfaccia locale o remota del servizio indicato */
	public Object getService(boolean local, String name) throws ServiceLocatorException {
		//return (local ? getLocal(name) : getRemote(name));
		return getService(prefix + name); // WILDFLY		
	}
	
	/** ritorna l'interfaccia remota per l'id indicato */
	public Object getRemote(String id) throws ServiceLocatorException {
		return getService(prefix + id + "/remote");
	}
	/** ritorna l'interfaccia locale per l'id indicato */
	public Object getLocal(String id) throws ServiceLocatorException {
		return getService(prefix + id + "/local");
	}
	/** 
	 * Invoca il servizio remoto associato all'EJB indicato 
	 * I moderni AS riescono ad ottimizzare il servizio remoto con prestazioni migliori
	 * dei servizi locali rendendoli superflui.
	 */
	public Object getService(Class<?> clazz) throws ServiceLocatorException {
		//return getRemote(clazz.getSimpleName());
		return getService(prefix + clazz.getSimpleName()); // WILDFLY		
	}
}
