package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the VERBALI database table.
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [24-11-2017]
 *
 */
@Entity @Table(name="VERBALI")
public class Verbale implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String SEQ = "VERBALI_SEQ";

	@Id
	@SequenceGenerator(name=Verbale.SEQ, sequenceName=Verbale.SEQ, allocationSize=0)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator=Verbale.SEQ)
	@Column(name="VERBALI_ID")
	private Integer id;
	@Column(name="DATA_VERBALE")
	private Date dataVerbale;
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDataVerbale() {
		return this.dataVerbale;
	}
	public void setDataVerbale(Date dataVerbale) {
		this.dataVerbale = dataVerbale;
	}
	@Transient private List<Trovato> trovati;
	public List<Trovato> getTrovati() {
		return trovati;
	}
	public void setTrovati(List<Trovato> trovati) {
		this.trovati = trovati;
	}
	// TODO da completare
	@Transient String azione;
	@Transient Integer rifiuto;
	public String getAzione() {
		return azione;
	}
	public void setAzione(String azione) {
		this.azione = azione;
	}
	public Integer getRifiuto() {
		return rifiuto;
	}
	public void setRifiuto(Integer rifiuto) {
		this.rifiuto = rifiuto;
	}
	@Override
	public String toString() {
		return "Verbale [id=" + id + "]";
	}	

}