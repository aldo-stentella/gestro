package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
/**
 * Questa entità è fittizia. Essa viene utilizzata come collezione di named queries 
 * che possono essere definite solo all'interno di un entity bean.
 * L'utilità è quella di avere tutte le "named queries" (che sono più performanti)
 * centralizzate in un unico punto. In caso di modifiche ad una query sarà necessario
 * cercare solo in questa classe/documento. Inoltre le named queries vengono controllate
 * alla partenza durante il processo di caching intercettando eventuali errori a monte.
 */
@Entity
@NamedQueries({
	@NamedQuery(name = XQuery.FIND_CLASSIFICAZIONI_BY_LIVELLO_AND_TIPO, query = XQuery.FIND_CLASSIFICAZIONI_BY_LIVELLO_AND_TIPO_SQL),
	@NamedQuery(name = XQuery.FIND_CLASSIFICAZIONI_BY_LIVELLO, query = XQuery.FIND_CLASSIFICAZIONI_BY_LIVELLO_SQL),
	@NamedQuery(name = XQuery.FIND_CLASSIFICAZIONI_CHILDREN, query = XQuery.FIND_CLASSIFICAZIONI_CHILDREN_SQL),
	@NamedQuery(name = XQuery.FIND_DENOMINAZIONI_STUDI_BREVETTUALI, query = XQuery.FIND_DENOMINAZIONI_STUDI_BREVETTUALI_SQL),
	@NamedQuery(name = XQuery.FIND_SEDI_STUDI_BREVETTUALI, query = XQuery.FIND_SEDI_STUDI_BREVETTUALI_SQL),
	@NamedQuery(name = XQuery.FIND_ISTITUTI, query = XQuery.FIND_ISTITUTI_SQL),
	@NamedQuery(name = XQuery.FIND_INVENTORE_BY_MATRICOLA, query = XQuery.FIND_INVENTORE_BY_MATRICOLA_SQL),
	@NamedQuery(name = XQuery.FIND_INVENTORI_BY_COGNOME, query = XQuery.FIND_INVENTORI_BY_COGNOME_SQL),
	@NamedQuery(name = XQuery.FIND_ISTITUTI_BY_NSRIF, query = XQuery.FIND_ISTITUTI_BY_NSRIF_SQL),
	@NamedQuery(name = XQuery.FIND_INVENTORI_BY_NSRIF, query = XQuery.FIND_INVENTORI_BY_NSRIF_SQL),
	@NamedQuery(name = XQuery.FIND_DEP_EST_BY_NSRIF, query = XQuery.FIND_DEP_EST_BY_NSRIF_SQL),
	@NamedQuery(name = XQuery.FIND_CLASSIFICAZIONI_INTERN_DEP_BY_NSRIF, query = XQuery.FIND_CLASSIFICAZIONI_INTERN_DEP_BY_NSRIF_SQL),
	@NamedQuery(name = XQuery.FIND_TITOLARITA_BY_NSRIF, query = XQuery.FIND_TITOLARITA_BY_NSRIF_SQL),
	@NamedQuery(name = XQuery.FIND_INVENTORE_RIFERIMENTO, query = XQuery.FIND_INVENTORE_RIFERIMENTO_SQL),
	@NamedQuery(name = XQuery.DELETE_ISTITUTI_TROVATI, query = XQuery.DELETE_ISTITUTI_TROVATI_SQL),
	@NamedQuery(name = XQuery.DELETE_TROVATI_INVENTORI, query = XQuery.DELETE_TROVATI_INVENTORI_SQL),
	@NamedQuery(name = XQuery.DELETE_CLASSIFICAZIONI_INTERNAZIONALI, query = XQuery.DELETE_CLASSIFICAZIONI_INTERNAZIONALI_SQL),
	@NamedQuery(name = XQuery.DELETE_TITOLARITA, query = XQuery.DELETE_TITOLARITA_SQL),
	@NamedQuery(name = XQuery.FIND_TIPI_REGIONAL_PATENT_BY_PCT, query = XQuery.FIND_TIPI_REGIONAL_PATENT_BY_PCT_SQL),
	@NamedQuery(name = XQuery.FIND_STATI_BY_DEP_EST, query = XQuery.FIND_STATI_BY_DEP_EST_SQL),
	@NamedQuery(name = XQuery.DELETE_PCT_TIPI_REGIONAL_PATENT, query = XQuery.DELETE_PCT_TIPI_REGIONAL_PATENT_SQL),
	@NamedQuery(name = XQuery.FIND_ALL_STUDI_BREVETTUALI_SORTED, query = XQuery.FIND_ALL_STUDI_BREVETTUALI_SORTED_SQL),
	@NamedQuery(name = XQuery.FIND_DIPENDENTE_BY_MATRICOLA, query = XQuery.FIND_DIPENDENTE_BY_MATRICOLA_SQL),
	@NamedQuery(name = XQuery.FIND_ALL_STATI_SORTED, query = XQuery.FIND_ALL_STATI_SORTED_SQL),
	@NamedQuery(name = XQuery.FIND_VOCI_FATTURA_BY_FATTURA_ID, query = XQuery.FIND_VOCI_FATTURA_BY_FATTURA_ID_SQL),
	@NamedQuery(name = XQuery.FIND_VALIDAZIONI, query = XQuery.FIND_VALIDAZIONI_SQL),
	@NamedQuery(name = XQuery.COUNT_TROVATI_BY_DIPARTIMENTO, query = XQuery.COUNT_TROVATI_BY_DIPARTIMENTO_SQL),
	@NamedQuery(name = XQuery.COUNT_TROVATI_BY_CLASSIFICAZIONE, query = XQuery.COUNT_TROVATI_BY_CLASSIFICAZIONE_SQL),
	@NamedQuery(name = XQuery.COUNT_TROVATI_BY_CLASSIFICAZIONE_SUBTREE, query = XQuery.COUNT_TROVATI_BY_CLASSIFICAZIONE_SUBTREE_SQL),
	@NamedQuery(name = XQuery.FIND_TROVATI_BY_UO, query = XQuery.FIND_TROVATI_BY_UO_SQL),
	@NamedQuery(name = XQuery.FIND_FATTURA_ID, query = XQuery.FIND_FATTURA_ID_SQL),
	@NamedQuery(name = XQuery.FIND_STORICO_TITOLARITA, query = XQuery.FIND_STORICO_TITOLARITA_SQL),
	@NamedQuery(name = XQuery.DELETE_STORICO_TITOLARITA, query = XQuery.DELETE_STORICO_TITOLARITA_SQL),
	@NamedQuery(name = XQuery.DELETE_DEPEST_STATI, query = XQuery.DELETE_DEPEST_STATI_SQL),
	@NamedQuery(name = XQuery.FIND_TROVATO_DIPARTIMENTO, query = XQuery.FIND_TROVATO_DIPARTIMENTO_SQL),
	@NamedQuery(name = XQuery.FIND_VALORIZZAZIONI_BY_NSRIF, query = XQuery.FIND_VALORIZZAZIONI_BY_NSRIF_SQL),
	@NamedQuery(name = XQuery.FIND_TROVATI_BY_VALID, query = XQuery.FIND_TROVATI_BY_VALID_SQL),
	@NamedQuery(name = XQuery.DELETE_TROVATI_VALORIZZAZIONI, query = XQuery.DELETE_TROVATI_VALORIZZAZIONI_SQL),
	@NamedQuery(name = XQuery.FIND_TROVATI_BY_DOCID, query = XQuery.FIND_TROVATI_BY_DOCID_SQL),
	@NamedQuery(name = XQuery.DELETE_TROVATI_DOCUMENTI_BY_NSRIF, query = XQuery.DELETE_TROVATI_DOCUMENTI_BY_NSRIF_SQL),
	@NamedQuery(name = XQuery.DELETE_TROVATI_DOCUMENTI_BY_DOCID, query = XQuery.DELETE_TROVATI_DOCUMENTI_BY_DOCID_SQL),
	@NamedQuery(name = XQuery.FIND_TROVATI_DOCUMENTI_BY_NSRIF, query = XQuery.FIND_TROVATI_DOCUMENTI_BY_NSRIF_SQL),
	@NamedQuery(name = XQuery.COUNT_TROVATI_DOCUMENTI_BY_DOCID, query = XQuery.COUNT_TROVATI_DOCUMENTI_BY_DOCID_SQL),
	@NamedQuery(name = XQuery.FIND_TROVATO_DOCUMENTO, query = XQuery.FIND_TROVATO_DOCUMENTO_SQL),
	@NamedQuery(name = XQuery.FIND_DOCUMENTI_BY_PROPOSTA, query = XQuery.FIND_DOCUMENTI_BY_PROPOSTA_SQL),
	@NamedQuery(name = XQuery.DELETE_PROPOSTE_DOCUMENTI, query = XQuery.DELETE_PROPOSTE_DOCUMENTI_SQL),
	@NamedQuery(name = XQuery.FIND_TRANSIZIONI_BY_PROPOSTA, query = XQuery.FIND_TRANSIZIONI_BY_PROPOSTA_SQL),
	@NamedQuery(name = XQuery.DELETE_TRANSIZIONI_BY_PROPOSTA, query = XQuery.DELETE_TRANSIZIONI_BY_PROPOSTA_SQL),
	@NamedQuery(name = XQuery.FIND_TROVATI_BY_VERBALE, query = XQuery.FIND_TROVATI_BY_VERBALE_SQL),
	@NamedQuery(name = XQuery.FIND_VERBALI_BY_NSRIF, query = XQuery.FIND_VERBALI_BY_NSRIF_SQL),
	@NamedQuery(name = XQuery.DELETE_TROVATI_VERBALI, query = XQuery.DELETE_TROVATI_VERBALI_SQL),
	
})
public class XQuery implements Serializable {
	// costanti
	public final static int FIRST_RESULT = 0;
	public final static int MAX_RESULTS = 15;
	public static final long serialVersionUID = 1L;
	
