package it.cnr.brevetti.util;

import it.cnr.brevetti.config.Settings;

/**
 * Gestione centralizzata proprietà dell'applicazione
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [09-Nov-07]
 */
public class ApplicationProperties {
	public static final String PREFIX = "it.cnr.lookup.prefix";
	public static final String HOST = "it.cnr.host.name";
	private static ApplicationProperties instance;
	private Settings settings;
	
	private ApplicationProperties() {
		settings = Settings.getInstance();
	}	
	public static ApplicationProperties getInstance() {
		if (instance == null) instance = new ApplicationProperties();
		return instance;
	}
	/** ritorna il prefisso da aggiungere al lookup */
	public String getPrefix() {
		return System.getProperty(PREFIX);								
	}
	public String getHostName() {
		return System.getProperty(HOST);
	}
	/** ritorna il prefisso di root del distinguished name di LDAP */
	public String getRootDN() {
		return settings.get("it.cnr.brevetti.ldap.root");								
	}
	public String getRestUser() {
		return settings.get("it.cnr.brevetti.rest.user");
	}
	public String getSiglaLogin() {
		return settings.get("it.cnr.brevetti.sigla.login");
	}
	public String getSiglaUrl() {
		return settings.get("it.cnr.brevetti.sigla.url");		
	}
	public String getFatturaAttiva() {
		return settings.get("it.cnr.brevetti.sigla.fattura.attiva");		
	}
	public String getFatturaAttivaRiga() {
		return settings.get("it.cnr.brevetti.sigla.fattura.attiva.riga");		
	}
	public String getFatturaPassiva() {
		return settings.get("it.cnr.brevetti.sigla.fattura.passiva");		
	}
	public String getFatturaPassivaRiga() {
		return settings.get("it.cnr.brevetti.sigla.fattura.passiva.riga");		
	}
	public String getGasLogin() {
		return settings.get("it.cnr.brevetti.gas.login");
	}
	public String getGasUrl() {
		return settings.get("it.cnr.brevetti.gas.url");		
	}
	public String getGasSessione() {
		return settings.get("it.cnr.brevetti.gas.sessione");		
	}
	/**
	 *  webapp properties
	 */
	public String getCopyright() {
		return settings.get("it.cnr.brevetti.copyright");		
	}
	public String getDept() {
		return settings.get("it.cnr.brevetti.dept");		
	}
	public String getExternalNotifySystemUrl() {
		return settings.get("it.cnr.brevetti.externalNotifySystem.url");
	}
	public String getExternalNotifySystemStatus() {
		return settings.get("it.cnr.brevetti.externalNotifySystem.status");		
	}
	public String getExternalNotifySystemSilentMode() {
		return settings.get("it.cnr.brevetti.externalNotifySystem.silentMode");		
	}
	public String getExternalNotifySystemGUIMode() {
		return settings.get("it.cnr.brevetti.externalNotifySystem.GUIMode");		
	}
	public String getExternalNotifySystemLogin() {
		return settings.get("it.cnr.brevetti.externalNotifySystem.login");		
	}
	public String getJasperServerUrl() {
		return settings.get("it.cnr.brevetti.jasperserver.url");
	}
	public String getModuloInvenzioneUrl() {
		return settings.get("it.cnr.brevetti.moduloinvenzione.url");
	}
	public String getModuloMarchioUrl() {
		return settings.get("it.cnr.brevetti.modulomarchio.url");
	}
	public String getSearchEngineRootProd() {
		return settings.get("it.cnr.brevetti.SearchEngineRootProd");
	}
	public String getSearchEngineRootTest() {
		return settings.get("it.cnr.brevetti.SearchEngineRootTest");
	}	
	public String getSearchEngineLoginProd() {
		return settings.get("it.cnr.brevetti.SearchEngineLoginProd");
	}
	public String getSearchEngineLoginTest() {
		return settings.get("it.cnr.brevetti.SearchEngineLoginTest");
	}	
	public String getSearchEngineClickAnalysisAddress() {
		return settings.get("it.cnr.brevetti.SearchEngineClickAnalysisAddress");
	}	
	public String getSolrEngineRootProd() {
		return settings.get("it.cnr.brevetti.SolrEngineRootProd");
	}
	public String getSolrEngineRootTest() {
		return settings.get("it.cnr.brevetti.SolrEngineRootTest");
	}	
	public String getEncryptKey() {
		return settings.get("it.cnr.brevetti.encrypt.key");
	}
}
