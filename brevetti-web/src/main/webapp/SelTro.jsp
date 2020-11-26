<%@ page language="java" %>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.cnr.brevetti.ejb.entities.Trovato"%>
<%@page import="java.util.ArrayList"%>
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
<script type="text/javascript" language="JavaScript1.4">
var openSession = false;


function inizialize() {
	//do nothing...
}
function goTab(ntab) {
	document.forms[0].nextab.value=ntab;
	document.forms[0].method='GET';
	document.forms[0].submit();
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
        <div class="titolo"><!-- InstanceBeginEditable name="title" -->Gestione dei Trovati<!-- InstanceEndEditable --></div><br/>
        <!-- InstanceBeginEditable name="content" -->
	        <table width="100%" border="0" cellpadding="0" cellspacing="0">
	          <tr>
	            <td>
		    	<bean:tabs />
		      	</td>
	          </tr>
	          <tr> 
	            <td id="area" valign="top">
	            	<html:form method="post" action="GesTro.do">
	            	<html:hidden property="nextab"  />
	            	<html:hidden property="mode"  />
	            	<table cellpadding="8" width="100%">
	            		<tr>
	            			<td align="center">
	            				Risultati della ricerca
	            			</td>
	            		</tr>
<%
	List list = (List)request.getAttribute("list");
	if(list==null || list.size()==0){
%>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td class="errorMsg">Nessun trovato presente in archivio.</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td><input type='button' value='Chiudi' onclick='window.location="home.do";' />
<%
	} else {
		ArrayList nsrifs = new ArrayList();
%>
	            		<tr><td>
							<select name="nsrif" size="20" style="font-family: Arial, Helvetica, sans-serif; font-size: 11px;width: 900px;">
<%
		for(Iterator i = list.iterator(); i.hasNext();){
			Trovato t = (Trovato)i.next();
			nsrifs.add(t.getNsrif());
			out.write("<option value='"+t.getNsrif()+"'>"+t.getNsrif()+" - "+t.getTitolo()+"</option>\n");
		}
		session.setAttribute("nsrifs",nsrifs);
%>
							</select>
						</td></tr>
						<tr><td>
							(<%= list.size() %> elementi) &nbsp;<input type="button" value="Apri" onclick="document.forms[0].submit();" />
<%
	}
%>
							&nbsp;&nbsp;&nbsp;<input type="button" value="Nuova ricerca" onclick="goTab(2);"/>
						</td></tr>
             		</table>
					</html:form>
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
