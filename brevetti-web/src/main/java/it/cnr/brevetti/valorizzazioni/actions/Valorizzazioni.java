package it.cnr.brevetti.valorizzazioni.actions;

import it.cnr.brevetti.domain.DocumentoQuery;
import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.domain.TrovatoQuery;
import it.cnr.brevetti.ejb.entities.Azienda;
import it.cnr.brevetti.ejb.entities.Dipartimento;
import it.cnr.brevetti.ejb.entities.DocumentoInfo;
import it.cnr.brevetti.ejb.entities.TipoValorizzazione;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.ejb.entities.Valorizzazione;
import it.cnr.brevetti.login.exceptions.ExpiredSessionException;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.AuditUtil;
import it.cnr.brevetti.valorizzazioni.actionForms.ValorizzazioneForm;
import it.cnr.brevetti.valorizzazioni.javabeans.ValorizzazioneJB;
import it.cnr.brevetti.gas.SessioneUtente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class Valorizzazioni extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ExpiredSessionException, Exception {
		HttpSession se=request.getSession(true);
		SessioneUtente user = (SessioneUtente)se.getAttribute("sessioneUtente");
		if(user==null)
			throw new ExpiredSessionException();
		Integer strutturaAfferenza= ((Dipartimento)se.getAttribute("dipartimento")).getId();		//Integer.decode(user.getStruttura());
		ValorizzazioneJB vjb = ValorizzazioneJB.getInstance();
        Parametri p = new Parametri();
		if (request.getMethod().equals("GET")) {
			ArrayList<LabelValueBean> tipi = new ArrayList<LabelValueBean>();
			tipi.add( new LabelValueBean("---Selezionare un tipo---","0"));
			for (TipoValorizzazione tv : vjb.getTipiValorizzazione()) {
				tipi.add( new LabelValueBean(tv.getNome(),""+tv.getId()));
			}
			se.setAttribute("tipiValorizzazione", tipi);
			ArrayList<LabelValueBean> aziende = new ArrayList<LabelValueBean>();
			aziende.add( new LabelValueBean("---Selezionare un'azienda---","0")); 
			for(Azienda azienda: vjb.getAziende()) {
				aziende.add( new LabelValueBean(azienda.getNome()+ (azienda.getTipo()!=null && azienda.getTipo().equalsIgnoreCase("spin-off")?" $$$ SpinOff $$$ ":"") +" - "+azienda.getLocalita().replaceAll("NON DISP.", ""), ""+azienda.getId()));
			}
			Collections.sort(aziende);
			se.setAttribute("aziende", aziende);
			ArrayList<LabelValueBean> trovati = new ArrayList<LabelValueBean>();
			p = new Parametri();
			p.add(TrovatoQuery.AFFERENZA_ID, strutturaAfferenza);
			for(Trovato tro: vjb.getTrovati(p)){
				trovati.add(new LabelValueBean(""+tro.getNsrif()+": "+StringUtils.left(tro.getTitolo(),90), ""+tro.getNsrif()));
			}
			se.setAttribute("trovati", trovati);
	    	return mapping.findForward("valorizzazione");
		}else{
			ValorizzazioneForm iForm = (ValorizzazioneForm) form;
			Valorizzazione val = new Valorizzazione();
			BeanUtils.copyProperties(val, iForm);
			if(val.getId()!=null && val.getId()==0)						//INSERT
				val.setId(null);
			if(val.getAziendaId()<0){
				val.setAziendaId(null);
				//TODO inserire nuova azienda
			}
			if(iForm.getUpdCluster().startsWith("1")) {
				List<Trovato> trovati = new ArrayList<Trovato>();
				for(StringTokenizer st = new StringTokenizer(iForm.getnCluster(), "|"); st.hasMoreTokens();) {
					String nsrif = st.nextToken();
					if(nsrif!=null && nsrif.length()>0){
						Trovato t = new Trovato();
						t.setNsrif(Integer.valueOf(nsrif.substring(0, nsrif.indexOf(":"))));
						trovati.add(t);
					}
				}
				val.setTrovati(trovati);
				if(val.getId()!=null){
					p = new Parametri();
					p.add(DocumentoQuery.ENTITA, val.getId());
					p.add(DocumentoQuery.TIPO_DOCUMENTO, 8);
					TrovatoJB tjb = TrovatoJB.getInstance();
					List<DocumentoInfo> lista = tjb.getDocumentiInfo(p);
					for (DocumentoInfo documentoInfo : lista) {
						documentoInfo.setTrovati(trovati);
						tjb.salvaDocumentoInfo(documentoInfo);
					}
				}
			}
			if(val.getId()==null)
				AuditUtil.getInstance().salva(val, user.getUtenteId() );
			else
				AuditUtil.getInstance().aggiorna(val, user.getUtenteId() );
			Valorizzazione v = vjb.saveValorizzazione(val);
			iForm.setId(v.getId());
			iForm.setAziendaNome(v.getAzienda().getNome());
			iForm.setAziendaCitta(v.getAzienda().getLocalita());
			iForm.setAziendaRegione(v.getAzienda().getRegione());
			iForm.setAziendaTipo(v.getAzienda().getTipo());
			if(v.getTipoValorizzazioneId()!=TipoValorizzazione.ALTRO)
				iForm.setTipoValorizzazioneNome(v.getTipo().getNome());
			else
				iForm.setTipoValorizzazioneNome(v.getAltro());
			request.setAttribute("iform", iForm);
			return mapping.findForward("successful");
		}
	}

}
