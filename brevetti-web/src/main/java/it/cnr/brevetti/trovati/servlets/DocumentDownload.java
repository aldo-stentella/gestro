package it.cnr.brevetti.trovati.servlets;

import it.cnr.brevetti.trovati.javabeans.TrovatoJB;
import it.cnr.brevetti.util.ServiceLocatorException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DocumentDownload
 */
public class DocumentDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocumentDownload() {
        super();
    }

	int bufferSize = 2048;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ByteArrayInputStream is = null;
		TrovatoJB tjb;
		try {
			tjb = TrovatoJB.getInstance();
			int id = Integer.parseInt(request.getParameter("id"));
			byte[] stream = tjb.getAllegato(id);
			is = new ByteArrayInputStream(stream);
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=0");
			response.setContentLength(stream.length);
			if(request.getRequestURI().endsWith(".pdf"))
				response.setContentType("application/pdf");
			else
				response.setContentType("www/unknown");
		} catch (ServiceLocatorException e1) {
			e1.printStackTrace();
			throw new ServletException(e1);
		}
		// response.setDateHeader("Last-Modified", postIt_bulk.getDuva().getTime());
		OutputStream os = response.getOutputStream();
		try {
			byte buffer[] = new byte[bufferSize];
			int size;
			while ((size = is.read(buffer)) > 0)
				os.write(buffer, 0, size);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// ignore
			}
			try {
				os.close();
			} catch (IOException e) {
				// ignore
			}
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
