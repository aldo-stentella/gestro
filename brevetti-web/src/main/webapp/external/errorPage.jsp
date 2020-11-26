<%@ page language="java" %>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.Date"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.io.PrintStream"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.StringReader"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<%@page import="it.cnr.brevetti.util.UtilityFunctions"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" 	href="../css/standard.css?<%=Version.getVersion()%>" type="text/css" />

<title>Dati non disponibili</title>


<bean:define id="exception" name="org.apache.struts.action.EXCEPTION"
  type="java.lang.Throwable" />
<script type="text/javascript" language="JavaScript1.4">
var openSession = false;
<% 
	exception.printStackTrace();
	ByteArrayOutputStream ostr = new ByteArrayOutputStream();
	exception.printStackTrace(new PrintStream(ostr));
	BufferedReader d = new BufferedReader(new StringReader(ostr.toString()));
	String p= "";
	String q= "";
	while((p=d.readLine())!=null){
		q+= (p+"\n");
	}
	UtilityFunctions.sendMail(q, "oil.support@amministrazione.cnr.it", "[BREVETTI] Exception on public pages");
%>

function inizialize() {
	//do something...
}
</script>

</head>
<body onload="inizialize()">
<div class="center">
  <table width="901" align="center" class="pagetable">
    <tr>
      <td id="header" align="left"><img src="../images/headerUVR.png" border="0" /></td>
    </tr>
    <tr>
      <td width="900" class="pagetable">
        <div class="titolo">Dati non disponibili</div>
        <br/>
        <br />
        Al momento non &egrave; possibile accedere al portale. <br/>
        <br />
        <br/>
        Si prega di ritentare pi&ugrave; tardi.<br/>        <br/>        </td>
    </tr>
  </table>
</div>
</body>
</html>
