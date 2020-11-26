<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="it.cnr.brevetti.entiEsterni.actionForms.EnteEsternoForm"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Selezione inventore del trovato</title>
</head>
<body>
<html:form action="EnteForm.do" method="post">
  <table width="800" border="1" cellspacing="15">
    <tr> 
      <td colspan="4"><b>Dettagli dell&acute;ente esterno 
      	</b>&nbsp;</td>
    </tr>
    <tr> 
      <td width="111" align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Nome</font></strong></td>
      <td width="242" align="center"> <html:text property="nome" readonly="true" size="40" /></td>
      <td width="112" align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Struttura</font></strong></td>
      <td width="242" align="center"> <html:text property="struttura" readonly="true" size="40" /></td>
    </tr>
    <tr> 
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Sigla</font></strong></td>
	  <td align="center"><html:text property="sigla" readonly="true" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Indirizzo</font></strong></td>
      <td align="center"><html:text property="indirizzo" readonly="true" size="40" /></td>
    </tr>
    <tr>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Citt&agrave;</font></strong></td>
      <td align="center"><html:text property="localita" readonly="true" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">CAP</font></strong></td>
      <td align="center"><html:text property="cap" readonly="true" size="40" /></td>
    </tr>
    <tr>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Provincia</font></strong></td>
      <td align="center"><html:text property="provincia" readonly="true" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Stato/nazione</font></strong></td>
      <td align="center"><html:hidden property="statoId"/><html:text property="nazNome" readonly="true" size="40" /></td>
    </tr>
    <tr>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Telefono</font></strong></td>
      <td align="center"><html:text property="telefono" readonly="true" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">E-mail</font></strong></td>
      <td align="center"><html:text property="email" readonly="true" size="40" /></td>
    </tr>
    <tr>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Fax</font></strong></td>
      <td align="center"><html:text property="fax" readonly="true" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Telex</font></strong></td>
      <td align="center"><html:text property="telex" readonly="true" size="40" /></td>
    </tr>
    <tr>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Sito web</font></strong></td>
      <td align="center"><html:text property="sitoWeb" readonly="true" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Partita IVA</font></strong></td>
      <td align="center"><html:text property="partitaIva" readonly="true" size="40" /></td>
    </tr>
    <tr> 
      <td colspan="4" align="center"><input type="button" value="Chiudi" onclick='window.close();' /></td>
    </tr>
  </table>
</html:form>
</body>
</html>
