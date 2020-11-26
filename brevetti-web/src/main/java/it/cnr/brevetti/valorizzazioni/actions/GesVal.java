package it.cnr.brevetti.valorizzazioni.actions;

import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.domain.TrovatoQuery;
import it.cnr.brevetti.ejb.entities.Azienda;
import it.cnr.brevetti.ejb.entities.Dipartimento;
import it.cnr.brevetti.ejb.entities.TipoValorizzazione;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.ejb.entities.Valorizzazione;
import it.cnr.brevetti.login.exceptions.ExpiredSessionException;
import it.cnr.brevetti.util.UtilityFunctions;
import it.cnr.brevetti.valorizzazioni.actionForms.ValorizzazioneForm;
import it.cnr.brevetti.valorizzazioni.actionForms.ValorizzazioneQueryForm;
import it.cnr.brevetti.valorizzazioni.javabeans.ValorizzazioneJB;
import it.cnr.brevetti.gas.SessioneUtente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class GesVal extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ExpiredSessionException, Exception {
		HttpSession se=request.getSession(true);
		SessioneUtente user = (SessioneUtente)se.getAttribute("sessioneUtente");
		if(user==null)
			throw new ExpiredSessionException();
		Integer strutturaAfferenza= ((Dipartimento)se.getAttribute("dipartimento")).getId();		//Integer.decode(user.getStruttura());
		ValorizzazioneJB vjb = ValorizzazioneJB.getInstance();
		ValorizzazioneQueryForm vqf = (ValorizzazioneQueryForm)request.getAttribute("valorizzazioneQuery");
        Parametri p = new Parametri();
        ArrayList<LabelValueBean> aziende = new ArrayList<LabelValueBean>();
        aziende.add( new LabelValueBean("---Selezionare un'azienda---","0")); 
        for(Azienda azienda: vjb.getAziende()) {
        	aziende.add( new LabelValueBean(azienda.getNome()+ (azienda.getTipo()!=null && azienda.getTipo().equalsIgnoreCase("spin-off")?" $$$ SpinOff $$$ ":"") + " - "+azienda.getLocalita().replaceAll("NON DISP.", ""), ""+azienda.getId()));
        }
        Collections.sort(aziende);
        se.setAttribute("aziende", aziende);
		if (request.getMethod().equals("GET")) {									//ricerca per NSRIF
			ArrayList<LabelValueBean> trovati = new ArrayList<LabelValueBean>();
			p = new Parametri();
			p.add(TrovatoQuery.AFFERENZA_ID, strutturaAfferenza);
			for(Trovato tro: vjb.getTrovati(p)){
				trovati.add(new LabelValueBean(""+tro.getNsrif()+": "+StringUtils.left(tro.getTitolo(),90), ""+tro.getNsrif()));
			}
			se.setAttribute("trovati", trovati);
	    	return mapping.findForward("2.qbeval");
		}else{
			String id = request.getParameter("idValorizzazione");
			if(id!=null && id.length()>0){											//singola valorizzazione selezionata da lista
				Integer idValorizzazione = Integer.parseInt(id);
				Valorizzazione val = vjb.getValorizzazione(idValorizzazione);
				ValorizzazioneForm iForm = new ValorizzazioneForm();
				BeanUtils.copyProperties(iForm, val);
				iForm.setAziendaNome(val.getAzienda().getNome()+(val.getAzienda().getTipo()!=null && val.getAzienda().getTipo().equalsIgnoreCase("spin-off")?" $$$ SpinOff $$$ ":""));
				iForm.setAziendaCitta(val.getAzienda().getLocalita());
				iForm.setAziendaRegione(val.getAzienda().getRegione());
				request.setAttribute("iform", iForm);
				String cluster = "";
				String xcluster = "";
				String pipe = "";
				for (Trovato tro : val.getTrovati()) {
					cluster=cluster.concat(pipe+ tro.getNsrif()+": "+(StringUtils.left(tro.getTitolo().replaceAll("\\|", " "), 70)));	//.replaceAll("\"","\\\\\"")
					//TODO char stuffing da sistemare
					xcluster=xcluster.concat(pipe+tro.getNsrif());
					pipe = "|";
				}
				iForm.setnCluster(cluster);
				iForm.setxCluster(xcluster);
				//request.setAttribute("cluster", cluster);
				ArrayList<LabelValueBean> tipi = new ArrayList<LabelValueBean>();
				tipi.add( new LabelValueBean("---Selezionare un tipo---","0"));
				for (TipoValorizzazione tv : vjb.getTipiValorizzazione()) {
					tipi.add( new LabelValueBean(tv.getNome(),""+tv.getId()));
				}
				se.setAttribute("tipiValorizzazione", tipi);
				//se.setAttribute("cluster", val.getTrovati());
				return mapping.findForward("valorizzazione");
			}																	//lista valorizzazioni
			if(vqf.getNsrif()!=null && vqf.getNsrif()>0){
				List<Valorizzazione>dest = new ArrayList<Valorizzazione>();
				List<Valorizzazione>list = vjb.getValorizzazioni(vqf.getNsrif());
				if(list==null || list.size()==0)
					request.setAttribute("mess", "" + "Nessuna valorizzazione presente in archivio.");
				else{
					boolean checkAzienda = (vqf.getAziendaId()!=null && vqf.getAziendaId()>0);
					for (Valorizzazione valorizzazione : list) {
						if(!checkAzienda || valorizzazione.getAziendaId().equals(vqf.getAziendaId()))
							dest.add(vjb.getValorizzazione(valorizzazione.getId()));
					}
					if(dest.size()==0)
						request.setAttribute("mess", "" + "Nessuna valorizzazione corrispondente ai criteri specificati.");
					request.setAttribute("list", dest);
				}
			}
        	return mapping.findForward("selval");
		}
	}

}
