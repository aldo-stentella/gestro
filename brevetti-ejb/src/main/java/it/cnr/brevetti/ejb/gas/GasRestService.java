package it.cnr.brevetti.ejb.gas;

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

import it.cnr.brevetti.gas.GasBean;
import it.cnr.brevetti.util.ApplicationProperties;
/**
 * Elementi comuni da ereditare
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Jun 23, 2017]
 *
 */
public class GasRestService {
	protected String getUser() {
		return StringUtils.substringBefore(ApplicationProperties.getInstance().getGasLogin(), ",");
	}
	protected String getPassword() {
		return StringUtils.substringAfter(ApplicationProperties.getInstance().getGasLogin(), ",");
	}
	protected String getRoot() {
		return ApplicationProperties.getInstance().getGasUrl();
	}
	protected String getGasSessioneUrl() {
		return getRoot() + ApplicationProperties.getInstance().getGasSessione();
	}
	protected String getJson(GasBean bean) {
		return new Gson().toJson(bean);
	}
	protected String postJson(String url, String json) throws Exception {
		//url = "http://as5.cedrc.cnr.it:8180/gas/rest/brevetti/sessione";
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
