<%@ page language="java"%>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.Iterator"%>
<%@page import="it.cnr.brevetti.ejb.entities.Titolarita"%>
<%@page import="java.util.HashMap"%>
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
	checkField();
}

function goTab(ntab) {
	document.forms[0].nextab.value=ntab;
	checkSubmit();
	document.forms[0].submit();
}

var maxIst = 4;	//numero di righe per istituti
var maxTit = 4;	//numero di righe per titolarit
var param1='';	//descrizione istituto/titolare
var param2='';	//codice istituto/titolare
var param3=0;	//riga su cui si inserisce il dato

function aggiornaIst(){
    self.document.forms[0].elements[1 + param3 * 2].value=param1;
	self.document.forms[0].elements[2 + param3 * 2].value=param2;
	checkField();
	self.document.forms[0].updIst.value='1';
  }

function aggiornaTit(){
	self.document.forms[0].updTit.value='1';
	self.document.forms[0].submit();
  }
  
  

function checkField(){
	for(var i = 1; i<= maxIst; i++) {
		if(i==1 || document.forms[0].elements[1+(i-1)*2].value.length > 0){
	    	document.forms[0].elements[1+i*2].style.visibility="visible";
			document.images['rist'+(i-1)].style.visibility="visible";
			document.images['dist'+(i-1)].style.visibility="visible";
		} else {
	    	document.forms[0].elements[1+i*2].style.visibility="hidden";
			document.images['rist'+(i-1)].style.visibility="hidden";
			document.images['dist'+(i-1)].style.visibility="hidden";
		}
	}
}

