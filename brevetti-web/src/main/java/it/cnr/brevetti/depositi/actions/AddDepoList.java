package it.cnr.brevetti.depositi.actions;

import it.cnr.brevetti.depositi.actionForms.DepositoForm;
import it.cnr.brevetti.ejb.entities.DepEst;
import it.cnr.brevetti.ejb.entities.Idioma;
import it.cnr.brevetti.ejb.entities.Stato;
import it.cnr.brevetti.ejb.entities.StoricoTitolarita;
import it.cnr.brevetti.ejb.entities.StudioBrevettuale;
import it.cnr.brevetti.ejb.entities.TipoEstinzione;
import it.cnr.brevetti.ejb.entities.TipoPctRegionalPatent;
import it.cnr.brevetti.ejb.entities.Titolarita;
import it.cnr.brevetti.trovati.actionForms.TrovatoForm;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.Utile;
import it.cnr.brevetti.util.UtilityFunctions;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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

public class AddDepoList extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession se=request.getSession(true);
		TrovatoJB tjb = TrovatoJB.getInstance();
		TrovatoForm tform = (TrovatoForm)se.getAttribute("datiTrovato");
		List<DepEst> listD = (List<DepEst>) se.getAttribute("depositi");
		SortedMap<Long, Integer> mapDep = (SortedMap<Long, Integer>)se.getAttribute("mapdep");
		Long proceduraId = Long.decode(request.getParameter("key"));
		DepEst dep = listD.get(mapDep.get(proceduraId));
		if (request.getRequestURI().endsWith("AddDepoList.do") && request.getMethod().equals("GET")) {		//pagina riepilogativa
			if(dep.getTipoId()==1)
				throw new Exception("Invocazione incoerente del wizard depositi") ;
			if(dep.getTipoId()==2 && dep.getTipiPctRegionalPatent()!=null)
				se.setAttribute("regioniWiz", new ArrayList<TipoPctRegionalPatent>(dep.getTipiPctRegionalPatent()));
			else
				se.removeAttribute("regioniWiz");
			if(dep.getStati()!=null)
				se.setAttribute("nazioniWiz", new ArrayList<Stato>(dep.getStati()));
			else
				se.removeAttribute("nazioniWiz");
			return mapping.findForward("AddDepoList");
		}
		if(request.getParameter("update")!=null && request.getParameter("update").startsWith("1"))			//refresh invocato da modifiche alle titolarità
			return mapping.findForward("AddDepoWiz");
		DepositoForm dform;
		List<Stato> nazioni = (List<Stato>) se.getAttribute("nazioniWiz");
		List<TipoPctRegionalPatent> regioni = (List<TipoPctRegionalPatent>) se.getAttribute("regioniWiz");
		boolean hasRegions = Utile.isNotEmptyOrNull(regioni);
		boolean hasNations = Utile.isNotEmptyOrNull(nazioni);
		HashMap<Integer, StoricoTitolarita>mapSto = (HashMap<Integer, StoricoTitolarita>) se.getAttribute("mapSto");
		List<DepEst> appoggio = (List<DepEst>) se.getAttribute("appoggio");
		Method met;
		if(!request.getRequestURI().endsWith("AddDepoList.do")){			//step successivi del wizard (salva scheda precedente)
			dform = (DepositoForm)form;
			DepEst newdep = new DepEst();
			BeanUtils.copyProperties(newdep, dform);
			newdep.setId(UtilityFunctions.generateRandomId());
			String sx;
			Class args[] = { };
			Object obj[] = { };
			for(int i = 0; i<UtilityFunctions.MAX_TITOLARITA; i++){
				sx = ("" + i);
				met = DepositoForm.class.getMethod("getTitolariId"+sx, args);
				String tt = (String)met.invoke(dform, obj);
				if(tt!=null && tt.length()!=0 && mapSto.containsKey(Integer.valueOf(tt))){
					met = DepositoForm.class.getMethod("getPercent"+sx, args);
					StoricoTitolarita st = mapSto.get(Integer.valueOf(tt));
					st.setPercentuale((BigDecimal)met.invoke(dform, obj));
				}
			}
			if(mapSto!=null)	newdep.setStoricoTitolarita(new ArrayList<StoricoTitolarita>(mapSto.values()));
			newdep.setStato(tjb.getStato(newdep.getStatoId()));
			newdep.setStudioBrevettuale(tjb.getStudioBrevettuale(newdep.getStudioBrevettualeId()));
			appoggio.add(newdep);
		}else{											//1° step del wizard, dalla pagina riepilogativa
			appoggio = new ArrayList<DepEst>();
			se.setAttribute("appoggio", appoggio);
		}
		if(!hasRegions && !hasNations){					//compilate tutte le schede, salvare
			for(DepEst de : appoggio){
				listD.add(de);
				mapDep.put(Long.parseLong(UtilityFunctions.numForm.format(de.getDataDeposito())+Math.abs(de.getId())), listD.indexOf(de));
			}
			if(dep.getTipiPctRegionalPatent()!=null)
				dep.getTipiPctRegionalPatent().removeAll(dep.getTipiPctRegionalPatent());
			if(dep.getStati()!=null)
				dep.getStati().removeAll(dep.getStati());
			if(dep.getTipoId()==DepEst.EPC && dep.getDataAbbandono()==null && dep.getDataRilascioDep()!=null){	//EPC scadenza = rilascio
				dep.setDataAbbandono(dep.getDataRilascioDep());
				dep.setTipiEstinzioneId(TipoEstinzione.PROSECUZIONE);
			}
			if(dep.getTipoId()==DepEst.PCT && dep.getDataAbbandono()==null){								//PCT scadenza = deposito + 18 mesi
       			dep.setDataAbbandono(UtilityFunctions.addMonth(dep.getDataDeposito(), 18));
       			dep.setTipiEstinzioneId(TipoEstinzione.PROSECUZIONE);
			}
			request.setAttribute("act", "close");
			return mapping.findForward("AddDepoList");
		}
		//ancora regioni o nazioni da compilare
		dform = new DepositoForm();
		List<StoricoTitolarita> list = new ArrayList<StoricoTitolarita>();
		ArrayList<LabelValueBean> tipiEstinzione = new ArrayList<LabelValueBean>();
		dform.setKey(proceduraId);				//carico temporaneamente l'id della procedura padre nel campo id, in fase di salvataggio verrà sostituito da un n°random 
		if(dep.getTipoId()==2){								//wizard da PCT
			dform.setDataUfficiale(dep.getDataDeposito());
			dform.setTipiDataUfficialeId(2);
			dform.setDataDeposito(UtilityFunctions.addMonth(dep.getDataDeposito(), 18));
		}else{
			if(dep.getTipiDataUfficialeId()==1){				//wizard da EPC
				dform.setDataUfficiale(dep.getDataDeposito());
				dform.setTipiDataUfficialeId(3);
			}else{												//wizard da EuroPCT
				for(Long n : mapDep.keySet()){
					DepEst other = listD.get(mapDep.get(n));
					if(other.getTipoId()==2)
						dform.setDataUfficiale(other.getDataDeposito());
				}
				dform.setTipiDataUfficialeId(2);
			}
			dform.setDataDeposito(dep.getDataRilascioDep());
			dform.setNumeroDeposito(dep.getNumRilascioDep());
			dform.setDataRilascioDep(dep.getDataRilascioDep());
		}
		dform.setNsrif(dep.getNsrif());
		dform.setPrimo(0);
		dform.setStudioBrevettualeId(dep.getStudioBrevettualeId());
		dform.setAnniValidita(tjb.getTipoTrovato(tform.getTipiTrovatoId()).getAnniValidita());
		List list2 = dep.getStoricoTitolarita();
		if(list2==null || list2.isEmpty()){							//se non ci sono nei depositi copia le titolarità del trovato
			HashMap<Integer, Titolarita>mapTit = (HashMap<Integer, Titolarita>)se.getAttribute("mapTit");
			if(mapTit!=null) 
				list2 = new ArrayList(mapTit.values());				
		}
		for (Object storicoTitolarita : list2) {					//copia lo storico delle titolarità
			StoricoTitolarita temp = new StoricoTitolarita();
			BeanUtils.copyProperties(temp, storicoTitolarita);
			temp.setId(UtilityFunctions.generateRandomId());
			temp.setDepEstId(null);
			list.add(temp);
		}
		int x = 0;
		Class[] stringa = {Class.forName("java.lang.String")};
		Class[] decimale = {Class.forName("java.math.BigDecimal")};
		Object[] parm = {null};
		mapSto = new HashMap<Integer, StoricoTitolarita>();
		for (StoricoTitolarita storicoTitolarita : list) {
			mapSto.put(storicoTitolarita.getId(), storicoTitolarita);
			String sx = ("" + x++);
			met = DepositoForm.class.getMethod("setTitolariId"+sx, stringa);
			parm[0] = ""+storicoTitolarita.getId();
			met.invoke(dform, parm);
			met = DepositoForm.class.getMethod("setPercent"+sx, decimale);
			parm[0] = storicoTitolarita.getPercentuale();
			met.invoke(dform, parm);
		}
		if(mapSto.size()>0)
			se.setAttribute("mapSto", mapSto);
		else
			se.removeAttribute("mapSto");
		ArrayList<LabelValueBean> idiomi = (ArrayList<LabelValueBean>)se.getAttribute("idiomi");
		if(idiomi==null){
			idiomi = new ArrayList<LabelValueBean>();
			for(Idioma idm : tjb.getIdiomi())
				idiomi.add(new LabelValueBean(idm.getNome(),""+idm.getId()));
			se.setAttribute("idiomi", idiomi);
		}
		// Stringa di array di tutte le sedi ad uso di javascript
		String allSedi = "new Array(\"- - -|0\"";
		String denom="";
		ArrayList<LabelValueBean> denoms = new ArrayList<LabelValueBean>();
		denoms.add( new LabelValueBean("---Selezionare uno studio---","0"));
		String vuoto = "\"- - -|0\",";
		int xx = 1;
		int yy = 0;
		int point = 0;
		for(StudioBrevettuale sede : tjb.getStudiBrevettualiSortedByDenominazione()){
			if(!denom.equals(sede.getDenominazione())){
				denom = sede.getDenominazione();		//denominazione corrente
				denoms.add(new LabelValueBean(denom,""+xx++));
				allSedi = allSedi.concat("),new Array(");
				if(yy>1){
					allSedi = allSedi.substring(0, point) + vuoto + allSedi.substring(point);
				}
				point = allSedi.length();
				yy=0;
			} else
				allSedi = allSedi.concat(",");
			yy++;
			if(sede.getId().equals(dform.getStudioBrevettualeId()))	dform.setDenom(""+(xx-1));		//nuovo deposito eredita studio
			allSedi = allSedi.concat("\""+sede.getCitta()+"|"+sede.getId()+"\"");
		}
		allSedi = allSedi.concat(")");
		se.setAttribute("denoms", denoms);
		se.setAttribute("sedi", allSedi);
		ArrayList<LabelValueBean> lNazioni = new ArrayList<LabelValueBean>();
		if(hasRegions){
			dform.setTipoId(DepEst.EPC);
			dform.setStatoId(119);
			lNazioni.add(new LabelValueBean("Procedura EPC","119"));
			se.setAttribute("nazioni", lNazioni);
			regioni.remove(0);
		}else if(hasNations) {
			dform.setTipoId(DepEst.DEPOSITO);
			dform.setStatoId(nazioni.get(0).getId());
			lNazioni.add(new LabelValueBean(nazioni.get(0).getNome(),""+nazioni.get(0).getId()));
			se.setAttribute("nazioni", lNazioni);
			nazioni.remove(0);
		}
		tipiEstinzione.add(new LabelValueBean("------",null));
		for (TipoEstinzione te : tjb.getTipiEstinzione(dform.getTipoId())){
			tipiEstinzione.add(new LabelValueBean(te.getNome(), ""+te.getTipiEstinzioneId()));
		}
		se.setAttribute("tipiEstinzione", tipiEstinzione);
		request.setAttribute("datiDepoWiz", dform);
		return mapping.findForward("AddDepoWiz");
	}

}
