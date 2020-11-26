<%@ page language="java" %>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.HashMap"%>
<%@page import="java.util.SortedMap"%>
<%@page import="it.cnr.brevetti.ejb.entities.DepEst"%>
<%@page import="java.util.List"%>
<%@page import="it.cnr.brevetti.gas.SessioneUtente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.cnr.brevetti.util.UtilityFunctions"%>
<%@page import="it.cnr.brevetti.trovati.actionForms.TrovatoForm"%>
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
<%
	List<DepEst> listD = (List<DepEst>) session.getAttribute("depositi");
	SortedMap<Long, Integer>mapdep = (SortedMap<Long,Integer>)session.getAttribute("mapdep");
	String[] icon = {"","nation","wipo","eu"};
	SortedMap<Long, Integer> mapDep = (SortedMap<Long, Integer>)session.getAttribute("mapdep");
%>
<script type="text/javascript" language="javascript1.4">
var openSession = false;
var arr = new Array(<%=request.getAttribute("titolari")%>);
var invs = new Array(<%=request.getAttribute("inventori")%>);
function popolate(){
	for(var i=0; i<arr.length; i++){
		document.forms[0].titolari.options[i]=new Option(arr[i],arr[i]);
	}
	for(var i=0; i<invs.length; i++){
		document.forms[0].inventori.options[i]=new Option(invs[i],invs[i]);
	}
}

function inizialize() {
	popolate();
	document.forms[1].nsrif.value = document.forms[0].nsrif.value;
}

function goItem(step){
	if(step==0){
		window.location.href='GesTro.do?lastQBE=y&mode=2';
	}else{
		document.forms[1].nsrif.value = step;
		document.forms[1].mode.value = 0;
		document.forms[1].submit();
	}
}

