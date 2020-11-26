package it.cnr.brevetti.ejb.facade;

import java.util.List;

import javax.ejb.Remote;

import it.cnr.brevetti.ejb.entities.Validazione;

@Remote
public interface GestioneValidazioneService {
	public List<Validazione> getValidazioni(String actionForm);
}
