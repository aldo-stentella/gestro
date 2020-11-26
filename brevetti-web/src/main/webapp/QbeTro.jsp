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
<title>Brevetti CNR - Gestione trovati</title>
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
	sortSelect(document.forms[0].istituto);
}

function goTab(ntab) {
	document.forms[0].nextab.value=ntab;
	document.forms[0].method='GET';
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
        <div class="titolo"><!-- InstanceBeginEditable name="title" -->Gestione dei Trovati<!-- InstanceEndEditable --></div><br/>
        <!-- InstanceBeginEditable name="content" -->
	        <table width="100%" border="0" cellpadding="0" cellspacing="0">
	          <tr>
	            <td>
		    	<bean:tabs />
		      	</td>
	          </tr>
	          <tr> 
	            <td id="area" valign="top">
					<html:form method="post" action="GesTro.do">
	            	<html:hidden property="nextab"  />
	            	<html:hidden property="mode"  />
                <table width="100%" border="0" cellpadding="5">
                  
                  <tr> 
                    <td align="right">Titolo:</td>
                    <td width="27%" align="left"><html:text property="titolo" size="40" styleClass="special" /></td>
                    <td width="12%" align="left"><html:radio property="linguat" value="1" />
                      <img src="images/italy0a.gif" alt="Italiano" name="flag" width="20" height="12" id="flag" /><br />
                      <html:radio property="linguat" value="2" />
                    <img src="images/uk1b.gif" alt="Italiano" name="flag" width="20" height="12" id="flag2" /></td>
                    <td align="right">Tipo:</td>
                    <td width="36%" align="left"><html:select property="tipoTrovato">
    					<html:options collection="tipi" property="value" labelProperty="label" />
				    </html:select></td>
                  </tr>
                  <tr>
                    <td align="right">Parola chiave:</td>
                    <td align="left"><html:text property="parolaChiave" size="40" styleClass="special" /></td>
                    <td align="left"><html:radio property="linguak"  value="1" />
                      <img src="images/italy0a.gif" alt="Italiano" name="flag" width="20" height="12" id="flag3" /><br />
                      <html:radio property="linguak"  value="2" />
                      <img src="images/uk1b.gif" alt="Italiano" name="flag" width="20" height="12" id="flag4" /></td>
                    <td align="right">Cognome inventore:</td>
                    <td align="left"><html:text property="inventore" size="35" styleClass="special" /></td>
                  </tr>
                  <tr>
                    <td align="right">Studio Brevettuale:</td>
                    <td colspan="2" align="left"><html:select property="studio" style="font-family: Arial, Helvetica, sans-serif; font-size: 11px;width: 350px;">
  			  		<html:options collection="studi" property="value" labelProperty="label" />
    				</html:select></td>
                    <td align="right">Ente esterno co-titolare:</td>
                    <td align="left"><html:text property="enteEsterno" size="35" styleClass="special" /></td>
                  </tr>
                  <tr> 
                    <td align="right">Data deposito:</td>
                    <td colspan="2" align="left">da <html:text property="dataDeposito1" size="10" /><img src="images/cal04c.gif" width="17" align="top" height="18" class="imaction" onclick="showCal('dataDeposito1');" />
                    a <html:text property="dataDeposito2" size="10" /><img src="images/cal04c.gif" width="17" align="top" height="18" class="imaction" onclick="showCal('dataDeposito2');" /> (gg/mm/aaaa)</td>
                    <td align="right">Data rilascio: </td>
                    <td align="left">da <html:text property="dataRilascio1" size="10" /><img src="images/cal04c.gif" width="17" align="top" height="18" class="imaction" onclick="showCal('dataRilascio1');" /> a <html:text property="dataRilascio2" size="10" /><img src="images/cal04c.gif" width="17" align="top" height="18" class="imaction" onclick="showCal('dataRilascio2');" /> (gg/mm/aaaa)</td>
                  </tr>
                  <tr>
                    <td width="12%" align="right">N&deg; deposito:</td>
                    <td colspan="2" align="left"><html:text property="numDeposito" size="20" styleClass="special" /></td>
                    <td width="13%" align="right">Rilascio N&deg;:</td>
                    <td align="left"><html:text property="numRilascio" size="20" /></td>
                  </tr>
                  <tr>
                    <td align="right">Istituto richiedente:</td>
                    <td colspan="2" align="left"><html:select property="istituto" style="font-family: Arial, Helvetica, sans-serif; font-size: 11px;width: 350px;">
    					<html:options collection="isti" property="value" labelProperty="label" />
					</html:select></td>
                    <td align="right">Data abbandono:</td>
                    <td align="left">da
                      <html:text property="dataAbbandono1" size="10"  />
                      <img src="images/cal04c.gif" width="17" align="top" height="18" class="imaction" onclick="showCal('dataAbbandono1');" /> a
                      <html:text property="dataAbbandono2" size="10"  />
                    <img src="images/cal04c.gif" width="17" align="top" height="18" class="imaction" onclick="showCal('dataAbbandono2');" /> (gg/mm/aaaa)</td>
                  </tr>
                  <tr>
                    <td align="right">Dipartimento</td>
                    <td colspan="2" align="left">
                        <html:select property="dipartimento">
    					<html:options collection="dips" property="value" labelProperty="label" />
				    </html:select></td>
                    <td align="right">Stato abbandono:</td>
                    <td align="left"><html:select property="abbandono">
                      <html:options collection="abba" property="value" labelProperty="label" />
                    </html:select></td>
                  </tr>
                  <tr>
                    <td align="right">Referente<br/>Titolo</td>
                    <td colspan="2" align="left"><html:select property="utentiId">
                    	<html:options collection="referenti" property="value" labelProperty="label" />
                    	</html:select></td>
                    <td align="right">D.lgs. n.30/2005:</td>
                    <td align="left"><html:select property="cessioneDiritti">
                			<html:options collection="diritti" property="value" labelProperty="label" />
                		</html:select>
                	</td>
                  </tr>
                  <tr align="right"> 
                    <td colspan="4" align="left" valign="bottom">(i campi di colore verde indicano una ricerca per chiave  ridotta)</td>
                    <td valign="middle"><input type="submit" name="Submit" value="Ricerca" /></td>
                  </tr>
                </table>
	 			  </html:form>
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
