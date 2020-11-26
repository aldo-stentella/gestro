package it.cnr.brevetti.ejb.sigla;

import java.util.List;

import javax.ejb.Stateless;

import com.google.gson.Gson;

import it.cnr.brevetti.sigla.JsonClause;
import it.cnr.brevetti.sigla.fatture.passive.FatturaPassiva;
import it.cnr.brevetti.sigla.fatture.passive.FatturaPassivaBase;
import it.cnr.brevetti.sigla.fatture.passive.FatturePassive;
import it.cnr.brevetti.sigla.fatture.passive.FatturePassiveBase;
/**
 * Implementazione del servizio di interrogazione delle fatture passive su SIGLA
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Sep 20, 2016]
 *
 */
@Stateless
public class FatturePassiveServiceBean extends SiglaRestService implements FatturePassiveService {
	/**
	 * ricerca per esercizio, cds origine, uo origine, progressivo fattura passiva
	 */
	@Override
	public List<FatturaPassiva> ricercaFatturaPassiva(Long esercizio, String cds, String uo, Long pg) throws Exception {
		List<JsonClause> params = newParams();
		if (esercizio!=null)
			params.add(newClause(ANNO, esercizio));
		if (cds!=null)
			params.add(newClause(CDS_ORIG, cds));
		if (uo!=null)
			params.add(newClause(UO_ORIG, uo));
		if (pg!=null)
			params.add(newClause(PG_PASS, pg));		
		try {
			return getFatturePassive(params);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}
	/**
	 * ricerca per esercizio, cds, uo, progressivo fattura passiva
	 */
	@Override
	public List<FatturaPassiva> ricercaFatturaPassivaByKey(Long esercizio, String cds, String uo, Long pg) throws Exception {
		List<JsonClause> params = newParams();
		if (esercizio!=null)
			params.add(newClause(ANNO, esercizio));
		if (cds!=null)
			params.add(newClause(CDS, cds));
		if (uo!=null)
			params.add(newClause(UO, uo));
		if (pg!=null)
			params.add(newClause(PG_PASS, pg));		
		try {
			return getFatturePassive(params);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}
	/**
	 * ricerca per nsrif
	 */
	@Override
	public List<FatturaPassiva> ricercaFatturePassive(Long trovato) throws Exception {
		if (trovato==null) return null;
		List<JsonClause> params = newParams();
		params.add(newClause(NSRIF, trovato));
		try {
			return getFatturePassive(params);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}
	/**
	 * ricerca per nsrif
	 */
	@Override
	public List<FatturaPassivaBase> ricercaFatturePassiveBase(Long trovato) throws Exception {		
		if (trovato==null) return null;
		List<JsonClause> params = newParams();
		params.add(newClause(NSRIF, trovato));
		try {
			String json = postJson(getUrlFatturaPassiva(), getJson(params));
			FatturePassiveBase fatture = new Gson().fromJson(json, FatturePassiveBase.class);
			if (fatture!=null && fatture.getTotalNumItems()>0) {
				return fatture.getElements();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private List<FatturaPassiva> getFatturePassive(List<JsonClause> params) throws Exception {
		String json = postJson(getUrlFatturaPassivaRiga(), getJson(params));		
		FatturePassive fatture = new Gson().fromJson(json, FatturePassive.class);
		if (fatture!=null && fatture.getTotalNumItems()>0) {
			return fatture.getElements();
		} 
		return null;
	}
}
