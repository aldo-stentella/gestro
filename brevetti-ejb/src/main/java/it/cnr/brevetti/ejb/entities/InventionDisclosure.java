package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the INVENTION_DISCLOSURE database table.
 * @author Aurelio D'Amico
 * @version 1.0 [11-Dec-15]
 */
@Entity
@Table(name="INVENTION_DISCLOSURE")
public class InventionDisclosure implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String SEQ = "INVENTION_DISCLOSURE_SEQ";
	
	public static final int TRASMESSA = 1;
	public static final int IN_ESAME = 2;
	public static final int DA_INTEGRARE = 3;
	public static final int BREVETTABILE = 4;
	public static final int RESPINTA = 5;
	public static final int NON_ESAMINABILE = 6;
	public static final int DEPOSITATA = 7;

	@Id
	@SequenceGenerator(name=InventionDisclosure.SEQ, sequenceName=InventionDisclosure.SEQ, allocationSize=0)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator=InventionDisclosure.SEQ)
	private Integer id;

	@Column(name="DATA_TRASMISSIONE")
	private Date dataTrasmissione;

	@Column(name="NOTA_RIFIUTO")
	private String notaRifiuto;

	private Integer nsrif;

	private Integer stato;

	@Column(name="TIPI_TROVATO_ID")
	private Integer tipiTrovatoId;

	private String titolo;

	@Column(name="UTENTE_LDAP")
	private String utenteLdap;

	public InventionDisclosure() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataTrasmissione() {
		return this.dataTrasmissione;
	}

	public void setDataTrasmissione(Date dataTrasmissione) {
		this.dataTrasmissione = dataTrasmissione;
	}

	public String getNotaRifiuto() {
		return this.notaRifiuto;
	}

	public void setNotaRifiuto(String notaRifiuto) {
		this.notaRifiuto = notaRifiuto;
	}

	public Integer getNsrif() {
		return this.nsrif;
	}

	public void setNsrif(Integer nsrif) {
		this.nsrif = nsrif;
	}

	public Integer getStato() {
		return this.stato;
	}

	public void setStato(Integer stato) {
		this.stato = stato;
	}

	public Integer getTipiTrovatoId() {
		return this.tipiTrovatoId;
	}

	public void setTipiTrovatoId(Integer tipiTrovatoId) {
		this.tipiTrovatoId = tipiTrovatoId;
	}

	public String getTitolo() {
		return this.titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getUtenteLdap() {
		return this.utenteLdap;
	}

	public void setUtenteLdap(String utenteLdap) {
		this.utenteLdap = utenteLdap;
	}
	
	@Transient List<DocumentoInfo> documenti;
	@Transient List<IdTransition> transizioni;

	public List<DocumentoInfo> getDocumenti() {
		return documenti;
	}

	public void setDocumenti(List<DocumentoInfo> documenti) {
		this.documenti = documenti;
	}

	public List<IdTransition> getTransizioni() {
		return transizioni;
	}

	public void setTransizioni(List<IdTransition> transizioni) {
		this.transizioni = transizioni;
	}
	
}