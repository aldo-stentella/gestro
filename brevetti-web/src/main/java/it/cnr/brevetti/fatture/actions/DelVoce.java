package it.cnr.brevetti.fatture.actions;

import it.cnr.brevetti.ejb.entities.VoceFattura;
import it.cnr.brevetti.fatture.actionForms.VoceForm;
import it.cnr.brevetti.fatture.javabeans.FatturaPassivaJB;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DelVoce extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession se=request.getSession(true);
		FatturaPassivaJB fjb = FatturaPassivaJB.getInstance();
		SortedMap<Long, VoceFattura>vociFattura =(TreeMap<Long, VoceFattura>)se.getAttribute("vociFattura");
		Long id = Long.decode(request.getParameter("idRiga"));
		if (request.getMethod().equals("GET")) {
			VoceFattura voceFattura = vociFattura.get(id);
			VoceForm vf = new VoceForm();
			if(voceFattura!=null){
				BeanUtils.copyProperties(vf, voceFattura);
				vf.setCausaleDescrizione(fjb.getCausale(vf.getCausaliId()).getNome());
				vf.setStatoDescrizione(fjb.getStato(vf.getStatiId()).getNome());
				vf.setIdRiga(id);
			}
			request.setAttribute("voceFattura", vf);
			request.setAttribute("act", "confirm");
		}else{							//POST
			if(vociFattura.containsKey(id)){
				vociFattura.remove(id);
			}
			request.setAttribute("act", "close");
		}
		return mapping.findForward("DelVoce");
	}
}
