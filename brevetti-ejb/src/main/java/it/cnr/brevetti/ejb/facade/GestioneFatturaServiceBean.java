package it.cnr.brevetti.ejb.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import it.cnr.brevetti.domain.FatturaQuery;
import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.ejb.entities.Causale;
import it.cnr.brevetti.ejb.entities.Fattura;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.ejb.entities.VoceFattura;
import it.cnr.brevetti.ejb.entities.VoceFatturaSigla;
import it.cnr.brevetti.ejb.entities.XQuery;
import it.cnr.brevetti.ejb.services.CommonAbstract;
import it.cnr.brevetti.util.Utile;

/**
 * Implementazione servizio FACADE di gestione delle Fatture
 * @author Aurelio D'Amico
 * @version 1.0 [19-Jan-09]
 */
@Stateless
@SuppressWarnings({"rawtypes", "unchecked"})
public class GestioneFatturaServiceBean extends CommonAbstract implements GestioneFatturaService {

	public Fattura getFattura(Integer id) {
		Fattura fattura = (Fattura) service.read(Fattura.class, id);
		return fattura == null ? null : loadFattura(fattura);
	}
	public Fattura salvaFattura(Fattura fattura) {
		if (fattura == null) return null;
		// recupero dell'ID dopo la scrittura
		fattura = (Fattura) service.create(fattura);
		salvaVociFattura(fattura);
		return getFattura(fattura.getId());
	}
	public Fattura aggiornaFattura(Fattura fattura) {
		if (fattura == null) return null;
		// protocollo: voci == null nessuna modifica
		if (fattura.getVociFatture() != null)
			aggiornaVociFattura(fattura);
		service.update(fattura);
		return getFattura(fattura.getId());
	}
	public void eliminaFattura(Integer id) {
		eliminaVociFattura(id);
		service.delete(id);		
	}
	private Fattura loadFattura(Fattura fattura) {
		Integer id = fattura.getStudioBrevettualeId();
		if (id != null) {
			fattura.setStudioBrevettuale(getStudioBrevettuale(id));
		}
		List<VoceFattura> list = getListByFatturaId(fattura.getId());
		if (list != null && list.size() > 0) {
			for (VoceFattura vf : list) {
				id = vf.getNsrif();
				if (id != null)
					vf.setTrovato((Trovato) service.read(Trovato.class, id));
				id = vf.getCausaliId();
				if (id != null)
					vf.setCausale((Causale) service.read(Causale.class, id));
				id = vf.getStatiId();
				if (id != null)
					vf.setStato(getStato(id));
			}
		}
		fattura.setVociFatture(list);
		return fattura;
	}
	private Fattura aggiornaVociFattura(Fattura fattura) {
		List<VoceFattura> list = fattura.getVociFatture();
		if (list !=  null) {
			if (list.size() > 0)
				salvaVociFattura(fattura);
			else
				// protocollo: lista vuota = eliminazione di tutte le voci
				eliminaVociFattura(fattura.getId());
		}
		return fattura;
	}
	private void eliminaVociFattura(Integer id) {
		List<VoceFattura> list = getListByFatturaId(id);
		if (list != null && list.size() > 0) {
			for (VoceFattura x : list) {			
				service.delete(x);
			}
		}
	}
	private void salvaVociFattura(Fattura fattura) {
		List<VoceFattura> list = fattura.getVociFatture();
		if (list == null || list.isEmpty())
			return;
		for (VoceFattura x : list) {
			eliminaVociRimosse(fattura);
			if (x.getId() == null || x.getId().intValue() < 0) {
				x.setId(null);
				x.setFattureId(fattura.getId());
				VoceFattura temp = (VoceFattura) service.create(x);
				x.setId(temp.getId());
				createVoceFatturaSigla(x);
			} else {
				service.update(x);
			}
		}
	}
	// eliminazione dal DB delle voci rimosse dalla fattura
	private void eliminaVociRimosse(Fattura fattura) {
		// lettura voci fattura precedenti
		List<VoceFattura> voci = getListByFatturaId(fattura.getId());
		if (voci == null || voci.isEmpty()) return;
		// salvataggio chiavi voci fattura precedenti
		List<Integer> chiavi = new ArrayList<Integer>();
		for (VoceFattura x : voci) {
			chiavi.add(x.getId());
		}
		// carica le voci fattura attuali
		voci = fattura.getVociFatture();
		if (voci == null || voci.isEmpty()) return;
		// rimuove le chiavi presenti nella fattura attuale
		for (VoceFattura x : voci) {
			if (chiavi.contains(x.getId()))
				chiavi.remove(x.getId());
		}
		// rimuove le voci di fattura assenti nella fattura attuale
		for (Integer id : chiavi) {			
			deleteVoceFattura(id);
			deleteVoceFatturaSigla(id);
		}
	}

	// ricerca avanzata
	public List getFatture(Parametri p) {
		if (p == null || p.isEmpty()) return null;		
		List list = service.find(new FatturaQuery().getQuery(p));
		if (list != null && list.size() == 1) {
			list.set(0, getFattura(((Fattura) list.get(0)).getId()));
		}
		return list;
	}

	public List getCausali() {
		return service.findAll(Causale.class);
	}

	// =================================================
	// 	integrazione fatture sigla
	// =================================================

	public Fattura getFattura(Long esercizio, String cds, String uo, Long pgFattura) {
		Parametri params = Parametri.getNewParametri();
		params.add("esercizio", esercizio);
		params.add("cds", cds);
		params.add("uo", uo);
		params.add("pgFattura", pgFattura);
		Integer fatturaId = (Integer) service.findObjectByQuery(XQuery.FIND_FATTURA_ID, params.getMap());
		if (fatturaId==null) return null;
		Fattura fattura = getFattura(fatturaId);
		if (fattura==null) return null;
		List<VoceFattura> voci = fattura.getVociFatture();
		if (Utile.isEmptyOrNull(voci)) return fattura;
		for (VoceFattura x : voci) {
			x.setVoceFatturaSigla((VoceFatturaSigla) service.read(VoceFatturaSigla.class, x.getId()));
		}
		fattura.setVociFatture(voci);
		return fattura;
	}	
	private void createVoceFatturaSigla(VoceFattura x) {
		VoceFatturaSigla sigla = x.getVoceFatturaSigla();
		sigla.setId(x.getId());
		service.create(sigla);
	}
	private void deleteVoceFattura(Integer id) {
		VoceFattura x = (VoceFattura) service.read(VoceFattura.class, id);
		if (x != null) service.delete(x);		
	}
	private void deleteVoceFatturaSigla(Integer id) {
		VoceFatturaSigla x = (VoceFatturaSigla) service.read(VoceFatturaSigla.class, id);
		if (x != null) service.delete(x);		
	}
	public List getListByFatturaId(Integer id) {
		return service.findByQuery(XQuery.FIND_VOCI_FATTURA_BY_FATTURA_ID, id);
	}	
}

	