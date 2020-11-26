package it.cnr.brevetti.trovati.javabeans;

import java.util.ArrayList;
import java.util.List;

import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.ejb.entities.Classificazione;
import it.cnr.brevetti.ejb.entities.Dipartimento;
import it.cnr.brevetti.ejb.entities.DipendenteCnr;
import it.cnr.brevetti.ejb.entities.EnteEsterno;
import it.cnr.brevetti.ejb.entities.Idioma;
import it.cnr.brevetti.ejb.entities.Inventore;
import it.cnr.brevetti.ejb.entities.Istituto;
import it.cnr.brevetti.ejb.entities.Stato;
import it.cnr.brevetti.ejb.entities.StudioBrevettuale;
import it.cnr.brevetti.ejb.entities.TipoDocumento;
import it.cnr.brevetti.ejb.entities.TipoEstinzione;
import it.cnr.brevetti.ejb.entities.TipoTrovato;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.ejb.entities.DocumentoInfo;
import it.cnr.brevetti.ejb.entities.Utente;
import it.cnr.brevetti.ejb.entities.Valorizzazione;
import it.cnr.brevetti.ejb.entities.Verbale;
import it.cnr.brevetti.ejb.services.DocumentoServiceBean;
import it.cnr.brevetti.util.ServiceLocator;
import it.cnr.brevetti.util.ServiceLocatorException;

public class TrovatoJB {

	private static TrovatoJB instance;
	private static ServiceLocator locator;
	
	private TrovatoJB(){
	}
	
	public static TrovatoJB getInstance()  throws ServiceLocatorException{
		if (instance==null) {
			instance = new TrovatoJB();
			locator = ServiceLocator.getInstance();
		}
		return instance;
	}
	
