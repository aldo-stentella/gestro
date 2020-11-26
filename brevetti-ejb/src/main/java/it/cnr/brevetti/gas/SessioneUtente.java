package it.cnr.brevetti.gas;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import it.cnr.brevetti.util.Utile;

/**
 * Contenitore degli attributi di accesso dell'utente
 * (utente + utenteLdap + menu + risorse + ruoli)
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [23-Apr-08]
 * @version 2.0 [26-Jun-17] adattamento REST (no utenteLdap)
 */

public class SessioneUtente implements Serializable {

	private static final long serialVersionUID = 1L;
	// ==============================================================

	private Utente utente;
	private List<Menu> menu;
	private List<Risorsa> risorse;
	private List<Ruolo> ruoli;
	private List<String> strutture;
	private boolean esterno;

	// ==============================================================
	//  Utente 
	// ==============================================================

	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}	
	public Integer getApplicazioneId() {
		return utente.getApplicazioneId();
	}
	public String getUtenteId() {
		return utente.getNomeUtente();
	}
	public Boolean getAttivo() {
		return utente.getAttivo();
	}
	public Integer getGruppoId() {
		return utente.getGruppoId();
	}
	public String getStruttura() {
		return utente.getStruttura();
	}
	public Date getUltimoLogin() {
		return utente.getUltimoAccesso();
	}

	// ==============================================================
	//  Menu
	// ==============================================================

	/** Ritorna la lista dei soli menu inerenti al ruolo dell'utente */
	public List<Menu> getMenu() {
		return menu;
	}
	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}

	// ==============================================================
	//  Risorse
	// ==============================================================

	/** Ritorna la lista di solo le risorse inerenti al ruolo dell'utente */
	public List<Risorsa> getRisorse() {
		return risorse;
	}
	public void setRisorse(List<Risorsa> risorse) {
		this.risorse = risorse;
	}

	// ==============================================================
	//  Ruoli
	// ==============================================================
	
	/** Ritorna la lista dei ruoli dell'utente */
	public List<Ruolo> getRuoli() {
		return ruoli;
	}
	public void setRuoli(List<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}
	
	// ==============================================================

	public boolean hasRole(Integer ruoloId) {
		if (ruoloId == null) return false;
		if (Utile.isEmptyOrNull(ruoli))  return false;
		for (Ruolo ruolo  : ruoli) {
			if (ruolo.getId().equals(ruoloId))
				return true;
		}
		return false;
	}

	public boolean hasRole(String codice) {
		if (Utile.isBlankOrNull(codice)) return false;
		if (Utile.isEmptyOrNull(ruoli))  return false;
		for (Ruolo ruolo  : ruoli) {
			if (codice.equalsIgnoreCase(ruolo.getCodice()))
				return true;
		}
		return false;
	}
	public boolean hasStruttura(String struttura) {
		if (struttura == null) return false;
		if (Utile.isEmptyOrNull(strutture))  return false;
		for (String s : strutture) {
			if (struttura.equalsIgnoreCase(s))
				return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return utente == null ? null : utente.getNomeUtente();
	}
	
	public boolean isAuthorized(String path) {
		for (Risorsa ris : risorse) {
			if (Pattern.matches(ris.getNome(), path))
				return true;
		}
		return false;		
	}
	public void setStrutture(List<String> strutture) {
		this.strutture = strutture;
	}
	public List<String> getStrutture() {
		return strutture;
	}
	public void setEsterno(boolean esterno) {
		this.esterno = esterno;
	}
	public boolean isEsterno() {
		return esterno;
	}
	
	
}
