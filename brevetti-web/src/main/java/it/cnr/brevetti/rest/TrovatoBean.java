package it.cnr.brevetti.rest;

import java.io.Serializable;

/**
 * Bean per il trasporto dei dati minimi di un trovato
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [2010-10-05]
 */
public class TrovatoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer nsrif;
	private String titolo;
	private String inventore;
	public Integer getNsrif() {
		return nsrif;
	}
	public void setNsrif(Integer nsrif) {
		this.nsrif = nsrif;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getInventore() {
		return inventore;
	}
	public void setInventore(String inventore) {
		this.inventore = inventore;
	}	
}
