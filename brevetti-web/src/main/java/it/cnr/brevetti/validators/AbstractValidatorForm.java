package it.cnr.brevetti.validators;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.ValidatorForm;

public abstract class AbstractValidatorForm extends ValidatorForm {

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;

	@SuppressWarnings("unchecked")
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		MessageResources resources = (MessageResources)request.getAttribute( "org.apache.struts.action.MESSAGE" );
		this.setRequest(request);
		try {
			ValidationHandler.execute(this, errors);
			//traduce le keys contenute negli argomenti degli ActionMessage con le relative label del res.bundle
			for (Iterator<ActionMessage> iterator = errors.get(); iterator.hasNext();) {
				ActionMessage mess = iterator.next();
				Object[] args = mess.getValues();
				for (int i = 0; i < args.length; i++) {
					if(args[i].toString().contains("$"))
						args[i] = resources.getMessage("labels."+StringUtils.substringAfterLast((String)args[i],"$"));
					else
						args[i] = resources.getMessage("labels."+args[i]);
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return errors;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	

}
