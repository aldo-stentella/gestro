package it.cnr.brevetti.gas;

import org.apache.commons.lang.StringUtils;

import it.cnr.brevetti.domain.Parametri;

/**
 * Costruisce una query da utilizzare per la ricerca degli utenti
 * in base ai parametri scelti
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [15-Jul-07]
 */
public class UtenteLdapQuery {
	private int index;
	protected Parametri params;	

	public UtenteLdapQuery(Parametri params) {
		this.params = params;
	}
	/** ritorna la query in base ai parametri passati */
	public String getQuery() {
		index = 0;
		StringBuilder sb = new StringBuilder();
		String string = (String) params.get(UserParam.CODICE_FISCALE);
		if (isNotBlank(string)) {
			sb.append(wrapAttribute(UtenteLdap.CODICE_FISCALE, string));
		}
		string = (String) params.get(UserParam.COGNOME);
		if (isNotBlank(string)) {
			sb.append(wrapAttribute(UtenteLdap.COGNOME, string));
		}
		string = (String) params.get(UserParam.MATRICOLA);
		if (isNotBlank(string)) {
			sb.append(wrapAttribute(UtenteLdap.MATRICOLA, string));
		}
		string = (String) params.get(UserParam.NOME);
		if (isNotBlank(string)) {
			sb.append(wrapAttribute(UtenteLdap.NOME, string));
		}		
		string = (String) params.get(UserParam.NOME_UTENTE);
		if (isNotBlank(string)) {
			sb.append(wrapAttribute(UtenteLdap.UTENTE_ID, string));
		}
		if (index > 1) return "(&" + sb.toString() + ")";
		return sb.toString();
	}
	
	private String wrapAttribute(String key, Object value) {
		String string = value.toString().replaceAll("%", "*");
		index++;
		return "(" + key + "=" + string + ")";
	}
	private boolean isNotBlank(String string) {
		return StringUtils.isNotBlank(string);
	}
}
