<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Brevetti CNR - Dettaglio classificazione</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<%
String cl1 = (String)request.getAttribute("cl1");
String cl2 = (String)request.getAttribute("cl2");
String cl3 = (String)request.getAttribute("cl3");
%>
<body>
<table width="100%" border="0" cellpadding="3">
  <tr align="center"> 
    <td width="10%">&nbsp;</td>
    <td width="84%">Dettagli della classificazione</td>
    <td width="6%" align="right" valign="top"> 
      <input type="button" name="Button" value="Chiudi" onclick="self.close()">
    </td>
  </tr>
  <tr> 
    <td align="right">1&deg; livello:</td>
    <td colspan="2"><textarea name="textarea" cols="90" rows="3" readonly="true"><%= cl1 %></textarea></td>
  </tr>
<%
	if(cl2!=null){
		
%>
  <tr> 
    <td align="right">2&deg; livello:</td>
    <td colspan="2"><textarea name="textarea2" cols="90" rows="3" readonly="true"><%= cl2 %></textarea></td>
  </tr>
<%
	}
	if(cl3!=null){
%>
  <tr> 
    <td align="right">3&deg; livello:</td>
    <td colspan="2"><textarea name="textarea3" cols="90" rows="3" readonly="true"><%= cl3 %></textarea></td>
  </tr>
<%
	}
%>
</table>
</body>
</html>
