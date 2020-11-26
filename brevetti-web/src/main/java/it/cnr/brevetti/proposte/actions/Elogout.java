package it.cnr.brevetti.proposte.actions;

import it.cnr.brevetti.login.exceptions.LoginFailureException;
import it.cnr.brevetti.util.ServiceLocatorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Elogout extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServiceLocatorException, LoginFailureException {
        HttpSession se=request.getSession(true);
        se.removeAttribute("sessioneEutente");
        return mapping.findForward("Elogin");
	}
}
