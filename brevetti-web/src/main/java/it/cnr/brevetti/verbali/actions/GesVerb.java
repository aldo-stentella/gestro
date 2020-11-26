package it.cnr.brevetti.verbali.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.domain.TrovatoQuery;
import it.cnr.brevetti.ejb.entities.Dipartimento;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.ejb.entities.Verbale;
import it.cnr.brevetti.gas.SessioneUtente;
import it.cnr.brevetti.login.exceptions.ExpiredSessionException;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.UtilityFunctions;

public class GesVerb extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ExpiredSessionException, Exception {
		HttpSession se=request.getSession(true);
		SessioneUtente user = (SessioneUtente)se.getAttribute("sessioneUtente");
		if(user==null)
			throw new ExpiredSessionException();
		TrovatoJB tjb = TrovatoJB.getInstance();
		Integer strutturaAfferenza= ((Dipartimento)se.getAttribute("dipartimento")).getId();
		if (request.getMethod().equals("GET")) {									//ricerca per NSRIF
			ArrayList<LabelValueBean> trovati = new ArrayList<LabelValueBean>();
			Parametri p = new Parametri();
			p.add(TrovatoQuery.AFFERENZA_ID, strutturaAfferenza);
			for(Trovato tro: tjb.getTrovati(p)){
				trovati.add(new LabelValueBean(""+tro.getNsrif()+": "+StringUtils.left(tro.getTitolo(),90), ""+tro.getNsrif()));
			}
			request.setAttribute("trovati", trovati);
			request.setAttribute("step", "trovati");
	    	return mapping.findForward("GesVerb");
		}else{
			if(request.getParameter("nsrif")!=null){
				Integer nsrif=Integer.parseInt(request.getParameter("nsrif"));
				List<Verbale>list = tjb.getVerbali(nsrif);
				ArrayList<LabelValueBean>verbali = new ArrayList<LabelValueBean>();
				if(list!=null) for(Verbale v : list){
					verbali.add(new LabelValueBean("Verb.del "+UtilityFunctions.itForm.format(v.getDataVerbale())+": "+StringUtils.substring(v.getAzione(), 0, 80)+"...",""+v.getId()));
				}
				request.setAttribute("verbali", verbali);
				request.setAttribute("step", "verbali");
				request.setAttribute("nsrif", nsrif);
			}else if(request.getParameter("id")!=null){
				Integer id=Integer.parseInt(request.getParameter("id"));
				Verbale v = tjb.getVerbale(id);
				for(Trovato t : v.getTrovati())
					if(t.getNsrif()==Integer.parseInt(request.getParameter("nsrif2"))){
						request.setAttribute("azione", t.getAzione());
						request.setAttribute("rifiuto", t.getRifiuto());
						break;
					}
				request.setAttribute("dataVerbale", UtilityFunctions.itForm.format(v.getDataVerbale()));
				request.setAttribute("nsrif2", request.getParameter("nsrif2"));
				request.setAttribute("step", "verbale");
			}
			return mapping.findForward("GesVerb");
		}
	}

}
