package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the TIPI_DOCUMENTO database table.
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Mar 18, 2015]
 *
 */
@Entity
@Table(name="TIPI_DOCUMENTO")
public class TipoDocumento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final int INVENTION_DISCLUSURE = 1;
	public static final int SCHEDA_DESCRITTIVA   = 2;
	public static final int DEPOSITO             = 3;
	public static final int CONCESSIONE          = 4;
	public static final int ESTENSIONE           = 5;
	public static final int ATTO_CESSIONE        = 6;
	public static final int IMPEGNO              = 7;
	public static final int VALORIZZAZIONE       = 8;
	public static final int CONTATTO             = 9;
	public static final int VERBALI_UVR          =10;
	public static final int FATTURA              =11;
	public static final int ACCORDO_COMUNIONE    =12;

	
	@Id @Column(name="TIPI_DOCUMENTO_ID")
	private Integer tipiDocumentoId;
	private String nome;

	public Integer getTipiDocumentoId() {
		return this.tipiDocumentoId;
	}
	public void setTipiDocumentoId(Integer tipiDocumentoId) {
		this.tipiDocumentoId = tipiDocumentoId;
	}
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}