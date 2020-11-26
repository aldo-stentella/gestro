package it.cnr.brevetti.ejb.services;

import javax.ejb.Stateless;

import it.cnr.brevetti.ejb.manager.BrevettiManager;

/**
 * Servizio generico per la gestione della persistenza nel dominio BREVETTI
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [18-Nov-09]
 */
@Stateless
public class BrevettiServiceBean extends BrevettiManager implements BrevettiService  {}
