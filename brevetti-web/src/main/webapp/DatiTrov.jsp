<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="it.cnr.brevetti.trovati.actionForms.TrovatoForm"%>
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
<c:set var="tti" value="${sessionScope.datiTrovato.tipiTrovatoId}" />
<c:set var="bundle" value="${sessionScope.bundleArray[tti]}" />
<style type="text/css">
#errors {
	top: 250px;
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
<script type="text/javascript" language="javascript1.4">
var language='it';
var openSession = true;
var currentNote;
var param3='';

function apriNota(field){
	currentNote = field;
	var ref = window.open('AddNote.do','popup_trov','height=500,width=800,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
	ref.focus();
}

function aggiornaNota(){
    currentNote.value=param3;
	self.document.forms[0].submit();
}
function goTab(ntab) {
	document.forms[0].nextab.value=ntab;
	document.forms[0].submit();
}

function inizialize() {
	renderIcon();
	fade('errors',true);
}

function renderIcon() {
	if(document.forms[0].provvisorio.value!="1"){
		document.images['provv'].src="images/null.gif";
		document.images['provv'].alt="_";
	} else {
		document.images['provv'].src="images/ok.gif";
		document.images['provv'].alt="X";
	}			
	if(document.forms[0].pubblicato.value!="1"){
		document.images['vetrina'].src="images/null.gif";
		document.images['vetrina'].alt="_";
	} else {
		document.images['vetrina'].src="images/ok.gif";
		document.images['vetrina'].alt="X";
	}			
	if(document.forms[0].respinto.value!="1"){
		document.images['respinto'].src="images/null.gif";
		document.images['respinto'].alt="_";
	} else {
		document.images['respinto'].src="images/ok.gif";
		document.images['respinto'].alt="X";
	}			
}

function swapProvv() {
	if(document.forms[0].provvisorio.value=="0"){
		document.forms[0].provvisorio.value="1";
	}else{
		document.forms[0].provvisorio.value="0";
	}
	renderIcon();
}

function swapVetrina() {
	if(document.forms[0].pubblicato.value=="0"){
		document.forms[0].pubblicato.value="1";
	}else{
		document.forms[0].pubblicato.value="0";
	}
	renderIcon();
}

function swapRespinto() {
	if(document.forms[0].respinto.value=="0"){
		document.forms[0].respinto.value="1";
		document.forms[0].provvisorio.value="0";
		document.forms[0].pubblicato.value="0";
	}else{
		document.forms[0].respinto.value="0";
	}
	renderIcon();
}

function swapLang() {
	if(language=='it'){
		document.forms[0].titolo.style.visibility="hidden";
		document.forms[0].descrizione.style.visibility="hidden";
		document.forms[0].usi.style.visibility="hidden";
		document.forms[0].vantaggi.style.visibility="hidden";
		document.forms[0].parolaChiave.style.visibility="hidden";
		document.forms[0].titoloInglese.style.visibility="visible";
		document.forms[0].descrizioneInglese.style.visibility="visible";
		document.forms[0].usiInglese.style.visibility="visible";
		document.forms[0].vantaggiInglese.style.visibility="visible";
		document.forms[0].parolaChiaveInglese.style.visibility="visible";
		DynWrite('tit',"Title");
		DynWrite('des',"Description");
		DynWrite('use',"Applications");
		DynWrite('van',"Advantages");
		DynWrite('pch',"Keywords");

		document.forms[0].titolo.cols="1";
		document.forms[0].descrizione.cols="1";
		document.forms[0].usi.cols="1";
		document.forms[0].vantaggi.cols="1";
		document.forms[0].parolaChiave.size="1";
		document.forms[0].titoloInglese.cols="32";
		document.forms[0].descrizioneInglese.cols="32";
		document.forms[0].usiInglese.cols="32";
		document.forms[0].vantaggiInglese.cols="32";
		document.forms[0].parolaChiaveInglese.size="45";

		document.images['flag'].src="images/uk1b.gif";
		document.images['flag'].alt="Inglese";
		language='en';
	} else {
		document.forms[0].titoloInglese.style.visibility="hidden";
		document.forms[0].descrizioneInglese.style.visibility="hidden";
		document.forms[0].usiInglese.style.visibility="hidden";
		document.forms[0].vantaggiInglese.style.visibility="hidden";
		document.forms[0].parolaChiaveInglese.style.visibility="hidden";
		document.forms[0].titolo.style.visibility="visible";
		document.forms[0].descrizione.style.visibility="visible";
		document.forms[0].usi.style.visibility="visible";
		document.forms[0].vantaggi.style.visibility="visible";
		document.forms[0].parolaChiave.style.visibility="visible";
		DynWrite('tit',"<bean:message key="labels.titolo" bundle="${bundle}" />");
		DynWrite('des',"<bean:message key="labels.descrizione" bundle="${bundle}" />");
		DynWrite('use',"<bean:message key="labels.usi" bundle="${bundle}" />");
		DynWrite('van',"<bean:message key="labels.vantaggi" bundle="${bundle}" />");
		DynWrite('pch',"<bean:message key="labels.parolaChiave" bundle="${bundle}" />");	
		document.forms[0].titoloInglese.cols="1";
		document.forms[0].descrizioneInglese.cols="1";
		document.forms[0].usiInglese.cols="1";
		document.forms[0].vantaggiInglese.cols="1";
		document.forms[0].parolaChiaveInglese.size="1";
		document.forms[0].titolo.cols="32";
		document.forms[0].descrizione.cols="32";
		document.forms[0].usi.cols="32";
		document.forms[0].vantaggi.cols="32";
		document.forms[0].parolaChiave.size="45";
		
		document.images['flag'].src="images/italy0a.gif";
		document.images['flag'].alt="Italiano";
		language='it';

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
        <div class="titolo"><!-- InstanceBeginEditable name="title" -->Gestione dati del trovato<!-- InstanceEndEditable --></div><br/>
        <!-- InstanceBeginEditable name="content" -->
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td>
		    	<bean:tabs />
			</td>
          </tr>
          <tr>
            <td id="area" valign="top"><html:form action="Trovati.do" method="post"><table width="100%" border="0">
                <tr>
                  <td width="100" height="50%" align="left" valign="top">NsRIF<br />
				  	<html:text property="nsrif" size="10" readonly="true" /><br /><br />
				  	<c:if test="${not empty datiTrovato.dataCreazione}">Data creazione:<br />
				  		<html:text property="dataCreazione" size="13" styleClass="departs" readonly="true" /></c:if>
				  	<c:if test="${not empty datiTrovato.utcr}">Creato da: <br />
				  		<html:text property="utcr" size="13" styleClass="departs" readonly="true" /></c:if>
				  	<c:if test="${not empty datiTrovato.dataVariazione}">Ultima modifica:<br />
				  		<html:text property="dataVariazione" size="13" styleClass="departs" readonly="true" /></c:if>
				  	<c:if test="${not empty datiTrovato.utva}">Modificato da:<br />
				  		<html:text property="utva" size="13" styleClass="departs" readonly="true" /></c:if>
			        <html:textarea property="note" rows="1" cols="1" style="visibility:hidden" />
                  </td>
    <td rowspan="2"><html:hidden property="nextab"  />
    		  <html:hidden property="updDoc" />
              <table width="720" border="0" align="center">
                <tr>
                <td height="3" colspan="4"><img src="images/spacer.gif" width="3" height="3" /></td>
                </tr>
              <tr>
                        <td width="345" align="left">Dipartimento</td>
                  <td width="6">&nbsp;</td>
                <td align="left">Data richiesta deposito</td>
                <td rowspan="2" align="center"><c:if test="${sessionScope.amministraTrovati}"><img src="images/attach1.gif" width="25" height="25" alt="Documenti allegati" class="imaction" onclick="openDocs(<c:out value="${datiTrovato.nsrif}" />, 0, 2);" /></c:if></td>
              </tr>
              <tr>
                <td align="left">
                	<html:select property="dipartimentiId">
    					<html:options collection="dipartimenti" property="value" labelProperty="label" />
					</html:select> 
                </td>
                <td>&nbsp;</td>
                <td align="left"><html:text property="dataRichiesta" size="10" />
                  <img src="images/cal04c.gif" width="17" align="top" height="18" class="imaction" onclick="showCal('dataRichiesta');" /> (gg/mm/aaaa)</td>
                </tr>
              <tr>
                <td height="3" colspan="4"><img src="images/spacer.gif" width="3" height="3" /></td>
                </tr>
              <tr>
                <td align="left">Tipo</td>
                <td>&nbsp;</td>
                  <td colspan="2" align="left">D.lgs. n.30/2005</td>
                  </tr>
              <tr>
                <td align="left">
                	<html:text property="tipiTrovatoDescrizione" size="40" readonly="true"/>
                	<%--html:select property="tipiTrovatoId">
    					<html:options collection="tipi" property="value" labelProperty="label" />
					</html:select--%>                
                </td>
                <td>&nbsp;</td>
                <td colspan="2" align="left">
                	<html:select property="cessioneDiritti">
                		<html:options collection="diritti" property="value" labelProperty="label" />
                	</html:select>&nbsp;
                	<c:if test="${sessionScope.amministraTrovati}"><img src="images/attach1.gif" width="25" height="25" alt="Documenti allegati" class="imaction" onclick="openDocs(<c:out value="${datiTrovato.nsrif}" />, 0, 6);" /></c:if>
                </td>
                </tr>
              <tr>
                <td height="3" colspan="4"><hr /></td>
                </tr>
              <tr>
                <td align="left"><table width="280"><tr><td id="tit" width="65%" ><bean:message key="labels.titolo" bundle="${bundle}" /></td>
                	<td align="right"><img src="images/null.gif" name="provv" border="1" align="left" onclick="swapProvv();" alt="_" /> Provvisorio</td></tr></table>
	  				<html:hidden property="provvisorio" />
                </td>
                <td>&nbsp;</td>
                  <td width="180" align="left"><div id="des"><bean:message key="labels.descrizione" bundle="${bundle}" /></div></td>
                <td width="171" align="right"><input type="button" name="lingua" value="Ita/Eng" onclick="swapLang()" />&nbsp; <img src="images/italy0a.gif" name="flag" alt="Italiano" /> </td>
              </tr>
              <tr>
                <td align="left"><html:textarea property="titolo" cols="32" rows="3" />
                				 <html:textarea property="titoloInglese" cols="1" rows="3" style="visibility: hidden" /><br />
                </td>
                <td>&nbsp;</td>
                <td colspan="2" align="left"><html:textarea property="descrizione" cols="32" rows="3" />
                							 <html:textarea property="descrizioneInglese" cols="1" rows="3" style="visibility: hidden" /></td>
              </tr>
              <tr>
                <td height="3" colspan="4"><img src="images/spacer.gif" width="3" height="3" /></td>
                </tr>
              <tr>
                <td align="left"><div id="use"><bean:message key="labels.usi" bundle="${bundle}" /></div></td>
                <td>&nbsp;</td>
                <td colspan="2" align="left"><div id="van"><bean:message key="labels.vantaggi" bundle="${bundle}" /></div></td>
              </tr>
              <tr>
                <td align="left"><html:textarea property="usi" cols="32" rows="3" />
                				 <html:textarea property="usiInglese" cols="1" rows="3" style="visibility: hidden" /></td>
                <td>&nbsp;</td>
                <td colspan="2" align="left"><html:textarea property="vantaggi" cols="32" rows="3" />
                							 <html:textarea property="vantaggiInglese" cols="1" rows="3" style="visibility: hidden" /></td>
              </tr>
              <tr>
                <td height="3" colspan="4"><img src="images/spacer.gif" width="3" height="3" /></td>
                </tr>
              <tr>
              	<td align="left">Referente Titolo</td>
              	<td>&nbsp;</td>
                <td align="left"><div id="pch"><bean:message key="labels.parolaChiave" bundle="${bundle}" /></div></td>
              </tr>
              <tr>
              	<td align="left">
                	<html:select property="utentiId">
                		<html:options collection="utenti" property="value" labelProperty="label" />
                	</html:select>
              	</td>
                <td>&nbsp;</td>
                <td colspan="2" align="left"><html:text property="parolaChiave" size="45" />
                <html:text property="parolaChiaveInglese" size="1" style="visibility: hidden" /></td>
              </tr>
              <tr>
                <td height="3" colspan="4"><img src="images/spacer.gif" width="3" height="3" /></td>
              </tr>
            </table>			
            </td>
			  </tr>
                <tr>
                  <td height="50%" align="left" valign="bottom">
                  	<img src="images/null.gif" name="vetrina" border="1" align="left" onclick="swapVetrina();" alt="_" vspace="5" hspace="5" /> Pubblica su vetrina
                  	<html:hidden property="pubblicato" /><br /><br />
                  	<img src="images/null.gif" name="respinto" border="1" align="left" onclick="swapRespinto();" alt="_" vspace="5" hspace="5" /> Mai depositato
                  	<img src="images/insNote.gif" align="right" class="imaction" onclick="apriNota(document.forms[0].notaRespinto);" />
                  	<html:hidden property="respinto" /><html:hidden property="notaRespinto" />
                  	<br /><br /><br /><br />
                  	<c:if test="${empty sessionScope.datiTrovato.note}">
                  	<img src="images/insNote.gif" align="right" class="imaction" onclick="apriNota(document.forms[0].note);" /> 
                  	</c:if>
                  	<c:if test="${not empty sessionScope.datiTrovato.note}">
                  	<img src="images/modNote.gif" align="right" class="imaction" onclick="apriNota(document.forms[0].note);" /> 
                  	</c:if>
                  	Note generali
                  </td>
                </tr>
			</table>
			<html:errors />
			</html:form>
			</td>
          </tr>
          <tr>
            <td height="4"><img src="images/spacer.gif" width="4" height="4" /></td>
          </tr>
          <tr>
            <td align="center">
            <c:choose>
	            <c:when test="${sessionScope.amministraTrovati}">
		            <input type="button" value="Salva" onclick="goTab(0);" />
		              &nbsp;&nbsp;        
		            <input type="button" value="Annulla" onclick="javascript:abandon('home.do')" />
		              &nbsp;&nbsp;&nbsp;&nbsp;
		      		<input type="button" value="Ultima ricerca" onclick="javascript:abandon('GesTro.do?lastQBE=y')" />
	      		</c:when>
				<c:otherwise>
		      		<input type="button" value="Ultima ricerca" onclick="javascript:window.location.href='GesTro.do?lastQBE=y'" />
	            	&nbsp;&nbsp;
		            <input type="button" value="Chiudi" onclick="javascript:window.location.href='home.do'" />
				</c:otherwise>
            </c:choose>
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
