package it.cnr.brevetti.verbali.actionForms;

import org.apache.struts.action.ActionForm;

public class VerbaliQueryForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private Integer nsrif;
	private Integer id;
	
	public Integer getNsrif() {
		return nsrif;
	}
	public void setNsrif(Integer nsrif) {
		this.nsrif = nsrif;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	
}
