<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<c:set var="tti" value="${sessionScope.datiTrovato.tipiTrovatoId}" />
<c:set var="bundle" value="${sessionScope.bundleArray[tti]}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="it.cnr.brevetti.ejb.entities.Inventore"%>
<%@page import="it.cnr.brevetti.inventori.actionForms.InventoreForm"%>
<%@page import="java.util.Date"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<script type="text/javascript" src="scripts/standard.js?<%=Version.getVersion()%>"></script>
<title>Selezione <bean:message key="labels.inventore00" bundle="${bundle}" /> del trovato</title>
<style type="text/css">
#errors {
	top: 100px;
	left: 200px;
    position: absolute;
    width: 400px;
    z-index: 60;
    display: none;
}
</style>
<script language="JavaScript" type="text/JavaScript">

<%
	Inventore inv = (Inventore)request.getAttribute("inv");
	String status = ""+request.getAttribute("status");
	InventoreForm iform = (InventoreForm)request.getAttribute("iform");
	boolean isCnr = false;					//iform.getMatricolaCnr()!=null || iform.getMatricolaCnr().intValue()!=0
	boolean isNew = false;
	if(!status.startsWith("chiudi")){
		isCnr = (iform.getTipo()!=null && iform.getTipo().startsWith("CNR"));
		isNew = (iform.getId()== null || iform.getId().intValue()==0);
%> 
		function on_load(){
			document.forms[0].invia.value = '<%=(isNew?"Inserisci":"Seleziona") %>';
			GetObj('nuovo').style.visibility='<%=(isNew?"hidden":"visible") %>';
			fade('errors',true);
		}

		function on_submit(){
			document.forms[0].act.value='ok';
			document.forms[0].submit();
		}
		
		function on_reset(){
			document.forms[0].act.value='ricomincia';
			document.forms[0].submit();
		}
		
		function on_add(){
			document.forms[0].act.value='nuovo';
		}
<%
	} else {
		if(inv!=null){
			String nome = inv.getCognome()+" "+inv.getNome(); 		//(String)request.getAttribute("nome");
%>
			window.opener.eval('param1=<%= "\"" + nome.replaceAll("\"","\\\\\\\\\\\"").replaceAll("\'","\\\\\'")+"\"" %>');
			window.opener.eval('param2="<%= inv.getId() %>"');
			window.opener.eval("aggiornaInv();");
<%
		}
%>
		window.close();
<%
	} 
%>
</script>
<!-- html:javascript formName="iform"/ -->
</head>
<body onload="on_load();">
<html:form action="InventoreForm.do" method="post">
  <input type="hidden" name="act" />
  <table width="800" border="1" cellspacing="15">
    <tr> 
      <td colspan="4"><b><bean:message key="labels.inventore00" bundle="${bundle}" /> 
<% 		if(isCnr){ 
%>
	      	(matricola <%= iform.getMatricolaCnr() %> )
<%		
			if(iform.getDataCessazione()!=null && iform.getDataCessazione().before(new Date())){
%>
	      	| <span class='highlighted'>Dipendente non pi&ugrave; in servizio</span>
<%					
			}
		}
%>
      	- dettagli:</b><html:hidden property="id"/><html:hidden property="matricolaCnr" /> </td>
    </tr>
    <tr> 
      <td width="111" align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Cognome</font></strong></td>
      <td width="242" align="center"> <html:text property="cognome" readonly="<%=(!isNew || isCnr) %>" size="40" /></td>
      <td width="112" align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Nome</font></strong></td>
      <td width="242" align="center"> <html:text property="nome" readonly="<%=(!isNew || isCnr) %>" size="40" /></td>
    </tr>
    <tr> 
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Tipo <bean:message key="labels.inventore00" bundle="${bundle}" /></font></strong></td>
      <td align="center">
      		<html:select property="tipo" size="1" style="width: 265px;">
    			<html:options collection="tipi" property="value" labelProperty="label" />
			</html:select>  
      </td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Indirizzo</font></strong></td>
      <td align="center"><html:text property="indirizzo" readonly="<%=(!isNew || isCnr) %>" size="40" /></td>
    </tr>
    <tr>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Citt&agrave;</font></strong></td>
      <td align="center"><html:text property="citta" readonly="<%=(!isNew || isCnr) %>" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">CAP</font></strong></td>
      <td align="center"><html:text property="cap" readonly="<%=(!isNew || isCnr) %>" size="40" /></td>
    </tr>
    <tr> 
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Telefono</font></strong></td>
      <td align="center"><html:text property="telefono" readonly="<%=(!isNew || isCnr) %>" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">E-mail</font></strong></td>
      <td align="center"> <html:text property="email" readonly="<%=(!isNew) %>" size="40" /></td>
    </tr>
    <tr> 
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Fax</font></strong></td>
      <td align="center"><html:text property="fax" readonly="<%=(!isNew || isCnr) %>" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Cellulare</font></strong></td>
      <td align="center"> <html:text property="cellulare" readonly="<%=(!isNew || isCnr) %>" size="40" /></td>
    </tr>
<%	if(!isCnr){
%>
    <tr> 
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Ente 
        di riferimento</font></strong></td>
      <td align="center"> <html:text property="enteRecapito" readonly="<%=(!isNew) %>" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Struttura dell'ente</font></strong></td>
      <td align="center"> <html:text property="strutturaEnteRecapito" readonly="<%=(!isNew) %>" size="40" /></td>
    </tr>
<%	}
	if(isNew){
%>
    <tr>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">Banca</font></strong></td>
      <td align="center"><html:text property="banca" readonly="<%=(isCnr) %>" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">n&deg; C/C</font></strong></td>
      <td align="center"><html:text property="cc" readonly="<%=(isCnr) %>" size="40" /></td>
    </tr>
    <tr>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">ABI</font></strong></td>
      <td align="center"><html:text property="abi" readonly="<%=(isCnr) %>" size="40" /></td>
      <td align="center" bgcolor="#A35052"><strong><font color="#FFFFFF">CAB</font></strong></td>
      <td align="center"><html:text property="cab" readonly="<%=(isCnr) %>" size="40" /></td>
    </tr>
<%	}
%>
    <tr> 
      <td colspan="4" align="center"> <input type="button" name="invia" value="Submit" onclick="on_submit();" /> 
        &nbsp;&nbsp;&nbsp;<html:button property="ricomincia" value="Ricomincia" onclick="on_reset();" /> 
        &nbsp;&nbsp;&nbsp;<html:button property="nuovo" styleId="nuovo" value="Nuovo" onmouseup='on_add();' style="visibility:hidden" />
      </td>
    </tr>
  </table>
<html:errors />
</html:form>
</body>
</html>
