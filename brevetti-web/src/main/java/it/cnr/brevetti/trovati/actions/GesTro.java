package it.cnr.brevetti.trovati.actions;

import it.cnr.brevetti.domain.AbstractQuery;
import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.domain.TrovatoQuery;
import it.cnr.brevetti.ejb.entities.ClassificazioneInternDep;
import it.cnr.brevetti.ejb.entities.DepEst;
import it.cnr.brevetti.ejb.entities.Dipartimento;
import it.cnr.brevetti.ejb.entities.Inventore;
import it.cnr.brevetti.ejb.entities.Istituto;
import it.cnr.brevetti.ejb.entities.StudioBrevettuale;
import it.cnr.brevetti.ejb.entities.TipoTrovato;
import it.cnr.brevetti.ejb.entities.Titolarita;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.ejb.entities.DocumentoInfo;
import it.cnr.brevetti.ejb.entities.Utente;
import it.cnr.brevetti.ejb.entities.Valorizzazione;
import it.cnr.brevetti.login.exceptions.ExpiredSessionException;
import it.cnr.brevetti.trovati.actionForms.TrovatoForm;
import it.cnr.brevetti.trovati.actionForms.TrovatoQueryForm;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.ServiceLocatorException;
import it.cnr.brevetti.util.UtilityFunctions;
import it.cnr.brevetti.gas.SessioneUtente;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 * @author astentella
 * 
 * Action invocata per la ricerca e la lettura di un trovato sul DB 
 */

public class GesTro extends Action {

