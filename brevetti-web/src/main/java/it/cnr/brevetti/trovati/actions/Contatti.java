package it.cnr.brevetti.trovati.actions;

import it.cnr.brevetti.util.CaptchaServiceSingleton;
import it.cnr.brevetti.util.UtilityFunctions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.octo.captcha.service.CaptchaServiceException;

import javax.mail.*;
import javax.mail.internet.*;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class Contatti extends Action {	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession se=request.getSession(true);
		if(request.getMethod().equals("POST")){
			Boolean isResponseCorrect =Boolean.FALSE;
			//remember that we need an id to validate!
			String captchaId = request.getSession().getId();
			//retrieve the response
			String captcha = request.getParameter("j_captcha_response");
			// Call the Service method
			try {
				isResponseCorrect = CaptchaServiceSingleton.getInstance().validateResponseForID(captchaId, captcha);
			} catch (CaptchaServiceException e) {
				//should not happen, may be thrown if the id is not valid
				request.setAttribute("error", true);
			}
			if(!isResponseCorrect){
				request.setAttribute("captchaError", true);
			}else {
				String body = "E' stata ricevuta la seguente richiesta di informazioni dall'applicazione Gestione Trovati:\n\n"+
				"Nominativo: "+request.getParameter("nominativo")+"\n"+
				"Societa' / ente: "+request.getParameter("ente")+"\n"+
				"Posizione ricoperta: "+request.getParameter("posizione")+"\n"+
				"Email di contatto: "+request.getParameter("email")+"\n"+
				"Telefono: "+request.getParameter("telefono")+"\n"+
				"Trovato da cui e' partita la richiesta: NsRif "+request.getParameter("nsrif")+"\n"+
				"Informazioni richieste: "+request.getParameter("info")+"\n\n"+
				"Servizio di notifica dell'applicazione Gestione Trovati\n";
				String hBody = "<html><head></head><body>E' stata ricevuta la seguente richiesta di informazioni dall'applicazione <b>Gestione Trovati</b>:<br>\n<br>\n"+
						"Nominativo: "+StringEscapeUtils.escapeHtml(request.getParameter("nominativo"))+"<br>\n"+
						"Societ&agrave; / ente: "+StringEscapeUtils.escapeHtml(request.getParameter("ente"))+"<br>\n"+
						"Posizione ricoperta: "+StringEscapeUtils.escapeHtml(request.getParameter("posizione"))+"<br>\n"+
						"Email di contatto: <a href='mailto:"+request.getParameter("email")+"'>"+request.getParameter("email")+"</a><br>\n"+
						"Telefono: "+StringEscapeUtils.escapeHtml(request.getParameter("telefono"))+"<br>\n"+
						"Trovato da cui &egrave; partita la richiesta: NsRif "+request.getParameter("nsrif")+"<br>\n"+
						"Informazioni richieste: "+StringEscapeUtils.escapeHtml(request.getParameter("info")).replaceAll("\n", "\n<br/>")+"<br>\n<br>\n"+
						"<i>Servizio di notifica dell'applicazione Gestione Trovati</i><br>\n"+
						"</html>";
				String toAddress =  "aldo.stentella@cnr.it";
				String subject = "[BREVETTI] Richiesta informazioni";
				try {
					UtilityFunctions.sendHtmlMail(body, hBody, toAddress, null, subject);
				} catch (MessagingException e) {
					request.setAttribute("error", true);
				}
			}



		}
		return mapping.findForward("Contatti");
	}



}
