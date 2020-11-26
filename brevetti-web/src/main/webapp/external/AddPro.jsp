<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.Date"%>
<%@page import="it.cnr.brevetti.util.UtilityFunctions"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<%@page import="it.cnr.brevetti.util.ApplicationProperties"%>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Consiglio Nazionale delle Ricerche - Unit&agrave; Valorizzazione della Ricerca</title>
<link href="../css/standard.css?<%=Version.getVersion()%>" rel="stylesheet" type="text/css" />
<link href="../css/reports.css?<%=Version.getVersion()%>" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/JavaScript">
	function doSubmit(){
		if(document.forms[0].titolo.value.trim()=='')
			alert('Compilare il campo - TITOLO PROVVISORIO -');
		else
			document.forms[0].submit();
	}
	function inizialize(){
		document.forms[0].titolo.value = '<%=request.getAttribute("titolo")%>';
		document.forms[0].tipiTrovatoId.selectedIndex = <%=request.getAttribute("tipiTrovatoId")%>;
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
<form enctype="multipart/form-data" method="post" action="AddPro.do">
<input type="hidden" name="tipoDocumentoId" value="1" />
<table width="1000" id="excel">
  <tr>
    <th colspan="3">Caricamento documento &quot;comunicazione preliminare di invenzione&quot;</th>
    <td width="10"><a href="Home.do"><img src="../images/close.gif" alt="Annulla" /></a></td>
    </tr>
  <tr>
    <td><h1>&#9312;</h1></td>
		<td colspan="3" align="left">
			<blockquote>
				Scaricare e compilare il modulo relativo alla propria richiesta:
				<ul class="moduliUVR">
					<li><a href="<%=ApplicationProperties.getInstance().getModuloInvenzioneUrl() %>">
						&quot;comunicazione preliminare di invenzione&quot;
					</a></li>
					<li><a href="<%=ApplicationProperties.getInstance().getModuloMarchioUrl() %>">
						&quot;modulo di richiesta marchio&quot;
					</a></li>
				</ul> 
			</blockquote>
		</td>
	</tr>
  <tr>
    <td width="20"><h1>&#9313;</h1></td>
    <td width="275" align="right">Scegliere il file compilato sul proprio disco locale:</td>
    <td colspan="2" width="625">
    <input type="file" name="fileField" size="300" onchange="document.forms[0].update.value=1;" />
    </td>
  </tr>
  <tr>
    <td><h1>&#9314;</h1></td>
    <td align="right">Comunicare il titolo provvisorio del trovato:</td>
    <td colspan="2"><input type="text" name="titolo" size="100" /></td>
  </tr>
  <tr>
    <td><h1>&#9315;</h1></td>
    <td align="right"> Scegliere la tipologia:</td>
    <td colspan="2"><select name="tipiTrovatoId">
	    <option value="1" selected="selected">Brevetto per invenzione industriale</option>
        <option value="3">Diritto d'Autore</option>
        <option value="4">Marchio</option>
        <option value="5">Modello di Utilit&agrave;</option>
        <option value="6">Nuova Variet&agrave; Vegetale</option>
        <option value="7">Software</option>
        <option value="8">Know-How</option>
    </select></td>
  </tr>
  <tr>
    <td><h1>&#9316;</h1></td>
    <td align="right"> Inviare la richiesta a UVR:</td>
    <td colspan="2"><img src="../images/Catalog/invia.png" hspace="20" onclick="doSubmit();" style="cursor: pointer;" /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="right">Richiedente:</td>
    <td colspan="2"><%= session.getAttribute("sessioneEutente") %></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
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
