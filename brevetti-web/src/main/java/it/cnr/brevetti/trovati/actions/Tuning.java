package it.cnr.brevetti.trovati.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;
import it.cnr.brevetti.domain.AbstractQuery;
import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.domain.TrovatoQuery;
import it.cnr.brevetti.ejb.entities.Dipartimento;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.UtilityFunctions;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Tuning extends Action {
	private static final int maxLength = 200;
	
	    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    	TrovatoJB tjb = TrovatoJB.getInstance();
	    	HttpSession se=request.getSession(true);
			if(request.getMethod().equals("POST")){
				Parametri p = new Parametri();
				ArrayList<Trovato> list = new ArrayList<Trovato>();
				HashMap<Integer, Double> res = new HashMap<Integer, Double>();
				Double score;
				if(request.getParameter("keywords")!=null && request.getParameter("keywords").length()!=0){
					try {
						res = UtilityFunctions.searchEngineQuery(request.getParameter("keywords"));
					} catch (IOException e) {
						// TODO Visualizzare messaggio di errore nella jsp
						e.printStackTrace();
					}
					if(res.isEmpty())	return mapping.findForward("EvalCatalogo");
					request.setAttribute("keywords", request.getParameter("keywords"));
				}
				p.add(TrovatoQuery.AFFERENZA_ID, ((Dipartimento)se.getAttribute("dipartimento")).getId());
				p.add(TrovatoQuery.DATA_ABBANDONO, AbstractQuery.IS_NULL);
				list.addAll(tjb.getTrovati(p));
				SortedMap<Double, Trovato> ser = new TreeMap<Double, Trovato>(Collections.reverseOrder());
				if(!res.isEmpty()) {
					for (int i = 0; i<list.size(); i++) {
						Trovato trovato = (Trovato)list.get(i);
						if((score = res.get(trovato.getNsrif()))!=null){
							ser.put(score, trovato);
						}
					}
					request.setAttribute("ser",ser);
				}
				return mapping.findForward("EvalCatalogo");
			}
	        return mapping.findForward("Tuning");
	    }



}
