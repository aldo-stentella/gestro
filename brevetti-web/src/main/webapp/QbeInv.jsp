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
<script type="text/javascript" language="JavaScript1.4">
var openSession = false;
<%
	String cs = (String)request.getAttribute("cs");	//"'Nessun elemento trovato (0)'";
	String action = ""+request.getAttribute("action");
	String error = (String)request.getAttribute("error");
	if(action.startsWith("scegli")) {
%>
function inizialize() {
	popolate();
}

var arr = new Array(<%=cs%>);

function popolate(){
	for(var i=0; i < arr.length; i++){
		document.form.combo.options[i]=new Option(arr[i].substring(0,arr[i].indexOf(" (")),arr[i]);
	}
	document.form.combo.style.visibility="visible";
	document.form.Submit3.style.visibility="visible";
	document.form.Cancel.style.visibility="visible";
	GetObj("qbe").style.display = 'none';
}
<%
	} else if(action.startsWith("inizia")) {
%>
function inizialize() {
	//do nothing
}

<%
	}
%>

function doSubmit(tipo) {
	if((tipo==1 && document.form.matricola.value.length>0) || tipo==2 ){
		document.forms[0].tipi_inventore.value=tipo;
		document.form.act.value='cerca';
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
        <div class="titolo"><!-- InstanceBeginEditable name="title" -->Gestione Inventori<!-- InstanceEndEditable --></div><br/>
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
					<form name="form" method="post" action="GesInv.do">
	            	<input type="hidden" name="tipi_inventore" />
	            	<input type="hidden" name="act" />
                <table width="100%" border="0" cellpadding="5" id="qbe" >
                  <tr>
                    <td width="12%" align="right">&nbsp;</td>
                    <td width="39%" align="left">Dipendente CNR</td>
                    <td width="13%" align="right">&nbsp;</td>
                    <td width="36%" align="left">Inventore esterno</td>
                  </tr>
                  <tr>
                    <td align="right">Matricola:</td>
                    <td align="left"><input type="text" name="matricola" size="20"  /></td>
                    <td align="right">Cognome:</td>
                    <td align="left"><input type="text" name="esterni" size="40" class="special" /></td>
                  </tr>
                  <tr>
                    <td align="right">&nbsp;</td>
                    <td align="left">&nbsp;</td>
                    <td colspan="2" align="left">filtra inventori disabilitati 
                      <input type="checkbox" name="flagAttivi" value="1" /></td>
                  </tr>
                  <tr>
                    <td align="right">&nbsp;</td>
                    <td align="left"><input type="button" name="Submit" value="Ricerca" onclick="doSubmit(1)" /></td>
                    <td align="right">&nbsp;</td>
                    <td align="left"><input type="button" name="Submit2" value="Ricerca" onclick="doSubmit(2)" /></td>
                  </tr>
                  <tr>
                    <td colspan="4" align="right">&nbsp;</td>
                  </tr>
                  <tr> 
                    <td colspan="4" align="center" valign="bottom">(i campi di colore verde indicano una ricerca per chiave  ridotta)</td>
                  </tr>
                  <tr> 
                    <td colspan="4" align="center" class="errorMsg"><%=request.getAttribute("error")!=null?request.getAttribute("error"):"&nbsp;" %></td>
                  </tr>

                  <tr> 
                    <td colspan="4" align="center" valign="bottom">
					</td>
                  </tr>
                </table>
                <select name="combo" size="18" style="visibility:hidden; width:700px;" ></select><br />
                <input type="submit" name="Submit3" value="Apri" style="visibility:hidden" onclick="document.form.act.value='seleziona';" /> &nbsp;
                <input type="button" name="Cancel" value="Nuova ricerca" style="visibility:hidden" onclick="window.location.href='#';" />
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
