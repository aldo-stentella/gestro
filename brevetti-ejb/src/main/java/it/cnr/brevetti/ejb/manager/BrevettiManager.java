package it.cnr.brevetti.ejb.manager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.cnr.brevetti.ejb.generic.GenericManager;

/**
 * Classe di impostazione del manager di persistenza per brevetti
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [18-Nov-2008]
 */

public abstract class BrevettiManager extends GenericManager {
	@PersistenceContext(unitName = "brevetti")
	protected EntityManager manager;
	public EntityManager getManager() {
		return manager;
	}
}