package it.cnr.brevetti.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.cnr.brevetti.ejb.entities.Inventore;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.ejb.facade.GestioneTrovatoService;
import it.cnr.brevetti.util.ServiceLocator;
import it.cnr.brevetti.util.Utile;

@Path("/trovato")
@Produces(MediaType.APPLICATION_JSON)
public class TrovatoRestService extends AbstractRestService {
	
	@GET
	@Path("{id}")
	public Response getTrovato(@PathParam("id") Integer id) {
		TrovatoBean trovato = null;
		try {
			trovato = findTrovato(id);
		} catch (Exception e) {
			return getInternalServerError(e);
		}
		return trovato==null ? getNotFoundError(null) : getOk(trovato);	
	}
	@GET
	@Path("/valido/{id}")
	public Response getTrovatoValido(@PathParam("id") Integer id) {
		TrovatoBean trovato = null;
		try {
			trovato = findTrovatoValido(id);
		} catch (Exception e) {
			return getInternalServerError(e);
		}
		return trovato==null ? getNotFoundError(null) : getOk(trovato);	
	}
	
	private TrovatoBean findTrovato(Integer nsrif) throws Exception {
		Trovato x = getService().getTrovato(nsrif, null);
		if (x==null) return null;
		TrovatoBean bean = new TrovatoBean();
		bean.setNsrif(x.getNsrif());
		bean.setTitolo(x.getTitolo());
		List<Inventore> list = x.getInventori(); 
		if (Utile.isNotEmptyOrNull(list)) {
			if (x.getInventoreIndex()!=null) {
				Inventore inv = list.get(x.getInventoreIndex());
				bean.setInventore(inv.getNome() + " " + inv.getCognome());
			}
		}		
		return bean;		
	}
	
	private TrovatoBean findTrovatoValido(Integer nsrif) throws Exception {
		TrovatoBean x = findTrovato(nsrif);
		if (x==null) return null;
		List<?> list = getService().getTrovatoDipartimento(nsrif);
		if (Utile.isEmptyOrNull(list)) return null;
		return x;		
	}
	private GestioneTrovatoService getService() throws Exception {
		return ServiceLocator.getInstance().getGestioneTrovatiFacade();
	}
	
}
