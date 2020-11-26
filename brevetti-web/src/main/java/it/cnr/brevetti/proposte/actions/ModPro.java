package it.cnr.brevetti.proposte.actions;

import java.lang.reflect.InvocationTargetException;

import it.cnr.brevetti.ejb.entities.Dipartimento;
import it.cnr.brevetti.ejb.entities.InventionDisclosure;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.login.exceptions.ExpiredSessionException;
import it.cnr.brevetti.proposte.javabeans.PropostaJB;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.AuditUtil;
import it.cnr.brevetti.util.ServiceLocatorException;
import it.cnr.brevetti.gas.SessioneUtente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ModPro extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServiceLocatorException, InvocationTargetException, IllegalAccessException, ExpiredSessionException {
        HttpSession se=request.getSession(true);
    	SessioneUtente user = (SessioneUtente)se.getAttribute("sessioneUtente");
		if(user==null)
			throw new ExpiredSessionException();
		Integer strutturaAfferenza=((Dipartimento)se.getAttribute("dipartimento")).getId();
		PropostaJB pjb = PropostaJB.getInstance();
		TrovatoJB tjb = TrovatoJB.getInstance();
        request.setAttribute("tipi", pjb.getTipiTrovato());
        request.setAttribute("stati", pjb.getStatiProposta());
        InventionDisclosure indi = null;
    	if (request.getMethod().equals("POST")){
    		Integer pid = NumberUtils.createInteger(request.getParameter("id"));
    		Integer dest = NumberUtils.createInteger(request.getParameter("dest"));
    		String note = request.getParameter("note");
           	indi = pjb.leggiProposta(pid);
           	Integer source = indi.getStato(); 
           	indi.setStato(dest);
           	if(StringUtils.isNotBlank(note)) indi.setNotaRifiuto(note);
			if(dest==InventionDisclosure.IN_ESAME && indi.getNsrif()==null){
				Trovato trovato = new Trovato();
				BeanUtils.copyProperties(trovato, indi);
				trovato.setProvvisorio(1);
				trovato.setPubblicato(1);
				trovato.setNote("Utente richiedente: "+indi.getUtenteLdap());
				trovato.setDataDomRichDeposito(indi.getDataTrasmissione());
				trovato.setCessioneDiritti(0);
				trovato.setUtentiId(request.getParameter("utentiId"));
				Dipartimento dipartimento = new Dipartimento();
				dipartimento.setId(strutturaAfferenza);
    			trovato.setDipartimento(dipartimento);
    			AuditUtil.getInstance().salva(trovato, user.getUtenteId() );
    			trovato = tjb.salvaTrovato(trovato);
    			indi.setNsrif(trovato.getNsrif());
			}
			indi = pjb.salvaProposta(indi);
			pjb.sendNotify(source, dest, indi, note);;
    	}
		request.setAttribute("indi", indi);
        return mapping.findForward("OkPro");
	}
}
