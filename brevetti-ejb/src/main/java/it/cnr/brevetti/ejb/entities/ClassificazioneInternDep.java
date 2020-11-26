package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Entity bean per l'entità CLASSIFICAZIONE_INTERN_DEP
 * @author Aurelio D'Amico
 * @version 1.1 [11-Jan-08] 
 * - eliminazione della classe chiave poichè non più necessaria
 * - aggiunta sequenza
 */
@Entity @Table(name="CLASSIFICAZIONE_INTERN_DEP")
public class ClassificazioneInternDep implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String SEQ = "CLASSIFICAZIONE_INTERN_DEP_SEQ";
	
	@Id @Column(name="CLASSIFICAZIONE_INTERN_DEP_ID")
	@SequenceGenerator(name=ClassificazioneInternDep.SEQ, sequenceName=ClassificazioneInternDep.SEQ, allocationSize=0)
	@GeneratedValue(generator=ClassificazioneInternDep.SEQ, strategy=GenerationType.SEQUENCE)
	private Integer ClassificazioneInternDepId;
	private Integer nsrif;
	@Column(name="CLASSIFICAZIONI_ID")
	private Integer classificazioniId;
	@Column(name="ALTRA_CLASSIFICAZIONE")
	private String altraClassificazione;

	public Integer getClassificazioneInternDepId() {
		return ClassificazioneInternDepId;
	}
	public void setClassificazioneInternDepId(Integer classificazioneInternDepId) {
		ClassificazioneInternDepId = classificazioneInternDepId;
	}
	public Integer getNsrif() {
		return nsrif;
	}
	public void setNsrif(Integer nsrif) {
		this.nsrif = nsrif;
	}

	public String getAltraClassificazione() {
		return altraClassificazione;
	}
	public void setAltraClassificazione(String altraClassificazione) {
		this.altraClassificazione = altraClassificazione;
	}
	public Integer getClassificazioniId() {
		return classificazioniId;
	}
	public void setClassificazioniId(Integer classificazioniId) {
		this.classificazioniId = classificazioniId;
	}

	// ============================================================
	//  tipi transient
	// ============================================================

	@Transient private Classificazione classificazione;
	
	public Classificazione getClassificazione() {
		return classificazione;
	}
	public void setClassificazione(Classificazione classificazione) {
		this.classificazione = classificazione;
	}
}
