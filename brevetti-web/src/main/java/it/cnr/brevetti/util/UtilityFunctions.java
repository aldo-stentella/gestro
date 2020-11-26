package it.cnr.brevetti.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrTokenizer;
import org.xml.sax.SAXException;

public class UtilityFunctions {

	public static final int MAX_INVENTORI = 15;
	public static final int MAX_ISTITUTI = 4;
	public static final int MAX_CLASSIFICAZIONI = 10;
	public static final int MAX_TITOLARITA = 15;
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final long MAX_FILE_SIZE = 20000000;
	public static final int PRECISION = 2;
	public static final SimpleDateFormat itForm = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
	public static final SimpleDateFormat numForm = new SimpleDateFormat("yyyyMMdd", Locale.ITALIAN);
	static boolean test_mode = false;
	public static final String SEARCH_ENGINE_ROOT_PROD = ApplicationProperties.getInstance().getSearchEngineRootProd();
	public static final String SEARCH_ENGINE_ROOT_TEST = ApplicationProperties.getInstance().getSearchEngineRootTest();
	public static final String CREDENTIAL_PROD = ApplicationProperties.getInstance().getSearchEngineLoginProd(); 
	public static final String CREDENTIAL_TEST = ApplicationProperties.getInstance().getSearchEngineLoginTest();
	public static final String CREDENTIAL = test_mode?CREDENTIAL_TEST:CREDENTIAL_PROD;
	public static final String SEARCH_ENGINE_ROOT = test_mode?SEARCH_ENGINE_ROOT_TEST:SEARCH_ENGINE_ROOT_PROD;
	public static final String SEARCH_ENGINE_INDEXING_ADDRESS = SEARCH_ENGINE_ROOT+"/cmis/s/SpacesStore/p/Sites/gestione_collezioni/tmp1/children";
	public static final String SEARCH_ENGINE_CLICK_ANALYSIS_ADDRESS = ApplicationProperties.getInstance().getSearchEngineClickAnalysisAddress();
	public static final String SEARCH_ENGINE_INQUIRING_ADDRESS = SEARCH_ENGINE_ROOT+"/search/searchtrovati.rss";
	public static final String SEARCH_ENGINE_AUTENTICATION_ADDRESS = SEARCH_ENGINE_ROOT+"/api/login?"+CREDENTIAL;
	public static final String SEARCH_ENGINE_DELETING_ADDRESS1 = SEARCH_ENGINE_ROOT+"/cmis/s/SpacesStore/p/Sites/gestione_collezioni/tmp1/descendants";
	public static final String SEARCH_ENGINE_DELETING_ADDRESS2 = SEARCH_ENGINE_ROOT+"/cmis/s/SpacesStore/p/Sites/gestione_collezioni/documentlibrary/Trovati_Collection/descendants";
	public static final String SEARCH_ENGINE_NEW_STRUCTURE_ADDRESS1 = SEARCH_ENGINE_ROOT+"/cmis/s/SpacesStore/p/Sites/gestione_collezioni/documentlibrary/children";
	public static final String SEARCH_ENGINE_NEW_STRUCTURE_ADDRESS2 = SEARCH_ENGINE_ROOT+"/cmis/s/SpacesStore/p/Sites/gestione_collezioni/children";
	public static final String SOLR_ENGINE_ROOT_PROD = ApplicationProperties.getInstance().getSolrEngineRootProd();
	public static final String SOLR_ENGINE_ROOT_TEST = ApplicationProperties.getInstance().getSolrEngineRootTest();
	public static final String SOLR_ENGINE_ROOT = test_mode?SOLR_ENGINE_ROOT_TEST:SOLR_ENGINE_ROOT_PROD;
	public static final String SOLR_KEYWORD_BOOST = "2.0";
	
	
	public static void cleanSession(HttpSession se){
		/*
		 * in sessione autenticata:
		 * "sessioneUtente"
		 * "dipartimento"
		 * "multiDipartimento"
		 * "amministraTrovati" (se si ha il ruolo)
		 */
        se.removeAttribute("datiTrovato");
        se.removeAttribute("depositi");
        se.removeAttribute("estensioni");
        se.removeAttribute("mapTit");
        se.removeAttribute("nuoviInventori");
        se.removeAttribute("tipi");
        se.removeAttribute("lastQBEParam");
        se.removeAttribute("forward");
		se.removeAttribute("datiFattura");
		se.removeAttribute("vociFattura");
		se.removeAttribute("nsrifs");
		se.removeAttribute("classificazioni");
		se.removeAttribute("idiomi");
		se.removeAttribute("nazioni");
		se.removeAttribute("denoms");
		se.removeAttribute("sedeInit");
		se.removeAttribute("listaNaz");
		se.removeAttribute("listaReg");
		se.removeAttribute("tipoTrovato");
		se.removeAttribute("cluster");
		se.removeAttribute("aziende");
		se.removeAttribute("valorizzazioni");
		se.removeAttribute("documenti");
		se.removeAttribute("fascicoli");
		se.removeAttribute("dataPct");
		se.removeAttribute("dataEpc");
		se.removeAttribute("mapsto");
		se.removeAttribute("regioniWiz");
		se.removeAttribute("nazioniWiz");
		se.removeAttribute("appoggio");
		se.removeAttribute("tipiEstinzione");
	}
	
