package it.cnr.brevetti.fatture.actions;

import it.cnr.brevetti.domain.FatturaQuery;
import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.domain.TrovatoQuery;
import it.cnr.brevetti.ejb.entities.Dipartimento;
import it.cnr.brevetti.ejb.entities.Fattura;
import it.cnr.brevetti.ejb.entities.StudioBrevettuale;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.fatture.actionForms.FatturaQueryForm;
import it.cnr.brevetti.fatture.javabeans.FatturaPassivaJB;
import it.cnr.brevetti.login.exceptions.ExpiredSessionException;
import it.cnr.brevetti.sigla.fatture.passive.FatturaPassiva;
import it.cnr.brevetti.sigla.fatture.passive.FatturaPassivaBase;
import it.cnr.brevetti.util.UtilityFunctions;
import it.cnr.brevetti.gas.SessioneUtente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

public class GesFat extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ExpiredSessionException, Exception {
		HttpSession se=request.getSession(true);
		SessioneUtente user = (SessioneUtente)se.getAttribute("sessioneUtente");
		if(user==null)
			throw new ExpiredSessionException();
		Integer strutturaAfferenza= ((Dipartimento)se.getAttribute("dipartimento")).getId();		//Integer.decode(user.getStruttura());
		FatturaPassivaJB fjb = FatturaPassivaJB.getInstance();
		FatturaQueryForm fqf = (FatturaQueryForm)request.getAttribute("fatturaQuery");
		request.setAttribute("tabNumber", new Integer(fqf.getNextab()));
        Parametri p = new Parametri();
		if (request.getMethod().equals("GET")) {
			if(se.getAttribute("lastQBEParam") instanceof Parametri)
				p = (Parametri)se.getAttribute("lastQBEParam");
			UtilityFunctions.cleanSession(se);
			se.setAttribute("lastQBEParam", p);
        	if (request.getParameter("lastQBE")!=null && request.getParameter("lastQBE").startsWith("y")){
        		if(p!=null){
        			request.setAttribute("list", fjb.getFatture(p)); //parametri
        			return mapping.findForward("selfat");
        		}
        	}
			/*if(fqf.getNextab()==1)				//ricerca semplice non più applicabile
				return mapping.findForward("1.gesfat");*/
			//getNextab()==2					//ricerca avanzata
			ArrayList<LabelValueBean> studi = new ArrayList<LabelValueBean>();
			studi.add( new LabelValueBean("---Selezionare uno studio---","0")); 
			for(Iterator i = fjb.getStudiBrevettuali().iterator(); i.hasNext();){
				StudioBrevettuale st = (StudioBrevettuale)i.next();
				studi.add( new LabelValueBean(st.getDenominazione()+" - "+st.getCitta().replaceAll("NON DISP.", ""), ""+st.getId()));
			}
			request.setAttribute("studi", studi);
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
				List<FatturaPassiva>list = fjb.getDettaglioFatturaSigla(Long.parseLong(parms[0]), parms[1], parms[2], Long.parseLong(parms[3]));
				HashMap<String, FatturaPassiva>map = new HashMap<String, FatturaPassiva>();
				for (FatturaPassiva fatturaPassiva : list) {
					String key = fatturaPassiva.getEsercizio()+"|"+fatturaPassiva.getCd_cds()+"|"+fatturaPassiva.getCd_unita_organizzativa()+"|"+fatturaPassiva.getPg_fattura_passiva()+"|"+fatturaPassiva.getProgressivo_riga();
					map.put(key, fatturaPassiva);
				}
				Fattura fattura = fjb.getFattura(list);
				fattura.setDipartimentoId(strutturaAfferenza);
				/*
				p.add(FatturaQuery.FATTURA_ID, fqf.getId());
				if(fqf.getFromList()==0)										//ricerca sempl.accede solo alle fatt.del dip.
					p.add(FatturaQuery.DIPARTIMENTO_ID, strutturaAfferenza);
				List<FatturaPassivaBase> list = fjb.getFatture(p);
				if(list==null || list.size()==0){
					request.setAttribute("mess", "identificativo non presente in archivio");
					return mapping.findForward("1.gesfat");
				}
				FatturaPassivaBase fattura = list.get(0);
				if(!fattura.getDipartimentoId().equals(strutturaAfferenza))
					fqf.setMode(2);											//fatture in cotitolarità accessibili ma solo in lettura
				 */	
				se.setAttribute("datiFattura", fattura);
				se.setAttribute("datiSigla", map);
				String modalita = "consultazione";
				switch (fqf.getMode()) {
				case 1: modalita = "gestione";	//vai alla funzione di gestione fatture
				break;
				case 2: modalita = "consultazione"; //vai alla funzione di consultazione fatture
				break;
				default: modalita = "consultazione";	//se non specificato, e non esiste l'attributo di sessione, vai in consultazione
				break;
				}
				return mapping.findForward(modalita);
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
			List<FatturaPassivaBase> list = fjb.getFatture(p);
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
