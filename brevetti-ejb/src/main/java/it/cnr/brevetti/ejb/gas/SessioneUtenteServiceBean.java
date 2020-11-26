package it.cnr.brevetti.ejb.gas;

import javax.ejb.Stateless;

import com.google.gson.Gson;

import it.cnr.brevetti.gas.GasBean;
import it.cnr.brevetti.gas.SessioneUtente;

/**
 * Implementazione servizio gestione login
 * @author Aurelio D'Amico
 * @version 1.0 [14-Apr-08]
 * @version 2.0 [23-Jun-17] REST
 */
@Stateless
public class SessioneUtenteServiceBean extends GasRestService
	implements SessioneUtenteService {
		
	public SessioneUtente getSessioneUtente(String uid) throws Exception {
		return getSessioneGas(uid,null);
	}	
	public SessioneUtente getSessioneUtente(String uid, String struttura) throws Exception {
		return getSessioneGas(uid, struttura);
	}
	private SessioneUtente getSessioneGas(String uid, String struttura) throws Exception {
		GasBean bean = new GasBean();
		bean.setUid(uid);
		bean.setStruttura(struttura);
		String json = postJson(getGasSessioneUrl(), getJson(bean));
		SessioneUtente sessione = new Gson().fromJson(json, SessioneUtente.class);
		return sessione;
	}
}
