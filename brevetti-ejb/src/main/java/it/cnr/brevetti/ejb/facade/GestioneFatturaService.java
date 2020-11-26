package it.cnr.brevetti.ejb.facade;

import java.util.List;

import javax.ejb.Remote;

import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.ejb.entities.Fattura;
import it.cnr.brevetti.ejb.services.CommonInterface;

/**
 * Interfaccia servizio FACADE di gestione delle Fatture
 * @author Aurelio D'Amico
 * @version 1.0 [19-Jan-09]
 */
@Remote
@SuppressWarnings("rawtypes")
public interface GestioneFatturaService extends CommonInterface {
	Fattura getFattura(Integer id);
	Fattura getFattura(Long esercizio, String cds, String uo, Long pgFattura);
	Fattura salvaFattura(Fattura fattura);
	Fattura aggiornaFattura(Fattura fattura);
	void eliminaFattura(Integer id);
	List getFatture(Parametri params);
	List getCausali();
}
