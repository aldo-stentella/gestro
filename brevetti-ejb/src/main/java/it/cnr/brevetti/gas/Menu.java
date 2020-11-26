package it.cnr.brevetti.gas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity bean di persistenza per la tabella MENU
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [29-Jan-2007]
 * @version 1.1 [23-Apr-2008] GAS revamp
 */
public class Menu implements Serializable, Comparable<Menu> {
	private static final long serialVersionUID = 1L;
	// =========================================================================

	private Integer id;
	private Integer applicazioneId;
	private Integer padreId;
	private Integer risorsaId;
	private Integer posizione;
	private String nome;
	private String note;

	private Risorsa risorsa;
	private List<Menu> submenus = new ArrayList<Menu>();
	
	// =========================================================================

	public Integer getApplicazioneId() {
		return applicazioneId;
	}

	public void setApplicazioneId(Integer applicazioneId) {
		this.applicazioneId = applicazioneId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPadreId() {
		return padreId;
	}

	public void setPadreId(Integer padreId) {
		this.padreId = padreId;
	}

	public Integer getPosizione() {
		return posizione;
	}

	public void setPosizione(Integer posizione) {
		this.posizione = posizione;
	}

	public Risorsa getRisorsa() {
		return risorsa;
	}

	public void setRisorsa(Risorsa risorsa) {
		this.risorsa = risorsa;
	}

	public Integer getRisorsaId() {
		return risorsaId;
	}

	public void setRisorsaId(Integer risorsaId) {
		this.risorsaId = risorsaId;
	}

	public List<Menu> getSubmenus() {
		return submenus;
	}

	public void setSubmenus(List<Menu> submenu) {
		this.submenus = submenu;
	}
	
	// ==============================================================

	private String dataToString() {
		StringBuffer sb = new StringBuffer();
		sb.append(id);
		sb.append('.');
		sb.append(nome);
		return sb.toString();
	}

	@Override
	public String toString() {
		return dataToString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Menu) {
			Menu that = (Menu)obj;
			return this.getId().equals(that.getId());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	public int compareTo(Menu that) {
		return this.getId().compareTo(that.getId());
	}

}
