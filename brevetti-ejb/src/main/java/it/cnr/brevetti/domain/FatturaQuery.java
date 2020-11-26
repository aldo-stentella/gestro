package it.cnr.brevetti.domain;

public class FatturaQuery extends AbstractQuery {

	public final static String FATTURA_ID = "fattura.id";
	public final static String DATA_FATTURA = "fattura.dataFattura";
	public final static String STUDI_BREVETTUALI_ID  = "fattura.studioBrevettualeId";
	public final static String PROTOCOLLO = "fattura.protocollo";
	public final static String NUM_FATTURA = "fattura.numFattura";
	public final static String IMPEGNO_OBBLIGAZIONE = "fattura.impegnoObbligazione";
	public final static String NSRIF = "voce.nsrif";
	public final static String DIPARTIMENTO_ID = "fattura.dipartimentoId";
		
	private final static String SELECT = "SELECT DISTINCT fattura from Fattura fattura";
//	private final static String SELECT = "SELECT fattura from Fattura fattura";
	private final static String ORDERED = " order by fattura.id";
	private final static String VOCE_JOIN = ", VoceFattura voce";
	private final static String VOCE_WHERE = " AND fattura.id = voce.fattureId"; 
	
	public FatturaQuery() {
		// categories
		equal = new String[] {
			FATTURA_ID
			,NUM_FATTURA
			,STUDI_BREVETTUALI_ID
			,PROTOCOLLO
			,NSRIF
			,DIPARTIMENTO_ID
		};
		like = new String[] {
			IMPEGNO_OBBLIGAZIONE
		};
		between = new String[] {
			DATA_FATTURA
		};
	}

	public String getQuery(Parametri p) {
		StringBuilder sb = new StringBuilder(SELECT);
	
		if (has("voce", p.getKeys()))
			sb.append(VOCE_JOIN);

		sb.append(WHERE);		
		
		sb.append(buildSql(p));

		if (has("voce", p.getKeys()))
			sb.append(VOCE_WHERE);
		
		sb.append(ORDERED);
		
		return sb.toString();
	}
}
