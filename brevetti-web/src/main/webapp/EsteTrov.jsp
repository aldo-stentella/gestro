<%@page import="java.util.ArrayList"%>
<%@ page language="java" %>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.cnr.brevetti.util.UtilityFunctions"%>
<%@page import="java.util.SortedMap"%>
<%@page import="java.util.List"%>
<%@page import="it.cnr.brevetti.ejb.entities.DepEst"%>
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
<style type="text/css">
#scelta {
	top: 2px;
	left: 2px;
    position: relative;
    width: 20px;
    z-index: 60;
    border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: groove;
	border-right-style: groove;
	border-bottom-style: groove;
	border-left-style: groove;
	border-top-color: #000000;
	border-right-color: #000000;
	border-bottom-color: #000000;
	border-left-color: #000000;
    display: none;
}
</style>
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

var param3=0;	//riga su cui si inserisce il dato

function aggiornaDep(){
	self.document.forms[0].updDep.value='1';
	self.document.forms[0].submit();
   
  }

/*function aggiornaEst(){
	self.document.forms[0].updDep.value='1';
	self.document.forms[0].submit();
  }*/

function searchDep(riga, tipo){
    if(tipo==1){
		var hi = 750
		var wi = 600
    }else if(tipo==2){
		var hi = 730
		var wi = 850
    } else if(tipo==3){
		var hi = 680
		var wi = 850
    }
	var ref = window.open('AddDepo.do?id='+riga+'&tipo='+tipo,'popup_trov','height='+hi+',width='+wi+',scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
	fade('scelta', false);
	ref.focus();
}

/*function searchEst(tipo){
	var ref = window.open('AddEst.do?tipo='+tipo,'popup_trov','height=680,width=850,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
	ref.focus();
}*/

</script>
<c:choose>
	<c:when test="${sessionScope.amministraTrovati}">
		<script type="text/javascript" language="javascript1.4">
			function deleteDep(riga){
				var ref = window.open('DelDepo.do?id='+riga,'popup_trov','height=700,width=600,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
				ref.focus();
			}

			function addListDep(riga){
				var ref = window.open('AddDepoList.do?key='+riga,'popup_trov','height=700,width=600,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
				ref.focus();
			}
			
			function newDep(){
				fade('scelta',(GetObj('scelta').style.display!='block'));	
			}
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript" language="javascript1.4">
			function deleteDep(riga){

			}
			
			function newDep(){
				
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
            <td id="area" valign="top">
              <table width="100%" border="0">
                <tr>
                  <td width="100" height="50%" align="left" valign="top">NsRIF<br />
		            <html:form action="Trovati.do" method="post">
					  	<html:text property="nsrif" size="10" readonly="true" />
						<html:hidden property="updDep"/>
				        <html:textarea property="note" rows="1" cols="1" style="visibility:hidden" />
	               		<html:hidden property="nextab" />
	               		<html:hidden property="updDoc" />
		            </html:form>
                  </td>
                  <td rowspan="2">
                    <table width="770" align="center" cellpadding="4" cellspacing="0" id="excel">
                      <tr> 
                        <th width="9%" scope="col">Azioni</th>
                        <th width="20%" scope="col">Nazione di deposito</th>
                        <th width="3%" scope="col">1&deg;</th>
                        <th width="15%" scope="col">Numero deposito</th>
                        <th width="10%" scope="col">Data</th>
                        <th width="10%">Status</th>
                        <th>Studio</th>
                      </tr>
<%
					List<DepEst> listD = (List<DepEst>)session.getAttribute("depositi");
					SortedMap<Long, Integer>mapdep = (SortedMap<Long,Integer>)session.getAttribute("mapdep");
					String[] icon = {"","nation","wipo","eu"};
					int x = 0;
					if(listD!=null && !listD.isEmpty())
						for(Long key : mapdep.keySet()){
							DepEst dep = listD.get(mapdep.get(key));
%>
                      <tr> 
                        <td scope="row" align="left"><img src="images/rtable.gif" class="imaction" alt="Ricerca" name="rdep0" onclick="searchDep('<%=key+"',"+dep.getTipoId()%>)" />
                          	<img src="images/delete.gif" class="imaction" name="ddep0" alt="Elimina" onclick="deleteDep('<%=key%>')" />
<%
							if(dep.getTipoId()!=1){
%>
                          	<img src="images/wizard.gif" class="imaction" name="ldep0" alt="Lista" onclick="addListDep('<%=key%>')" />
<%
							}
%>
                        </td>
                        <td style="white-space: nowrap;">
                        	<img src="images/<%=icon[dep.getTipoId()]%>.gif" />
                        	<input type="text" name="naz<%=x%>" size="30" readonly="true" value="<%=dep.getStato().getNome() %>" />
                        </td>
                        <td><img src="images/<%=dep.getPrimo()==1?"ok.gif":"null.gif"%>" border="1" /></td>
                        <td><input type="text" name="num<%=x%>" size="20" readonly="true" value="<%=dep.getNumeroDeposito()!=null?dep.getNumeroDeposito():"" %>" /></td>
                        <td><input type="text" name="dat<%=x%>" size="9" readonly="true" value="<%=dep.getDataDeposito()!=null?UtilityFunctions.itForm.format(dep.getDataDeposito()):"" %>" /></td>
                        <td><div class="status-<%=dep.getStatus() %>"><%=dep.getStatus() %></div></td>
                        <td><input type="text" name="stu<%=x%>" size="15" readonly="true" value="<%=dep.getStudioBrevettualeId()!=null&&dep.getStudioBrevettualeId()!=0?dep.getStudioBrevettuale().getDenominazione():"&nbsp;" %>" /></td>
                      </tr>
<%
						x++;
					}
%>
                      <tr> 
                        <td scope="row" align="left">
                        	<img src="images/rtable.gif" alt="Aggiungi" class="imaction" onClick="newDep();" />
                        	<img src="images/delete.gif" class="imaction" />
	                        <div id="scelta">
	                        	<img src="images/nation.gif" vspace="2" class="imaction" alt="Deposito" name="rdep0" onclick="searchDep('0',1)" /><br />
	                          	<img src="images/wipo.gif" vspace="2" class="imaction" name="ddep0" alt="PCT" onclick="searchDep(0,2)" /><br />
	                          	<img src="images/eu.gif" vspace="2" class="imaction" name="ddep0" alt="EPC" onclick="searchDep(0,3)" />
	                        </div>
                         </td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                    </table></td>
	</tr>
                <tr>
                  <td height="50%" align="left" valign="bottom">
                  	<c:if test="${sessionScope.amministraTrovati}"><img src="images/attach1.gif" width="25" height="25" align="right" alt="Documenti allegati" class="imaction" onclick="openDocs(<c:out value="${datiTrovato.nsrif}" />, 0, 10);" /> Verbali UVR <br /><br /><br /></c:if>
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
