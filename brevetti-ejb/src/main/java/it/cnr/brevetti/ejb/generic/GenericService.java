package it.cnr.brevetti.ejb.generic;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

@SuppressWarnings("rawtypes")
public interface GenericService {
	/** ritorna il manager associato al service bean di implementazione */
	EntityManager getManager();
	/** insert di una entità */
	Object create(Object entity);
	/** lettura di una entità */
	Object read(Class clazz, Object id);
	/** aggiornamento di una entità */
	Object update(Object entity);
	/** eliminazione di una entità */
	void delete(Object entity);
	/** conteggio di tutte le entità */
	Long count(Class clazz);
	/** ritorna tutte le entità */
	List findAll(Class clazz);
	/** trova un'entità in base alla select sql passata */
	List find(String sql);
	/** trova in base al nome di query passato */
	List findByQuery(String name);
	/** trova in base al nome di query passato + id canonico */	
	List findByQuery(String name, Object id);
	/** trova in base al nome di query passato + mappa di argomenti */	
	List findByQuery(String name, Map<String, Object> args);
	/** trova un'entità in base al nome di query passato + id canonico */	
	Object findObjectByQuery(String name, Object id);
	/** trova un singolo oggetto in base alla query passata + mappa di argomenti */
	Object findObjectByQuery(String name, Map<String, Object> args);
	/** esegue la query indicata impostando l'id passato */
	void executeQuery(String name, Object id);
	/** esegue la query indicata impostando gli argomenti passati */
	void executeQuery(String name, Map<String, Object> args);
}
