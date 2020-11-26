package it.cnr.brevetti.ejb.generic;


import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Classe astratta di persistenza
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [18-Nov-09]
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public abstract class GenericManager extends AbstractManager {

	public abstract EntityManager getManager();

	// ====================================================================
	// CRUD
	// ====================================================================

	public Object create(Object entity) {
		return persist(entity);
	}
	public Object read(Class clazz, Object id) {
		return find(clazz, id);
	}
	public Object update(Object entity) {
		return merge(entity);
	}
	public void delete(Object id) {
		remove(id);

	}

	/**
	 * ritorna una lista di entity che corrisponde alla query indicata
	 */
	public List find(String sql) {
		Query query = getManager().createQuery(sql);
		return getResultList(query);
	}
	/**
	 * ritorna una lista di entity che corrisponde ai parametri indicati
	 * 
	 * @param name
	 *            (nome della query)
	 * @args argomenti per la query nella forma identificativo, valore
	 */
	public List findByQuery(String name, Map<String, Object> args) {
		if (args == null || args.isEmpty()) return getResultList(name);
		return getResultList(getMappedQuery(name,args));
	}
	/**
	 * ritorna una lista di entity che corrisponde ai parametri indicati
	 * 
	 * @param name
	 *            (nome della query)
	 * @id singolo argomento di ricerca
	 */
	public List findByQuery(String name, Object id) {
		return getResultList(name, id);
	}
	/**
	 * ritorna una lista di entity che corrisponde ai parametri indicati
	 * 
	 * @param name
	 *            (nome della query)
	 */

	public List findByQuery(String name) {
		return getResultList(name);
	}
	/**
	 * ritorna un elemento di entity che corrisponde ai parametri indicati
	 * 
	 * @param name
	 *            (nome della query)
	 * @id singolo argomento di ricerca
	 */
	public Object findObjectByQuery(String name, Object id) {
		return getSingleResult(name, id);
	}
	/**
	 * ritorna un elemento di entity che corrisponde ai parametri indicati
	 * 
	 * @param name
	 *            (nome della query)
	 * @args argomenti per la query nella forma identificativo, valore
	 */
	public Object findObjectByQuery(String name, Map<String, Object> args) {
		if (args == null || args.isEmpty()) return getResultList(name);
		return getSingleResult(getMappedQuery(name, args));
	}
	public List executeNativeQuery(String sql) {
		Query query = getManager().createNativeQuery(sql);
		return query.getResultList();
	}
	public void executeQuery(String name, Object id) {
		Query query = getNamedQuery(name);
		query.setParameter("id", id);
		query.executeUpdate();		
	}

	public void executeQuery(String name, Map<String, Object> args) {
		Query query = getMappedQuery(name,args);
		query.executeUpdate();		
	}

	// ================================================================

	/**
	 * ritorna la lista corrispondente alla named query indicata
	 * 
	 * @param namedQuery
	 *            - nome della query
	 * @return - collezione di entities
	 */
	protected List getList(String queryName) {
		Query query = getNamedQuery(queryName);
		return query.getResultList();
	}
	protected Query getNamedQuery(String name) {
		return getManager().createNamedQuery(name);
	}
	/** ritorna null se il risultato della query è vuoto */
	protected List getResultList(Query query) {
		List list = query.getResultList();
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	/** ritorna null se il risultato della query è vuoto */
	protected List getResultList(String name) {
		return getResultList(getNamedQuery(name));
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
	protected Object getSingleResult(String queryName) {
		return getSingleResult(getNamedQuery(queryName));
	}
	/** indicare nome query e valore per parametro "id" */
	protected List getResultList(String name, Object id) {
		Query query = getNamedQuery(name);
		query.setParameter("id", id);
		return getResultList(query);
	}
	/** indicare nome query e valore per parametro "id" */
	protected Object getSingleResult(String name, Object id) {
		Query query = getNamedQuery(name);
		query.setParameter("id", id);
		return getSingleResult(query);
	}

	// ========================================================
	// C R U D
	// ========================================================

	protected Object persist(Object obj) {
		getManager().persist(obj);
		return obj;
	}
	protected Object merge(Object obj) {
		getManager().merge(obj);
		return obj;
	}
	protected void remove(Object obj) {
		if (obj != null) {
			getManager().remove(getManager().merge(obj));
		}
	}
	protected Object find(Class clazz, Object key) {
		return getManager().find(clazz, key);
	}

	// ========================================================
	// UTILITIES
	// ========================================================
	
	public static Map getIdArgument(Object id) {
		Map map = new Hashtable<String, Object>(1);
		map.put("id", id);
		return map;
	}
	private Query getMappedQuery(String name, Map<String, Object> args) {
		Query query = getNamedQuery(name);
		Set<String> keys = args.keySet();
		for (String key : keys) {
			query.setParameter(key, args.get(key));
		}
		return query;		
	}
}