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
<script type="text/javascript" language="javascript1.4">
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
<%
Integer inventoreIndex = (Integer)request.getAttribute("inventoreIndex");
%>
var param1='';	//descrizione istituto/titolare
var param2='';	//codice istituto/titolare
var param3=0;	//riga su cui si inserisce il dato
var righe=15;	//num. max elementi nella pagina

function aggiornaInv(){
    self.document.forms[0].elements[ param3 * 3 - 1].value=param1;
	self.document.forms[0].elements[ param3 * 3].value=param2;
	checkField();
	self.document.forms[0].updInv.value='1';
	/**if(righe > param3){
		self.document.forms[0].elements[2 + param3 * 3].style.visibility="visible";
		self.document.forms[0].elements[4 + param3 * 3].style.visibility="visible";
		self.document.images['rinv'+(param3)].style.visibility="visible";
	}**/
  }
  

function checkField(){
	for(var i = 1; i< righe; i++) {
		if(document.forms[0].elements[i*3-1].value.length > 0){
	   		document.forms[0].elements[2+i*3].style.visibility="visible";
	   		document.forms[0].elements[1+i*3].style.visibility="visible";
			document.images['rinv'+ i].style.visibility="visible";
			document.images['dinv'+ i].style.visibility="visible";
			document.images['iinv'+ (i-1)].style.visibility="visible";
		} else {
	   		document.forms[0].elements[2+i*3].style.visibility="hidden";
	   		document.forms[0].elements[1+i*3].style.visibility="hidden";
			document.images['rinv'+ i].style.visibility="hidden";
			document.images['dinv'+ i].style.visibility="hidden";
			document.images['iinv'+ (i-1)].style.visibility="hidden";
		}
	}
	if(document.forms[0].elements[righe*3-1].value.length > 0){
		document.forms[0].elements[1+righe*3].style.visibility="visible";
		document.images['iinv'+ (righe-1)].style.visibility="visible";
	} else {
		document.forms[0].elements[1+righe*3].style.visibility="hidden";
		document.images['iinv'+ (righe-1)].style.visibility="hidden";
	}
}



