<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="it.cnr.brevetti.util.UtilityFunctions"%>
<%@page import="it.cnr.brevetti.ejb.entities.DocumentoInfo"%>
<%@page import="java.util.List"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/generic2.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<%@page import="it.cnr.brevetti.util.ApplicationProperties"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<script type="text/javascript" src="scripts/standard.js?<%=Version.getVersion()%>"></script>
<!-- InstanceBeginEditable name="doctitle" -->
<title>Verbali di struttura</title>
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

<script type="text/javascript">
	var openSession = false;
	function inizialize() {
		//do nothing
	}
	function doSubmit() {
		if(document.forms[0].dataVerbale.value==''){
			alert("E' obbligatorio specificare una data");
		} else if(document.forms[0].update.value==0){
			alert("E' obbligatorio caricare il documento di verbale");
		} else {
			document.forms[0].submit();
		}
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
        <div class="titolo"><!-- InstanceBeginEditable name="title" -->Inserimento Verbali di struttura<!-- InstanceEndEditable --></div><br/>
        <!-- InstanceBeginEditable name="content" -->
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr > 
                  <td id="tabspacer" width="219"><img src="images/spacer.gif" width="5" height="1" /></td>
                  <td width="712" id="tabspacer">&nbsp;</td>
                </tr>
              </table></td>
          </tr>
          <tr>
            <td align="center" valign="top" id="area">
            <c:if test="${not empty act}">
           		<br /><br /><br />
            	<p align="center" style="color: green;">Verbale caricato correttamente.</p>
            	<p align="center"><input type="button" value="Chiudi" onclick="window.location='home.do';" /></p>
			</c:if>
			<c:if test="${not empty mess}">
		        <br /><br /><span class="errorCloud"><c:out value="${mess}"/></span><br /><br /><br />
			</c:if>
           	<c:if test="${empty act}">
           		<br /><br /><br />
			    <form enctype="multipart/form-data" method="post" name="iform" action="AddVerb.do">
					<table>
					  <tr>
					    <td>Data Verbale:</td>
					    <td><input type="text" name="dataVerbale" size="10" />
					    	<img src="images/cal04c.gif" align="top" class="imaction" onclick="showCal('dataVerbale');" /> (gg/mm/aaaa)
					     </td>
					  </tr>
					  <tr>
					    <td width="122">Documento associato:</td>
					    <td width="335">
					      <input type="file" name="fileField" onchange="document.forms[0].update.value=1;" />
					      <input type="hidden" name="update" value="0" />
					    </td>
					  </tr>
					  <tr>
					    <td colspan="2">&nbsp;</td>
					  </tr>
					  <tr><td colspan="2" align="center">
					      <input type="button" value="Inserisci" onclick="doSubmit();" /> &nbsp;&nbsp; <input type="button" value="Annulla" onclick="window.location='home.do';" /> </td></tr>
					</table>
				</form>
           	</c:if>
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
