package it.cnr.brevetti.ejb.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import org.apache.commons.collections.ListUtils;
import  org.apache.commons.lang.StringEscapeUtils;

import it.cnr.brevetti.domain.AbstractQuery;
import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.domain.TrovatoQuery;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.ejb.facade.GestioneTrovatoService;
import it.cnr.brevetti.util.ApplicationProperties;
import it.cnr.brevetti.util.Utile;

@Singleton
public class IndicizzatoreServiceBean implements IndicizzatoreService {
	private static final String NAME = IndicizzatoreService.class.getSimpleName();
	
	@Resource TimerService timerService;
	
	public void start(String s) {
		if (Utile.isNotBlankOrNull(s)) {
			stop();
			ScheduleExpression schedule = new ScheduleExpression();
			schedule.hour(s);
			TimerConfig config = new TimerConfig();
			//config.setPersistent(false); // il timer non viene ripetuto se interrotto
			config.setInfo(NAME);
			timerService.createCalendarTimer(schedule,config);			
		}
	}
	public void stop() {
		Collection<Timer> timers = timerService.getTimers();
		if (Utile.isEmptyOrNull(timers)) return;
		for (Timer timer : timers) {
			try {
				if (timer.getInfo().equals(NAME))
					timer.cancel();
			} catch (Exception e) {
				// do nothing
			}
		}
	}
	public String getTimersInfo() {
		Collection<Timer> timers = timerService.getTimers();
		if (Utile.isEmptyOrNull(timers)) return null;
		StringBuilder sb = new StringBuilder();
		for (Timer timer : timers) {
			try {
				if (timer.getInfo().equals(NAME)) {
					sb.append(timer.getInfo() + ": ");
					sb.append("timeout ");
					sb.append(Utile.getDateFormat(timer.getNextTimeout(), "dd/MM/yyyy HH:mm"));
					sb.append("\n");
				}
			} catch (Exception e) {
				return null;
			}
		}
		return sb.length()==0 ? null : sb.toString();
		
	}
	@Timeout
    public void timeout(Timer timer) {
		indicizza();
	}
	
	@EJB GestioneTrovatoService service;

	public static final String SOLR_ENGINE_ROOT = ApplicationProperties.getInstance().getSolrEngineRootProd();
	public static final String SOLR_KEYWORD_BOOST = "2.0";
	public static final SimpleDateFormat itForm = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
	
	
	private String result = "";

	@SuppressWarnings("unchecked")
	public void indicizza() {
		String urlStr = SOLR_ENGINE_ROOT + "/update?wt=json&commit=true";
		String cType = "text/xml";
		String[] paramName = {"stream.body"};
		String[] paramValue = new String[1];
		try {
			Parametri p = new Parametri();
			p.add(TrovatoQuery.DATA_ABBANDONO, AbstractQuery.IS_NULL);
			p.add(TrovatoQuery.PUBBLICATO, 1);							//solo con flag "Pubblica su vetrina"
			List<Trovato> list = getTrovati(p);
			p.getMap().remove(TrovatoQuery.DATA_ABBANDONO);
			p.addRange(TrovatoQuery.DATA_ABBANDONO, new Date(), itForm.parse("01/01/2900"));
			List<Trovato> list2 = getTrovati(p);
			if(list!=null && list2!=null)
				list = ListUtils.sum(list, list2);
			p.getMap().remove(TrovatoQuery.DATA_ABBANDONO);
			p.add(TrovatoQuery.CESSIONE_DIRITTI, new Integer(3));
			list2 = getTrovati(p);
			if(list2!=null && list!=null)
				list.removeAll(list2);
			
			paramValue[0] =  "<delete><query>*:*</query></delete>";
			this.result = httpPost(urlStr, paramName, paramValue, cType);
			System.out.println(this.result);
			for (Iterator<Trovato> iterator = list.iterator(); iterator.hasNext();) {
				Trovato trovato = iterator.next();
				StringBuffer sb = new StringBuffer();
				sb.append("<add>\n");
				sb.append("<doc>\n");
				sb.append("<field name=\"id\">"+trovato.getNsrif()+"</field>\n");
				sb.append("<field name=\"TITOLO\">"+StringEscapeUtils.escapeXml(trovato.getTitolo())+"</field>\n");
				sb.append("<field name=\"DESCRIZIONE\">"+StringEscapeUtils.escapeXml(trovato.getDescrizione())+"</field>\n");
				sb.append("<field name=\"USI\">"+StringEscapeUtils.escapeXml(trovato.getUsi())+"</field>\n");
				sb.append("<field name=\"VANTAGGI\">"+StringEscapeUtils.escapeXml(trovato.getVantaggi())+"</field>\n");
				sb.append("<field boost=\""+SOLR_KEYWORD_BOOST+"\" name=\"PAROLA_CHIAVE\">"+StringEscapeUtils.escapeXml(trovato.getParolaChiave())+"</field>\n");				
				sb.append("<field name=\"TITOLO_INGLESE\">"+StringEscapeUtils.escapeXml(trovato.getTitoloInglese())+"</field>\n");
				sb.append("<field name=\"DESCRIZIONE_INGLESE\">"+StringEscapeUtils.escapeXml(trovato.getDescrizioneInglese())+"</field>\n");
				sb.append("<field name=\"USI_INGLESE\">"+StringEscapeUtils.escapeXml(trovato.getUsiInglese())+"</field>\n");
				sb.append("<field name=\"VANTAGGI_INGLESE\">"+StringEscapeUtils.escapeXml(trovato.getVantaggiInglese())+"</field>\n");
				sb.append("<field boost=\""+SOLR_KEYWORD_BOOST+"\" name=\"PAROLA_CHIAVE_INGLESE\">"+StringEscapeUtils.escapeXml(trovato.getParolaChiaveInglese())+"</field>\n");		
				sb.append("</doc>\n");
				sb.append("</add>\n");
				paramValue[0] = sb.toString();
				System.out.println(sb.toString());
				try {
					this.result = httpPost(urlStr, paramName, paramValue, cType);
					System.out.println(this.result);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				//break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<Trovato> getTrovati(Parametri p) {
		return service.getTrovati(p);
	}
	
	private String httpPost(String urlStr, String[] paramName, String[] paramVal, String cType) throws Exception {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setAllowUserInteraction(false);
		conn.setRequestProperty("Content-Type", cType);
		// Create the form content
		OutputStream out = conn.getOutputStream();
		Writer writer = new OutputStreamWriter(out);	//, "UTF-8");
		for (int i = 0; i < paramName.length; i++) {
			//writer.write(paramName[i]);
			//writer.write("=");
			//writer.write(URLEncoder.encode(paramVal[i], "UTF-8"));
			writer.write(paramVal[i]);
			//writer.write("&");
		}
		writer.close();
		out.close();
		if (conn.getResponseCode() < 200 || conn.getResponseCode() > 299) {
			throw new IOException(conn.getResponseCode()+" : "+conn.getResponseMessage());
		}
		// Buffer the result into a string
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		return sb.toString();
	}
}
