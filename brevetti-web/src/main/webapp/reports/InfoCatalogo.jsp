<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="it.cnr.brevetti.ejb.entities.Trovato"%>
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/Gcatalogo.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<%@page import="it.cnr.brevetti.util.Version"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- InstanceBeginEditable name="doctitle" -->
<title>Consiglio Nazionale delle Ricerche - Catalogo Brevetti e Propriet&agrave; Intellettuale</title>
<!-- InstanceEndEditable -->
<link href="css/standard.css?<%=Version.getVersion()%>" rel="stylesheet" type="text/css" />
<link href="css/reports.css?<%=Version.getVersion()%>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="scripts/standard.js?<%=Version.getVersion()%>"></script>
<!-- InstanceBeginEditable name="head" -->
<%
	Trovato tro = (Trovato)request.getAttribute("trovato");
	String titolari = (String)request.getAttribute("titolari");
	String inventori = (String)request.getAttribute("inventori");
	String IPC = (String)request.getAttribute("IPC");
	String TMC = (String)request.getAttribute("TMC");
%>
<script language="JavaScript1.4" type="text/javascript"><!--
function inizialize(){
	if(window.opener != null){
		GetObj('link2cnr').style.visibility='hidden';
	}
}
//-->
</script>
<!-- InstanceEndEditable -->
</head>
<body onload="inizialize();">
<table width="950px" border="0" align="center" cellpadding="0" cellspacing="6">
  <tr>
    <td valign="bottom" id="Gheader"><hr align="right" width="775" size="1" noshade color="#ffffff" class="GdottedLine" />
    <div id="link2cnr"><a href="http://www.cnr.it">&laquo; back to cnr.it</a></div></td>
  </tr>
  <tr>
    <td valign="top"><!-- InstanceBeginEditable name="content" -->
      <table border="0" align="center" cellpadding="10" cellspacing="0">
        <tr>
          <td colspan="3" align="right"><table width="914" border="0" cellpadding="0" cellspacing="0" id="Gtab">
              <tr>
                <td width="64%" align="left"><p><br/>
                Scheda di dettaglio</p></td>
                <td width="12%" align="left"><img src="images/Catalog/scarica-pdf.png" class="GImgOver" onclick="window.location='download/PDF/datasheet<%=tro.getNsrif() %>.pdf'"/></td>
                <td width="12%" align="left"><img src="images/Catalog/contatti.png" class="GImgOver" onclick="window.location='Contatti.do?nsrif=<%=tro.getNsrif() %>'"/></td>
                <td width="12%" align="left"><img src="images/Catalog/close.png" class="GImgOver" onclick="self.close();"/></td>
              </tr>
              <tr>
                <td colspan="4" class="Gscheda-top">&nbsp;</td>
              </tr>
              <tr>
                <td colspan="4" class="Gscheda-mid"><h1><%= tro.getTitolo() %></h1></td>
              </tr>
              <tr>
                <td colspan="4" class="Gscheda-mid"><h2>
                    <%= tro.getDescrizione()!=null?tro.getDescrizione():"" %>
                  </h2></td>
              </tr>
              <tr>
                <td colspan="4" class="Gscheda-bot">&nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td width="47%" align="right"><table width="393" border="0" cellpadding="0" cellspacing="0" id="Gtably">
              <tr>
                <td class="Gschedina-top">&nbsp;</td>
              </tr>
              <tr>
                <td align="left" class="Gschedina-mid"><p><br/>
                    Inventori</p>
                  <%= "<ul>"+inventori+"</ul>" %> </td>
              </tr>
              <tr>
                <td class="Gschedina-bot">&nbsp;</td>
              </tr>
            </table>
            <table width="393" border="0" cellpadding="0" cellspacing="0" id="Gtably">
              <tr>
                <td class="Gschedina-top">&nbsp;</td>
              </tr>
              <tr>
                <td align="left" class="Gschedina-mid"><p><br/>
                    Titolarit&Agrave;</p>
                <%= "<ul>"+titolari+"</ul>" %> </td>
              </tr>
              <tr>
                <td class="Gschedina-bot">&nbsp;</td>
              </tr>
            </table></td>
          <td width="4%"><img src="images/spacer.gif" width="51" height="1" /></td>
          <td width="47%" align="left"><table width="393" border="0" cellpadding="0" cellspacing="0" id="Gtably">
              <tr>
                <td class="Gschedina-top">&nbsp;</td>
              </tr>
              <tr>
                <td align="left" class="Gschedina-mid"><p><br/>
                    Classificazioni</p>
                  <ul>
                    <%= IPC %>
                </ul></td>
              </tr>
              <tr>
                <td class="Gschedina-bot">&nbsp;</td>
              </tr>
            </table>
            <table width="393" border="0" cellpadding="0" cellspacing="0" id="Gtably">
              <tr>
                <td class="Gschedina-top">&nbsp;</td>
              </tr>
              <tr>
                <td align="left" class="Gschedina-mid"><p><br/>
                    Settore Tecnologico</p>
                  <ul>
                    <%= TMC %>
                </ul></td>
              </tr>
              <tr>
                <td class="Gschedina-bot">&nbsp;</td>
              </tr>
            </table>
            <table width="393" border="0" cellpadding="0" cellspacing="0" id="Gtably">
              <tr>
                <td class="Gschedina-top">&nbsp;</td>
              </tr>
              <tr>
                <td align="left" class="Gschedina-mid"><p><br/>
                    Keyword</p>
                  <ul>
                    <li><%= tro.getParolaChiave()!=null?tro.getParolaChiave():"" %></li>
                </ul></td>
              </tr>
              <tr>
                <td class="Gschedina-bot">&nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td colspan="3" align="center"><table cellpadding="5" cellspacing="0" id="Gtably">
              <tr>
                <td class="Gscheda2-top">&nbsp;</td>
              </tr>
              <tr>
                <td class="Gscheda2-mid"><p><br/>
                    Vantaggi</p>
                  <h2><%= tro.getVantaggi()!=null?tro.getVantaggi():"" %></h2> </td>
              </tr>
              <tr>
                <td class="Gscheda2-bot">&nbsp;</td>
              </tr>
            </table>
            <table cellpadding="5" cellspacing="0" id="Gtably">
              <tr>
                <td class="Gscheda2-top">&nbsp;</td>
              </tr>
              <tr>
                <td class="Gscheda2-mid"><p><br/>
                    Applicazioni</p>
                  <h2><%= tro.getUsi()!=null?tro.getUsi():"" %></h2> </td>
              </tr>
              <tr>
                <td class="Gscheda2-bot">&nbsp;</td>
              </tr>
            </table></td>
        </tr>
      </table>
      <!-- InstanceEndEditable --></td>
  </tr>
  <tr>
    <td align="right" class="Gfooter"><hr align="right" width="775" size="1" noshade color="#880010" />fonte dati: &quot;Gestione Trovati&quot; | <a href="https://brevetti.cnr.it" target="_blank">brevetti.cnr.it</a> | <a href="http://www.cnr.it" target="_blank">www.cnr.it</a> | Consiglio Nazionale delle Ricerche |  P.le   Aldo Moro, 7 | 00185 Roma</td>
  </tr>
</table>
</body>
<!-- InstanceEnd --></html>
