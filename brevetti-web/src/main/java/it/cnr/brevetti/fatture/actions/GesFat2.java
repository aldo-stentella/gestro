package it.cnr.brevetti.fatture.actions;

import it.cnr.brevetti.domain.FatturaQuery;
import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.domain.TrovatoQuery;
import it.cnr.brevetti.ejb.entities.Dipartimento;
import it.cnr.brevetti.ejb.entities.Fattura;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.fatture.actionForms.FatturaQueryForm;
import it.cnr.brevetti.fatture.javabeans.FatturaAttivaJB;
import it.cnr.brevetti.login.exceptions.ExpiredSessionException;
import it.cnr.brevetti.sigla.fatture.attive.FatturaAttiva;
import it.cnr.brevetti.util.UtilityFunctions;
import it.cnr.brevetti.gas.SessioneUtente;

import java.util.ArrayList;
import java.util.HashMap;
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

public class GesFat2 extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ExpiredSessionException, Exception {
		HttpSession se=request.getSession(true);
		SessioneUtente user = (SessioneUtente)se.getAttribute("sessioneUtente");
		if(user==null)
			throw new ExpiredSessionException();
		Integer strutturaAfferenza= ((Dipartimento)se.getAttribute("dipartimento")).getId();
		FatturaAttivaJB fjb = FatturaAttivaJB.getInstance();
		FatturaQueryForm fqf = (FatturaQueryForm)request.getAttribute("fatturaQuery");
		request.setAttribute("tabNumber", new Integer(fqf.getNextab()));
        Parametri p = new Parametri();
		if (request.getMethod().equals("GET")) {
			p = (Parametri)se.getAttribute("lastQBEParam");
			UtilityFunctions.cleanSession(se);
			se.setAttribute("lastQBEParam", p);
        	if (request.getParameter("lastQBE")!=null && request.getParameter("lastQBE").startsWith("y")){
        		if(p!=null){
        			request.setAttribute("list", fjb.getFatture(p)); //parametri
        			return mapping.findForward("selfat");
        		}
        	}
			ArrayList<LabelValueBean> trovati = new ArrayList<LabelValueBean>();
			p = new Parametri();
			p.add(TrovatoQuery.AFFERENZA_ID, strutturaAfferenza);
			for(Trovato tro: fjb.getTrovati(p)){
				trovati.add(new LabelValueBean(""+tro.getNsrif()+":"+StringUtils.left(tro.getTitolo(),90), ""+tro.getNsrif()));
			}
			request.setAttribute("trovati", trovati);
	    	return mapping.findForward("2.qbefat");
		}else{
			if(fqf.getIdSigla()!=null && fqf.getIdSigla().length()!=0){				//selezione fattura da lista prodotta con QBE
				String[] parms = StringUtils.split(fqf.getIdSigla(), "|");
				List<FatturaAttiva>list = fjb.getDettaglioFatturaSigla(Long.parseLong(parms[0]), parms[1], parms[2], Long.parseLong(parms[3]));
				se.setAttribute("datiFattura", list);
				return mapping.findForward("consultazione");
			}
//			ricerca avanzata
			if(fqf.getNsrif()!=null && fqf.getNsrif()>0)
				p.add(FatturaQuery.NSRIF, fqf.getNsrif());
			else
				p.add(FatturaQuery.DIPARTIMENTO_ID, strutturaAfferenza);
			if(fqf.getProtocollo()!=null && fqf.getProtocollo().length()>0)
				p.add(FatturaQuery.PROTOCOLLO, fqf.getProtocollo());
			if(fqf.getStudioBrevettualeId()!=null && fqf.getStudioBrevettualeId()>0)
				p.add(FatturaQuery.STUDI_BREVETTUALI_ID, fqf.getStudioBrevettualeId());
			if(fqf.getDataFattura1()!=null || fqf.getDataFattura2()!=null){
				if(fqf.getDataFattura1()==null) fqf.set_dataFattura1("01/01/1900");
				if(fqf.getDataFattura2()==null) fqf.set_dataFattura2("01/01/2900");
				p.addRange(FatturaQuery.DATA_FATTURA, fqf.getDataFattura1(), fqf.getDataFattura2());
			}
			if(fqf.getNumFattura()!=null && fqf.getNumFattura().length()>0)
				p.add(FatturaQuery.NUM_FATTURA, fqf.getNumFattura());
			if(fqf.getImpegnoObbligazione()!=null && fqf.getImpegnoObbligazione().length()>0)
				p.add(FatturaQuery.IMPEGNO_OBBLIGAZIONE, fqf.getImpegnoObbligazione()+"%");
			List<FatturaAttiva> list = fjb.getFatture(p);
			if(list==null || list.size()==0)
				request.setAttribute("mess", "" + "Nessuna fattura presente in archivio.");
			else if(list.size()>400)
				request.setAttribute("mess", "Troppi risultati selezionati. Effettuare una ricerca pi&ugrave; specifica.");
			else {
				request.setAttribute("list", list);
				se.setAttribute("lastQBEParam", p);
			}
        	return mapping.findForward("selfat");
		}
	}

}
