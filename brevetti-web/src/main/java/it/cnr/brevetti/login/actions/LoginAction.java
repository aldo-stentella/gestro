package it.cnr.brevetti.login.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import it.cnr.brevetti.ejb.entities.Dipartimento;
import it.cnr.brevetti.login.exceptions.LoginFailureException;
import it.cnr.brevetti.login.javabeans.LoginJB;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.ServiceLocatorException;
import it.cnr.brevetti.util.UtilityFunctions;
import it.cnr.brevetti.gas.SessioneUtente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LoginAction extends Action {

	private static final Integer appid = new Integer(2);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServiceLocatorException, LoginFailureException {
        HttpSession se=request.getSession(true);
        if (request.getMethod().equals("POST")) {
        	LoginJB ljb = LoginJB.getInstance();
        	TrovatoJB tjb = TrovatoJB.getInstance();
        	HashMap<Integer, Dipartimento> deps = new HashMap<Integer, Dipartimento>();
        	for(Iterator i = tjb.getDipartimenti().iterator(); i.hasNext();){
				Dipartimento dp = (Dipartimento)i.next();
				deps.put(dp.getId(), dp);
        	}
        	String username = request.getParameter("login");
        	String password = request.getParameter("password");
        	String dipartimento = request.getParameter("dipartimento");
        	SessioneUtente su = (SessioneUtente)se.getAttribute("sessioneUtente");
        	if(dipartimento!=null && dipartimento.length()>0){
        		username = su.getUtente().getNomeUtente();
    			su = ljb.getSessioneUtente(username, appid, dipartimento);
    			se.setAttribute("sessioneUtente", su);
        		se.setAttribute("dipartimento", deps.get(Integer.decode(dipartimento)));
        		if(su.hasRole("amministra trovati")) se.setAttribute("amministraTrovati", "true");
        		else se.removeAttribute("amministraTrovati");
        		System.out.println("§ - Dipartimento di sessione: "+dipartimento);
				request.setAttribute("action", "open");
				return mapping.findForward("open");
        	}
        	boolean isAlreadyLogged = false;
        	if(su!=null && se.getAttribute("dipartimento")==null){
        		isAlreadyLogged = true;
        		username = su.getUtenteId();
        	}
        	boolean testMode = false;
        	System.out.println("modalita': "+servlet.getServletContext().getInitParameter("mode"));
        	if((""+servlet.getServletContext().getInitParameter("mode")).startsWith("-TEST MODE-"))
        		testMode = true;
        	if(ljb.verificaUtente(username) && (ljb.autenticaUtente(username, password)||testMode||isAlreadyLogged)){
        		try {
					su = ljb.getSessioneUtente(username, appid);
					System.out.println("§ - "+su.getUtenteId()+" ha effettuato il login.");
        			se.setAttribute("sessioneUtente", su);
				} catch (RuntimeException e) {
					e.printStackTrace();
					throw new LoginFailureException();
				}
				
				ArrayList<Dipartimento> adeps = new ArrayList<Dipartimento>();
				/*if(su.getStruttura().indexOf(";")>-1){
					StringTokenizer st = new StringTokenizer(su.getStruttura(), ";");
					while(st.hasMoreTokens())
						adeps.add(deps.get(Integer.decode(st.nextToken())));
				} else adeps.add(deps.get(Integer.decode(su.getStruttura())));
				for(Iterator<Integer> i = UtilityFunctions.estraiDaExa(su.getStruttura()).iterator(); i.hasNext();){
					adeps.add(deps.get(i.next()));
				}
				 */
				for(Iterator<String> i = su.getStrutture().iterator(); i.hasNext();){
					adeps.add(deps.get(Integer.decode(i.next())));					
				}
				if(adeps.size()==1){
					se.setAttribute("dipartimento", adeps.get(0));
					if(su.hasRole("amministra trovati")) se.setAttribute("amministraTrovati", "true");
					request.setAttribute("action", "open");
					return mapping.findForward("open");
				} else{
					request.setAttribute("adeps", adeps);
					se.setAttribute("multiDipartimento", su.getStrutture().size()>1);	//(UtilityFunctions.estraiDaExa(su.getStruttura()).size()>1));
				}
        	}else
        		throw new LoginFailureException();
        }
        return mapping.findForward("login");
	}
}
