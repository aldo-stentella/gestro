package it.cnr.brevetti.classificazioniInternDep.actions;

import it.cnr.brevetti.ejb.entities.Classificazione;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InfoClasse extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TrovatoJB tjb = TrovatoJB.getInstance();
		String ids = request.getParameter("id");
		try{
			Classificazione temp = tjb.getClassificazione(Integer.decode(ids));
			if(temp.getLivello()==3){
				request.setAttribute("cl3", temp.getNome());
				temp = tjb.getClassificazione(temp.getIdPadre());
			}
			if(temp.getLivello()==2){
				request.setAttribute("cl2", temp.getNome());
				temp = tjb.getClassificazione(temp.getIdPadre());
			}
			request.setAttribute("cl1", temp.getNome());					
		} catch (Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("InfoClasse");
	}

}
