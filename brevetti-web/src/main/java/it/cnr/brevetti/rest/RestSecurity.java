package it.cnr.brevetti.rest;

import java.lang.reflect.Method;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;

/**
 * Questa classe verifica i permessi di accesso in base al nome utente e
 * password
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Sep 9, 2016]
 */
@Provider
public class RestSecurity implements ContainerRequestFilter, RestNames {
	
	@Override
	public void filter(ContainerRequestContext requestContext) {
		//System.out.println(requestContext.getUriInfo().getAbsolutePath());
	
		ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) requestContext
				.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
		Method method = methodInvoker.getMethod();
		
		// Access allowed for all
		if (method.isAnnotationPresent(PermitAll.class)) return;

		// Access denied for all
		if (method.isAnnotationPresent(DenyAll.class)) {
			requestContext.abortWith(getAccessForbidden());
			return;
		}

		String authorization = null; 
		try {
			authorization = RestHelper.getInstance().getAuthorization(requestContext.getHeaderString(AUTHORIZATION_PROPERTY));
			if (authorization==null) {
				requestContext.abortWith(getAuthenticationRequired());
				return;
			}
		} catch (Exception e) {
			requestContext.abortWith(getServerError(e));
			return;
		}

		// Split username and password tokens
		StringTokenizer tokenizer = new StringTokenizer(authorization, ":");
		String uid = tokenizer.nextToken();
		String pas = tokenizer.nextToken();
		
		try {
			if (RestHelper.getInstance().isUserAllowed(uid,pas)) return;
		} catch (Exception e) {
			requestContext.abortWith(getServerError(e));
			return;
		}
		requestContext.abortWith(getAccessDenied());		
	}
	private ServerResponse getAuthenticationRequired() {
		int status = HttpServletResponse.SC_UNAUTHORIZED;
		Headers<Object> headers = new Headers<Object>();
		headers.add(AUTENTHICATE, AUTHENTICATION_SCHEME + AUTHENTICATION_REALM);
		return new ServerResponse(status + " - Authentication required for this resource", status, headers);
	}
	private ServerResponse getAccessDenied() {
		int status = HttpServletResponse.SC_UNAUTHORIZED;
		return new ServerResponse(status + " - Access denied for this resource", status, new Headers<Object>());
	}
	private ServerResponse getAccessForbidden() {
		int status = HttpServletResponse.SC_FORBIDDEN;
		return new ServerResponse(status + " - Access forbidden for this resource", status, new Headers<Object>());
	}
	private ServerResponse getServerError(Exception e) {
		int status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		String s = e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage();		
		return new ServerResponse(status + " - " + s, status, new Headers<Object>());
	}
	@SuppressWarnings("unused")
	private Response getNotFound(String instance) {
		String x = instance == null ? "" : instance;
		int status = HttpServletResponse.SC_NOT_FOUND;
		return new ServerResponse(status + " - " + x + " not found", status, new Headers<Object>());
	}

}
