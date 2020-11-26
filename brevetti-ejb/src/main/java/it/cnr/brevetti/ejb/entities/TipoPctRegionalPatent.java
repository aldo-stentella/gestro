package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity @Table(name="TIPI_PCT_REGIONAL_PATENT")
public class TipoPctRegionalPatent implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String SEQ = "TIPI_PCT_REGIONAL_PATENT_SEQ";
	
	@Id @Column(name = "TIPI_PCT_REGIONAL_PATENT_ID")	
	@SequenceGenerator(name=TipoPctRegionalPatent.SEQ, sequenceName=TipoPctRegionalPatent.SEQ, allocationSize=0)
	@GeneratedValue(generator=TipoPctRegionalPatent.SEQ, strategy=GenerationType.SEQUENCE)	
	private Integer id;
	private String nome;
	private String descrizione;
	
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {		
		return getId() + "." + getNome();
	}
	//==================================================================
	// metodi per la definizione dell'uguaglianza
	//==================================================================		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoPctRegionalPatent other = (TipoPctRegionalPatent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}