	@Id // fake (una entity richiede sempre un identificativo)
	private int id;
	
	// names	
	public final static String FIND_CLASSIFICAZIONI_BY_LIVELLO = "FIND_CLASSIFICAZIONI_BY_LIVELLO";
	public final static String FIND_CLASSIFICAZIONI_CHILDREN = "FIND_CLASSIFICAZIONI_CHILDREN";
	public final static String FIND_DENOMINAZIONI_STUDI_BREVETTUALI = "FIND_DENOMINAZIONI_STUDI_BREVETTUALI";
	public final static String FIND_SEDI_STUDI_BREVETTUALI = "FIND_SEDI_STUDI_BREVETTUALI";
	public final static String FIND_ISTITUTI = "FIND_ISTITUTI";
	public final static String FIND_INVENTORI_BY_COGNOME = "FIND_INVENTORI_BY_COGNOME";
	public final static String FIND_DIPENDENTE_BY_MATRICOLA = "FIND_DIPENDENTE_BY_MATRICOLA";
	public final static String FIND_INVENTORE_BY_MATRICOLA = "FIND_INVENTORE_BY_MATRICOLA";
	public final static String FIND_ISTITUTI_BY_NSRIF = "FIND_ISTITUTI_BY_NSRIF";
	public final static String FIND_INVENTORI_BY_NSRIF = "FIND_INVENTORI_BY_NSRIF";
	public final static String FIND_DEP_EST_BY_NSRIF = "FIND_DEPOSITI_BY_NSRIF";
	public final static String FIND_CLASSIFICAZIONI_INTERN_DEP_BY_NSRIF = "FIND_CLASSIFICAZIONI_INTERN_DEP_BY_NSRIF";
	public final static String FIND_ESTENSIONE_BY_NSRIF = "FIND_ESTENSIONE_BY_NSRIF";
	public final static String FIND_TITOLARITA_BY_NSRIF = "FIND_TITOLARITA_BY_NSRIF";
	public final static String FIND_INVENTORE_RIFERIMENTO = "FIND_INVENTORE_RIFERIMENTO";
	public final static String DELETE_ISTITUTI_TROVATI = "DELETE_ISTITUTI_TROVATI";
	public final static String DELETE_TROVATI_INVENTORI = "DELETE_TROVATI_INVENTORI";
	public final static String DELETE_CLASSIFICAZIONI_INTERNAZIONALI = "DELETE_CLASSIFICAZIONI_INTERNAZIONALI";
	public final static String DELETE_TITOLARITA = "DELETE_TITOLARITA";
	public final static String FIND_TIPI_REGIONAL_PATENT_BY_PCT = "FIND_TIPI_REGIONAL_PATENT_BY_PCT";
	public final static String FIND_STATI_BY_DEP_EST = "FIND_STATI_BY_DEP_EST";
	public final static String FIND_AZIENDE_BY_ESTENSIONE = "FIND_AZIENDE_BY_ESTENSIONE";
	public final static String DELETE_PCT_TIPI_REGIONAL_PATENT = "DELETE_PCT_TIPI_REGIONAL_PATENT";
	public final static String FIND_ALL_STUDI_BREVETTUALI_SORTED = "FIND_ALL_STUDI_BREVETTUALI_SORTED";
	public final static String FIND_ALL_STATI_SORTED = "FIND_ALL_STATI_SORTED";
	public final static String FIND_VOCI_FATTURA_BY_FATTURA_ID = "FIND_VOCI_FATTURA_BY_FATTURA_ID";
	public final static String FIND_VALIDAZIONI = "FIND_VALIDAZIONI";
	public final static String COUNT_TROVATI_BY_DIPARTIMENTO = "COUNT_TROVATI_BY_DIPARTIMENTO";
	public final static String COUNT_TROVATI_BY_CLASSIFICAZIONE = "COUNT_TROVATI_BY_CLASSIFICAZIONE";
	public final static String COUNT_TROVATI_BY_CLASSIFICAZIONE_SUBTREE = "COUNT_TROVATI_BY_CLASSIFICAZIONE_SUBTREE";
	public static final String FIND_CLASSIFICAZIONI_BY_LIVELLO_AND_TIPO = "FIND_CLASSIFICAZIONI_BY_LIVELLO_AND_TIPO";	
	public static final String FIND_TROVATI_BY_UO = "FIND_TROVATI_BY_UO";	
	public static final String FIND_FATTURA_ID = "FIND_FATTURA_ID";	
	public static final String FIND_STORICO_TITOLARITA = "FIND_STORICO_TITOLARITA";	
	public final static String DELETE_STORICO_TITOLARITA = "DELETE_STORICO_TITOLARITA";
	public final static String DELETE_DEPEST_STATI = "DELETE_DEPEST_STATI";
	public final static String FIND_TROVATO_DIPARTIMENTO = "FIND_TROVATO_DIPARTIMENTO";
	public final static String FIND_VALORIZZAZIONI_BY_NSRIF = "FIND_VALORIZZAZIONI_BY_NSRIF";
	public final static String FIND_TROVATI_BY_VALID = "FIND_TROVATI_BY_VALID";
	public final static String DELETE_TROVATI_VALORIZZAZIONI = "DELETE_TROVATI_VALORIZZAZIONI";
	public final static String FIND_TROVATI_BY_DOCID = "FIND_TROVATI_BY_DOCID";
	public final static String FIND_TROVATI_DOCUMENTI_BY_NSRIF = "FIND_TROVATI_DOCUMENTI_BY_NSRIF";
	public final static String DELETE_TROVATI_DOCUMENTI_BY_NSRIF = "DELETE_TROVATI_DOCUMENTI_BY_NSRIF";
	public final static String DELETE_TROVATI_DOCUMENTI_BY_DOCID = "DELETE_TROVATI_DOCUMENTI_BY_DOCID";
	public final static String COUNT_TROVATI_DOCUMENTI_BY_DOCID = "COUNT_TROVATI_DOCUMENTI_BY_DOCID";
	public final static String FIND_TROVATO_DOCUMENTO = "FIND_TROVATO_DOCUMENTO";
	public final static String FIND_DOCUMENTI_BY_PROPOSTA = "FIND_DOCUMENTI_BY_PROPOSTA";
	public final static String DELETE_PROPOSTE_DOCUMENTI = "DELETE_PROPOSTE_DOCUMENTI";	
	public final static String FIND_TRANSIZIONI_BY_PROPOSTA = "FIND_TRANSIZIONI_BY_PROPOSTA";
	public final static String DELETE_TRANSIZIONI_BY_PROPOSTA = "DELETE_TRANSIZIONI_BY_PROPOSTA";
	public final static String FIND_TROVATI_BY_VERBALE = "FIND_TROVATI_BY_VERBALE";
	public static final String FIND_VERBALI_BY_NSRIF = "FIND_VERBALI_BY_NSRIF";
	public static final String DELETE_TROVATI_VERBALI = "DELETE_TROVATI_VERBALI";
	
