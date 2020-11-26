package it.cnr.brevetti.trovati.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import it.cnr.brevetti.domain.AbstractQuery;
import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.domain.TrovatoQuery;
import it.cnr.brevetti.ejb.entities.Classificazione;
import it.cnr.brevetti.ejb.entities.Dipartimento;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.ServiceLocatorException;
import it.cnr.brevetti.util.UtilityFunctions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Catalogo extends Action {
	private static final int maxLength = 200;
	private static final int infLimit = 5;
	TrovatoJB tjb;
	
	    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    	tjb = TrovatoJB.getInstance();
			if(request.getMethod().equals("GET") && request.getParameterMap().size()==0){
	    		String liv2="new Array('Tutte le sotto-categorie|0')";
	    		String liv3="new Array('Tutti i sotto-settori|0')";
				List<Classificazione> list1, list2, list3;
				List list4;
				Long[] totIPC = new Long[30], totTMC = new Long[50];
				list1 = tjb.getClassiLivello1();
				int k = 0;
				if(list1!=null)
					for(Classificazione cl1 : list1){
						list2 = tjb.getClassificazioniChildren(cl1.getId());
						liv2=liv2.concat(",new Array('Tutte le sotto-categorie|0'");
						if(list2!=null)
						for(Classificazione cl2 : list2){
							liv2 = liv2.concat(",'"+cl2.getNome().substring(cl2.getNome().indexOf("-")+2, Math.min(maxLength, cl2.getNome().length())).replaceAll("'", "\\\\\'")+"|"+cl2.getId()+"'");
						}
						liv2=liv2.concat(")");
						Long tot = new Long(0);
						if((tot = tjb.countTrovatiByClassificazione(cl1.getCodice()+" %"))!=null)
							totIPC[k] = tot;
						else
							totIPC[k] = new Long(0);
						k++;
					}
				request.setAttribute("totIPC", totIPC);
				request.setAttribute("list1", list1);
				request.setAttribute("liv2", liv2);
				list3 = tjb.getSettoriTecnologici();
				k =0;
				if(list3!=null)
					for(Iterator<Classificazione> i = list3.iterator(); i.hasNext();){
						Classificazione cl1 = i.next();
						list2 = tjb.getClassificazioniChildren(cl1.getId());
						liv3=liv3.concat(",new Array('Tutti i sotto-settori|0'");
						if(list2!=null)
							for(Iterator<Classificazione> j = list2.iterator(); j.hasNext();){
								Classificazione cl2 = j.next();
								liv3 = liv3.concat(",'"+cl2.getNome().substring(0, Math.min(maxLength, cl2.getNome().length())).replaceAll("'", "\\\\\'")+"|"+cl2.getId()+"'");
							}
						liv3=liv3.concat(")");
						Long tot = new Long(0);
						if((tot = tjb.countTrovatiByClassificazione(cl1.getCodice()+"%"))!=null)
							totTMC[k] = tot;
						else
							totTMC[k] = new Long(0);
						k++;
					}
				request.setAttribute("totTMC", totTMC);
				request.setAttribute("list3", list3);
				request.setAttribute("liv3", liv3);
				String[]deps = new String[20];
				for (Iterator iterator = tjb.getDipartimenti().iterator(); iterator.hasNext();) {
					Dipartimento dipart = (Dipartimento) iterator.next();
					if(dipart.getDataSoppressione() != null)
						deps[dipart.getId()] = dipart.getDescrizione();
				}
				request.setAttribute("deps",deps);
				request.setAttribute("dcount",tjb.countTrovatiByDipartimento());

			} else {
				String liv1=request.getParameter("liv1")!=null?request.getParameter("liv1"):"0";
				String liv2=request.getParameter("liv2")!=null?request.getParameter("liv2"):"0";
				String liv3=request.getParameter("liv3")!=null?request.getParameter("liv3"):"0";
				String liv4=request.getParameter("liv4")!=null?request.getParameter("liv4"):"0";
				String dipartimenti=request.getParameter("dipartimenti")!=null?request.getParameter("dipartimenti"):"0";
				String kw = request.getParameter("keywords")!=null?request.getParameter("keywords"):"";
				Parametri p = new Parametri();
				HashMap<Integer, Double> perKeywords = new HashMap<Integer, Double>();
				Double score;
				int size = 0;
				if(kw!=null && kw.length()!=0){
					kw = kw.replaceAll("[^a-zA-Z 0-9\"]", " ");		// caratteri da sopprimere: ^+-"~.:,;?!'()
					if(StringUtils.countMatches(kw, "\"")%2 != 0){
						kw = kw.replaceAll("[^a-zA-Z 0-9]", " ");		// doppi apici in numero dispari vengono rimossi
					}
					System.out.println("parola chiave 'clean': "+kw);
					try {
						perKeywords = UtilityFunctions.searchEngineQuery(kw);
					} catch (IOException e) {
						e.printStackTrace();
						String kw2 = "%"+kw+"%";
						if(StringUtils.countMatches(kw2, "\"")>0)
							kw2 = StringUtils.substringBefore(kw2, "\"").replace(" ", "%") + StringUtils.substringBetween(kw2, "\"") + StringUtils.substringAfterLast(kw2, "\"").replace(" ", "%");
						else
							kw2 = kw2.replace(" ", "%");
						p.add(TrovatoQuery.DIGEST, kw2.replaceAll("%+", "%"));						
					}
					if(perKeywords.isEmpty() && !p.getMap().containsKey(TrovatoQuery.DIGEST))	return mapping.findForward("ListaCatalogo");
				}
				p.add(TrovatoQuery.PUBBLICATO, 1);							//solo con flag "Pubblica su vetrina"
				p.add(TrovatoQuery.DATA_ABBANDONO, AbstractQuery.IS_NULL);
				ArrayList<Trovato> temp = (ArrayList<Trovato>) getTrovati(p);
				p.getMap().remove(TrovatoQuery.DATA_ABBANDONO);
				p.addRange(TrovatoQuery.DATA_ABBANDONO, new Date(), UtilityFunctions.itForm.parse("01/01/2900"));
				ArrayList<Trovato> nonEstinti = (ArrayList<Trovato>)ListUtils.sum(getTrovati(p), temp);			//lista trovati con almeno 1 DepEst non estinto (data estinsione nulla o futura)
				p.getMap().remove(TrovatoQuery.DATA_ABBANDONO);
				if(!dipartimenti.startsWith("0")){
					p.add(TrovatoQuery.DIPARTIMENTO_ID, Integer.decode(dipartimenti));
				}
				if(!liv2.startsWith("0")) {
					String cod = tjb.getClassificazione(Integer.decode(liv2)).getCodice();
					p.add(TrovatoQuery.CODICE, cod+"%");
				}else if(!liv1.startsWith("0")) {
					String cod = tjb.getClassificazione(Integer.decode(liv1)).getCodice();
					p.add(TrovatoQuery.CODICE, cod+" %");
				}
				if(!liv4.startsWith("0")) {
					String cod = tjb.getClassificazione(Integer.decode(liv4)).getCodice();
					p.add(TrovatoQuery.CODICE, cod+"%");
				}else if(!liv3.startsWith("0")) {
					String cod = tjb.getClassificazione(Integer.decode(liv3)).getCodice();
					p.add(TrovatoQuery.CODICE, cod+"%");
				}
				ArrayList<Trovato> perCriteria;
				try {
					perCriteria = (ArrayList<Trovato>) ListUtils.intersection(getTrovati(p), nonEstinti);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw e;
				}
				size = perCriteria.size();
				SortedMap<Double, Trovato> intersect = new TreeMap<Double, Trovato>(Collections.reverseOrder());
				if(!perKeywords.isEmpty()) {
					for (int i = 0; i<perCriteria.size(); i++) {
						Trovato trovato = (Trovato)perCriteria.get(i);
						if((score = perKeywords.get(trovato.getNsrif()))!=null){
							while(intersect.containsKey(score)) 
								score = score + new Double("0.000000000000001");
							intersect.put(score, trovato);
						}
					}
					size = intersect.size();
					request.setAttribute("ser",intersect);
				} else request.setAttribute("list",perCriteria);
				if(size<infLimit){
					if(p.getMap().containsKey(TrovatoQuery.DIPARTIMENTO_ID)){
						p.getMap().remove(TrovatoQuery.DIPARTIMENTO_ID);
						perCriteria = (ArrayList<Trovato>) ListUtils.intersection(getTrovati(p), nonEstinti);
						if(perCriteria!=null){
							int allDip = 0;
							if(perKeywords.isEmpty()) allDip = perCriteria.size();
							else for (int i = 0; i<perCriteria.size(); i++) {
								Trovato trovato = (Trovato)perCriteria.get(i);
								if(perKeywords.containsKey(trovato.getNsrif())) allDip++;
							}
							if(size<allDip) request.setAttribute("allDip", allDip);
						}
						p.add(TrovatoQuery.DIPARTIMENTO_ID, dipartimenti);
					}
					if(p.getMap().containsKey(TrovatoQuery.CODICE)){
						p.getMap().remove(TrovatoQuery.CODICE);
						perCriteria = (ArrayList<Trovato>) ListUtils.intersection(getTrovati(p), nonEstinti);
						if(perCriteria!=null){
							int allCat = 0;
							if(perKeywords.isEmpty()) allCat = perCriteria.size();
							else for (int i = 0; i<perCriteria.size(); i++) {
								Trovato trovato = (Trovato)perCriteria.get(i);
								if(perKeywords.containsKey(trovato.getNsrif())) allCat++;
							}
							if(intersect.size()<allCat) request.setAttribute("allCat", allCat);
						}
					}
					request.setAttribute("keywords", kw);
					request.setAttribute("liv1", liv1);
					request.setAttribute("liv2", liv2);
					request.setAttribute("liv3", liv3);
					request.setAttribute("liv4", liv4);
					request.setAttribute("dipartimenti", dipartimenti);
				}
				return mapping.findForward("ListaCatalogo");
			}
	        return mapping.findForward("Catalogo");
	    }
	    
	    ArrayList<Trovato> getTrovati(Parametri p) throws ServiceLocatorException {
	    	ArrayList<Trovato> trovati = (ArrayList<Trovato>)tjb.getTrovati(p);
	    	if (trovati==null) trovati = new ArrayList<Trovato>();
	    	return trovati;
	    }
}
