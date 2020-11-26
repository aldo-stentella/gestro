package it.cnr.brevetti.login.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class HomeAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
        HttpSession se=request.getSession(true);
        if (se.getAttribute("sessioneUtente")!=null) {
        	return mapping.findForward("home");
        }
        return mapping.findForward("login");     
	}
}
