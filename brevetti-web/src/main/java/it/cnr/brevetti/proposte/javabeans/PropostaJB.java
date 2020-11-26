package it.cnr.brevetti.proposte.javabeans;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.ServletException;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.text.StrSubstitutor;

import it.cnr.brevetti.config.Settings;
import it.cnr.brevetti.domain.Parametri;
import it.cnr.brevetti.domain.TrovatoQuery;
import it.cnr.brevetti.ejb.entities.DepEst;
import it.cnr.brevetti.ejb.entities.Documento;
import it.cnr.brevetti.ejb.entities.DocumentoInfo;
import it.cnr.brevetti.ejb.entities.InventionDisclosure;
import it.cnr.brevetti.ejb.entities.TipoDocumento;
import it.cnr.brevetti.ejb.entities.TipoTrovato;
import it.cnr.brevetti.ejb.entities.Trovato;
import it.cnr.brevetti.ejb.entities.Utente;
import it.cnr.brevetti.util.CipherUtil;
import it.cnr.brevetti.util.KeyFactory;
import it.cnr.brevetti.util.ServiceLocator;
import it.cnr.brevetti.util.ServiceLocatorException;
import it.cnr.brevetti.util.UtilityFunctions;
import it.cnr.brevetti.gas.UtenteLdap;

public class PropostaJB {
	
	private static PropostaJB instance;
	private static ServiceLocator locator;
	
	private PropostaJB(){
	}
	
	public static PropostaJB getInstance()  throws ServiceLocatorException {
		if (instance==null) {
			instance = new PropostaJB();
			locator = ServiceLocator.getInstance();
		}
		return instance;
	}
	
