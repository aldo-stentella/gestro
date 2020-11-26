package it.cnr.brevetti.ejb.services;

import java.util.List;

import javax.ejb.EJB;

/**
 * Some basic methods
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Dec 14, 2015]
 *
 */
@SuppressWarnings("rawtypes")
public abstract class BasicAbstract {

	@EJB protected BrevettiService service;

	protected Object find(Class clazz, Object id) {
		return service.read(clazz, id);
	}
	protected List find(String sql) {
		return service.find(sql);
	}
	public List findAll(Class clazz) {
		return service.findAll(clazz);
	}	
	protected List findByQuery(String query) {
		return service.findByQuery(query);
	}
	protected List findByQuery(String query, Object id) {
		return service.findByQuery(query, id);
	}
	protected Object findObjectByQuery(String query, Object id) {
		return service.findObjectByQuery(query, id);
	}
	protected Object create(Object entity) {
		return service.create(entity);
	}
	protected Object update(Object entity) {
		return service.update(entity);
	}
	protected void delete(Object entity) {
		service.delete(entity);
	}
	protected List getList(String name, Object id) {
		return service.findByQuery(name,id);
	}
	protected void deleteAll(String q, Integer id) {
		service.executeQuery(q, id);	
	}
	protected void executeQuery(String query, Object id) {
		service.executeQuery(query, id);
	}
}
