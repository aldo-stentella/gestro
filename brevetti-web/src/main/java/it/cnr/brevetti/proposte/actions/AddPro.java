package it.cnr.brevetti.proposte.actions;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import it.cnr.brevetti.domain.DocumentoQuery;
import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.ejb.entities.DocumentoInfo;
import it.cnr.brevetti.ejb.entities.InventionDisclosure;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.login.exceptions.LoginFailureException;
import it.cnr.brevetti.proposte.javabeans.PropostaJB;
import it.cnr.brevetti.trovati.actionForms.TrovatoForm;
import it.cnr.brevetti.util.CipherUtil;
import it.cnr.brevetti.util.KeyFactory;
import it.cnr.brevetti.util.ServiceLocatorException;
import it.cnr.brevetti.util.UtilityFunctions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AddPro extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServiceLocatorException, FileUploadException, Exception {
        HttpSession se=request.getSession(true);
    	String username = (String)se.getAttribute("sessioneEutente");
    	PropostaJB pjb = PropostaJB.getInstance();
    	if(StringUtils.isEmpty(username))
    		return mapping.findForward("Elogin");
    	String titolo = "";
    	Integer tipiTrovatoId = 0;
    	int[]map = {0,0,0,1,2,3,4,5,6};
    	if (request.getMethod().equals("POST")) {
			if(ServletFileUpload.isMultipartContent(request)){
				ServletFileUpload upload = new ServletFileUpload();
				upload.setFileItemFactory(new DiskFileItemFactory());
				ArrayList<FileItem> items = (ArrayList<FileItem>)upload.parseRequest(request);
				Integer tipoDocumentoId = Integer.parseInt(items.get(0).getString());
				titolo = items.get(2).getString();
				tipiTrovatoId = Integer.parseInt(items.get(3).getString());
				FileItem item = items.get(1);
				String mess = null;
				if(StringUtils.isBlank(titolo)){
					mess = "Specificare il titolo del trovato.";
				}
				if(item.getSize()>UtilityFunctions.MAX_FILE_SIZE){
					mess = "Dimensione del file eccessiva. Impossibile caricare il documento.";
				}else if(item.getSize()==0){
					mess = "Caricare il documento 'comunicazione preliminare di invenzione' compilato.";
				}
				if(mess!=null)
					request.setAttribute("mess", mess);
				else{
					File itemp = new File(item.getName());
					String nomeFile = itemp.getName();
					nomeFile = nomeFile.substring(java.lang.Math.max(nomeFile.lastIndexOf("/"), nomeFile.lastIndexOf("\\")) + 1);
					Integer id = pjb.creaDocumento(item.get());
					InventionDisclosure indi = new InventionDisclosure();
					Date now = new Date();
					indi.setDataTrasmissione(now);
					indi.setTipiTrovatoId(tipiTrovatoId);
					indi.setTitolo(titolo);
					indi.setUtenteLdap(username);
					indi.setStato(InventionDisclosure.TRASMESSA);
					indi = pjb.salvaProposta(indi);
					DocumentoInfo doc = new DocumentoInfo();
					doc.setDocumentoId(id);
					doc.setEntita(indi.getId());
					doc.setNomeFile(nomeFile.replaceAll("'", "_"));
					doc.setDataDocumento(now);
					doc.setTipoDocumentoId(tipoDocumentoId);
					doc.setTipoDocumento(pjb.getTipoDocumento(doc.getTipoDocumentoId()));
					pjb.salvaDocumentoInfo(doc);
					List<DocumentoInfo>docs = new ArrayList<DocumentoInfo>();
					docs.add(doc);
					indi.setDocumenti(docs);
					indi = pjb.salvaProposta(indi);
					request.setAttribute("indi", indi);
					request.setAttribute("tipi", pjb.getTipiTrovato());
					pjb.sendNotify(null, 1, indi, "");
					return mapping.findForward("EokPro");
				}
			}
    	}
    	request.setAttribute("titolo", titolo);
    	request.setAttribute("tipiTrovatoId", map[tipiTrovatoId]);
        return mapping.findForward("AddPro");
	}

}
