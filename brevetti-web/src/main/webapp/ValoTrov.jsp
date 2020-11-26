<%@ page language="java" %>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="it.cnr.brevetti.ejb.entities.TipoValorizzazione"%>
<%@page import="it.cnr.brevetti.ejb.entities.Trovato"%>
<%@page import="it.cnr.brevetti.ejb.entities.Valorizzazione"%>
<%@page import="it.cnr.brevetti.util.UtilityFunctions"%>
<%@page import="java.util.List"%>
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
function inizialize() {
	//checkField();
}

function goTab(ntab) {
	document.forms[0].nextab.value=ntab;
	document.forms[0].submit();
}

function search(id){
	var ref = window.open('InfoVal.do?id='+id,'popup_trov','height=600,width=580,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
	ref.focus();
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
            <td id="area" valign="top">
              <table width="100%" border="0">
                <tr>
                  <td width="100" height="50%" align="left" valign="top">NsRIF<br />
		            <html:form action="Trovati.do" method="post">
					  	<html:text property="nsrif" size="10" readonly="true" />
				        <html:textarea property="note" rows="1" cols="1" style="visibility:hidden" />
	               		<html:hidden property="nextab" />
	               		<html:hidden property="updDoc" />
		            </html:form>
                  </td>
                  <td rowspan="2">
                  <c:choose><c:when test="${sessionScope.amministraTrovati}">
                    <table width="750" align="center" cellpadding="4" cellspacing="0" id="excel">
                      <tr> 
                        <th width="4%" scope="col">&nbsp;</th>
                        <th width="25%" scope="col">Azione</th>
                        <th width="25%" scope="col">Azienda</th>
                        <th width="7%" scope="col">Inizio</th>
                        <th width="7%">Fine</th>
                        <th width="5%">&#9733;</th>
                        <th width="27%">Cluster</th>
                      </tr>
<%
					List<Valorizzazione> list = (List<Valorizzazione>)session.getAttribute("valorizzazioni");
					if(list!=null && !list.isEmpty())
						for(Valorizzazione val : list){
%>
                      <tr>
                      	<td><img src="images/info.gif" class="imaction" alt="Dettaglio" onclick="search(<%= val.getId() %>)" /></td>
                        <td scope="row"><input type="text" size="30" readonly="true" value="<%= val.getTipoValorizzazioneId()!=TipoValorizzazione.ALTRO?val.getTipo().getNome():val.getAltro() %>" /></td>
                        <td style="white-space:nowrap"><input type="text" size="30" readonly="readonly" value="<%= val.getAzienda().getNome() %>" style="<%= (val.getAzienda().getTipo()!=null && val.getAzienda().getTipo().equalsIgnoreCase("spin-off"))?"background-color:#F90":"" %>" /></td>
                        <td><input type="text" size="8" readonly="true" value="<%=val.getInizio()!=null?UtilityFunctions.itForm.format(val.getInizio()):"" %>" /></td>
                        <td><input type="text" size="8" readonly="readonly" value="<%=val.getFine()!=null?UtilityFunctions.itForm.format(val.getFine()):"" %>" /></td>
                        <td><img src="images/attach1.gif" width="25" height="25" alt="Documenti allegati" class="imaction" onclick="readDocs(<c:out value="${datiTrovato.nsrif}" />, <%= val.getId() %>, 8);" /></td>
                        <td><span class="departs">
<% 	
	String comma = "";
	for(Trovato t : val.getTrovati()){
		out.println(comma+t.getNsrif());
		comma = ", ";
	}
%>						
                        </span></td>
                      </tr>
<%
					} else {
%>
					<tr><td colspan="7">- - - Nessuna valorizzazione presente - - -</td></tr>
<%
					}
%>
                    </table>
                    </c:when>
                    <c:otherwise>
                    	&nbsp;
                    </c:otherwise>
                    </c:choose>
                    </td>
	</tr>
                <tr>
                  <td height="50%" align="left" valign="bottom">
                  	<img src="images/<%=request.getAttribute("noteGenButton")%>" align="right" class="imaction" onclick="apriNota(document.forms[0].note);" /> Note generali
                  </td>
                </tr>
	</table>
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
