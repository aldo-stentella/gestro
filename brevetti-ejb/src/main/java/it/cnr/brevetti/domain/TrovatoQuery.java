package it.cnr.brevetti.domain;

import java.text.MessageFormat;

import it.cnr.brevetti.ejb.entities.TipoTitolareEnum;
import it.cnr.brevetti.util.Utile;


/**
 * Generatore di query per la ricerca avanzata del trovato
 * @author Aurelio D'Amico
 * @version 1.0 [14-Feb-08]
 * @version 1.1 [14-Feb-08] aggiunta query per dipartimento di afferenza
 * @version 1.2 [28-Ago-08] aggiunta order by
 */
public class TrovatoQuery extends AbstractQuery {
	// special
	public final static String DIGEST = "digest";	

	// trovato
	public final static String NSRIF = "trovato.nsrif";	
	public final static String TIPI_TROVATO_ID = "trovato.tipiTrovatoId";	
	public final static String TITOLO = "trovato.titolo";	
	public final static String TITOLO_INGLESE = "trovato.titoloInglese";	
	public final static String PAROLA_CHIAVE = "trovato.parolaChiave";	
	public final static String PAROLA_CHIAVE_INGLESE = "trovato.parolaChiaveInglese";
	public final static String CESSIONE_DIRITTI = "trovato.cessioneDiritti";
	public final static String DIPARTIMENTO_ID = "trovato.dipartimentiId";	
	public final static String UTENTI_ID = "trovato.utentiId";	
	public final static String PUBBLICATO = "trovato.pubblicato";	
	
	// TODO fix deposito
	public final static String NUM_DEPOSITO = "deposito.numeroDeposito";	
	public final static String DATA_DEPOSITO = "deposito.dataDeposito";	
	public final static String NUM_RILASCIO = "deposito.numRilascioDep";	
	public final static String DATA_RILASCIO = "deposito.dataRilascioDep";	
	public final static String DATA_ABBANDONO = "deposito.dataAbbandono";	
	public final static String DEPOSITO_PRIMO = "deposito.primo";	
	public final static String STUDI_BREVETTUALI_ID = "deposito.studioBrevettualeId";	
	
	// altro
	public final static String COGNOME = "inventore.cognome";	
	public final static String NOME_ENTE = "enteEsterno.nome";	
	public final static String ISTITUTI_ID = "istitutoTrovato.key.istitutiId";	
	public final static String DIPARTIMENTO_COTITOLARE_ID = "dipartimento.frkSoggettoId";	
	public static final String AFFERENZA_ID = "afferenza.key.dipartimentiId";
	public static final String CLASSIFICAZIONE_ID = "classificazione.classificazioniId";
	public static final String CODICE = "classificazione.codice";
	
	// sql
	private final static String SELECT = "SELECT DISTINCT trovato from Trovato trovato";
	private final static String ORDERED = " order by trovato.nsrif ";

	private final static String INVENTORE_JOIN = ", TrovatoInventore trovatoInventore, Inventore inventore";
	private final static String ISTITUTO_JOIN = ", IstitutoTrovato istitutoTrovato";
	private final static String DIPARTIMENTO_COTITOLARE_JOIN = ", Titolarita dipartimento";
	private final static String ENTE_ESTERNO_JOIN = ", Titolarita titolarita, EnteEsterno enteEsterno";
	private final static String AFFERENZA_JOIN = ", TrovatoDipartimento afferenza";
	private final static String CLASSIFICAZIONE_JOIN = ", ClassificazioneInternDep classificazioneInternDep, Classificazione classificazione";
	// TODO fix deposito
	private final static String DEPOSITO_JOIN = ", DepEst deposito";
	
	private final static String INVENTORE_WHERE = 
		" AND trovatoInventore.key.nsrif = trovato.nsrif" +  
		" AND trovatoInventore.key.inventoriId = inventore.id";
	private final static String ISTITUTO_WHERE = 
		" AND istitutoTrovato.key.nsrif = trovato.nsrif"; 
	private final static String DIPARTIMENTO_COTITOLARE_WHERE = 
		" AND dipartimento.nsrif = trovato.nsrif" + 
		" AND dipartimento.tipiTitolareId = " + TipoTitolareEnum.DIPARTIMENTO.ordinal(); 
	private final static String ENTE_ESTERNO_WHERE =
		" AND titolarita.nsrif = trovato.nsrif" + 
		" AND titolarita.frkSoggettoId = enteEsterno.id" + 
		" AND titolarita.tipiTitolareId = " + TipoTitolareEnum.ENTE_ESTERNO.ordinal();
	private final static String DEPOSITO_WHERE = 
		" AND deposito.nsrif = trovato.nsrif"; 
		//+ " AND deposito.primo = 1";
	private final static String AFFERENZA_WHERE = 
		" AND afferenza.key.nsrif = trovato.nsrif";
	private final static String CLASSIFICAZIONE_WHERE = 
		" AND classificazioneInternDep.nsrif = trovato.nsrif" +
		" AND classificazioneInternDep.classificazioniId  = classificazione.id"; 
	
