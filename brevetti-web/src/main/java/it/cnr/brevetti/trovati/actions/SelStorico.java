package it.cnr.brevetti.trovati.actions;

import it.cnr.brevetti.ejb.entities.Dipartimento;
import it.cnr.brevetti.ejb.entities.EnteEsterno;
import it.cnr.brevetti.ejb.entities.Stato;
import it.cnr.brevetti.ejb.entities.StoricoTitolarita;
import it.cnr.brevetti.entiEsterni.actionForms.EnteEsternoForm;
import it.cnr.brevetti.trovati.actionForms.TrovatoForm;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.UtilityFunctions;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class SelStorico extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		TrovatoJB tjb = TrovatoJB.getInstance();
		List<Dipartimento> dips = tjb.getDipartimenti();
		for (int i = 0; i<dips.size();) {
			Dipartimento dipartimento = dips.get(i);
			if(dipartimento.getDataSoppressione()!=null)
				dips.remove(i);
			else
				i++;
		}
		HttpSession se = request.getSession(true);
		TrovatoForm dform = (TrovatoForm) se.getAttribute("datiTrovato");
		String inv = "";
		if (request.getMethod().equals("POST")) {
			StoricoTitolarita tit = new StoricoTitolarita();
			HashMap<Integer, StoricoTitolarita> mapSto = (HashMap<Integer, StoricoTitolarita>)se.getAttribute("mapSto");
			if(mapSto==null) {
				mapSto = new HashMap<Integer, StoricoTitolarita>();
				se.setAttribute("mapSto", mapSto);
			}
			Integer titolaritaId = Integer.valueOf(request.getParameter("titolaritaId"));
			if(titolaritaId.intValue()==0)	titolaritaId = UtilityFunctions.generateRandomId();		//in caso di nuova titolarità
			if(titolaritaId.intValue()<0)	tit.setPercentuale(new BigDecimal(0));									//azzera la percentuale				
			tit.setId(titolaritaId);
			if(request.getParameter("act").startsWith("cerca")){
				String tipiTitolare = request.getParameter("tipi_titolare");
				tit.setTipiTitolareId(Integer.valueOf(tipiTitolare));
				if (tipiTitolare.startsWith("1")) {
					tit.setFrkSoggettoId(Integer.parseInt(request.getParameter("dipartimenti")));
					tit.setDipartimento(dips.get(tit.getFrkSoggettoId()));
				} else if (tipiTitolare.startsWith("3")) {
					inv=request.getParameter("inventori");
					tit.setFrkSoggettoId(Integer.valueOf(inv.substring(0,inv.indexOf("|"))));
					tit.setInventore(tjb.getInventore(tit.getFrkSoggettoId()));
				} else if (tipiTitolare.startsWith("4")) {
					String combo = request.getParameter("combo")+"";
					EnteEsterno ee = tjb.getEnteEsterno(Integer.valueOf(combo.substring(combo.indexOf("|")+1)));
					EnteEsternoForm eef = new EnteEsternoForm();
					BeanUtils.copyProperties(eef, ee);
					if(eef.getStatoId()!=null && eef.getStatoId().intValue()>0)	eef.setNazNome(tjb.getStato(eef.getStatoId()).getNome());
					request.setAttribute("eform", eef);
					se.setAttribute("titolaritaId", titolaritaId);
					request.setAttribute("status", "conferma");
					return mapping.findForward("FormEnte");
				}
			} else if(request.getParameter("act").startsWith("ok")){
				EnteEsternoForm eef = (EnteEsternoForm)form;
				EnteEsterno ee = tjb.getEnteEsterno(eef.getId());
				if(ee==null){			//nuovo ente
					ee = new EnteEsterno();
					BeanUtils.copyProperties(ee, eef);
					ee.setId(null);
					ee.setStatus(1);
				}
				tit.setEnteEsterno(ee);
				tit.setTipiTitolareId(4);
				tit.setFrkSoggettoId(ee.getId());
				mapSto.put(titolaritaId, tit);
				se.removeAttribute("titolaritaId");
				request.setAttribute("status", "chiudi");
				return mapping.findForward("FormEnte");
			} else { 		//crea nuovo ente
				ArrayList<LabelValueBean> nazioni = new ArrayList<LabelValueBean>();
				for(Iterator i = tjb.getStati().iterator(); i.hasNext();){
					Stato sta = (Stato)i.next();
					if(sta!=null && sta.getStatus()==1)
						nazioni.add(new LabelValueBean(sta.getNome(),""+sta.getId()));
				}
				Collections.sort(nazioni);
		    	se.setAttribute("nazioni", nazioni);
				se.setAttribute("titolaritaId", titolaritaId);
				request.setAttribute("status", "crea");
				request.removeAttribute("eform");
				return mapping.findForward("FormEnte");
			}
			mapSto.put(titolaritaId, tit);
		} else {								//method=GET
			ArrayList<String> invNomi = new ArrayList<String>(0);
			Integer idInv;
			int x = 100;
			String sx;
			Method met;
			Class args[] = { };
			Object obj[] = { };
			if (dform != null)
				for (int i = 0; i < 15; i++) {
					/* trick to obtain 2-digit series like 00,01,02..10,11.. */
					sx = ("" + x++).substring(1);
					met = TrovatoForm.class.getMethod("getInventoriId"+sx, args);
					idInv = (Integer)met.invoke(dform, obj);
					met = TrovatoForm.class.getMethod("getInventore"+sx, args);
					inv = (String)met.invoke(dform, obj);
					if(idInv!=null && idInv.intValue()!=0)				
						invNomi.add(idInv+"|"+inv);		      				
				}
			Iterator i = tjb.getEntiEsterni().iterator();
			String cs = "";
			while (i.hasNext()){
				EnteEsterno ee = (EnteEsterno)i.next();
				if(ee!=null && ee.getStatus()!=0){
					if(cs.length()>0) cs=cs.concat(",");
					cs=cs.concat("\""+ ee.getNome().replaceAll("\"","\\\\\"")+"|"+ee.getId()+"\"");
				}
			}
			request.setAttribute("cs", cs);
			request.setAttribute("allDip", dips.iterator());
			request.setAttribute("allInv", invNomi.iterator());
		}
		return mapping.findForward("SelTitolare");
	}

}