	public static Collection<Integer>estraiDaExa(String exa){
		ArrayList<Integer>list = new ArrayList<Integer>();
		String bin = Integer.toBinaryString(Integer.decode("0X"+exa).intValue());
		for(int i=1;i<=bin.length();i++){
			if(bin.substring(bin.length()-i).startsWith("1")){
				list.add(new Integer(i));
			}
		}
		return list;
	}
	
	public static Integer generateRandomId(){
		double rand = Math.random()*1000000000;
		int rani = (int)rand;
		return new Integer(-rani);
	}
	
	public static String pad(String s, int i, String pad, boolean left){
		if(s==null)
			s = "";
		if(s.length()>=i)
			return s.substring(0, i);
		for(int x = (i-s.length()); x>0; x--)
			if(left)
				s = pad.concat(s);
			else
				s = s.concat(pad);
		return s;
	}
	
	public static HashMap<Integer, Double> searchEngineQuery(String key) throws IOException, SAXException, ParserConfigurationException, Exception {
		String url = SOLR_ENGINE_ROOT+"/select?wt=xslt&tr=trovati.xsl&indent=true&fl=id,score";
		String cType = "text/plain";
		StringBuilder params = new StringBuilder();
		params.append("&q=");
		params.append(buildQuery(key));
		String response = queryHttpPost(url, params.toString());
		BufferedReader reader = new BufferedReader(new StringReader(response));
		HashMap<Integer, Double> res = new HashMap<Integer, Double>();
		String temp = "";
		while((temp = reader.readLine())!=null){
			System.out.println(temp);
			if(temp.startsWith("null|")) break;
			StringTokenizer st = new StringTokenizer(temp, "|");
			try {
				//se qualche ID è del tipo "99999-1", viene soppressa la parte "-1"
				res.put(Integer.valueOf(StringUtils.substringBefore(st.nextToken(),"-")),Double.parseDouble(st.nextToken().replace(",", ".")));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	
	public static String buildQuery (String key) {
		String[] aFields = {"TITOLO", "DESCRIZIONE", "USI", "VANTAGGI", "PAROLA_CHIAVE", "TITOLO_INGLESE", "DESCRIZIONE_INGLESE", "USI_INGLESE", "VANTAGGI_INGLESE", "PAROLA_CHIAVE_INGLESE"};
		ArrayList<String> fields = new ArrayList<String>(Arrays.asList(aFields));
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		String or = "";
		for (String field : fields) {
			StrTokenizer st = new StrTokenizer(key, ' ', '\"');
			while (st.hasNext()) {
				String token = st.nextToken(); 
				sb.append(or);
				sb.append(field);
				sb.append(":");
				sb.append("\"");
				sb.append(token);
				sb.append("\"");
				or = " OR ";
			}
		}
		sb.append(")");
		System.out.println(sb.toString());
		return sb.toString();
	}

	public static String HttpClientGET(String url) {
	    HttpClient client = new HttpClient();
	    GetMethod method = new GetMethod(url);
	    method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
	    try {
	      int statusCode = client.executeMethod(method);
	      if (statusCode != HttpStatus.SC_OK) {
	        System.err.println("Method failed: " + method.getStatusLine());
	      }
	      return method.getResponseBodyAsString();
	    } catch (HttpException e) {
	      System.err.println("Fatal protocol violation: " + e.getMessage());
	      e.printStackTrace();
	    } catch (IOException e) {
	      System.err.println("Fatal transport error: " + e.getMessage());
	      e.printStackTrace();
	    } finally {
	      method.releaseConnection();
	    }  
	    return "";
	}
	
	public static String httpGeneric(String urlStr, String method, String cType, String paramVal) throws Exception {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(method);
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setAllowUserInteraction(false);
		conn.setRequestProperty("Content-Type", cType);
		if(!(method.equalsIgnoreCase("DELETE")||method.equalsIgnoreCase("GET"))){
			OutputStream out = conn.getOutputStream();
			Writer writer = new OutputStreamWriter(out);
			writer.write(paramVal);
			writer.close();
			out.close();
		}
		if (conn.getResponseCode()!=-1 && conn.getResponseCode() < 200 || conn.getResponseCode() > 299)
			throw new IOException(conn.getResponseCode()+" : "+conn.getResponseMessage());
		// Buffer the result into a string
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			System.out.println(line);
			sb.append(line+"\n");
		}
		rd.close();
		conn.disconnect();
		return sb.toString();
	}
	
	public static String httpPost(String urlStr, String[] paramName, String[] paramVal) throws Exception {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setAllowUserInteraction(false);
		conn.setRequestProperty("Content-Type", "application/atom+xml;type=entry");	//"application/x-www-form-urlencoded");
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
	
	public static String httpPost(String urlStr, String[] paramName, String[] paramVal, String cType) throws Exception {
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
	
	public static String queryHttpPost(String urlStr, String params) throws Exception {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setAllowUserInteraction(false);
		// Create the form content
		OutputStream out = conn.getOutputStream();
		Writer writer = new OutputStreamWriter(out);
		writer.write(params);
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
			sb.append("\n");
		}
		rd.close();
		conn.disconnect();
		return sb.toString();
	}
	
	public static void sendMail(String body, String toAddress, String subject) throws MessagingException{
		sendMail(body, toAddress, null, subject);
	}
	
	public static void sendMail(String body, String toAddress, String bccAddress, String subject) throws MessagingException{
		String host_smtp = "smtp.cnr.it";
		String from_smtp = "Ufficio Sistemi Informativi <sistemi.informativi@cnr.it>";
		try {
			Properties props= new Properties();
			props.put("mail.smtp.host", host_smtp);
			Session session = Session.getInstance(props, null);
			
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from_smtp));
			msg.setRecipients(Message.RecipientType.TO, toAddress);
			if(bccAddress!=null) msg.setRecipients(Message.RecipientType.BCC, bccAddress);
			msg.setSubject(subject);
			msg.setSentDate(new Date());

		    //Create an "Alternative" Multipart message
		      Multipart mp = new MimeMultipart("alternative");
		      BodyPart messageBodyPart1 = new MimeBodyPart();
		      messageBodyPart1.setContent(body, "text/plain" );
		      BodyPart messageBodyPart2 = new MimeBodyPart();
		      messageBodyPart2.setContent(StringEscapeUtils.escapeHtml(body).replaceAll("\n", "<br/>"), "text/html" );
		      mp.addBodyPart(messageBodyPart1);
		      mp.addBodyPart(messageBodyPart2);
		      msg.setContent(mp);
		      Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public static void sendHtmlMail(String body, String hBody, String toAddress, String bccAddress, String subject) throws MessagingException{
		String host_smtp = "smtp.cnr.it";
		String from_smtp = "Ufficio Sistemi Informativi <sistemi.informativi@cnr.it>";
		try {
			Properties props= new Properties();
			props.put("mail.smtp.host", host_smtp);
			Session session = Session.getInstance(props, null);
			
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from_smtp));
			msg.setRecipients(Message.RecipientType.TO, toAddress);
			if(bccAddress!=null) msg.setRecipients(Message.RecipientType.BCC, bccAddress);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			
			//Create an "Alternative" Multipart message
			Multipart mp = new MimeMultipart("alternative");
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setContent(body, "text/plain" );
			BodyPart messageBodyPart2 = new MimeBodyPart();
			messageBodyPart2.setContent(hBody, "text/html" );
			mp.addBodyPart(messageBodyPart1);
			mp.addBodyPart(messageBodyPart2);
			msg.setContent(mp);
			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public static Connection createConnection() throws Exception {
		Context ctx;
		Connection con;
		javax.sql.DataSource ds;
		try {
			ctx = new InitialContext();
			ds=(javax.sql.DataSource) ctx.lookup ("java:/brevetti/datasource");
			con = ds.getConnection();
		} catch(NamingException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch(SQLException e2) {
			e2.printStackTrace();
			throw new Exception(e2.getMessage());
		}
		return con;
	}
	
	public static void closeConnection(Connection con) throws Exception {
		try{
			if (con!=null) con.close();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}	
	}
	
	public static String itFormat(Calendar cal){
		if(cal != null){
			return itForm.format(cal.getTime());
		}else{
			return "";
		}
	}
	
	public static Date addMonth(Date data, int mesi){
		Calendar c1 = Calendar.getInstance();
		c1.setTime(data);
		c1.add(Calendar.MONTH, mesi);
		return c1.getTime();
	}
	
	public static String decodeType(String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
		//String path = "images/icon/";
			if ("gif|jpg|jpeg|png|bmp|tiff".indexOf(ext)!=-1)
				return ("image.gif");
			if ("txt|asc|log".indexOf(ext)!=-1) 
				return ("text.gif");
			if ("doc|dot|rtf|wri|docx".indexOf(ext)!=-1) 
				return ("word.gif");
			if ("pdf".indexOf(ext)!=-1)
				return ("pdf.gif");
			if ("xls|xlt|csv|xlsx".indexOf(ext)!=-1) 
				return ("excel.gif");
			if ("htm|html|jsp".indexOf(ext)!=-1) 
				return ("html.gif");
			if ("ppt|pps|pptx|ppsx".indexOf(ext)!=-1) 
				return ("ppoint.gif");
			if ("zip|arj|lzh|cab|tar|tgz".indexOf(ext)!=-1) 
				return ("zip.gif");
			if ("exe|com|dll|scr|".indexOf(ext)!=-1) 
				return ("exe.gif");
			return ("unknown.gif");
	}
}
