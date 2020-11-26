package it.cnr.brevetti.classificazioniInternDep.actions;

import it.cnr.brevetti.ejb.entities.ClassificazioneInternDep;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class DelClasse extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession se=request.getSession(true);
		TreeMap<Integer, ClassificazioneInternDep> mapCla = (TreeMap<Integer, ClassificazioneInternDep>)se.getAttribute("classificazioni");
		Integer id = Integer.decode(request.getParameter("id"));
		if (request.getMethod().equals("GET")) {
			request.setAttribute("classe", mapCla.get(id));
			request.setAttribute("act", "confirm");
		}else{							//POST
			if(mapCla.containsKey(id)){
				mapCla.remove(id);
			}
			request.setAttribute("act", "close");
		}
		return mapping.findForward("DelClasse");
	}

}