	// queries
	public static final String FIND_CLASSIFICAZIONI_BY_LIVELLO_SQL = 
		"select x from Classificazione x where x.livello = :id";	
	
	public static final String FIND_CLASSIFICAZIONI_BY_LIVELLO_AND_TIPO_SQL = 
		FIND_CLASSIFICAZIONI_BY_LIVELLO_SQL + " and x.tipo = :tipo";	
	
	public static final String FIND_CLASSIFICAZIONI_CHILDREN_SQL = 
		"select x from Classificazione x where x.idPadre = :id";	
	
	public static final String FIND_DENOMINAZIONI_STUDI_BREVETTUALI_SQL = 
		"select distinct x.denominazione from StudioBrevettuale x";	
	
	public static final String FIND_SEDI_STUDI_BREVETTUALI_SQL = 
		"select x from StudioBrevettuale x WHERE x.denominazione =:id";	

	public static final String FIND_ISTITUTI_SQL = 
		"select x from Istituto x where x.dataFine IS NULL";	

	public static final String FIND_INVENTORI_BY_COGNOME_SQL = 
		"select x from Inventore x where upper(x.cognome) like :id AND " + 
		"(x.matricolaCnr IS NULL OR x.matricolaCnr = 0)";	

	public static final String FIND_INVENTORE_BY_MATRICOLA_SQL = 
		"select x from Inventore x where x.matricolaCnr =:id";	