	public Trovato salvaTrovato(Trovato trovato) throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().salvaTrovato(trovato);
	}
	
	public Trovato aggiornaTrovato(Trovato trovato) throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().aggiornaTrovato(trovato);
	}
	public Trovato getTrovato(Integer id, Integer dipartimentoId) throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getTrovato(id, dipartimentoId);
	}
	public List<Trovato> getTrovati(Parametri p) throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getTrovati(p);
	}
	public List<TipoTrovato> getTipiTrovato() throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getTipiTrovato();
	}
	public TipoTrovato getTipoTrovato(Integer id)throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getTipoTrovato(id);
	}
	public List<Stato> getStati()throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getStati();
	}
	public List getTipiRegionalPatent()throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getTipiRegionalPatent();
	}
	public List<StudioBrevettuale> getStudiBrevettuali()throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getStudiBrevettuali();
	}
	public StudioBrevettuale getStudioBrevettuale(Integer id)throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getStudioBrevettuale(id);
	}
	public List<Idioma> getIdiomi()throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getIdiomi();
	}
	public Idioma getIdioma(Integer id)throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getIdioma(id);
	}
	public Stato getStato(Integer id) throws ServiceLocatorException{
		return locator.getGestioneTrovatiFacade().getStato(id);
	}
	public List<String> getDenominazioniStudiBrevettuali()throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getDenominazioniStudiBrevettuali();
	}
	public List<StudioBrevettuale> getSediStudiBrevettuali(String denominazione)throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getSediStudiBrevettuali(denominazione);
	}
	public List<StudioBrevettuale> getStudiBrevettualiSortedByDenominazione() throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getStudiBrevettualiSortedByDenominazione();
	}
	public List<Classificazione> getClassiLivello1()throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getClassificazioniLivello(new Integer(1),new Integer(1));
	}
	public List<Classificazione> getSettoriTecnologici()throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getClassificazioniLivello(new Integer(1),new Integer(2));
	}
	public List<Classificazione> getClassificazioniChildren(Integer idPadre)throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getClassificazioniChildren(idPadre);
	}
	public Classificazione getClassificazione(Integer id) throws ServiceLocatorException{
		return locator.getGestioneTrovatiFacade().getClassificazione(id);
	}
	public List<Classificazione> getClassificazioniAncestors(Integer id) throws ServiceLocatorException{
		return locator.getGestioneTrovatiFacade().getClassificazioniAncestors(id);
	}
	public Inventore getInventoreByMatricola(Integer matricola)throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getInventoreByMatricola(matricola);
	}
	public DipendenteCnr getDipendenteByMatricola(Integer matricola) throws ServiceLocatorException {
		return 	locator.getGestioneTrovatiFacade().getDipendenteByMatricola(matricola);
	}
	public List getInventori(String cognome)throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getInventori(cognome);
	}
	public Inventore getInventore(Integer id)throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getInventore(id);
	}
	public List getIstituti()throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getIstituti();
	}
	public List<Dipartimento> getDipartimenti()throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getDipartimenti();
	}
	public List getEntiEsterni()throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getEntiEsterni();
	}
	public EnteEsterno getEnteEsterno(Integer id) throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getEnteEsterno(id);
	}
	public Istituto getIstituto(Integer id) throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getIstituto(id);
	}
	public Long countTrovatiByClassificazione(String codice) throws ServiceLocatorException {
		return (Long)locator.getGestioneTrovatiFacade().countTrovatiByClassificazione(codice);
	}
	public List countTrovatiByDipartimento() throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().countTrovatiByDipartimento();
	}
	public List<Utente> getUtenti() throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getUtenti();

	}
	public Utente getUtente(String utentiID) throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getUtente(utentiID);
	}
	public Valorizzazione getValorizzazione(Integer id) throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getValorizzazione(id);
		
	}
	public DocumentoInfo getTrovatoDocumento(Integer id) throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getDocumentoInfo(id);
	}
	public List<DocumentoInfo> getDocumentiInfo(Parametri p) throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getDocumentiInfo(p);
	}
	public List<TipoDocumento> getTipiDocumento() throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getTipiDocumento();
	}
	public TipoDocumento getTipoDocumento(Integer id) throws ServiceLocatorException {
		for(TipoDocumento td: getTipiDocumento()){
			if(td.getTipiDocumentoId().equals(id))
				return td;
		}
		return null;
	}
	public Integer creaDocumento(byte[] stream) throws ServiceLocatorException {
		return locator.getDocumentoService().create(stream);
	}
	public byte[] getAllegato(Integer id) throws ServiceLocatorException {
		return locator.getDocumentoService().getAllegato(id);
	}
	public DocumentoInfo salvaDocumentoInfo(DocumentoInfo doc) throws ServiceLocatorException{
		return locator.getGestioneTrovatiFacade().salvaDocumentoInfo(doc);
	}
	public void delDocumentoInfo(Integer id) throws ServiceLocatorException{
		locator.getGestioneTrovatiFacade().delDocumentoInfo(id);
	}
	public List<TipoEstinzione> getTipiEstinzione() throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getTipiEstinzione();
	}
	public TipoEstinzione getTipoEstinzione(Integer id) throws ServiceLocatorException {
		for(TipoEstinzione te:getTipiEstinzione())
			if(te.getTipiEstinzioneId().equals(id))
				return te;
		return null;
	}
	public List<TipoEstinzione> getTipiEstinzione(Integer fase) throws ServiceLocatorException {
		List<TipoEstinzione> list = new ArrayList<TipoEstinzione>(), temp = locator.getGestioneTrovatiFacade().getTipiEstinzione();
		String[] fasi = {"","NAZ","PCT","EPC"};
		for (TipoEstinzione tipoEstinzione : temp) {
			if(tipoEstinzione.getFasi().indexOf(fasi[fase])!=-1)
				list.add(tipoEstinzione);
		}
		return list;
	}
	public String[] getCessioneDiritti() {
		String[] cd = {"N/D", "ceduto al CNR", "Art.65 comma 5","Art.65 commi 1-4", "non applicabile"};
		return cd;
	}
	public Verbale getVerbale(Integer id) throws ServiceLocatorException{
		return locator.getGestioneTrovatiFacade().getVerbale(id);
	}
	public List<Verbale> getVerbali(Integer nsrif) throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getVerbali(nsrif);
	}
	public Verbale salvaVerbale(Verbale verbale) throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().salvaVerbale(verbale);
	}
	
	public void startIndicizzazione(String pattern) throws ServiceLocatorException {
		locator.getIndicizzatore().start(pattern);
	}
	public void stopIndicizzazione() throws ServiceLocatorException {
		locator.getIndicizzatore().stop();
	}
	public void indicizzazioneOra() throws ServiceLocatorException {
		locator.getIndicizzatore().indicizza();
	}
	public String listaTimerAttivi() throws ServiceLocatorException {
		return locator.getIndicizzatore().getTimersInfo();
	}
}
