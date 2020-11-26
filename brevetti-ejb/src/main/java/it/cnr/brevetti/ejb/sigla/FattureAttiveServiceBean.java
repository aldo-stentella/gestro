package it.cnr.brevetti.ejb.sigla;

import java.util.List;

import javax.ejb.Stateless;

import com.google.gson.Gson;

import it.cnr.brevetti.sigla.JsonClause;
import it.cnr.brevetti.sigla.fatture.attive.FatturaAttiva;
import it.cnr.brevetti.sigla.fatture.attive.FatturaAttivaBase;
import it.cnr.brevetti.sigla.fatture.attive.FattureAttive;
import it.cnr.brevetti.sigla.fatture.attive.FattureAttiveBase;

/**
 * Implementazione del servizio di interrogazione delle fatture attive su SIGLA
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Sep 22, 2016]
 */
@Stateless
public class FattureAttiveServiceBean extends SiglaRestService implements FattureAttiveService {

	/**
	 * ricerca per esercizio, cds origine, uo origine, progressivo fattura attiva
	 */
	@Override
	public List<FatturaAttiva> ricercaFatturaAttiva(Long esercizio, String cds,	String uo, Long pg) throws Exception {
		List<JsonClause> params = newParams();
		if (esercizio!=null)
			params.add(newClause(ANNO, esercizio));
		if (cds!=null)
			params.add(newClause(CDS_ORIG, cds));
		if (uo!=null)
			params.add(newClause(UO_ORIG, uo));
		if (pg!=null)
			params.add(newClause(PG_ATT, pg));		
		try {
			return getFattureAttive(params);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}
	/**
	 * ricerca per esercizio, cds, uo, progressivo fattura attiva
	 */
	@Override
	public List<FatturaAttiva> ricercaFatturaAttivaByKey(Long esercizio, String cds, String uo, Long pg) throws Exception {
		List<JsonClause> params = newParams();
		if (esercizio!=null)
			params.add(newClause(ANNO, esercizio));
		if (cds!=null)
			params.add(newClause(CDS, cds));
		if (uo!=null)
			params.add(newClause(UO, uo));
		if (pg!=null)
			params.add(newClause(PG_ATT, pg));		
		try {
			return getFattureAttive(params);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}
	/**
	 * ricerca per nsrif
	 */
	@Override
	public List<FatturaAttiva> ricercaFattureAttive(Long trovato) throws Exception {
		if (trovato==null) return null;
		List<JsonClause> params = newParams();
		params.add(newClause(NSRIF, trovato));
		try {
			return getFattureAttive(params);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}
	/**
	 * ricerca per nsrif
	 */
	@Override
	public List<FatturaAttivaBase> ricercaFattureAttiveBase(Long trovato) throws Exception {
		if (trovato==null) return null;
		List<JsonClause> params = newParams();
		params.add(newClause(NSRIF, trovato));
		try {
			String json = postJson(getUrlFatturaAttiva(), getJson(params));
			FattureAttiveBase fatture = new Gson().fromJson(json, FattureAttiveBase.class);
			if (fatture!=null && fatture.getTotalNumItems()>0) {
				return fatture.getElements();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private List<FatturaAttiva> getFattureAttive(List<JsonClause> params) throws Exception {
		String json = postJson(getUrlFatturaAttivaRiga(), getJson(params));		
		FattureAttive fatture = new Gson().fromJson(json, FattureAttive.class);
		if (fatture!=null && fatture.getTotalNumItems()>0) {
			return fatture.getElements();
		} 
		return null;
	}
}
