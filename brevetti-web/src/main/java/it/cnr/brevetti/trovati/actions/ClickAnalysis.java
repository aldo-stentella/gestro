package it.cnr.brevetti.trovati.actions;

import it.cnr.brevetti.util.UtilityFunctions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.RollingFileAppender;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ClickAnalysis extends Action {

	//private static Logger logger = Logger.getLogger("AnalisiDeiClick");
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 /*logger.setLevel(Level.INFO);
		 logger.setAdditivity(true);
		 PatternLayout layout=new PatternLayout();
		 RollingFileAppender appender=new RollingFileAppender();
		 appender.setAppend(true);
		 appender.setFile("/clicks.log");
		 appender.setLayout(layout);
		 logger.addAppender(appender);
		String[] valori = request.getParameterValues("item");
		for (int i = 0; i < valori.length; i++) {
			logger.info(valori[i]);				
		}
		return mapping.findForward("Tuning");*/
		String urlStr = UtilityFunctions.SEARCH_ENGINE_CLICK_ANALYSIS_ADDRESS;
		String[] paramName = {"qrel"};
		String[] paramValue = new String[1];
		String[] valori = request.getParameterValues("item");
		String result = "";
		StringBuffer sb = new StringBuffer();
		HashMap<Integer, Double> res = new HashMap<Integer, Double>();
		try {
			res = UtilityFunctions.searchEngineQuery(request.getParameter("keywords"));
		} catch (IOException e) {
			// TODO Visualizzare messaggio di errore nella jsp
			e.printStackTrace();
		}
		for (int i = 0; i < valori.length; i++) {
			sb.append(valori[i]+"|t@@");				
		}
		paramValue[0] = sb.toString();
		for (Iterator<Integer> iterator = res.keySet().iterator(); iterator.hasNext();) {
			Integer nsrif = iterator.next();
			if(paramValue[0].indexOf("|"+nsrif+"|")==-1){
				sb.append(request.getParameter("keywords")+"|"+nsrif+"|"+res.get(nsrif)+"|f@@");
			}
		}
		paramValue[0] = sb.toString();
		System.out.println(paramValue[0]);
		try {
			result = UtilityFunctions.httpPost(urlStr, paramName, paramValue);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("Tuning");
		
	}



}
