package it.cnr.brevetti.trovati.actions;

import it.cnr.brevetti.domain.DocumentoQuery;
import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.ejb.entities.DocumentoInfo;
import it.cnr.brevetti.login.exceptions.ExpiredSessionException;
import it.cnr.brevetti.trovati.actionForms.TrovatoForm;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.gas.SessioneUtente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DelDocumenti extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ExpiredSessionException, Exception {
		HttpSession se=request.getSession(true);
		SessioneUtente user = (SessioneUtente)se.getAttribute("sessioneUtente");
		if(user==null)
			throw new ExpiredSessionException();
		TrovatoJB tjb = TrovatoJB.getInstance();
		TrovatoForm dform = (TrovatoForm)se.getAttribute("datiTrovato");
		List<DocumentoInfo> documenti = (List<DocumentoInfo>) se.getAttribute("documenti");
		HashMap<String, List<DocumentoInfo>>fascicoli = (HashMap<String, List<DocumentoInfo>>) se.getAttribute("fascicoli");
		List<DocumentoInfo> lista;
		Parametri p = new Parametri();
		if (request.getMethod().equals("GET")) {
			Integer entita = Integer.parseInt(request.getParameter("entita"));
			Integer tipoDocumentoId = Integer.parseInt(request.getParameter("tipoDocumentoId"));
			Integer id = Integer.parseInt(request.getParameter("id"));
			String key = entita+"|"+tipoDocumentoId;
			if(fascicoli==null){
				fascicoli = new HashMap<String, List<DocumentoInfo>>();
				se.setAttribute("fascicoli", fascicoli);
			}
			if(fascicoli.containsKey(key)){
				lista = fascicoli.get(key);
			} else {
				p.add(DocumentoQuery.NSRIF, dform.getNsrif());
				p.add(DocumentoQuery.ENTITA, entita);
				p.add(DocumentoQuery.TIPO_DOCUMENTO, tipoDocumentoId);
				lista = tjb.getDocumentiInfo(p);
				if(lista==null)  throw new Exception("Errore nella sessione."); //stato di incoerenza: impossibile cancellare un elemento di una lista vuota.
				fascicoli.put(key, lista);
			}
			for(DocumentoInfo x: lista){
				if(x.getDocumentoId().equals(id)){
					lista.remove(x);
					break;
				}
			}
			for(DocumentoInfo x: documenti){
				if(x.getDocumentoId().equals(id)){
					documenti.remove(x);
					//dform.setUpdDoc(1);
					
					/*/TODO workaround in attesa che venga corretto errore in GestioneTrovatoService.aggiornaDocumenti() 
					List<Integer> eliminati = (List<Integer>) se.getAttribute("docEliminati");
					if(eliminati==null){
						eliminati = new ArrayList<Integer>();
						se.setAttribute("docEliminati", eliminati);
					}
					eliminati.add(id);
					FINE/*/
					break;
				}
			}
			request.setAttribute("act", "close");
			return mapping.findForward("documenti");
		} 
		return mapping.findForward("updateAndClose");
	}
}
