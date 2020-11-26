package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the VALIDAZIONI database table.
 * 
 */
@Entity @Table(name="VALIDAZIONI")
public class Validazione implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String SEQ = "VALIDAZIONI_SEQ";
	
	@SequenceGenerator(name=Validazione.SEQ, sequenceName=Validazione.SEQ, allocationSize=0)
	@Id @GeneratedValue(generator=Validazione.SEQ, strategy=GenerationType.SEQUENCE)	
	private long id;

	private String actionform;

	private String args;

	private String field;

	private String message;

	private String validator;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getActionform() {
		return this.actionform;
	}

	public void setActionform(String actionform) {
		this.actionform = actionform;
	}

	public String getArgs() {
		return this.args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getValidator() {
		return this.validator;
	}

	public void setValidator(String validator) {
		this.validator = validator;
	}

}