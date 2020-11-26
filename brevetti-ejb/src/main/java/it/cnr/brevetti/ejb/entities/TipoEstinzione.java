package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the TIPI_ESTINZIONE database table.
 * 
 */
@Entity
@Table(name="TIPI_ESTINZIONE")
public class TipoEstinzione implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final int SCADENZA = 1;
	public static final int ABBANDONO = 2;
	public static final int CESSIONE_TERZI_O = 3;     
	public static final int CESSIONE_INVENTORI_O = 4; 
	public static final int CESSIONE_COTITOLARI_O = 5;
	public static final int CESSIONE_INVENTORI_G = 7; 
	public static final int CESSIONE_COTITOLARI_G = 8;
	public static final int RIGETTO = 9;
	public static final int PROSECUZIONE = 10;
	
	@Id
	@Column(name="TIPI_ESTINZIONE_ID")
	private Integer tipiEstinzioneId;

	private String fasi;

	private String nome;

	private Integer status;

	@Column(name="TIPI_VALORIZZAZIONE_ID")
	private Integer tipiValorizzazioneId;

	public TipoEstinzione() {
	}

	public Integer getTipiEstinzioneId() {
		return this.tipiEstinzioneId;
	}

	public void setTipiEstinzioneId(Integer tipiEstinzioneId) {
		this.tipiEstinzioneId = tipiEstinzioneId;
	}

	public String getFasi() {
		return this.fasi;
	}

	public void setFasi(String fasi) {
		this.fasi = fasi;
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

	public Integer getTipiValorizzazioneId() {
		return this.tipiValorizzazioneId;
	}

	public void setTipiValorizzazioneId(Integer tipiValorizzazioneId) {
		this.tipiValorizzazioneId = tipiValorizzazioneId;
	}

}