<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="it.cnr.brevetti.ejb.entities.ClassificazioneInternDep"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Staff</title>
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<script type="text/javascript" language="javascript1.4">
<%
	Integer id = 0;
	String livello3 = "";
	String livello4 = "";
	if(request.getAttribute("act").toString().startsWith("close")){
%>
			window.opener.eval("aggiornaCla();");
			window.close();
<%		
	}else{
		ClassificazioneInternDep cla = (ClassificazioneInternDep)request.getAttribute("classe");
		id = cla.getClassificazioneInternDepId();
		livello3 = cla.getClassificazione().getNome();
		if(cla.getClassificazione().getTipo()==1) livello4 = cla.getAltraClassificazione();
	}
%>
</script>
</head>
<body>
<form action="DelClasse.do" method="post">
<table width="506" border="1" align="center" cellpadding="10" cellspacing="0">
  <tr>
	<td width="482" background="images/header.jpg"><br />
    <br />
    <br /></td>
  </tr>
  <tr>
    <td bgcolor="#FFFFEF">Eliminare la seguente classificazione?
    	<input type="hidden" name="id" value="<%= id %>" />
    </td>
  </tr>
  <tr>
  	<td><i><%= livello3%></i><br/>
  		<%= livello4 %>  	
  	</td>
  </tr>
  <tr>
    <td align="center"><input type="submit" name="button" value="Si" />
     &nbsp; 
    <input type="button" name="button2" value="No" onclick="self.close();" /></td>
  </tr>
</table>
</form>
</body>
</html>
