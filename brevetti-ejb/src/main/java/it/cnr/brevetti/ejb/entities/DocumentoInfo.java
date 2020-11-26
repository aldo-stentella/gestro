package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the TROVATI_DOCUMENTI database table.
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Mar 17, 2015]
 *
 */
@Entity
@Table(name="DOCUMENTI_INFO")
public class DocumentoInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @Column(name="DOCUMENTI_ID")
	private Integer documentoId;
	private Integer entita;
	@Column(name="NOME_FILE")
	private String nomeFile;
	private String metadati;
	@Column(name="TIPI_DOCUMENTO_ID")
	private Integer tipoDocumentoId;
	@Column(name="DATA_DOCUMENTO")
	private Date dataDocumento;
	public Integer getDocumentoId() {
		return documentoId;
	}
	public void setDocumentoId(Integer documentoId) {
		this.documentoId = documentoId;
	}
	public Integer getEntita() {
		return entita;
	}
	public void setEntita(Integer entita) {
		this.entita = entita;
	}
	public String getMetadati() {
		return metadati;
	}
	public void setMetadati(String metadati) {
		this.metadati = metadati;
	}
	public Integer getTipoDocumentoId() {
		return tipoDocumentoId;
	}
	public void setTipoDocumentoId(Integer tipoDocumentoId) {
		this.tipoDocumentoId = tipoDocumentoId;
	}
	public String getNomeFile() {
		return nomeFile;
	}
	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}
	public Date getDataDocumento() {
		return dataDocumento;
	}
	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}
	
	// transient
	@Transient private TipoDocumento tipoDocumento;
	@Transient private List<Trovato> trovati;
	
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public List<Trovato> getTrovati() {
		return trovati;
	}
	public void setTrovati(List<Trovato> trovati) {
		this.trovati = trovati;
	}
	
	@Override
	public String toString() {
		return "DocumentoInfo [documentoId=" + documentoId + ", nomeFile="
				+ nomeFile + "]";
	}
	//==================================================================
	// metodi per la definizione dell'uguaglianza
	//==================================================================
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((documentoId == null) ? 0 : documentoId.hashCode());
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
		DocumentoInfo other = (DocumentoInfo) obj;
		if (documentoId == null) {
			if (other.documentoId != null)
				return false;
		} else if (!documentoId.equals(other.documentoId))
			return false;
		return true;
	}
}