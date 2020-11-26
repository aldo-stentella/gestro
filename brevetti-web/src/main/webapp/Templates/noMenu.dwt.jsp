<%@ page language="java" %>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="it.cnr.brevetti.util.ApplicationProperties"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" 	href="../css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<!-- TemplateBeginEditable name="doctitle" -->
<title>Documento senza titolo</title>
<!-- TemplateEndEditable -->
<!-- TemplateBeginEditable name="head" -->
<script type="text/javascript" language="JavaScript1.4">
var openSession = false;

function goTab(ntab) {
    alert('tab selezionato: ' + ntab);
}

function inizialize() {
	//do something...
}
</script>
<!-- TemplateEndEditable -->
<script type="text/javascript" src="../scripts/standard.js?<%=Version.getVersion()%>"></script>
</head>
<body onload="inizialize()">
<div class="center">
  <table width="100%" class="pagetable">
    <tr>
      <td id="header" align="left"><img src="../images/cnr_logo.gif" width="900" height="100" border="0" /></td>
    </tr>
    <tr>
      <td width="100%" class="pagetable">
        <div class="titolo"><!-- TemplateBeginEditable name="title" -->Errore nell'applicazione<!-- TemplateEndEditable --></div>
        <br/>
        <!-- TemplateBeginEditable name="content" -->
         <table width='100%' border='0' cellpadding='0' cellspacing='0'>
        <tr id='tabs'><td id='tabon'>&nbsp;</td></tr></table>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td id="area">&nbsp;</td>
          </tr>
          <tr>
            <td height="3"><img src="../images/spacer.gif" width="3" height="3" /></td>
          </tr>
          <tr>
            <td align="center">&nbsp;</td>
          </tr>
        </table>
        <!-- TemplateEndEditable -->      </td>
    </tr>
    <tr>
      <td id="footer">&copy;2007-<%=ApplicationProperties.getInstance().getCopyright()%> <a href="http://www.cnr.it" target="_blank">Consiglio Nazionale delle Ricerche</a> | <a href="http://www.si.cnr.it" target="_blank"><%=ApplicationProperties.getInstance().getDept()%></a> | <a href="javascript:openHelp('staff.html')">Staff</a></td>
    </tr>
  </table>
</div>
</body>
</html>
