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
import it.cnr.brevetti.sigla.fatture.attive.FatturaAttiva;
import it.cnr.brevetti.util.ServiceLocator;
import it.cnr.brevetti.util.ServiceLocatorException;
import it.cnr.brevetti.util.UtilityFunctions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class FatturaAttivaJB {

	private static FatturaAttivaJB instance;
	private static ServiceLocator locator;
	
	private FatturaAttivaJB(){
	}
	
	public static FatturaAttivaJB getInstance()  throws ServiceLocatorException{
		if (instance==null) {
			instance = new FatturaAttivaJB();
			locator = ServiceLocator.getInstance();
		}
		return instance;
	}
	
	public Fattura getFattura(Integer id) throws ServiceLocatorException {
		return locator.getGestioneFattureFacade().getFattura(id);
	}
	public List<FatturaAttiva> getDettaglioFatturaSigla(Long esercizio, String cds, String uo, Long pg) throws Exception {
		return locator.getFattureAttiveService().ricercaFatturaAttiva(esercizio, cds, uo, pg);
	}
	
	public Fattura getFattura(List<FatturaAttiva> righeSigla) throws Exception {
		Long esercizio = (long)righeSigla.get(0).getEsercizio();
		String cds = righeSigla.get(0).getCd_cds();
		String uo = righeSigla.get(0).getCd_unita_organizzativa();
		Long pg = righeSigla.get(0).getPg_fattura_attiva();
		Fattura fattura = locator.getGestioneFattureFacade().getFattura(esercizio, cds, uo, pg);
		if(fattura==null){
			fattura = new Fattura();
			fattura.setImporto(BigDecimal.ZERO);
			List<VoceFattura> voci = new ArrayList<VoceFattura>();
			for (FatturaAttiva fatturaAttiva : righeSigla) {
				VoceFattura voce = new VoceFattura();
				voce.setId(UtilityFunctions.generateRandomId());
				voce.setAnticipazione(fatturaAttiva.getFcIva());
				voce.setOnorari(fatturaAttiva.getIm_imponibile());
				voce.setIva(fatturaAttiva.getIm_iva());
				voce.setParzialeEuro(fatturaAttiva.getIm_imponibile().add(fatturaAttiva.getIm_iva().add(fatturaAttiva.getFcIva())));
				voce.setNsrif(Long.valueOf(fatturaAttiva.getPg_trovato()).intValue());
				VoceFatturaSigla voceFatturaSigla = VoceFatturaSigla(fatturaAttiva.getEsercizio(), fatturaAttiva.getCd_cds(), fatturaAttiva.getCd_unita_organizzativa(), fatturaAttiva.getPg_fattura_attiva(), fatturaAttiva.getProgressivo_riga(), null);
				voce.setVoceFatturaSigla(voceFatturaSigla);
				voci.add(voce);
				fattura.setImporto(fattura.getImporto().add(voce.getParzialeEuro()));
			}
			fattura.setVociFatture(voci);
			//fattura.setNumFattura(righeSigla.get(0).getNr_fattura_fornitore());
			//TODO data emissione o data registrazione?
			fattura.setDataFattura(righeSigla.get(0).getDt_emissione().getTime());
			if(getStudioBrevettuale(righeSigla.get(0).getCd_terzo())!=null){
				fattura.setStudioBrevettualeId(righeSigla.get(0).getCd_terzo());
				fattura.setStudioBrevettuale(getStudioBrevettuale(fattura.getStudioBrevettualeId()));
			}else{
				fattura.setStudioBrevettualeId(66);						//mappato sul record ALTRO
				fattura.setStudioBrevettuale(getStudioBrevettuale(66));
			}
			//fattura.setMandatoProtocollo(righeSigla.get(0).getPg_mandato()+" "+righeSigla.get(0).getEsercizio_mandato());
			//if(righeSigla.get(0).getDt_emissione_obbligazione_impegno()!=null)
			//	fattura.setDataImpegno(righeSigla.get(0).getDt_emissione_obbligazione_impegno().getTime());
			//fattura.setImpegnoObbligazione(""+righeSigla.get(0).getPg_obbligazione_impegno());
		}else{
			List<VoceFattura> voci = fattura.getVociFatture();
			if(fattura.getNote()==null) fattura.setNote("");
			BigDecimal totale = BigDecimal.ZERO;
			ArrayList<VoceFattura> vociAssenti = new ArrayList<VoceFattura>(voci);
			for (FatturaAttiva fatturaAttiva : righeSigla) {
				BigDecimal totIva = BigDecimal.ZERO;
				BigDecimal totImponibile = BigDecimal.ZERO;
				totale=totale.add(fatturaAttiva.getIm_imponibile().add(fatturaAttiva.getIm_iva().add(fatturaAttiva.getFcIva())));
				for (VoceFattura voce : voci) {
					if(fatturaAttiva.getProgressivo_riga()==voce.getVoceFatturaSigla().getProgressivoRiga().longValue()){
						totIva=totIva.add(voce.getIva());
						totImponibile=totImponibile.add(voce.getOnorari());
						vociAssenti.remove(voce);
					}
					if(voce.getNsrif().longValue()!=fatturaAttiva.getPg_trovato()){
						voce.setNsrif(Long.valueOf(fatturaAttiva.getPg_trovato()).intValue());
						fattura.setNote(fattura.getNote().trim().concat("!NSRIF"));
					}
				}
				if(totIva.compareTo(fatturaAttiva.getIm_iva())!=0)
					fattura.setNote(fattura.getNote().trim().concat("!Parziale IVA"));
				if(totImponibile.compareTo(fatturaAttiva.getIm_imponibile())!=0){
					fattura.setNote(fattura.getNote().trim().concat("!Parziale Imponibile"));
				}
			}
			//if(fattura.getNumFattura().trim().toCharArray()==righeSigla.get(0).getNr_fattura_fornitore().trim().toCharArray()){
			//	fattura.setNumFattura(righeSigla.get(0).getNr_fattura_fornitore());
			//	fattura.setNote(fattura.getNote().trim().concat("!Numero Fattura"));
			//}
			if(fattura.getDataFattura().equals(righeSigla.get(0).getDt_emissione().getTime())){
				fattura.setDataFattura(righeSigla.get(0).getDt_emissione().getTime());
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
	public List<FatturaAttiva> getFatture(Parametri p) throws Exception {
		List<FatturaAttiva> dest = new ArrayList<FatturaAttiva>();
		List<FatturaAttiva> list = locator.getFattureAttiveService().ricercaFattureAttive(((Integer)p.get(FatturaQuery.NSRIF)).longValue());
		HashSet<String>index = new HashSet<String>();
		if(list!=null) for (FatturaAttiva fatturaAttiva : list) {
			String ndx = fatturaAttiva.getEsercizio()+"|"+fatturaAttiva.getCd_cds()+"|"+fatturaAttiva.getCd_unita_organizzativa()+"|"+fatturaAttiva.getPg_fattura_attiva();
			if(index.contains(ndx))				//inserisce solo una riga per fattura
				continue;
			if(has(FatturaQuery.DATA_FATTURA, p.getKeys())){
				Date[] range = (Date[])p.get(FatturaQuery.DATA_FATTURA);
				Date dataFattura = fatturaAttiva.getDt_emissione()!=null?fatturaAttiva.getDt_emissione().getTime():fatturaAttiva.getDt_registrazione().getTime();
				if(dataFattura.before(range[0]) || dataFattura.after(range[1]))
					continue;
			}
			/*if(has(FatturaQuery.STUDI_BREVETTUALI_ID, p.getKeys())){
				if(fatturaAttiva.getCd_terzo()!=((Integer)p.get(FatturaQuery.STUDI_BREVETTUALI_ID)))
					continue;
			}
			if(has(FatturaQuery.NUM_FATTURA, p.getKeys()))
				if(!fatturaPassiva.getNr_fattura_fornitore().equals(p.get(FatturaQuery.NUM_FATTURA)))
					continue;
			*/
			//fattura.setNumFattura(fatturaPassiva.getNr_fattura_fornitore());
			//fattura.setDataFattura(fatturaPassiva.getDt_fattura_fornitore().getTime());
			//fattura.setNote(fatturaPassiva.getDs_fattura_passiva());
			//fattura.setStudioBrevettualeId(fatturaPassiva.getCd_terzo());
			dest.add(fatturaAttiva);
			index.add(ndx);
		}
		return dest;
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
}
