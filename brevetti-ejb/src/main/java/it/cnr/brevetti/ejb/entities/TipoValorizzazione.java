package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the TIPI_VALORIZZAZIONE database table.
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Jan 12, 2015]
 *
 */
@Entity
@Table(name="TIPI_VALORIZZAZIONE")
public class TipoValorizzazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TIPI_VALORIZZAZIONE_ID")
	private Integer id;
	private String nome;
	private Integer status;
	
	public static final int ALTRO = 12; 

	public long getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getStatus() {
		return this.status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}