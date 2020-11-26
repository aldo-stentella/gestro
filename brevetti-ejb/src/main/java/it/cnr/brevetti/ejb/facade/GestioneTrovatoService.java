package it.cnr.brevetti.ejb.facade;

import java.util.List;

import javax.ejb.Remote;

import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.ejb.entities.Azienda;
import it.cnr.brevetti.ejb.entities.DocumentoInfo;
import it.cnr.brevetti.ejb.entities.Inventore;
import it.cnr.brevetti.ejb.entities.TipoDocumento;
import it.cnr.brevetti.ejb.entities.TipoEstinzione;
import it.cnr.brevetti.ejb.entities.TipoTrovato;
import it.cnr.brevetti.ejb.entities.TipoValorizzazione;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.ejb.entities.TrovatoDipartimento;
import it.cnr.brevetti.ejb.entities.Utente;
import it.cnr.brevetti.ejb.entities.Valorizzazione;
import it.cnr.brevetti.ejb.entities.Verbale;
import it.cnr.brevetti.ejb.services.CommonInterface;
/**
 * Interfaccia servizio FACADE di gestione del Trovato
 * @author Aurelio D'Amico
 * @version 1.0 [19-Nov-07]
 */
@Remote
@SuppressWarnings("rawtypes")
public interface GestioneTrovatoService extends CommonInterface {	
	Trovato aggiornaTrovato(Trovato trovato);
	Trovato salvaTrovato(Trovato trovato);
	Trovato getTrovato(Integer id, Integer dipartimentoId);
	List getTipiTrovato();
	TipoTrovato getTipoTrovato(Integer id);
	List getTrovati(Parametri p);
	List countTrovatiByDipartimento();
	Object countTrovatiByClassificazione(String codice);
	List getTrovatiByUo(String uo);
	Inventore salvaInventore(Inventore inventore);
	List<TrovatoDipartimento> getTrovatoDipartimento(Integer id);
	List<Utente> getUtenti();
	Utente getUtente(String id);
	Valorizzazione salvaValorizzazione(Valorizzazione valorizzazione);
	Valorizzazione getValorizzazione(Integer id);
	List<Valorizzazione> getValorizzazioni(Integer nsrif);
	List<Azienda> getAziende();
	Azienda getAzienda(Integer id);
	List<TipoValorizzazione> getTipiValorizzazione();
	List<TipoDocumento> getTipiDocumento();
	DocumentoInfo getDocumentoInfo(Integer id);
	List<DocumentoInfo> getDocumentiInfo(Parametri p);
	void delDocumentoInfo(Integer id);
	DocumentoInfo salvaDocumentoInfo(DocumentoInfo doc);
	List<TipoEstinzione> getTipiEstinzione();
	Verbale salvaVerbale(Verbale verbale);
	Verbale getVerbale(Integer id);
	List<Verbale> getVerbali(Integer nsrif);
	
}
