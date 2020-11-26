package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the UTENTI database table.
 * 
 */
@Entity
@Table(name="UTENTI")
public class Utente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="UTENTI_ID")
	private String utentiId;

	public String getUtenteId() {
		return utentiId;
	}

	public void setUtenteId(String utentiId) {
		this.utentiId = utentiId;
	}

	private String cognome;

	@Column(name="DIPARTIMENTI_ID")
	private BigDecimal dipartimentiId;

	private String email;

	private Integer matricola;

	private String nome;

	private String telefono;


	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public BigDecimal getDipartimentiId() {
		return this.dipartimentiId;
	}

	public void setDipartimentiId(BigDecimal dipartimentiId) {
		this.dipartimentiId = dipartimentiId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getMatricola() {
		return matricola;
	}

	public void setMatricola(Integer matricola) {
		this.matricola = matricola;
	}

}