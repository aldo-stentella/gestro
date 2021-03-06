package it.cnr.brevetti.login.actions;

import it.cnr.brevetti.util.ServiceLocatorException;
import it.cnr.brevetti.util.UtilityFunctions;
import it.cnr.brevetti.gas.SessioneUtente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LogoutAction extends Action {

	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServiceLocatorException, Exception {
        HttpSession se=request.getSession(true);
        SessioneUtente user = (SessioneUtente)se.getAttribute("sessioneUtente");
        if (user!=null) {
        	System.out.println("� - "+user.getUtenteId()+" ha effettuato il logout.");
        	se.removeAttribute("sessioneUtente");
        	se.removeAttribute("dipartimento");
        	se.removeAttribute("multiDipartimento");
        	se.removeAttribute("amministraTrovati");
        	UtilityFunctions.cleanSession(se);
        }
        request.setAttribute("action", "close");
        return mapping.findForward("close");
	}
}
