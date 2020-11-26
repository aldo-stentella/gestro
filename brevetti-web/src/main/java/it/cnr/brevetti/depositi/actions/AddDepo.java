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
import it.cnr.brevetti.util.UtilityFunctions;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.apache.commons.lang.ArrayUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class AddDepo extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession se=request.getSession(true);
		TrovatoJB tjb = TrovatoJB.getInstance();
		TrovatoForm tform = (TrovatoForm)se.getAttribute("datiTrovato");
		DepositoForm dform;
		DepEst dep = null;
		Long key;
		Method met;
		String[] page = {"","AddDepo","AddEst","AddEpc"};
		List<DepEst> listD = (List<DepEst>) se.getAttribute("depositi");
		SortedMap<Long, Integer> mapDep = (SortedMap<Long, Integer>)se.getAttribute("mapdep");
		if(listD==null)	{
			listD=new ArrayList<DepEst>();
			mapDep=new TreeMap<Long, Integer>();
		}
		ArrayList<LabelValueBean> idiomi = new ArrayList<LabelValueBean>();
		ArrayList<LabelValueBean> nazioni = new ArrayList<LabelValueBean>();
		ArrayList<LabelValueBean> denoms = new ArrayList<LabelValueBean>();
		ArrayList<LabelValueBean> sedeInit = new ArrayList<LabelValueBean>();
		ArrayList<LabelValueBean> listaReg = new ArrayList<LabelValueBean>();
		ArrayList<LabelValueBean> listaNaz = new ArrayList<LabelValueBean>();
		ArrayList<LabelValueBean> tipiEstinzione = new ArrayList<LabelValueBean>();
		if (request.getMethod().equals("GET")) {
			dform = new DepositoForm();
			key = Long.decode(request.getParameter("id"));
			List<StoricoTitolarita> list = new ArrayList<StoricoTitolarita>();
			Date dataPct = null, dataEpc = null;
			for(Long n : mapDep.keySet()){
				DepEst other = listD.get(mapDep.get(n));
				if(other.getTipoId()==2)
					dataPct = other.getDataDeposito();
				if(other.getTipoId()==3)
					dataEpc = other.getDataDeposito();
			}
			if(key!=0 && (dep = listD.get(mapDep.get(key)))!=null){					//modifica deposito esistente 
				BeanUtils.copyProperties(dform, dep);
				switch (dep.getTipiDataUfficialeId()) {
				case 1:
					dform.setDataUfficiale(dep.getDataDeposito());
					break;
				case 2:
					dform.setDataUfficiale(dataPct);
					break;
				case 3:
					dform.setDataUfficiale(dataEpc);
					break;
				default:
					break;
				}
				dform.setKey(key);
				list = dep.getStoricoTitolarita();
			}else{																	//crea nuovo deposito
				dform.setTipoId(Integer.decode(request.getParameter("tipo")));
				if(dform.getTipoId()==2){	//PCT
					dform.setStatoId(118);
					dform.setTipiDataUfficialeId(1);
				}
				if(dform.getTipoId()==3)	//EPC
					dform.setStatoId(119);
				dform.setNsrif(tform.getNsrif());
				dform.setPrimo(listD.size()==0?1:0);									//se non ci sono altri depositi, si presume sia primo
				DepEst ultimo;
				if(!mapDep.isEmpty() && (ultimo = listD.get(mapDep.get(mapDep.lastKey())))!=null){
					dform.setStudioBrevettualeId(ultimo.getStudioBrevettualeId());		//copia l'ultimo studio selezionato
					List<StoricoTitolarita>list2 = ultimo.getStoricoTitolarita();
					if(list2!=null) for (StoricoTitolarita storicoTitolarita : list2) {	//copia lo storico delle titolarità
						StoricoTitolarita temp = new StoricoTitolarita();
						BeanUtils.copyProperties(temp, storicoTitolarita);
						temp.setId(UtilityFunctions.generateRandomId());
						temp.setDepEstId(null);
						list.add(temp);
					}
				}
			}
			dform.setAnniValidita(tjb.getTipoTrovato(tform.getTipiTrovatoId()).getAnniValidita());
			int x = 0;
			Class[] stringa = {Class.forName("java.lang.String")};
			Class[] decimale = {Class.forName("java.math.BigDecimal")};
			Object[] parm = {null};
			HashMap<Integer, StoricoTitolarita>mapSto = new HashMap<Integer, StoricoTitolarita>();
			if(list==null){														//se non ci sono nei depositi copia le titolarità del trovato
				HashMap<Integer, Titolarita>mapTit = (HashMap<Integer, Titolarita>)se.getAttribute("mapTit");
				list = new ArrayList<StoricoTitolarita>();
				if(mapTit!=null) for (Titolarita titolarita : mapTit.values()) {
					StoricoTitolarita st = new StoricoTitolarita();
					BeanUtils.copyProperties(st, titolarita);
					st.setId(UtilityFunctions.generateRandomId());
					list.add(st);
				}
			}
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
			for(Idioma idm : tjb.getIdiomi()){
				idiomi.add(new LabelValueBean(idm.getNome(),""+idm.getId()));
			}
			for(Stato sta : tjb.getStati()){
				if(dform.getTipoId()!=3 || (sta.getContinente()!=null && sta.getContinente().startsWith("EUROPE")))		//solo stati europei x procedure EPC
					if(sta!=null && sta.getStatus()==1)
						nazioni.add(new LabelValueBean(sta.getNome(),""+sta.getId()));
			}
			Collections.sort(nazioni);
			// Stringa di array di tutte le sedi ad uso di javascript
			String allSedi = "new Array(\"- - -|0\"";
			String denom="";
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
				if(dep!=null && sede.getId().equals(dep.getStudioBrevettualeId()))	dform.setDenom(""+(xx-1));			//deposito esistente
				if(dep==null && sede.getId().equals(dform.getStudioBrevettualeId()))	dform.setDenom(""+(xx-1));		//nuovo deposito eredita studio
				allSedi = allSedi.concat("\""+sede.getCitta()+"|"+sede.getId()+"\"");
				if(dform.getDenom()!=null && Integer.parseInt(dform.getDenom())==xx-1)
					sedeInit.isEmpty(); //sedeInit.add(new LabelValueBean(sede.getCitta(),""+sede.getId()));
			}
			allSedi = allSedi.concat(")");
			if(dep!=null && dep.getStati()!=null)	for(Stato sta : dep.getStati()){
				listaNaz.add(new LabelValueBean(sta.getNome(),""+sta.getId()));
			}
			if(dep!=null && dep.getTipiPctRegionalPatent()!=null)	for(TipoPctRegionalPatent prp :	dep.getTipiPctRegionalPatent()){
				listaReg.add(new LabelValueBean(prp.getNome(),""+prp.getId()));
			}
			tipiEstinzione.add(new LabelValueBean("------",null));
			for (TipoEstinzione te : tjb.getTipiEstinzione(dform.getTipoId())){
				tipiEstinzione.add(new LabelValueBean(te.getNome(), ""+te.getTipiEstinzioneId()));
			}
			se.setAttribute("dataPct", dataPct);
			se.setAttribute("dataEpc", dataEpc);
			se.setAttribute("sedi", allSedi);
			se.setAttribute("nazioni", nazioni);
			se.setAttribute("idiomi", idiomi);
			se.setAttribute("denoms", denoms);
			se.setAttribute("sedeInit", sedeInit);
			se.setAttribute("listaNaz", listaNaz);
			se.setAttribute("listaReg", listaReg);
			se.setAttribute("tipiEstinzione", tipiEstinzione);
			request.setAttribute("datiDepEst", dform);
		}else{							//POST
			dform = (DepositoForm)form;
			HashMap<Integer, StoricoTitolarita>mapSto = (HashMap<Integer, StoricoTitolarita>) se.getAttribute("mapSto");
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
					//if(st.getId()<0) st.setId(null);
				}
			}
			if(request.getParameter("update").startsWith("0")){
				dep = new DepEst();
				BeanUtils.copyProperties(dep, dform);
				if(dform.getId().intValue()==0)
					dep.setId(UtilityFunctions.generateRandomId());
				if(dform.getPrimo()==1)					//se primo deposito... 
					for (DepEst dxx : listD) {
						if(dxx.getId()!=dep.getId())		//...toglie il flag da ogni altro deposito
							dxx.setPrimo(0);
					}
				if(mapSto!=null)	dep.setStoricoTitolarita(new ArrayList<StoricoTitolarita>(mapSto.values()));
				dep.setStato(tjb.getStato(dep.getStatoId()));
				dep.setStudioBrevettuale(tjb.getStudioBrevettuale(dep.getStudioBrevettualeId()));
				dep.setTipoEstinzione(tjb.getTipoEstinzione(dep.getTipiEstinzioneId()));
				if(dform.getTipoId()!=DepEst.DEPOSITO){
					List<Stato> stati=new ArrayList<Stato>();
					for(Iterator i= Arrays.asList(ArrayUtils.nullToEmpty(dform.getListaNaz())).iterator(); i.hasNext();){
						stati.add(tjb.getStato(Integer.decode(""+i.next())));
					}
					dep.setStati(stati);
					HashMap<Integer, TipoPctRegionalPatent> map = new HashMap<Integer, TipoPctRegionalPatent>();
					for(Iterator i=tjb.getTipiRegionalPatent().iterator(); i.hasNext();){
						TipoPctRegionalPatent tip=(TipoPctRegionalPatent)i.next();
						map.put(tip.getId(), tip);
					}
					List<TipoPctRegionalPatent> regioni=new ArrayList<TipoPctRegionalPatent>();
					for(Iterator i= Arrays.asList(ArrayUtils.nullToEmpty(dform.getListaReg())).iterator(); i.hasNext();){
						Integer id = Integer.decode(""+i.next());
						regioni.add(map.get(id));
					}
					dep.setTipiPctRegionalPatent(regioni);
				}
				if((key = dform.getKey())==0){
					listD.add(dep);
					mapDep.put(Long.parseLong(UtilityFunctions.numForm.format(dep.getDataDeposito())+Math.abs(dep.getId())), listD.indexOf(dep));
				}else{
					int pos = mapDep.get(key);
					listD.set(pos, dep);
					Long key2 = Long.parseLong(UtilityFunctions.numForm.format(dep.getDataDeposito())+Math.abs(dep.getId()));
					if(key!=key2){
						mapDep.remove(key);
						mapDep.put(key2, pos);
					}
				}
				se.setAttribute("depositi", listD);
				se.setAttribute("mapdep", mapDep);
				request.setAttribute("act", "close");
			}	//else request.setAttribute("datiDepEst", dform);
		} 
		return mapping.findForward(page[dform.getTipoId()]);
	}

}
