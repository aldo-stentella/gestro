<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<c:set var="tti" value="${sessionScope.datiTrovato.tipiTrovatoId}" />
<c:set var="bundle" value="${sessionScope.bundleArray[tti]}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.*"%>
<%@page import="it.cnr.brevetti.ejb.entities.Dipartimento"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<title>Selezione titolare del trovato</title>
<style type="text/css">
#errors {
	top: 200px;
	left: 200px;
    position: absolute;
    width: 400px;
    z-index: 60;
    display: none;
}
</style>
<script type="text/javascript" src="scripts/standard.js?<%=Version.getVersion()%>"></script>
<script language="javascript">

<%
String cs = (String)request.getAttribute("cs");
Iterator i = new ArrayList().iterator();
Iterator j = new ArrayList().iterator();
if(request.getMethod().equals("POST")){

%>
  window.opener.eval("aggiornaTit();");
  window.close();
<%
}else {
	i = (Iterator) request.getAttribute("allDip");
	j = (Iterator) request.getAttribute("allInv");
	if(cs!=null){
%>
var arr = new Array(<%=cs%>);
function popolate(){
	for(var i=0; i<arr.length; i++){
		document.forms[0].combo.options[i]=new Option(arr[i].substring(0,arr[i].indexOf("|")),arr[i]);
	}
}
function doFilter(patt) {
	var el = "";
	popolate();
	var eln = document.forms[0].combo.options.length;
	for(var i=0; i<eln; i++){
		el = document.forms[0].combo.options[i].text.toUpperCase();
		if(el.indexOf(patt.toUpperCase())==-1){
			if(document.all){
				document.forms[0].combo.options.remove(i);
			} else {
				document.forms[0].combo.options[i] = null;
			}
			i--;
			eln--;
		}
	}
	if(eln==0) document.forms[0].combo.options[0]=new Option('Nessun elemento trovato','§');
}
<%
	}
%>
function change(n){
	if(n==1){
		document.forms[0].dipartimenti.style.visibility="visible";
		document.forms[0].inventori.style.visibility="hidden";
		document.forms[0].filter.style.visibility="hidden";
		document.forms[0].combo.style.visibility="hidden";
	} else if(n==3){
		document.forms[0].dipartimenti.style.visibility="hidden";
		document.forms[0].inventori.style.visibility="visible";
		document.forms[0].filter.style.visibility="hidden";
		document.forms[0].combo.style.visibility="hidden";
	} else if(n==4){
		document.forms[0].dipartimenti.style.visibility="hidden";
		document.forms[0].inventori.style.visibility="hidden";
		document.forms[0].filter.value="";
		document.forms[0].filter.style.visibility="visible";
		document.forms[0].combo.style.visibility="visible";
		popolate();
	}
}
function doSubmit(){
	if(document.forms[0].tipi_titolare[1].checked){
		var z = document.forms[0].inventori.selectedIndex;
		if(z<0){
			return;
		}
		if(document.forms[0].inventori.options[z].value.indexOf('-')==0){
			//alert("L'inventore selezionato non è ancora memorizzato negli archivi. \nSolo a seguito del salvataggio del presente trovato sarà possibile selezionarlo come titolare.");
			fade('errors',true);
			return;
		}
}
	
	if(document.forms[0].tipi_titolare[2].checked){
		if(document.forms[0].combo.selectedIndex<0 ){
			return;
		}
		var list = document.forms[0].combo;
		if(list.options[list.selectedIndex].value.indexOf('§')==0){
			return;
		}
	}
	document.forms[0].act.value='cerca';
	document.forms[0].submit();
}

<% } %>
</script>
</head>
<body>
<form action="#" method="post">
  <input type="hidden" name="titolaritaId" value="<%= request.getParameter("titolaritaId") %>" />
  <input type="hidden" name="act" />
  <table border="1" cellspacing="15">
    <tr> 
      <td width="360" class="titolo">
        <input name="tipi_titolare" value="1"
			type="radio" checked="checked" onclick="change(1);" />
        Dipartimento CNR:</td>
<td width="153" rowspan="7" id="enti" ><select name="combo" size="12" style="visibility:hidden" >
                  </select></td>
    </tr>
    <tr> 
      <td bgcolor="#999999"><select name="dipartimenti" id="dipartimenti">
          <%
				Dipartimento dip;
				int x = 0;
				while (i.hasNext()) {
					dip = (Dipartimento) i.next();
					if (dip != null)
						out.print("\t<option value='" + (x++) + "'>"
						+ dip.getSigla() + " - " + dip.getDescrizione()
						+ "</option>\n");
					//cs=cs.concat("\""+ ist.getIstitutoSigla()+" - "+ist.getNome().replaceAll("\"","\\\\\"")+"\"");
				}
			%>
        </select></td>
    </tr>
    <tr> 
      <td class="titolo">
        <input name="tipi_titolare" value="3" type="radio"
			onclick="change(3);" />
        <bean:message key="labels.inventore00" bundle="${bundle}" /> del trovato:</td>
    </tr>
    <tr> 
      <td bgcolor="#999999"><select name="inventori" id="inventori"
			style="visibility:hidden">
          <%
				while (j.hasNext()) {
					String inv = (String)j.next();
					if (inv != null)
						out.print("\t<option value='" + inv + "'>" + inv.substring(inv.indexOf("|")+1)
						+ "</option>\n");
				}
			%>
        </select></td>
    </tr>
    <tr> 
      <td class="titolo">
        <input name="tipi_titolare" value="4" type="radio"
			onclick="change(4);" />
        Ente esterno (chiave di ricerca):</td>
    </tr>
    <tr> 
      <td bgcolor="#999999"><input type="text" name="filter" onchange="doFilter(this.value);" onkeyup="doFilter(this.value);" 
			size="60" style="visibility:hidden" /></td>
    </tr>
    <tr> 
      <td align="center"> <input type="button" value="Seleziona" onclick="doSubmit()" /> &nbsp;&nbsp;&nbsp; 
        <input type="button" value="Annulla" onclick='window.close();' /> </td>
    </tr>
  </table>
</form>
<div id="errors" class="errorCloud" onclick="fade('errors',false);"><p align='right'><img src='images/close.gif' /></p><b>Attenzione</b>:<br /><bean:message key="labels.inventore00" bundle="${bundle}" /> selezionato non ancora memorizzato negli archivi.<br />Solo a seguito del salvataggio del presente trovato sar&agrave; possibile selezionarlo come titolare.<br/></div>
</body>
</html>
