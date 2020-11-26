<%@page import="it.cnr.brevetti.util.UtilityFunctions"%>
<%@page import="it.cnr.brevetti.ejb.entities.InventionDisclosure"%>
<%@ page language="java" %>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/generic2.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<%@page import="it.cnr.brevetti.util.ApplicationProperties"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<script type="text/javascript" src="scripts/standard.js?<%=Version.getVersion()%>"></script>
<!-- InstanceBeginEditable name="doctitle" -->
<title>Invention Disclosure</title>
<!-- InstanceEndEditable -->
<c:set var="sessioneUtente" value="${sessionScope.sessioneUtente}" />
<c:set var="dipartimento" value="${sessionScope.dipartimento}" />
<c:set var="MultiDipartimento" value="${sessionScope.multiDipartimento}" />

<!-- InstanceBeginEditable name="head" -->
<script language="JavaScript" type="text/JavaScript">
	var openSession = false;

	function inizialize(){
		//do nothing
	}
	function apriTrovato(nsrif){
		document.forms['gestro'].nsrif.value = nsrif;
		document.forms['gestro'].submit();
	}
</script>

<!-- InstanceEndEditable -->

</head>
<body onload="inizialize()">
<div class="center">
  <table width="100%" class="pagetable">
    <tr>
      <td colspan="2" id="header" align="left"><img src="images/cnr_logo.gif" width="900" height="100" border="0" />
      	<span class="highlighted"><%=application.getInitParameter("mode") %></span></td>
    </tr>
    <tr>
      <td width="1%" valign="top">
      	<div id="login">utente: 
	      	<b><c:out value="${sessioneUtente.utente.nomeUtente}" /></b>
      	</div>
        <div class="departs">[<c:out value="${dipartimento.descrizione}" />]
        	<c:if test="${MultiDipartimento}"><br />
	    		<a href="javascript:abandon('SwitchDip.do')">Cambia dipartimento</a>
			</c:if>
        </div>
      <table width="100%" id="menu">
        <tr>
          <td class="menuGroups"><ul>
              <li><a href="javascript:openHelp('#')">Aiuto</a></li>
              <script type="text/javascript">
			  var acerchiata = 64;
              document.write('<li><a href="mailto:oil.support'+String.fromCharCode(acerchiata)+'amministrazione.cnr.it?subject=[segnalazione BREVETTI]">Contattaci</a></li>\n');
              </script>
              <li><a href="javascript:abandon('logout.do')">Uscita</a></li>
            </ul></td>
        </tr>
        <%@ include file="/WEB-INF/jsp/layout/menu.jsp"%>
        <tr>
          <td><img src="images/spacer.gif" width="140" height="1" /></td>
        </tr>        
      </table></td>
      <td width="100%" class="pagetable">
        <div class="titolo"><!-- InstanceBeginEditable name="title" -->Invention Disclosure<!-- InstanceEndEditable --></div><br/>
        <!-- InstanceBeginEditable name="content" -->
        <%
	String[] tipi = (String[])request.getAttribute("tipi");
	String[] stati = (String[])request.getAttribute("stati");
	String[] stili = (String[])request.getAttribute("stili");
	int[] adiacenze = (int[])request.getAttribute("adiacenze");
	InventionDisclosure id = (InventionDisclosure)request.getAttribute("indi");
%>
        <table width="100%" border="0" cellspacing="5" class="area">
          <tr>
            <td width="20%" align="right" class="label">Identificatore:</td>
            <td colspan="2" align="left"><%= id.getId() %></td>
          </tr>
          <tr>
            <td width="20%" align="right" class="label">Richiedente:</td>
            <td colspan="2" align="left"><%= id.getUtenteLdap() %></td>
          </tr>
          <tr>
            <td align="right" class="label">Data trasmissione:</td>
            <td colspan="2" align="left"><%= UtilityFunctions.itForm.format(id.getDataTrasmissione()) %></td>
          </tr>
          <tr>
            <td align="right" class="label">Tipologia trovato:</td>
            <td colspan="2" align="left"><%= tipi[id.getTipiTrovatoId()] %></td>
          </tr>
          <tr>
            <td align="right" class="label">Titolo proposto:</td>
            <td colspan="2" align="left"><%= id.getTitolo() %></td>
          </tr>
          <tr>
            <td align="right" class="label">Stato:</td>
            <td colspan="2" align="left"><img src="images/<%= id.getStato() %>dset1n.gif" align="top" />&nbsp;<%= stati[id.getStato()] %>
<% 
	if(id.getNsrif()!=null){
%>
				<span class='buttonize' onclick='apriTrovato(<%=id.getNsrif()%>);'>NSRIF: <%=id.getNsrif()%></span>
				<form name="gestro" method="post" action="GesTro.do">
					<input type="hidden" name="mode" value="1" />
					<input type="hidden" name="nsrif" />
				</form>
<%
	}
%>
            </td>
          </tr>
		  <tr>
		    <td colspan="2" align="center">Stato aggiornato con successo.</td>
		  </tr>
		  <tr>
		    <td colspan="2" align="center"><input type="button" value="Chiudi" onclick="window.location='home.do';" /></td>
		  </tr>
  </table>
<!-- InstanceEndEditable -->
        </td>
    </tr>
    <tr>
      <td id="footer" colspan="2">&copy;2007-<%=ApplicationProperties.getInstance().getCopyright()%> <a href="http://www.cnr.it" target="_blank">Consiglio Nazionale delle Ricerche</a> | <a href="http://www.si.cnr.it" target="_blank"><%=ApplicationProperties.getInstance().getDept()%></a> | <a href="javascript:openHelp('staff.html')">Staff</a></td>
    </tr>
  </table>
</div>
</body>
<!-- InstanceEnd --></html>
