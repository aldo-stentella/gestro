package it.cnr.brevetti.util;

import java.io.Serializable;
/**
 * Log del versionamento del lato web
 * @author aldo stentella <br />
 * [2015-06-26] creazione
 * [2015-10-14] bypass della cache contenuti jsp
 * [2015-10-30] evidenza aziende di spin-off
 */
public class Version implements Serializable {
	private static final long serialVersionUID = 1L;
	public static String versione = "$Revision: 549 $";
	public static String getVersion() {
		if (versione == null || versione.trim().length() == 0)
			return null;
		String temp = versione.replace('$', ' '); 
		int pos = temp.indexOf(':');
		if (pos < 0) return null;
		return temp.substring(pos + 1).trim();
	}
}