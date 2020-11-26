<%@ page language="java" %>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.cnr.brevetti.ejb.entities.Dipartimento"%>
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/esterna.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<%@page import="it.cnr.brevetti.util.ApplicationProperties"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<!-- InstanceBeginEditable name="doctitle" -->
<title>Brevetti - Home</title>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="head" -->
<script type="text/javascript" language="JavaScript1.4">
var openSession = false;

function inizialize() {
	//do nothing...
}
function doSubmit() {
	if(document.forms[0].dipartimento==null || document.forms[0].dipartimento.selectedIndex>-1){
		document.forms[0].submit();
	}
}
</script>
<!-- InstanceEndEditable -->
<script type="text/javascript" src="scripts/standard.js?<%=Version.getVersion()%>"></script>

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
      <table width="100%" id="menu">
        <tr>
          <td class="menuGroups"><ul>
              <li><a href="javascript:openHelp('#')">Aiuto</a></li>
              <script type="text/javascript">
			  var acerchiata = 64;
              document.write('<li><a href="mailto:oil.support'+String.fromCharCode(acerchiata)+'amministrazione.cnr.it?subject=[segnalazione BREVETTI]">Contattaci</a></li>\n');
              </script>
              <li><a href="https://utenti.cnr.it/" target="_blank">utenti.cnr.it</a></li>
            </ul></td>
        </tr>
        <tr><td class="menuGroups">
        	<img src="images/spacer.gif" width="1" height="335" />&nbsp;</td>
        </tr>
        <tr>
          <td><img src="images/spacer.gif" width="140" height="1" /></td>
        </tr>        
      </table></td>
      <td width="100%" class="pagetable">
        <div class="titolo"><!-- InstanceBeginEditable name="title" -->Accesso<!-- InstanceEndEditable --></div>
        <br/>
        <!-- InstanceBeginEditable name="content" -->
        <html:form action="login" method="post">
        <table width='100%' border='0' cellpadding='0' cellspacing='0'>
        <tr id='tabs'><td id='tabon'>&nbsp;</td></tr></table>
        <table width="100%" border="0" cellpadding="5" id="area">
          <tr>
            <td height="81" colspan="2" align="left" valign="middle"><font color="red"><html:errors/></font></td>
          </tr>
          <tr>
<%
			ArrayList adeps = (ArrayList)request.getAttribute("adeps");
			if(adeps!=null){				
%>
			<td width="35%" height="24" align="right" valign="bottom">
            	Selezionare un dipartimento </td>
            <td width="65%" align="left" valign="bottom">    
				<html:hidden property="login" value="xxxxx"/>
				<html:hidden property="password" value="xxxxx"/>
				<select name="dipartimento" size="6">
<%
				for(Iterator i=adeps.iterator(); i.hasNext();){
					Dipartimento dp = (Dipartimento)i.next();
%>
					<option value='<%=dp.getId() %>' ><%=dp.getDescrizione()%></option>
<%
				}
%>
				</select>
			</td>
<%
			} else {
%>
            <td width="35%" height="24" align="right" valign="bottom">
            	Utente            </td>
            <td width="65%" align="left" valign="bottom">    
			<html:text property="login" size="30" />            </td>
          </tr>
          <tr>
            <td height="24" align="right" valign="top">Password</td>
            <td align="left" valign="top"><html:password property="password" size="30" /></td>
          </tr>
<%
			}
%>
          <tr>
            <td align="right">&nbsp;</td>
            <td align="left" valign="top"><input type="button" onclick="doSubmit()" value="Accedi" /> </td>
          </tr>
        </table>
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
