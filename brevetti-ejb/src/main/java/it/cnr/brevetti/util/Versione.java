package it.cnr.brevetti.util;

import java.io.Serializable;
/**
 * Log del versionamento del lato server
 * @author Aurelio D'Amico
 * [2011-08-03] gestione del dipertimento tematico
 * [2011-08-05] sincronizzazione
 * [2011-09-05] documentazione librerie client axis
 * [2014-02-10] creazione ambiente Brevetti-2.0
 * [2014-02-11] gestione entity DepEst (DEP_EST) (12)
 * [2014-04-03] DepEst aggiunti attributi transient (04)
 * [2014-04-09] implementazione di getTipiRegionalPatent()
 * [2014-05-12] fix detached error in removing DepEst + unit test
 * [2014-06-03] gestione sicurezza in ejb di consumo del WS SIGLA (05)
 * [2014-06-06] modifica gestione fatture (integrazione sigla)
 * [2014-06-09] gestione storico titolarità (12,13)
 * [2014-06-18] rimozione servizi ridondanti (19,20)
 * [2014-06-23] implementazione servizi comuni
 * [2014-06-24] ristrutturazione servizi (remote only)
 * [2014-06-25] collaudo ristrutturazione servizi (27)
 * [2014-07-15] collaudo errore scrittura DepEst (trigger DB?)
 * [2014-09-03] gestione associazione DepEst/Stati(08)
 * [2014-09-11] implementazione servizio fatture attive
 * [2014-09-18] gestione auditing (19)
 * [2014-10-28] aggiunto metodo findTrovatoValido in web service
 * [2015-01-12] gestione valorizzazioni (13)
 * [2015-02-12] gestione aziende
 * [2015-03-17] gestione documenti (18,19)
 * [2015-06-30] salvataggio ed aggiornamento PctTipoRegionalPatent
 * [2015-07-02] associazione OneToMany fra Documento e Trovato (03,07,08,09)
 * [2015-07-28] fixed NPE in salvataggio nuovo documento
 * [2015-07-31] implementazione eliminazione documento
 * [2015-11-09] fix errore eliminazione troavato_documento ALL
 * [2015-11-12] gestione entity TipoEstinzione
 * [2015-12-10] gestione entity InventionDisclosure (11,14,15,16,17,18,23)
 * [2016-01-11] gestione proposta: getUtenteLdap
 * [2016-01-13] gestione proposta: leggiProposte
 * [2016-02-23] gestione sicurezza documenti (24,26)
 * [2016-02-25] gestione IdTransition (29)
 * [2017-11-24] gestione verbali
 */
public class Versione implements Serializable {
	private static final long serialVersionUID = 1L;
	public static String versione = "171124";
	public static String getVersione() {
		if (versione == null || versione.trim().length() == 0)
			return null;
		return versione;
	}
}