	private final static String DIGEST_WHERE = 
		"({0}titolo like {1} OR" + 
		" {0}descrizione like {1} OR" + 
		" {0}usi like {1} OR" + 
		" {0}vantaggi like {1} OR" + 
		" {0}parolaChiave like {1} OR" + 
		" {0}titoloInglese like {1} OR" + 
		" {0}descrizioneInglese like {1} OR" + 
		" {0}usiInglese like {1} OR" + 
		" {0}vantaggiInglese like {1} OR" + 
		" {0}parolaChiaveInglese like {1})"; 
				
	public TrovatoQuery() {
		// categories
		equal = new String[] {
				NSRIF
				,TIPI_TROVATO_ID 
				,ISTITUTI_ID
				,DIPARTIMENTO_ID
				,AFFERENZA_ID
				,DEPOSITO_PRIMO
				,CESSIONE_DIRITTI
				,CLASSIFICAZIONE_ID
				,DIPARTIMENTO_COTITOLARE_ID
				,UTENTI_ID
				,PUBBLICATO
		};
		like = new String[] {
				TITOLO
				,NUM_DEPOSITO
				,NUM_RILASCIO			
				,TITOLO_INGLESE
				,PAROLA_CHIAVE
				,PAROLA_CHIAVE_INGLESE
				,NOME_ENTE
				,COGNOME
				,CODICE
		};
		between = new String[] {
				DATA_RILASCIO
				,DATA_DEPOSITO
				,DATA_ABBANDONO
		};
		in = new String[] {
				STUDI_BREVETTUALI_ID
		};
	}

	public String getQuery(Parametri p) {
		StringBuilder sb = new StringBuilder(SELECT);
//		if (isNotEmpty(p.get(NSRIF))) {
//			sb.append(WHERE);
//			sb.append(addParametro(NSRIF, p.get(NSRIF)));
//			return sb.toString();
//		}
		if (has("inventore", p.getKeys()))
			sb.append(INVENTORE_JOIN);
		if (has("istituto", p.getKeys()))
			sb.append((ISTITUTO_JOIN));
		if (has("dipartimento", p.getKeys()))
			sb.append(DIPARTIMENTO_COTITOLARE_JOIN);
		if (has("enteEsterno", p.getKeys()))
			sb.append(ENTE_ESTERNO_JOIN);
		if (has("deposito", p.getKeys()))
			sb.append(DEPOSITO_JOIN);
		if (has("afferenza", p.getKeys()))
			sb.append(AFFERENZA_JOIN);
		if (has("classificazione", p.getKeys()))
			sb.append(CLASSIFICAZIONE_JOIN);

		sb.append(WHERE);		
		
		sb.append(buildSql(p));
		
		/*if (has(DIGEST, p.getKeys()))
			sb.append(buildDigest(p));		*/
		
		if (has("inventore", p.getKeys()))
			sb.append(INVENTORE_WHERE);
		if (has("istituto", p.getKeys()))
			sb.append((ISTITUTO_WHERE));
		if (has("dipartimento", p.getKeys()))
			sb.append(DIPARTIMENTO_COTITOLARE_WHERE);
		if (has("enteEsterno", p.getKeys()))
			sb.append(ENTE_ESTERNO_WHERE);
		if (has("deposito", p.getKeys()))
			sb.append(DEPOSITO_WHERE);
		if (has("afferenza", p.getKeys()))
			sb.append(AFFERENZA_WHERE);
		if (has("classificazione", p.getKeys()))
			sb.append(CLASSIFICAZIONE_WHERE);
		
		sb.append(ORDERED);
		
		return sb.toString();
	}

	private StringBuilder buildDigest(Parametri p) {
		StringBuilder sb = new StringBuilder();
		String word = (String) p.get(DIGEST);
		if (Utile.isNotBlankOrNull(word)) {
			word = "'" + word + "'";
			sb.append(MessageFormat.format(DIGEST_WHERE, "trovato.", word));
		}
		return sb;
	}

	@Override
	protected StringBuilder addParametro(String key, Object value) {
		if(key.equalsIgnoreCase(DIGEST)){
			StringBuilder sb = new StringBuilder();
			String word = (String) value;
			if (Utile.isNotBlankOrNull(word)) {
				word = "'" + word + "'";
				if (index > 0) sb.append(AND);
				sb.append(MessageFormat.format(DIGEST_WHERE, "trovato.", word));
				index++;
			}
			return sb;
		}
		return super.addParametro(key, value);
	}
}