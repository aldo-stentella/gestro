package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity bean di persistenza per la tabella PROPERTIES
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [04-Feb-2014]
 */
@Entity
@Table(name="properties")
public class Property implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String valore;
	private String sistema;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValore() {
		return valore;
	}
	public void setValore(String valore) {
		this.valore = valore;
	}
	public String getSistema() {
		return sistema;
	}
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	public boolean isSistema() {
		return "Y".equals(getSistema());
	}
	@Override
	public String toString() {
		return describe();
	}	
	public String describe() {
		return id + "=" + valore + (isSistema() ? " (s)" : ""); 
	}	
}