package it.cnr.brevetti.domain;

public class DocumentoQuery extends AbstractQuery {
	public final static String NSRIF = "y.key.nsrif";
	public final static String TIPO_DOCUMENTO = "x.tipoDocumentoId";
	public final static String ENTITA = "x.entita";
	
	private final static String SELECT = "SELECT DISTINCT x.documentoId from DocumentoInfo x, TrovatoDocumento y";
	private final static String ORDERED = " order by x.documentoId";
	
	public DocumentoQuery() {
		// categories
		equal = new String[] {
			NSRIF
			,TIPO_DOCUMENTO
			,ENTITA
		};
	}
	public String getQuery(Parametri p) {
		StringBuilder sb = new StringBuilder(SELECT);
		sb.append(WHERE);
		sb.append(buildSql(p));
		sb.append(" and x.documentoId = y.key.documentoId");
		sb.append(ORDERED);		
		return sb.toString();		
	}	
}
