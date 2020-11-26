package it.cnr.brevetti.trovati.actions;

import it.cnr.brevetti.ejb.entities.Titolarita;
import it.cnr.brevetti.trovati.actionForms.TrovatoForm;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.ServiceLocatorException;
import it.cnr.brevetti.util.UtilityFunctions;
import it.cnr.brevetti.gas.Ruolo;
import it.cnr.brevetti.gas.SessioneUtente;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * @author astentella
 * 
 */

public class InfoTrov extends Action {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServiceLocatorException {
		HttpSession se=request.getSession(true);
		String[] bundleArray = {"", "BI", "", "DA", "TM", "MU", "NV", "SW", "KH"};
		se.setAttribute("bundleArray", bundleArray);
		TrovatoForm dform = (TrovatoForm)se.getAttribute("datiTrovato");
		TrovatoJB tjb = TrovatoJB.getInstance();
		dform.setTipiTrovatoDescrizione(tjb.getTipoTrovato(dform.getTipiTrovatoId()).getTipo());
		if(dform.getCessioneDiritti()!=null)
			dform.setCessioneDirittiDescrizione(tjb.getCessioneDiritti()[dform.getCessioneDiritti().intValue()]);
		else 
			dform.setCessioneDirittiDescrizione("N/D");
		String titolari = "";
		String inventori  = "";
		String sx;
		Method met;
		Class args[] = { };
		Object obj[] = { };
		try {
			int x = 100;
			for(int i = 0; i<UtilityFunctions.MAX_INVENTORI;i++){
				/* trick to obtain 2-digit series like 00,01,02..10,11.. */
				sx = ("" + x++).substring(1);
				met = TrovatoForm.class.getMethod("getInventore"+sx, args );
				boolean riferimento = (dform.getInventoreIndex()==i);
				String nominativo = (String)met.invoke(dform, obj);
				if(StringUtils.isNotBlank(nominativo))
					inventori = inventori.concat((inventori.length()>0?",":"") + "\""+ nominativo + (riferimento?" (*)":"") + "\"");
				else
					break;
			}
			if(dform.getInventoreIndex()!=null){
				sx = ("" + (dform.getInventoreIndex()+100)).substring(1);
				met = TrovatoForm.class.getMethod("getInventore"+sx, args );
				dform.setInvRifDescrizione((String)met.invoke(dform, obj));
			}
			HashMap<Integer, Titolarita> titolarita = (HashMap)se.getAttribute("mapTit");
			String denominazione="";
			if(titolarita!=null){
				Iterator<Integer> it = titolarita.keySet().iterator();
				while(it.hasNext()){
					Titolarita tit = (Titolarita)titolarita.get(it.next());
					if(titolari.length()>0) titolari=titolari.concat(",");
					if(tit.getTipiTitolareId().intValue()==1)
						denominazione=tit.getDipartimento().getDescrizione();
					else if(tit.getTipiTitolareId().intValue()==3)
						denominazione=tit.getInventore().getCognome()+" "+tit.getInventore().getNome();
					else if(tit.getTipiTitolareId().intValue()==4)
						denominazione=tit.getEnteEsterno().getNome();
					titolari=titolari.concat("\""+ denominazione.replaceAll("\"", "\\\\\"") +" ("+ tit.getPercentuale() +"%)\"");    				
				}
			}
			request.setAttribute("titolari", titolari);
			request.setAttribute("inventori", inventori);

		} catch (Exception e) {
			e.printStackTrace();
		}

		SessioneUtente user = (SessioneUtente)se.getAttribute("sessioneUtente");
		for(Ruolo ruolo : user.getRuoli() ){
			if(ruolo.getNome().startsWith("amministra trovati")){
    			request.setAttribute("amministraTrovati", "true");				
				break;
			}
		}
		return mapping.findForward("infotrov");            
	}
}
