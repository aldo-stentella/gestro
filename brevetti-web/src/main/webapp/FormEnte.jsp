<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="it.cnr.brevetti.entiEsterni.actionForms.EnteEsternoForm"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<script type="text/javascript" src="scripts/standard.js?<%=Version.getVersion()%>"></script>
<title>Selezione inventore del trovato</title>
<style type="text/css">
#errors {
	top: 300px;
	left: 70px;
    position: absolute;
    width: 400px;
    z-index: 60;
    display: none;
}
</style>
<script language="JavaScript" type="text/JavaScript">

<%
	String status = ""+request.getAttribute("status");
	EnteEsternoForm eform = (EnteEsternoForm)request.getAttribute("eform");
	boolean isNew = false;
	if(status.startsWith("chiudi")){
%>
		window.opener.eval("aggiornaTit();");
		window.close();
<%
	} else if(status.startsWith("conferma")) {
			if(eform.getId()== null || eform.getId().intValue()==0)
				isNew = true;
%> 
		function on_load(){
			document.forms[0].invia.value = 'Seleziona';
			GetObj('nuovo').style.visibility='visible';
			fade('errors',true);
		}
		
		
		function on_add(){
			document.forms[0].act.value='nuovo';
			document.forms[0].action='<%=request.getParameter("action")%>';
			document.forms[0].submit();
		}

<%
	 } else {					//if(status.startsWith("crea")) 
		 	isNew=true;
%> 
		function on_load(){
			document.forms[0].invia.value = 'Inserisci';
			fade('errors',true);
		}
		
		function on_add(){
			//
		}

<%
	 }
%>
		function on_submit(){
			document.forms[0].act.value='ok';
			document.forms[0].action='<%=request.getParameter("action")%>';
			document.forms[0].submit();
		}
</script>
<!-- html:javascript formName="eform"/-->
</head>
<body onload="on_load();">
<html:form action="EnteForm.do" method="post">
  <input type="hidden" name="act" />
  <input type="hidden" name="titolaritaId" value="<%= session.getAttribute("titolaritaId") %>" />
  <table width="800" border="1" cellspacing="15">
    <tr> 
      <td colspan="4"><b>Dettagli dell&acute;ente esterno 
      	</b><html:hidden property="id"/></td>
    </tr>
    <tr> 
      <td width="111" align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Nome</font></strong></td>
      <td width="242" align="center"> <html:text property="nome" readonly="<%=(!isNew) %>" size="40" /></td>
      <td width="112" align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Struttura</font></strong></td>
      <td width="242" align="center"> <html:text property="struttura" readonly="<%=(!isNew) %>" size="40" /></td>
    </tr>
    <tr> 
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Sigla</font></strong></td>
	  <td align="center"><html:text property="sigla" readonly="<%=(!isNew) %>" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Indirizzo</font></strong></td>
      <td align="center"><html:text property="indirizzo" readonly="<%=(!isNew) %>" size="40" /></td>
    </tr>
    <tr>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Citt&agrave;</font></strong></td>
      <td align="center"><html:text property="localita" readonly="<%=(!isNew) %>" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">CAP</font></strong></td>
      <td align="center"><html:text property="cap" readonly="<%=(!isNew) %>" size="40" /></td>
    </tr>
    <tr>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Provincia</font></strong></td>
      <td align="center"><html:text property="provincia" readonly="<%=(!isNew) %>" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Stato/nazione</font></strong></td>
      <td align="center">
<%
	if(isNew){
%>
	  <html:select property="statoId" style="width: 265px;">
        <html:options collection="nazioni" property="value" labelProperty="label" />
      </html:select>
<%
	}else{
%>
      <html:hidden property="statoId"/><html:text property="nazNome" readonly="true" size="40" />
<%
	}
%>
      </td>
    </tr>
    <tr>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Telefono</font></strong></td>
      <td align="center"><html:text property="telefono" readonly="<%=(!isNew) %>" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">E-mail</font></strong></td>
      <td align="center"><html:text property="email" readonly="<%=(!isNew) %>" size="40" /></td>
    </tr>
    <tr>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Fax</font></strong></td>
      <td align="center"><html:text property="fax" readonly="<%=(!isNew) %>" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Telex</font></strong></td>
      <td align="center"><html:text property="telex" readonly="<%=(!isNew) %>" size="40" /></td>
    </tr>
    <tr>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Sito web</font></strong></td>
      <td align="center"><html:text property="sitoWeb" readonly="<%=(!isNew) %>" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Partita IVA</font></strong></td>
      <td align="center"><html:text property="partitaIva" readonly="<%=(!isNew) %>" size="40" /></td>
    </tr>
    <tr> 
      <td colspan="4" align="center"> <input type="button" name="invia" value="Submit" onclick="on_submit();" /> 
        &nbsp;&nbsp;&nbsp;<input type="button" value="Annulla" onclick='window.close();' /> 
        &nbsp;&nbsp;&nbsp;<html:button styleId="nuovo" property="new" value="Nuovo ente esterno" onclick='on_add();' style="visibility:hidden" />      </td>
    </tr>
  </table>
<html:errors />
</html:form>
</body>
</html>
