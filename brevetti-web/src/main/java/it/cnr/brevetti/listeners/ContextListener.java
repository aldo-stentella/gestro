package it.cnr.brevetti.listeners;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import it.cnr.brevetti.config.Config;
import it.cnr.brevetti.config.Configurator;
import it.cnr.brevetti.util.Log;

/**
 * Listener richiamato alla partenza ed alla chiusura dell'applicazione web
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [03-07-2017]
 *
 */
public class ContextListener implements ServletContextListener {
	private static final Log log = Log.getInstance(ContextListener.class);
	@EJB Configurator configurator;
	public void contextDestroyed(ServletContextEvent sce) {
		String name = sce.getServletContext().getServletContextName().toUpperCase();
		log.info("Applicazione " + name + " chiusa");
	}
	public void contextInitialized(ServletContextEvent sce) {
		Config config = Config.getInstance();
		config.bind(configurator);
		ServletContext sc = sce.getServletContext();
		sc.setAttribute(Config.KEY, config);		
		String name = sce.getServletContext().getServletContextName().toUpperCase();
		log.info("Applicazione " + name + " avviata");
	}
}
