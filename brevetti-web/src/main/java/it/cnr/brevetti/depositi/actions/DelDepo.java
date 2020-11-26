package it.cnr.brevetti.depositi.actions;

import it.cnr.brevetti.depositi.actionForms.DepositoForm;
import it.cnr.brevetti.ejb.entities.DepEst;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class DelDepo extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession se=request.getSession(true);
		TrovatoJB tjb = TrovatoJB.getInstance();
		DepositoForm dform;
		DepEst dep;
		Long key;
		List<DepEst> listD = (List<DepEst>) se.getAttribute("depositi");
		SortedMap<Long, Integer> mapDep = (SortedMap<Long, Integer>)se.getAttribute("mapdep");
		ArrayList<LabelValueBean> idiomi = new ArrayList<LabelValueBean>();
		ArrayList<LabelValueBean> nazioni = new ArrayList<LabelValueBean>();
		ArrayList<LabelValueBean> denoms = new ArrayList<LabelValueBean>();

		if (request.getMethod().equals("GET")) {
			dform = new DepositoForm();
			key = Long.decode(request.getParameter("id"));
			if((dep = listD.get(mapDep.get(key)))!=null){
				BeanUtils.copyProperties(dform, dep);
				dform.setKey(key);
				nazioni.add(new LabelValueBean(tjb.getStato(dep.getStatoId()).getNome(),""+dep.getStatoId()));
				if(dep.getIdiomiId()!=null && dep.getIdiomiId()!=0)
					idiomi.add(new LabelValueBean(tjb.getIdioma(dep.getIdiomiId()).getNome(),""+dep.getIdiomiId()));
				if(dep.getStudioBrevettualeId()!=null && dep.getStudioBrevettualeId()!=0)
					denoms.add(new LabelValueBean(tjb.getStudioBrevettuale(dep.getStudioBrevettualeId()).getDenominazione(),""+dep.getStudioBrevettualeId()));
				request.setAttribute("datiDepEst", dform);
			}
		}else{							//POST
			dform = (DepositoForm)form;
			key = dform.getKey();
			int pos = mapDep.get(key);
			listD.remove(pos);
			mapDep.remove(key);
			Integer elm;
			for (Long kk : mapDep.keySet()) {
				if((elm = mapDep.get(kk))>pos)
					mapDep.put(kk, elm-1);
			}
			request.setAttribute("act", "close");
		}
		request.setAttribute("nazioni", nazioni);
		request.setAttribute("idiomi", idiomi);
		request.setAttribute("denoms", denoms);
		return mapping.findForward("DelDepo");
	}

}
