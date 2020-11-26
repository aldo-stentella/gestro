package it.cnr.brevetti.ejb.services;

import javax.ejb.Remote;

import it.cnr.brevetti.ejb.entities.DocumentoInfo;

/**
 * Interfaccia remota per la gestione dei documenti
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Mar 18, 2015]
 *
 */
@Remote
public interface DocumentoService {
	Integer create(byte[] bytes);
	byte[] getAllegato(Integer id);
	void delete(Integer id);
	DocumentoInfo creaDocumentoInfo(DocumentoInfo info);
	DocumentoInfo aggiornaDocumentoInfo(DocumentoInfo info);
	DocumentoInfo salvaDocumentoInfo(DocumentoInfo info);
	DocumentoInfo leggiDocumentoInfo(Integer id);
}