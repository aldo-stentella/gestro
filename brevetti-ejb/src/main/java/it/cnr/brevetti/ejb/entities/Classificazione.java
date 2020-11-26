package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Aurelio D'Amico
 * @version 1.0 [29-Nov-07]
 */
@Entity @Table(name="CLASSIFICAZIONI")
public class Classificazione implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @Column(name="CLASSIFICAZIONI_ID")
	private Integer id;
	private String codice;     
	private String nome;
	private String descrizione;
	@Column(name="ID_PADRE")
	private Integer idPadre;
	private Integer livello;
	private Integer tipo;
	
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdPadre() {
		return idPadre;
	}
	public void setIdPadre(Integer idPadre) {
		this.idPadre = idPadre;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getLivello() {
		return livello;
	}
	public void setLivello(Integer livello) {
		this.livello = livello;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}	
}
