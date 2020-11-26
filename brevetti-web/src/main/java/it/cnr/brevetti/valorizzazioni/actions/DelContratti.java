package it.cnr.brevetti.valorizzazioni.actions;

import it.cnr.brevetti.login.exceptions.ExpiredSessionException;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.gas.SessioneUtente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DelContratti extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ExpiredSessionException, Exception {
		HttpSession se=request.getSession(true);
		SessioneUtente user = (SessioneUtente)se.getAttribute("sessioneUtente");
		if(user==null)
			throw new ExpiredSessionException();
		TrovatoJB tjb = TrovatoJB.getInstance();
		if (request.getMethod().equals("GET")) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			tjb.delDocumentoInfo(id);
		} 
		return mapping.findForward("updateAndClose");
	}
}
