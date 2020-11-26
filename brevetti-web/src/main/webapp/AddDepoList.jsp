<%@page import="it.cnr.brevetti.util.Version"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<script type="text/javascript" src="scripts/standard.js?<%=Version.getVersion()%>"></script>
<title>Creazione regionalizzazioni e nazionalizzazioni designate</title>
<%
	String act = (String)request.getAttribute("act");
	if(act!=null && act.startsWith("close")){

%>
	<script type="text/javascript" language="javascript1.4">
		window.opener.eval("aggiornaDep();");
		window.close();
	</script>
<%
	}
%>
</head>
<body>
<table width="330" border="1" class="area">
<tr><td><form action="AddDepoList.do" method="post">
<input type="hidden" name="key" value="<%= request.getParameter("key") %>" >
<c:set var="list" value="${sessionScope.nazioniWiz}" />
<c:set var="listR" value="${sessionScope.regioniWiz}" />
<c:set var="vuoto" value="${true}" />
<c:if test="${not empty list}">
	<c:set var="vuoto" value="${false}" />
	Stati designati per le nazionalizzazioni:
	<ul>
	<c:forEach items="${list}" var="stato">
		<li><c:out value="${stato.nome}" /></li>
	</c:forEach> 
	</ul>
</c:if>
<c:if test="${not empty listR}">
	<c:set var="vuoto" value="${false}" />
	Regionalizzazioni designate:
	<ul>
	<c:forEach items="${listR}" var="area">
		<li><c:out value="${area.nome}" /></li>
	</c:forEach> 
	</ul>
</c:if>
<c:if test="${not vuoto}">
	<input type="submit" value="-> Conferma" />&nbsp;
	<input type="button" value="Annulla" onclick="window.close();" />
</c:if>
<c:if test="${vuoto}">
	Nessuno stato o regionalizzazione designati.<br /><br />
	<input type="button" value="Chiudi" onclick="window.close();" />
</c:if>
</form></td></tr>
</table>
</body>
</html>