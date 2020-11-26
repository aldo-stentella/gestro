<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.SortedMap"%>
<%@page import="it.cnr.brevetti.ejb.entities.Classificazione"%>
<%@page import="it.cnr.brevetti.ejb.entities.Dipartimento"%>
<%@page import="it.cnr.brevetti.ejb.entities.Trovato"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/noMenu.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<%@page import="it.cnr.brevetti.util.ApplicationProperties"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<!-- InstanceBeginEditable name="doctitle" -->
<title>Documento senza titolo</title>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="head" -->
<style type="text/css">
<!--
.odd-row {
	background-color: #FFE7B3;
	clip: rect(auto,auto,auto,auto);
	padding: 5px;
	margin: 5px;
}
.odd-row a{
	text-decoration:none;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #600000;
	font-size: 11px;
}
.even-row {
	background-color: #FEF1B4;
	clip: rect(auto,auto,auto,auto);
	padding: 5px;
	margin: 5px;
	color: #FDE98A;
}
.even-row a{
	text-decoration:none;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #404F90;
	font-size: 11px;
}
-->
</style>
<script language="JavaScript">
<!--
<%
SortedMap ser = (SortedMap)request.getAttribute("ser");
	boolean italian = true;
%>

function openDetail(nsrif, dep) {
	var ref = window.open('<%= italian?"InfoCatalogo.do":"InfoTrovatoEn.jsp" %>?nsrif='+nsrif+'&dip=0','popup_trovato','height=980,width=980,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
	ref.focus();
}
function abilita(obj, ide) {
	var riga = GetObj(ide);
	if(riga.disabled){
		riga.disabled=false;
		obj.src="images/3dset2n.gif"
	} else {
		riga.disabled=true;
		obj.src="images/3dset1n.gif"
	}
}

function inizialize(){
	//nulla
}

function emptylist(){
	document.forms[0].subm.style.visibility='hidden'
	document.forms[0].renew.value='Nuova ricerca'

}
//-->
</script>
<!-- InstanceEndEditable -->
<script type="text/javascript" src="scripts/standard.js?<%=Version.getVersion()%>"></script>
</head>
<body onload="inizialize()">
<div class="center">
  <table width="100%" class="pagetable">
    <tr>
      <td id="header" align="left"><img src="images/cnr_logo.gif" width="900" height="100" border="0" /></td>
    </tr>
    <tr>
      <td width="100%" class="pagetable">
        <div class="titolo"><!-- InstanceBeginEditable name="title" -->Motore di ricerca trovati<!-- InstanceEndEditable --></div>
        <br/>
        <!-- InstanceBeginEditable name="content" -->
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr id="tabs">
                  <td id="tabspacer" width="5"><img src="images/spacer.gif" width="5" height="1" /></td>
                  <td id="tabon"> Ricerca e tuning </td>
                  <td id="tabspacer">&nbsp;</td>
                </tr>
            </table></td>
          </tr>
          <tr>
            <td id="area">
	    	<form action="ClickAnalysis.do" method="post">
        <table width="100%" border="0" cellpadding="10" cellspacing="0">
	        <tr>
	          <td width="90%">Risultati della ricerca: </td>
	          <td width="10%">
	          	<input type="submit" name="subm" value="Conferma risultati" />
	          	<input type="button" name="renew" value="Esci" onclick="window.location='Tuning.do'" />	          	</td>
            </tr>
	        <tr>
	        <td colspan="2">
	        		<table cellspacing="10" width="100%">
<%	int q = 1;
	if(ser!=null && ser.size()>0){
		%>
		<tr><td colspan="2">(<%=ser.size() %> brevetti individuati)
			<input type="hidden" name="keywords" value="<%= request.getAttribute("keywords")%>" />
		</td></tr>

<%
		for(Iterator i = ser.keySet().iterator(); i.hasNext();){
			Double chiave = (Double)i.next();
			Trovato t = (Trovato)ser.get(chiave);
%>
<tr>
<td onclick='openDetail(<%=t.getNsrif() %>);' class='<%=(q==0)?"even-row":"odd-row" %>'>
			<input type="hidden" name="item" id="<%= t.getNsrif() %>" value="<%= request.getAttribute("keywords")+"|"+t.getNsrif()+"|"+chiave %>" disabled="disabled" />
	<a href="#">
		<%= italian?t.getTitolo():t.getTitoloInglese() %><br />
	<img src="images/wedit.gif" border="0" align="middle" /> dettagli	</a></td>
<td>
<div title="<%= chiave %>% - powered by Terrier" style="width: 70px; height: 10px; border: 1px solid black; background-color: #B2CDE0;">
     <div style="height: 6px; width: <%= chiave %>%; background-color: green; border: 1px solid gray; padding:1px;"><img height="100%" width="100%" src="images/spacer.gif" alt="<%= chiave %>% - powered by Terrier" /></div>
</div>
<img src="images/3dset1n.gif" alt="Conferma risultato" vspace="5" onclick="abilita(this, '<%= t.getNsrif() %>');"/></td>
</tr>
<%
			q = 1 - q;
		} 
	}else {
%>	 
				<tr><td>La ricerca non ha prodotto nessun risultato con i criteri specificati. <img src="images/spacer.gif" width="1" height="1" onLoad="emptylist();" /></td></tr>
<%
	}
%>       
	        	</table>
	        		        </td>
	      </tr>
	    </table>
	   	</form>
	  </td>
	</tr>
  </table>
<!-- InstanceEndEditable -->      </td>
    </tr>
    <tr>
      <td id="footer">&copy;2007-<%=ApplicationProperties.getInstance().getCopyright()%> <a href="http://www.cnr.it" target="_blank">Consiglio Nazionale delle Ricerche</a> | <a href="http://www.si.cnr.it" target="_blank"><%=ApplicationProperties.getInstance().getDept()%></a> | <a href="javascript:openHelp('staff.html')">Staff</a></td>
    </tr>
  </table>
</div>
</body>
<!-- InstanceEnd --></html>
