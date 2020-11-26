package it.cnr.brevetti.trovati.actions;

import it.cnr.brevetti.ejb.entities.StoricoTitolarita;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class DelStorico extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession se=request.getSession(true);
		HashMap<Integer, StoricoTitolarita> mapSto = (HashMap<Integer, StoricoTitolarita>)se.getAttribute("mapSto");
		Integer id = Integer.decode(request.getParameter("id"));
		if (request.getMethod().equals("GET")) {
			request.setAttribute("titolare", mapSto.get(id));
			request.setAttribute("act", "confirm");
		}else{							//POST
			if(mapSto.containsKey(id)){
				mapSto.remove(id);
			}
			request.setAttribute("act", "close");
		}
		return mapping.findForward("DelStorico");
	}

}
