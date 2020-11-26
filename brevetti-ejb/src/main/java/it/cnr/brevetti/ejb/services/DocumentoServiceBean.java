package it.cnr.brevetti.ejb.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Calendar;

import javax.ejb.Stateless;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import it.cnr.brevetti.ejb.entities.Documento;
import it.cnr.brevetti.ejb.entities.DocumentoInfo;
import it.cnr.brevetti.ejb.manager.BrevettiManager;

/**
 * Implementazione dell'interfaccia DocumentoService
 * 
 * Blob is not a serializable object so it is necessary for it to work
 * to stay in the same manager context (getManager())
 * Furthermore when saving or reading the blob it is necessary to stay
 * in the same transaction. It is not possible to use this service to save blobs
 * within other services (the transaction must be nuclear i.e. start and end here)
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Mar 17, 2015]
 *
 */
@Stateless
public class DocumentoServiceBean extends BrevettiManager implements DocumentoService {
	
	public Integer create(byte[] bytes) {
		Session session = (Session) getManager().getDelegate();
		Documento doc = new Documento();
		doc.setAllegato(Hibernate.getLobCreator(session).createBlob(bytes));
		getManager().persist(doc);
		return doc.getDocumentoId(); 
	}	
	public void delete(Integer id) {
		Documento doc = (Documento) getManager().find(Documento.class, id);
		if (doc!=null) getManager().remove(doc);		
	}
	public byte[] getAllegato(Integer id) {
		Documento doc = (Documento) getManager().find(Documento.class, id);
		return doc==null ? null : getAllegato(doc.getAllegato());
	}
	// =================================================================
	// Metodi di conversione
	// ================================================================
	private byte[] getAllegato(Blob blob) {
		return toByteArray(blob);
	}
    private byte[] toByteArray(Blob blob) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
        	return toByteArrayImpl(blob, baos);
        } catch (Exception e) {
        	throw new RuntimeException(e);
        } finally {
        	if (baos != null) {
        		try {
        			baos.close();
        		} catch (IOException ex) {
        		}
        	}
        }
    }
    private byte[] toByteArrayImpl(Blob blob, ByteArrayOutputStream baos)
        throws Exception {
        byte[] buf = new byte[4096];
        InputStream is = blob.getBinaryStream();
        try {
        	for (;;) {
        		int dataSize = is.read(buf);
        		if (dataSize == -1) break;
        		baos.write(buf, 0, dataSize);
        	}
        } finally {
        	if (is != null) {
        		try {
        			is.close();
        		} catch (IOException ex) {
        		}
        	}
        }
        return baos.toByteArray();
    }
	// =================================================================
	// Metodi di convenienza per la gestione delle info
	// ================================================================
    
    /** crea od aggiorna il DocumentoInfo in base all'esistenza o meno in DB */
	public DocumentoInfo salvaDocumentoInfo(DocumentoInfo info) {
		DocumentoInfo x = (DocumentoInfo) find(DocumentoInfo.class, info.getDocumentoId());
		if (x==null)
			return creaDocumentoInfo(info);
		else
			return aggiornaDocumentoInfo(info);
	}
	public DocumentoInfo creaDocumentoInfo(DocumentoInfo info) {
		if (info.getDataDocumento()==null)
			info.setDataDocumento(Calendar.getInstance().getTime());
		return (DocumentoInfo) create(info);
	}
	public DocumentoInfo aggiornaDocumentoInfo(DocumentoInfo info) {
		return (DocumentoInfo) update(info);
	}
	public DocumentoInfo leggiDocumentoInfo(Integer id) {
		return (DocumentoInfo) read(DocumentoInfo.class, id);
	}
}
