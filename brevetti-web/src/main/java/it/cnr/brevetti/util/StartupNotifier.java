package it.cnr.brevetti.util;

import java.util.Date;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class StartupNotifier
 */

public class StartupNotifier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	public void init() throws ServletException {
		StringBuilder st = new StringBuilder();
		st.append("Server Brevetti restarted at: ");
		st.append(ApplicationProperties.getInstance().getHostName());
				//System.getProperty("java.rmi.server.hostname"));
		st.append(" on: ");
		st.append((new Date()).toString());
		try {
			UtilityFunctions.sendMail(st.toString() , "oil.support@amministrazione.cnr.it", "[BREVETTI] Server restarted");
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		super.init();
	}

}
