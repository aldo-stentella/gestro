<%@page import="it.cnr.brevetti.util.Version"%>
<html>
<head>
<title>Index refresh</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script type="text/javascript" src="scripts/standard.js?<%=Version.getVersion()%>"></script>
<script type="text/javascript" language="javascript1.4">
function inizialize() {
<%
	String action = (String)request.getAttribute("action");
	if(action!=null && action.startsWith("open")){
%>
		var pag = window.open('home.do', 'pag', 'menubar=no,toolbar=no,location=no,scrollbars=yes,resizable=yes');
		if (pag==null || pag.name != 'pag') {
      		window.alert('Un blocco popup ha impedito l\'apertura della finestra principale.\nDisattivare il blocco popup per il dominio <%=request.getLocalName() %>');
      		GetObj('riapri').style.visibility = 'visible';
		} else {
			pag.focus();
		}
<%
	}
	if(action!=null && action.startsWith("close")){
%>
		window.opener.location='home.do';
		window.opener.focus();
		self.close();
<%
	}
	if(action!=null && action.startsWith("switch")){
%>
		window.opener.document.forms[0].submit();
		window.opener.focus();
		self.close();
<%
	}
%>
}
</script>
<style type="text/css">
<!--
body {
	background-color: #cccccc;
}
-->
</style></head>

<body onload="inizialize();">
<form action="login.do" method="post">
<input type="hidden" name="login" value="xxxxx"/>
<input type="hidden" name="password" value="xxxxx"/>
</form>
<p align="center" style="visibility: hidden" id="riapri" ><br /><br /><br />
	<input type="button" value="Apri nuovamente" onclick="this.style.visibility='hidden'; window.open('home.do', 'pag', 'menubar=no,toolbar=no,location=no,scrollbars=yes,resizable=yes');" />
</p>
</body>
</html>