	/*@SuppressWarnings("unchecked")*/
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServiceLocatorException, ExpiredSessionException, Exception {
		HttpSession se=request.getSession(true);
		SessioneUtente user = (SessioneUtente)se.getAttribute("sessioneUtente");
		if(user==null)
			throw new ExpiredSessionException();
		Integer strutturaAfferenza= ((Dipartimento)se.getAttribute("dipartimento")).getId();		//Integer.decode(user.getStruttura());
		TrovatoJB tjb = TrovatoJB.getInstance();
		TrovatoForm dform = new TrovatoForm();
		TrovatoQueryForm tqf = (TrovatoQueryForm)request.getAttribute("trovatoQuery");
		request.setAttribute("tabNumber", new Integer(tqf.getNextab()));
        Parametri p = new Parametri();
        String forward = (String)se.getAttribute("forward");
		if (request.getMethod().equals("GET")) {
        	if (request.getParameter("lastQBE")!=null && request.getParameter("lastQBE").startsWith("y")){
        		tqf = (TrovatoQueryForm)se.getAttribute("lastQBEParam");
        		UtilityFunctions.cleanSession(se);
        		if(tqf==null)	tqf = new TrovatoQueryForm();
        		se.setAttribute("lastQBEParam", tqf);
        		if(tqf!=null){
        			p.add(TrovatoQuery.AFFERENZA_ID, strutturaAfferenza);			//restringe la ricerca ai trovati di pertinenza del dipartimento dell'operatore
        			List<Trovato> result = getTrovatiByQBE(tqf, p);
        			request.setAttribute("list", result);
        			return mapping.findForward("seltro");
        		}
        	}
        	UtilityFunctions.cleanSession(se);
	        switch (tqf.getMode()) {
	        	case 1: se.setAttribute("forward", "trovati");	//vai alla funzione di gestione trovati
	        	break;
	        	case 2: se.setAttribute("forward", "infotrov"); //vai alla funzione di consultazione trovati
	        	break;
	        	default: if(forward==null) se.setAttribute("forward", "infotrov");	//se non specificato, e non esiste l'attributo di sessione, vai in consultazione
	        	break;
	        }
			if(tqf.getNextab()==1)				//ricerca semplice
				return mapping.findForward("1.gestro");
			//getNextab()==2					//ricerca avanzata
			ArrayList<LabelValueBean> tipi = new ArrayList<LabelValueBean>();
			ArrayList<LabelValueBean> diritti = new ArrayList<LabelValueBean>();
			ArrayList<LabelValueBean> abbandono = new ArrayList<LabelValueBean>();
			tipi.add( new LabelValueBean("---Selezionare una tipologia---","0")); 
			for(TipoTrovato tt : tjb.getTipiTrovato())
				tipi.add( new LabelValueBean(tt.getTipo(), ""+tt.getId()));
        	//TODO creare tabella associata?
			diritti.add( new LabelValueBean("---Selezionare un'opzione---","0"));
			for(int i = 1; i<tjb.getCessioneDiritti().length; i++)
				diritti.add(new LabelValueBean(tjb.getCessioneDiritti()[i], ""+i));
			request.setAttribute("tipi", tipi);
        	request.setAttribute("diritti", diritti);
			ArrayList<LabelValueBean> studi = new ArrayList<LabelValueBean>();
			studi.add( new LabelValueBean("---Selezionare uno studio---","0"));
			List<String> temp = tjb.getDenominazioniStudiBrevettuali();
			Collections.sort(temp);
			for (String studio : temp) {
				studi.add( new LabelValueBean(studio, studio));
			}
			/*for(Iterator i = tjb.getStudiBrevettuali().iterator(); i.hasNext();){
				StudioBrevettuale st = (StudioBrevettuale)i.next();
				studi.add( new LabelValueBean(st.getDenominazione()+" - "+st.getCitta().replaceAll("NON DISP.", ""), ""+st.getId()));
			}*/
			request.setAttribute("studi", studi);
			ArrayList<LabelValueBean> dips = new ArrayList<LabelValueBean>();
			dips.add( new LabelValueBean("---Selezionare un dipartimento---","0")); 
			for(Iterator i = tjb.getDipartimenti().iterator(); i.hasNext();){
				Dipartimento dp = (Dipartimento)i.next();
				if(dp.getDataSoppressione() != null)			   //.getId().compareTo(strutturaAfferenza)!=0)
					dips.add( new LabelValueBean(dp.getDescrizione(), ""+dp.getId()));
			}
			request.setAttribute("dips", dips);
			ArrayList<LabelValueBean> isti = new ArrayList<LabelValueBean>();
			isti.add( new LabelValueBean("---Selezionare un istituto---","0")); 
			for(Iterator i = tjb.getIstituti().iterator(); i.hasNext();){
				Istituto is = (Istituto)i.next();
				isti.add( new LabelValueBean(is.getNome(), ""+is.getId()));
			}
			request.setAttribute("isti", isti);
			abbandono.add( new LabelValueBean("---Selezionare un'opzione---","0"));
			abbandono.add( new LabelValueBean("Abbandonati definitivamente", "1"));
			abbandono.add( new LabelValueBean("Con almeno un deposito attivo", "2"));
			request.setAttribute("abba", abbandono);
        	ArrayList<LabelValueBean> utenti = new ArrayList<LabelValueBean>();
    		utenti.add( new LabelValueBean("---Selezionare un Referente Titolo---", "---"));
    		for(Utente utente : tjb.getUtenti() ){
    			utenti.add(new LabelValueBean(utente.getNome()+" "+utente.getCognome(),utente.getUtenteId()));
    		}
    		utenti.add( new LabelValueBean("Referente non assegnato", "0"));
    		request.setAttribute("referenti", utenti);
			return mapping.findForward("2.qbetro");
		} else {						//method==POST
			List list;
			switch (tqf.getMode()) {
			case 1: se.setAttribute("forward", "trovati");	//vai alla funzione di gestione trovati
			break;
			case 2: se.setAttribute("forward", "infotrov"); //vai alla funzione di consultazione trovati
			break;
			default: if(forward==null) se.setAttribute("forward", "infotrov");	//se non specificato, non modificare l'attributo di sessione
				break;
			}
			if(tqf.getNsrif()!=null && tqf.getNsrif().intValue()!=0){			//ricerca semplice o selezione trovato da lista prodotta con QBE 
				p.add(TrovatoQuery.NSRIF, tqf.getNsrif());
				p.add(TrovatoQuery.AFFERENZA_ID, strutturaAfferenza);			//restringe la ricerca ai trovati di pertinenza del dipartimento dell'operatore
				list = tjb.getTrovati(p);
				if(list==null || list.size()==0){
					request.setAttribute("mess", "identificativo non presente in archivio");
					return mapping.findForward("1.gestro");
				}
				Trovato trovato = (Trovato)list.get(0);
				BeanUtils.copyProperties(dform, trovato);
				dform.setDipartimentoTitolare(trovato.getDipartimento().getId());
				se.setAttribute("tipoTrovato", trovato.getTipiTrovatoId());
				if(StringUtils.isNotEmpty(trovato.getUtentiId()))
					dform.setUtentiNominativo(trovato.getUtente().getNome()+" "+trovato.getUtente().getCognome());
				List<DepEst> listD = trovato.getDepEst();
				SortedMap<Long, Integer> mapDep = new TreeMap<Long, Integer>();
				int x = 0;
				if(listD!=null) for(DepEst dep : listD){
					mapDep.put(Long.parseLong(UtilityFunctions.numForm.format(dep.getDataDeposito())+dep.getId()), x++);
				}
				if(mapDep.size()>0){
					se.setAttribute("depositi", listD);
					se.setAttribute("mapdep", mapDep);
				} else {
					se.removeAttribute("depositi");
					se.removeAttribute("mapdep");
				}
				Method met;
				String sx;
				Object[] parm = {null};
				Class[] stringa = {Class.forName("java.lang.String")};
				Class[] intero = {Class.forName("java.lang.Integer")};
				Class[] decimale = {Class.forName("java.math.BigDecimal")};
				list = trovato.getInventori();
				x = 100;	        			
				if(list!=null) for(Iterator i = list.iterator(); i.hasNext();){
					Inventore inv = (Inventore)i.next();
					/* trick to obtain 2-digit series like 00,01,02..10,11.. */
					sx = ("" + x++).substring(1);
					met = TrovatoForm.class.getMethod("setInventore"+sx, stringa);
					parm[0] = inv.getCognome()+" "+inv.getNome();
					met.invoke(dform, parm);
					met = TrovatoForm.class.getMethod("setInventoriId"+sx, intero);
					parm[0] = inv.getId();
					met.invoke(dform, parm);
				}
				list = trovato.getIstituti();
				/* if elements exceded 10 elements, use above trick to have 2-digit series */
				x = 0;
				if(list!=null) for(Iterator i = list.iterator(); i.hasNext();){
					Istituto ist = (Istituto)i.next();
					sx = ("" + x++);
					met = TrovatoForm.class.getMethod("setIstituto"+sx, stringa);
					parm[0] = ist.getIstitutoSigla()+" - "+ist.getNome().replaceAll("\"","\\\"");
					met.invoke(dform, parm);
					met = TrovatoForm.class.getMethod("setIstitutiId"+sx, intero);
					parm[0] = ist.getId();
					met.invoke(dform, parm);
				}
				list = trovato.getClassificazioniInternazionali();
				TreeMap<Integer, ClassificazioneInternDep> mapCla = new TreeMap<Integer, ClassificazioneInternDep>();
				if(list!=null) for(Iterator i = list.iterator(); i.hasNext();){
					ClassificazioneInternDep cla = (ClassificazioneInternDep)i.next();
					mapCla.put(cla.getClassificazioneInternDepId(), cla);
				}
				if(mapCla.size()>0)
					se.setAttribute("classificazioni", mapCla);
				else
					se.removeAttribute("classificazioni");
				list = trovato.getTitolarita();
				HashMap<Integer, Titolarita>mapTit = new HashMap<Integer, Titolarita>();
				/* if elements exceded 10 elements, use above trick to have 2-digit series */
				x = 0;
				if(list!=null) for(Iterator i = list.iterator(); i.hasNext();){
					Titolarita tit = (Titolarita)i.next();
					mapTit.put(tit.getId(), tit);
					sx = ("" + x++);
					met = TrovatoForm.class.getMethod("setTitolariId"+sx, stringa);
					parm[0] = ""+tit.getId();				//tit.getTipiTitolareId()+":"+tit.getFrkSoggettoId();
					met.invoke(dform, parm);
					met = TrovatoForm.class.getMethod("setPercent"+sx, decimale);
					parm[0] = tit.getPercentuale();
					met.invoke(dform, parm);
				}
				if(mapTit.size()>0)
					se.setAttribute("mapTit", mapTit);
				else
					se.removeAttribute("mapTit");
				List<Valorizzazione>vals = new ArrayList<Valorizzazione>();
				if(trovato.getValorizzazioni()!=null) for(Valorizzazione v : trovato.getValorizzazioni()){
					vals.add(tjb.getValorizzazione(v.getId()));
				}
				se.setAttribute("valorizzazioni", vals);
				se.setAttribute("documenti", trovato.getDocumenti()!=null?trovato.getDocumenti():new ArrayList<DocumentoInfo>());
				se.setAttribute("datiTrovato",dform);
				se.setAttribute("verbali", tjb.getVerbali(trovato.getNsrif()));
				request.removeAttribute("tabNumber");
				return mapping.findForward((String)se.getAttribute("forward"));		        	
			}
			//ricerca avanzata
			p.add(TrovatoQuery.AFFERENZA_ID, strutturaAfferenza);			//restringe la ricerca ai trovati di pertinenza del dipartimento dell'operatore
			List<Trovato> result = getTrovatiByQBE(tqf, p);
			request.setAttribute("list", result);
	        se.setAttribute("lastQBEParam", tqf);
			return mapping.findForward("seltro");
		}	            
	}
	