	public static final String FIND_DIPENDENTE_BY_MATRICOLA_SQL = 
		"select x from DipendenteCnr x where x.matricolaCnr =:id";	

	public static final String FIND_ISTITUTI_BY_NSRIF_SQL = 
		"select x from Istituto x, IstitutoTrovato y " +
	    "where x.id = y.key.istitutiId and y.key.nsrif = :id";	

	public static final String FIND_INVENTORI_BY_NSRIF_SQL = 
		"select x from Inventore x, TrovatoInventore y " +
	    "where x.id = y.key.inventoriId and y.key.nsrif = :id";	

	public static final String FIND_DEP_EST_BY_NSRIF_SQL = 
		"select x from DepEst x where x.nsrif = :id order by x.dataDeposito";	

	public static final String FIND_CLASSIFICAZIONI_INTERN_DEP_BY_NSRIF_SQL = 
		"select x from ClassificazioneInternDep x where x.nsrif = :id";	

	public static final String FIND_TITOLARITA_BY_NSRIF_SQL = 
		"select x from Titolarita x where x.nsrif = :id";	

	public static final String FIND_INVENTORE_RIFERIMENTO_SQL = 
		"select x from TrovatoInventore x where x.key.nsrif = :id and x.inventoreRiferimento = 1";
	
