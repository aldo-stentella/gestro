package it.cnr.brevetti.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Contenitore generico di parametri da utilizzare come data transfer object.
 * Si tratta di una mappa con chiave di tipo String e valore di tipo Object.
 * Viene utilizzato principalmente come contenitore di parametri per le queries.

 * @author Aurelio D'Amico
 * @version 1.0 [22-Feb-2007]
 */
public class Parametri implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> map = new TreeMap<String, Object>();
	
	/** ritorna l'intera mappa dei parametri */
	public Map<String, Object> getMap() {
		return map;
	}
	/** aggiunge un parametro*/
	public void add(String key, Object value) {
		map.put(key, value);
	}
	/** aggiunge un range di date */
	public void addRange(String key, Date start, Date end) {
		map.put(key, new Date[]{start, end});
	}
	public Object get(String key) {
		return map.get(key);
	}
	public String[] getKeys() {
		if (map.keySet() == null) return null;
		Object[] temp = map.keySet().toArray();
		String[] keys = new String[temp.length];
		for (int i = 0; i < temp.length; i++) {
			keys[i] = (String)temp[i];
		}
		return keys;
	}
	public boolean isEmpty() {
		return map == null ? true : map.isEmpty();
	}	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		for(String key: map.keySet()) {
			if (builder.length() > 1) {
				builder.append(", ");
			}
			builder.append(key.toString());
			builder.append("=");
			builder.append(map.get(key) == null ? "null" : map.get(key).toString());
		}
		builder.append("]");
		return builder.toString();
	}
	public static Parametri getNewParametri() {
		return new Parametri();
	}
	public static Parametri getNewParametri(String key, Object value) {
		Parametri params = getNewParametri();
		params.add(key, value);
		return params;
	}	
}