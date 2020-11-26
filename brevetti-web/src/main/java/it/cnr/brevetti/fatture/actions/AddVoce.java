package it.cnr.brevetti.fatture.actions;

import it.cnr.brevetti.ejb.entities.Causale;
import it.cnr.brevetti.ejb.entities.Stato;
import it.cnr.brevetti.ejb.entities.VoceFattura;
import it.cnr.brevetti.ejb.entities.VoceFatturaSigla;
import it.cnr.brevetti.fatture.actionForms.VoceForm;
import it.cnr.brevetti.fatture.javabeans.FatturaPassivaJB;
import it.cnr.brevetti.util.UtilityFunctions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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
import org.apache.struts.util.LabelValueBean;

public class AddVoce extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession se=request.getSession(true);
		FatturaPassivaJB fjb = FatturaPassivaJB.getInstance();
		ArrayList<LabelValueBean> nazioni = new ArrayList<LabelValueBean>();
		ArrayList<LabelValueBean> causali = new ArrayList<LabelValueBean>();
		SortedMap<Long, VoceFattura>vociFattura =(TreeMap<Long, VoceFattura>)se.getAttribute("vociFattura");
		if(vociFattura==null)	vociFattura = new TreeMap<Long, VoceFattura>();
		VoceForm vf = new VoceForm();
		VoceFattura voceFattura = new VoceFattura();
		if (request.getMethod().equals("GET")) {
			Long id = Long.decode(request.getParameter("id"));
			Long prec = Long.decode(request.getParameter("prec"));
			voceFattura = vociFattura.get(id);
			if(voceFattura!=null){
				BeanUtils.copyProperties(vf, voceFattura);
				vf.setIdRiga(id);
			}else{
				voceFattura = vociFattura.get(prec);
				vf.setIdRiga(prec+1);
				vf.setNsrif(voceFattura.getNsrif());
				se.setAttribute("vocefatturasigla", voceFattura.getVoceFatturaSigla());
			}
			//vf.setImposta((int)UtilityFunctions.IMPOSTA);
			for(Stato sta: fjb.getStati()){
				if(sta!=null && (sta.getStatus()==1 || (sta.getContinente()!=null && sta.getContinente().startsWith("PROCEDURE"))))
					nazioni.add(new LabelValueBean(sta.getNome(),""+sta.getId()));
			}
			Collections.sort(nazioni);
			for(Causale cau: fjb.getCausali())
				causali.add(new LabelValueBean(cau.getNome() , ""+cau.getId()));
			request.setAttribute("voceFattura", vf);
		} else {										//Method == POST
			vf = (VoceForm)request.getAttribute("voceFattura");
			if(vf.getId()!=null && vf.getId()!=0){
				voceFattura = vociFattura.get(vf.getIdRiga());
				BeanUtils.copyProperties(voceFattura, vf);
			} else {
				BeanUtils.copyProperties(voceFattura, vf);
				voceFattura.setId(UtilityFunctions.generateRandomId());
				voceFattura.setVoceFatturaSigla((VoceFatturaSigla) se.getAttribute("vocefatturasigla"));
				//TODO generare correttamente la posizione
				vociFattura.put(vf.getIdRiga(), voceFattura);
			}
			voceFattura.setParzialeEuro(
					voceFattura.getAnticipazione()
					.add(voceFattura.getOnorari()
							.add(voceFattura.getIva())).setScale(UtilityFunctions.PRECISION, BigDecimal.ROUND_HALF_UP));
			voceFattura.setCausale(fjb.getCausale(voceFattura.getCausaliId()));
			voceFattura.setStato(fjb.getStato(voceFattura.getStatiId()));
			request.setAttribute("act", "close");
		}
		request.setAttribute("nazioni", nazioni);
		request.setAttribute("causali", causali);
		return mapping.findForward("AddVoce");
	}

}
