package it.cnr.brevetti.ejb.facade;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.domain.PropostaQuery;
import it.cnr.brevetti.ejb.entities.DocumentoInfo;
import it.cnr.brevetti.ejb.entities.IdTransition;
import it.cnr.brevetti.ejb.entities.InventionDisclosure;
import it.cnr.brevetti.ejb.entities.InventionDisclosureDocumento;
import it.cnr.brevetti.ejb.entities.InventionDisclosureDocumentoKey;
import it.cnr.brevetti.ejb.entities.XQuery;
import it.cnr.brevetti.ejb.services.BasicAbstract;
import it.cnr.brevetti.ejb.services.DocumentoService;
import it.cnr.brevetti.ejb.services.LdapService;
import it.cnr.brevetti.gas.UtenteLdap;
import it.cnr.brevetti.util.Utile;

/**
 * Implementazione servizio FACADE di gestione delle proposte (Invention Disclosure)
 * @author Aurelio D'Amico
 * @version 1.0 [11-Dec-15]
 */
@Stateless
public class GestionePropostaServiceBean extends BasicAbstract implements GestionePropostaService {
	
	@EJB LdapService ldapService;
	@EJB DocumentoService docService;
	
	public boolean login(String uid, String password) {
		UtenteLdap user = ldapService.readUtente(uid);
		if (user == null) return false;
		return ldapService.autenticaUtente(user.getDN(), password).booleanValue();
	}
	public InventionDisclosure leggiProposta(Integer id) {
		InventionDisclosure x = (InventionDisclosure) find(InventionDisclosure.class, id);
		if (x!=null) {
			x.setDocumenti(getDocumenti(x.getId()));
			x.setTransizioni(getTransizioni(x.getId()));
		}
		return x;
	}
	public InventionDisclosure salvaProposta(InventionDisclosure indi) {
		InventionDisclosure x = indi;
		if (x==null) return null;
		if (x.getId()==null)
			x = creaProposta(indi);
		else
			x = aggiornaProposta(indi);
		return leggiProposta(x.getId());
	}
	public void eliminaProposta(InventionDisclosure indi) {
		if (indi==null || indi.getId()==null) return;
		InventionDisclosure dis = leggiProposta(indi.getId());
		if (dis==null) return;
		List<DocumentoInfo> list = getDocumenti(dis.getId());
		if (Utile.isNotEmptyOrNull(list)) {
			deleteAll(XQuery.DELETE_PROPOSTE_DOCUMENTI, dis.getId());
			eliminaDocumenti(dis.getId(), list);
		}
		deleteAll(XQuery.DELETE_TRANSIZIONI_BY_PROPOSTA, indi.getId());
		delete(indi);
	}
	private InventionDisclosure creaProposta(InventionDisclosure indi) {
		InventionDisclosure x = (InventionDisclosure) create(indi);
		associaDocumenti(x.getId(), indi.getDocumenti());
		creaTransizione(x);
		return leggiProposta(x.getId());
	}
	private InventionDisclosure aggiornaProposta(InventionDisclosure indi) {
		Integer stato = ((InventionDisclosure) find(InventionDisclosure.class, indi.getId())).getStato();
		InventionDisclosure dis = (InventionDisclosure) update(indi);
		if (!(stato==null && indi.getStato()==null)) {
			if (stato==null) {
				creaTransizione(indi);
			} else {
				if (!stato.equals(indi.getStato()))
					creaTransizione(indi);
			}
		}
		List<DocumentoInfo> saved = getDocumenti(dis.getId());
		List<DocumentoInfo> list = indi.getDocumenti();
		if (list!=null) {
			deleteAll(XQuery.DELETE_PROPOSTE_DOCUMENTI, dis.getId());
			if (Utile.isNotEmptyOrNull(list)) {
				associaDocumenti(dis.getId(), list);
			}
			eliminaDocumenti(dis.getId(), saved);
		}
		return leggiProposta(dis.getId());
	}
	@SuppressWarnings("unchecked")
	private List<DocumentoInfo> getDocumenti(Integer id) {
		return getList(XQuery.FIND_DOCUMENTI_BY_PROPOSTA, id);
	}
	private void associaDocumenti(Integer id, List<DocumentoInfo> list) {
		if (id!=null && Utile.isNotEmptyOrNull(list)) {
			for (DocumentoInfo x : list) {
				InventionDisclosureDocumentoKey key = new InventionDisclosureDocumentoKey();
				key.setInventionDisclosureId(id);
				key.setDocumentoId(x.getDocumentoId());
				create(new InventionDisclosureDocumento(key));
			}
		}		
	}
	private void eliminaDocumenti(Integer id, List<DocumentoInfo> list) {
		if (id!=null && Utile.isNotEmptyOrNull(list)) {
			for (DocumentoInfo x : list) {
				InventionDisclosureDocumentoKey key = new InventionDisclosureDocumentoKey();
				key.setInventionDisclosureId(id);
				key.setDocumentoId(x.getDocumentoId());
				InventionDisclosureDocumento idd = (InventionDisclosureDocumento) find(InventionDisclosureDocumento.class, key);
				if (idd==null) {
					delete(x);
					docService.delete(x.getDocumentoId());
				}
			}
		}		
	}
	public UtenteLdap getUtenteLdap(String uid) {
		return ldapService.readUtente(uid);
	}
	@SuppressWarnings("unchecked")
	public List<InventionDisclosure> leggiProposte(Parametri p) {
		if (p == null || p.isEmpty()) return null;		
		List<InventionDisclosure> list = find(new PropostaQuery().getQuery(p));
		return Utile.isEmptyOrNull(list) ? null : list;
	}
	private void creaTransizione(InventionDisclosure indi) {
		IdTransition x = new IdTransition();
		x.setId(null);
		x.setInventionDisclosure(indi.getId());
		x.setStato(indi.getStato());
		x.setNota(indi.getNotaRifiuto());
		x.setDataTransizione(Calendar.getInstance().getTime());
		x = (IdTransition) create(x);
	}
	@SuppressWarnings("unchecked")
	private List<IdTransition> getTransizioni(Integer id) {
		return findByQuery(XQuery.FIND_TRANSIZIONI_BY_PROPOSTA, id);
	}
}
