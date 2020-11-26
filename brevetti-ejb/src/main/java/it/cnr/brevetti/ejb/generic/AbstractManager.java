package it.cnr.brevetti.ejb.generic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Astrazione dei metodi di base per la gestione di una entity
 * @author Aurelio D'Amico
 * @version 1.0 [21-Nov-07]
 * @version 1.1 [03-Jan-08] + getResultList, getSingleResult override x nome query
 */
@SuppressWarnings("rawtypes") 
public abstract class AbstractManager {

	public abstract EntityManager getManager();
	
	// ====================================================================
	// conteggio generico
	// ====================================================================
	public Long count(Class clazz) {
		String sql = "select count(x) from " + clazz.getSimpleName() + " x";
		Query query = getManager().createQuery(sql);
		return (Long) query.getSingleResult();
	}

	// ====================================================================
	// query generica
	// ====================================================================
	public List findAll(Class clazz) {
		String sql = "SELECT x from " + clazz.getSimpleName() + " x";
		Query query = getManager().createQuery(sql);
		return query.getResultList();
	}

	/**
	 * ritorna la lista corrispondente alla named query indicata
	 * @param namedQuery - nome della query
	 * @return - collezione di entities
	 */
	protected List getList(String name) {
		Query query = getQuery(name);
		return query.getResultList();
	}
	protected List getList(String name, Object id) {
		return getResultList(name, id);
	}
	
	protected Query getQuery(String name) {
		return getManager().createNamedQuery(name);
	}

	/** ritorna null se il risultato della query è vuoto */
	protected List getResultList(Query query) {
		List list = query.getResultList();
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	/** indicare nome query e valore per parametro "id" */
	protected List getResultList(String name, Object id) {
		Query query = getQuery(name);
		query.setParameter("id", id);
		return getResultList(query);
	}

	/** ritorna null se il risultato della query è vuoto */
	protected List getResultList(String name) {
		return getResultList(getQuery(name));
	}
	/** ritorna null per NoResultException */
	protected Object getSingleResult(Query query) {
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	/** ritorna null per NoResultException */
	protected Object getSingleResult(String name) {
		return getSingleResult(getQuery(name));
	}
	// ========================================================
	//  C R U D
	// ========================================================
	
	protected Object persist(Object obj) {
		getManager().persist(obj);
		return obj;
	}
	
	protected Object merge(Object obj) {
		return getManager().merge(obj);
	}
	
	protected void remove(Object obj) {
		if (obj != null) {
			getManager().remove(obj);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected Object find(Class clazz, Object key) {
		return getManager().find(clazz, key);
	}
}