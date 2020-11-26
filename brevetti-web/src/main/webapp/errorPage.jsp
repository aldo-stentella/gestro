<%@ page language="java" %>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.Date"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.io.PrintStream"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.StringReader"%>
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/noMenu.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<%@page import="it.cnr.brevetti.util.ApplicationProperties"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<!-- InstanceBeginEditable name="doctitle" -->
<title>Documento senza titolo</title>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="head" -->
<bean:define id="exception" name="org.apache.struts.action.EXCEPTION"
  type="java.lang.Throwable" />
<script type="text/javascript" language="JavaScript1.4">
var openSession = false;
<% 
	ByteArrayOutputStream ostr = new ByteArrayOutputStream();
	exception.printStackTrace();
	exception.printStackTrace(new PrintStream(ostr));
	BufferedReader d = new BufferedReader(new StringReader(ostr.toString()));
	String p= "";
	String q= "";
	int i = 0;
	while((p=d.readLine())!=null){
		q+= (p+"%0D%0A");
		if(15 == i++)
		    break;
}
%>
function sendMail(){
	window.open("mailto:oil.support@amministrazione.cnr.it?subject=[BREVETTI Exception]&body=Exception catched at '<%=new Date()%>':%0D%0A%0D%0A<%=q.replaceAll("\"","\\\\\"")%>");
}

function annulla(){
	if(window.opener.name != 'pag'){		//pagina principale
		window.location='home.do';
	} else {								//pagina di popup
		self.close();
	}
}

function inizialize() {
	//do something...
}
</script>
<!-- InstanceEndEditable -->
<script type="text/javascript" src="scripts/standard.js?<%=Version.getVersion()%>"></script>
</head>
<body onload="inizialize()">
<div class="center">
  <table width="100%" class="pagetable">
    <tr>
      <td id="header" align="left"><img src="images/cnr_logo.gif" width="900" height="100" border="0" /></td>
    </tr>
    <tr>
      <td width="100%" class="pagetable">
        <div class="titolo"><!-- InstanceBeginEditable name="title" -->Errore nell'applicazione<!-- InstanceEndEditable --></div>
        <br/>
        <!-- InstanceBeginEditable name="content" -->
         <table width='100%' border='0' cellpadding='0' cellspacing='0'>
        <tr id='tabs'><td id='tabon'>&nbsp;</td></tr></table>
        <table width="100%" border="0" cellpadding="10" cellspacing="0">
          <tr>
            <td valign="middle" align="left" id="area"><p>Errore di sistema:</p>
              <blockquote>
                <p><font color="red"><html:errors /></font></p>
                <p>&nbsp;</p>
              </blockquote>
              <p>Scegliere tra le seguenti opzioni:</p>
              <blockquote>
                <p>
                  <img src="images/envelope.gif" width="22" height="21" align="absmiddle" class="imaction" onclick="sendMail();" /> inviare la segnalazione di errore al gruppo di sviluppo</p>
                <p><img src="images/home.gif" width="21" height="22" align="absmiddle" class="imaction" onclick="annulla();" /> annullare l'operazione</p>
              </blockquote>              </td>
          </tr>
          <tr>
            <td height="3"><img src="images/spacer.gif" width="3" height="3" /></td>
          </tr>
          <tr>
            <td align="center">&nbsp;</td>
          </tr>
        </table>
        <!-- InstanceEndEditable -->      </td>
    </tr>
    <tr>
      <td id="footer">&copy;2007-<%=ApplicationProperties.getInstance().getCopyright()%> <a href="http://www.cnr.it" target="_blank">Consiglio Nazionale delle Ricerche</a> | <a href="http://www.si.cnr.it" target="_blank"><%=ApplicationProperties.getInstance().getDept()%></a> | <a href="javascript:openHelp('staff.html')">Staff</a></td>
    </tr>
  </table>
</div>
</body>
<!-- InstanceEnd --></html>
