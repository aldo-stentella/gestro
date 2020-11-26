package it.cnr.brevetti.valorizzazioni.actionForms;

import org.apache.struts.action.ActionForm;

public class ValorizzazioneQueryForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private int mode = 0;
	private int nextab = 1;
	private Integer nsrif;
	private Integer id;
	private Integer aziendaId;
	
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public int getNextab() {
		return nextab;
	}
	public void setNextab(int nextab) {
		this.nextab = nextab;
	}
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
	public Integer getAziendaId() {
		return aziendaId;
	}
	public void setAziendaId(Integer aziendaId) {
		this.aziendaId = aziendaId;
	}
	
	
}
