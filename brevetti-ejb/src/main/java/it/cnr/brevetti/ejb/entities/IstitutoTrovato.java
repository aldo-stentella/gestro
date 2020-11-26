package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name="ISTITUTI_TROVATI")
public class IstitutoTrovato implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IstitutoTrovatoKey key;
	@Column(name="SEZIONI_ID")
	private Integer sezioneId;
	public IstitutoTrovatoKey getKey() {
		return key;
	}
	public void setKey(IstitutoTrovatoKey key) {
		this.key = key;
	}
	public Integer getSezioneId() {
		return sezioneId;
	}
	public void setSezioneId(Integer sezioneId) {
		this.sezioneId = sezioneId;
	}
}
