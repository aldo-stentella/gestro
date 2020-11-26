<%@page import="it.cnr.brevetti.ejb.entities.TipoEstinzione"%>
<%@page import="it.cnr.brevetti.util.UtilityFunctions"%>
<%@page import="java.util.Date"%>
<%@page import="it.cnr.brevetti.depositi.actionForms.DepositoForm"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.cnr.brevetti.ejb.entities.StoricoTitolarita"%>
<%@page import="java.util.HashMap"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<%@ page language="java" %>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<script type="text/javascript" src="scripts/standard.js?<%=Version.getVersion()%>"></script>
<title>Brevetti CNR - </title>
<style type="text/css">
#errors {
	top: 300px;
	left: 70px;
    position: absolute;
    width: 400px;
    z-index: 60;
    display: none;
}

#scelta {
	top: 2px;
	left: 2px;
    position: relative;
    width: 100px;
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
<%
String sedi = (String)session.getAttribute("sedi");
Date  dataPct = (Date)session.getAttribute("dataPct");
Date dataEpc = (Date)session.getAttribute("dataEpc");
%>
var sedi = new Array(<%=sedi%>);
var arr = new Array();
var dataPct = '<%= dataPct!=null?UtilityFunctions.itForm.format(dataPct):"" %>';
var dataEpc = '<%= dataEpc!=null?UtilityFunctions.itForm.format(dataEpc):"" %>';

function inizialize(){
	for(var i=0; i<document.forms[0].statoId.options.length; i++){
		arr[i]=document.forms[0].statoId.options[i];
	}
	popolateSedi(''+document.forms[0].denom.value);
	var elms = document.forms[0].studioBrevettualeId.options;
	for(var i = 0; i<elms.length; i++){
		if(elms[i].value == <%=((DepositoForm)request.getAttribute("datiDepEst")).getStudioBrevettualeId() %>)
			elms[i].selected = true;
	}
	fade('errors',true);
}

function popolate(){
	for(var i=0; i<arr.length; i++){
		document.forms[0].statoId.options[i]= arr[i];
	}
}

function popolateSedi(ind){
	var sede = sedi[ind];
	var voce = "";
	
	while(document.forms[0].studioBrevettualeId.options.length>0){
		if(document.all){
			document.forms[0].studioBrevettualeId.options.remove(0);
		} else {
			document.forms[0].studioBrevettualeId.options[0] = null;
		}
	}
	for(var i=0; i<sede.length; i++){
		voce = sede[i].toString();
		document.forms[0].studioBrevettualeId.options[i]=new Option(voce.substring(0,voce.indexOf('|')),voce.substr(voce.indexOf('|')+1));
	}
}

