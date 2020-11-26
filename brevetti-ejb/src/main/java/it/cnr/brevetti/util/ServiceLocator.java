package it.cnr.brevetti.util;

import it.cnr.brevetti.config.Configurator;
import it.cnr.brevetti.ejb.facade.GestioneFatturaService;
import it.cnr.brevetti.ejb.facade.GestioneFatturaServiceBean;
import it.cnr.brevetti.ejb.facade.GestioneLoginService;
import it.cnr.brevetti.ejb.facade.GestioneLoginServiceBean;
import it.cnr.brevetti.ejb.facade.GestionePropostaService;
import it.cnr.brevetti.ejb.facade.GestionePropostaServiceBean;
import it.cnr.brevetti.ejb.facade.GestioneTrovatoService;
import it.cnr.brevetti.ejb.facade.GestioneTrovatoServiceBean;
import it.cnr.brevetti.ejb.facade.GestioneValidazioneService;
import it.cnr.brevetti.ejb.facade.GestioneValidazioneServiceBean;
import it.cnr.brevetti.ejb.services.BrevettiService;
import it.cnr.brevetti.ejb.services.BrevettiServiceBean;
import it.cnr.brevetti.ejb.services.DocumentoService;
import it.cnr.brevetti.ejb.services.DocumentoServiceBean;
import it.cnr.brevetti.ejb.services.IndicizzatoreService;
import it.cnr.brevetti.ejb.services.IndicizzatoreServiceBean;
import it.cnr.brevetti.ejb.services.LdapService;
import it.cnr.brevetti.ejb.services.LdapServiceBean;
import it.cnr.brevetti.ejb.sigla.FattureAttiveService;
import it.cnr.brevetti.ejb.sigla.FattureAttiveServiceBean;
import it.cnr.brevetti.ejb.sigla.FatturePassiveService;
import it.cnr.brevetti.ejb.sigla.FatturePassiveServiceBean;
/**
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [13-Nov-07]
 * @version 1.1 [16-Apr-08] aggiunto metodo getGestioneLoginFacade
 */
public class ServiceLocator extends AbstractServiceLocator {
	private static ServiceLocator instance;

	private ServiceLocator() throws ServiceLocatorException {
		super(ApplicationProperties.getInstance().getPrefix());
	}
	public static ServiceLocator getInstance() throws ServiceLocatorException {
		if (instance == null) {
			instance = new ServiceLocator();
		}
		return instance;
	}
	public GestioneTrovatoService getGestioneTrovatiFacade() throws ServiceLocatorException {
		return (GestioneTrovatoService) getService(GestioneTrovatoServiceBean.class);		
	}	
	public GestioneLoginService getGestioneLoginFacade() throws ServiceLocatorException {
		return (GestioneLoginService) getService(GestioneLoginServiceBean.class);		
	}
	public GestioneFatturaService getGestioneFattureFacade() throws ServiceLocatorException {
		return (GestioneFatturaService) getService(GestioneFatturaServiceBean.class);		
	}
	
	public GestioneValidazioneService getGestioneValidazioneFacade() throws ServiceLocatorException {
		return (GestioneValidazioneService) getService(GestioneValidazioneServiceBean.class);		
	}
	public FatturePassiveService getFatturePassiveService() throws ServiceLocatorException {
		return (FatturePassiveService) getService(FatturePassiveServiceBean.class);		
	}
	public FattureAttiveService getFattureAttiveService() throws ServiceLocatorException {
		return (FattureAttiveService) getService(FattureAttiveServiceBean.class);		
	}
	public DocumentoService getDocumentoService() throws ServiceLocatorException {
		return (DocumentoService) getService(DocumentoServiceBean.class);
	}
	public GestionePropostaService getGestionePropostaService() throws ServiceLocatorException {
		return (GestionePropostaService) getService(GestionePropostaServiceBean.class);
	}
	public LdapService getLdapService() throws ServiceLocatorException {
		return (LdapService) getService(LdapServiceBean.class);
	}
	public BrevettiService getBrevettiService() throws ServiceLocatorException {
		return (BrevettiService) getService(BrevettiServiceBean.class);
	}
	public Configurator getConfigurator() throws ServiceLocatorException {
		return (Configurator) getService(Configurator.class);
	}
	public IndicizzatoreService getIndicizzatore() throws ServiceLocatorException {
		return (IndicizzatoreService) getService(IndicizzatoreServiceBean.class);
	}
}
