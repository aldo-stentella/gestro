package it.cnr.brevetti.trovati.servlets;

import it.cnr.brevetti.util.UtilityFunctions;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class InfoCatalogoPDF extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = null;
		try {
			String requestURI = request.getRequestURI();
			Integer nsrif = Integer.decode(StringUtils.substringBetween(requestURI, "datasheet", ".pdf") );
			OutputStream os = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=0");
			response.setHeader("Content-Disposition", "attachment; filename=datasheet"+nsrif+".pdf");
			HashMap<String, Integer> parameterMap = new HashMap<String, Integer>();
			parameterMap.put("nsrif",nsrif);
			//ClassLoader loader = this.getClass().getClassLoader();
			//URL fileURL = loader.getResource("WEB-INF/jasper/scheda_trovato.jasper");
			ServletContext loader = getServletContext();
			URL fileURL = loader.getResource("/WEB-INF/jasper/scheda_trovato.jasper");
			if (fileURL == null)
				throw new Exception("Can't find the file: scheda_trovato.jasper");
			connection = UtilityFunctions.createConnection();
			JRPdfExporter pdfExporter = new JRPdfExporter();
			pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, JasperFillManager.fillReport(fileURL.getFile(), parameterMap, connection) );
			pdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
			System.out.println("Exporting sheet...");
			pdfExporter.exportReport();
			System.out.println("Done!");
			UtilityFunctions.closeConnection(connection);
		} catch (NumberFormatException e) {
			System.out.println("Parametri NsRif o dip non numerici");
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("Parametri NsRif o dip mancanti");
			e.printStackTrace();
		} catch (JRException e) {
			System.out.println("Errore nella creazione del doc. PDF");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				UtilityFunctions.closeConnection(connection);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
