<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/Gcatalogo.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<%@page import="it.cnr.brevetti.util.Version"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- InstanceBeginEditable name="doctitle" -->
<title>Consiglio Nazionale delle Ricerche - Catalogo Brevetti e Propriet&agrave; Intellettuale - Modulo di richiesta informazioni</title>
<!-- InstanceEndEditable -->
<link href="css/standard.css?<%=Version.getVersion()%>" rel="stylesheet" type="text/css" />
<link href="css/reports.css?<%=Version.getVersion()%>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="scripts/standard.js?<%=Version.getVersion()%>"></script>
<!-- InstanceBeginEditable name="head" -->
<script type="text/javascript" language="JavaScript1.4"><!--
	function inizialize(){
		if(window.opener != null){
			GetObj('link2cnr').style.visibility='hidden';
		}
	}

	String.prototype.trim = function() { return this.replace(/^\s+|\s+$/g, ""); };

	function doSubmit(){
		if(document.form1.nominativo.value.trim()==''){
			alert('Nominativo obbligatorio.');
			return;
		}
		if(document.form1.j_captcha_response.value.trim()==''){
			alert('Inserire codice Captcha.');
			return;
		}
		var field = document.form1.email;
		var goodEmail = field.value.match(/\b(^(\S+@).+((\.com)|(\.net)|(\.edu)|(\.mil)|(\.gov)|(\.org)|(\..{2,2}))$)\b/gi);
		if (!goodEmail){
		    alert('Inserire un indirizzo e-mail valido.');
			return;
		}
		document.form1.submit();
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
<%
	if(request.getMethod().equals("POST") && request.getAttribute("captchaError")==null){
		if(request.getAttribute("error")!=null){
%>
		<br/><br/><br/><br/><font color="red"><b>Si &egrave; verificato un errore durante l'invio della richiesta.</b><br/><br/>
		</font>
		Si prega di ritentare pi&ugrave; tardi.<br/><br/>
		<input type="button" value="Chiudi" onclick="self.close();" /><br/><br/><br/><br/>
<%
		}else{
%>
		<br/><br/><br/><br/><br/>Richiesta inviata con successo.<br/><br/>
		Il nostro referente la contatter&agrave; a breve.<br/><br/>
		Grazie.<br/><br/>
		<input type="button" value="Chiudi" onclick="self.close();" />	<br/><br/><br/><br/>	
<%
		}
	}else{
%>
<form action="Contatti.do" method="post" name="form1" id="form1">
<input type="hidden" name="nsrif" value="<%=request.getParameter("nsrif") %>" />
<table border="0" cellpadding="0" cellspacing="0" id="Gtab">
  <tr>
    <td colspan="2" align="left"><p><br />Richiesta informazioni</p></td>
    </tr>
  <tr>
    <td colspan="2" class="Gscheda-top">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2" class="Gscheda-mid"><table width="95%" cellpadding="7" cellspacing="0">
  <tr>
    <td width="21%" align="right">Nominativo *</td>
    <td width="79%"><input name="nominativo" type="text" size="60" value="<%=request.getParameter("nominativo")!=null?request.getParameter("nominativo"):"" %>" /></td>
  </tr>
  <tr>
    <td align="right">Societ&agrave; / ente</td>
    <td><input name="ente" type="text" size="60" value="<%=request.getParameter("ente")!=null?request.getParameter("ente"):"" %>" /></td>
  </tr>
  <tr>
    <td align="right">Posizione ricoperta</td>
    <td><input name="posizione" type="text" size="60" value="<%=request.getParameter("posizione")!=null?request.getParameter("posizione"):"" %>" /></td>
  </tr>
  <tr>
    <td align="right">Email di contatto *</td>
    <td><input name="email" type="text" size="60" value="<%=request.getParameter("email")!=null?request.getParameter("email"):"" %>" /></td>
  </tr>
  <tr>
    <td align="right">Telefono</td>
    <td><input name="telefono" type="text" size="60" value="<%=request.getParameter("telefono")!=null?request.getParameter("telefono"):"" %>" /></td>
  </tr>
  <tr>
    <td align="right">Informazioni richieste</td>
    <td><textarea name="info" cols="45" rows="3"><%=request.getParameter("info")!=null?request.getParameter("info"):"" %></textarea></td>
  </tr>
  <tr>
    <td align="right">Inserire codice 'Captcha' *</td>
    <td>
      <input type='text' name="j_captcha_response" value="" />
      <font color="red"><%= request.getAttribute("captchaError")!=null?"Codice 'Captcha' errato, ritentare":"" %>      </font>    </td>
  </tr>
  <tr>
    <td align="right">Codice 'Captcha' :</td>
    <td>
      <img src="<%= request.getContextPath()+"/jcaptcha" %>" border="1" align="middle" /> <input type="button" value="Rigenera immagine" onclick="document.location.reload();" />    </td>
  </tr>
  <tr>
    <td align="center"><em>(* campi obbligatori)</em></td>
    <td align="center">
    	<input type="button" onclick="doSubmit();" value="Invia" />&nbsp;&nbsp;
    	<input type="button" onclick="self.close();" value="Annulla" />    </td>
  </tr></table>
  </td></tr>
  <tr>
    <td colspan="2"  class="Gscheda-bot">&nbsp;</td>
    </tr>
</table>
</form>
<%	
	}
%>
<!-- InstanceEndEditable --></td>
  </tr>
  <tr>
    <td align="right" class="Gfooter"><hr align="right" width="775" size="1" noshade color="#880010" />fonte dati: &quot;Gestione Trovati&quot; | <a href="https://brevetti.cnr.it" target="_blank">brevetti.cnr.it</a> | <a href="http://www.cnr.it" target="_blank">www.cnr.it</a> | Consiglio Nazionale delle Ricerche |  P.le   Aldo Moro, 7 | 00185 Roma</td>
  </tr>
</table>
</body>
<!-- InstanceEnd --></html>