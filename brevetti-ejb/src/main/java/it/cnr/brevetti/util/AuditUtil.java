package it.cnr.brevetti.util;

import java.util.Calendar;
import java.util.Date;

import it.cnr.brevetti.ejb.entities.AuditTrail;

/**
 * Classe di utilità per la gestione delle colonne di auditing
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [2014-09-18]
 *
 */
public class AuditUtil {
	
	private static AuditUtil instance;
	
	private AuditUtil() {}

	public static synchronized AuditUtil getInstance() {
		if (instance==null) instance = new AuditUtil();
		return instance;
	}
	public void salva(AuditTrail item, String user) {
		if (item==null) return;
		Date now = Calendar.getInstance().getTime();
		aggiorna(item, user, now);
		item.setDacr(now);
		item.setUtcr(user);
		item.setRev(0);
	}
	public void aggiorna(AuditTrail item, String user) {
		if (item==null) return;
		aggiorna(item, user, Calendar.getInstance().getTime()); 
	}
	public void aggiorna(AuditTrail item, String user, Date date) {
		if (item==null) return;
		item.setDuva(date);
		item.setUtva(user);
		item.setRev(item.getRev()==null ? 1 : item.getRev() + 1);
	}
}
