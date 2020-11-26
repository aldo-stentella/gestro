package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the DOCUMENTI database table.
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Mar 17, 2015]
 *
 */
@Entity @Table(name="DOCUMENTI")
public class Documento implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String SEQ = "DOCUMENTI_SEQ";
	public static final int KS = 0x1C3;
	public static final int KL = 0x10;
	
	@Id @Column(name="DOCUMENTI_ID")
	@SequenceGenerator(name=Documento.SEQ, sequenceName=Documento.SEQ, allocationSize=0)
	@GeneratedValue(generator=Documento.SEQ, strategy=GenerationType.SEQUENCE)
	private Integer documentoId;
	@Lob
	private Blob allegato;

	public Integer getDocumentoId() {
		return documentoId;
	}
	public void setDocumentoId(Integer documentoId) {
		this.documentoId = documentoId;
	}
	public Blob getAllegato() {
		return allegato;
	}
	public void setAllegato(Blob allegato) {
		this.allegato = allegato;
	}
}
