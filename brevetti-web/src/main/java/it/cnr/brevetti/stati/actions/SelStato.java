package it.cnr.brevetti.stati.actions;

import it.cnr.brevetti.ejb.entities.Stato;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.ServiceLocatorException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SelStato extends Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServiceLocatorException {
		if(request.getMethod().equals("POST")){
			String combo = request.getParameter("combo")+"";
			String id = combo.substring(combo.indexOf("|")+1);
			request.setAttribute("id",id);
			request.setAttribute("selected",combo.substring(0,combo.indexOf("(")));
		} else {
	    	TrovatoJB tjb = TrovatoJB.getInstance();
	    	List allStati = tjb.getStati();
	    	Stato sta;
	    	String cs ="";
			Iterator i = allStati.iterator();
			while (i.hasNext()){
				sta = (Stato)i.next();
				//TODO codificare i possibili status
				if(sta!=null && sta.getStatus()==1){					
					if(cs.length()>0) cs=cs.concat(",");
					cs=cs.concat("\""+ sta.getNome()+" ("+sta.getSigla()+")|"+sta.getId()+"\"");
				}
			}
	    	request.setAttribute("cs", cs);
		}
        return mapping.findForward("SelStato");
    }

}