	public static final String DELETE_ISTITUTI_TROVATI_SQL = 
		"delete from IstitutoTrovato x where x.key.nsrif = :id";

	public static final String DELETE_TROVATI_INVENTORI_SQL = 
		"delete from TrovatoInventore x where x.key.nsrif = :id";
	
	public static final String DELETE_CLASSIFICAZIONI_INTERNAZIONALI_SQL = 
		"delete from ClassificazioneInternDep x where x.nsrif = :id";

	public static final String DELETE_TITOLARITA_SQL = 
		"delete from Titolarita x where x.nsrif = :id";
	
	public static final String FIND_TIPI_REGIONAL_PATENT_BY_PCT_SQL =
		"select x from TipoPctRegionalPatent x, PctTipoRegionalPatent y " + 
		"where x.id = y.key.tipoId and y.key.pctId = :id " +
		"order by x.id";

	public static final String FIND_STATI_BY_DEP_EST_SQL =
		"select x from Stato x, DepEstStato y " + 
		"where x.id = y.key.statoId and y.key.depEstId = :id " +
		"order by x.id";
		
	public static final String DELETE_PCT_TIPI_REGIONAL_PATENT_SQL = 
		"delete from PctTipoRegionalPatent x where x.key.pctId = :id";

	public static final String FIND_ALL_STUDI_BREVETTUALI_SORTED_SQL =
		"select x from StudioBrevettuale x " +
		"where x.status = 1 " +
		"order by x.denominazione";
	
