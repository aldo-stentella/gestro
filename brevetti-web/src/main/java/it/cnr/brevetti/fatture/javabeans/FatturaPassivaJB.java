package it.cnr.brevetti.fatture.javabeans;

import it.cnr.brevetti.domain.FatturaQuery;
import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.ejb.entities.Causale;
import it.cnr.brevetti.ejb.entities.Fattura;
import it.cnr.brevetti.ejb.entities.Stato;
import it.cnr.brevetti.ejb.entities.StudioBrevettuale;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.ejb.entities.VoceFattura;
import it.cnr.brevetti.ejb.entities.VoceFatturaSigla;
import it.cnr.brevetti.sigla.fatture.passive.FatturaPassiva;
import it.cnr.brevetti.sigla.fatture.passive.FatturaPassivaBase;
import it.cnr.brevetti.util.ServiceLocator;
import it.cnr.brevetti.util.ServiceLocatorException;
import it.cnr.brevetti.util.UtilityFunctions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class FatturaPassivaJB {

	private static FatturaPassivaJB instance;
	private static ServiceLocator locator;
	
	private FatturaPassivaJB(){
	}
	
	public static FatturaPassivaJB getInstance()  throws ServiceLocatorException{
		if (instance==null) {
			instance = new FatturaPassivaJB();
			locator = ServiceLocator.getInstance();
		}
		return instance;
	}
	
	public Fattura getFattura(Integer id) throws ServiceLocatorException {
		return locator.getGestioneFattureFacade().getFattura(id);
	}
	public List<FatturaPassiva> getDettaglioFatturaSigla(Long esercizio, String cds, String uo, Long pg) throws Exception {
		return locator.getFatturePassiveService().ricercaFatturaPassivaByKey(esercizio, cds, uo, pg);
	}
	
	public Fattura getFattura(List<FatturaPassiva> righeSigla) throws Exception {
		Long esercizio = (long)righeSigla.get(0).getEsercizio();
		String cds = righeSigla.get(0).getCd_cds();
		String uo = righeSigla.get(0).getCd_unita_organizzativa();
		Long pg = righeSigla.get(0).getPg_fattura_passiva();
		Fattura fattura = locator.getGestioneFattureFacade().getFattura(esercizio, cds, uo, pg);
		if(fattura==null){
			fattura = new Fattura();
			fattura.setImporto(BigDecimal.ZERO);
			List<VoceFattura> voci = new ArrayList<VoceFattura>();
			for (FatturaPassiva fatturaPassiva : righeSigla) {
				VoceFattura voce = new VoceFattura();
				voce.setId(UtilityFunctions.generateRandomId());
				voce.setAnticipazione(fatturaPassiva.getFcIva());
				voce.setOnorari(fatturaPassiva.getIm_imponibile());
				voce.setIva(fatturaPassiva.getIm_iva());
				voce.setParzialeEuro(fatturaPassiva.getIm_imponibile().add(fatturaPassiva.getIm_iva().add(fatturaPassiva.getFcIva())));
				voce.setNsrif(Long.valueOf(fatturaPassiva.getPg_trovato()).intValue());
				VoceFatturaSigla voceFatturaSigla = VoceFatturaSigla(fatturaPassiva.getEsercizio(), fatturaPassiva.getCd_cds(), fatturaPassiva.getCd_unita_organizzativa(), fatturaPassiva.getPg_fattura_passiva(), fatturaPassiva.getProgressivo_riga(), null);
				voce.setVoceFatturaSigla(voceFatturaSigla);
				voci.add(voce);
				fattura.setImporto(fattura.getImporto().add(voce.getParzialeEuro()));
			}
			fattura.setVociFatture(voci);
			fattura.setNumFattura(righeSigla.get(0).getNr_fattura_fornitore());
			fattura.setDataFattura(righeSigla.get(0).getDt_fattura_fornitore().getTime());
			if(getStudioBrevettuale(righeSigla.get(0).getCd_terzo())!=null){
				fattura.setStudioBrevettualeId(righeSigla.get(0).getCd_terzo());
				fattura.setStudioBrevettuale(getStudioBrevettuale(fattura.getStudioBrevettualeId()));
			}else{
				fattura.setStudioBrevettualeId(66);						//mappato sul record ALTRO
				fattura.setStudioBrevettuale(getStudioBrevettuale(66));
			}
			fattura.setMandatoProtocollo(righeSigla.get(0).getPg_mandato()+" "+righeSigla.get(0).getEsercizio_mandato());
			if(righeSigla.get(0).getDt_emissione_obbligazione_impegno()!=null)
				fattura.setDataImpegno(righeSigla.get(0).getDt_emissione_obbligazione_impegno().getTime());
			fattura.setImpegnoObbligazione(""+righeSigla.get(0).getPg_obbligazione_impegno());
		}else{
			List<VoceFattura> voci = fattura.getVociFatture();
			if(fattura.getNote()==null) fattura.setNote("");
			BigDecimal totale = BigDecimal.ZERO;
			ArrayList<VoceFattura> vociAssenti = new ArrayList<VoceFattura>(voci);
			for (FatturaPassiva fatturaPassiva : righeSigla) {
				BigDecimal totIva = BigDecimal.ZERO;
				BigDecimal totImponibile = BigDecimal.ZERO;
				totale=totale.add(fatturaPassiva.getIm_imponibile().add(fatturaPassiva.getIm_iva().add(fatturaPassiva.getFcIva())));
				for (VoceFattura voce : voci) {
					if(fatturaPassiva.getProgressivo_riga()==voce.getVoceFatturaSigla().getProgressivoRiga().longValue()){
						totIva=totIva.add(voce.getIva());
						totImponibile=totImponibile.add(voce.getOnorari());
						vociAssenti.remove(voce);
					}
					if(voce.getNsrif().longValue()!=fatturaPassiva.getPg_trovato()){
						voce.setNsrif(Long.valueOf(fatturaPassiva.getPg_trovato()).intValue());
						fattura.setNote(fattura.getNote().trim().concat("!NSRIF"));
					}
				}
				if(totIva.compareTo(fatturaPassiva.getIm_iva())!=0)
					fattura.setNote(fattura.getNote().trim().concat("!Parziale IVA"));
				if(totImponibile.compareTo(fatturaPassiva.getIm_imponibile())!=0){
					fattura.setNote(fattura.getNote().trim().concat("!Parziale Imponibile"));
				}
			}
			if(fattura.getNumFattura().trim().toCharArray()==righeSigla.get(0).getNr_fattura_fornitore().trim().toCharArray()){
				fattura.setNumFattura(righeSigla.get(0).getNr_fattura_fornitore());
				fattura.setNote(fattura.getNote().trim().concat("!Numero Fattura"));
			}
			if(fattura.getDataFattura().equals(righeSigla.get(0).getDt_fattura_fornitore().getTime())){
				fattura.setDataFattura(righeSigla.get(0).getDt_fattura_fornitore().getTime());
				fattura.setNote(fattura.getNote().trim().concat("!Data Fattura"));
			}
			if(totale.compareTo(fattura.getImporto())!=0){
				fattura.setNote(fattura.getNote().trim().concat("!Totale Fattura"));
			}
			if(vociAssenti.size()!=0){
				voci.removeAll(vociAssenti);
				fattura.setNote(fattura.getNote().trim().concat("!Voci Fattura"));
			}
			if(fattura.getStudioBrevettualeId().compareTo(righeSigla.get(0).getCd_terzo())!=0){
				if(getStudioBrevettuale(righeSigla.get(0).getCd_terzo())!=null){
					fattura.setStudioBrevettualeId(righeSigla.get(0).getCd_terzo());
					fattura.setStudioBrevettuale(getStudioBrevettuale(fattura.getStudioBrevettualeId()));
				}else{
					fattura.setStudioBrevettualeId(66);						//mappato sul record ALTRO
					fattura.setStudioBrevettuale(getStudioBrevettuale(66));
				}
				fattura.setNote(fattura.getNote().trim().concat("!Studio"));
			}
		}
		return fattura; 
	}
	
	public VoceFatturaSigla VoceFatturaSigla(int esercizio, String cds, String uo, long pg, long riga, Integer id){
		VoceFatturaSigla voceFatturaSigla = new VoceFatturaSigla();
		voceFatturaSigla.setEsercizio(Long.valueOf(esercizio));
		voceFatturaSigla.setCdCds(cds);
		voceFatturaSigla.setCdUnitaOrganizzativa(uo);
		voceFatturaSigla.setPgFatturaPassiva(pg);
		voceFatturaSigla.setProgressivoRiga(riga);
		voceFatturaSigla.setId(id);
		return voceFatturaSigla;
	}
	
	public Fattura salvaFattura(Fattura fattura) throws ServiceLocatorException {
		return locator.getGestioneFattureFacade().salvaFattura(fattura);
	}
	public Fattura aggiornaFattura(Fattura fattura) throws ServiceLocatorException {
		return locator.getGestioneFattureFacade().aggiornaFattura(fattura);
	}
	public List<StudioBrevettuale> getStudiBrevettuali() throws ServiceLocatorException {
		return locator.getGestioneFattureFacade().getStudiBrevettualiSortedByDenominazione();
	}
	//TODO modificare facade
	public StudioBrevettuale getStudioBrevettuale(Integer id) throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getStudioBrevettuale(id);
	}
	public List<Trovato> getTrovati(Parametri p) throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getTrovati(p);
	}
	public List<FatturaPassivaBase> getFatture(Parametri p) throws Exception {
		List<FatturaPassivaBase> dest = new ArrayList<FatturaPassivaBase>();
		List<FatturaPassivaBase> list = locator.getFatturePassiveService().ricercaFatturePassiveBase(((Integer)p.get(FatturaQuery.NSRIF)).longValue());
		if(list!=null) for (FatturaPassivaBase fatturaPassiva : list) {
			if(has(FatturaQuery.DATA_FATTURA, p.getKeys())){
				Date[] range = (Date[])p.get(FatturaQuery.DATA_FATTURA);
				Date dataFattura = fatturaPassiva.getDt_fattura_fornitore().getTime();
				if(dataFattura.before(range[0]) || dataFattura.after(range[1]))
					continue;
			}
			if(has(FatturaQuery.STUDI_BREVETTUALI_ID, p.getKeys())){
				if(fatturaPassiva.getCd_terzo()!=((Integer)p.get(FatturaQuery.STUDI_BREVETTUALI_ID)))
					continue;
			}
			if(has(FatturaQuery.NUM_FATTURA, p.getKeys()))
				if(!fatturaPassiva.getNr_fattura_fornitore().equals(p.get(FatturaQuery.NUM_FATTURA)))
					continue;

			//fattura.setNumFattura(fatturaPassiva.getNr_fattura_fornitore());
			//fattura.setDataFattura(fatturaPassiva.getDt_fattura_fornitore().getTime());
			//fattura.setNote(fatturaPassiva.getDs_fattura_passiva());
			//fattura.setStudioBrevettualeId(fatturaPassiva.getCd_terzo());
			dest.add(fatturaPassiva);
		}
		return dest;	//locator.getGestioneFattureFacade().getFatture(p);
	}
	
	protected boolean has(String what, String[] where) {
		for (int i = 0; i < where.length; i++) {
			if (where[i].startsWith(what)) return true;
		}
		return false;
	}
	
	public List<Stato> getStati() throws ServiceLocatorException {
		return locator.getGestioneFattureFacade().getStati();
	}
	public List<Causale> getCausali() throws ServiceLocatorException {
		return locator.getGestioneFattureFacade().getCausali();
	}
//	TODO aggiungere getCausale(id) e getStato(id)
	public Causale getCausale(Integer id) throws ServiceLocatorException {
		for (Iterator iter = locator.getGestioneFattureFacade().getCausali().iterator(); iter.hasNext();) {
			Causale causale = (Causale) iter.next();
			if(causale.getId().equals(id))
				return causale;
		}
		return null;
	}
	public Stato getStato(Integer id) throws ServiceLocatorException {
		for (Iterator iter = locator.getGestioneFattureFacade().getStati().iterator(); iter.hasNext();) {
			Stato stato = (Stato) iter.next();
			if(stato.getId().equals(id))
				return stato;
		}
		return null;
	}
}