function stampa(){
	window.open('RepoTrov.jsp','popup_trov','height=800,width=1000,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
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
        <table width='100%' border='0' cellpadding='0' cellspacing='0'>
        <tr id='tabs'><td id='tabon'>&nbsp;</td></tr></table>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td id="area" valign="top"><html:form action="InfoTrov.do" method="post"><table width="100%" border="0">
                <tr>
                  <td><html:hidden property="nextab" />
                  	<html:hidden property="inventore00" />
					<html:hidden property="inventore01" />
					<html:hidden property="inventore02" />
					<html:hidden property="inventore03" />
					<html:hidden property="inventore04" />
					<html:hidden property="inventore05" />
					<html:hidden property="inventore06" />
					<html:hidden property="inventore07" />
					<html:hidden property="inventore08" />
					<html:hidden property="inventore09" />
					<html:hidden property="inventore10" />
					<html:hidden property="inventore11" />
					<html:hidden property="inventore12" />
					<html:hidden property="inventore13" />
					<html:hidden property="inventore14" />
              <table width="96%" border="0" align="center">
              <tr>
                        <td width="416" align="left">NsRIF</td>
                  <td width="6">&nbsp;</td>
                <td width="416" align="left">Data richiesta deposito</td>
              </tr>
              <tr>
                <td align="left"><html:text property="nsrif" size="10" readonly="true" /></td>
                <td>&nbsp;</td>
                <td align="left"><html:text property="dataRichiesta" size="10" readonly="true" /></td>
              </tr>
              <tr>
                <td height="2" colspan="3"><img src="images/spacer.gif" width="2" height="2" /></td>
                </tr>
              <tr>
                <td align="left">Tipo</td>
                <td>&nbsp;</td>
                  <td align="left">D.lgs. n.30/2005</td>
                  </tr>
              <tr>
                <td align="left"><html:text property="tipiTrovatoDescrizione" size="50" readonly="true" /></td>
                <td>&nbsp;</td>
                <td align="left"><html:text property="cessioneDirittiDescrizione" size="50" readonly="true" /></td>
                </tr>
              <tr>
                <td height="2" colspan="3"><img src="images/spacer.gif" width="2" height="2" /></td>
              </tr>
              <tr><td align="left">Referente Titolo</td>
              	  <td>&nbsp;</td>
              	  <td align="left"><bean:message key="tabs.2.invetrov" bundle="${bundle}" /></td>
              </tr>
              <tr><td align="left"><html:text property="utentiNominativo" size="40" /></td>
              	  <td>&nbsp;</td>
              	  <td align="left" rowspan="3"><select name="inventori" size="4"></select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*=riferimento</td>
              </tr>
              <tr>
                <td align="left"><bean:message key="labels.titolo" bundle="${bundle}" /></td>
                <td>&nbsp;</td>
                </tr>
              <tr>
                <td align="left" rowspan="4"> 
                <html:textarea property="titolo" cols="35" rows="6" readonly="true" />
                </td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td height="2" colspan="3"><img src="images/spacer.gif" width="2" height="2" /></td>
              </tr>
              <tr>
                  <td>&nbsp;</td>
                  <td align="left">Titolarit&agrave;</td>
                </tr>
              <tr>
                <td>&nbsp;</td>
                <td align="left" valign="top"><select name="titolari" size="4" >
                </select></td>
              </tr>
              <tr>
                <td height="2" colspan="3"><img src="images/spacer.gif" width="2" height="2" /></td>
                </tr>
                <tr><td colspan="3">
                   <table width="750" align="center" cellpadding="4" cellspacing="0" id="excel">
                      <tr> 
                        <th width="20%" scope="col">Nazione di deposito</th>
                        <th width="3%" scope="col">1&deg;</th>
                        <th width="15%" scope="col">Numero deposito</th>
                        <th width="10%" scope="col">Data</th>
                        <th width="10%">Status</th>
                        <th>Studio</th>
                      </tr>
<%
					int x = 0;
					if(listD!=null && !listD.isEmpty())
						for(Long key : mapdep.keySet()){
							DepEst dep = listD.get(mapdep.get(key));
%>
                      <tr> 
                        <td style="white-space: nowrap;">
                        	<img src="images/<%=icon[dep.getTipoId()]%>.gif" />
                        	<input type="text" name="naz<%=x%>" size="30" readonly="true" value="<%= dep.getStato().getNome() %>" />
                        </td>
                        <td><img src="images/<%=dep.getPrimo()==1?"ok.gif":"null.gif"%>" border="1" /></td>
                        <td><input type="text" name="num<%=x%>" size="20" readonly="true" value="<%=dep.getNumeroDeposito()!=null?dep.getNumeroDeposito():"" %>" /></td>
                        <td><input type="text" name="dat<%=x%>" size="9" readonly="true" value="<%=dep.getDataDeposito()!=null?UtilityFunctions.itForm.format(dep.getDataDeposito()):"" %>" /></td>
                        <td><div class="status-<%=dep.getStatus() %>"><%=dep.getStatus() %></div></td>
                        <td><input type="text" name="stu<%=x%>" size="20" readonly="true" value="<%=dep.getStudioBrevettualeId()!=null&&dep.getStudioBrevettualeId()!=0?dep.getStudioBrevettuale().getDenominazione():"&nbsp;" %>" /></td>
                      </tr>
<%
						x++;
					}
%>
					</table>                
                </td>
                </tr>
               <tr>
                <td align="left">Note</td>
                <td>&nbsp;</td>
                <td align="left">&nbsp;</td>
              </tr>
              <tr>
                <td align="left" valign="top" colspan="3"><html:textarea property="note" cols="85" rows="10" readonly="true" /></td>
              </tr>
              <tr>
                <td height="2" colspan="3"><img src="images/spacer.gif" width="2" height="2" /></td>
              </tr>
            </table></td>
			  </tr>
			</table>
			</html:form>
	        <form method="post" action="GesTro.do">
                <input type="hidden" name="nsrif" />
                <input type="hidden" name="mode" value="1" />
            </form>
			</td>
          </tr>
          <tr>
            <td height="4"><img src="images/spacer.gif" width="4" height="4" /></td>
          </tr>
          <tr>
            <td align="center">
			<c:if test="${sessionScope.amministraTrovati}">
            	<input type="button" value="Modalità modifica" onclick="document.forms[1].submit();" />
			</c:if>
            	<input type="button" value="Ultima ricerca" onclick="goItem(0)" />
            	<input type="button" value="Stampa" onclick="stampa();" />
<%
            		ArrayList nsrifs = (ArrayList)session.getAttribute("nsrifs");
            		if(nsrifs!=null && nsrifs.size()>0){
            			int i = nsrifs.indexOf(((TrovatoForm)session.getAttribute("datiTrovato")).getNsrif());
            			if(i>0){
%>
							<input type="button" value="&lt;&lt;" onclick="goItem(<%=nsrifs.get(i-1) %>)" />
<%            				
            			}
            				
            			if(i>=0 && i<nsrifs.size()-1){
%>
							<input type="button" value="&gt;&gt;" onclick="goItem(<%=nsrifs.get(i+1) %>)" />
<%
            			}
            		}
            	%>
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
