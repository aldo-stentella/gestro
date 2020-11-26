package it.cnr.brevetti.ejb.facade;

import java.util.List;

import javax.ejb.Remote;

import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.ejb.entities.InventionDisclosure;
import it.cnr.brevetti.gas.UtenteLdap;

/**
 * Interfaccia servizio FACADE di gestione delle proposte (Invention Disclosure)
 * @author Aurelio D'Amico
 * @version 1.0 [11-Dec-15]
 */
@Remote
public interface GestionePropostaService {
	boolean login(String uid, String password);
	InventionDisclosure leggiProposta(Integer id);
	InventionDisclosure salvaProposta(InventionDisclosure indi);
	void eliminaProposta(InventionDisclosure indi);
	UtenteLdap getUtenteLdap(String uid);
	List<InventionDisclosure> leggiProposte(Parametri p);
}
