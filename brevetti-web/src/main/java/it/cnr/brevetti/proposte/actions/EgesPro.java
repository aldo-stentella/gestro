package it.cnr.brevetti.proposte.actions;

import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.domain.PropostaQuery;
import it.cnr.brevetti.ejb.entities.DocumentoInfo;
import it.cnr.brevetti.ejb.entities.InventionDisclosure;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.proposte.javabeans.PropostaJB;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.CipherUtil;
import it.cnr.brevetti.util.KeyFactory;
import it.cnr.brevetti.util.ServiceLocatorException;
import it.cnr.brevetti.util.UtilityFunctions;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EgesPro extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServiceLocatorException, FileUploadException, Exception {
        HttpSession se=request.getSession(true);
    	String username = (String)se.getAttribute("sessioneEutente");
    	PropostaJB pjb = PropostaJB.getInstance();
    	TrovatoJB tjb = TrovatoJB.getInstance();
    	if(StringUtils.isEmpty(username))
    		return mapping.findForward("Elogin");
        request.setAttribute("tipi", pjb.getTipiTrovato());
        request.setAttribute("stati", pjb.getStatiProposta());
        Integer pid;
        InventionDisclosure indi = null;
        String forward = "EgesPro";
    	if (request.getMethod().equals("GET")){
    		pid = NumberUtils.createInteger(request.getParameter("id"));
            if(pid==null){				//pagina riepilogativa delle proposte
            	Parametri p = Parametri.getNewParametri(PropostaQuery.UTENTE_LDAP , username);
            	List<InventionDisclosure>proposte = pjb.leggiProposte(p);
            	request.setAttribute("proposte", proposte);
            	forward = "EselPro";
            }else			            //pagina di dettaglio
            	indi = pjb.leggiProposta(pid);
    	}else{
			if(ServletFileUpload.isMultipartContent(request)){
				ServletFileUpload upload = new ServletFileUpload();
				upload.setFileItemFactory(new DiskFileItemFactory());
				@SuppressWarnings("unchecked")
				ArrayList<FileItem> items = (ArrayList<FileItem>)upload.parseRequest(request);
				Integer tipoDocumentoId = Integer.parseInt(items.get(0).getString());
				pid = NumberUtils.createInteger(items.get(1).getString());
				indi = pjb.leggiProposta(pid);
				FileItem item = items.get(2);
				String message = items.get(3).getString();
				String mess = null;
				if(StringUtils.isBlank(message) && item.getSize()==0){
					mess = "E' necessario inviare, in base a quanto richiesto nella notifica ricevuta, un nuovo documento o un messaggio scritto nell'area di testo.";
				}else if(item.getSize()>UtilityFunctions.MAX_FILE_SIZE){
					mess = "Dimensione del file eccessiva. Impossibile caricare il documento.";
				}
				if(mess!=null){
					request.setAttribute("mess", mess);
					request.setAttribute("message", message);
				}else{
					Date now = new Date();
					if(item.getSize()!=0){
						File itemp = new File(item.getName());
						String nomeFile = itemp.getName();
						nomeFile = nomeFile.substring(java.lang.Math.max(nomeFile.lastIndexOf("/"), nomeFile.lastIndexOf("\\")) + 1);
						Integer did = pjb.creaDocumento(item.get());
						DocumentoInfo doc = new DocumentoInfo();
						doc.setDocumentoId(did);
						doc.setEntita(indi.getId());
						doc.setNomeFile(nomeFile.replaceAll("'", "_"));
						doc.setDataDocumento(now);
						doc.setTipoDocumentoId(tipoDocumentoId);
						doc.setTipoDocumento(pjb.getTipoDocumento(doc.getTipoDocumentoId()));
						pjb.salvaDocumentoInfo(doc);
						List<DocumentoInfo>docs = indi.getDocumenti();
						docs.add(doc);
						indi.setDocumenti(docs);
						if(indi.getNsrif()!=null){
							Trovato t = tjb.getTrovato(indi.getNsrif(), 0);
							List<DocumentoInfo>tdocs=t.getDocumenti();
							tdocs.add(doc);
							t.setDocumenti(tdocs);
							tjb.aggiornaTrovato(t);
						}
					}
					indi.setDataTrasmissione(now);
					indi.setStato(InventionDisclosure.IN_ESAME);
					indi.setNotaRifiuto(message);
					indi = pjb.salvaProposta(indi);
					pjb.sendNotify(3, 2, indi, message);
					forward = "EokPro";
				}
			}else
				throw new java.lang.IllegalAccessError() ;
    	}
		request.setAttribute("indi", indi);
        return mapping.findForward(forward);
	}
}
