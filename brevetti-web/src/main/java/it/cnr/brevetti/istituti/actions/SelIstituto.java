package it.cnr.brevetti.istituti.actions;

import java.util.Iterator;
import java.util.List;

import it.cnr.brevetti.ejb.entities.Istituto;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.ServiceLocatorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SelIstituto extends Action {
	
	    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServiceLocatorException {
			if(request.getMethod().equals("POST")){
				String combo = request.getParameter("combo")+"";
				String id = combo.substring(combo.indexOf("|")+1);
				request.setAttribute("id",id);
				request.setAttribute("selected",combo.substring(0,combo.indexOf("|")));
			} else {
		    	TrovatoJB tjb = TrovatoJB.getInstance();
		    	List allIstituti = tjb.getIstituti();
		    	Istituto ist;
		    	String cs ="";
				Iterator i = allIstituti.iterator();
				while (i.hasNext()){
					if(cs.length()>0) cs=cs.concat(",");
					ist = (Istituto)i.next();
					if(ist!=null)
						cs=cs.concat("\""+ ist.getIstitutoSigla()+" - "+ist.getNome().replaceAll("\"","\\\\\"")+"|"+ist.getId()+"\"");
				}
		    	request.setAttribute("cs", cs);
			}
	        return mapping.findForward("SelIstituto");
	    }



}
