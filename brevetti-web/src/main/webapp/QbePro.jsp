<%@ page language="java" %>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/generic2.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<%@page import="it.cnr.brevetti.util.UtilityFunctions"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.cnr.brevetti.ejb.entities.Utente"%>
<%@page import="it.cnr.brevetti.util.ApplicationProperties"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<script type="text/javascript" src="scripts/standard.js?<%=Version.getVersion()%>"></script>
<!-- InstanceBeginEditable name="doctitle" -->
<title>Brevetti CNR - Gestione Invention Disclosure</title>
<!-- InstanceEndEditable -->
<c:set var="sessioneUtente" value="${sessionScope.sessioneUtente}" />
<c:set var="dipartimento" value="${sessionScope.dipartimento}" />
<c:set var="MultiDipartimento" value="${sessionScope.multiDipartimento}" />

<!-- InstanceBeginEditable name="head" -->
<script language="javascript" src="scripts/cal2.js?<%=Version.getVersion()%>">
/*
Xin's Popup calendar script-  Xin Yang (http://www.yxscripts.com/)
Script featured on/available at http://www.dynamicdrive.com/
This notice must stay intact for use
*/
</script>
<script language="javascript" src="scripts/cal_conf2.js?<%=Version.getVersion()%>"></script>
<script type="text/javascript" language="JavaScript1.4">
var openSession = false;

function inizialize() {
	//do nothing...
}
function doSubmit() {
	document.forms[0].submit();
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
        <div class="titolo"><!-- InstanceBeginEditable name="title" -->Gestione delle Invention Disclosure<!-- InstanceEndEditable --></div><br/>
        <!-- InstanceBeginEditable name="content" -->
	        <table width="100%" border="0" cellpadding="0" cellspacing="0">
	          <tr>
	            <td>
		    	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr id="tabs">
                  <td id="tabspacer" width="5"><img src="images/spacer.gif" width="5" height="1" /></td>
                  <td id="tabon"> Ricerca </td>
                  <td id="tabspacer">&nbsp;</td>
                </tr>
            	</table>
		      	</td>
	          </tr>
	          <tr> 
	            <td id="area" valign="top">
	            	<form name="iform" method="post" action="GesPro.do">
	            	<input type="hidden" name="nextab"  />
	            	<input type="hidden" name="role" value="int"  />
	            	<table cellpadding="8" width="100%">
	            		<tr>
	            			<td align="right">Identificatore:</td>
	            			<td align="left"><input type="text" name="idProposta" size="8" /></td>
	            		</tr>
	            		<tr>
		            		<td width="30%" align="right">Data di trasmissione:</td>
		            		<td align="left">da <input type="text" name="dadata" size="10" />
		            		<img src="images/cal04c.gif" align="top" class="imaction" onclick="showCal('dataTrasm1');" />
		            		 a <input type="text" name="adata" size="10" value="<%= UtilityFunctions.itForm.format(new Date()) %>" />
		            		<img src="images/cal04c.gif" align="top" class="imaction" onclick="showCal('dataTrasm2');" /> (gg/mm/aaaa)
		            		 </td>
	            		</tr>
	            		<tr>
		            		<td align="right">Tipologia trovato:</td>
		            		<td align="left"><select name="tipiTrovatoId" >
		            			<option value="0" selected="selected">- - - Selezionare una tipologia - - -</option>
		            				    <option value="1">Brevetto per invenzione industriale</option>
								        <option value="3">Diritto d'Autore</option>
								        <option value="4">Marchio</option>
								        <option value="5">Modello di Utilit&agrave;</option>
								        <option value="6">Nuova Variet&agrave; Vegetale</option>
								        <option value="7">Software</option>
								        <option value="8">Know-How</option>
		            		</select></td>
	            		</tr>
	            		<tr>
		            		<td align="right">Utente LDAP:</td>
		            		<td align="left"><input type="text" name="utenteLdap" size="30" /></td>
	            		</tr>
	            		<tr>
		            		<td align="right">Stato:</td>
		            		<td align="left"><select name="stato" >
		            			<option value="0" selected="selected">- - - Selezionare uno stato - - -</option>
								<option value="1">Trasmessa       </option>
								<option value="2">In esame        </option>
								<option value="3">Da integrare    </option>
								<option value="4">Brevettabile    </option>
								<option value="5">Non brevettabile</option>
								<option value="6">Non esaminabile </option>
								<option value="7">Depositata      </option>
		            		</select></td>
	            		</tr>
	            		<tr>
		            		<td align="right">Referente Titolo:</td>
		            		<td align="left"><select name="utente" >
		            			<option value="" selected="selected">- - - Selezionare un Referente Titolo - - -</option>
<%
								ArrayList<Utente>utenti = (ArrayList<Utente>)request.getAttribute("referenti");
								for(Utente utente :  utenti){
%>
								<option value="<%=utente.getUtenteId()%>"><%= utente.getNome()+" "+utente.getCognome() %></option>
<%
								}
%>
		            		</select></td>
	            		</tr>
	            		<tr>
	            		<td align="center" colspan="2">
	            				<input type="button" value="Ricerca" onclick="doSubmit();"/>
						</td>
						</tr>
             		</table>
					</form>
			    </td>
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
