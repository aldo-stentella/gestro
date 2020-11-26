package it.cnr.brevetti.verbali.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.EmptyFileException;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import it.cnr.brevetti.config.Config;
import it.cnr.brevetti.config.Settings;
import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.domain.TrovatoQuery;
import it.cnr.brevetti.ejb.entities.Dipartimento;
import it.cnr.brevetti.ejb.entities.DocumentoInfo;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.ejb.entities.Verbale;
import it.cnr.brevetti.gas.SessioneUtente;
import it.cnr.brevetti.login.exceptions.ExpiredSessionException;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.UtilityFunctions;

public class AddVerb extends Action {
	static final int NSRIF_POSITION = Integer.parseInt(Settings.getInstance().get("it.cnr.brevetti.verbali.column.nsrif"));
	static final int AZIONE_POSITION = Integer.parseInt(Settings.getInstance().get("it.cnr.brevetti.verbali.column.azione"));
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ExpiredSessionException, Exception {
		HttpSession se=request.getSession(true);
		SessioneUtente user = (SessioneUtente)se.getAttribute("sessioneUtente");
		if(user==null)
			throw new ExpiredSessionException();
		TrovatoJB tjb = TrovatoJB.getInstance();
		if (request.getMethod().equals("GET")) {
			return mapping.findForward("AddVerb");
		} else {
			if(ServletFileUpload.isMultipartContent(request)){
				ServletFileUpload upload = new ServletFileUpload();
				upload.setFileItemFactory(new DiskFileItemFactory());
				ArrayList<FileItem> items = (ArrayList<FileItem>)upload.parseRequest(request);
				FileItem item = items.get(1);
				if(item.getSize()>UtilityFunctions.MAX_FILE_SIZE){
					request.setAttribute("mess", "Dimensione del file eccessiva. Impossibile caricare il documento.");
					return mapping.findForward("AddVerb");				
				}
				File itemp = new File(item.getName());
                String nomeFile = itemp.getName();
                nomeFile = nomeFile.substring(java.lang.Math.max(nomeFile.lastIndexOf("/"), nomeFile.lastIndexOf("\\")) + 1);
                
        		Map<Integer, String>map = new HashMap<Integer, String>();
        		Set<Integer>trovati = new HashSet<Integer>();
        		Parametri p = new Parametri();
        		p.add(TrovatoQuery.AFFERENZA_ID, ((Dipartimento)se.getAttribute("dipartimento")).getId());
        		for(Trovato t : tjb.getTrovati(p) )
        			trovati.add(t.getNsrif());
        		XWPFDocument doc = null;
        		try {
        			InputStream fis = item.getInputStream();
        			doc = new XWPFDocument(fis);
        			Iterator<IBodyElement> iter = doc.getBodyElementsIterator();
        			boolean first = true;
        			while (iter.hasNext()) {
        				IBodyElement elem = iter.next();
        				if (elem instanceof XWPFParagraph) {
        					System.out.println(((XWPFParagraph) elem).getText());
        				} else if (elem instanceof XWPFTable) {
        					for (XWPFTableRow row : ((XWPFTable) elem).getRows()) {
        						String azione = row.getCell(AddVerb.AZIONE_POSITION).getText();
        						if(!first) for(XWPFParagraph par : row.getCell(AddVerb.NSRIF_POSITION).getParagraphs()){
        							if(par.getText().length()!=0){
        								String cpar = par.getText();
        								for(String temp : StringUtils.splitPreserveAllTokens(cpar)){
        									if(temp.trim().length()!=0){
        										Integer nsrif = Integer.decode(temp);
        										if(trovati.contains(nsrif)){
        											map.put(nsrif, (map.get(nsrif)!=null?map.get(nsrif)+ Config.CR :"") + azione);
        											System.out.println(nsrif+" - "+ azione);
        										} else {
        											request.setAttribute("mess", "Sono presenti NSRIF inesistenti. Caricamento annullato.");
        											return mapping.findForward("AddVerb");
        										}
        									}
        								}
        							}
        						} else {
        							//TODO check delle intestazioni di colonna?
        							first = false;
        						}
        					}
        				}
        			}
        			if(first){
        				request.setAttribute("mess", "Errore nel formato del verbale. Caricamento annullato.");
        				doc.close();
        				return mapping.findForward("AddVerb");				
        			}
        		} catch (FileNotFoundException e) {
        			e.printStackTrace();
					request.setAttribute("mess", "Impossibile caricare il documento.");
					return mapping.findForward("AddVerb");				
        		} catch (IOException e) {
        			e.printStackTrace();
        			request.setAttribute("mess", "Impossibile caricare il documento.");
        			return mapping.findForward("AddVerb");				
        		} catch (EmptyFileException e) {
        			e.printStackTrace();
        			request.setAttribute("mess", "E' obbligatorio caricare il documento.");
        			return mapping.findForward("AddVerb");				
        		} catch (NumberFormatException e) {
        			e.printStackTrace();
        			request.setAttribute("mess", "Errore nel formato del verbale. Caricamento annullato.");
        			return mapping.findForward("AddVerb");				
        		} finally {
        			doc.close();
        		}
        		Verbale ver = new Verbale();
        		List<Trovato> nsrifs = new ArrayList<Trovato>();
        		for(Integer nsrif : map.keySet()){
        			Trovato t = new Trovato();
        			t.setNsrif(nsrif);
        			t.setAzione(map.get(nsrif));
        			t.setRifiuto(0);
        			nsrifs.add(t);
        		}
                ver.setTrovati(nsrifs);
    			try {
    				ver.setDataVerbale(UtilityFunctions.itForm.parse(items.get(0).getString()));
    			} catch (ParseException e) {
    				request.setAttribute("mess", "Errore nel formato della data. Caricamento annullato.");
    				return mapping.findForward("AddVerb");				
    			} 
                Integer idv = tjb.salvaVerbale(ver).getId();
                
                Integer id = tjb.creaDocumento(item.get());
                DocumentoInfo di = new DocumentoInfo();
                di.setDocumentoId(id);
                di.setEntita(idv);
                di.setNomeFile(nomeFile.replaceAll("'", "_"));
                di.setTrovati(nsrifs);
                di.setTipoDocumentoId(10);
                di.setTipoDocumento(tjb.getTipoDocumento(di.getTipoDocumentoId()));
                tjb.salvaDocumentoInfo(di);
                request.setAttribute("act", "close");
                return mapping.findForward("AddVerb");
			}
			return mapping.findForward("close");
		}
	}

}
