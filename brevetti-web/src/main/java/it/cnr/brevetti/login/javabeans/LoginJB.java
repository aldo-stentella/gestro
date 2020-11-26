package it.cnr.brevetti.login.javabeans;

import it.cnr.brevetti.util.ServiceLocator;
import it.cnr.brevetti.util.ServiceLocatorException;
import it.cnr.brevetti.gas.SessioneUtente;


public class LoginJB {

	private static LoginJB instance;
	//private static ServiceLocator locator;
	private static ServiceLocator locator;
	
	private LoginJB(){
	}
	
	public static LoginJB getInstance()  throws ServiceLocatorException{
		if (instance==null) {
			instance = new LoginJB();
			//locator = ServiceLocator.getInstance();
			try {
				locator = ServiceLocator.getInstance();
			} catch (Exception e) {
				throw new ServiceLocatorException(e);
			}
		}
		return instance;
	}
	
	public boolean verificaUtente(String uid) throws ServiceLocatorException{
		try {
			return locator.getGestioneLoginFacade().verificaUtente(uid);
		} catch (Exception e) {
			throw new ServiceLocatorException(e);
		}
	}
	
	public boolean autenticaUtente(String uid, String password) throws ServiceLocatorException{
		try {
			return locator.getGestioneLoginFacade().autenticaUtente(uid, password);
		} catch (Exception e) {
			throw new ServiceLocatorException(e);
		}
	}
	public SessioneUtente getSessioneUtente(String uid, Integer appid) throws ServiceLocatorException{
		try {
			return locator.getGestioneLoginFacade().getSessioneUtente(uid);
		} catch (Exception e) {
			throw new ServiceLocatorException(e);
		}
	}
	public SessioneUtente getSessioneUtente(String uid, Integer appid, String struttura) throws ServiceLocatorException{
		try {
			return locator.getGestioneLoginFacade().getSessioneUtente(uid, struttura);
		} catch (Exception e) {
			throw new ServiceLocatorException(e);
		}
		//getGestioneLoginFacade().getSessioneUtente(uid, appid);
	}

}
