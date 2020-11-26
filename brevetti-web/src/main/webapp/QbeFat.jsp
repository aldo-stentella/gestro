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
<title>Brevetti CNR - Gestione fatture</title>
<!-- InstanceEndEditable -->
<c:set var="sessioneUtente" value="${sessionScope.sessioneUtente}" />
<c:set var="dipartimento" value="${sessionScope.dipartimento}" />
<c:set var="MultiDipartimento" value="${sessionScope.multiDipartimento}" />

<!-- InstanceBeginEditable name="head" -->
<style type="text/css">
#errors {
	top: 300px;
	left: 400px;
    position: absolute;
    width: 400px;
    z-index: 60;
    display: none;
}
</style>
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
	var rifs = new Array();
	var tmp = new Array();
	function inizialize() {
		rifs = document.forms[0].nsrif.options;
		for(var i=0; i<rifs.length; i++){
			tmp[i]=rifs[i];
		}
	}

	function popolate(){
		for(var i=0; i<tmp.length; i++){
			rifs[i]=tmp[i];
		}
	}
	
	function doFilter(patt) {
		var el = "";
		popolate();
		var eln = rifs.length;
		for(var i=0; i<eln; i++){
			el = rifs[i].text.toUpperCase();
			if(el.indexOf(patt.toUpperCase())==-1){
				if(document.all){
					rifs.remove(i);
				} else {
					rifs[i] = null;
				}
				i--;
				eln--;
			}
		}
		if(eln==0) rifs[0]=new Option('Nessun elemento trovato','§');
	}

	function do_submit(){
		var ndx = document.forms[0].nsrif.selectedIndex;
		if(ndx<0 || document.forms[0].nsrif.options[ndx].value=='§')
			fade('errors',true);
		else
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
        <div class="titolo"><!-- InstanceBeginEditable name="title" -->Gestione delle Fatture Passive<!-- InstanceEndEditable --></div><br/>
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
					<html:form method="post" action="GesFat.do">
	            	<html:hidden property="nextab"  />
	            	<html:hidden property="mode"  />
                    <br /><br />
                <table width="96%" border="0" cellpadding="5">
                  <tr>
                    <td width="12%" align="right">NSRIF trovato:</td>
                    <td align="left" colspan="3">
                    	<html:select property="nsrif" size="8">
						    <html:options collection="trovati" property="value" labelProperty="label" />
					    </html:select><br /><input type="text" name="filter" onchange="doFilter(this.value);" onkeyup="doFilter(this.value);" size="30" /> (filtro)
					</td>
                  </tr>
                  <tr>
                    <td align="right">Studio Brevettuale:</td>
                    <td width="42%" align="left"><html:select property="studioBrevettualeId" style="font-family: Arial, Helvetica, sans-serif; font-size: 11px;width: 350px;">
  			  		<html:options collection="studi" property="value" labelProperty="label" />
    				</html:select></td>
                    <td align="right">&nbsp;</td>
                    <td width="32%" align="left"><html:text property="protocollo" size="20" style="display:none;" /></td>
                  </tr>
                  <tr> 
                    <td colspan="4"><img src="images/spacer.gif" width="3" height="3" /></td>
                  </tr>
                  <tr> 
                    <td align="right">Data fattura:</td>
                    <td align="left">da <html:text property="_dataFattura1" size="10" /> 
                    <img src="images/cal04c.gif" width="17" align="top" height="18" class="imaction" onclick="showCal('_dataFattura1');" />
                    a <html:text property="_dataFattura2" size="10" /> 
                    <img src="images/cal04c.gif" width="17" align="top" height="18" class="imaction" onclick="showCal('_dataFattura2');" /> (gg/mm/aaaa)</td>
                    <td align="right">N&deg; fattura:</td>
                    <td align="left"><html:text property="numFattura" size="20" /></td>
                  </tr>
                  <tr><td colspan="4"><html:text property="impegnoObbligazione" size="20" styleClass="special" style="display:none;" /></td>
                  </tr>
                  <tr>
                    <td colspan="4"><img src="images/spacer.gif" width="3" height="3" /></td>
                  </tr>
                  <tr>
                    <td colspan="4"><img src="images/spacer.gif" width="3" height="3" /></td>
                  </tr>
                  <tr align="right"> 
                    <td colspan="3" align="left" valign="bottom">(i campi di colore verde indicano una ricerca per chiave  ridotta)</td>
                    <td valign="middle"><input type="button" name="Submit" value="Ricerca" onclick="do_submit();" /></td>
                  </tr>
                </table>
	 			  </html:form>
			     </td>
	          </tr>
	     	</table>
	     	<div id="errors" class="errorCloud" onclick="fade('errors',false);"><p align='right'><img src='images/close.gif' /></p><b>Attenzione</b>:<ul><li>E' obbligatorio selezionare un trovato</li></ul><br/></div>
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
