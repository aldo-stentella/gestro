package it.cnr.brevetti.ejb.services;

import javax.ejb.Remote;

@Remote
public interface IndicizzatoreService {
	void indicizza();
	void start(String pattern);
	void stop();
	String getTimersInfo();
}
