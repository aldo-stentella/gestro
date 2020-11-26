package it.cnr.brevetti.login.actionForms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

public class LoginForm extends ValidatorForm {

	private static final long serialVersionUID = 1L;
	private String login;
	private String password;
	
	/**
	 * @return Returns the login.
	 */	
	public String getLogin() {
		return login;
	}
	/**
	 * @param login The login to set.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void reset(ActionMapping action, HttpServletRequest request){
	    super.reset(action, request);
	    login=null;
		password=null;
	}
}
