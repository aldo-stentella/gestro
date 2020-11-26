package it.cnr.brevetti.inventori.actions;

import it.cnr.brevetti.ejb.entities.DipendenteCnr;
import it.cnr.brevetti.ejb.entities.Inventore;
import it.cnr.brevetti.inventori.actionForms.InventoreForm;
import it.cnr.brevetti.inventori.javabeans.InventoreJB;
import it.cnr.brevetti.login.exceptions.ExpiredSessionException;
import it.cnr.brevetti.util.ServiceLocatorException;
import it.cnr.brevetti.gas.SessioneUtente;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class GesInv extends Action {
	
	    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServiceLocatorException, IllegalAccessException, InvocationTargetException, ExpiredSessionException {
			HttpSession se=request.getSession(true);
			SessioneUtente user = (SessioneUtente)se.getAttribute("sessioneUtente");
			if(user==null)
				throw new ExpiredSessionException();
	    	InventoreJB ijb = InventoreJB.getInstance();
	    	ArrayList<LabelValueBean> tipi = new ArrayList<LabelValueBean>();
			if(request.getMethod().equals("POST")){
				if(request.getParameter("act").startsWith("cerca")){
					if(request.getParameter("tipi_inventore").startsWith("2")){	/*cerca inventore non CNR per cognome*/
						String pattern = request.getParameter("esterni").trim()+"%";
						List<Inventore> allInventori = ijb.getInventori(pattern);
				    	Inventore inv;
				    	String cs ="";
				    	if(allInventori!=null){
							Iterator<Inventore> i = allInventori.iterator();
							while (i.hasNext()){
								inv = i.next();
								if(inv!=null && (request.getParameter("flagAttivi")==null || inv.getStatus()==1)){
									if(cs.length()>0) cs=cs.concat(",");
									cs=cs.concat("\""+ inv.getCognome()+" "+inv.getNome()+" ("+inv.getId()+") \"");
								}
							}
							if(cs.length()==0){
								request.setAttribute("error", "Nessun inventore non disabilitato trovato");
								request.setAttribute("action", "inizia");
							} else {
								request.setAttribute("cs", cs);
								request.setAttribute("action", "scegli");
							}
						} else {
							request.setAttribute("error", "Nessun inventore trovato");
							request.setAttribute("action", "inizia");
						}
				    	return mapping.findForward("QbeInv");
					} else {		/*tipi_inventore=1	-> cerca inventore CNR per matricola */
						String smatr = request.getParameter("matricola");
						if(smatr!=null && smatr.length()>0){
							Integer matricola;
							try {
								matricola = Integer.decode(smatr);
							} catch (NumberFormatException e) {
								e.printStackTrace();
								request.setAttribute("error", "Formato matricola non valido");
								request.setAttribute("action", "inizia");								
								return mapping.findForward("QbeInv");
							}
							DipendenteCnr dip = ijb.getDipendenteByMatricola(matricola);
							
							if(dip==null){
								request.setAttribute("error", "Matricola inesistente");
								request.setAttribute("action", "inizia");
								return mapping.findForward("QbeInv");
							} else {
								InventoreForm iForm = new InventoreForm();
								BeanUtils.copyProperties(iForm, dip);
								iForm.setStatus(1);
								request.setAttribute("iform", iForm);
								request.setAttribute("action", "modifica");
								tipi.add( new LabelValueBean("CNR","CNR"));
								se.setAttribute("tipi", tipi);
								return mapping.findForward("FormInventore");
							}
						} else {
							request.setAttribute("error", "Matricola inesistente");
							request.setAttribute("action", "inizia");
							return mapping.findForward("QbeInv");
						}
					}
				} else if(request.getParameter("act").startsWith("seleziona")){		/* seleziona inventore esterno dalla lista */
					String combo = request.getParameter("combo")+"";
					Integer id = Integer.decode((combo.length()==0)?"":combo.substring(combo.indexOf(" (")+2,combo.length()-2));
					Inventore inv = ijb.getInventore(id);
					InventoreForm iForm = new InventoreForm();
					BeanUtils.copyProperties(iForm, inv);
					tipi.add( new LabelValueBean("ENTE_ESTERNO","ENTE_ESTERNO")); 
					tipi.add( new LabelValueBean("PRIVATO","PRIVATO"));
					tipi.add( new LabelValueBean("COLLABORATORE_CNR","COLLABORATORE_CNR"));
					se.setAttribute("tipi", tipi);
					request.setAttribute("iform", iForm);
					request.setAttribute("action", "modifica");
					return mapping.findForward("FormInventore");
				} else {	/* act=ok */
					InventoreForm iForm = (InventoreForm)request.getAttribute("iform");
					Inventore inv = new Inventore();
					BeanUtils.copyProperties(inv, iForm);
					ijb.saveInventore(inv);
					tipi.add( new LabelValueBean(iForm.getTipo(),iForm.getTipo()));
					se.setAttribute("tipi", tipi);
					request.setAttribute("iform", iForm);	
			    	request.setAttribute("action", "chiudi");
			    	return mapping.findForward("FormInventore");
				}
			} else {
				if(request.getParameter("act")!=null && request.getParameter("act").startsWith("new")){
					tipi.add( new LabelValueBean("ENTE_ESTERNO","ENTE_ESTERNO")); 
					tipi.add( new LabelValueBean("PRIVATO","PRIVATO"));
					tipi.add( new LabelValueBean("COLLABORATORE_CNR","COLLABORATORE_CNR"));
					se.setAttribute("tipi", tipi);
					request.setAttribute("action", "crea");
					return mapping.findForward("FormInventore");
				}
				request.setAttribute("action", "inizia");
				return mapping.findForward("QbeInv");
			}
	    }



}
