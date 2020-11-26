package it.cnr.brevetti.proposte.actions;

import it.cnr.brevetti.login.exceptions.LoginFailureException;
import it.cnr.brevetti.proposte.javabeans.PropostaJB;
import it.cnr.brevetti.util.ServiceLocatorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Elogin extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServiceLocatorException, LoginFailureException {
        HttpSession se=request.getSession(true);
        String username = request.getParameter("login");
        if (request.getMethod().equals("POST")) {
        	PropostaJB pjb = PropostaJB.getInstance();
        	String password = request.getParameter("password");
        	if(pjb.login(username, password)){
					System.out.println("§ - l'utente LDAP "+username+" ha effettuato il login.");
        			se.setAttribute("sessioneEutente", username);
        	} else
        		throw new LoginFailureException();
        }else{
        	username = (String) se.getAttribute("sessioneEutente");
        	if(StringUtils.isBlank(username))
        		return mapping.findForward("login");
        }
        return mapping.findForward("Ehome");
	}
}
