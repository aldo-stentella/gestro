package it.cnr.brevetti.domain;
/**
 * Classe factory per la generazione di una query di ricerca delle proposte in DB
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Jan 13, 2016]
 *
 */
public class PropostaQuery extends AbstractQuery {
	/*
	List<InventionDisclosure> leggiProposte(Parametri p)

	come parametri i campi dell'invention disclosure:
		data_trasmissione (range)
		NSRIF
		TIPI_TROVATO_ID
		UTENTE_LDAP
		STATO
	 */
	
	public final static String DATA_TRASMISSIONE = "x.dataTrasmissione";
	public final static String NSRIF = "x.nsrif";
	public final static String TIPI_TROVATO_ID = "x.tipiTrovatoId";
	public final static String UTENTE_LDAP = "x.utenteLdap";
	public final static String STATO = "x.stato";
	
	private final static String SELECT = "SELECT x from InventionDisclosure x";
	private final static String ORDERED = " order by x.id";
	
	public PropostaQuery() {
		// categories
		equal = new String[] {
			NSRIF
			,TIPI_TROVATO_ID
			,STATO
			,UTENTE_LDAP
		};
		between = new String[] {
			DATA_TRASMISSIONE
		};		
	}
	public String getQuery(Parametri p) {
		StringBuilder sb = new StringBuilder(SELECT);
		sb.append(WHERE);
		sb.append(buildSql(p));
		sb.append(ORDERED);		
		return sb.toString();		
	}	
}
