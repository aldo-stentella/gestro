package it.cnr.brevetti.gas;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
/**
 * Astrazione di un Entry LDAP
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [27-Apr-07]
 */
public class LdapEntry extends Hashtable<String, List> {
	private static final long serialVersionUID = 1L;
	private String dn = null;
	public LdapEntry() {
		super();
	}
	@SuppressWarnings("unchecked")
	public LdapEntry(LdapEntry entry) {
		super((Map) entry);
		setDN(entry.getDN());
	}
	public void setDN(String dn) {
		this.dn = dn;
	}
	public String getDN() {
		return dn;
	}
}