function infoTit(riga, idc, tipo){
	if(tipo == 3){
		var ref = window.open('InfoInventore.do?id='+idc,'popup_trov','height=550,width=850,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
		ref.focus();
	} else if(tipo == 1) {
		alert('"'+ document.forms[0].elements[riga * 3 + 11].value + '"');
	} else if(tipo == 4) {
		if(idc!=null ){
		var ref = window.open('InfoEnte.do?id='+idc,'popup_trov','height=550,width=850,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
		} else {
		alert('Dettaglio non disponibile');
		}
	}
}

function checkSubmit(){
	var tot = 0;
	var nt = document.forms[0].numTitolari.value;
	for(var i = 1; i<= nt; i++) {
		var tmp = document.forms[0].elements[(i*3)+10].value.replace(',','\.') ;
		document.forms[0].elements[(i*3)+10].value = tmp;
		tot = tot + parseFloat(document.forms[0].elements[(i*3)+10].value);
	}
	if((tot<96 || tot>104) && tot!=0){
		alert('La somma delle percentuali di titolarità ('+tot+'%)  fuori margine');
		document.forms[0].nextab.value='3';
	} 
}
</script>
<c:choose>
	<c:when test="${sessionScope.amministraTrovati}">
		<script type="text/javascript" language="javascript1.4">
			function searchIst(riga){
				param3=riga+1;
				var ref = window.open('SelIstituto.do','popup_trov','height=300,width=900,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
				ref.focus();
			}
			
			function deleteIst(riga){
				if(document.forms[0].elements[1+(riga+1)*2].value.length > 0 && window.confirm("Eliminare l'istituto '" + document.forms[0].elements[1+(riga+1)*2].value + "' ?")){
					for(var i = (riga+1); i< maxIst; i++) {
						if(! document.forms[0].elements[1+i*2].value.length > 0){
							break;
						}
				    	document.forms[0].elements[1+i*2].value = document.forms[0].elements[1+(i+1)*2].value;
				    	document.forms[0].elements[i*2+2].value = document.forms[0].elements[(i+1)*2+2].value;
					}
					document.forms[0].elements[1+maxIst*2].value = "";		//l'ultima riga di istituti  sicuramente vuota
				    document.forms[0].elements[maxIst*2+2].value = "";
				    self.document.forms[0].updIst.value='1';
					checkField();
				}
			}
			
			function searchTit(riga){
				param3=riga+1;
				var ref = window.open('SelTitolare.do?titolaritaId='+riga,'popup_trov','height=500,width=900,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
				ref.focus();
			}
			
			function deleteTit(riga){
				var ref = window.open('DelTitolare.do?id='+riga,'popup_trov','height=230,width=600,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
				ref.focus();
			}
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript" language="javascript1.4">
			function searchIst(riga){
				
			}
			function deleteIst(riga){
				
			}
			function searchTit(riga){
				
			}
			function deleteTit(riga){

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
				    <td rowspan="2">
					<html:hidden property="nextab" />
					<html:hidden property="updDoc" />
					<br />
					<table id="excel" cellpadding="4" cellspacing="0" align="center" width="750">
					  <tr>
							<th width="50" scope="col">Azioni
							</th>
							<th width="680" scope="col">Istituti richiedenti</th>
					  </tr>
					  <tr>
							
                        <td scope="row">
                          <img src="images/rtable.gif" class="imaction" name="rist0" alt="Ricerca" onclick="searchIst(0)" />&nbsp;
                          <img src="images/delete.gif" class="imaction" name="dist0" alt="Elimina" onclick="deleteIst(0)" />
                        </td>
							<td align="left">
						    	<html:text property="istituto0" readonly="true" size="110" />
								<html:hidden property="istitutiId0" />
							</td>
					  </tr>
					  <tr>
							
                        <td scope="row"> 
                          <img src="images/rtable.gif" class="imaction" name="rist1" alt="Ricerca" onclick="searchIst(1)" style="visibility:hidden" />&nbsp;
                          <img src="images/delete.gif" class="imaction" name="dist1" alt="Elimina" onclick="deleteIst(1)" style="visibility:hidden" />
                        </td>
							<td align="left">
						    	<html:text property="istituto1" readonly="true" size="110" style="visibility:hidden" />
								<html:hidden property="istitutiId1" />
							</td>
					  </tr>
					  <tr>
							
                        <td scope="row"> 
                          <img src="images/rtable.gif" class="imaction" name="rist2" alt="Ricerca" onclick="searchIst(2)" style="visibility:hidden" />&nbsp;
                          <img src="images/delete.gif" class="imaction" name="dist2" alt="Elimina" onclick="deleteIst(2)" style="visibility:hidden" />
                        </td>
							<td align="left">
						    	<html:text property="istituto2" readonly="true"  size="110" style="visibility:hidden" />
								<html:hidden property="istitutiId2" />
							</td>
					  </tr>
						<tr>
							
                        <td scope="row">
                          <img src="images/rtable.gif" class="imaction" name="rist3" alt="Ricerca" onclick="searchIst(3)" style="visibility:hidden" />&nbsp;
                          <img src="images/delete.gif" class="imaction" name="dist3" alt="Elimina" onclick="deleteIst(3)" style="visibility:hidden" />
                        </td>
							<td align="left">
						    	<html:text property="istituto3" readonly="true" size="110" style="visibility:hidden" />
								<html:hidden property="istitutiId3" />
							</td>
					  </tr>
					</table>
  					<br />
					<table id="excel" cellpadding="4" cellspacing="0" align="center"
						width="750">
						<tr>
							<th width="50" scope="col">Azioni
				    		</th>
						    <th width="607" scope="col">Titolare</th>
						    <th width="60" scope="col">%</th>
						    <th width="5" scope="col"><c:if test="${sessionScope.amministraTrovati}"><img src="images/attach1.gif" width="25" height="25" alt="Documenti allegati" class="imaction" onclick="openDocs(<c:out value="${datiTrovato.nsrif}" />, 0, 12);" /></c:if></th>
					  	</tr>
<%						HashMap titolarita = (HashMap)session.getAttribute("mapTit");
						int x = 0;
						String denominazione="";
						if(titolarita!=null){
							Iterator it = titolarita.keySet().iterator();
							while(it.hasNext()){
								Titolarita tit = (Titolarita)titolarita.get(it.next());
								if(tit.getTipiTitolareId().intValue()==1)
									denominazione=tit.getDipartimento().getDescrizione();
								else if(tit.getTipiTitolareId().intValue()==3)
									denominazione=tit.getInventore().getCognome()+" "+tit.getInventore().getNome();
								else if(tit.getTipiTitolareId().intValue()==4)
									denominazione=tit.getEnteEsterno().getNome();
%>
						<tr>
							
                        <td scope="row">
                          <img src="images/rtable.gif" class="imaction" name="rtit0" alt="Ricerca" onclick="searchTit(<%=tit.getId() %>)" />&nbsp;
                          <img src="images/delete.gif" class="imaction" name="dtit0" alt="Elimina" onclick="deleteTit(<%=tit.getId() %>)" /> 
                        </td>
						  <td align="left">
						  	<img src="images/tit_<%= tit.getTipiTitolareId() %>.gif" class="imaction" style="vertical-align: text-bottom;" onclick="infoTit(<%= x+","+tit.getFrkSoggettoId()+","+ tit.getTipiTitolareId()%>)" />
					  		<input type="text" name="<%="titolare"+x%>" value="<%=denominazione.replaceAll("\"","&quot;") %>" size="95" readonly="true" />
					  		<input type="hidden" name="<%="titolariId"+x%>" value="<%=tit.getId() %>" />
					  	  </td>
						  <td>
						  	<input type="text" name="<%="percent"+x%>" value="<%=tit.getPercentuale().toPlainString()%>" size="4" onchange="document.forms[0].updTit.value='1'" />
						  </td>
						  <td>&nbsp;</td>
						</tr>
<%
							x++;
							}
						}
%>
						<tr>
							
                        <td scope="row">
                          <img src="images/rtable.gif" class="imaction" name="rtit0" alt="Ricerca" onclick="searchTit(0)" />&nbsp;
                          <img src="images/delete.gif" class="imaction" name="dtit0" alt="Elimina" /> 
                        </td>
						  <td align="left">
					  		<input type="text" name="titolare" size="100" readonly="true" />
					  		<input type="hidden" name="numTitolari" value="<%=x %>" />
					  	  </td>
						  <td>&nbsp;</td>
						  <td>&nbsp;</td>
						</tr>
					</table>
				  </td>
				  </tr>
                <tr>
                  <td height="50%" align="left" valign="bottom">
					<html:hidden property="updIst" />
					<html:hidden property="updTit" />
			        <html:textarea property="note" rows="1" cols="1" style="visibility:hidden" /><br />
                  	<img src="images/<%=request.getAttribute("noteGenButton")%>" align="right" class="imaction" onclick="apriNota(document.forms[0].note);" /> Note generali
                  </td>
                </tr>
				  </table>
				</html:form>			
				</td>
			</tr>
			<tr>
				<td height="4"><img src="images/spacer.gif" width="4"
					height="4" /></td>
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
