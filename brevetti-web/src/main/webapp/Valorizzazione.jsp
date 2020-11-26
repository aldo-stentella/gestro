
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>Azioni di valorizzazione</title>
<!-- InstanceEndEditable -->
<c:set var="sessioneUtente" value="${sessionScope.sessioneUtente}" />
<c:set var="dipartimento" value="${sessionScope.dipartimento}" />
<c:set var="MultiDipartimento" value="${sessionScope.multiDipartimento}" />

<!-- InstanceBeginEditable name="head" -->
<script language="javascript" src="scripts/cal2.js?<%=Version.getVersion()%>">
/*
Xin's Popup calendar script-  Xin Yang (http://www.yxscripts.com/)
Script featured on/available at http://www.dynamicdrive.com/
This notice must stay intact for use
*/
</script>
<script language="javascript" src="scripts/cal_conf2.js?<%=Version.getVersion()%>"></script>
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
<script language="JavaScript" type="text/JavaScript">

	var openSession = true;
	var param3='';
	var ns6=document.getElementById&&!document.all;
	var ie4=document.all&&navigator.userAgent.indexOf("Opera")==-1;
	
	function popolate(){
		var nc = document.forms[0].nCluster.value;
		if(nc!=null && nc.length>0){
			var arr = nc.split("|");
			for(var i=0; i<arr.length; i++){
				document.forms[0].cluster.options[i]=new Option(arr[i], arr[i].substring(0,arr[i].indexOf(": ")));
			}
		}
	}
	
	function inizialize(){
		popolate();
        fade('errors',true);
	}

	function rem(){
		var combo = document.forms[0].cluster;
		if(combo.selectedIndex>=0){
			var nsrif = combo.options[combo.selectedIndex].value;
			if(confirm('Conferma l\'eliminazione del trovato '+nsrif+' ?')){
				if(ie4)
					combo.options.remove(combo.selectedIndex);
				else
					combo.options[combo.selectedIndex] = null;
				var re = new RegExp("(^|\|)"+nsrif+"(\||$)", "g");
				document.forms[0].xCluster.value = document.forms[0].xCluster.value.replace(re,'|');
				document.forms[0].updCluster.value = '1';
			}
		}
	}

	function add(){
		var ref = window.open('SelClu.do','popup_val','height=250,width=880,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
		ref.focus();
	}

	function aggiornaCluster(){
		var combo = self.document.forms[0].cluster;
		for(var i=0; i<combo.length; i++){
			if(combo.options[i].value == param3.substring(0,param3.indexOf(":"))){
				return false;
			}
		}
		combo.options.add(new Option(param3, param3.substring(0,param3.indexOf(":"))));
		document.forms[0].xCluster.value+='|'+ param3.substring(0,param3.indexOf(":"));
		document.forms[0].updCluster.value = '1';
		return true;
	}
	
	function getAzienda(){
		var ref = window.open('SelAzienda.do','popup_val','height=250,width=880,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
		ref.focus();
	}
	
	function aggiornaAzienda(){
		self.document.forms[0].aziendaId.value = param3.substring(0,param3.indexOf("|"));
		self.document.forms[0].aziendaNome.value = param3.substring(param3.indexOf("|")+1,param3.indexOf("-"));
		self.document.forms[0].aziendaCitta.value = param3.substring(param3.indexOf("-")+1);
	}

	function doSubmit(){
		var combo = document.forms[0].cluster;
		document.forms[0].nCluster.value = "";
		var pipe = "";
		for(var i=0; i<combo.length; i++){
			document.forms[0].nCluster.value += pipe + combo.options[i].label;
			pipe='|';
		}
		document.forms[0].submit();
	}
</script>
<!-- html:javascript formName="iform"/-->
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
        <div class="titolo"><!-- InstanceBeginEditable name="title" -->Valorizzazione<!-- InstanceEndEditable --></div><br/>
        <!-- InstanceBeginEditable name="content" -->
	<html:form action="Valorizzazione.do" method="POST">
	<html:hidden property="updCluster" />
	<html:hidden property="updDoc" />
	<html:hidden property="id" />
	  <table width="100%" border="0" align="center" cellpadding="5" class="area">
	  <tr>
	    <td width="104" align="right">Azione:</td>
	    <td width="330" align="left">
	    	<html:select property="tipoValorizzazioneId" size="1"><html:options collection="tipiValorizzazione" property="value" labelProperty="label" />
    </html:select>
	        <img src="images/attach1.gif" width="25" height="25"  onclick="openVDocs(document.forms[0].xCluster.value, document.forms[0].id.value, 8);" /></td>
	    <td width="85" align="right">Altra azione:</td>
	    <td width="351" align="left"><html:text property="altro" size="40" /></td>
	  </tr>
	  <tr>
	    <td align="right">Nome progetto:</td>
	    <td align="left"><html:text property="nomeProgetto" size="40" /></td>
	    <td align="right">Referente:</td>
	    <td align="left"><html:text property="referente" size="40" /></td>
	  </tr>
	  <tr>
	    <td align="right">Data inizio:</td>
	    <td align="left"><html:text property="dataInizio" size="10" />
	      <img src="images/cal04c.gif" alt="" class="imaction" onclick="showCal('dataInizio');" align="top" /> (gg/mm/aaaa)</td>
	    <td align="right">Data fine:</td>
	    <td align="left"><html:text property="dataFine" size="10" />
	      <img src="images/cal04c.gif" alt="" class="imaction" onclick="showCal('dataFine');" align="top" /> (gg/mm/aaaa)</td>
	  </tr>
	  <tr>
	    <td colspan="4"><hr /></td>
	    </tr>
	  <tr>
	    <td>Azienda</td>
	    <td colspan="2" align="left">&nbsp;</td>
	    <td align="left">Cluster:</td>
	  </tr>
	  <tr>
	    <td>Nome</td>
	    <td colspan="2" align="left"><html:hidden property="aziendaId" />
	    	<html:text property="aziendaNome" readonly="true" size="40" />&nbsp;<img src="images/rtable.gif" class="imaction" onclick="getAzienda();" /></td>
	    <td rowspan="3" align="left"><select name="cluster" size="6" ></select><html:hidden property="nCluster" /><html:hidden property="xCluster" />
	      <br /><img src="images/addbutt.gif" width="20" height="14" style=" width : 20px;" class="imaction" onclick="add();"/> <img src="images/deletebutt.gif" width="20" height="14" class="imaction" onclick="rem();" /></td>
	  </tr>
	  <tr>
	    <td>Citt&agrave;</td>
	    <td colspan="2" align="left"><html:text property="aziendaCitta" readonly="true" size="40" /></td>
	    </tr>
	  <tr>
	    <td style="visibility: hidden;">Regione / Nazione</td>
	    <td colspan="2" align="left" style="visibility: hidden;"><html:text property="aziendaRegione" disabled="true" size="40" /></td>
	    </tr>
	  <tr>
	    <td colspan="4"><hr /></td>
	    </tr>
	  <tr>
	    <td>
		  	<c:if test="${not empty iform.dataCreazione}">Data creazione:<br />
		  		<html:text property="dataCreazione" size="13" styleClass="departs" readonly="true" /></c:if>
		  	<c:if test="${not empty iform.utcr}">Creato da: <br />
		  		<html:text property="utcr" size="13" styleClass="departs" readonly="true" /></c:if>
		  	<c:if test="${not empty iform.dataVariazione}">Ultima modifica:<br />
		  		<html:text property="dataVariazione" size="13" styleClass="departs" readonly="true" /></c:if>
		  	<c:if test="${not empty iform.utva}">Modificato da:<br />
		  		<html:text property="utva" size="13" styleClass="departs" readonly="true" /></c:if>
	    </td>
	    <td colspan="3" align="left">Note<br/><html:textarea property="note" rows="10" cols="80" /></td>
	  </tr>
	  <tr>
	    <td colspan="4" align="center"><input type="button" value="Salva" onclick="doSubmit()" />  &nbsp;&nbsp;
	      <input type="button" name="annulla" value="Annulla" onclick="javascript:abandon('home.do')" /></td>
	    </tr>
	  </table>
	</html:form> 
	<html:errors />
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
