package it.cnr.brevetti.validators;

import it.cnr.brevetti.ejb.entities.Validazione;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;

public interface Validation {
	public void validate(AbstractValidatorForm form, Validazione validazione, ActionErrors errors) throws ValidationException;
}
