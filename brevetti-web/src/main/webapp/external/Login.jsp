<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
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
            <html:form action="/external/Login" method="post">
              <table border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td class="Gschedina-top">&nbsp;</td>
                </tr>
                <tr>
                  <td class="Gschedina-mid"><table width="100%">
                      <tr>
                        <td colspan="2" align="left">&nbsp;</td>
                      </tr>
                      <tr>
                        <td width="23%">Utente</td>
                        <td width="77%"><html:text property="login" size="30" /></td>
                      </tr>
                      <tr>
                        <td>Password</td>
                        <td><html:password property="password" size="30" /></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td><html:submit styleClass="buttScheda" value="Invia" /> </td>
                      </tr>
                      <tr>
                        <td colspan="2" class="footerScheda">Inserire le proprie credenziali CNR</td>
                      </tr>
                    </table></td>
                 </tr>
                <tr>
                  <td class="Gschedina-bot">&nbsp;</td>
                </tr>
              </table>
            </html:form></td>
        </tr>
    </table></td>
  </tr>
  <tr><td style="padding-left:15%; padding-right:15%;"><html:errors/></td></tr>
  <tr>
    <td align="center" class="Gfooter"><strong>Consiglio Nazionale delle Ricerche</strong> -  P.le   Aldo Moro, 7 - 00185 Roma</td>
  </tr>
</table>
</body>
</html>
