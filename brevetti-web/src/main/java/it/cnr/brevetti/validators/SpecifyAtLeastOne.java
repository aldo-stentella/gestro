package it.cnr.brevetti.validators;

import it.cnr.brevetti.ejb.entities.Validazione;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

public class SpecifyAtLeastOne extends AbstractValidator implements Validation {

	public void validate(AbstractValidatorForm form, Validazione validazione, ActionErrors errors) throws ValidationException {
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> names = new ArrayList<String>();
		values.add(getValue(form, validazione.getField()));
		names.add(validazione.getField());
		StringTokenizer st = new StringTokenizer(validazione.getArgs(),";");
		while(st.hasMoreTokens()){
			String token = st.nextToken();
			names.add(token);
			values.add(getValue(form,token));
		}
		for (Object value : values) {
			if(value != null && value.toString().trim().length()>0)
				return;
		}
		errors.add(validazione.getMessage(), new ActionMessage(validazione.getMessage(), names.toArray()));
	}
	
}
