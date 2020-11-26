package it.cnr.brevetti.validators;

import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.text.StrTokenizer;

public abstract class AbstractValidator {

	protected Object getValue(AbstractValidatorForm form, String field) throws ValidationException {
		if(form == null)
			throw new ValidationException("ActionForm nullo");
		if(field == null)
			throw new ValidationException("Field nullo");
		try {
            if(field.contains("$"))
            {
                StrTokenizer st = new StrTokenizer(field, "$");
                String label = (String)st.getTokenList().get(0);
                Integer id = Integer.parseInt(st.getTokenList().get(1).toString());
                String fieldName = (String)st.getTokenList().get(2);
                Map map = (Map)form.getRequest().getSession().getAttribute(label);
                return PropertyUtils.getProperty(map.get(id), fieldName);
            }
            return PropertyUtils.getProperty(form, field);
		} catch (Exception e) {
			throw new ValidationException(e);
		} 
	}
}
