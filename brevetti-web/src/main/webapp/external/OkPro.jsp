<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="it.cnr.brevetti.util.UtilityFunctions"%>
<%@page import="it.cnr.brevetti.ejb.entities.InventionDisclosure"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Consiglio Nazionale delle Ricerche - Unit&agrave; Valorizzazione della Ricerca</title>
<link href="../css/standard.css?<%=Version.getVersion()%>" rel="stylesheet" type="text/css" />
<link href="../css/reports.css?<%=Version.getVersion()%>" rel="stylesheet" type="text/css" />
</head>
<body onload="inizialize();">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><img src="../images/headerUVR.png" /></td>
  </tr>
  <tr>
    <td align="center" valign="top"><table id="boxScheda">
        <tr>
          <td align="center"><h1>Comunicazione preliminare</h1>
            <h2>PROPOSTA DI BREVETTO O MARCHIO</h2>

              <table border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td class="Gschedina-top">&nbsp;</td>
                </tr>
                <tr>
                  <td class="Gschedina-mid"><table width="100%">
                  	  <tr>
                  	    <td width="24%" align="right" class="even">Utente:</td>
                  	    <td width="38%" align="left" class="even"><%=session.getAttribute("sessioneEutente")%></td>
                  	    <td width="38%" align="right" class="even"><a href="Logout.do">Esci</a></td>
                  	  </tr>
<%
	InventionDisclosure id = (InventionDisclosure) request.getAttribute("indi");
	String[] tipi = (String[]) request.getAttribute("tipi");
%>
                  	  <tr id="excel">
                  	    <td align="right"><strong>Identificatore:</strong></td>
                  	    <td align="left" colspan="2"><%=id.getId()%></td>
                  	  </tr>
					  <tr id="excel">
						<td align="right"><strong>Tipologia:</strong></td>
						<td colspan="2" align="left"><%=tipi[id.getTipiTrovatoId()]%></td>
					  </tr>
					  <tr id="excel">
                  	    <td align="right"><strong>Titolo proposto:</strong></td>
                  	    <td align="left" colspan="2"><%=id.getTitolo()%></td>
                  	  </tr>
					  <tr id="excel">
                  	    <td align="right"><strong>Trasmesso il:</strong></td>
                  	    <td align="left" colspan="2"><%= UtilityFunctions.itForm.format(id.getDataTrasmissione()) %></td>
                  	  </tr>
					  <tr id="excel">
					  	<td colspan="3" class="errorMsg">Proposta inserita con successo.</td>
					  </tr>
					  <tr>
					  	<td colspan="3" align="center"><input type="button" value="Chiudi" onclick="window.location='Home.do'" /></td>
					  </tr>
                    </table></td>
                 </tr>
                <tr>
                  <td class="Gschedina-bot">&nbsp;</td>
                </tr>
              </table>
            </td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td align="center" class="Gfooter"><strong>Consiglio Nazionale delle Ricerche</strong> -  P.le   Aldo Moro, 7 - 00185 Roma</td>
  </tr>
</table>
</body>
</html>
