package it.cnr.brevetti.ejb.sigla;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import it.cnr.brevetti.sigla.JsonBody;
import it.cnr.brevetti.sigla.JsonClause;
import it.cnr.brevetti.util.ApplicationProperties;
/**
 * Elementi comuni da ereditare
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Sep 19, 2016]
 *
 */
public class SiglaRestService {
	public static final String NSRIF = "pgTrovato";
	public static final String ANNO = "esercizio";
	public static final String CDS = "cd_cds";
	public static final String CDS_ORIG = "cd_cds_origine";
	public static final String UO = "cd_unita_organizzativa";
	public static final String UO_ORIG = "cd_uo_origine";
	public static final String PG_PASS = "pg_fattura_passiva";
	public static final String PG_ATT = "pg_fattura_attiva";
	
	protected String getUser() {
		return StringUtils.substringBefore(ApplicationProperties.getInstance().getSiglaLogin(), ",");
	}
	protected String getPassword() {
		return StringUtils.substringAfter(ApplicationProperties.getInstance().getSiglaLogin(), ",");
	}
	protected String getRoot() {
		return ApplicationProperties.getInstance().getSiglaUrl();
	}
	protected String getUrlFatturaAttiva() {
		return getRoot() + ApplicationProperties.getInstance().getFatturaAttiva();
	}
	protected String getUrlFatturaAttivaRiga() {
		return getRoot() + ApplicationProperties.getInstance().getFatturaAttivaRiga();
	}
	protected String getUrlFatturaPassiva() {
		return getRoot() + ApplicationProperties.getInstance().getFatturaPassiva();
	}
	protected String getUrlFatturaPassivaRiga() {
		return getRoot() + ApplicationProperties.getInstance().getFatturaPassivaRiga();
	}
	protected List<JsonClause> newParams() {
		return new ArrayList<JsonClause>();
	}
	protected JsonClause newClause(String name, Object value) {
		JsonClause x = new JsonClause();
		x.setFieldName(name);
		x.setFieldValue(value);
		x.setCondition("AND");
		x.setOperator("=");
		return x;
	}
	protected String getJson(List<JsonClause> params) {
		return new Gson().toJson(new JsonBody(params));
	}
	protected String postJson(String url, String json) throws Exception {
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			CredentialsProvider provider = new BasicCredentialsProvider();
			UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(getUser(), getPassword());
			provider.setCredentials(AuthScope.ANY, credentials);
			client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
			HttpPost request = new HttpPost(url);
			request.addHeader(new BasicScheme().authenticate(credentials, request, null));
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			request.setEntity(entity);		
			response = client.execute(request);
			if (HttpStatus.SC_OK==response.getStatusLine().getStatusCode()) {
				HttpEntity resEntity = response.getEntity();
			    if (resEntity != null) {
			    	String result = EntityUtils.toString(resEntity);
			    	EntityUtils.consume(resEntity);
			    	return result;
			    }
			} else {
				String msg = response.getStatusLine().getStatusCode() + " - " 
						   + response.getStatusLine().getReasonPhrase();
				throw new Exception(msg);
			}
		} finally {
			if (response!=null) response.close();
			if (client!=null) client.close();
		}
		return null;
	}
}
