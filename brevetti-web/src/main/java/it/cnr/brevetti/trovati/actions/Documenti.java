package it.cnr.brevetti.trovati.actions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.cnr.brevetti.domain.DocumentoQuery;
import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.ejb.entities.DocumentoInfo;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.login.exceptions.ExpiredSessionException;
import it.cnr.brevetti.trovati.actionForms.TrovatoForm;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.UtilityFunctions;
import it.cnr.brevetti.gas.SessioneUtente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Documenti extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ExpiredSessionException, Exception {
		HttpSession se=request.getSession(true);
		SessioneUtente user = (SessioneUtente)se.getAttribute("sessioneUtente");
		if(user==null)
			throw new ExpiredSessionException();
		TrovatoJB tjb = TrovatoJB.getInstance();
		List<DocumentoInfo> documenti = (List<DocumentoInfo>) se.getAttribute("documenti");
		HashMap<String, List<DocumentoInfo>>fascicoli = (HashMap<String, List<DocumentoInfo>>) se.getAttribute("fascicoli");
		List<DocumentoInfo> lista;
		Parametri p = new Parametri();
		if (request.getMethod().equals("GET")) {
			Integer nsrif = Integer.parseInt(request.getParameter("nsrif"));
			Integer entita = Integer.parseInt(request.getParameter("entita"));
			Integer tipoDocumentoId = Integer.parseInt(request.getParameter("tipoDocumentoId"));
			String key = entita+"|"+tipoDocumentoId;
			if(fascicoli!=null && fascicoli.containsKey(key)){
				lista = fascicoli.get(key);
			} else {
				p.add(DocumentoQuery.NSRIF, nsrif);
				p.add(DocumentoQuery.ENTITA, entita);
				p.add(DocumentoQuery.TIPO_DOCUMENTO, tipoDocumentoId);
				lista = tjb.getDocumentiInfo(p);
				
			}
			request.setAttribute("docs", lista);
			request.setAttribute("tipoDocumento", tjb.getTipoDocumento(tipoDocumentoId).getNome());
			return mapping.findForward("documenti");
		} else {
			if(ServletFileUpload.isMultipartContent(request)){
				ServletFileUpload upload = new ServletFileUpload();
				upload.setFileItemFactory(new DiskFileItemFactory());
				ArrayList<FileItem> items = (ArrayList<FileItem>)upload.parseRequest(request);
				FileItem item = items.get(3);
				if(item.getSize()>UtilityFunctions.MAX_FILE_SIZE){
					request.setAttribute("mess", "Dimensione del file eccessiva. Impossibile caricare il documento.");
					return mapping.findForward("documenti");				
				}
				File itemp = new File(item.getName());
                String nomeFile = itemp.getName();
                nomeFile = nomeFile.substring(java.lang.Math.max(nomeFile.lastIndexOf("/"), nomeFile.lastIndexOf("\\")) + 1);
                Integer id = tjb.creaDocumento(item.get());
                DocumentoInfo doc = new DocumentoInfo();
                doc.setDocumentoId(id);
                doc.setEntita(Integer.parseInt(items.get(1).getString()));
                doc.setNomeFile(nomeFile.replaceAll("'", "_"));
                List<Trovato> trovati = new ArrayList<Trovato>();
                Trovato t = new Trovato();
                Integer nsrif = Integer.parseInt(items.get(2).getString());
                t.setNsrif(nsrif);
                trovati.add(t);
                doc.setTrovati(trovati);
                doc.setTipoDocumentoId(Integer.parseInt(items.get(0).getString()));
                doc.setTipoDocumento(tjb.getTipoDocumento(doc.getTipoDocumentoId()));
                documenti.add(doc);
                String key = doc.getEntita()+"|"+doc.getTipoDocumentoId();
    			if(fascicoli==null){
    				fascicoli = new HashMap<String, List<DocumentoInfo>>();
    				se.setAttribute("fascicoli", fascicoli);
    			}
    			if(fascicoli.containsKey(key)){
    				lista = fascicoli.get(key);
    			} else {
    				p = new Parametri();
    				p.add(DocumentoQuery.NSRIF, nsrif);
    				p.add(DocumentoQuery.ENTITA, doc.getEntita());
    				p.add(DocumentoQuery.TIPO_DOCUMENTO, +doc.getTipoDocumentoId());
    				lista = tjb.getDocumentiInfo(p);
    				if(lista==null) lista = new ArrayList<DocumentoInfo>();
    				fascicoli.put(key, lista);
    			}
    			lista.add(doc);
    			//TrovatoForm dform = (TrovatoForm)se.getAttribute("datiTrovato");
    			//dform.setUpdDoc(1);
    			request.setAttribute("act", "close");
    			return mapping.findForward("documenti");
			}
			return mapping.findForward("close");
		}
	}
}
