package it.cnr.brevetti.validators;

import it.cnr.brevetti.ejb.entities.Validazione;
import it.cnr.brevetti.ejb.facade.GestioneValidazioneService;
import it.cnr.brevetti.util.ServiceLocator;

import java.util.List;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;

public class ValidationHandler {
	public static void execute(AbstractValidatorForm form, ActionErrors errors) throws Exception{
		GestioneValidazioneService service = ServiceLocator.getInstance().getGestioneValidazioneFacade();
		List<Validazione>list = service.getValidazioni(form.getClass().getName());
		if(list == null || list.isEmpty())
			return;
		for (Validazione validazione : list) {
			Object o = Class.forName(validazione.getValidator()).newInstance();
			if (!(o instanceof Validation))
				throw new Exception("oggetto non di tipo 'Validation'");
			((Validation)o).validate(form, validazione, errors);
		}
	}
}
