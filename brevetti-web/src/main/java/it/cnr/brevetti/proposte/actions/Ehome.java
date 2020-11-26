package it.cnr.brevetti.proposte.actions;

import java.util.List;

import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.domain.PropostaQuery;
import it.cnr.brevetti.ejb.entities.InventionDisclosure;
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

public class Ehome extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServiceLocatorException {
        HttpSession se=request.getSession(true);
    	String username = (String)se.getAttribute("sessioneEutente");
    	PropostaJB pjb = PropostaJB.getInstance();
    	if(StringUtils.isEmpty(username))
    		return mapping.findForward("Elogin");
    	Parametri p = Parametri.getNewParametri(PropostaQuery.UTENTE_LDAP , username);
    	List<InventionDisclosure>proposte = pjb.leggiProposte(p);
    	if(proposte!=null) for (InventionDisclosure inventionDisclosure : proposte) {
			if(inventionDisclosure.getStato()==InventionDisclosure.DA_INTEGRARE)
				return mapping.findForward("EselPro");
		}
        return mapping.findForward("Ehome");
	}
}