function infoTit(riga, idc, tipo){
	if(tipo == 3){
		var ref = window.open('InfoInventore.do?id='+idc,'popup_dep','height=550,width=850,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
		ref.focus();
	} else if(tipo == 1) {
		alert('"'+ document.forms[0].elements[riga * 3 + 20].value + '"');
	} else if(tipo == 4) {
		if(idc!=null ){
		var ref = window.open('InfoEnte.do?id='+idc,'popup_dep','height=550,width=850,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
		} else {
		alert('Dettaglio non disponibile');
		}
	}
}
</script>
<c:choose>
	<c:when test="${sessionScope.amministraTrovati}">
<script language="javascript" src="scripts/cal2.js?<%=Version.getVersion()%>">
/*
Xin's Popup calendar script-  Xin Yang (http://www.yxscripts.com/)
Script featured on/available at http://www.dynamicdrive.com/
This notice must stay intact for use
*/
</script>
<script language="javascript" src="scripts/cal_conf2.js?<%=Version.getVersion()%>"></script>

<script type="text/javascript" language="javascript1.4">

function doFilter(patt) {
	var el = "";
	popolate();
	var eln = document.forms[0].statoId.options.length;
	for(var i=0; i<eln; i++){
		el = document.forms[0].statoId.options[i].text.toUpperCase();
		if(el.indexOf(patt.toUpperCase())!=0){
			if(document.all){
				document.forms[0].statoId.options.remove(i);
			} else {
				document.forms[0].statoId.options[i] = null;
			}
			i--;
			eln--;
		}
	}
	if(eln==0) document.forms[0].statoId.options[0]=new Option('Nessun elemento trovato','');
}


function searchTit(riga){
	param3=riga+1;
	var ref = window.open('SelStorico.do?titolaritaId='+riga,'popup_dep','height=500,width=900,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
	ref.focus();
}

function deleteTit(riga){
	var ref = window.open('DelStorico.do?id='+riga,'popup_dep','height=230,width=600,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
	ref.focus();
}

function aggiornaTit(){
	document.forms[0].update.value = 1;
	document.forms[0].submit();
	//self.location.reload(); //history.go(0);
}
function switchDU(){
	fade('scelta',(GetObj('scelta').style.display!='block'));
}
function setData(tipo){
	if(tipo==1){
		document.forms[0].dataUfficialeX.value = document.forms[0].dataDepositoX.value; 
		document.forms[0].tipiDataUfficialeId.value = 1;
	} else if(tipo==2 && dataPct.length>0){
		document.forms[0].dataUfficialeX.value = dataPct;
		document.forms[0].tipiDataUfficialeId.value = 2;
	} else if(tipo==3 && dataEpc.length>0){
		document.forms[0].dataUfficialeX.value = dataEpc;
		document.forms[0].tipiDataUfficialeId.value = 3;
	} else
		alert('Procedura internazionale non presente.'); 
	fade('scelta', false);
}
function calcolaScadenza(){
	if(document.forms[0].dataUfficialeX.value == "") return;
	if(document.forms[0].tipiEstinzioneId.value != "<%= TipoEstinzione.SCADENZA %>") return;
	var uDep = parseDate(document.forms[0].dataUfficialeX.value);
	uDep.setFullYear(uDep.getFullYear() + (+ document.forms[0].anniValidita.value));
	document.forms[0].dataAbbandonoX.value = renderDate(uDep);
}
</script>
</c:when></c:choose>
<%
	String act = (String)request.getAttribute("act");
	if(act!=null && act.startsWith("close")){

%>
	<script type="text/javascript" language="javascript1.4">
		window.opener.eval("aggiornaDep();");
		window.close();
	</script>
<%
	}
%>
</head>
<body onload="inizialize();">
<html:form action="InsDepo.do" method="POST">
			<input type="hidden" name="update" value="0" />
            <html:hidden property="id" />
           	<html:hidden property="nsrif" />
           	<html:hidden property="key" />
           	<html:hidden property="tipoId" />
           	<html:hidden property="tipiDataUfficialeId" />
           	<html:hidden property="anniValidita" />
            <table width="500" border="0" align="center" cellpadding="5" class="area">
  <tr>
    <td colspan="2" align="right">Stato / Nazione    </td>
    <td width="309" align="left">
      <html:select property="statoId" style="width: 390px;">
        <html:options collection="nazioni" property="value" labelProperty="label" />
      </html:select><br />
      <span class="imaction" onclick="doFilter('');">#</span>
      <span class="imaction" onclick="doFilter('a');">A</span>
      <span class="imaction" onclick="doFilter('b');">B</span>
      <span class="imaction" onclick="doFilter('c');">C</span>
      <span class="imaction" onclick="doFilter('d');">D</span>
      <span class="imaction" onclick="doFilter('e');">E</span>
      <span class="imaction" onclick="doFilter('f');">F</span>
      <span class="imaction" onclick="doFilter('g');">G</span>
      <span class="imaction" onclick="doFilter('h');">H</span>
      <span class="imaction" onclick="doFilter('i');">I</span>
      <span class="imaction" onclick="doFilter('j');">J</span>
      <span class="imaction" onclick="doFilter('k');">K</span>
      <span class="imaction" onclick="doFilter('l');">L</span>
      <span class="imaction" onclick="doFilter('m');">M</span>
      <span class="imaction" onclick="doFilter('n');">N</span>
      <span class="imaction" onclick="doFilter('o');">O</span>
      <span class="imaction" onclick="doFilter('p');">P</span>
      <span class="imaction" onclick="doFilter('q');">Q</span>
      <span class="imaction" onclick="doFilter('r');">R</span>
      <span class="imaction" onclick="doFilter('s');">S</span>
      <span class="imaction" onclick="doFilter('t');">T</span>
      <span class="imaction" onclick="doFilter('u');">U</span>
      <span class="imaction" onclick="doFilter('v');">V</span>
      <span class="imaction" onclick="doFilter('w');">W</span>
      <span class="imaction" onclick="doFilter('x');">X</span>
      <span class="imaction" onclick="doFilter('y');">Y</span>
      <span class="imaction" onclick="doFilter('z');">Z</span>
                                                         
    </td>
  </tr>
  <tr>
    <td colspan="2" align="right">Lingua</td>
    <td align="left"><html:select property="idiomiId" >
    		<html:options collection="idiomi" property="value" labelProperty="label" />
    	</html:select></td>
  </tr>
  <tr>
    <td height="10" colspan="3" align="center"><img src="images/spacer.gif" width="480" height="1" class="imaction" /></td>
    </tr>
  <tr>
    <td align="right" valign="middle" rowspan="2">
    	<strong>Studio Brevettuale:</strong>
    </td>
    <td>Nome</td>
    <td><html:select property="denom" style="font-size: 10px; width: 255px;" onchange="popolateSedi(this.value);">
      <html:options collection="denoms" property="value" labelProperty="label" />
    </html:select>
    </td>
  </tr>
  <tr>
    <td>Sede</td>
    <td>
    	<select name="studioBrevettualeId"></select>
    <%-- html:select property="studioBrevettualeId">
      <html:options collection="sedeInit" property="value" labelProperty="label" />
      </html:select--%>
    </td>
  </tr>
  <tr>
    <td height="10" colspan="3" align="center"><img src="images/spacer.gif" width="480" height="1" class="imaction" /></td>
  </tr>
  <tr>
    <td width="117" rowspan="4" align="right" valign="middle"><strong>Deposito: </strong></td>
    <td width="32" align="right">N&deg;</td>
    <td align="left"><html:text property="numeroDeposito" size="15" />&nbsp; <c:if test="${sessionScope.amministraTrovati}"><img src="images/attach1.gif" width="25" height="25" alt="Documenti allegati" class="imaction" onclick="openDocs(<c:out value="${datiTrovato.nsrif}" />, <c:out value="${not empty datiDepEst.id ? datiDepEst.id : -1}" />, 3);" /></c:if></td>
  </tr>
  <tr>
    <td align="right"><img src="images/info.gif" onclick="openHelp('date_privative')" /> data effettiva</td>
    <td align="left"><html:text property="dataDepositoX" size="10" />
    	<img src="images/cal04c.gif" align="top" class="imaction" onclick="showCal('dataDeposito');" /> (gg/mm/aaaa)</td>
  </tr>
  <tr>
    <td align="right"><img src="images/info.gif" onclick="openHelp('date_privative')" /> data ufficiale</td>
    <td align="left"><html:text property="dataUfficialeX" size="10" readonly="true" />
    	<img src="images/rtable.gif" align="top" class="imaction" onclick="switchDU();" /><div id="scelta">
	                        	<img src="images/nation.gif" vspace="2" align="middle" class="imaction" alt="Deposito" name="rdep0" onclick="setData(1)" /> effettiva<br />
	                          	<img src="images/wipo.gif" vspace="2" align="middle" class="imaction" name="ddep0" alt="PCT" onclick="setData(2)" /> da PCT<br />
	                          	<img src="images/eu.gif" vspace="2" align="middle" class="imaction" name="ddep0" alt="EPC" onclick="setData(3)" /> da EPC
	                        </div></td>
  </tr>
  <tr>
    <td align="right">Priorit&agrave;</td>  
    <td align="left">
	    <html:radio property="primo" value="1"/>S&igrave; &nbsp;&nbsp;&nbsp;&nbsp;
	    <html:radio property="primo" value="0"/>No
    </td>
  </tr>
  <tr>
    <td height="10" colspan="3" align="center" valign="middle"><img src="images/spacer.gif" alt="" width="480" height="1" class="imaction" /></td>
  </tr>
  <tr>
    <td rowspan="2" align="right"><strong>Rilascio: </strong></td>
    <td align="right">N&deg;</td>
    <td align="left"><html:text property="numRilascioDep" size="15" />&nbsp; <c:if test="${sessionScope.amministraTrovati}"><img src="images/attach1.gif" width="25" height="25" alt="Documenti allegati" class="imaction" onclick="openDocs(<c:out value="${datiTrovato.nsrif}" />, <c:out value="${not empty datiDepEst.id ? datiDepEst.id : -1}" />, 4);" /></c:if></td>
  </tr>
  <tr>
    <td align="right">data</td>
    <td align="left"><html:text property="dataRilascioDepX" size="10" />
        <img src="images/cal04c.gif" align="top" class="imaction" onclick="showCal('dataRilascio');" /> (gg/mm/aaaa)</td>
  </tr>
    <tr>
    <td height="10" colspan="3" align="center" valign="middle"><img src="images/spacer.gif" alt="" width="480" height="1" class="imaction" /></td>
    </tr>
  <tr>
    <td rowspan="2" align="right"><strong>Pubblicazione: </strong></td>
    <td align="right">N&deg;</td>
    <td align="left"><html:text property="numPubblicazione" size="15" /></td>
  </tr>
  <tr>
    <td align="right">data</td>
    <td align="left"><html:text property="dataPubblicazioneX" size="10" />
    	<img src="images/cal04c.gif" align="top" class="imaction" onclick="showCal('dataPubblicazione');" /> (gg/mm/aaaa)</td>
  </tr>
    <tr>
    <td height="10" colspan="3" align="center" valign="middle"><img src="images/spacer.gif" alt="" width="480" height="1" class="imaction" /></td>
    </tr>
  <tr>
    <td rowspan="3" align="right"><strong>Estinzione titolarit&agrave;:</strong></td>
    <td align="right">data</td>
    <td align="left"><html:text property="dataAbbandonoX" size="10" />
      <img src="images/cal04c.gif" alt="" align="top" class="imaction" onclick="showCal('dataAbbandono');" /> 
      <img src="images/wizard.gif" alt="calcola" align="top" class="imaction" onclick="calcolaScadenza();" /> (gg/mm/aaaa)</td>
  </tr>
  <tr>
    <td align="right">causale</td>
    <td align="left"><html:select property="tipiEstinzioneId">
      <html:options collection="tipiEstinzione" property="value" labelProperty="label" />
      </html:select></td>
  </tr>
  <tr>
    <td align="right">nota</td>
    <td align="left">
       <html:textarea property="notaAbbandono" rows="2" cols="40" />
    </td>
  </tr>
  <tr>
  	<td colspan="3">
					<table id="excel" cellpadding="4" cellspacing="0" align="center">
						<tr>
							<th width="40" scope="col">Azioni
				    		</th>
						    <th width="380" scope="col">Titolare</th>
						    <th width="45" scope="col">%</th>
					  	</tr>
<%						HashMap<Integer, StoricoTitolarita> titolarita = (HashMap<Integer, StoricoTitolarita>)session.getAttribute("mapSto");
						int x = 0;
						String denominazione="";
						if(titolarita!=null){
							Iterator<Integer> it = titolarita.keySet().iterator();
							while(it.hasNext()){
								StoricoTitolarita tit = titolarita.get(it.next());
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
					  		<input type="text" name="<%="titolare"+x%>" value="<%=denominazione.replaceAll("\"","&quot;") %>" size="55" readonly="true" />
					  		<input type="hidden" name="<%="titolariId"+x%>" value="<%=tit.getId() %>" />
					  	  </td>
						  <td>
						  	<input type="text" name="<%="percent"+x%>" value="<%=tit.getPercentuale().toPlainString()%>" size="3" onchange="document.forms[0].updTit.value='1'" />
						  </td>
						</tr>
<%
							x++;
							}
						}
%>
						<tr>
							
                        <td scope="row" align="left">
                          <img src="images/plus.gif" height="16" width="16" class="imaction" name="rtit0" alt="Ricerca" onclick="searchTit(0)" />&nbsp;
                        </td>
						  <td align="left">
					  		&nbsp;<input type="hidden" name="numTitolari" value="<%=x %>" />
					  	  </td>
						  <td>&nbsp;</td>
						</tr>
					</table>
  	</td>
  </tr>
  <tr>
    <td colspan="3" align="center">
	    <c:choose>
		    <c:when test="${sessionScope.amministraTrovati}">
			    <html:submit value="Salva" />
				&nbsp;&nbsp;
				<input type="button" name="abort" value="Annulla" onclick="window.close();" />
		    </c:when>
		    <c:otherwise>
				<input type="button" name="close" value="Chiudi" onclick="window.close();" />
		    </c:otherwise>
	    </c:choose>
	</td>
    </tr>
  </table>
<html:errors />
</html:form>
</body>
</html>
