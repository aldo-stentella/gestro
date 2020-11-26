package it.cnr.brevetti.config;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import it.cnr.brevetti.ejb.entities.Property;
import it.cnr.brevetti.ejb.services.BrevettiService;
import it.cnr.brevetti.util.ApplicationProperties;
import it.cnr.brevetti.util.HostName;
import it.cnr.brevetti.util.Log;

/**
 * Inizializza la configurazione dell'applicazione
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [2017-09-14]
 */
@Startup
@Singleton
public class Configurator {
	private static final Log log = Log.getInstance(Configurator.class);	
	private static final int GLOBAL = 1;
	private static final int APP = 2; 
	private static final int MODULE = 3;
	private static final String GLOBAL_PREFIX = "java:global/";
	private static final String APP_PREFIX = "java:app/";
	private static final String MODULE_PREFIX = "java:module/";
	// tipologia del lookup
	private static final int LOOKUP = APP; 

	@Resource(lookup="java:module/ModuleName")
	private String moduleName;
	@Resource(lookup="java:app/AppName")
	private String appName;
	@EJB BrevettiService crud;
	private Settings settings;
	private LdapConfig ldapConfig;	
	public Settings getSettings() {
		return settings;
	}
	public LdapConfig getLdapConfig() {
		return ldapConfig;
	}
	@PostConstruct 
	@SuppressWarnings("unchecked")
    public void initialize() {
		try {
			settings = Settings.getInstance();
			settings.initialize(crud.findAll(Property.class));
			ldapConfig = LdapConfig.getInstance();
			ldapConfig.bind(settings);
			String prefix = null;
			switch (LOOKUP) {
			case GLOBAL:
				prefix = GLOBAL_PREFIX + appName + "/" + moduleName + "/";
				break;
			case APP:		
				prefix = APP_PREFIX + moduleName + "/";
				break;
			case MODULE:				
				prefix = MODULE_PREFIX;
				break;
			}
			System.setProperty(ApplicationProperties.PREFIX, prefix);
			System.setProperty(ApplicationProperties.HOST, HostName.getHostName());
		} catch (Exception e) {
			log.error(e);
			throw new EJBException(e);
		}
    }
}
