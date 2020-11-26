package it.cnr.brevetti.ejb.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import it.cnr.brevetti.ejb.entities.Validazione;
import it.cnr.brevetti.ejb.entities.XQuery;
import it.cnr.brevetti.ejb.services.BrevettiService;

@Stateless
public class GestioneValidazioneServiceBean implements GestioneValidazioneService {

	@EJB BrevettiService service;
	
	@SuppressWarnings("unchecked")
	public List<Validazione> getValidazioni(String actionForm) {
		return service.findByQuery(XQuery.FIND_VALIDAZIONI, actionForm);
	}

}
