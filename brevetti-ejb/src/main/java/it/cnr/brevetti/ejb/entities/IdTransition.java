package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the ID_TRANSITION database table.
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Feb 25, 2016]
 *
 */
@Entity
@Table(name="ID_TRANSITION")
public class IdTransition implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String SEQ = "ID_TRANSITION_SEQ";
	
	@Id
	@SequenceGenerator(name=IdTransition.SEQ, sequenceName=IdTransition.SEQ, allocationSize=0)
	@GeneratedValue(generator=IdTransition.SEQ, strategy=GenerationType.SEQUENCE)
	private Integer id;
	@Column(name="DATA_TRANSIZIONE")
	private Date dataTransizione;
	@Column(name="INVENTION_DISCLOSURE")
	private Integer inventionDisclosure;
	private String nota;
	private Integer stato;
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDataTransizione() {
		return this.dataTransizione;
	}
	public void setDataTransizione(Date dataTransizione) {
		this.dataTransizione = dataTransizione;
	}
	public Integer getInventionDisclosure() {
		return this.inventionDisclosure;
	}
	public void setInventionDisclosure(Integer inventionDisclosure) {
		this.inventionDisclosure = inventionDisclosure;
	}
	public String getNota() {
		return this.nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public Integer getStato() {
		return this.stato;
	}
	public void setStato(Integer stato) {
		this.stato = stato;
	}
	@Override
	public String toString() {
		return "IdTransition [id=" + id + ", inventionDisclosure="
				+ inventionDisclosure + ", stato=" + stato + "]";
	}
	
}