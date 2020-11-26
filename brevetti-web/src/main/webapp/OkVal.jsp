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
<title>Azioni di Valorizzazione</title>
<!-- InstanceEndEditable -->
<c:set var="sessioneUtente" value="${sessionScope.sessioneUtente}" />
<c:set var="dipartimento" value="${sessionScope.dipartimento}" />
<c:set var="MultiDipartimento" value="${sessionScope.multiDipartimento}" />

<!-- InstanceBeginEditable name="head" -->

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
        <div class="titolo"><!-- InstanceBeginEditable name="title" -->Valorizzazione<!-- InstanceEndEditable --></div><br/>
        <!-- InstanceBeginEditable name="content" -->
<html:form action="Valorizzazione.do">
<html:hidden property="id" />
  <table width="100%" border="0" align="center" cellpadding="5" class="area">
  <tr>
    <td width="162">Azione</td>
    <td width="732" align="left">
    	<html:text property="tipoValorizzazioneNome" readonly="true" size="40" />
    </td>
  </tr>
  <tr>
    <td>Nome progetto</td>
    <td align="left"><html:text property="nomeProgetto" readonly="true" size="40" /></td>
  </tr>
  <tr>
    <td>Referente</td>
    <td align="left"><html:text property="referente" readonly="true" size="40" /></td>
  </tr>
  <tr>
    <td>Data inizio</td>
    <td align="left"><html:text property="dataInizio" readonly="true" size="10" /></td>
  </tr>
  <tr>
    <td>Data fine</td>
    <td align="left"><html:text property="dataFine" readonly="true" size="10" /></td>
  </tr>
  <tr>
    <td colspan="2"><hr /></td>
    </tr>
  <tr>
    <td>Azienda</td>
    <td align="left">&nbsp;</td>
  </tr>
  <tr>
    <td>Nome</td>
    <td align="left"><html:text property="aziendaNome" readonly="true" size="40" /></td>
  </tr>
  <tr>
    <td>Citt&agrave;</td>
    <td align="left"><html:text property="aziendaCitta" readonly="true" size="30" /></td>
  </tr>
  <tr style="visibility: hidden;">
    <td>Regione / Nazione</td>
    <td align="left"><html:text property="aziendaRegione" readonly="true" size="30" /></td>
  </tr>
  <tr>
    <td colspan="2"><hr /></td>
    </tr>
  <tr>
    <td>Note</td>
    <td align="left"><html:textarea property="note" rows="5" cols="50" readonly="true" /></td>
  </tr>
  <tr>
    <td colspan="2" align="center">Valorizzazione scritta con successo.</td>
  </tr>
  <tr>
    <td colspan="2" align="center"><input type="button" value="Chiudi" onclick="window.location='home.do';" /></td>
    </tr>
  </table>
</html:form>
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
