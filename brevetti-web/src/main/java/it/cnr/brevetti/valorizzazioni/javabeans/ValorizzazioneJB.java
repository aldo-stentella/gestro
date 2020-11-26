package it.cnr.brevetti.valorizzazioni.javabeans;

import java.util.ArrayList;
import java.util.List;

import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.ejb.entities.Azienda;
import it.cnr.brevetti.ejb.entities.TipoValorizzazione;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.ejb.entities.Valorizzazione;
import it.cnr.brevetti.util.ServiceLocator;
import it.cnr.brevetti.util.ServiceLocatorException;

public class ValorizzazioneJB {

	private static ValorizzazioneJB instance;
	private static ServiceLocator locator;
	
	private ValorizzazioneJB() {
	}
	public static ValorizzazioneJB getInstance()  throws ServiceLocatorException{
		if (instance==null) {
			instance = new ValorizzazioneJB();
			locator = ServiceLocator.getInstance();
		}
		return instance;
	}
	public Valorizzazione getValorizzazione(Integer id) throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getValorizzazione(id);
	}
	public List<Valorizzazione> getValorizzazioni(Integer nsrif) throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getValorizzazioni(nsrif);
	}
	public Valorizzazione saveValorizzazione(Valorizzazione valorizzazione) throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().salvaValorizzazione(valorizzazione);
	}
	public List<Azienda> getAziende() throws ServiceLocatorException{	
		return locator.getGestioneTrovatiFacade().getAziende();
	}
	public Azienda getAzienda(Integer id) throws ServiceLocatorException{
		return locator.getGestioneTrovatiFacade().getAzienda(id);
	}
	public List<Trovato> getTrovati(Parametri p) throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getTrovati(p);
	}
	public List<TipoValorizzazione> getTipiValorizzazione() throws ServiceLocatorException {
		//TODO manca locator.getGestioneTrovatiFacade().getTipiValorizzazione()
		ArrayList<TipoValorizzazione> list = new ArrayList<TipoValorizzazione>();
		String[]nomi = {"","Diritti di opzione","NDA/MDTA","Cessione a inventori","Cessione a cotitolari","Cessione a terzi","Inserimento in vetrine","Valorizzazione tramite broker ","Licenza non esclusiva","Licenza esclusiva","Term Sheet for Cooperation and License","Manifestazioni di interesse","altro"};
		for(int i = 1; i<13; i++){
			TipoValorizzazione tv = new TipoValorizzazione();
			tv.setId(i);
			tv.setNome(nomi[i]);
			tv.setStatus(1);
			list.add(tv);
		}
		return locator.getGestioneTrovatiFacade().getTipiValorizzazione();
	}
}
