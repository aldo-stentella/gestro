package it.cnr.brevetti.fatture.actions;

import it.cnr.brevetti.ejb.entities.Dipartimento;
import it.cnr.brevetti.ejb.entities.Fattura;
import it.cnr.brevetti.ejb.entities.StudioBrevettuale;
import it.cnr.brevetti.ejb.entities.VoceFattura;
import it.cnr.brevetti.fatture.actionForms.FatturaForm;
import it.cnr.brevetti.fatture.javabeans.FatturaPassivaJB;
import it.cnr.brevetti.login.exceptions.ExpiredSessionException;
import it.cnr.brevetti.util.UtilityFunctions;

import java.math.BigDecimal;
import java.util.ArrayList;
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

public class Fatture extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ExpiredSessionException, Exception {
		HttpSession se=request.getSession(true);
		FatturaPassivaJB fjb = FatturaPassivaJB.getInstance();
		//HashMap<Integer, VoceFattura>vociFattura = new HashMap<Integer, VoceFattura>();
		SortedMap<Long, VoceFattura> vociFattura = new TreeMap<Long, VoceFattura>();
		Fattura fattura = new Fattura();
		FatturaForm ff = new FatturaForm();
		if (request.getMethod().equals("GET")) {
			fattura = (Fattura)se.getAttribute("datiFattura");
			boolean readOnly = (request.getParameter("readonly").startsWith("y"));
			//UtilityFunctions.cleanSession(se);
			se.removeAttribute("datiFattura");
			if(fattura!=null){
				BeanUtils.copyProperties(ff, fattura);
				ff.setReadOnly(readOnly);
				ff.setStudioDescrizione(fattura.getStudioBrevettuale()!=null?fattura.getStudioBrevettuale().getDenominazione()+" - "+fattura.getStudioBrevettuale().getCitta().replaceAll("NON DISP.", ""):"NON DISP.");
				int xx = 0;
				if(fattura.getVociFatture()!=null)	for(VoceFattura vf : fattura.getVociFatture()){
					vociFattura.put((vf.getVoceFatturaSigla().getProgressivoRiga()*100)+xx++, vf);
				}
				request.setAttribute("datiFattura", ff);
			}
			se.setAttribute("vociFattura", vociFattura);
		}else {
			vociFattura = (TreeMap<Long, VoceFattura>)se.getAttribute("vociFattura");
			ff = (FatturaForm)request.getAttribute("datiFattura");
			BigDecimal rowSum = new BigDecimal(0);
			//MathContext mc = new MathContext(UtilityFunctions.PRECISION);
			for (VoceFattura voceFattura : vociFattura.values()) {
				rowSum = voceFattura.getParzialeEuro().add(rowSum);
			}
			ff.setImporto(rowSum.setScale(UtilityFunctions.PRECISION, BigDecimal.ROUND_HALF_UP));
			if(request.getParameter("write").startsWith("1")){
				BeanUtils.copyProperties(fattura, ff);
				fattura.setDipartimentoId(((Dipartimento)se.getAttribute("dipartimento")).getId());
				if(ff.getUpdDet()!=null && ff.getUpdDet()==1){
					if(fattura.getVociFatture() == null) fattura.setVociFatture(new ArrayList<VoceFattura>());
					fattura.getVociFatture().addAll(vociFattura.values());
				}
				try {
					if(fattura.getId()!=null && fattura.getId()!=0)
						fjb.aggiornaFattura(fattura);
					else {
						fattura.setId(null);
						Integer id = fjb.salvaFattura(fattura).getId();
						ff.setId(id);
					}
					StudioBrevettuale stu = fjb.getStudioBrevettuale(fattura.getStudioBrevettualeId());
					ff.setStudioDescrizione(stu.getDenominazione()+" - "+stu.getCitta().replaceAll("NON DISP.", ""));
				} catch (RuntimeException e) {
					e.printStackTrace();
					throw e;
				}
				return mapping.findForward("successful");
			}
		}
    	return mapping.findForward("fatture");
	}
}
