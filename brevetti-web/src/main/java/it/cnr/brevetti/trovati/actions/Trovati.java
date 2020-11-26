package it.cnr.brevetti.trovati.actions;

import it.cnr.brevetti.ejb.entities.ClassificazioneInternDep;
import it.cnr.brevetti.ejb.entities.DepEst;
import it.cnr.brevetti.ejb.entities.Dipartimento;
import it.cnr.brevetti.ejb.entities.DocumentoInfo;
import it.cnr.brevetti.ejb.entities.Inventore;
import it.cnr.brevetti.ejb.entities.Istituto;
import it.cnr.brevetti.ejb.entities.StoricoTitolarita;
import it.cnr.brevetti.ejb.entities.Titolarita;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.ejb.entities.Utente;
import it.cnr.brevetti.login.exceptions.ExpiredSessionException;
import it.cnr.brevetti.trovati.actionForms.TrovatoForm;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.AuditUtil;
import it.cnr.brevetti.util.UtilityFunctions;
import it.cnr.brevetti.gas.SessioneUtente;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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

/**
 * @author astentella
 *
 * Action invocata nella finestra del trovato per il passaggio ad altro tab o premendo il pulsante 'salva'
 */
public class Trovati extends Action {
	
	    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    	HttpSession se=request.getSession(true);
	    	SessioneUtente user = (SessioneUtente)se.getAttribute("sessioneUtente");
			if(user==null)
				throw new ExpiredSessionException();
			Integer strutturaAfferenza=((Dipartimento)se.getAttribute("dipartimento")).getId();			//user.getStruttura());
	    	String[] forwards = mapping.findForwards();
	    	Arrays.sort(forwards);
   		    String[] nextabs = new String[forwards.length+1];
   		    nextabs[0] = "successful";
   		    System.arraycopy(forwards, 0, nextabs, 1, forwards.length);
	        TrovatoJB tjb = TrovatoJB.getInstance();
	        TrovatoForm dform = (TrovatoForm)se.getAttribute("datiTrovato");
	        if(request.getParameter("percent0")!=null){							//intercetta e salva i dati del tab Titolarità, quando da esso si cambia tab o si preme Salva
	        	System.out.println("-----------------tab richiedenti");
    			if(dform.getUpdTit()!=null && dform.getUpdTit().intValue()==1){	//controllare se sono state fatte modifiche
    				HashMap<Integer, Titolarita>mapTit = (HashMap<Integer, Titolarita>) se.getAttribute("mapTit");
        			Method met;
        			String sx;
        			Class args[] = { };
        			Object obj[] = { };
        			for(int i = 0; i<UtilityFunctions.MAX_TITOLARITA; i++){
        				sx = ("" + i);
        				met = TrovatoForm.class.getMethod("getTitolariId"+sx, args);
        				String tt = (String)met.invoke(dform, obj);
        				if(tt!=null && tt.length()!=0 && mapTit.containsKey(Integer.valueOf(tt))){
	        				met = TrovatoForm.class.getMethod("getPercent"+sx, args);
	        				mapTit.get(Integer.valueOf(tt)).setPercentuale((BigDecimal)met.invoke(dform, obj));
        				}
        			}
    			}
	        }
	        request.setAttribute("tabNumber", new Integer(dform.getNextab()));
	        if(dform.getNote()!=null && dform.getNote().trim().length()>0)
	        	request.setAttribute("noteGenButton", "modNote.gif");
	        else
	        	request.setAttribute("noteGenButton", "insNote.gif");
	        String[] bundleArray = {"", "BI", "", "DA", "TM", "MU", "NV", "SW", "KH"};
	        se.setAttribute("bundleArray", bundleArray);
        	//ArrayList<LabelValueBean> tipi = new ArrayList<LabelValueBean>();
        	ArrayList<LabelValueBean> diritti = new ArrayList<LabelValueBean>();
        	ArrayList<LabelValueBean> dipartimenti = new ArrayList<LabelValueBean>();
        	ArrayList<LabelValueBean> utenti = new ArrayList<LabelValueBean>();
        	if(nextabs[dform.getNextab()].indexOf("datitrov")>-1){
	        	//tipi.add( new LabelValueBean("---Selezionare una tipologia---","0")); 
	        	//for(TipoTrovato tt : tjb.getTipiTrovato())
	        	//	tipi.add( new LabelValueBean(tt.getTipo(), ""+tt.getId()));
	        	//TODO creare tabella associata?
	        	String tipoTrovato = request.getParameter("tipoTrovato");
	        	if(tipoTrovato!=null && tipoTrovato.length()>0)
	        		dform.setTipiTrovatoId(Integer.parseInt(tipoTrovato));
	        	dform.setTipiTrovatoDescrizione(tjb.getTipoTrovato(dform.getTipiTrovatoId()).getTipo());
	        	/*diritti.add( new LabelValueBean("---Selezionare un'opzione---","-1"));
        		diritti.add( new LabelValueBean("Art.65 commi 1-4", "0"));
        		diritti.add( new LabelValueBean("Art.65 comma 5", "2"));
        		diritti.add( new LabelValueBean("ceduto al CNR", "1"));*/
				diritti.add( new LabelValueBean("---Selezionare un'opzione---","0"));
				for(int i = 1; i<tjb.getCessioneDiritti().length; i++)
					diritti.add(new LabelValueBean(tjb.getCessioneDiritti()[i], ""+i));
        		dipartimenti.add(new LabelValueBean("---Selezionare un dipartimento", "0"));
        		for(Dipartimento dip : tjb.getDipartimenti()) {
        			if(dip.getDataSoppressione() != null)
        				dipartimenti.add(new LabelValueBean(dip.getDescrizione(), ""+dip.getId()));
        		}
        		utenti.add( new LabelValueBean("---Selezionare un Referente Titolo---", ""));
        		for(Utente utente : tjb.getUtenti() ){
        			utenti.add(new LabelValueBean(utente.getNome()+" "+utente.getCognome(),utente.getUtenteId()));
        		}
	        	//se.setAttribute("tipi", tipi);
	        	se.setAttribute("diritti", diritti);
	        	se.setAttribute("dipartimenti", dipartimenti);
	        	se.setAttribute("utenti", utenti);
	        } else if(nextabs[dform.getNextab()].indexOf("invetrov")>-1){
	        	request.setAttribute("inventoreIndex", dform.getInventoreIndex());
	        } else if(dform.getNextab()==0){							//tasto 'Salva'
	    			Trovato trovato = new Trovato();
	    			BeanUtils.copyProperties(trovato, dform);
       				if(dform.getUpdDep()!=null && dform.getUpdDep().intValue()==1){	//controllare se sono state fatte modifiche
       					List<DepEst> deps = (List<DepEst>)se.getAttribute("depositi");
       					if(deps!=null && deps.size()!=0){
       						for (DepEst depEst : deps){
       							if(depEst.getId()<0)	depEst.setId(null);
       							if(depEst.getStoricoTitolarita()!=null) for(StoricoTitolarita st : depEst.getStoricoTitolarita()){
       								if(st.getId()<0) st.setId(null);
       							}
       						}
       						trovato.setDepEst(deps);
       					}
    				}
        			int x = 1;
        			Method met;
        			String sx;
        			Class args[] = { };
        			Object obj[] = { };
        			List<Inventore> invs = new ArrayList(); //trovato.getInventori();
        			x = 100;	    
        			HashMap<Integer, Inventore> map = (HashMap<Integer, Inventore>)se.getAttribute("nuoviInventori");
        			if(dform.getUpdInv()!=null && dform.getUpdInv().intValue()==1){	//controllare se sono state fatte modifiche
	        			for(int i = 0; i<UtilityFunctions.MAX_INVENTORI;i++){
	        				/* trick to obtain 2-digit series like 00,01,02..10,11.. */
	        				sx = ("" + x++).substring(1);
	        				met = TrovatoForm.class.getMethod("getInventoriId"+sx, args );
	        				Integer idInv = (Integer)met.invoke(dform, obj);
	        				Inventore inv = null; 
	        				if(idInv!=null && idInv.intValue()!=0) {
	        					if(idInv.intValue()>0) {
	        						inv = tjb.getInventore(idInv);        				
	        					}else{
	        						inv = map.get(idInv);
	        						inv.setId(null);
	        					}
	        					invs.add(inv);
	        				}
	        			}
	        			trovato.setInventori(invs);
	        			//trovato.setInventoreIndex(dform.getInventoreIndex());
        			}
        			List<Istituto> ists =  new ArrayList<Istituto>(); //trovato.getIstituti();
        			/* if elements exceded 10 elements, use above trick to have 2-digit series */
        			x = 0;
        			if(dform.getUpdIst()!=null && dform.getUpdIst().intValue()==1){	//controllare se sono state fatte modifiche
	        			for(int i = 0; i<UtilityFunctions.MAX_ISTITUTI;i++){
	        				sx = ("" + x++);
	        				met = TrovatoForm.class.getMethod("getIstitutiId"+sx, args);
	        				Integer idIst = (Integer)met.invoke(dform, obj);
	        				if(idIst!=null && idIst.intValue()!=0){
	        					Istituto ist = (Istituto) tjb.getIstituto(idIst);
	        					ists.add(ist);
	        				}
	        			}
	        			trovato.setIstituti(ists);
        			}
        			if(dform.getUpdClas()!=null && dform.getUpdClas().intValue()==1){	//controllare se sono state fatte modifiche
	        			List<ClassificazioneInternDep> clist =  new ArrayList<ClassificazioneInternDep>(); //trovato.getClassificazioniInternazionali();
	        			TreeMap<Integer, ClassificazioneInternDep> mapCla = (TreeMap<Integer, ClassificazioneInternDep>)se.getAttribute("classificazioni");
	        			if(mapCla!=null && mapCla.size()>0) for (Iterator<Integer> iterator = mapCla.keySet().iterator(); iterator.hasNext();) {
							ClassificazioneInternDep cla =  mapCla.get(iterator.next());
							cla.setClassificazioneInternDepId(null);				//in caso di aggiornamenti, si svuota la lista precedente delle relazioni e si salvano ex-novo
							clist.add(cla);
						}
	        			trovato.setClassificazioniInternazionali(clist);
        			}
        			List<Titolarita> tits= new ArrayList<Titolarita>(); //trovato.getTitolarita();
        			/* if elements exceded 10 elements, use above trick to have 2-digit series */
        			x = 0;
        			if(dform.getUpdTit()!=null && dform.getUpdTit().intValue()==1){	//controllare se sono state fatte modifiche
        				HashMap<Integer, Titolarita> mapTit = (HashMap<Integer, Titolarita>)se.getAttribute("mapTit");
        				if(mapTit!=null && mapTit.size()!=0)
        					for(Iterator<Integer> i=mapTit.keySet().iterator(); i.hasNext();){
        						Integer k = i.next();
        						Titolarita nuovo = mapTit.get(k);
        						nuovo.setId(null);						//in caso di aggiornamenti, si svuota la lista precedente delle relazioni e si salvano ex-novo
        						tits.add(nuovo);
        					}   			
        				trovato.setTitolarita(tits);
        			}
        			if(dform.getUpdDoc()!=null && dform.getUpdDoc().intValue()==1)
        				trovato.setDocumenti((List<DocumentoInfo>) se.getAttribute("documenti"));
        			Dipartimento dipartimento = new Dipartimento();
        			if(dform.getNsrif()==null || dform.getNsrif().intValue()==0){
        				dipartimento.setId(strutturaAfferenza);
	        			trovato.setDipartimento(dipartimento);									//dipartimento di afferenza assegnato alla creazione
	        			AuditUtil.getInstance().salva(trovato, user.getUtenteId() );
	    				request.setAttribute("trovato", tjb.salvaTrovato(trovato));		//cdclrb
        			} else {
	    				dipartimento.setId(dform.getDipartimentoTitolare());
	    				trovato.setDipartimento(dipartimento);									//Aggiunto x uniformita', di norma non viene riscritto in aggiornamento. 
	    				//trovato.setNsrif(dform.getNsrif());
	    				AuditUtil.getInstance().aggiorna(trovato, user.getUtenteId());
	    				request.setAttribute("trovato", tjb.aggiornaTrovato(trovato));		//cdclmb
	    			}
        			/*/TODO workaround in attesa che venga corretto errore in GestioneTrovatoService.aggiornaDocumenti()
        			List<Integer> eliminati = (List<Integer>) se.getAttribute("docEliminati");
        			if(eliminati!=null){
        				for (Integer id : eliminati) {
        					tjb.delDocumentoInfo(id);
						}
        				se.removeAttribute("docEliminati");
        			}
        			FINE*/
        			
	    			//UtilityFunctions.cleanSession(se);
	    			request.removeAttribute("tabNumber");
	    			return mapping.findForward("successful");
	    			
	    		}
		    	return mapping.findForward(nextabs[dform.getNextab()]);
	    }
}