	public boolean login(String uid, String password) throws ServiceLocatorException{
		return locator.getGestionePropostaService().login(uid, password);
	}
	public Integer creaDocumento(byte[] stream) throws ServiceLocatorException {
		try {
			return locator.getDocumentoService().create(CipherUtil.encrypt(KeyFactory.getKey(Documento.KS, Documento.KL), stream));
		} catch (Exception e) {
			e.printStackTrace();
			return locator.getDocumentoService().create(stream);
		}
	}
	public byte[] getAllegato(Integer id) throws ServiceLocatorException {
		try {
			return CipherUtil.decrypt(KeyFactory.getKey(Documento.KS, Documento.KL), locator.getDocumentoService().getAllegato(id));
		} catch (Exception e) {
			e.printStackTrace();
			return locator.getDocumentoService().getAllegato(id);
		}
	}
	public List<TipoDocumento> getTipiDocumento() throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getTipiDocumento();
	}
	public TipoDocumento getTipoDocumento(Integer id) throws ServiceLocatorException {
		for(TipoDocumento td: getTipiDocumento()){
			if(td.getTipiDocumentoId().equals(id))
				return td;
		}
		return null;
	}
	public DocumentoInfo salvaDocumentoInfo(DocumentoInfo doc) throws ServiceLocatorException{
		return locator.getDocumentoService().salvaDocumentoInfo(doc);
	}
	public InventionDisclosure salvaProposta(InventionDisclosure indi) throws ServiceLocatorException{
		return locator.getGestionePropostaService().salvaProposta(indi);
	}
	public InventionDisclosure leggiProposta(Integer id) throws ServiceLocatorException{
		return locator.getGestionePropostaService().leggiProposta(id);
	}
	public List<InventionDisclosure> leggiProposte(Parametri p) throws ServiceLocatorException{
		return locator.getGestionePropostaService().leggiProposte(p);
	}
	public void eliminaProposta(InventionDisclosure indi) throws ServiceLocatorException{
		locator.getGestionePropostaService().eliminaProposta(indi);
	}
	public UtenteLdap getUtenteLdap(String uid) throws ServiceLocatorException{
		return locator.getGestionePropostaService().getUtenteLdap(uid);
	}
	public String[] getTipiTrovato() throws ServiceLocatorException {
		String[] atipi = new String[15];
		List<TipoTrovato> tipi = locator.getGestioneTrovatiFacade().getTipiTrovato();
		for (TipoTrovato tipoTrovato : tipi) {
			atipi[tipoTrovato.getId()] = tipoTrovato.getTipo();
		}
		return atipi;
	}
	public Trovato getTrovato(Integer nsrif) throws ServiceLocatorException {
		Parametri p = Parametri.getNewParametri();
		p.add(TrovatoQuery.NSRIF, nsrif);
		List<Trovato> list = locator.getGestioneTrovatiFacade().getTrovati(p);
		return list.get(0);
	}
	public List<Utente> getUtenti() throws ServiceLocatorException {
		return locator.getGestioneTrovatiFacade().getUtenti();
	}
	public String[] getStatiProposta() throws ServiceLocatorException {
		String[] stati = {"N/D","Trasmessa","In esame","Da integrare","Brevettabile","Non brevettabile","Non esaminabile","Depositata"};
		return stati;
	}
	public String[] getStiliProposta() throws ServiceLocatorException {
		String[] stati = {"N/D","transmitted","examining","integrating","patentable","rejected","inadmissible","registered"};
		return stati;
	}
	public int[] getAdjacencies(int state){
		int[][] matrix = {{0},{2,6},{3,4,5},{2,4,5,6},{3,7},{0},{0},{0}};
		return matrix[state];
	}
	public void sendNotify(Integer from, Integer to, InventionDisclosure id, String body ) throws ServiceLocatorException {
		String toRecipient, bccRecipient;
		UtenteLdap user =  getUtenteLdap(id.getUtenteLdap());
		String userMail = (user.getMatricola()!=null)?user.getEmail():user.getEmailPerPuk();
		toRecipient = userMail;
		bccRecipient = Settings.getInstance().get("it.cnr.brevetti.emailReferenti");
		Map<String, String> valuesMap = new HashMap<String, String>();
		if(id.getNsrif()!=null){
			Trovato trovato = getTrovato(id.getNsrif());
			if(trovato.getUtente()!=null)
				bccRecipient = trovato.getUtente().getEmail();		//se c'è un referente titolo,lo mette in copia nascosta, altrimenti ci mette l'indirizzo del gruppo referenti titolo
			valuesMap.put("nsrif", ""+trovato.getNsrif());
			valuesMap.put("titolo", trovato.getTitolo());
			if(trovato.getDepEst()!=null && !trovato.getDepEst().isEmpty()) for(DepEst de: trovato.getDepEst()) {
				if(de.getPrimo()==1) {
					valuesMap.put("dataDeposito", UtilityFunctions.itForm.format(de.getDataDeposito()));
					valuesMap.put("numeroDeposito", de.getNumeroDeposito());					
				}
			}
		}
		String tBody = StrSubstitutor.replace(getBody(from, to), valuesMap);
		StringBuilder st = new StringBuilder();
		st.append("AVVERTENZA: non rispondere alla presente notifica automatica ma seguire le istruzioni riportate in calce per contattare i referenti."+"\n");
		st.append("            NON INVIARE MAI materiale risevato per posta elettronica, utilizzare esclusivamente i servizi online con autenticazione."+"\n\n");
		st.append(tBody+"\n\n");
		st.append(body+"\n\n");
		st.append("Per contattare i referenti o inviare materiale richiesto utilizzare i servizi disponibili sul sito \n\n https://brevetti.cnr.it/external/Home.do\n\n");
		st.append("registrandosi con le proprie credenziali CNR. \nNon rispondere alla presente notifica.");
		//st.append((new Date()).toString());
		StringBuilder stml = new StringBuilder();
		stml.append("<html><head></head><body bgcolor='#E4E9ED'>\n");
		stml.append("<p style='line-height:20px; font-size:14px;font-family:\"Helvetica\",\"sans-serif\";color:#1B1F3F;font-weight:bold'>\n");
		stml.append("AVVERTENZA: non rispondere alla presente notifica automatica ma seguire le istruzioni riportate in calce per contattare i referenti."+"<br/>\n");
		stml.append("NON INVIARE MAI materiale risevato per posta elettronica, utilizzare esclusivamente i servizi online con autenticazione."+"</p>\n");
		stml.append("<p style='line-height:20px; font-size:14px;font-family:\"Helvetica\",\"sans-serif\";color:#1B1F3F'>\n");
		stml.append(tBody+"</p>\n");
		stml.append("<p style='line-height:20px; font-size:14px;font-family:\"Helvetica\",\"sans-serif\";color:#098F05;font-style:italic'>\n");
		stml.append(StringEscapeUtils.escapeHtml(body).replaceAll("\n", "<br/>")+"</p>\n");
		stml.append("<p style='line-height:20px; font-size:14px;font-family:\"Helvetica\",\"sans-serif\";color:#1B1F3F'>\n");
		stml.append("Per contattare i referenti o inviare materiale richiesto utilizzare i servizi disponibili sul sito <a href='https://brevetti.cnr.it/external/Home.do'>https://brevetti.cnr.it/external/Home.do</a> \n");
		stml.append("registrandosi con le proprie credenziali CNR.</p>\n");
		stml.append("<u>Non rispondere alla presente notifica.</u>\n");
		stml.append("</body></html>\n");
		try {
			UtilityFunctions.sendHtmlMail(st.toString(), stml.toString() , toRecipient, bccRecipient, "Notifica su comunicazione preliminare di invenzione (id:"+id.getId()+" user:"+id.getUtenteLdap()+")");
		} catch (MessagingException e) {
			e.printStackTrace();
			ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(ostr));
			BufferedReader d = new BufferedReader(new StringReader(ostr.toString()));
			String p= "";
			String q= "";
			try {
				while((p=d.readLine())!=null){
					q+= (p+"\n");
				}
				UtilityFunctions.sendMail(q, "oil.support@amministrazione.cnr.it", "[BREVETTI] Exception on sending email notification");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
	}
	String getBody(Integer from, Integer to){
		String[] mess = {
				Settings.getInstance().get("it.cnr.brevetti.emailBody0"),
				Settings.getInstance().get("it.cnr.brevetti.emailBody1"),
				Settings.getInstance().get("it.cnr.brevetti.emailBody2"),
				Settings.getInstance().get("it.cnr.brevetti.emailBody3"),
				Settings.getInstance().get("it.cnr.brevetti.emailBody4"),
				Settings.getInstance().get("it.cnr.brevetti.emailBody5"),
				Settings.getInstance().get("it.cnr.brevetti.emailBody6"),
				Settings.getInstance().get("it.cnr.brevetti.emailBody7"),
				""
		};
		if(from==null && to.equals(1))
			return mess[1];
		if(from.equals(1)){
			if(to.equals(2))
				return mess[2];
			if(to.equals(6))
				return mess[6];
		}
		if(from.equals(2)){
			if(to.equals(3))
				return mess[3];
			if(to.equals(4))
				return mess[4];
			if(to.equals(5))
				return mess[5];
		}
		if(from.equals(3)){
			if(to.equals(2))
				return mess[0];
			if(to.equals(4))
				return mess[4];
			if(to.equals(5))
				return mess[5];
			if(to.equals(6))
				return mess[6];
		}
		if(from.equals(4)){
			if(to.equals(3))
				return mess[3];
			if(to.equals(7))
				return mess[7];
		}
		return "";
	}
}
