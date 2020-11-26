package it.cnr.brevetti.util;

import org.apache.struts.action.*;
import org.apache.struts.validator.*;
import org.apache.commons.validator.*;
import org.apache.commons.validator.util.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Aldo Stentella Liberati
 * 


 */
public class StrutsValidator {

	public static boolean validateTwoFields(Object bean, ValidatorAction va,
			Field field, ActionMessages messages, HttpServletRequest request) {

		String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		String sProperty2 = field.getVarValue("secondProperty");
		String value2 = ValidatorUtils.getValueAsString(bean, sProperty2);

		if (!GenericValidator.isBlankOrNull(value)) {
			try {
				if (!value.equals(value2)) {
					messages.add(field.getKey(), Resources.getActionMessage(request, va, field));

					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				messages.add(field.getKey(), Resources.getActionMessage(request, va, field));
				return false;
			}
		}

		return true;
	}

}