package it.cnr.brevetti.inventori.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import it.cnr.brevetti.ejb.entities.DipendenteCnr;
import it.cnr.brevetti.ejb.entities.Inventore;
import it.cnr.brevetti.inventori.actionForms.InventoreForm;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.ServiceLocatorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class SelInventore extends Action {
	
	    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServiceLocatorException, IllegalAccessException, InvocationTargetException {
	    	TrovatoJB tjb = TrovatoJB.getInstance();
	    	HttpSession se=request.getSession(true);
	    	ArrayList<LabelValueBean> tipi = new ArrayList<LabelValueBean>();
	    	//request.setAttribute("tipi", tipi);
			if(request.getMethod().equals("POST")){
				if(request.getParameter("act").startsWith("cerca")){
					if(request.getParameter("tipi_inventore").startsWith("2")){	/*cerca inventore non CNR per cognome*/
						String pattern = request.getParameter("esterni").trim()+"%";
						if(pattern.length()!=1){
							List allInventori = tjb.getInventori(pattern);
					    	Inventore inv;
					    	String cs ="";
					    	if(allInventori!=null){
								Iterator i = allInventori.iterator();
								while (i.hasNext()){
									inv = (Inventore)i.next();
//									TODO codificare i possibili status
									if(inv!=null && inv.getStatus()==1){
										if(cs.length()>0) cs=cs.concat(",");
										cs=cs.concat("\""+ inv.getCognome()+" "+inv.getNome()+" ("+inv.getId()+") \"");
									}
								}
								if(cs.length()==0){
									request.setAttribute("error", "Nessun inventore trovato");
									request.setAttribute("status", "inizia");
								} else {
									request.setAttribute("cs", cs);
									request.setAttribute("status", "scegli");
								}
							} else {
								request.setAttribute("error", "Nessun inventore trovato");
								request.setAttribute("status", "inizia");
							}
						} else request.setAttribute("status", "chiudi");
					} else {		/*tipi_inventore=1	-> cerca inventore CNR per matricola */
						String smatr = request.getParameter("matricola");
						if(smatr!=null && smatr.length()>0){
							Integer matricola;
							try {
								matricola = Integer.decode(smatr);
							} catch (NumberFormatException e) {
								e.printStackTrace();
								request.setAttribute("error", "Formato matricola non valido");
								request.setAttribute("status", "inizia");								
								return mapping.findForward("SelInventore");
							}
							//Inventore inv = tjb.getInventoreByMatricola(matricola);
							DipendenteCnr dip = tjb.getDipendenteByMatricola(matricola);
							
							if(dip==null){
								request.setAttribute("error", "Matricola inesistente");
								request.setAttribute("status", "inizia");
/*							} else if(dip.getDataCessazione()!=null && dip.getDataCessazione().before(new Date())) {
								request.setAttribute("error", "Dipendente non più in servizio");
								request.setAttribute("status", "inizia");	*/
							} else {
								InventoreForm iForm = new InventoreForm();
								BeanUtils.copyProperties(iForm, dip);
								request.setAttribute("iform", iForm);
								request.setAttribute("status", "conferma");
								tipi.add( new LabelValueBean("CNR","CNR"));
								se.setAttribute("tipi", tipi);
								return mapping.findForward("FormInventore");
								
							}
						} else request.setAttribute("status", "chiudi"); 
					}
				} else if(request.getParameter("act").startsWith("seleziona")){		/* seleziona inventore esterno dalla lista */
					String combo = request.getParameter("combo")+"";
					Integer id = Integer.decode((combo.length()==0)?"":combo.substring(combo.indexOf(" (")+2,combo.length()-2));
					Inventore inv = tjb.getInventore(id);
					InventoreForm iForm = new InventoreForm();
					BeanUtils.copyProperties(iForm, inv);
					tipi.add( new LabelValueBean(inv.getTipo(),inv.getTipo())); 
					se.setAttribute("tipi", tipi);
					request.setAttribute("iform", iForm);
					request.setAttribute("status", "conferma");
					return mapping.findForward("FormInventore");
				} else if(request.getParameter("act").startsWith("nuovo")){
					tipi.add( new LabelValueBean("ENTE_ESTERNO","ENTE_ESTERNO")); 
					tipi.add( new LabelValueBean("PRIVATO","PRIVATO"));
					tipi.add( new LabelValueBean("COLLABORATORE_CNR","COLLABORATORE_CNR"));
					se.setAttribute("tipi", tipi);
					request.setAttribute("status", "crea");
					request.setAttribute("iform", new InventoreForm());
					return mapping.findForward("FormInventore");
				} else if(request.getParameter("act").startsWith("ricomincia")){
						request.setAttribute("status", "inizia");
				} else {	/* act=ok */
					InventoreForm iForm = (InventoreForm)request.getAttribute("iform");
					Inventore inv = new Inventore();
					if(iForm.getId()==null || iForm.getId().intValue()==0){					//nuovo inventore
						BeanUtils.copyProperties(inv, iForm);
						inv.setOldSystem(0);
						inv.setStatus(1);
						if(inv.getIstitutiId()==null) inv.setIstitutiId(0);
						double rand = Math.random()*1000000000;
						int rani = (int)rand;
						inv.setId(-rani);
						HashMap<Integer, Inventore> map = (HashMap) se.getAttribute("nuoviInventori");
						if(map==null)	map=new HashMap<Integer, Inventore>();
						map.put(inv.getId(), inv);
						se.setAttribute("nuoviInventori", map);
					}else{
						BeanUtils.copyProperties(inv, iForm);	//inv = tjb.getInventore(Integer.decode(request.getParameter("ident")));
					}
					se.setAttribute("tipi", tipi);
					request.setAttribute("inv", inv);		
			    	request.setAttribute("status", "chiudi");
			    	return mapping.findForward("FormInventore");
				}
			} else
				request.setAttribute("status", "inizia");
	        return mapping.findForward("SelInventore");
	    }



}
