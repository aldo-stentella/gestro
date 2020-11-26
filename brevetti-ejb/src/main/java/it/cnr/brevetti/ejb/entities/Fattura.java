package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @version 1.1 [19-Jan-09] completamento entita' by Aurelio
 */
@Entity @Table(name="FATTURE")
public class Fattura implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String SEQ = "FATTURE_SEQ";
	
	@Id @Column(name="FATTURE_ID")
	@SequenceGenerator(name=Fattura.SEQ, sequenceName=Fattura.SEQ, allocationSize=0)
	@GeneratedValue(generator=Fattura.SEQ, strategy=GenerationType.SEQUENCE)	
	private Integer id;
	private String protocollo;
	@Column(name="STUDIO_BREVETTUALE_ID")
	private Integer studioBrevettualeId;
	@Column(name="DATA_FATTURA")
	private Date dataFattura;
	@Column(name="NUM_FATTURA")
	private String numFattura;
	private BigDecimal importo;
	@Column(name="DATA_IMPEGNO")
	private Date dataImpegno;
	@Column(name="MANDATO_PROTOCOLLO")
	private String mandatoProtocollo;
	@Column(name="IMPEGNO_OBBLIGAZIONE")
	private String impegnoObbligazione;
	private String note;
	@Column(name="DIPARTIMENTI_ID")
	private Integer dipartimentoId;
	
	public Date getDataFattura() {
		return dataFattura;
	}
	public void setDataFattura(Date dataFattura) {
		this.dataFattura = dataFattura;
	}
	public Date getDataImpegno() {
		return dataImpegno;
	}
	public void setDataImpegno(Date dataImpegno) {
		this.dataImpegno = dataImpegno;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImpegnoObbligazione() {
		return impegnoObbligazione;
	}
	public void setImpegnoObbligazione(String impegnoObbligazione) {
		this.impegnoObbligazione = impegnoObbligazione;
	}
	public BigDecimal getImporto() {
		return importo;
	}
	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}
	public String getMandatoProtocollo() {
		return mandatoProtocollo;
	}
	public void setMandatoProtocollo(String mandatoProtocollo) {
		this.mandatoProtocollo = mandatoProtocollo;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getNumFattura() {
		return numFattura;
	}
	public void setNumFattura(String numFattura) {
		this.numFattura = numFattura;
	}
	public String getProtocollo() {
		return protocollo;
	}
	public void setProtocollo(String protocollo) {
		this.protocollo = protocollo;
	}
	public Integer getStudioBrevettualeId() {
		return studioBrevettualeId;
	}
	public void setStudioBrevettualeId(Integer studioBrevettualeId) {
		this.studioBrevettualeId = studioBrevettualeId;
	}
	public Integer getDipartimentoId() {
		return dipartimentoId;
	}
	public void setDipartimentoId(Integer dipartimentoId) {
		this.dipartimentoId = dipartimentoId;
	}
		
	// dati transienti
	@Transient private List<VoceFattura> vociFatture;
	@Transient private StudioBrevettuale studioBrevettuale;

	public StudioBrevettuale getStudioBrevettuale() {
		return studioBrevettuale;
	}
	public void setStudioBrevettuale(StudioBrevettuale studioBrevettuale) {
		this.studioBrevettuale = studioBrevettuale;
	}
	public List<VoceFattura> getVociFatture() {
		return vociFatture;
	}
	public void setVociFatture(List<VoceFattura> vociFatture) {
		this.vociFatture = vociFatture;
	}
}
