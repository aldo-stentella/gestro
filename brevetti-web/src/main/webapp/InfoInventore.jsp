 <?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<c:set var="tti" value="${sessionScope.datiTrovato.tipiTrovatoId}" />
<c:set var="bundle" value="${sessionScope.bundleArray[tti]}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="it.cnr.brevetti.inventori.actionForms.InventoreForm"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Selezione <bean:message key="labels.inventore00" bundle="${bundle}" /> del trovato</title>
<%
	InventoreForm iform = (InventoreForm)request.getAttribute("iform");
	boolean isCnr = (iform.getTipo().startsWith("CNR"));
%> 
</head>
<body>
<html:form action="SelInventore.do" method="post">
  <table width="800" border="1" cellspacing="15">
    <tr> 
      <td colspan="4"><b><bean:message key="labels.inventore00" bundle="${bundle}" /> 
<% 		if(isCnr){ 
%>
	      	(matricola <%= iform.getMatricolaCnr() %> )
<%		}
%>
      	- dettagli:</b>&nbsp;</td>
    </tr>
    <tr> 
      <td width="111" align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Cognome</font></strong></td>
      <td width="242" align="center"> <html:text property="cognome" readonly="true" size="40" /></td>
      <td width="112" align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Nome</font></strong></td>
      <td width="242" align="center"> <html:text property="nome" readonly="true" size="40" /></td>
    </tr>
    <tr> 
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Tipo <bean:message key="labels.inventore00" bundle="${bundle}" /></font></strong></td>
      <td align="center"><html:text property="tipo" readonly="true" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Indirizzo</font></strong></td>
      <td align="center"><html:text property="indirizzo" readonly="true" size="40" /></td>
    </tr>
    <tr>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Citt&agrave;</font></strong></td>
      <td align="center"><html:text property="citta" readonly="true" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">CAP</font></strong></td>
      <td align="center"><html:text property="cap" readonly="true" size="40" /></td>
    </tr>
    <tr> 
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Telefono</font></strong></td>
      <td align="center"><html:text property="telefono" readonly="true" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">E-mail</font></strong></td>
      <td align="center"> <html:text property="email" readonly="true" size="40" /></td>
    </tr>
    <tr> 
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Fax</font></strong></td>
      <td align="center"><html:text property="fax" readonly="true" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Cellulare</font></strong></td>
      <td align="center"> <html:text property="cellulare" readonly="true" size="40" /></td>
    </tr>
<%	if(!isCnr){
%>
    <tr> 
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Ente 
        di rifermento</font></strong></td>
      <td align="center"> <html:text property="enteRecapito" readonly="true" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Struttura dell'ente</font></strong></td>
      <td align="center"> <html:text property="strutturaEnteRecapito" readonly="true" size="40" /></td>
    </tr>
<%	}
%>
    <tr> 
      <td colspan="4" align="center"><input type="button" value="Chiudi" onclick='window.close();' /></td>
    </tr>
  </table>
</html:form>
</body>
</html>
