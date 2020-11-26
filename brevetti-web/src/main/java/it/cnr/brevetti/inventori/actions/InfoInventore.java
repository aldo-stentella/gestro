package it.cnr.brevetti.inventori.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import it.cnr.brevetti.ejb.entities.DipendenteCnr;
import it.cnr.brevetti.ejb.entities.Inventore;
import it.cnr.brevetti.inventori.actionForms.InventoreForm;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.ServiceLocatorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class InfoInventore extends Action {
	
	    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServiceLocatorException, IllegalAccessException, InvocationTargetException {
	    	TrovatoJB tjb = TrovatoJB.getInstance();
	    	HttpSession se=request.getSession(true);
			if(request.getMethod().equals("GET")){
					InventoreForm iForm = new InventoreForm();
					Integer id = Integer.decode(request.getParameter("id"));
					Inventore inv = null;
					if(id>0){
						inv = tjb.getInventore(id);
						if(inv.getTipo().equalsIgnoreCase("CNR")){							
							DipendenteCnr dip = tjb.getDipendenteByMatricola(inv.getMatricolaCnr());
							BeanUtils.copyProperties(iForm, dip);
						} else
							BeanUtils.copyProperties(iForm, inv);							
					} else {
						HashMap<Integer, Inventore> map = (HashMap) se.getAttribute("nuoviInventori");
						inv = map.get(id);
						BeanUtils.copyProperties(iForm, inv);							
					}
					request.setAttribute("iform", iForm);
			}
						
	        return mapping.findForward("InfoInventore");
	    }



}
