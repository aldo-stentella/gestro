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
</style>
<script type="text/javascript" language="javascript1.4">
<%
String sedi = (String)session.getAttribute("sedi");

%>
var sedi = new Array(<%=sedi%>);
var arr = new Array();

function inizialize(){
	for(var i=0; i<document.forms[0].paesi.options.length; i++){
		arr[i]=document.forms[0].paesi.options[i];
	}
	popolateSedi(''+document.forms[0].denom.value);
	var elms = document.forms[0].studioBrevettualeId.options;
	for(var i = 0; i<elms.length; i++){
		if(elms[i].value==<%=((DepositoForm)request.getAttribute("datiDepEst")).getStudioBrevettualeId() %> )
			elms[i].selected = true;
	}
	fade('errors',true);
}

function popolate(){
	for(var i=0; i<arr.length; i++){
		document.forms[0].paesi.options[i]= arr[i];
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
		alert('"'+ document.forms[0].elements[riga * 3 + 25].value + '"');
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
	var eln = document.forms[0].paesi.options.length;
	for(var i=0; i<eln; i++){
		el = document.forms[0].paesi.options[i].text.toUpperCase();
		if(el.indexOf(patt.toUpperCase())!=0){
			if(document.all){
				document.forms[0].paesi.options.remove(i);
			} else {
				document.forms[0].paesi.options[i] = null;
			}
			i--;
			eln--;
		}
	}
	if(eln==0) document.forms[0].paesi.options[0]=new Option('Nessun elemento trovato','');
}


function addNation(){
	var list = document.forms[0].paesi;
	if(!(list.selectedIndex==-1)){
		var dest = document.forms[0].listaNaz.options;
		var elm = list.options[list.selectedIndex];
		if(elm.value==0){
			return;
		}
		for (var i = 0; i < dest.length; i++) {
	    	if (dest[i].value == elm.value) {
		        return;
		    }
		}
		dest[dest.length] = new Option(elm.text, elm.value, false, false);
	}
}

function delNation(){
	var list = document.forms[0].listaNaz;
	if(!(list.selectedIndex==-1)){
		if(document.all){
				list.options.remove(list.selectedIndex);
		} else {
				list.options[list.selectedIndex] = null;
		}	
	}
}

function addRegion(){
	var list = document.forms[0].regione;
	if(!(list.selectedIndex==-1)){
		var dest = document.forms[0].listaReg.options;
		var elm = list.options[list.selectedIndex];
		for (var i = 0; i < dest.length; i++) {
	    	if (dest[i].value == elm.value) {
		        return;
		    }
		}
		dest[dest.length] = new Option(elm.text, elm.value, false, false);
	}
}

function delRegion(){
	var list = document.forms[0].listaReg;
	if(!(list.selectedIndex==-1)){
		if(document.all){
				list.options.remove(list.selectedIndex);
		} else {
				list.options[list.selectedIndex] = null;
		}	
	}
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

function salva(){
	var list = document.forms[0].listaNaz.options;
	for (var i = 0; i < list.length; i++) {
		list[i].selected = true;
	}
	list = document.forms[0].listaReg.options;
	for (var i = 0; i < list.length; i++) {
		list[i].selected = true;
	}
	document.forms[0].submit();
}
function aggiornaTit(){
	document.forms[0].update.value = 1;
	document.forms[0].submit();
	//self.location.reload(); //history.go(0);
}
function calcolaScadenza(){
	if(document.forms[0].dataDepositoX.value == "") return;
	var uDep = parseDate(document.forms[0].dataDepositoX.value);
	uDep.setMonth(uDep.getMonth() + 18);
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
<html:form action="InsPct.do" method="POST">
			<input type="hidden" name="update" value="0" />
    	    <html:hidden property="id" />
          	<html:hidden property="nsrif" />
          	<html:hidden property="key" />
          	<html:hidden property="tipoId" />
           	<html:hidden property="tipiDataUfficialeId" />
          	<html:hidden property="statoId" />
            <table width="810" border="0" align="center" cellpadding="5" cellspacing="0" class="area">
<tr>
    	<td colspan="2" align="center">Tipo di procedura:</td>
        <td align="left"><img src="images/wipo.gif" class="imaction" /><span class="wide"> PCT</span></td>
     
        <td colspan="2" align="right">Lingua di deposito</td>
        <td align="left"><html:select property="idiomiId" >
          <html:options collection="idiomi" property="value" labelProperty="label" />
        </html:select></td>
    </tr>
  <tr>
    <td width="112" rowspan="3" align="right" valign="middle"><strong>Deposito internazionale: </strong></td>
    <td width="45" align="right">Priorit&agrave;</td>
    <td width="209" align="left"><html:radio property="primo" value="1"/>S&igrave; &nbsp;&nbsp;&nbsp;&nbsp;
	    <html:radio property="primo" value="0"/>No</td>
    <td width="126" rowspan="3" align="right"><strong>Pubblicazione internazionale: </strong></td>
    <td width="51" rowspan="2" align="right">N&deg;</td>
    <td width="207" rowspan="2" align="left"><html:text property="numPubblicazione" size="23" /></td>
  </tr>
  <tr>
    <td width="45" align="right">N&deg;</td>
    <td align="left"><html:text property="numeroDeposito" size="23" />&nbsp; <c:if test="${sessionScope.amministraTrovati}"><img src="images/attach1.gif" width="25" height="25" alt="Documenti allegati" class="imaction" onclick="openDocs(<c:out value="${datiTrovato.nsrif}" />, <c:out value="${not empty datiDepEst.id ? datiDepEst.id : -1}" />, 5);" /></c:if></td>
  </tr>
  <tr>
    <td align="right">data</td>
    <td align="left"><html:text property="dataDepositoX" size="10" />
    	<img src="images/cal04c.gif" align="top" class="imaction" onclick="showCal('dataDeposito');" /></td>
    <td align="right">data</td>
    <td align="left"><html:text property="dataPubblicazioneX" size="10" />
      <img src="images/cal04c.gif" alt="" align="top" class="imaction" onclick="showCal('dataPubblicazione');" /></td>
  </tr>
  <tr>
    <td align="right" valign="middle" rowspan="2"><strong>Estinzione titolarit&agrave;:</strong></td>
    <td align="right">data</td>
    <td align="left"><html:text property="dataAbbandonoX" size="10" />
      <img src="images/cal04c.gif" alt="" align="top" class="imaction" onclick="showCal('dataAbbandono');" />
      <img src="images/wizard.gif" alt="calcola" align="top" class="imaction" onclick="calcolaScadenza();" />
    </td>
    <td align="right">Studio Brevettuale</td>
    <td colspan="2" align="left"><html:select property="denom" style="font-size: 10px; width: 255px;" onchange="popolateSedi(this.value);">
      <html:options collection="denoms" property="value" labelProperty="label" />
    </html:select></td>
  </tr>
  <tr>
    <td align="right">causale</td>
    <td align="left" valign="middle"><html:select property="tipiEstinzioneId">
      <html:options collection="tipiEstinzione" property="value" labelProperty="label" />
      </html:select></td>
    <td align="right">Sede studio</td>
    <td colspan="2" align="left">
		<select name="studioBrevettualeId"></select>
	<%-- html:select property="studioBrevettualeId">
      <html:options collection="sedeInit" property="value" labelProperty="label" />
    </html:select--%>
	</td>
  </tr>
  <tr>
    <td colspan="6" align="right"><img src="images/spacer.gif" width="750" height="1" class="imaction" /></td>
    </tr>
	<tr>
		<td colspan="6" align="center">
	    	<html:textarea property="note" rows="6" cols="100" style="font-size: 11px;" />
		</td>
	</tr>
  <tr>
    <td colspan="6" align="right"><img src="images/spacer.gif" width="750" height="1" class="imaction" /></td>
    </tr>
  <tr>
    <td align="right"><p>Nazionalizzazioni</p>      </td>
    <td colspan="3" align="right">
      <html:select property="paesi" size="5" style="width: 380px;">
        <html:options collection="nazioni" property="value" labelProperty="label" />
      </html:select>
      <br />
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
      <span class="imaction" onclick="doFilter('z');">Z</span>    </td>
    <td align="right">
      <input type="button" name="add" value="&gt;&gt;" onclick="addNation();" />
      <input type="button" name="del" value="&lt;&lt;" onclick="delNation();" />    </td>
    <td align="left"><html:select property="listaNaz" multiple="true" size="5" style="width: 200px;" >
    	<html:options collection="listaNaz" property="value" labelProperty="label" />
    </html:select></td>
  </tr>
  <tr>
    <td align="right">Regionalizzazioni</td>
    <td colspan="3" align="right"><label>
      <select name="regione" size="4" style="width: 250px;">
        <option value="3">European Patent</option>
        <option value="1">AIRIPO Patent</option>
        <option value="2">Eurasian Patent</option>
        <option value="4">OAPI Patent</option>
            </select>
    </label></td>
    <td align="right"><input type="button" name="add2" value="&gt;&gt;" onclick="addRegion();" />
      <input type="button" name="del2" value="&lt;&lt;" onclick="delRegion();" /></td>
    <td align="left"><html:select property="listaReg" multiple="true" size="4" style="width: 200px;" >
	    <html:options collection="listaReg" property="value" labelProperty="label" />
   	</html:select></td>
  </tr>
  <tr>
  	<td colspan="6">
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
    <td colspan="6" align="center">
  		<c:choose>
		    <c:when test="${sessionScope.amministraTrovati}">
		    	<input type="button" name="save" value="Salva" onclick="salva();" />
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
