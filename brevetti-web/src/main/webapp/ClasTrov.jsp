<%@ page language="java" %>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.TreeMap"%>
<%@page import="it.cnr.brevetti.ejb.entities.ClassificazioneInternDep"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.cnr.brevetti.trovati.javabeans.TrovatoJB"%>
<%@page import="it.cnr.brevetti.ejb.entities.Classificazione"%><html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/generic2.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
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


function aggiornaCla(){
	self.document.forms[0].updClas.value='1';
	self.document.forms[0].submit();
  }
  


function infoCla(riga){
	var ref = window.open('InfoClasse.do?id='+riga,'popup_trov','height=300,width=900,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
	ref.focus();	
}
</script>

<c:choose>
	<c:when test="${sessionScope.amministraTrovati}">
		<script type="text/javascript" language="javascript1.4">
			function searchCla(riga){
				var ref = window.open('SelClasse.do?tipo=1&id='+riga,'popup_trov','height=300,width=930,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
				ref.focus();
			}
			
			function searchTec(riga){
				var ref = window.open('SelClasse.do?tipo=2&id='+riga,'popup_trov','height=300,width=930,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
				ref.focus();
			}
			function deleteCla(riga){
				var ref = window.open('DelClasse.do?id='+riga,'popup_trov','height=300,width=930,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
				ref.focus();
			}
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript" language="javascript1.4">
			function searchCla(riga){

			}
			function searchTec(riga){

			}
			function deleteCla(riga){

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
                    <html:hidden property="updDoc" /><br/>
                    <div align="center"><strong>International Patent Classification (IPC) </strong></div>
                    <table width="636" align="center" cellpadding="4" cellspacing="0" id="excel">
                      <tr> 
                        <th width="45" scope="col">Azioni</th>
                        <th width="354" scope="col">I - II - III livello</th>
                        <th width="209" scope="col">IV livello</th>
                      </tr>
<%						TreeMap<Integer, ClassificazioneInternDep> mapCla = (TreeMap<Integer, ClassificazioneInternDep>)session.getAttribute("classificazioni");
						int x = 0;
						String denominazione="";
						if(mapCla!=null){
							for(Iterator<Integer> it = mapCla.keySet().iterator(); it.hasNext();){
								ClassificazioneInternDep cla = mapCla.get(it.next());
								if(cla.getClassificazione().getTipo()==1){

%>
	                      <tr> 
	                        <td scope="row"><img src="images/rtable.gif" class="imaction" alt="Ricerca" onclick="searchCla(<%=cla.getClassificazioneInternDepId() %>)" />&nbsp;
	                        	<img src="images/delete.gif" class="imaction" alt="Elimina" onclick="deleteCla(<%=cla.getClassificazioneInternDepId() %>)" /></td>
	                        <td><input type="text" value="<%=cla.getClassificazione().getNome() %>" size="50" readonly="true" /><img src="images/info.gif" alt="Informazioni dettagliate" onclick="infoCla(<%=cla.getClassificazioniId() %>);" /></td>
	                        <td><input type="text" value="<%=cla.getAltraClassificazione() %>" size="30" readonly="true" /></td>
	                      </tr>
<%
									x++;
								}
							}
						}
%>
	                      <tr> 
	                        <td scope="row" align="left"><img src="images/rtable.gif" class="imaction" alt="Ricerca" onclick="searchCla(0)" />&nbsp;</td>
	                        <td>&nbsp;</td>
	                        <td>&nbsp;</td>
	                      </tr>
                   </table><br/>
                    <div align="center"><strong>
                      Classificazione per Mercato Tecnologico</strong></div>
                    <table width="636" align="center" cellpadding="4" cellspacing="0" id="excel">
                      <tr> 
                        <th width="58" scope="col">Azioni</th>
                        <th width="216" scope="col">Settore tecnologico</th>
                        <th width="342" scope="col">Sottosettore</th>
                      </tr>
<%
						if(mapCla!=null){
							TrovatoJB tjb = TrovatoJB.getInstance();
							for(Iterator<Integer> it = mapCla.keySet().iterator(); it.hasNext();){
								ClassificazioneInternDep cla = mapCla.get(it.next());
								if(cla.getClassificazione().getTipo()==2){
									String padre = "";
									String figlio = "";
									if(cla.getClassificazione().getLivello()==2){
										padre = tjb.getClassificazione(cla.getClassificazione().getIdPadre()).getNome(); 
										figlio = cla.getClassificazione().getNome();								
									}else
										padre = cla.getClassificazione().getNome();
%>
	                      <tr> 
	                        <td scope="row" style="white-space:nowrap"><img src="images/rtable.gif" class="imaction" alt="Ricerca" onclick="searchTec(<%=cla.getClassificazioneInternDepId() %>)" /> 
	                        <img src="images/delete.gif" class="imaction" alt="Elimina" onclick="deleteCla(<%=cla.getClassificazioneInternDepId() %>)" /> 
	                        <img src="images/info.gif" alt="Informazioni dettagliate" onclick="infoCla(<%=cla.getClassificazioniId() %>);" /></td>
	                        <td ><input type="text" value="<%=padre %>" size="30" readonly="true" /></td>
	                        <td><input type="text" value="<%=figlio %>" size="50" readonly="true" /></td>
	                      </tr>
<%
								}
							}
						}
%>
	                      <tr> 
	                        <td scope="row" align="left"><img src="images/rtable.gif" class="imaction" alt="Ricerca" onclick="searchTec(0)" /></td>
	                        <td>&nbsp;</td>
	                        <td>&nbsp;</td>
	                      </tr>
                    </table>
                    </td>
	</tr>
                <tr>
                  <td height="50%" align="left" valign="bottom">
					<html:hidden property="updClas" />
					<html:hidden property="updTecn" />
			        <html:textarea property="note" rows="1" cols="1" style="visibility:hidden" /><br />
                  	<img src="images/<%=request.getAttribute("noteGenButton")%>" align="right" class="imaction" onclick="apriNota(document.forms[0].note);" /> Note generali
                  </td>
                </tr>
	</table>
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
