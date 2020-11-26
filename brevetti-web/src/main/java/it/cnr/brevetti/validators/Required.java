package it.cnr.brevetti.validators;

import it.cnr.brevetti.ejb.entities.Validazione;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;

public class Required extends AbstractValidator implements Validation {

	public void validate(AbstractValidatorForm form, Validazione validazione, ActionErrors errors) throws ValidationException {
		if(validazione.getField().contains("$")){
			
		}
		Object value = getValue(form, validazione.getField());
		if(value == null || value.toString().trim().length()==0)
			errors.add(validazione.getMessage(), new ActionMessage(validazione.getMessage(),validazione.getField()));
	}



}
