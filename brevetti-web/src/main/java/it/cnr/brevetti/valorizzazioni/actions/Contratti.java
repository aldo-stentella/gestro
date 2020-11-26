package it.cnr.brevetti.valorizzazioni.actions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import it.cnr.brevetti.domain.DocumentoQuery;
import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.ejb.entities.Dipartimento;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.ejb.entities.DocumentoInfo;
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
import org.apache.commons.lang.StringUtils;

public class Contratti extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ExpiredSessionException, Exception {
		HttpSession se=request.getSession(true);
		SessioneUtente user = (SessioneUtente)se.getAttribute("sessioneUtente");
		if(user==null)
			throw new ExpiredSessionException();
		TrovatoJB tjb = TrovatoJB.getInstance();
		if (request.getMethod().equals("GET")) {
			List<DocumentoInfo> lista;
			Parametri p = new Parametri();
			//String ns = request.getParameter("nsrif");
			//Integer nsrif = Integer.parseInt(StringUtils.substringBefore(ns, "|"));		//per avere l'elenco doc. basta usare un qualsiasi nsrif del cluster, prelevo il 1°
			Integer entita = Integer.parseInt(request.getParameter("entita"));
			Integer tipoDocumentoId = Integer.parseInt(request.getParameter("tipoDocumentoId"));
			//p.add(DocumentoQuery.NSRIF, nsrif);
			p.add(DocumentoQuery.ENTITA, entita);
			p.add(DocumentoQuery.TIPO_DOCUMENTO, tipoDocumentoId);
			lista = tjb.getDocumentiInfo(p);
			request.setAttribute("docs", lista);
			request.setAttribute("tipoDocumento", tjb.getTipoDocumento(tipoDocumentoId).getNome());
			return mapping.findForward("contratti");
		} else {
			if(ServletFileUpload.isMultipartContent(request)){
				ServletFileUpload upload = new ServletFileUpload();
				upload.setFileItemFactory(new DiskFileItemFactory());
				ArrayList<FileItem> items = (ArrayList<FileItem>)upload.parseRequest(request);
				FileItem item = items.get(3);
				if(item.getSize()>UtilityFunctions.MAX_FILE_SIZE){
					request.setAttribute("mess", "Dimensione del file eccessiva. Impossibile caricare il documento.");
					return mapping.findForward("contratti");				
				}
				File itemp = new File(item.getName());
                String nomeFile = itemp.getName();
                nomeFile = nomeFile.substring(java.lang.Math.max(nomeFile.lastIndexOf("/"), nomeFile.lastIndexOf("\\")) + 1);
                Integer id = tjb.creaDocumento(item.get());
                DocumentoInfo doc = new DocumentoInfo();
                doc.setDocumentoId(id);
                doc.setEntita(Integer.parseInt(items.get(1).getString()));
                doc.setNomeFile(nomeFile.replaceAll("'", "_"));
                String ns = items.get(2).getString();
                List<Trovato> trovati = new ArrayList<Trovato>();
                //if(ns.indexOf("|")!=-1) 
                	for(StringTokenizer st = new StringTokenizer(ns, "|"); st.hasMoreTokens();) {
					String nsrif = st.nextToken();
					if(nsrif!=null && nsrif.length()>0){
						Trovato t = new Trovato();
						t.setNsrif(Integer.valueOf(nsrif));
						trovati.add(t);
					}
				}/*else {
					Trovato t = new Trovato();
					t.setNsrif(Integer.valueOf(ns.substring(0, ns.indexOf(":"))));
					trovati.add(t);		
				}*/
                doc.setTrovati(trovati);
                doc.setTipoDocumentoId(Integer.parseInt(items.get(0).getString()));
                doc.setTipoDocumento(tjb.getTipoDocumento(doc.getTipoDocumentoId()));
                tjb.salvaDocumentoInfo(doc);
                request.setAttribute("act", "close");
                return mapping.findForward("contratti");
			}
			return mapping.findForward("close");
		}
	}
}