function infoInv(riga){
	var idc = document.forms[0].elements[ (riga+1) * 3].value
	var ref = window.open('InfoInventore.do?id='+idc,'popup_trov','height=550,width=850,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
	ref.focus();	
}

function inizialize() {
	checkField();
	if(<%= inventoreIndex!=null?"true":"false" %>){
		document.forms[0].elements[(<%=inventoreIndex%>)*3+4].checked = true;
	}
}
</script>
<c:choose>
	<c:when test="${sessionScope.amministraTrovati}">
		<script type="text/javascript" language="javascript1.4">
			function searchInv(riga){
				param3=riga+1;
				var ref = window.open('SelInventore.do','popup_trov','height=550,width=850,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
				ref.focus();
			}
			
			function deleteInv(riga){
				if(document.forms[0].elements[riga*3+2].value.length > 0 && window.confirm("Eliminare '" + document.forms[0].elements[riga*3+2].value + "' ?")){
			    	document.forms[0].elements[riga*3+4].checked = false;			//azzero radio-button elemento cancellato
					for(var i = riga; i< (righe-1); i++) {
						if(! document.forms[0].elements[i*3+2].value.length > 0){
							break;
						}
				    	document.forms[0].elements[i*3+2].value = document.forms[0].elements[(i+1)*3+2].value;
				    	document.forms[0].elements[i*3+3].value = document.forms[0].elements[(i+1)*3+3].value;
				    	if(document.forms[0].elements[(i+1)*3+4].checked){
				    	   	document.forms[0].elements[i*3+4].checked = true;
				    	}
					}
					document.forms[0].elements[righe*3-1].value = "";		//l'ultima riga di inventori sicuramente vuota
				    document.forms[0].elements[righe*3].value = "";	
				    self.document.forms[0].updInv.value='1';
					checkField();
				}
			}
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript" language="javascript1.4">
			function searchInv(riga){	
			}
			function deleteInv(riga){
			}
		</script>
	</c:otherwise>
</c:choose>
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
				  	<html:text property="nsrif" size="10" readonly="true" />
                  </td>
    <td rowspan="2"><html:hidden property="nextab" />
              <table width="728" align="center" cellpadding="4" cellspacing="0" id="excel">
                <tr>
                        <th width="47" scope="col">Azioni</th>
    <th width="550" scope="col"><bean:message key="labels.inventore00" bundle="${bundle}" /> </th>
                        <th width="103" scope="col">Riferimento</th>
  </tr>
  <tr>
    <td scope="row"><img src="images/rtable.gif" class="imaction" name="rinv0" alt="Ricerca" onclick="searchInv(0)" />&nbsp;
    <img src="images/delete.gif" class="imaction" name="dinv0" alt="Elimina" onclick="deleteInv(0)" /></td>
    <td><html:text property="inventore00" size="80" readonly="true" /> <img src="images/info.gif" alt="Informazioni dettagliate" name="iinv0" onclick="infoInv(0);" />
    <html:hidden property="inventoriId00" />
    </td>
    <td><html:radio property="inventoreIndex" style="visibility: hidden" value="0" onchange="document.forms[0].updInv.value='1'" /></td>
  </tr>
  <tr>
    <td scope="row"><img src="images/rtable.gif" class="imaction" name="rinv1" alt="Ricerca" onclick="searchInv(1)" style="visibility:hidden" />&nbsp;
    <img src="images/delete.gif" class="imaction" name="dinv1" alt="Elimina" onclick="deleteInv(1)" style="visibility:hidden" /></td>
    <td><html:text property="inventore01" size="80"  style="visibility:hidden" readonly="true" /> <img src="images/info.gif" alt="Informazioni dettagliate" name="iinv1" onclick="infoInv(1);" />
    <html:hidden property="inventoriId01" />
    </td>
    <td><html:radio property="inventoreIndex" style="visibility: hidden" value="1" onchange="document.forms[0].updInv.value='1'" /></td>
  </tr>
  <tr>
    <td scope="row"><img src="images/rtable.gif" class="imaction" name="rinv2" alt="Ricerca" onclick="searchInv(2)" style="visibility:hidden" />&nbsp;
    <img src="images/delete.gif" class="imaction" name="dinv2" alt="Elimina" onclick="deleteInv(2)" style="visibility:hidden" /></td>
    <td><html:text property="inventore02" size="80"  style="visibility:hidden"  readonly="true" /> <img src="images/info.gif" alt="Informazioni dettagliate" name="iinv2" onclick="infoInv(2);" />
    <html:hidden property="inventoriId02" />
    </td>
    <td><html:radio property="inventoreIndex" style="visibility: hidden" value="2" onchange="document.forms[0].updInv.value='1'" /></td>
  </tr>
  <tr>
    <td scope="row"><img src="images/rtable.gif" class="imaction" name="rinv3" alt="Ricerca" onclick="searchInv(3)" style="visibility:hidden" />&nbsp;
    <img src="images/delete.gif" class="imaction" name="dinv3" alt="Elimina" onclick="deleteInv(3)" style="visibility:hidden" /></td>
    <td><html:text property="inventore03" size="80"  style="visibility:hidden"  readonly="true" /> <img src="images/info.gif" alt="Informazioni dettagliate" name="iinv3" onclick="infoInv(3);" />
    <html:hidden property="inventoriId03" />
    </td>
    <td><html:radio property="inventoreIndex" style="visibility: hidden" value="3" onchange="document.forms[0].updInv.value='1'" /></td>
  </tr>
  <tr>
    <td scope="row"><img src="images/rtable.gif" class="imaction" name="rinv4" alt="Ricerca" onclick="searchInv(4)" style="visibility:hidden" />&nbsp;
    <img src="images/delete.gif" class="imaction" name="dinv4" alt="Elimina" onclick="deleteInv(4)" style="visibility:hidden" /></td>
    <td><html:text property="inventore04" size="80"  style="visibility:hidden"  readonly="true" /> <img src="images/info.gif" alt="Informazioni dettagliate" name="iinv4" onclick="infoInv(4);" />
    <html:hidden property="inventoriId04" />
    </td>
    <td><html:radio property="inventoreIndex" style="visibility: hidden" value="4" onchange="document.forms[0].updInv.value='1'" /></td>
  </tr>
  <tr>
    <td scope="row"><img src="images/rtable.gif" class="imaction" name="rinv5" alt="Ricerca" onclick="searchInv(5)" style="visibility:hidden" />&nbsp;
    <img src="images/delete.gif" class="imaction" name="dinv5" alt="Elimina" onclick="deleteInv(5)" style="visibility:hidden" /></td>
    <td><html:text property="inventore05" size="80"  style="visibility:hidden"  readonly="true" /> <img src="images/info.gif" alt="Informazioni dettagliate" name="iinv5" onclick="infoInv(5);" />
    <html:hidden property="inventoriId05" />
    </td>
    <td><html:radio property="inventoreIndex" style="visibility: hidden" value="5" onchange="document.forms[0].updInv.value='1'" /></td>
  </tr>
  <tr>
    <td scope="row"><img src="images/rtable.gif" class="imaction" name="rinv6" alt="Ricerca" onclick="searchInv(6)" style="visibility:hidden" />&nbsp;
    <img src="images/delete.gif" class="imaction" name="dinv6" alt="Elimina" onclick="deleteInv(6)" style="visibility:hidden" /></td>
    <td><html:text property="inventore06" size="80"  style="visibility:hidden"  readonly="true" /> <img src="images/info.gif" alt="Informazioni dettagliate" name="iinv6" onclick="infoInv(6);" />
    <html:hidden property="inventoriId06" />
    </td>
    <td><html:radio property="inventoreIndex" style="visibility: hidden" value="6" onchange="document.forms[0].updInv.value='1'" /></td>
  </tr>
  <tr>
    <td scope="row"><img src="images/rtable.gif" class="imaction" name="rinv7" alt="Ricerca" onclick="searchInv(7)" style="visibility:hidden" />&nbsp;
    <img src="images/delete.gif" class="imaction" name="dinv7" alt="Elimina" onclick="deleteInv(7)" style="visibility:hidden" /></td>
    <td><html:text property="inventore07" size="80"  style="visibility:hidden"  readonly="true" /> <img src="images/info.gif" alt="Informazioni dettagliate" name="iinv7" onclick="infoInv(7);" />
    <html:hidden property="inventoriId07" />
    </td>
    <td><html:radio property="inventoreIndex" style="visibility: hidden" value="7" onchange="document.forms[0].updInv.value='1'" /></td>
  </tr>
  <tr>
    <td scope="row"><img src="images/rtable.gif" class="imaction" name="rinv8" alt="Ricerca" onclick="searchInv(8)" style="visibility:hidden" />&nbsp;
    <img src="images/delete.gif" class="imaction" name="dinv8" alt="Elimina" onclick="deleteInv(8)" style="visibility:hidden" /></td>
    <td><html:text property="inventore08" size="80"  style="visibility:hidden"  readonly="true" /> <img src="images/info.gif" alt="Informazioni dettagliate" name="iinv8" onclick="infoInv(8);" />
    <html:hidden property="inventoriId08" />
    </td>
    <td><html:radio property="inventoreIndex" style="visibility: hidden" value="8" onchange="document.forms[0].updInv.value='1'" /></td>
  </tr>
  <tr>
    <td scope="row"><img src="images/rtable.gif" class="imaction" name="rinv9" alt="Ricerca" onclick="searchInv(9)" style="visibility:hidden" />&nbsp;
    <img src="images/delete.gif" class="imaction" name="dinv9" alt="Elimina" onclick="deleteInv(9)" style="visibility:hidden" /></td>
    <td><html:text property="inventore09" size="80"  style="visibility:hidden"  readonly="true" /> <img src="images/info.gif" alt="Informazioni dettagliate" name="iinv9" onclick="infoInv(9);" />
    <html:hidden property="inventoriId09" />
    </td>
    <td><html:radio property="inventoreIndex" style="visibility: hidden" value="9" onchange="document.forms[0].updInv.value='1'" /></td>
  </tr>
  <tr>
    <td scope="row"><img src="images/rtable.gif" class="imaction" name="rinv10" alt="Ricerca" onclick="searchInv(10)" style="visibility:hidden" />&nbsp;
    <img src="images/delete.gif" class="imaction" name="dinv10" alt="Elimina" onclick="deleteInv(10)" style="visibility:hidden" /></td>
    <td><html:text property="inventore10" size="80"  style="visibility:hidden"  readonly="true" /> <img src="images/info.gif" alt="Informazioni dettagliate" name="iinv10" onclick="infoInv(10);" />
    <html:hidden property="inventoriId10" />
    </td>
    <td><html:radio property="inventoreIndex" style="visibility: hidden" value="10" onchange="document.forms[0].updInv.value='1'" /></td>
  </tr>
  <tr>
    <td scope="row"><img src="images/rtable.gif" class="imaction" name="rinv11" alt="Ricerca" onclick="searchInv(11)" style="visibility:hidden" />&nbsp;
    <img src="images/delete.gif" class="imaction" name="dinv11" alt="Elimina" onclick="deleteInv(11)" style="visibility:hidden" /></td>
    <td><html:text property="inventore11" size="80"  style="visibility:hidden"  readonly="true" /> <img src="images/info.gif" alt="Informazioni dettagliate" name="iinv11" onclick="infoInv(11);" />
    <html:hidden property="inventoriId11" />
    </td>
    <td><html:radio property="inventoreIndex" style="visibility: hidden" value="11" onchange="document.forms[0].updInv.value='1'" /></td>
  </tr>
  <tr>
    <td scope="row"><img src="images/rtable.gif" class="imaction" name="rinv12" alt="Ricerca" onclick="searchInv(12)" style="visibility:hidden" />&nbsp;
    <img src="images/delete.gif" class="imaction" name="dinv12" alt="Elimina" onclick="deleteInv(12)" style="visibility:hidden" /></td>
    <td><html:text property="inventore12" size="80"  style="visibility:hidden"  readonly="true" /> <img src="images/info.gif" alt="Informazioni dettagliate" name="iinv12" onclick="infoInv(12);" />
    <html:hidden property="inventoriId12" />
    </td>
    <td><html:radio property="inventoreIndex" style="visibility: hidden" value="12" onchange="document.forms[0].updInv.value='1'" /></td>
  </tr>
  <tr>
    <td scope="row"><img src="images/rtable.gif" class="imaction" name="rinv13" alt="Ricerca" onclick="searchInv(13)" style="visibility:hidden" />&nbsp;
    <img src="images/delete.gif" class="imaction" name="dinv13" alt="Elimina" onclick="deleteInv(13)" style="visibility:hidden" /></td>
    <td><html:text property="inventore13" size="80"  style="visibility:hidden"  readonly="true" /> <img src="images/info.gif" alt="Informazioni dettagliate" name="iinv13" onclick="infoInv(13);" />
    <html:hidden property="inventoriId13" />
    </td>
    <td><html:radio property="inventoreIndex" style="visibility: hidden" value="13" onchange="document.forms[0].updInv.value='1'" /></td>
  </tr>
  <tr>
    <td scope="row"><img src="images/rtable.gif" class="imaction" name="rinv14" alt="Ricerca" onclick="searchInv(14)" style="visibility:hidden" />&nbsp;
    <img src="images/delete.gif" class="imaction" name="dinv14" alt="Elimina" onclick="deleteInv(14)" style="visibility:hidden" /></td>
    <td><html:text property="inventore14" size="80"  style="visibility:hidden"  readonly="true" /> <img src="images/info.gif" alt="Informazioni dettagliate" name="iinv14" onclick="infoInv(14);" />
    <html:hidden property="inventoriId14" />
    </td>
    <td><html:radio property="inventoreIndex" style="visibility: hidden" value="14" onchange="document.forms[0].updInv.value='1'" /></td>
  </tr>
</table>
</td>
	</tr>
                <tr>
                  <td height="50%" align="left" valign="bottom">
			        <html:textarea property="note" rows="1" cols="1" style="visibility:hidden" /><br />
                  	<img src="images/<%=request.getAttribute("noteGenButton")%>" align="right" class="imaction" onclick="apriNota(document.forms[0].note);" /> Note generali
                  </td>
                </tr>
		</table>
			<html:hidden property="updInv" />
    		<html:hidden property="updDoc" />
            </html:form></td>
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
