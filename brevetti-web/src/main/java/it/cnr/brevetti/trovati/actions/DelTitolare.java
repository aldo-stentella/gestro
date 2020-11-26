package it.cnr.brevetti.trovati.actions;

import it.cnr.brevetti.ejb.entities.Titolarita;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class DelTitolare extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession se=request.getSession(true);
		HashMap<Integer, Titolarita> mapTit = (HashMap<Integer, Titolarita>)se.getAttribute("mapTit");
		Integer id = Integer.decode(request.getParameter("id"));
		if (request.getMethod().equals("GET")) {
			request.setAttribute("titolare", mapTit.get(id));
			request.setAttribute("act", "confirm");
		}else{							//POST
			if(mapTit.containsKey(id)){
				mapTit.remove(id);
			}
			request.setAttribute("act", "close");
		}
		return mapping.findForward("DelTitolare");
	}

}
