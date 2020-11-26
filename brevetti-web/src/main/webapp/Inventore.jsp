
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="it.cnr.brevetti.ejb.entities.Inventore"%>
<%@page import="it.cnr.brevetti.inventori.actionForms.InventoreForm"%>
<%@page import="java.util.Date"%>
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/generic2.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<%@page import="it.cnr.brevetti.util.ApplicationProperties"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<script type="text/javascript" src="scripts/standard.js?<%=Version.getVersion()%>"></script>
<!-- InstanceBeginEditable name="doctitle" -->
<title>Scheda inventore</title>
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
<script language="JavaScript" type="text/JavaScript">

<%
	String action = ""+request.getAttribute("action");
	InventoreForm iform = (InventoreForm)request.getAttribute("iform");
	boolean isCnr = false;
	boolean isNew = false;
	if(!action.startsWith("chiudi")) {
			isCnr = (iform.getTipo()!=null && iform.getTipo().startsWith("CNR"));
			isNew = (iform.getId()== null || iform.getId().intValue()==0);
%> 
	var openSession = true;
	function inizialize(){
		<%=(isNew?"document.forms[0].status.value=1;":"") %>
		<%= (isCnr?"":"displayEnable();")%>
        document.forms[0].invia.value = "<%=(isNew?"Inserisci":"Salva") %>";
        fade('errors',true);
	}
<%

	 } else {
%> 
	var openSession = false;
	function inizialize(){
		for (var i = 0; i < document.forms[0].elements.length; i++) {
		    if (document.forms[0].elements[i].type == "text" || document.forms[0].elements[i].type == "select") {
		        document.forms[0].elements[i].disabled = true;
		    }
	        document.forms[0].invia.style.visibility = "hidden";
	        document.forms[0].disable.style.visibility = "hidden";
	        document.forms[0].cancel.value = "Chiudi";
		}
	}
		
<%
		 
	 }
%>

	function displayEnable(){
		if(document.forms[0].status.value==1){
			GetObj('enable').style.display = 'block';
			GetObj('disable').style.display = 'none';
		} else {
			GetObj('enable').style.display = 'none';
			GetObj('disable').style.display = 'block';
		}
	}
	function switchEnable(){
		if(document.forms[0].status.value==1){
			document.forms[0].status.value=0;
		} else {
			document.forms[0].status.value=1;
		}
		displayEnable();
	}
	function on_submit(){

			document.forms[0].act.value='<%= action %>';
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
        <div class="titolo"><!-- InstanceBeginEditable name="title" -->Gestione inventori<!-- InstanceEndEditable --></div><br/>
        <!-- InstanceBeginEditable name="content" -->
<html:form action="AddInv.do" method="post">
  <input type="hidden" name="act" />
  <table width="800" border="1" cellspacing="15" class="area">
    <tr> 
      <td colspan="4"><table><tr><td width="90%" align="center"><b>Dettagli dell&acute;inventore</b></td><td width="10%" align="right" style="white-space: nowrap;">
<% 		if(isCnr){
%>
	      	(matricola <%= iform.getMatricolaCnr() %> ):
<%		
			if(iform.getDataCessazione()!=null && iform.getDataCessazione().before(new Date())){
%>
	      	| <span class='special'>Dipendente non pi&ugrave; in servizio</span>
<%					
			}
		}
%>
	      	<span id="enable" style="display: none;"><img src="images/ok.gif" align="top" /> Abilitato</span>
	      	<span id="disable" style="display: none;"><img src="images/no.gif" align="top" /> Disabilitato</span>
			</td></tr></table>
      		<html:hidden property="id"/><html:hidden property="matricolaCnr" />
      		<html:hidden property="status"/><html:hidden property="codAnagraficaSigla"/>
      		<html:hidden property="codTerzoSigla"/>
      		<html:hidden property="sezioniId"/>
      		<html:hidden property="titolo"/>
      		<html:hidden property="istitutiId"/>
      		<html:hidden property="oldSystem"/>
      	 </td>
    </tr>
    <tr> 
      <td width="111" class="label">Cognome</td>
      <td width="242" align="center"> <html:text property="cognome" size="40" readonly="<%=(isCnr) %>" /></td>
      <td width="112" class="label">Nome</td>
      <td width="242" align="center"> <html:text property="nome" size="40" readonly="<%=(isCnr) %>" /></td>
    </tr>
    <tr> 
      <td class="label">Tipo inventore</td>
      <td align="center">
      		<html:select property="tipo" size="1" style="width: 265px;">
    			<html:options collection="tipi" property="value" labelProperty="label" />
			</html:select>  
      </td>
      <td class="label">Indirizzo</td>
      <td align="center"><html:text property="indirizzo" size="40" readonly="<%=(isCnr) %>" /></td>
    </tr>
    <tr>
      <td class="label">Citt&agrave;</td>
      <td align="center"><html:text property="citta" size="40" readonly="<%=(isCnr) %>" /></td>
      <td class="label">CAP</td>
      <td align="center"><html:text property="cap" size="40" readonly="<%=(isCnr) %>" /></td>
    </tr>
    <tr> 
      <td class="label">Telefono</td>
      <td align="center"><html:text property="telefono" size="40" readonly="<%=(isCnr) %>" /></td>
      <td class="label">E-mail</td>
      <td align="center"> <html:text property="email" size="40" /></td>
    </tr>
    <tr> 
      <td class="label">Fax</td>
      <td align="center"><html:text property="fax" size="40" readonly="<%=(isCnr) %>" /></td>
      <td class="label">Cellulare</td>
      <td align="center"> <html:text property="cellulare" size="40" readonly="<%=(isCnr) %>" /></td>
    </tr>
<%	if(!isCnr){
%>
    <tr> 
      <td class="label">Ente 
        di riferimento</td>
      <td align="center"> <html:text property="enteRecapito" size="40" /></td>
      <td class="label">Struttura dell'ente</td>
      <td align="center"> <html:text property="strutturaEnteRecapito" size="40" /></td>
    </tr>
<%	}

%>
    <tr>
      <td class="label">Banca</td>
      <td align="center"><html:text property="banca" readonly="<%=(isCnr) %>" size="40" /></td>
      <td class="label">n&deg; C/C</td>
      <td align="center"><html:text property="cc" size="40" readonly="<%=(isCnr) %>" /></td>
    </tr>
    <tr>
      <td class="label">ABI</td>
      <td align="center"><html:text property="abi" size="40" readonly="<%=(isCnr) %>" /></td>
      <td class="label">CAB</td>
      <td align="center"><html:text property="cab" size="40" readonly="<%=(isCnr) %>" /></td>
    </tr>
    <tr> 
      <td colspan="4" align="center"> <input type="button" name="invia" value="Salva" onclick="on_submit();" /> 
        &nbsp;&nbsp;&nbsp;<input type="button" name="disable" value="Abilita/disabilita" onclick="switchEnable()" <%=(isCnr||isNew?"disabled='true'":"") %> /> 
        &nbsp;&nbsp;&nbsp;<input type="button" name="cancel" value="Annulla" onclick="abandon('home.do')" />
      </td>
    </tr>
  </table>
<html:errors />
</html:form>
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
