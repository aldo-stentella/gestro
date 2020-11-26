package it.cnr.brevetti.classificazioniInternDep.actions;

import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import it.cnr.brevetti.ejb.entities.Classificazione;
import it.cnr.brevetti.ejb.entities.ClassificazioneInternDep;
import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.UtilityFunctions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SelClasse extends Action {
	private static final int maxLength = 200;


	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id1="'0'",id2="'0'",id3="'0'",nome1="'Selez. classificazione 1°livello'",nome2="'Selez. classificazione 2°livello'",nome3="'Selez. classificazione 3°livello'";
		TrovatoJB tjb = TrovatoJB.getInstance();
		HttpSession se=request.getSession(true);
		String tipo = ""+request.getParameter("tipo");
		String classificazioneInternDepId = (""+request.getParameter("id")).replaceAll("null", "0");
		String liv1 = (""+request.getParameter("liv1")).replaceAll("null", "0");
		String liv2 = (""+request.getParameter("liv2")).replaceAll("null", "0");
		String liv3 = (""+request.getParameter("liv3")).replaceAll("null", "0");
		String altraClassificazione = request.getParameter("altraClassificazione")!=null?request.getParameter("altraClassificazione"):"";
		String aggiorna = request.getParameter("aggiorna");
		TreeMap<Integer, ClassificazioneInternDep> mapCla = (TreeMap<Integer, ClassificazioneInternDep>) se.getAttribute("classificazioni");
		if(mapCla==null) mapCla = new TreeMap<Integer, ClassificazioneInternDep>();
		ClassificazioneInternDep cid = mapCla.get(Integer.decode(classificazioneInternDepId));
		if(request.getMethod().equals("GET")) {
			if(cid!=null){																	//modifica di una classificazione preesistente
				Integer temp = cid.getClassificazione().getId();
				Integer lTemp = cid.getClassificazione().getLivello();
				if(lTemp==3){
					liv3 = ""+temp;
					temp = cid.getClassificazione().getIdPadre();
					lTemp=2;
				}
				if(lTemp==2){
					liv2 = ""+temp;
					temp = cid.getClassificazione().getIdPadre();
				}
				liv1 = ""+temp;
				altraClassificazione = cid.getAltraClassificazione();
			}
		} else if(aggiorna!=null && aggiorna.startsWith("0")){									//submit di conferma
			if(cid==null){
				cid = new ClassificazioneInternDep();
				cid.setClassificazioneInternDepId(UtilityFunctions.generateRandomId());
			}
			Integer id;
			if(!liv3.startsWith("0"))			//tipo=1 (IPC)
				id = Integer.decode(liv3);
			else if(!liv2.startsWith("0"))		//tipo=2 (Merc.tecn.)
				id = Integer.decode(liv2);
			else
				id = Integer.decode(liv1);
			cid.setClassificazioniId(id);
			cid.setClassificazione(tjb.getClassificazione(id));
			cid.setAltraClassificazione(altraClassificazione);
			mapCla.put(cid.getClassificazioneInternDepId(), cid);
			se.setAttribute("classificazioni", mapCla);
			request.setAttribute("act", "close");					
			return mapping.findForward("SelClasse");
		}
		List<Classificazione> list = tjb.getClassiLivello1();
		if(tipo.startsWith("1")) list = tjb.getClassiLivello1();	//IPC
		else list = tjb.getSettoriTecnologici();					//Merc.tecn.
		if(list!=null)
			for(Iterator<Classificazione> i = list.iterator(); i.hasNext();){
				Classificazione cl1 = i.next();
				id1 = id1.concat(",'"+cl1.getId()+"'");
				if(cl1.getId().toString().startsWith(liv1))	request.setAttribute("sel1", cl1.getId());
				nome1 = nome1.concat(",'"+cl1.getNome().substring(0, Math.min(maxLength, cl1.getNome().length())).replaceAll("'", "\\\\\'")+"'");
			}

		if(!liv1.startsWith("0")){
			list = tjb.getClassificazioniChildren(Integer.decode(liv1));
			if(list!=null)
				for(Iterator<Classificazione> i = list.iterator(); i.hasNext();){
					Classificazione cl2 = i.next();
					id2 = id2.concat(",'"+cl2.getId()+"'");
					if(cl2.getId().toString().startsWith(liv2))	request.setAttribute("sel2", cl2.getId());
					nome2 = nome2.concat(",'"+cl2.getNome().substring(0, Math.min(maxLength, cl2.getNome().length())).replaceAll("'", "\\\\\'")+"'");
				}

		}
		if(!liv2.startsWith("0")){
			list = tjb.getClassificazioniChildren(Integer.decode(liv2));
			if(list!=null)
				for(Iterator<Classificazione> i = list.iterator(); i.hasNext();){
					Classificazione cl3 = i.next();
					id3 = id3.concat(",'"+cl3.getId()+"'");
					if(cl3.getId().toString().startsWith(liv3))	request.setAttribute("sel3", cl3.getId());
					nome3 = nome3.concat(",'"+cl3.getNome().substring(0, Math.min(maxLength, cl3.getNome().length())).replaceAll("'", "\\\\\'")+"'");
				}
		}
		request.setAttribute("id", classificazioneInternDepId);
		request.setAttribute("tipo", tipo);
		request.setAttribute("id1", id1);
		request.setAttribute("nome1", nome1);
		request.setAttribute("id2", id2);
		request.setAttribute("nome2", nome2);	
		if(tipo.startsWith("1")){
			request.setAttribute("id3", id3);
			request.setAttribute("nome3", nome3);	
			request.setAttribute("altraClassificazione", altraClassificazione);
		}
		request.setAttribute("act", "show");
		return mapping.findForward("SelClasse");
	}



}
