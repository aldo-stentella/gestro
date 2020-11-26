<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="it.cnr.brevetti.util.Version"%>
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
                  	    <td width="38%" align="left" class="even"><%= session.getAttribute("sessioneEutente") %></td>
                  	    <td width="38%" align="right" class="even"><a href="Logout.do">Esci</a></td>
                  	  </tr>
                      <tr>
                        <td align="right"><a href="AddPro.do" class="buts"><img src="../images/Catalog/dettagli.png" width="84" height="85" alt="Inserisci" /></a></td>
                        <td colspan="2"><a href="AddPro.do" class="buts">Inserisci nuova proposta</a></td>
                      </tr>
                      <tr>
                        <td align="right"><a href="GesPro.do" class="buts"><img src="../images/Catalog/search-button.png" width="84" height="83" alt="Cerca" /></a></td>
                        <td colspan="2"><a href="GesPro.do" class="buts">Gestisci proposte inserite</a></td>
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
