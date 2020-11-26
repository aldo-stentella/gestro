package it.cnr.brevetti.ejb.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;
/**
 * Campi comuni per il tracciamento delle modifiche (auditing)
 * 
 * Le colonne audit di base per convenzione sono:
 * 
 * Utente creatore   		(utcr)
 * Data di creazione 		(dacr)
 * Utente ultima variazione (utva) 
 * Data ultima variazione	(duva)
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [2014-09-18]
 *
 */
/*
alter table
   brevetti.tabella
add (
   utcr  varchar2(64),
   dacr date,
   utva  varchar2(64),
   duva date,
   rev number(22)
); 
 */
@MappedSuperclass
public class AuditTrail implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String utcr;
	protected String utva;
	protected Date dacr;
	protected Date duva;
	protected Integer rev;
	
	public String getUtcr() {
		return utcr;
	}
	public void setUtcr(String utcr) {
		this.utcr = utcr;
	}
	public String getUtva() {
		return utva;
	}
	public void setUtva(String utva) {
		this.utva = utva;
	}
	public Date getDacr() {
		return dacr;
	}
	public void setDacr(Date dacr) {
		this.dacr = dacr;
	}
	public Date getDuva() {
		return duva;
	}
	public void setDuva(Date duva) {
		this.duva = duva;
	}
	public Integer getRev() {
		return rev;
	}
	public void setRev(Integer rev) {
		this.rev = rev;
	}
}
