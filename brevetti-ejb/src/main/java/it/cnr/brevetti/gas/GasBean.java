package it.cnr.brevetti.gas;

import java.io.Serializable;

/**
 * Modello per chiamate REST
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Jun 20, 2017]
 *
 */
public class GasBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String appid;
	private String uid;
	private String psw;
	private String struttura;
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public String getStruttura() {
		return struttura;
	}
	public void setStruttura(String struttura) {
		this.struttura = struttura;
	}
}
