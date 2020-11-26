<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="it.cnr.brevetti.ejb.entities.InventionDisclosure"%>
<%@page import="java.util.Date"%>
<%@page import="it.cnr.brevetti.util.UtilityFunctions"%>
<%@page import="it.cnr.brevetti.ejb.entities.DocumentoInfo"%>
<%@page import="java.util.List"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Consiglio Nazionale delle Ricerche - Unit&agrave; Valorizzazione della Ricerca</title>
<link href="../css/standard.css?<%=Version.getVersion()%>" rel="stylesheet" type="text/css" />
<link href="../css/reports.css?<%=Version.getVersion()%>" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/JavaScript">
	function doSubmit(){
		if(document.forms[0].message.value.length>4000)
			alert('Campo messaggio troppo lungo, ammessi fino a 4000 caratteri.');
		else
			document.forms[0].submit();
	}
	function inizialize(){
		//
	}
</script>
</head>
<body onload="inizialize()">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><img src="../images/headerUVR.png" /></td>
  </tr>
  <tr>
    <td align="center" valign="top"><table id="boxScheda">
        <tr>
          <td align="center"><h1>Comunicazione preliminare</h1>
            <h2>PROPOSTA DI BREVETTO O MARCHIO</h2>
    	<c:if test="${not empty mess}">
		    <br /><br />
			<span class="errorCloud"><c:out value="${mess}"/></span><br /><br /><br />
		</c:if>
<form enctype="multipart/form-data" method="post" action="GesPro.do">
<%
	InventionDisclosure indi = (InventionDisclosure)request.getAttribute("indi");
	String[] tipi = (String[])request.getAttribute("tipi");
	String message = request.getAttribute("message")!=null?""+request.getAttribute("message"):"";
%>
<input type="hidden" name="tipoDocumentoId" value="1" />
<input type="hidden" name="id" value="<%= indi.getId() %>" />
<table width="1000" id="excel">
  <tr>
    <th colspan="2">Integrazione della &quot;comunicazione preliminare di invenzione&quot;</th>
    <td width="10"><a href="Home.do"><img src="../images/close.gif" alt="Annulla" /></a></td>
    </tr>
  <tr>
    <td align="right"> Identificatore:</td>
    <td colspan="2"><div class="even"><%= indi.getId() %></div></td>
  </tr>
  <tr>
    <td align="right">Titolo provvisorio:</td>
    <td colspan="2"><div class="even"><%= indi.getTitolo() %></div></td>
  </tr>
  <tr>
    <td align="right"> Tipologia:</td>
    <td colspan="2"><div class="even"><%= tipi[indi.getTipiTrovatoId()] %></div></td>
  </tr>
  <tr>
    <td colspan="3"><hr width="90%"  /></td>
  </tr>
  <tr>
    <td width="295" align="right">Nuovo documento da allegare:</td>
    <td colspan="2" width="625">
    <input type="file" name="fileField" onchange="document.forms[0].update.value=1;" />
    </td>
  </tr>
  <tr>
    	<td align="right">Informazioni richieste o commenti:</td>
		<td colspan="2" align="left">
			<textarea name="message" rows="6" cols="90"><%= message %></textarea>
		</td>
	</tr>
  <tr>
    <td align="right">Inviare l'integrazione alla SPRVR:</td>
    <td colspan="2"><img src="../images/Catalog/invia.png" hspace="20" onclick="doSubmit();" style="cursor: pointer;" /></td>
  </tr>
  <tr>
    <td align="right">Richiedente:</td>
    <td colspan="2"><%= session.getAttribute("sessioneEutente") %></td>
  </tr>
  <tr>
    <td align="right">Data di trasmissione:</td>
    <td colspan="2"><%= UtilityFunctions.itForm.format(new Date()) %></td>
  </tr>
</table>
</form>
<br/>

</td></tr></table></td></tr>
<tr><td align="center" class="Gfooter"><strong>Consiglio Nazionale delle Ricerche</strong> -  P.le   Aldo Moro, 7 - 00185 Roma</td></tr>
</table>
</body>
</html>
