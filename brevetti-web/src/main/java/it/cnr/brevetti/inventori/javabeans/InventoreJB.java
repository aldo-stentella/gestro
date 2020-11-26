package it.cnr.brevetti.inventori.javabeans;


import it.cnr.brevetti.ejb.entities.DipendenteCnr;
import it.cnr.brevetti.ejb.entities.Inventore;
import it.cnr.brevetti.util.ServiceLocator;
import it.cnr.brevetti.util.ServiceLocatorException;

import java.util.List;

public class InventoreJB {
	private static InventoreJB instance;
	private static ServiceLocator locator;
	
	private InventoreJB(){
	}
	
	public static InventoreJB getInstance()  throws ServiceLocatorException{
		if (instance==null) {
			instance = new InventoreJB();
			locator = ServiceLocator.getInstance();
		}
		return instance;
	}
	
	public Inventore getInventoreByMatricola(Integer matricola)throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getInventoreByMatricola(matricola);
	}
	public DipendenteCnr getDipendenteByMatricola(Integer matricola) throws ServiceLocatorException {
		return 	locator.getGestioneTrovatiFacade().getDipendenteByMatricola(matricola);
	}
	@SuppressWarnings("unchecked")
	public List<Inventore> getInventori(String cognome)throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getInventori(cognome);
	}
	public Inventore getInventore(Integer id)throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getInventore(id);
	}

	public void saveInventore(Inventore inventore) throws ServiceLocatorException {
		locator.getGestioneTrovatiFacade().salvaInventore(inventore);
	}
	/*
	public Inventore getInventoreByID(Integer inventore) throws Exception{
		Inventore inventoreEntity = null;
		InitialContext jndiCntx = new InitialContext();

		InventoreService bean = (InventoreService) jndiCntx.lookup("brevetti/InventoreServiceBean/local");
		inventoreEntity=bean.readInventore(inventore);
		return inventoreEntity;
		}
	
	public List findAllInventori(){
		InitialContext jndiCntx;
		try {
			jndiCntx = new InitialContext();
			InventoreService bean = (InventoreService) jndiCntx.lookup("brevetti/InventoreServiceBean/local");
			return bean.findAllInventori();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return new ArrayList();
	}
	
	public Inventore findInventoreByMatricola(Integer matricola){
		Inventore inventoreEntity = null;
		try {
			InitialContext jndiCntx = new InitialContext();
			InventoreService bean;
			bean = (InventoreService) jndiCntx.lookup("brevetti/InventoreServiceBean/local");
			inventoreEntity=bean.findInventoreByMatricola(matricola);
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
		return inventoreEntity;
	}*/
}
