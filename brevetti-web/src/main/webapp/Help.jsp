<%@page import="it.cnr.brevetti.util.Version"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<title>Brevetti CNR - Gestione trovati</title>
</head>
<body>
<%
	String target = ""+request.getParameter("target");
	if(target.equalsIgnoreCase("staff.html")){
%>
<jsp:include page="staff.html" />
<%		
	}else if(target.equalsIgnoreCase("date_privative")){
%>
<jsp:include page="date_privative.html" />
<%		
	}else if(target.equalsIgnoreCase("diagramma_stati_ID")){
%>
<jsp:include page="diagramma_stati_ID.html" />
<%		
	}else{
%>
<table width="100%" height="100%"><tr><td  align="center">
<br /><br /><br />
<img src="images/working.gif" width="26" height="36" /><br />
  <br />
Help non disponibile<br /><br /><input type="button" value="Chiudi" onclick="window.close()" /><br />
</td>
</tr></table>
<%		
		
	}
%>
</body>
</html>