package it.cnr.brevetti.trovati.actions;

import java.util.ArrayList;
import java.util.Iterator;
import it.cnr.brevetti.ejb.entities.ClassificazioneInternDep;
import it.cnr.brevetti.ejb.entities.Inventore;
import it.cnr.brevetti.ejb.entities.Titolarita;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.ServiceLocatorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * @author astentella
 * 
 */

public class InfoCatalogo extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServiceLocatorException {
		String nsrif = request.getParameter("nsrif");
		String dip = request.getParameter("dip");
		TrovatoJB tjb = TrovatoJB.getInstance();
		Trovato tro;
		try {
			tro = tjb.getTrovato(Integer.decode(nsrif),Integer.decode(dip));
			String titolari = "";
			String inventori = "";
			String IPC = "";
			String TMC = "";
			if(tro.getTitolarita()!=null) for(Iterator<Titolarita> i = tro.getTitolarita().iterator(); i.hasNext();){
				Titolarita tit = i.next();
				if (tit.getTipiTitolareId().intValue()==1 && !titolari.startsWith("Consiglio Nazionale delle Ricerche", 4))
					titolari = ("<li>Consiglio Nazionale delle Ricerche</li>").concat(titolari);
				else if (tit.getTipiTitolareId().intValue()==4)
					titolari = titolari.concat("<li>"+tit.getEnteEsterno().getNome()+"</li>");
				/*else if (tit.getTipiTitolareId().intValue()==3)
				titolari = titolari.concat("<li>"+tit.getInventore().getCognome()+" "+tit.getInventore().getNome()+" (inventore)</li>");
				 */

			}
			if(tro.getInventori()!=null) for(Iterator<Inventore> i = tro.getInventori().iterator(); i.hasNext();){
				Inventore inv = i.next();
				String ist = "";
				if("CNR".equalsIgnoreCase(inv.getTipo()) && inv.getIstitutiId()!=0)
					ist = " ("+tjb.getIstituto(inv.getIstitutiId()).getIstitutoSigla()+")";
				inventori = inventori.concat("<li>"+inv.getCognome()+" "+inv.getNome()+ist+"</li>");
			}
			if(tro.getClassificazioniInternazionali()!=null){ 
				ArrayList<Integer> ipcs = new ArrayList<Integer>();
				for(Iterator<ClassificazioneInternDep> i = tro.getClassificazioniInternazionali().iterator(); i.hasNext();){
					ClassificazioneInternDep cla = i.next();
					if(cla.getClassificazione().getTipo()==1){
						if(!ipcs.contains(cla.getClassificazione().getId())){
							ipcs.add(cla.getClassificazione().getId());
							String temp = cla.getClassificazione().getNome();
							IPC = IPC.concat("<li>"+temp.substring(temp.indexOf(" - ")+3)+"</li>");
						}
					} else if(cla.getClassificazione().getTipo()==2){
						TMC = TMC.concat("<li>"+cla.getClassificazione().getNome()+"</li>");
					}
				}
			}
			request.setAttribute("trovato", tro);
			request.setAttribute("titolari", titolari);
			request.setAttribute("inventori", inventori);
			request.setAttribute("IPC", IPC);
			request.setAttribute("TMC", TMC);
		} catch (NumberFormatException e) {
			System.out.println("Parametri NsRif o dip non numerici");
			e.printStackTrace();
			return mapping.findForward("null");
		} catch (NullPointerException e) {
			System.out.println("Parametri NsRif o dip mancanti");
			e.printStackTrace();
			return mapping.findForward("null");
		}
		return mapping.findForward("InfoCatalogo");            
	}
}
