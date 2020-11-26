package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="TIPI_TROVATO")
public class TipoTrovato implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	@Id @Column(name="TIPI_TROVATO_ID")
	private Integer id;
	private String tipo;
	@Column(name="ANNI_VALIDITA")
	private Integer anniValidita;
	
	public Integer getAnniValidita() {
		return anniValidita;
	}
	public void setAnniValidita(Integer anniValidita) {
		this.anniValidita = anniValidita;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
