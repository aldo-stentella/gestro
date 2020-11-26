package it.cnr.brevetti.entiEsterni.actions;

import java.lang.reflect.InvocationTargetException;
import it.cnr.brevetti.ejb.entities.EnteEsterno;
import it.cnr.brevetti.entiEsterni.actionForms.EnteEsternoForm;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.ServiceLocatorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class InfoEnte extends Action {
	
	    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServiceLocatorException, IllegalAccessException, InvocationTargetException {
	    	TrovatoJB tjb = TrovatoJB.getInstance();
			if(request.getMethod().equals("GET")){
					EnteEsternoForm eForm = new EnteEsternoForm();
					Integer id = Integer.decode(request.getParameter("id"));
					EnteEsterno ee = null;
					ee = tjb.getEnteEsterno(id);
					BeanUtils.copyProperties(eForm, ee);
					if(eForm.getStatoId()!=null && eForm.getStatoId().intValue()>0)	eForm.setNazNome(tjb.getStato(eForm.getStatoId()).getNome());
					request.setAttribute("eform", eForm);
			}
	        return mapping.findForward("InfoEnte");
	    }
	    
}
