package it.cnr.brevetti.valorizzazioni.actions;

import it.cnr.brevetti.ejb.entities.TipoValorizzazione;
import it.cnr.brevetti.ejb.entities.Valorizzazione;
import it.cnr.brevetti.util.ServiceLocatorException;
import it.cnr.brevetti.valorizzazioni.actionForms.ValorizzazioneForm;
import it.cnr.brevetti.valorizzazioni.javabeans.ValorizzazioneJB;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InfoVal extends Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServiceLocatorException, IllegalAccessException, InvocationTargetException {
    	ValorizzazioneJB vjb = ValorizzazioneJB.getInstance();
		if(request.getMethod().equals("GET")){
			Integer id = Integer.decode(request.getParameter("id"));
			Valorizzazione val = vjb.getValorizzazione(id);
			ValorizzazioneForm iForm = new ValorizzazioneForm();
			BeanUtils.copyProperties(iForm, val);
			iForm.setAziendaNome(val.getAzienda().getNome()+ (val.getAzienda().getTipo()!=null && val.getAzienda().getTipo().equalsIgnoreCase("spin-off")?" $$$ SpinOff $$$ ":"" ));
			iForm.setAziendaCitta(val.getAzienda().getLocalita());
			iForm.setAziendaRegione(val.getAzienda().getRegione());
			iForm.setAziendaTipo(val.getAzienda().getTipo());
			if(val.getTipoValorizzazioneId()!=TipoValorizzazione.ALTRO)
				iForm.setTipoValorizzazioneNome(val.getTipo().getNome());
			else
				iForm.setTipoValorizzazioneNome(val.getAltro());
			request.setAttribute("iform", iForm);
		}
        return mapping.findForward("InfoVal");
    }
}