	public static final String FIND_ALL_STATI_SORTED_SQL =
		"select x from Stato x order by x.nome";
	
	public static final String FIND_VOCI_FATTURA_BY_FATTURA_ID_SQL = 
		"select x from VoceFattura x where x.fattureId = :id order by x.id";

	public static final String FIND_VALIDAZIONI_SQL = 
		"select x from Validazione x where x.actionform = :id order by x.id";
	
	// TODO fix deposito
	public static final String COUNT_TROVATI_BY_DIPARTIMENTO_SQL = 
		"select count(distinct x.nsrif), x.dipartimentiId " +
		"from Trovato x, DepEst d where " +
		"x.nsrif = d.nsrif and d.dataAbbandono is null " +
		"and x.dipartimentiId is not null " +
		"group by x.dipartimentiId";	

	// TODO fix deposito
	public static final String COUNT_TROVATI_BY_CLASSIFICAZIONE_SQL = 
		"select count(distinct x.nsrif), c.id " +
		"from Trovato x, DepEst d, " +
		"Classificazione c, ClassificazioneInternDep z where " +
		"x.nsrif = d.nsrif and d.dataAbbandono is null " +
		"and x.nsrif = z.nsrif " +
		"and c.id = z.classificazioniId " +
		"group by c.id";
	
	// TODO fix deposito
	public static final String COUNT_TROVATI_BY_CLASSIFICAZIONE_SUBTREE_SQL = 
		"select count(distinct x.nsrif) " +
		"from Trovato x, DepEst d, " +
		"Classificazione c, ClassificazioneInternDep z where " +
		"x.nsrif = d.nsrif and d.dataAbbandono is null " +
		"and x.nsrif = z.nsrif " +
		"and c.id = z.classificazioniId " +
		"and c.codice like :id ";

	public static final String FIND_TROVATI_BY_UO_SQL = 
		"select x from Trovato x," +
		"Dipartimento d, TrovatoDipartimento t " +
		"where x.nsrif = t.key.nsrif " +
		"and d.id = t.key.dipartimentiId " + 
		"and d.cdUo = :id " + 
		"order by x.nsrif";		

	public static final String FIND_FATTURA_ID_SQL = 
		"select distinct v.fattureId from " +
		"VoceFatturaSigla s, VoceFattura v " +
		"where s.id = v.id " +
		"and s.cdCds = :cds " + 
		"and s.cdUnitaOrganizzativa = :uo " + 
		"and s.esercizio = :esercizio " + 
		"and s.pgFatturaPassiva = :pgFattura ";

