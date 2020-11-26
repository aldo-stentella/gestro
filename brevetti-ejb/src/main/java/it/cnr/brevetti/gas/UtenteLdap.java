package it.cnr.brevetti.gas;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * classe wrapper per gli attributi LDAP riguardanti l'utente
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [20-Apr-07]
 * @version 1.1 [15-May-07] gestione campo CNRAPP4
 * @version 1.2 [27-Jul-07] fix di NPE in metodo parseEntry
 * @version 1.3 [30-Jul-07] aggiunto attributo CN
 * @version 1.4 [17-Jan-08] aggiunto attributo primaattivazioneeffettuata
 * @version 1.5 [13-Jun-08] aggiunto attributo codicifiscalivecchi
 * @version 1.6 [30-Jul-08] aggiunto attributo password 
 * @version 1.7 [19-Sep-08] aggiunto attibuti telefonocell, telephoneNumber, emailesterno 
 * @version 1.8 [25-Sep-08] aggiunto attibuto emailperpuk 
 * @version 1.9 [20-Nov-08] aggiunto attibuto mailForwardingAddress
 * @version 1.10 [5-Dic-08] aggiunto attibuto pukhash
 * @version 1.11 [27-Jan-08] aggiunto metodo getCodiciFiscali()
 */
public class UtenteLdap implements Serializable, Comparable<UtenteLdap> {
	private static final long serialVersionUID = 1L;
	// nomi valori
	public static final String RICHIESTA_PUK = "PR";
	public static final String INVIO_PUK = "PI";
	public static final String FORMATO_DATA_PUK = "/dd/MM/yyyy/HH/mm";
	public static final String ERRORE_INVIO_PUK = "ERROREINVIO";
	// nomi particolari
	public static final String DIPENDENTI = "dipendenti";
	public static final String NONDIPENDENTI = "nondipendenti";
	public static final String CESSATI = "AccountRapportoCessato";
	public static final String ISTITUZIONALI = "istituzionali";
	// nomi attributi interessanti
	public static final String UTENTE_ID = "uid";
	public static final String CODICE_FISCALE = "codicefiscale";
	public static final String NOME = "givenName";
	public static final String COGNOME = "sn";
	public static final String MATRICOLA = "matricola";
	public static final String EMAIL = "mail";
	public static final String DATA_CAMBIO_PASSWORD = "dataultimocambiopw";
	public static final String FREE_FIELD = "cnrapp4";
	public static final String CN = "cn";
	public static final String ATTIVAZIONE = "primaattivazioneeffettuata";
	public static final String CF_VECCHI = "codicifiscalivecchi";
	public static final String PASSWORD = "userPassword";
	public static final String CELLULARE = "telefonocell";
	public static final String TELEFONO = "telephoneNumber";
	public static final String EMAIL_ESTERNO = "emailesterno";
	public static final String EMAIL_X_PUK = "emailperpuk";
	public static final String FORWARD = "mailForwardingAddress";
	public static final String PUKHASH = "pukhash";
	// lista di attributi da leggere
	private static final String[] nomiAttributi = new String[]{
		UTENTE_ID, CODICE_FISCALE, NOME, COGNOME, MATRICOLA, EMAIL,
		DATA_CAMBIO_PASSWORD, FREE_FIELD, CN, ATTIVAZIONE, CF_VECCHI, 
		PASSWORD, CELLULARE, TELEFONO, EMAIL_ESTERNO, EMAIL_X_PUK, FORWARD,
		PUKHASH
	};
	// contiene gli attributi letti dal repository LDAP
	private LdapEntry entry;
	// contiene
	private String pukhash;
	private static final DateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss'N'"); 
	// ============================================================
	public UtenteLdap() {
	}
	public UtenteLdap(LdapEntry entry) {
		setEntry(entry);
	}
	// ============================================================
	public static String[] getNomiAttributi() {
		return nomiAttributi;
	}
	public LdapEntry getEntry() {
		return entry;
	}
	public void setEntry(LdapEntry entry) {
		this.entry = entry;
	}
	// ============================================================
	public String getDN() {
		return entry == null ? null : entry.getDN();
	}
	public String getCN() {
		return parseEntry(CN);
	}
	public String getUtenteId() {
		return parseEntry(UTENTE_ID);
	}
	public String getUid() {
		return parseEntry(UTENTE_ID);
	}
	public String getNome() {
		return parseEntry(NOME);
	}
	public String getCognome() {
		return parseEntry(COGNOME);
	}
	public String getCodiceFiscale() {
		return parseEntry(CODICE_FISCALE);
	}
	public Integer getMatricola() {
		String value = parseEntry(MATRICOLA);
		try {
			return new Integer(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	public String getEmail() {
		return parseEntry(EMAIL);
	}
	public Date getUltimoCambioPassword() {
		String value = parseEntry(DATA_CAMBIO_PASSWORD);
		try {
			return formatter.parse(value);
		} catch (ParseException e) {
			return null;
		}
	}
	// ver1.1
	public String getFreeField() {
		return parseEntry(FREE_FIELD);
	}	
	public void setFreeField(String value) {
		List<String> list = new ArrayList<String>();
		list.add(value);
		entry.put(FREE_FIELD, list);
	}
	// ver1.4
	public Boolean isPrimaAttivazioneEseguita() {
		String value = parseEntry(ATTIVAZIONE);
		if ("yes".equalsIgnoreCase(value))
			return true;
		else
			return false;		
	}
	// ver1.5
	public String[] getCFVecchi() {
		String value = parseEntry(CF_VECCHI);
		if (value == null || value.trim().length() == 0)
			return null;
		int pos = value.indexOf(',');
		if (pos < 0) return new String[] {value.trim()};
		String[] codici = value.split(",");
		for (int i = 0; i < codici.length; i++) {
			codici[i] = codici[i].trim();
		}
		return codici;
	}
	
	// ver1.6
	public String getPassword() {
		return parseEntry(PASSWORD);
	}

	// ver1.7
	public String getCellulare() {
		return parseEntry(CELLULARE);
	}
	public String getTelefono() {
		return parseEntry(TELEFONO);
	}
	public String getEmailEsterno() {
		return parseEntry(EMAIL_ESTERNO);
	}
	// ver1.8
	public String getEmailPerPuk() {
		return parseEntry(EMAIL_X_PUK);
	}
	// ver1.9
	public String getForward() {
		return parseEntry(FORWARD);
	}
	// ver1.10
	public String getPukhash() {
		return pukhash;
	}
	public void setPukhash(String pukhash) {
		this.pukhash = pukhash;
	}
	// ver1.11
	public List<String> getCodiciFiscali() {
		List<String> list = new ArrayList<String>();
		list.add(getCodiceFiscale().trim().toUpperCase());
		String[] codici = getCFVecchi();
		if (codici != null && codici.length > 0)
			for (int i = 0; i < codici.length; i++) {
				list.add(codici[i].trim().toUpperCase());
			}
		return list;
	}
	/* ---------------------------------------------------------------------- 
	 * i seguenti metodi gestiscono l'abilitazione o meno
	 * dell'utente che per contratto è stabilita dal campo
	 * CNRAPP4 impostato a "si" o "no"
	 */
	/**
	 * ritorna true se e solo se il campo CNRAPP4 risulta impostato a "si"
	 */
	public Boolean isAbilitato() {
		String value = parseEntry(FREE_FIELD);
		if ("si".equalsIgnoreCase(value))
			return true;
		else
			return false;
	}
	/** imposta il campo CNRAPP4 a "si" */
	public void abilitaUtente() {
		setFreeField("si");
	}
	/** imposta il campo CNRAPP4 a "no" */
	public void disabilitaUtente() {
		setFreeField("no");
	}
	// ---------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	private String parseEntry(String key) {
		StringBuilder sb = new StringBuilder();
		List<String> values = entry.get(key);
		// ver1.2
		if (null != values) {
			int i = 0;
			for (String value : values) {
				if (i > 0)
					sb.append(", ");
				sb.append(value);
				i++;
			}
		}
		return sb.toString();
	}
	@Override
	public String toString() {
		return getDN();
	}

	public int compareTo(UtenteLdap o) {
		return this.getDN().compareTo(o.getDN());
	}	
}