	private List<Trovato> getTrovatiByQBE(TrovatoQueryForm tqf, Parametri p) throws ServiceLocatorException, ParseException{
		TrovatoJB tjb = TrovatoJB.getInstance();
		if(tqf.getTitolo()!=null && tqf.getTitolo().length()>0)
			if(tqf.getLinguat().intValue()==1)
				p.add(TrovatoQuery.TITOLO, "%"+tqf.getTitolo()+"%");
			else
				p.add(TrovatoQuery.TITOLO_INGLESE, "%"+tqf.getTitolo()+"%");
		if(tqf.getParolaChiave()!=null && tqf.getParolaChiave().length()>0)
			if(tqf.getLinguak().intValue()==1)
				p.add(TrovatoQuery.PAROLA_CHIAVE, "%"+tqf.getParolaChiave()+"%");
			else
				p.add(TrovatoQuery.PAROLA_CHIAVE_INGLESE, "%"+tqf.getParolaChiave()+"%");
		if(tqf.getTipoTrovato()!=null && tqf.getTipoTrovato().intValue()!=0)
			p.add(TrovatoQuery.TIPI_TROVATO_ID, tqf.getTipoTrovato() );
		if(tqf.getInventore()!=null && tqf.getInventore().length()>0)
			p.add(TrovatoQuery.COGNOME, tqf.getInventore()+"%");
		if(tqf.getStudio()!=null && !tqf.getStudio().equalsIgnoreCase("0")){
			List<Integer>sedi = new ArrayList<Integer>();
			for (StudioBrevettuale studio : tjb.getSediStudiBrevettuali(tqf.getStudio())) {
				sedi.add(studio.getId());
			}
			p.add(TrovatoQuery.STUDI_BREVETTUALI_ID, sedi);
		}
		if(tqf.getUtentiId()!=null && !tqf.getUtentiId().startsWith("---") ){
			if(tqf.getUtentiId().startsWith("0"))
				p.add(TrovatoQuery.UTENTI_ID, AbstractQuery.IS_NULL);
			else
				p.add(TrovatoQuery.UTENTI_ID, tqf.getUtentiId());
		}
		if(tqf.getNumDeposito()!=null && tqf.getNumDeposito().length()>0)
			p.add(TrovatoQuery.NUM_DEPOSITO, "%"+tqf.getNumDeposito()+"%");
		if(tqf.getDataDeposito1()!=null && (tqf.getDataDeposito1().length()>0 || tqf.getDataDeposito2().length()>0)){
			if(tqf.getDataDeposito1().length()==0) tqf.setDataDeposito1("01/01/1900");
			if(tqf.getDataDeposito2().length()==0) tqf.setDataDeposito2("01/01/2900");
			p.addRange(TrovatoQuery.DATA_DEPOSITO, UtilityFunctions.itForm.parse(tqf.getDataDeposito1()), UtilityFunctions.itForm.parse(tqf.getDataDeposito2()));
		}
		if(tqf.getNumRilascio()!=null && tqf.getNumRilascio().length()>0)
			p.add(TrovatoQuery.NUM_RILASCIO, tqf.getNumRilascio());
		if(tqf.getDataRilascio1()!=null && (tqf.getDataRilascio1().length()>0 || tqf.getDataRilascio2().length()>0)){
			if(tqf.getDataRilascio1().length()==0) tqf.setDataRilascio1("01/01/1900");
			if(tqf.getDataRilascio2().length()==0) tqf.setDataRilascio2("01/01/2900");
			p.addRange(TrovatoQuery.DATA_RILASCIO, UtilityFunctions.itForm.parse(tqf.getDataRilascio1()), UtilityFunctions.itForm.parse(tqf.getDataRilascio2()));
		}
		if(tqf.getDataAbbandono1()!=null && (tqf.getDataAbbandono1().length()>0 || tqf.getDataAbbandono2().length()>0)){
			if(tqf.getDataAbbandono1().length()==0) tqf.setDataAbbandono1("01/01/1900");
			if(tqf.getDataAbbandono2().length()==0) tqf.setDataAbbandono2("01/01/2900");
			p.addRange(TrovatoQuery.DATA_ABBANDONO, UtilityFunctions.itForm.parse(tqf.getDataAbbandono1()), UtilityFunctions.itForm.parse(tqf.getDataAbbandono2()));
		}
		if(tqf.getCessioneDiritti()!=null && tqf.getCessioneDiritti()!=0)
			p.add(TrovatoQuery.CESSIONE_DIRITTI, tqf.getCessioneDiritti());
		if(tqf.getIstituto()!=null && tqf.getIstituto().intValue()!=0)
			p.add(TrovatoQuery.ISTITUTI_ID, tqf.getIstituto());	   
		if(tqf.getDipartimento()!=null && tqf.getDipartimento().intValue()!=0)
			p.add(TrovatoQuery.DIPARTIMENTO_ID, tqf.getDipartimento());
		if(tqf.getEnteEsterno()!=null && tqf.getEnteEsterno().length()>0)
			p.add(TrovatoQuery.NOME_ENTE, "%"+tqf.getEnteEsterno()+"%");	   
		if(tqf.getAbbandono()!=null && tqf.getAbbandono()>0)
			p.add(TrovatoQuery.DATA_ABBANDONO, AbstractQuery.IS_NULL);
		List<Trovato> result = tjb.getTrovati(p);
		if(tqf.getAbbandono()!=null && tqf.getAbbandono()==1){
			p.add(TrovatoQuery.DATA_ABBANDONO, AbstractQuery.IS_NOT_NULL);
			List<Trovato> result2 = tjb.getTrovati(p);
			if(result2!=null && result!=null)
				result2.removeAll(result);
			result = result2;
		}
		if(tqf.getAbbandono()!=null && tqf.getAbbandono()==2){
			p.getMap().remove(TrovatoQuery.DATA_ABBANDONO);
			p.addRange(TrovatoQuery.DATA_ABBANDONO, new Date(), UtilityFunctions.itForm.parse("01/01/2900"));
			List<Trovato> result2 = tjb.getTrovati(p);
			if(result!=null && result2!=null)
				result = ListUtils.union(result, ListUtils.subtract(result2, result));
		}
		return result;
	}
}
