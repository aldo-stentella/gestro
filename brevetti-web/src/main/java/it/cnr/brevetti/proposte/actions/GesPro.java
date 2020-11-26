package it.cnr.brevetti.proposte.actions;

import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.domain.PropostaQuery;
import it.cnr.brevetti.domain.TrovatoQuery;
import it.cnr.brevetti.ejb.entities.DocumentoInfo;
import it.cnr.brevetti.ejb.entities.IdTransition;
import it.cnr.brevetti.ejb.entities.InventionDisclosure;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.ejb.entities.Utente;
import it.cnr.brevetti.login.exceptions.ExpiredSessionException;
import it.cnr.brevetti.proposte.javabeans.PropostaJB;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.ServiceLocatorException;
import it.cnr.brevetti.util.UtilityFunctions;
import it.cnr.brevetti.gas.SessioneUtente;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class GesPro extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServiceLocatorException, ExpiredSessionException {
        HttpSession se=request.getSession(true);
        SessioneUtente user = (SessioneUtente)se.getAttribute("sessioneUtente");
        if(user==null)
        	throw new ExpiredSessionException();
        PropostaJB pjb = PropostaJB.getInstance();
        TrovatoJB tjb = TrovatoJB.getInstance();
        request.setAttribute("tipi", pjb.getTipiTrovato());
        String[] stati = pjb.getStatiProposta();
        request.setAttribute("stati", stati);
        Parametri p = new Parametri();
    	if (request.getMethod().equals("POST")) {
    		request.setAttribute("stili", pjb.getStiliProposta());
    		if((request.getParameter("fromList")!=null && request.getParameter("fromList").startsWith("1")) || StringUtils.isNotBlank(request.getParameter("idProposta"))){
    			InventionDisclosure id = pjb.leggiProposta(Integer.parseInt(request.getParameter("idProposta")));
    			if(id==null){
    				request.setAttribute("mess", "Nessun risultato");
    	    		return mapping.findForward("SelPro");
    			}    				
    			SortedMap<Long, DocumentoInfo> mapDocs = new TreeMap<Long, DocumentoInfo>();
    			for(DocumentoInfo td:id.getDocumenti())
    				mapDocs.put(td.getDataDocumento().getTime(), td);
    			List<IdTransition>tran = id.getTransizioni();
    			int oldStatus = 0;
    			StringBuilder st = new StringBuilder();
    			for(IdTransition it : tran){
    				st.append("["+UtilityFunctions.itForm.format(it.getDataTransizione())+"]: ");
    				if(it.getNota()!=null){
    					st.append(stati[oldStatus]+" -> "+stati[it.getStato()] +"\n");
    					st.append(it.getNota()+"\n");
	    			}else
	    				st.append("Trasmissione da inventore\n");
    				st.append("\n");
    				oldStatus = it.getStato();
    			}
    			ArrayList<LabelValueBean> utenti = new ArrayList<LabelValueBean>();
    			if(id.getNsrif()!=null){
    				Utente utente = pjb.getTrovato(id.getNsrif()).getUtente();
    				utenti.add(new LabelValueBean(utente.getNome()+" "+utente.getCognome(),utente.getUtenteId()));
    			}else {
    				utenti.add( new LabelValueBean("---Selezionare un Referente Titolo---", ""));
    				for(Utente utente : pjb.getUtenti() ){
    					utenti.add(new LabelValueBean(utente.getNome()+" "+utente.getCognome(),utente.getUtenteId()));
    				}
    			}
    			request.setAttribute("utenti", utenti);
    			request.setAttribute("id", id);
    			request.setAttribute("docs", mapDocs);
    			request.setAttribute("history", st.toString());
    			request.setAttribute("adiacenze", pjb.getAdjacencies(id.getStato()));    			
    			return mapping.findForward("InventionDisclosure");
    		}
    		String dadata = request.getParameter("dadata");
    		String adata = request.getParameter("adata");
    		if(StringUtils.isNotBlank(dadata) || StringUtils.isNotBlank(adata))
				try {
					if(StringUtils.isBlank(dadata)) dadata ="01/01/1900";
					if(StringUtils.isBlank(adata)) adata="01/01/2900";
					p.addRange(PropostaQuery.DATA_TRASMISSIONE, UtilityFunctions.itForm.parse(dadata),DateUtils.addDays(UtilityFunctions.itForm.parse(adata),1));
				} catch (ParseException e) {
					request.setAttribute("mess", "Data non valida");
					return mapping.findForward("SelPro");
				}
     		if(!request.getParameter("tipiTrovatoId").startsWith("0"))
    			p.add(PropostaQuery.TIPI_TROVATO_ID, request.getParameter("tipiTrovatoId"));
     		if(!request.getParameter("stato").startsWith("0"))
     			p.add(PropostaQuery.STATO, request.getParameter("stato"));
    		if(StringUtils.isNotBlank(request.getParameter("utenteLdap")))
    			p.add(PropostaQuery.UTENTE_LDAP, request.getParameter("utenteLdap"));
    		List<InventionDisclosure> proposte = pjb.leggiProposte(p);
    		p = Parametri.getNewParametri();
    		if(StringUtils.isNotBlank(request.getParameter("utente")) && proposte!=null) {
    			p.add(TrovatoQuery.UTENTI_ID, request.getParameter("utente"));
    			List<Trovato> result = tjb.getTrovati(p);
    			if(result!=null) {
    				HashSet<Integer>nsrifs = new HashSet<Integer>();
    				for (Trovato trovato : result)
    					nsrifs.add(trovato.getNsrif());
    				List<InventionDisclosure> temp = new ArrayList<InventionDisclosure>();
    				for (InventionDisclosure id : proposte) 
    					if(nsrifs.contains(id.getNsrif()))
    						temp.add(id);
    				proposte = temp.isEmpty() ? null : temp;
    			} else proposte = null;
    		}
    		if(proposte!=null)
    			request.setAttribute("proposte", proposte);
    		else
    			request.setAttribute("mess", "Nessun risultato");
    		return mapping.findForward("SelPro");
    	} else
    		request.setAttribute("referenti", tjb.getUtenti());
        return mapping.findForward("GesPro");
	}
}