	public static final String FIND_STORICO_TITOLARITA_SQL = 
		"select x from StoricoTitolarita x " +
		"where x.depEstId = :id " +
		"order by x.id";		

	public static final String DELETE_STORICO_TITOLARITA_SQL = 
		"delete from StoricoTitolarita x where x.depEstId = :id";
		
	public static final String DELETE_DEPEST_STATI_SQL = 
		"delete from DepEstStato x where x.key.depEstId = :id";
			
	public static final String FIND_TROVATO_DIPARTIMENTO_SQL = 
		"select x from TrovatoDipartimento x where x.key.nsrif = :id";

	public static final String FIND_VALORIZZAZIONI_BY_NSRIF_SQL = 
		"select x from Valorizzazione x, TrovatoValorizzazione y " + 
		"where x.id = y.key.valid " +
		"and y.key.nsrif = :id";

	public static final String FIND_TROVATI_BY_VALID_SQL = 
		"select x from Trovato x, TrovatoValorizzazione y " + 
		"where x.nsrif = y.key.nsrif " +
		"and y.key.valid = :id";

	public static final String DELETE_TROVATI_VALORIZZAZIONI_SQL = 
		"delete from TrovatoValorizzazione x where x.key.valid = :id";
	
	public static final String DELETE_TROVATI_DOCUMENTI_BY_NSRIF_SQL = 
		"delete from TrovatoDocumento x where x.key.nsrif = :id";

	public static final String DELETE_TROVATI_DOCUMENTI_BY_DOCID_SQL = 
		"delete from TrovatoDocumento x where x.key.documentoId = :id";

	public static final String FIND_TROVATI_BY_DOCID_SQL = 
		"select x from Trovato x, TrovatoDocumento y " + 
		"where x.nsrif = y.key.nsrif " +
		"and y.key.documentoId = :id";
	
	public static final String FIND_TROVATI_DOCUMENTI_BY_NSRIF_SQL =
		"select x.key.documentoId from TrovatoDocumento x where x.key.nsrif = :id";

	public static final String COUNT_TROVATI_DOCUMENTI_BY_DOCID_SQL =
		"select count(x.key.nsrif) from TrovatoDocumento x where x.key.documentoId = :id";

	public static final String FIND_TROVATO_DOCUMENTO_SQL =
		"select x from TrovatoDocumento x where x.key.nsrif = :nsrif and x.key.documentoId = :id";

	public static final String FIND_DOCUMENTI_BY_PROPOSTA_SQL = 
		"select x from DocumentoInfo x, InventionDisclosureDocumento y " + 
		"where x.id = y.key.documentoId " +
		"and y.key.inventionDisclosureId = :id";

	public static final String DELETE_PROPOSTE_DOCUMENTI_SQL = 
		"delete from InventionDisclosureDocumento x where x.key.inventionDisclosureId = :id";
		
	public static final String FIND_TRANSIZIONI_BY_PROPOSTA_SQL = 
		"select x from IdTransition x where x.inventionDisclosure = :id order by x.dataTransizione";

	public static final String DELETE_TRANSIZIONI_BY_PROPOSTA_SQL = 
		"delete from IdTransition x where x.inventionDisclosure = :id";
	
	public static final String FIND_TROVATI_BY_VERBALE_SQL = 
			"select x from Trovato x, TrovatoVerbale y " + 
			"where x.nsrif = y.key.nsrif " +
			"and y.key.verbaleId = :id";
	
	public static final String FIND_VERBALI_BY_NSRIF_SQL = 
			"select x from Verbale x, TrovatoVerbale y " + 
			"where x.id = y.key.verbaleId " +
			"and y.key.nsrif = :id";
	
	public static final String DELETE_TROVATI_VERBALI_SQL = 
			"delete from TrovatoVerbale x where x.key.verbaleId = :id";
		
}
