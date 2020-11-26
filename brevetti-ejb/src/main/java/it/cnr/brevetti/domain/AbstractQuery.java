package it.cnr.brevetti.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import it.cnr.brevetti.util.Utile;

/**
 * Astrazione attributi e funzioni comuni
 * @author Aurelio D'Amico
 * @version 1.0 [22-Jan-09]
 */
public class AbstractQuery {
	public static final String IS_NULL = " IS NULL";
	public static final String IS_NOT_NULL = " IS NOT NULL";
	protected final static String APOS = "'";	
	protected final static String dateMask = "yyyyMMdd";
	protected final static DateFormat formatter = new SimpleDateFormat(dateMask);
	protected final static String WHERE = " WHERE ";
	protected final static String AND = " AND ";

	protected int index = 0;
	protected String[] equal;
	protected String[] like;
	protected String[] between;
	protected String[] in;
	
	protected StringBuilder addEqual(String key, Object value) {
		StringBuilder sb = new StringBuilder();
		if (isEmpty(value)) return sb;
		if (isNull(value)) return addNull(key, value);
		sb.append(key);
		sb.append('=');
		if (value instanceof String)
			sb.append(APOS + getString(value) + APOS);
		else
			sb.append(value.toString());
		return sb;
	}
	protected StringBuilder addLike(String key, Object value) {
		StringBuilder sb = new StringBuilder();
		if (isEmpty(value)) return sb;		
		if (isNull(value)) return addNull(key, value);
		sb.append("lower(" + key + ")");
		sb.append(" LIKE ");
		sb.append(APOS + value.toString().toLowerCase() + APOS);
		return sb;
	}
	protected StringBuilder addBetween(String key, Object value) {
		StringBuilder sb = new StringBuilder();
		if (isEmpty(value)) return sb;
		if (isNull(value)) return addNull(key, value);
		Date start, end = null;
		if (value instanceof Date) {
			start = end = (Date) value;
		} else if (value instanceof Date[]) {
			Date[] date = (Date[]) value;
			start = date[0];
			end = date[1];
		} else {
			return sb;
		}			
		sb.append(key);
		sb.append(" BETWEEN ");
		sb.append("TO_DATE('");
		sb.append(formatter.format(start));
		sb.append("','" + dateMask + "')");
		sb.append(AND);
		sb.append("TO_DATE('");
		sb.append(formatter.format(end));
		sb.append("','" + dateMask + "')");
		return sb;
	}
	protected StringBuilder addNull(String key, Object value) {
		StringBuilder sb = new StringBuilder();
		if (isEmpty(value)) return sb;
		sb.append(key);
		sb.append(value);
		return sb;
	}
	protected StringBuilder addIn(String key, Object value) {
	    StringBuilder sb = new StringBuilder();
	    if (isEmpty(value)) return sb;
	    if (isNull(value)) return addNull(key, value);
	    sb.append(key);
	    sb.append(" IN ");
	    if (value instanceof List<?>){
	        StringBuilder sb2 = new StringBuilder();
	        for(Object elem: (List<?>)value){
	            if(sb2.length()>0)
	                sb2.append(',');
	            if (elem instanceof String)
	                sb2.append(APOS + getString(elem) + APOS);
	            else
	                sb2.append(elem.toString());
	        }
	        sb.append('(');
	        sb.append(sb2);
	        sb.append(')');
	    } else if (value instanceof String)
	        sb.append('(' + APOS + getString(value) + APOS + ')');
	    else
	        sb.append('(' + value.toString() + ')');
	    return sb;
	}
	protected StringBuilder buildSql(Parametri p) {
		StringBuilder sb = new StringBuilder();
		String[] key = p.getKeys();
		for (int i = 0; i < key.length; i++) {
			sb.append(addParametro(key[i], p.get(key[i])));
		}
		return sb;
	}
	protected StringBuilder addParametro(String key, Object value) {
		StringBuilder sb = new StringBuilder();
		if (index > 0) sb.append(AND);
		if (find(key, equal))
			sb.append(addEqual(key, value));
		else if (find(key, like))
			sb.append(addLike(key, value));
		else if (find(key, between))
			sb.append(addBetween(key, value));
		else if (find(key, in))
			sb.append(addIn(key, value));
		index++;
		return sb;
	}
	protected boolean find(String what, String[] where) {
		if (what!=null && where!=null) {
			for (int i = 0; i < where.length; i++) {
				if (where[i].equals(what)) return true;
			}
		}
		return false;
	}
	protected boolean has(String what, String[] where) {
		if (what!=null && where!=null) {
			for (int i = 0; i < where.length; i++) {
				if (where[i].startsWith(what)) return true;
			}
		}
		return false;
	}

	protected boolean isEmpty(Object x) {
		if (x==null || x.toString().trim().length() == 0)
			return true;
		return false;
	}
	protected boolean isNotEmpty(Object x) {
		return !isEmpty(x);
	}
	protected boolean isNull(Object value) {
		if (!(value instanceof String)) return false;
		if (IS_NULL.equals(value.toString())) return true;
		if (IS_NOT_NULL.equals(value.toString())) return true;
		return false;
	}
	protected String getString(Object value) {
		String string = value.toString();
		if (string.contains(APOS)) {
			Utile.replace(string, APOS, APOS+APOS);
		}
		return string;
	}
}
